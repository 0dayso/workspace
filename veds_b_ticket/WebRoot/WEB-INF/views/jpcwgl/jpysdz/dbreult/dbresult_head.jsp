<%@page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="tabContainer" style="width:99.6%;">
	<ul class="tabHead">
		<li lx='1' onclick="loadContent('1');" class="${empty param.jglx or param.jglx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">当日到账金额正确(${empty counts.C1 ? '0' : counts.C1})</a></li>
		<li lx='2' onclick="loadContent('2');" class="${param.jglx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">当日到账金额错误(${empty counts.C2 ? '0' : counts.C2})</a></li>
		<li lx='3' onclick="loadContent('3');" class="${param.jglx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">当日未到账(${empty counts.C3 ? '0' : counts.C3})</a></li>
		<li lx='4' onclick="loadContent('4');" class="${param.jglx eq '4' ? 'currentBtn' : ''}"><a style="text-decoration:none;">历史单当日到账金额正确(${empty counts.C4 ? '0' : counts.C4})</a></li>
		<li lx='5' onclick="loadContent('5');" class="${param.jglx eq '5' ? 'currentBtn' : ''}"><a style="text-decoration:none;">历史单当日到账金额错误(${empty counts.C5 ? '0' : counts.C5})</a></li>
		<li lx='6' onclick="loadContent('6');" class="${param.jglx eq '6' ? 'currentBtn' : ''}"><a style="text-decoration:none;">当日漏单(${empty counts.C6 ? '0' : counts.C6})</a></li>
		<li lx='7' onclick="loadContent('7');" class="${param.jglx eq '7' ? 'currentBtn' : ''}"><a style="text-decoration:none;">当日补单(${empty counts.C7 ? '0' : counts.C7})</a></li>
	</ul>
	<div class="clear"></div>
</div>