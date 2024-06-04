/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import Dao.VeiculosAlugadosDao;
import Dao.VeiculosAtrasadosDao;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eduar
 */
@ManagedBean(name = "VeiculosAlugados")
@ViewScoped
public class VeiculosAlugadosbean {
      private List<String> Nome = null;
    private List<String> Veiculo;
    private List<String> Veiculosatrasados = null;
    private List<String> ValorPago;

    @PostConstruct
    public void init() {
        try {
            CarregarTAbelaNomes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CarregarTAbelaNomes() throws Exception {
        VeiculosAlugadosDao start = new VeiculosAlugadosDao();
        this.Nome = start.VeiculosAlugados();
        this.Veiculo = start.VeiculosAlugadosCarros();
        this.ValorPago = start.VeiculosAlugadosValor();
        
        for(int i = 0; i < ValorPago.size(); i++){
            System.out.println("Valor: " +ValorPago);
        }
       
    }
    public static void main(String[] args) {
        
    }
  

    public List<String> getNome() {
        return Nome;
    }

    public void setNome(List<String> Nome) {
        this.Nome = Nome;
    }

    public List<String> getVeiculo() {
        return Veiculo;
    }

    public void setVeiculo(List<String> Veiculo) {
        this.Veiculo = Veiculo;
    }

    public List<String> getVeiculosatrasados() {
        return Veiculosatrasados;
    }

    public void setVeiculosatrasados(List<String> Veiculosatrasados) {
        this.Veiculosatrasados = Veiculosatrasados;
    }

    public List<String> getValorPago() {
        return ValorPago;
    }

    public void setValorPago(List<String> ValorPago) {
        this.ValorPago = ValorPago;
    }
}
