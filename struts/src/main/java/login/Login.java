package login;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class Login extends ActionSupport implements SessionAware {

    private String login;
    private String mdp;

    private Map<String, Object> variableSession;

    @Override
    public String execute() throws Exception {
        if (login.equals("a") & mdp.equals("a")) {
            variableSession.put("login", login);
            return SUCCESS;
        }
        return INPUT;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.variableSession = map;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}