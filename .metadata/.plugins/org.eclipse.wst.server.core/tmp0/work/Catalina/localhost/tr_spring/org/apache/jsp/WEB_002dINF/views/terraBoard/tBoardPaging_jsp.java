/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.87
 * Generated at: 2023-07-29 05:46:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.terraBoard;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public final class tBoardPaging_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("java.util.Collection");
    _jspx_imports_classes.add("java.io.File");
    _jspx_imports_classes.add("java.util.ArrayList");
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
      response.setContentType("text/html; charset=utf-8");
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

	/********************************
	전달해야 할 변수
	********************************/
	String url = null;
	String str = null;
	
	url = request.getParameter("url");
	System.out.println("url >>> : " + url);
	
	str = request.getParameter("str");
	System.out.println("str >>> : " + str);
	
	if (str != null && str.length() > 0) {
	   str = str + "&";
	   System.out.println("str + & >>>" + str);
}

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>My JSP Page</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".custom-link {\r\n");
      out.write("   /* 필요한 스타일을 적용해주세요 */\r\n");
      out.write("   text-decoration: none; /* 링크에 밑줄 제거 */\r\n");
      out.write("   color: #000; /* 링크의 색상 설정 */\r\n");
      out.write("   /* 그 외 원하는 스타일을 추가로 적용할 수 있습니다 */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".number {\r\n");
      out.write("   display: inline-block;\r\n");
      out.write("   width: 30px;\r\n");
      out.write("   height: 30px;\r\n");
      out.write("   border: 1px solid #92D050; /* 테두리에 1px 두께의 검정색 선 추가 */\r\n");
      out.write("   text-align: center;\r\n");
      out.write("   font-size: 18px;\r\n");
      out.write("   line-height: 30px;\r\n");
      out.write("   background-color: #92D050;\r\n");
      out.write("   color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".string {\r\n");
      out.write("   display: inline-block;\r\n");
      out.write("   width: 60px;\r\n");
      out.write("   height: 30px;\r\n");
      out.write("   border: 1px solid #92D050; /* 테두리에 1px 두께의 검정색 선 추가 */\r\n");
      out.write("   text-align: center;\r\n");
      out.write("   font-size: 18px;\r\n");
      out.write("   line-height: 30px;\r\n");
      out.write("   background-color: white;\r\n");
      out.write("   color: #92D050;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".othernumber {\r\n");
      out.write("   display: inline-block;\r\n");
      out.write("   width: 30px;\r\n");
      out.write("   height: 30px;\r\n");
      out.write("   border: 1px solid #92D050; /* 테두리에 1px 두께의 검정색 선 추가 */\r\n");
      out.write("   text-align: center;\r\n");
      out.write("   font-size: 18px;\r\n");
      out.write("   line-height: 30px;\r\n");
      out.write("   background-color: #FFF;\r\n");
      out.write("   color: #92D050;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".otherstring {\r\n");
      out.write("   display: inline-block;\r\n");
      out.write("   width: 60px;\r\n");
      out.write("   height: 30px;\r\n");
      out.write("   border: 1px solid #92D050; /* 테두리에 1px 두께의 검정색 선 추가 */\r\n");
      out.write("   text-align: center;\r\n");
      out.write("   font-size: 18px;\r\n");
      out.write("   line-height: 30px;\r\n");
      out.write("   background-color: #FFF;\r\n");
      out.write("   color: #337ab7;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   ");

      /**********************************
   페이지 네비게이션 관련 변수
   ***********************************/
   // 한페이지에 보여질 게시물의 수
   int pageSize = 0;
   // 그룹의 크기
   int groupSize = 0;
   // 전체 게시물의 개수
   int totalCount = 0;
   // 현재 페이지
   int curPage = 0;
   // 전체 페이지의 개수
   int pageCount = 0;

   if (request.getParameter("pageSize") != null) {
      pageSize = Integer.parseInt(request.getParameter("pageSize")); // 10
      System.out.println("pageSize >>> : " + pageSize);
   }

   if (request.getParameter("groupSize") != null) {
      groupSize = Integer.parseInt(request.getParameter("groupSize")); //5
      System.out.println("groupSize >>> : " + groupSize);
   }

   if (request.getParameter("curPage") != null) {
      curPage = Integer.parseInt(request.getParameter("curPage")); //1
      System.out.println("curPage >>> : " + curPage);
   }

   if (request.getParameter("totalCount") != null) {
      totalCount = Integer.parseInt(request.getParameter("totalCount"));//400
      System.out.println("totalCount >>> : " + totalCount);
   }

   // 전체게시물수와 페이지크기를 가지고 전체 페이지 개수를 계산함.
   // 소수점에 따라 계산상의 오류가 없도록 한것임.
   pageCount = (int) Math.ceil(totalCount / (pageSize + 0.0));
   System.out.println("pageCount >>> : " + pageCount);
   //(0/page)

   // 현재그룹 설정
   // 1-1/5
   int curGroup = (curPage - 1) / groupSize;
   System.out.println("curGroup >>> : " + curGroup);

   // 0*0
   int linkPage = curGroup * groupSize;
   System.out.println("linkPage >>> : " + linkPage);
   
      out.write("\r\n");
      out.write("   <div style=\"text-align:right\">\r\n");
      out.write("   <p align=\"right\">\r\n");
      out.write("      ");

         // 첫번째 그룹이 아닌경우
      if (curGroup > 0) {

         //boardSelectList.jsp?&curPage=1   
         //boardSelectList.jsp?&curPage=0
      
      out.write("\r\n");
      out.write("      <a href=\"");
      out.print(url);
      out.write('?');
      out.print(str);
      out.write("curPage=");
      out.print(linkPage);
      out.write("\"><span class=\"otherstring\">이전</span></a>\r\n");
      out.write("      ");

         } else {
      
      out.write("\r\n");
      out.write("      <span class=\"string\">이전</span>\r\n");
      out.write("      ");

         }

      // 다음 링크를 위해 증가시킴
      linkPage++;
      System.out.println("linkPage++ >>> : " + linkPage);
      //1

      int loopCount = groupSize;
      System.out.println("loopCount >>> : " + loopCount);
      //5

      // 그룹범위내에서 페이지 링크만듬.
      //5>0 && 1<=40
      while ((loopCount > 0) && (linkPage <= pageCount)) {
      //1==1
      if (linkPage == curPage) {
         System.out.println("그룹범위내에서 페이지 링크if");
         //linkPage :1
      
      out.write("\r\n");
      out.write("      <span class=\"number\">");
      out.print(linkPage);
      out.write("</span>\r\n");
      out.write("      ");

         } else {
            System.out.println("그룹범위내에서 페이지 링크else");
      
      out.write("\r\n");
      out.write("      <a href=\"");
      out.print(url);
      out.write('?');
      out.print(str);
      out.write("curPage=");
      out.print(linkPage);
      out.write("\" id=\"custom-link\"><span\r\n");
      out.write("         class=\"othernumber\">");
      out.print(linkPage);
      out.write("</span></a>\r\n");
      out.write("      ");

         }
      linkPage++;
      loopCount--;
      }

      // 다음그룹이 있는 경우
      //      6         40
      if (linkPage <= pageCount) {
      System.out.println("다음그룹이 있는 경우 linkPage >>> : " + linkPage);

      //   boardSelectList.jsp?&curPage=6
      //   boardSelectList.jsp?&curPage=40
      
      out.write("\r\n");
      out.write("      <a href=\"");
      out.print(url);
      out.write('?');
      out.print(str);
      out.write("curPage=");
      out.print(linkPage);
      out.write("\"><span class=\"otherstring\">다음</span></a>\r\n");
      out.write("      ");

         } else {
      System.out.println("다음그룹이 있는 경우 linkPage >>> : " + linkPage);
      System.out.println("다음그룹이 있는 경우_else");
      
      out.write("\r\n");
      out.write("      <span class=\"string\">다음</span>\r\n");
      out.write("      ");

         }
      
      out.write("\r\n");
      out.write("   </p>\r\n");
      out.write("</div>\r\n");
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
