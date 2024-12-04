package com.example.hangheastudy.service;

import com.example.hangheastudy.dto.UserDto;
import com.example.hangheastudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        return userRepository.findByUserNameAndUserPasswd(username, password).isPresent();
    }


    public Map<String, Object> getUser(UserDto userDto) {

        boolean authUser = this.authenticate(userDto.getUsername(), userDto.getPassword());
        Map<String, Object> resultMap = new HashMap<>();

        if(authUser){
            resultMap.put("msg", "로그인 성공");
        }else{
            resultMap.put("msg", "로그인 실패");
        }
        return resultMap;

    }
}
