package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.client.ClientCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.client.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.service.ClientService;
import com.syxbruno.CrudClientRegistration.util.CreateClientTest;
import com.syxbruno.CrudClientRegistration.util.CreateCreatedClientTest;
import com.syxbruno.CrudClientRegistration.util.CreateResponseClientTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;



    @BeforeEach
    void setUp() {

        PageImpl<ClientResponseDTO> clientPage = new PageImpl<>(List.of(CreateResponseClientTest.createValidResponseClient()));
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
    @DisplayName("findAllClient returns Page<ClientResponseDTO> when successful")
    void findAllClient_ReturnPageClientResponse_WhenSuccessful() {

        String expectedName = CreateClientTest.createValidClient().getName();
        List<ClientResponseDTO> allClient = clientController.findAllClient().getBody();

        Assertions.assertThat(allClient)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(allClient.getFirst().getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findClientById returns Client when successfull")
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
        ResponseEntity<String> compare = ResponseEntity.status(HttpStatus.CREATED).body("customer created successfully, about: " + CreateResponseClientTest.createValidResponseClient());

        Assertions.assertThat(clientSaved)
                .isNotNull()
                .isEqualTo(compare);
    }

    @Test
    void updateClient() {

        ResponseEntity<String> clientUpdate = clientController.updateClient(1L, CreateCreatedClientTest.createValidCreatedClient());
        ResponseEntity<String> compare = ResponseEntity.status(HttpStatus.OK).body("client successfully updated, about: " + CreateResponseClientTest.createValidResponseClient());

        Assertions.assertThat(clientUpdate)
                .isNotNull()
                .isEqualTo(compare);
    }

    @Test
    void deleteClient() {

        ResponseEntity<String> clientForDelete = clientController.deleteClient(1L);
        ResponseEntity<String> compare = ResponseEntity.status(HttpStatus.OK).body("client successfully deleted, about: " + CreateClientTest.createValidClient());

        Assertions.assertThat(clientForDelete).isNotNull();
        Assertions.assertThat(clientForDelete).isEqualTo(compare);
    }
}