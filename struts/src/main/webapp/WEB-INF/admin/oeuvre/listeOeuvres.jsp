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
        </h1>
        <ol class="breadcrumb">
            <s:url id="accueil" namespace="/" action="acceuilConnecte"/>
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
                    <s:if test="listeOeuvresPagine.size==20">
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
            <s:div cssClass="col-md-11">
                <s:if test="messageOK!=null">
                    <s:div cssClass="alert alert-success alert-dismissable col-md-10" role="alert">
                        <i class="glyphicon glyphicon-floppy-saved"></i>
                        <s:property value="messageOK"/>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </s:div>
                </s:if>
                <s:if test="listeOeuvres.size==0">
                    <p class="alert-danger col-md-10 col-md-offset-1"><s:text name="admin.noOeuvres"/></p>
                </s:if>
                <s:else>
                    <table border="2" class="col-md-12">
                        <tr>
                            <th><s:text name="oeuvre.titre"/></th>
                            <th><s:text name="oeuvre.auteur"/></th>
                            <th><s:text name="oeuvre.type"/></th>
                            <th><s:text name="oeuvre.quantite"/></th>
                            <th><s:text name="admin.mod"/></th>
                            <th><s:text name="admin.suppr"/></th>
                        </tr>
                        <s:iterator value="listeOeuvresPagine" var="oeuvre">
                        <s:if test="%{#oeuvre.empruntable == false }">
                            <tr style="background-color: #dd4b39">
                        </s:if>
                        <s:else>
                            <tr>
                        </s:else>

                            <th><s:property value="#oeuvre.titre"/></th>
                            <th><s:property value="#oeuvre.auteur"/></th>
                            <th><s:property value="#oeuvre.type"/></th>
                            <th><s:property value="#oeuvre.quantite"/></th>

                            <s:url id="modifier" action="toModifierOeuvre">
                            <s:param name="idOeuvre"><s:property value="#oeuvre.id"/> </s:param>
                                <s:param name="pageNumber"><s:property value="pageNumber"/></s:param>
                            </s:url>
                            <th><s:a href="%{modifier}" cssClass="btn btn-primary btn-primary"><span class="glyphicon glyphicon-wrench"></span></s:a></th>

                            <s:url id="suppr" action="supprimerOeuvre" namespace="/">
                                <s:param name="idOeuvre"><s:property value="#oeuvre.id"/> </s:param>
                                <s:param name="pageNumber"><s:property value="pageNumber"/></s:param>
                            </s:url>
                            <th><s:a href="%{suppr}" cssClass="btn btn-primary btn-danger"><span class="glyphicon glyphicon-ok"></span></s:a></th>
                        </tr>
                        </s:iterator>
                    </table>
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
                    <s:if test="listeOeuvresPagine.size==20">
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