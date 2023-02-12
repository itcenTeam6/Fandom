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

        function LoadLogIn(){
            location.href = 'member/LogIn.do';
        }

        function LoadRegister(){
            location.href = 'member/Register.do';
        }

        function LoadBoard(){
            location.href = 'board/BoardList.do';
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
            <a href="javascript:LoadLogIn()" class="menu-open"><span class="menu-txt">Login</span> <span class="menu-img"></span></a>
            <a href="javascript:LoadRegister()" class="menu-open"><span class="menu-txt">Register</span> <span class="menu-img"></span></a>
            <a href="#" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
            <!-- test 용 -->
            <a href="javascript:LoadBoard()" class="menu-open"><span class="menu-txt">Board</span> <span class="menu-img"></span></a>
        </div>
    </header>
    <!-- //header -->
</body>

</html>