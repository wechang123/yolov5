package DanParking.dto.response;

import DanParking.entity.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ParkingSlotResponseDTO {
    private Long id;
    private Long parkingLotId;
    private Long slotNumber;
    private Boolean isAvailable;

    public static ParkingSlotResponseDTO fromEntity(ParkingSlot parkingSlot){
        return new ParkingSlotResponseDTO(
                parkingSlot.getId(),
                parkingSlot.getParkingLotId(),
                parkingSlot.getSlotNumber(),
                parkingSlot.getIsAvailable());
    }
    public static List<ParkingSlotResponseDTO> fromEntityList(List<ParkingSlot> savedParkingSlots) {
        List<ParkingSlotResponseDTO> parkingSlotResponseDTOList = new ArrayList<>();
        for(ParkingSlot parkingSlot : savedParkingSlots){
            parkingSlotResponseDTOList.add(ParkingSlotResponseDTO.fromEntity(parkingSlot));
        }
        return parkingSlotResponseDTOList;
    }
}
