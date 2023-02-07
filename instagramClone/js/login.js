
let pwInput = document.querySelector(".pwInput")
let idInput = document.querySelector(".idInput")
let button = document.querySelector(".loginBtn")

window.addEventListener("keydown",checkPW)
window.addEventListener("keydown", checkID)

/* 1. 아이디를 먼저 입력하는 경우 id
2. 패스워드를 먼저 입력하는 경우 pw*/

function checkPW(){
    let password = pwInput.value
    let id = idInput.value

    if( password.length >= 0 && id.length >= 1){
        console.log("id 먼저 입력되었을때")
        changeColor()
    } 
}

function checkID(){
    let password = pwInput.value
    let id = idInput.value
   
    if( password.length >= 1 && id.length >= 0){
        console.log("password 먼저 입력되었을때")
       changeColor()
    } 
}

function changeColor(){
    button.classList.remove("logOff");
    button.classList.contains("logOn");
}

