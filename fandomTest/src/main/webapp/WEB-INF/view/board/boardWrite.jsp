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

</head>

<body>
    <jsp:include page="../header/innerHeader.jsp" />
    <section id="container">
        <div id="main_container">
            <div class="post_form_container">
                <form action="${cpath}/board/boardWrite.do" class="post_form" id="fileForm" name="fileForm" method="POST" enctype="multipart/form-data">
                    <div class="title">
                        POST
                    </div>
                    <input type="file" id="InputImg" name="InputImg" accept=".jpg, .jpeg, .png" style="display: none;">
                    <input type="text" id="IdolID" name="IdolID" value="${idolID}" style="display: none;">
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
                        <textarea name="InputTxt" id="text_field" cols="100" rows="15"
                            placeholder="Input your text"></textarea>
                    </p>
                    <input class="submit_btn" type="submit" value="Submit">
                </form>
            </div>
        </div>
    </section>

    <script>
        var fileInput = document.querySelector("#InputImg");

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
            document.fileForm.InputImg.click()
        }
    </script>
</body>

</html>