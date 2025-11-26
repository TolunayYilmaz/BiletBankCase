package com.flight.spring.flightbooking.controller;

import com.flight.spring.flightbooking.dto.FlightDto;
import com.flight.spring.flightbooking.dto.FlightResponseDto;
import com.flight.spring.flightbooking.dto.SearchRequestDto;
import com.flight.spring.flightbooking.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    @Autowired
    private final FlightSearchService flightSearchService;

    @GetMapping("/all")
    public FlightResponseDto getAllFlights() {
        return flightSearchService.getAllFlights();

    }

    @PostMapping("/search")
    public FlightResponseDto getSearchFlights(@RequestBody SearchRequestDto searchRequestDto) {
        return flightSearchService.SearchFlight(searchRequestDto);
    }
    @GetMapping("/cheapest")
    public FlightResponseDto getCheaphestFlights() {
        return flightSearchService.getCheapestAllFlights();
    }

}