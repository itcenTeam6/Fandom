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
    <title>FEED</title>

    <meta name="author" content="Soon9">
    <meta name="description" content="Soon9's web studio">
    <meta name="viewport"
        content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/boardList.css">
    <link rel="stylesheet" href="/css/innerPage.css">
    <link rel="stylesheet" href="/css/animate.min.css">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <script text="text/javascript">
        function commentUpload() {
            document.getElementById("inputButton").click()
        }

        function JoinMemberShip(){
            alert("멤버십 가입하시겠습니까??")
            location.href = "${cpath}/idolImg/join?idol-id=" + "${idolID}";
        }
    </script>

</head>
<body>
    <jsp:include page="../header/innerHeader.jsp" />
    <section id="container">
        <section id="main_container">
            <div class="inner">
                <div class="contents_box">
                    <!-- Article =============================================== -->
                    <c:forEach var="boardItem" items="${listBoard.boards}">
                        <article class="contents">
                            <div class="top">
                                <div class="user_container">
                                    <div class="profile_img">
                                        <img src="/img/userProfile.png">
                                    </div>
                                    <div class="user_name">
                                        <div class="nick_name m_text">${ boardItem.memNickName }</div>
                                        <div class="country s_text">${ boardItem.boardDate }</div>
                                    </div>
                                </div>
                            </div>

                            <div class="img_section">
                                <div class="trans_inner">
                                    <div>
                                        <c:if test="{!empty boardItem.boardFile }">
                                            <img src="${ boardItem.boardFile }" alt="visual01">
                                        </c:if>
                                        <p>${ boardItem.boardContent }</p>
                                    </div>
                                </div>
                            </div>

                            <div class="bottom_icons">
                                <div class="left_icons">
                                    <div class="heart_btn">
                                        <span class="lnr lnr-pencil"></span>
                                    </div>
                                    <div class="heart_btn">
                                        <span class="lnr lnr-trash"></span>
                                    </div>
                                </div>
                            </div>

                            <!-- Comment ============= -->
                            <div class="showComment">
                                <div class="commentBox">
                                    <c:forEach var="comment" items="${boardItem.comments}">
                                        <div class="commentSet">
                                            <div class="commentUser">
                                                <span class="commentId">${ comment.memNickName }</span>
                                            </div>
                                            <div class="commentContents">
                                                ${ comment.cmtContent }
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="comment_field" id="add-comment-post37">
                                <div class="replyComment">
                                    <form class="replyForm" action="">
                                        <input class="replyInput" type="text" placeholder="댓글달기...">
                                        <div class="upload_btn m_text" data-name="comment"
                                            onclick="javascript:commentUpload()">게시</div>
                                        <button id="inputButton" class="replyBtn" style="display: none;"></button>
                                    </form>
                                </div>
                            </div>
                        </article>
                    </c:forEach>

                    <!-- Side Box============================================== -->
                    <div class="side_box">
                        <div class="user_profile">
                            <div class="profile_thumb">
                                <img src="/img/userProfile.png" alt="프로필사진">
                            </div>
                            <div class="detail">
                                <div class="id m_text_profile">${ emailAndNick.memNickName }</div>
                                <div class="ko_name">${ emailAndNick.memEmail }</div>
                            </div>
                        </div>
                        <article class="recommend">
                            <div class="myprofile_thumb">
                                <img
                                    src="${ idol.idolSubImg }">
                                <h1 class="thumb_text">${ idol.idolName }</h1>
                                <div class="thumb_box"></div>
                            </div>
                        </article>
                        <c:if test="${memberShipForBoard eq 'false'}">
                            <button class="memberShipBtn" onclick="javascript:JoinMemberShip()">Join MemberShip !!</button>
                        </c:if>
                    </div>
                </div>
        </section>
    </section>
    <footer>
        <a href="#top" class="go-top"><span class="lnr lnr-arrow-up"></span></a>
    </footer>

    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/scrolla.jquery.min.js"></script>
    <script src="/js/slick.min.js"></script>
    <script src="/js/script.js"></script>
    <script>
        /*
        대충 어케어케 현재 유저 닉네임이든 아이디든 받아서 넣을 수 있도록
        대충 어케어케 게시글 pk 받아서 querySelector로 선택할 수 있도록
        */
        let replyInput = document.querySelector(".replyInput");
        let replyBtn = document.querySelector(".replyBtn");
        let commentBox = document.querySelector(".commentBox");
        let commentContainer = document.querySelector(".showComment");

        replyInput.addEventListener("keydown", submitEnter);
        replyBtn.addEventListener("click", makeComment);

        function submitEnter(event) {
            if (event.keycode === 13) {
                makeComment();
            }
        }

        function makeComment(e) {
            e.preventDefault();

            let commentText = replyInput.value;
            let newCommentBox = document.createElement("div");
            let newCommentSet = document.createElement("div");
            let newCommentUser = document.createElement("div")
            let newCommentId = document.createElement("span");
            let newCommentContents = document.createElement("div");

            newCommentBox.setAttribute("class", "commentBox");
            newCommentSet.setAttribute("class", "commentSet");
            newCommentUser.setAttribute("class", "commentUser")
            newCommentId.setAttribute("class", "commentId");
            newCommentContents.setAttribute("class", "commentContents");

            newCommentId.innerText = "testUser";
            newCommentContents.innerText = commentText;

            commentContainer.appendChild(newCommentBox);
            newCommentBox.appendChild(newCommentSet);
            newCommentSet.appendChild(newCommentUser);
            newCommentUser.appendChild(newCommentId);
            newCommentSet.appendChild(newCommentContents);
            initInput();
        }

        function initInput() {
            replyInput.value = "";
        }

    </script>
</body>

</html>