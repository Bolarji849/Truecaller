package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.data.model.User;
import com.byteBuilders.TrueCaller.data.repository.UserRepository;
import com.byteBuilders.TrueCaller.dtos.LoginRequest;
import com.byteBuilders.TrueCaller.dtos.UserRequest;
import com.byteBuilders.TrueCaller.dtos.UserResponse;
import com.byteBuilders.TrueCaller.exception.InvalidCredentialsException;
import com.byteBuilders.TrueCaller.exception.UserAlreadyExist;
import com.byteBuilders.TrueCaller.exception.UserAlreadyLoggedInException;
import com.byteBuilders.TrueCaller.exception.UserNotFoundException;
import com.byteBuilders.TrueCaller.utills.SecurityValidation;
import com.byteBuilders.TrueCaller.utills.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIml implements UserService {


    @Autowired
    UserMapper mapper;
    @Autowired
    UserRepository userRepository;


    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new UserAlreadyExist(userRequest.getEmail() + " Already Exists");
        }
        User user = mapper.mapToRequest(userRequest);
        userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("REGISTRATION SUCCESSFUL!");
        return userResponse;
    }
    public long get_user_count() {
        return userRepository.findAll().size();
    }

    @Override
    public boolean UserLogin(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new IllegalArgumentException("Login request cannot be null");
        }

        User foundUser = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (foundUser.isLoggedIn()) {
            throw new UserAlreadyLoggedInException("User already logged in");
        }
        return SecurityValidation.validatePassword(loginRequest.getPassword(), foundUser.getPassword());


    }





    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }
}
