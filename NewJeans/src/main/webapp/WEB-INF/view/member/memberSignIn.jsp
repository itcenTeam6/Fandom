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
    <title>App landing</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/memberStyle.css">
    <link rel="stylesheet" href="/css/my.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <main class="login-body slider-bg">
        <div class="login-form">
            <form class="form-default" id="frm-login" action="#" method="POST">
                <h2>Login</h2>
                <div class="form-input">
                    <label for="name">Email</label> <input type="email" name="userEmail" id="memEmail" placeholder="Email">
                        <span id="memEmailRight"></span>
                </div>
                <div class="form-input">
                    <label for="name">Password</label> <input type="password" name="userPw" id="memPassword" placeholder="Password">
                        <span id="memPasswordRight"></span>
                </div>
                <div class="form-input pt-30">
                    <input type="submit" name="submit" value="login" id="loginBtn">
                </div>
            </form>
            <!-- Forget Password -->
            <!-- <a href="#" class="forget">Forget Password</a> -->
            <div class="text-center">
            <p class="mem">Not a member?</p>
            <a href="#" class="registration">Registration</a>
            </div>
        </div>
    </main>
    <script src="/js/bootstrap.min.js"></script>
<script>
let flag1=false;
let flag2=false;


const $memEmail=document.getElementById('memEmail');
const $memEmailRight=document.getElementById('memEmailRight');
const $memPassword=document.getElementById('memPassword');
const $memPasswordRight=document.getElementById('memPasswordRight');

const $signUpBtn=document.getElementById('signUpBtn');
const $loginBtn=document.getElementById('loginBtn');



$memEmail.onkeyup=function() {
        const emailRegex = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/;

        if (!$memEmail.value) {
            $memEmailRight.style.color='white';
            $memEmailRight.innerHTML ='아이디는 필수로 입력해야 합니다.';

        }else if (!emailRegex.test($memEmail.value)) {
         $memEmailRight.style.color='white';
              $memEmailRight.innerHTML ='이메일 형식에 맞춰서 입력해 주세요.';
        }else{
            $memEmailRight.innerHTML ='';
            flag1=true;
        }

}


$memPassword.onkeyup=function() {
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/;
    if (!$memPassword.value) {
                $memPasswordRight.innerHTML ='비밀번호는 필수값입니다!';
                 $memPasswordRight.style.color='white';
            } else if (!pwRegex.test($memPassword.value)) {
                $memPasswordRight.innerHTML = '8글자 이상의 영문,숫자,특수문자를 포함해주세요!';
                  $memPasswordRight.style.color='white';
            } else {
                $memPasswordRight.innerHTML = '';
                flag2=true;
            }

};

$loginBtn.onclick=function(){
    if(flag1 &&flag2){
           const userValue={
                    memEmail:$memEmail.value,
                    memPassword:$memPassword.value
            }
              fetch('/member/signup',{
                            method:'POST',
                            headers:{
                                'content-type':'application/json'
                            },
                            body:JSON.stringify(userValue)
                        })
                            .then(result => {
                                       if(result.message){
                                           alert(result.message);
                                       }else{

                                           sessionStorage.setItem('ACCESS_TOKEN',result.token);
                                           sessionStorage.setItem('LOGIN_USERNAME',result.userName);
                                           window.location.href='/';
                                       }
                                   })
   }
}



</script>
</body>
</html>
