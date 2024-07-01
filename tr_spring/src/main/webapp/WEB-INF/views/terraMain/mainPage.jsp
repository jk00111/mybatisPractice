<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@page import="com.kos.tr.old.member.vo.TerraMemberVO"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.apache.log4j.LogManager" %>

<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>


<% 
	Logger logger = LogManager.getLogger(this.getClass());
	String mnum = (String)request.getAttribute("mnum"); 
	ArrayList<TerraMemberVO> uList = (ArrayList<TerraMemberVO>)request.getAttribute("uList");
	String mname = (String)request.getAttribute("mname");
	TerraMemberVO mvo = uList.get(0);
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1, user-scalable=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../tr_spring/resources/css/sidebars.css">
	<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
	<script src="../tr_spring/resources/js/util.js"></script>
	<link rel="stylesheet" href="resources/css/main.css">
    <title>TerraGroupMain</title>

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
    <script type="text/javascript">
    $(document).ready(function(){
    	
        $(function(){
            // 현재 시간 
            function printTime(){
            	let days = ["일", "월", "화", "수", "목", "금", "토"];
                let md = new Date();
                
                let mm = md.getMonth() + 1;
				let dd = md.getDate();
				let ii = md.getDay();
				let di = days[ii];
                let hh = md.getHours();
                let mi = md.getMinutes();
                let ss = md.getSeconds();

                let day = 
                		  numpad(mm) + "월 " +
						  numpad(dd) + "일 " +
						  " ("+ di + ")            " ;
                
                let time = 
                		numpad(hh) + ":" +
                        numpad(mi) + ":" +
                        numpad(ss);
                document.getElementById("timer").innerHTML = time;
                $('#day').html(day);
            }
            setInterval(printTime, 1000);

            function numpad(num) {
                return (num < 10 ? "0" + num : num);
            }
          
        });
        
        
       
     // 버튼 클릭 이벤트 핸들러
        $('button[name="status"]').click(function() {
            var statusText = $(this).text();  // 클릭한 버튼의 텍스트 가져오기
            $('#status').text(statusText);    // 상태 텍스트 업데이트
        	
            // 이전에 클릭된 버튼에서 'active' 클래스 제거
            if (lastClickedButton !== null) {
                lastClickedButton.removeClass('active');
            }

            // 현재 클릭된 버튼에 'active' 클래스 추가
            $(this).addClass('active');
            lastClickedButton = $(this);
        });
     
    	 // 초기 상태 텍스트와 클릭된 버튼 초기화
        var initialStatusText = $('#status').text();
        if (initialStatusText !== '') {
            lastClickedButton = $('button[name="status"]').filter(function() {
                return $(this).text() === initialStatusText;
            });
            lastClickedButton.addClass('active');
        }
        
        $(document).on('click', '#come', function(){
        	
        	$('.businesstb').css('display','block');
        });
        
		$(document).on('click', '#gone', function(){
        	
        	$('.businesstb').css('display','none');
        });
        
        // 업무 버튼 클릭 이벤트
        $("#work").click(function() {
            $("#status").text("업무");
        });

        // 외출 버튼 클릭 이벤트
        $("#goout").click(function() {
            $("#status").text("외출");
        });

        // 회의 버튼 클릭 이벤트
        $("#meeting").click(function() {
            $("#status").text("회의");
        });

        // 외근 버튼 클릭 이벤트
        $("#outoffice").click(function() {
            $("#status").text("외근");
        });
        
        
        // 로그아웃 버튼
        $(document).on("click", "#logout", function(){
			alert("계정 로그아웃 합니다.");
			location.href="logout.tr";
		});
        
        
        function callToMain() {
			
			$('#trMainForm').attr({
				'action':'loginForm.tr',
				'method':'GET',
				'enctype':'multipart/form-data'
			}).submit();				
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
		
		$(document).on('click', '#cbtn', function(){
			
			window.open('http://192.168.0.25:3000/', '캘린더', 'width=600,height=600,left=300,top=100');
		});
		
		openPopup();
		function openPopup() {

			  window.open('mainNotice.tr', '공지사항', 'width=410,height=540,left=300,top=300');
			}
    });

    </script>
    
   
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark sticky-top" style="background-color: #C5E0B4;padding-right:0;">
		<!--LOGO-->
		<a class="navbar-brand" href="/tr_spring/mainPage.tr" >&nbsp&nbsp<img src="../tr_spring/resources/images/logo-green2.png" style="height:30px"></a>
		<!--Links-->
		<div class="container-fluid full-width">
		<ul class="nav navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false" style="padding-top:13px;font-size:18px;color:#534825;font-weight:bold;cursor:pointer">메인 페이지</a>
				<ul class="dropdown-menu" aria-labelledby="dropdown01" >
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
			  <img src="../tr_spring/fileupload/tmem/<%= mvo.getMphoto() %>" alt="" width="35" height="35" class="rounded-circle me-2">
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

  <form name="trMainForm" id="trMainForm">
       <div class="all" style="background-color:#f2f2f2">
        <div class="s">
            
            
            		<%
            	
				   	for(int i=0; i < uList.size(); i++){
					    		
					    	TerraMemberVO trvo = (TerraMemberVO)uList.get(i);
					    	
					%>	
					<% 
						String mposition = trvo.getMposition();
						
					%>
					
					<img class="img" src="fileupload/tmem/<%= mvo.getMphoto() %>" id="imageClick"> <br>
					<h2 class="name" style="left:60px"><%if (mposition.equals("06")) {
					    out.print("대표이사");
					} else if (mposition.equals("05")) {
					    out.print("이사");
					} else if (mposition.equals("04")) {
					    out.print("과장");
					} else if (mposition.equals("03")) {
					    out.print("차장");
					} else if (mposition.equals("02")) {
					    out.print("대리");
					} else if (mposition.equals("01")) {
					    out.print("사원");
					}%> <%= trvo.getMname() %></h2><br>
					
					<button class="logout" id="logout" name="logout" >로그아웃</button>
									
				<%
					}//end of if
				%>

					
					
               <div class="time-box">
				    <div class="time">
				        <table class="timetb" style="text-align:left;width:300px;height:150px">
				            <tr>
				                <td>
				                    <span class="timebold" id="day" style="font-size:13px;width:200px"></span><br>
				                	<span class="timebold" id="timer" style="font-size:23px;width:200px"></span>
				                </td>
				                <td style="width:100px;"><span class="timebold" id="status">출근전</span></td>
				            </tr>
				        </table>
				        
				        <div class="business">
						    <table>
						    <thead class="businessgo">
						      <tr>
				        <td>
				        	<button class="but" type="button" name="come" id="come" value="">출근</button>
				        </td>
				        <td>
				        	<button class="but" type="button" name="gone" id="gone" value="">퇴근</button>
				        </td>
				        </tr>
						    </thead>
						    <tbody class="businesstb" style="display:none">
						        <tr>
						            <td>
						                <button class="but" type="button" name="status" id="work" value="">업무</button>
						            </td>
						            <td>
						                <button class="but" type="button" name="status" id="goout" value="">외출</button>
						            </td>
						        </tr>
						        <tr>
						            <td>
						                <button class="but" type="button" name="status" id="meeting" value="">회의</button>
						            </td>
						            <td>
						                <button class="but" type="button" name="status" id="outoffice" value="">외근</button>
						            </td>
						        </tr>
						        </tbody>
						    </table>
						</div>
				    </div>
				</div>
				
				<div class="approval-box">
				       <div class="approval">
						    <table >
						    <tbody class="approvaltb">
						        <tr>
						            <td>
						                <button class="but" type="button" onclick="location.href='appSelect/W.tr'" name="wait" id="wait" value="대기">대기</button>
						             </td>
						             <td>
						                <button class="but" type="button" onclick="location.href='appSelect/AA.tr'" name="check" id="check" value="확인">확인</button>
						             </td>
						             </tr>
						            <tr>
						            <td>
						                <button class="but" type="button" onclick="location.href='appSelect/P.tr'" name="expect" id="expect" value="예정">예정</button>
						                </td>
						                <td>
						                <button class="but" type="button" onclick="location.href='appSelect/K.tr'" name="progress" id="progress" value="진행">진행</button>
						                </td>
						        </tr>
						        </tbody>
						    </table>
						</div>
				</div>
				
				<% 
				    // mposition 값 가져오기
				    String mposition = "";
				    if (uList != null && !uList.isEmpty()) {
				        TerraMemberVO trvo = uList.get(0); // 첫 번째 사용자 정보만 가져옴
				        mposition = trvo.getMposition();
				    }
				%>
				
				
				
				
          <!--  
          
                -->
        </div>
        
        <div class="main" style="background-color:white">
				<ul class="dep1" style="width:1045px">
                    <li style="background-color:white;border-radius: 50%;height:100px;width:100px;margin-right:30px;">
                    	<a href="/tr_spring/tBoardSelectAll.tr">
                        	<img src="resources/images/boardMain.png" alt="게시판" style="width:50px;margin-top:10px;opacity:0.6"><br>
                        	<span style="font-weight:bold">게시판</span>
                    	</a>
                    </li>                  
                    <li style="background-color:white;border-radius: 50%;height:100px;width:100px;margin-right:30px">
                    	<a href="/tr_spring/tMemAddrBook.tr">
                        	<img src="resources/images/people.png" alt="주소록" style="width:50px;margin-top:12px;opacity:0.6"><br>
                        	<span style="font-weight:bold">주소록</span>
                    	</a>
                    </li>
            
                    <li style="background-color:white;border-radius: 50%;height:100px;width:100px;margin-right:30px">
                    	<a href="/tr_spring/appSelect/A.tr">
                       		<img src="resources/images/app.png" alt="전자결재" style="width:50px;margin-top:12px;margin-left:5px;opacity:0.6"><br>
                   			<span style="font-weight:bold">전자결재</span>
                   		</a>
                   	</li>
        
                    <li style="background-color:white;border-radius: 50%;height:100px;width:100px;margin-right:30px">
                    	<a href="/tr_spring/workSelect.tr">
                        	<img src="resources/images/work.png" alt="인사" style="width:50px;margin-top:12px;opacity:0.6"><br>
                        	<span style="font-weight:bold">인사</span>
                    	</a>
                    </li>
                    <li id="cbtn" style="background-color:white;border-radius: 50%;height:100px;width:100px;margin-right:30px">
                        	<img src="resources/images/calender.png" alt="일정" style="width:50px;margin-top:12px;opacity:0.6"><br>
                        	<span style="font-weight:bold">일정</span>
                    </li>
                	
                	<%-- mposition 값이 "06"인 경우에만 해당 메뉴를 보여줍니다. --%>
                	<% if (mposition.equals("06")) { %>
	                    <li style="background-color:white;border-radius: 50%;height:100px;width:100px;margin-right:30px">
	                    	<a href="/tr_spring/tMemSelectAll.tr">
	                        	<img src="resources/images/member.png" alt="직원관리" style="width:50px;margin-top:10px;margin-left:7px;opacity:0.6"><br>
	                        	<span style="font-weight:bold">사원관리</span>
	                    	</a>
	                    </li>
                  
                  <% } %>
				</ul>
			<div style="top:150px;position:absolute"><jsp:include page="mainMoney.jsp"></jsp:include></div>
			<div style="left:515px;top:450px;position:absolute"><iframe src="http://192.168.0.25:3000/" width=530px height=500px frameborder="0"></iframe></div>
			<div style="top:355px;left:-42px;position:absolute"><iframe src="http://192.168.0.25:3008/" width="600px" height="600px" frameborder="0"></iframe></div>
		</div>
      </div>

		
    </form>
    <script src="/tr_spring/resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>