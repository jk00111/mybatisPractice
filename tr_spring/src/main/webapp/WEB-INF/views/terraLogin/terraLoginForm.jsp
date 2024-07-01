<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
    	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1, user-scalable=no">
	
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   		<title>login page</title>
		<style>
			 @import url('https://fonts.googleapis.com/css2?family=Bungee+Inline&family=Kaushan+Script&family=Noto+Sans+KR:wght@900&family=Press+Start+2P&display=swap');
        
			.terraFont {
				font-family: 'Bungee Inline';
				font-size:130px;
			}

			html {
			height: 100%;
			margin: 0;
			display: flex;
			align-items: center;
			justify-content: center;
			}

			
			.form-control{
				width: 400px;
				height : 50px;
				
			}

			div.card-body {
			display: flex;
			justify-content: center;
			align-items: center;
			}
		</style>
		
	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#mid").attr('placeholder', 'id');
			$("#mpw").attr('placeholder', 'password');
			
			$("#loginBtn").click(function(){
				console.log("loginBtn >>> : ");
				
				$('#terraLoginForm').attr({
					'action':"loginCheck.tr",
					'method':'POST',
					'enctype':'application/x-www-form-urlencoded'
				}).submit();
			});		
		});	
		
	</script>
	</head>

	<body>
		<br><div class="container" style="margin:0px;padding:0px">
		<div class="card-title" style="margin-top:50px;" onclick="javascript:location.href='/'">
			<h2 class="text-center terraFont" style="color:#C5E0B4; margin:0px;" onfocus="this.blur()">TERRA</h2>
		</div>
		<div class="card align-middle" style="width:500px; border-radius:20px;background-color:#C5E0B4; bottom:25px">
		<div class="card-body">
    	<form class="terraLoginForm" name="terraLoginForm" id="terraLoginForm"> 
			<br><br>
        	<input type="text" name="mid" id="mid" class="form-control" required autofocus>
			<div style="height:15px"></div>
        	<input type="password" name="mpw" id="mpw" class="form-control" required><br>
<!-- 		<div style="text-align: right;position:relative; bottom:20px"><a href="/">비밀번호 찾기</a></div> -->	
			<button id="loginBtn" name="loginBtn" class="btn btn-primary btn-block" type="button" style="background-color:#92D050; border-color:#92D050; height:50px; font-size:20px">로그인</button>
			<button id="regBtn" name="regBtn" class="btn btn-primary btn-block" type="button"  style="background-color:#92D050; border-color:#92D050; height:50px; font-size:20px">회원가입</button>
			<br>
    	</form>
		</div>
		</div>
  </body>
</html>