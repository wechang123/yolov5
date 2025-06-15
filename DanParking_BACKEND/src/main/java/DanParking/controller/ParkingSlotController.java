package DanParking.controller;

import DanParking.dto.request.ParkingSlotRequestDTO;
import DanParking.dto.request.ParkingSlotsInitDTO;
import DanParking.dto.response.ApiResponseDTO;
import DanParking.dto.response.ParkingSlotResponseDTO;
import DanParking.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingSlotController {
    @Autowired
    private ParkingSlotService parkingSlotService;

    @PreAuthorize("hasRole('YOLO')")
    @PostMapping("/parking-slots/init")
    public ApiResponseDTO<List<ParkingSlotResponseDTO>> initializeParkingSlots(@RequestBody ParkingSlotsInitDTO parkingSlotsInitDTO){
        return ApiResponseDTO.success(parkingSlotService.saveParkingSlots(parkingSlotsInitDTO));
    }

    @GetMapping("/parking-slots/all")
    public ApiResponseDTO<List<ParkingSlotResponseDTO>> getAllParkingSlots(){
        return ApiResponseDTO.success(parkingSlotService.findAllParkingSlots());
    }

    @GetMapping("/parking-slots/{parkingLotId}")
    public ApiResponseDTO<List<ParkingSlotResponseDTO>> getParkingSlotsByParkingLotId(@PathVariable Long parkingLotId){
        return ApiResponseDTO.success(parkingSlotService.findParkingSlotsByParkingLotId(parkingLotId));
    }

    @PreAuthorize("hasRole('YOLO')")
    @PutMapping("/parking-slots")
    public ApiResponseDTO<ParkingSlotResponseDTO> updateParkingSlot(@RequestBody ParkingSlotRequestDTO parkingSlotRequestDTO){
        return ApiResponseDTO.success(parkingSlotService.updateParkingSlot(parkingSlotRequestDTO));
    }
}
