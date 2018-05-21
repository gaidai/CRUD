package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.DepartmentDAO;
import com.sgaidai.aimprosoft.models.Department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;


@WebServlet("/departments")
public class DepartmentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Department> list = null;
        try {
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            DepartmentDAO dao = new DepartmentDAO(con);
            list = dao.findAll();
            request.setAttribute("departments", list);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/departments.jsp");
            rd.forward(request, response);
        }catch (ServletException e ) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/AppExceptionHandler");
            rd.forward(request, response);

        }
    }

}