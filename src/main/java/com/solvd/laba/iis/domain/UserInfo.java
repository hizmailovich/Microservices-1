package com.solvd.laba.iis.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;

}