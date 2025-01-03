package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.ClientCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.mapper.ClientMapper;
import com.syxbruno.CrudClientRegistration.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")

@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping(path = "/find")
    public ResponseEntity<Page<ClientResponseDTO>> findAllClient(Pageable pageable) {

        return ResponseEntity.ok(clientService.findAllClient(pageable));
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(@PathVariable Long id) {
        Client clientById = clientService.findClientById(id);

        ClientResponseDTO responseClient = ClientMapper.toResponseClient(clientById);
        return ResponseEntity.ok(responseClient);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<ClientResponseDTO> saveClient(@RequestBody @Valid ClientCreateDTO clientCreateDTO) {
        Client client = ClientMapper.toClient(clientCreateDTO);

        clientService.saveClient(client);

        ClientResponseDTO responseClient = ClientMapper.toResponseClient(client);
        return new ResponseEntity<>(responseClient, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientCreateDTO clientCreateDTO) {

        clientService.updateClient(id, clientCreateDTO);

        Client client = ClientMapper.toClient(clientCreateDTO);
        ClientResponseDTO responseClient = ClientMapper.toResponseClient(client);

        responseClient.setId(id);
        return ResponseEntity.ok(responseClient);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }
}





