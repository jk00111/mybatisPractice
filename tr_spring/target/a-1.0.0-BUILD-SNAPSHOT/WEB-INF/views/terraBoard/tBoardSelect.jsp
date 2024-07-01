<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%@ page import="com.kos.tr.common.CodeUtil" %> --%>
<%@ page import="com.kos.tr.board.vo.TerraBoardVO" %>
<%@ page import="java.util.List" %>

<% request.setCharacterEncoding("UTF-8"); %>
<%
	Object obj = request.getAttribute("listS");
	if (obj == null) return;
	
	List<TerraBoardVO> list = (List<TerraBoardVO>)obj;
	int nCnt = list.size();
	
	TerraBoardVO _tbvo = null;
	if (nCnt == 1){
		_tbvo = list.get(0);
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			
			$(document).ready(function(){
		
				$(document).on("click", "#U", function(e){

					e.preventDefault();
					$("#boardUpdateForm").attr({
												"method":"GET",
												"action":"tBoardUpdateForm.tr"
												}).submit();
				});
		
					$(document).on("click", "#D", function(e){
	
						e.preventDefault();
						$("#boardUpdateForm").attr({
													"method":"GET",
													"action":"tBoardDelete.tr"
													}).submit();
					});
			});
		</script>
		<style type="text/css">
			
			div {
				 margin: 0 auto;
				 border: 1px solid #6d82f3;
				 display: table;
			}
			
			table, h3 {
						border: 1px;
			}
			
			table {
					width: 100%;
					border: 1px solid #444;
					border-collapse: collapse;
			}
			
			th, td {
					border: 1px solid #444;
					padding: 10px;
			}
			
			.mem {
					text-align: center;
			}
			
		</style>
	</head>
	<body>
		<h3 style="text-align: center;">게시글 내용</h3>
		<hr>
			<div>
				<form name="boardUpdateForm" id="boardUpdateForm">
					<table>
						<tr>
							<td colspan="2" align="center">게시판 글 수정하기</td>
						</tr>
						<tr>
							<td class="board">글 번호</td>
							<td><input type="text" name="bnum" id="bnum" value="<%= _tbvo.getBnum() %>" readonly></td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td class="board">이름</td> -->
<%-- 							<td><input type="text" name="mnum" id="mnum" value="<%= _tbvo.getMnum() %>" readonly></td> --%>
<!-- 						</tr> -->
						<tr>
							<td class="board">제목</td>
							<td><input type="text" name="bsubject" id="bsubject" value="<%= _tbvo.getBsubject() %>" style="width:100px" readonly></td>
						</tr>
						<tr>
							<td class="board">소개글</td>
							<td><textarea name="bmemo" id="bmemo" rows="5" cols="50"><%= _tbvo.getBmemo() %></textarea></td>
						</tr>
						<tr>
							<td class="board">사진</td>
							<td><img src="/tr_spring/fileupload/tboard/<%= _tbvo.getBfile() %>" border="1" width="40" height="50" alt="image"></td>
						</tr>
						<tr>
							<td class="board">등록일</td>
							<td><input type="date" name="sbinsertdate" id="sbinsertdate" value="<%= _tbvo.getInsertdate() %>" readonly /></td>
						</tr>
						<tr>
							<td class="board">수정일</td>
							<td><input type="date" name="sbupdatedate" id="sbupdatedate" value="<%= _tbvo.getUpdatedate() %>" readonly /></td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<button type="button" id="U">수정</button>
								<button type="button" id="D">삭제</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
<%-- 		<jsp:include page="/WEB-INF/views/terraRboard/trRboardInsertForm.jsp" flush="true"> --%>
<%-- 			<jsp:param value="<%= _tbvo.getBnum() %>" name="bnum"/> --%>
<%-- 		</jsp:include> --%>
	</body>
</html>