package br.inatel.Model;

import java.util.Date;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String endereço;
    private String dataNascimento;

    public Cliente(int id, String nome, String telefone, String endereço, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereço = endereço;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereço() {
        return endereço;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
