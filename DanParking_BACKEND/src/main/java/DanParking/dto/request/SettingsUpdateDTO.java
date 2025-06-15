package DanParking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettingsUpdateDTO {
    private String parkingSort;
    private Boolean congestionAlert;
    private Boolean availableAlert;
    private Boolean autoLaunch;
    private String theme;
    private String fontSize;
    private String language;
}
