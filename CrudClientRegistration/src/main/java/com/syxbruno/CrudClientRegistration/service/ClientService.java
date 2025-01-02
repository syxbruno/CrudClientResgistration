package com.syxbruno.CrudClientRegistration.service;

import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.exception.BadRequestException;
import com.syxbruno.CrudClientRegistration.repository.ClientRepository;
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

    public void saveClient(Client client) {

        if(clientRepository.existsByCpf(client.getCpf())) {
            throw new BadRequestException("CPF already registered");
        }

        clientRepository.save(client);
    }

    public void delete(ClientResponseDTO clientResponseDTO) {
        Long id = clientResponseDTO.getId();
        Client clientDelete = findClientById(id);

        clientRepository.delete(clientDelete);
    }
}
