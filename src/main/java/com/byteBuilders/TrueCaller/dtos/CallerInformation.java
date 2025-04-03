package com.byteBuilders.TrueCaller.dtos;

import lombok.Data;

@Data
public class CallerInformation {
    private boolean valid;
    private String country;
    private String location;
    private String carrier;
    private String phoneNumber;
}
