package DanParking.service;

import DanParking.entity.Settings;
import DanParking.entity.User;
import DanParking.dto.request.SettingsUpdateDTO;
import DanParking.dto.response.SettingsResponseDTO;
import DanParking.repository.SettingsJpaRepository;
import DanParking.repository.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SettingsService {
    @Autowired
    private SettingsJpaRepository settingsJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    public SettingsResponseDTO getMySettings(Long userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+" 에 해당하는 user 없음."));
        Settings settings = settingsJpaRepository.findByUser(user)
                .orElseThrow(()-> new EntityNotFoundException("userId: "+userId+" 에게 Settings가 존재하지 않음."));
        return SettingsResponseDTO.fromEntity(settings);
    }

    @Transactional
    public SettingsResponseDTO updateSettings(Long userId, SettingsUpdateDTO settingsUpdateDTO) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+" 에 해당하는 user 없음."));
        Settings settings = settingsJpaRepository.findByUser(user)
                .orElseThrow(()-> new EntityNotFoundException("userId: "+userId+" 에게 Settings가 존재하지 않음."));
        settings.updateSettings(
                settingsUpdateDTO.getParkingSort(),
                settingsUpdateDTO.getCongestionAlert(),
                settingsUpdateDTO.getAvailableAlert(),
                settingsUpdateDTO.getAutoLaunch(),
                settings.getTheme(),
                settings.getFontSize(),
                settings.getLanguage());
        return SettingsResponseDTO.fromEntity(settings);
    }
}
