package servlet;

import model.KurveLinje;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FyldKurv", urlPatterns = {"/fyldkurv"})
public class FyldKurv extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String destination = "/bestil";
        String source = request.getParameter("source");

        response.setContentType("text/html;charset=UTF-8");

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
                    HttpSession session = request.getSession();
                    ArrayList<KurveLinje> kurv = new ArrayList<>();
                    kurv = (ArrayList) session.getAttribute("kurvKey");

                    if (kurv == null){
                        kurv = new ArrayList<KurveLinje>();
                        kurv.add(kurveLinje);
                        session.setAttribute("kurvKey", kurv);
                    } else {
                        kurv.add(kurveLinje);
                        session.setAttribute("kurvKey", kurv);
                    }

                } else {
                    request.setAttribute("status", "error");
                    request.setAttribute("message",
                            String.format("Du mangler at vælge bund, top eller antal!",
                                    bottom, top, number));
                }
                destination = "/bestil";
                break;

        }

        request.getServletContext().getRequestDispatcher(destination).forward(request, response);

    }

}
