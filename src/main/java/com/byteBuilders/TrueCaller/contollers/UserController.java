package com.byteBuilders.TrueCaller.contollers;

import com.byteBuilders.TrueCaller.dtos.LoginRequest;
import com.byteBuilders.TrueCaller.dtos.UserRequest;
import com.byteBuilders.TrueCaller.dtos.UserResponse;
import com.byteBuilders.TrueCaller.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    
    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
        
    }
    @PostMapping("/login")
    public  boolean userLogin(@RequestBody LoginRequest loginRequest){
      return  userService.UserLogin(loginRequest);
        
    }

}
