package dao.configuration;

/**
 * Une exception qui est renvoyée dans le cas ou l'élément recherché n'est pas dans la base de données.
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
