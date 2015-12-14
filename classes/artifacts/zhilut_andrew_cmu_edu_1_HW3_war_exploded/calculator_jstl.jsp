<!DOCTYPE html>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Simple Calculator</title>
</head>

<body>

<h2>Simple Calculator</h2>

<c:forEach var="error" items="${errors}">
    <h3 style="color:red">${error}</h3>
</c:forEach>


<form action="JustDoIt" method="POST">
    <table>
        <tr>
            <td><label for="label_x">X:</label><input id="label_x" name="parameterX" type="text" size="40"
                                                      value="${xValue}"/>
            </td>
        </tr>
        <tr>
            <td><label for="label_y">Y:</label><input id="label_y" name="parameterY" type="text" size="40"
                                                      value="${yValue}"/>
            </td>
        </tr>
        <tr>
            <td><label for="label_result">Answer:</label><input id="label_result" name="result" type="text" size="40"
                                                                value="${resultValue}" readonly/></td>
        </tr>
        <tr>
            <td><input class="operator" type="submit" name="action" value="+"/>
                <input class="operator" type="submit" name="action" value="-"/>
                <input class="operator" type="submit" name="action" value="*"/>
                <input class="operator" type="submit" name="action" value="/"/>
            </td>
        </tr>
    </table>


</form>
</body>
</html>