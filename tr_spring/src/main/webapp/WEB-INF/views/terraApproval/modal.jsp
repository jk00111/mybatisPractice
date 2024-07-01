<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kos.tr.old.member.vo.TerraMemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kos.tr.old.common.CodeUtil"%>
<%

	Object obj = request.getAttribute("aList");
	ArrayList<TerraMemberVO> aList = (ArrayList<TerraMemberVO>) obj;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
	table{
		border:solid 1px black;
		border-collapse: collapse;
		
		padding:0;
	}
	
	ul {
    	list-style: none; /* 점(불릿)을 없애는 스타일 설정 */
  	}
	.c1{
 		width:200px; 
		height:50px;
	}

	.emp{
		display:none;
	}
	
	.modal {
	  display: none;
	  position: fixed;
	  z-index: 1;
	  left: 0;
	  top: 0;
	  width: 100%;
	  height: 100%;
	  overflow: auto;
	  background-color: rgba(0, 0, 0, 0.4);
	}

	.modal-content {
	  background-color: #fefefe;
	  margin: 15% auto;
	  padding: 20px;
	  border: 1px solid #888;
	  width: 80%;
	}

	.close {
	  float: right;
	  font-size: 28px;
	  font-weight: bold;
	  cursor: pointer;
	}
	
	.close:hover {
	  color: #000;
	}
	
	.dep{
		color:green;
	}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		$(document).on('click', '.dep', function(){
			var dep = $(this).val()
			
			$('.emp').css('display', 'none');
			$('.' + dep).css('display','block');
		});
		
		$(document).on('click', '.plusBtn', function(){
			
			var plusId = $(this).attr("id");
			var empName = $("#empList option:selected").html();
			
			var nameOption = $("<option value=" + $("#empList option:selected").val() + ">");
			nameOption.html(empName + " ");
			
			
			if (plusId == 'plus1') {
				nameOption.addClass("selected1");
				$("#appList").append(nameOption);	
				
			}else if(plusId == 'plus2'){
				nameOption.addClass("selected2");
				$("#refList").append(nameOption);
			}
		});
		
		$(document).on('click', '.minBtn', function(){
					
			var minId = $(this).attr("id");
			
			if (minId == 'min1') {
				$("#appList option:selected").remove();
			}else if(minId == 'min2'){
				$("#refList option:selected").remove();
			}
		});
		
		$("#openModal").click(function() {
			$("#myModal").css("display", "block");
		});

		// 모달 닫기 버튼 클릭 이벤트 처리
		$(document).on('click', '#cfmBtn', function(){
			
			var aapp = "";
			var aref = "";
			
			for (var i = 0; i < 5; i++) {
				$('#r'+ (i+1)).text($('#appList').text().split(' ')[(i*2)]);
				$('#n'+ (i+1)).text($('#appList').text().split(' ')[(i*2)+1]);
				$('#areftext').text($('#refList').text());
				if ($('#appList').find('.selected1:nth-child('+ (i+1) +')').val() === undefined) {
					aapp += "-,";
				}else{
					aapp += $('#appList').find('.selected1:nth-child('+ (i+1) +')').val() + ',';	
				}
				if ($('#refList').find('.selected2:nth-child('+ (i+1) +')').val() === undefined) {
					aref += "-,";
				}else{
					aref += $('#refList').find('.selected2:nth-child('+ (i+1) +')').val() + ',';					
				}
			}
			$('#aapp').val(aapp);
			$('#aref').val(aref);
			
			
			console.log(aapp);
			console.log(aref);
			
			
			$("#myModal").css("display", "none");
		});
		
		$(document).on('click', '.closed', function(){
			
			$("#myModal").css("display", "none");
		});
		

		// 모달 외부 클릭 이벤트 처리
		$(document).click(function(event) {
			if ($(event.target).is("#myModal")) {
			$("#myModal").css("display", "none");
			}
		});
	});
	
</script>
</head>
<body>
<div id=openModal style="width:100px;height:15px;"><span style="color:green">결재선 설정</span></div>
<div id="myModal" class="modal">
  <div class="modal-content" style="align:center;width:1050px">
    <span class="close closed" style="text-align:right">&times;</span>
    <h4 style="color:green">결재선 설정</h4>
    <table>
<tr>
	<td class="c1" rowspan="6" style="width:150px;height:446px;">
	<ul id="folderList" style="text-align:center">
      <li class="dep" value="1000">관리부</li>
      <li class="dep" value="2000">기획부</li>
      <li class="dep" value="3000">영업부</li>
      <li class="dep" value="4000">생산부</li>
      <li class="dep" value="5000">안전관리부</li>
    </ul>
	</td>
	<td class="c1" rowspan="6" style="width:100px;height:400px;text-align:center">
		<select name="empList" id="empList" multiple="multiple" style="width:228px;height:500px;">
			<%
				for(int i=0; i < aList.size(); i++){
					TerraMemberVO tmvo = aList.get(i);
			%>
			<option class="<%=tmvo.getMdeptno()%> emp" value="<%=tmvo.getMnum()%>"><%=tmvo.getMname()%> <%=CodeUtil.mpositionVal(tmvo.getMposition())%></option>
			<%
				}
			%>
		</select>
	</td>
	<td class="c1" rowspan="3" style="width:20px"><input type="button" class="plusBtn" id="plus1" value=">"/><br>
	<input type="button" class="minBtn" id="min1" value="<"/></td>
	<td class="c1" style="text-align:center;width:200px;font-weight:bold;">결재</td>
</tr>
<tr>
	<td class="c1" rowspan="2" style="text-align:center"><select name="appList" id="appList" multiple="multiple" style="width:228px;height:223px;"></select></td>
</tr >
<tr>
</tr>
<tr>
	<td class="c1" rowspan="3" style="width:20px"><input type="button" class="plusBtn" id="plus2" value=">"/><br><input type="button" class="minBtn" id="min2" value="<"/></td>
	<td class="c1" style="text-align:center;width:200px;font-weight:bold;">참조</td>
</tr>
<tr>
	<td class="c1" rowspan="2" style="text-align:center"><select name="refList" id="refList" multiple="multiple" style="width:228px;height:223px;"></select></td>
</tr>
<tr>
</tr>
</table>
<br>
<div style="text-align:center">
<button type="button" class="btn btn-primary" style="background-color:#92D050;border-color:#92D050;width:100px" id="cfmBtn">확인</button>
<button type="button" class="btn btn-light closed" style="border-color:#92D050;width:100px">취소</button>
</div>
</div>
</div>


</body>
</html>