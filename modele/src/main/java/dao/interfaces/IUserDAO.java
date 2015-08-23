package dao.interfaces;

import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import users.User;

import java.util.List;

/**
 * Created by Fabien on 14/07/2015.
 */
public interface IUserDAO {

    public void create(User obj) throws AlreadyExistsException;

    public void delete(User obj);

    public void update(User obj);

    public User find(int id) throws NotFoundException;

    User find(String nom, String prenom) throws NotFoundException;

    public List<User> findAll() throws NotFoundException;

    void remove (int id);
}
