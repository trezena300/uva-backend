package com.example.piuva.repository;

import com.example.piuva.entity.LeituraClimatica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LeituraClimaticaRepository extends JpaRepository<LeituraClimatica, Long> {
    List<LeituraClimatica> findByDataHoraBetweenOrderByDataHoraDesc(LocalDateTime inicio, LocalDateTime fim);
    LeituraClimatica findTopByOrderByDataHoraDesc();
    Optional<LeituraClimatica> findByEntryIdThingSpeak(Long entryIdThingSpeak);
}
