package com.example.piuva.service;

import com.example.piuva.dto.LeituraClimaticaDTO;
import com.example.piuva.entity.LeituraClimatica;
import com.example.piuva.integration.ThingSpeakClient;
import com.example.piuva.integration.ThingSpeakFeedResponse;
import com.example.piuva.repository.LeituraClimaticaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeituraClimaticaService {

    private final LeituraClimaticaRepository leituraClimaticaRepository;
    private final ThingSpeakClient thingSpeakClient;
    private final AlertaService alertaService;

    private static final DateTimeFormatter THINGSPEAK_FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    @Scheduled(fixedRateString = "${thingspeak.poll-interval-ms:30000}")
    public void sincronizarComThingSpeak() {
        List<ThingSpeakFeedResponse.Feed> feeds = thingSpeakClient.buscarUltimasLeituras(5);

        for (ThingSpeakFeedResponse.Feed feed : feeds) {
            if (feed.getField1() == null || feed.getField2() == null) {
                continue;
            }

            boolean jaExiste = feed.getEntry_id() != null &&
                    leituraClimaticaRepository.findByEntryIdThingSpeak(feed.getEntry_id()).isPresent();

            if (jaExiste) {
                continue;
            }

            LeituraClimatica leitura = LeituraClimatica.builder()
                    .temperatura(Double.parseDouble(feed.getField1()))
                    .umidade(Double.parseDouble(feed.getField2()))
                    .entryIdThingSpeak(feed.getEntry_id())
                    .dataHora(LocalDateTime.parse(feed.getCreated_at(), THINGSPEAK_FORMATO))
                    .build();

            LeituraClimatica salva = leituraClimaticaRepository.save(leitura);
            log.info("Nova leitura climática salva: {}°C / {}%", salva.getTemperatura(), salva.getUmidade());

            alertaService.avaliarLeitura(salva);
        }
    }

    public List<LeituraClimaticaDTO> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return leituraClimaticaRepository.findByDataHoraBetweenOrderByDataHoraDesc(inicio, fim)
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public LeituraClimaticaDTO buscarUltima() {
        LeituraClimatica ultima = leituraClimaticaRepository.findTopByOrderByDataHoraDesc();
        return ultima != null ? paraDTO(ultima) : null;
    }

    private LeituraClimaticaDTO paraDTO(LeituraClimatica entidade) {
        return LeituraClimaticaDTO.builder()
                .id(entidade.getId())
                .temperatura(entidade.getTemperatura())
                .umidade(entidade.getUmidade())
                .dataHora(entidade.getDataHora())
                .build();
    }
}
