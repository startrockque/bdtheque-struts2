package accueil;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by Fabien on 07/07/2015.
 */
public class Deconnection extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    /**
     * Vide la session, ainsi il n'y a plus d'utilisateur courant et il est impossible d'effectuer une action avan de se reconnecter
     * @return SUCCESS
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        session.clear();
        return SUCCESS;
    }
}
