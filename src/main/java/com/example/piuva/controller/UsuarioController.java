package com.example.piuva.controller;

import com.example.piuva.dto.AuthResponseDTO;
import com.example.piuva.dto.LoginDTO;
import com.example.piuva.dto.UsuarioCadastroDTO;
import com.example.piuva.dto.UsuarioDTO;
import com.example.piuva.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody UsuarioCadastroDTO dto) {
        usuarioService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(usuarioService.login(dto));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> buscarPerfil(Authentication authentication) {
        return ResponseEntity.ok(usuarioService.buscarPerfil(authentication.getName()));
    }

    @PutMapping("/me")
    public ResponseEntity<UsuarioDTO> atualizarPerfil(Authentication authentication, @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizarPerfil(authentication.getName(), dto));
    }
}