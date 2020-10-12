<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

  <title>free money!</title>
  </head>
  <body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/frontend">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
     <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
             <a class="nav-link" href="/frontend/home.html">Home <span class="sr-only">(current)</span></a>
   </li>

   <sec:authorize access="isAuthenticated()">
      <li class="nav-item">
            <a class="nav-link" href="/frontend/new-wallet.html">New Wallet</a>
      </li>
      <li class="nav-item">
            <a class="nav-link" href="/frontend/wallets.html">my wallets</a>
      </li>
      <li class="nav-item">
           <a class="nav-link" href="/frontend/logout">Logout</a>
      </li>
   </sec:authorize>
   <sec:authorize access="!isAuthenticated()">
      <li class="nav-item">
                  <a class="nav-link" href="/frontend/login">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/frontend/new-registration">new-registration</a>
      </li>
   </sec:authorize>
    </ul>
    <sec:authorize access="hasRole('USER')">
       <a href="#">Welcome user, <strong><sec:authentication property="principal.username"/></strong>!</a>
    </sec:authorize>
  </div>
</nav>