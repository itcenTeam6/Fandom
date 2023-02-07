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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <!-- header -->
    <header>
        <div class="inner-header">
            <h1 class="logo">
                <a href="#">
                    <img src="img/logo.png" alt="logo">
                    <img src="img/logo2.png" alt="logo">
                </a>
            </h1>
            <a href="#" class="menu-open"><span class="menu-txt">Login</span><span class="menu-img"></span></a>
            <a href="#" class="menu-open"><span class="menu-txt">LogOut</span><span class="menu-img"></span></a>
            <!-- <a href="#" class="menu-open"><span class="menu-txt">MENU</span> <span class="lnr lnr-menu menu-img"></span></a> -->
        </div>
        <!-- gnb
            <nav class="gnb">
                <a href="#" class="close">
                    <span class="lnr lnr-cross"></span>
                </a>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Work</a></li>
                    <li><a href="#">Contact</a></li>
                    <li><a href="#">News</a></li>
                    <li><a href="#">Favorite</a></li>
                </ul>
            </nav> -->

    </header>
    <!-- //header -->
</body>

</html>