<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>


<s:include value="adminLTE/adminLTETopLeft.jsp"/>

<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <s:text name="admin.accueil"/>
        </h1>
        <ol class="breadcrumb">
            <li><s:a href="#"><i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span></s:a></li>
        </ol>
        <hr/>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="jumbotron">
            <h1><s:text name="admin.accueilH1"/></h1>
            <p class="lead">
                <s:text name="admin.accueilJumbotron"/>
            </p>
        </div>
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<s:include value="adminLTE/adminLTEFooter.jsp"/>