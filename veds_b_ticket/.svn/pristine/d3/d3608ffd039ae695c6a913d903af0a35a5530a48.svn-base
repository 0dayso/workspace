<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<html>
	<head>
		<title>新增采购异常</title>
		<script type="text/javascript">
			function save(n,ddbh){
				if($("#key").val() == '' || $("#key").val() == null){
					$("#cgly").val($("#cgly_"+n).val());
					$("#yclx").val($("#yclx_"+n).val());
					$("#cgje").val($("#cgje_"+n).val());
					$("#ycddbh").val(ddbh);
					$("#ycsm").val($("#ycsm_"+n).val());
					$("#zfkm").val($("#zfkm_"+n).val());
					$("#ycsm").val($("#ycsm_"+n).val());
				}else{
					$("#zfkm").val($("#zfkms").val());
					$("#cgly").val($("#cglys").val());
					$("#yclx").val($("#yclxs").val());
					$("#ycsm").val($("#ycsms").val());
					$("#cgje").val($("#cgjes").val());
				}
				if($("#cgje").val() == null || $("#cgje").val() == ''){
					layer.msg('采购金额不能为空!',2,3);
					return;
				}else if($("#zfkm").val() == null || $("#zfkm").val() == ''){
					layer.msg('采购科目不能为空!',2,3);
					return;
				}else if($("#cgly").val() == null || $("#cgly").val() == ''){
					layer.msg('采购来源不能为空!',2,3);
					return;
				}else if($("#yclx").val() == null || $("#yclx").val() == ''){
					layer.msg('异常类型不能为空!',2,3);
					return;
				}else if($("#ycsm").val() == null || $("#ycsm").val() == ''){
					layer.msg('异常说明不能为空!',2,3);
					return;
				}
				$("#cyForm").submit();
			}
			
			function addsearch(){
				if(($("#pnr").val() == null || $("#pnr").val() == '') && ($("#ddbh").val() == null || $("#ddbh").val() == '')){
					layer.msg('至少输入一个查询条件!',2,3);
					return;
				}
				$("#addsearchForm").submit();
			}
		</script>
	</head>
	<body>
		<form action="${ctx}/vedsb/cgdzbb/jpcgdzyc/addcgycSearch" name="pnrForm" method="post" id="addsearchForm">
			<table border="0" cellpadding="0" cellspacing="0" class="list_table">
			  <tr>
				<td style="width: 50px;text-align: right;">P N R</td> 
				<td style="width: 80px">
				  <input type="text" id="pnr" name="pnrno" size="10" value="${param.pnrno}" onblur="value=$.trim(this.value).toUpperCase();" >
				</td>
				<td style="width: 80px;text-align: right;">订单编号</td> 
				<td style="width: 80px">
				  <input type="text" id="ddbh" name="ddbh"  value="${param.ddbh}"/>
				</td>
			    <td align="left" >
			     <input type="button" class="ext_btn ext_btn_submit" value="查询" onclick="addsearch()">
			    </td>
			  </tr>
			</table>
		</form>
			<table border="0" cellpadding="0" cellspacing="0" class="list_table">
				<tr>
					<th style="text-align: center;">订单编号</th>
					<th style="text-align: center;">PNR</th>
					<th style="text-align: center;">票号</th>
					<th style="text-align: center;">乘机人</th>
					<th style="text-align: center;">航程</th>
					<th style="text-align: center;">采购</br>金额</th>
					<th style="text-align: center;">采购科目</th>
					<th style="text-align: center;">采购来源</th>
					<th style="text-align: center;">采购流水号</th>
					<th style="text-align: center;width: 50px">出票时间</th>
					<th style="text-align: center;">异常类型</th>
					<th style="text-align: center;">异常说明</th>
					<th style="text-align: center;">操作</th>
				</tr>
				<c:if test="${flag ne '1'}">
				<c:forEach items="${list}" var="v" varStatus="s">
					<tr>
						<td>${v.DDBH}</td>
						<td>${v.CG_PNR_NO}</td>
						<td>${v.TKNO}</td>
						<td>${v.CJR}</td>
						<td>${v.HC}</td>
						<td><input type="text" id="cgje_${s.index}" value="${v.CG_PJ}"/></td>
						<td>
							<select id="zfkm_${s.index}">
								<option value="">==选择科目==</option>
								<c:forEach items="${zfkmlist}" var="zf">
								<option value="${zf.kmbh}">${zf.kmmc}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select id="cgly_${s.index}">
								<option value="">==所有类型==</option>
								<c:forEach items="${vfc:getVeclassLb('10014')}" var="cplxs">
								<option value="${cplxs.mc}">${cplxs.mc}</option>
								</c:forEach>
							</select>
						</td>
						<td></td>
						<td>${v.CP_DATETIME }</td>
						<td>
							<select id="yclx_${s.index}">
								<option value="">==异常类型==</option>
								<option value="2251214">重复出票</option>
							</select>
						</td>
						<td>
							<input type="text" name="ycsm" id="ycsm_${s.index}"/>
						</td>
						<td>
					
							<input type="button" class="ext_btn ext_btn_success" value="保存" onclick="save('${s.index}','${v.DDBH}')">
						</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${flag eq '1'}">
				<tr>
					<td>${cyb.ddbh}</td>
					<td>${cyb.pnrno}</td>
					<td>${cyb.tkno}</td>
					<td>${cyb.cjr}</td>
					<td>${cyb.hc}</td>
					<td><input type="text" id="cgjes" value="${cyb.zfjeAsms}"/></td>
					<td>
						<select id="zfkms">
							<option value="">==选择科目==</option>
							<c:forEach items="${zfkmlist}" var="zf">
								<option value="${zf.kmbh}" ${zf.kmbh eq cyb.cgZfkm ? 'selected' : '' }>${zf.kmmc}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select id="cglys">
							<option value="">==所有类型==</option>
							<c:forEach items="${vfc:getVeclassLb('10014')}" var="cplx">
								<option value="${cplx.mc}" ${cyb.cplx eq cplx.mc ? 'selected' : ''}>${cplx.mc}</option>
							</c:forEach>
						</select>
					</td>
					<td></td>
					<td><p>${vfn:format(cyb.cpDatetime,'yyyy-MM-dd HH:mm:ss')}</p></td>
					<td>
						<select id="yclxs">
							<option value="">==异常类型==</option>
							<option value="2251214">重复出票</option>
						</select>
					</td>
					<td>
						<input type="text" value="${cyb.by3}" id="ycsms"/>
					</td>
					<td>
						<input type="button" class="ext_btn ext_btn_success" value="保存" onclick="save('','')">
					</td>
				</tr>
				</c:if>
			</table>
			<form action="${ctx}/vedsb/cgdzbb/jpcgdzyc/addcgdzSave" name="saveForm" method="post" id="cyForm">
				<input type="hidden" name="callback"  value="parent.refresh()" />
	            <input type="hidden" name="close" value="true"/>
				<input type="hidden" name="cgly" id="cgly"/><!-- 采购来源 -->
				<input type="hidden" name="yclx" id="yclx"/><!-- 异常类型 -->
				<input type="hidden" name="cgje" id="cgje"/><!-- 采购金额 -->
				<input type="hidden" name="ddbh" id="ycddbh"><!-- 订单编号 -->
				<input type="hidden" name="ycsm" id="ycsm"/><!-- 异常说明 -->
				<input type="hidden" name="zfkm" id="zfkm"/><!-- 采购科目 -->
				<input type="hidden" name="id" value="${cyb.id}" id="key"/><!-- 异常说明 -->
			</form>
	</body>
</html>