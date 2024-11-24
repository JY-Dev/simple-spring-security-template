package com.jydev.backend.presentation.test;

import com.jydev.backend.security.auth.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "Test API",
        description = """
                인증 테스트 API 입니다."""
)
@RequestMapping("/v1/test")
public interface TestApi {

    @Operation(
            summary = "인증 테스트 API",
            description = """
                    인증을 테스트 합니다."""
    )
    @GetMapping
    ResponseEntity<Long> test(AuthenticatedUser authentication);
}
