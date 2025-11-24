package com.flight.spring.flightbooking.mapper;

import com.flight.spring.flightbooking.dto.FlightDTO;
import java.time.LocalDateTime;

public class FlightMapper {


    //XML gelen veriyi maplama
    public static FlightDTO mapProviderAToDTO(com.flight.spring.flightbooking.soap.providera.Flight source) {
        FlightDTO dto = new FlightDTO();
        dto.setProviderName("ProviderA (THY)");


        dto.setFlightNumber(source.getFlightNo());
        dto.setOrigin(source.getOrigin());
        dto.setDestination(source.getDestination());
        dto.setPrice(source.getPrice());


        if (source.getDeparturedatetime() != null) {
            dto.setDeparture(LocalDateTime.parse(source.getDeparturedatetime()));
        }
        if (source.getArrivaldatetime() != null) {
            dto.setArrival(LocalDateTime.parse(source.getArrivaldatetime()));
        }

        return dto;
    }


}