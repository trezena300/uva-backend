package com.example.piuva.repository;

import com.example.piuva.entity.DadoMercado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadoMercadoRepository extends JpaRepository<DadoMercado, Long> {
    List<DadoMercado> findByProdutoIgnoreCase(String produto);
}