package com.syxbruno.CrudClientRegistration.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "This dto class is focused on returns, it has attributes that users can see without causing security impacts")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String email;
}
