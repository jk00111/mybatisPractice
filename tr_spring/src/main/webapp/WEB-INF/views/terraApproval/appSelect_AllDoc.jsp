<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kos.tr.old.common.CodeUtil"%>
<%@page import="com.kos.tr.old.app.vo.AppDocVO"%>
<%@page import="com.kos.tr.old.member.vo.TerraMemberVO"%>
<%
	
	ArrayList<AppDocVO> cList = (ArrayList<AppDocVO>)request.getAttribute("cList");
	ArrayList<TerraMemberVO> uList = (ArrayList<TerraMemberVO>)request.getAttribute("uList");
	AppDocVO pagingdvo = (AppDocVO)request.getAttribute("pagingdvo");
	ArrayList<AppDocVO> prList = new ArrayList<AppDocVO>(); 
	ArrayList<TerraMemberVO> pList = new ArrayList<TerraMemberVO>();
	
	if((ArrayList<AppDocVO>)request.getAttribute("prList") != null && (ArrayList<TerraMemberVO>)request.getAttribute("pList") != null){
		prList = (ArrayList<AppDocVO>)request.getAttribute("prList");
		pList = (ArrayList<TerraMemberVO>)request.getAttribute("pList");
		System.out.println("listcheck >>>>>>>>>>>>>>" + pList.size() + ":" + prList.size());
		
	}
	
	int pageSize = 0;
	int groupSize = 0;
	int curPage = 0; // curPage : 현재페이지
	int totalCount = 0;
	
	TerraMemberVO mvo = uList.get(0);
	

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
<link rel="stylesheet" type="text/css" href="../resources/css/sidebars.css">
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
<script src="../resources/js/util.js"></script>

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

			var type = "";
		
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
			
			$(document).on('click', '.contents', function(){
				var thisVal = $(this).find('input').eq(0).val();
				$('#anum').val(thisVal);
				
				var typeEn = $(this).closest('tr').find('td:nth-last-child(2)');
				var value = typeEn.text();
				type = docTypeR(value);
				console.log(type);
				
				$('#appSelectAll').attr({
					'action':'/tr_spring/appSelectContent/' + type + '.tr',    
					'method':'POST',
					'enctype':'application/x-www-form-urlencoded'
				}).submit();
			});
			
			
			if (<%=prList.size() == 0%>) { 
				
 				$('#proxytable').css("display", "none");
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
						  <li><div id="nowSelected" class="idx" onclick="javascript:location.href='/tr_spring/appSelect/AllDoc.tr'"><img src="/tr_spring/resources/images/file.png" class="icon_img"><span class="link-dark rounded" style="font-size:20px;margin-left:12px">전체</span></div></li>
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
		  <div class="col-9" style="left:300px">
			<!-- 본문 내용 -->
			<div class="content">
			  <!-- 본문 내용을 추가 -->
			  
			  <div>
	<form name="appSelectAll" id="appSelectAll" style="padding-left:100px">
	<br>
	<table class="table table-hover">
	<thead>
	<tr>
		<td colspan="9" style="text-align:left;border:0">
			<img src=><h6>전자결재 > 문서함 > 전체</h6>
		</td>
	</tr>
	<tr>
		<th>문서번호</th>
		<th class="subj">제목</th>
		<th>기안자</th>
		<th>기안일</th>
		<th>문서종류</th>
		<th>상태</th>
	</tr>
	</thead>
      <%
         if(cList.size() == 0){
      %>      
      <tr>
         <td colspan="7" height="300" style="text-align:center;">조회된 데이터가 없습니다.</td>
      </tr>
      <%   
         } else {
		for(int i=0; i < cList.size(); i++){
			
			AppDocVO advo = (AppDocVO)cList.get(i);
			
            pageSize = Integer.parseInt(pagingdvo.getPageSize());
            groupSize = Integer.parseInt(pagingdvo.getGroupSize());
            curPage = Integer.parseInt(pagingdvo.getCurPage());
            totalCount = Integer.parseInt(advo.getTotalCount());
	%>					
	<tbody>
	<tr class="contents">	
		<td><%= advo.getAnum() %> <input type="hidden" value=<%= advo.getAnum() %>>	</td>
		<td class="subj"><%= advo.getSubject() %></td>	
		<td><%= advo.getMname() %></td>
		<td><%= advo.getInsertdate() %></td>			
		<td><%= CodeUtil.docType(advo.getAtype())%></td>		
		<%
			switch(advo.getAlevel()){
			
			case "09":
				%>
				<td>완료</td>
				<%
				break;
			case "00":
				%>
				<td>반려</td>
				<%
				break;
			default:
				%>
				<td>진행중</td>
				<%
				break;
			}	
		%>
	</tr>	
	<%
			}
		}
	%>	
	</tbody>
	         <tr>
               <td style="border-left:none;" colspan="6"><jsp:include page="appPaging.jsp" flush="true">
                  <jsp:param name="url" value="../appSelect/AllDoc.tr"/>
                  <jsp:param name="str" value=""/>
                  <jsp:param name="pageSize" value="<%=pageSize%>"/>
                  <jsp:param name="groupSize" value="<%=groupSize%>"/>
                  <jsp:param name="curPage" value="<%=curPage%>"/>
                  <jsp:param name="totalCount" value="<%=totalCount%>"/>
                  </jsp:include>
               </td>
            </tr>
	</table>
		<input type="hidden" name="anum" id="anum">
	</form>	
</div>
</div>
</div>
	<script src="../resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>