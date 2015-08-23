package users;

/**
 * Created by Fabien on 07/07/2015.
 */
public abstract class User {

    protected int id;
    protected String nom;
    protected String prenom;
    protected String mail;
    protected String tel;
    protected String residence;
    protected int chambre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public int getChambre() {
        return chambre;
    }

    public void setChambre(int chambre) {
        this.chambre = chambre;
    }

    @Override
    public String toString() {
        return prenom + " || " + nom + " || " + chambre + " || " + residence;
    }
}
