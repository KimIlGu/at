<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="게시물 상세보기"></c:set>
<%@ include file="../part/head.jspf"%>	

<h2 class="con">게시물 상세보기</h2>

<div class="table-box con">
	<table>
		<colgroup>
			<col width="180">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th>번호</th>
				<td>${article.id}</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${article.regDate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${article.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${article.body}</td>
			</tr>
			<tr>
				<th>비고</th>
				<td>
					<a href="./doDelete?id=${article.id}" onclick="if ( confirm('삭제하시겠습니까?') == false ) { return false; }">삭제</a>
					<a href="./modify?id=${article.id}">수정</a>
					<a href="list">취소</a>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="con">
	<c:if test="${prev != null}">
		<a href="./detail?id=${prev.id}">이전</a>
	</c:if>

	<c:if test="${next != null}">
		<a href="./detail?id=${next.id}">다음</a>
	</c:if>
</div>

<h2 class="con">댓글 작성</h2>

<script>
	function WriteReply__submitForm(form) {
		form.body.value = form.body.value.trim();
		if (form.body.value.length == 0) {
			alert('댓글을 입력해주세요.');
			form.body.focus();
			return;
		}
		$.post('./doWriteReplyAjax', {
			articleId : param.id,
			body : form.body.value
		}, function(data) {
			if (data.msg) {
				alert(data.msg);
			}
			if ( data.resultCode.substr(0, 2) == 'S-' ) {
				location.reload(); // 임시
			}
		}, 'json');
		form.body.value = '';
	}
</script>

<form action="./doWriteReply" onsubmit="WriteReply__submitForm(this); return false;">
	<input type="hidden" name="articleId" value="${param.id}">
	<div class="table-box con">
		<table>
			<tbody>
				<tr>
					<th>내용</th>
					<td>
						<textarea class="height-100px" name="body" placeholder="내용을 입력해주세요."></textarea>
					</td>
				</tr>
				<tr>
					<th>작성</th>
					<td>
						<input type="submit" value="작성">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form>

<h2 class="con">댓글 리스트</h2>

<div class="table-box con">
	<table>
		<colgroup>
			<col width="80">
			<col width="180">
			<col>
			<col width="200">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>내용</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articleReplies}" var="articleReply">
				<tr>
					<td>${articleReply.id}</td>
					<td>${articleReply.regDate}</td>
					<td>${articleReply.body}</td>
					<td>
						<a href="./doDeleteReply?id=${articleReply.id}&redirectUrl=${EncodedUrllPassword}" onclick="if ( confirm('삭제하시겠습니까?') == false ) { return false; }">삭제</a>
						<a href="./modifyReply?id=${articleReply.id}&redirectUrl=${EncodedUrllPassword}">수정</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="../part/foot.jspf"%> 