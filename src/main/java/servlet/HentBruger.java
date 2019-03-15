package servlet;


import datamappers.BrugerMapper;
import datamappers.OrderMapper;
import datamappers.TopBundMapper;
import model.Bruger;
import model.Order;
import model.TopCake;
import model.BundCake;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HentBruger",urlPatterns = {"/kunder"})
public class HentBruger extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Bruger> brugerList = BrugerMapper.readBrugers();

        request.setAttribute("brugerlisten",brugerList); //sender nøgle med object

        int kundeordrer;

        if (request.getParameter("kundeordrer") != null) {
            kundeordrer = Integer.valueOf(request.getParameter("kundeordrer"));

            for (int i = 0; i < OrderMapper.loadOrder().size(); i++) {
                if (kundeordrer == OrderMapper.loadOrder().get(i).getBrugerID()) {
                    request.setAttribute("kundeordrer",kundeordrer);
                    request.getServletContext().getRequestDispatcher("/kundeordrer.jsp").forward(request,response);
                }
            }
        }

      //  request.setAttribute("kundeordrer",kundeordrer);


        request.getServletContext().getRequestDispatcher("/kunder.jsp").forward(request,response);
    }
}
