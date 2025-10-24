package com.josuelLoquaz.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat; // 1. IMPORTE A ANOTAÇÃO

import java.math.BigDecimal;
import java.time.LocalDate; // 2. MUDE A IMPORTAÇÃO DE LocalDateTime PARA LocalDate
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Opportunities")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Opportunities {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String stage;

    private BigDecimal amount;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expected_close_date")
    private LocalDate expectedCloseDate;

    private Double probability;

    private String status;

    @Column(name = "owner_id")
    private int ownerId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Accounts_id")
    private Accounts accounts;
}