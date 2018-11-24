<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars Store Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Users</a>
    </h2>

    <c:if test="${message != null}">
        <center>
            <div>
                <span><c:out value='${message}' /></span>
                <div>
        </center>
    </c:if>

    <div align="center">
        <c:if test="${isEdit}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${isNew}">
            <form action="insert" method="post">
        </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${isEdit}">
                                Edit User
                            </c:if>
                            <c:if test="${isNew}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${isEdit}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                    </c:if>
                    <tr>
                        <th>Email: </th>
                        <td>
                            <input type="text" name="email" size="45"
                                   value="<c:out value='${user.email}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Password: </th>
                        <td>
                            <input type="password" name="password" size="45"
                                   value="<c:out value='${user.password}' />"
                            />
                        </td>
                    </tr>

                    <tr>
                        <th>Password: </th>
                        <td>
                            <input type="password" name="password" size="45"
                                   value="<c:out value='${user.password}' />"
                            />
                        </td>
                    </tr>



                    <tr>
                        <th>Type: </th>
                        <td>
                            <select name='type'>
                              <option value="${user.userType}" selected>${user.userType}</option>
                                <c:forEach items="${types}" var="type">
                                    <c:if test="${type != selected}">
                                        <option value="${type}">${type}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Save" />
                        </td>
                    </tr>
                </table>
            </form>
    </div>



</center>
</body>
</html>