<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header.jsp"/>

<body>
   <sec:authorize access="hasRole('USER')">
      <a>HELLO <b><sec:authentication property="principal.username"/></b>!</a>
    </sec:authorize>

    <div class="content">
    homepage

    homepage ${homepage}
    </div>
  </body>

<jsp:include page="footer.jsp"/>