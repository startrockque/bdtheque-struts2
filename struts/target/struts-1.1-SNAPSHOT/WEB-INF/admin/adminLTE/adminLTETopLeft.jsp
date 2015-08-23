<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

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
  <link href="../../../css/AdminLTE.css" rel="stylesheet" type="text/css" />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->

  <script>
    function verifOeuvre(){
      document.formulaire.btn.disabled = document.formulaire.titre.value == "" || document.formulaire.auteur.value == "";
    }

    function verifUser() {
      document.formulaire.btn.disabled = document.formulaire.nom.value == "" || document.formulaire.prenom.value == "" || document.formulaire.mail.value == "" || document.formulaire.tel.value == "" || document.formulaire.chambre.value == 0;
    }

    function verifEmprunt() {
      document.formulaire.btn.disabled = document.formulaire.titre.value == "" || document.formulaire.nom.value == "";
    }
  </script>


  <sb:head/>
  <sx:head />
</head>
<body class="skin-black" >
<!-- header logo: style can be found in header.less -->
<header class="header">
  <s:url id="accueil" namespace="/" action="acceuilConnecte"/>
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
              <s:url id="livres" namespace="/" action="listeOeuvres">
                <s:param name="pageNumber">0</s:param>
              </s:url>
              <s:a href="%{livres}">
                <i class="glyphicon glyphicon-list"></i> <span><s:text name="admin.listLivre"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addOeuvre" namespace="/" action="toAjoutOeuvre"/>
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
              <s:url id="Membres" namespace="/" action="listeMembres">
                <s:param name="pageNumber">0</s:param>
              </s:url>
              <s:a href="%{Membres}">
                <i class="glyphicon glyphicon-list"></i><span><s:text name="admin.listMembre"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addMembre" namespace="/" action="ajoutMembres"/>
              <s:a href="%{addMembre}">
                <i class="glyphicon glyphicon-plus-sign"></i><span><s:text name="admin.addUser"/></span>
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
              <s:url id="emprunts" namespace="/" action="listeEmprunts">
                <s:param name="pageNumber">0</s:param>
              </s:url>
              <s:a href="%{emprunts}">
                <i class="glyphicon glyphicon-list"></i><span><s:text name="admin.listEmprunt"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addEmprunt" namespace="/" action="toAjoutEmprunts"/>
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