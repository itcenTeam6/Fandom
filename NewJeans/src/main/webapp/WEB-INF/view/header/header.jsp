<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Soon9">
    <meta name="description" content="Soon9's web studio">
    <meta name="viewport"
        content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <title>Document</title>

    <script text="text/javascript">
        function LoadMain(){
            location.href = '/';
        }

        function LoadLogIn(){
            location.href = '${cpath}/member/signin';
        }

        function LoadRegister(){
            location.href = '${cpath}/member/signup';
        }

        function LogOut(){
            location.href = '${cpath}/member/logout'
        }
    </script>
</head>

<body>
    <!-- header -->
    <header>
        <div class="inner-header">
            <h1 class="logo">
                <a href="javascript:LoadMain()">
                    <img src="/img/WeverseLogo_main.png" alt="logo">
                </a>
            </h1>
                 <c:if test="${cookieValue eq 'false'}">
                    <a href="javascript:LoadLogIn()" class="menu-open"><span class="menu-txt">Login</span> <span class="menu-img"></span></a>
                    <a href="javascript:LoadRegister()" class="menu-open"><span class="menu-txt">Register</span> <span class="menu-img"></span></a>
                 </c:if>
                <c:if test="${cookieValue eq 'true'}">
                    <a href="javascript:LogOut()" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
                </c:if>
        </div>
    </header>
    <!-- //header -->
</body>

</html>