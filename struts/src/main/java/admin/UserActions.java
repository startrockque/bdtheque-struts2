package admin;

import com.opensymphony.xwork2.ActionSupport;
import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import rmi.InitRemoteService;
import rmi.RMIService;
import users.User;
import users.UserFactory;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserActions extends ActionSupport implements SessionAware, ApplicationAware{
    private RMIService rmiService;

    private Map<String, Object> variableSession;

    private List<User> listUsers;
    private List<String> listeResidences;

    private String messageOK;
    private String messageKO;

    private int pageNumber;

    private int idUser;
    private String nom;
    private String prenom;
    private String mail;
    private String tel;
    private String residence;
    private int chambre;


    public String getAllUsers() throws RemoteException, NotFoundException {
        listUsers = rmiService.getAllUsers();
        return SUCCESS;
    }

    public String toAddUser() {
        loadResidences();
        return SUCCESS;
    }

    public String addUser() throws RemoteException {
        loadResidences();
        User user = UserFactory.createUser();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setMail(mail);
        user.setTel(tel);
        user.setResidence(residence);
        user.setChambre(chambre);
        try {
            rmiService.addUser(user);
            messageOK = "Ajout de " + nom + " "+ prenom +" à la base de données.";
        } catch (AlreadyExistsException e) {
            messageKO = "Cet utilisateur est déjà dans la base de données.";
            return ERROR;
        }
        return SUCCESS;
    }

    private void loadResidences(){
        listeResidences = new ArrayList<String>();
        listeResidences.add("Aristote 1");
        listeResidences.add("Aristote 2");
        listeResidences.add("Aristote 3");
        listeResidences.add("Campo Santo");
        listeResidences.add("Dessaux");
        listeResidences.add("Jacquard");
        listeResidences.add("Les Charmes");
        listeResidences.add("Les Châtaigniers");
        listeResidences.add("Les Dahlias");
        listeResidences.add("Les Hêtres");
        listeResidences.add("Les Magnolias");
        listeResidences.add("Les Ormes");
        listeResidences.add("Les Roses");
        listeResidences.add("Autre");
    }

    public String toModifierUser() throws RemoteException {
        try {
            User user = rmiService.getUser(idUser);
            nom = user.getNom();
            prenom = user.getPrenom();
            mail = user.getMail();
            tel = user.getTel();
            residence = user.getResidence();
            chambre = user.getChambre();
        } catch (NotFoundException e) {
            messageKO = "Utilisateur non trouvé dans la base de données.";
            return ERROR;
        }
        loadResidences();
        return SUCCESS;
    }

    public String modifierUser() throws NotFoundException, RemoteException {
        User user = UserFactory.createUser();
        user.setId(idUser);
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setMail(mail);
        user.setTel(tel);
        user.setResidence(residence);
        user.setChambre(chambre);
        try {
            rmiService.modifierUser(user);
            messageOK = "Modification effectuée.";
        } catch (RemoteException e) {
            messageKO = "Problème de connexion à la base de données.";
        }
        getAllUsers();
        return SUCCESS;
    }

    public String supprimerUser() throws RemoteException, NotFoundException {
        rmiService.supprimerUser(idUser);
        getAllUsers();
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

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<String> getListeResidences() {
        return listeResidences;
    }

    public void setListeResidences(List<String> listeResidences) {
        this.listeResidences = listeResidences;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public int getChambre() {
        return chambre;
    }

    public void setChambre(int chambre) {
        this.chambre = chambre;
    }
}
