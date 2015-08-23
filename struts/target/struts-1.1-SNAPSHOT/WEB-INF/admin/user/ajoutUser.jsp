<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:include value="../adminLTE/adminLTETopLeft.jsp"/>
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:text name="admin.addUser"/>
        </h1>
        <ol class="breadcrumb">
            <s:url id="accueil" namespace="/" action="acceuilConnecte"/>
            <li><s:a href="%{accueil}"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>
            <li class="active"><s:text name="admin.addUser"/></li>
        </ol>
        <hr/>
    </section>

    <!-- Main content -->
    <p class="content">
        <s:div cssClass="container">
        <s:div cssClass="col-md-9 col-md-offset-2">
        <s:div cssClass="row">
        <s:if test="messageOK!=null">
        <s:div cssClass="alert alert-success alert-dismissable col-md-10" role="alert">
        <i class="glyphicon glyphicon-floppy-saved"></i>
            <s:property value="messageOK"/>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        </s:div>
        </s:if>
        <s:if test="messageKO!=null">
        <s:div cssClass="alert alert-danger alert-dismissable col-md-10" role="alert">
        <i class="glyphicon glyphicon-floppy-remove"></i>
            <s:property value="messageKO"/>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        </s:div>
        </s:if>
        <s:div cssClass="col-md-10 col-md-offset-0">
        <s:div cssClass="panel panel-primary">
        <s:div cssClass="panel-heading">
    <h3 class="panel-title"><s:text name="admin.infosUser"/></h3>
    </s:div>
    <s:div cssClass="panel-body">
        <s:form action="creerUser" theme="bootstrap" name="formulaire" id="formulaire">
            <s:div>
                <br/>Prénom : <br/>
                <s:textfield name="prenom" id="prenom" placeholder="Prénom" value="" onkeyup="verifUser()" cssClass="col-md-12 input-higher"/>
            </s:div>
            <s:div>
                Nom : <br/>
                <s:textfield name="nom" id="nom" placeholder="Nom" value="" onkeyup="verifUser()" cssClass="col-md-12 input-higher"/>
            </s:div>
            <s:div>
                <br/>Mail : <br/>
                <s:textfield name="mail" id="mail" placeholder="Email" value="" onclick="verifUser()" cssClass="col-md-12 input-higher"/>
            </s:div>
            <s:div>
                <br/>Téléphone : <br/>
                <s:textfield name="tel" id="tel" placeholder="Téléphone" value="" onclick="verifUser()" cssClass="col-md-12 input-higher"/>
            </s:div>

            <s:div>
                <br/>Résidence : <br/>
                <s:select name="residence" list="listeResidences" cssClass="col-md-7"/>
            </s:div>
            <br/>
            <s:div>
                <br/>Chambre : <br/>
                <s:textfield name="chambre" id="chambre" placeholder="Chambre" cssClass="col-md-3" onkeyup="verifUser()"/>
            </s:div>
            <br/>
            <s:div>
                <s:submit disabled="true" id="btn" cssClass="btn btn-block btn-success" key="admin.creer"/>
            </s:div>
        </s:form>
    </s:div>
    </s:div>
    </s:div>
    </s:div>
    </s:div>
    </s:div>
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<s:include value="../adminLTE/adminLTEFooter.jsp"/>