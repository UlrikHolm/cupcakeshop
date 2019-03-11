package servlet;

import datamappers.BrugerMapper;
import datamappers.OrdrerMapper;
import model.Bruger;
import model.Ordre;

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

        List<Ordre> ordreList = OrdrerMapper.readOrdre();

        request.setAttribute("ordrelisten",ordreList); //sender nøgle med object

        request.getServletContext().getRequestDispatcher("/ordrer.jsp").forward(request,response);

    }
}
