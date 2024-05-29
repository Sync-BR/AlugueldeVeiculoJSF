package beans;

import Dao.ClienteDao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author SYNC
 * @Clientebean Responsavel pela setagem e retornor de informações JSF.
 * @ManagedBean Definir que a classe é um beans gerenciado.
 * @ViewScoped Definir o tempo de vida com interação do usuario.
 */
@ManagedBean(name = "Clientebean")
@ViewScoped
public class Clientebean implements Serializable {

    private int id;
    private String Nome;
    private String Endereco;
    private String Uf;
    private String Email;
    private String Telefone;
    private String Cpf;
    private List<String> ListaDeClientes;
    private String ClienteSelecionado;

    /**
     * @init Metado de inicialização
     * @PostConstruct Iniciar o metado automatico.
     */
    @PostConstruct
    public void init() {
        try {
            CarregaLista();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @throws java.lang.Exception
     * @CarregaLista Metado para carregar a lista de cliente
     *
     */
    public void CarregaLista() throws Exception {
        ClienteDao CarregaLista = new ClienteDao();
        this.ListaDeClientes = CarregaLista.CarregarLista();

    }

    /**
     * @param event Efetuar interação para retornar o nome selecionado
     * @onClienteChange Metado para retornar o cliente selecionado
     *
     */
    public void onClienteChange(AjaxBehaviorEvent event) {

        System.out.println("Cliente selecionado: " + ClienteSelecionado);
        // Aqui você pode adicionar a lógica para lidar com o cliente selecionado
    }

    /**
     * @Editar Editar o cliente selecionado.
     * @Exception Tratar os possiveis erros
     *
     */
    public void Editar() throws Exception {
        Clientebean Addcliente = new Clientebean();
        ClienteDao EditarCliente = new ClienteDao();
        Addcliente.setNome(Nome);
        Addcliente.setCpf(Cpf);
        Addcliente.setEmail(Email);
        Addcliente.setEndereco(Endereco);
        Addcliente.setTelefone(Telefone);
        Addcliente.setUf(Uf);
        System.out.println("Logs de parametros");
        System.out.println(Addcliente.getNome());
        System.out.println(Addcliente.getCpf());
        System.out.println(Addcliente.getEmail());
        System.out.println(Addcliente.getEndereco());
        System.out.println(Addcliente.getUf());
        System.out.println(Addcliente.getTelefone());
        boolean teste = EditarCliente.Editar(Addcliente);
        if (teste) {
            System.out.println("Editado");
        } else {
            System.out.println("Falhou");
        }
    }

    /**
     * @Registrar Efetuar o registro do cliente
     * @Exception Tratar os possiveis erros
     *
     */
    public void Registrar() throws Exception {
        Clientebean Addcliente = new Clientebean();
        ClienteDao registrer = new ClienteDao();
        //Adicionar um cliente
        Addcliente.setNome(Nome);
        Addcliente.setCpf(Cpf);
        Addcliente.setEmail(Email);
        Addcliente.setTelefone(Telefone);
        Addcliente.setEndereco(Endereco);
        Addcliente.setUf(Uf);
        boolean registrado = registrer.Register(Addcliente);
        if (registrado) {
            System.out.println("Cadastrado");
        } else {
            System.out.println("Falhou");
        }
    }

    /**
     * @Deletar Deletar o cliente por CPF
     * @Exception Tratar os possiveis erros
     *
     */
    public void Deletar() throws Exception {
        Clientebean DeletarCliente = new Clientebean();
        ClienteDao deleta = new ClienteDao();
        DeletarCliente.setCpf(Cpf);
        deleta.Deletar(DeletarCliente);
    }

    public Clientebean() throws Exception {
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getUf() {
        return Uf;
    }

    public void setUf(String Uf) {
        this.Uf = Uf;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        this.Cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getListaDeClientes() {
        return ListaDeClientes;
    }

    public void setListaDeClientes(List<String> ListaDeClientes) {
        this.ListaDeClientes = ListaDeClientes;
    }

    public String getClienteSelecionado() {
        return ClienteSelecionado;
    }

    public void setClienteSelecionado(String ClienteSelecionado) {
        this.ClienteSelecionado = ClienteSelecionado;
    }
}
