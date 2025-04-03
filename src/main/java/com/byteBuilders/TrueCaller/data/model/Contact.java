package com.byteBuilders.TrueCaller.data.model;

import lombok.Data;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Contact {

    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String phoneNumber;

    private boolean blocked;
}
