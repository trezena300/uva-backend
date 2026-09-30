package com.example.piuva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadoMercadoDTO {
    private Long id;
    private String produto;
    private BigDecimal preco;
    private String fonte;
    private LocalDateTime dataAtualizacao;
}
