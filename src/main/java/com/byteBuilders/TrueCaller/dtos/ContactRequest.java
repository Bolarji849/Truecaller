package com.byteBuilders.TrueCaller.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ContactRequest {
    private String firstName;
    private String lastName;
   @Indexed(unique = true)
    private String phoneNumber;
//    private String message;
}
