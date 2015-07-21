package rmi;

import dao.DAOFactory;
import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
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
    public Oeuvre getOeuvre(int idOeuvre) throws NotFoundException {
        return daoFactory.getOeuvreDAO().find(idOeuvre);
    }

    @Override
    public void addOeuvre(Oeuvre oeuvre) throws RemoteException, AlreadyExistsException {
        daoFactory.getOeuvreDAO().create(oeuvre);
    }

    @Override
    public void modifierOeuvre(Oeuvre oeuvre) throws RemoteException {
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
}
