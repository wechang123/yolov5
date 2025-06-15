package DanParking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"USER\"")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Settings settings;

    @OneToMany(mappedBy = "user")
    private List<FavoriteParkingLot> favoriteParkingLotList;

    @Builder
    public User(String name, String email, String password, Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void updateUserInfo(String name, String password){
        this.name = name;
        this.password = password;
    }
}
