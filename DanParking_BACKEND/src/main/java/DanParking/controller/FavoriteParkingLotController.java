package DanParking.controller;

import DanParking.dto.request.FavoriteParkingLotCreateDTO;
import DanParking.dto.response.ApiResponseDTO;
import DanParking.dto.response.FavoriteParkingLotResponseDTO;
import DanParking.security.service.CustomUserDetails;
import DanParking.service.FavoriteParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteParkingLotController {
    @Autowired
    private FavoriteParkingLotService favoriteParkingLotService;

    @PostMapping("/favorite-parking-lots")
    public ApiResponseDTO<FavoriteParkingLotResponseDTO> createFavoriteParkingLot(
            @RequestBody FavoriteParkingLotCreateDTO favoriteParkingLotCreateDTO,
            @AuthenticationPrincipal CustomUserDetails userDetails){
        return ApiResponseDTO.success(favoriteParkingLotService.saveFavoriteParkingLot(userDetails.getUserId(), favoriteParkingLotCreateDTO));
    }

    @GetMapping("/favorite-parking-lots")
    public ApiResponseDTO<List<FavoriteParkingLotResponseDTO>> getMyFavoriteParkingLots(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ApiResponseDTO.success(favoriteParkingLotService.findMyFavoriteParkingLots(userDetails.getUserId()));
    }

    @DeleteMapping("/favorite-parking-lots/{favoriteParkingLotId}")
    public ApiResponseDTO<String> deleteFavoriteParkingLot(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long favoriteParkingLotId){
        favoriteParkingLotService.deleteFavoriteParkingLot(userDetails.getUserId(), favoriteParkingLotId);
        return ApiResponseDTO.success("즐겨찾는 주차장 삭제 완료");
    }
}
