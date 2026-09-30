package com.example.piuva.repository;

import com.example.piuva.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByLidoFalseOrderByDataHoraDesc();
}
