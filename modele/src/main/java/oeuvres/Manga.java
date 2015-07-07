package oeuvres;

/**
 * Created by Fabien on 07/07/2015.
 */
public class Manga extends Oeuvre {

    public Manga(){
        id = 0;
        titre = "";
        auteur = "";
        type = 2;
        quantite = 0;
        empruntable = true;
    }

    public Manga(String t, String a, int q, boolean e){
        id = 0;
        titre = t;
        auteur = a;
        type = 2;
        quantite = q;
        empruntable = e;
    }

    public Manga(int i, String t, String a, int q, boolean e){
        id = i;
        titre = t;
        auteur = a;
        type = 2;
        quantite = q;
        empruntable = e;
    }
}
