package beans;

import Dao.AluguelDao;
import Dao.ClienteDao;
import Dao.VeiculoDao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import util.Config;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "Aluguelbean")
@ViewScoped
public class Aluguelbean {

    private int Id;
    private Veiculobean Veiculo;
    private Date DataAluguel;
    private Date DataEntrega;
    private Clientebean Cliente;
    private char Entregue;
    private String Observacao;
    private BigDecimal ValorPago;
    //Variavel de retorno de cliente
    private List<String> ListarClientes;
    private List<String> ListarVeiculosClientes;
    private String ClienteSelecionado;
    //Variavel de retornor de veiculo
    private List<String> ListarVeiculos;
    private String VeiculoSelecionado;
    //Variavel de consultar de class
    private Clientebean ConsultarClientes;
    private Veiculobean ConsultarVeiculos;
    //Variavel de datas do tipo String
    private String DataAtualSTR;
    private String DataVencimentoSTR;
    //Veiculo
    private String VeiculoModelo;
    private String ClienteNome;
    Config config = new Config();

    //Contrutor
    public Aluguelbean() {
        this.ConsultarClientes = new Clientebean();
        this.ConsultarVeiculos = new Veiculobean();
    }

    @PostConstruct
    public void init() {
        try {
            ConsultarVeiculosAlugado();
            CarregarAlugueisClientes();
            CarregarCarros();
            CarregarClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //veiculo+"--"+DataAlugado+"--"+DataVencimento+"--"+Cliente
    //Consultar veiculos alugados e não entregue
    public void ConsultarVeiculosAlugado() throws Exception {
        AluguelDao consultar = new AluguelDao();
//       List<String> dados = consultar.VeiculosAlugados();
    //    for (int index = 0; index < dados.size(); index++) {
    //        System.out.println("Cliente: " + dados.get(index));
    //    }

    }

    //Carregar todos veiculos
    public void CarregarCarros() throws Exception {
        VeiculoDao carregar = new VeiculoDao();
        this.ListarVeiculos = carregar.CarregarLista();

    }

    public void CarregarCarrosClientes(AjaxBehaviorEvent event) throws Exception {
        AluguelDao buscarCliente = new AluguelDao();
        Aluguelbean add = new Aluguelbean();
        add.setClienteNome(ClienteSelecionado);
        buscarCliente.BuscarCliente(add);
        

    }

    //Carregar lista de clientes
    public void CarregarClientes() throws Exception {
        ClienteDao buscar = new ClienteDao();
        this.ListarClientes = buscar.CarregarLista();
    }

    //Carregar lista de alugueis de clientes.
    public void CarregarAlugueisClientes() throws Exception {
        AluguelDao carregar = new AluguelDao();
        this.ListarVeiculosClientes = carregar.ListarVeiculos();

    }

    //Deletar Aluguel
    public void DeletarAluguel() throws Exception {
        Aluguelbean addCliente = new Aluguelbean();
        AluguelDao deletar = new AluguelDao();
        addCliente.setClienteNome(ClienteSelecionado);
        boolean sucesso = deletar.DeletarAluguel(addCliente);
        if (sucesso) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Veiculo deletado com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falhou", "Não foi possivel deletar veiculo."));
        }
    }

    //Consultar aluguel de um cliente
    public void AlugueldoCliente(AjaxBehaviorEvent event) throws Exception {
        Aluguelbean addaluguel = new Aluguelbean();
        AluguelDao buscar = new AluguelDao();
        addaluguel.setClienteNome(ClienteSelecionado);
        System.out.println("Nome do cliente: " + ClienteSelecionado);
        String dados = buscar.BuscarCliente(addaluguel);
        String[] partes = dados.split("--");
        this.DataAtualSTR = partes[0];
        this.DataVencimentoSTR = partes[1];
        this.Entregue = partes[2].charAt(0);
        this.Observacao = partes[3];
        double Valor = Double.parseDouble(partes[4]);
        this.ValorPago = BigDecimal.valueOf(Valor);
        this.VeiculoModelo = partes[5];
        System.out.println("Veiculo Selecionado" + VeiculoModelo);

    }

    //Editar aluguel
    public void EditarAluguel() throws Exception {
        Aluguelbean addveiculo = new Aluguelbean();
        AluguelDao verificar = new AluguelDao();
        addveiculo.setVeiculoModelo(VeiculoModelo);
        addveiculo.setDataAluguel(Date.valueOf(DataAtualSTR));
        addveiculo.setDataEntrega(Date.valueOf(DataVencimentoSTR));
        addveiculo.setClienteNome(ClienteSelecionado);
        addveiculo.setEntregue(Entregue);
        addveiculo.setObservacao(Observacao);
        addveiculo.setValorPago(ValorPago);
        boolean sucesso = verificar.EditarAluguel(addveiculo);
        if (sucesso) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Veiculo atualizado com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falhou", "Falhou ao atualizar os dados."));
        }
    }

    //Verificar se o veiculo foi alugado
    public String VerificarAlugueis() throws Exception {
        Aluguelbean addveiculo = new Aluguelbean();
        AluguelDao verificar = new AluguelDao();
        addveiculo.setVeiculoModelo(VeiculoModelo);
        boolean status = verificar.VerificarAluguel(addveiculo);
        System.out.println("Status: " + status);
        if (status) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veiculo alugado.", "Esté veiculo já se encontra alugado."));

        } else if (!status) {
            Alugarveiculo();

        }
        return null;

    }

    //Efetuar aluguel para um cliente
    public void Alugarveiculo() throws Exception {
        Aluguelbean Addaluguel = new Aluguelbean();
        AluguelDao Alugar = new AluguelDao();

        Addaluguel.setClienteNome(ClienteSelecionado);
        Addaluguel.setVeiculoModelo(VeiculoModelo);
        Addaluguel.setDataAluguel(Date.valueOf(DataAtualSTR));
        Addaluguel.setDataEntrega(Date.valueOf(DataVencimentoSTR));
        Addaluguel.setEntregue(Entregue);
        Addaluguel.setObservacao(Observacao);
        Addaluguel.setValorPago(ValorPago);
        boolean sucesso = Alugar.AlugarVeiculo(Addaluguel);
        if (sucesso) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Alugado com sucesso."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falhou", "Falhou ao alugar um veiculo."));
        }

    }

    //Verificar os dados do cliente
    public void Dadosclientes(AjaxBehaviorEvent event) throws Exception {
        ClienteDao buscar = new ClienteDao();
        Clientebean add = new Clientebean();
        add.setNome(ClienteSelecionado);
        buscar.CarregarDados(add);

    }

    //Verificar a placa do veiculo
    public void onClienteChange(AjaxBehaviorEvent event) throws Exception {
        //Iniciar data atual e pegando o valor
        LocalDate dataatual = LocalDate.now();
        Date dataHoje = Date.valueOf(dataatual);
        // Iniciando data da entregar e passando os parametros
        LocalDate dataentregar = dataatual.plusMonths(1);
        Date dataEntrega = Date.valueOf(dataentregar);

        //Iniciar metado para buscar por placa
        VeiculoDao busca = new VeiculoDao();
        //Passando os parametros
        ConsultarVeiculos.setPlaca(VeiculoSelecionado);
        //Retornar o conjunto de resultado
        String dados = busca.BuscarPlaca(ConsultarVeiculos);
        //Efetuar as separação das informações util
        String[] partes = dados.split("--");
        this.VeiculoModelo = partes[3];
        this.ConsultarVeiculos.setModelo(partes[3]);
        this.DataAtualSTR = String.valueOf(dataHoje);
        this.DataVencimentoSTR = String.valueOf(dataEntrega);

    }

    public static void main(String[] args) throws Exception {
        Aluguelbean add = new Aluguelbean();
        add.getConsultarClientes().setNome("ola");
        System.out.println("Nome: " + add.getConsultarClientes().getNome());
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Veiculobean getVeiculo() {
        return Veiculo;
    }

    public void setVeiculo(Veiculobean Veiculo) {
        this.Veiculo = Veiculo;
    }

    public Date getDataAluguel() {
        return DataAluguel;
    }

    public void setDataAluguel(Date DataAluguel) {
        this.DataAluguel = DataAluguel;
    }

    public Date getDataEntrega() {
        return DataEntrega;
    }

    public void setDataEntrega(Date DataEntrega) {
        this.DataEntrega = DataEntrega;
    }

    public Clientebean getCliente() {
        return Cliente;
    }

    public void setCliente(Clientebean Cliente) {
        this.Cliente = Cliente;
    }

    public char getEntregue() {
        return Entregue;
    }

    public void setEntregue(char Entregue) {
        this.Entregue = Entregue;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    public BigDecimal getValorPago() {
        return ValorPago;
    }

    public void setValorPago(BigDecimal ValorPago) {
        this.ValorPago = ValorPago;
    }

    public List<String> getListarClientes() {
        return ListarClientes;
    }

    public void setListarClientes(List<String> ListarClientes) {
        this.ListarClientes = ListarClientes;
    }

    public List<String> getListarVeiculos() {
        return ListarVeiculos;
    }

    public void setListarVeiculos(List<String> ListarVeiculos) {
        this.ListarVeiculos = ListarVeiculos;
    }

    public String getClienteSelecionado() {
        return ClienteSelecionado;
    }

    public void setClienteSelecionado(String ClienteSelecionado) {
        this.ClienteSelecionado = ClienteSelecionado;
    }

    public String getVeiculoSelecionado() {
        return VeiculoSelecionado;
    }

    public void setVeiculoSelecionado(String VeiculoSelecionado) {
        this.VeiculoSelecionado = VeiculoSelecionado;
    }

    public Clientebean getConsultarClientes() {
        return ConsultarClientes;
    }

    public void setConsultarClientes(Clientebean ConsultarClientes) {
        this.ConsultarClientes = ConsultarClientes;
    }

    public Veiculobean getConsultarVeiculos() {
        return ConsultarVeiculos;
    }

    public void setConsultarVeiculos(Veiculobean ConsultarVeiculos) {
        this.ConsultarVeiculos = ConsultarVeiculos;
    }

    public String getDataAtualSTR() {
        return DataAtualSTR;
    }

    public void setDataAtualSTR(String DataAtualSTR) {
        this.DataAtualSTR = DataAtualSTR;
    }

    public String getDataVencimentoSTR() {
        return DataVencimentoSTR;
    }

    public void setDataVencimentoSTR(String DataVencimentoSTR) {
        this.DataVencimentoSTR = DataVencimentoSTR;
    }

    public String getVeiculoModelo() {
        return VeiculoModelo;
    }

    public void setVeiculoModelo(String VeiculoModelo) {
        this.VeiculoModelo = VeiculoModelo;
    }

    public String getClienteNome() {
        return ClienteNome;
    }

    public void setClienteNome(String ClienteNome) {
        this.ClienteNome = ClienteNome;
    }

    public List<String> getListarVeiculosClientes() {
        return ListarVeiculosClientes;
    }

    public void setListarVeiculosClientes(List<String> ListarVeiculosClientes) {
        this.ListarVeiculosClientes = ListarVeiculosClientes;
    }

}
