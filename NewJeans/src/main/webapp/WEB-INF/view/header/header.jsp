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

    <script type="text/javascript">
        function LoadMain(){
            location.href = "/"
        }

        function LoadSignIn(){
            location.href = "/member/signin";
        }

        function LoadSignUp(){
            location.href = "/member/signup"
        }

        function LoadBoardList(){
            location.href = "/boardList.do"
        }
    </script>
</head>

<body>
 <!-- header -->
    <header>
        <div class="inner-header">
            <h1 class="logo">
                <a href="javascript:LoadMain()">
                    <img src="/img/logo.png" alt="logo">
                    <img src="/img/logo2.png" alt="logo">
                </a>
            </h1>
            <a href="javascript:LoadSignIn()" class="menu-open"><span class="menu-txt">SignIn</span> <span class="menu-img"></span></a>
            <a href="javascript:LoadSignUp()" class="menu-open"><span class="menu-txt">SignUp</span> <span class="menu-img"></span></a>
            <a href="#" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
            <a href="javascript:LoadBoardList()" class="menu-open"><span class="menu-txt">Board</span> <span class="menu-img"></span></a>
        </div>
    </header>
    <!-- //header -->
</body>

</html>