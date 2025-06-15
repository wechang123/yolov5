package DanParking.controller;

import DanParking.dto.request.SettingsUpdateDTO;
import DanParking.dto.response.ApiResponseDTO;
import DanParking.dto.response.SettingsResponseDTO;
import DanParking.security.service.CustomUserDetails;
import DanParking.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController {
    @Autowired
    private SettingsService settingsService;

    @GetMapping("/settings")
    public ApiResponseDTO<SettingsResponseDTO> getMySettings(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ApiResponseDTO.success(settingsService.getMySettings(userDetails.getUserId()));
    }

    @PutMapping("/settings/{settingsId}")
    public ApiResponseDTO<SettingsResponseDTO> updateMySettings(
            @RequestBody SettingsUpdateDTO settingsUpdateDTO,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return ApiResponseDTO.success(settingsService.updateSettings(userDetails.getUserId(), settingsUpdateDTO));
    }
}
