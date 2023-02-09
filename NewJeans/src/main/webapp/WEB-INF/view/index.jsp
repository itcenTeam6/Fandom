<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

    <!-- 파비콘 정의 -->
    <link rel="icon" href="nakocon.ico">
    <link rel="apple-touch-icon" href="nakocon.ico">

    <!-- css -->
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/main-responsive.css">
    <link rel="stylesheet" href="/css/animate.min.css">
    <link rel="stylesheet" href="/css/slick.css">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
</head>

<body>
    <jsp:include page="./header/header.jsp" />
    <div class="wrap">
        <!-- section visual -->
        <section class="visual">
            <ul class="slide">
                <li>
                    <img src="https://pbs.twimg.com/media/FFgr7uBVIAAoYYx.jpg:large" alt="슬라이드 이미지1">
                    <div class="txt">
                        <p>Creative design with Hong Studio</p>
                        <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                        <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                    </div>
                    <span class="scroll">Scroll down</span>
                </li>
                <li>
                    <img src="https://images.chosun.com/resizer/gst8Y76TWvb559SjLSh43AmEUrU=/2000x1333/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/GO7X3DGRXZDGAPFJJRGNZBR4VI.jpg"
                        alt="슬라이드 이미지2">
                    <div class="txt">
                        <p>Creative design with Hong Studio</p>
                        <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                        <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                    </div>
                    <span class="scroll">Scroll down</span>
                </li>
                <li>
                    <img src="https://4kwallpapers.com/images/wallpapers/blackpink-pubg-mobile-pink-background-2560x1440-3105.jpg"
                        alt="슬라이드 이미지3">
                    <div class="txt">
                        <p>Creative design with Hong Studio</p>
                        <h3>Website & APP, <br>Responsive Web and <br>Brand Identity </h3>
                        <a href="#">See the work <span class="lnr lnr-chevron-right"></span></a>
                    </div>
                    <span class="scroll">Scroll down</span>
                </li>
                <li>
                    <img src="https://dbkpop.com/wp-content/uploads/2022/09/le_sserafim_antifragile_teaser_frozen_aquamarine_all_group_2.jpg"
                        alt="슬라이드 이미지4">
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
            <!-- <div class="main-text animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">
            <h3>Great experience <br> build great brands.</h3>
            <p>다양한 온라인 서비스들을 구축하고 운영해온 풍부한 경험으로<br class="mo">
                UI/UX 기획과 디자인 그리고 구축에서 운영까지, 우리 스튜디오는<br class="mo">
                고객의 비즈니스를 위해 수준 높은 결과물을 제작하는 크리에이티브<br class="mo">
                파트너(Creative Partner) 입니다.
                <span>Soongu Hong, HONG STUDIO</span></p>
        </div> -->

            <div class="project">
                <div class="top">
                    <!-- magenta -->
                    <h3 class="animate" data-animate="fadeInLeft" data-duration="1s" data-delay="0s">Meet <br>Out
                        Projects</h3>
                </div>
                <ul class="animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo" src="https://pbs.twimg.com/media/FFgr7uBVIAAoYYx.jpg:large"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>Linkin Aviation</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://4kwallpapers.com/images/wallpapers/blackpink-pubg-mobile-pink-background-2560x1440-3105.jpg"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>HEESUNG LMS</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://images.chosun.com/resizer/gst8Y76TWvb559SjLSh43AmEUrU=/2000x1333/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/GO7X3DGRXZDGAPFJJRGNZBR4VI.jpg"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>ORPHAN</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://dbkpop.com/wp-content/uploads/2022/09/le_sserafim_antifragile_teaser_frozen_aquamarine_all_group_2.jpg"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>APMA WEB/APP</h3>
                            </div>
                        </a>
                    </li>
                </ul>
                <ul class="animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo" src="https://pbs.twimg.com/media/FFgr7uBVIAAoYYx.jpg:large"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>Linkin Aviation</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://4kwallpapers.com/images/wallpapers/blackpink-pubg-mobile-pink-background-2560x1440-3105.jpg"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>HEESUNG LMS</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://images.chosun.com/resizer/gst8Y76TWvb559SjLSh43AmEUrU=/2000x1333/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/GO7X3DGRXZDGAPFJJRGNZBR4VI.jpg"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>ORPHAN</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://dbkpop.com/wp-content/uploads/2022/09/le_sserafim_antifragile_teaser_frozen_aquamarine_all_group_2.jpg"
                                alt="">
                            <div class="name">
                                <span>Website</span>
                                <h3>APMA WEB/APP</h3>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- blueviolet -->
            <div class="partner">
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
        </section>
        <!-- //section contents -->

        <!-- footer 따로 꾸며야됨-->
        <footer>
            <ul>
                <li style="margin-top: 30px;">Hong Studio<br>사업자등록번호:123-12-12345 <br>대표자:홍순구</li>
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
    <script src="/js/scrolla.jquery.min.js"></script>
    <script src="/js/slick.min.js"></script>
    <script src="/js/script.js"></script>
</body>

</html>