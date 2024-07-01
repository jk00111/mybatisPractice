<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String atype = (String)request.getParameter("atype");
	String anum = (String)request.getParameter("anum");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		
		type = "<%=atype%>";
		console.log(type);
		
		$('#redirectForm').attr({
			'action':'/tr_spring/appSelectContent/' + type + '.tr',    
			'method':'POST',
			'enctype':'application/x-www-form-urlencoded'
		}).submit();
	
	});

</script>
</head>
<body>
<script type="text/javascript">
</script>
</body>
<form name="redirectForm" id="redirectForm">
	<input type="hidden" name="atype" id="atype" value="<%=atype%>">
	<input type="hidden" name="anum" id="anum" value="<%=anum%>">
</form>
</html>