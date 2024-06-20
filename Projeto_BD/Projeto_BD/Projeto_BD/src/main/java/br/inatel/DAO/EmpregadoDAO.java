package br.inatel.DAO;

import br.inatel.Model.Cliente;
import br.inatel.Model.Empregado;

import java.sql.SQLException;
public class EmpregadoDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Inserir empregado no Banco de Dados
    public boolean insertEmpregado(Empregado empregado){

        connect();

        String sql = "INSERT INTO Empregado (idEmpregado,nome,sobrenome,função,salário,telefone,idade) values (?,?,?,?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,empregado.getIdEmpregado());
            pst.setString(2,empregado.getNome());
            pst.setString(3,empregado.getSobrenome());
            pst.setString(4,empregado.getFuncao());
            pst.setDouble(5,empregado.getSalario());
            pst.setString(6,empregado.getTelefone());
            pst.setInt(7, empregado.getIdade());
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

    //Buscar empregado no Banco de Dados
    public boolean selectEmpregadoId(int empregadoId) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM Empregado";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                Empregado empregadoTemp = new Empregado(
                        resultSet.getInt("idEmpregado"),
                        resultSet.getString("nome"),
                        resultSet.getString("sobrenome"),
                        resultSet.getString("função"),
                        resultSet.getDouble("salário"),
                        resultSet.getString("telefone"),
                        resultSet.getInt("idade")
                );
                if (empregadoTemp.getIdEmpregado() == empregadoId) {
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

    //Listar infos de um empregado no Banco de Dados
    public void selectInfosEmpregado(int empregadoId) {

        connect();

        boolean verificado;

        String sql = "SELECT * FROM Empregado WHERE idEmpregado = ?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, empregadoId);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Empregado empregadoTemp = new Empregado(
                        resultSet.getInt("idEmpregado"),
                        resultSet.getString("nome"),
                        resultSet.getString("sobrenome"),
                        resultSet.getString("função"),
                        resultSet.getDouble("salário"),
                        resultSet.getString("telefone"),
                        resultSet.getInt("idade")
                );
                if (empregadoTemp.getIdEmpregado() == empregadoId) {
                    System.out.println("Id do Empregado: " + empregadoTemp.getIdEmpregado());
                    System.out.println("Nome do Empregado: " + empregadoTemp.getNome());
                    System.out.println("Sobrenome do Empregado: " + empregadoTemp.getSobrenome());
                    System.out.println("Função do Empregado: " + empregadoTemp.getFuncao());
                    System.out.println("Salário do Empregado: " + empregadoTemp.getSalario());
                    System.out.println("Telefone do Empregado: " + empregadoTemp.getTelefone());
                    System.out.println("Idade do Empregado: " + empregadoTemp.getIdade());
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

    public void updateNomeEmpregado(int empregadoID, String novo_nome){

        connect();

        String sql = "UPDATE Empregado SET nome=? WHERE idEmpregado=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, novo_nome); // Define o novo nome
            pst.setInt(2, empregadoID); // Define o ID do empregado
            int rowsAffected = pst.executeUpdate(); // Executa a atualização e obtém o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Nome atualizado com sucesso para o Empregado com ID " + empregadoID);
            } else {
                System.out.println("Nenhum Empregado encontrado com o ID " + empregadoID);
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

    public void updateSobrenomeEmpregado(int empregadoID, String novo_sobrenome){

        connect();

        String sql = "UPDATE Empregado SET sobrenome=? WHERE idEmpregado=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, novo_sobrenome); // Define o novo sobrenome
            pst.setInt(2, empregadoID); // Define o ID do empregado
            int rowsAffected = pst.executeUpdate(); // Executa a atualização e obtém o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Sobrenome atualizado com sucesso para o Empregado com ID " + empregadoID);
            } else {
                System.out.println("Nenhum Empregado encontrado com o ID " + empregadoID);
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

    public void updateTelefoneEmpregado(int empregadoID, String novo_telefone){

        connect();

        String sql = "UPDATE Empregado SET telefone=? WHERE idEmpregado=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, novo_telefone); // Define o novo telefone
            pst.setInt(2, empregadoID); // Define o ID do empregado
            int rowsAffected = pst.executeUpdate(); // Executa a atualização e obtém o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Telefone atualizado com sucesso para o Empregado com ID " + empregadoID);
            } else {
                System.out.println("Nenhum Empregado encontrado com o ID " + empregadoID);
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


    public void updateSalarioEmpregado(int empregadoID, double novo_salario){

        connect();

        String sql = "UPDATE Empregado SET salário=? WHERE idEmpregado=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, novo_salario); //define o novo salário
            pst.setInt(2, empregadoID); // Define o ID do Empregado
            int rowsAffected = pst.executeUpdate(); // Executa a atualização e obtém o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Salário atualizado com sucesso para o Empregado com ID " + empregadoID);
            } else {
                System.out.println("Nenhum Empregado encontrado com o ID " + empregadoID);
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


    public void updateFuncaoEmpregado(int empregadoID, String nova_funcao){

        connect();

        String sql = "UPDATE Empregado SET função=? WHERE idEmpregado=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nova_funcao); // Define a nova da função
            pst.setInt(2, empregadoID); // Define o ID do empregado
            int rowsAffected = pst.executeUpdate(); // Executa a atualização e obtém o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Função atualizada com sucesso para o Empregado com ID " + empregadoID);
            } else {
                System.out.println("Nenhum Empregado encontrado com o ID " + empregadoID);
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

    public void updateIdadeEmpregado(int empregadoID, int nova_idade){

        connect();

        String sql = "UPDATE Empregado SET idade=? WHERE idEmpregado=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, nova_idade); // Define o novo valor da idade
            pst.setInt(2, empregadoID); // Define o ID do empregado
            int rowsAffected = pst.executeUpdate(); // Executa a atualização e obtém o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Função atualizada com sucesso para o Empregado com ID " + empregadoID);
            } else {
                System.out.println("Nenhum Empregado encontrado com o ID " + empregadoID);
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

    //Deletar empregado no Banco de Dados
    public boolean deleteEmpregado(int empregadoId) {

        connect();

        String sql = "DELETE FROM Empregado WHERE idEmpregado=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, empregadoId);
            pst.execute();
            sucesso = true;
            System.out.println("Empregado deletado com sucesso");
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
