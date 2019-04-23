<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Campagne</title>
<link rel="stylesheet" type="text/css" href="inc/style.css"/>
</head>
<body>
	
	<table id="t01">
		<tr>
			<td class="number">N°</td>
			<th>Campaign ID</th>
			<th>Campaign Title</th>
			<th>Campaign Type</th>
			<th>Emails Sent</th>
			<th>Abuse Reports</th>
			<th>Unsubscribe Count</th>
			<th>Send Time</th>
			<th class="action">Action</th>
		</tr>
			<c:forEach items="${sessionScope.mesCampagnes}" var="mapCampagne" varStatus="boucle">
			<tr>
			<td><c:out value="${boucle.count}"/></td>		
			<td><c:out value="${mapCampagne.value.id }" />
			<td><c:out value="${mapCampagne.value.campaign_title }" /></td>	
			<td><c:out value="${mapCampagne.value.type }" /></td>	
			<td><c:out value="${mapCampagne.value.emails_sent }" /></td>	
			<td><c:out value="${mapCampagne.value.abuse_reports }" /></td>	
			<td><c:out value="${mapCampagne.value.unsubscribed }" /></td>	
			<td><c:out value="${ mapCampagne.value.send_time }" /></td>	
			<td class="action">
                        <a href="<c:url value="/suppressioncampagne"><c:param name="ID" value="${ mapCampagne.key }" /></c:url>">
                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
                        </a>
            </td>	
			</tr>
	</c:forEach>
		

	</table>

</body>
</html>
