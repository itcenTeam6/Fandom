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
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

    <link rel="icon" href="nakocon.ico">
    <link rel="apple-touch-icon" href="nakocon.ico">

    <!-- css -->
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/main-responsive.css">
    <link rel="stylesheet" href="/css/animate.min.css">
    <link rel="stylesheet" href="/css/slick.css">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
      <script type="text/javascript">
        function LoadIdolFeed(idolID){
            location.href = "${cpath}/board/" + String(idolID);
        }

        function LoadLogin(){
            location.href = "${cpath}/member/signin"
        }
      </script>
</head>

<body>
    <jsp:include page="./header/header.jsp" />
    <div class="wrap">
        <!-- section visual -->
        <section class="visual">
            <ul class="slide">
                <c:forEach var="idol" items="${idolList}">
                    <li>
                        <img src="${ idol.idolMainImg }" alt="슬라이드 이미지">
                        <div class="txt">
                            <h3 class="origin_p">Interact with<br>Our K-POP IDOL</h3>
                            <h3 class="myIdolName">${ idol.idolName }</h3>
                            <c:if test="${cookieValue eq 'true'}">
                                <a href="javascript:LoadIdolFeed(${ idol.idolID })">More Contents<span class="lnr lnr-chevron-right"></span></a>
                            </c:if>
                            <c:if test="${cookieValue eq 'false'}">
                                <a href="javascript:LoadLogin()">More Contents<span class="lnr lnr-chevron-right"></span></a>
                            </c:if>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </section>
        <!-- //section visual -->

        <!-- section contents -->
        <section class="contents">
            <div class="project">
                <div class="top">
                    <h3 class="animate" data-animate="fadeInUp" data-duration="1s" data-delay="0s">Meet New K-POP IDOL </h3>
                </div>
                <ul>
                    <c:forEach var="idol" items="${ idolList }">
                        <li>
                            <c:if test="${cookieValue eq 'true'}">
                                <a href="javascript:LoadIdolFeed(${ idol.idolID })">
                            </c:if>
                            <c:if test="${cookieValue eq 'false'}">
                                <a href="javascript:LoadLogin()">
                            </c:if>
                                <img class="mainIdolImgPo"
                                    src="${ idol.idolMainImg }">
                                <div class="name">
                                    <span>K-POP IDOL</span>
                                    <h3>${ idol.idolName }</h3>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
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