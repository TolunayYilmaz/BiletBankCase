package com.flight.spring.flightbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private String providerName; // "ProviderA"/ "ProviderB"
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private BigDecimal price;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        FlightDTO flightDTO = (FlightDTO) object;
        return Objects.equals(flightNumber, flightDTO.flightNumber) && Objects.equals(origin, flightDTO.origin) && Objects.equals(destination, flightDTO.destination) && Objects.equals(departure, flightDTO.departure) && Objects.equals(arrival, flightDTO.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, origin, destination, departure, arrival);
    }
}