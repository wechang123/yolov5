package DanParking.repository;

import DanParking.entity.RefreshToken;
import DanParking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {
    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.user = :user")
    void deleteByUser(User user);
    void deleteByUserId(Long userId);
}
