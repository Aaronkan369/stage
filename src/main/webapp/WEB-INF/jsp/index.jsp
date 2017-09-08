<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>前台测试</title>
  </head>
  <body>
    <form action="/stage/me/person" method="get">
      选择id
    <select name="value">
      <option  value="1">1</option>
      <option  value="2">2</option>
    </select>
      <input type="submit" value="查询">
    </form>
    <td>${p.id}</td>
    <td>${p.age}</td>
    <td>${p.pname}</td>
    <td>${p.sex}</td>
  </body>
</html>
