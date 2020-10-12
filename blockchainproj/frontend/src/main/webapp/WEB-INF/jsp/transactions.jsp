<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>
    <h1>Transactions:</h1>

    <table class="table">
      <thead>
        <tr>
          <th scope="col">transaction ID</th>
          <th scope="col">walletId</th>
          <th scope="col">receiverId</th>
          <th scope="col">value</th>
          <th scope="col">status</th>
        </tr>
      </thead>
      <tbody>
    <c:forEach items="${transactions}" var="transaction">
        <tr>
          <th scope="row">${transaction.id}</th>
          <td>${transaction.walletId}</td>
          <td>${transaction.receiverId}</td>
          <td>${transaction.value}</td>
          <td>${transaction.status}</td>
        </tr>
    </c:forEach>
      </tbody>

    </table>


<jsp:include page="footer.jsp"/>