
package simples;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;
import util.Config;
/**
 *
 * @author Eduardo
 * @Tabela Classe responsavel pelos retorno das tabela do jsp
 */
public class Tabela {
    Config config = new Config();
    public List<String> Veiculos = new ArrayList<>();
    public List<String> Clientes = new ArrayList<>();
    public List<BigDecimal> Valor = new ArrayList<>();
    
    public void VeiculosAtrasados() throws Exception{
        Veiculos.clear();
        Clientes.clear();
        Valor.clear();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " +config.BancodeDados+config.Tabela_aluguel;
        try{
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Veiculos.add(rs.getString("Veiculo"));
                Clientes.add(rs.getString("Cliente"));
                Valor.add(rs.getBigDecimal("ValorPago"));

            }
            
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            Conexao.closeConnection(conn, ps, rs);
        }
        
    }
    
    public List<String> AtrasadosClientes = new ArrayList<>();
    public List<String> AtrasadosDatas = new ArrayList<>();
    public List<String> AtrasadosVeiculos = new ArrayList<>();
    public void VeiculusAtrasados() throws Exception {
        AtrasadosClientes.clear();
        AtrasadosDatas.clear();
        AtrasadosVeiculos.clear();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String paran = "n";
        String sql = "SELECT * FROM " +config.BancodeDados+config.Tabela_aluguel+ " Where Entregue = ?";
        try{
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, paran);
            rs = ps.executeQuery();
            while (rs.next()) {                
                String Cliente = rs.getString("Cliente");
                AtrasadosClientes.add(Cliente);
                String Veiculo = rs.getString("Veiculo");
                AtrasadosVeiculos.add(Veiculo);
                Date date = rs.getDate("DataAluguel");
                String data = df.format(date);
                AtrasadosDatas.add(data);
                
            }
        
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            Conexao.closeConnection(conn, ps, rs);
        }
        
    
    }
    
    public static void main(String[] args)  throws Exception{
        Tabela P1 = new Tabela();
        P1.VeiculusAtrasados();
    }
    
}
