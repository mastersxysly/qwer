/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.22
 * Generated at: 2019-08-28 03:21:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = null;
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

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP 只允许 GET、POST 或 HEAD。Jasper 还允许 OPTIONS");
        return;
      }
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>注册界面</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-3.3.1.min.js\"></script>\r\n");
      out.write("<!-- jqeury 表单提交  -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"#register\").click(function(){\r\n");
      out.write("\t\t\tvar username = $(\"#user_name\").val();\r\n");
      out.write("\t\t\tif(username == \"\" || username == undefined || username == null){\r\n");
      out.write("\t\t\t\talert(\"用户姓名不能空！\");\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar password_One = $(\"#password_One\").val();\r\n");
      out.write("\t\t\tvar password_Two = $(\"#password_Two\").val();\r\n");
      out.write("\t\t\tif(password_One == \"\" || password_One == undefined || password_One == null){\r\n");
      out.write("\t\t\t\talert(\"用户密码不能空！\");\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(password_One != password_Two){\r\n");
      out.write("\t\t\t\talert(\"两次密码不一致！\");\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$(\"#register\").click(function(){\r\n");
      out.write("\t\t\t\t$(\"#register_form\").submit();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#reset_btn\").click(function(){\r\n");
      out.write("\t\t\t$(\"#register_form\")[0].reset();//因为$(\"#id/.class\")得到的是一个数组\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t})\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<!-- ajax提交 -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(document).ready(function(){\r\n");
      out.write("\t\t//var psd1 = $(\"#password_One\").val();\r\n");
      out.write("\t\t//var psd2 = $(\"#password_Two\").val();\r\n");
      out.write("\t\t$(\"#user_name\").blur(function(){\r\n");
      out.write("\t\t\tif($(\"#user_name\").val() == \"\"){\r\n");
      out.write("\t\t\t\t$(\"#warn_u\").html(\"用户名不能为空\");\r\n");
      out.write("\t\t\t\t$(\"#warn_u\").css(\"color\",\"red\");\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t var user = {username:$(\"#user_name\").val()};\r\n");
      out.write("\t\t\t  $.ajax(\r\n");
      out.write("\t\t\t   {url:\"ajax.do\",\r\n");
      out.write("\t\t\t   data:user,\r\n");
      out.write("\t\t\t   async:true,\r\n");
      out.write("\t\t\t   type:\"POST\",\r\n");
      out.write("\t\t\t   dataType:\"html\",\r\n");
      out.write("\t\t\t   success:function(result){\r\n");
      out.write("\t\t\t\t\t $(\"#warn_u\").html(result);\r\n");
      out.write("\t\t\t\t\t if(result == \"您的用户名已经注册\"){\r\n");
      out.write("\t\t\t\t\t\t $(\"#warn_u\").css(\"color\",\"red\");\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t }else{\r\n");
      out.write("\t\t\t\t\t\t $(\"#warn_u\").css(\"color\",\"blue\");\r\n");
      out.write("\t\t\t\t\t }}  \r\n");
      out.write("\t\t        }); \r\n");
      out.write("\t\t\t}//else\r\n");
      out.write("        }); \r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div>\r\n");
      out.write("        <br/>\r\n");
      out.write("        <br/>\r\n");
      out.write("        <center><h1>用户信息注册</h1></center><br/>\r\n");
      out.write("    <br/><br/>\r\n");
      out.write("    <form action=\"register.do\" method=\"POST\" id=\"register_form\">\r\n");
      out.write("        \t用户账号:<input  id=\"user_name\" type=\"text\" name=\"username\" /><div id=\"warn_u\"></div><br/>\r\n");
      out.write("    \t\t用户密码:<input  id=\"password_One\" type=\"password\" name=\"password\" /><div id=\"warn_p1\"></div><br/>\r\n");
      out.write("   \t \t\t确认密码:<input  id=\"password_Two\" type=\"password\" name=\"passwordTwo\" /><div id=\"warn_p2\"></div><br>\r\n");
      out.write(" \t\t\t\t  <input  id=\"register\"  style=\"width: 200px;color: #fff;background: #999;\" type=\"button\" value=\"立即注册\"/>\r\n");
      out.write(" \t\t\t\t  <input id=\"reset_btn\"  type=\"button\" name=\"btn2\" value='重置' />\r\n");
      out.write("    </form>\r\n");
      out.write("    </div>\r\n");
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
