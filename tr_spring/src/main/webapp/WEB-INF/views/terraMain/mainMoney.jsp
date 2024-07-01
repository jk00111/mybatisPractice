<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mongodb.BasicDBObject" %>
<%@ page import="com.mongodb.DB" %>
<%@ page import="com.mongodb.DBCollection" %>
<%@ page import="com.mongodb.DBCursor" %>
<%@ page import="com.mongodb.DBObject" %>
<%@ page import="com.mongodb.MongoClient" %>
<%@ page import="com.mongodb.ServerAddress" %>
<%@ page import="org.bson.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kos.tr.old.main.controller.Flask" %>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		money();
		
		async function money() {
			console.log("money 함수 시작  ---------------");
			var d = await trmoney();
			// trmoney() 함수가 본인의 일을 끝낼때까지 기다려서
			// async 함수가 다시 돌아감
			
		
		}
		
		function trmoney() {
			console.log("trmoney 함수 시작  ---------------");
			
			let urlV = "trMoneyMongoDB.tr";
			let methodV = "GET";
			let dataTypeV = "json";
			
			$.ajax({
				url: urlV,
				method: methodV,
				dataType: dataTypeV,
				success: trmoneySuccess,
				error: trmoneyError
			});
			
			
		}
		
		function trmoneySuccess(res) {
			
			var usd = " " + JSON.stringify(res['USD']).replaceAll('"', '') + "$";
			var jpy = " " + JSON.stringify(res['JPY']).replaceAll('"', '') + "¥";
			var eur = " " + JSON.stringify(res['EUR']).replaceAll('"', '') + "€";
			var cny = " " + JSON.stringify(res['CNY']).replaceAll('"', '') + "元";
			console.log(usd);
			console.log(jpy);
			console.log(eur);
			console.log(cny);
			
			console.log("확인ㄱ인");
			
			$("#usd").val(usd);
			$("#jpy").val(jpy);
			$("#eur").val(eur);
			$("#cny").val(cny);
		}
		
		function trmoneyError() {
			
			 console.log("Error: " + e.responseText);
		}
		
		setInterval(() => money(), 10000);
		
	});

</script>

</head>
<body>
 	<table>
 		<tr>
 			<td style="background-color:#C5E0B4;width:150px">미국USD</td>
 			<td style="background-color:#E2F0D9"><input type="text" id="usd" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9"></td>
 		</tr>
 		<tr>
 			<td style="background-color:#C5E0B4;width:150px">일본JPY</td>
 			<td style="background-color:#E2F0D9"><input type="text" id="jpy" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9"></td>
 		</tr>
 		<tr>
 			<td style="background-color:#C5E0B4;width:150px">유럽연합EUR</td>
 			<td style="background-color:#E2F0D9"><input type="text" id="eur" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9"></td>
 		</tr>
 		<tr>
 			<td style="background-color:#C5E0B4;width:150px">중국CNY</td>
 			<td style="background-color:#E2F0D9"><input type="text" id="cny" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9"></td>
 		</tr>
 	</table>

</body>
</html>