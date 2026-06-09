package com.vikas.linkedin.userService.service;

import com.vikas.linkedin.userService.dto.LoginRequestDto;
import com.vikas.linkedin.userService.dto.SignupRequestDto;
import com.vikas.linkedin.userService.dto.UserDto;
import com.vikas.linkedin.userService.entity.User;
import com.vikas.linkedin.userService.exception.BadRequestException;
import com.vikas.linkedin.userService.exception.ResourceNotFoundException;
import com.vikas.linkedin.userService.repo.UserRepository;
import com.vikas.linkedin.userService.util.Bcrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    public UserDto signUp(SignupRequestDto signupRequestDto) {
          log.info("Signup a user with email:{}",signupRequestDto.getEmail());

          boolean exists=userRepository.existsByEmail(signupRequestDto.getEmail());

          if(exists){
              throw new BadRequestException("User Already Exists");
          }
          User user=modelMapper.map(signupRequestDto,User.class);
          user.setPassword(Bcrypt.hash(signupRequestDto.getPassword()));
          user=userRepository.save(user);

          return modelMapper.map(user,UserDto.class);

    }

    public String login(LoginRequestDto loginRequestDto) {
        log.info("Login request for user with email:{}",loginRequestDto.getEmail());
        User user=userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User not found with email: "+loginRequestDto.getEmail()));
        boolean isPasswordMatch=Bcrypt.match(loginRequestDto.getPassword(),user.getPassword());
        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect Credentials");
        }
        return jwtService.generateAccessToken(user);

    }
}
