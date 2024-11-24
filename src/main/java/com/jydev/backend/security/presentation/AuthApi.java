package com.jydev.backend.security.presentation;

import com.jydev.backend.core.web.ErrorResponse;
import com.jydev.backend.security.presentation.model.request.LoginRequest;
import com.jydev.backend.security.presentation.model.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "AUTH API",
        description = """
                사용자 인증 관련 API 입니다.
                """
)
@RequestMapping("/v1/account")
public interface AuthApi {

    @Operation(
            summary = "로그인 API",
            description = "로그인을 합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상"),
            @ApiResponse(responseCode = "401", description = "인증 오류",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    LoginResponse login(@Valid @RequestBody LoginRequest request);
}
