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

public class OeuvresActions extends ActionSupport implements ApplicationAware, SessionAware {
    private RMIService rmiService;

    private Map<String, Object> variableSession;

    private List<Oeuvre> listeOeuvres;
    private List<Oeuvre> listeOeuvresPagine;
    private List<String> listeTypes;

    private String messageOK;
    private String messageKO;

    private int pageNumber;

    private int idOeuvre;
    private String titre;
    private String auteur;
    private String type;
    private int quantite;
    private boolean empruntable;


    public String getAllOeuvresPagine() throws RemoteException, NotFoundException {
        listeOeuvresPagine = paginer(listeOeuvres, pageNumber);
        return SUCCESS;
    }

    private List<Oeuvre> paginer(List<Oeuvre> list, int pageNumber) {
        List<Oeuvre> res = new ArrayList<>();
        for (int i=0; i<20; i++){
            Oeuvre o;
            try {
                o = list.get(pageNumber*20 + i);
                res.add(o);
            } catch (IndexOutOfBoundsException e){
                return res;
            }
        }
        return res;
    }

    public String toAddOeuvre() {
        loadTypes();
        return SUCCESS;
    }

    public String addOeuvre() throws RemoteException {
        loadTypes();
        Oeuvre oeuvre = OeuvreFactory.createOeuvre(type);
        oeuvre.setTitre(titre);
        oeuvre.setAuteur(auteur);
        oeuvre.setType(type);
        oeuvre.setQuantite(quantite);
        oeuvre.setEmpruntable(empruntable);
        try {
            rmiService.addOeuvre(oeuvre);
            messageOK = "Ajout de " + titre + " à la base de données.";
        } catch (AlreadyExistsException e) {
            messageKO = "Cette oeuvre est déjà dans la base de données.";
            return ERROR;
        }
        listeOeuvres.add(oeuvre);
        return SUCCESS;
    }

    private void loadTypes(){
        listeTypes = new ArrayList<String>();
        listeTypes.add("Livre");
        listeTypes.add("BD");
        listeTypes.add("Manga");
    }

    public String toModifierOeuvre() throws RemoteException {
        try {
            Oeuvre oeuvre = rmiService.getOeuvre(idOeuvre);
            titre = oeuvre.getTitre();
            auteur = oeuvre.getAuteur();
            type = oeuvre.getType();
            quantite = oeuvre.getQuantite();
            empruntable = oeuvre.isEmpruntable();
        } catch (NotFoundException e) {
            messageKO = "Oeuvre non trouvée dans la base de données.";
        }
        loadTypes();
        return SUCCESS;
    }

    public String modifierOeuvre() throws NotFoundException, RemoteException {
        Oeuvre oeuvre = OeuvreFactory.createOeuvre(type);
        oeuvre.setId(idOeuvre);
        oeuvre.setTitre(titre);
        oeuvre.setAuteur(auteur);
        oeuvre.setQuantite(quantite);
        oeuvre.setEmpruntable(empruntable);
        try {
            rmiService.modifierOeuvre(oeuvre);
            messageOK = "Modification effectuée.";
        } catch (RemoteException e) {
            messageKO = "Problème de connexion à la base de données.";
        } catch (AlreadyExistsException e) {
            messageKO = "Une oeuvre identique existe déjà.";
            loadTypes();
            return ERROR;
        }
        getAllOeuvresPagine();
        int i = 0;
        for (Oeuvre o : listeOeuvresPagine){
            if (o.getId() == oeuvre.getId()) {
                listeOeuvresPagine.set(i, oeuvre);
                listeOeuvres.set(pageNumber*20+i, oeuvre);
                return SUCCESS;
            }
            i ++;
        }
        return SUCCESS;
    }

    public String supprimerOeuvre() throws RemoteException, NotFoundException {
        rmiService.supprimerOeuvre(idOeuvre);
        getAllOeuvresPagine();
        int i = 0;
        for (Oeuvre o : listeOeuvresPagine){
            if (o.getId() == idOeuvre) {
                listeOeuvresPagine.remove(i);
                listeOeuvres.remove(pageNumber*20+i);
                return SUCCESS;
            }
            i ++;
        }
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
        if (variableSession.get("oeuvres") == null) {
            try {
                listeOeuvres = rmiService.getAllOeuvres();
            } catch (Exception e) {
                e.printStackTrace();
            }
            variableSession.put("oeuvres", listeOeuvres);
        } else {
            listeOeuvres = (ArrayList<Oeuvre>) variableSession.get("oeuvres");
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
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

    public List<Oeuvre> getListeOeuvresPagine() {
        return listeOeuvresPagine;
    }

    public void setListeOeuvresPagine(List<Oeuvre> listeOeuvresPagine) {
        this.listeOeuvresPagine = listeOeuvresPagine;
    }
}
