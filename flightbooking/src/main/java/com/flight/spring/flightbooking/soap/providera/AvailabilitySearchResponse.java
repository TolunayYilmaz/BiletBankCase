package com.flight.spring.flightbooking.soap.providera;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "availabilitySearchResponse", namespace = "http://service.flightprovidera.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class AvailabilitySearchResponse {
    //xml dönüş tagini yazmayınca datayı eşlemiyor
    @XmlElement(name = "return")
    private SearchResult returnData;


}