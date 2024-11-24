package com.jydev.backend.presentation.test;

import com.jydev.backend.security.auth.AuthenticatedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestControllerApi implements TestApi {

    @Override
    public ResponseEntity<Long> test(AuthenticatedUser authentication) {
        log.info("test id {}", authentication.getPrincipal());
        return ResponseEntity.ofNullable(authentication.getPrincipal());
    }
}
