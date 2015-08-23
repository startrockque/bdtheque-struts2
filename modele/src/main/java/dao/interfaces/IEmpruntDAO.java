package dao.interfaces;

import dao.configuration.AlreadyExistsException;
import dao.configuration.NotFoundException;
import emprunts.Emprunt;

import java.util.List;

/**
 * Created by Fabien on 14/07/2015.
 */
public interface IEmpruntDAO {
    void create(Emprunt emprunt) throws AlreadyExistsException;

    void delete (Emprunt emprunt);

    void update (Emprunt emprunt);

    Emprunt find (int idO, int idU) throws NotFoundException;

    List<Emprunt> findAll() throws NotFoundException;

    List<Emprunt> findRetard();

    void remove(int idO, int idU);
}
