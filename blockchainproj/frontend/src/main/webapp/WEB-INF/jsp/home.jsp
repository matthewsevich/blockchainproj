<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header.jsp"/>

<body>
   <sec:authorize access="hasRole('USER')">
      <a>HELLO <b><sec:authentication property="principal.username"/></b>!</a>
    </sec:authorize>

    <div class="content">
    homepage

    homepage ${homepage}

    <strong>
        <div>maximum transaction should be <100</div>
        <div>if blockchain is empty "mine" button will create genesis transaction(5000)</div>
    </strong>
    </div>
  </body>

<jsp:include page="footer.jsp"/>