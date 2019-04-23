<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<h2>Hello World!</h2>
</body>

<div id="menu">
    <p><a href="<c:url value="/creationcampagne.jsp"/>">Créer une nouvelle Campagne</a></p>
    <p><a href="<c:url value="/listecampagnes.jsp"/>">Liste des Campagnes</a></p>
    <p><a href="<c:url value="/affichecampagne.jsp"/>">Modifier les Campagnes</a></p>
</div>
</html>
 