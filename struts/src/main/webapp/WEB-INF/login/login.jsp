<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<html>
<head>
    <link href="./css/styles.css" rel="stylesheet" type="text/css"/>
    <s:include value="../include/header.jsp"/>
    <s:include value="../include/espaces.jsp"/>
    <script src="./JS/verifLogin.js" type="text/javascript"></script>


    <title><s:text name="accueil.titre"/></title>
</head>
<body>
<s:div cssClass="container">
    <s:div cssClass="col-md-6 col-md-offset-3">
        <h2><s:text name="connexion.titre2"/></h2>

        <s:form  action="Login" method="POST" theme="simple" cssClass="well form-search" name="formSaisie" onsubmit="return verifPseudo()">

            <s:textfield id ="login" key="connection.login" name="login" placeholder="Login" />
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