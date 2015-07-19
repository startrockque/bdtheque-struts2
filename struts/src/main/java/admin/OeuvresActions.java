package admin;

import com.opensymphony.xwork2.ActionSupport;
import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import oeuvres.*;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import rmi.InitRemoteService;
import rmi.RMIService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabien on 07/07/2015.
 */
public class OeuvresActions extends ActionSupport implements ApplicationAware, SessionAware {
    private RMIService rmiService;

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


    public String getAllOeuvres() throws RemoteException, NotFoundException {
        listeOeuvres = rmiService.getAllOeuvres();
        return SUCCESS;
    }

    public String toAddOeuvre() {
        loadTypes();
        return SUCCESS;
    }

    public String addOeuvre() throws RemoteException{
        loadTypes();
        Oeuvre oeuvre = OeuvreFactory.createOeuvre(type);
        oeuvre.setTitre(titre);
        oeuvre.setAuteur(auteur);
        oeuvre.setType(type);
        oeuvre.setQuantite(quantite);
        oeuvre.setEmpruntable(empruntable);
        try {
            rmiService.addOeuvre(oeuvre);
            messageOK = "Ajout de " + titre + " à la base de données."+ type;
        } catch (AlreadyExistsException e) {
            messageKO = "Cette oeuvre est déjà dans la base de données.";
        }
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

    public String modifierOeuvre() {
        return SUCCESS;
    }

    public String supprimerOeuvre() throws RemoteException, NotFoundException {
        rmiService.supprimerOeuvre(idOeuvre);
        getAllOeuvres();
        return SUCCESS;
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
