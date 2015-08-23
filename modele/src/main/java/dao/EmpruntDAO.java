package dao;

import dao.configuration.AlreadyExistsException;
import dao.configuration.DAOException;
import dao.configuration.NotFoundException;
import dao.interfaces.DAO;
import dao.interfaces.IEmpruntDAO;
import emprunts.Emprunt;
import emprunts.EmpruntFactory;
import oeuvres.Oeuvre;
import oeuvres.OeuvreFactory;
import users.User;
import users.UserFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dao.configuration.DAOUtilitaire.fermetureSilencieuse;
import static dao.configuration.DAOUtilitaire.fermeturesSilencieuses;
import static dao.configuration.DAOUtilitaire.initialisationRequetePreparee;

/**
 * Created by Fabien on 14/07/2015.
 */
public class EmpruntDAO extends DAO<Emprunt> implements IEmpruntDAO {
    private static final String SELECT_EMPRUNT = "SELECT * FROM Database.Emprunt WHERE idOeuvre = ? AND idUser = ?";
    private static final String SELECT_EMPRUNTS = "SELECT * FROM Database.Emprunt";
    private static final String SELECT_EMPRUNTS_EN_RETARD = "SELECT * FROM Database.Emprunt WHERE retour < NOW()";

    private static final String SELECT_OEUVRE_BY_ID = "SELECT * FROM Database.Oeuvre WHERE idOeuvre = ?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM Database.User WHERE idUser = ?";

    private static final String INSERT_EMPRUNT = "INSERT INTO Database.Emprunt (idOeuvre, idUser, retour) VALUES (?, ?, ?)";

//    private static final String UPDATE_EMPRUNT = "UPDATE Database.Emprunt SET idOeuvre = ?, idUser = ?, retour = ? WHERE idOeuvre = ? AND idUser = ?";

    private static final String DELETE_EMPRUNT = "DELETE FROM Database.Emprunt WHERE idOeuvre = ? AND idUser = ?";

    /**
     * Constructeur
     *
     * @param daoFactory daoFactory
     */
    public EmpruntDAO(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(Emprunt obj) throws AlreadyExistsException {
        Connection connexion = null;
        PreparedStatement requete = null;
        PreparedStatement requeteEtu = null;
        ResultSet valeurGenere = null;

        try {
            find(obj.getOeuvre().getId(), obj.getUser().getId());
            throw new AlreadyExistsException("Cet emprunt existe déjà.");
        } catch (NotFoundException nfe) {
            try {
                connexion = daoFactory.getConnection();

                requete = initialisationRequetePreparee(connexion, INSERT_EMPRUNT, false, obj.getOeuvre().getId(), obj.getUser().getId(), obj.getRetour());
                if (requete.executeUpdate() == 0) {
                    throw new DAOException("Échec lors de l'insertion de l'emprunt, aucune ligne dans la table Emprunt.");
                }
                valeurGenere = requete.getGeneratedKeys();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                fermetureSilencieuse(requeteEtu);
                fermeturesSilencieuses(valeurGenere, requete, connexion);
            }
        }
    }

    public Emprunt find(int idO, int idU) throws NotFoundException {
        try {
            return trouver(SELECT_EMPRUNT, idO, idU).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Emprunt not found", e);
        }
    }

    @Override
    public List<Emprunt> findAll() throws NotFoundException {
        return trouver(SELECT_EMPRUNTS);
    }

    @Override
    public List<Emprunt> findRetard() {
        return trouver(SELECT_EMPRUNTS_EN_RETARD);
    }

    @Override
    public void remove(int idO, int idU) {
        Connection connexion = null;
        PreparedStatement requeteReponses = null;
        PreparedStatement requeteQuotat = null;

        try {
            connexion = daoFactory.getConnection();

            requeteQuotat = initialisationRequetePreparee(connexion, DELETE_EMPRUNT, true, idO, idU);

            if (requeteQuotat.executeUpdate() == 0) {
                throw new DAOException("Échec de la suppression de l'emprunt");
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(requeteReponses, connexion);
        }
    }

    private List<Emprunt> trouver(String sql, Object... objets) {
        Connection connexion = null;
        PreparedStatement requete = null;
        ResultSet resultSet = null;
        List<Emprunt> empruntList = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();

            requete = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = requete.executeQuery();

            while ( resultSet.next() ) {
                empruntList.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, requete, connexion );
        }
        return empruntList;
    }

    private Emprunt map(ResultSet resultSet) throws SQLException{
        Emprunt emprunt = EmpruntFactory.createEmprunt();

        int idO = resultSet.getInt("idOeuvre");
        int idU = resultSet.getInt("idUser");
        Date date = resultSet.getDate("retour");

        try {
            reconstituerEmprunt(emprunt, idO, idU, date);
        } catch (NotFoundException e) {
            throw new DAOException("Impoosible de retrouver les infos de l'emprunts");
        }

        return emprunt;
    }

    private void reconstituerEmprunt(Emprunt emprunt, int idO, int idU, Date date) throws NotFoundException {
        Oeuvre oeuvre = findOeuvre(idO);
        User user = findUser(idU);
        emprunt.setOeuvre(oeuvre);
        emprunt.setUser(user);
        emprunt.setRetour(date);
    }

    private User findUser(int idU) throws NotFoundException {
        try {
            return trouverUser(SELECT_USER_BY_ID, idU).get(0);
        }  catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("User not found", e);
        }
    }

    private List<User> trouverUser(String sql, Object... objets) {
        Connection connexion = null;
        PreparedStatement requete = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();

            requete = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = requete.executeQuery();

            while ( resultSet.next() ) {
                userList.add(mapUser(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(resultSet, requete, connexion);
        }
        return userList;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = UserFactory.createUser();

        user.setId(resultSet.getInt("idUser"));
        user.setNom(resultSet.getString("nom"));
        user.setPrenom(resultSet.getString("prenom"));
        user.setMail(resultSet.getString("mail"));
        user.setTel(resultSet.getString("tel"));
        user.setResidence(resultSet.getString("residence"));
        user.setChambre(resultSet.getInt("chambre"));
        return user;
    }

    private Oeuvre findOeuvre(int idO) throws NotFoundException {
        try {
            return trouverOeuvre(SELECT_OEUVRE_BY_ID, idO).get(0);
        }  catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Oeuvre not found", e);
        }
    }

    private List<Oeuvre> trouverOeuvre(String sql, Object... objets) {
        Connection connexion = null;
        PreparedStatement requete = null;
        ResultSet resultSet = null;
        List<Oeuvre> oeuvreList = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();

            requete = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = requete.executeQuery();

            while ( resultSet.next() ) {
                oeuvreList.add(mapOeuvre(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, requete, connexion );
        }
        return oeuvreList;
    }

    private Oeuvre mapOeuvre(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("typeOeuvre");
        Oeuvre oeuvre = OeuvreFactory.createOeuvre(type);

        oeuvre.setId(resultSet.getInt("idOeuvre"));
        oeuvre.setTitre(resultSet.getString("titre"));
        oeuvre.setAuteur(resultSet.getString("auteur"));
        oeuvre.setType(resultSet.getString("typeOeuvre"));
        oeuvre.setQuantite(resultSet.getInt("quantite"));
        oeuvre.setEmpruntable(resultSet.getBoolean("empruntable"));
        return oeuvre;
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

}
