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

    <script text="text/javascript">
    


        function commentUpload(boardId, userEmail){
            const replyInput = document.getElementById("replyInput_" + boardId);
            const commentContainer = document.getElementById("showComment_" + boardId);

            const commentText = replyInput.value;
            const newCommentBox = document.createElement("div");
            const newCommentSet = document.createElement("div");
            const newCommentUser = document.createElement("div");
            const newDivCommentId = document.createElement("div");
            const newCommentId = document.createElement("span");
            const newCommentContents = document.createElement("div");

            newCommentBox.setAttribute("class", "commentBox");
            newCommentSet.setAttribute("class", "commentSet");
            newDivCommentId.setAttribute("style", "white-space: nowrap;")
            newCommentUser.setAttribute("class", "commentUser")
            newCommentId.setAttribute("class", "commentId");
            newCommentContents.setAttribute("class", "commentContents");

            $.ajax({
                url: "${cpath}/comment/commentSave",
                type: "POST",
                data : {
                    "boardId" : boardId,
                    "userEmail" : userEmail,
                    "commentText" : commentText
                },
                success: function(data){

                    newCommentId.innerText = data.userNickName;
                    newCommentContents.innerText = data.commentText;

                    commentContainer.appendChild(newCommentBox);
                    newCommentBox.appendChild(newCommentSet);
                    newCommentSet.appendChild(newCommentUser);
                    newCommentUser.appendChild(newCommentId);
                    newCommentSet.appendChild(newCommentContents);

                    replyInput.value = "";
                },
                error: function(){
                    alert("Error")
                }
            })
        }

        function JoinMemberShip() {
            alert("멤버십 가입하시겠습니까??")
            location.href = "${cpath}/idolImg/join?idol-id=" + "${idolID}";
        }

        function getDeletePost(boardId) {
            alert("게시글을 삭제하시겠습니까??")
            location.href = "${cpath}/board" + "/${idolID}/" + boardId;
        }

        function getUpdatePost(boardId) {
            alert("게시글을 수정하시겠습니까??")
            location.href = "${cpath}/board/updateForm?boardId=" + boardId + "&idolID=${idolID}";
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
                                        <c:if test="${boardItem.fileExist}">
                                            <img src="data:image/jpeg;base64,${ boardItem.boardFilePath }" alt="visual">
                                        </c:if>
                                        <p>${ boardItem.boardContent }</p>
                                    </div>
                                </div>
                            </div>

                            <div class="bottom_icons">
                                <div class="left_icons">
                                    <c:if test="${boardItem.member.memEmail eq userEmail}">
                                        <div class="heart_btn">
                                            <span class="lnr lnr-pencil"
                                                onclick="javascript:getUpdatePost(${ boardItem.boardId })"></span>
                                        </div>
                                        <div class="heart_btn">
                                            <span class="lnr lnr-trash"
                                                onclick="javascript:getDeletePost(${ boardItem.boardId })"></span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>

                            <!-- Comment ============= -->
                            <div class="showComment" id="showComment_${ boardItem.boardId }">
                                <c:forEach var="comment" items="${boardItem.comments}">
                                    <div class="commentBox" id="commentBox_${ boardItem.boardId }">
                                            <div class="commentSet" id="commentSet_${ boardItem.boardId }">
                                                <div class="commentUser" id="commentUser_${ boardItem.boardId }">
                                                    <span class="commentId" id="commentId_${ boardItem.boardId }">${ comment.memNickName }</span>
                                                </div>
                                                <div class="commentContents" id="commentContents_${ boardItem.boardId }">
                                                    ${ comment.cmtContent }
                                                </div>
                                            </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="comment_field" id="add-comment-post37">
                                <div class="replyComment">
                                    <input class="replyInput" type="text" placeholder="댓글달기..." id="replyInput_${ boardItem.boardId }">
                                    <div class="upload_btn m_text" data-name="comment" onclick="javascript:commentUpload('${ boardItem.boardId }', '${ userEmail }')">게시</div>
                                    <button id="inputButton" class="replyBtn" style="display: none;" id="replyBtn_${ boardItem.boardId }"></button>
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
                                <div class="id m_text_profile">${ userNick }</div>
                                <div class="ko_name">${ userEmail }</div>
                            </div>
                        </div>
                        <article class="recommend">
                            <div class="myprofile_thumb">
                                <img src="${ idol.idolSubImg }">
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
</body>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/scrolla.jquery.min.js"></script>
<script src="/js/slick.min.js"></script>
<script src="/js/script.js"></script>
</html>