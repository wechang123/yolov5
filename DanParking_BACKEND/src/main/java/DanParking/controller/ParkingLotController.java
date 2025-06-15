package DanParking.controller;

import DanParking.dto.request.ParkingLotsInitDTO;
import DanParking.dto.response.ApiResponseDTO;
import DanParking.dto.response.ParkingLotResponseDTO;
import DanParking.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PreAuthorize("hasRole('YOLO')")
    @PostMapping("/parking-lots/init")
    public ApiResponseDTO<List<ParkingLotResponseDTO>> initializeParkingLots(@RequestBody ParkingLotsInitDTO parkingLotsInitDTO){
        return ApiResponseDTO.success(parkingLotService.saveParkingLots(parkingLotsInitDTO));
    }

    @GetMapping("/parking-lots")
    public ApiResponseDTO<List<ParkingLotResponseDTO>> getAllParkingLots(){
        return ApiResponseDTO.success(parkingLotService.findAllParkingLots());
    }
}
