<%@ page import="java.util.ArrayList" %>
<%@ page import="model.KurveLinje" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.BundCake" %>
<%@ page import="model.TopCake" %>
<%@ page import="datamappers.TopBundMapper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<%@ include file = "include/header.jsp" %>
<div class="container bg-white pt-4">
    <div class="row">
        <div class="col text-center">
            <h2>Kurv</h2>
            </br>
        </div>
    </div>
    <div class="row">
        <div class="col-1 text-center">
        </div>
        <div class="col-10 text-center">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Bund</th>
                    <th scope="col">Topping</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Pris</th>
                    <th scope="col">I alt</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                    HashMap<Integer, BundCake> bundTabel = TopBundMapper.readBundsHash();
                    HashMap<Integer, TopCake> topTabel = TopBundMapper.readTopsHash();
                    String bundNavn = "";
                    String topNavn = "";
                    int bundPris = 0;
                    int topPris = 0;
                    int totalPris = 0;
                    ArrayList<KurveLinje> kurv = (ArrayList) session.getAttribute("kurvKey");
                    if (kurv != null) {
                        if (kurv.size() > 0){
                        for (int i = 0; i < kurv.size(); i++) {
                            for (int j : bundTabel.keySet()) {
                                if (j == kurv.get(i).getBundID()) {
                                    bundNavn = bundTabel.get(j).getNavnBund();
                                    bundPris = bundTabel.get(j).getPrisBund();
                                }
                            }
                            for (int j : topTabel.keySet()) {
                                if (j == kurv.get(i).getTopID()) {
                                    topNavn = topTabel.get(j).getNavnTop();
                                    topPris = topTabel.get(j).getPrisTop();
                                }
                            }
                            totalPris = totalPris + ((bundPris + topPris) * kurv.get(i).getAntal());
                            out.print(
                                    "<tr>" + " " +
                                            "<td>" + bundNavn + "</td>" +
                                            "<td>" + topNavn + "</td>" +
                                            "<td>" + kurv.get(i).getAntal() + "</td>" +
                                            "<td>" + (bundPris + topPris) + "</td>" +
                                            "<td>" + ((bundPris + topPris) * kurv.get(i).getAntal()) +
                                            "</td>");
                %>
                <td>
                    <form action="/fyldkurv" method="get">
                        <input type="hidden" name="source" value="<%=i%>"/>
                        <button type="submit" class="btn btn-danger btn-block">Fjern</button>
                    </form>
                </td>
                </tr>

                <%
                            }
                        } else {
                            out.print("Kurv.size = 0" +
                                    "<div class=\"alert alert-secondary\" role=\"alert\">\n" +
                                    "Din kurv er tom!\n" +
                                    "</div>");
                            }
                    } else {
                        out.print("kurv = null" +
                                "<div class=\"alert alert-secondary\" role=\"alert\">\n" +
                                "Din kurv er tom!\n" +
                                "</div>");
                    }
                %>
                <tr>
                    <td><b>Total</b></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><%=totalPris%></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-1 text-center">
        </div>
    </div>
    <div class="row">
        <div class="col-7">
        </div>
        <div class="col-md-2">
            <a href="bestil" class="btn btn-primary btn-block">Shop videre</a>
        </div>
        <div class="col-md-2">
            <form action="/fyldkurv" method="get">
                <input type="hidden" name="source" value="betal"/>
                <button type="submit" class="btn btn-success btn-block">Betal</button>
            </form>
        </div>
    </div>
    <div class="row pt-4">
    </div>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>