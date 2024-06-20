package br.inatel.DAO;

import br.inatel.Model.Cliente;
import br.inatel.Model.Empregado;
import br.inatel.Model.Remedio;

import java.sql.SQLException;
public class RemedioDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Inserir remédio no Banco de Dados
    public boolean insertRemedio(Remedio remedio){

        connect();

        String sql = "INSERT INTO Remédio (idRemedio,nomeRemedio,fabricante,tipoRemedio,estoque,validade,preço) values (?,?,?,?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,remedio.getId());
            pst.setString(2,remedio.getNomeRemedio());
            pst.setString(3,remedio.getFabricante());
            pst.setString(4,remedio.getTipoRemedio());
            pst.setInt(5,remedio.getEstoque());
            pst.setString(6,remedio.getValidade());
            pst.setDouble(7,remedio.getPreco());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex){
            System.out.println("Erro de conexao  = " + ex.getMessage());
            sucesso = false;
        }finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexao " + e.getMessage());
            }
        }
        return sucesso;
    }

    //Buscar remédio no Banco de Dados
    public boolean selectRemedioId(int remedioId) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM Remédio";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                Remedio remedioTemp = new Remedio(
                        resultSet.getInt("idRemedio"),
                        resultSet.getString("nomeRemedio"),
                        resultSet.getString("fabricante"),
                        resultSet.getString("tipoRemedio"),
                        resultSet.getInt("estoque"),
                        resultSet.getString("validade"),
                        resultSet.getDouble("preço")
                );
                if (remedioTemp.getId() == remedioId) {
                    verificado = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());

        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return verificado;
    }

    //Buscar remédio (retorna remédio) no Banco de Dados
    public Remedio searchRemedioId(int remedioId) {

        Remedio remedioReturn = null;
        connect();

        String sql = "SELECT * FROM Remédio";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                Remedio remedioTemp = new Remedio(
                        resultSet.getInt("idRemedio"),
                        resultSet.getString("nomeRemedio"),
                        resultSet.getString("fabricante"),
                        resultSet.getString("tipoRemedio"),
                        resultSet.getInt("estoque"),
                        resultSet.getString("validade"),
                        resultSet.getDouble("preço")
                );
                if (remedioTemp.getId() == remedioId) {
                    remedioReturn = remedioTemp;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());

        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return remedioReturn;
    }

    //Listar infos de um remedio no Banco de Dados
    public void selectInfosRemedio(int remedioId) {

        connect();

        boolean verificado;

        String sql = "SELECT * FROM Remédio WHERE idRemedio = ?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, remedioId);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Remedio remedioTemp = new Remedio(
                        resultSet.getInt("idRemedio"),
                        resultSet.getString("nomeRemedio"),
                        resultSet.getString("fabricante"),
                        resultSet.getString("tipoRemedio"),
                        resultSet.getInt("estoque"),
                        resultSet.getString("validade"),
                        resultSet.getDouble("preço")
                );
                if (remedioTemp.getId() == remedioId) {
                    System.out.println("Id do Remédio: " + remedioTemp.getId());
                    System.out.println("Nome do Remédio: " + remedioTemp.getNomeRemedio());
                    System.out.println("Fabricante do Remédio: " + remedioTemp.getFabricante());
                    System.out.println("Tipo do Remédio: " + remedioTemp.getTipoRemedio());
                    System.out.println("Quantidade no estoque: " + remedioTemp.getEstoque());
                    System.out.println("Validade do Remédio: " + remedioTemp.getValidade());
                    System.out.println("Preço do Remédio: " + remedioTemp.getPreco());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
    }

    public void updateRemedio(int remedioId, int novoEstoque) {
        connect();

        String sql = "UPDATE Remédio SET estoque=? WHERE idRemedio=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, novoEstoque); // Define o novo valor do estoque
            pst.setInt(2, remedioId); // Define o ID do remédio
            int rowsAffected = pst.executeUpdate(); // Executa a atualização e obtém o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Estoque atualizado com sucesso para o remédio com ID " + remedioId);
            } else {
                System.out.println("Nenhum remédio encontrado com o ID " + remedioId);
            }
        } catch (SQLException var13) {
            System.out.println("Erro: " + var13.getMessage());
            this.sucesso = false;
        } finally {
            try {
                connection.close();
                this.pst.close();
            } catch (SQLException var12) {
                System.out.println("Erro ao fechar conexão: " + var12.getMessage());
            }
        }
    }

    //Deletar remedio no Banco de Dados
    public boolean deleteRemedio(int remedioId) {

        connect();

        String sql = "DELETE FROM Remédio WHERE idRemedio=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, remedioId);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
}
