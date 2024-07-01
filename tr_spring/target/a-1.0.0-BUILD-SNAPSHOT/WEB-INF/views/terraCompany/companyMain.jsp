<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Terra Company</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 디바이스에 최적화된 크기로 출력됨 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0
     	maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="resources/css/terraCompany.css">

</head>
<body>
	  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<script>
	
	// 문서가 준비되었을 때 실행
	  $(document).ready(function () {
	   
		// 날씨 api - fontawesome 아이콘
	        var weatherIcon = {
	            '01' : 'fas fa-sun',
	            '02' : 'fas fa-cloud-sun',
	            '03' : 'fas fa-cloud',
	            '04' : 'fas fa-cloud-meatball',
	            '09' : 'fas fa-cloud-sun-rain',
	            '10' : 'fas fa-cloud-showers-heavy',
	            '11' : 'fas fa-poo-storm',
	            '13' : 'far fa-snowflake',
	            '50' : 'fas fa-smog'
	        };

	        // 날씨 api - 서울
	        var apiURI = "http://api.openweathermap.org/data/2.5/weather?q="+'seoul'+"&appid="+"ffef07ceb882a71853636872f58282ae";
	        $.ajax({
	            url: apiURI,
	            dataType: "json",
	            type: "GET",
	            async: "false",
	            success: function(resp) {

	                var $Icon = (resp.weather[0].icon).substr(0,2);
	                var $weather_description = resp.weather[0].main;
	                var $Temp = Math.floor(resp.main.temp- 273.15) + 'º';
	                var $humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + resp.main.humidity+ ' %';
	                var $wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' +resp.wind.speed + ' m/s';
	                var $city = '서울';
	                var $cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + resp.clouds.all +"%";
	                var $temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_min- 273.15) + 'º';
	                var $temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_max- 273.15) + 'º';
	                

	                $('.weather_icon').append('<i class="' + weatherIcon[$Icon] +' fa-5x" style="height : 150px; width : 150px;"></i>');
	                $('.weather_description').prepend($weather_description);
	                $('.current_temp').prepend($Temp);
	                $('.humidity').prepend($humidity);
	                $('.wind').prepend($wind);
	                $('.city').append($city);
	                $('.cloud').append($cloud);
	                $('.temp_min').append($temp_min);
	                $('.temp_max').append($temp_max);               
	            }
	        })
	        
		  
	  });
	
	</script>

	
	  <!-- Navigation (maun_wrap) 섹션 -->
  <div class="maun_wrap">
    <img onclick="location.href='companyMain.tr'" class="logo" src="resources/images/logo-green2.png" alt="logo" style="padding-top:10px">
    <ul class="dep1">
      <li>
        <a href="#">회사소개</a>
        <ul class="dep2"> 
          <li><a href="#">회사소개_1</a></li>
          <li><a href="#">회사소개_2</a></li>
          <li><a href="#">회사소개_3</a></li>
          <li><a href="#">회사소개_4</a></li>
        </ul>
      </li>
      <li>
        <a href="#">뉴스/소식</a>
        <ul class="dep2">
          <li><a href="#">뉴스/소식_1</a></li>
          <li><a href="#">뉴스/소식_2</a></li>
          <li><a href="#">뉴스/소식_3</a></li>
          <li><a href="#">뉴스/소식_4</a></li>
        </ul>
      </li>
      <li>
        <a href="#">회사공지</a>
        <ul class="dep2">
          <li><a href="#">회사공지_1</a></li>
          <li><a href="#">회사공지_2</a></li>
          <li><a href="#">회사공지_3</a></li>
          <li><a href="#">회사공지_4</a></li>
        </ul>
      </li>
      <li>
        <a href="#">고객지원</a>
        <ul class="dep2">
          <li><a href="#">고객지원_1</a></li>
          <li><a href="#">고객지원_2</a></li>
          <li><a href="#">고객지원_3</a></li>
          <li><a href="#">고객지원_4</a></li>
        </ul>
      </li>
    </ul>
  </div>
<!-- 메인 컨텐츠 (main) 섹션 -->
    <div class="main">
      
      <p>회사 찾아오시는 길</p>
      <div class="weather">
      	 <div  style=" padding : 40px;color : black; height : 220px">
	        <div style="float : left;">
	              	   <!-- map 지도 섹션 -->
	            <div class="weather_icon"></div>
	        </div><br>
	    
	        <div style="float : right; margin : -5px 0px 0px 60px; font-size : 11pt">
	                <div class="temp_min"></div>
	                <div class="temp_max"></div>
	                <div class="humidity"></div>
	                <div class="wind"></div>
	                <div class="cloud"></div>
	        </div>
	        <div style="float : right; margin-top : -45px;">
	            <div class="current_temp" style="font-size : 50pt"></div>
	            <div class="weather_description" style="font-size : 20pt"></div>
	            <div class="city" style="font-size : 13pt"></div>
	        </div>
	    </div>
      </div>
       
        <div class="btn_group">
        
          <button id="loginButton" class="button">로그인</button><br>
          <button onclick="location.href='loginForm.tr'" id="groupwareButton" class="button"> 그룹웨어 </button>
        </div>
      </div>
      <div class="map" id="inf" >
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1583.1176388537303!2d126.87769174575804!3d37.47877391076083!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b61fcb47b582b%3A0x536ee2f714c1e156!2z7JuU65Oc66mU66W065SU7JWZ67Kk7LOQ7IS87YSwMuywqA!5e0!3m2!1sko!2skr!4v1689730940424!5m2!1sko!2skr" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
    </div>
 <!-- 바닥부 (footer) 섹션 -->
  <footer class="footer_wrap" style="height:123px;">
    <div class="footer_content">
      <div class="footer_logo" >
        <img src="resources/images/logo-green2.png" alt="footer logo">
      </div >
      <div class="footer_text" style="text-align:right; left:700px">
        <p>(우) 00000 서울 금천구 가산디지털로2로 123</p>
        <p>대표이사 : 박설희 / 대표번호 : 02-0000-0000</p>
      </div>
    </div>
  </footer>

</body>
</html>