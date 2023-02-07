let replyInput = document.querySelector(".replyInput")
let replyBtn = document.querySelector(".replyBtn")
let commentBox = document.querySelector(".commentBox")
let commentContainer = document.querySelector(".showComment")

replyInput.addEventListener("keydown",submitEnter)
replyBtn.addEventListener("click",makeComment)

function submitEnter(event){
  if(event.keycode === 13){
    makeComment()

  }
}

/* 1. 만약 엔터를 누르거나 
 2. 게시를 클릭하면
 3. contents.set만들기
 4. 
*/

function makeComment(){
event.preventDefault()
  console.log("test")
    let commentText = replyInput.value;
    console.log(commentText)
    let newCommentBox = document.createElement("div")
    let newCommentSet = document.createElement("div");
    let newCommentId = document.createElement("span")
    let newCommentContents = document.createElement("span")


    newCommentId.setAttribute("class","commentId")
    newCommentSet.setAttribute("class","commentSet")
    newCommentBox.setAttribute("class","commentBox")
    newCommentContents.setAttribute("class","commentContents")


    newCommentId.innerText = "hwanggee20"
    newCommentContents.innerText= commentText;

    newCommentSet.appendChild(newCommentId);
    newCommentSet.appendChild(newCommentContents);
    newCommentBox.appendChild(newCommentSet)
   
    commentContainer.appendChild(newCommentBox)
    initInput()
}


function initInput(){
  replyInput.value = ""
  console.log("test")
}