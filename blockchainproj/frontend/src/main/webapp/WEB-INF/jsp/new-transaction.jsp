<jsp:include page="header.jsp"/>

<form action="/frontend/wallets/${walletId}/new-transaction.html" method="post">

    <h2>creating new transaction:</h2>
    <div class="form-group">
        <label for="formGroupExampleInput1">receiverId</label>
        <input type="text" class="form-control" name="receiverId" id="formGroupExampleInput1" placeholder="receiverID">
    </div>
    <div class="form-group">
        <label for="formGroupExampleInput2">value</label>
        <input type="text" class="form-control" name="value" id="formGroupExampleInput2" placeholder="value">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<jsp:include page="footer.jsp"/>


