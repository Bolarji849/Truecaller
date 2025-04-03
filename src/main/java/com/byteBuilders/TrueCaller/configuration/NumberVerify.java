package com.byteBuilders.TrueCaller.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Getter
@PropertySource("classpath:.env")
@Configuration
public class NumberVerify {
    @Value("${API_ACCESS_KEY}")
    private String apiNum;
}

