package DanParking.controller;

import DanParking.dto.request.UserCreateDTO;
import DanParking.dto.request.UserUpdateDTO;
import DanParking.dto.response.ApiResponseDTO;
import DanParking.dto.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import DanParking.security.service.CustomUserDetails;
import DanParking.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("/users/register")
    public ApiResponseDTO<?> register(@RequestBody UserCreateDTO userCreateDTO) {
        userService.register(userCreateDTO);
        return ApiResponseDTO.success("회원가입이 완료되었습니다.");
    }

    // 현재 로그인한 사용자 정보 조회
    @GetMapping("/users/info")
    public ApiResponseDTO<UserResponseDTO> getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserResponseDTO userInfo = userService.getUserInfo(userDetails.getUserId());
        return ApiResponseDTO.success(userInfo);
    }

    // 현재 로그인한 사용자 정보 수정
    @PutMapping("/users/info")
    public ApiResponseDTO<UserResponseDTO> updateUserInfo(
            @RequestBody UserUpdateDTO userUpdateDTO,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        UserResponseDTO updatedUser = userService.updateUserInfo(userDetails.getUserId(), userUpdateDTO);
        return ApiResponseDTO.success(updatedUser);
    }

    // 현재 로그인한 사용자 계정 삭제
    @DeleteMapping("/users/info")
    public ApiResponseDTO<?> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.deleteUser(userDetails.getUserId());
        return ApiResponseDTO.success("회원 탈퇴 성공");
    }
}
