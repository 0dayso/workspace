<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	<thead>
		<th colspan="11" align="left">
			<span style="font-size:16px;color: #0858c7">已经添加的支付账号信息</span>
		</th>
	</thead>
	<thead>
		<th style="width: 5%;">序号</th>
		<th style="width: 8%;">操作</th>
		<th style="width: 10%;">账号</th>
		<c:if test="${param.zflx eq '3' || param.zflx eq '4' || param.zflx eq '6' || param.zflx eq '9'}">
			<th style="width: 13%;">支付类型</th>
		</c:if>
		<c:if test="${param.zflx eq '1' || param.zflx eq '2' || param.zflx eq '7' }">
			<th style="width: 10%;">操作员<br>子账号</th>
			<th style="width: 8%;">是否<br>使用子账号</th>
		</c:if>
		<c:if test="${param.zflx eq '7' }">
			<th style="width: 10%;">密钥</th>
		</c:if>
		<c:if test="${param.zflx eq '1' || param.zflx eq '2' || param.zflx eq '6' || param.zflx eq '7' || param.zflx eq '8' || param.zflx eq '9'}">
			<th style="width: 8%;">是否签约</th>
			<th style="width: 8%;">签约信息</th>
			<th style="width: 12%;">签约操作</th>
			<th style="width: 8%;">本账号<br>对应支付科目</th>
		</c:if>
        <th style="width: 12%;">操作人</th>
        <th style="width: 10%;">状态</th>
	</thead>
	<tbody>
       	<c:if test="${not empty zfzhList && fn:length(zfzhList)>0 }">
        	<c:forEach items="${zfzhList}" var="zfzh" varStatus="sta">
        		<tr>
        			<td class="td_center">${sta.index+1 }</td>
        			<td class="td_center">
						<a href="${ctx}/vedsb/b2bsz/jpb2bzfzh/viewlist?id=${zfzh.id }&zfzh=${zfzh.zfzh }&sfsyzzh=${zfzh.sfsyzzh }&zflx=${zfzh.zflx }&zfzzh=${zfzh.zfzzh }&zfxx7=${zfzh.zfxx7 }">编辑</a>  
 						<a href="javascript:toDel('${zfzh.id }')">删除</a>
 						<c:if test="${zfzh.sfkq eq '1'}">
	                   		<a href="javascript:toUpdate('${zfzh.id }','0')">停用</a>
	                   	</c:if>
	                   	<c:if test="${zfzh.sfkq ne '1'}">
	                   		<a href="javascript:toUpdate('${zfzh.id }','1')">启用</a>
	                   	</c:if>
					</td>
					<td class="td_left">${zfzh.zfzh }</td>
					<c:if test="${param.zflx eq '3' || param.zflx eq '4' || param.zflx eq '6' || param.zflx eq '9'}">
						<td class="td_center">${zfzh.zfxx1Name }</td>
					</c:if>
					<c:if test="${param.zflx eq '1' || param.zflx eq '2' || param.zflx eq '7' }">
						<td class="td_left">${zfzh.zfzzh }</td>
						<td class="td_center">
							<c:if test="${not empty zfzh.zfzzh }">
								${zfzh.sfsyzzh eq '0' ? '不使用' : ''}${zfzh.sfsyzzh eq '1' ? '使用' : ''}
							</c:if>
						</td>
					</c:if>
					<c:if test="${param.zflx eq '7' }">
						<td class="td_left">
							账号：${fn:substring(zfzh.zfxx2,0,4) }...
							<c:if test="${not empty zfzh.zfzzh}"> 
								<br>操作员：${fn:substring(zfzh.zfxx3,0,4) }...
							</c:if>
						</td>
					</c:if>
					<c:if test="${param.zflx eq '1' || param.zflx eq '2' || param.zflx eq '6' || param.zflx eq '7' || param.zflx eq '8' || param.zflx eq '9'}">
						<td class="td_center">${zfzh.sfqyName }</td>
						<td class="td_center">${zfzh.zfxx3 }</td>
						<td class="td_center">
	                     	<a href="##" onclick="changeQY('${zfzh.id}','${zfzh.zflx}')">修改(更新)签约信息</a>
						</td>
						<td class="td_center">${zfzh.ex.ZFXX7.kmmc }</td>
					</c:if>
					<td class="td_left">${zfzh.yhbh }<br>${zfzh.cjtime }</td>
					<td class="td_center">
						<c:if test="${zfzh.sfkq eq '1'}">
	                   		<font color="green">可以使用</font>
	                   	</c:if>
	                   	<c:if test="${zfzh.sfkq ne '1'}">
	                   		<font color="red">不可使用</font>
	                   	</c:if>
					</td>
        		</tr>
        	</c:forEach>
        </c:if>
        <c:if test="${empty zfzhList}">
        	<tr>
        		<td class="td_center" colspan="9">
        			<span style="font-size:14px;color: red;">没有记录</span>
				</td>
        	</tr>
        </c:if>
	</tbody>
</table>