package servlets;

import dao.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Add new user");
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory factory = new DaoFactoryImpl();
            try {
                UserDaoImpl userController = (UserDaoImpl) factory.getDao(UserDaoImpl.class);
                User user = new User(req.getParameterMap());
                String msg = user.validate();

                if (msg != null){
                    req.setAttribute("msg", msg);
                    req.getRequestDispatcher("add.jsp").forward(req, resp);
                }

                userController.create(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            resp.setContentType("text/html");
            resp.sendRedirect("hello");

    }

}
