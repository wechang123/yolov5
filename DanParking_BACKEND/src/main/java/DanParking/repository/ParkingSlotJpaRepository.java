package DanParking.repository;

import DanParking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotJpaRepository extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByParkingLotId(Long parkingLotId);
    Optional<ParkingSlot> findByParkingLotIdAndSlotNumber(Long parkingLotId, Long slotNumber);
}
