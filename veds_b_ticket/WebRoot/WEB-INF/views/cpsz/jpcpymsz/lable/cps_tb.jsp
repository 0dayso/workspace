<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<!-- CPS-TB -->
<input type="hidden" name="sfkq"  value="1" >
<table border="0" cellpadding="0" cellspacing="0" class="list_table">
	<tr>
		<td>
			过滤产品展示:<input type="text" id="cgGlCp" class="input1" name="cgGlCp"  value="${cpymsz.cgGlCp}"/>
			<br><font color="gray">注:询价时不显示出来的产品（QW,JX,JP,HS,KUFEI），多个用‘/’分隔。<br>
			编号说明：QW 全网最低价,JX 精选产品,JP 金牌产品,HS 航司产品,KUFEI 酷飞产品。</font>
		</td>
	</tr>
	<tr>
		<td>
			过滤淘宝店铺:<input type="text" id="tbGlDp" class="input1" name="tbGlDp"  value="${cpymsz.tbGlDp}"/>
			<br><font color="gray">注:用于询价时过滤的店铺名称，以免采购自家政策（全称或者店铺旺旺昵称），多个用‘/’分隔。</font>
		</td>
	</tr>
	<tr>
		<td>
			设置联系人信息:
			联系人<input type="text" id="lxr" class="input1"  onblur="linkNxr();" value="${fn:split(cpymsz.tbNxr,'||')[0]}"/>
			手机<input type="text" id="lxrsj" class="input1"   onblur="linkNxr();" value="${fn:split(cpymsz.tbNxr,'||')[1] == ' ' ? '' : fn:split(cpymsz.tbNxr,'||')[1]}"/>
			Email<input type="text" id="lxremail" class="input1"  onblur="linkNxr();"  value="${fn:split(cpymsz.tbNxr,'||')[2]}"/>
			<input type="hidden" id="tbNxr" class="input1" name="tbNxr" value="${cpymsz.tbNxr}"/>
			<br><font color="gray">不设置的话将取订单上联系人信息。</font>
		</td>
	</tr>
	<tr>
		<td>
			是否开启自动XEPNR:<input type="radio" name="cgXepnr" value="1" ${cpymsz.cgXepnr eq '1' ? 'checked' : ''}>开启
			<input type="radio" name="cgXepnr" value="0" ${cpymsz.cgXepnr ne '1' ? 'checked' : ''} >不开启
			XE用户编号<input type="text" id="cgXeYhbh" class="input1" name="cgXeYhbh" size="15" value="${cpymsz.cgXeYhbh}"/>
		</td>
	</tr>
	<tr>
		<td>
			是否开启全自动淘宝代购时自动分离订单:<input type="radio" name="tbSp" value="1" ${cpymsz.tbSp eq '1' ? 'checked' : ''}>开启
			<input type="radio" name="tbSp" value="0" ${cpymsz.tbSp ne '1' ? 'checked' : ''} >不开启
			<br><font color="gray">余票不足或者设置的航司订单最大人数限制时，自动分离订单</font>
		</td>
	</tr>
	<tr>
		<td>
			淘宝代购出票方式:<input type="radio" name="tbCpfs" value="0" ${empty cpymsz.tbCpfs or cpymsz.tbCpfs eq '0' ? 'checked' : ''}>换编码出票
			<input type="radio" name="tbCpfs" value="1" ${cpymsz.tbCpfs eq '1' ? 'checked' : ''} >原编码出票
			<input type="radio" name="tbCpfs" value="2" ${cpymsz.tbCpfs eq '2' ? 'checked' : ''} >先换编码、后原编码出票
		</td>
	</tr>
	<tr>
		<td>
			CPSLINK是否带舱位询价：
			<input type="radio" name="tbXj" value="0" ${empty cpymsz.tbXj or cpymsz.tbXj eq '0' ? 'checked' : ''} >带舱位询价
			<input type="radio" name="tbXj" value="1" ${cpymsz.tbXj eq '1' ? 'checked' : ''} >不带舱位询价
		</td>
	</tr>
	<!--
	<tr>
		<td>
			淘宝余额不足，点击<input type="button" value="解除限制" class="ext_btn ext_btn_submit" onclick="tbunfreeze()">解除余额不足限制状态
		</td>
	</tr>
	  -->
	<tr>
		<td colspan="2"  height="30" style="text-align: center;">
			<input type="button" value=" 保 存 "   class="ext_btn ext_btn_submit" onclick="toSave();">
			<input type="button" value=" 关 闭 "   class="ext_btn ext_btn_submit" onclick="closeLayer();">
			
		</td>
	</tr>
</table>