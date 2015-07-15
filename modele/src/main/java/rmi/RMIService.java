package rmi;

import dao.configuration.NotFoundException;
import oeuvres.Oeuvre;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RMIService extends Remote{
    String SERVICE_NAME = "RMIService";

    List<Oeuvre> getAllOeuvres() throws RemoteException, NotFoundException;

    void addOeuvre(Oeuvre oeuvre) throws RemoteException;

    void modifierOeuvre(int id, Oeuvre oeuvre) throws RemoteException;

    void supprimerOeuvre(int id) throws RemoteException;
}