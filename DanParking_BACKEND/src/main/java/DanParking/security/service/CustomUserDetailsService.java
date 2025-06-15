package DanParking.security.service;

import DanParking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import DanParking.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findByEmail(username)
                .orElseThrow(()-> new IllegalArgumentException("email: "+username+"에 해당하는 유저 찾을 수 없음"));
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+"에 해당하는 유저 찾을 수 없음"));
        return new CustomUserDetails(user);
    }
}
