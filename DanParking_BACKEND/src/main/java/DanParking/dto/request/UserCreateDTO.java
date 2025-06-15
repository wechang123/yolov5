package DanParking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateDTO {
    private String email;
    private String password;
    private String name;
}
