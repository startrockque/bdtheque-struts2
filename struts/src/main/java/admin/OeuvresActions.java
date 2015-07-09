package admin;

import com.opensymphony.xwork2.ActionSupport;
import oeuvres.BD;
import oeuvres.Livre;
import oeuvres.Manga;
import oeuvres.Oeuvre;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabien on 07/07/2015.
 */
public class OeuvresActions extends ActionSupport implements ApplicationAware, SessionAware {
    private Map<String, Object> variableSession;

    private List<Oeuvre> listeOeuvres;

    private int pageNumber;
    private int pageNumberM;
    private int pageNumberE;

    private int idOeuvre;


    public String getAllOeuvres() {
        listeOeuvres = new ArrayList<>();
        Oeuvre o = new Livre(1, "Empruntable", "Toto", 1, true);
        listeOeuvres.add(o);
        listeOeuvres.add(o);
        listeOeuvres.add(o);
        listeOeuvres.add(o);
        listeOeuvres.add(o);
        listeOeuvres.add(o);
        listeOeuvres.add(o);
        listeOeuvres.add(o);
        Oeuvre o2 = new BD(2, "Non empruntable", "Toto", 1, false);
        listeOeuvres.add(o2);
        listeOeuvres.add(o2);
        listeOeuvres.add(o2);
        listeOeuvres.add(o2);
        listeOeuvres.add(o2);
        listeOeuvres.add(o2);
        listeOeuvres.add(o2);
        Oeuvre o3 = new Manga(3, "Manga !!", "Toto Kuromi", 2, true);
        listeOeuvres.add(o3);
        listeOeuvres.add(o3);
        listeOeuvres.add(o3);
        listeOeuvres.add(o3);
        listeOeuvres.add(o3);
        // recupérer les oeuvres (voir pagination)
        return SUCCESS;
    }

    public String toAddOeuvre() {
        return SUCCESS;
    }

    public String toModifierOeuvre() {
        return SUCCESS;
    }

    public String supprimerOeuvre(){
        //Supprimer le livre dont l'id est en paramètre
        return getAllOeuvres();
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

    public List<Oeuvre> getListeOeuvres() {
        return listeOeuvres;
    }

    public void setListeOeuvres(List<Oeuvre> listeOeuvres) {
        this.listeOeuvres = listeOeuvres;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }
}
