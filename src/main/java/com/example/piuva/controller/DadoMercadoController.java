package com.example.piuva.controller;

import com.example.piuva.dto.DadoMercadoDTO;
import com.example.piuva.service.DadoMercadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dados-mercado")
@RequiredArgsConstructor
public class DadoMercadoController {

    private final DadoMercadoService dadoMercadoService;

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody DadoMercadoDTO dto) {
        dadoMercadoService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DadoMercadoDTO>> listarTodos() {
        return ResponseEntity.ok(dadoMercadoService.listarTodos());
    }

    @GetMapping("/produto/{produto}")
    public ResponseEntity<List<DadoMercadoDTO>> listarPorProduto(@PathVariable String produto) {
        return ResponseEntity.ok(dadoMercadoService.listarPorProduto(produto));
    }
}
