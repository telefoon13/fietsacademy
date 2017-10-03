<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
    <v:head title='Opslag'/>

</head>
<body>
<v:menu/>
<h1>Docent opslag geven</h1>
<form method="post" id="opslagform">
    <label>Percentage: <span>${fouten.percantage}</span>
    <input name="percantage" value="${param.percantage}" type="number" min="0.01" step="0.01" autofocus required></label>
    <input type="submit" value="Opslag geven" id="opslagKnop">
</form>

<script>
    document.getElementById('opslagform').onsubmit = function() {
        document.getElementById('opslagKnop').disabled = true;
    };
</script>
</body>
</html>