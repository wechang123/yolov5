package DanParking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoriteParkingLotResponseDTO {
    private Long id;
    private Long parkingLotId;
    private String name;
    private String location;
    private Long totalSlots;
    private Long availableSlots;
}
