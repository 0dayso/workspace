<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="tabContainer">
	<ul class="tabHead">
		<li tl='2' onclick="loadTitleContent('2');" class="${empty param.lx or param.lx eq '2' ? 'currentBtn' : ''}"><a style="text-decoration:none;">待审核</a></li>
		<li tl='3' onclick="loadTitleContent('3');" class="${param.lx eq '3' ? 'currentBtn' : ''}"><a style="text-decoration:none;">待销售办理</a></li>
		<li tl='4' onclick="loadTitleContent('4');" class="${param.lx eq '4' ? 'currentBtn' : ''}"><a style="text-decoration:none;">待采购办理</a></li>
		<li tl='5' onclick="loadTitleContent('5');" class="${param.lx eq '5' ? 'currentBtn' : ''}"><a style="text-decoration:none;">销售完/采购完</a></li>
		<li tl='1' onclick="loadTitleContent('1');" class="${param.lx eq '1' ? 'currentBtn' : ''}"><a style="text-decoration:none;">全部</a></li>
		<!--  <li tl='6' onclick="loadTitleContent('6');" class="${param.lx eq '6' ? 'currentBtn' : ''}"><a style="text-decoration:none;">取消座位</a></li>-->
	</ul>
</div>


