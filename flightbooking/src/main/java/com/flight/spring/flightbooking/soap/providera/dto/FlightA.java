package com.flight.spring.flightbooking.soap.providera.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class FlightA {

    private String flightNo;
    private String origin;
    private String destination;
    @XmlElement(name = "departuredatetime")
    private String departureDateTime;
    @XmlElement(name = "arrivaldatetime")
    private String arrivalDateTime;
    private BigDecimal price;


}