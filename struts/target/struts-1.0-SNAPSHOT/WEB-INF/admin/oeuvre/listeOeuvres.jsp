<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:include value="../adminLTE/adminLTETopLeft.jsp"/>
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:text name="admin.listLivre"/>
            <small><s:property value="titreThematique"/> </small>
        </h1>
        <ol class="breadcrumb">
            <s:url id="accueil" namespace="/" action="accueilConnecte"/>
            <li><s:a href="%{accueil}"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>
            <li class="active"><s:text name="admin.listLivre"/></li>
        </ol>
        <hr/>
        <nav>
            <ul class="pager">
                <li>
                    <s:if test="pageNumber!=0">
                        <s:url id="prev" action="listeOeuvres">
                            <s:param name="pageNumber"><s:property value="%{pageNumber-1}"/></s:param>
                        </s:url>
                        <s:a href="%{prev}"><s:text name="admin.prev"/></s:a>
                    </s:if>
                </li>
                <li>
                    <s:if test="listeOeuvres.size==5">
                        <s:url id="next" action="listeOeuvres">
                            <s:param name="pageNumber"><s:property value="%{pageNumber+1}"/></s:param>
                        </s:url>
                        <s:a href="%{next}"><s:text name="admin.next"/></s:a>
                    </s:if>
                </li>
            </ul>
        </nav>
    </section>

    <!-- Main content -->
    <section class="content">
        <s:div cssClass="container">
            <s:div cssClass="col-md-9 col-md-offset-2">
                <s:if test="listeOeuvres.size==0">
                    <p class="alert-danger col-md-10 col-md-offset-1"><s:text name="admin.noOeuvres"/></p>
                </s:if>
                <s:else>

                    <s:iterator value="listeOeuvres" var="oeuvre">
                        <s:div cssClass="row">
                            <s:div cssClass="col-md-10 col-md-offset-0">
                                <s:div cssClass="panel panel-primary">
                                    <s:div cssClass="panel-heading">
                                        <h3 class="panel-title"><s:property value="#qcm.titre"/></h3>
                                    </s:div>
                                    <s:div cssClass="panel-body">
                                        <s:if test="%{#oeuvre.empruntable == true}">
                                            <p style="background-color: #dd4b39">
                                        </s:if>
                                        <s:else>
                                            <p>
                                        </s:else>

                                        <b><s:text name="oeuvre.titre"/></b> <s:property value="#oeuvre.titre"/><br/>
                                        <b><s:text name="oeuvre.auteur"/> </b> <s:property value="#oeuvre.auteur"/><br/>
                                        <b><s:text name="oeuvre.type"/> </b> <s:property value="#oeuvre.type"/><br/>
                                        <b><s:text name="oeuvre.quantite"/> </b> <s:property value="#oeuvre.quantite"/><br/>
                                        <b>Etat : </b>
                                        <br/>

                                        </p>
                                        <p>
                                            <s:url id="modifier" action="toModifierOeuvre">
                                                <s:param name="idOeuvre"><s:property value="#oeuvre.id"/> </s:param>
                                                <s:param name="pageNumber"><s:property value="pageNumber"/></s:param>
                                            </s:url>
                                            <s:a href="%{modifier}" cssClass="btn btn-primary btn-primary"><span class="glyphicon glyphicon-wrench"></span> <s:text name="admin.mod"/></s:a>
                                            <s:url id="soum" action="supprimerOeuvre" namespace="/">
                                                <s:param name="idOeuvre"><s:property value="#oeuvre.id"/> </s:param>
                                                <s:param name="pageNumber"><s:property value="pageNumber"/></s:param>
                                            </s:url>
                                            <s:a href="%{soum}" cssClass="btn btn-primary btn-danger"><span class="glyphicon glyphicon-ok"></span> <s:text name="admin.suppr"/></s:a>
                                    </s:div>
                                </s:div>
                            </s:div>
                        </s:div>
                    </s:iterator>
                    <br/>
                </s:else>
            </s:div>
        </s:div>
        <nav>
            <ul class="pager">
                <li>
                    <s:if test="pageNumber!=0">
                        <s:url id="prev" action="listeOeuvres">
                            <s:param name="pageNumber"><s:property value="%{pageNumber-1}"/></s:param>
                        </s:url>
                        <s:a href="%{prev}"><s:text name="admin.prev"/></s:a>
                    </s:if>
                </li>
                <li>
                    <s:if test="listeQcm.size==5">
                        <s:url id="next" action="listeOeuvres">
                            <s:param name="pageNumber"><s:property value="%{pageNumber+1}"/></s:param>
                        </s:url>
                        <s:a href="%{next}"><s:text name="admin.next"/></s:a>
                    </s:if>
                </li>
            </ul>
        </nav>
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<s:include value="../adminLTE/adminLTEFooter.jsp"/>