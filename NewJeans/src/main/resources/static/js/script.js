'use strict';

$(document).on('click', 'a[href="#"]', function(e) {
    e.preventDefault();
});

//gnb 메뉴
$(function() {
    var menuBtn = $('header .menu-open');
    var closeBtn = $('.gnb .close');
    var isMenuOpen = false;
    menuBtn.on('click', function() {
        $('.gnb').addClass('on');
    });
    $('.gnb .close, section').on('click', function(){
        $('.gnb').removeClass('on');
    });
    //메뉴 토글
    $('body').on('keyup', function (e) {
        e.preventDefault();
        if (e.keyCode === 27) {
            if (!isMenuOpen) {
                menuBtn.click();
                isMenuOpen = true;
            } else {
                closeBtn.click();
                isMenuOpen = false;
            }
        }
    });
});

//fix header
var scrollTop = 0;
scrollTop = $(document).scrollTop();

//고정헤더 함수
function fixHeader() {
    if (scrollTop > 150) {
        $('header').addClass('on');
    } else {
        $('header').removeClass('on');
    }
}

fixHeader();

//윈도우창 조절시 이벤트
$(window).on('scroll resize', function() {
    scrollTop = $(document).scrollTop();
    fixHeader();
    hideGoTop();
});

//스크롤라 애니메이션
$(function() {
    $('.animate').scrolla({
        mobile: false,
        once: false
    });
});

//맨 위로 이동 부드럽게 이동하기
$(function() {
    $('.go-top').on('click', function() {
        $('html, body').animate({
            scrollTop: 0
        }, 1000);
    });
});

//맨 위로 이동버튼 중간부터 나오게하기
function hideGoTop() {
    if (scrollTop < 800) {
        $('.go-top').addClass('hide');
    } else {
        $('.go-top').removeClass('hide');
    }
}

//.top-visual 이미지슬라이드
$(function(){
    $(".visual .slide").slick({
        arrows: true, //화살표
        dots: false,  //인디케이트 해제
        fade: true, //페이드인효과
        autoplay :true, //자동재생
        autoplaySpeed: 4000,  //재생시간
        pauseOnHover: false,
        PauseOnFocus: false
    });
});