<%@page import="es.caib.carpeta.back.security.LoginInfo"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ page pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><c:if test="${loginInfo.needConfigUser}" >
  <%
   LoginInfo.getInstance().setNeedConfigUser(false);
  %>
  <c:redirect url="/common/usuario/edit/${loginInfo.usuariPersona.id}"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<c:out value="${pageContext.response.locale.language}"/>"  lang="<c:out value="${pageContext.response.locale.language}"/>">

<head>
<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
<%--  XYZ ZZZ ZZZ ICON  LOGO ENTITAT

 <c:if test="${not empty loginInfo.entitatID}" >
  <link href="<c:url value="${pfi:fileUrl(loginInfo.entitat.favicon)}"/>" rel="shortcut icon" type="image/x-icon" />
</c:if>

 --%>

</head>
<body>

  <!--  INICI CAPÇALERA -->
  
  <tiles:insertAttribute name="cap">
    <tiles:putAttribute name="data" value="${data}" />
    <tiles:putAttribute name="menu" value="${menu_tile}" />
  </tiles:insertAttribute>

  <!--  PIPELLES -->
  <div class="row-fluid container main" style="max-width: none;">

    <%-- INICI MENU + CONTINGUT --%>
    <div class="well well-white" style="padding:10px">
       <tiles:insertAttribute name="menu_i_contingut" >
            <tiles:putAttribute name="contingut" value="${contingut_tile}"  />
       </tiles:insertAttribute>
    </div>
    <%-- FINAL MENU + CONTINGUT --%>

  <%-- FINAL DIV PIPELLES --%>
  </div>

  <div class="container row-fluid">
    <tiles:insertAttribute name="peu">     
    </tiles:insertAttribute>
  </div>

</body>
</html>