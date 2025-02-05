package com.syxbruno.CrudClientRegistration.util.client;

import com.syxbruno.CrudClientRegistration.domain.Client;

public class CreateClientTest {

    public static Client createValidClient() {

        return Client.builder()
                .id(1L)
                .name("bruno oliveira")
                .cpf("666.047.550-88") // CPF GERADO EM www.4devs.com.br/gerador_de_cpf
                .password("12345678")
                .email("syxbrunoo@gmail.com")
                .phone("(32) 98487-4376")
                .dateBirth("01/01/2001")
                .build();
    }
}
