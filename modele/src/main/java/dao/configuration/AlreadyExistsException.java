package dao.configuration;


/**
 * Une exception qui est renvoy�e dans le cas ou l'on veut ajouter un �l�ment d�j� existant dans la base de donn�es
 */
public class AlreadyExistsException extends Exception {
    public AlreadyExistsException (String s) { super(s);}
}
