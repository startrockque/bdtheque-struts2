package admin;

import com.opensymphony.xwork2.ActionSupport;
import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import emprunts.Emprunt;
import emprunts.EmpruntFactory;
import oeuvres.Oeuvre;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import rmi.InitRemoteService;
import rmi.RMIService;
import users.User;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.*;

public class EmpruntsActions extends ActionSupport implements SessionAware, ApplicationAware {
    private RMIService rmiService;
    private Map<String, Object> variableSession;

    private List<Emprunt> listeEmprunts;
    private List<Oeuvre> listeOeuvres;
    private List<User> listeUsers;
    private List<String> listTitres;
    private List<String> listNoms;

    private String messageOK;
    private String messageKO;

    private int pageNumber;

    private String titre;
    private String nom;
    private String[] infosOeuvre;
    private String[] infosUser;
    private int idOeuvre;
    private int idUser;


    public String getAllEmprunts() throws RemoteException, NotFoundException {
        listeEmprunts = rmiService.getAllEmprunts();
        return SUCCESS;
    }

    public String toAddEmprunt() throws NotFoundException, RemoteException {
        listeOeuvres = rmiService.getAllOeuvresEmpruntables();
        listeUsers = rmiService.getAllUsers();
        recupTitre();
        recupNoms();
        return SUCCESS;
    }

    private void recupNoms() {
        listNoms = new ArrayList<String>();
        for (User u : listeUsers){
            listNoms.add(u.toString());
        }
    }

    private void recupTitre() {
        listTitres = new ArrayList<String>();
        for (Oeuvre o : listeOeuvres){
            listTitres.add(o.toString());
        }
    }

    public String addEmprunt() throws RemoteException, NotFoundException {
        infosOeuvre = titre.split("\\|\\|");
        infosUser = nom.split("\\|\\|");
        try {
            Oeuvre oeuvre= rmiService.getOeuvre(infosOeuvre[0].trim(), infosOeuvre[2].trim());
            User user = rmiService.getUser(infosUser[1].trim(), infosUser[0].trim());
            Emprunt emprunt = EmpruntFactory.createEmprunt();
            emprunt.setOeuvre(oeuvre);
            emprunt.setUser(user);

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(Calendar.WEEK_OF_YEAR, 2);
            Date retour = calendar.getTime();
            emprunt.setRetour(retour);
            rmiService.addEmprunt(emprunt);

            oeuvre.setQuantite(oeuvre.getQuantite()-1);
            if (oeuvre.getQuantite() == 0)
                oeuvre.setEmpruntable(false);
            rmiService.modifierOeuvre(oeuvre);
            messageOK = "L'emprunt a bien été pris en compte";
            toAddEmprunt();
        } catch (AlreadyExistsException e) {
            messageKO = "Cette utilisateur a déjà emprunté cette oeuvre.";
            toAddEmprunt();
            return ERROR;
        } catch (NotFoundException e) {
            messageKO = "Impossible de trouver cet utilisateur ou cette oeuvre en BDD" + e.getMessage();
        }
        return SUCCESS;
    }

    public String supprimerEmprunt() throws RemoteException, NotFoundException, AlreadyExistsException {
        rmiService.supprimerEmprunt(idOeuvre, idUser);
        Oeuvre oeuvre = rmiService.getOeuvre(idOeuvre);
        oeuvre.setQuantite(oeuvre.getQuantite()+1);
        oeuvre.setEmpruntable(true);
        rmiService.modifierOeuvre(oeuvre);
        getAllEmprunts();
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

    public List<Emprunt> getListeEmprunts() {
        return listeEmprunts;
    }

    public void setListeEmprunts(List<Emprunt> listeEmprunts) {
        this.listeEmprunts = listeEmprunts;
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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<User> getListeUsers() {
        return listeUsers;
    }

    public void setListeUsers(List<User> listeUsers) {
        this.listeUsers = listeUsers;
    }

    public List<Oeuvre> getListeOeuvres() {
        return listeOeuvres;
    }

    public void setListeOeuvres(List<Oeuvre> listeOeuvres) {
        this.listeOeuvres = listeOeuvres;
    }

    public List<String> getListTitres() {
        return listTitres;
    }

    public void setListTitres(List<String> listTitres) {
        this.listTitres = listTitres;
    }

    public List<String> getListNoms() {
        return listNoms;
    }

    public void setListNoms(List<String> listNoms) {
        this.listNoms = listNoms;
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

    public String[] getInfosOeuvre() {
        return infosOeuvre;
    }

    public void setInfosOeuvre(String[] infosOeuvre) {
        this.infosOeuvre = infosOeuvre;
    }

    public String[] getInfosUser() {
        return infosUser;
    }

    public void setInfosUser(String[] infosUser) {
        this.infosUser = infosUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }
}
