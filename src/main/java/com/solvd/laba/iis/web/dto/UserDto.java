package com.solvd.laba.iis.web.dto;

import com.solvd.laba.iis.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
