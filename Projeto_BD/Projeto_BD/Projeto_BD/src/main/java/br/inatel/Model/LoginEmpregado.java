package br.inatel.Model;

public class LoginEmpregado {
    private int id;
    private String senha;
    private String email;
    private int empregadoID;

    public LoginEmpregado(int id, String senha, String email, int empregadoID) {
        this.id = id;
        this.senha = senha;
        this.email = email;
        this.empregadoID = empregadoID;
    }

    public int getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public int getEmpregadoID() {
        return empregadoID;
    }
}
