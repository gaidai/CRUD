package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.DepartmentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/updateDepartment")
public class UpdateDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = null;
        int id = Integer.parseInt(request.getParameter("id"));
        if(id <= 0){
            response.sendError(response.SC_BAD_REQUEST, "ID can't be null or empty.");
        }else {
            try {
                // get jsp page with updating form
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                DepartmentDAO dao = new DepartmentDAO(con);
                name = dao.findById(id);
                request.setAttribute("id", id);
                request.setAttribute("name", name);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateDepartment.jsp");
                rd.forward(request, response);
            }catch (ServletException e) {

                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "DB exception");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        String errorMsg = null;
        String name = request.getParameter("name");
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        if(name == null || name.equals("")){
            errorMsg = "Name can't be null or empty.";
        };
        if(id <= 0){
            errorMsg = "ID can't be null or empty.";
        };
        if(errorMsg != null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
        }else {
            try {
                System.out.println(id+" "+name);
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                DepartmentDAO dao = new DepartmentDAO(con);
                dao.update(id, name);
            }catch (ServletException e){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "DB exception");
            }
        }
    }

}