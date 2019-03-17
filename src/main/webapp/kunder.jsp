<%@ page import="model.Bruger" %>
<%@ page import="java.util.List" %>
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
                    <th scope="col">Kunde ID</th>
                    <th scope="col">Kunde</th>
                    <th scope="col">Saldo</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <form action="/kunder" method="get">
                <tbody>
                <%
                    List<Bruger> brugerList = (List<Bruger>) request.getAttribute("brugerlisten");
                    //for (int i = brugerList.size()-1;i >= 0; i--) {
                    for (int i = 0; i < brugerList.size() ; i++) {
                        String brugerth = "";

                        String printBrugerID = Integer.toString(brugerList.get(i).getBrugerID());
                        String printBrugerEmail = brugerList.get(i).getEmail();
                        String printBrugerSaldo =  Integer.toString(brugerList.get(i).getSaldo());

                        brugerth = "<tr>" +
                                "<th>_printBrugerID_</th>" +
                                "<td> _printBrugerEmail_</td>" +
                                "<td>_printBrugerSaldo_</td>" +
                                "<td>" + "" +
                                "<form action=\"/kunder\" method=\"get\">"+"" +
                                "<input type=\"hidden\" name=\"kundeordrer\" value=\"" + printBrugerID + "\"/>" +
                                "<button type=\"submit\" class=\"btn btn-success \">Ordrer</button>\n" +
                                "</form>";
                                 brugerth = brugerth.replace("_printBrugerID_",printBrugerID);
                                 brugerth = brugerth.replace("_printBrugerEmail_",printBrugerEmail);
                                 brugerth = brugerth.replace("_printBrugerSaldo_",printBrugerSaldo);
                                 out.println(brugerth);
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