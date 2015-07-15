package dao;

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

import static dao.configuration.DAOUtilitaire.fermeturesSilencieuses;
import static dao.configuration.DAOUtilitaire.initialisationRequetePreparee;

/**
 * Created by Fabien on 14/07/2015.
 */
public class OeuvreDAO extends DAO<Oeuvre> implements IOeuvreDAO{
    private static final String SELECT_OEUVRES = "SELECT * FROM Database.Oeuvre";

    /**
     * Constructeur
     *
     * @param daoFactory daoFactory
     */
    public OeuvreDAO(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(Oeuvre obj) {

    }

    @Override
    public void delete(Oeuvre obj) {

    }

    @Override
    public void update(Oeuvre obj) {

    }

    @Override
    public Oeuvre find(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Oeuvre> findAll() throws NotFoundException {
        return trouver(SELECT_OEUVRES);
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
