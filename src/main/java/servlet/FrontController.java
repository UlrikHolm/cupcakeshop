package servlet;

import datamappers.BrugerMapper;
import model.Bruger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FrontController",urlPatterns = {"/index.jsp"})
public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean ok = false;
        int brugerType = 1;
        HttpSession session = request.getSession();
        session.setAttribute("brugerType",brugerType);
        session.setAttribute("loggedin",ok);
        request.getRequestDispatcher("/index2.jsp").forward(request, response);

    }
}