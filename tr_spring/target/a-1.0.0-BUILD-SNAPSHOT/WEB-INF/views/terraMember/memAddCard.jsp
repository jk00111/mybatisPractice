<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kos.tr.member.vo.TerraMemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kos.tr.common.CodeUtil"%>
<%

	Object obj = request.getAttribute("listAll");
	if (obj == null) return;
	
	ArrayList<TerraMemberVO> aList = (ArrayList<TerraMemberVO>)obj;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
	td{
		border-collapse:collapse;
	}

    .modal {
      display: none; /* 초기에는 모달 창을 숨김 */
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5); /* 배경을 반투명하게 설정 */
    }
    .modal-content {
      background-color: white;
      width: 650px;
      margin: 100px auto; /* 화면 중앙에 위치하도록 설정 */
      padding: 20px;
      border-radius: 5px;
    }
  </style>

<script type="text/javascript">

	$(document).ready(function(){
		
		$('.contents').click(function() {
		  // 모달 창 보이기
			
			$('#myModal').css('display', 'block');
		});

		// 모달 창 닫기 버튼 클릭 이벤트 처리
		$('#closeBtn').click(function() {
		  // 모달 창 닫기
		  $('#myModal').css('display', 'none');
		});
	});
</script>
</head>
<body>

<!-- 모달 창 -->
<div class="modal" id="myModal">
  <div class="modal-content">
	<h4 id="emname"></h4>
<table style="border-top:solid 1px;border-bottom:solid 1px;">
	<tr>
		<td style="width:150px;height:30px;font-weight:bold;text-align:left">&nbsp;&nbsp;이메일</td>
		<td id="email" style="width:200px;height:30px;text-align:left"></td>
		<td rowspan="5" style="width:300px;height:250px"><img id="photo" style="width:250px;height:250px;padding:5px"></td>
	</tr>
	<tr>
		<td style="width:150px;height:30px;font-weight:bold;text-align:left">&nbsp;&nbsp;전화</td>
		<td id="hp" style="width:200px;height:30px;text-align:left"></td>
	</tr>
	<tr>
		<td style="width:150px;height:30px;font-weight:bold;text-align:left">&nbsp;&nbsp;회사</td>
		<td style="width:200px;height:30px;text-align:left">TERRA</td>
	</tr>
	<tr>
		<td style="width:150px;height:30px;font-weight:bold;text-align:left">&nbsp;&nbsp;직위</td>
		<td id="position" style="width:200px;height:30px;text-align:left"></td>
	</tr>
	<tr>
		<td style="width:150px;height:30px;font-weight:bold;text-align:left">&nbsp;&nbsp;부서</td>
		<td id="dept" style="width:200px;height:30px;text-align:left"></td>
	</tr>
</table>
<br>
<div style="text-align:center">
<button type="button" class="btn btn-primary" style="background-color:#92D050;border-color:#92D050;width:100px;" id="closeBtn">확인</button>
</div>
  </div>
</div>
</body>
</html>