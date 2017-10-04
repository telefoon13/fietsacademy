<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
    <v:head title='Docent voornamen'/>
</head>
<body>
<v:menu/>
<h1>Docent voornamen</h1>
<ul class="zonderbolletjes">
    <c:forEach items="${voornamen}" var="voornaamEnID">
        <c:url value="/docenten/zoeken.htm" var="docentURL">
            <c:param name="id" value="${voornaamEnID.id}"/>
        </c:url>
        <li style="font-size: ${voornaamEnID.voornaam.length() mod 3+1}em;">
                <a href="${docentURL}"> ${voornaamEnID.voornaam}</a></li>
    </c:forEach>
</ul>
</body>
</html>