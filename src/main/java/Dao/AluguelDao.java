package Dao;

import beans.Aluguelbean;
import beans.Clientebean;
import beans.Veiculobean;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import util.Config;
import util.Conexao;

/**
 *
 * @author Eduardo
 * @AluguelDao Responsavel pelas operações do aluguel;
 */
public class AluguelDao {

    Config config = new Config();

    
    public List<String> ListarVeiculos() throws Exception {
        List<String> Cliente = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_aluguel;
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("Cliente");
                Cliente.add(nome);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return Cliente;
    }

    public boolean DeletarAluguel(Aluguelbean delete) throws Exception {
        boolean deletado = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM " + config.BancodeDados + config.Tabela_aluguel + " WHERE Cliente = ?";

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, delete.getClienteNome());
            int status = ps.executeUpdate();
            if (status > 0) {
                deletado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return deletado;
    }

    public String BuscarCliente(Aluguelbean buscar) throws Exception {
        String Veiculo = null;
        String DataAlugado = null;
        String DataVencimento = null;
        String Entregue = null;
        String Observacao = null;
        BigDecimal Valorpago = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_aluguel + " Where Cliente = ?";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, buscar.getClienteNome());
            rs = ps.executeQuery();
            while (rs.next()) {
                Veiculo = rs.getString("Veiculo");
                DataAlugado = String.valueOf(rs.getDate("DataAluguel"));
                DataVencimento = String.valueOf(rs.getDate("DataEntrega"));
                Entregue = rs.getString("Entregue");
                Observacao = rs.getString("Observacao");
                Valorpago = rs.getBigDecimal("ValorPago");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return DataAlugado + "--" + DataVencimento + "--" + Entregue + "--" + Observacao + "--" + Valorpago + "--" + Veiculo;
    }

    public boolean EditarAluguel(Aluguelbean edit) throws Exception {
        boolean sucesso = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE " + config.BancodeDados + config.Tabela_aluguel
                + " SET DataAluguel = ?,  DataEntrega = ?, Cliente = ?, Entregue = ?, Observacao = ?, ValorPago  = ? WHERE Veiculo = ?";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, edit.getDataAluguel());
            ps.setDate(2, edit.getDataEntrega());
            ps.setString(3, edit.getClienteNome());
            ps.setString(4, String.valueOf(edit.getEntregue()));
            ps.setString(5, edit.getObservacao());
            ps.setBigDecimal(6, edit.getValorPago());
            ps.setString(7, edit.getVeiculoModelo());
            int status = ps.executeUpdate();
            if (status > 0) {
                sucesso = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps);
        }

        return sucesso;

    }

    public boolean VerificarAluguel(Aluguelbean addAluguel) throws Exception {
        boolean Encontrado = false;
        String Status = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_aluguel + " Where Veiculo = ?";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, addAluguel.getVeiculoModelo());
            rs = ps.executeQuery();
            while (rs.next()) {
                Status = rs.getString("Entregue");
                Encontrado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }

        return Encontrado;
    }

    public boolean AlugarVeiculo(Aluguelbean addAluguel) throws Exception {

        boolean Sucesso = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO " + config.BancodeDados + config.Tabela_aluguel
                + " (Veiculo, DataAluguel, DataEntrega, Cliente, Entregue, Observacao,ValorPago) "
                + "VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, addAluguel.getVeiculoModelo());
            ps.setDate(2, addAluguel.getDataAluguel());
            ps.setDate(3, addAluguel.getDataEntrega());
            ps.setString(4, addAluguel.getClienteNome());
            ps.setString(5, String.valueOf(addAluguel.getEntregue()));
            ps.setString(6, addAluguel.getObservacao());
            ps.setBigDecimal(7, addAluguel.getValorPago());
            int sucess = ps.executeUpdate();
            if (sucess > 0) {
                Sucesso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps);
        }

        return Sucesso;
    }

    /**
     * Veiculo DataAluguel DataEntrega Cliente Entregue Observacao ValorPago
     */
}
