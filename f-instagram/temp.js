function commentUpload(boardId, userEmail, userNick){
    const replyInput = document.getElementById("replyInput_" + boardId);
    const commentContainer = document.getElementById("commentBox_" + boardId);

    const commentText = replyInput.value;
    const newCommentBox = document.createElement("div");
    const newCommentSet = document.createElement("div");
    const newCommentUser = document.createElement("div")
    const newCommentId = document.createElement("span");
    const newCommentContents = document.createElement("div");

    newCommentBox.setAttribute("class", "commentBox");
    newCommentSet.setAttribute("class", "commentSet");
    newCommentUser.setAttribute("class", "commentUser")
    newCommentId.setAttribute("class", "commentId");
    newCommentContents.setAttribute("class", "commentContents");

    newCommentId.innerText = userNick;
    newCommentContents.innerText = commentText;

    commentContainer.appendChild(newCommentBox);
    newCommentBox.appendChild(newCommentSet);
    newCommentSet.appendChild(newCommentUser);
    newCommentUser.appendChild(newCommentId);
    newCommentSet.appendChild(newCommentContents);

    $.ajax({
        url: "${cpath}/comment/commentSave",
        type: "POST",
        data : {
            "boardId" : boardId,
            "userEmail" : userEmail,
            "commentText" : commentText
        },
        success: function(data){
            console.log(data)
            replyInput.value = "";
        },
        error: function(){
            alert("Error")
        }
    })
}
