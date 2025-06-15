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
public class ParkingLot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Long totalSlots;
    private Long availableSlots;

    @Builder
    public ParkingLot(String name, String location, Long totalSlots, Long availableSlots){
        this.name = name;
        this.location = location;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
    }
}
