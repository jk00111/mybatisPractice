/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.87
 * Generated at: 2023-08-02 12:58:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.terraMain;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.parser.JSONParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.bson.*;
import com.kos.tr.main.controller.Flask;

public final class mainMoney_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1688364746784L));
    _jspx_dependants.put("jar:file:/C:/Users/kosmo/Desktop/TR_SpringLegacy_final/el_tr_spring_final_work/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/tr_spring/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("org.bson");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.mongodb.ServerAddress");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.mongodb.DBObject");
    _jspx_imports_classes.add("com.kos.tr.main.controller.Flask");
    _jspx_imports_classes.add("org.json.simple.parser.JSONParser");
    _jspx_imports_classes.add("com.mongodb.DBCursor");
    _jspx_imports_classes.add("com.mongodb.MongoClient");
    _jspx_imports_classes.add("com.mongodb.BasicDBObject");
    _jspx_imports_classes.add("com.mongodb.DBCollection");
    _jspx_imports_classes.add("com.mongodb.DB");
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
      out.write("    \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<script  src=\"http://code.jquery.com/jquery-latest.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("	$(document).ready(function() {\r\n");
      out.write("		\r\n");
      out.write("		money();\r\n");
      out.write("		\r\n");
      out.write("		async function money() {\r\n");
      out.write("			console.log(\"money 함수 시작  ---------------\");\r\n");
      out.write("			var d = await trmoney();\r\n");
      out.write("			// trmoney() 함수가 본인의 일을 끝낼때까지 기다려서\r\n");
      out.write("			// async 함수가 다시 돌아감\r\n");
      out.write("			\r\n");
      out.write("		\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		function trmoney() {\r\n");
      out.write("			console.log(\"trmoney 함수 시작  ---------------\");\r\n");
      out.write("			\r\n");
      out.write("			let urlV = \"trMoneyMongoDB.tr\";\r\n");
      out.write("			let methodV = \"GET\";\r\n");
      out.write("			let dataTypeV = \"json\";\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url: urlV,\r\n");
      out.write("				method: methodV,\r\n");
      out.write("				dataType: dataTypeV,\r\n");
      out.write("				success: trmoneySuccess,\r\n");
      out.write("				error: trmoneyError\r\n");
      out.write("			});\r\n");
      out.write("			\r\n");
      out.write("			\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		function trmoneySuccess(res) {\r\n");
      out.write("			\r\n");
      out.write("			var usd = \" \" + JSON.stringify(res['USD']).replaceAll('\"', '') + \"$\";\r\n");
      out.write("			var jpy = \" \" + JSON.stringify(res['JPY']).replaceAll('\"', '') + \"¥\";\r\n");
      out.write("			var eur = \" \" + JSON.stringify(res['EUR']).replaceAll('\"', '') + \"€\";\r\n");
      out.write("			var cny = \" \" + JSON.stringify(res['CNY']).replaceAll('\"', '') + \"元\";\r\n");
      out.write("			console.log(usd);\r\n");
      out.write("			console.log(jpy);\r\n");
      out.write("			console.log(eur);\r\n");
      out.write("			console.log(cny);\r\n");
      out.write("			\r\n");
      out.write("			console.log(\"확인ㄱ인\");\r\n");
      out.write("			\r\n");
      out.write("			$(\"#usd\").val(usd);\r\n");
      out.write("			$(\"#jpy\").val(jpy);\r\n");
      out.write("			$(\"#eur\").val(eur);\r\n");
      out.write("			$(\"#cny\").val(cny);\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		function trmoneyError() {\r\n");
      out.write("			\r\n");
      out.write("			 console.log(\"Error: \" + e.responseText);\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		setInterval(() => money(), 10000);\r\n");
      out.write("		\r\n");
      out.write("	});\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write(" 	<table>\r\n");
      out.write(" 		<tr>\r\n");
      out.write(" 			<td style=\"background-color:#C5E0B4;width:150px\">미국USD</td>\r\n");
      out.write(" 			<td style=\"background-color:#E2F0D9\"><input type=\"text\" id=\"usd\" style=\"border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9\"></td>\r\n");
      out.write(" 		</tr>\r\n");
      out.write(" 		<tr>\r\n");
      out.write(" 			<td style=\"background-color:#C5E0B4;width:150px\">일본JPY</td>\r\n");
      out.write(" 			<td style=\"background-color:#E2F0D9\"><input type=\"text\" id=\"jpy\" style=\"border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9\"></td>\r\n");
      out.write(" 		</tr>\r\n");
      out.write(" 		<tr>\r\n");
      out.write(" 			<td style=\"background-color:#C5E0B4;width:150px\">유럽연합EUR</td>\r\n");
      out.write(" 			<td style=\"background-color:#E2F0D9\"><input type=\"text\" id=\"eur\" style=\"border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9\"></td>\r\n");
      out.write(" 		</tr>\r\n");
      out.write(" 		<tr>\r\n");
      out.write(" 			<td style=\"background-color:#C5E0B4;width:150px\">중국CNY</td>\r\n");
      out.write(" 			<td style=\"background-color:#E2F0D9\"><input type=\"text\" id=\"cny\" style=\"border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:#E2F0D9\"></td>\r\n");
      out.write(" 		</tr>\r\n");
      out.write(" 	</table>\r\n");
      out.write("\r\n");
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