package com.flight.spring.flightbooking.soap.providera.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@XmlRootElement(name = "return")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class SearchResultA {

    private boolean hasError;
    private String errorMessage;
    private List<FlightA> flightOptions;


}