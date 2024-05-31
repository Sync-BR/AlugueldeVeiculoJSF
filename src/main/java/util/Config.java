
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
    public String Tabela_veiculo = "veiculos";
    public String Tabela_aluguel = "aluguel";
    
    //Configurações de redirecionamento de pagina
    public String PaginaVeiculos =  "Veiculos?faces-redirect=true";
    public String PaginaAluguel =  "Aluguel?faces-redirect=true";
    
}
