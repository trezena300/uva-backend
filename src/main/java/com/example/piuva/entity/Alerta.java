package com.example.piuva.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NivelAlerta nivel;

    @Column(nullable = false, length = 255)
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "leitura_climatica_id")
    private LeituraClimatica leituraClimatica;

    @Column(nullable = false)
    private boolean lido;

    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    @PrePersist
    public void prePersist() {
        this.dataHora = LocalDateTime.now();
        this.lido = false;
    }
}
