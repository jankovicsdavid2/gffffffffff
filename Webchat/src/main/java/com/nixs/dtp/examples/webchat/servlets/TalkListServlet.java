package com.nixs.dtp.examples.webchat.servlets;

import com.nixs.dtp.examples.webchat.model.DBModel;
import com.nixs.dtp.examples.webchat.model.IModel;
import com.nixs.dtp.examples.webchat.model.Talk;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TalkListServlet", value = "/talks")
public class TalkListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("hello get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Context initCtx = null;
        Connection conn = null;

        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/chatdb");
            conn = ds.getConnection();

            IModel model = new DBModel(conn);

            User user = (User) request.getSession().getAttribute("user");

            List<Talk> talks = model.getTalks(user);

            conn.close();

            request.setAttribute("talks", talks);
            request.getRequestDispatcher("talk_list.jsp").forward(request,response);


        } catch (NamingException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}