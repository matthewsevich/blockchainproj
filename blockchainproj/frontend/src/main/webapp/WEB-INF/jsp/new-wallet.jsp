<jsp:include page="header.jsp"/>
    <h1>New wallet:</h1>

<form action="/frontend/new-wallet.html" method="post">
   <div class="form-group">
      <label for="formGroupExampleInput2">secret key</label>
      <input type="text" class="form-control" name="secretKey" id="formGroupExampleInput2" placeholder="secret key">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<jsp:include page="footer.jsp"/>