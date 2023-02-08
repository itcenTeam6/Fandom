<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
    <title>JSP Sample Page</title>
</head>
    <body>

        size = ${listIdolResponseDTO.size} <br>
        page = ${listIdolResponseDTO.page} <br>
        totalElements = ${listIdolResponseDTO.totalElements} <br>
        totalPages = ${listIdolResponseDTO.totalPages} <br>
        hasNext = ${listIdolResponseDTO.hasNext} <br>
        hasPrevious = ${listIdolResponseDTO.hasPrevious} <br>

       <c:forEach var = "idol" items = "${listIdolResponseDTO.idols}">
       ${idol.idolName} <br>
       </c:forEach>

    </body>
</html>
