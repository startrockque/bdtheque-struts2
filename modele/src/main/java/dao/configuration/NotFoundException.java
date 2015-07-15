package dao.configuration;

/**
 * Created by Dylan on 26/12/2014.
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
