<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Meal list</title>
    </head>
    <body>
        <h2>Meal list of ${names}</h2>
        <hr>
        <h2>Filter</h2><br>
        <form>
            Date : <input type="date" id="filter_date"><br>
            St time : <input type="time" id="filter_st"><br>
            End time : <input type="time" id="filter_et"><br>
            Cal lim : <input type="number" id="cal_lim"><br>
            <input type="submit" value="Go!" id="go">
        </form>
        <hr>
        <h2>Result</h2><br>
        <table border="1px" cellpadding="5px">
            <tr><td>id</td><td>description</td><td>time</td><td>call</td></tr>
            <c:set var="i" value="0"/>
                <c:forEach items="${list}" var="item">

                    <c:choose>
                        <c:when test="${item.exceed}">
                            <tr>
                                <td><font color="red">${i = i + 1}</font></td>
                                <td><font color="red">${item.description}</font></td>
                                <td><font color="red">${item.dateTime}</font></td>
                                <td><font color="red">${item.calories}</font></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td><font color="green">${i = i + 1}</font></td>
                                <td><font color="green">${item.description}</font></td>
                                <td><font color="green">${item.dateTime}</font></td>
                                <td><font color="green">${item.calories}</font></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>

        </table>
        <hr>

    </body>
</html>