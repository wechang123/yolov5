package DanParking.service;

import DanParking.entity.Role;
import DanParking.entity.Settings;
import DanParking.entity.User;
import DanParking.dto.request.UserCreateDTO;
import DanParking.dto.request.UserUpdateDTO;
import DanParking.dto.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DanParking.repository.RefreshTokenJpaRepository;
import DanParking.repository.SettingsJpaRepository;
import DanParking.repository.UserJpaRepository;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private SettingsJpaRepository settingsJpaRepository;
    @Autowired
    private RefreshTokenJpaRepository refreshTokenJpaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void register(UserCreateDTO userCreateDTO) {
        if(userJpaRepository.findByEmail(userCreateDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        User user = new User(
                userCreateDTO.getName(),
                userCreateDTO.getEmail(),
                passwordEncoder.encode(userCreateDTO.getPassword()),
                Role.ROLE_USER
        );
        Settings settings = new Settings(user);
        user.setSettings(settings);
        settingsJpaRepository.save(settings);
        userJpaRepository.save(user);
    }

    public UserResponseDTO getUserInfo(Long userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+"에 해당하는 user 찾을 수 없음."));
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getCreatedAt());
    }

    @Transactional
    public UserResponseDTO updateUserInfo(Long userId, UserUpdateDTO userUpdateDTO) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+"에 해당하는 user 찾을 수 없음."));
        user.updateUserInfo(userUpdateDTO.getName(), passwordEncoder.encode(userUpdateDTO.getPassword()));
        return new UserResponseDTO(user.getId(), user.getEmail(),user.getName(), user.getCreatedAt());
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+"에 해당하는 user 찾을 수 없음."));
        refreshTokenJpaRepository.deleteByUser(user);
        settingsJpaRepository.deleteByUser(user);
        userJpaRepository.delete(user);
    }
}
