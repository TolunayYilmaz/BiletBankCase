package com.flight.spring.flightbooking.soap.providera.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@XmlRootElement(name = "availabilitySearch", namespace = "http://service.flightprovidera.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class AvailabilitySearchA {
    @XmlElement(name = "arg0")
    private SearchRequestA request;

}