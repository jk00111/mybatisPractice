<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kos.tr.old.member.vo.TerraMemberVO"%>
<%@page import="com.kos.tr.old.common.CodeUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kos.tr.old.common.T_Session"%>
<%@page import="java.util.Arrays" %>
<%@page import="com.kos.tr.old.app.vo.AppExpandVO" %>
<%
	ArrayList<TerraMemberVO> aList = (ArrayList<TerraMemberVO>)request.getAttribute("aList"); // 결재자들의 정보 조회
	ArrayList<TerraMemberVO> bList = (ArrayList<TerraMemberVO>)request.getAttribute("bList"); // 참조자들의 정보 조회
	ArrayList<AppExpandVO> eList = (ArrayList<AppExpandVO>)request.getAttribute("eList"); // 지출결의서
	ArrayList<TerraMemberVO> uList = (ArrayList<TerraMemberVO>)request.getAttribute("uList"); // 사용자 정보 조회
	ArrayList<TerraMemberVO> pList = (ArrayList<TerraMemberVO>)request.getAttribute("pList"); // 대결 맡긴 사람 정보 조회
	String passYN = (String) request.getAttribute("passYN");

	TerraMemberVO mvo = uList.get(0);
	int uplevel = Integer.parseInt(eList.get(0).getAlevel()) + 1 ;
	String step = CodeUtil.nowStep(eList.get(0).getAlevel(), eList.get(0).getAapp(), uList.get(0).getMnum());

	
	AppExpandVO aevo = eList.get(0);
	String app[] = aevo.getAapp().split(",");
	String memo[] = aevo.getEmemo().split(",");
	String etype[] = aevo.getEtype().split(",");
	String deal[] = aevo.getEdeal().split(",");
	String when[] = aevo.getEwhen().split(",");
	String amount[] = aevo.getEamount().split(",");
	String price[] = aevo.getEprice().split(",");
	String card[] = aevo.getEcard().split(",");
	
	
	ArrayList<String> ffList = new ArrayList<String>(Arrays.asList(card));
	
	int ff = ffList.indexOf("-");
	if ( ff == -1 ) {
		ff = 5;
	}
	System.out.println(ff);
	ArrayList<String> fList = new ArrayList<String>(Arrays.asList(app));
	
	int f = fList.indexOf(uList.get(0).getMnum());
	System.out.println(f);
	int j = 0;
	if(pList != null){
		j =  eList.indexOf(pList.get(0).getMnum());
	}
	String[] confirm = eList.get(0).getAconfirm().split(",");
	String ref = "";
	
	for (int i=0; i < bList.size(); i++){ 
		ref += bList.get(i).getMname() + " ";
	}
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
	
			
	.idx{
		cursor:pointer;
	}

	table {
	 border-collapse: collapse;
	 width:780px;
	}

	table, tr, td{
		border-collapse: collapse;
		border:1px solid black ;
		}
	
	td{
		width:130px;
		height:60px;
	}

	.mpo{
		background-color:#E2F0D9;
		text-align:center;
		font-weight:bold;
	}
</style>

<script type="text/javascript">


$(document).ready(function(){
	
	if (<%=f%> == -1 || <%=Integer.parseInt(eList.get(0).getAlevel())%> == 9) {
		$('#btnshow').css('display','none');
	}
	
	if ('<%=step%>' != '대기') {
		$('#reject').css('display','none');
	}
	
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
	
	if ('<%=passYN%>' == 'Y') {
		
		$('#passY').css("display" , "block");
	}
	
	var aconfirm = "";
	
	$(document).on('click', '#approval', function() {

		if ('<%=step%>' == '대기') {
			var num = $('#alevel').val('<%=uplevel%>');
			
			for (var i = 0; i < 5; i++) {
				var cVal = $('.confirms').eq(i).val();
				var cValCk = cVal ?? '`';
				
				if ('<%=uList.get(0).getMnum()%>' ===  cVal) {
					aconfirm += "<%=uList.get(0).getMnum()%>" + ",";					
				}else{
					aconfirm += cValCk + ",";	
				}
			}
			$('#aconfirm').val(aconfirm);
			console.log($('#aconfirm').val());
			
			$('#sangsinForm').attr({
				'action':'/tr_spring/appSangsinUpdate.tr',    
				'method':'POST',
				'enctype':'application/x-www-form-urlencoded'
			}).submit();
		}
		
		if ('<%=step%>' == '예정') {
			for (var i = 0; i < 5; i++) {
				var cVal = $('.confirms').eq(i).val();
				var cValCk = cVal ?? '`';
				
				if ('<%=uList.get(0).getMnum()%>' ===  cVal) {
					aconfirm += "<%=uList.get(0).getMnum()%>" + ",";					
				}else{
					aconfirm += cValCk + ",";	
				}
			}
			$('#aconfirm').val(aconfirm);
			var nCnt = frequency(aconfirm);
			var num = $('#alevel').val(parseInt('<%=uplevel%>') + nCnt);
			
			$('#sangsinForm').attr({
				'action':'/tr_spring/appSangsinUpdate.tr',    
				'method':'POST',
				'enctype':'application/x-www-form-urlencoded'
			}).submit();
		}
	});

	$(document).on('click', '#approvalproxy', function() {
		
		<%if(pList != null){
			step = CodeUtil.nowStep(eList.get(0).getAlevel(), eList.get(0).getAapp(), pList.get(0).getMnum());
		}%>
		if ('<%=step%>' == '대기') {
			var num = $('#alevel').val('<%=uplevel%>');
			
			for (var i = 0; i < 5; i++) {
				var cVal = $('.confirms').eq(i).val();
				var cValCk = cVal ?? '`';
				
				if ('<%=uList.get(0).getMnum()%>' ===  cVal) {
					aconfirm += "<%=uList.get(0).getMnum()%>" + ",";					
				}else{
					aconfirm += cValCk + ",";	
				}
			}

			$('#aconfirm').val(aconfirm);
			console.log($('#aconfirm').val());
			$('#sangsinForm').attr({
				'action':'/tr_spring/appSangsinUpdateProxy.tr',    
				'method':'POST',
				'enctype':'application/x-www-form-urlencoded'
			}).submit();
		}
		
		if ('<%=step%>' == '예정') {
			for (var i = 0; i < 5; i++) {
				var cVal = $('.confirms').eq(i).val();
				var cValCk = cVal ?? '`';
				
				if ('<%=uList.get(0).getMnum()%>' ===  cVal) {
					aconfirm += "<%=uList.get(0).getMnum()%>" + ",";					
				}else{
					aconfirm += cValCk + ",";	
				}
			}
			$('#aconfirm').val(aconfirm);
			var nCnt = frequency(aconfirm);
			var num = $('#alevel').val(parseInt('<%=uplevel%>') + nCnt);

			
			$('#sangsinForm').attr({
				'action':'/tr_spring/appSangsinUpdateProxy.tr',    
				'method':'POST',
				'enctype':'application/x-www-form-urlencoded'
			}).submit();
		}
	});
	
	
	$(document).on('click', '#passY', function() {
		
		for (var i = 0; i < 5; i++) {
			var cVal = $('.confirms').eq(i).val();
			var cValCk = cVal ?? '~';
			
			if ('<%=uList.get(0).getMnum()%>' ===  cVal) {
				aconfirm += "<%=uList.get(0).getMnum()%>" + ",";					
			}else{
				aconfirm += cValCk + ",";	
			}
		}
		
		$('#aconfirm').val(aconfirm);

		$('#alevel').val('09');	
		
		$('#sangsinForm').attr({
			'action':'/tr_spring/appPassUpdate.tr',    
			'method':'POST',
			'enctype':'application/x-www-form-urlencoded'
		}).submit();
	});
	
	$(document).on("click", '#reject', function(){
         $('#sangsinForm').attr({
            "action":"/tr_spring/appRejectUpdate.tr",
            "method":"POST",
            "enctype":"application/x-www-form-urlencoded"
         }).submit();
         
      });
	

	
	function frequency(str){
		var nCnt = 0;
		
		for (var i = 0; i < str.length; i++) {
			var at = str.charAt(i);
			
			if (at === '@') {
				nCnt++;
			}
		}
		return nCnt;
	}
	
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
				  <li><a class="dropdown-item" href="#">주소록</a></li>
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
			  <img src="../fileupload/tmem/<%= mvo.getMphoto() %>" alt="" width="35" height="35" class="rounded-circle me-2">
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
<form id="sangsinForm">
<div style="display: flex;" id="btnshow">
	<span id="reject" style="color:green;cursor:pointer;">반려</span>&nbsp;&nbsp;
	<span id="passY" style="display:none;color:green;cursor:pointer;">전결</span>
</div>
<br>
<table>
	<tr>
		<td class="mpo">문서 종류</td>
		<td colspan="2">&nbsp;기안서</td>
		<td class="mpo">문서 번호</td>
		<td colspan="2">&nbsp;<%=aevo.getAnum() %></td>
	</tr>
	<tr>
		<td class="mpo">기안 부서</td>
		<td colspan="2">&nbsp;<%=CodeUtil.dappVal(aevo.getMdeptno())%></td>
		<td class="mpo">기안자</td>
		<td colspan="2">&nbsp;<%=aevo.getMname()%></td>
	</tr>
	<tr>
		<td class="mpo">보존 연한</td>
		<td colspan="2">&nbsp;<%=aevo.getDeletedate()%> 년</td>
		<td class="mpo">보안 등급</td>
		<td colspan="2">&nbsp;<%=CodeUtil.aauthVal(aevo.getAauth())%></td>
	</tr>
	<tr>
		<td class="mpo">기안 일시</td>
		<td colspan="2">&nbsp;<%=aevo.getInsertdate()%></td>
		<td class="mpo">완료 일시</td>
		<td colspan="2">&nbsp;<%=aevo.getFindate()%></td>
	</tr>
	<tr>
		<td rowspan="3" class="mpo">결재</td>
		<% for (int i = 0; i < 5; i++) { %>
		<td class="mpo">
			<% if (i < aList.size()) { %>
				<%= CodeUtil.mpositionVal(aList.get(i).getMposition()) %>
			<% } %>
		</td>
		<% } %>
	</tr>
	<tr>
		<%if(pList != null && pList.size() > 0){ %>
		<% for (int i = 0; i < 5; i++) { %>
		<td style="text-align:center;">
			<%if(confirm[i].equals("~")) {%>
				<img style="width:50px;height:50px;" src="../resources/images/pass.png"/>
			<%}else if(confirm[i].equals("@")) {%>
				<img style="width:50px;height:50px;" src="../resources/images/traffic-signal.png"/>
				<input type="hidden" class="confirms" value="@"/>
			<%}else if (confirm[i].equals(app[i])) { %>
				<img style="width:50px;height:50px;" src="../resources/images/evaluate.png"/>
				<input type="hidden" class="confirms" value="<%=app[i]%>"/>
			<%}else if(app[i].equals(uList.get(0).getMnum())|| app[i].equals(pList.get(0).getMnum())){ %>
				<input type="button" value="대결" name="approvalproxy" id="approvalproxy" style="cursor:pointer;"/>  
				<input type="hidden" class="confirms" value="<%=uList.get(0).getMnum()%>"/>
			<%}else if(i < f || i < j){ %>
				<input type="hidden" class="confirms" value="@"/>
		</td>
		<% }}}else{%>
		<% for (int i = 0; i < 5; i++) { %>
		<td style="text-align:center;">
			<%if(confirm[i].equals("~")) {%>
				<img style="width:50px;height:50px;" src="../resources/images/pass.png"/>
			<%}else if(confirm[i].equals("@")) {%>
				<img style="width:50px;height:50px;" src="../resources/images/traffic-signal.png"/>
				<input type="hidden" class="confirms" value="@"/>
			<%}else if (confirm[i].equals(app[i])) { %>
				<img style="width:50px;height:50px;" src="../resources/images/evaluate.png"/>
				<input type="hidden" class="confirms" value="<%=app[i]%>"/>
			<%}else if(app[i].equals(uList.get(0).getMnum())){ %>
				<input type="button" value="결재" name="approval" id="approval"style="cursor:pointer;"/>
				<input type="hidden" class="confirms" value="<%=uList.get(0).getMnum()%>"/>
			<%}else if(i < f || i < j){ %>
				<input type="hidden" class="confirms" value="@"/>
		</td>
		<% }}} %>
	</tr>
	<tr>
		<% for (int i = 0; i < 5; i++) { %>
		<td style="text-align:center">
			<% if (i < aList.size()) { %>
				<%= aList.get(i).getMname() %>
		<% } %>
		</td>
		<% } %>
	</tr>
	<tr>
		<td class="mpo" style="width:130px">참조</td>
		<td colspan="5"><%=ref%></td>
	</tr>
</table>
	<br>
<table>
	<tr>
		<td class="mpo" style="width:130px;height:40px" colspan="2">제목</td>
		<td colspan="6" style="height:40px">&nbsp;<%= aevo.getEsubject() %></td>
	</tr>
	<tr>
		<td class="mpo" style="height:40px">지출구분</td>
		<td class="mpo" style="height:40px">거래처</td>
		<td class="mpo" style="height:40px">회계기준월</td>
		<td class="mpo" style="width:300px;height:40px">내용</td>
		<td class="mpo" style="height:40px">수량</td>
		<td class="mpo" style="height:40px">단가</td>
		<td class="mpo" style="height:40px">금액</td>
		<td class="mpo" style="height:40px">카드</td>
	</tr>
	<% 
		int sum = 0;
		for(int i=0; i < ff; i++) {%>
	<tr>
		<td style="text-align:center;height:40px"><%= CodeUtil.expandEtype(etype[i]) %></td>
		<td style="text-align:center;height:40px"><%= deal[i] %></td>
		<td style="text-align:center;height:40px"><%= when[i] %></td>
		<td style="text-align:center;height:40px"><%= memo[i] %></td>
		<td style="text-align:right;height:40px"><%= amount[i] %>개&nbsp;</td>
		<td style="text-align:right;height:40px"><%= price[i] %>원&nbsp;</td>
		<td style="text-align:right;height:40px"><%= Integer.parseInt(amount[i]) * Integer.parseInt(price[i])%>원&nbsp;</td>
		<td style="text-align:center;height:40px"><%= CodeUtil.expandEcard(card[i])%></td>
	</tr>
	<% 
		sum += Integer.parseInt(amount[i]) * Integer.parseInt(price[i]);
		} %>
	<tr>
	<td colspan="2" class="mpo" style="height:40px">총합</td>
	<td colspan="6" style="text-align:right;height:40px"><%=sum%>원&nbsp;</td>
	</tr>
</table>	
	<br>
	<hr>
	<input type="hidden" id="alevel" name="alevel"/>
	<input type="hidden" id="aconfirm" name="aconfirm"/>
	<input type="hidden" id="aapp" name="aapp" value="<%=aevo.getAapp()%>"/>
	<input type="hidden" id="anum" name="anum" value="<%=aevo.getAnum()%>"/>
	<input type="hidden" id="atype" name="atype" value="<%=aevo.getAtype()%>"/>
</form>
	</div>
	</div>
	<script src="../resources/js/bootstrap.bundle.min.js"></script>



</body>
</html>