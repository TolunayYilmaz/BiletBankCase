package com.flight.spring.flightbooking.mapper;

import com.flight.spring.flightbooking.dto.FlightDTO;
import com.flight.spring.flightbooking.soap.providera.FlightA;
import com.flight.spring.flightbooking.soap.providerb.FlightB;

import java.time.LocalDateTime;

public class FlightMapper {


    //XML gelen veriyi maplama
    public static FlightDTO mapProviderAToDTO(FlightA source) {
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
    public static FlightDTO mapProviderBToDTO(FlightB source) {
        FlightDTO dto = new FlightDTO();
        dto.setProviderName("ProviderB (Pegasus)");


        dto.setFlightNumber(source.getFlightNumber());
        dto.setOrigin(source.getDeparture());
        dto.setDestination(source.getArrival());
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