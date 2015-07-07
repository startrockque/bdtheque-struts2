package oeuvres;

/**
 * Created by Fabien on 07/07/2015.
 */
public class Livre extends Oeuvre {

    public Livre(){
        id = 0;
        titre = "";
        auteur = "";
        type = 0;
        quantite = 0;
    }

    public Livre(String t, String a, int q){
        id = 0;
        titre = t;
        auteur = a;
        type = 0;
        quantite = q;
    }

    public Livre(int i, String t, String a, int q){
        id = i;
        titre = t;
        auteur = a;
        type = 0;
        quantite = q;
    }
}
