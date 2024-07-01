<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.log4j.LogManager" %>  
<%@ page import="org.apache.log4j.Logger" %>  

<%@ page import="com.kos.tr.old.member.vo.TerraMemberVO" %>

<%@ page import="java.util.List" %>
    
<% request.setCharacterEncoding("UTF-8");%> 
<%
	Logger logger = LogManager.getLogger(this.getClass());
	logger.info("memSelect.jsp >>> : ");
%>

<%	
	Object obj = request.getAttribute("listS");
	if (obj == null) return;

	List<TerraMemberVO> list = (List<TerraMemberVO>)obj;	
	int nCnt = list.size();
	
	String mpw = "";
	String mbirth = "";
	String mbirth0 = "";
	String mbirth1 = "";
	String mbirth2 = "";
	String memail = "";	
	String memails[] = null;
	String mdeptno = ""; // 부서번호
	String mgrno = ""; // 상급자 번호
	String mduty = ""; // 직책
	String mposition = ""; // 직위
	String mjob = ""; // 직무
	
	TerraMemberVO _tmvo = null;	
	_tmvo = list.get(0);
	
	
	// 이메일
	memail = _tmvo.getMemail();
	memails = memail.split("@");
	
	
	// 상급자 번호
	mgrno = _tmvo.getMgrno();
	
	// 데이터베이스에서 가져온 핸드폰 번호 데이터
	String phoneNumber = _tmvo.getMhp();  // 데이터베이스에서 값을 가져오는 로직을 구현해야 합니다.

	// 핸드폰 번호를 분리하여 각 부분에 할당
	String mhp = phoneNumber.substring(0, 3);
	String mhp1 = phoneNumber.substring(3, 7);
	String mhp2 = phoneNumber.substring(7);
	
	// 데이터베이스에서 가져온 핸드폰 번호 데이터
	String mtelNumber = _tmvo.getMtel();  // 데이터베이스에서 값을 가져오는 로직을 구현해야 합니다.

	// 핸드폰 번호를 분리하여 각 부분에 할당
	String mtel = mtelNumber.substring(0, 3);
	String mtel1 = mtelNumber.substring(3, 7);
	String mtel2 = mtelNumber.substring(7);
		
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select page</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 디바이스에 최적화된 크기로 출력됨 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0
     	maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>     	

<script  src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">     	
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="resources/css/memSelect.css">

	<script>
	function previewImage(event) {
	    var reader = new FileReader();
	    reader.onload = function() {
	        var output = document.getElementById('preview');
	        output.src = reader.result;
	        output.style.display = "block"; // 미리보기 이미지를 화면에 보이도록 설정합니다
	    };
	    reader.readAsDataURL(event.target.files[0]);
	}
	
	</script>
	
<script type="text/javascript">

$(document).ready(function(){
	
	$('#mdeptno1').val("<%=_tmvo.getMdeptno()%>");
	$('#mjob').val("<%=_tmvo.getMjob()%>");
	$('#mposition').val("<%=_tmvo.getMposition()%>");mtel2
	$('#mtel2').val("<%=mtel2%>");
	// 이메일 
	$('#memail2').change(function(){	
		$("#memail2 option:selected").each(function () {
			if($(this).val()== '1'){ //직접입력일 경우 
					var aa = $("#memail1").val();						
					$("#memail1").val(''); //값 초기화 
					$("#memail1").attr("readonly",false); //활성화 				
			}else{ //직접입력이 아닐경우 
					$("#memail1").val($(this).text()); //선택값 입력 
					$("#memail1").attr("readonly",true); //비활성화 
			}
		}); 
	}); 	
	
	// 우편번호	
	$("#zonecode").click(function(){
		console.log("zonecode >>> : ");
		new daum.Postcode({
			oncomplete: function(data) {
			    $("#mzipcode").val(data.mzipcode); 		//5자리 새우편번호 사용
			    $("#mroadaddr").val(data.roadAddress); 		//도로명 주소
			    $("#mroaddetail").val(''); 					// 도로명 상세주소
			    $("#mjibun").val(data.mjibun); 	//지번주소			
			}
		}).open();
	});
	
	// 부서 번호
	$('#mdeptno1').change(function(){	
		$("#mdeptno1 option:selected").each(function () {
			if($(this).val()== '1'){ //직접입력일 경우 
					var aa = $("#mdeptno").val();						
					$("#mdeptno").val(''); //값 초기화 
					$("#mdeptno").attr("readonly",false); //활성화 				
			}else{ //직접입력이 아닐경우 
					$("#mdeptno").val($(this).text()); //선택값 입력 
					$("#mdeptno").attr("readonly",true); //비활성화 
			}
		}); 
	}); 	
	
	// 직위 번호
	$('#mposition1').change(function(){	
		$("#mposition1 option:selected").each(function () {
			if($(this).val()== '1'){ //직접입력일 경우 
					var aa = $("#mposition").val();						
					$("#mposition").val(''); //값 초기화 
					$("#mposition").attr("readonly",false); //활성화 				
			}else{ //직접입력이 아닐경우 
					$("#mposition").val($(this).text()); //선택값 입력 
					$("#mposition").attr("readonly",true); //비활성화 
			}
		}); 
	}); 
	
	// 직책 번호 mduty1
	$('#mduty1').change(function(){	
		$("#mduty1 option:selected").each(function () {
			if($(this).val()== '1'){ //직접입력일 경우 
					var aa = $("#mduty").val();						
					$("#mduty").val(''); //값 초기화 
					$("#mduty").attr("readonly",false); //활성화 				
			}else{ //직접입력이 아닐경우 
					$("#mduty").val($(this).text()); //선택값 입력 
					$("#mduty").attr("readonly",true); //비활성화 
			}
		}); 
	}); 	
	
	// 직책 번호 mjob1
	$('#mjob1').change(function(){	
		$("#mjob1 option:selected").each(function () {
			if($(this).val()== '1'){ //직접입력일 경우 
					var aa = $("#mjob").val();						
					$("#mjob").val(''); //값 초기화 
					$("#mjob").attr("readonly",false); //활성화 				
			}else{ //직접입력이 아닐경우 
					$("#mjob").val($(this).text()); //선택값 입력 
					$("#mjob").attr("readonly",true); //비활성화 
			}
		}); 
	}); 
	
	// 전결권 번호 mpassyn1 
	$('input[name="mpassyn1"]').change(function(){
	  var selectedValue = $('input[name="mpassyn1"]:checked').val();
	  if (selectedValue === '1') { // 직접입력일 경우
	    $("#mpassyn").val(''); // 값 초기화
	    $("#mpassyn").prop("readonly", false); // 활성화
	  } else { // 직접입력이 아닐 경우
	    $("#mpassyn").val(selectedValue); // 선택값 입력
	    $("#mpassyn").prop("readonly", true); // 비활성화
	  }
	});	
	
	// 입사일
	$("#hiredate").datepicker({		             
		dateFormat: "yymmdd",             
		changeMonth: true,                  			            
	});	
	
});

//updateBtn
$(document).on("click", "#updateBtn", function(){
	//alert("U >>> : ");		
	$("#memUpdateForm").attr({ "method":"POST"
		                      ,"action":"tMemUpdate.tr"
		                      ,'enctype':'multipart/form-data'
		                      }).submit();
});



</script>

</head>

<body>

<div>
<form name="memUpdateForm" id="memUpdateForm">	

<table>

		<tr>
			<td class="U" colspan="3" align="center">					
			<span style="font-weight:bold;font-size:20px;color:#534825">사원 정보 수정 </span>						
			</td>
		</tr>
		<tr>
			<td class="title">회원번호</td>
			<td class="input">
				<input type="text" name="mnum" id="mnum" value="<%= _tmvo.getMnum() %>" style="border:0" readonly/>
			</td>
			
			<td class="input" rowspan="5">
			    <!-- 이미지를 조건에 따라 숨기거나 보여주기 -->
			    <% if (_tmvo.getMphoto() != null && !_tmvo.getMphoto().isEmpty()) { %>
			        <img id="preview" class="photo" src="./fileupload/tmem/<%= _tmvo.getMphoto() %>" ><!-- 파일업로드전 -->
			    <% } %>
			    <!-- 파일업로드후 
			    <img id="preview" class="photo" src="" style="<% if (_tmvo.getMphoto() != null && !_tmvo.getMphoto().isEmpty()) { %> display:none;<% } %>">
			    -->
			    <!-- 파일 선택 입력 요소 -->
			    <input type="file" accept="image/*" onchange="previewImage(event)" name="mphoto" id="mphoto">
			</td>
		</tr>
		
		<tr>
			<td class="title">이름</td>
			<td class="input">
				<input type="text" name="mname" id="mname" value="<%= _tmvo.getMname() %>" style="border:0"/>
			</td>
		 </tr>
		 
		 <tr>
		 	<td class="title">아이디</td>
			<td class="input">
				<input type="text" name="mid" id="mid" style="width:100px;border:0" value="<%= _tmvo.getMid() %>" readonly />		
			</td>
		 </tr>
		 
		 <tr>
		 	<td class="title">패스워드</td>
			<td class="input">
				<input type="text" name="mpw" id="mpw" style="width:100px" value="<%= _tmvo.getMpw() %>" /><br/>
			</td>
		 </tr>
		 
		 <tr>
		 	<td class="title">성별</td>
			<td class="input">
		      	<input type="text" name="mgender" id="mgender_m" value="<%= _tmvo.getMgender() %>" style="border:0" readonly />
		    </td>
		 </tr>
		 
		 <tr>
		 	<td class="title">생년월일</td>
			<td class="input" colspan="2">		
				<input type="text" name="mbirth" id=mbirth value="<%= _tmvo.getMbirth() %>" style="width:100px;border:0" readonly/>
			</td>
		 </tr>
		 
		 <tr>
		 	<td class="title">핸드폰</td>
			<td class="input" colspan="2">
				<select name="mhp" id="mhp" style="width:50px;" value="<%= mhp %>">
					<option value="010" <%= mhp.equals("010") ? "selected" : "" %>>010</option>
					<option value="011" <%= mhp.equals("011") ? "selected" : "" %>>011</option>
					<option value="016" <%= mhp.equals("016") ? "selected" : "" %>>016</option>
					<option value="017" <%= mhp.equals("017") ? "selected" : "" %>>017</option>
				</select>
				- <input type="text" name="mhp1" id="mhp1" value="<%= mhp1 %>" maxlength="4" style="width:50px;" />
				- <input type="text" name="mhp2" id="mhp2" value="<%= mhp2 %>" maxlength="4" style="width:50px;" />
			</td>
		</tr>
				 
		 
		 <tr>
		 	<td class="title">이메일</td>
			<td class="input" colspan="2">
				<input type="text" name="memail" id=memail style="width:100px" value="<%= memails[0] %>" />
				@ <input type="text" name="memail1" id=memail1 style="width:100px" value="<%= memails[1] %>" placeholder="직접입력" />
				<select name="memail2" id="memail2" style="width:100px;margin-right:10px">
		        	 <option value="1" selected>직접입력</option>
		       		 <option value="naver.com">naver.com</option>	       	   
		      		 <option value="gmail.com">gmail.com</option>
		      		 <option value="daum.net">daum.net</option>	       	   
		         </select>
			</td>
		 </tr>
		 
		 <tr>	
		 	<td class="title">주소</td>
		 	<td class="input" colspan="2">
		 		<input type="text" name="mzipcode" id="mzipcode" 
		 			style="width:50px" value="<%= _tmvo.getMzipcode() %>">
		 		<input name="zonecode" id="zonecode" value="우편번호 찾기"><br>	 	
		 		<input type="text" name="mroadaddr" id="mroadaddr" 
		 			placeholder="도로명주소" style="width:300px" value="<%= _tmvo.getMroadaddr() %>"><br>	 	
		 		<input type="text" name="mroaddetail" id="mroaddetail" 
		 			placeholder="도로명주소 상세주소" style="width:300px" value="<%= _tmvo.getMroaddetail() %>"><br>	 	
		 		<input type="text" name="mjibun" id="mjibun" 
		 			placeholder="지번주소" style="width:300px" value="<%= _tmvo.getMjibun() %>">
		 	</td>
		 </tr>	 
		 
		 <tr>
		 	<td class="title">상급자 번호</td>
			<td class="input" colspan="2">
					<input type="text" name="mgrno" id="mgrno"  value="<%= _tmvo.getMgrno() %>" style="width:100px">
					
			 	</td>
			</tr>
			
				
			<tr>	 
				<td class="title">부서 번호</td>
				<td class="input" colspan="2">
					<select name="mdeptno" id="mdeptno">
						   <option value="1000">관리부</option>
						   <option value="2000">기획부</option>
						   <option value="3000">영업부</option>
						   <option value="4000">생산부</option>
						   <option value="5000">안전관리</option>	        	
					</select>
				   </td>
			</tr>
				
			
			<tr>	 
				<td class="title">직무</td>
				<td class="input" colspan="2">
					<select  name="mjob" id="mjob">
							   <option value="DEV">개발</option>
							   <option value="BUS">영업</option>
							   <option value="AFF">총무</option>
							   <option value="PLAN">기획</option>
							   <option value="PROD">생산</option>	
					</select>
				</td>
			</tr>	
			
			
				
			<tr>	 
				<td class="title">직위</td>
				<td class="input" colspan="2">
					<select name="mposition" id="mposition" >
						   <option value="01">사원</option>
						   <option value="02">대리</option>
						   <option value="03">과장</option>
						   <option value="04">부장</option>
						   <option value="05">임원</option>	
						   <option value="06">대표이사</option>        	
					</select>
			</tr>	

				
			<tr>	 
				<td class="title">전결권</td>
				<td class="input" colspan="2">
					<input type="text" name="mpassyn" id="mpassyn" value="<%= _tmvo.getMpassyn() %>" style="width:100px">
					
						<input type="radio" name="mpassyn1" id="mpassyn1" style="background-color: #92D050" value="N" checked />미부여 
						<input type="radio" name="mpassyn1" id="mpassyn1" style="background-color: #92D050" value="Y" />부여      	
					
				</td>
			</tr>	
				
			<tr>
				<td class="title">입사일</td>
				<td class="input" colspan="2">
					<input type="text" name="hiredate" id="hiredate" size="10" style="width:100px" value="<%= _tmvo.getHiredate() %>" />
				</td>
			</tr>
				
			<tr>
				<td class="title">연차</td>
				<td class="input" colspan="2">
					<input type="text" name="mholiday" id="mholiday" size="10" style="width:100px"  value="<%= _tmvo.getMholiday() %>"/>
				</td>
			</tr>

			<tr>
				<td class="title">사내번호</td>
				<td class="input" colspan="2">
					<input type="text" name="mtel" id="mtel" maxlength="3" size="2" value="<%= mtel %>"/>
					- <input  type="text" name="mtel1" id="mtel1" maxlength="4" size="2" value="<%= mtel1 %>"/>
					- <select name="mtel2" id="mtel2"  >
						   <option value="5000">5000</option>
						   <option value="5001">5001</option>
						   <option value="5002">5002</option>
						   <option value="5003">5003</option>
						   <option value="5004">5004</option>
						   <option value="5005">5005</option>		        	
					</select>
				</td>
			</tr> 
				
				
 <tr>
	<td colspan="3" class="but">
		<button type="button" id="updateBtn" class="btn btn-primary" style="font-size:18px;background-color:#92D050;border-color:#92D050;width:100px;height:40px;color:white;align-items: center;">수정</button>
		<button type="reset" class="btn btn-primary" style="font-size:18px;background-color:#92D050;border-color:#92D050;width:100px;height:40px;color:white;align-items: center;">다시</button>
	</td>				
 </tr>
 </table>				 		        		     
</form>	
</div>		

</body>
</html>