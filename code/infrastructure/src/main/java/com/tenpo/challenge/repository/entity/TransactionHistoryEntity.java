package com.tenpo.challenge.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction_history", schema = "public")
public class TransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private java.time.LocalDateTime creationDate;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "parameters", nullable = false)
    private String parameters;

    @Column(name = "response", nullable = false)
    private String response;
}