<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
  <title>App <%=(String)request.getAttribute("title")%> </title>
  <link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css"/>
   <script src="http://yui.yahooapis.com/3.17.2/build/yui/yui-min.js"></script>
   <script src="behavior.js"></script>
</head>
<body>

    <c:if test="${empty msg}">
    <p>Add form:</p><br>
    <form action='/edit' method='POST'>
        <table>
            <tr>
                <td>
                    Id
                </td>
                <td>
                    ${user.getId()}
                    <input type="hidden" id="id" name="id" value="${user.getId()}"/>
                </td>
            <tr>
            <tr>
                <td>
                     First Name
                </td>
                <td>
                    <input type="text" id="firstname" name="firstname" value="${user.getFirstName()}"/>
                </td>
            <tr>
            <tr>
                <td>
                      Last Name
                </td>
                <td>
                    <input type="text" id="lastname" name="lastname" value="${user.getLastName()}"/>
                </td>
            <tr>
            <tr>
                <td>
                    Age
                </td>
                <td>
                    <input type="text" id="age" name="age" value="${user.getAge()}"/>
                </td>
            <tr>
            <tr>
                <td>
                       Sex
                </td>
                <td>
                    <select id="sex" name="sex">
                        <option value="m" <c:if test="${user.getSex() == 'm'}">selected</c:if>>male</option>
                        <option value="f" <c:if test="${user.getSex() == 'f'}">selected</c:if>>female</option>
                    </select>
                </td>
            <tr>
            <tr>
                <td>
                    Phone
                </td>
                <td>
                    <input type="text" id="phone" name="phone" placeholder="+(xxx) xxx-xx-xx "/ value="${user.getPhone()}" >
                </td>
            <tr>
        </table>
        <input class="submit" type='submit'>
    </form>
    <a href="/hello"><h5>Cancel<h5></a>
    </c:if>
    ${msg}

</body>
</html>