<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>


<s:include value="adminLTE/adminLTETopLeft.jsp"/>

<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <%--<section class="content-header">--%>
        <%--<h1>--%>
            <%--<s:text name="admin.accueil"/>--%>
        <%--</h1>--%>
        <%--<ol class="breadcrumb">--%>
            <%--<li><s:a href="#"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>--%>
        <%--</ol>--%>
        <%--<hr/>--%>
    <%--</section>--%>

    <!-- Main content -->
    <section class="content">
        <s:if test="%{empruntsEnRetard.size != 0}">
            <h1><s:text name="emprunt.enRetard"/></h1>
            <table border="2" class="col-md-12">
                <tr>
                    <th><s:text name="oeuvre.titre"/></th>
                    <th><s:text name="membre.identite"/></th>
                    <th><s:text name="emprunt.retourprevu"/></th>
                    <th><s:text name="admin.suppr"/></th>
                </tr>
                <s:iterator value="empruntsEnRetard" var="emprunt">
                    <tr>
                        <th><s:property value="#emprunt.oeuvre.titre"/></th>
                        <th><s:property value="#emprunt.user.nom"/> <s:property value="#emprunt.user.prenom"/></th>
                        <th><s:property value="#emprunt.retour"/></th>

                        <s:url id="suppr" action="supprimerEmprunt" namespace="/">
                            <s:param name="titre"><s:property value="#emprunt.oeuvre.titre"/></s:param>
                            <s:param name="nom"><s:property value="#emprunt.user.nom"/></s:param>
                        </s:url>
                        <th><s:a href="%{suppr}" cssClass="btn btn-primary btn-danger"><span class="glyphicon glyphicon-ok"></span></s:a></th>
                    </tr>
                </s:iterator>
            </table>
        </s:if>
        <s:else>
            <div class="jumbotron">
                <h1><s:text name="admin.accueilH1"/></h1>
                <p class="lead">
                    <s:text name="admin.accueilJumbotron"/>
                </p>
            </div>
        </s:else>
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<s:include value="adminLTE/adminLTEFooter.jsp"/>