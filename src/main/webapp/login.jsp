<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<%@ include file = "include/header.jsp" %>
<div class="container bg-white pt-4">
    <div class="row">
        <div class="col text-center">
            <h2>Log ind</h2>
            </br>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-4 col-md-3 text-center">
        </div>
        <div class="col-lg-4 col-md-6">
            <center><h5>Indtast e-mail og kodeord</h5></center>
            <%  String besked = (String) request.getAttribute("message");
                String status = (String) request.getAttribute("loginstatus");
                if (besked != null && status != null) {
                    String alert = "";
                    if (status.equals("error")) {
                        alert = "<div class=\"alert alert-danger\">_message_</div>";
                    } else if (status.equals("loginfindes") || status.equals("brugerlaves")){
                        alert = "<div class=\"alert alert-success\">_message_</div>";
                    }
                    alert = alert.replace("_message_", besked);
                    out.println(alert);
                }
            %>
            <form method="get" action="HentLogin">
            <div class="input-group mb-2 mr-md-2">
                <div class="input-group-prepend">
                    <div class="input-group-text"><i class="fas fa-envelope"></i></div>
                </div>
                <input type="text" class="form-control" name="email" placeholder="e-mail">
            </div>
            <div class="input-group mb-2 mr-sm-2">
                <div class="input-group-prepend">
                    <div class="input-group-text"><i class="fas fa-lock"></i></div>
                </div>
                <input type="password" class="form-control" name="kodeord" placeholder="kodeord">
            </div>
            <div class="input-group mb-2 mr-sm-2 justify-content-center">
                <input type="submit" name="loginknap" value="log ind" class="btn btn-success btn-block"/>
            </div>
            <div class="input-group mb-2 mr-sm-2 justify-content-center">
                <a href="opret.jsp">Opret konto</a>
            </div>
            </form>
        </div>
        <div class="col-lg-4 col-md-3 text-center">
        </div>
    </div>
    </br>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
