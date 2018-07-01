package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/checkEmail")
public class CheckEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        if (email == null || email.equals("")) {
            response.sendError(response.SC_BAD_REQUEST, "Invalid email parameter");
        } else {
            try {
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                EmployeeDAO dao = new EmployeeDAO(con);
                boolean check = !dao.checkEmail(email).isEmpty();
                response.setContentType("text/html");
                response.getWriter()
                        .append(Boolean.toString(check))
                        .close();
            } catch (ServletException e) {
                response.sendError(response.SC_BAD_REQUEST, "DB exception");
            }
        }
    }
}