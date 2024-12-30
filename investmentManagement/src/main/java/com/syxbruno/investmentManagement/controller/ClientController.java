package com.syxbruno.investmentManagement.controller;

import com.syxbruno.investmentManagement.domain.Client;
import com.syxbruno.investmentManagement.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")

@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    
}
