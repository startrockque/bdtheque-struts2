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

    void update (Oeuvre oeuvre) throws AlreadyExistsException;

    Oeuvre find (int id) throws NotFoundException;

    Oeuvre find(String titre, String type) throws NotFoundException;

    List<Oeuvre> findAll() throws NotFoundException;

    List<Oeuvre> findAllEmpruntables();

    void remove(int id);
}
