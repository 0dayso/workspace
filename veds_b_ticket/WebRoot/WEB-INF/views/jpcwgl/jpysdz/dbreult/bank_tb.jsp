<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<display:column title="发生金额" property="WD_FSJE" total="true" style="text-align:right;background-color:#00E000;color:#000000;" format="{0,number,#0.00}"/>
<display:column title="外部单号" property="WD_DDH"  style="text-align:center;"/>
<display:column title="代扣佣金" property="BY1"  style="text-align:right;" format="{0,number,#0.00}"/>
<display:column title="其他代扣" property="BY2"  style="text-align:right;" format="{0,number,#0.00}"/>
<display:column title="保险分润" property="BY3"  style="text-align:right;" format="{0,number,#0.00}"/>
<display:column title="机票票款" property="BY4"  style="text-align:right;" format="{0,number,#0.00}"/>
<display:column title="账务流水号" property="WD_ZWLSH"  style="text-align:left;"/>
<display:column title="业务流水号" property="WD_YWLSH"  style="text-align:left;"/>
<display:column title="商户订单号" property="WD_SHDDH" style="text-align:center;"/>
<display:column title="账务时间" property="WD_ZWSJ"  style="text-align:left;"/>
<display:column title="商品名称" property="WD_SPMC" style="text-align:left;"/>
<display:column title="业务类型" property="WD_YWLX" style="text-align:left;"/>
<display:column title="收入金额" property="WD_RZJE" style="text-align:right;" format="{0,number,#0.00}"/>
<display:column title="支出金额" property="WD_CZJE" style="text-align:right;" format="{0,number,#0.00}"/>
<display:column title="账户余额" property="WD_ZHYE" style="text-align:right;" format="{0,number,#0.00}"/>
<display:column title="备注" property="WD_BZ" style="text-align:left;" maxLength="20"/>
<display:column title="对方账号" property="WD_DFZH" style="text-align:left;"/>
