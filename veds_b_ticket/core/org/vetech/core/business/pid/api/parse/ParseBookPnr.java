package org.vetech.core.business.pid.api.parse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.pidbean.PidBookResult;
import org.vetech.core.business.pid.pidbean.PidPnrnrBean;
import org.vetech.core.modules.utils.XmlUtils;

/**
 * 解析预订PNR接口返回的内容
 * @author  gengxianyan
 * @version  [版本号, Aug 28, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class ParseBookPnr {

//	@Autowired
//	private PidCommonService commonService;
	
	private String flag;//1成功 0失败
	private String pnrno;//PNR编码
	private String pnrlr;//PNR内容
	private String pnrzt;//PNR状态
	private String office;//预订PNR的OFFICE号x
	private String auth;//授权的OFFICE号
	private String big_pnrno;//PNR大编码
	private String timeofnoseat;//NO位时限
	
	private PidBookResult resultBean;//PNR解析转换Bean

	/**
	 * 构造函数
	 * 负责解析预订PNR后返回的数据
	 * @param pnrxx 接口返回的信息
	 * @param bookType 预订方式
	 * @param dpyId 订票员ID
	 */
	public ParseBookPnr(String pnrxx, String bookType, String dpyId) {
		
//		if ("1".equals(bookType)) {// IBE
//			RT rt = new RT();
//			try {
//				RTResult re = rt.retrieve(pnrxx);
//				PNRAirSeg pa = re.getAirSegAt(0);
//				String pnr_zt = pa.getActionCode();
//				
//				if (pnr_zt.equals("HK")) {
//					flag = "1";
//					pnrno = pnrxx;
//					pnrlr = "0";
//					pnrzt = "HK";
//				} else {
//					flag = "0";
//					pnrno = pnrxx;
//					pnrlr = "0";
//					pnrzt = pnr_zt;
//				}
//				
//			} catch (Exception e) {
//				flag = "0";
//				pnrno = "0";
//				pnrlr = "0";
//				pnrzt = "HL";
//				e.printStackTrace();
//			} finally {
////				commonService.p_yy_fxll_interface(dpyId, "RT", "1");// 计算流量
//			}
//		} else { // ETERM
			try {
				resultBean = (PidBookResult)XmlUtils.fromXml(pnrxx, PidBookResult.class);
				
				if(null == resultBean || (StringUtils.isBlank(resultBean.getPnrno()) && StringUtils.isBlank(resultBean.getPnrlr()))){
					flag = "0";
					
					PidPnrnrBean pnrnrBean = (PidPnrnrBean)XmlUtils.fromXml(pnrxx, PidPnrnrBean.class);
					if(StringUtils.isBlank(pnrnrBean.getPnrno()) && StringUtils.isNotBlank(pnrnrBean.getOther())){
						pnrlr = pnrnrBean.getOther();
					}else{
						pnrlr = pnrxx;
					}
					
				}else{
					flag = ObjectUtils.toString(resultBean.getFlag());
					pnrno = ObjectUtils.toString(resultBean.getPnrno());
					
					if (StringUtils.isNotBlank(pnrno) && !"0".equals(pnrno)) {
						flag = "1";
					}
					
					pnrlr = ObjectUtils.toString(resultBean.getPnrlr());
					pnrzt = ObjectUtils.toString(resultBean.getPnrzt());
					
					if(StringUtils.isBlank(pnrlr) && null != resultBean.getChildBean()){
					    pnrlr = resultBean.getChildBean().getPnrnr();
					}
					
					auth = StringUtils.trimToEmpty(ObjectUtils.toString(resultBean.getAuth(), ""));
					big_pnrno = StringUtils.trimToEmpty(ObjectUtils.toString(resultBean.getBig_pnrno(), ""));
					office = StringUtils.trimToEmpty(ObjectUtils.toString(resultBean.getOffice(), ""));
					
					if(null != resultBean.getChildBean() ){
						timeofnoseat = resultBean.getChildBean().getTimeofnoseat();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = "0";
				pnrlr = pnrxx;
			}
//		}
	}

	public String getFlag() {
		return flag;
	}

	public String getPnrlr() {
		return pnrlr;
	}

	public String getPnrno() {
		return pnrno;
	}

	public String getPnrzt() {
		return pnrzt;
	}

	public String getOffice() {
		return office;
	}

	public String getAuth() {
		return auth;
	}

	public void setPnrzt(String pnrzt) {
		this.pnrzt = pnrzt;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getBig_pnrno() {
		return big_pnrno;
	}

	public void setBig_pnrno(String big_pnrno) {
		this.big_pnrno = big_pnrno;
	}

	public String getTimeofnoseat() {
		return timeofnoseat;
	}

    public PidBookResult getResultBean() {
        return resultBean;
    }
}
