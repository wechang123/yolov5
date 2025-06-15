package DanParking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParkingSlot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parkingLotId;
    private Long slotNumber;
    private Boolean isAvailable;

    @Builder
    public ParkingSlot(Long parkingLotId, Long slotNumber, Boolean isAvailable){
        this.parkingLotId = parkingLotId;
        this.slotNumber = slotNumber;
        this.isAvailable = isAvailable;
    }

    public void updateParkingSlot(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
