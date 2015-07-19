package rmi;

import dao.DAOFactory;
import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import oeuvres.Oeuvre;

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
    public void addOeuvre(Oeuvre oeuvre) throws RemoteException, AlreadyExistsException {
        daoFactory.getOeuvreDAO().create(oeuvre);
    }

    @Override
    public void modifierOeuvre(int id, Oeuvre oeuvre) throws RemoteException {

    }

    @Override
    public void supprimerOeuvre(int id) throws RemoteException {

    }
}
