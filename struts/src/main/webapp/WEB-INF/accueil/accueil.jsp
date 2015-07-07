<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<html>
<head>
    <s:include value="../include/header.jsp"/>
    <s:include value="../include/espaces.jsp"/>

    <title><s:text name="accueil.gya"/></title>
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
        <s:form theme="bootstrap" cssClass="well form-vertical">
            <p class="text-center">
                <s:url id="login" namespace="/" action="Log"/>
                <s:a href="%{login}"><s:text name="accueil.id"/></s:a>
            </p>
        </s:form>
    </s:div>
    <s:div cssClass="col-md-offset-3"><p></p></s:div>
</s:div>


</body>
</html>
