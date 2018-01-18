<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- c 标签，后面会使用到 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>

<style type="text/css">
    td{
        text-align: center;
        width: 100px;
    }
</style>

</head>
<body>

    </div>
       
        <center>
               <form action="base!queryBase.action" method="post">
                   user name<input type="text" name="base.id"/>&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="submit" value="Search"/>
           </form>
        </center>
        <br>
        <br>
        <br><br>


        <!-- tag>0 表示查询结果正常-->
        <c:if test="${tag>0}">
        <center>
            <table border="1">

                <!-- 查询结果也以表格形式展现 -->
                <tr>
                    <td>user id</td><td>user name</td><td>email</td>
                </tr>


                <!-- users2 就是 UserAction 中的 queryUser() 返回的结果，同样是遍历 -->
                <c:forEach items="${bases1}" var="b">
                    <tr>
                        <td>${b.id}</td><td>${b.storage_place}</td>
                    </tr>
                </c:forEach>

            </table>

        </center>
        </c:if>

</body>
</html>