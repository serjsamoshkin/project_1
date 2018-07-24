package ua.testing.project1.controller;

import ua.testing.project1.db.util.DbInit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Main and single Servlet in application.
 * Handles urls "/tour", "/set_locale", "/tour_open", "/tour_buy".
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/set_locale", "/tour", "/tour_open", "/tour_buy"})
public class MainServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        DbInit.initTours();
        getServletContext().setAttribute("language", "ru_RU");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    /**
     * Process doGet and doPost methods of MainServlet.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/tour":
                new TourController().showTorsList(request, response);
                break;
            case "/tour_open":
                new TourController().openTour(request, response);
                break;
            case "/tour_buy":
                new TourController().byuTour(request, response);
                break;
            case "/set_locale":
                String loc = request.getParameter("loc");
                switch (loc) {
                    case "en_En":
                    case "ru_Ru":
                        break;
                    default:
                        loc = "ru_Ru";
                        break;
                }
                getServletContext().setAttribute("language", loc);
                getServletContext().getRequestDispatcher("/").forward(request, response);
                break;
            default:
                getServletContext().getRequestDispatcher("/").forward(request, response);
                break;
        }


    }
}
