package com.flight.spring.flightbooking.soap.providerb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class SearchRequestB {
    private String departure;
    private String arrival;
    private String departureDate;

}
