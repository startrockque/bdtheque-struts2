package oeuvres;

/**
 * Created by Fabien on 15/07/2015.
 */
public class OeuvreFactory {

    public static Oeuvre createOeuvre(String type){
        switch (type){
            case "Livre":
                return new Livre();
            case "Manga":
                return new Manga();
            default:
                return new BD();
        }
    }
}
