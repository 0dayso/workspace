package cn.vetech.vedsb.platpolicy.cps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.platpolicy.NoticeStatus;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.EtdzRequest;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;


/**
 * CPS出票通知信息
 * @author vetech
 *
 */
@Service
public class CpsEtdzNoticeService {
	@Autowired
	private CpsService cpsService;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpPtzcFzszServiceImpl jpPtzcFzszServiceImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;
	public NoticeStatus execute(JpCgdd jpCgdd, Map<String, String[]> params){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		NoticeStatus s = new NoticeStatus();
		JpPtLog ptLog=new JpPtLog();
		ptLog.add("采购 出票成功通知："+PlatCode.CPS);
		ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		ptLog.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		ptLog.setBy1("1001901");//国内机票
		ptLog.setYwlx(DictEnum.PTLOGYWLX.CPTZ.getValue());
		ptLog.setCzsm("采购 出票成功通知");
		try {
			EtdzRequest request = this.cpsService.etdzNotify(params);
			if(null == request){
				throw new Exception("没有数据！");
			}
			String orderNo=request.getOrderNo();
			String orderStatus=request.getOrderStatus();
			if(StringUtils.isBlank(orderNo)){
				throw new Exception("没有传入订单编号！");
			}
			if(!"142".equals(orderStatus) && !"161".equals(orderStatus)){
				throw new Exception("订单状态不正确！传入【"+orderStatus+"】");
			}
			//检查系统中订单是否该存在
			//数据库中的订单数据
			JpKhdd jpkhdd = this.jpKhddServiceImpl.getJpKhddByCgDdbh(shbh, orderNo, PlatCode.CPS);
			if(null == jpkhdd || StringUtils.isBlank(jpkhdd.getDdbh())){
				throw new Exception("在系统中没有找到对应的订单【"+orderNo+"】");
			}
			ptLog.setDdbh(jpkhdd.getDdbh());
			ptLog.setPnrNo(request.getPnrNo());
			
			//验证数据
			String tknoStr=request.getTicketNo();//票号
			String lxkpStr=request.getContinuousTicket();//连续客票
			String hcStr=request.getTravelRange();
			String cjrxmStr=request.getPassenger();
			String zjhmStr=request.getCardId();
			String cfsjStr=request.getFromDatetime();
			String zdjStr=request.getScny();
			String taxStr=request.getTax();
			String jsfStr=request.getYq();
			
			if(StringUtils.isBlank(tknoStr)){
				throw new Exception("票号为空！");
			}
			if(StringUtils.isBlank(hcStr)){
				throw new Exception("航程为空！");
			}
			if(StringUtils.isBlank(cjrxmStr)){
				throw new Exception("乘机人为空！");
			}
			if(StringUtils.isBlank(zjhmStr)){
				throw new Exception("证件号码为空！");
			}
			if(StringUtils.isBlank(zdjStr)){
				throw new Exception("账单价为空！");
			}
			if(StringUtils.isBlank(jsfStr)){
				throw new Exception("机建为空！");
			}
			if(StringUtils.isBlank(taxStr)){
				throw new Exception("税费为空！");
			}
			
			String [] array_tkno=tknoStr.split("\\|", -1);
			String [] array_lxkp=StringUtils.isNotBlank(lxkpStr) ? lxkpStr.split("\\|", -1) : null;
			String [] array_hc=hcStr.split("\\|", -1);
			String [] array_cjrxm=cjrxmStr.split("\\|", -1);
			String [] array_zjhm=zjhmStr.split("\\|", -1);
			String [] array_cfsj=cfsjStr.split("\\|", -1);
			String [] array_zdj=zdjStr.split("\\|", -1);
			String [] array_tax=taxStr.split("\\|", -1);
			String [] array_jsf=jsfStr.split("\\|", -1);
			
			int len=array_tkno.length;
			if(array_hc.length!=len){
				throw new Exception("航程和机票数量不一致！航程【"+hcStr+"】票号【"+tknoStr+"】");
			}
			if(array_cjrxm.length!=len){
				throw new Exception("乘机人和机票数量不一致！航程【"+cjrxmStr+"】票号【"+tknoStr+"】");
			}
			if(array_zjhm.length!=len){
				throw new Exception("证件号码和机票数量不一致！航程【"+zjhmStr+"】票号【"+tknoStr+"】");
			}
			if(array_cfsj.length!=len){
				throw new Exception("出发时间和机票数量不一致！航程【"+cfsjStr+"】票号【"+tknoStr+"】");
			}
			if(array_zdj.length!=len){
				throw new Exception("账单价和机票数量不一致！账单价【"+zdjStr+"】票号【"+tknoStr+"】");
			}
			if(array_tax.length!=len){
				throw new Exception("机建和机票数量不一致！税费【"+taxStr+"】票号【"+tknoStr+"】");
			}
			if(array_jsf.length!=len){
				throw new Exception("税费和机票数量不一致！机建【"+jsfStr+"】票号【"+tknoStr+"】");
			}
			
			String zffs=request.getPayment();
			ptLog.add("传入支付方式【"+zffs+"】");
			//根据平台返回的支付对接码转化为本地系统的支付方式代码
			if("312013301".equals(zffs) || "支付宝".equals(zffs)){//支付宝
				zffs="10063971";
			}else if("312013304".equals(zffs) || "汇付天下".equals(zffs)){//汇付天下
				zffs="1006394";
			}else if("312013318".equals(zffs) || "财付通".equals(zffs)){//财付通 	
				zffs="16063781";
			}else if("312013300".equals(zffs) || "预存款".equals(zffs)){//预存款
				zffs="312013300";
			}
			ptLog.add("转换后支付方式【"+zffs+"】");
			JpPtzcFzsz jpPtzcFzsz = this.jpPtzcFzszServiceImpl.genZfkmByDjm("2",shbh,zffs,PlatCode.CPS);
			String zfkm="";
			if(jpPtzcFzsz == null){
				ptLog.add("没有设置对应的支付方式！取默认支付方式");
			}else{
				zfkm = jpPtzcFzsz.getXtZfkm();
				if(StringUtils.isBlank(zfkm)){
					ptLog.add("支付方式或支付科目为空！传入支付方式【"+zffs+"】");
		        }
			}
			ptLog.add("对应系统中支付科目【"+zfkm+"】");
			
			String pnr=request.getPnrNo();
			String hkgs_pnr=request.getBigPnrNo();
//			JpCgdd jpCgdd = this.jpCgddServiceImpl.getDdByZflsh(orderNo);//查支付记录根据平台订单编号查
//			if(jpCgdd == null){
//				ptLog.add("出票失败！没有找到支付记录！");
//                throw new Exception("出票失败！没有找到支付记录");
//			}
			String cpy = jpCgdd.getCjUserid();
			//组织数据、拼接xml
			StringBuffer xml=new StringBuffer("<WBDD>");
			xml.append(XmlUtils.xmlnode("DDBH", jpkhdd.getDdbh()));
			xml.append(XmlUtils.xmlnode("CPLX", "OP"));//OP票
			xml.append(XmlUtils.xmlnode("CP_USERID", cpy));
			xml.append(XmlUtils.xmlnode("CG_ZFKM", zfkm));
			xml.append(XmlUtils.xmlnode("FKDH", request.getPaymentTransaction()));
			xml.append(XmlUtils.xmlnode("DLFL", request.getRebate()));
			xml.append(XmlUtils.xmlnode("CGJE", request.getPaymentAmount()));
			if(StringUtils.isNotBlank(pnr)){
			    xml.append(XmlUtils.xmlnode("NEWPNR", pnr.toUpperCase() ));			    
			}
			if(StringUtils.isNotBlank(hkgs_pnr)){
			    xml.append(XmlUtils.xmlnode("NEWHKGS_PNR", hkgs_pnr.toUpperCase()));			    
			}
			xml.append(XmlUtils.xmlnode("PTZCBS", PlatCode.CPS));
			xml.append(XmlUtils.xmlnode("WCPDW",jpCgdd.getCgdw()));
			boolean bl1 = false;
			double pj_cgj_one = 0.0;
			String cgdw = jpCgdd.getCgdw();
			if("TB".equals(cgdw)){
				pj_cgj_one = Arith.div(jpkhdd.getCgZdj(), len);
				ptLog.add("转机票时账单价取订单上账单价："+pj_cgj_one);
				bl1 = true;
			}
			for (int i = 0; i < len; i++) {
				if(bl1){
					array_zdj[i]=pj_cgj_one+"";
				}
				xml.append("<TK>");
				xml.append(XmlUtils.xmlnode("CJR", array_cjrxm[i]));
				xml.append(XmlUtils.xmlnode("TKNO", array_tkno[i]));
				xml.append(XmlUtils.xmlnode("LXKP", null == array_lxkp ? "" : array_lxkp[i]));
				xml.append(XmlUtils.xmlnode("PJ_PMJ", array_zdj[i]));
				xml.append(XmlUtils.xmlnode("PJ_ZDJ", array_zdj[i]));
				xml.append(XmlUtils.xmlnode("PJ_JSF", array_jsf[i]));
				xml.append(XmlUtils.xmlnode("PJ_TAX", array_tax[i]));
				String[] tk_cfsj=array_cfsj[i].split(",", -1);
				String[] tk_hc=array_hc[i].split(",", -1);
				if(tk_cfsj.length!=tk_hc.length){
					throw new Exception("机票【"+array_tkno[i]+"】航段【"+array_hc[i]+"】和出发时间【"+array_cfsj[i]+"】长度不一致！");
				}
				for (int j = 0; j < tk_cfsj.length; j++) {
					xml.append("<HD>");
					xml.append(XmlUtils.xmlnode("HC", tk_hc[j]));
					xml.append(XmlUtils.xmlnode("CFSJ", tk_cfsj[j]));
					xml.append("</HD>");
				}
				xml.append("</TK>");
			}
			xml.append("</WBDD>");
			//调用接口
			ptLog.add("采购平台返回票号转机票入参:"+xml.toString());
			Map<String, Object> paramXml = new HashMap<String, Object>();
			paramXml.put("pxml", xml.toString());
			this.pkgZjpSgServiceImpl.fzdzjpCps(paramXml);
			int result = (Integer)paramXml.get("result");
			if(-1 == result){
				String error = StringUtils.trimToEmpty((String)paramXml.get("perror"));
				ptLog.add("采购平台返回票号系统转机票返回:"+result + "===" + error);
				throw new Exception(error);
			}
			jpCgdd.setZt("1");//出票成功
			jpCgdd.setPlatzt("13");
			this.jpCgddServiceImpl.update(jpCgdd);
		} catch (Exception e) {
			e.printStackTrace();
			ptLog.add(e.getMessage());
			s.setMsg(e.getMessage());
			s.setStatus(-1);
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(ptLog);//写日志
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	} 
}
