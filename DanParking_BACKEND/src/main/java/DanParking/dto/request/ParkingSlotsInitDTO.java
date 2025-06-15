package DanParking.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ParkingSlotsInitDTO {
    List<ParkingSlotRequestDTO> slots;
}
