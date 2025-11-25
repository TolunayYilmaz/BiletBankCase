package com.flight.spring.flightbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // Sadece Provider A'nın manuel paketini eklendi
        marshaller.setPackagesToScan("com.flight.spring.flightbooking.soap.providera",
                "com.flight.spring.flightbooking.soap.providerb");
        return marshaller;
    }
}