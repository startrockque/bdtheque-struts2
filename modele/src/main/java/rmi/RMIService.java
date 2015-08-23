package rmi;

import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import emprunts.Emprunt;
import oeuvres.Oeuvre;
import users.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RMIService extends Remote{
    String SERVICE_NAME = "RMIService";

    List<Oeuvre> getAllOeuvres() throws RemoteException, NotFoundException;

    List<Oeuvre> getAllOeuvresEmpruntables() throws RemoteException;

    void addOeuvre(Oeuvre oeuvre) throws RemoteException, AlreadyExistsException;

    void modifierOeuvre(Oeuvre oeuvre) throws RemoteException, AlreadyExistsException;

    void supprimerOeuvre(int id) throws RemoteException;

    Oeuvre getOeuvre(int idOeuvre) throws RemoteException, NotFoundException;

    Oeuvre getOeuvre(String titre, String type) throws RemoteException, NotFoundException;


    void addUser(User user) throws RemoteException, AlreadyExistsException;

    List<User> getAllUsers() throws RemoteException, NotFoundException;

    User getUser(int idUser) throws RemoteException, NotFoundException;

    User getUser(String nom, String prenom) throws RemoteException, NotFoundException;

    void modifierUser(User user) throws RemoteException;

    void supprimerUser(int idUser) throws RemoteException;


    void addEmprunt(Emprunt emprunt) throws RemoteException, AlreadyExistsException;

    List<Emprunt> getAllEmprunts() throws RemoteException, NotFoundException;

    List<Emprunt> getAllRetards() throws RemoteException;

    Emprunt getEmprunt(int idOeuvre, int idUser) throws RemoteException, NotFoundException;

    void supprimerEmprunt(int idOeuvre, int idUser) throws RemoteException;
}