package com.vikas.linkedin.userService.controller;


import com.vikas.linkedin.userService.dto.LoginRequestDto;
import com.vikas.linkedin.userService.dto.SignupRequestDto;
import com.vikas.linkedin.userService.dto.UserDto;
import com.vikas.linkedin.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        UserDto userDto=userService.signUp(signupRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);

    }

    @PostMapping("/Login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){

        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.CREATED);

    }




}
