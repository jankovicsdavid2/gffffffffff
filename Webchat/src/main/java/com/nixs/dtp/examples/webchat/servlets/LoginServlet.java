package com.nixs.dtp.examples.webchat.servlets;

import com.nixs.dtp.examples.webchat.model.DBModel;
import com.nixs.dtp.examples.webchat.model.IModel;
import com.nixs.dtp.examples.webchat.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("Hello from doGet");*/

        //request.getRequestDispatcher("login.html").forward(request, response);

        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //String dbURL = "jdbc:mariadb://localhost:3306/chatdb";
        //String dbuser = "root";

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        /*try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            out.println(e);
        }*/

        Context initCtx = null;
        Connection conn = null;

        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/chatdb");
            conn = ds.getConnection();

            IModel model = new DBModel(conn);

            user = model.authUser(username, password);

            if (user == null) {
                out.println("Hibas credentials");

            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("talks").forward(request, response);
                //out.println("Hello "+ user.getName());

            }


        } catch (NamingException | SQLException ex) {
            throw new RuntimeException(ex);
        }


        //try (Connection conn = DriverManager.getConnection(dbURL,dbuser,null)) {


    }
}