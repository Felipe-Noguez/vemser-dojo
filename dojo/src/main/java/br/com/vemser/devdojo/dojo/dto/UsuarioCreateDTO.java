package br.com.vemser.devdojo.dojo.dto;

import br.com.vemser.devdojo.dojo.entity.TipoUsuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioCreateDTO {
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String senha;
    private LocalDate dataCriacao;
    private String ativo;
    private TipoUsuario tipoUsuario;
}
