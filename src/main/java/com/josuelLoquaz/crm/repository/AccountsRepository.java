package com.josuelLoquaz.crm.repository;

import com.josuelLoquaz.crm.domain.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountsRepository extends JpaRepository<Accounts,UUID> {
}
