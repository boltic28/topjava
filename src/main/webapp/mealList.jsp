<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Meal list</title>
    </head>
    <body>
        <h2>Meal list of ${names}</h2>
        <hr>

        <table border="1px" cellpadding="5px">
            <tr><td>id</td><td>description</td><td>time</td><td>call</td></tr>
            <c:set var="i" value="0"/>
                <c:forEach items="${list}" var="item">

                    <c:choose>
                        <c:when test="${item.exceed}">
                            <tr>
                                <td><font color="red"> ${i = i + 1}</font></td>
                                <td><font color="red">${item.description}</font></td>
                                <td><font color="red">${item.dateTime}</font></td>
                                <td><font color="red">${item.calories}</font></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td><font color="green"> ${i = i + 1}</font></td>
                                <td><font color="green">${item.description}</font></td>
                                <td><font color="green">${item.dateTime}</font></td>
                                <td><font color="green">${item.calories}</font></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>

        </table>
        <font: color="black">
        <hr>

    </body>
</html>