package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.dtos.CallerInformation;
import reactor.core.publisher.Mono;

public interface PhoneNumberValidation {

    public Mono<CallerInformation> ValidatePhoneNumber(String phoneNumber);

}
