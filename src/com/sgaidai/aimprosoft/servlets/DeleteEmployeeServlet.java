package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");

        int id = 0;
        String errorMsg = null;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            if(id<1){
                errorMsg = "Employee ID can't be null or empty.";
            };
        }catch (NumberFormatException  e){
            errorMsg = "Invalid ID parameter";
        }
        if (errorMsg != null ) {
            response.sendError(response.SC_BAD_REQUEST, errorMsg);
        } else {
            try {
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                EmployeeDAO dao = new EmployeeDAO(con);
                dao.delete(id);
            } catch (ServletException e) {
                response.sendError(response.SC_BAD_REQUEST, "DB exception");
            }
        }


    }

}