package servlet;

import datamappers.BrugerMapper;
import model.Bruger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OpretLogin", urlPatterns = {"/OpretLogin"})
public class OpretLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Bruger> brugerList = BrugerMapper.readBrugers();

        String username = request.getParameter("email");
        String password = request.getParameter("kodeord");
        String password2 = request.getParameter("kodeord2");
        boolean ensKoder = true;
        boolean erEmail = false;
        boolean tomKode = false;

        Bruger bruger = new Bruger();


            if (username.contains("@") && username.contains(".")) {
                System.out.println("der er @ og . i mail");
                erEmail = true;
            } else {
                request.setAttribute("status","ikkemail");
                request.setAttribute("message","Det er ikke en e-mail.");
                request.getRequestDispatcher("/opret.jsp").forward(request, response);
            }

            if (password.isEmpty() || password2.isEmpty()){
                request.setAttribute("status","tomkode");
                request.setAttribute("message","En af kodeordene er tomme!");
                request.getRequestDispatcher("/opret.jsp").forward(request, response);
                System.out.println("den ene eller anden kode er tom");
                tomKode = true;
            }

            if(!password.equals(password2) && tomKode == false){
                System.out.println("kodeord er IKKE ens");
                request.setAttribute("status","ikkeenskoder");
                request.setAttribute("message","Kodeordene er ikke ens!");
                request.getRequestDispatcher("/opret.jsp").forward(request, response);
                ensKoder = false;
            } else if (password.equals(password2) && tomKode == false){
            System.out.println("koder ER ens");
            ensKoder = true;
            }

            if ((ensKoder == true) && (erEmail == true)){
                request.setAttribute("loginstatus","brugerlaves");
                request.setAttribute("message","Du er nu oprettet, og kan logge ind");
                Bruger tempBruger = new Bruger(1,username,password,300);
                BrugerMapper.createBruger(tempBruger);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                System.out.println("bruger laves");
            }


        for (int i = 0; i <brugerList.size() ; i++) {
            if ((username.equals(brugerList.get(i).getEmail()))){
                bruger = brugerList.get(i);
                System.out.println("bruger findes allerede");
            }
        }

        request.getRequestDispatcher("/opret.jsp").forward(request, response);
    }
}
