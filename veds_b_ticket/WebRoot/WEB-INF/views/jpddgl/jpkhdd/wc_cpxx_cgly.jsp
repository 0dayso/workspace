<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<c:choose>
  <c:when test="${param.cgly eq 'BSP' }"><%@include file="wccgly/bsp.jsp" %></c:when>
  <c:when test="${param.cgly eq 'BOP' }"><%@include file="wccgly/bop.jsp" %></c:when>
  <c:when test="${param.cgly eq 'OP' }"><%@include file="wccgly/op.jsp" %></c:when>
  <c:when test="${param.cgly eq 'B2C' }"><%@include file="wccgly/b2c.jsp" %></c:when>
  <c:when test="${param.cgly eq 'GP' }"><%@include file="wccgly/gp.jsp" %></c:when>
  <c:otherwise><%@include file="wccgly/other.jsp" %></c:otherwise>
</c:choose>