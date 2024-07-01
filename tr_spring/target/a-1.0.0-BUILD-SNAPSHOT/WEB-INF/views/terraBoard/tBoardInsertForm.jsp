<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">

			
			$(document).ready(function(){

				
				$(document).on("click", "#bBtn", function(){
					console.log("tbBtn >>> : ");
					
					$('#tBoardInsertForm').attr({
										  'action'	:'tBoardInsert.tr',
										  'method'	:'POST',
										  'enctype'	:'multipart/form-data'
										}).submit();
				});
			});
		</script>
		<style type="text/css">
			
			h3 {
				text-align: center;
			}
			
			.tt {
				 text-align: center;
			}
			
		</style>
	</head>
	<body>
		<h3>boardInsertForm.jsp</h3>
		<hr>
		<form name="tBoardInsertForm" id="tBoardInsertForm">
			<table border="1" align="center">
				<tr>
					<td colspan="2" align="center">게시판 글쓰기</td>
				</tr>
				<tr>
					<td>글번호</td>
					<td><input type="text" name="bnum" id="bnum" size="20" readonly></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="bsubject" id="bsubject" size="53"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
					<textarea name="bmemo" id="bmemo" rows="10" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<td>사진</td>
					<td><input type="file" name="bfile" id="bfile"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
					<input type="button" value="글쓰기" id="bBtn">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>