package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.configuration.NumberVerify;
import com.byteBuilders.TrueCaller.configuration.WebClientConfig;
import com.byteBuilders.TrueCaller.dtos.CallerInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class NumberVerification implements PhoneNumberValidation{
    @Autowired
    WebClient.Builder webClientConfig;
    @Autowired
    NumberVerify numberVerify;

    public Mono<CallerInformation> ValidatePhoneNumber(String phoneNumber) {
        return WebClient.builder()
                .baseUrl("http://apilayer.net/api")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/validate")
                        .queryParam("access_key", numberVerify.getApiNum())
                        .queryParam("number", phoneNumber)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .doOnNext(response -> System.out.println("Received response: " + response))
                .map(response -> mapResponseToCallerInformation(response, phoneNumber));

    }
    private CallerInformation mapResponseToCallerInformation(Map<String, Object> response, String phoneNumber) {
        boolean isValid = (Boolean) response.getOrDefault("valid", Boolean.FALSE);
        if (isValid) {
            CallerInformation callerInfo = new CallerInformation();
            callerInfo.setValid((Boolean) response.getOrDefault("valid", Boolean.FALSE));
            callerInfo.setCountry((String) response.getOrDefault("country_name", "null"));
            callerInfo.setLocation((String) response.getOrDefault("location", "null"));
            callerInfo.setCarrier((String) response.getOrDefault("carrier", "null"));
            callerInfo.setPhoneNumber((String) response.getOrDefault("number", "null"));
            return callerInfo;
        }
        CallerInformation callerInfo = new CallerInformation();
        callerInfo.setValid(false);
        callerInfo.setCountry("unknown");
        callerInfo.setLocation("unknown");
        callerInfo.setCarrier("unknown");
        callerInfo.setPhoneNumber("unknown");
        return callerInfo;
    }



    }




