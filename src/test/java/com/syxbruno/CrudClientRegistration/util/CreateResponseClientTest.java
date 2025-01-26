package com.syxbruno.CrudClientRegistration.util;

import com.syxbruno.CrudClientRegistration.dto.client.ClientResponseDTO;

public class CreateResponseClientTest {

    public static ClientResponseDTO createValidResponseClient() {

        return ClientResponseDTO.builder()
                .id(CreateClientTest.createValidClient().getId())
                .name(CreateClientTest.createValidClient().getName())
                .email(CreateClientTest.createValidClient().getEmail())
                .build();
    }
}
