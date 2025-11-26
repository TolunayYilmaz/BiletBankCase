package com.flight.spring.flightbooking.soap.providerb.dto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class FlightB {

    private String flightNumber;
    private String departure;
    private String arrival;
    @XmlElement(name = "departuredatetime")
    private String departureDateTime;
    @XmlElement(name = "arrivaldatetime")
    private String arrivalDateTime;
    private BigDecimal price;
}
