<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <script text="text/javascript">
        function LoadMain(){
            location.href = '/';
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
                    <a href="/member/signin" class="menu-open"><span class="menu-txt">Login</span> <span class="menu-img"></span></a>
                 </c:if>
                <c:if test="${cookieValue eq 'true'}">
                    <a href="/member/logout" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
                </c:if>
           <a href="boardList.html" class="menu-open"><span class="menu-txt">Board</span> <span class="menu-img"></span></a>
           <a href="idolImg.html" class="menu-open"><span class="menu-txt">Idol</span> <span class="menu-img"></span></a>
        </div>
    </header>
    <!-- //header -->
</body>

</html>