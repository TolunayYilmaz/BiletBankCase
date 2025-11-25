package com.flight.spring.flightbooking.service;

import com.flight.spring.flightbooking.dto.FlightDTO;
import com.flight.spring.flightbooking.entity.SearchLog;
import com.flight.spring.flightbooking.mapper.FlightMapper;
import com.flight.spring.flightbooking.repository.SearchLogRepository;
import com.flight.spring.flightbooking.soap.providera.FlightA;
import com.flight.spring.flightbooking.soap.providerb.FlightB;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class FlightSearchService {

    private final FlightIntegrationService integrationService;
    private final SearchLogRepository logRepository;

    public FlightSearchService(FlightIntegrationService integrationService, SearchLogRepository logRepository) {
        this.integrationService = integrationService;
        this.logRepository = logRepository;
    }

    public List<FlightDTO> getAllFlights() {
        List<FlightDTO> allFlights = new ArrayList<>();


        try {

            List<FlightA> listA = integrationService.getFlightsFromProviderA();
            List<FlightB> listB = integrationService.getFlightsFromProviderB();

            if (listA != null&&listB!=null) {
                listA.forEach(f -> allFlights.add(FlightMapper.mapProviderAToDTO(f)));
                listB.forEach(f->allFlights.add(FlightMapper.mapProviderBToDTO(f)));
                saveLog("ProviderA & ProviderB", "SearchRequest: ALL", "Success: " + listA.size() + " flights");
            }

        } catch (Exception e) {
            e.printStackTrace();
            saveLog("ProviderA / Provider B", "SearchRequest: ALL", "Error: " + e.getMessage());
        }



        return allFlights;
    }



    private void saveLog(String provider, String request, String response) {
        SearchLog log = new SearchLog(); // Veri Tabanına kaydet
        log.setProvider(provider);
        log.setRequest(request);
        log.setResponse(response);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }
}