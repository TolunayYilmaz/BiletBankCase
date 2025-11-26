package com.flight.spring.flightbooking.soap.providerb.dto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "availabilitySearchResponse", namespace = "http://service.flightproviderb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class AvailabilitySearchResponseB {
    @XmlElement(name = "return")
    private SearchResultB returnData;
}
