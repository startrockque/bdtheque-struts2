<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:include value="../adminLTE/adminLTETopLeft.jsp"/>
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:text name="admin.listMembre"/>
        </h1>
        <ol class="breadcrumb">
            <s:url id="accueil" namespace="/" action="acceuilConnecte"/>
            <li><s:a href="%{accueil}"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>
            <li class="active"><s:text name="admin.listMembre"/></li>
        </ol>
        <hr/>
        <nav>
            <ul class="pager">
                <li>
                    <s:if test="pageNumberU!=0">
                        <s:url id="prev" action="listeMembres">
                            <s:param name="pageNumberU"><s:property value="%{pageNumberU-1}"/></s:param>
                        </s:url>
                        <s:a href="%{prev}"><s:text name="admin.prev"/></s:a>
                    </s:if>
                </li>
                <li>
                    <s:if test="listUsers.size==20">
                        <s:url id="next" action="listeMembres">
                            <s:param name="pageNumberU"><s:property value="%{pageNumberU+1}"/></s:param>
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
                <s:if test="listUsers.size==0">
                    <p class="alert-danger col-md-10 col-md-offset-1"><s:text name="admin.noMembres"/></p>
                </s:if>
                <s:else>
                    <table border="2" class="col-md-12">
                        <tr>
                            <th><s:text name="user.nom"/></th>
                            <th><s:text name="user.prenom"/></th>
                            <th><s:text name="user.mail"/></th>
                            <th><s:text name="user.tel"/></th>
                            <th><s:text name="user.residence"/></th>
                            <th><s:text name="user.chambre"/></th>
                            <th><s:text name="admin.mod"/></th>
                            <th><s:text name="admin.suppr"/></th>
                        </tr>
                        <s:iterator value="listUsers" var="user">
                             <tr>
                                <th><s:property value="#user.nom"/></th>
                                <th><s:property value="#user.prenom"/></th>
                                <th><s:property value="#user.mail"/></th>
                                <th><s:property value="#user.tel"/></th>
                                <th><s:property value="#user.residence"/></th>
                                <th><s:property value="#user.chambre"/></th>

                                <s:url id="modifier" action="toModifierUser">
                                    <s:param name="idUser"><s:property value="#user.id"/> </s:param>
                                    <s:param name="pageNumberU"><s:property value="pageNumberU"/></s:param>
                                </s:url>
                                <th><s:a href="%{modifier}" cssClass="btn btn-primary btn-primary"><span class="glyphicon glyphicon-wrench"></span></s:a></th>

                                <s:url id="suppr" action="supprimerUser" namespace="/">
                                    <s:param name="idUser"><s:property value="#user.id"/> </s:param>
                                    <s:param name="pageNumberU"><s:property value="pageNumberU"/></s:param>
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
                    <s:if test="pageNumberU!=0">
                        <s:url id="prev" action="listeMembres">
                            <s:param name="pageNumberU"><s:property value="%{pageNumberU-1}"/></s:param>
                        </s:url>
                        <s:a href="%{prev}"><s:text name="admin.prev"/></s:a>
                    </s:if>
                </li>
                <li>
                    <s:if test="listUsers.size==20">
                        <s:url id="next" action="listeMembres">
                            <s:param name="pageNumberU"><s:property value="%{pageNumberU+1}"/></s:param>
                        </s:url>
                        <s:a href="%{next}"><s:text name="admin.next"/></s:a>
                    </s:if>
                </li>
            </ul>
        </nav>
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<s:include value="../adminLTE/adminLTEFooter.jsp"/>