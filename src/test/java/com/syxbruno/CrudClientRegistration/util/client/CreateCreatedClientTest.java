package com.syxbruno.CrudClientRegistration.util.client;

import com.syxbruno.CrudClientRegistration.dto.client.ClientCreateDTO;

public class CreateCreatedClientTest {

    public static ClientCreateDTO createValidCreatedClient() {

        return ClientCreateDTO.builder()
                .name(CreateClientTest.createValidClient().getName())
                .cpf(CreateClientTest.createValidClient().getCpf())
                .password(CreateClientTest.createValidClient().getPassword())
                .email(CreateClientTest.createValidClient().getEmail())
                .phone(CreateClientTest.createValidClient().getPhone())
                .dateBirth(CreateClientTest.createValidClient().getDateBirth())
                .build();
    }
}
