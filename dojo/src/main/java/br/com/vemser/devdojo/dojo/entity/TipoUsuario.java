package br.com.vemser.devdojo.dojo.entity;

public enum TipoUsuario {
    N("Normal"),
    A("Admin");

    private String tipo;

    TipoUsuario(String tipo){

        this.tipo = tipo;
    }

    public String getTipos() {
        return tipo;
    }
}
