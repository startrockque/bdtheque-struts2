package rmi;

import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import oeuvres.Oeuvre;
import users.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RMIService extends Remote{
    String SERVICE_NAME = "RMIService";

    List<Oeuvre> getAllOeuvres() throws RemoteException, NotFoundException;

    void addOeuvre(Oeuvre oeuvre) throws RemoteException, AlreadyExistsException;

    void modifierOeuvre(Oeuvre oeuvre) throws RemoteException;

    void supprimerOeuvre(int id) throws RemoteException;

    Oeuvre getOeuvre(int idOeuvre) throws RemoteException, NotFoundException;


    void addUser(User user) throws RemoteException, AlreadyExistsException;

    List<User> getAllUsers() throws RemoteException, NotFoundException;

    User getUser(int idUser) throws RemoteException, NotFoundException;

    void modifierUser(User user) throws RemoteException;

    void supprimerUser(int idUser) throws RemoteException;
}