<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

</head>

<body>
    <jsp:include page="../header/innerHeader.jsp" />
    <section id="img_container">
        <div id="profile_container">
            <div class="container gallery-container">
                <div class="tz-gallery">
                    <div class="row myRow">
                        <c:forEach var="idolImg" items="${idolImgList.idolImages}">
                            <c:if test="${idolImg.msType eq 'yes'}">
                                <!-- MemberShip 회원용 -->
                                <div class="mycol-md-2">
                                    <a class="lightbox" href="${idolImg.imgPath}">
                                        <img class="img-fluid"
                                            src="${idolImg.imgPath}"
                                            alt="Park">
                                    </a>
                                </div>
                            </c:if>
                            <c:if test="${idolImg.msType eq 'no'}">
                                <!-- Non MemberShip 회원용 -->
                                <div class="mycol-md-2">
                                    <a class="lightbox">
                                        <div class="memberShip">
                                            <div class="memberShipImg">
                                                <img class="img-fluid myBlur"
                                                    src="${idolImg.imgPath}"
                                                    alt="Bridge">
                                            </div>
                                            <div class="memberShipTxt">
                                                <h5>MemberShip Only</h5>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="pagingDiv">
                <ul class="pagination">
                    <c:if test="${not idolImgList.hasPrevious}">
                        <li class="disabled"><a class="myPage">&laquo;</a></li>
                    </c:if>
                    <c:if test="${idolImgList.hasPrevious}">
                        <li><a class="myPage" href="${cpath}/idolImg/idolImg.do?idolID=${idolID}&page=${idolImgList.page}">&laquo;</a></li>
                    </c:if>
                    <c:forEach var="showPage" begin="${idolImgList.startPage}" end="${ idolImgList.endPage }">
                        <c:if test="${showPage == idolImgList.page+1}">
                            <li class="active"><a class="myPage">${ showPage }</a></li>
                        </c:if>
                        <c:if test="${showPage != idolImgList.page+1}">
                            <li><a class="myPage" href="${cpath}/idolImg/idolImg.do?idolID=${idolID}&page=${showPage}">${ showPage }</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${not idolImgList.hasNext}">
                        <li class="disabled"><a class="myPage">&raquo;</a></li>
                    </c:if>
                    <c:if test="${idolImgList.hasNext}">
                        <li><a class="myPage" href="${cpath}/idolImg/idolImg.do?idolID=${idolID}&page=${idolImgList.page+2}">&raquo;</a></li>
                    </c:if>
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