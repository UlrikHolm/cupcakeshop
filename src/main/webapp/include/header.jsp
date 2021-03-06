<%@ page import="model.Bruger" %>
<%@ page import="datamappers.BrugerMapper" %>
<%@ page import="com.sun.org.apache.bcel.internal.generic.BREAKPOINT" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Olsker Cupcakes</title>
    <link rel="icon" href="img/cupcake.ico" type="image/gif" sizes="16x16">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
<div class="container text-center" id="banner">
    <img id="logo-main" src="img/logo.png" width="146">
</div>
<div class="container" id="navbar">
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <a href="index.jsp" class="navbar-brand"><img src="img/cupcake.svg" width="30" height="30" class="d-inline-block align-top" alt="" style="opacity: 0.5"></a>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a href="index.jsp" class="nav-link ">Forside</a>
                </li>
                <li class="nav-item">
                    <a href="bestil" class="nav-link ">Bestil</a>
                </li>
                <%
                    int brugerType = 0;
                    if(session.getAttribute("brugerType") != null) {
                        brugerType = (int) session.getAttribute("brugerType");
                        //System.out.println(brugerType);
                    }
                    if(brugerType == 2){
                        out.print(
                        "<li class=\"nav-item\">" +
                             " <a href=\"ordrer\" class=\"nav-link\">Ordrer </a> " +
                        "</li>"+
                        "<li class=\"nav-item\">"+
                             "<a href=\"kunder\" class=\"nav-link\">Kunder</a>"+
                        "</li>"
                        );
                    }
                %>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                    <%
                        boolean loggedin = false;
                        if (session.getAttribute("loggedin") != null){
                        loggedin = (boolean) session.getAttribute("loggedin");
                        }

                        Bruger brugerData = (Bruger)session.getAttribute("brugerData");
                        String brugerInfo = "";
                        String brugerSaldo = "";

                        if (loggedin){
                            brugerSaldo = Integer.toString(brugerData.getSaldo());
/*
                            for (int i = 0; i < BrugerMapper.readBrugers().size(); i++) {
                                if (BrugerMapper.readBrugers().get(i).getBrugerID() == brugerData.getBrugerID()){
                                    System.out.println(i);
                                    brugerSaldo = Integer.toString(BrugerMapper.readBrugers().get(i).getSaldo());

                                }
                            }

*/

                        }


                        if(loggedin == true){
                            brugerInfo = "<li class=\"nav-item dropdown\">\n" +
                                    "<a href=\"login.jsp\" class=\"nav-link dropdown-toggle\" data-toggle=\"dropdown\">\n" +
                                    "<span class=\"fas fa-user\"></span> " + brugerData.getEmail() +"</a>" +
                             "<div class=\"dropdown-menu\">\n" +
                            "<a class=\"dropdown-item\" href=\"http://localhost:8080\">Log ud</a></div>" +
                             "</li>" +
                            "<li class=\"nav-item\">" +
                             "<a href=\"#\" class=\"nav-link\">"+
                             "<span class=\"fas fa-coins\"></span> Saldo: " + brugerSaldo +
                            "</a></li>";
                            out.print(brugerInfo);
                        } else {
                            out.print("<li class=\"nav-item\"><a href=\"login.jsp\" class=\"nav-link\">\n" +
                            "<span class=\"fas fa-user\"></span>" +
                            " Log Ind<a/>" +
                             "</li>");
                        }

                    %>
                <a href="kurv.jsp" class="nav-link">
                    <span class="fas fa-shopping-basket"></span>
                </a>
            </ul>
        </div>
    </nav>
</div>