<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schools Manage Page</title>
        <style>
            table,th,td {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <%-- School Add/Edit logic --%>
        <c:if test="${requestScope.error ne null}">
            <strong style="color: red;"><c:out
                    value="${requestScope.error}"></c:out></strong>
            </c:if>
            <c:if test="${requestScope.success ne null}">
            <strong style="color: green;"><c:out
                    value="${requestScope.success}"></c:out></strong>
            </c:if>
            <c:url value="/addSchool" var="addURL"></c:url>
            <c:url value="/editSchool" var="editURL"></c:url>

        <%-- Edit Request --%>
        <c:if test="${requestScope.school ne null}">
            <form action='<c:out value="${editURL}"></c:out>' method="post">
                <p> ID:</p> <input type="text" value="${requestScope.school.id}"
                                   readonly="readonly" name="id"><br> 
                <p>Name:</p> <input type="text" value="${requestScope.school.name}" name="name"><br>
                <p>Code:</p> <input type="text" value="${requestScope.school.code}" name="code"><br> 
               
                <p>Address:</p> <input type="text" value="${requestScope.school.address}" name="address"><br> 
                <p>Email Domain:</p> <input type="text" value="${requestScope.school.emailDomain}" name="emailDomain"    ><br>
                <input type="submit" value="Edit School">
            </form>
        </c:if>

        <%-- Add Request --%>
        <c:if test="${requestScope.school eq null}">
            <form action='<c:out value="${addURL}"></c:out>' method="post">
                    <p> Name:</p> <input type="text" name="name"><br> 
                    <p>Code:</p> <input type="text" name="code"><br> 
                    <p>Address:</p> <input type="text" name="address"><br>
                    <p>  Email Domain:</p> <input type="text" name="emailDomain"><br>   <input type="submit"
                                                                                               value="Add School">
                </form>
        </c:if>

        <%-- Schoolss List Logic --%>
        <c:if test="${not empty requestScope.schools}">
            <table>
                <tbody>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Code</th>
                        <th>Address</th>
                        <th>Email Domain</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${requestScope.schools}" var="school">
                        <c:url value="/editSchool" var="editURL">
                            <c:param name="id" value="${school.id}"></c:param>
                        </c:url>
                        <c:url value="/deleteSchool" var="deleteURL">
                            <c:param name="id" value="${school.id}"></c:param>
                        </c:url>
                        <tr>
                            <td><c:out value="${school.id}"></c:out></td>
                            <td><c:out value="${school.name}"></c:out></td>
                            <td><c:out value="${school.code}"></c:out></td>
                            <td><c:out value="${school.address}"></c:out></td>
                            <td><c:out value="${school.emailDomain}"></c:out></td>

                                <td><a
                                        href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
                                <td><a
                                        href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
                            </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>