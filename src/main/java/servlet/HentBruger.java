package servlet;


import datamappers.BrugerMapper;
import datamappers.TopBundMapper;
import model.Bruger;
import model.TopCake;
import model.BundCake;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HentBruger",urlPatterns = {"/kunder"})
public class HentBruger extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Bruger> brugerList = BrugerMapper.readBrugers();

        request.setAttribute("brugerlisten",brugerList); //sender nøgle med object

        request.getServletContext().getRequestDispatcher("/kunder.jsp").forward(request,response);
    }
}
