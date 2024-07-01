<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="org.apache.log4j.LogManager"%>
<%@page import="org.apache.log4j.Logger"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.kos.tr.board.vo.TerraBoardVO" %>
<%-- <%@ page import="com.kos.tr.common.CodeUtil" %> --%>
<%@ page import="java.util.List" %>

<% request.setCharacterEncoding("UTF-8"); %>
<%
	Logger logger = LogManager.getLogger(this.getClass());
	
	Object obj = request.getAttribute("listS");
	if (obj == null) return;
	
	List<TerraBoardVO> list = (List<TerraBoardVO>)obj;
	int nCnt = list.size();
	
// 	logger.info(">>>> " + list.get(0).getLike_cnt_1());
// 	logger.info(">>>> " + list.get(0).getLike_cnt_2());
// 	logger.info(">>>> " + list.get(0).getBlnum());
	
	TerraBoardVO _tbvo = null;
		_tbvo = list.get(0);
// 	if (nCnt == 1){
//	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
		
			$(document).ready(function(){
				
				$(document).on("click", "#write", function(){
					location.href="tBoardInsertForm.tr";
				});
				
				$(document).on("click", "#sall", function(){

					$("#boardForm").attr({
											"action":"tBoardSelectAll.tr"
										  }).submit();
				});
				
				$(document).on("click", "#update", function(){

					$("#boardForm").attr({
											"action":"tBoardSelect.tr"
										 }).submit();
				});
				
				$(document).on("click", "#delete", function(){
			
					$("#boardForm").attr({
											"action":"tBoardDelete.tr"
										 }).submit();
				});
			});
			
		</script>
		<style type="text/css">
			
			div {
				 margin: 0 auto;
				 display: table;
			}
			
			table, hd, h3 {
							text-align: center;
							border: 1px;
			}
			
			table {
					width: 800px;
					border: 1px solid #444;
					border-collapse: collapse;
			}
			
			th, td {
					border: 1px solid #444;
					padding: 10px;
			}
			
			.td_1 {
					font-size: 12px;
					color: black;
			}
			
			.td_2 {
					font-size: 12px;
					color: black;
					text-align: right;
			}
			
			.board{
				width:130px;
				background-color:#E2F0D9;
			}
			
			.bwrite{
				color:green;
				cursor:pointer;
				border:0;
			}
			
			#bwirtebox{
				border:0px;
				float:left;
			}
			
		</style>
	</head>
	<body>
		<h3>게시글</h3>
			

			
			<div>
				<div id="bwirtebox" >
							<span class="bwrite" id="write">글쓰기</span>&nbsp;
							<span class="bwrite" id="sall">글목록</span>&nbsp;
							<span class="bwrite" id="update">수정</span>&nbsp;
							<span class="bwrite" id="delete">삭제</span>
				</div>
				<br><br>
				<form name="boardForm" id="boardForm">
					<table>
					
						<tr>
							<th class="board">글 번호</th>
							<td colspan="2" name="bnum" id="bnum" value=""><%= _tbvo.getBnum() %></td>
							<td style="text-align: center" class="td_2" colspan="3">
								<font size="3" style="color:blue;">
									조회 <%= _tbvo.getBhit() %>&nbsp;| 작성일자 : <%= _tbvo.getUpdatedate() %>
								</font>
							</td>
						</tr>
						<tr>
							<th class="board">제목</th>
							<td colspan="4" style="text-align:left; width:400;">
								<font size="4" style="color:black;"><%= _tbvo.getBsubject() %></font>
<%-- 								<input type="hidden" name="bnum" id="bnum" value="<%= "B202307050001"%>" /> --%>
								<input type="hidden" name="mnum" id="mnum" value="<%= "M20230001" %>" />
							</td>
							
						</tr>
<!-- 						<tr> -->
<!-- 							<td>작성자</td> -->
<%-- 							<td><%= _kbvo.getBname() %></td> --%>
<%-- 							<td>카테고리</td>	<td class="td_1"><%= "업체 후기" %></td> --%>
<%-- 							<td>상세</td> <td class="td_1"><%= "서울시 금천구 독산동 > 카페/식당" %></td> --%>
<!-- 						</tr> -->
						<tr>
							<td colspan="6" style="text-align:left">
								<img src="/tboard/<%= _tbvo.getBfile() %>" border="1" alt="image"><br>
							</td>
						</tr>
						<tr>
							<td colspan="6" style="text-align:left">
								<textarea name="tbmemo" id="tbmemo" row="5" cols="105" style="border: none;"><%= _tbvo.getBmemo() %></textarea>
							</td>
						</tr>
					</table>
				</form>
			</div>
		<jsp:include page="/WEB-INF/views/terraRboard/trRboardInsertForm.jsp" flush="true">
			<jsp:param value="<%= _tbvo.getBnum() %>" name="bnum"/>
		</jsp:include>	
	</body>
</html>