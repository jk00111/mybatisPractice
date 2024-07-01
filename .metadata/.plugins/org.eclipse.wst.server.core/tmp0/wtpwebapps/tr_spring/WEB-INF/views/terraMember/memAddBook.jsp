<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kos.tr.common.CodeUtil"%>
<%@page import="com.kos.tr.member.vo.TerraMemberVO"%>
<%
	
	ArrayList<TerraMemberVO> uList = (ArrayList<TerraMemberVO>)request.getAttribute("uList");
	TerraMemberVO mvo = uList.get(0);

	int pageSize 	= 0;
	int groupSize 	= 0;
	int curPage 	= 0;
	int totalCount 	= 0;
	
	Object objPaging = request.getAttribute("pagingTMVO");
	TerraMemberVO pagingTMVO = (TerraMemberVO)objPaging;
	
	Object obj = request.getAttribute("listAll");
	if (obj == null) return;
	
	ArrayList<TerraMemberVO> aList = (ArrayList<TerraMemberVO>)obj;

%>
<!DOCTYPE html>
<html>
<head>
<title> post page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1, user-scalable=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/sidebars.css">
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
<script src="resources/js/util.js"></script>

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

 	th{ 
		background-color:#E2F0D9;
		text-align:center;

	}
	
	td{
		text-align:center;
	}
	
	.subj{
		width:400px;
	}
	
	.idx{
		cursor:pointer;
	}
	
	#nowSelected{
		background-color: #C5E0B4;
	}
	
	</style>
	<script>
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
			
			$(document).on('click', '.contents', function(){
				var nameVal = $(this).find('td').eq(0).text();
				var mailVal = $(this).find('td').eq(1).text();
				var hpVal = $(this).find('td').eq(2).text();
				var positionVal = $(this).find('td').eq(3).text();
				var deptVal = $(this).find('td').eq(4).text();
				var photoVal = $(this).find('input').eq(0).val();
				
				$('#emname').text(nameVal);
				$('#email').text(mailVal);
				$('#hp').text(hpVal);
				$('#position').text(positionVal);
				$('#dept').text(deptVal);
				
				var photoPath = "fileupload/tmem/" + photoVal;
				$('#photo').attr('src', photoPath);
				
				console.log(photoVal + ":" + mailVal + ":" + hpVal + ":" + positionVal + ":" +deptVal);
				
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
		});

		
	</script>
</head>




<body>
<nav class="navbar navbar-expand-sm navbar-dark sticky-top" style="background-color: #C5E0B4;padding-right:0;">
		<!--LOGO-->
		<a class="navbar-brand" href="/tr_spring/mainPage.tr" >&nbsp&nbsp<img src="resources/images/logo-green2.png" style="height:30px"></a>
		<!--Links-->
		<div class="container-fluid full-width">
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false" style="padding-top:13px;font-size:18px;color:#534825;font-weight:bold;cursor:pointer">주소록</a>
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
			  <img src="fileupload/tmem/<%= mvo.getMphoto() %>" alt="" width="35" height="35" class="rounded-circle me-2">
			</a>
			<ul class="dropdown-menu dropdown-menu-dark text-small shadow" style="left:-110px" aria-labelledby="dropdownUser1">
			  <li><a class="dropdown-item" href="#">마이 페이지</a></li>
			  <li><hr class="dropdown-divider"></li>
			  <li><a class="dropdown-item" href="/tr_spring/terraLogout.tr">로그아웃</a></li>
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
				<button type="button" class="btn btn-primary" id="istBtn" style="background-color:#E2F0D9;height:50px;border:0; font-size:20px;font-weight:1000;color:#E2F0D9;" disabled ></button>
				<br>
				<ul class="list-unstyled ps-0">
					<li class="mb-1">
					  <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true" style="font-size: 20px;cursor:pointer">
						주소록
					  </button>
					  <div class="collapse show" id="home-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						  <li><div id="nowSelected" class="idx" onclick="javascript:location.href='/tr_spring/tMemAddrBook.tr'"><img src="/tr_spring/resources/images/address-book.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">전체</span></div></li>
						  <li><div class="idx" onclick="javascript:location.href='/tr_spring/tMemAddrBook.tr'"><img src="/tr_spring/resources/images/star.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">즐겨찾기</span></div></li>
						</ul>
					  </div>
					</li>
				  </ul>
			  </div>
			</div>
		  </div>
		</div>
		  <div class="col-9" style="left:300px">
			<!-- 본문 내용 -->
			<div class="content">
			  <!-- 본문 내용을 추가 -->
			  
			  <div>
				
<form name="memFormAll" id="memFormAll" style="padding-left:100px">
	<br>
	<table class="table table-hover">
	<thead>
<tr>
	<td colspan="10" align="center" style="border:0;text-align:left">
		<img src=><h6>주소록 > 전체</h6>
	</td>
</tr>
<tr>
	<th>이름</th>
	<th>이메일</th>
	<th>전화번호</th>
	<th>직위</th>
	<th>부서</th>
</tr>
</thead>
<%
	for(int i=0; i< aList.size(); i++){
		
		TerraMemberVO tmvo = (TerraMemberVO)aList.get(i);
		
		pageSize 	= Integer.parseInt(pagingTMVO.getPageSize());
		groupSize 	= Integer.parseInt(pagingTMVO.getGroupSize());
		curPage 	= Integer.parseInt(pagingTMVO.getCurPage());
		totalCount 	= Integer.parseInt(tmvo.getTotalCount());
%>					
<tbody>
<tr class="contents">
	<td style="width:150px"><%= tmvo.getMname() %> <input type="hidden" name="mphoto" id="mphoto" value="<%=tmvo.getMphoto()%>"></td>
	<td style="width:300px"><%= tmvo.getMemail() %> </td>
	<td style="width:250px"><%= CodeUtil.hpval(tmvo.getMhp()) %></td>
	<td style="width:100px"><%= CodeUtil.mpositionVal(tmvo.getMposition())%></td>
	<td style="width:100px"><%= CodeUtil.dappVal(tmvo.getMdeptno())%></td>
</tr>	
<%
	}//end of if
%>			
<tr>
							<td colspan="5">
								<jsp:include page="memPaging.jsp" 		flush="true">
									<jsp:param value="tMemAddrBook.tr" 	name="url"/>
									<jsp:param value="" 					name="str"/>
									<jsp:param value="<%= pageSize%>" 		name="pageSize"/>
									<jsp:param value="<%= groupSize%>" 		name="groupSize"/>
									<jsp:param value="<%= curPage%>" 		name="curPage"/>
									<jsp:param value="<%= totalCount%>" 	name="totalCount"/>
								</jsp:include>
							</td>
						</tr>				
</tbody>	
</table>
</form>	
</div>
</div>
</div>
	<jsp:include page="memAddCard.jsp"></jsp:include>
	<script src="resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>