<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/css/foundation.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HubLille1 | Les annonces</title>
</head>
<body>
	<jsp:include page="bar.jsp" />
	
	  <div class="row">
      <div class="large-12 columns">
        <h1>Hub Lille 1</h1>
      </div>
    </div>
      
      <div class="row">
          <div class="large-6 columns">
      <div class="row collapse postfix-round">
        <div class="small-9 columns">
          <input type="text" placeholder="Value">
        </div>
        <div class="small-3 columns">
          <a href="#" class="button postfix">Go</a>
        </div>
      </div>
    </div>
      </div>
<!-- Annonces -->
        <div class="row">
      <div class="large-8 columns">
              <h4>Les dernieres annonces</h4>
        </div>
         <div class="large-4 columns">
              <a class="small radius button">Deposer une annonce</a>
        </div>
        </div>
      <div class="row">
    <div class="large-10 columns">
      	<ul class="example-orbit" data-orbit>
  <li>
    <img src="${pageContext.request.contextPath}/ressources/img/satelite-orbit.jpg" alt="slide 1" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
  <li class="active">
    <img src="${pageContext.request.contextPath}/ressources/img/andromeda-orbit.jpg" alt="slide 2" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
  <li>
    <img src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg" alt="slide 3" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
             <li>
    <img src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg" alt="slide 4" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
</ul>
      </div>
    </div>
      
      
      
      <!-- Annonces -->
    <div class="row">
      <div class="large-8 columns">
              <h4>Les derniers jobs</h4>
        </div>
         <div class="large-4 columns">
              <a class="small radius button">Deposer un job</a>
        </div>
        </div>
      <div class="row">
    <div class="large-10 columns">
      	<ul class="example-orbit" data-orbit>
  <li>
    <img src="${pageContext.request.contextPath}/ressources/img/satelite-orbit.jpg" alt="slide 1" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
  <li class="active">
    <img src="${pageContext.request.contextPath}/ressources/img/andromeda-orbit.jpg" alt="slide 2" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
  <li>
    <img src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg" alt="slide 3" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
             <li>
    <img src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg" alt="slide 4" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
</ul>
      </div>
    </div>
      
      
      
      <!-- Annonces -->
    <div class="row">
      <div class="large-8 columns">
              <h4>Les derniers evenements</h4>
        </div>
         <div class="large-4 columns">
              <a class="small radius button">Deposer un evenement</a>
        </div>
        </div>
      <div class="row">
    <div class="large-10 columns">
      	<ul class="example-orbit" data-orbit>
  <li>
    <img src="${pageContext.request.contextPath}/ressources/img/satelite-orbit.jpg" alt="slide 1" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
  <li class="active">
    <img src="${pageContext.request.contextPath}/ressources/img/andromeda-orbit.jpg" alt="slide 2" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
  <li>
    <img src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg" alt="slide 3" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
             <li>
    <img src="${pageContext.request.contextPath}/ressources/img/launch-orbit.jpg" alt="slide 4" />
    <div class="orbit-caption">
      [Annonce] Tickets Voyage dans l'espace !
    </div>
  </li>
</ul>
      </div>
    </div>
	<script src="${pageContext.request.contextPath}/ressources/js/vendor/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/ressources/js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
</body>
</html>