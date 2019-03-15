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

@WebServlet(name = "HentLogin",urlPatterns = {"/HentLogin"})
public class HentLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Bruger> brugerList = BrugerMapper.readBrugers();

        String username = request.getParameter("email");
        String password = request.getParameter("kodeord");

        Boolean ok = false;
        Bruger bruger = new Bruger();
        int brugerType = 1;

        for (int i = 0; i <brugerList.size() ; i++) {
            if ((username.equals(brugerList.get(i).getEmail())
                    &&(password.equals(brugerList.get(i).getKodeord())))){
                ok = true;
                bruger = brugerList.get(i);
                brugerType = brugerList.get(i).getBrugerType();
            }
        }

        if (ok == true) {

            HttpSession session = request.getSession();
            session.setAttribute("brugerData",bruger);
            session.setAttribute("brugerType", brugerType);
            session.setAttribute("loggedin",ok);
            request.setAttribute("loginstatus","loginfindes");
            request.setAttribute("message","Du er nu logget ind.");
            request.getRequestDispatcher("/bestil").forward(request, response);

        } else  {
            request.setAttribute("loginstatus","error");
            request.setAttribute("message","Det login findes ikke.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
}
