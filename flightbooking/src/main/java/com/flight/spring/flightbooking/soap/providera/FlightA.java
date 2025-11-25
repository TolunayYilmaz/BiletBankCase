package com.flight.spring.flightbooking.soap.providera;

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
    private String departuredatetime;
    private String arrivaldatetime;
    private BigDecimal price;


}