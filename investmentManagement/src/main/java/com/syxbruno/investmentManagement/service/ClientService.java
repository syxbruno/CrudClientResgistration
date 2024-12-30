package com.syxbruno.investmentManagement.service;

import com.syxbruno.investmentManagement.domain.Client;
import com.syxbruno.investmentManagement.exception.BadRequestException;
import com.syxbruno.investmentManagement.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Client not found"));
    }
}
