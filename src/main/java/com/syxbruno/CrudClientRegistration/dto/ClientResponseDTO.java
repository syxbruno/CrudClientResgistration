package com.syxbruno.CrudClientRegistration.dto;

import com.syxbruno.CrudClientRegistration.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String email;

    public ClientResponseDTO(Client client) {

    }
}
