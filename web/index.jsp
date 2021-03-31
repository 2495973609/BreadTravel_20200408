<%--
  Created by IntelliJ IDEA.
  User: KBT
  Date: 2020/4/9
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>

    <base href="<%=basePath%>">
    <title>$Title$</title>
  </head>
    <body style="background-color: #80CBF1">
  </body>
</html>
