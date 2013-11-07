<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Big Up - Administration</title>

    <!-- Bootstrap core CSS -->
    <link href="/includes/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/includes/css/navbar-fixed-top.css" rel="stylesheet">
    <link href="/includes/css/style.css" rel="stylesheet">
    
	<script src="/includes/js/jquery.js"></script>
	<script src="/includes/js/jquery.dataTables.min.js"></script>
	<script src="/includes/js/bootstrap.min.js"></script>
	<script src="/includes/js/equipes.js"></script>

  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Big Up</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/admin/">Home</a></li>
            <li><a href="/admin/adherents/">Adhérents</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipes <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="/admin/equipes/masculines/">Masculines</a></li>
                <li><a href="/admin/equipes/feminines/">Féminines</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <!-- <li><a href="">Default</a></li> -->
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">




