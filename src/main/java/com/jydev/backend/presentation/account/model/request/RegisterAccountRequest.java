package com.jydev.backend.presentation.account.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RegisterAccountRequest(

        @Schema(
                description = """
                        로그인 아이디
                        """,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String userId,

        @Schema(
                description = """
                        로그인 비밀번호
                        """,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String password,

        @Schema(
                description = """
                        사용자 이름
                        """,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String name
) {
}
