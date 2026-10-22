package com.example.piuva.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String telefone;

    @Column(length = 150)
    private String empresa;

    @Column(length = 150)
    private String localizacao;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PerfilUsuario perfil;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean ativo = true;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}