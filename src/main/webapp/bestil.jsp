<%@ page import="model.TopCake" %>
<%@ page import="model.BundCake" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<%@ include file = "include/header.jsp" %>
<div class="container bg-white pt-4">
    <%  String besked = (String) request.getAttribute("message");
        String status = (String) request.getAttribute("status");
        if (besked != null && status != null) {
            String alert = "";
            if (status.equals("ok")) {
                alert = "<div class=\"alert alert-success\">_message_</div>";
            } else {
                alert = "<div class=\"alert alert-danger\">_message_</div>";
            }
            alert = alert.replace("_message_", besked);
            out.println(alert);
        }
    %>
    <div class="row">
        <div class="col text-center">
            <h2>Vælg og bestil her:</h2>
            </br>
        </div>
    </div>
    <form action="/fyldkurv" method="get">
        <input type="hidden" name="source" value="addtocart"/>
    <div class="row">
        <div class="col-md-3 text-center">
            <label>Bund:</label>
            <select class="form-control" name="bottom">
                <option value="0" disabled selected>Vælg bund
                        <%

                    List<BundCake> bundList = (List<BundCake>) request.getAttribute("bundlisten");

                    for (int i = 0; i < bundList.size() ; i++) {
                        String bundOption = "";

                        String printBundID = Integer.toString(bundList.get(i).getBundID());
                        String printBundNavn = bundList.get(i).getNavnBund()+"  "+Integer.toString(bundList.get(i).getPrisBund());

                        bundOption = "<option value=\"_printBundID_\">_printBundNavn_</option>";
                        bundOption = bundOption.replace("_printBundNavn_",printBundNavn);
                        bundOption = bundOption.replace("_printBundID_",printBundID);
                        out.println(bundOption);
                    }
                %>
            </select>
        </div>
        <div class="col-md-3 text-center">
            <label>Topping:</label>
            <select class="form-control" name="top">
                <option value="0" disabled selected>Vælg top
                        <%

                    List<TopCake> topList = (List<TopCake>) request.getAttribute("toplisten");

                    for (int i = 0; i < topList.size() ; i++) {
                        String topOption = "";

                        String printTopID = Integer.toString(topList.get(i).getTopID());
                        String printTopNavn = topList.get(i).getNavnTop()+"  "+Integer.toString(topList.get(i).getPrisTop());
                        //String printTopID = (String) request.getAttribute("topList.get(i).getTopID()");
                        //String printTopNavn = (String) request.getAttribute("topList.get(i).getNavnTop()");


                        topOption = "<option value=\"printTopID\">printTopNavn</option>";
                        topOption = topOption.replace("printTopNavn",printTopNavn);
                        topOption = topOption.replace("printTopID",printTopID);
                        out.println(topOption);

                    }
                %>
            </select>
        </div>
        <div class="col-md-3 text-center">
            <label>Antal:</label>
            <select class="form-control" name="number">
                <option value="0" disabled selected>Vælg antal
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
            </select>
        </div>
        <div class="col-md-3 text-center">
            <label>&zwnj;</label>
            <input type="submit" name="selectcupcake" value="Læg i kurv" class="btn btn-success form-control"/>
        </div>
    </div>
    </form>
    <div class="row pt-4">
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>