<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<s:include value="../adminLTE/adminLTETopLeft.jsp"/>
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:text name="admin.addEmprunt"/>
        </h1>
        <ol class="breadcrumb">
            <s:url id="accueil" namespace="/" action="acceuilConnecte"/>
            <li><s:a href="%{accueil}"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>
            <li class="active"><s:text name="admin.addEmprunt"/></li>
        </ol>
        <hr/>
    </section>

    <!-- Main content -->
    <section class="content">
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
                                <h3 class="panel-title"><s:text name="admin.infosEmprunt"/></h3>
                            </s:div>
                            <s:div cssClass="panel-body dark">
                                <s:form action="creerEmprunt"  name="formulaire" id="formulaire">
                                    <sx:autocompleter name="titre" id="oeuvre" label="Titre de l'oeuvre" list="listTitres" value="" showDownArrow="false" dropdownWidth="600" dropdownHeight="150dp" onkeyup="verifEmprunt()" cssClass="col-md-11 input-higher"/>
                                    <sx:autocompleter name="nom" id="emprunteur" label="Emprunté par" list="listNoms" value="" showDownArrow="false" dropdownWidth="600" dropdownHeight="150p" onkeyup="verifEmprunt()" cssClass="col-md-11 input-higher"/>
                                    <s:submit disabled="true" id="btn" cssClass="btn btn-block btn-success" key="admin.creer"/>
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