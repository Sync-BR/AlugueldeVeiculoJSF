package Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;
import util.Config;

public class VeiculosAtrasadosDao {

    //Consultar Veiculos alugados e não entregue
    Config config = new Config();

    public List<String> VeiculosAlugados() throws Exception {
        List<String> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_aluguel + " where Entregue = 'n'";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("Cliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return lista;
    }
 public List<String> VeiculosAlugadosCarros() throws Exception {
        List<String> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_aluguel + " where Entregue = 'n'";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("Veiculo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return lista;
    }
 public List<String> VeiculosAlugadosValor() throws Exception {
        List<String> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_aluguel + " where Entregue = 'n'";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("ValorPago"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return lista;
    }
}
