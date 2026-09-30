package com.example.piuva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeituraClimaticaDTO {
    private Long id;
    private Double temperatura;
    private Double umidade;
    private LocalDateTime dataHora;
}
