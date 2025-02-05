package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.domain.Client;
import com.syxbruno.CrudClientRegistration.dto.client.ClientCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.client.ClientResponseDTO;
import com.syxbruno.CrudClientRegistration.mapper.ClientMapper;
import com.syxbruno.CrudClientRegistration.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Client Controller", description = "Client related operations")

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(description = "Search for all existing customers in the database, this endpoint can only be used by a user with role user or higher", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping(path = "/user/find")
    public ResponseEntity<List<ClientResponseDTO>> findAllClient() {

        return ResponseEntity.ok(clientService.findAllClient());
    }

    @Operation(description = "Search for an existing customer in the database, this endpoint can only be used by a user with role user or higher", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Client not found", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping(path = "/user/find/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(@PathVariable Long id) {
        Client clientById = clientService.findClientById(id);

        ClientResponseDTO responseClient = ClientMapper.toResponseClient(clientById);
        return ResponseEntity.ok(responseClient);
    }

    @Operation(description = "Save clients in the database, this endpoint can only be used by a user with admin role", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Cpf already registered", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping(path = "/admin/save")
    public ResponseEntity<String> saveClient(@RequestBody @Valid ClientCreateDTO clientCreateDTO) {
        Client client = ClientMapper.toClient(clientCreateDTO);

        Client clientSaved = clientService.saveClient(client);

        ClientResponseDTO responseClient = ClientMapper.toResponseClient(clientSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully, about: " + responseClient);
    }

    @Operation(description = "Update existing clients in the database, this endpoint can only be used by a user with the admin role", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping(path = "/admin/update/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody @Valid ClientCreateDTO clientCreateDTO) {

        clientService.updateClient(id, clientCreateDTO);

        Client client = ClientMapper.toClient(clientCreateDTO);
        ClientResponseDTO responseClient = ClientMapper.toResponseClient(client);

        responseClient.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client successfully updated, about: " + responseClient);
    }

    @Operation(description = "Delete a client saved in database, this endpoint can only be used by a user with the admin role", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Client not found", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping(path = "/admin/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {

        Client clientById = clientService.findClientById(id);
        clientService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Client successfully deleted, about: " + clientById);
    }
}





