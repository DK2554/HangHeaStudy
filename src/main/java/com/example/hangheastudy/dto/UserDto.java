package com.example.hangheastudy.dto;

import com.example.hangheastudy.common.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto extends Timestamped {
    private Long id;
    private String username;
    private String password;
}
