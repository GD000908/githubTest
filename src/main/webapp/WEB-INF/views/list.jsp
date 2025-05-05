<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>목록</title></head>
<body>
<h1>사용자 목록</h1>

<ul>
    <c:forEach var="user" items="${users}">
        <li>${user}</li>
    </c:forEach>
</ul>

<a href="${pageContext.request.contextPath}/">돌아가기</a>
</body>
</html>
