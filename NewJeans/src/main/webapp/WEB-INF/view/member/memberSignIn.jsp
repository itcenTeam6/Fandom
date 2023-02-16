<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>LogIn</title>
    <meta name="author" content="Soon9">
    <meta name="description" content="Soon9's web studio">
    <meta name="viewport"
        content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

    <!-- css -->
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/main-responsive.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/my.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <jsp:include page="../header/header.jsp" />
    <main class="login-body slider-bg">
        <div class="login-form">
            <h2>Login</h2>
             <div class="form-input" style="height :80px !important">
                <label for="name">Email</label>
                <input type="email" name="userEmail" id="memEmail" placeholder="Input your Email">
                <span id="memEmailRight"></span>
            </div>
            <div class="form-input" style="height :80px !important">
                <label for="name">Password</label>
                <input type="password" name="userPw" id="memPassword" placeholder="Input your Password">
                <span id="memPasswordRight"></span>
            </div>
            <div class="form-input pt-30">
                <input type="submit" name="submit" value="login" id="loginBtn">
            </div>

            <div class="text-center">
                <p class="mem">Not a member?</p>
                <a href="/member/signup" class="registration">Registration</a>
            </div>
        </div>
    </main>
    <script src="/js/bootstrap.min.js"></script>
    <script>
        let flag1 = false;
        let flag2 = false;

        const $memEmail = document.getElementById('memEmail');
        const $memEmailRight = document.getElementById('memEmailRight');
        const $memPassword = document.getElementById('memPassword');
        const $memPasswordRight = document.getElementById('memPasswordRight');

        const $signUpBtn = document.getElementById('signUpBtn');
        const $loginBtn = document.getElementById('loginBtn');

        $memEmail.onkeyup = function () {
            const emailRegex = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/;

            if (!$memEmail.value) {
                $memEmailRight.style.color = '#f21a3f';
                $memEmailRight.innerHTML = '아이디는 필수로 입력해야 합니다.';

            } else if (!emailRegex.test($memEmail.value)) {
                $memEmailRight.style.color = '#f21a3f';
                $memEmailRight.innerHTML = '이메일 형식에 맞춰서 입력해 주세요.';
            } else {
                $memEmailRight.innerHTML = '';
                flag1 = true;
            }
        }

        $memPassword.onkeyup = function () {
            const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/;
            if (!$memPassword.value) {
                $memPasswordRight.innerHTML = '비밀번호는 필수값입니다!';
                $memPasswordRight.style.color = '#f21a3f';
            } else if (!pwRegex.test($memPassword.value)) {
                $memPasswordRight.innerHTML = '8글자 이상의 영문,숫자,특수문자를 포함해주세요!';
                $memPasswordRight.style.color = '#f21a3f';
            } else {
                $memPasswordRight.innerHTML = '';
                flag2 = true;
            }
        };

        $loginBtn.onclick = function () {
            if (flag1 && flag2) {
                const userValue = {
                    memEmail: $memEmail.value,
                    memPassword: $memPassword.value
                }
                fetch('/member/signin', {
                    method: 'POST',
                    headers: {
                        'content-type':'application/json; charset=utf-8;'
                    },
                    body: JSON.stringify(userValue)
                }).then(t => t.json())
                    .then(result => {
                        if (result.message) {
                            alert(result.message);
                        } else {
                                document.cookie = "ACCESS_TOKEN="+result.token+"; path=/; max-age=1*60*30;"
                                document.cookie = "LOGIN_USEREMAIL="+result.memEmail+"; path=/; max-age=1*60*30;"
                                document.cookie = "LOGIN_NICKNAME="+result.memNickname+"; path=/; max-age=1*60*30;"
                                localStorage.clear()
                                window.location.href = '/';
                        }
                    })
            }
        }
    </script>
</body>

</html>