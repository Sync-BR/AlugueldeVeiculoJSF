package beans;

import Dao.ClienteDao;
import Dao.VeiculoDao;
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
@ManagedBean(name = "Veiculobean")
@ViewScoped
public class Veiculobean {

    public Veiculobean() {
    }

    private int Id;
    private String Numero;
    private String Placa;
    private String Fabricante;
    private String Modelo;
    private int AnoModelo;
    private int QtdPortas;
    private String Acessorio;
    private String VeiculoSelecionado;
    private List<String> ListarDeVeiculos;
Config config = new Config();
    
    @PostConstruct
    public void init(){
        try{
            CarregarLista();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void CarregarLista() throws Exception {
        System.out.println("Etapa 2");
        VeiculoDao carregar = new VeiculoDao();
        this.ListarDeVeiculos = carregar.CarregarLista();
        for(int index = 0; index < ListarDeVeiculos.size(); index++){
            System.out.println("Placa" +ListarDeVeiculos.get(index));
        }

    }

    public static void main(String[] args) throws Exception {
        Veiculobean add = new Veiculobean();
        add.CarregarLista();
    }

    /**
     * @throws java.lang.Exception
     * @Atualizar atualizar um veiculo.
     *
     */
    public void Atualizar() throws Exception {
        VeiculoDao atualiza = new VeiculoDao();
        Veiculobean addveiculo = new Veiculobean();
        addveiculo.setNumero(Numero);
        addveiculo.setPlaca(Placa);
        addveiculo.setFabricante(Fabricante);
        addveiculo.setModelo(Modelo);
        addveiculo.setAnoModelo(AnoModelo);
        addveiculo.setQtdPortas(QtdPortas);
        addveiculo.setAcessorio(Acessorio);
        boolean sucesso = atualiza.Editar(addveiculo);
        if (sucesso) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Atualizado com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falhou", "Falhou ao atualizar dados"));
        }
    }

    /**
     * @Registra Registra um veiculo.
     * @Exception Tratar os possiveis erros
     *
     */
    public void Registra() throws Exception {
        Veiculobean AddVeiculos = new Veiculobean();
        VeiculoDao register = new VeiculoDao();
        AddVeiculos.setNumero(Numero);
        AddVeiculos.setPlaca(Placa);
        AddVeiculos.setFabricante(Fabricante);
        AddVeiculos.setModelo(Modelo);
        AddVeiculos.setAnoModelo(AnoModelo);
        AddVeiculos.setQtdPortas(QtdPortas);
        AddVeiculos.setAcessorio(Acessorio);
        boolean sucesso = register.Registrar(AddVeiculos);
        if (sucesso) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Veículo registrado com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falhou", "Erro ao registrar veiculo"));
        }
    }

    public void Deletar() throws Exception{
        Veiculobean addveiculo = new Veiculobean();
        VeiculoDao delete = new VeiculoDao();
        addveiculo.setPlaca(VeiculoSelecionado);
        boolean sucesso = delete.Deletar(addveiculo);
        if(sucesso){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Veiculo Deletado com sucesso."));
        } else {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falhou", "Falhou ao deletar veiculo"));
        }
       
    }
    
    /**
     * @param event Efetuar interação para retornar o nome selecionado
     * @onClienteChange Metado para retornar o cliente selecionado
     *
     */
    public void onClienteChange(AjaxBehaviorEvent event) throws Exception {
        System.out.println("Veiculo selecionado: " + VeiculoSelecionado);
        Veiculobean addveiculo = new Veiculobean();
        VeiculoDao buscar = new VeiculoDao();
        addveiculo.setPlaca(VeiculoSelecionado);
        String dados = buscar.BuscarPlaca(addveiculo);
        String[] partes = dados.split("--");
        this.Numero = partes[1];
        this.Fabricante = partes[2];
        this.Modelo = partes[3];
        this.Acessorio = partes[4];
       
        this.Placa = partes[5];
        this.AnoModelo = Integer.parseInt(partes[6]);
        this.QtdPortas = Integer.parseInt(partes[7]);
      
       // System.out.println("Numero: " +Numero);
      //  System.out.println("fabricante: " +Fabricante);
      //  System.out.println("modelo: " +Modelo);
      //  System.out.println("acessorios: " +Acessorio);
      //  System.out.println("placa: " +Placa);
       // System.out.println("Ano do modelo: " +AnoModelo);
       // System.out.println("Portas: " +QtdPortas);
        
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getFabricante() {
        return Fabricante;
    }

    public void setFabricante(String Fabricante) {
        this.Fabricante = Fabricante;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getAnoModelo() {
        return AnoModelo;
    }

    public void setAnoModelo(int AnoModelo) {
        this.AnoModelo = AnoModelo;
    }

    public int getQtdPortas() {
        return QtdPortas;
    }

    public void setQtdPortas(int QtdPortas) {
        this.QtdPortas = QtdPortas;
    }

    public String getAcessorio() {
        return Acessorio;
    }

    public void setAcessorio(String Acessorio) {
        this.Acessorio = Acessorio;

    }

    public String getVeiculoSelecionado() {
        return VeiculoSelecionado;
    }

    public void setVeiculoSelecionado(String VeiculoSelecionado) {
        this.VeiculoSelecionado = VeiculoSelecionado;
    }

    public List<String> getListarDeVeiculos() {
        return ListarDeVeiculos;
    }

    public void setListarDeVeiculos(List<String> ListarDeVeiculos) {
        this.ListarDeVeiculos = ListarDeVeiculos;
    }
}
