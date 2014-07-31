package servlets;

import dao.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory factory = new DaoFactoryImpl();
        try {
            GenericDao userController = (UserDaoImpl)factory.getDao(UserDaoImpl.class);

            List<User> list = userController.search(req.getParameterMap());
            req.setAttribute("list", list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        req.setAttribute("title", "All users");

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
