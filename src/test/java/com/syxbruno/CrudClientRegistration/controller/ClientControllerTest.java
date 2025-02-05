package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.client.ClientCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.client.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.service.ClientService;
import com.syxbruno.CrudClientRegistration.util.client.CreateClientTest;
import com.syxbruno.CrudClientRegistration.util.client.CreateCreatedClientTest;
import com.syxbruno.CrudClientRegistration.util.client.CreateResponseClientTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;



    @BeforeEach
    void setUp() {

        BDDMockito.when(clientService.findAllClient())
                .thenReturn(List.of(CreateResponseClientTest.createValidResponseClient()));


        BDDMockito.when(clientService.findClientById(ArgumentMatchers.anyLong()))
                .thenReturn(CreateClientTest.createValidClient());


        BDDMockito.when(clientService.saveClient(ArgumentMatchers.any(Client.class)))
                .thenReturn(CreateClientTest.createValidClient());


        BDDMockito.when(clientService.updateClient(ArgumentMatchers.anyLong(), ArgumentMatchers.any(ClientCreateDTO.class)))
                .thenReturn(CreateClientTest.createValidClient());
    }



    @Test
    @DisplayName("findAllClient returns List<ClientResponseDTO> when successful")
    void findAllClient_ReturnListClientResponse_WhenSuccessful() {

        String expectedName = CreateClientTest.createValidClient().getName();
        List<ClientResponseDTO> allClient = clientController.findAllClient().getBody();

        Assertions.assertThat(allClient)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(allClient.getFirst().getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findAllClient must return an empty list")
    void findAllClient_MustReturnEmptyList() {

        BDDMockito.when(clientService.findAllClient())
                .thenReturn(Collections.emptyList());

        List<ClientResponseDTO> allClient = clientController.findAllClient().getBody();

        Assertions.assertThat(allClient)
                .isNotNull()
                .isEmpty();
    }


    @Test
    @DisplayName("findClientById returns Client when successful")
    void findClientById_ReturnClient_WhenSuccessful() {

        Long expectedId = CreateClientTest.createValidClient().getId();
        ClientResponseDTO clientById = clientController.findClientById(1L).getBody();

        Assertions.assertThat(clientById).isNotNull();
        Assertions.assertThat(clientById.getId()).isNotNull().isEqualTo(expectedId);
    }


    @Test
    @DisplayName("saveClient and return Client when successful")
    void saveClient_ReturnClient_WhenSuccessful() {

        ResponseEntity<String> clientSaved = clientController.saveClient(CreateCreatedClientTest.createValidCreatedClient());
        ResponseEntity<String> compare = ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully, about: " + CreateResponseClientTest.createValidResponseClient());

        Assertions.assertThat(clientSaved)
                .isNotNull()
                .isEqualTo(compare);
    }


    @Test
    void updateClient() {

        ResponseEntity<String> clientUpdate = clientController.updateClient(1L, CreateCreatedClientTest.createValidCreatedClient());
        ResponseEntity<String> compare = ResponseEntity.status(HttpStatus.OK).body("Client successfully updated, about: " + CreateResponseClientTest.createValidResponseClient());

        Assertions.assertThat(clientUpdate)
                .isNotNull()
                .isEqualTo(compare);
    }


    @Test
    void deleteClient() {

        ResponseEntity<String> clientForDelete = clientController.deleteClient(1L);
        ResponseEntity<String> compare = ResponseEntity.status(HttpStatus.OK).body("Client successfully deleted, about: " + CreateClientTest.createValidClient());

        Assertions.assertThat(clientForDelete).isNotNull();
        Assertions.assertThat(clientForDelete).isEqualTo(compare);
    }
}