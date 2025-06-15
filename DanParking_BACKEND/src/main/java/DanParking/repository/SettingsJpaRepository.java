package DanParking.repository;

import DanParking.entity.Settings;
import DanParking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettingsJpaRepository extends JpaRepository<Settings, Long> {
    void deleteByUser(User user);

    Optional<Settings> findByUser(User user);
}
