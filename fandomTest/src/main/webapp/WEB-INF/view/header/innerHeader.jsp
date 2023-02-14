<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>

	<script type="text/javascript">

        function LoadMain(){
            location.href = '/';
        }

        function LoadBoardList(idolID){
            location.href = '${cpath}/board/boardList.do?idolID=' + String(idolID);
        }

        function LoadBoardWrite(idolID){
            location.href = '${cpath}/board/boardWrite.do?idolID=' + String(idolID);
        }

        function LoadIdolImg(idolID){
            location.href = '${cpath}/idolImg/idolImg.do?idolID=' + String(idolID);
        }

        function LogOut(){
            location.href = '${cpath}/member/logOut.do'
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
            <a href="javascript:LogOut()" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
        </div>
        <div class="outer-header">
            <a href="javascript:LoadBoardList(${ idolID })">Feed</a>
            <a href="javascript:LoadBoardWrite(${ idolID })">Post</a>
            <a href="javascript:LoadIdolImg(${ idolID })">Media</a>
        </div>
    </header>
    <!-- //header -->
</body>

</html>