package dao;

import dao.configuration.NotFoundException;
import dao.interfaces.DAO;
import dao.interfaces.IUserDAO;
import users.User;

import java.util.List;

/**
 * Created by Fabien on 14/07/2015.
 */
public class UserDao extends DAO<User> implements IUserDAO {
    /**
     * Constructeur
     *
     * @param daoFactory daoFactory
     */
    public UserDao(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(User obj) {

    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public User find(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<User> findAll() throws NotFoundException {
        return null;
    }
}
