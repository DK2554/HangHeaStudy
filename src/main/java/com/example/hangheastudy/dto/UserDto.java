package com.example.hangheastudy.dto;

import com.example.hangheastudy.common.Timestamped;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class UserDto extends Timestamped {
    private Long id;
    private String username;
    private String password;
}
