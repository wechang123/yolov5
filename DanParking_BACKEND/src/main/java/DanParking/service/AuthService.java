package DanParking.service;

import DanParking.entity.RefreshToken;
import DanParking.entity.User;
import DanParking.dto.request.LoginRequestDTO;
import DanParking.dto.response.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DanParking.repository.RefreshTokenJpaRepository;
import DanParking.repository.UserJpaRepository;
import DanParking.security.util.JwtTokenUtil;

@Service
public class AuthService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private RefreshTokenJpaRepository refreshTokenJpaRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userJpaRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("email: "+loginRequestDTO.getEmail()+"에 해당하는 user 없음"));
        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new BadCredentialsException("비밀번호가 올바르지 않습니다");
        }
        refreshTokenJpaRepository.deleteByUser(user);
        String accessToken = jwtTokenUtil.generateAccessToken(user.getId());
        String refreshToken = jwtTokenUtil.generateRefreshToken(user.getId());
        refreshTokenJpaRepository.save(new RefreshToken(user, refreshToken));
        return new TokenResponseDTO(accessToken, refreshToken);
    }

    @Transactional
    public TokenResponseDTO tokenRefresh(String token) {
        if(token==null || !token.startsWith("Bearer ")){
            throw new IllegalArgumentException("잘못된 refreshToken 입니다.");
        }
        token = token.replace("Bearer ","");
        jwtTokenUtil.validateRefreshToken(token);
        Long userId = jwtTokenUtil.extractUserId(token);
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId "+userId+"에 해당하는 user 없음"));
        refreshTokenJpaRepository.deleteByUser(user);
        String accessToken = jwtTokenUtil.generateAccessToken(userId);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userId);
        refreshTokenJpaRepository.save(new RefreshToken(user, refreshToken));
        return new TokenResponseDTO(accessToken, refreshToken);
    }

    @Transactional
    public void logout(Long userId) {
        refreshTokenJpaRepository.deleteByUserId(userId);
    }
}
