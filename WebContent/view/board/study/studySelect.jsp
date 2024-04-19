<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/dokkyCss/studySelectStyle.css"> <!-- 스타일 시트 링크 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<!-- 파비콘(주소창 아이콘 표시) -->
<link href="<%=request.getContextPath() %>/imgs/fav.ico" rel="shortcut icon" type="image/x-icon">
<title>DOKKY - 스터디게시판 글목록</title>
</head>
<body>
<!-- 헤더 -->
<%@ include file="../../headerFooter/header.jsp" %>
<br>
<br>
<br>

<!-- 페이지소개 -->
<div class="container">
	<div class="square-box">
		<p class="main-text">스터디</p>
		<p class="sub-text">스터디원을 모집하거나 참가하고 싶은 분들을 위한 만남의 장을 만들어요!</p>
		<img src="<%=request.getContextPath() %>/imgs/study.svg" alt="SVG Icon" class="r-icon" >
	</div>

	<!-- 글작성버튼 -->
	<div class="button-container">
		<button class="custom-button" onclick="location.href='/study/write.do'">
			<span><img src="<%=request.getContextPath() %>/imgs/write-icon.png" alt="write-icon"></span>
			<span>작성하기</span>
		</button>
	</div>


	<!-- 정렬바 -->
	<div class="dropdown" style="float:right;">
		<button class="dropbtn">
			<img src="<%=request.getContextPath() %>/imgs/select-icon.png" alt="select-icon">최신순
		</button>
		<div class="dropdown-content">
   			<a href="#">최신순</a>
   			<a href="#">추천순</a>
   			<a href="#">조회순</a>
   			<a href="#">댓글순</a>
		</div>
	</div>

	<!-- 검색창 -->
	<div style="display: grid; place-items: center; text-align: center;">
		<form class="search-box" action="" method="get" >
   			<input class="search-txt" type="text" name="" placeholder="검색어를 입력하세요."/>
   			<input class="search-btn" type="image" src="<%=request.getContextPath() %>/imgs/search-icon.png" title="search-icon"/>
   		</form>
	</div>
	<div style="clear:both;"></div>
	<c:if test="${studyPage.hasNoContents()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
	</c:if> 
    
	<!-- 글 목록 -->
	<ul class="bordered-list">
	    <c:forEach var="study" items="${studyPage.studyList}">
	        <li>
	            <div class="content">
	                <div class="user">${study.memId}</div>
	                <div class="title">${study.title}</div>
	                <div class="date">${study.regDate}</div>
	            </div>
	        </li>
	    </c:forEach>
	</ul>


	<hr>

	<!-- 페이지네이션 -->
	<div class="pagination-container">
		<div class="pagination" style="margin-top:-20px">
			<a href="#">&laquo;</a>
			<a href="#">1</a>
			<a href="#" class="active">2</a>
			<a href="#">3</a>
			<a href="#">4</a>
			<a href="#">5</a>
			<a href="#">6</a>
			<a href="#">&raquo;</a>
		</div>
	</div>
</div>
<br>
<br>
<br>
<br>

<!-- 푸터 -->
<%@ include file="../../headerFooter/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
