package DanParking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String email;
    private String name;
    private LocalDateTime createdAt;
}