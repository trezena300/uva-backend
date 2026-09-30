package com.example.piuva.service;

import com.example.piuva.dto.AlertaDTO;
import com.example.piuva.entity.Alerta;
import com.example.piuva.entity.LeituraClimatica;
import com.example.piuva.entity.NivelAlerta;
import com.example.piuva.exception.ResourceNotFoundException;
import com.example.piuva.repository.AlertaRepository;
import com.example.piuva.util.TemperatureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;

    public void avaliarLeitura(LeituraClimatica leitura) {
        if (TemperatureUtil.temperaturaForaDoIdeal(leitura.getTemperatura())) {
            criarAlerta(NivelAlerta.CRITICO,
                    String.format("Temperatura fora da faixa ideal: %.1f°C", leitura.getTemperatura()),
                    leitura);
        }

        if (TemperatureUtil.umidadeForaDoIdeal(leitura.getUmidade())) {
            criarAlerta(NivelAlerta.ATENCAO,
                    String.format("Umidade acima do ideal: %.1f%%", leitura.getUmidade()),
                    leitura);
        }
    }

    private void criarAlerta(NivelAlerta nivel, String mensagem, LeituraClimatica leitura) {
        Alerta alerta = Alerta.builder()
                .nivel(nivel)
                .mensagem(mensagem)
                .leituraClimatica(leitura)
                .build();

        alertaRepository.save(alerta);
    }

    public List<AlertaDTO> listarNaoLidos() {
        return alertaRepository.findByLidoFalseOrderByDataHoraDesc()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public void marcarComoLido(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado: " + id));
        alerta.setLido(true);
        alertaRepository.save(alerta);
    }

    private AlertaDTO paraDTO(Alerta entidade) {
        return AlertaDTO.builder()
                .id(entidade.getId())
                .nivel(entidade.getNivel())
                .mensagem(entidade.getMensagem())
                .lido(entidade.isLido())
                .dataHora(entidade.getDataHora())
                .build();
    }
}
