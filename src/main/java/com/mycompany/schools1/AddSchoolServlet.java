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
@WebServlet("/addSchool")
public class AddSchoolServlet extends HttpServlet {

    private static final long serialVersionUID = -7060758261496829905L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String address = request.getParameter("address");
        String emailDomain = request.getParameter("emailDomain");

        if ((name == null || name.equals("")) || (code == null || code.equals("")) || (address == null || address.equals("")) || (emailDomain == null || emailDomain.equals(""))) {
            request.setAttribute("error", "Mandatory Parameters Missing");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/schools.jsp");

            rd.forward(request, response);
        } else {
            School s = new School();
            s.setCode(code);
            s.setAddress(address);
            s.setEmailDomain(emailDomain);
            s.setName(name);
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBSchoolDAO schoolDAO = new MongoDBSchoolDAO(mongo);
            schoolDAO.createSchool(s);
            System.out.println("School Added Successfully with id=" + s.getId());
            request.setAttribute("success", "School Added Successfully");
            List<School> schools = schoolDAO.readAllSchool();
            request.setAttribute("schools", schools);

            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/schools.jsp");
            rd.forward(request, response);
        }
    }

}
