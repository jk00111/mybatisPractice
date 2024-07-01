<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.kos.tr.work.vo.TerraWorkVO" %>

<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<% Logger logger = LoggerFactory.getLogger(this.getClass()); %>

<%
String startdate = "";
String wstate = "";
Object obj = request.getAttribute("listS");
logger.info("obj 초기 >>> : {}", obj);

List<TerraWorkVO> listS = null;

if (obj != null) {
	listS = (List<TerraWorkVO>)obj;
	int nCnt = listS.size();
	logger.info("listS.size() | trWorkSelect.jsp | {}", nCnt);
	// 출근시간 | 첫번째 listS를 꺼내와야함
	TerraWorkVO _twvo = listS.get(0);
	startdate = _twvo.getStartdate();
	
	// 상태
	wstate = _twvo.getWstate();
	
	logger.info("startdate | trWorkSelect.jsp | {}", startdate);
	logger.info("wstate | trWorkSelect.jsp | {}", wstate);
}else{
	logger.info("trWorkSelect|데이터안넘어옴");
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SELECT 처리</title>
</head>
<body>
<form>
<h3>trWorkSelect.jsp</h3>
<hr>
<input id="sdate" value="<%= startdate %>">
<input id="wstateL" value="<%= wstate %>">
<script>
	location.href="/tr_spring/trWorkForm.tr"
</script>
</form>
</body>
</html>