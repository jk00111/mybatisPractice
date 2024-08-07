<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Terra Test Member Form</title>

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
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">     	
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="resources/css/memInsert.css">

<script>
    function previewImage(event) {
      var reader = new FileReader();
      reader.onload = function() {
        var output = document.getElementById('preview');
        output.src = reader.result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  </script>
  
  <script type="text/javascript">
  	
  	$(document).ready(function(){
  		var idChk = "FAIL";
  		var ar = false;
  		
  		// 아이디 중복체크 Ajax 비동기 방식  시작  ================================================= 				
		$(document).on("change", "#mid", function(){
			var mid = $("#mid").val();
			  if (mid === "") {
			    $("#idtext").text('아이디를 입력해주세요');
			    $("#idtext").css('display', 'block');
			    idChk = "FAIL";
			    return;
			  }
			  
			  // 아이디 유효성 검사 로직
			  var regex = /^[a-zA-Z0-9]+$/;
			  if (!regex.test(mid)) {
			    $("#idtext").text('아이디는 영문자와 숫자로만 구성되어야 합니다');
			    $("#idtext").css('display', 'block');
			    idChk = "FAIL";
			    return;
			  }
			  
			// 아이디 중복 체크 로직
			  var idCheckURL = "tMemIdcheck.tr";
			  var reqType = "GET";
			  var dataParam = { mid: mid };
			  
			  $.ajax({
			    url: idCheckURL,
			    type: reqType,
			    data: dataParam,
			    success: whenSuccess,
			    error: whenError
			  });

			  function whenSuccess(resData) {
			    if (resData === "ID_YES") {
			      $("#idtext").css('display', 'none');
			      idChk = "PASS";
		
			    } else if (resData === "ID_NO") {
			      $("#idtext").text('사용할 수 없는 아이디입니다');
			      $("#idtext").css('display', 'block');
			      idChk = "FAIL";
			    }
			  }

			  function whenError(e) {
			    console.log("Error: " + e.responseText);
			  }
		});
  		
		$("#mpw").keyup(function(){
            
            $.ajax({
               async:false,
               url:"pwCheck.tr",
               type:"GET",
               data:{mpw : $("#mpw").val()},
               success:pwCheck,
               error:pwChecke
               
            });
            
            function pwCheck(data) {
               
               var array = data.split("+");
                           
               var ar0 = array[0] == "true";
               var ar1 = array[1] == "true";
               var ar2 = array[2] == "true";
               var ar3 = array[3] == "true";
               
               if(ar0 && ar1 && ar2 && ar3) {
                  ar = true;
                  $("#pwtext").css('display', 'none');
               }else{
            	  ar = false;
            	  $("#pwtext").css('display', 'block');
               }

            }
            function pwChecke(e) {
               alert(JSON.stringify(e));
            }
         });
		
         $("#mpw, #mpw_r").keyup(function(){
            pw_nCnt = 0;
         });
         
  	
		// 입사일
		$("#hiredate").datepicker({		             
			dateFormat: "yymmdd",             
			changeMonth: true,                  			            
		});	
		
		
        // 월 
        $("#miredate1").append("<option value=''>월--- </option>");
        for(var i = 1; i <= 12; i++){
        	if (i < 10){ i = '0'+i;}
            $("#miredate1").append("<option value='"+ i +"'>"+ i + "</option>");
        }
        // 일 
        $("#miredate2").append("<option value=''>일 ---</option>");
        for(var i = 1; i <= 31; i++){
        	if (i < 10){ i = '0'+i;}
            $("#miredate2").append("<option value='"+ i +"'>"+ i + "</option>");
        }
        
        
     // 우편번호
		$("#mzipcode").prop('readonly', true);
		$("#mroadaddr").prop('readonly', true);
		$("#mjibun").prop('readonly', true);		
		$("#zonecode").click(function(){
			console.log("zonecode >>> : ");
			new daum.Postcode({
				oncomplete: function(data) {
				    $("#mzipcode").val(data.zonecode); //5자리 새우편번호 사용
				    $("#mroadaddr").val(data.roadAddress); //도로명 주소
				    $("#mjibun").val(data.jibunAddress); //지번주소			
				}
			}).open();
		});
		
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
			}}); 
		}); 
		
		// 폼태그 데이터 콘트롤러에 보내기 
		$(document).on("click", "#btn", function(){	
			console.log("btn >>> : ");	
		
			
			if (idChk == 'PASS' && ar) {
				
				$('#memForm').attr({
					'action':'tMemInsertForm.tr',
					'method':'POST',
					'enctype':'multipart/form-data'
				}).submit();
			}else{
				alert('필수정보를 입력해주세요');
			}
		});
		
		
  	});
  	
  	
  </script>
  
  <script>
			function previewImage(event) {
				var reader = new FileReader();
				reader.onload = function () {
					var preview = document.getElementById('preview');
					preview.src = reader.result;
				}
				reader.readAsDataURL(event.target.files[0]);
			}
		</script>


	</head>

	<body>
	<form name="memForm" id="memForm">
		<h3><img src="../tr_spring/resources/images/logo-white.png" onclick="javascript:location.href='/tr_spring/mainPage.tr'" style="cursor:pointer"></h3>
		<div class="all">
			
			<table>
			
				<tr>
					<td class="U" colspan="3" align="center">					
						<span style="font-weight:bold;font-size:20px;color:#534825">사원 등록 </span>					
					</td>
				</tr>
			
				 <tr>
				 	<td class="a">아이디</td>
			        <td class="b">
			            <input type="text" name="mid" id="mid" placeholder="아이디" required />
			            <p id="idtext" style="display:none;color:red;font-size:12px;margin:0">사용할 수 없는 아이디입니다</p>
			        </td>
			        <td class="b" rowspan="6" style="text-align:center">
			        <br>
						<img id="preview" src="../tr_spring/resources/images/user.png">
						<input type="file" accept="image/*" onchange="previewImage(event)" name="mphoto" id="mphoto">
					</td>
			    </tr>
			    <tr>
			    	<td class="a">비밀번호</td>
			        <td class="b">
			            <input type="text" class="imf" name="mpw" id="mpw" placeholder="비밀번호" maxlength="20" required /><br>
			            <p id="pwtext" style="display:none;color:red;font-size:12px;margin:0">8~20자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요. </p>
			        </td>
			    </tr>
				<tr>
					<td class="a">생년월일</td>
					<td class="b">
						<input type="text" name="mbirth" id="mbirth" placeholder="생년월일 (8자리)" maxlength="8" style="width:100px"/>
					</td>
				</tr>
		
				
				<tr>
					<td class="a">이름</td>
					<td class="b">
						<input type="text" name="mname" id="mname" placeholder="이름" required /></td>
					</tr>
				<tr>
				
				<tr>
					<td class="a">성별</td>
					<td class="b">
						<input type="radio" name="mgender" id="mgender_m" style="background-color: #92D050" value="M" checked />남자 
						<input type="radio" name="mgender" id="mgender_f" style="background-color: #92D050" value="F" />여자
				 	</td>
				</tr>
				
				
				<tr>
					<td class="a">이메일</td>
					<td class="b" colspan="2">
						<input type="text" name="memail" id=memail style="width:100px"  placeholder="이메일"/>
						@ <input type="text" name="memail1" id=memail1 style="width:100px" placeholder="직접입력" />
						<select name="memail2" id="memail2" style="width:100px;margin-right:10px">
								<option value="1" selected>직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
							</select>
					</td>
				</tr>
				
				<tr>
					<td class="a">주소</td>
					<td class="b" colspan="2">
						<input type="text" name="mzipcode" id="mzipcode" placeholder="우편번호" style="width:50px">
						<input type="button" name="zonecode" id="zonecode" value="우편번호 찾기"><br>	 	
						<input type="text" name="mroadaddr" id="mroadaddr" placeholder="도로명주소" style="width:250px"><br>	 	
						<input type="text" name="mroaddetail" id="mroaddetail" placeholder="상세주소" style="width:250px"><br>	 	
						<input type="text" name="mjibun" id="mjibun" placeholder="지번주소" style="width:250px">
					</td>
				</tr>	 
				
				
				<tr>
					<td class="a">핸드폰 번호</td>
					<td class="b" colspan="2">
						<select name="mhp" id="mhp" >
							   <option value="010" selected>010</option>
							   <option value="011">011</option>
							   <option value="016">016</option>
							   <option value="017">017</option>		        	
						</select>
						- <input type="text" name="mhp1" id="mhp1" maxlength="4" size="2" />
						- <input type="text" name="mhp2" id="mhp2" maxlength="4" size="2"/>
					</td>
				</tr>
				
			
			
				<tr>
					<td class="a">입사일</td>
					<td class="b" colspan="2">
						<input type="text" name="hiredate" id="hiredate" size="10" placeholder="입사일" style="width:100px"/>
					</td>
				</tr>
				
				
				<tr>
					<td class="a">부서</td>
					<td class="b" colspan="2">
						<select name="mdeptno" id="mdeptno">
							   <option value="00" selected>부서선택</option>
							   <option value="1000">관리부</option>
							   <option value="2000">기획부</option>
							   <option value="3000">영업부</option>
							   <option value="4000">생산부</option>
							   <option value="5000">안전관리</option>		        	
						</select>
					   </td>
				</tr>
				
				
				<tr>
					<td class="a">직무</td>
					<td class="b" colspan="2">
						<select name="mjob" id="mjob">
								   <option value="00" selected>직무선택</option>
								   <option value="DEV">개발</option>
								   <option value="BUS">영업</option>
								   <option value="AFF">총무</option>
								   <option value="PLAN">기획</option>
								   <option value="PROD">생산</option>	
						</select>
					 </td>
					 
				</tr>	
				
				<tr>
					<td class="a">직위</td>
					<td class="b" colspan="2">
					<select name="mposition" id="mposition">
							   <option value="00" selected>직위선택</option>
							   <option value="01">사원</option>
							   <option value="02">대리</option>
							   <option value="03">차장</option>
							   <option value="04">과장</option>
							   <option value="05">임원</option>	
							   <option value="06">대표이사</option>	        	
						</select>
				</tr>	

				
				<tr>
					<td class="a">상급자 번호</td>
					<td class="b" colspan="2">
						<input type="text" name="mgrno" id="mgrno" placeholder="상급자 번호" style="width:150px" >
				 	</td>
				</tr>


				<tr>
					<td class="a">사내 번호</td>
					<td class="b" colspan="2">
						<input type="text" name="mtel" id="mtel" maxlength="3" size="2"/>
						- <input type="text" name="mtel1" id="mtel1" maxlength="4" size="2"/>
						- <select name="mtel2" id="mtel2">
							   <option value="00" selected>사내번호</option>
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
					<td class="but" colspan="3" align="center"> 			
						<button type="button" id="btn" class="btn btn-primary" style="font-size:18px;background-color:#92D050;border-color:#92D050;width:100px;height:40px;color:white;align-items: center;">SIGN IN</button>
					</td>				
				</tr>
			</table>
		</div>
	</form>
  </body>
</html>