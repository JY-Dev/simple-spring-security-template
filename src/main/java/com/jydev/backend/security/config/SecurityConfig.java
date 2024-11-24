package com.jydev.backend.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jydev.backend.security.SecurityErrorCodeAuthenticationEntryPoint;
import com.jydev.backend.security.auth.login.CustomUsernamePasswordAuthenticationFilter;
import com.jydev.backend.security.auth.login.LoginSuccessHandler;
import com.jydev.backend.security.auth.token.JwtAuthenticationProvider;
import com.jydev.backend.security.auth.token.TokenAuthenticationConverter;
import com.jydev.backend.security.token.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Slf4j
@Configuration
public class SecurityConfig {

    private static final String LOGIN_URL = "/v1/account/login";

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    OrRequestMatcher ignoredMatchers() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/v3/api-docs/**"),
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/v1/account/signup", HttpMethod.POST.name()),
                new AntPathRequestMatcher(LOGIN_URL, HttpMethod.POST.name())
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AbstractAuthenticationProcessingFilter loginAuthenticationFilter,
            AuthenticationFilter accessTokenAuthenticateFilter,
            AuthenticationEntryPoint authenticationEntryPoint,
            OrRequestMatcher ignoredMatchers
    ) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(hc -> hc.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(ignoredMatchers).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(loginAuthenticationFilter)
                .addFilterBefore(accessTokenAuthenticateFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ehc -> ehc.authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }

    @Bean
    public AbstractAuthenticationProcessingFilter loginAuthenticationFilter(
            JwtHelper jwtHelper,
            UserDetailsService userDetailsService,
            ObjectMapper objectMapper
    ) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        var authenticationManager = new ProviderManager(authProvider);

        var authenticateFilter = new CustomUsernamePasswordAuthenticationFilter(authenticationManager, objectMapper);
        authenticateFilter.setFilterProcessesUrl(LOGIN_URL);

        var loginSuccessHandler = new LoginSuccessHandler(jwtHelper, objectMapper);
        authenticateFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        return authenticateFilter;
    }

    @Bean
    public AuthenticationFilter accessTokenAuthenticateFilter(
            JwtHelper jwtHelper,
            AuthenticationEntryPoint authenticationEntryPoint
    ) {

        var authenticationManger = new ProviderManager(
                new JwtAuthenticationProvider(jwtHelper)
        );
        var authenticationConverter = new TokenAuthenticationConverter();

        AuthenticationFilter filter = new AuthenticationFilter(
                authenticationManger,
                authenticationConverter
        );

        filter.setRequestMatcher(new NegatedRequestMatcher(new AntPathRequestMatcher(LOGIN_URL)));

        filter.setSuccessHandler((req, res, auth) -> log.debug("토큰 인증 성공 : {}", auth.getPrincipal()));
        filter.setFailureHandler(new AuthenticationEntryPointFailureHandler(authenticationEntryPoint));

        return filter;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return new SecurityErrorCodeAuthenticationEntryPoint(objectMapper);
    }
}
