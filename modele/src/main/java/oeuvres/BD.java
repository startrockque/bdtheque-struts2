package oeuvres;

/**
 * Created by Fabien on 07/07/2015.
 */
public class BD extends Oeuvre {

    public BD(){
        id = 0;
        titre = "";
        auteur = "";
        type = 1;
        quantite = 0;
    }

    public BD(String t, String a, int q){
        id = 0;
        titre = t;
        auteur = a;
        type = 1;
        quantite = q;
    }

    public BD(int i, String t, String a, int q){
        id = i;
        titre = t;
        auteur = a;
        type = 1;
        quantite = q;
    }
}
