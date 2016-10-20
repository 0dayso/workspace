package org.vetech.core.business.pid.api.tknetrdx;

import java.util.List;

import org.vetech.core.business.pid.api.Param;
import org.vetech.core.modules.utils.XmlUtils;

/**
 * 白屏改签指令请求参数
 * 
 * @author lkh
 * @version [版本号, Nov 14, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VeTkneTrdxParam extends Param{

	/**
	 * PNR编码 不能为空
	 */
	private String pnrno;

	/**
	 * 国内国际, D代表国内,I代表国际
	 */
	private String dori;

	/**
	 * 指令类型 TKNE或者TRDX
	 */
	private String type;

	/**
	 * 原PNR是否有效 0表示无效，1表示有效
	 */
	private String pnrValid;

	/**
	 * PNR是否被保留 0表示不保留，1表示保留
	 */
	private String pnrRemain;

	/**
	 * 人名,证件号, 趁机人类型(ADULT，CHD，INF),电话,票号|人名,证件号,趁机人类型(ADULT，CHILD，INF),电话,票号
	 * 
	 * 如果是国内预定，那么人名仅仅只能是人名，不能加性别称谓或CHD等儿童标识；如果国际预定，那么必须在人名中拼上所有的信息，例如性别称谓，或儿童标识（CHD或UM）， 不同的航空公司有不同的规则
	 * 如果是国内预定，那么证件类型就是常规的身份证号等，例如420803197712275111；如果是国际预定，证件号码是‘证件类型/发证国家/证件号码/国籍/出生日期/性别/证件有效期限/SURNAME(姓)/FIRST-NAME(名)/MID-NAME(中间名)/持有人标识H’，例如
	 * P/CN/G35463153/CN/01JUN09/MI/12JUN12/SHAN/YIPENG； 如果乘机人类型是婴儿，那么电话填写的是出生日期，必填，格式为
	 * DDMMMYY；婴儿必须紧跟在其监护人后面，监护人类型必须是ADULT。 如果乘机人类型是儿童，那么电话填写的是出生日期，必填，格式为 DDMMMYY
	 * 当乘机人类型是ADULT，电话可填可不填，当前该信息没有被使用；但以后会被使用。 当原始PNR有效、需要保留而且是非部分人时，PERSONS节点可以忽略掉，只传入待XE的航段信息以及需要预定的新信息即可
	 */
	private String persons;

	/**
	 * 指定改签时待XE的航段信息列表，如果有多个航段，中间用’|’分隔开。每个航段信息包括航班号,出发城市、到达城市、出发时间和仓位，中间用‘,’分隔开,时间格式是YYYY-MM-DD
	 * 
	 * 航班号,出发城市,到达城市,出发时间，仓位|…
	 */
	private String hdToXe;

	/**
	 * 待预定的航段列表
	 * 日期为DDMMMYY类型的格式，其中当DDMMMYY中的YY可以省略；出发时间和到达时间都是DDdd（(+|-）\d）？格式，也即是两位小时后接两位数的分钟，并且后面可以接+或-表示下一天或上一天，例如1900+1表示明天19:00,1900-1表示昨天19:00；城市对为两个城市3字代码拼凑成的6字母参数，例如北京上海对应的是PEKSHA.
	 * ‘航班号,仓位，日期，城市对，出发时间，到达时间（#航班号,仓位，日期，城市对，出发时间，到达时间）’是一个信息组，代表一个航段（单航段或联程航段），可以有多个信息组，彼此之间用‘|’分隔开。此参数非空。
	 * 
	 * 航班号,仓位，日期，城市对，出发时间，到达时间（#航班号,仓位，日期，城市对，出发时间，到达时间）|…
	 */
	private String flights;

	/**
	 * 联系电话 当预定新PNR的时候，这个信息可以为空；但是如果共享无法根据传入的User自行确定OSI电话或者从PNR中获取OSI电话，那么将报错
	 */
	private String osict;

	/**
	 * OI时需要的信息 出票城市三字码,日期（DDMMM或DDMMMYY）,航协号
	 */
	private String oiInfo;
	
	/**
	 * office号
	 */
	private String office;
	
	/**
	 * 指明是否是查询改签运价,值为0或1
	 * 1指示查询TRD运价，不会封口；
	 * 0的时候，如果SCNY节点没有传入运价但是TRD后有多个运价，那么也会将这多个运价返回到调用方；如果只有一个运价，那么会直接用这个运价封口。
	 */
	private String queryPriceForTRDX;
	
	/**
	 * <SCNY>运价明细</SCNY> 
     * 格式：
     * <TRDXINDEX><INDEX>1</INDEX><PERSON>旅客名称</PERSON><FOID>身份证</FOID><PRICE>被选择的运价明细，例如02 YX14ZS FARE:CNY360.00 TAX:CNY50.00 YQ:CNY110.00  TOTAL:520.00</PRICE><TRDXINDEX>
     * <TRDXINDEX><INDEX>2</INDEX><PERSON>旅客名称</PERSON><FOID>身份证</FOID><PRICE>被选择的运价明细</PRICE><TRDXINDEX>
	 */
	private List<VeTkneTrdxIndex> trdxIndexs;

	public String toXml() {
		StringBuffer xml = new StringBuffer("<INPUT><COMMAND>VETKNETRDX</COMMAND><PARA>");
		xml.append(XmlUtils.xmlnode("USER", this.getUserid()));
		xml.append(XmlUtils.xmlnode("PNRNO", pnrno));
		xml.append(XmlUtils.xmlnode("OFFICE", this.getOffice()));
		xml.append(XmlUtils.xmlnode("DORI", dori));
		xml.append(XmlUtils.xmlnode("TYPE", type));
		xml.append(XmlUtils.xmlnode("PNRVALID", pnrValid));
		xml.append(XmlUtils.xmlnode("PNRREMAIN", pnrRemain));
		xml.append(XmlUtils.xmlnode("PERSONS", persons));
		xml.append(XmlUtils.xmlnode("HDTOXE", hdToXe));
		xml.append(XmlUtils.xmlnode("FLIGHTS", flights));
		xml.append(XmlUtils.xmlnode("OSICT", osict));
		xml.append(XmlUtils.xmlnode("OIINFO", oiInfo));
		xml.append(XmlUtils.xmlnode("QUERYPRICEFORTRDX", queryPriceForTRDX));
		
		if ("TRDX".equals(type) && trdxIndexs != null && !trdxIndexs.isEmpty()) {
			xml.append("<SCNY>");
			VeTkneTrdxIndex trdxIndex = null;
			for (int i = 0; i < trdxIndexs.size(); i++) {
				trdxIndex = trdxIndexs.get(i);
				xml.append("<TRDXINDEX>");
				xml.append(XmlUtils.xmlnode("INDEX", trdxIndex.getIndex()));
				xml.append(XmlUtils.xmlnode("PERSON", trdxIndex.getPerson()));
				xml.append(XmlUtils.xmlnode("FOID", trdxIndex.getFoid()));
				xml.append(XmlUtils.xmlnode("PRICE", trdxIndex.getPrice()));
				xml.append("</TRDXINDEX>");
			}
			xml.append("</SCNY>");
		}
		xml.append("</PARA></INPUT>");
		return xml.toString();
	}

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getDori() {
		return dori;
	}

	public void setDori(String dori) {
		this.dori = dori;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPnrValid() {
		return pnrValid;
	}

	public void setPnrValid(String pnrValid) {
		this.pnrValid = pnrValid;
	}

	public String getPnrRemain() {
		return pnrRemain;
	}

	public void setPnrRemain(String pnrRemain) {
		this.pnrRemain = pnrRemain;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public String getHdToXe() {
		return hdToXe;
	}

	public void setHdToXe(String hdToXe) {
		this.hdToXe = hdToXe;
	}

	public String getFlights() {
		return flights;
	}

	public void setFlights(String flights) {
		this.flights = flights;
	}

	public String getOsict() {
		return osict;
	}

	public void setOsict(String osict) {
		this.osict = osict;
	}

	public String getOiInfo() {
		return oiInfo;
	}

	public void setOiInfo(String oiInfo) {
		this.oiInfo = oiInfo;
	}

	public String getQueryPriceForTRDX() {
		return queryPriceForTRDX;
	}

	public void setQueryPriceForTRDX(String queryPriceForTRDX) {
		this.queryPriceForTRDX = queryPriceForTRDX;
	}

	public List<VeTkneTrdxIndex> getTrdxIndexs() {
		return trdxIndexs;
	}

	public void setTrdxIndexs(List<VeTkneTrdxIndex> trdxIndexs) {
		this.trdxIndexs = trdxIndexs;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}
	
	
	
	// <INPUT>
	// <COMMAND>VETKNETRDX</COMMAND>
	// <PARA>
	// <USER>用户名</USER> 不能为空
	// <PNRNO>PNR编码</PNRNO> 不能为空
	// <OFFICE>office号</OFFICE> 可选，如果指定了，指令将在属于该office的pid上执行
	// <DORI>D或者I</DORI> 指明是国内还是国际, D代表国内,I代表国际
	// <TYPE>TKNE或者TRDX</TYPE> 指明改期类型，即是同仓位改期还是升舱。
	// <PNRVALID>原PNR是否有效 0或1 </PNRVALID> 如果为空，默认值就是1.当传入的PNR编码是无效编码时，实际上是指明要重新预定一个新的PNR。
	// <PNRREMAIN>PNR是否被保留 0或1 </PNRREMAIN>
	// 当原PNR不需要被保留时，对于部分改签，先使用SP分离出部分人，然后XEPNR掉原始编码；如果是所有人都需要改签，实际上等同于原PNR无效的处理方式但是多了XEPNR原PNR的动作。如果原PNR需要被保留，对于部分人改签，先使用SP分离出部分人，老PNR不用理会；如果是所有人都需要改签，就直接修改原PNR即可。
	// <PERSONS>人名,证件号, 趁机人类型(ADULT，CHD，INF),电话,票号|人名,证件号,
	// 趁机人类型(ADULT，CHILD，INF),电话,票号</PERSONS>旅客信息为逗号分隔的多个信息组合，可以有多个这样的信息组合，彼此之间用‘|’分隔开。此参数非空。
	// 如果是国内预定，那么人名仅仅只能是人名，不能加性别称谓或CHD等儿童标识；如果国际预定，那么必须在人名中拼上所有的信息，例如性别称谓，或儿童标识（CHD或UM），不同的航空公司有不同的规则。
	// 如果是国内预定，那么证件类型就是常规的身份证号等，例如420803197712275111；如果是国际预定，证件号码是‘证件类型/发证国家/证件号码/国籍/出生日期/性别/证件有效期限/SURNAME(姓)/FIRST-NAME(名)/MID-NAME(中间名)/持有人标识H’，例如
	// P/CN/G35463153/CN/01JUN09/MI/12JUN12/SHAN/YIPENG；
	// 如果乘机人类型是婴儿，那么电话填写的是出生日期，必填，格式为 DDMMMYY；婴儿必须紧跟在其监护人后面，监护人类型必须是ADULT。
	// 如果乘机人类型是儿童，那么电话填写的是出生日期，必填，格式为 DDMMMYY
	// 当乘机人类型是ADULT，电话可填可不填，当前该信息没有被使用；但以后会被使用。
	// 当原始PNR有效、需要保留而且是非部分人时，PERSONS节点可以忽略掉，只传入待XE的航段信息以及需要预定的新信息即可。
	// <HDTOXE>航班号,出发城市,到达城市,出发时间，仓位|…</HDTOXE>
	// 指定改签时待XE的航段信息列表，如果有多个航段，中间用’|’分隔开。每个航段信息包括航班号,出发城市、到达城市、出发时间和仓位，中间用‘,’分隔开,时间格式是YYYY-MM-DD。
	// Todo：这个里面没有open航段的定义，待补充。
	// <FLIGHTS>航班号,仓位，日期，城市对，出发时间，到达时间（#航班号,仓位，日期，城市对，出发时间，到达时间）|…</ FLIGHTS > 待预定的航段列表
	// 日期为DDMMMYY类型的格式，其中当DDMMMYY中的YY可以省略；出发时间和到达时间都是DDdd（(+|-）\d）？格式，也即是两位小时后接两位数的分钟，并且后面可以接+或-表示下一天或上一天，例如1900+1表示明天19:00,1900-1表示昨天19:00；城市对为两个城市3字代码拼凑成的6字母参数，例如北京上海对应的是PEKSHA.
	// ‘航班号,仓位，日期，城市对，出发时间，到达时间（#航班号,仓位，日期，城市对，出发时间，到达时间）’是一个信息组，代表一个航段（单航段或联程航段），可以有多个信息组，彼此之间用‘|’分隔开。此参数非空。
	//
	// 在‘航班号,仓位，日期，城市对，出发时间，到达时间（#航班号,仓位，日期，城市对，出发时间，到达时间）’中，表示的是一个单航段或者是一个联程航段，如果是一个单航段，那么格式变成‘航班号,仓位，日期，城市对，出发时间，到达时间’；如果是联程航段，那么格式变成‘航班号,仓位，日期，城市对，出发时间，到达时间#航班号,仓位，日期，城市对，出发时间，到达时间#...’，也就是说，用‘#’将联程航段信息连起来。
	// <OSICT>联系电话</OSICT> 当预定新PNR的时候，这个信息可以为空；但是如果共享无法根据传入的User自行确定OSI电话或者从PNR中获取OSI电话，那么将报错。
	// <PRINTER></PRINTER>
	// <QUERYPRICEFORTRDX>1或0</QUERYPRICEFORTRDX>
	// </PARA>
	// </INPUT>

}
