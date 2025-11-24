package com.flight.spring.flightbooking.soap.providera;

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
public class SearchResult {

    private boolean hasError;
    private String errorMessage;
    private List<Flight> flightOptions;


}