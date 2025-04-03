package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.data.model.User;
import com.byteBuilders.TrueCaller.dtos.LoginRequest;
import com.byteBuilders.TrueCaller.dtos.UserRequest;
import com.byteBuilders.TrueCaller.dtos.UserResponse;

public interface UserService {
    public UserResponse registerUser(UserRequest userRequest);
    public long get_user_count() ;
    public boolean UserLogin(LoginRequest loginRequest);


    public User findUserByEmail(String email);
}
