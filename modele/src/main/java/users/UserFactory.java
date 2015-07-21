package users;

/**
 * Created by Fabien on 21/07/2015.
 */
public class UserFactory {

    public static User createUser(){
        return new Membre();
    }
}
