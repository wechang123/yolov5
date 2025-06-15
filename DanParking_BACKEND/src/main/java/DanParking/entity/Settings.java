package DanParking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Settings {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String parkingSort = "distance";

    private Boolean congestionAlert = false;

    private Boolean availableAlert = false;

    private Boolean autoLaunch = false;

    private String theme = "light";

    private String fontSize = "medium";

    private String language = "ko";

    @Builder
    public Settings(User user){
        this.user = user;
    }

    public void updateSettings(String parkingSort, Boolean congestionAlert, Boolean availableAlert, Boolean autoLaunch,
                               String theme, String fontSize, String language) {
        this.parkingSort = parkingSort;
        this.congestionAlert = congestionAlert;
        this.availableAlert = availableAlert;
        this.autoLaunch = autoLaunch;
        this.theme = theme;
        this.fontSize = fontSize;
        this.language = language;
    }
}
