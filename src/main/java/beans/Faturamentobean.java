/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import Dao.FaturamentoDao;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eduar
 */
@ManagedBean(name = "Faturamentobean")
@ViewScoped
public class Faturamentobean {

    private BigDecimal Total;
    private List<BigDecimal> Valores;
    private String TotalSTR;

    public Faturamentobean() {
    }

    @PostConstruct
    public void init() {
        try {
            ConsultarFaturamento();
        } catch (Exception e) {
        }
    }

    public void ConsultarFaturamento() throws Exception {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        FaturamentoDao calcular = new FaturamentoDao();
        BigDecimal value = calcular.Calcular();
        String formattedValue = currencyFormat.format(value);
        System.out.println(formattedValue);
        this.TotalSTR = formattedValue;

    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    public List<BigDecimal> getValores() {
        return Valores;
    }

    public void setValores(List<BigDecimal> Valores) {
        this.Valores = Valores;
    }

    public String getTotalSTR() {
        return TotalSTR;
    }

    public void setTotalSTR(String TotalSTR) {
        this.TotalSTR = TotalSTR;
    }

}
