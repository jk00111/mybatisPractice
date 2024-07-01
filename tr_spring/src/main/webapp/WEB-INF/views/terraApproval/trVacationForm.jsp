<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@page import="com.kos.tr.old.common.CodeUtil"%>
<%@page import="com.kos.tr.old.app.vo.AppDocVO"%>
<%@page import="com.kos.tr.old.member.vo.TerraMemberVO"%>
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
				
				// 결재라인 서명
				$(document).on("click", "#rBtn", function() {
					
					var rank1 = $("#rank1").val();
					$("#r1").text(rank1);
									
				});
				
				// 결재라인 도장 ---- 수정필요함
				$(document).on("click", "#aBtn", function() {
					var auto1 = $("#auto").val();
					 // $("#a1").get(auto1);
				});
			
				// 기안하기 
				$(document).on("click", "#alevel01", function() {
					
					var a = $('.ql-editor').html();
		            
		            var b =$("#vmemo").val(a);
					
					$('#alevel').val("01");
					
					$('#vacApprovalForm').attr({
						'action':'../appInsert/V.tr',
						'method':'POST',
						'enctype':'multipart/form-data'
					}).submit();
				});
				
				// 휴가날짜 계산하기
				$("#enddate").change(function() {
					
					let startdate = document.querySelector("#startdate").value;
					let enddate = document.querySelector("#enddate").value;
					
					startdate = new Date(startdate); 
					enddate = new Date(enddate);  
					
					if (enddate <= startdate) {
						alert("휴가종료일을 확인해주세요");
						$("#enddate").val("");
					
						return false;
					}
					
					var weekdayCount = getWeekdayCount(startdate, enddate);
					console.log(weekdayCount);  
					
					
					$("#mholiday").val(Number("15")-weekdayCount);
					
				});
				
				function getWeekdayCount(startdate, enddate) {
				
					  var count = 0;
					  var currentDate = new Date(startdate);
					
					  // endDate까지 반복하면서 주말(토요일과 일요일)을 제외한 평일 수를 계산합니다.
					  while (currentDate <= enddate) {
					    var dayOfWeek = currentDate.getDay();
					    if (dayOfWeek !== 0 && dayOfWeek !== 6) {
					      count++;
					    }
					    currentDate.setDate(currentDate.getDate() + 1);
					  }
					
					  return count;
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
		<form name="vacApprovalForm" id="vacApprovalForm">
		<p id="alevel01" style="color:green">기안하기</p>
	<table border="1" class="type" name="type" id="type">
		<colgroup>
			<col span="1" style="background-color: #E2F0D9">
			<col span="1">
			<col span="1" style="background-color: #E2F0D9">
		</colgroup>
		<tr>
			<th width="130px;" height="40px;"class="texttp">문서 종류</th>
			<td><input type="hidden" id="anum" name="anum" readonly>&nbsp;휴가신청서</td>
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
		<tr>
			<th rowspan="3" width="130px;"class="texttp">신청</th>
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
			<td class="mposition" id="r1"></td>
			<td class="mposition" id="r2"></td>
			<td class="mposition" id="r3"></td>
			<td class="mposition" id="r4"></td>
			<td class="mposition" id="r5"></td>
		</tr>
		<tr>
			<th width="130px;"class="texttp">참조</th>
			<td colspan="6" style="height:50px;" name="areftext" id="areftext">
			</td>
		</tr>
	</table>
	<br>
	<br>
	<table border="1">
		<tr>
			<th width="130px;" style="background-color: #E2F0D9"class="texttp">잔여휴가</th>
			<td><input type="text" name="mholiday" id="mholiday" style=" width: 20px; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" value="<%=ttmvo.getMholiday() %>" readonly>일</td>
		</tr>
	</table>
	<br>
	<div>

		<table border="1" class="vac_start" name="vac_start" id="vac_start"> 
			<tr>
				<th id="start_end" width="130px;" style="background-color: #E2F0D9"class="texttp">휴가시작일</th>	
				<td>&nbsp;<input type="date" id="startdate" name="startdate"></td>
			</tr>
			<tr>
				<th id="start_end" width="130px;" style="background-color: #E2F0D9"class="texttp">휴가종료일</th>
				<td>&nbsp;<input type="date" id="enddate" name="enddate"></td>
			</tr>
		</table>
		<br><br>
		<table border="1" class="vac_user" name="vac_user" id="vac_user">
			<tr>
			<th width="130px;" style="background-color: #E2F0D9"class="texttp">제목</th>
			<td>
				<input type="text" id="vsubject" name="vsubject" style="width:95%; outline:none; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;">
			</td>
		</tr>	
	</table>
	</div>
	<br>
		<div id="editor" class="initial-size"></div>	
	    <script>
			
	  		var quill = new Quill('#editor', {
	    	  // Quill 설정 옵션
	    	});
	
	    	// 작성 영역 클릭 시 이벤트 처리
	    	var editorContainer = document.querySelector('#editor');
	    	editorContainer.addEventListener('click', function() {
	    	  quill.focus(); // 작성 영역으로 포커스 이동
	    	});
	    
	        var toolbarOptions = [
	            ['bold', 'italic', 'underline', 'strike'],
	            ['link', 'image'],
	            [{ 'list': 'ordered' }, { 'list': 'bullet' }],
	            [{ 'header': [1, 2] }]
	        ];
			
	        var quill = new Quill('#editor', {
	            modules: {
	                toolbar: toolbarOptions
	            },
	            theme: 'snow'
	        });
	        
	        function myFunction(){
	        
	        }
	        
    </script>
	<br>
	<table border="1" class="type">
		<tr>
			<th width="130px;" style="background-color: #E2F0D9" class="texttp">첨부파일</th>
			<td>&nbsp;<input type="file" id="vfile" name="vfile"></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="aref" id="aref" value="">
	<input type="hidden" name="aapp" id="aapp" value="">
	<input type="hidden" name="atype" id="atype" value="V">
	<input type="hidden" id="vmemo" name="vmemo">
	<input type="hidden" name="alevel" id="alevel">
	</form>
	</div>
	</div>
	<script src="../resources/js/bootstrap.bundle.min.js"></script>
	</body>
</html>