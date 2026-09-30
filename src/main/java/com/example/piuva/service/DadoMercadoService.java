package com.example.piuva.service;

import com.example.piuva.dto.DadoMercadoDTO;
import com.example.piuva.entity.DadoMercado;
import com.example.piuva.repository.DadoMercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DadoMercadoService {

    private final DadoMercadoRepository dadoMercadoRepository;

    public DadoMercado registrar(DadoMercadoDTO dto) {
        DadoMercado dado = DadoMercado.builder()
                .produto(dto.getProduto())
                .preco(dto.getPreco())
                .fonte(dto.getFonte())
                .build();

        return dadoMercadoRepository.save(dado);
    }

    public List<DadoMercadoDTO> listarPorProduto(String produto) {
        return dadoMercadoRepository.findByProdutoIgnoreCase(produto)
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public List<DadoMercadoDTO> listarTodos() {
        return dadoMercadoRepository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    private DadoMercadoDTO paraDTO(DadoMercado entidade) {
        return DadoMercadoDTO.builder()
                .id(entidade.getId())
                .produto(entidade.getProduto())
                .preco(entidade.getPreco())
                .fonte(entidade.getFonte())
                .dataAtualizacao(entidade.getDataAtualizacao())
                .build();
    }
}
