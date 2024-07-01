<%@page import="com.kos.tr.old.common.CodeUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.kos.tr.old.post.vo.TerraBoardVO" %>
<%@ page import="com.kos.tr.old.rboard.vo.TerraRboardVO" %>
<%@ page import="com.kos.tr.old.member.vo.TerraMemberVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kos.tr.old.common.CodeUtil" %>

<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>
		<% request.setCharacterEncoding("UTF-8"); %>
		<%
			List<TerraMemberVO> uList = (List<TerraMemberVO>)request.getAttribute("uList");
			TerraMemberVO mvo = uList.get(0);
		
			Logger logger = LogManager.getLogger(this.getClass());
			logger.info("trRboardInsertForm.jsp Page >>> : " + uList.size());
			
			String bnum = request.getParameter("bnum");
			logger.info("trRboardInsertForm.jsp bnum >>> : " + bnum);
			
		
		%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
		
			$(document).ready(function(){
				selectAll();
				
				$("#rmemo").keyup(function(){
					cut_200(this);
				});
				
				// 댓글 insert
				$(document).on("click", "#insertRbtn", function(){
					console.log("insertBtn >>> : ");
					
					var bnum = $("#bnum").val();
					var mname = $("#mname").val();
					var rmemo = $("#rmemo").val();
					var mdeptno = $("#mdeptno").val();
					
					if (rmemo === "") {
						alert("댓글을 입력해주세요~");
						return;
					}
					
					
					let insertURL 	= "rBoardInsert.tr";
					let method 		= "POST";
					let dataParam 	=	{
											bnum :bnum,
											mname : mname,
											rmemo : rmemo,
											mdeptno : mdeptno
										};
									
					dataParam = $("#trRboardInsertForm").serialize();
					console.log("dataParam >>> : " + dataParam);
					
					$.ajax({
							url 	: insertURL,
							type 	: method,
							data 	: dataParam,
							success : whenSuccess,
							error 	: whenError
							});
					
					function whenSuccess(resData){

						if ("GOOD" == resData){
							
							trRboardInsertFormData();
							location.reload();
							
						}
					}
					
					function whenError(e){
						alert("e >>> : " + e.responseText);
					}
					
					selectAll();
				});
				
				$(document).on("click", ".deleteRbtn", function(){
					console.log("D >>> : ");
					
					var rnumV = $(this).parents("li").attr("dataNum");

					var target = $(this).parents(".rbmemoItem");
					console.log("target >>> : " + target);
					
					let deleteURL = "rBoardDelete.tr";
					let method = "POST";
					let dataParam = {
									 rnum : $('#rnum').val(rnumV),
									};
					dataParam = $("#trRboardInsertForm").serialize();
					console.log("dataParam >>> : " + dataParam);
					
					$.ajax({
							url 	: deleteURL,
							type 	: method,
							data 	: dataParam,
							success : whenSuccess,
							error 	: whenError
							});
					
					function whenSuccess(resData){
						console.log("resData >>> : " + resData);
						if ("GOOD" == resData){
							target.remove();
						}
						
						location.reload();
					}
					
					function whenError(e){
						console.log("e >>> : " + e.responseText);
					}
				});
			});
			
			function selectAll(){
				
				console.log("SALL >>> : ");
				
				let selectAllURL = "rBoardSelectAll.tr";
				let method		 = "GET";
				let dataParam	 = {
									bnum : $("#bnum").val(),
									mname : $("#mname").val(),
									mdeptno : $("#mdeptno").val()
									};
				dataParam = $("#trRboardInsertForm").serialize();
				console.log("dataParam >>> : " + dataParam);
				
				$.ajax({
						url 	: selectAllURL,
						type 	: method,
						data 	: dataParam,
						success : whenSuccess,
						error 	: whenError
						});
				
				function whenSuccess(resData){
					console.log("resData >>> : " + resData);
					console.log("whenSuccess trRboardSelectAll resData >>> : " + resData);
					
					if (isEmpty(resData)){
						return false;
					}
					
					let v = resData.split("&");
					for(let i = 0; i < v.length; i++){
						console.log(v[i]);
						
						let vv = v[i].split(",");
						let j = 0
						for (j=0; j < vv.length-1; j++){
							console.log("vv[0] >>> : " + vv[0]);
							console.log("vv[1] >>> : " + vv[1]);
							console.log("vv[2] >>> : " + vv[2]);
							console.log("vv[3] >>> : " + vv[3]);
						}
						
						addNewItem(vv[0], vv[1], vv[2], vv[3]);
					}
				}
				
				function whenError(e){
					console.log("e셀렉트올 에러발생>>> : " + e.responseText);
				}
			}
			
			function addNewItem(num, writer, content, datetime){
				
				// 데이터 체크
				if(isEmpty(num)) return false;
				
				var newLi = $("<span>");
				newLi.attr("dataNum", num);
				newLi.addClass("rmemoItem");
				
				var writerP = $("<p>");
				writerP.addClass("writer");
				
				var nameSpan = $("<span>");
				nameSpan.addClass("name");
				nameSpan.html(decodeURIComponent(writer) + "님");
				
				
				var dateSpan = $("<span>");
				dateSpan.html(" / " + datetime + " ");
				
				var delInput = $("<input>");
				delInput.attr({
								"type" 	: "button",
								"value" : "삭제하기"
							  });
				delInput.addClass("deleteRbtn");
				
				var contentP = $("<p>");
				contentP.html(decodeURIComponent(content));
				
				writerP.append(nameSpan).append(dateSpan).append(delInput);
				newLi.append(writerP).append(contentP);
				$("#trRboardlist").append(newLi);
			}
			
			function getTextLength(s){
				
				var len = 0;
				for(var i=0; i < s.length; i++){
					if(escape(s.charAt(i)).length == 6){
						len++;
					}
					
					len++;
				}
				
				return len;
			}
			
			function cut_200(obj){
				
				var t = $(obj).val();
				var l = t.length;
				while(getTextLength(t) > 200){
					l--;
					t = t.substring(0, 1);
				}
				
				$(obj).val(t);
				$('.bytes').text(getTextLength(t));
			}
			
			function trRboardInsertFormData(){
				
				$("#mname").val("");
				$("#rmemo").val("");
			}
			
			function isEmpty(val){
				
				if(typeof val == undefined || val == null || val == "null" || val == ""){
					
					return true;
					
				}else{
					return false;
				}
			}
			
		</script>
		<style type="text/css">
		
			#rmemo:focus{
				 outline: none;
			}
			
			.rboard {
				width:800px;
			}
			
			#insertRbtn{
				  display: block;
				  position: relative;
				  width: 120px;
				  padding: 0;
				  margin: 10px 20px 10px 0;
				  font-weight: 600;
				  text-align: center;
				  line-height: 50px;
				  color: black;
				  border-radius: 5px;
				  transition: all 0.2s ;
				  box-shadow: 0px 0px 0px 0px #21825B;
				  float:right;
				  background-color:#E2F0D9;
			}
			
			.post{
				width:130px;
				background-color:#E2F0D9 ;
			}
			
			#insertRbtn:hover {
			  box-shadow: 0px 0px 0px 5px #21825B;
			}
			
			
		
		</style>
	</head>
	<body>
<!-- 		<h3 style="text-align: center">댓글</h3> -->
		<br>
		<div id="rbwriterdiv">
			<form name="trRboardInsertForm" id="trRboardInsertForm">
				
				<table>
					<tr>
						<th class="post" name="mname" id="mname">
							<%= CodeUtil.dappVal(mvo.getMdeptno()) %>
							<%= mvo.getMname() %>
						</th>
						<td rowspan="2">
							<textarea name="rmemo" id="rmemo" rows="5" cols="50" style="width: 500px; border: none;"></textarea>
							<input type="button" value="입력" style=" width:100px;" id="insertRbtn">
						</td>
						
					</tr>
					<tr>
						<td class="post" >댓글<br><br>
							<div id="bytes" style="width:100px; height:20px; border:1px solid gray;color:gray; "><span style="color:gray;"class="bytes">0</span>bytes</div>
						</td>
					</tr>
					
				</table>
				
				
				
				
				
				
				
				<input type="hidden" name="bnum" id="bnum" value="<%= bnum %>">
				<input type="hidden" name="rnum" id="rnum">
				<input type="hidden" name="mnum" id="mnum" value="<%= mvo.getMnum() %>">
				<input type="hidden" name="mname" id="mname" value="<%= mvo.getMname() %>" >
				<input type="hidden" name="mdeptno" id="mdeptno" value="<%= mvo.getMdeptno()%>">
				<ul name="trRboardlist" id="trRboardlist">
<!-- 					이곳에 동적 생성 요소가 들어옴 -->
				</ul>
			</form>
		</div>
	</body>
</html>