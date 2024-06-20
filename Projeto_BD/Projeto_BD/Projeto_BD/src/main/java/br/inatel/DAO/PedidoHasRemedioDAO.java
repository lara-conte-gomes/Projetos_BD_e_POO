package br.inatel.DAO;

import br.inatel.Model.Cliente;
import br.inatel.Model.Empregado;
import br.inatel.Model.PedidoHasRemedio;
import br.inatel.Model.Remedio;

import java.sql.SQLException;
public class PedidoHasRemedioDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Inserir relação de pedido e remédio no Banco de Dados
    public boolean insertPedidoRemedio(PedidoHasRemedio pedidoHasRemedio){

        connect();

        String sql = "INSERT INTO Pedido_has_Remédio (Pedido_IDPedido,Remédio_idRemedio,Preco,Quantidade_pedida,Id_do_pedido) values (?,?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,pedidoHasRemedio.getIdPedido());
            pst.setInt(2,pedidoHasRemedio.getIdRemedio());
            pst.setDouble(3,pedidoHasRemedio.getPreco());
            pst.setInt(4,pedidoHasRemedio.getQntPedido());
            pst.setInt(5,pedidoHasRemedio.getIdDoPedido());
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

    public boolean selectPedidoHasRemedioId(int pedidoId) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM Pedido_has_Remédio";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                PedidoHasRemedio pedidoHasRemedio  = new PedidoHasRemedio(
                        resultSet.getInt("Pedido_IDPedido"),
                        resultSet.getInt("Remédio_idRemedio"),
                        resultSet.getDouble("Preco"),
                        resultSet.getInt("Quantidade_pedida"),
                        resultSet.getInt("Id_do_pedido")
                );
                if (pedidoHasRemedio.getIdPedido() == pedidoId) {
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

    public void searchPedidoRemedio(int pedidoId){

        connect();

        String sql = "SELECT * FROM Pedido_has_Remédio WHERE Pedido_IDPedido=?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, pedidoId);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                PedidoHasRemedio pedidoHasRemedio  = new PedidoHasRemedio(
                        resultSet.getInt("Pedido_IDPedido"),
                        resultSet.getInt("Remédio_idRemedio"),
                        resultSet.getDouble("Preco"),
                        resultSet.getInt("Quantidade_pedida"),
                        resultSet.getInt("Id_do_pedido")
                );
                if (pedidoHasRemedio.getIdPedido() == pedidoId) {
                    System.out.println("Id do PedidoHasRemedio: " + pedidoHasRemedio.getIdDoPedido());
                    System.out.println("Id do Pedido: " + pedidoHasRemedio.getIdPedido());
                    System.out.println("Id do Remédio: " + pedidoHasRemedio.getIdRemedio());
                    System.out.println("Preço: " + pedidoHasRemedio.getPreco());
                    System.out.println("Quantidade do remédio: " + pedidoHasRemedio.getQntPedido());
                }
            }
        } catch (SQLException ex){
            System.out.println("Erro de conexao  = " + ex.getMessage());
            sucesso = false;
        }finally {
            try {
                connection.close();
                //pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexao " + e.getMessage());
            }
        }
    }
}
