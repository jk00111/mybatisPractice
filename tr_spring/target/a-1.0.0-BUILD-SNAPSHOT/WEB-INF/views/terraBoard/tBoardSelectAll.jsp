<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kos.tr.member.vo.TerraMemberVO"%>
<%@ page import="com.kos.tr.board.vo.TerraBoardVO" %>
<%@ page import="java.util.List" %>
<%@page import="com.kos.tr.common.CodeUtil"%>
<%@page import="com.kos.tr.common.EncryptSHA"%>
<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>

<% request.setCharacterEncoding("UTF-8"); %>
<%
	Logger logger = LogManager.getLogger(this.getClass());
	logger.info("tBoardSelectAll.jsp Page >>> : ");
	
	ArrayList<TerraMemberVO> uList = (ArrayList<TerraMemberVO>)request.getAttribute("uList");
	TerraMemberVO mvo = uList.get(0);
	int pageSize 	= 0;
	int groupSize 	= 0;
	int curPage 	= 0;
	int totalCount 	= 0;
	
	Object objPaging = request.getAttribute("pagingTBVO");
	TerraBoardVO pagingTBVO = (TerraBoardVO)objPaging;
	
	Object obj = request.getAttribute("listAll");
	if (obj == null) return;
	
	List<TerraBoardVO> list = (List<TerraBoardVO>)obj;
	
	int nCnt = list.size();
	String nCntS = "::: 전체 조회 건수 " + nCnt + " 건 ";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
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

 	th{ 
		background-color:#E2F0D9;
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
	
	table {
		width:1110px !important;
	}
	
	</style>
		
		<script type="text/javascript">
			
			$(document).ready(function(){
				
			
				$(document).on("click", "#bnum", function(){
					
					if($(this).prop('checked')){
						$('.bnum').prop('checked', false);
						$(this).prop('checked', true);
					}
				});
				
				$(document).on("click", "#istBtn", function(){
					location.href="tBoardInsertForm.tr";
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
		<a class="navbar-brand" href="/tr_spring/mainPage.tr" >&nbsp&nbsp<img src="/tr_spring/resources/images/logo-green2.png" style="height:30px"></a>
		<!--Links-->
		<div class="container-fluid full-width">
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false" style="padding-top:13px;font-size:18px;color:#534825;font-weight:bold;cursor:pointer">게시판</a>
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
			  <img src="../tr_spring/fileupload/tmem/<%=mvo.getMphoto()%>" alt="" width="35" height="35" class="rounded-circle me-2">
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
				<button type="button" class="btn btn-primary" id="istBtn" style="background-color:#92D050;height:50px;border:0; font-size:20px;font-weight:1000;color:#534825;cursor:pointer">+ 작성하기</button>
				<br>
				<ul class="list-unstyled ps-0">
					<li class="mb-1">
					  <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true" style="font-size: 20px;cursor:pointer">
						게시판
					  </button>
					  <div class="collapse show" id="home-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
						  <li><div id="nowSelected" class="idx" onclick="javascript:location.href='/tr_spring/tBoardSelectAll.tr'"><img src="/tr_spring/resources/images/file.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">전체 게시판</span></div></li>
						  <li><div id="noticebaord" class="idx" onclick="javascript:location.href='http://192.168.0.36:5000/post?manager=<%= EncryptSHA.encryptSHA256(mvo.getMposition())%>'"><img src="/tr_spring/resources/images/noticeboard.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">공지사항</span></div></li>
						</ul>
					  </div>
					</li>
				  </ul>
			  </div>
			</div>
		  </div>
		</div>
		  <div class="col-9" style="left:250px">
			<!-- 본문 내용 -->
			
		<div class="container">
			<form name="boardList" id="boardList" >
				<table class="table table-hover">
					<thead>
						<tr>
		<td colspan="3" style="text-align:left;border:0;">
		<br>
			<img src=><h6>게시판 > 일반게시판</h6>
		</td>
	</tr>
						<tr>
							<th style="width:800px">제목</th>
							<th style="width:200px">작성자</th>
							<th style="width:200px">조회수</th>
							<th style="width:200px">등록일</th>
						</tr>
					</thead>
					<%
					if(uList.size() == 0 || uList == null){
					%>
					<tr><td colspan="4">조회된 데이터가 없습니다</td></tr>	
					<%
					}else{
					%>
					
					<%
					for (int i=0; i < nCnt; i++) { 
						TerraBoardVO _tbvo = list.get(i);

						pageSize 	= Integer.parseInt(pagingTBVO.getPageSize());
						groupSize 	= Integer.parseInt(pagingTBVO.getGroupSize());
						curPage 	= Integer.parseInt(pagingTBVO.getCurPage());
						totalCount 	= Integer.parseInt(_tbvo.getTotalCount());
					%> 
					<tbody>
						<tr>
							<td style="cursor: pointer;" align="left" onClick="location.href='tBoardSelectContents.tr?bnum=<%= _tbvo.getBnum() %>'"><%= _tbvo.getBsubject() %></td>
							<td style="text-align: center"><%= _tbvo.getMname() %></td>
							<td style="text-align: center"><%= _tbvo.getBhit() %></td>
							<td style="text-align: center"><%= CodeUtil.formatDateTime( _tbvo.getUpdatedate())%></td>
						</tr>
					<%
						}}
					%>
						<tr>
							<td colspan="4">
								<jsp:include page="tBoardPaging.jsp" 		flush="true">
									<jsp:param value="tBoardSelectAll.tr" 	name="url"/>
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
			<script src="/tr_spring/resources/js/bootstrap.bundle.min.js"></script>
	</body>
</html>