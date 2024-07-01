<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kos.tr.board.vo.TerraBoardVO" %>
<%@ page import="java.util.List" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NOTICE</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<style type="text/css">
	 table{
		border:solid 1px;
	}
</style>
	<script>
		function notice(){

			$.ajax({
				type:"GET",
				url: 'http://192.168.0.36:8000/notice',
				dataType:'JSON',
				success : function(result){
					var rs = JSON.stringify(result)
					console.log(result[0]['NMEMO']);
					
					$('#nmemo').html(result[0]['NMEMO']);
					$('#nsubject').text(result[0]['NSUBJECT']);
				},
				error: function(xtr, status, error){
					alert(xtr + ":" + status + ":" + error);
				}
			});
		}
		notice();
		
		$(document).ready(function(){
			
			$(document).on('click', '#closeBtn', function(){
				window.close();
			});
		});
	</script>
</head>
<body>
<div style="width:400px; height:500px; padding-left:10px">
<table>
	<tr>
		<td style="width:400px;height:80px;text-align:center;background-color:#E2F0D9"><h2 id="nsubject"></h2></td>
	</tr>
	<tr>
		<td style="width:300px;height:420px;background-image: url('/tr_spring/resources/images/noticebg.png');text-align:right;vertical-align: top;"><div style="padding-top:90px;padding-right:30px"><span id="nmemo" style="font-size:20px;"></span></div></td>
	</tr>
	<tr>
	<td style="text-align:right"><button type="button" id="closeBtn" class="btn btn-primary btn-sm" style="background-color:#92D050;border-color:#92D050;">닫기</button></td>
	</tr>
</table>
</div>
</body>
</html>