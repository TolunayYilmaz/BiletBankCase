package com.flight.spring.flightbooking.service;

import com.flight.spring.flightbooking.dto.FlightDto;
import com.flight.spring.flightbooking.dto.FlightResponseDto;
import com.flight.spring.flightbooking.dto.SearchRequestDto;
import com.flight.spring.flightbooking.entity.SearchLog;
import com.flight.spring.flightbooking.exceptions.ApiException;
import com.flight.spring.flightbooking.mapper.FlightMapper;
import com.flight.spring.flightbooking.repository.SearchLogRepository;
import com.flight.spring.flightbooking.soap.providera.dto.FlightA;
import com.flight.spring.flightbooking.soap.providera.dto.SearchRequestA;
import com.flight.spring.flightbooking.soap.providerb.dto.FlightB;
import com.flight.spring.flightbooking.soap.providerb.dto.SearchRequestB;
import org.springframework.http.HttpStatus;
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

    public FlightResponseDto getAllFlights() {
        List<FlightDto> allFlights =fetchRawFlights();
        saveLog("ProviderA & ProviderB", "SearchRequest: ALL", "Success: " + allFlights.size() + " flights");
        return new FlightResponseDto(allFlights.size(),allFlights,"Basarili");
    }


    public FlightResponseDto SearchFlight(SearchRequestDto searchRequestDto) {

        if (searchRequestDto.origin().equalsIgnoreCase(searchRequestDto.destination())) {
            throw new ApiException("Kalkış ve varış noktası aynı olamaz: " + searchRequestDto.origin(), HttpStatus.BAD_REQUEST);
        }

        try {

            List<FlightA> listA = integrationService.getFlightsFromProviderA(searchRequestDto);
            List<FlightB> listB = integrationService.getFlightsFromProviderB(searchRequestDto);


            List<FlightDto> result = new ArrayList<>();

            if (listA != null) {
                listA.forEach(f -> result.add(FlightMapper.mapProviderAToDTO(f)));
            }
            if (listB != null) {
                listB.forEach(f -> result.add(FlightMapper.mapProviderBToDTO(f)));
            }


            if (result.isEmpty()) {
                throw new ApiException(
                        searchRequestDto.origin() + " - " + searchRequestDto.destination() + " rotasında uçuş bulunamadı.",
                        HttpStatus.NOT_FOUND
                );
            }


            saveLog("ProviderA & ProviderB",
                    "SearchRequest: " + searchRequestDto.origin() + "-" + searchRequestDto.destination() + " " + searchRequestDto.date(),
                    "Success: " + result.size());

            return new FlightResponseDto(result.size(),result,"Basarili");

        } catch (Exception e) {
            throw new ApiException(
                    "Uçuş sağlayıcılarına şu an erişilemiyor: " + e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE
            );
        }
    }


    //flightNumber-origin-destination-departure-arrival değerleri aynı olanları karşılatşrırır
    //FlightDTO  equals metodu bu değerlere göre güncellendi.
    public FlightResponseDto getCheapestAllFlights(){
        List<FlightDto> allFlights = fetchRawFlights();

        List<FlightDto> mockDataFlights=createMockFlights();

        mockDataFlights.forEach(f->allFlights.add(f));


        Map<FlightDto, FlightDto> cheapestMap = new HashMap<>();

        try {
            for (FlightDto currentFlight : allFlights){

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
            saveLog("ProviderA & ProviderB", "SearchRequest: Cheapest", "Success: " + cheapestMap.size() + " flights");

        }
        catch (Exception e) {

            saveLog("ProviderA / Provider B", "SearchRequest: Cheapest", "Error: " + e.getMessage());
        }
        List<FlightDto> result = new ArrayList<>(cheapestMap.values());
        result.sort(Comparator.comparing(flightDto -> flightDto.getPrice()));//comparing nesnenin içindeki proplara göre sıralamamızı sağlar
        return new FlightResponseDto(result.size(),result,"Basarili");
    }

    //Tüm verilerileri listeye koyar
    private List<FlightDto> fetchRawFlights() {
        List<FlightDto> flights = new ArrayList<>();
        try {
            List<FlightA> listA = integrationService.getFlightsFromProviderA();
            List<FlightB> listB = integrationService.getFlightsFromProviderB();


            if (listA != null) {
                listA.forEach(f -> flights.add(FlightMapper.mapProviderAToDTO(f)));
            }
            if (listB != null) {
                listB.forEach(f -> flights.add(FlightMapper.mapProviderBToDTO(f)));
            }
        } catch (Exception e) {

            saveLog("System", "Data Fetch", "Error: " + e.getMessage());
        }
        return flights;
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
    private List<FlightDto> createMockFlights() {
        List<FlightDto> mocks = new ArrayList<>();

        LocalDateTime sabah = LocalDateTime.of(2025, 12, 30, 9, 0);
        LocalDateTime ogle = LocalDateTime.of(2025, 12, 30, 12, 0);
        LocalDateTime ikindi = LocalDateTime.of(2025, 12, 30, 15, 0);

        mocks.add(new FlightDto("ProviderA (THY)", "PC1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("251.0")));
        mocks.add(new FlightDto("ProviderB (Pegasus)", "PC1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("224.0")));

        mocks.add(new FlightDto("ProviderA (THY)", "TK1001", "IST", "COV", sabah, LocalDateTime.of(2025, 12, 30, 11, 29), new BigDecimal("197.0")));
        mocks.add(new FlightDto("ProviderB (Pegasus)", "TK1001", "IST", "COV", sabah, LocalDateTime.of(2025, 12, 30, 11, 29), new BigDecimal("162.0")));

        mocks.add(new FlightDto("ProviderA (THY)", "XQ1003", "IST", "COV", ikindi, LocalDateTime.of(2025, 12, 30, 18, 28), new BigDecimal("261.0")));
        mocks.add(new FlightDto("ProviderB (Pegasus)", "XQ1003", "IST", "COV", ikindi, LocalDateTime.of(2025, 12, 30, 18, 28), new BigDecimal("261.0")));

        mocks.add(new FlightDto("ProviderA (THY)", "TK1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("220.0")));
        mocks.add(new FlightDto("ProviderB (Pegasus)", "TK1002", "IST", "COV", ogle, LocalDateTime.of(2025, 12, 30, 16, 29), new BigDecimal("224.0")));

        mocks.add(new FlightDto("ProviderA (THY)", "TK9999", "IST", "LHR", sabah, LocalDateTime.of(2025, 12, 30, 13, 0), new BigDecimal("5000.0")));

        mocks.add(new FlightDto("ProviderB (Pegasus)", "PC8888", "IST", "CDG", ikindi, LocalDateTime.of(2025, 12, 30, 19, 0), new BigDecimal("4500.0")));

        return mocks;
    }
}