<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">


	$(document).ready(function(){
		
		
		$(document).on('click', '#dateCal', function(){
			
			var num = getWeekdayCount();
			
			alert(num);
		});
		
		
		function getWeekdayCount(daycnt) {

			const today = new Date();
			const year = today.getFullYear();
			const month = String(today.getMonth() + 1).padStart(2, '0');
			const day = String(today.getDate()).padStart(2, '0');

			startdate = new Date(year, 01, 01);
			enddate = new Date(year, month, day);
			
			var count = 0;
			
			
			// endDate까지 반복하면서 주말(토요일과 일요일)을 제외한 평일 수를 계산
			while (startdate <= enddate) {
			var dayOfWeek = startdate.getDay();
			if (dayOfWeek !== 0 && dayOfWeek !== 6) {
				count++;
			    }
			    startdate.setDate(startdate.getDate() + 1);
			  }
			
			  return (count - 1) - daycnt;
		}
	});


</script>
</head>
<body>
<input type="button" id="dateCal" value="계산">
</body>
</html>