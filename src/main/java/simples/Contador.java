
package simples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Conexao;
import util.Config;

/**
 *
 * @author Eduarrdo
 * @Contador Classe responsavel pelas operações de contagem
 */
public class Contador {
    Config config = new Config();
    public int ContarClientes() throws Exception{
        int TotalClientes = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM "+config.BancodeDados+config.Tabela_Clientes;
        try{
            connection = Conexao.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                TotalClientes++;
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            Conexao.closeConnection(connection, ps, rs);
        }
        
        return TotalClientes;
    }
    
     public int ContarVeiculos() throws Exception{
        int TotalVeiculos = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM "+config.BancodeDados+config.Tabela_veiculo;
        try{
            connection = Conexao.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                TotalVeiculos++;
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            Conexao.closeConnection(connection, ps, rs);
        }
        
        return TotalVeiculos;
    }
     
     public int ContarAluguel() throws Exception{
        int TotalAluguel = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM "+config.BancodeDados+config.Tabela_aluguel;
        try{
            connection = Conexao.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                TotalAluguel++;
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            Conexao.closeConnection(connection, ps, rs);
        }
        
        return TotalAluguel;
    }
    
}
