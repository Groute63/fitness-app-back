package com.ssau.study.authentication.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String login;
    private String password;

    public UserDto() {
    }

    public UserDto(String login, String password) {
        this.password = password;
        this.login = login;
    }
}
