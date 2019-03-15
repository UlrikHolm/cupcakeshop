package servlet;

import datamappers.OrderMapper;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HentKundeOrdrer", urlPatterns = {"/kundeordrer"})
public class HentKundeOrdrer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int kundeordrer = Integer.valueOf(request.getParameter("kundeordrer")) ;

        for (int i = 0; i < OrderMapper.loadOrder().size(); i++) {
            if (OrderMapper.loadOrder().get(i).getBrugerID() == kundeordrer){
                System.out.println(OrderMapper.loadOrder().get(i).getTimeNow());
            }
        }




        // request.setAttribute("kundeordrelisten",kundeOrdrerList); //sender nøgle med object

        request.getServletContext().getRequestDispatcher("/kundeordrer.jsp").forward(request,response);
    }
}
