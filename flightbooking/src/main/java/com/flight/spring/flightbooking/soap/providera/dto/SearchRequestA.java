package com.flight.spring.flightbooking.soap.providera.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class SearchRequestA {

    private String origin;
    private String destination;
    private String departureDate;

}