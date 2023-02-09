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
    <title>Default</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/my.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <main class="login-body slider-bg">
        <form class="form-default" id="frm-register" action="#" method="POST">
            <div class="login-form">
                <h2>Registration Here</h2>
                <div class="form-input">
                    <label for="name">Email Address</label>
                    <input type="email" name="userEmail" id="userEmail" placeholder="Email Address">
                </div>
                <div class="form-input">
                    <label for="name">Password</label>
                    <input type="password" name="userPw" id="userPw" placeholder="Password">
                </div>
                <div class="form-input">
                    <label for="name">Confirm Password</label>
                    <input type="password" name="userPw-confirm" id="userPw-confirm" placeholder="Confirm Password">
                </div>
                <div class="form-input">
                    <label for="name">Nick name</label>
                    <input type="text" name="userName" id="userName" placeholder="Nick name">
                </div>
                <div class="form-input pt-30">
                    <input type="submit" name="submit" value="Registration">
                </div>
            </div>
        </form>
    </main>
    <script src="/js/bootstrap.min.js"></script>
</body>

</html>