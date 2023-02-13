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

    <link rel="icon" href="nakocon.ico">
    <link rel="apple-touch-icon" href="nakocon.ico">

    <!-- css -->
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/main-responsive.css">
    <link rel="stylesheet" href="/css/animate.min.css">
    <link rel="stylesheet" href="/css/slick.css">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body>
    <jsp:include page="./header/header.jsp" />
    <div class="wrap">
        <!-- section visual -->
        <section class="visual">
            <ul class="slide">
                <li>
                    <img src="https://cdn.clien.net/web/api/file/F01/13174997/2fdb6acdb5ecb3.jpg" alt="슬라이드 이미지2">
                    <div class="txt">
                        <!-- <p>Creative design with Hong Studio</p> -->
                        <h3 class="origin_p">Interact with<br>Our K-POP IDOL</h3>
                        <h3 class="myIdolName">NewJeans</h3>
                        <a href="#">More Contents<span class="lnr lnr-chevron-right"></span></a>
                    </div>
                </li>
                <li>
                    <img src="https://4kwallpapers.com/images/wallpapers/blackpink-pubg-mobile-pink-background-2560x1440-3105.jpg"
                        alt="슬라이드 이미지3">
                    <div class="txt">
                        <!-- <p>Creative design with Hong Studio</p> -->
                        <h3 class="origin_p">Interact with<br>Our K-POP IDOL</h3>
                        <h3 class="myIdolName">BlackPink</h3>
                        <a href="#">More Contents<span class="lnr lnr-chevron-right"></span></a>
                    </div>
                </li>
                <li>
                    <img src="https://rare-gallery.com/uploads/posts/340441-Aespa-Kpop-K-pop-Girl-Group-Girls-Members-Karina-Ningning-Giselle-Winter.jpg"
                        alt="슬라이드 이미지1">
                    <div class="txt">
                        <!-- <p>Creative design with Hong Studio</p> -->
                        <h3 class="origin_p">Interact with<br>Our K-POP IDOL</h3>
                        <h3 class="myIdolName">AESPA</h3>
                        <a href="#">More Contents<span class="lnr lnr-chevron-right"></span></a>
                    </div>
                </li>
                <li>
                    <img src="https://pbs.twimg.com/media/FFgr7uBVIAAoYYx.jpg:large" alt="슬라이드 이미지4">
                    <div class="txt">
                        <!-- <p>Creative design with Hong Studio</p> -->
                        <h3 class="origin_p">Interact with<br>Our K-POP IDOL</h3>
                        <h3 class="myIdolName">IVE</h3>
                        <a href="#">More Contents<span class="lnr lnr-chevron-right"></span></a>
                    </div>
                </li>
            </ul>
        </section>
        <!-- //section visual -->

        <!-- section contents -->
        <section class="contents">
            <div class="project">
                <div class="top">
                    <!-- magenta -->
                    <h3 class="animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">Meet New K-POP IDOL
                    </h3>
                </div>
                <ul class="animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://images.chosun.com/resizer/gst8Y76TWvb559SjLSh43AmEUrU=/2000x1333/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/GO7X3DGRXZDGAPFJJRGNZBR4VI.jpg"
                                alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>NewJeans</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="/board/1">
                            <img class="mainIdolImgPo"
                                src="https://4kwallpapers.com/images/wallpapers/blackpink-pubg-mobile-pink-background-2560x1440-3105.jpg"
                                alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>BlackPink</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://rare-gallery.com/uploads/posts/340441-Aespa-Kpop-K-pop-Girl-Group-Girls-Members-Karina-Ningning-Giselle-Winter.jpg"
                                alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>AESPA</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo" src="https://pbs.twimg.com/media/FFgr7uBVIAAoYYx.jpg:large"
                                alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>IVE</h3>
                            </div>
                        </a>
                    </li>

                </ul>
                <ul class="animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://dbkpop.com/wp-content/uploads/2022/09/le_sserafim_antifragile_teaser_frozen_aquamarine_all_group_2.jpg"
                                alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>LESSERAFIM</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://img.khan.co.kr/news/2022/11/08/news-p.v1.20221108.aabaa9e1f02f4e149257b60cf19c2b2f_P1.jpg"
                                alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>(G)-IDLE</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo" src="https://wallpapercave.com/wp/wp4390658.jpg" alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>TWICE</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img class="mainIdolImgPo"
                                src="https://cdn.clien.net/web/api/file/F01/13158556/2e6e71a7c8886b.jpg" alt="">
                            <div class="name">
                                <span>K-POP IDOL</span>
                                <h3>ITZY</h3>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </section>
        <!-- //section contents -->

        <!-- footer-->
        <footer>
            <div class="footer-content">
                <ul class="socials">
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li>|</li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li>|</li>
                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                    <li>|</li>
                    <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                    <li>|</li>
                    <li><a href="#"><i class="fa fa-linkedin-square"></i></a></li>
                </ul>
            </div>
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