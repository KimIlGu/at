<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 리스트"></c:set>

<%@ include file="../part/head.jspf"%>	

<div class="con search-box">
	<form action="${pageContext.request.contextPath}/article/list">
		<input type="hidden" name="page" value="1" /> 
		<input type="hidden" name="searchKeywordType" value="title" /> 
		<input type="text" name="searchKeyword" value="${param.searchKeyword}" />
		<button type="submit">검색</button>
	</form>
</div>

<h2 class="con">전체 게시물 개수 : ${totalCount}</h2>

<div class="btns con" style="margin-left:1476px; margin-bottom:px; font-weight:bold;">
	<a href="./add">게시물 추가</a>
</div>

<div class="table-box con">
	<table>
		<colgroup>
			<col width="80">
			<col width="180">
			<col>
			<col width="80">
			<col width="100">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>제목</th>
				<th>조회수</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articles}" var="article">
				<tr>
					<td>${article.id}</td>
					<td>${article.regDate}</td>
					<td><a href="./detail?id=${article.id}">${article.title}</a></td>
					<td>${article.hit}</td>
					<td>
						<a href="./doDelete?id=${article.id}" onclick="if ( confirm('삭제하시겠습니까?') == false ) { return false;}">삭제</a>
						<a href="./modify?id=${article.id}">수정</a>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="con page-box" style="margin-top: 30px; margin-left:950px; font-weight:bold">
	<ul class="flex flex-jc-c">
		<c:forEach var="i" begin="1" end="${totalPage}" step="1">
			<li class="${i == cPage ? 'current' : ''}"><a
				href="?searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}&page=${i}"
				class="block"> ${i} </a></li>
		</c:forEach>
	</ul>
</div>

<%@ include file="../part/foot.jspf"%> 