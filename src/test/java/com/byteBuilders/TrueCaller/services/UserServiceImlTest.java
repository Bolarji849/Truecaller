package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.data.model.User;
import com.byteBuilders.TrueCaller.data.repository.UserRepository;
import com.byteBuilders.TrueCaller.dtos.LoginRequest;
import com.byteBuilders.TrueCaller.dtos.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImlTest {
@Autowired
UserService userService;
@Autowired
UserRepository userRepository;

    @BeforeEach
    public void setup(){
        userRepository.deleteAll();



    }
    @Test
    public void testUserCanRegister (){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("jay@gmail.com");
        userRequest.setPassword("1234");

        userService.registerUser(userRequest);
        assertEquals(1, userService.get_user_count());


    }

    @Test
    public void testUserCanLogin (){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("jay@gmail.com");
        userRequest.setPassword("1234");

        userService.registerUser(userRequest);
        assertEquals(1, userService.get_user_count());


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jay@gmail.com");
        loginRequest.setPassword("1234");

        boolean UserIslogedIn = userService.UserLogin(loginRequest);
        assertTrue(UserIslogedIn);
    }
    @Test
    public void testUserCannotLoginWithEmptyDetails (){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("jay@gmail.com");
        userRequest.setPassword("1234");

        userService.registerUser(userRequest);
        assertEquals(1, userService.get_user_count());


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("");


        assertThrows(RuntimeException.class, () -> userService.UserLogin(loginRequest));
    }


}