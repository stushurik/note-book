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
    <form action='/add' method='POST'>
        <table>

                <c:forTokens items="id,First Name,Last Name,Age,Sex,Phone" delims="," var="field">
                <tr>
                    <c:set var="tmp_value" value="${fn:replace(field, ' ', '')}" />
                    <c:set var="value" value="${fn:toLowerCase(tmp_value)}" />
                    <td>
                        ${field}
                    </td>
                    <c:choose>
                        <c:when test="${value == 'id'}">
                            <td>
                                Generated automatically
                            </td>
                        </c:when>
                        <c:when test="${value == 'sex'}">
                            <td>
                                <select id="${value}" name="${value}">
                                    <option value="m" selected>male</option>
                                    <option value="f">female</option>
                                </select>
                            </td>
                        </c:when>
                        <c:when test="${value == 'phone'}">
                            <td>
                                <input type="text" id="${value}" name="${value}" placeholder="+(xxx) xxx-xx-xx "/>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><input type="text" id="${value}" name="${value}" /></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                </c:forTokens>

        </table>
        <input class="submit" type='submit'>
    </form>
    </c:if>
    <a href="/hello"><h5>Cancel<h5></a>
${msg}
</body>
</html>