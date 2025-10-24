package com.josuelLoquaz.crm.repository;

import com.josuelLoquaz.crm.domain.CrmAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrmAssociationRepository extends JpaRepository<CrmAssociation, UUID> {
}
