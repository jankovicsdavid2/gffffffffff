package com.nixs.dtp.examples.webchat.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("Hello from doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");

        Context initCtx = null;
        Connection conn = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/chatdb");
            conn = ds.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement register = conn.prepareStatement("INSERT INTO `user`(`username`, `password`, `picture`, `name`) " +
                    "VALUES (?,?,?,?) ");
            register.setString(1,username);
            register.setString(2,request.getParameter("password"));
            register.setString(3,null);
            register.setString(4,request.getParameter("name"));

            register.executeUpdate();


        } catch (SQLException e) {

            

            response.sendRedirect("Registration.html");


        }


        response.setContentType("text/html");

        out.println("Thank you for registering on our site!! " + username);
    }
}