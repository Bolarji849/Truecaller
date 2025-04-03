package com.byteBuilders.TrueCaller.utills;

import com.byteBuilders.TrueCaller.data.model.User;
import com.byteBuilders.TrueCaller.dtos.LoginRequest;
import com.byteBuilders.TrueCaller.dtos.UserRequest;
import com.byteBuilders.TrueCaller.dtos.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {




    public User mapToRequest(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        String password = SecurityValidation.hashPassword(userRequest.getPassword());
        user.setPassword(password);
        return user;
    }
    public UserResponse mapToUserRequest(String user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("REGISTRATION SUCCESSFUL!");
        return userResponse;
    }
    public void mapToLoginRequest(LoginRequest loginRequest) {
        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword());

    }
}
