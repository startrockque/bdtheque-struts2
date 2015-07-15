package admin;

import com.opensymphony.xwork2.ActionSupport;
import emprunts.Emprunt;
import oeuvres.Livre;
import oeuvres.Oeuvre;
import oeuvres.OeuvreFactory;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import rmi.InitRemoteService;
import rmi.RMIService;
import users.Membre;
import users.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabien on 09/07/2015.
 */
public class AccueilAdmin extends ActionSupport implements ApplicationAware, SessionAware {
    private RMIService rmiService;
    private Map<String, Object> variableSession;


    private List<Emprunt> empruntsEnRetard;

    private String titre;
    private String nom;

    @Override
    public String execute() throws Exception {
        //recup les emprunts en retard dans la bdd
        empruntsEnRetard = new ArrayList<>();
        Oeuvre oeuvre = OeuvreFactory.createOeuvre("Livre");
        oeuvre.setTitre("Toto chez les fermiers");
        User user = new Membre();
        Emprunt e = new Emprunt();
        e.setOeuvre(oeuvre);
        e.setUser(user);
        e.setRetour(Date.valueOf("2015-07-08"));
        empruntsEnRetard.add(e);
        empruntsEnRetard.add(e);
        return SUCCESS;
    }

    public List<Emprunt> getEmpruntsEnRetard() {
        return empruntsEnRetard;
    }

    public void setEmpruntsEnRetard(List<Emprunt> empruntsEnRetard) {
        this.empruntsEnRetard = empruntsEnRetard;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        rmiService = (RMIService) map.get(RMIService.SERVICE_NAME);
        if (rmiService == null){
            rmiService = InitRemoteService.getService();
            map.put(RMIService.SERVICE_NAME, rmiService);
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        variableSession = map;
    }
}
