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
            String token = jwtUtil.createToken(user.getUserName());

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
        // 1. 유효성 검증
        validateUsername(userDto.getUsername());
        validatePassword(userDto.getPassword());

        // 2. 중복 체크
        if (userRepository.findByUserName(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 이름 사용 중");
        }

        // 3. User 엔티티 생성 및 저장
        User user = User.builder()
                .userName(userDto.getUsername())
                .userPasswd(userDto.getPassword())
                .delYn("N")
                .regId(userDto.getUsername()) // 예제용
                .modId(userDto.getUsername()) // 예제용
                .build();

        User savedUser =  userRepository.save(user);

        // 4. 성공 메시지 반환
        return UserDto.builder()
                .id(savedUser.getUserId())
                .username(savedUser.getUserName())
                .password(savedUser.getUserPasswd())
                .build();
    }

    private void validateUsername(String username) {
        if (username == null || !username.matches("^[a-z0-9]{4,10}$")) {
            throw new IllegalArgumentException("유효하지 않은 사용자 이름입니다. " +
                    "소문자(a-z)와 숫자(0-9)로 구성된 4~10자여야 합니다.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || !password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,15}$")) {
            throw new IllegalArgumentException("유효하지 않은 비밀번호입니다. " +
                    "대소문자(a-z, A-Z), 숫자(0-9), 특수문자(@#$%^&+=)로 구성된 8~15자여야 합니다.");
        }
    }
}
