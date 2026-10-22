package com.example.piuva.service;

import com.example.piuva.dto.AuthResponseDTO;
import com.example.piuva.dto.LoginDTO;
import com.example.piuva.dto.UsuarioCadastroDTO;
import com.example.piuva.dto.UsuarioDTO;
import com.example.piuva.dto.UsuarioListaDTO;
import com.example.piuva.entity.Usuario;
import com.example.piuva.exception.ResourceNotFoundException;
import com.example.piuva.repository.UsuarioRepository;
import com.example.piuva.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Usuario cadastrar(UsuarioCadastroDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com esse e-mail");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .perfil(dto.getPerfil())
                .telefone(dto.getTelefone())
                .empresa(dto.getEmpresa())
                .localizacao(dto.getLocalizacao())
                .build();

        return usuarioRepository.save(usuario);
    }

    public AuthResponseDTO login(LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new IllegalArgumentException("E-mail ou senha inválidos");
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException("Usuário inativo. Fale com o administrador.");
        }

        String token = jwtUtil.gerarToken(usuario.getEmail());

        return AuthResponseDTO.builder()
                .token(token)
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil().name())
                .build();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + email));
    }

    public UsuarioDTO buscarPerfil(String email) {
        Usuario usuario = buscarPorEmail(email);
        return paraDTO(usuario);
    }

    public UsuarioDTO atualizarPerfil(String email, UsuarioDTO dadosNovos) {
        Usuario usuario = buscarPorEmail(email);

        usuario.setNome(dadosNovos.getNome());
        usuario.setTelefone(dadosNovos.getTelefone());
        usuario.setEmpresa(dadosNovos.getEmpresa());
        usuario.setLocalizacao(dadosNovos.getLocalizacao());

        Usuario salvo = usuarioRepository.save(usuario);
        return paraDTO(salvo);
    }

    public List<UsuarioListaDTO> listar() {
        return usuarioRepository.findAll(Sort.by("nome"))
                .stream()
                .map(this::paraListaDTO)
                .toList();
    }

    public UsuarioListaDTO alterarStatus(Long id, boolean ativo, String emailLogado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));

        if (!ativo && usuario.getEmail().equalsIgnoreCase(emailLogado)) {
            throw new IllegalArgumentException("Você não pode desativar o seu próprio usuário");
        }

        usuario.setAtivo(ativo);
        return paraListaDTO(usuarioRepository.save(usuario));
    }

    private UsuarioDTO paraDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil().name())
                .telefone(usuario.getTelefone())
                .empresa(usuario.getEmpresa())
                .localizacao(usuario.getLocalizacao())
                .build();
    }

    private UsuarioListaDTO paraListaDTO(Usuario usuario) {
        return UsuarioListaDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil().name())
                .ativo(usuario.isAtivo())
                .build();
    }
}