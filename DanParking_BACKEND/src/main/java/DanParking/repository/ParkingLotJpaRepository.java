package DanParking.repository;

import DanParking.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotJpaRepository extends JpaRepository<ParkingLot, Long> {
}
