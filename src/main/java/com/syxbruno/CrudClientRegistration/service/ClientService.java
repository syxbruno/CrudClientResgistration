package com.syxbruno.CrudClientRegistration.service;

import com.syxbruno.CrudClientRegistration.config.SecurityConfiguration;
import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.client.ClientCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.client.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.exception.BadRequestException;
import com.syxbruno.CrudClientRegistration.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("clientService")
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public List<ClientResponseDTO> findAllClient() {

        List<Client> allClient = clientRepository.findAll();

        return allClient
                .stream()
                .map(client -> ClientResponseDTO.builder().id(client.getId()).name(client.getName()).email(client.getEmail()).build())
                .toList();
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Client not found"));
    }

    public Client saveClient(Client client) {

        if(clientRepository.existsByCpf(client.getCpf())) {
            throw new BadRequestException("CPF already registered");
        }

        String passwordEncoder = securityConfiguration.passwordEncoder().encode(client.getPassword());
        client.setPassword(passwordEncoder);

        return clientRepository.save(client);
    }

    public Client updateClient(Long id, ClientCreateDTO clientCreateDTO) {
        Client existingClient = findClientById(id);

        existingClient.setName(clientCreateDTO.getName());
        existingClient.setCpf(clientCreateDTO.getCpf());
        existingClient.setPassword(clientCreateDTO.getPassword());
        existingClient.setEmail(clientCreateDTO.getEmail());
        existingClient.setPhone(clientCreateDTO.getPhone());
        existingClient.setDateBirth(clientCreateDTO.getDateBirth());

        return clientRepository.save(existingClient);
    }

    public void delete(Long id) {

        Client clientDelete = findClientById(id);
        clientRepository.delete(clientDelete);
    }
}
