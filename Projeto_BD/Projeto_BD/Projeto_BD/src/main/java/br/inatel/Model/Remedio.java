package br.inatel.Model;

import java.util.Date;

public class Remedio {
    private int idRemedio;
    private String nomeRemedio;
    private String fabricante;
    private String tipoRemedio;
    private int estoque;
    private String validade;
    private double preço;

    public Remedio(int idRemedio, String nomeRemedio, String fabricante, String tipoRemedio, int estoque, String validade, double preço) {
        this.idRemedio = idRemedio;
        this.nomeRemedio = nomeRemedio;
        this.fabricante = fabricante;
        this.tipoRemedio = tipoRemedio;
        this.estoque = estoque;
        this.validade = validade;
        this.preço = preço;
    }

    public int getId() {
        return idRemedio;
    }

    public String getNomeRemedio() {
        return nomeRemedio;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getTipoRemedio() {
        return tipoRemedio;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getValidade() {
        return validade;
    }

    public double getPreco() {
        return preço;
    }
}
