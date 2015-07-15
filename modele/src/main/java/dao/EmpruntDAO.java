package dao;

import dao.configuration.NotFoundException;
import dao.interfaces.DAO;
import dao.interfaces.IEmpruntDAO;
import emprunts.Emprunt;

import java.util.List;

/**
 * Created by Fabien on 14/07/2015.
 */
public class EmpruntDAO extends DAO<Emprunt> implements IEmpruntDAO {
    /**
     * Constructeur
     *
     * @param daoFactory daoFactory
     */
    public EmpruntDAO(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(Emprunt obj) {

    }

    @Override
    public void delete(Emprunt obj) {

    }

    @Override
    public void update(Emprunt obj) {

    }

    @Override
    public Emprunt find(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Emprunt> findAll() throws NotFoundException {
        return null;
    }
}
