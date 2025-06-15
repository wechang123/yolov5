package DanParking.repository;

import DanParking.entity.FavoriteParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteParkingLotJpaRepository extends JpaRepository<FavoriteParkingLot, Long> {
    Optional<FavoriteParkingLot> findByParkingLotId(Long parkingLotId);
}
