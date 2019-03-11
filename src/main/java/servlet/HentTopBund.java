package servlet;


import datamappers.TopBundMapper;
import model.TopCake;
import model.BundCake;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HentTopBund",urlPatterns = {"/bestil"})
public class HentTopBund extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<TopCake> topList = TopBundMapper.readTops();

        request.setAttribute("toplisten", topList); //sender nøgle med object


        List<BundCake> bundList = TopBundMapper.readBunds();

        request.setAttribute("bundlisten", bundList); //sender nøgle med object

        request.getServletContext().getRequestDispatcher("/bestil.jsp").forward(request, response);


    }
}

