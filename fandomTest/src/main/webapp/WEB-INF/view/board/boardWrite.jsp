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
    <title>POST</title>
    
    <meta name="author" content="Soon9">
    <meta name="description" content="Soon9's web studio">
    <meta name="viewport"
        content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/boardWrite.css">
    <link rel="stylesheet" href="/css/innerPage.css">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

    <script text="text/javascript">
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

        function commentUpload() {
            document.getElementById("inputButton").click()
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
            <a href="#" class="menu-open"><span class="menu-txt">LogOut</span> <span class="menu-img"></span></a>
        </div>
        <div class="outer-header">
            <a href="javascript:LoadBoardList(${ idol.idolID })">Feed</a>
            <a href="javascript:LoadBoardWrite(${ idol.idolID })">Post</a>
            <a href="javascript:LoadIdolImg(${ idol.idolID })">Media</a>
        </div>
    </header>
    <!-- //header -->
    <section id="container">
        <div id="main_container">
            <div class="post_form_container">
                <form action="#" class="post_form" id="fileForm" name="fileForm">
                    <div class="title">
                        POST
                    </div>
                    <input type="file" name="userInputImg" id="userInputImg" style="display: none;">
                    <div class="preview" onclick="javascript:inputTagClick()">
                        <div class="upload">
                            <div class="post_btn">
                                <div class="plus_icon">
                                    <span></span>
                                    <span></span>
                                </div>
                                <p>Upload Image</p>
                                <canvas id="imageCanvas"></canvas>
                            </div>
                        </div>
                    </div>
                    <p>
                        <textarea name="content" id="text_field" cols="100" rows="15"
                            placeholder="Input your text"></textarea>
                    </p>
                    <input class="submit_btn" type="submit" value="Submit">
                </form>
            </div>
        </div>
    </section>

    <script>
        var fileInput = document.querySelector("#userInputImg");

        function handleImage(e) {
            var reader = new FileReader();
            reader.onload = function (event) {
                var img = new Image();
                img.onload = function () {
                    canvas.width = 300;
                    canvas.height = 300;
                    ctx.drawImage(img, 0, 0, 300, 300);
                };
                img.src = event.target.result;
            };
            reader.readAsDataURL(e.target.files[0]);
        }
        
        // Show image
        fileInput.addEventListener('change', handleImage, false);
        var canvas = document.getElementById('imageCanvas');
        var ctx = canvas.getContext('2d');


        function inputTagClick() {
            document.fileForm.userInputImg.click()
        }
    </script>
</body>

</html>