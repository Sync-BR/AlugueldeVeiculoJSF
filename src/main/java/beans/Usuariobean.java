package beans;

import Dao.UserDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SYNC
 * @Usuariobean Responsavel pela setagem e retornor de informações JSF.
 * @ManagedBean Definir que a classe é um beans gerenciado.
 * @ViewScoped Definir o tempo de vida com interação do usuario.
 */
@ManagedBean(name = "Usuariobean")
@ViewScoped

public class Usuariobean implements Serializable {

    private List<String> Status = new ArrayList<>();
    private String user;
    private String password;

    public Usuariobean() {
        Status.add("Usuario não enco");
        Status.add("Senha incorreta");
        Status.add("Falhou");

    }

    /**
     * Realiza a autenticação de um usuário com base no nome e senha fornecidos.
     * Redireciona para a página principal se autenticado com sucesso, caso
     * contrário, exibe uma mensagem de erro.
     *
     * @return URL de redirecionamento ou null se a autenticação falhar.
     * @throws Exception Se ocorrer um erro durante a autenticação.
     */
    public String Autenticar() throws Exception {
        UserDao p1 = new UserDao();
        Usuariobean adduser = new Usuariobean();
        adduser.setUser(user); 
        adduser.setPassword(password);
        boolean AutenticarSucesso = p1.Autenticar(adduser);

        if (AutenticarSucesso) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("principal.html");
            return null;
        } else {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ou senha incorretos", null);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return null;  // Retorna null para permanecer na mesma página e mostrar mensagem de erro
        }
    }

    public List<String> getStatus() {
        return Status;
    }

    public void setStatus(List<String> Status) {
        this.Status = Status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
