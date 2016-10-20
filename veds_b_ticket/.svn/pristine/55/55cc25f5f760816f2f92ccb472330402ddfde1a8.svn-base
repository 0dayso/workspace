package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.List;
import java.util.Map;
/**
 * 写订单的bean
 * @author 章磊
 *
 */
public class OrderBean {

	private List<Map<String, String>> cjrList;
	private List<Map<String, String>> hdList;
	private Map<String, String> khddMap;
	/**
	 * 用来入库前修改扩展表
	 */
	private Map<String,String> khddKzMap;
	/**
	 * 乘机人表信息
	 * @return
	 */
	public List<Map<String, String>> getCjrList() {
		return cjrList;
	}
	
	public void setCjrList(List<Map<String, String>> cjrList) {
		this.cjrList = cjrList;
	}
	public List<Map<String, String>> getHdList() {
		return hdList;
	}
	/**
	 * 航段表信息
	 * @param hdList
	 */
	public void setHdList(List<Map<String, String>> hdList) {
		this.hdList = hdList;
	}
	/**
	 * 订单表信息
	 * @return
	 */
	public Map<String, String> getKhddMap() {
		return khddMap;
	}
	public void setKhddMap(Map<String, String> khddMap) {
		this.khddMap = khddMap;
	}

	public Map<String, String> getKhddKzMap() {
		return khddKzMap;
	}

	public void setKhddKzMap(Map<String, String> khddKzMap) {
		this.khddKzMap = khddKzMap;
	}
	/**
	<PNR>
	<KHDD>
	<SHBH>商户编号</SHBH>
	<WBDH>外部订单编号</WBDH>
	<JJD>紧急度</JJD>
	<NOSX>NO位时限</NOSX>
	<SKKM>收款科目</SKKM>
	<NXR>联系人</NXR>
	<NXSJ>联系手机</NXSJ>
	<NXDH>联系电话</NXDH>
	<DDYH>导单用户</DDYH>
	<XS_PNR_NO>销售PNR_NO</XS_PNR_NO>
	<XS_PNR_ZT>销售PNR状态</XS_PNR_ZT>
	<XS_HKGS_PNR>销售航司大编码</XS_HKGS_PNR>
	<CFRQ>出发日期</CFRQ>
	<CFSJ>出发时间</CFSJ>
	<XS_HBH>销售航班号</XS_HBH>
	<XS_CW>销售舱位</XS_CW>
	<XS_PJ>销售价</XS_PJ>
	<XS_JSF>销售机建</XS_JSF>
	<XS_TAX>销售税费</XS_TAX>
	<XS_BXFS>销售保险份数</XS_BXFS>
	<XS_BXLR>销售保险利润</XS_BXLR>--此处需要另外加两个字段保险份数与利润
	<XS_QDF>销售快递费</XS_QDF>
	<XS_JE>销售金额</XS_JE>
	<CG_PNR_NO>采购PNR_NO</CG_PNR_NO>
	<CG_PNR_ZT>采购PNR状态</CG_PNR_ZT>
	<CG_HKGS_PNR>采购航司大编码</CG_HKGS_PNR>
	<CG_HBH>采购航班号</CG_HBH>
	<CG_CW>采购舱位</CG_CW>
	<CG_ZDJ>采购账单价</CG_ZDJ>
	<CG_PJ>采购价</CG_PJ>
	<CG_JSF>采购机建</CG_JSF>
	<CG_TAX>采购税费</CG_TAX>
	<CGLY>采购来源</CGLY>
	<CGDW>采购单位</CGDW>
	<CGZT>采购状态</CGZT>
	<CG_DDBH>采购订单编号</CG_DDBH>
	<CGKM>采购科目</CGKM>
	</KHDD>
	<CJRDATA>
	<SXH>顺序号</SXH>
	<CJRLX>乘机人类型</CJRLX>
	<CJR>乘机人</CJR>
	<ZJLX>证件类型</ZJLX>
	<ZJHM>证件号码</ZJHM>
	<SJHM>手机号码</SJHM>
	<XS_PJ>销售价</XS_PJ>
	<XS_JSF>销售机建</XS_JSF>
	<XS_TAX>销售税费</XS_TAX>
	<XS_BXFS>销售保险份数</XS_BXFS>
	<XS_BXLR>销售保险利润</XS_BXLR>--增加航意险的份数与利润字段
	<XS_QDF>销售快递费</XS_QDF>
	<XS_JE>销售金额</XS_JE>
	<CG_ZDJ>采购账单价</CG_ZDJ>
	<CG_PJ>采购价</CG_PJ>
	<CG_JSF>采购机建</CG_JSF>
	<CG_TAX>采购税费</CG_TAX>
	<CPZT>出票状态</CPZT>
	<XCDH>行程单号</XCDH>
	<XB>性别</XB>
	<GJ>国籍</GJ>
	<CSRQ>出生日期</CSRQ>
	<ZJYXQ>证件有效期</ZJYXQ>
	<ZJQFG>证件签发国</ZJQFG>
	</CJRDATA>
	<HD>
	<SXH>顺序号</SXH>
	<CFCITY>出发城市</CFCITY>
	<DDCITY>到达城市</DDCITY>
	<CFSJ>出发时间</CFSJ>
	<DDSJ>到达时间</DDSJ>
	<CFHZL>出发航站楼</CFHZL>
	<DDHZL>到达航站楼</DDHZL>
	<FJJX>飞机机型</FJJX>
	<HDZT>航段状态</HDZT>
	<XS_HBH>销售航班号</XS_HBH>
	<XS_CW>销售舱位</XS_CW>
	<XS_TGQ>销售退改签</XS_TGQ>
	<CG_HBH>采购航班号</CG_HBH>
	<CG_CW>采购舱位</CG_CW>
	<CG_TGQ>采购退改签</CG_TGQ>
	</HD>
	<RETURNDATA>ddbh,pnr_no</RETURNDATA>
	</PNR>
	 */
}
