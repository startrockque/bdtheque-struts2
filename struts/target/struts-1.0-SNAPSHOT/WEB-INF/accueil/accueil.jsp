<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<html>
<head>
    <s:include value="../include/header.jsp"/>
    <s:include value="../include/espaces.jsp"/>

    <title><s:text name="accueil.titre"/></title>
    <sb:head/>
</head>
<body>

<s:div cssClass="row" >
    <s:div cssClass="col-md-6 col-md-offset-3">
        <h2><s:text name="accueil.accueil"/></h2>
        <s:form theme="bootstrap" cssClass="well form-vertical">
            <p class="lead">
                <s:text name="accueil.text"/>
            </p>
        </s:form>
    </s:div>

    <s:div cssClass="col-md-offset-3"/>
</s:div>

<s:div cssClass="container">
    <br/>
</s:div>

<s:div cssClass="container">
    <s:div cssClass="col-md-6 col-md-offset-3">
        <h2><s:text name="accueil.id"/></h2>

        <s:form action="Login" method="POST" theme="simple" cssClass="well form-search" name="formSaisie" onsubmit="return verifPseudo()">

            <s:textfield id ="login" key="connection.login" name="login" placeholder="Identifiant" />
            <s:password  id="mdp" key="connection.mdp" name="mdp" placeholder="Mot de passe" />
            <s:submit key="connexion.bouton" cssClass="btn btn-primary" />
            <s:actionerror theme="bootstrap"/>
            <s:actionmessage theme="bootstrap"/>
            <s:fielderror theme="bootstrap"/>

        </s:form>

    </s:div>
    <s:div cssClass="col-md-offset-3"><p></p></s:div>
</s:div>


</body>
</html>
