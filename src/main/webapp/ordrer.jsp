<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<%@ include file = "include/header.jsp" %>
<div class="container bg-white pt-4">
    <div class="row">
        <div class="col text-center">
            <h2>Kunder</h2>
            </br>
        </div>
    </div>
    <div class="row">
        <div class="col-1 text-center">
        </div>
        <div class="col-10">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Ordre ID</th>
                    <th scope="col">Tidspunkt</th>
                    <th scope="col">Kunde ID</th>
                    <th scope="col">Total sum</th>
                </tr>
                </thead>
                <!--<form action="/kunder" method="get">-->
                <tbody>
                <%
                    List<Order> ordreList = (List<Order>) request.getAttribute("ordrelisten");
                    //for (int i = 0; i < ordreList.size() ; i++) {
                    for (int i = ordreList.size()-1; i >= 0; i--) {
                        String ordretable = "";

                        String printOrdreID = Integer.toString(ordreList.get(i).getOrdreID());
                        String printOrdreTime = ordreList.get(i).getTimeNow();
                        String printOrdreBrugerID = Integer.toString(ordreList.get(i).getBrugerID());
                        String printOrdreTotal = Integer.toString(ordreList.get(i).getTotalSum());

                        ordretable = "<tr>" +
                                          "<th>_printOrdreID_</th>" +
                                          "<td>_printOrdreTime_</td><td>_printOrdreBrugerID_</td>" +
                                          "<td>_printOrdreTotal_</td>" +
                                     "</tr>";
                        ordretable = ordretable.replace("_printOrdreID_",printOrdreID);
                        ordretable = ordretable.replace("_printOrdreTime_",printOrdreTime);
                        ordretable = ordretable.replace("_printOrdreBrugerID_",printOrdreBrugerID);
                        ordretable = ordretable.replace("_printOrdreTotal_",printOrdreTotal);
                        out.println(ordretable);
                    }
                %>
                </tbody>
                </form>
            </table>
        </div>
        <div class="col-1 text-center">
        </div>
    </div>
    </br>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>