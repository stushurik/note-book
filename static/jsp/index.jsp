<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
  <title>App <%=(String)request.getAttribute("title")%> </title>
  <link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>

    <a href="/add"><h5>Add new user<h5></a>
    <p>Search form:</p>
    <form action='/search' method='GET'>
        <table>
            <tr>
                <c:forTokens items="id,First Name,Last Name,Age,Sex,Phone" delims="," var="field">
                    <th>${field}</th>
                </c:forTokens>
            </tr>
            <tr>
                <c:forTokens items="id,firstname,lastname,age,sex,phone" delims="," var="field">
                        <c:if test="${field == 'sex'}">
                            <td>
                                <select id="${field}" name="${field}">
                                    <option <c:if test="${empty param.sex}">selected</c:if> ></option>
                                    <option value="m" <c:if test="${param.sex == 'm' }">selected</c:if> >male</option>
                                    <option value="f" <c:if test="${param.sex == 'f' }">selected</c:if> >female</option>
                                </select>
                            </td>
                        </c:if>
                        <c:if test="${field == 'phone'}">
                            <td>
                                <input type="text" id="${field}" name="${field}" placeholder="+(xxx) xxx-xx-xx " value="${param.phone}"/>
                            </td>
                        </c:if>
                        <c:if test="${field == 'age'}">
                            <td><input type="text" id="${field}" name="${field}" value="${param.age}" /></td>
                        </c:if>

                        <c:if test="${field == 'firstname'}">
                            <td><input type="text" id="${field}" name="${field}" value="${param.firstname}"  /></td>
                        </c:if>

                        <c:if test="${field == 'lastname'}">
                            <td><input type="text" id="${field}" name="${field}" value="${param.lastname}"  /></td>
                        </c:if>

                        <c:if test="${field == 'id'}">
                            <td><input type="text" id="${field}" name="${field}" value="${param.id}"  /></td>
                        </c:if>

                </c:forTokens>
            </tr>
        </table>
        <input type='submit'>
    </form>

    <table border='1'>
    <tr>
        <th> # </td>
        <th> First Name</td>
        <th> Last Name </td>
        <th> Age</td>
        <th> Sex </td>
        <th> Phone </td>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.getId()}</td>
            <td>${item.getFirstName()}</td>
            <td>${item.getLastName()}</td>
            <td>${item.getAge()}</td>
            <td>${item.getSex()}</td>
            <td>${item.getPhone()}</td>
            <td><a href="/edit?user=${item.getId()}">edit</a></td>
            <td><a href="/delete?user=${item.getId()}">x</a></td>
        </tr>
    </c:forEach>
    </table>



</body>
</html>