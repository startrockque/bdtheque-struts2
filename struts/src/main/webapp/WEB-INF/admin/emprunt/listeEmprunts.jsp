<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:include value="../adminLTE/adminLTETopLeft.jsp"/>
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:text name="admin.listEmprunt"/>
        </h1>
        <ol class="breadcrumb">
            <s:url id="accueil" namespace="/" action="acceuilConnecte"/>
            <li><s:a href="%{accueil}"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>
            <li class="active"><s:text name="admin.listEmprunt"/></li>
        </ol>
        <hr/>
        <nav>
            <ul class="pager">
                <li>
                    <s:if test="pageNumber!=0">
                        <s:url id="prev" action="listeEmprunts">
                            <s:param name="pageNumber"><s:property value="%{pageNumber-1}"/></s:param>
                        </s:url>
                        <s:a href="%{prev}"><s:text name="admin.prev"/></s:a>
                    </s:if>
                </li>
                <li>
                    <s:if test="listeEmprunts.size==20">
                        <s:url id="next" action="listeEmprunts">
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
                <s:if test="listeEmprunts.size==0">
                    <p class="alert-danger col-md-10 col-md-offset-1"><s:text name="admin.noEmprunts"/></p>
                </s:if>
                <s:else>
                    <table border="2" class="col-md-12">
                        <tr>
                            <th><s:text name="oeuvre.titre"/></th>
                            <th><s:text name="membre.identite"/></th>
                            <th><s:text name="emprunt.retourprevu"/></th>
                            <th><s:text name="admin.suppr"/></th>
                        </tr>
                        <s:iterator value="listeEmprunts" var="emprunt">
                            <%--<s:if test="%{#emprunt.enRetard == true }">--%>
                                <%--<tr style="background-color: #dd4b39">--%>
                            <%--</s:if>--%>
                            <%--<s:else>--%>
                                <tr>
                            <%--</s:else>--%>

                            <th><s:property value="#emprunt.oeuvre.titre"/></th>
                            <th><s:property value="#emprunt.user.prenom"/> <s:property value="#emprunt.user.nom"/></th>
                            <th><s:property value="#emprunt.retour"/></th>

                            <s:url id="suppr" action="supprimerEmprunt" namespace="/">
                                <s:param name="idOeuvre"><s:property value="#emprunt.oeuvre.id"/> </s:param>
                                <s:param name="idUser"><s:property value="#emprunt.user.id"/> </s:param>
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
                        <s:url id="prev" action="listeEmprunts">
                            <s:param name="pageNumber"><s:property value="%{pageNumber-1}"/></s:param>
                        </s:url>
                        <s:a href="%{prev}"><s:text name="admin.prev"/></s:a>
                    </s:if>
                </li>
                <li>
                    <s:if test="listeEmprunts.size==20">
                        <s:url id="next" action="listeEmprunts">
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