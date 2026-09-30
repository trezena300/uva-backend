package com.example.piuva.controller;

import com.example.piuva.dto.LeituraClimaticaDTO;
import com.example.piuva.service.LeituraClimaticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leituras-climaticas")
@RequiredArgsConstructor
public class LeituraClimaticaController {

    private final LeituraClimaticaService leituraClimaticaService;

    @GetMapping
    public ResponseEntity<List<LeituraClimaticaDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(leituraClimaticaService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/ultima")
    public ResponseEntity<LeituraClimaticaDTO> buscarUltima() {
        LeituraClimaticaDTO ultima = leituraClimaticaService.buscarUltima();
        return ultima != null ? ResponseEntity.ok(ultima) : ResponseEntity.noContent().build();
    }
}
