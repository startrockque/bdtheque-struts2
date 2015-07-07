<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <title><s:text name="accueil.titre"/></title>

  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />

  <!-- Ionicons -->
  <link href="//code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css" rel="stylesheet" type="text/css" />

  <!-- Theme style -->
  <link href="../css/AdminLTE.css" rel="stylesheet" type="text/css" />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->

  <%--<script src="./JS/verifTitre.js" type="text/javascript"></script>--%>
  <%--<script>--%>
    <%--function verifTitre()--%>
    <%--{--%>
      <%--document.formulaire.btnCreer.disabled = document.formulaire.qcmTitre.value == "";--%>
    <%--}--%>

    <%--function verifTitreThema()--%>
    <%--{--%>
      <%--document.formThema.btnCreer.disabled = document.formThema.titreThematique.value == "";--%>
    <%--}--%>

    <%--function verifQues(){--%>
      <%--if(document.formQues.ques.value != "" && document.formQues.rep1.value != "" && document.formQues.rep2.value != "" && document.formQues.rep3.value != "" && document.formQues.rep4.value != ""){--%>
        <%--document.formQues.sub.disabled = !(document.getElementById("chk1").checked == true || document.getElementById("chk2").checked == true || document.getElementById("chk3").checked == true || document.getElementById("chk4").checked == true);--%>
      <%--} else {--%>
        <%--document.formQues.sub.disabled = true;--%>
      <%--}--%>
    <%--}--%>

    <%--function verifUser() {--%>
      <%--document.formUser.btn.disabled = document.formUser.nomUser.value == "" || document.formUser.mdpUser.value == "";--%>
    <%--}--%>

    <%--function verifSearch(){--%>
      <%--document.formSearch.sub.disabled = document.formSearch.search.value.length < 3;--%>
    <%--}--%>

    <%--function verifSearch2(){--%>
      <%--document.formSearch2.sub2.disabled = document.formSearch2.search2.value.length < 3;--%>
    <%--}--%>

    <%--function verifSearch3(){--%>
      <%--document.formSearch3.sub3.disabled = document.formSearch3.search3.value.length < 3;--%>
    <%--}--%>
  <%--</script>--%>

  <sb:head/>
</head>
<body class="skin-black" >
<!-- header logo: style can be found in header.less -->
<header class="header">
  <s:url id="accueil" namespace="/logged" action="accueil"/>
  <s:a href="%{accueil}" cssClass="logo">
    <!-- Add the class icon to your logo image or logo icon to add the margining -->
    <s:text name="admin.accueilLogo"/>
  </s:a>

  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </a>

    <div class="navbar-right">
      <s:url namespace="/" action="deco" id="deco"/>
      <s:a href="%{deco}" cssClass="btn btn-danger btn-danger"><span class="glyphicon glyphicon-off"></span></s:a>
    </div>
  </nav>
</header>

<div class="wrapper row-offcanvas row-offcanvas-left">
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left info">
          <p><em><s:property value="#session.admin.login" /></em></p>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="active">
          <s:a href="%{accueil}">
            <i class="glyphicon glyphicon-home"></i> <span><s:text name="admin.accueil"/></span>
          </s:a>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="glyphicon glyphicon-book"></i> <span><s:text name="admin.livre"/></span>
          </a>
          <ul class="treeview-menu">
            <li>
              <s:url id="livres" namespace="/logged" action="listeOeuvres">
                <s:param name="pageNumber">0</s:param>
              </s:url>
              <s:a href="%{livres}">
                <i class="glyphicon glyphicon-list"></i> <span><s:text name="admin.listLivre"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addOeuvre" namespace="/logged" action="ajoutOeuvre"/>
              <s:a href="%{addOeuvre}">
                <i class="glyphicon glyphicon-plus-sign"></i><span><s:text name="admin.addLivre"/></span>
              </s:a>
            </li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="glyphicon glyphicon-user"></i> <span><s:text name="admin.membres"/></span>
          </a>
          <ul class="treeview-menu">
            <li>
              <s:url id="Membres" namespace="/logged" action="listeMembres">
                <s:param name="pageNumberM">0</s:param>
              </s:url>
              <s:a href="%{Membres}">
                <i class="glyphicon glyphicon-list"></i><span><s:text name="admin.listMembre"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addMembre" namespace="/logged" action="ajoutMembres"/>
              <s:a href="%{addMembre}">
                <i class="glyphicon glyphicon-plus-sign"></i><span><s:text name="admin.addMembre"/></span>
              </s:a>
            </li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="glyphicon glyphicon-send"></i> <span><s:text name="admin.emprunts"/></span>
          </a>
          <ul class="treeview-menu">
            <li>
              <s:url id="User" namespace="/logged" action="listeEmprunts">
                <s:param name="pageNumberE">0</s:param>
              </s:url>
              <s:a href="%{User}">
                <i class="glyphicon glyphicon-list"></i><span><s:text name="admin.listEmprunt"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addEmprunt" namespace="/logged" action="ajoutEmprunts"/>
              <s:a href="%{addEmprunt}">
                <i class="glyphicon glyphicon-plus-sign"></i><span><s:text name="admin.addEmprunt"/></span>
              </s:a>
            </li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>