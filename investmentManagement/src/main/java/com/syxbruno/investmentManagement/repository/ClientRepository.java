package com.syxbruno.investmentManagement.repository;

import com.syxbruno.investmentManagement.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
