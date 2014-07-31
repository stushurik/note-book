package servlets;

import dao.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            DaoFactory factory = new DaoFactoryImpl();
            UserDaoImpl userController = (UserDaoImpl)factory.getDao(UserDaoImpl.class);

            String id = req.getParameter("user");

            User user = userController.getByPK(Integer.parseInt(id));

            userController.delete(user);

            resp.sendRedirect("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
