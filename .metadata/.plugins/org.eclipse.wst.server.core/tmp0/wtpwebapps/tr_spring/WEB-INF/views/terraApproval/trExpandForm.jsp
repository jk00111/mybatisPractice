<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@page import="com.kos.tr.common.CodeUtil"%>
<%@page import="com.kos.tr.app.vo.AppDocVO"%>
<%@page import="com.kos.tr.member.vo.TerraMemberVO"%>
	<%
		Object obj = request.getAttribute("aList");
		
		List<TerraMemberVO> list = (List<TerraMemberVO>)obj;
				
		Object sbj = request.getAttribute("uList");
		List<TerraMemberVO> ulist = (List<TerraMemberVO>)sbj;
		
		TerraMemberVO ttmvo = ulist.get(0);
		
		
	%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
			 <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
			 <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
  			 <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
  			 <meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1, user-scalable=no">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
			<link rel="stylesheet" type="text/css" href="../resources/css/sidebars.css">
			<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
		<style type="text/css">
		.icon_img{
		
			width:22px;
			height: 22px;
			margin-bottom: 10px;
			margin-left: 25px;
		}
		
		.dropdown-toggle-no-caret::after {
			display: none;
		}		
		.initial-size {
		  	min-height: 200px; 
		  	width : 1000px;
		}
		
		table {
			border-collapse : collapse;
			width:1000px;
		}
			
		.a {
			width: flex;
			height:100px;
		}
		
		.rank{
			width:flex;
			height: 40px;
		}
		
		.aauto {
			width: flex;
			height:100px;
		}
		
		.mposition{
			width: 173px;
			height:40px;
			text-align:center;
		}
		
		.idx{
			cursor:pointer;
		}
		
		.texttp{
			text-align:center;
			background-color:#E2F0D9;
		}
				
		.expand01{
			height:40px;
		}
		.rightali{
			text-align:right;
		}
		
		.ceterali{
			text-align:center;
		}
	</style>

		<script type="text/javascript">
		
			$(document).ready(function(){
				
				$(document).on('click', '#istBtn', function(){
					location.href = "../appInsertDoc.tr";
				});
			
			
				$(document).on('mouseenter', '.idx', function(){

					$(this).css('background-color', '#ecf9ec');
				});

				$(document).on('mouseleave', '.idx', function(){

					$(this).css('background-color', '#E2F0D9');
					});
				
				$(document).on('mouseleave', '#nowSelected', function(){

					$(this).css('background-color', '#C5E0B4');
				});
				
				// 기안하기 
				$(document).on("click", "#alevel01", function() {

					
					$('#alevel').val("01");
					
					$('#appExpandForm').attr({
						'action':'../appInsert/E.tr',
						'method':'POST',
						'enctype':'multipart/form-data'
					}).submit();
				});
				
				
				// 수량 단가 곱해서 금액계산하기 
				var a = 0;
				var sum = 0;
				
				$(".expand01").change(function() {
					
					let eamount = $(this).find('td input').eq(3).val();
					let eprice = $(this).find('td input').eq(4).val();
			
					var result = getValue(eamount, eprice);
			
					if (!isNaN(result)) {
						$(this).find('td input').eq(5).val(result);
					}
					
					var numbers = [parseInt($('#esum1').val()), parseInt($('#esum2').val()), parseInt($('#esum3').val()), parseInt($('#esum4').val()), parseInt($('#esum5').val())];
					var res = addNumbers(numbers);
					console.log("res >>>>>" + res);
					
					$("#sumresult").val(res);
				});
				
				function addNumbers(numbers) {
					let sum = 0;
					for (let i = 0; i < numbers.length; i++) {
						const num = Number(numbers[i]);
						if (!isNaN(num)) {
							sum += num;
							}
					}
					  return sum;
					}
				
				function getValue(eamount, eprice) {
					var sum = parseInt(eamount) * parseInt(eprice);
					return sum;
				}
				
				$('.ql-toolbar').css('width',"1000px");
				
				$.ajax({
					type:"GET",
					url: '/tr_spring/appAlert.tr',
					success : function(result){
						
						if (result == 'ALERT_YES') {
							$('#bell').attr("src", "/tr_spring/resources/images/alert.png")
						}
					},
					error: function(xtr, status, error){
						alert(xtr + ":" + status + ":" + error);
					}
				});
			});
		
		</script>
		
	</head>

	<body>
	<nav class="navbar navbar-expand-sm navbar-dark sticky-top" style="background-color: #C5E0B4;padding-right:0;">
		<!--LOGO-->
		<a class="navbar-brand" href="/tr_spring/mainPage.tr" >&nbsp&nbsp<img src="../resources/images/logo-green2.png" style="height:30px"></a>
		<!--Links-->
		<div class="container-fluid full-width">
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false" style="padding-top:13px;font-size:18px;color:#534825;font-weight:bold;cursor:pointer">전자결재</a>
				<ul class="dropdown-menu" aria-labelledby="dropdown01">
				  <li><a class="dropdown-item" href="/tr_spring/mainPage.tr">메인페이지</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/tBoardSelectAll.tr">게시판</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/tMemAddrBook.tr">주소록</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/appSelect/A.tr">전자결재</a></li>
				  <li><a class="dropdown-item" href="#">일정</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/trWorkForm.tr">인사</a></li>
				</ul>
			  </li>
		</ul>
		<div>
						<div class="dropdown" style="position:absolute;right:80px">
				<img id="bell" onclick="javascript:location.href='/tr_spring/appSelect/W.tr'" src="/tr_spring/resources/images/notification-bell.png" alt="" width="32" height="32" style="cursor:pointer">
			</div>
		<div>
		<div class="dropdown" style="left:-10px;cursor:pointer">
			<a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle dropdown-toggle-no-caret" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
			  <img src="../fileupload/tmem/<%= ttmvo.getMphoto() %>" alt="" width="35" height="35" class="rounded-circle me-2">
			</a>
			<ul class="dropdown-menu dropdown-menu-dark text-small shadow" style="left:-110px" aria-labelledby="dropdownUser1">
			  <li><a class="dropdown-item" href="#">마이 페이지</a></li>
			  <li><hr class="dropdown-divider"></li>
			  <li><a class="dropdown-item" href="../terraLogout.tr">로그아웃</a></li>
			</ul>
		  </div>
		</div>
		</div>
		</div>
	</nav>
		<div class="row" style="margin-right: 0;">
		  <div class="col-3" >
			<!-- 사이드바 내용 -->
			<div class="sidebar">
			  <!-- 사이드바 내용을 추가 -->
			  <div class="d-flex flex-column flex-shrink-0 p-3 " style="width: 280px; height:auto; overflow-y: auto; height: 100vh;position:fixed; background-color:#E2F0D9;">
				<button type="button" class="btn btn-primary" id="istBtn" style="background-color:#92D050;height:50px;border:0; font-size:20px;font-weight:1000;color:#534825;cursor:pointer">+ 작성하기</button>
				<br>
				<ul class="list-unstyled ps-0">
					<li class="mb-1">
					  <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true" style="font-size: 20px;cursor:pointer">
						진행중인 문서
					  </button>
					  <div class="collapse show" id="home-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/A.tr'"><img src="/tr_spring/resources/images/file.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">전체</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/W.tr'"><img src="/tr_spring/resources/images/hourglass.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">대기</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/P.tr'"><img src="/tr_spring/resources/images/time.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">예정</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/K.tr'"><img src="/tr_spring/resources/images/loading.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">진행</span></div></li>
						</ul>
					  </div>
					</li>
					<li class="mb-1">
					  <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="true" style="font-size: 20px;cursor:pointer">
						문서함
					  </button>
					  <div class="collapse show" id="dashboard-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/AllDoc.tr'"><img src="/tr_spring/resources/images/file.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">전체</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/D.tr'"><img src="/tr_spring/resources/images/pencil.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">기안</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/AA.tr'"><img src="/tr_spring/resources/images/digital-signature.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">결재</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/R.tr'"><img src="/tr_spring/resources/images/add-user.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">참조</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/appSelect/RJ.tr'"><img src="/tr_spring/resources/images/traffic-signal.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">반려</span></div></li>
						</ul>
					  </div>
					</li>
				  </ul>
			  </div>
			</div>
		  </div>
		</div>
		  <div class="col-9" style="left:400px;top:30px">
			<!-- 본문 내용 -->
			<div class="content">
			  <!-- 본문 내용을 추가 -->
		<form name="appExpandForm" id="appExpandForm">
		
		<div><p id="alevel01" style="color:green">기안하기</p>
		</div>
		<table border="1" class="type" name="type" id="type">
		<colgroup>
			<col span="1" style="background-color: #E2F0D9">
			<col span="1">
			<col span="1" style="background-color: #E2F0D9">
		</colgroup>
		<tr>
			<th width="130px;" height="40px;"class="texttp">문서 종류</th>
			<td><input type="hidden" id="anum" name="anum" readonly>&nbsp;지출결의서</td>
			<th width="130px;" height="40px;"class="texttp">작성자</th>
			<td><input type="hidden"  id="mnum" name="mnum" readonly>&nbsp;<%= ttmvo.getMname() %></td>
		</tr>
		<tr>
			<th width="130px;"  height="40px;"class="texttp">보존연한</th>
			<td>
			&nbsp;<select id="deletedate" name="deletedate" style="width:150px;" >
				<option value="deletedate1" selected>보존연한</option>
				<option value="1">1</option>
				<option value="3">3</option>
				<option value="5">5</option>
			</select>
			년</td>
			<th width="130px;"  height="40px;"class="texttp">보안등급</th>
			<td>
			&nbsp;<select id="aauth" name="aauth" style="width:150px;" >
				<option value="aauth1" selected>보안등급</option>
				<option value="09">S</option>
				<option value="04">A</option>
				<option value="03">B</option>
				<option value="01">C</option>

			</select>
			</td>
		</tr>
	</table>
	<br>
	<div><jsp:include page="modal.jsp"></jsp:include><br></div>
	<table border="1" class="vac_sign" name="vac_sign" id="vac_sign">
		<colgroup>
			<col span="1" style="background-color: #E2F0D9">
			<col span="5">
		</colgroup>
		<tbody><tr>
			<th rowspan="3" width="130px;" class="texttp">신청</th>
			<td class="mposition" id="n1"></td>
			<td class="mposition" id="n2"></td>
			<td class="mposition" id="n3"></td>
			<td class="mposition" id="n4"></td>
			<td class="mposition" id="n5"></td>
		</tr>
		<tr class="aauto">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			
		</tr>
		<tr class="rank">
			<td id="r1" class="mposition"></td>
			<td id="r2" class="mposition"></td>
			<td id="r3" class="mposition"></td>
			<td id="r4" class="mposition"></td>
			<td id="r5" class="mposition"></td>
		</tr>
		<tr>
			<th width="130px;" class="texttp">참조</th>
			<td colspan="6" style="height:50px;" name="areftext" id="areftext">
			</td>
		</tr>
	</tbody></table>
	<br><br>
	<table border="1">
		<tr>
			<th width="130px;" class="texttp">제목</th>
			<td><input type="text" id="esubject" name="esubject" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
		</tr>
	</table>
	<br>
	
	<table border="1">
		<tbody><tr class="expand01">
			<th width="8%" class="texttp">지출구분</th>
			<th width="10%" class="texttp">거래처</th>
			<th width="10%" class="texttp">회계기준월</th>
			<th width="22%" class="texttp">내용</th>
			<th width="8%" class="texttp">수량</th>
			<th width="8%" class="texttp">단가</th>
			<th width="8%" class="texttp">금액</th>
			<th width="8%" class="texttp">카드구분</th>
		</tr>
		<tr class="expand01">
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="etype01" name="etype" style="text-align:center; text-align-last: center;">
					<option value="-" selected>지출구분</option>
					<option value="01">식비</option>
					<option value="02">소모품</option>
					<option value="03">복리후생</option>
					<option value="04">교통비</option>
					<option value="05">운반비</option>
					<option value="06">숙박비</option>
					<option value="07">기타</option>
				</select></div>
			</td>
			<td><input type="text" id="edeal" name="edeal" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><div id="selectcontainer" style="text-align:center"><input type="date" id="ewhen" name="ewhen"></div></td>
			<td><input type="text" id="ememo" name="ememo" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eamount" name="eamount" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eprice" name="eprice" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="esum1" name="esum" class="rightali totalsum" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly></td>
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="ecard" name="ecard" style="text-align:center; text-align-last: center;">
					<option id="-" selected>카드구분</option>
					<option value="C">법인카드</option>
					<option value="P">개인카드</option>
				</select></div>
			</td>
			
		</tr>
		<tr class="expand01">
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="etype02" name="etype" style="text-align:center; text-align-last: center;">
					<option value="-" selected>지출구분</option>
					<option value="01">식비</option>
					<option value="02">소모품</option>
					<option value="03">복리후생</option>
					<option value="04">교통비</option>
					<option value="05">운반비</option>
					<option value="06">숙박비</option>
					<option value="07">기타</option>
				</select></div>
			</td>
			<td><input type="text" id="edeal" name="edeal" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><div id="selectcontainer" style="text-align:center"><input type="date" id="ewhen" name="ewhen"></div></td>
			<td><input type="text" id="ememo" name="ememo" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eamount" name="eamount" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eprice" name="eprice" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="esum2" name="esum" class="rightali totalsum" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly></td>
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="ecard" name="ecard" style="text-align:center; text-align-last: center;">
					<option value="-" selected>카드구분</option>
					<option value="C">법인카드</option>
					<option value="P">개인카드</option>
				</select></div>
			</td>
		</tr>
		<tr class="expand01">
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="etype03" name="etype" style="text-align:center; text-align-last: center;">
					<option value="-" selected>지출구분</option>
					<option value="01">식비</option>
					<option value="02">소모품</option>
					<option value="03">복리후생</option>
					<option value="04">교통비</option>
					<option value="05">운반비</option>
					<option value="06">숙박비</option>
					<option value="07">기타</option>
				</select></div>
			</td>
			<td><input type="text" id="edeal" name="edeal" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><div id="selectcontainer" style="text-align:center"><input type="date" id="ewhen" name="ewhen"></div></td>
			<td><input type="text" id="ememo" name="ememo" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eamount" name="eamount" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eprice" name="eprice" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="esum3" name="esum" class="rightali totalsum" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly></td>
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="ecard" name="ecard" style="text-align:center; text-align-last: center;">
					<option value="-" selected>카드구분</option>
					<option value="C">법인카드</option>
					<option value="P">개인카드</option>
				</select></div>
			</td>
		</tr>
		<tr class="expand01">
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="etype04" name="etype" style="text-align:center; text-align-last: center;">
					<option value="-" selected>지출구분</option>
					<option value="01">식비</option>
					<option value="02">소모품</option>
					<option value="03">복리후생</option>
					<option value="04">교통비</option>
					<option value="05">운반비</option>
					<option value="06">숙박비</option>
					<option value="07">기타</option>
				</select></div>
			</td>
			<td><input type="text" id="edeal" name="edeal" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><div id="selectcontainer" style="text-align:center"><input type="date" id="ewhen" name="ewhen"></div></td>
			<td><input type="text" id="ememo" name="ememo" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eamount" name="eamount" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eprice" name="eprice" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="esum4" name="esum" class="rightali totalsum" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly></td>
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="ecard" name="ecard" style="text-align:center; text-align-last: center;">
					<option value="-" selected>카드구분</option>
					<option value="C">법인카드</option>
					<option value="P">개인카드</option>
				</select></div>
			</td>
		</tr>
		<tr class="expand01">
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="etype05" name="etype" style="text-align:center; text-align-last: center;">
					<option value="-" selected>지출구분</option>
					<option value="01">식비</option>
					<option value="02">소모품</option>
					<option value="03">복리후생</option>
					<option value="04">교통비</option>
					<option value="05">운반비</option>
					<option value="06">숙박비</option>
					<option value="07">기타</option>
				</select></div>
			</td>
			<td><input type="text" id="edeal" name="edeal" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><div id="selectcontainer" style="text-align:center"><input type="date" id="ewhen" name="ewhen"></div></td>
			<td><input type="text" id="ememo" name="ememo" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eamount" name="eamount"class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="eprice" name="eprice" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
			<td><input type="text" id="esum5" name="esum" class="rightali totalsum" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly></td>
			<td>
				<div id="selectcontainer" style="text-align:center"><select id="ecard" name="ecard" style="text-align:center; text-align-last: center;">
					<option value="-" selected>카드구분</option>
					<option value="C">법인카드</option>
					<option value="P">개인카드</option>
				</select></div>
			</td>
		</tr>
		<tr>
			<th colspan="4" class="texttp">총합</th>
			<td colspan="4"><input type="text" id="sumresult" class="rightali" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
		</tr>
	</tbody>
	</table>
	<br>
	<table>
		<tr>
			<th class="texttp" style="border:solid 1px">파일선택</th>
			<td>&nbsp;<input type="file" id="efile" name="efile"></td>
		</tr>
	</table>
	<input type="hidden" name="aref" id="aref" value="">
	<input type="hidden" name="aapp" id="aapp" value="">
	<input type="hidden" id="atype" name="atype" value="E">
	<input type="hidden" name="alevel" id="alevel">
	</form>
	</div>
	</div>
	<script src="../resources/js/bootstrap.bundle.min.js"></script>
	</body>
</html>