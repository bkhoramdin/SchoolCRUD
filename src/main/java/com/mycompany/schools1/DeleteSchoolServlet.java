/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.schools1;
import com.mycompany.schools1.School;
import com.mycompany.schools1.MongoDBSchoolDAO;


import java.io.IOException;
import java.util.List;
 import com.mongodb.MongoClient;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author Babak
 */

@WebServlet("/deleteSchool")
public class DeleteSchoolServlet   extends HttpServlet {
 
    private static final long serialVersionUID = 6798036766148281767L;
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBSchoolDAO schoolDAO = new MongoDBSchoolDAO(mongo);
        School s = new School();
        s.setId(id);
        schoolDAO.deleteSchool(s);
        System.out.println("School deleted successfully with id=" + id);
        request.setAttribute("success", "School deleted successfully");
        List<School> schools = schoolDAO.readAllSchool();
        request.setAttribute("schools", schools);
 
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/schools.jsp");
        rd.forward(request, response);
    }
 
}
