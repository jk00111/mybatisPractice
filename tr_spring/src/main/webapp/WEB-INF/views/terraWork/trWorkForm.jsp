<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kos.tr.old.work.vo.TerraWorkVO" %>
<%@ page import="com.kos.tr.old.member.vo.TerraMemberVO" %>

<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<% 	request.setCharacterEncoding("UTF-8"); %>
<% 	Logger logger = LoggerFactory.getLogger(this.getClass()); %>
<% 	logger.info("trWorkForm 진입"); %>
<% 	ArrayList<TerraMemberVO> uList = (ArrayList<TerraMemberVO>)request.getAttribute("uList");
	TerraMemberVO mvo = uList.get(0);
	
	String mnum = uList.get(0).getMnum();
	logger.info("trWorkForm | 로그인세션 <<< mnum", mnum);
	
	// 
	Object wnchk = (String) request.getAttribute("datashift");
	
	String[] worknow_2 = null;
	String[] s_work = null;
	String wtime = null;
	String wstateL = null;
	int wlen = 0;
	
%>	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Personnel Management Page</title>
	
	<script type="text/javascript" src="/tr_spring/resources/script/common.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1, user-scalable=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/tr_spring/resources/css/sidebars.css">
	<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
	<script src="/tr_spring/resources/js/util.js"></script>
	
	<style>
	.icon_img{
		width:22px;
		height: 22px;
		margin-bottom: 10px;
		margin-left: 25px;
	}
	
	.dropdown-toggle-no-caret::after {
		display: none;
	}
	
	.docimg{
		height:300px;
	
	}
	
	.idx{
		cursor:pointer;
	}
	
	.doctext{
		font-weight:bold;
		font-size:25px;
	}
	
	#nowSelected{
		background-color: #C5E0B4;

	}
	</style>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
		
			$(document).on('mouseenter', '.idx', function(){
				
				$(this).css('background-color', '#ecf9ec');
			});
		
			$(document).on('mouseleave', '.idx', function(){
		
				$(this).css('background-color', '#E2F0D9');
				});
			
			$(document).on('mouseleave', '#nowSelected', function(){
		
				$(this).css('background-color', '#C5E0B4');
			});
			
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
			
			// 받아온 데이터를  한 번 더 처리
			var mnum = '<%=mnum%>';
			$("#mnum").val(mnum);
						
			var mnum_chk = $("#mnum").val();
			console.log("mnum 값 : " + mnum_chk +" | " + typeof(mnum_chk));
			
			// mnum의 null 체크
			$(function (){
				if(mnum_chk == "null"){
					alert("로그인세션없음");
					callToMain();
				}else{
					restVacation();
					workHour();
					worknow();
					workEtc();
				}
			});
			
			// 근무상태 변경 버튼 jQuery 시작 ================================
			var wchange = null;	
				
			// 업무:01
			$("#sWork").click(function(){
				wchange = $("#sWork").val();
				callWchange();
			});
			
			// 외근:02
			$("#sWo").click(function(){
				wchange = $("#sWo").val();
				callWchange();
			});
			
			// 외출:03
			$("#sOut").click(function(){
				wchange = $("#sOut").val();
				callWchange();
			});
			
			// 회의:04
			$("#sCon").click(function(){
				wchange = $("#sCon").val();
				callWchange();
			});
			
			// 퇴근하기:05
			$("#sLto").click(function(){
				wchange = $("#sLto").val();
				callWchange();
			});
			
			// 조퇴:05
			$("#sElto").click(function(){
				
				let today = new Date();   
				let hours = ('0' + today.getHours()).slice(-2); 
				let minutes = ('0' + today.getMinutes()).slice(-2);

				let timeString = hours + minutes;
				console.log(timeString);
				let timeInt = parseInt(timeString);
				console.log("timeInt | " + timeInt + " | type | " + typeof(timeInt));
				let timechk = 1800;
				console.log("timechk | " + timechk + " | type | " + typeof(timechk));
				
				if (timeInt < timechk){
					wchange = $("#sElto").val();
					callWchange();
				}
			});
			// 근무상태 변경 버튼 jQuery 종료 ================================
			
			// 근태현황 | 지각, 조퇴, 퇴근미체크, 결근
			function workEtc(){
				$.ajax({
					url: "workEtc.tr",
					type: "GET",
					data: {	mnum: $("#mnum").val()},
					success: whenSuccess,
					error: whenError
				}); // end of ajax : 근태현황
				
				//etcData 
				function whenSuccess(etcData) {	
					console.log("근태현황 리턴성공 >>> : " + etcData);
					console.log("근태현황 리턴 타입 : " + typeof(etcData));
					let wEtc = etcData.split(",");
					console.log("근태현황 자르기 1번째 | 지각 " + wEtc[0]);
					console.log("근태현황 자르기 2번째 | 조퇴 " + wEtc[1]);
					console.log("근태현황 자르기 3번째 | 퇴근미체크 " + wEtc[2]);
					console.log("근태현황 자르기 마지막 | 결근 " + wEtc[3]);
					$('#late').text(wEtc[0]);
					$('#early').text(wEtc[1]);
					$('#notchk').text(wEtc[2]);
					$('#absence').text(wEtc[3]);
					
				}					
			
				function whenError(e){
					alert("근태현황 통신실패 >>>> : ");
				}
			};
				
			// 잔여휴가>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			function restVacation() {
				$.ajax({
					url: "workSelectVa.tr",
					type: "GET",
					data: {	mnum: $("#mnum").val()},
					success: whenSuccess,
					error: whenError
				}); // end of ajax : 잔여휴가
				
				function whenSuccess(vaData) {	
					console.log("휴가 리턴 데이터 출력 >>> : " + vaData);
					$("#restvacation").val(vaData);
				}					
				
				function whenError(e){
					alert(JSON.stringify(e));
				}
				//잔여휴가 끝<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			}
			
			// INSERT : 출근하기 버튼 AJAX
			$('#sCtw').click(function(){
				callAjax();
				callPython();
				
			}); // end of $('#ctW').click
			
			function callAjax(){
				$.ajax({
					url: "workInsert.tr",
					type: "GET",
					data: {	
							mnum: $("#mnum").val(),
							wstate: $("#sCtw").val()
							},
					success: whenSuccess,
					error: whenError,
				}); // end of ajax

				function whenSuccess(resData) {
					worknow();
				} // 출근하기 성공시, select 함수 호출
				function whenError(){
					
					alert("callAjax() | error");
					
				}
			} //INSEERT : 출근하기 END 
			
			function callPython(){
				$.ajax({
					url: "http://192.168.0.36:5000/chulgeun",
					type: "GET",
					success: whenSuccess,
					error: whenError,
				}); // end of ajax

				function whenSuccess(resData) {
				}
				function whenError(){
				}
			} //INSEERT : 출근하기 END 
			// 현재시각 표시===================================================
			$(function Time(){
			// 현재 시간 표시 시작
				function printTime(){
					let md = new Date();
					
					let hh = ('0' + md.getHours()).slice(-2);
					let mi = ('0' + md.getMinutes()).slice(-2);
					let ss = ('0' + md.getSeconds()).slice(-2);
					
					let time = 	numpad(hh) + ":" +
								numpad(mi) + ":" +
								numpad(ss);
					document.getElementById("timer").innerHTML=time;
				}
				setInterval(printTime, 1000);

			}); // 현재시간 표시 끝
			// 현재시각 표시 END===============================================
			
			// mnum값 없으면(세션 없음) 로그인 메인으로 이동
			function callToMain() {
				
				$('#trForm').attr({
					'action':'loginForm.tr',
					'method':'GET',
					'enctype':'multipart/form-data'
				}).submit();				
			}

			// 출근 외 상태 변화
			function callWchange(){
				
				$.ajax({
					url: "workUpdate.tr",
					type: "GET",
					data: {	
							mnum: $("#mnum").val(),
							wstate: wchange,
							startdate:$("#startdate").val(),
							enddate: $("#enddate").val()							
						},
					success: whenSuccess,
					error: whenError,
				}); // end of ajax : 근무상태변화
				
				function whenSuccess(cwData) {	
					console.log("근무상태변화 리턴  성공>>> : " );
					worknow();
				}					
				
				function whenError(e){
					alert("출근 외 상태변화 >>> : " + JSON.stringify(e));
				}
			} // end of callWchange()
			
			// 올해 근무 정보 - 근무시간>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			function workHour(){
				$.ajax({
					url: "workHour.tr",
					type: "GET",
					data: {	mnum: $("#mnum").val()},
					success: whenSuccess,
					error: whenError
				}); // end of ajax : 잔여휴가
				
				//whData : dayNhour
				function whenSuccess(whData) {	
					console.log("올해 근무시간 정보 출력 >>> : " + whData);
					let typechk = typeof(whData);
					console.log("올해 근무시간 데이터타입 >>> " + typechk);
					let arrwhData = whData.split(",");
					$("#workday").text(arrwhData[0]);  // 근무일수
					$("#workhour").text(arrwhData[1]); // 총근무시간 - 시간
					$("#workmin").text(arrwhData[2]);  //  총근무시간 - 분
				}					
				
				function whenError(e){
					alert(JSON.stringify(e));
				}
			}
			// 올해 근무 정보 - 근무시간<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			// 근무현황 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			function worknow() {
				$.ajax({
					url: "workSelect.tr",
					type: "Get",
					data: {	mnum: $("#mnum").val()},
					success: whenSuccess,
					error: whenError
				}); // end of ajax : 근무현황
				
				//whData : dayNhour
				function whenSuccess(wnData) {	
					$('#wndiv').load(location.href+' #wntable');
				}					
				
				function whenError(e){
					alert(JSON.stringify(e));
				}
			}
			// 근무현황 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			$(document).on('click', '.vacApp', function(){
				location.href="appInsertForm/V.tr";
			});
		});	// end of $(document).ready(function(){});
		
		
	</script>
		<style type="text/css">
			.t_out{
 				border: 2px solid #cccccc;
				border-radius: 5px;
				width: 95%;
				height: 95%;
				margin-left:auto;
				margin-right:auto;
				absolute;
				
			}
			.div1{
				float: left; 
				width: 33%;
				height: 250px;
			}
			.div2{
				float: left; 
				width: 33%;
				height: 400px;
			}
			.timer_tr{
				align-content:center;
			}
			thead, td{
				text-align:center;
			}
			td{
				vertical-align:top;
			}
			th{
				height:50px;
				font-size:20px;
			}
			.td_0{
				width:20%;
			}
			.td_1{
				vertical-align:middle;
				width: 33%;
			}
			.hl{
				text-decoration-line:none;
			}
			.td_3, .td_4{
				height : 15px;
			}
			.tn{
				font-weight:bold;
				font-size: 30px;
			}
			.st{
				margin : 0 ;
			}

			.wchkBtn2{
				width: 100px;
				height: 40px;
				font-size: 17px;
				font-weight:bold;
				background-color: #92D050;
				border: 2px #C5E0B4 solid;
				border-radius:10px;
				cursor:pointer;
				color:black;
			}
			#vaBtn{
				width: 90px;
				height: 40px;
				font-size: 17px;
				font-weight:bold;
				background-color: #92D050;
				border: 2px #C5E0B4 solid;
			}
		</style>
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark sticky-top" style="background-color: #C5E0B4;padding-right:0;">
		<!--LOGO-->
		<a class="navbar-brand" href="/tr_spring/mainPage.tr" >&nbsp&nbsp<img src="../tr_spring/resources/images/logo-green2.png" style="height:30px"></a>
		<!--Links-->
		<div class="container-fluid full-width">
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false" style="padding-top:13px;font-size:18px;color:#534825;font-weight:bold;cursor:pointer">인사</a>
				<ul class="dropdown-menu" aria-labelledby="dropdown01">
				  <li><a class="dropdown-item" href="/tr_spring/mainPage.tr">메인페이지</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/tBoardSelectAll.tr">게시판</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/tMemAddrBook.tr">주소록</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/appSelect/A.tr">전자결재</a></li>
				  <li><a class="dropdown-item" href="#">일정</a></li>
				  <li><a class="dropdown-item" href="/tr_spring/workSelect.tr">인사</a></li>
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
			  <img src="fileupload/tmem/<%= mvo.getMphoto() %>" alt="" width="35" height="35" class="rounded-circle me-2">
			</a>
			<ul class="dropdown-menu dropdown-menu-dark text-small shadow" style="left:-110px" aria-labelledby="dropdownUser1">
			  <li><a class="dropdown-item" href="#">마이 페이지</a></li>
			  <li><hr class="dropdown-divider"></li>
			  <li><a class="dropdown-item" href="terraLogout.tr">로그아웃</a></li>
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
				<button type="button" class="btn btn-primary vacApp" id="istBtn" style="background-color:#92D050;height:50px;border:0; font-size:20px;font-weight:1000;color:#534825;cursor:pointer">+ 휴가신청</button>
				<br>
				<ul class="list-unstyled ps-0">
					<li class="mb-1">
					  <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true" style="font-size: 20px;cursor:pointer">
						근무
					  </button>
					  <div class="collapse show" id="home-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						  <li><div class="idx" id="nowSelected" onclick="javascript:location.href='/tr_spring/workSelect.tr'"><img src="/tr_spring/resources/images/documents.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">근무현황</span></div></li>
						</ul>
					  </div>
					</li>
				  </ul>
			  </div>
			</div>
		  </div>
		</div>
		  <div class="col-9" style="left:400px;margin-top:60px">
			<!-- 본문 내용 -->
			<div class="content">
			  <!-- 본문 내용을 추가 -->
<form name="trForm" id="trForm">
	<!-- 테스트용 사원번호 M20230001 -->
	<input type="hidden" name="mnum" id="mnum" value="">
	<input type="hidden" name="wnum" id="wnum" value="">
	<input type="hidden" name="startdate" id="startdate" value="">
	<input type="hidden" name="enddate" id="enddate" value="">
	<input type="hidden" name="deleteyn" id="deleteyn" value="Y">
	<input type="hidden" name="insertdate" id="insertdate" value="">
	<input type="hidden" name="updatedate" id="updatedate" value="">
	<input type="hidden" name="wchange" id="wchange" value="">
	<!-- DIV 시작 -->
	<div class="main">
	<h4>올해 근무 정보</h4>
		<div class="d_top" id="dtop">
		<!--근태 현황-->
		<div class="div1" id="Etcdiv">
			<table class="t_out" id="Etctbl">
				<thead>
					<tr>
						<th colspan="4" rowspan="2">근태현황<br><hr style="width:90%;"></th>
					</tr>					
				</thead>
				<tbody>				
					<tr>
						<td class="td_0">
							<br>
							<p class="ss">지각</p>
							<span id="late">0</span>회
						</td>
						<td class="td_0">
						<br>
							<p class="ss">조기퇴근</p>
							<span id="early">0</span>회
						</td>
						<td class="td_0">	
						<br>
							<p class="ss">퇴근미체크</p>
							<span id="notchk">0</span>회
						</td>
						<td class="td_0">
						<br>
							<p class="ss">결근</p>
							<span id="absence">0</span>회
						</td>
					</tr>						
				</tbody>				
			</table>
		</div>
		</div>
		<!--휴가 현황-->
		<div class="div1">
			<table class="t_out">
				<thead>
					<tr>
						<th colspan="3">휴가현황<br><hr style="width:90%;"></th>
					</tr>	
				</thead>
				<tbody>
					<tr>
						<td class="td_1">
							<p class="ss">잔여휴가</p>
							<input 	type="text" id="restvacation" value="" align="center" 
									style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; width:25px;font-size:20px;" readonly> 일
						</td>
						<td class="td_1">
						</td>
						<td class="td_1" style=vertical-align:middle;>
							<span class="vacApp"style="font-weight:bold;font-size:20px;color:#92D050;cursor:pointer">휴가 신청</span>	
						</td>
					</tr>
					<tr >
						<td colspan="3">
							<br>
						</td>
					</tr>
				</tbody>				
			</table>
		</div>
		<!--근무시간-->
		<div class="div1">
			<table class="t_out">
				<thead>
					<tr>
						<th colspan="2">근무시간<br><hr style="width:90%;"></th>
					</tr>					
				</thead>
				<tbody>
					<tr>
						<td class="td_1">
							<p class="ss">근무일수</p>
							<span id="workday">0</span><span>일</span>
							<br><br><br>
						</td>
						<td class="td_1">
							<p class="ss">총근무시간</p>
							<span id="workhour">0</span><span>시간</span><span id="workmin">0</span><span>분</span>
							<br><br><br>
						</td>
					</tr>
				</tbody>				
			</table>
		</div>
	</div>
	<!-- 아래칸 -->
	<div class="main2" >
	<div>
	<h4>오늘 근무 현황</h4>
		</div>
		<!--근무 체크-->
		<div class="div2">
			<table class="t_out">
				<thead>
					<tr>
						<th colspan="4">근무체크<br><hr style="width:90%;"></th>
					</tr>				
				</thead>
				<tbody>
					<tr id="timer_tr">
						<td colspan="3">
							<span class="tn" id="timetxt">현재시각 : </span>
							<span class="tn" id="timer"> </span>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<!-- 출근 : come to work -->
							<hr style="width:90%;">
							<input type="button" class="btn btn-primary wchkBtn2" style="background-color:#92D050;border-color:#92D050;width:100px;" name="wstate" id="sCtw" value="출근하기">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn btn-primary wchkBtn2" style="background-color:#92D050;border-color:#92D050;width:100px;" name="wstate" id="sLto" value="퇴근하기">
							<br>
							<hr style="width:90%;">
							<input type="button" class="btn btn-primary wchkBtn2" style="background-color:#92D050;border-color:#92D050;width:100px;" name="wstate" id="sWork" value="업무">
							&nbsp;&nbsp;
							<input type="button" class="btn btn-primary wchkBtn2" style="background-color:#92D050;border-color:#92D050;width:100px;" name="wstate" id="sOut" value="외출">
							&nbsp;&nbsp;
							<input type="button" class="btn btn-primary wchkBtn2" style="background-color:#92D050;border-color:#92D050;width:100px;" name="wstate" id="sCon" value="회의"><br><br>
							<input type="button" class="btn btn-primary wchkBtn2" style="background-color:#92D050;border-color:#92D050;width:100px;" name="wstate" id="sWo" value="외근">
							&nbsp;&nbsp;
							<input type="button" class="btn btn-primary wchkBtn2" style="background-color:#92D050;border-color:#92D050;width:100px;" name="wstate" id="sElto" value="조퇴">
					</tr>
				</tbody>				 
			</table>
		</div>
		<!-- SELECT 데이터 가져오기 -->		
		<!--근무 현황-->
		<!--게시판 셀렉트 올처럼 표시해야해....-->
		<div class="div2" id="wndiv">
			<table class="t_out" id="wntable">
			<thead>
					<tr>
						<th colspan="2">근무현황<br><hr style="width:90%;"></th>
					</tr>
					<tr>
						<td class="td_3">
							<p class="st">시간</p>
							<hr style="width:75%;">
						</td>
						<td class="td_3">
							<p class="st">상태</p>
							<hr style="width:75%;">
						</td>
					</tr>
			</thead>
			<tbody id="wnbody">
<tr>
<td colspan="2">
<% 
		
	if(wnchk !=  null){
		String worknow_1 = (String)request.getAttribute("datashift");
		
		// 넘어온 데이터 1차 split => 시간,상태코드 형태
		worknow_2 = worknow_1.split("~");
	
		wlen = worknow_2.length;
		System.out.println("trWorkForm | wlen 체크 | " + wlen );
		System.out.println("trWorkForm | wlen 체크 | worknow_2[0] | " + worknow_2[0]);
		
//		int j = 0;
//		int k = 0;
		int i=0;
		for (i=0; i < wlen; i++) {

			s_work = worknow_2[i].split(",");
			//01:업무, 02:외근, 03:외출, 04:회의, 05:퇴근
			String txtcng = s_work[1];
			
			if(txtcng.equals("01")){
				// 출근|업무 구분 넣어야함>>>>>>>>>>>>>>>> 출퇴근 9 to 6 가정
				if (i==0){
					s_work[1] = s_work[1].replace("01", "출근");
				}else{
					s_work[1] = s_work[1].replace("01", "업무");	
				}	
			}else if(txtcng.equals("02")){
				s_work[1] = s_work[1].replace("02", "외근");
			}else if(txtcng.equals("03")){
				s_work[1] = s_work[1].replace("03", "외출");
			}else if(txtcng.equals("04")){
				s_work[1] = s_work[1].replace("04", "회의");
			}else if(txtcng.equals("05")){
				s_work[1] = s_work[1].replace("05", "퇴근");
			}
			logger.info("trWorkForm | s_work[0] | {}",s_work[0]);
			logger.info("trWorkForm | s_work[1] | {}",s_work[1]);		
			
%>
			
				<span><%=s_work[0]%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=s_work[1]%>&nbsp;<br></span>
			
<%
		}
	}
	System.out.println("근무현황 출력 종료 =============================");
%>
</td>
</tr>

			</tbody>
		</table>							
		</div>
		<!-- 근무 통계-->
		<div class="div2">
			<table class="t_out">
				<thead>
					<tr>
						<th colspan="2">근무통계<br><hr style="width:90%;"></th>
					</tr>			
				</thead>
				<tbody>
					<tr>
						<td><img id="chul" src="fileupload/sigak/chulgeun.png" style="width:400px;height:300px"></td>
					</tr>				
				</tbody>				
			</table>
		
		</div>
	</div>
	
</form>
		</div>
	</div>
		<script src="/tr_spring/resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>