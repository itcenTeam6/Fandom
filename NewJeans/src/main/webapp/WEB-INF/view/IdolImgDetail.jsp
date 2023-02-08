<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
    <title>JSP Sample Page</title>
</head>
    <body>

       <div>${detailIdolImgResponseDTO.idolName}님은 아이돌입니다.</div>

        <span>아이돌 사진 아이디 : </span> ${detailIdolImgResponseDTO.imgId} <br>
        <span>아이돌 사진 경로 : </span> ${detailIdolImgResponseDTO.imgPath} <br>
        <span>아이돌 사진 타입 : </span> ${detailIdolImgResponseDTO.msType} <br>

    </body>
</html>
