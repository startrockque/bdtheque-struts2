package dao;

import dao.configuration.AlreadyExistsException;
import dao.configuration.DAOException;
import dao.configuration.NotFoundException;
import dao.interfaces.DAO;
import dao.interfaces.IOeuvreDAO;
import oeuvres.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.configuration.DAOUtilitaire.fermetureSilencieuse;
import static dao.configuration.DAOUtilitaire.fermeturesSilencieuses;
import static dao.configuration.DAOUtilitaire.initialisationRequetePreparee;

/**
 * Created by Fabien on 14/07/2015.
 */
public class OeuvreDAO extends DAO<Oeuvre> implements IOeuvreDAO{
    private static final String SELECT_OEUVRE = "SELECT * FROM Database.Oeuvre WHERE titre = ? AND typeOeuvre = ?";
    private static final String SELECT_OEUVRES = "SELECT * FROM Database.Oeuvre";
    private static final String SELECT_OEUVRES_EMPRUNTABLES = "SELECT * FROM Database.Oeuvre WHERE empruntable = true";
    private static final String SELECT_OEUVRE_BY_ID = "SELECT * FROM Database.Oeuvre WHERE idOeuvre = ?";

    private static final String INSERT_OEUVRE = "INSERT INTO Database.Oeuvre (titre, auteur, typeOeuvre, quantite, empruntable) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_OEUVRE = "UPDATE Database.Oeuvre SET titre = ?, auteur = ?, typeOeuvre = ?, quantite = ?, empruntable = ? WHERE idOeuvre = ?";

    private static final String DELETE_OEUVRE = "DELETE FROM Database.Oeuvre WHERE idOeuvre = ?";

    /**
     * Constructeur
     *
     * @param daoFactory daoFactory
     */
    public OeuvreDAO(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(Oeuvre obj) throws AlreadyExistsException {
        Connection connexion = null;
        PreparedStatement requete = null;
        PreparedStatement requeteEtu = null;
        ResultSet valeurGenere = null;

        try {
            find(obj.getTitre(), obj.getAuteur());
            throw new AlreadyExistsException("Cette oeuvre existe déjà.");
        } catch (NotFoundException nfe) {
            try {
                connexion = daoFactory.getConnection();

                requete = initialisationRequetePreparee(connexion, INSERT_OEUVRE, true, obj.getTitre(), obj.getAuteur(), obj.getType(), obj.getQuantite(), obj.isEmpruntable());
                if (requete.executeUpdate() == 0) {
                    throw new DAOException("Échec lors de l'insertion de l'oeuvre, aucune ligne dans la table Oeuvre.");
                }
                valeurGenere = requete.getGeneratedKeys();
                if (valeurGenere.next()) {
                    obj.setId(valeurGenere.getInt(1));
                } else {
                    throw new DAOException("Échec de la création de l'oeuvre en base, aucun ID auto-généré retourné.");
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
    public void delete(Oeuvre obj) {

    }

    @Override
    public void remove(int id) {
        Connection connexion = null;
        PreparedStatement requeteReponses = null;
        PreparedStatement requeteQuotat = null;

        try {
            connexion = daoFactory.getConnection();

            requeteQuotat = initialisationRequetePreparee(connexion, DELETE_OEUVRE, true, id);

            if (requeteQuotat.executeUpdate() == 0) {
                throw new DAOException("Échec de la suppression de l'oeuvre");
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(requeteReponses, connexion );
        }
    }

    @Override
    public void update(Oeuvre obj) throws AlreadyExistsException {
        Connection connexion = null;
        PreparedStatement requeteReponses = null;
        PreparedStatement requeteQuotat = null;

        try {
            find(obj.getTitre(), obj.getType());
            throw new AlreadyExistsException("Cette oeuvre existe déjà.");
        } catch (NotFoundException nfe) {
            try {
                connexion = daoFactory.getConnection();

                requeteQuotat = initialisationRequetePreparee(connexion, UPDATE_OEUVRE, true, obj.getTitre(), obj.getAuteur(), obj.getType(), obj.getQuantite(), obj.isEmpruntable(), obj.getId());

                if (requeteQuotat.executeUpdate() == 0) {
                    throw new DAOException("Échec de la mise à jour de l'oeuvre");
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                fermeturesSilencieuses(requeteReponses, connexion);
            }
        }
    }

    @Override
    public Oeuvre find(int id) throws NotFoundException {
        try {
            return trouver(SELECT_OEUVRE_BY_ID, id).get(0);
        }  catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Oeuvre not found", e);
        }
    }

    @Override
    public Oeuvre find(String titre, String type) throws NotFoundException{
        try {
            return trouver(SELECT_OEUVRE, titre, type).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Oeuvre not found", e);
        }
    }

    @Override
    public List<Oeuvre> findAll() throws NotFoundException {
        return trouver(SELECT_OEUVRES);
    }

    @Override
    public List<Oeuvre> findAllEmpruntables() {
        return trouver(SELECT_OEUVRES_EMPRUNTABLES);
    }

    private List<Oeuvre> trouver(String sql, Object... objets) {
        Connection connexion = null;
        PreparedStatement requete = null;
        ResultSet resultSet = null;
        List<Oeuvre> oeuvreList = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();

            requete = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = requete.executeQuery();

            while ( resultSet.next() ) {
                oeuvreList.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, requete, connexion );
        }
        return oeuvreList;
    }

    private Oeuvre map(ResultSet resultSet) throws SQLException{
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
}
