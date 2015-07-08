function verifTitre()
{
    document.formulaire.btnCreer.disabled = document.formulaire.qcmTitre.value == "";
}

function verifTitreThema()
{
    document.formThema.btnCreer.disabled = document.formThema.titreThematique.value == "";
}

function verifQues(){
    if(document.formQues.ques.value != "" && document.formQues.rep1.value != "" && document.formQues.rep2.value != "" && document.formQues.rep3.value != "" && document.formQues.rep4.value != ""){
        document.formQues.sub.disabled = !(document.getElementById("chk1").checked == true || document.getElementById("chk2").checked == true || document.getElementById("chk3").checked == true || document.getElementById("chk4").checked == true);
    } else {
        document.formQues.sub.disabled = true;
    }
}

function verifUser() {
    document.formUser.btn.disabled = document.formUser.nomUser.value == "" || document.formUser.mdpUser.value == "";
}

function verifSearch(){
    document.formSearch.sub.disabled = document.formSearch.search.value.length < 3;
}

function verifSearch2(){
    document.formSearch2.sub2.disabled = document.formSearch2.search2.value.length < 3;
}

function verifSearch3(){
    document.formSearch3.sub3.disabled = document.formSearch3.search3.value.length < 3;
}