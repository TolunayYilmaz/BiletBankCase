package com.flight.spring.flightbooking.soap.providera;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class SearchRequest {


    @XmlElement(name = "origin", namespace = "")
    private String origin;

    @XmlElement(name = "destination", namespace = "")
    private String destination;

    @XmlElement(name = "departureDate", namespace = "")
    private String departureDate;

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

}