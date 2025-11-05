package com.josuelLoquaz.crm.repository;

import com.josuelLoquaz.crm.domain.Opportunities;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OpportunitiesRepository extends JpaRepository<Opportunities, UUID> {
    List<Opportunities> findByAccountsId(UUID accountId);}
