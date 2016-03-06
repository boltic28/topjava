
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- connect jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Meal list</title>
    </head>

    <body>
        <h2> It's Meal list</h2>
        <hr>

        ${name}<br>    <!-- пытаемся вывести просто переданное имя-->
        <!--sothing changing if-->
        <hr>
        <table border="1px">
            <tr><td>id</td><td>description</td><td>time</td><td>call</td>
            <!-- here build table-->
        </table>
        <hr>
                <c:forEach items="${list}" var="item">  <!-- пытаемся вывести простой список-->
                ${item}<br>
                </c:forEach>
    </body>
</html>
