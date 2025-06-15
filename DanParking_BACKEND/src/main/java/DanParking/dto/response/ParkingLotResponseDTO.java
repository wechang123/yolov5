package DanParking.dto.response;

import DanParking.entity.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ParkingLotResponseDTO {
    private Long id;
    private String name;
    private String location;
    private Long totalSlots;
    private Long availableSlots;

    public static ParkingLotResponseDTO fromEntity(ParkingLot parkingLot){
        return new ParkingLotResponseDTO(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getLocation(),
                parkingLot.getTotalSlots(),
                parkingLot.getAvailableSlots()
        );
    }

    public static List<ParkingLotResponseDTO> fromEntityList(List<ParkingLot> parkingLotList){
        List<ParkingLotResponseDTO> parkingLotResponseDTOList = new ArrayList<>();
        for(ParkingLot parkingLot: parkingLotList){
            parkingLotResponseDTOList.add(ParkingLotResponseDTO.fromEntity(parkingLot));
        }
        return parkingLotResponseDTOList;
    }
}
