package admin;

import com.opensymphony.xwork2.ActionSupport;
import emprunts.Emprunt;
import oeuvres.Livre;
import oeuvres.Oeuvre;
import users.Membre;
import users.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabien on 09/07/2015.
 */
public class AccueilAdmin extends ActionSupport {
    private List<Emprunt> empruntsEnRetard;

    private String titre;
    private String nom;

    @Override
    public String execute() throws Exception {
        //recup les emprunts en retard dans la bdd
        empruntsEnRetard = new ArrayList<>();
        Oeuvre oeuvre = new Livre();
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
}
