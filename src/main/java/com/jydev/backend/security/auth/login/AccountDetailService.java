package com.jydev.backend.security.auth.login;

import com.jydev.backend.domain.auth.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var account = accountRepository.findByLoginId(username).orElseThrow();

        return new LoginAccount(
                account.getLoginId(),
                account.getPassword(),
                account.getUserId()
        );
    }

}

