package com.solvd.laba.iis.web.controller;

import com.solvd.laba.iis.domain.UserInfo;
import com.solvd.laba.iis.domain.security.JwtRefreshRequest;
import com.solvd.laba.iis.domain.security.JwtRequest;
import com.solvd.laba.iis.domain.security.JwtResponse;
import com.solvd.laba.iis.service.UserService;
import com.solvd.laba.iis.web.dto.UserInfoDto;
import com.solvd.laba.iis.web.dto.security.JwtRefreshRequestDto;
import com.solvd.laba.iis.web.dto.security.JwtRequestDto;
import com.solvd.laba.iis.web.dto.security.JwtResponseDto;
import com.solvd.laba.iis.web.dto.validation.OnCreateGroup;
import com.solvd.laba.iis.web.dto.validation.OnUpdateGroup;
import com.solvd.laba.iis.web.mapper.UserInfoMapper;
import com.solvd.laba.iis.web.mapper.security.JwtRefreshRequestMapper;
import com.solvd.laba.iis.web.mapper.security.JwtRequestMapper;
import com.solvd.laba.iis.web.mapper.security.JwtResponseMapper;
import com.solvd.laba.iis.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final UserInfoMapper userInfoMapper;
    private final JwtRequestMapper jwtRequestMapper;
    private final JwtResponseMapper jwtResponseMapper;
    private final JwtRefreshRequestMapper jwtRefreshRequestMapper;

    @GetMapping
    public List<UserInfoDto> getAll() {
        List<UserInfo> users = userService.retrieveAll();
        return userInfoMapper.entityToDto(users);
    }

    @GetMapping("/{id}")
    public UserInfoDto getById(@PathVariable Long id) {
        UserInfo user = userService.retrieveById(id);
        return userInfoMapper.entityToDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDto create(@RequestBody @Validated(OnCreateGroup.class) UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoMapper.dtoToEntity(userInfoDto);
        userInfo = userService.create(userInfo);
        userInfoDto = userInfoMapper.entityToDto(userInfo);
        return userInfoDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping
    public UserInfoDto update(@RequestBody @Validated(OnUpdateGroup.class) UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoMapper.dtoToEntity(userInfoDto);
        userInfo = userService.update(userInfo);
        userInfoDto = userInfoMapper.entityToDto(userInfo);
        return userInfoDto;
    }

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody JwtRequestDto jwtRequestDto) {
        JwtRequest jwtRequest = jwtRequestMapper.dtoToEntity(jwtRequestDto);
        JwtResponse jwtResponse = authenticationService.login(jwtRequest);
        return jwtResponseMapper.entityToDto(jwtResponse);
    }

    @PostMapping("/refresh")
    public JwtResponseDto refresh(@RequestBody JwtRefreshRequestDto jwtRefreshRequestDto) {
        JwtRefreshRequest jwtRefreshRequest = jwtRefreshRequestMapper.dtoToEntity(jwtRefreshRequestDto);
        JwtResponse jwtResponse = authenticationService.refresh(jwtRefreshRequest);
        return jwtResponseMapper.entityToDto(jwtResponse);
    }

}
