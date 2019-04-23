<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'un client</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<div>
		<form method="post" action="test">
			<fieldset>
				<legend>Informations Campagne</legend>

				<label for="id">Campaign ID</label> 
				<input type="text" id="id" name="id" value="" size="20"maxlength="20" /><span>${traite.erreurs['id']}</span> <br /> 
				<label for="campaign_title">Campaign Title</label> 
				<input type="text" id="campaign_title" name="campaign_title" value="" size="20" maxlength="20" /><span>${traite.erreurs['campaign_title']}</span> <br /> 
				<label for="type">Campaign Type</label> 
				<input type="text" id="type" name="type" value=""	size="20" maxlength="20" /><span>${traite.erreurs['type']}</span> <br />
			    <label for="emails_sent">Emails Sent </label> 
			    <input type="number" id="emails_sent" name="emails_sent" value="" size="20" maxlength="20" /><span>${traite.erreurs['emails_sent']}</span> <br /> 
			    <label for="abuse_reports">Abuse Report</label> 
			    <input type="number" id="abuse_reports" name="abuse_reports" value="" size="20" maxlength="60" /> <span>${traite.erreurs['abuse_reports']}</span><br />
			    <label for="unsubscribed">Unsubscribe Count </label> 
			    <input type="number" id="unsubscribed" name="unsubscribed" value="" size="20" maxlength="20" /><span>${traite.erreurs['unsubscribed']}</span> <br /> 			 
			    <label for="send_time">Send Time</label> 
			    <input type="datetime" id="send_time" name="send_time" value=""  /> <span>${traite.erreurs['send_time']}</span><br /> 
			    
			</fieldset> 
			<input type="submit" value="Créer" /> <input type="reset"
				value="Remettre à zéro" /> <br />
		</form>
	</div>
	<c:forEach items="${traite.resultat}" var="message" varStatus="boucle">
            <p>${ boucle.count }. ${ message }</p>
        </c:forEach>
	<c:forEach items="${traite.erreurs}" var="message" varStatus="boucle">
            <p>${ boucle.count }. ${ message }</p>
        </c:forEach>
</body>
</html>