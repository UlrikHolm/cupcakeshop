package servlet;

import datamappers.BrugerMapper;
import datamappers.OrderMapper;
import datamappers.TopBundMapper;
import model.*;

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
        String item_remove = request.getParameter("item_remove");

        response.setContentType("text/html;charset=UTF-8");

        HashMap<Integer, BundCake> bundTabel = TopBundMapper.readBundsHash();
        HashMap<Integer, TopCake> topTabel = TopBundMapper.readTopsHash();

        HttpSession session = request.getSession();
        int totalSum = 0;

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
                    ArrayList<KurveLinje> kurv = (ArrayList) session.getAttribute("kurvKey");

                    if ((Boolean) session.getAttribute("loggedin")) {
                        Bruger bruger = (Bruger) session.getAttribute("brugerData");
                        kurveLinje.setBrugerID(bruger.getEmail());
                        if (kurv!=null) {
                            for (int i = 0; i <kurv.size() ; i++) {
                                kurv.get(i).setBrugerID(bruger.getEmail());
                            }
                        }
                    }

                    kurveLinje.setBundID(Integer.parseInt(bottom));
                    kurveLinje.setTopID(Integer.parseInt(top));
                    kurveLinje.setAntal(Integer.parseInt(number));
                    kurveLinje.setNavnBund(bundTabel.get(kurveLinje.getBundID()).getNavnBund());
                    kurveLinje.setNavnTop(topTabel.get(kurveLinje.getTopID()).getNavnTop());
                    kurveLinje.setPrisIalt(bundTabel.get(kurveLinje.getBundID()).getPrisBund()+
                                           topTabel.get(kurveLinje.getTopID()).getPrisTop());

                    //totalSum=totalSum + (kurveLinje.getPrisIalt()*kurveLinje.getAntal());
                    //session.setAttribute("totalSum",totalSum);

                    //ArrayList<KurveLinje> kurv = new ArrayList<>();
                    //kurv = (ArrayList) session.getAttribute("kurvKey");
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
                        int maxKurvelinjeID = 0;
                        for (int i = 0; i < kurv.size(); i++) {
                            if (kurv.get(i).getKurvelinjeID() >= maxKurvelinjeID) {
                                maxKurvelinjeID = kurv.get(i).getKurvelinjeID() + 1;
                            }
                        }
                        kurveLinje.setKurvelinjeID(maxKurvelinjeID);
                        kurv.add(kurveLinje);
                        session.setAttribute("kurvKey", kurv);
                        request.setAttribute("status","ok");
                        request.setAttribute("message",
                                String.format("Bund: %s, Top: %s, Antal: %s er nu lagt i kurven",
                                        bottomNavn, topNavn, number));
                    }

                    for (int i = 0; i < kurv.size(); i++) {
                        System.out.println(kurv.get(i).toString());
                    }

                } else {
                    request.setAttribute("status", "error");
                    request.setAttribute("message",
                            String.format("Du mangler at vælge bund, top eller antal!",
                                    bottom, top, number));
                }
                destination = "/bestil";
                break;

            case "fjern":

                ArrayList<KurveLinje> tempKurv2 = (ArrayList) session.getAttribute("kurvKey");


                for (int i = 0; i < tempKurv2.size(); i++) {

                    if (item_remove.equals(Integer.toString(i))) {
                        response.getWriter().println("i = " + i);
                        tempKurv2.remove(i);

                        request.getRequestDispatcher("/kurv.jsp").forward(request, response);
                    }
                }

                break;

            case "betal":

                if (session.getAttribute("loggedin") != null) {
                    if (!(Boolean) session.getAttribute("loggedin")) {
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }

                Bruger tempBruger = (Bruger) session.getAttribute("brugerData");
                ArrayList<KurveLinje> tempKurv = (ArrayList) session.getAttribute("kurvKey");


                LocalDateTime tidspunkt = LocalDateTime.now();
                String timeNow = tidspunkt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                session.setAttribute("TimeNow",timeNow);


                for (int i = 0; i <tempKurv.size() ; i++) {
                    totalSum = totalSum + tempKurv.get(i).getPrisIalt()*tempKurv.get(i).getAntal();
                }

                System.out.println("totalSum = "+totalSum);
                session.setAttribute("totalSum",totalSum);
                int tempSaldo = tempBruger.getSaldo();
                int nySaldo = tempSaldo-totalSum;
                if (nySaldo>=0) {

                    tempBruger.setSaldo(nySaldo);
                    ArrayList<OrderLinje> kurvOrderLinjer = new ArrayList<>();

                    for (int i = 0; i <tempKurv.size() ; i++) {
                        //kurvOrderLinjer.get(i).setTopID(tempKurv.get(i).getTopID());
                        //kurvOrderLinjer.get(i).setBundID(tempKurv.get(i).getBundID());
                        //kurvOrderLinjer.get(i).setAntal(tempKurv.get(i).getAntal());
                        //kurvOrderLinjer.get(i).setPrisIalt(tempKurv.get(i).getPrisIalt());

                        kurvOrderLinjer.add(new OrderLinje(tempKurv.get(i).getTopID(),
                                                           tempKurv.get(i).getBundID(),
                                                           tempKurv.get(i).getAntal(),
                                                           tempKurv.get(i).getPrisIalt()));
                    }

                    for (int i = 0; i <kurvOrderLinjer.size() ; i++) {
                        System.out.println(kurvOrderLinjer.get(i).toString());
                    }

                    OrderMapper.createOrderAndLines(kurvOrderLinjer,timeNow,tempBruger,totalSum);
                    ArrayList<KurveLinje> tempkurv = null;
                    session.setAttribute("kurvKey", tempkurv);


                    destination = "/betaling.jsp";

                } else {
                    // Saldo bliver negativ
                    destination = "/neg_saldo.jsp";
                }

                break;
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
