package admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by Fabien on 07/07/2015.
 */
public class OeuvresActions extends ActionSupport implements ApplicationAware, SessionAware {
    private Map<String, Object> variableSession;

    private int pageNumber;
    private int pageNumberM;
    private int pageNumberE;


    public String getAllOeuvres() throws Exception {
        // recupérer les oeuvres (voir pagination)
        return "success";
    }

    public String toAddOeuvre() throws Exception {
        return "success";
    }




    @Override
    public void setApplication(Map<String, Object> map) {
        // Init le rmi/DAO
    }

    @Override
    public void setSession(Map<String, Object> map) {
        variableSession = map;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumberM() {
        return pageNumberM;
    }

    public void setPageNumberM(int pageNumberM) {
        this.pageNumberM = pageNumberM;
    }

    public int getPageNumberE() {
        return pageNumberE;
    }

    public void setPageNumberE(int pageNumberE) {
        this.pageNumberE = pageNumberE;
    }
}
