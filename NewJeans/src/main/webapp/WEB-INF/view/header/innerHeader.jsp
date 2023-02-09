<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>

	<script type="text/javascript">
	    function LoadMain(){
	        location.href = "/"
	    }

		function LoadBoardList(){
            location.href = "/boardList.do"
        }

        function LoadBoardWrite(){
            location.href = "/boardWrite.do"
        }

        function LoadIdolImg(){
            location.href = "/idolImg.do"
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
			<a href="#" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
		</div>
		<div class="outer-header">
			<a href="javascript:LoadBoardList()">BoardList</a>
			<a href="javascript:LoadBoardWrite()">BoardWrite</a>
			<a href="javascript:LoadIdolImg()">Idol</a>
		</div>
	</header>
	<!-- //header -->
</body>

</html>