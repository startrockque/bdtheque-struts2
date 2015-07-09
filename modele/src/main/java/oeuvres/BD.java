package oeuvres;

/**
 * Created by Fabien on 07/07/2015.
 */
public class BD extends Oeuvre {

    public BD(){
        id = 0;
        titre = "";
        auteur = "";
        type = "BD";
        quantite = 0;
        empruntable = true;
    }

    public BD(String t, String a, int q, boolean e){
        id = 0;
        titre = t;
        auteur = a;
        type = "BD";
        quantite = q;
        empruntable = e;
    }

    public BD(int i, String t, String a, int q, boolean e){
        id = i;
        titre = t;
        auteur = a;
        type = "BD";
        quantite = q;
        empruntable = e;
    }
}
