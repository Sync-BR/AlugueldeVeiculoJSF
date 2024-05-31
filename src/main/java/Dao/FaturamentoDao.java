
package Dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;
import util.Config;

/**
 *
 * @author Eduar
 */
public class FaturamentoDao {
    Config config = new Config();
    
    public BigDecimal Calcular()throws Exception{
        List<BigDecimal> Valortotal = new ArrayList<>();
        BigDecimal valor = BigDecimal.ZERO;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " +config.BancodeDados+config.Tabela_aluguel;
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {   
                Valortotal.add(rs.getBigDecimal("ValorPago"));
            }
            
            for(int index = 0; index < Valortotal.size(); index ++){
                 valor = valor.add(Valortotal.get(index));
            }
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            Conexao.closeConnection(conn, ps, rs);
        }
        return valor;
        
    }
    public static void main(String[] args) throws Exception{
        FaturamentoDao run = new FaturamentoDao();
        run.Calcular();
    }
}
