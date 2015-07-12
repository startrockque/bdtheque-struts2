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
    private List<String> listeTypes;

    private String messageOK;
    private String messageKO;

    private int pageNumber;
    private int pageNumberM;
    private int pageNumberE;

    private int idOeuvre;
    private String titre;
    private String auteur;
    private String type;
    private int quantite;
    private boolean empruntable;


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
        loadTypes();
        return SUCCESS;
    }

    public String addOeuvre() {

        loadTypes();
        messageOK = "Ajout de "+ titre +" à la base de données.";
        return SUCCESS;
    }

    private void loadTypes(){
        listeTypes = new ArrayList<>();
        listeTypes.add("Livre");
        listeTypes.add("BD");
        listeTypes.add("Manga");
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

    public String getMessageOK() {
        return messageOK;
    }

    public void setMessageOK(String messageOK) {
        this.messageOK = messageOK;
    }

    public String getMessageKO() {
        return messageKO;
    }

    public void setMessageKO(String messageKO) {
        this.messageKO = messageKO;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public boolean isEmpruntable() {
        return empruntable;
    }

    public void setEmpruntable(boolean empruntable) {
        this.empruntable = empruntable;
    }

    public List<String> getListeTypes() {
        return listeTypes;
    }

    public void setListeTypes(List<String> listeTypes) {
        this.listeTypes = listeTypes;
    }
}
