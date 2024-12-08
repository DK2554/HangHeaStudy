package com.example.hangheastudy.service;

import com.example.hangheastudy.dto.NoticeDto;
import com.example.hangheastudy.dto.UserDto;
import com.example.hangheastudy.entity.User;
import com.example.hangheastudy.repository.UserRepository;
import com.example.hangheastudy.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUserNameAndUserPasswd(username, password);
    }


    public Map<String, Object> getUser(UserDto userDto) {

        Optional<User> authUser = this.authenticate(userDto.getUsername(), userDto.getPassword());

        Map<String, Object> resultMap = new HashMap<>();

        if (authUser.isPresent()) {
            // 인증 성공
            User user = authUser.get();

            // JWT 생성
            String token = jwtUtil.generateToken(user.getUserName());

            resultMap.put("msg", "로그인 성공");
            resultMap.put("token", token);
            resultMap.put("user", Map.of(
                    "id", user.getUserId(),
                    "username", user.getUserName()
            ));
        } else {
            // 인증 실패
            resultMap.put("msg", "로그인 실패");
            resultMap.put("error", "Invalid username or password");
        }

        return resultMap;

    }

    @Transactional
    public UserDto sigup(UserDto userDto) {
        if (userRepository.findByUserName(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자이름 사용중");
        }
        User user = User.builder()
                .userName(userDto.getUsername())
                .userPasswd(userDto.getPassword())
                .delYn("N")
                .regId("system") // 예제용, 실제 값으로 대체
                .modId("system") // 예제용, 실제 값으로 대체
                .build();

        User savedUser = userRepository.save(user);

        // User → UserDto 변환
        return UserDto.builder()
                .id(savedUser.getUserId())
                .username(savedUser.getUserName())
                .password(savedUser.getUserPasswd())
                .build();
    }
}
