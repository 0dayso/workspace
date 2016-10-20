<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<style>

.cpkz{border:1px solid #d5d5d5;text-align:center; border-collapse:collapse}
.cpkz th{ width:20px;background:#e9f2fe;padding:2px; color:#0843a5;}
.cpkz td{border:1px solid #d5d5d5; }
.cpkz .title{ background:#f7f7f7;font-weight:bold;color:#000; height: 20px;}
.cpkz .title2{background:#FFFFFF url(../img/tabtitle.gif) 0 0 repeat-x;color:#000;text-align: center}
.cpkz a:link{color:#0433a0;text-decoration: none}
.cpkz a:visited{text-decoration: none}
.cpkz a{color:#0433a0}
.cpkz a.y:link,a.y:visited{color:#cc3e00; background:#FFFFFF url(../img/arrr.gif) 3px 2px no-repeat;padding:0 2px 0 10px; text-decoration:none}
.cpkz a.y:hover{color:#cc3e00;}
</style>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="cpkz">
	<tr class="title">
		<td style="width:30px;" class="title2"></td>
		<td style="text-align: center;${empty param.yw_type or param.yw_type eq 'WBL' ? 'background:#f3f7fd;' : ''}">
			<a href="javascript:setCgdw('WBL','','')" class="${empty param.yw_type or param.yw_type eq 'WBL' ? 'y' : ''}">
				待办理(<span class="red">${empty totalMap.WBL ? 0 : totalMap.WBL}</span>)
			</a>
		</td>
		<td style="text-align: center;${param.yw_type eq 'a1' ? 'background:#f3f7fd;' : ''}">
			<a href="javascript:setCgdw('YTJ','','')" class="${param.yw_type eq 'YTJ' ? 'y' : ''}">
				已提交未完成(<span class="red">${empty totalMap.YTJ ? 0 : totalMap.YTJ}</span>)
			</a>
		</td>
		<td style="text-align: center;${param.yw_type eq 'e1' ? 'background:#f3f7fd;' : ''}">
			<a href="javascript:setCgdw('YBL','','')" class="${param.yw_type eq 'YBL' ? 'y' : ''}">
				已办理完成(<span class="red">${empty totalMap.YBL ? 0 : totalMap.YBL}</span>)
			</a>
		</td>
		<c:if test="${cs7551 eq '1' or cs7551 eq '2' or cs7551 eq '3' or cs7551 eq '4'}">
			<td style="text-align: center;width:40px;padding: 0 0;${param.yw_type eq 'f1' ? 'background:#f3f7fd;' : ''}" rowspan="5" >
				<a href="javascript:setBllx('f1')" class="${param.yw_type eq 'f1' ? 'y' : ''}" title="开启分销申请退废时自动取消座位功能后未正常取消数据">
					取消<br>座位<br>失败<br>(<span class="red">${empty cslist[0].F1 ? '0' : cslist[0].F1}</span>)
				</a>
			</td>
		</c:if>
		<!--  
		<td style="text-align: center;width:40px;padding: 0 0;${param.yw_type eq 'f1' ? 'background:#f3f7fd;' : ''}" rowspan="5">
			<a href="###" class="${param.yw_type eq 'yw' ? 'y' : '' }" title="延误/取消的退票订单" onclick="setBllx('yw')">延误<br/>/取消(<span class="red">${empty cslist[0].YW ? '0' : cslist[0].YW}</span>)</a>
		</td>
		-->
	</tr>
	
	<c:forEach items="${vfc:getVeclassLb('10014')}" var="oneLx">
		<c:forEach items="${cglyMap}" var="one">
			<c:if test="${oneLx.id ne '10014' and oneLx.ywmc eq one.CGLY}">
				<tr class="title">
				    <td style="width:30px;" class="title2">${one.CGLY}</td>
				    <td style="text-align:left;padding-left: 5px;${empty param.yw_type or param.yw_type eq 'WBL' ? 'background:#f3f7fd;' : ''}">
					    <c:if test="${not empty one.WBL and one.WBL ne 0}">
				            <a href="javascript:setCgdw('WBL','${one.CGLY}','${one.CGDW}')" class="${param.yw_type eq 'WBL' and  param.cgdw eq one.CGDW ? 'y' : ''}">
								${one.CGDW}(<span class="red">${one.WBL}</span>)
							</a>
					    </c:if>
					</td>	
					<td style="text-align:left;padding-left: 5px;${empty param.yw_type or param.yw_type eq 'YTJ' ? 'background:#f3f7fd;' : ''}">	
					    <c:if test="${not empty one.YTJ and one.YTJ ne 0}">
						           <a href="javascript:setCgdw('YTJ','${one.CGLY}','${one.CGDW}')" class="${param.yw_type eq 'YTJ'  and param.cgdw eq one.CGDW ? 'y' : ''}">
										${one.CGDW}(<span class="red">${one.YTJ}</span>)
								  </a>
					    </c:if>
					</td>	
					<td style="text-align:left;padding-left: 5px;${empty param.yw_type or param.yw_type eq 'YBL' ? 'background:#f3f7fd;' : ''}">
					    <c:if test="${not empty one.YBL and one.YBL ne 0}">
						        <a href="javascript:setCgdw('YBL','${one.CGLY}','${one.CGDW}')" class="${param.yw_type eq 'YBL'  and param.cgdw eq one.CGDW ? 'y' : ''}">
										${one.CGDW}(<span class="red">${one.YBL}</span>)
								</a>
					    </c:if>
				   </td>
		      </tr>
		    </c:if>	
	   </c:forEach>
	</c:forEach>
</table>