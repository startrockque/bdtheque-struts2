package dao.interfaces;


import dao.DAOFactory;
import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;

import java.util.List;

/**
 * classe abstrait de Dao.
 */
public abstract class DAO<T> {
    /**
     * le daoFactory
     */
    protected DAOFactory daoFactory;

    /**
     * Constructeur
     *
     * @param daoFactory
     *          daoFactory
     */
    public DAO(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    /**
     * Méthode de création
     * @param obj
     *          Objet
     */
    public abstract void create(T obj) throws AlreadyExistsException;

    /**
     * Méthode pour effacer
     * @param obj
     *          Objet
     */
    public abstract void delete(T obj);

    /**
     * Méthode de mise à jour
     * @param obj
     *          Objet
     */
    public abstract void update(T obj) throws AlreadyExistsException;

    /**
     * Méthode de recherche des informations
     * @param id
     *          Identifiant
     * @return T
     */
    public abstract T find(int id) throws NotFoundException;

    /**
     * Méthode de recherche des informations
     * @return List<T>
     */
    public abstract List<T> findAll() throws NotFoundException;
}