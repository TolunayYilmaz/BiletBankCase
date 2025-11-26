package com.flight.spring.flightbooking.soap.providerb.dto;

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
public class SearchResultB {
    private boolean hasError;
    private List<FlightB> flightOptions;
    private String errorMessage;
}
