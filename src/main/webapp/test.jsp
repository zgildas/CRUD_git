<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:forEach items="${ messages }" var="message" varStatus="boucle">
            <p>${ boucle.count }. ${ message }</p>
        </c:forEach>

</body>
</html>