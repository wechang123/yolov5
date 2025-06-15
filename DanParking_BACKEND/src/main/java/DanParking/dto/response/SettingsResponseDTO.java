package DanParking.dto.response;

import DanParking.entity.Settings;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettingsResponseDTO {
    private Long id;
    private String parkingSort;
    private Boolean congestionAlert;
    private Boolean availableAlert;
    private Boolean autoLaunch;
    private String theme;
    private String fontSize;
    private String language;

    public static SettingsResponseDTO fromEntity(Settings settings){
        return new SettingsResponseDTO(
                settings.getId(),
                settings.getParkingSort(),
                settings.getCongestionAlert(),
                settings.getAvailableAlert(),
                settings.getAutoLaunch(),
                settings.getTheme(),
                settings.getFontSize(),
                settings.getLanguage()
        );
    }
}
