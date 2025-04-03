package com.byteBuilders.TrueCaller.contollers;

import com.byteBuilders.TrueCaller.dtos.CallerInformation;
import com.byteBuilders.TrueCaller.services.PhoneNumberValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RequestMapping("/validate")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class NumberVerificationController {
    @Autowired
    PhoneNumberValidation phoneNumberValidation;
    @GetMapping("{number}")
    public Mono<CallerInformation> ValidatePhoneNumber(@PathVariable("number") String phoneNumber){
        return phoneNumberValidation.ValidatePhoneNumber(phoneNumber);

    }

}
