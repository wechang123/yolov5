package DanParking.dto.request;

import DanParking.dto.response.ParkingLotResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ParkingLotsInitDTO {
    List<ParkingLotRequestDTO> parkingLots;
}
