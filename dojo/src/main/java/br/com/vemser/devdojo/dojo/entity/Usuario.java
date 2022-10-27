package br.com.vemser.devdojo.dojo.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String senha;
    private LocalDate dataCriacao;
    private String ativo;
    private TipoUsuario tipoUsuario;

}
