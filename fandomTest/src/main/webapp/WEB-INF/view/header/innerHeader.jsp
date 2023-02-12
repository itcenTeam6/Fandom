<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
                <a href="index.html">
                    <img src="/img/WeverseLogo_main.png" alt="logo">
                </a>
            </h1>
            <a href="#" class="menu-open"><span class="menu-txt">SignIn</span> <span class="menu-img"></span></a>
            <a href="#" class="menu-open"><span class="menu-txt">SignUp</span> <span class="menu-img"></span></a>
            <a href="#" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
            <a href="boardList.html" class="menu-open"><span class="menu-txt">Board</span> <span class="menu-img"></span></a>
            <a href="idolImg.html" class="menu-open"><span class="menu-txt">Idol</span> <span class="menu-img"></span></a>
        </div>
        <div class="outer-header">
            <a href="boardList.html">Feed</a>
            <a href="boardWrite.html">Post</a>
            <a href="idolImg.html">Media</a>
        </div>
    </header>
    <!-- //header -->
</body>

</html>