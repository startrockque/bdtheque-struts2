package dao;

import dao.configuration.AlreadyExistsException;
import dao.configuration.DAOException;
import dao.configuration.NotFoundException;
import dao.interfaces.DAO;
import dao.interfaces.IUserDAO;
import users.User;
import users.UserFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.configuration.DAOUtilitaire.fermetureSilencieuse;
import static dao.configuration.DAOUtilitaire.fermeturesSilencieuses;
import static dao.configuration.DAOUtilitaire.initialisationRequetePreparee;

public class UserDao extends DAO<User> implements IUserDAO {
    private static final String SELECT_USER = "SELECT * FROM Database.User WHERE nom = ? AND prenom = ?";
    private static final String SELECT_USERS = "SELECT * FROM Database.User";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM Database.User WHERE idUser = ?";

    private static final String INSERT_USER = "INSERT INTO Database.User (nom, prenom, mail, tel, residence, chambre) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER = "UPDATE Database.User SET nom = ?, prenom = ?, mail = ?, tel = ?, residence = ?, chambre = ?  WHERE idUser = ?";

    private static final String DELETE_USER = "DELETE FROM Database.User WHERE idUser = ?";

    /**
     * Constructeur
     *
     * @param daoFactory daoFactory
     */
    public UserDao(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(User obj) throws AlreadyExistsException{
        Connection connexion = null;
        PreparedStatement requete = null;
        PreparedStatement requeteEtu = null;
        ResultSet valeurGenere = null;

        try {
            find(obj.getNom(), obj.getPrenom());
            throw new AlreadyExistsException("Cet utilisateur existe déjà.");
        } catch (NotFoundException nfe) {
            try {
                connexion = daoFactory.getConnection();

                requete = initialisationRequetePreparee(connexion, INSERT_USER, true, obj.getNom(), obj.getPrenom(), obj.getMail(), obj.getTel(), obj.getResidence(), obj.getChambre());
                if (requete.executeUpdate() == 0) {
                    throw new DAOException("Échec lors de l'insertion de l'utilisateur, aucune ligne dans la table User.");
                }
                valeurGenere = requete.getGeneratedKeys();
                if (valeurGenere.next()) {
                    obj.setId(valeurGenere.getInt(1));
                } else {
                    throw new DAOException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                fermetureSilencieuse(requeteEtu);
                fermeturesSilencieuses(valeurGenere, requete, connexion);
            }
        }
    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public void update(User obj) {
        Connection connexion = null;
        PreparedStatement requeteReponses = null;
        PreparedStatement requeteQuotat = null;

        try {
            connexion = daoFactory.getConnection();

            requeteQuotat = initialisationRequetePreparee(connexion, UPDATE_USER, true, obj.getNom(), obj.getPrenom(), obj.getMail(), obj.getTel(), obj.getResidence(), obj.getChambre() ,obj.getId());

            if (requeteQuotat.executeUpdate() == 0) {
                throw new DAOException("Échec de la mise à jour de l'oeuvre");
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(requeteReponses, connexion );
        }
    }

    @Override
    public User find(int id) throws NotFoundException {
        try {
            return trouver(SELECT_USER_BY_ID, id).get(0);
        }  catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("User not found", e);
        }
    }

    public User find (String nom, String prenom) throws NotFoundException {
        try {
            return trouver(SELECT_USER, nom, prenom).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("User not found", e);
        }
    }

    @Override
    public List<User> findAll() throws NotFoundException {
        return trouver(SELECT_USERS);
    }

    @Override
    public void remove(int id) {
        Connection connexion = null;
        PreparedStatement requeteReponses = null;
        PreparedStatement requeteQuotat = null;

        try {
            connexion = daoFactory.getConnection();

            requeteQuotat = initialisationRequetePreparee(connexion, DELETE_USER, true, id);

            if (requeteQuotat.executeUpdate() == 0) {
                throw new DAOException("Échec de la suppression de l'utilisateur");
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(requeteReponses, connexion );
        }
    }

    private List<User> trouver(String sql, Object... objets) {
        Connection connexion = null;
        PreparedStatement requete = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();

            requete = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = requete.executeQuery();

            while ( resultSet.next() ) {
                userList.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, requete, connexion );
        }
        return userList;
    }

    private User map(ResultSet resultSet) throws SQLException{
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
}