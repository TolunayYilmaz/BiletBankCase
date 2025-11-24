package com.flight.spring.flightbooking.soap.providera;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Flight {



    @XmlElement(name = "flightNo", namespace = "")
    private String flightNo;

    @XmlElement(name = "origin", namespace = "")
    private String origin;

    @XmlElement(name = "destination", namespace = "")
    private String destination;

    @XmlElement(name = "departuredatetime", namespace = "")
    private String departuredatetime;

    @XmlElement(name = "arrivaldatetime", namespace = "")
    private String arrivaldatetime;

    @XmlElement(name = "price", namespace = "")
    private BigDecimal price;


}