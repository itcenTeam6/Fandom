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
    <title>Hong Studio</title>
    
    <meta name="author" content="Soon9">
    <meta name="description" content="Soon9's web studio">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- http://ogp.me/ -->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Soon9's web studio">
    <meta property="og:title" content="Soon9's web studio">
    <meta property="og:description" content="Soon9's web studio">
    <meta property="og:image" content="/img/visual2.jpg">
    <meta property="og:url" content="https://soongu.github.io">

    <!-- https://developer.twitter.com/en/docs/tweets/optimize-with-cards/guides/getting-started.html -->
    <meta property="twitter:card" content="summary">
    <meta property="twitter:site" content="Soon9's web studio">
    <meta property="twitter:title" content="Soon9's web studio">
    <meta property="twitter:description" content="Soon9's web studio">
    <meta property="twitter:image" content="/img/visual2.jpg">
    <meta property="twitter:url" content="https://soongu.github.io">

    <!-- 파비콘 정의 -->
    <link rel="icon" href="nakocon.ico">
    <link rel="apple-touch-icon" href="nakocon.ico">

    <!-- css -->
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/main-responsive.css">
    <!-- https://animate.style/ -->
    <link rel="stylesheet" href="/css/animate.min.css">
    <!-- https://kenwheeler.github.io/slick/ -->
    <link rel="stylesheet" href="/css/slick.css">
    <!-- https://linearicons.com/free -->
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
</head>

<body>

<div class="wrap">
    <!-- header -->
    <header>
        <div class="inner-header">
            <h1 class="logo">
                <a href="#">
                    <img src="/img/logo.png" alt="logo">
                    <img src="/img/logo2.png" alt="logo">
                </a>
            </h1>
            <h2 class="intro-text">Welcome</h2>
            <a href="#" class="menu-open"><span class="menu-txt">MENU</span> <span class="lnr lnr-menu menu-img"></span></a>
        </div>
        <!-- gnb -->
        <nav class="gnb">
            <a href="#" class="close">
                <span class="lnr lnr-cross"></span>
            </a>
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Work</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">News</a></li>
                <li><a href="#">Favorite</a></li>
            </ul>
        </nav>

    </header>
    <!-- //header -->

    <!-- section visual -->
    <section class="visual">
        <ul class="slide">
            <li>
                <img src="/img/visual1.jpg" alt="슬라이드 이미지1">
                <div class="txt">
                    <p>Creative design with Hong Studio</p>
                    <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                    <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                </div>
                <span class="scroll">Scroll down</span>
            </li>
            <li>
                <img src="/img/visual2.jpg" alt="슬라이드 이미지2">
                <div class="txt">
                    <p>Creative design with Hong Studio</p>
                    <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                    <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                </div>
                <span class="scroll">Scroll down</span>
            </li>
            <li>
                <img src="/img/visual3.jpg" alt="슬라이드 이미지3">
                <div class="txt">
                    <p>Creative design with Hong Studio</p>
                    <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                    <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                </div>
                <span class="scroll">Scroll down</span>
            </li>
            <li>
                <img src="/img/visual4.jpg" alt="슬라이드 이미지4">
                <div class="txt">
                    <p>Creative design with Hong Studio</p>
                    <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                    <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                </div>
                <span class="scroll">Scroll down</span>
            </li>
            <li>
                <img src="/img/visual5.jpg" alt="슬라이드 이미지5">
                <div class="txt">
                    <p>Creative design with Hong Studio</p>
                    <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                    <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                </div>
                <span class="scroll">Scroll down</span>
            </li>
            <li>
                <img src="/img/visual6.jpg" alt="슬라이드 이미지6">
                <div class="txt">
                    <p>Creative design with Hong Studio</p>
                    <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                    <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                </div>
                <span class="scroll">Scroll down</span>
            </li>
        </ul>
    </section>
    <!-- //section visual -->

    <!-- section contents -->
    <section class="contents">
        <div class="main-text animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">
            <h3>Great experience <br> build great brands.</h3>
            <p>다양한 온라인 서비스들을 구축하고 운영해온 풍부한 경험으로<br class="mo">
                UI/UX 기획과 디자인 그리고 구축에서 운영까지, 우리 스튜디오는<br class="mo">
                고객의 비즈니스를 위해 수준 높은 결과물을 제작하는 크리에이티브<br class="mo">
                파트너(Creative Partner) 입니다.
                <span>Soongu Hong, HONG STUDIO</span></p>
        </div>

        <div class="project">
            <div class="top">
                <h3 class="animate" data-animate="fadeInLeft" data-duration="1s" data-delay="1s">Meet <br>Out Projects</h3>
                <a href="#">More Projects
                    <span class="lnr lnr-chevron-right"></span>
                </a>
            </div>
            <ul class="animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">
                <li>
                    <a href="#">
                        <img src="/img/project1.jpg" alt="">
                        <div class="name">
                            <span>Website</span>
                            <h3>Linkin Aviation</h3>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/img/project2.jpg" alt="">
                        <div class="name">
                            <span>Website</span>
                            <h3>HEESUNG LMS</h3>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/img/project3.png" alt="">
                        <div class="name">
                            <span>Website</span>
                            <h3>ORPHAN</h3>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/img/project4.jpg" alt="">
                        <div class="name">
                            <span>Website</span>
                            <h3>APMA WEB/APP</h3>
                        </div>
                    </a>
                </li>
            </ul>
        </div>

        <div class="partner">
            <h3>우리의 소중한 고객입니다.</h3>
            <ul class="animate" data-animate="fadeInRight" data-duration="1s" data-delay="0s">
                <li><img src="/img/partner1.png" alt=""></li>
                <li><img src="/img/partner2.png" alt=""></li>
                <li><img src="/img/partner3.png" alt=""></li>
                <li><img src="/img/partner4.png" alt=""></li>
                <li><img src="/img/partner5.png" alt=""></li>
                <li><img src="/img/partner6.png" alt=""></li>
                <li><img src="/img/partner7.png" alt=""></li>
                <li><img src="/img/partner8.png" alt=""></li>
            </ul>
        </div>

        <div class="contact animate" data-animate="fadeIn" data-duration="2s" data-delay="0.3s">
            <div class="inner">
                <h3>당신의 프로젝트를 이야기해 주세요.</h3>
                <p class="tel">010.7270.9984</p>
                <p>모바일 앱/웹 디자인 및 웹 표준화 작업 <br> UI/UX설계 및 제안서 작성<br> 스프링 기반 웹사이트 구축 등을 제공합니다.</p>
            </div>
        </div>

    </section>
    <!-- //section contents -->

    <!-- footer-->
    <footer>
        <ul>
            <li>Hong Studio<br>사업자등록번호:123-12-12345 <br>대표자:홍순구</li>
            <li>
                <span>A :</span>대전 서구 계룡로 637<br>
                <span>T :</span>010.7270.9984<br>
                <span>E :</span>hsg9984@kitt.co.kr<br>
            </li>
            <li> &#xa9 Hong Studio 2020 <br>All Rights Reseved</li>
        </ul>
        <a href="#top" class="go-top"><span class="lnr lnr-arrow-up"></span></a>
    </footer>
    <!-- //footer-->
</div>
<!-- script-->
<script src="/js/jquery-3.3.1.min.js"></script>
<!-- https://animate.style/ -->
<script src="/js/scrolla.jquery.min.js"></script>
<!-- https://kenwheeler.github.io/slick/ -->
<script src="/js/slick.min.js"></script>
<script src="/js/script.js"></script>
</body>
</html>