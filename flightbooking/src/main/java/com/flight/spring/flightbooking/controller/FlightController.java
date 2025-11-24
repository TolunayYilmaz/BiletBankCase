package com.flight.spring.flightbooking.controller;

import com.flight.spring.flightbooking.dto.FlightDTO;
import com.flight.spring.flightbooking.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private final FlightSearchService searchService;

    public FlightController(FlightSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/all")
    public List<FlightDTO> getAllFlights() {
        return searchService.getAllFlights();
    }


}