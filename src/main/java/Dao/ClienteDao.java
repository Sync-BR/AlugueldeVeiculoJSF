package Dao;

import util.Config;
import beans.Clientebean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

/**
 * Classe responsável pelas operações relacionadas ao cliente no banco de dados.
 * Fornece métodos para registrar clientes.
 *
 * @autor Eduardo
 */
public class ClienteDao {

    /**
     * Instância da classe Config utilizada para obter configurações do banco de
     * dados.
     */
    Config config = new Config();

    
    public List<String> CarregarLista() throws Exception{
        List<String> Lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM "+config.BancodeDados+config.Tabela_Clientes;
        try{
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Lista.add(rs.getString("Nome"));
            }
 
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            Conexao.closeConnection(conn, ps, rs);
        }
        return Lista;
    }
    
    
    
    
    
    /**
     * Indicador do status do cadastro do cliente.
     */
    private boolean RegistradoSucesso;

    /**
     * Registra um novo cliente no banco de dados.
     *
     * @param addCliente Instância da classe Clientebean contendo os dados do
     * cliente a ser cadastrado.
     * @return true se o cliente foi cadastrado com sucesso, false caso
     * contrário.
     * @throws Exception Se ocorrer algum erro durante o processo de cadastro.
     */
    public boolean Register(Clientebean addCliente) throws Exception {
        RegistradoSucesso = false;
        Connection conn = null;
        PreparedStatement ps = null;

        // Monta a consulta SQL para inserção de um novo cliente.
        String sql = "INSERT INTO " + config.BancodeDados + config.Tabela_Clientes
                + " (Nome, Endereco, Uf, Email, Telefone, Cpf) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Prepara a conexão e a instrução SQL.
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, addCliente.getNome());
            ps.setString(2, addCliente.getEndereco());
            ps.setString(3, addCliente.getUf());
            ps.setString(4, addCliente.getEmail());
            ps.setString(5, addCliente.getTelefone());
            ps.setString(6, addCliente.getCpf());

            // Executa a inserção e verifica se foi bem-sucedida.
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                RegistradoSucesso = true;
                System.out.println("Registrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão e a instrução SQL.
            Conexao.closeConnection(conn, ps);
        }

        // Retorna o status do cadastro do cliente.
        return RegistradoSucesso;
    }

    /**
     * Indicador do status da exclusão do cliente.
     */
    private boolean DeletadoSucesso;

    /**
     * Deleta um cliente do banco de dados.
     *
     * @param addCliente Instância da classe Clientebean contendo os dados do
     * cliente a ser deletado.
     * @return true se o cliente foi deletado com sucesso, false caso contrário.
     * @throws Exception Se ocorrer algum erro durante o processo de exclusão.
     */
    public boolean Deletar(Clientebean addCliente) throws Exception {
        DeletadoSucesso = false;
        Connection conn = null;
        PreparedStatement ps = null;

        // Monta a consulta SQL para exclusão de um cliente.
        String sql = "DELETE FROM " + config.BancodeDados + config.Tabela_Clientes + " WHERE Cpf = ?";

        try {
            // Prepara a conexão e a instrução SQL.
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, addCliente.getCpf());
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

    private boolean EditadoSucesso; //Responsavel pelo retorno da editação se true a operação foi bem sucedida, caso ao contrario houve uma falha;

    /**
     * Edita os dados de um cliente no banco de dados.
     *
     * @param addCliente Instância da classe Clientebean contendo os novos dados
     * do cliente a ser editado.
     * @return true se o cliente foi editado com sucesso, false caso contrário.
     * @throws Exception Se ocorrer algum erro durante o processo de edição.
     */
    public boolean Editar(Clientebean addCliente) throws Exception {
        EditadoSucesso = false;
        Connection conn = null;
        PreparedStatement ps = null;

        // Monta a consulta SQL para edição de um cliente.
        String sql = "UPDATE " + config.BancodeDados + config.Tabela_Clientes
                + " SET Nome = ?,  Endereco = ?, Uf = ?, Email = ?, Telefone = ? WHERE Cpf = ?";

        try {
            // Prepara a conexão e a instrução SQL.
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, addCliente.getNome());
            ps.setString(2, addCliente.getEndereco());
            ps.setString(3, addCliente.getUf());
            ps.setString(4, addCliente.getEmail());
            ps.setString(5, addCliente.getTelefone());
            ps.setString(6, addCliente.getCpf());

            // Executa a edição e verifica se foi bem-sucedida.
            int editado = ps.executeUpdate();
            if (editado > 0) {
                EditadoSucesso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão e a instrução SQL.
            Conexao.closeConnection(conn, ps);
        }

        // Retorna o status da edição do cliente.
        return EditadoSucesso;
    }

    /**
     * Método principal para testar o registro de um cliente.
     *
     * @param args Argumentos da linha de comando.
     * @throws Exception Se ocorrer algum erro durante o processo de teste.
     */
    public static void main(String[] args) throws Exception {
        Clientebean addCliente = new Clientebean();
        ClienteDao p1 = new ClienteDao();

        // Configura os dados do cliente.
        addCliente.setNome("SYNC");
        addCliente.setEndereco("Alto do cabrito");
        addCliente.setUf("Ba");
        addCliente.setEmail("Eduardofreitas@gmail.com");
        addCliente.setTelefone("71981590149");
        addCliente.setCpf("06126039522");

        // Tenta registrar o cliente.
        List<String> Lista = p1.CarregarLista();
        for(int index = 0; index < Lista.size(); index++){
            System.out.println("Lista 2.0: " +Lista.get(index));
        }
    }
}
