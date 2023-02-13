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
    <title>Media</title>

    <meta name="author" content="Soon9">
    <meta name="description" content="Soon9's web studio">
    <meta name="viewport"
        content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/idolimg.css">
    <link rel="stylesheet" href="/css/innerPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">
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
    <section id="img_container">
        <div id="profile_container">
            <div class="container gallery-container">
                <div class="tz-gallery">
                    <div class="row myRow">
                        <!-- MemberShip 회원용 -->
                        <div class="mycol-md-2">
                            <a class="lightbox"
                                href="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA5MTJfNzkg%2FMDAxNjYyOTU2MTA5NTMy.Tk7IpleKqqzX-7HyhX8toMU881oUjV6QWmsW4gAEihIg.X5NJvQ-T9W7eVtHYSPaQO3xq--BS71APLB4PSNuiyMYg.JPEG.youth629%2F20220912%25A3%25DF023603.jpg&type=a340">
                                <img class="img-fluid"
                                    src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA5MTJfNzkg%2FMDAxNjYyOTU2MTA5NTMy.Tk7IpleKqqzX-7HyhX8toMU881oUjV6QWmsW4gAEihIg.X5NJvQ-T9W7eVtHYSPaQO3xq--BS71APLB4PSNuiyMYg.JPEG.youth629%2F20220912%25A3%25DF023603.jpg&type=a340"
                                    alt="Park">
                            </a>
                        </div>
                        <!-- Non MemberShip 회원용 -->
                        <div class="mycol-md-2">
                            <a class="lightbox">
                                <div class="memberShip">
                                    <div class="memberShipImg">
                                        <img class="img-fluid myBlur"
                                            src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA4MjZfMTA1%2FMDAxNjYxNDkwNzIwNzY0.62lEXCofM3sv9j8jM8RDyN-b8Q-3NbPBZh14lE5iZGgg.mHNooYdEivkYQKqON_-kW1gArBDAvHvQU6Lt_Lziabkg.JPEG.niceguy00%2FSeul_%25B4%25BA%25C1%25F8%25BD%25BA_%25BE%25EE%25C5%25D9%25BC%25C7_%25C7%25FD%25C0%25CE69.jpg&type=ofullfill340_600_png"
                                            alt="Bridge">
                                    </div>
                                    <div class="memberShipTxt">
                                        <h5>MemberShip Only</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pagingDiv">
                <ul class="pagination">
                    <li class="disabled"><a class="myPage">&laquo;</a></li>
                    <li class="active"><a class="myPage">1</a></li>
                    <li><a class="myPage" href="idolImg.html">2</a></li>
                    <li><a class="myPage" href="#">3</a></li>
                    <li><a class="myPage" href="#">4</a></li>
                    <li><a class="myPage" href="#">5</a></li>
                    <li><a class="myPage" href="#">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </section>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js"></script>
    <script>
        baguetteBox.run('.tz-gallery');
    </script>
</body>

</html>