package com.jydev.backend.presentation.account;

import com.jydev.backend.core.web.ErrorResponse;
import com.jydev.backend.presentation.account.model.request.RegisterAccountRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(
        name = "Acccount API",
        description = """
        계정 관련 API 입니다."""
)
@RequestMapping("/v1/account")
public interface AccountApi {

    @Operation(
            summary = "회원 가입",
            description = """
                    회원 가입을 합니다."""
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "정상"),
            @ApiResponse(responseCode = "400",
                    description = """
                            * 에러코드 \n\
                            - A01002 : 로그인 아이디 중복
                            - A01003 : 이미 가입된 사용자
                            """,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    void registerAccount(@RequestBody @Valid RegisterAccountRequest request);
}
