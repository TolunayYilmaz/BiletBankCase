package com.flight.spring.flightbooking.soap.providera.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "availabilitySearchResponse", namespace = "http://service.flightprovidera.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class AvailabilitySearchResponseA {
    //xml dönüş tagini yazmayınca datayı eşlemiyor
    @XmlElement(name = "return")
    private SearchResultA returnData;


}