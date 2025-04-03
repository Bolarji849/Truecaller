package com.byteBuilders.TrueCaller.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isLoggedIn;
//    private List<Contact> contactsList =new ArrayList<>();
}
