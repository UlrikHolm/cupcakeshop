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

@WebServlet(name = "HentOrdrer", urlPatterns = {"/ordrer"})
public class HentOrdrer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> ordreList = OrderMapper.loadOrder();
        for (int i = 0; i < ordreList.size() ; i++) {
            System.out.println(OrderMapper.loadOrder().get(i).getOrdreID() + " " + OrderMapper.loadOrder().get(i).getTimeNow() +
                    " " + OrderMapper.loadOrder().get(i).getBrugerID() + " " + OrderMapper.loadOrder().get(i).getTotalSum());

        }

        request.setAttribute("ordrelisten",ordreList); //sender nøgle med object

        request.getServletContext().getRequestDispatcher("/ordrer.jsp").forward(request,response);

    }
}
