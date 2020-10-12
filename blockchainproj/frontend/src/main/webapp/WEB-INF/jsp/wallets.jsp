<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>
    <h1>Wallets:</h1>
<form action="localhost:8082/start/${wallet.walletId}" method="post" enctype="multipart/form-data">

<table class="table">
      <thead>
        <tr>
          <th scope="col">wallet ID</th>
          <th scope="col">secret key</th>
          <th scope="col">  </th>
          <th scope="col">  </th>
          <th scope="col">  </th>
          <th scope="col">  </th>
        </tr>
      </thead>
   <tbody>
        <c:forEach items="${wallets}" var="wallet">
        <tr>
          <th scope="row">${wallet.walletId}</th>
          <td>${wallet.secretKey}</td>
          <td><p><a href="/frontend/wallets/${wallet.walletId}/transactions.html" class="btn btn-primary btn-sm" role="button" aria-pressed="true">history</a></p><td>
          <td><p><a href="/frontend/wallets/${wallet.walletId}/balance.html" class="btn btn-primary btn-sm" role="button" aria-pressed="true">balance</a></p><td>
          <td><p><a href="/frontend/wallets/${wallet.walletId}/new-transaction.html" class="btn btn-primary btn-sm" role="button" aria-pressed="true">send funds</a></p><td>
          <td><p><a href="http://localhost:8082/start/${wallet.walletId}" class="btn btn-primary btn-sm" role="button" aria-pressed="true">mine</a></p><td>
        </tr>
        </c:forEach>
   </tbody>

</table>
</form>

<jsp:include page="footer.jsp"/>