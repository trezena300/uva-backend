package com.example.piuva.controller;

import com.example.piuva.dto.AlertaDTO;
import com.example.piuva.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;

    @GetMapping
    public ResponseEntity<List<AlertaDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(alertaService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/nao-lidos")
    public ResponseEntity<List<AlertaDTO>> listarNaoLidos() {
        return ResponseEntity.ok(alertaService.listarNaoLidos());
    }

    @PatchMapping("/{id}/marcar-lido")
    public ResponseEntity<Void> marcarComoLido(@PathVariable Long id) {
        alertaService.marcarComoLido(id);
        return ResponseEntity.ok().build();
    }
}