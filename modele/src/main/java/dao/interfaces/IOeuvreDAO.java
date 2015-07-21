package dao.interfaces;

import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import oeuvres.Oeuvre;

import java.util.List;

/**
 * Created by Fabien on 14/07/2015.
 */
public interface IOeuvreDAO {

    void create(Oeuvre oeuvre) throws AlreadyExistsException;

    void delete (Oeuvre oeuvre);

    void update (Oeuvre oeuvre);

    Oeuvre find (int id) throws NotFoundException;

    List<Oeuvre> findAll() throws NotFoundException;

    void remove(int id);
}
