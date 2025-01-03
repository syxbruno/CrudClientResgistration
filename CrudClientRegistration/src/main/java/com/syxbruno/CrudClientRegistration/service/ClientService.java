package com.syxbruno.CrudClientRegistration.service;

import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.ClientCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.exception.BadRequestException;
import com.syxbruno.CrudClientRegistration.mapper.ClientMapper;
import com.syxbruno.CrudClientRegistration.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Page<ClientResponseDTO> findAllClient(Pageable pageable) {
        Page<Client> allClient = clientRepository.findAll(pageable);

        return allClient.map(ClientResponseDTO::new);
    }

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

    public void updateClient(Long id, ClientCreateDTO clientCreateDTO) {
        Client existingClient = findClientById(id);

        existingClient.setName(clientCreateDTO.getName());
        existingClient.setCpf(clientCreateDTO.getCpf());
        existingClient.setPassword(clientCreateDTO.getPassword());
        existingClient.setEmail(clientCreateDTO.getEmail());
        existingClient.setPhone(clientCreateDTO.getPhone());
        existingClient.setDateBirth(clientCreateDTO.getDateBirth());

        clientRepository.save(existingClient);
    }

    public void delete(Long id) {

        Client clientDelete = findClientById(id);
        clientRepository.delete(clientDelete);
    }
}
