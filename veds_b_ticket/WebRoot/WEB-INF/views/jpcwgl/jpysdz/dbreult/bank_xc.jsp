<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
	<display:column title="发生金额" property="WD_FSJE" total="true" style="text-align:right;background-color:#00E000;color:#000000" format="{0,number,#0.00}"/>
	<display:column title="外部单号" property="WD_DDH"  style="text-align:center"/>
	<display:column title="账户余额" property="WD_ZHYE"  style="text-align:right" format="{0,number,#0.00}"/>
	<display:column title="产品类型" property="WD_CPLX"  style="text-align:left"/>
	<display:column title="审核时间" property="WD_SHSJ" style="text-align:center"/>
	<display:column title="操作类型" property="WD_CZLX" style="text-align:left"/>
	<display:column title="票号" property="WD_TKNO" style="text-align:left"/>
	<display:column title="款项类型" property="WD_KXLX" style="text-align:left"/>
	<display:column title="冻结时间" property="WD_DJSJ" style="text-align:left"/>
	<display:column title="乘机人" property="WD_CJR" style="text-align:left"/>
	<display:column title="出票日期" property="WD_CPRQ" style="text-align:left"/>
	<display:column title="款项金额" property="WD_KXJE" style="text-align:right"/>
	<display:column title="金额备注" property="WD_JEBZ" style="text-align:left"/>
	<display:column title="结算周期" property="WD_JSZQ" style="text-align:left"/>
	<display:column title="批次号" property="WD_PCH" style="text-align:left"/>
