package Dao;
import util.Config;
import beans.Usuariobean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Conexao;

/**
 *
 * @author SYNC
 * @UserDao Classe responsavel pelas operações de login
 */
public class UserDao {
    Config config = new Config();
    /**
     *
     * @author SYNC
     * @Usuariobean Funcionalidade de efetuar Registro
     * @Usuariobean Iniciar o metado bean
     * @connection Iniciar a conexão.
     * @ps Inicar as intruções SQL.
     */
    public void Register(Usuariobean adduser) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO "+config.BancodeDados+config.Tabelar_user+" (user, Password) value (?,?)";
        try {
            conn = (Connection) Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, adduser.getUser());
            ps.setString(2, adduser.getPassword());
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                System.out.println("Sucesso");
            } else {
                System.out.println("Falhou");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    public static void main(String[] args) throws Exception {
        UserDao p1 = new UserDao();
        Usuariobean p2 = new Usuariobean();
        p2.setUser("Admin");
        p2.setPassword("1415");
        p1.Autenticar(p2);
    }

    public boolean AutenticarSucesso;

    /**
     * Método para autenticar um usuário no banco de dados.
     *
     * @param user Objeto Usuariobean que contém o nome de usuário e a senha.
     * @return boolean true se a autenticação for bem-sucedida, false caso
     * contrário.
     * @throws Exception se ocorrer um erro ao tentar autenticar o usuário.
     */
    public boolean Autenticar(Usuariobean user) throws Exception {
        AutenticarSucesso = false; // Inicializa AutenticarSucesso como false.
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM "+config.BancodeDados+config.Tabelar_user+" WHERE user = ? AND Password = ?;"; // Consulta SQL para autenticação.

        try {
            connection = Conexao.getConnection(); // Obtém a conexão com o banco de dados.
            ps = connection.prepareStatement(sql); // Prepara a instrução SQL.
            ps.setString(1, user.getUser()); // Define o nome de usuário.
            ps.setString(2, user.getPassword()); // Define a senha.
            rs = ps.executeQuery(); // Executa a consulta.

            // Verifica se há um resultado.
            if (rs.next()) {
                String usuario = rs.getString("user");
                String password = rs.getString("Password");

                // Verifica nome de usuário e senha.
                if (usuario.equals(user.getUser()) && password.equals(user.getPassword())) {
                    AutenticarSucesso = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções de SQL.
        } finally {
            Conexao.closeConnection(connection, ps, rs); // Fecha recursos.
        }
        return AutenticarSucesso; // Retorna o resultado da autenticação.
    }

}
