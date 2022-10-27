package br.com.vemser.devdojo.dojo.entity;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String senha;
    private LocalDate dataCriacao;
    private boolean ativo;
    private TipoUsuario tipoUsuario;

}
