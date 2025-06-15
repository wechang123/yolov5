package DanParking.config;

import DanParking.entity.Role;
import DanParking.entity.User;
import DanParking.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitYoloAccount implements ApplicationRunner {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${yolo.email}")
    private String email;
    @Value("${yolo.password}")
    private String password;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!userJpaRepository.existsByEmail("yolo")){
            User yolo = new User(
                    "yolo",
                    email,
                    passwordEncoder.encode(password),
                    Role.ROLE_YOLO
            );
            userJpaRepository.save(yolo);
        }
    }
}
