package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.DepartmentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/checkDepartment")
public class CheckDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        if(name == null || name.equals("")){
            response.sendError(response.SC_BAD_REQUEST, "Name can't be null or empty.");
        }else {
            try {
                // Check that department name is exists
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                DepartmentDAO dao = new DepartmentDAO(con);
                boolean check = !dao.checkName(name).isEmpty();
                response.setContentType("text/html");
                response.getWriter()
                        .append(Boolean.toString(check))
                        .close();
            } catch (ServletException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "DB exception");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String errorMsg = null;
        int id = 0;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            if(id<1){
                errorMsg = "Invalid ID parameter";
            };
        }catch (NumberFormatException  e){
            errorMsg = "Invalid ID parameter";
        }
        if (errorMsg != null ) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
        } else {
            try {
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                DepartmentDAO dao = new DepartmentDAO(con);
                // Check that department ID is exists
                String dep = dao.findById(id);
                boolean check = false;
                if(dep != null){
                    check =  true;
                }
                response.setContentType("text/html");
                response.getWriter()
                        .append(Boolean.toString(check) )
                        .close();
            } catch (ServletException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "DB exception");
            }
        }
    }

}