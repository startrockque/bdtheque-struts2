package dao.configuration;


/**
 * Une exception qui est renvoyée dans le cas ou l'on veut ajouter un élément déjà existant dans la base de données
 */
public class AlreadyExistsException extends Exception {
    public AlreadyExistsException (String s) { super(s);}
}
