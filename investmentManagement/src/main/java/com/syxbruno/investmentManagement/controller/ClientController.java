package com.syxbruno.investmentManagement.controller;

import com.syxbruno.investmentManagement.domain.Client;
import com.syxbruno.investmentManagement.dto.ClientCreateDTO;
import com.syxbruno.investmentManagement.dto.ClientResponseDTO;
import com.syxbruno.investmentManagement.mapper.ClientMapper;
import com.syxbruno.investmentManagement.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")

@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping(path = "/{id}")
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
}
