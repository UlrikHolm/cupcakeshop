package servlet;

import datamappers.BrugerMapper;
import datamappers.OrderMapper;
import datamappers.TopBundMapper;
import model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "FyldKurv", urlPatterns = {"/fyldkurv"})
public class FyldKurv extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String destination = "/bestil";
        String source = request.getParameter("source");

        response.setContentType("text/html;charset=UTF-8");

        HashMap<Integer, BundCake> bundTabel = TopBundMapper.readBundsHash();
        HashMap<Integer, TopCake> topTabel = TopBundMapper.readTopsHash();

        HttpSession session = request.getSession();

        switch (source) {
            case "addtocart":
                String bottom = request.getParameter("bottom");
                String top = request.getParameter("top");
                String number = request.getParameter("number");
                System.out.println(bottom + "-" + top + "-" + number);
                if (top == null){
                    System.out.println("top er tom");
                }
                if (bottom != null && top != null && number != null) {
                    request.setAttribute("status", "ok");
                    request.setAttribute("message",
                            String.format("Bund: %s, Top: %s, Antal: %s er nu lagt i kurven",
                                    bottom, top, number));
                    KurveLinje kurveLinje = new KurveLinje();
                    kurveLinje.setBundID(Integer.parseInt(bottom));
                    kurveLinje.setTopID(Integer.parseInt(top));
                    kurveLinje.setAntal(Integer.parseInt(number));
                    ArrayList<KurveLinje> kurv = new ArrayList<>();
                    kurv = (ArrayList) session.getAttribute("kurvKey");
                    String bottomNavn = bundTabel.get(Integer.valueOf(bottom)).getNavnBund();
                    String topNavn = topTabel.get(Integer.valueOf(top)).getNavnTop();

                    if (kurv == null){
                        kurv = new ArrayList<KurveLinje>();
                        kurv.add(kurveLinje);
                        session.setAttribute("kurvKey", kurv);
                        request.setAttribute("status","ok");
                        request.setAttribute("message",
                                String.format("Bund: %s, Top: %s, Antal: %s er nu lagt i kurven",
                                        bottomNavn, topNavn, number));
                    } else {
                        kurv.add(kurveLinje);
                        session.setAttribute("kurvKey", kurv);
                        request.setAttribute("status","ok");
                        request.setAttribute("message",
                                String.format("Bund: %s, Top: %s, Antal: %s er nu lagt i kurven",
                                        bottomNavn, topNavn, number));
                    }

                } else {
                    request.setAttribute("status", "error");
                    request.setAttribute("message",
                            String.format("Du mangler at vælge bund, top eller antal!",
                                    bottom, top, number));
                }
                destination = "/bestil";
                break;

            case "betal":

                int bundPris = 0;
                int topPris = 0;
                int prisStk = 0;
                int prisIalt = 0;
                int totalSum = 0;
                String bundNavn = "";
                String topNavn = "";

                if (session.getAttribute("loggedin") != null) {
                    if (!(Boolean) session.getAttribute("loggedin")) {
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }

                Bruger tempBruger = (Bruger) session.getAttribute("brugerData");
                ArrayList<KurveLinje> tempKurv = (ArrayList) session.getAttribute("kurvKey");

                for (int i = 0; i < tempKurv.size(); i++) {
                    System.out.println(tempKurv.get(i).toString());
                }

                LocalDateTime tidspunkt = LocalDateTime.now();
                String timeNow = tidspunkt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                session.setAttribute("TimeNow",timeNow);


                for (int i = 0; i < tempKurv.size(); i++) {
                    for (int j : bundTabel.keySet()) {
                        if (j == tempKurv.get(i).getBundID()) {
                            bundNavn = bundTabel.get(j).getNavnBund();
                            bundPris = bundTabel.get(j).getPrisBund();
                        }
                    }
                    for (int j : topTabel.keySet()) {
                        if (j == tempKurv.get(i).getTopID()) {
                            topNavn = topTabel.get(j).getNavnTop();
                            topPris = topTabel.get(j).getPrisTop();
                        }
                    }
                    prisStk = bundTabel.get(tempKurv.get(i).getBundID()).getPrisBund()
                            + topTabel.get(tempKurv.get(i).getTopID()).getPrisTop();
                    prisIalt = prisStk*tempKurv.get(i).getAntal();
                    totalSum=totalSum+prisIalt;

                    System.out.println( bundNavn + " " + topNavn + " " + tempKurv.get(i).getAntal() +
                            " " + prisStk + " " + prisIalt);

                }

                System.out.println("totalSum = " + totalSum);
                request.setAttribute("totalSum",totalSum);

                int tempSaldo = tempBruger.getSaldo();
                int nySaldo = tempSaldo-totalSum;
                if (nySaldo>=0) {

                    tempBruger.setSaldo(nySaldo);
                    BrugerMapper.udskiftSaldo(tempBruger);
                    session.setAttribute("brugerData",tempBruger);

                    Order tempOrder = new Order(timeNow, tempBruger.getBrugerID(), totalSum);
                    System.out.println(tempOrder.getTimeNow()+"  "+tempOrder.getBrugerID());
                    OrderMapper.createOrder(tempOrder);

                    Order lastOrder = OrderMapper.lastOrder();
                    response.getWriter().println("Order " + lastOrder.getOrdreID()
                            +" "+lastOrder.getTimeNow()+" "+lastOrder.getBrugerID()
                            +" "+lastOrder.getTotalSum());

                    int ordreID = lastOrder.getOrdreID();
                    for (int i = 0; i <tempKurv.size() ; i++) {

                        prisStk = bundTabel.get(tempKurv.get(i).getBundID()).getPrisBund()
                                + topTabel.get(tempKurv.get(i).getTopID()).getPrisTop();
                        prisIalt = prisStk*tempKurv.get(i).getAntal();

                        OrderLinje tempOrderLinje = new OrderLinje (ordreID, tempKurv.get(i).getTopID(),
                                tempKurv.get(i).getBundID(),
                                tempKurv.get(i).getAntal(), prisIalt);
                        OrderMapper.createOrderLinje(tempOrderLinje);
                    }


                    destination = "/betaling.jsp";

                } else {

                    // Saldo bliver negativ
                    destination = "/neg_saldo.jsp";


                }


                break;


        }

        ArrayList<KurveLinje> tempKurv = (ArrayList) session.getAttribute("kurvKey");


        //kurv = (ArrayList) session.getAttribute("kurvKey");

        for (int i = 0; i < tempKurv.size(); i++) {

            if (source.equals(Integer.toString(i))) {
                response.getWriter().println("i = " + i);
                tempKurv.remove(i);

                request.getRequestDispatcher("/kurv.jsp").forward(request, response);
            }
        }

        request.getServletContext().getRequestDispatcher(destination).forward(request, response);

    }

}

// GAMMEL KODE

/*
    Bruger testBruger = new Bruger(1, "email", "1234",300);
                    BrugerMapper.createBruger(testBruger);
                            List<Bruger> tempListBruger=BrugerMapper.readBrugers();

        for (int i = 0; i <tempListBruger.size() ; i++) {
        System.out.println(tempListBruger.get(i).toString());

        }*/
