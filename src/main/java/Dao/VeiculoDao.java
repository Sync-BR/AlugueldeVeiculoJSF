package Dao;

import beans.Clientebean;
import beans.Veiculobean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Config;
import util.Conexao;

/**
 *
 * @author Eduardo
 */
public class VeiculoDao {

    Config config = new Config();



    /**
     *
     * Deleta um cliente do banco de dados.
     *
     * @param addCliente Instância da classe Clientebean contendo os dados do
     * cliente a ser deletado.
     * @return true se o cliente foi deletado com sucesso, false caso contrário.
     * @throws Exception Se ocorrer algum erro durante o processo de exclusão.
     */
    public boolean Deletar(Veiculobean Deleteveiculo) throws Exception {
        boolean DeletadoSucesso = false;
        Connection conn = null;
        PreparedStatement ps = null;

        // Monta a consulta SQL para exclusão de um cliente.
        String sql = "DELETE FROM " + config.BancodeDados + config.Tabela_veiculo + " WHERE placa = ?";

        try {
            // Prepara a conexão e a instrução SQL.
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, Deleteveiculo.getPlaca());
            int deletado = ps.executeUpdate();
            if (deletado > 0) {
                DeletadoSucesso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão e a instrução SQL.
            Conexao.closeConnection(conn, ps);
        }

        // Retorna o status da exclusão do cliente.
        return DeletadoSucesso;
    }

    /**
     * Buscar o carro pela placa
     *
     * @param buscar instancia a classe Veiculobean
     * @return retornar os valores obtidos
     * @throws Exception Se ocorrer algum erro durante o processo de cadastro.
     */
    public String BuscarPlaca(Veiculobean buscar) throws Exception {
        String veiculo = null;
        String numero = null;
        String fabricante = null;
        String modelo = null;
        int anoModelo = 0;
        int qtdPortas = 0;
        String acessorios = null;
        String placa = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_veiculo + " WHERE placa = ?";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, buscar.getPlaca());
            rs = ps.executeQuery();
            while (rs.next()) {
                numero = rs.getString("numero");
                fabricante = rs.getString("fabricante");
                modelo = rs.getString("modelo");
                acessorios = rs.getString("Acessorios");
                placa = rs.getString("placa");
                anoModelo = rs.getInt("anoModelo");
                qtdPortas = rs.getInt("qtdPortas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return veiculo + "--" + numero + "--" + fabricante + "--" + modelo + "--" + acessorios + "--" + placa + "--" + anoModelo + "--" + qtdPortas;
    }

    /**
     * Carregar o conjunto de placas do veiculo
     *
     * @return true se o Veiculobean foi editado com sucesso, false caso
     * contrário.
     * @throws Exception Se ocorrer algum erro durante o processo de cadastro.
     */
    public List<String> CarregarLista() throws Exception {
        System.out.println("Etapa 3");
        List<String> Dados = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + config.BancodeDados + config.Tabela_veiculo;
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dados.add(rs.getString("placa"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return Dados;

    }

    public static void main(String[] args) throws Exception {
        Veiculobean add = new Veiculobean();
        VeiculoDao p1 = new VeiculoDao();
        List<String> Lista;
        add.setNumero("52111");
        add.setPlaca("BMS254BR");
        add.setModelo("Ford");
        add.setFabricante("Vowai");
        add.setAcessorio("nenhum");
        add.setQtdPortas(4);
        add.setAnoModelo(2014);
        Lista = p1.CarregarLista();
        for (int index = 0; index < Lista.size(); index++) {
            System.out.println("Nome: " + Lista.get(index));
        }

    }

    /**
     * @param editarVeiculo Instância da classe Veiculobean contendo os dados do
     * Veiculobean a ser cadastrado. Editar um veiculo no banco de dados.
     *
     * @return true se o Veiculobean foi editado com sucesso, false caso
     * contrário.
     * @throws Exception Se ocorrer algum erro durante o processo de cadastro.
     */
    public boolean Editar(Veiculobean editarVeiculo) throws Exception {
        boolean Editado = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE " + config.BancodeDados + config.Tabela_veiculo
                + " SET numero = ?,  fabricante = ?, modelo = ?, anoModelo = ?, qtdPortas = ?, Acessorios = ? WHERE placa = ?";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, editarVeiculo.getNumero());
            ps.setString(2, editarVeiculo.getFabricante());
            ps.setString(3, editarVeiculo.getModelo());
            ps.setInt(4, editarVeiculo.getAnoModelo());
            ps.setInt(5, editarVeiculo.getQtdPortas());
            ps.setString(6, editarVeiculo.getAcessorio());
            ps.setString(7, editarVeiculo.getPlaca());
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                Editado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return Editado;
    }

    /**
     * Registra um novo veiculo no banco de dados.
     *
     * @param addveiculo Instância da classe Veiculobean contendo os dados do
     * Veiculobean a ser cadastrado.
     * @return true se o Veiculobean foi cadastrado com sucesso, false caso
     * contrário.
     * @throws Exception Se ocorrer algum erro durante o processo de cadastro.
     */
    public boolean Registrar(Veiculobean addveiculo) throws Exception {
        boolean Sucesso = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String Sql = "INSERT INTO " + config.BancodeDados + config.Tabela_veiculo
                + " (numero, placa, fabricante, modelo, anoModelo, qtdPortas, Acessorios) VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(Sql);
            ps.setString(1, addveiculo.getNumero());
            ps.setString(2, addveiculo.getPlaca());
            ps.setString(3, addveiculo.getFabricante());
            ps.setString(4, addveiculo.getModelo());
            ps.setInt(5, addveiculo.getAnoModelo());
            ps.setInt(6, addveiculo.getQtdPortas());
            ps.setString(7, addveiculo.getAcessorio());
            int Registrado = ps.executeUpdate();
            if (Registrado > 0) {
                Sucesso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return Sucesso;
    }

}
