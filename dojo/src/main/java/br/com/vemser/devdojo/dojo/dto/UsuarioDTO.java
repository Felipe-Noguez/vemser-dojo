package br.com.vemser.devdojo.dojo.dto;

import br.com.vemser.devdojo.dojo.entity.TipoUsuario;

import java.time.LocalDate;

public class UsuarioDTO {
    private int id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String senha;
    private LocalDate dataCriacao;
    private boolean ativo;
    private TipoUsuario tipoUsuario;
}
