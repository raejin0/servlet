<%--<%@ page import="hello.servlet.domain.member.Member" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>
    <li>usename=<%=((Member)request.getAttribute("member")).getUsername()%></li>
    <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>--%>

<%--    <li>id=asdf</li>--%>
    <li>id=${member.id}</li>
    <li>usename=${member.username}</li>
    <li>age=${member.age}</li>
</body>
<a href="/index.html">main</a>
</html>

