package com.flight.spring.flightbooking.dto;


import java.util.List;

public record FlightResponseDto(int count, List<FlightDto> flightList,String message) {
}
