<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<table class="t_tab" cellpadding="0" cellspacing="0" align="center" style="width: 100%;margin-top: 1px;" id="skInfo">
	<tr>
	    <td style="background:#efefef;width:100px;height:80px; border:1px #efefef solid;text-align:center;">
	    	<img src="${ctx}/static/images/wdimages/skxx.png" /><br />
	    	<span style="font-size:14px; font-weight:bold; color:#1195db">收款信息</span>
	    </td>
	    <td style="background:#f1f1f1;">
			<table class="form_table pt15 pb15"  border="0" cellpadding="0" cellspacing="0" style="width:100%;">
			   <tr>
				    <th class="gqd_left">
				    	应收金额：
				    	<span style="color:red">￥</span>
				    	<span id="ysje" style="color:red">0</span>
				    </th>
				    <th class="gqd_left">
				    	收款状态：
				    	<select name="skzt" class="select" datatype="*" nullmsg="请选择值">
				   		 	<option value="">==请选择==</option>
				   		 	<option value="0" ${jpgqd.skzt eq '0' ? 'selected' : ''}>未收款</option>
				   		 	<option value="1" ${jpgqd.skzt eq '1' ? 'selected' : ''}>已收款</option>
				   		</select>
				   		<span style="color:red"> * </span>
					</th>
				    <th class="gqd_left">
				   		 收款科目：
				   		 <select name="skkm" class="select" datatype="*" nullmsg="请选择值">
				   		 	<option value="">==请选择==</option>
				   		 	<c:forEach items="${zfkmList}" var="zfkm"> 
					   		 	<option value="${zfkm.kmbh}" ${jpgqd.gqCgkm eq zfkm.kmbh ? 'selected' : ''}>${zfkm.kmmc}</option>
					   		</c:forEach>
				   		 </select>
				   		 <span style="color:red"> * </span>
				    </th>
				</tr>
				<tr style="width:100%;height:10px;">
				</tr>
				<tr>
				    <th class="gqd_left">
				 		联系人：<input name="nxr" value="${jpgqd.nxr}" class="input-text" style="height: 20px;width:100px" datatype="*" nullmsg="请填写值"/><span style="color:red"> * </span>
				    </th>
				    <th class="gqd_left">
				  		  联系电话：<input id="dh" name="nxdh" value="${jpgqd.nxdh}" class="input-text" style="height: 20px;width:100px"/>
				    </th>
				    <th class="gqd_left">
				   		 手机：<input id="sj" name="nxsj" value="${jpgqd.nxsj}" class="input-text" style="height: 20px;width:100px"/>&nbsp;&nbsp;
				   		<span style="color:red">电话与手机必须选填一项</span>
				    </th>
			   </tr> 
			</table> 
	    </td>
	</tr>
</table>
