package DanParking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingSlotRequestDTO {
    private Long parkingLotId;
    private Long slotNumber;
    private Boolean isAvailable;
}
