package com.flight.spring.flightbooking.service;

import com.flight.spring.flightbooking.dto.FlightDTO;
import com.flight.spring.flightbooking.entity.SearchLog;
import com.flight.spring.flightbooking.mapper.FlightMapper;
import com.flight.spring.flightbooking.repository.SearchLogRepository;
import com.flight.spring.flightbooking.soap.providera.FlightA;
import com.flight.spring.flightbooking.soap.providerb.FlightB;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


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


    //flightNumber-origin-destination-departure-arrival değerleri aynı olanları karşılatşrırır
    //FlightDTO  equals metodu bu değerlere göre güncellendi.
    public List<FlightDTO> getCheapestAllFlights(){
        List<FlightDTO> allFlights = getAllFlights();
        List<FlightDTO> mockDataFlights=createMockFlights();

        mockDataFlights.forEach(f->allFlights.add(f));


        Map<FlightDTO, FlightDTO> cheapestMap = new HashMap<>();

        try {
            for (FlightDTO currentFlight : allFlights){

                cheapestMap.merge(
                        currentFlight,       // Key (Anahtar)
                        currentFlight,       // Value (Değer)
                        (existingFlight, newFlight) -> {
                            // Çakışma oldu! Hangisi daha ucuz?
                            if (newFlight.getPrice().compareTo(existingFlight.getPrice()) < 0) {
                                return newFlight; // Yeni gelen daha ucuz, onu al
                            }
                            return existingFlight; // Eskisi daha ucuz (veya eşit), o kalsın
                        });
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            saveLog("ProviderA / Provider B", "SearchRequest: Cheapest", "Error: " + e.getMessage());
        }
        List<FlightDTO> result = new ArrayList<>(cheapestMap.values());
        result.sort(Comparator.comparing(flightDTO -> flightDTO.getPrice()));//comparing nesnenin içindeki proplara göre sıralamamızı sağlar
        return result;
    }

    private void saveLog(String provider, String request, String response) {
        SearchLog log = new SearchLog(); // Veri Tabanına kaydet
        log.setProvider(provider);
        log.setRequest(request);
        log.setResponse(response);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }
    /*mock veri (servislerden gelen datalar flightNumber-origin-destination-departure-arrival
     değerlerinden biri bile farklı olunca uniq sayıdlığı için veri sayısı değişmiyor
     bu nedenle mock veri eklendi*/
    private List<FlightDTO> createMockFlights() {
        List<FlightDTO> mocks = new ArrayList<>();

        LocalDateTime sabah = LocalDateTime.of(2025, 12, 30, 9, 0);
        LocalDateTime ogle = LocalDateTime.of(2025, 12, 30, 12, 0);
        LocalDateTime ikindi = LocalDateTime.of(2025, 12, 30, 15, 0);

        mocks.add(new FlightDTO("ProviderA (THY)", "PC1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("251.0")));
        mocks.add(new FlightDTO("ProviderB (Pegasus)", "PC1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("224.0")));

        mocks.add(new FlightDTO("ProviderA (THY)", "TK1001", "IST", "COV", sabah, LocalDateTime.of(2025, 12, 30, 11, 29), new BigDecimal("197.0")));
        mocks.add(new FlightDTO("ProviderB (Pegasus)", "TK1001", "IST", "COV", sabah, LocalDateTime.of(2025, 12, 30, 11, 29), new BigDecimal("162.0")));

        mocks.add(new FlightDTO("ProviderA (THY)", "XQ1003", "IST", "COV", ikindi, LocalDateTime.of(2025, 12, 30, 18, 28), new BigDecimal("261.0")));
        mocks.add(new FlightDTO("ProviderB (Pegasus)", "XQ1003", "IST", "COV", ikindi, LocalDateTime.of(2025, 12, 30, 18, 28), new BigDecimal("261.0")));

        mocks.add(new FlightDTO("ProviderA (THY)", "TK1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("220.0")));
        mocks.add(new FlightDTO("ProviderB (Pegasus)", "TK1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("224.0")));

        mocks.add(new FlightDTO("ProviderA (THY)", "TK9999", "IST", "LHR", sabah, LocalDateTime.of(2025, 12, 30, 13, 0), new BigDecimal("5000.0")));

        mocks.add(new FlightDTO("ProviderB (Pegasus)", "PC8888", "IST", "CDG", ikindi, LocalDateTime.of(2025, 12, 30, 19, 0), new BigDecimal("4500.0")));

        return mocks;
    }
}