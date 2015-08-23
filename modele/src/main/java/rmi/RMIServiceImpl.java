package rmi;

import dao.DAOFactory;
import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import emprunts.Emprunt;
import oeuvres.Oeuvre;
import users.User;

import java.rmi.RemoteException;
import java.util.List;


public class RMIServiceImpl implements RMIService {
    private DAOFactory daoFactory;

    public RMIServiceImpl(String path){
        if (daoFactory == null){
            daoFactory = DAOFactory.getInstance(path);
        }
    }

    @Override
    public List<Oeuvre> getAllOeuvres() throws RemoteException, NotFoundException {
        return daoFactory.getOeuvreDAO().findAll();
    }

    @Override
    public List<Oeuvre> getAllOeuvresEmpruntables() throws RemoteException {
        return daoFactory.getOeuvreDAO().findAllEmpruntables();
    }

    @Override
    public Oeuvre getOeuvre(int idOeuvre) throws NotFoundException {
        return daoFactory.getOeuvreDAO().find(idOeuvre);
    }

    @Override
    public Oeuvre getOeuvre(String titre, String type) throws RemoteException, NotFoundException {
        return daoFactory.getOeuvreDAO().find(titre, type);
    }

    @Override
    public void addOeuvre(Oeuvre oeuvre) throws RemoteException, AlreadyExistsException {
        daoFactory.getOeuvreDAO().create(oeuvre);
    }

    @Override
    public void modifierOeuvre(Oeuvre oeuvre) throws RemoteException, AlreadyExistsException {
        daoFactory.getOeuvreDAO().update(oeuvre);
    }

    @Override
    public void supprimerOeuvre(int id) throws RemoteException {
        daoFactory.getOeuvreDAO().remove(id);
    }




    @Override
    public void addUser(User user) throws RemoteException, AlreadyExistsException {
        daoFactory.getUserDao().create(user);
    }

    @Override
    public User getUser(int idUser) throws RemoteException, NotFoundException {
        return daoFactory.getUserDao().find(idUser);
    }

    @Override
    public User getUser(String nom, String prenom) throws RemoteException, NotFoundException {
        return daoFactory.getUserDao().find(nom, prenom);
    }

    @Override
    public List<User> getAllUsers() throws RemoteException, NotFoundException {
        return daoFactory.getUserDao().findAll();
    }

    @Override
    public void modifierUser(User user) throws RemoteException{
        daoFactory.getUserDao().update(user);
    }

    @Override
    public void supprimerUser(int idUser) throws RemoteException {
        daoFactory.getUserDao().remove(idUser);
    }



    @Override
    public void addEmprunt(Emprunt emprunt) throws RemoteException, AlreadyExistsException {
        daoFactory.getEmpruntDao().create(emprunt);
    }

    @Override
    public List<Emprunt> getAllEmprunts() throws RemoteException, NotFoundException {
        return daoFactory.getEmpruntDao().findAll();
    }

    @Override
    public  List<Emprunt> getAllRetards() throws RemoteException {
        return daoFactory.getEmpruntDao().findRetard();
    }

    @Override
    public Emprunt getEmprunt(int idOeuvre, int idUser) throws RemoteException, NotFoundException {
        return daoFactory.getEmpruntDao().find(idOeuvre, idUser);
    }

    @Override
    public void supprimerEmprunt(int idOeuvre, int idUser) throws RemoteException {
        daoFactory.getEmpruntDao().remove(idOeuvre, idUser);
    }
}
