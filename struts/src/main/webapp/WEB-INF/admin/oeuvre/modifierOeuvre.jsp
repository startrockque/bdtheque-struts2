<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:include value="../adminLTE/adminLTETopLeft.jsp"/>
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:text name="admin.modifOeuvre"/>
        </h1>
        <ol class="breadcrumb">
            <s:url id="accueil" namespace="/" action="acceuilConnecte"/>
            <li><s:a href="%{accueil}"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>
            <li class="active"><s:text name="admin.modif"/></li>
        </ol>
        <hr/>
    </section>

    <!-- Main content -->
    <section class="content">
        <s:div cssClass="container">
            <s:url id="livres" namespace="/" action="listeOeuvres">
                <s:param name="pageNumber"><s:property value="pageNumber"/></s:param>
            </s:url>
            <s:a href="%{livres}">
                <span><s:text name="admin.listLivre"/></span>
            </s:a>

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
                                <h3 class="panel-title"><s:text name="admin.infosOeuvre"/></h3>
                            </s:div>
                            <s:div cssClass="panel-body">
                                <s:form action="modifierOeuvre" theme="simple" name="formulaire" id="formulaire">
                                    <s:div>
                                        Titre : <br/>
                                        <s:textfield name="titre" id="titre" placeholder="Titre" onkeyup="verifOeuvre()" cssClass="col-md-12 input-higher"/>
                                    </s:div>
                                    <s:div>
                                        <br/>Auteur : <br/>
                                        <s:textfield name="auteur" id="auteur" placeholder="Auteur" onkeyup="verifOeuvre()" cssClass="col-md-12 input-higher"/>
                                    </s:div>
                                    <s:div>
                                        <br/>
                                        <s:select name="type" list="listeTypes" cssClass="col-md-7"/>
                                    </s:div>
                                    <br/>
                                    <s:div>
                                        <br/>Quantité : <br/>
                                        <s:textfield type="number" name="quantite" placeholder="Quantité" step="1" min="0" cssClass="col-md-3"/>
                                    </s:div>

                                    <br/>
                                    <p class="col-md-offset-4">
                                        <s:div cssClass="input-group col-md-3">
                                            <s:text name="oeuvre.empruntable"/>
                                            <span class="input-group-addon">
                                                <s:checkbox name="empruntable" id="empruntable"/>
                                            </span>
                                        </s:div>
                                    </p>
                                    <s:hidden name="idOeuvre"/>
                                    <s:hidden name="pageNumber"/>
                                    <s:div>
                                        <s:submit id="btn" cssClass="btn btn-block btn-success" key="admin.mod"/>
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