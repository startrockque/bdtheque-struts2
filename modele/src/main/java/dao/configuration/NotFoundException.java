package dao.configuration;

/**
 * Une exception qui est renvoy�e dans le cas ou l'�l�ment recherch� n'est pas dans la base de donn�es.
 */
public class NotFoundException extends Exception {
    public NotFoundException( String message ) {
        super( message );
    }

    public NotFoundException( String message, Throwable cause ) {
        super( message, cause );
    }

    public NotFoundException( Throwable cause ) {
        super( cause );
    }
}
