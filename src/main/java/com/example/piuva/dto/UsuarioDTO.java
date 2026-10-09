package com.example.piuva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String nome;
    private String email;
    private String perfil;
    private String telefone;
    private String empresa;
    private String localizacao;
}
