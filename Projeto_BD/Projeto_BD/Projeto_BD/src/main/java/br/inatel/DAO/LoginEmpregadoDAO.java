package br.inatel.DAO;

import br.inatel.Model.Cliente;
import br.inatel.Model.LoginEmpregado;

import java.sql.SQLException;

public class LoginEmpregadoDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Adicionando senha, email e idEmpregado no login
    public boolean insertLoginEmpregado(LoginEmpregado login) {
        connect();

        String sql = "INSERT INTO loginempregado (id, senha, email, Empregado_idEmpregado) values (?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,login.getId());
            pst.setString(2,login.getSenha());
            pst.setString(3,login.getEmail());
            pst.setInt(4,login.getEmpregadoID());
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

    //Buscar login no Banco de Dados
    public boolean selectLoginEmpregadoId(int idLogin) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM loginempregado";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                LoginEmpregado loginTemp = new LoginEmpregado(
                        resultSet.getInt("id"),
                        resultSet.getString("senha"),
                        resultSet.getString("email"),
                        resultSet.getInt("Empregado_idEmpregado")
                );
                if(loginTemp.getId() == idLogin){
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

    //Listar infos de um login no Banco de Dados
    public void selectInfosLoginEmpregado(int loginID) {

        connect();

        boolean verificado;

        String sql = "SELECT * FROM loginempregado WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, loginID);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                LoginEmpregado loginTemp = new LoginEmpregado(
                        resultSet.getInt("id"),
                        resultSet.getString("senha"),
                        resultSet.getString("email"),
                        resultSet.getInt("Empregado_idEmpregado")
                );
                if (loginTemp.getId() == loginID) {
                    System.out.println("Id do Login: " + loginTemp.getId());
                    System.out.println("Senha: " + loginTemp.getSenha());
                    System.out.println("Email: " + loginTemp.getEmail());
                    System.out.println("ID do Empregado: " + loginTemp.getEmpregadoID());
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

    //Deletar login no Banco de Dados
    public boolean deleteLoginEmpregado(int loginID) {

        connect();

        String sql = "DELETE FROM loginempregado WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, loginID);
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
