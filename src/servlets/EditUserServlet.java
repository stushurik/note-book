package servlets;

import dao.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            DaoFactory factory = new DaoFactoryImpl();
            UserDaoImpl userController = (UserDaoImpl)factory.getDao(UserDaoImpl.class);

            String id = req.getParameter("user");

            User user = userController.getByPK(Integer.parseInt(id));

            req.setAttribute("user", user);
            req.setAttribute("title", "Edit " + user.getFirstName() + " " + user.getLastName()+" user profile");
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory factory = new DaoFactoryImpl();
        try {
            UserDaoImpl userController = (UserDaoImpl)factory.getDao(UserDaoImpl.class);
            User user = new User(req.getParameterMap());

            String msg = user.validate();

            if (msg != null){
                req.setAttribute("msg", msg);
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
            }

            userController.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        resp.sendRedirect("hello");
    }

}
