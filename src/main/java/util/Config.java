
package util;
import util.Conexao;
/**
 *
 * @author Eduardo
 * @Config Definições de nome de tabela dos bancos de dados
 */
public class Config {
    
    Conexao Consultar = new Conexao();
    public String BancodeDados = "autenticador.";
    public String Tabelar_user = "usuarios"; 
    public String Tabela_Clientes = "cliente";
    
}
