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
  let newCommentId = document.createElement("span");
  let newCommentContents = document.createElement("span");

  newCommentId.setAttribute("class", "commentId");
  newCommentSet.setAttribute("class", "commentSet");
  newCommentBox.setAttribute("class", "commentBox");
  newCommentContents.setAttribute("class", "commentContents");

  newCommentId.innerText = "testUser";
  newCommentContents.innerText = commentText;

  newCommentSet.appendChild(newCommentId);
  newCommentSet.appendChild(newCommentContents);
  newCommentBox.appendChild(newCommentSet);

  commentContainer.appendChild(newCommentBox);
  initInput();
}

function initInput() {
  replyInput.value = "";
}
