/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.schools1;

import com.mycompany.schools1.School;
import com.mycompany.schools1.MongoDBSchoolDAO;

import com.mongodb.MongoClient;
import java.io.IOException;
import java.util.List;

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

@WebServlet("/editSchool")
public class EditSchoolServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }
        System.out.println("School edit requested with id=" + id);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBSchoolDAO schoolDAO = new MongoDBSchoolDAO(mongo);
        School s = new School();
        s.setId(id);
        s = schoolDAO.readSchool(s);
        request.setAttribute("school", s);
        List<School> schools = schoolDAO.readAllSchool();
        request.setAttribute("schools", schools);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/schools.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"); // keep it non-editable in UI
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String address = request.getParameter("address");
        String emailDomain = request.getParameter("emailDomain");

        if ((name == null || name.equals("")) || (code == null || code.equals("")) || (address == null || address.equals("")) || (emailDomain == null || emailDomain.equals(""))) {
            request.setAttribute("error", "Name, Code, address and EmailDomain Can't be empty");
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBSchoolDAO schoolDAO = new MongoDBSchoolDAO(mongo);
            School s = new School();
            s.setId(id);
            s.setName(name);
            s.setCode(code);
            s.setAddress(address);
            s.setEmailDomain(emailDomain);
            request.setAttribute("school", s);
            List<School> schools = schoolDAO.readAllSchool();
            request.setAttribute("schools", schools);

            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/schools.jsp");
            rd.forward(request, response);
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBSchoolDAO schoolDAO = new MongoDBSchoolDAO(mongo);
            School s = new School();
            s.setId(id);
            s.setName(name);
            s.setCode(code);
            s.setAddress(address);
            s.setEmailDomain(emailDomain);
            
            schoolDAO.updateSchool(s);
            System.out.println("School edited successfully with id=" + id);
            request.setAttribute("success", "School edited successfully");
            List<School> schools = schoolDAO.readAllSchool();
            request.setAttribute("schools", schools);

            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/schools.jsp");
            rd.forward(request, response);
        }
    }

   
}
