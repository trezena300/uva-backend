package com.example.piuva.dto;

import com.example.piuva.entity.NivelAlerta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaDTO {
    private Long id;
    private NivelAlerta nivel;
    private String mensagem;
    private boolean lido;
    private LocalDateTime dataHora;
}
