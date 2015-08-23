package emprunts;

import oeuvres.Oeuvre;
import users.User;

import java.util.Date;

/**
 * Created by Fabien on 07/07/2015.
 */
public class Emprunt {

    private Oeuvre oeuvre;
    private User user;
    private Date retour;

    public Emprunt() {
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRetour() {
        return retour;
    }

    public void setRetour(Date retour) {
        this.retour = retour;
    }
}
