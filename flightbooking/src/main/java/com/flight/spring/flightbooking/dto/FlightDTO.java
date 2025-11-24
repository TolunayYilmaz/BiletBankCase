package com.flight.spring.flightbooking.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightDTO {
    private String providerName; // "ProviderA"/ "ProviderB"
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private BigDecimal price;
}