package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.DepartmentDAO;
import com.sgaidai.aimprosoft.dao.EmployeeDAO;
import com.sgaidai.aimprosoft.models.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Get createEmployee form for selected Department
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if(id == null || id.equals("")){
            response.sendError(response.SC_BAD_REQUEST, "Invalid ID parameter");
        }else{
            try {
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                DepartmentDAO dao = new DepartmentDAO(con);
                String name = dao.findById(Integer.parseInt(id));
                request.setAttribute("departmentid", id);
                request.setAttribute("name", name);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/createEmployee.jsp");
                rd.forward(request, response);
            }catch (ServletException e){
                response.sendError(response.SC_BAD_REQUEST, "DB exception");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int salary = Integer.parseInt(request.getParameter("salary"));
        int department = Integer.parseInt(request.getParameter("departmentid"));
        SimpleDateFormat  formatter = new SimpleDateFormat ("dd-MM-yyyy");
        String errorMsg = null;
        Date birthdate = null;
        try {
            birthdate = formatter.parse(request.getParameter("birthdate"));
        } catch (ParseException e) {
            errorMsg = " Parcing exception of birthdate";
        }
        if(email == null || email.equals("")){
            errorMsg = "Email ID can't be null or empty.";
        }
        if(firstname == null || firstname.equals("")){
            errorMsg = "Fistname can't be null or empty.";
        }
        if(lastname == null || lastname.equals("")){
            errorMsg = "Lastname can't be null or empty.";
        }
        if(department == 0){
            errorMsg = "Department can't be 0 or empty.";
        }
        if(salary == 0){
            errorMsg = "Salary should be more then 100";
        }
        // compare birthday with current date
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        int yearBirthdate = Integer.parseInt( df.format(birthdate));
        int yearNow = Integer.parseInt( df.format(today));
        if(yearBirthdate < yearNow - 100|| yearNow <yearBirthdate + 15){
            errorMsg = "Employee can't be younger then 15 or older then 100.";
        }

        if(errorMsg != null){
            response.sendError(response.SC_BAD_REQUEST, errorMsg);
        }else{
            try{
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                EmployeeDAO dao = new EmployeeDAO(con);
                Employee emp = new Employee(firstname,lastname,email,salary,birthdate,department);
                dao.create(emp);
            }catch (ServletException e){
                response.sendError(response.SC_BAD_REQUEST, "DB exception");
            }
        }
    }
}