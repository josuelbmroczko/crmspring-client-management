package com.josuelLoquaz.crm.repository;

import com.josuelLoquaz.crm.domain.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactsRepository extends JpaRepository<Contacts, UUID> {
    List<Contacts> findByAccountsId(UUID accountId);}
