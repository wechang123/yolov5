package DanParking.controller;

import DanParking.dto.request.LoginRequestDTO;
import DanParking.dto.response.ApiResponseDTO;
import DanParking.dto.response.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import DanParking.security.service.CustomUserDetails;
import DanParking.service.AuthService;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public ApiResponseDTO<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ApiResponseDTO.success(authService.login(loginRequestDTO));
    }

    @PostMapping("/auth/token")
    public ApiResponseDTO<TokenResponseDTO> refreshAccessToken(@RequestHeader("Authorization") String refreshTokenHeader) {
        return ApiResponseDTO.success(authService.tokenRefresh(refreshTokenHeader));
    }

    @PostMapping("/auth/logout")
    public ApiResponseDTO<String> logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        authService.logout(userDetails.getUserId());
        return ApiResponseDTO.success("로그아웃이 완료되었습니다.");
    }
}
