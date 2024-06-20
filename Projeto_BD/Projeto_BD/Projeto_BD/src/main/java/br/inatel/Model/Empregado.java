package br.inatel.Model;

public class Empregado {
    private int idEmpregado;
    private String nome;
    private String sobrenome;
    private String função;
    private double salário;
    private String telefone;
    private int idade;

    public Empregado(int idTemp, String nome, String sobrenome, String função, double salário, String telefone, int idade) {
        this.idEmpregado = idTemp;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.função = função;
        this.salário = salário;
        this.telefone = telefone;
        this.idade = idade;
    }

    public int getIdEmpregado() {
        return idEmpregado;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getFuncao() {
        return função;
    }

    public double getSalario() {
        return salário;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdade(){return idade;}
}
