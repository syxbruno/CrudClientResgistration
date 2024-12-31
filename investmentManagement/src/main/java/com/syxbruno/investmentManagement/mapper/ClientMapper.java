package com.syxbruno.investmentManagement.mapper;

import com.syxbruno.investmentManagement.domain.Client;
import com.syxbruno.investmentManagement.dto.ClientCreateDTO;
import com.syxbruno.investmentManagement.dto.ClientResponseDTO;

public class ClientMapper {

    public static Client toClient(ClientCreateDTO clientCreateDTO) {
        Client client = Client.builder()
                .name(clientCreateDTO.getName())
                .cpf(clientCreateDTO.getCpf())
                .password(clientCreateDTO.getPassword())
                .email(clientCreateDTO.getEmail())
                .phone(clientCreateDTO.getPhone())
                .dateBirth(clientCreateDTO.getDateBirth())
                .build();

        return client;
    }

    public static ClientResponseDTO toResponseClient(Client client) {
        ClientResponseDTO clientResponse = ClientResponseDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .build();

        return clientResponse;
    }
}
