package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável pelas funcionalidades de conexão e fechamento da conexão
 * com o banco de dados.
 * 
 * @autor SYNC
 * 
 * Parâmetros de conexão:
 * @param user     Usuário do banco de dados.
 * @param password Senha do banco de dados.
 * @param db       Nome do banco de dados.
 * @param url      URL do servidor.
 * @param jdbc     URL de conexão com o MySQL.
 * @param jdbcDriver Driver do JDBC.
 */
public class Conexao {

    private String user;
    private String password;
    private String db;
    private String url;
    private String jdbc;
    private String jdbcDriver;
    private static Conexao connection = null;

    /**
     * Construtor padrão que inicializa os parâmetros de conexão com valores padrão.
     */
    public Conexao() {
        user = "root";
        password = "";
        db = "autenticador";
        url = "127.0.0.1/";
        jdbc = "jdbc:mysql://";
        jdbcDriver = "com.mysql.jdbc.Driver";
    }

    /**
     * Método para obter uma conexão com o banco de dados.
     * 
     * @return Conexao uma instância da conexão com o banco de dados.
     * @throws Exception se ocorrer um erro ao tentar se conectar ao banco de dados.
     */
    public static Connection getConnection() throws Exception {
        Conexao classConnection = new Conexao();
        Class.forName(classConnection.jdbcDriver);
        try {
            return DriverManager.getConnection(
                classConnection.jdbc + 
                classConnection.url + 
                classConnection.db, 
                classConnection.user, 
                classConnection.password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Connection) connection;
    }

    /**
     * Método para fechar a conexão, o PreparedStatement e o ResultSet.
     * 
     * @param conn Conexão a ser fechada.
     * @param ps PreparedStatement a ser fechado.
     * @param rs ResultSet a ser fechado.
     * @throws Exception se ocorrer um erro ao fechar os recursos.
     */
    private static void Close(Connection conn, PreparedStatement ps, ResultSet rs) throws Exception {
        if (conn != null) {
            conn.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * Método para fechar a conexão, o PreparedStatement e o ResultSet.
     * 
     * @param conn Conexão a ser fechada.
     * @param ps PreparedStatement a ser fechado.
     * @param rs ResultSet a ser fechado.
     * @throws Exception se ocorrer um erro ao fechar os recursos.
     */
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws Exception {
        Close(conn, ps, rs);
    }

    /**
     * Método para fechar a conexão e o PreparedStatement.
     * 
     * @param conn Conexão a ser fechada.
     * @param ps PreparedStatement a ser fechado.
     * @throws Exception se ocorrer um erro ao fechar os recursos.
     */
    public static void closeConnection(Connection conn, PreparedStatement ps) throws Exception {
        Close(conn, ps, null);
    }

    /**
     * Método para fechar apenas a conexão.
     * 
     * @param conn Conexão a ser fechada.
     * @throws Exception se ocorrer um erro ao fechar a conexão.
     */
    public static void closeConnection(Connection conn) throws Exception {
        Close(conn, null, null);
    }
}
