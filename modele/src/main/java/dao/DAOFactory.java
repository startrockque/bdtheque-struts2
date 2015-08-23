package dao;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import dao.configuration.DAOConfigurationException;
import dao.interfaces.IEmpruntDAO;
import dao.interfaces.IOeuvreDAO;
import dao.interfaces.IUserDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAOFactory {

    private static final int MIN_PARTITION               = 50;
    private static final int MAX_PARTITION               = 1000;
    private static final int NB_PARTITION                = 20;
    private static final String FICHIER_PROPERTIES       = "dao/dao.properties";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    private Connection connection;
    private Statement stmt;
    private BoneCP connectionPool           = null;

    /* package */DAOFactory( BoneCP connectionPool ) {
        this.connectionPool = connectionPool;
        initBdd(connectionPool);
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Configuration
     */
    public static DAOFactory getInstance(String path) throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        BoneCP connectionPool;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = "jdbc:h2:file:"+ path + "/bdd/bdtheque";
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( FileNotFoundException e ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.", e );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        try {
            /*
             * Création d'une configuration de pool de connexions via l'objet
             * BoneCPConfig et les différents setters associés.
             */
            BoneCPConfig config = new BoneCPConfig();
            /* Mise en place de l'URL, du nom et du mot de passe */
            config.setJdbcUrl( url );
            config.setUsername( nomUtilisateur );
            config.setPassword( motDePasse );
            /* Paramétrage de la taille du pool */
            config.setMinConnectionsPerPartition( MIN_PARTITION );
            config.setMaxConnectionsPerPartition( MAX_PARTITION );
            config.setPartitionCount( NB_PARTITION );
            /* Création du pool à partir de la configuration, via l'objet BoneCP */
            connectionPool = new BoneCP( config );
        } catch ( SQLException e ) {
            throw new DAOConfigurationException( "Erreur de configuration du pool de connexions.", e );
        }
        /*
         * Enregistrement du pool créé dans une variable d'instance via un appel
         * au constructeur de DAOFactory
         */
        return new DAOFactory( connectionPool );
    }

    /* Méthode chargée de fournir une connexion à la base de données */
    /* package */Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO
     */
    public IOeuvreDAO getOeuvreDAO() {
        return new OeuvreDAO( this );
    }

    public IUserDAO getUserDao() {
        return new UserDao( this );
    }

    public IEmpruntDAO getEmpruntDao() {
        return new EmpruntDAO( this );
    }

    private void initBdd(BoneCP connectionPool) {
        try {
            this.connection = connectionPool.getConnection();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            this.stmt = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "CREATE SCHEMA IF NOT EXISTS Database;" +
                "" +
                "CREATE TABLE IF NOT EXISTS Database.Oeuvre" +
                "(" +
                "    idOeuvre int PRIMARY KEY auto_increment NOT NULL," +
                "    titre text NOT NULL," +
                "    auteur VARCHAR2 (64)  NOT NULL," +
                "    typeOeuvre VARCHAR2 (64)  NOT NULL," +
                "    quantite int NOT NULL," +
                "    empruntable BOOLEAN NOT NULL" +
                ");" +
                "CREATE TABLE IF NOT EXISTS Database.User" +
                "(" +
                "    idUser int PRIMARY KEY auto_increment NOT NULL," +
                "    nom VARCHAR2 (64) NOT NULL," +
                "    prenom VARCHAR2 (64) NOT NULL," +
                "    mail VARCHAR2 (64) NOT NULL," +
                "    tel VARCHAR2 (64) NOT NULL," +
                "    residence VARCHAR2 (64) NOT NULL," +
                "    chambre int NOT NULL" +
                ");" +
                "CREATE TABLE IF NOT EXISTS Database.Emprunt" +
                "(" +
                "    idOeuvre int NOT NULL," +
                "    idUser int NOT NULL," +
                "    retour DATE NOT NULL," +
                "    FOREIGN KEY (idOeuvre) REFERENCES Database.Oeuvre (IDOEUVRE)," +
                "    FOREIGN KEY (idUser) REFERENCES Database.User (IDUSER)," +
                ");";
        try {
            stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stmt != null)
                stmt.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}