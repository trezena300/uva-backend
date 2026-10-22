package com.example.piuva.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlterarStatusDTO {

    @NotNull(message = "O campo ativo é obrigatório")
    private Boolean ativo;
}
