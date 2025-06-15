package DanParking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingLotRequestDTO {
    private String name;
    private String location;
    private Long totalSlots;
    private Long availableSlots;
}
