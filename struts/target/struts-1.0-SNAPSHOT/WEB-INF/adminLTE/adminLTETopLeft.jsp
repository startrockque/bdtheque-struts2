<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <title><s:text name="admin.titre"/></title>

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

  <script src="./JS/verifTitre.js" type="text/javascript"></script>
  <script>
    function verifTitre()
    {
      document.formulaire.btnCreer.disabled = document.formulaire.qcmTitre.value == "";
    }

    function verifTitreThema()
    {
      document.formThema.btnCreer.disabled = document.formThema.titreThematique.value == "";
    }

    function verifQues(){
      if(document.formQues.ques.value != "" && document.formQues.rep1.value != "" && document.formQues.rep2.value != "" && document.formQues.rep3.value != "" && document.formQues.rep4.value != ""){
        document.formQues.sub.disabled = !(document.getElementById("chk1").checked == true || document.getElementById("chk2").checked == true || document.getElementById("chk3").checked == true || document.getElementById("chk4").checked == true);
      } else {
        document.formQues.sub.disabled = true;
      }
    }

    function verifUser() {
      document.formUser.btn.disabled = document.formUser.nomUser.value == "" || document.formUser.mdpUser.value == "";
    }

    function verifSearch(){
      document.formSearch.sub.disabled = document.formSearch.search.value.length < 3;
    }

    function verifSearch2(){
      document.formSearch2.sub2.disabled = document.formSearch2.search2.value.length < 3;
    }

    function verifSearch3(){
      document.formSearch3.sub3.disabled = document.formSearch3.search3.value.length < 3;
    }
  </script>

  <sb:head/>
</head>
<body class="skin-black" >
<!-- header logo: style can be found in header.less -->
<header class="header">
  <s:url id="accueil" namespace="/admin" action="accueil"/>
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
            <i class="glyphicon glyphicon-cog"></i> <span><s:text name="admin.Qcm"/></span>
          </a>
          <ul class="treeview-menu">
            <li>
              <s:url id="Qcm" namespace="/admin" action="listeQcm">
                <s:param name="pageNumber">0</s:param>
              </s:url>
              <s:a href="%{Qcm}">
                <i class="glyphicon glyphicon-list"></i> <span><s:text name="admin.listeQcm"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addQcm" namespace="/admin" action="ajoutQcm"/>
              <s:a href="%{addQcm}">
                <i class="glyphicon glyphicon-plus-sign"></i><span><s:text name="admin.addQcm"/></span>
              </s:a>
            </li>
            <li>
              <s:form name="formSearch" id="formSearch" action="searchQcm" theme="bootstrap" cssClass="sidebar-form">
                <s:div cssClass="input-group">
                  <s:textfield name="qcmTitre" id="search" value="" cssClass="form-control" placeholder="Titre du QCM" onkeyup="verifSearch()"/>
                    <span class="input-group-btn">
                      <button name="sub" id="sub" type="submit" class="btn btn-flat" disabled="disabled"><span class="glyphicon glyphicon-search"></span></button>
                    </span>
                </s:div>
              </s:form>
            </li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="glyphicon glyphicon-cog"></i> <span><s:text name="admin.thematiques"/></span>
          </a>
          <ul class="treeview-menu">
            <li>
              <s:url id="Them" namespace="/admin" action="listeThem">
                <s:param name="pageNumberT">0</s:param>
              </s:url>
              <s:a href="%{Them}">
                <i class="glyphicon glyphicon-list"></i><span><s:text name="admin.listeThe"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addQcm" namespace="/admin" action="ajoutThem"/>
              <s:a href="%{addQcm}">
                <i class="glyphicon glyphicon-plus-sign"></i><span><s:text name="admin.addThem"/></span>
              </s:a>
            </li>
            <li>
              <s:form name="formSearch2" id="formSearch2" action="searchThema" theme="bootstrap" cssClass="sidebar-form">
                <s:div cssClass="input-group">
                  <s:textfield name="titreThematique" id="search2" value="" cssClass="form-control" placeholder="Titre thematique" onkeyup="verifSearch2()"/>
                    <span class="input-group-btn">
                      <button name="sub" id="sub2" type="submit" class="btn btn-flat" disabled="disabled"><span class="glyphicon glyphicon-search"></span></button>
                    </span>
                </s:div>
              </s:form>
            </li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="glyphicon glyphicon-cog"></i> <span><s:text name="admin.user"/></span>
          </a>
          <ul class="treeview-menu">
            <li>
              <s:url id="User" namespace="/admin" action="listeUser">
                <s:param name="pageNumberU">0</s:param>
              </s:url>
              <s:a href="%{User}">
                <i class="glyphicon glyphicon-list"></i><span><s:text name="admin.listeUser"/></span>
              </s:a>
            </li>
            <li>
              <s:url id="addUser" namespace="/admin" action="ajoutUser"/>
              <s:a href="%{addUser}">
                <i class="glyphicon glyphicon-plus-sign"></i><span><s:text name="admin.addUser"/></span>
              </s:a>
            </li>
            <li>
              <s:form name="formSearch3" id="formSearch3" action="searchUser" theme="bootstrap" cssClass="sidebar-form">
                <s:div cssClass="input-group">
                  <s:textfield name="nomUser" id="search3" value="" cssClass="form-control" placeholder="Nom a chercher" onkeyup="verifSearch3()"/>
                    <span class="input-group-btn">
                      <button name="sub3" id="sub3" type="submit" class="btn btn-flat" disabled="disabled"><span class="glyphicon glyphicon-search"></span></button>
                    </span>
                </s:div>
              </s:form>
            </li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>