package com.flight.spring.flightbooking.mapper;

import com.flight.spring.flightbooking.dto.FlightDto;
import com.flight.spring.flightbooking.soap.providera.dto.FlightA;
import com.flight.spring.flightbooking.soap.providerb.dto.FlightB;

import java.time.LocalDateTime;

public class FlightMapper {


    //XML gelen veriyi maplama
    public static FlightDto mapProviderAToDTO(FlightA source) {
        FlightDto dto = new FlightDto();
        dto.setProviderName("ProviderA (THY)");


        dto.setFlightNumber(source.getFlightNo());
        dto.setOrigin(source.getOrigin());
        dto.setDestination(source.getDestination());
        dto.setPrice(source.getPrice());


        if (source.getDepartureDateTime() != null) {
            dto.setDeparture(LocalDateTime.parse(source.getDepartureDateTime()));
        }
        if (source.getArrivalDateTime() != null) {
            dto.setArrival(LocalDateTime.parse(source.getArrivalDateTime()));
        }

        return dto;
    }
    public static FlightDto mapProviderBToDTO(FlightB source) {
        FlightDto dto = new FlightDto();
        dto.setProviderName("ProviderB (Pegasus)");


        dto.setFlightNumber(source.getFlightNumber());
        dto.setOrigin(source.getDeparture());
        dto.setDestination(source.getArrival());
        dto.setPrice(source.getPrice());


        if (source.getDepartureDateTime() != null) {
            dto.setDeparture(LocalDateTime.parse(source.getDepartureDateTime()));
        }
        if (source.getArrivalDateTime() != null) {
            dto.setArrival(LocalDateTime.parse(source.getArrivalDateTime()));
        }

        return dto;
    }



}