/**
 * Created by Baptiste on 01/01/15.
 */
function verifPseudo()
{
    if(document.formSaisie.login.value != "" && document.formSaisie.mdp.value != "") {
        // les données sont ok, on peut envoyer le formulaire
        return true;
    }
    else {
        // sinon on affiche un message
        alert("Completez tous les champs");
        // et on indique de ne pas envoyer le formulaire
        return false;
    }
}