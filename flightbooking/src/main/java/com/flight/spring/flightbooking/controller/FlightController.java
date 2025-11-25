package com.flight.spring.flightbooking.controller;

import com.flight.spring.flightbooking.dto.FlightDTO;
import com.flight.spring.flightbooking.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    @Autowired
    private final FlightSearchService flightSearchService;



    @GetMapping("/all")
    public List<FlightDTO> getAllFlights() {
        return flightSearchService.getAllFlights();
    }

    @GetMapping("/cheapest")
    public List<FlightDTO> getCheaphestFlights() {
        return flightSearchService.getCheapestAllFlights();
    }

}