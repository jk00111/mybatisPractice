/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.87
 * Generated at: 2023-07-28 07:41:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.terraApproval;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import com.kos.tr.common.CodeUtil;
import com.kos.tr.app.vo.AppDocVO;
import com.kos.tr.member.vo.TerraMemberVO;

public final class appSelect_005fD_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.kos.tr.app.vo.AppDocVO");
    _jspx_imports_classes.add("com.kos.tr.member.vo.TerraMemberVO");
    _jspx_imports_classes.add("java.util.ArrayList");
    _jspx_imports_classes.add("com.kos.tr.common.CodeUtil");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	
	ArrayList<AppDocVO> cList = (ArrayList<AppDocVO>)request.getAttribute("cList");
	ArrayList<TerraMemberVO> uList = (ArrayList<TerraMemberVO>)request.getAttribute("uList");
	ArrayList<AppDocVO> prList = new ArrayList<AppDocVO>(); 
	ArrayList<TerraMemberVO> pList = new ArrayList<TerraMemberVO>();
	
	if((ArrayList<AppDocVO>)request.getAttribute("prList") != null && (ArrayList<TerraMemberVO>)request.getAttribute("pList") != null){
		prList = (ArrayList<AppDocVO>)request.getAttribute("prList");
		pList = (ArrayList<TerraMemberVO>)request.getAttribute("pList");
		System.out.println("listcheck >>>>>>>>>>>>>>" + pList.size() + ":" + prList.size());
		
	}
	
	AppDocVO pagingdvo = (AppDocVO)request.getAttribute("pagingdvo");
	
	int pageSize = 0;
	int groupSize = 0;
	int curPage = 0; // curPage : 현재페이지
	int totalCount = 0;
	
	TerraMemberVO mvo = uList.get(0);

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title> post page</title>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maxinum-scale=1, user-scalable=no\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js\"></script>\r\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../resources/css/sidebars.css\">\r\n");
      out.write("<link rel=\"canonical\" href=\"https://getbootstrap.com/docs/5.0/examples/sidebars/\">\r\n");
      out.write("<script src=\"../resources/js/util.js\"></script>\r\n");
      out.write("\r\n");
      out.write("	<style>\r\n");
      out.write("	.icon_img{\r\n");
      out.write("		width:22px;\r\n");
      out.write("		height: 22px;\r\n");
      out.write("		margin-bottom: 10px;\r\n");
      out.write("		margin-left: 25px;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.dropdown-toggle-no-caret::after {\r\n");
      out.write("		display: none;\r\n");
      out.write("	}\r\n");
      out.write("\r\n");
      out.write(" 	th{ \r\n");
      out.write("		background-color:#E2F0D9;\r\n");
      out.write("		text-align:center;\r\n");
      out.write("\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	td{\r\n");
      out.write("		text-align:center;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.subj{\r\n");
      out.write("		width:400px;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.idx{\r\n");
      out.write("		cursor:pointer;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	#nowSelected{\r\n");
      out.write("		background-color: #C5E0B4;\r\n");
      out.write("	}\r\n");
      out.write("	</style>\r\n");
      out.write("	<script>\r\n");
      out.write("		$(document).ready(function(){\r\n");
      out.write("\r\n");
      out.write("			var type = \"\";\r\n");
      out.write("		\r\n");
      out.write("			$(document).on('click', '#istBtn', function(){\r\n");
      out.write("				location.href = \"../appInsertDoc.tr\";\r\n");
      out.write("			});\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("			$(document).on('mouseenter', '.idx', function(){\r\n");
      out.write("\r\n");
      out.write("				$(this).css('background-color', '#ecf9ec');\r\n");
      out.write("			});\r\n");
      out.write("\r\n");
      out.write("			$(document).on('mouseleave', '.idx', function(){\r\n");
      out.write("\r\n");
      out.write("				$(this).css('background-color', '#E2F0D9');\r\n");
      out.write("				});\r\n");
      out.write("			\r\n");
      out.write("			$(document).on('mouseleave', '#nowSelected', function(){\r\n");
      out.write("\r\n");
      out.write("				$(this).css('background-color', '#C5E0B4');\r\n");
      out.write("			});\r\n");
      out.write("			\r\n");
      out.write("			$(document).on('click', '.contents', function(){\r\n");
      out.write("				var thisVal = $(this).find('input').eq(0).val();\r\n");
      out.write("				$('#anum').val(thisVal);\r\n");
      out.write("				\r\n");
      out.write("				var typeEn = $(this).closest('tr').find('td:nth-last-child(2)');\r\n");
      out.write("				var value = typeEn.text();\r\n");
      out.write("				type = docTypeR(value);\r\n");
      out.write("				console.log(type);\r\n");
      out.write("				\r\n");
      out.write("				$('#appSelectAll').attr({\r\n");
      out.write("					'action':'/tr_spring/appSelectContent/' + type + '.tr',    \r\n");
      out.write("					'method':'POST',\r\n");
      out.write("					'enctype':'application/x-www-form-urlencoded'\r\n");
      out.write("				}).submit();\r\n");
      out.write("			});\r\n");
      out.write("			\r\n");
      out.write("			\r\n");
      out.write("			if (");
      out.print(prList.size() == 0);
      out.write(") { \r\n");
      out.write("				\r\n");
      out.write(" 				$('#proxytable').css(\"display\", \"none\");\r\n");
      out.write("			}\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				type:\"GET\",\r\n");
      out.write("				url: '/tr_spring/appAlert.tr',\r\n");
      out.write("				success : function(result){\r\n");
      out.write("					\r\n");
      out.write("					if (result == 'ALERT_YES') {\r\n");
      out.write("						$('#bell').attr(\"src\", \"/tr_spring/resources/images/alert.png\")\r\n");
      out.write("					}\r\n");
      out.write("				},\r\n");
      out.write("				error: function(xtr, status, error){\r\n");
      out.write("					alert(xtr + \":\" + status + \":\" + error);\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("\r\n");
      out.write("		\r\n");
      out.write("	</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<nav class=\"navbar navbar-expand-sm navbar-dark sticky-top\" style=\"background-color: #C5E0B4;padding-right:0;\">\r\n");
      out.write("		<!--LOGO-->\r\n");
      out.write("		<a class=\"navbar-brand\" href=\"/tr_spring/mainPage.tr\" >&nbsp&nbsp<img src=\"../resources/images/logo-green2.png\" style=\"height:30px\"></a>\r\n");
      out.write("		<!--Links-->\r\n");
      out.write("		<div class=\"container-fluid full-width\">\r\n");
      out.write("		<ul class=\"nav navbar-nav\">\r\n");
      out.write("			<li class=\"nav-item dropdown\">\r\n");
      out.write("				<a class=\"nav-link dropdown-toggle\" id=\"dropdown01\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\" style=\"padding-top:13px;font-size:18px;color:#534825;font-weight:bold;cursor:pointer\">전자결재</a>\r\n");
      out.write("				<ul class=\"dropdown-menu\" aria-labelledby=\"dropdown01\">\r\n");
      out.write("				  <li><a class=\"dropdown-item\" href=\"/tr_spring/mainPage.tr\">메인페이지</a></li>\r\n");
      out.write("				  <li><a class=\"dropdown-item\" href=\"/tr_spring/tBoardSelectAll.tr\">게시판</a></li>\r\n");
      out.write("				  <li><a class=\"dropdown-item\" href=\"/tr_spring/tMemAddrBook.tr\">주소록</a></li>\r\n");
      out.write("				  <li><a class=\"dropdown-item\" href=\"/tr_spring/appSelect/A.tr\">전자결재</a></li>\r\n");
      out.write("				  <li><a class=\"dropdown-item\" href=\"#\">일정</a></li>\r\n");
      out.write("				  <li><a class=\"dropdown-item\" href=\"/tr_spring/trWorkForm.tr\">인사</a></li>\r\n");
      out.write("				</ul>\r\n");
      out.write("			  </li>\r\n");
      out.write("		</ul>\r\n");
      out.write("		<div>\r\n");
      out.write("						<div class=\"dropdown\" style=\"position:absolute;right:80px\">\r\n");
      out.write("				<img id=\"bell\" onclick=\"javascript:location.href='/tr_spring/appSelect/W.tr'\" src=\"/tr_spring/resources/images/notification-bell.png\" alt=\"\" width=\"32\" height=\"32\" style=\"cursor:pointer\">\r\n");
      out.write("			</div>\r\n");
      out.write("		<div>\r\n");
      out.write("		<div class=\"dropdown\" style=\"left:-10px;cursor:pointer\">\r\n");
      out.write("			<a href=\"#\" class=\"d-flex align-items-center text-white text-decoration-none dropdown-toggle dropdown-toggle-no-caret\" id=\"dropdownUser1\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\r\n");
      out.write("			  <img src=\"../fileupload/tmem/");
      out.print( mvo.getMphoto() );
      out.write("\" alt=\"\" width=\"35\" height=\"35\" class=\"rounded-circle me-2\">\r\n");
      out.write("			</a>\r\n");
      out.write("			<ul class=\"dropdown-menu dropdown-menu-dark text-small shadow\" style=\"left:-110px\" aria-labelledby=\"dropdownUser1\">\r\n");
      out.write("			  <li><a class=\"dropdown-item\" href=\"#\">마이 페이지</a></li>\r\n");
      out.write("			  <li><hr class=\"dropdown-divider\"></li>\r\n");
      out.write("			  <li><a class=\"dropdown-item\" href=\"../terraLogout.tr\">로그아웃</a></li>\r\n");
      out.write("			</ul>\r\n");
      out.write("		  </div>\r\n");
      out.write("		</div>\r\n");
      out.write("		</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</nav>\r\n");
      out.write("		<div class=\"row\" style=\"margin-right: 0;\">\r\n");
      out.write("		  <div class=\"col-3\" >\r\n");
      out.write("			<!-- 사이드바 내용 -->\r\n");
      out.write("			<div class=\"sidebar\">\r\n");
      out.write("			  <!-- 사이드바 내용을 추가 -->\r\n");
      out.write("			  <div class=\"d-flex flex-column flex-shrink-0 p-3 \" style=\"width: 280px; height:auto; overflow-y: auto; height: 100vh;position:fixed; background-color:#E2F0D9;\">\r\n");
      out.write("				<button type=\"button\" class=\"btn btn-primary\" id=\"istBtn\" style=\"background-color:#92D050;height:50px;border:0; font-size:20px;font-weight:1000;color:#534825;cursor:pointer\">+ 작성하기</button>\r\n");
      out.write("				<br>\r\n");
      out.write("				<ul class=\"list-unstyled ps-0\">\r\n");
      out.write("					<li class=\"mb-1\">\r\n");
      out.write("					  <button class=\"btn btn-toggle align-items-center rounded collapsed\" data-bs-toggle=\"collapse\" data-bs-target=\"#home-collapse\" aria-expanded=\"true\" style=\"font-size: 20px;cursor:pointer\">\r\n");
      out.write("						진행중인 문서\r\n");
      out.write("					  </button>\r\n");
      out.write("					  <div class=\"collapse show\" id=\"home-collapse\">\r\n");
      out.write("						<ul class=\"btn-toggle-nav list-unstyled fw-normal pb-1 small\">\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/A.tr'\"><img src=\"/tr_spring/resources/images/file.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">전체</span></div></li>\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/W.tr'\"><img src=\"/tr_spring/resources/images/hourglass.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">대기</span></div></li>\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/P.tr'\"><img src=\"/tr_spring/resources/images/time.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">예정</span></div></li>\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/K.tr'\"><img src=\"/tr_spring/resources/images/loading.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">진행</span></div></li>\r\n");
      out.write("						</ul>\r\n");
      out.write("					  </div>\r\n");
      out.write("					</li>\r\n");
      out.write("					<li class=\"mb-1\">\r\n");
      out.write("					  <button class=\"btn btn-toggle align-items-center rounded collapsed\" data-bs-toggle=\"collapse\" data-bs-target=\"#dashboard-collapse\" aria-expanded=\"true\" style=\"font-size: 20px;cursor:pointer\">\r\n");
      out.write("						문서함\r\n");
      out.write("					  </button>\r\n");
      out.write("					  <div class=\"collapse show\" id=\"dashboard-collapse\">\r\n");
      out.write("						<ul class=\"btn-toggle-nav list-unstyled fw-normal pb-1 small\">\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/AllDoc.tr'\"><img src=\"/tr_spring/resources/images/file.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">전체</span></div></li>\r\n");
      out.write("						  <li><div id=\"nowSelected\" class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/D.tr'\"><img src=\"/tr_spring/resources/images/pencil.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">기안</span></div></li>\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/AA.tr'\"><img src=\"/tr_spring/resources/images/digital-signature.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">결재</span></div></li>\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/R.tr'\"><img src=\"/tr_spring/resources/images/add-user.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">참조</span></div></li>\r\n");
      out.write("						  <li><div class=\"idx\" onclick=\"javascript:location.href='/tr_spring/appSelect/RJ.tr'\"><img src=\"/tr_spring/resources/images/traffic-signal.png\" class=\"icon_img\"><span class=\"link-dark rounded\" style=\"font-size:20px;margin-left:12px\">반려</span></div></li>\r\n");
      out.write("						</ul>\r\n");
      out.write("					  </div>\r\n");
      out.write("					</li>\r\n");
      out.write("				  </ul>\r\n");
      out.write("			  </div>\r\n");
      out.write("			</div>\r\n");
      out.write("		  </div>\r\n");
      out.write("		</div>\r\n");
      out.write("		  <div class=\"col-9\" style=\"left:300px\">\r\n");
      out.write("			<!-- 본문 내용 -->\r\n");
      out.write("			<div class=\"content\">\r\n");
      out.write("			  <!-- 본문 내용을 추가 -->\r\n");
      out.write("			  \r\n");
      out.write("			  <div>\r\n");
      out.write("				\r\n");
      out.write("	<form name=\"appSelectAll\" id=\"appSelectAll\" style=\"padding-left:100px\">\r\n");
      out.write("	<br>\r\n");
      out.write("	<table class=\"table table-hover\">\r\n");
      out.write("	<thead>\r\n");
      out.write("	<tr>\r\n");
      out.write("		<td colspan=\"9\" style=\"text-align:left;border:0\">\r\n");
      out.write("			<img src=><h6>전자결재 > 문서함 > 기안</h6>\r\n");
      out.write("		</td>\r\n");
      out.write("	</tr>\r\n");
      out.write("	<tr>\r\n");
      out.write("		<th>문서번호</th>\r\n");
      out.write("		<th class=\"subj\">제목</th>\r\n");
      out.write("		<th>기안자</th>\r\n");
      out.write("		<th>기안일</th>\r\n");
      out.write("		<th>문서종류</th>\r\n");
      out.write("		<th>상태</th>\r\n");
      out.write("	</tr>\r\n");
      out.write("	</thead>\r\n");
      out.write("        ");

          if(cList.size() == 0){
       
      out.write("      \r\n");
      out.write("       <tr>\r\n");
      out.write("          <td colspan=\"7\" height=\"300\" style=\"text-align:center;\">조회된 데이터가 없습니다.</td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       ");
   
          } else {
		for(int i=0; i < cList.size(); i++){
			
			AppDocVO advo = (AppDocVO)cList.get(i);
			
            pageSize = Integer.parseInt(pagingdvo.getPageSize());
            groupSize = Integer.parseInt(pagingdvo.getGroupSize());
            curPage = Integer.parseInt(pagingdvo.getCurPage());
            totalCount = Integer.parseInt(advo.getTotalCount());
	
      out.write("					\r\n");
      out.write("	<tbody>\r\n");
      out.write("	<tr class=\"contents\">	\r\n");
      out.write("		<td>");
      out.print( advo.getAnum() );
      out.write(" <input type=\"hidden\" value=");
      out.print( advo.getAnum() );
      out.write(">	</td>\r\n");
      out.write("		<td class=\"subj\">");
      out.print( advo.getSubject() );
      out.write("</td>	\r\n");
      out.write("		<td>");
      out.print( advo.getMname() );
      out.write("</td>\r\n");
      out.write("		<td>");
      out.print( advo.getInsertdate() );
      out.write("</td>			\r\n");
      out.write("		<td>");
      out.print( CodeUtil.docType(advo.getAtype()));
      out.write("</td>		\r\n");
      out.write("		<td>기안</td>		\r\n");
      out.write("	</tr>	\r\n");
      out.write("	");

			}
		}
	
      out.write("	\r\n");
      out.write("	         <tr>\r\n");
      out.write("               <td style=\"border-left:none;\" colspan=\"6\">");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "appPaging.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("url", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("../appSelect/D.tr", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("str", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("pageSize", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(pageSize), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("groupSize", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(groupSize), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("curPage", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(curPage), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("totalCount", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(totalCount), request.getCharacterEncoding()), out, true);
      out.write("\r\n");
      out.write("               </td>\r\n");
      out.write("            </tr>	\r\n");
      out.write("	</tbody>\r\n");
      out.write("	</table>\r\n");
      out.write("			<input type=\"hidden\" name=\"anum\" id=\"anum\">\r\n");
      out.write("	</form>	\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("	<script src=\"../resources/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
