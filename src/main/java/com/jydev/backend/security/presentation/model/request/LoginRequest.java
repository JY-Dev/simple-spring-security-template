package com.jydev.backend.security.presentation.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @Schema(
                description = """
                        사용자 아이디
                        """
        )
        @NotBlank
        String userId,

        @Schema(
                description = """
                        사용자 비밀번호
                        """
        )
        @NotBlank
        String password
) {
}
