package oeuvres;

/**
 * Created by Fabien on 07/07/2015.
 */
public class Livre extends Oeuvre {

    public Livre(){
        id = 0;
        titre = "";
        auteur = "";
        type = "Livre";
        quantite = 0;
        empruntable = true;
    }

    public Livre(String t, String a, int q, boolean e){
        id = 0;
        titre = t;
        auteur = a;
        type = "Livre";
        quantite = q;
        empruntable = e;
    }

    public Livre(int i, String t, String a, int q, boolean e){
        id = i;
        titre = t;
        auteur = a;
        type = "Livre";
        quantite = q;
        empruntable = e;
    }
}
