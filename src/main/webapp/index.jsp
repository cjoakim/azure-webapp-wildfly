<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Wildfly Web App</title>
</head>
<body align='center'>
  <h1>azure-webapp-wildfly</h1>
  <p>current time: <%= (new java.util.Date()).toLocaleString() %></p>
  <p>build date: <%= com.chrisjoakim.azure.webapp.PingServlet.buildDate %></p>
  <p>build user: <%= com.chrisjoakim.azure.webapp.PingServlet.buildUser %></p>
  <p><a href="ping"> ping </a></p>
  <p><a href="redis?key=cat"> redis </a></p>
</body>
</html>
