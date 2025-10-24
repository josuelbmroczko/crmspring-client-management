package com.josuelLoquaz.crm.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "crm_associations")
public class CrmAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Ligação com o Contato (Muitas associações para UM contato)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id",referencedColumnName = "id")
    private Contacts contacts;

    // Ligação com a Oportunidade (Muitas associações para UMA oportunidade)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id",referencedColumnName = "id")
    private Opportunities opportunities;

    // Você pode adicionar outros campos a esta associação, se necessário
    @Column(name = "association_type")
    private String associationType;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Opportunities getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(Opportunities opportunities) {
        this.opportunities = opportunities;
    }

    public String getAssociationType() {
        return associationType;
    }

    public void setAssociationType(String associationType) {
        this.associationType = associationType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
