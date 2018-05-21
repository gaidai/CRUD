package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.DepartmentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/addDepartment")
public class AddDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        if(name == null || name.equals("")){
            response.sendError(response.SC_BAD_REQUEST,  "Name can't be null or empty.");
        }else {
            try {
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                DepartmentDAO dao = new DepartmentDAO(con);
                int id = dao.create(name);
                response.setContentType("text/html");
                response.getWriter()
                        .append(Integer.toString(id))
                        .close();
            } catch (ServletException e) {
                response.sendError(response.SC_BAD_REQUEST, "DB exception");
            }
        }
    }

}