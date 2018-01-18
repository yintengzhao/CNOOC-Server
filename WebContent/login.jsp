<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 获取相关路径 -->    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="./css/login.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="./login.js"></script>
</head>
<body>

<div id="container">
    <div id="header">
        <img class="logo" src="./image/logo.jpg">
        <h1 >物资保障体系模拟仿真软件研究与开发平台</h1>
        <div id="menu" class="col-md-3">
            <img class="logo2" src="./image/1.jpg" alt="Pulpit rock" width="800" height="400">
        </div>
        <div>
            <form class="bs-sshple bs-sshple-form" role="form" 
                action="<%=basePath%>user!checkUser.action" method="post">
                <div class="input-group">
                    <span class="input-group-addon">用户名：</span>
                    <input type="text" style="width: 50%" class=form-control input-lg" 
                    name="user.username"
                    input-lg" placeholder="用户名/手机号/邮箱">
                </div>
                <br>
                <div class="input-group">  
                    <span class="input-group-addon">&nbsp&nbsp&nbsp&nbsp密码：</span>
                    <input type="password" style="width: 50%" class=form-control input-lg" 
                    name="user.password"
                    placeholder="密码">
                </div>
                <br>
                <input class="btn btn-primary" id="fat-btn"
                 type="submit" value="Login" 
                 style="height:40px;width:174px;"/>
                
                </form>
        </div>
        <p>版权所有© www.cnooc.com.cn</p>
    </div>
   <!--  <center>
        <h1>Please Login First</h1><br/>
        
        <form action="<%=basePath%>user!checkUser.action" method="post">
        User Name:<input type="text" name="user.username" style="width: 100px;"/><br/>
        User Password:<input type="text" name="user.password" style="width: 100px;"/>
        <br/>
        <input type="submit" value="Login"/>
        </form>
    </center> -->

</body>
</html>