package com.jydev.backend.security.presentation.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginResponse(
        @Schema(
                description = """
                        유저 ID
                        """
        )
        long userId,

        @Schema(
                description = """
                        AccessToken \n
                        만료 : 30분
                        """
        )
        String accessToken
) {
}
