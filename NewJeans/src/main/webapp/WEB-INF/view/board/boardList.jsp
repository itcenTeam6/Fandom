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

    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/boardList.css">
    <link rel="stylesheet" href="/css/innerPage.css">
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



</head>

<body>
    <jsp:include page="../header/innerHeader.jsp" />
    <section id="container">
        <section id="main_container">
            <div class="inner">
                <div class="contents_box">
                 <!-- Article =============================================== -->
                 <c:forEach var="board" items="${ListBoardResponseDTO.boards}">
                    <article class="contents">
                        <div class="top">
                            <div class="user_container">
                                <div class="profile_img">
                                    <img src="/img/userProfile.png">
                                </div>
                                <div class="user_name">
                                    <div class="country s_text">
                                         <div class="nick_name m_text"></div>
                                            <div class="country s_text">${board.boardDate}</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="img_section">
                            <div class="trans_inner">
                             <img src="data:image/jpeg;base64,${board.boardFilePath}">

                             <div>${board.boardContent}</div>
                        </div>

                        <div class="bottom_icons">
                            <div class="left_icons">
                                <div class="heart_btn">

                                    <span class="lnr lnr-pencil"></span>
                                </div>


                                    <div class="heart_btn" id="trash2" value="${board.boardId}">
                                    <c:if test="${member eq board.member.memID}">
                                        <span class="lnr lnr-trash" id="trash" onclick="deleteByBoardId(${board.boardId})"></span>
                                    </c:if>
                                    </div>


                            </div>
                        </div>

                        <!-- Comment ============= -->
                        <div class="showComment">
                            <div class="commentBox">
                                <div class="commentSet">
                                    <div class="commentUser">
                                        <span class="commentId"></span>
                                    </div>
                                    <div class="commentContents">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="comment_field" id="add-comment-post37">
                            <div class="replyComment">
                             <button id="inputButton" class="replyBtn">댓글</button>
                                <form class="replyForm" name="commentInsertForm" action="" >
                                    <input class="replyInput" type="text" placeholder="댓글달기...">
                                    <div class="upload_btn m_text" data-name="comment"
                                        onclick="javascript:commentUpload(${board.boardId})">게시</div>
                                </form>
                            </div>
                        </div>
                    </article>
                  </c:forEach>
                     <!-- Article =============================================== -->



                    <!-- Side Box============================================== -->
                    <div class="side_box">
                        <div class="user_profile" " >
                            <div class="profile_thumb">
                                <img src="/img/userProfile.png" alt="프로필사진">
                            </div>
                            <div class="detail">
                                <div class="id m_text_profile">${userId}</div>

                            </div>
                        </div>
                        <article class="recommend">

                            <div class="myprofile_thumb">
                                <img src="${IdolMainImg}">
                                <h1 class="thumb_text">"${IdolName}"</h1>
                                <div class="thumb_box"></div>
                            </div>
                        </article>
                    </div>
                </div>
        </section>
    </section>
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/boardList.js"></script>
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
        let replyForm = document.querySelector(".replyForm");
        let commentBox = document.querySelector(".commentBox");
        let commentContainer = document.querySelector(".showComment");
        let trash=document.querySelector("#trash");
        let commentCreate=document.querySelector(".m_text");


        replyInput.addEventListener("keydown", submitEnter);
        replyBtn.addEventListener("click", makeComment);
        function submitEnter(event) {
            if (event.keycode === 13) {
                makeComment();
            }
        }

        replyBtn.onclick=function(){
            replyForm.style.display='block';
        }


        /* 게시글 삭제 (유저 본인이 쓴 게시물 아니면 삭제  x  */
        function deleteByBoardId(boardId){
                let idolId='${IdolId}';
                var chk = confirm("정말 삭제하시겠습니까?");
                 if (chk) {
                    location.href='/board/'+idolId+'/'+boardId
                  }

        }

















    </script>
</body>
</html>