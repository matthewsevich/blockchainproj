<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>

    <head>
    <title>new registration with validation</title>
    <style>
    .error
    {
    color:red
    }
    </style>
    </head>
    <body>
    <form:form action="/frontend/new-registration" modelAttribute="appUser">
<table>
<tr>
   <td> Username:     <form:input path="userName"/> </td>
   <td> <form:errors path="userName" cssClass="error"/><br><br></td>
</tr>
<tr>
   <td> phoneNumber:  <form:input path="phoneNumber"/></td>
   <td> <form:errors path="phoneNumber" cssClass="error"/><br><br></td>
</tr>
<tr>
   <td> emailAddress: <form:input path="emailAddress"/></td>
   <td> <form:errors path="emailAddress" cssClass="error"/><br><br></td>
</tr>
<tr>
   <td> Password(*):  <form:password path="userPassword"/></td>
   <td> <form:errors path="userPassword" cssClass="error"/><br><br></td>
</tr>
<tr>
   <td> <button type="submit" class="btn btn-primary">submit</button></td>
</tr>
</table>
    </form:form>
    </body>

<jsp:include page="footer.jsp"/>