package com.flight.spring.flightbooking.soap.providerb;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "availabilitySearch", namespace = "http://service.flightproviderb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class AvailabilitySearchB {
    @XmlElement(name = "arg0")
    private SearchRequestB request;
}
