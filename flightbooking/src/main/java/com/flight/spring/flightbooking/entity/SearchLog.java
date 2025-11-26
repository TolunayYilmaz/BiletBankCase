package com.flight.spring.flightbooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "search_logs")
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provider;
    @Column(length = 200)
    private String request;
    @Column(length = 200)
    private String response;
    private LocalDateTime timestamp;
}