package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.client.ClientCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.client.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.mapper.ClientMapper;
import com.syxbruno.CrudClientRegistration.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(path = "/user/find")
    public ResponseEntity<List<ClientResponseDTO>> findAllClient() {

        return ResponseEntity.ok(clientService.findAllClient());
    }

    @GetMapping(path = "/user/find/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(@PathVariable Long id) {
        Client clientById = clientService.findClientById(id);

        ClientResponseDTO responseClient = ClientMapper.toResponseClient(clientById);
        return ResponseEntity.ok(responseClient);
    }

    @PostMapping(path = "/admin/save")
    public ResponseEntity<String> saveClient(@RequestBody @Valid ClientCreateDTO clientCreateDTO) {
        Client client = ClientMapper.toClient(clientCreateDTO);

        Client clientSaved = clientService.saveClient(client);

        ClientResponseDTO responseClient = ClientMapper.toResponseClient(clientSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully, about: " + responseClient);
    }

    @PutMapping(path = "/admin/update/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody @Valid ClientCreateDTO clientCreateDTO) {

        clientService.updateClient(id, clientCreateDTO);

        Client client = ClientMapper.toClient(clientCreateDTO);
        ClientResponseDTO responseClient = ClientMapper.toResponseClient(client);

        responseClient.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client successfully updated, about: " + responseClient);
    }

    @DeleteMapping(path = "/admin/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {

        Client clientById = clientService.findClientById(id);
        clientService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Client successfully deleted, about: " + clientById);
    }
}





