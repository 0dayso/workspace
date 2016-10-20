<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="list_table">
		<tr>
			<td colspan="4" class="title">${t_b2b_hkgsxx.hkgs eq null ? t_b2b_dlzh.hkgs : t_b2b_hkgsxx.hkgs}其他信息：</td>
		</tr>
		<c:if test="${bca eq '720102'}">
			<tr>
         		<td>是否开启自动挂票：</td>
         		<td>
					<input name="zdtss" id="zdtss" value="${t_b2b_hkgs.zdtss}" type="hidden">
					<input type="checkbox"  ${t_b2b_hkgs.zdtss eq '1' ? 'checked' : ''} onclick="checkFs(this,'zdtss');" />
					&nbsp;
					<span style="color: gray;font-size: 12">如果勾选了这个选项，出票完成后会根据“订单详”中“是否挂票”判断自动挂起客票。</span>
         		</td>
			</tr>

			<c:if test="${t_b2b_hkgsxx.hkgs eq 'CA' || t_b2b_hkgsxx.hkgs eq 'CZ' || t_b2b_hkgsxx.hkgs eq 'KY' || t_b2b_hkgsxx.hkgs eq 'MU' || t_b2b_hkgsxx.hkgs eq 'ZH' || t_b2b_hkgsxx.hkgs eq '3U' || t_b2b_hkgsxx.hkgs eq 'KN' || t_b2b_hkgsxx.hkgs eq 'GX' || t_b2b_hkgsxx.hkgs eq 'FU' }">
				<tr>
					<td width="20%">大客户编码：</td>
					<td><input type="text" name="dkhbm" id="dkhbm" value="${t_b2b_hkgs.dkhbm}" size="70" class="input1" /><br>
						多个大客户编号用小逗号(,)分隔，常用的一个放第一位,方便在政策显示页面选择。<br>
						小技巧：如果在政策页面只需要提供选择但不默认，可以在最前面输入小逗号。</td>
				</tr>
			</c:if>

			<c:if test="${t_b2b_hkgsxx.hkgs eq 'CA' || t_b2b_hkgsxx.hkgs eq 'MU' || t_b2b_hkgsxx.hkgs eq 'CZ'}">
				<!-- 包含：大客户号 -->
				<tr id="tr_zdmr" style="${t_b2b_hkgs.zdmr eq '0' ? 'display=block' : 'display=none' }">
					<td colspan="2"><font color="red">您已经设置为不自动默认配置,下列选项为非填项。</font>
					</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td><input type="text" id="lxdh" name="lxdh"
						value="${t_b2b_hkgs.lxdh}" class="input1" />
					</td>
				</tr>
			</c:if>
			<c:if test="${t_b2b_hkgsxx.hkgs eq 'HU'}">
				<!-- 包含：售票员代码、联系人、联系电话 -->
				<tr id="tr_zdmr" style="${t_b2b_hkgs.zdmr eq '0' ? 'display=block' : 'display=none' }">
					<td colspan="2"><font color="red">您已经设置为不自动默认配置,下列选项为非填项。</font>
					</td>
				</tr>
				<tr>
					<td>售票员代码：</td>
					<td><input type="text" name="spydm" value="${t_b2b_hkgs.spydm}" maxlength="30" class="input1" />
					</td>
				</tr>
				<tr>
					<td>联系人：</td>
					<td>
						<input type="text" name="lxr" value="${t_b2b_hkgs.lxr}" maxlength="30" class="input1 required" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td>
						<input type="text" name="lxdh" value="${t_b2b_hkgs.lxdh}" class="input1 required" />
						<font color="red">*</font>
					</td>
				</tr>
			</c:if>
			<c:if test="${t_b2b_hkgsxx.hkgs eq 'ZH' || t_b2b_hkgsxx.hkgs eq 'KY'}">
				<!-- 包含：协议客户号、大客户编码、姓名、固定电话、手机号、电子邮箱 -->
				<tr id="tr_zdmr" style="${t_b2b_hkgs.zdmr eq '0' ? 'display=block' : 'display=none' }">
					<td colspan="2">
						<font color="red">您已经设置为不自动默认配置,下列选项为非填项。</font>
					</td>
				</tr>
				<tr>
					<td>售票员代码：</td>
					<td>
						<input type="text" name="spydm" value="${t_b2b_hkgs.spydm}" maxlength="30" class="input1" />
					</td>
				</tr>
				<tr>
					<td>协议客户号：</td>
					<td>
						<input type="text" name="xykhh" value="${t_b2b_hkgs.xykhh}" maxlength="30" class="input1" />
					</td>
				</tr>
				<tr>
					<td>联系人：</td>
					<td>
						<input type="text" name="lxr" value="${t_b2b_hkgs.lxr}" maxlength="30" class="input1 required" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" value="${t_b2b_hkgs.lxdh}" class="input1" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>手机：</td>
					<td>
						<input type="text" id="sj" name="sj" value="${t_b2b_hkgs.sj}" maxlength="30" class="input1" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>电子邮箱：</td>
					<td>
						<input type="text" name="email" value="${t_b2b_hkgs.email}" maxlength="30" class="input1" />
					</td>
				</tr>
			</c:if>

			<c:if test="${fn:length(jpB2bDlhzList)>1}">
				<tr id="tr_mrwz">
					<td>默认网址：</td>
					<td>
						<c:forEach items="${jpB2bDlhzList}" var="dlhz">
							<input type="radio" name="mrwz" value="${dlhz.wz }" ${ t_b2b_hkgs.mrwz eq dlhz.wz ? 'checked' : ''}/>
							${dlhz.xlmc}(${dlhz.wz})
						</c:forEach></td>
				</tr>
			</c:if>

		</c:if>
		<c:if test="${bca eq '720104'}">
			<tr>
				<td>联系人：</td>
				<td>
					<input type="text" name="lxr" value="${t_b2b_hkgs.lxr}" maxlength="30" class="input1 required" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>手机：</td>
				<td>
					<input type="text" id="sj" name="sj" value="${t_b2b_hkgs.sj}" maxlength="130" class="input1" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>电子邮箱：</td>
				<td>
					<input type="text" name="email" value="${t_b2b_hkgs.email}" maxlength="30" class="input1" />
					<font color="red">*</font>
				</td>
			</tr>
		</c:if>

		<tr id="tr_mrwz">
			<td>是否使用代理：</td>
			<td><select name="sfsydl">
					<option value="0" ${t_b2b_hkgs.sfsydl eq '0' || emptyt_b2b_hkgs.sfsydl ? 'selected' : ''}>不使用代理</option>
					<option value="1" ${t_b2b_hkgs.sfsydl eq '1' ? 'selected' : '' }>使用下面填写的值</option>
					<option value="2" ${t_b2b_hkgs.sfsydl eq '2' ? 'selected' : '' }>自动分配代理</option>
			</select> 主要正对航空公司网站被封了的情况</td>
		</tr>
		<tr id="tr_mrwz">
			<td>代理IP端口：</td>
			<td>
				<input type="text" value="${ t_b2b_hkgs.dlipport}" name="dlipport" class="input1">
				当上面选择【使用下面填写的值】时此处需要输入IP:PORT
			</td>
		</tr>


		<tr>
			<td colspan="2" align="center">
				<input type="button" class="ext_btn ext_btn_submit" value="保存" onclick="toSave();" />
			</td>
		</tr>
	</table>
</form>
