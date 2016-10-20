package cn.vetech.vedsb.platpolicy.cpslink.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShshbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.platpolicy.cpslink.request.EtdzNotifyRequest;
import cn.vetech.vedsb.platpolicy.cpslink.response.NoticeRes;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;

@Service
public class CpslinkEtdzNoticeService {
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;
	@Autowired
	private ShshbServiceImpl shshbServiceImpl;
	public NoticeRes execute(JpPtLog log,EtdzNotifyRequest req,JpPtzcZh jpPtzcZh,String shbh) throws  Exception{
//		Shyhb user = SessionUtils.getShshbSession();
//		String shbh = user.getShbh();
		Shshb shshb = shshbServiceImpl.getShbhByBh(shbh);
		NoticeRes res = new NoticeRes();
		log.add("采购出票通知"+PlatCode.getPtname(req.getPlatcode()));
		log.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		log.setYwlx(DictEnum.PTLOGYWLX.CPTZ.getValue());
		log.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		log.setBy1("1001901");//国内机票
		log.setCzsm("采购出票通知");
		log.setPtzcbs(req.getPlatcode());
		log.setShbh(shbh);
		log.setYhbh(shshb.getBh());
		try {
			String orderNo = req.getOutOrderNo();
			if(StringUtils.isBlank(orderNo)){
				throw new Exception("没有传入订单编号");
			}
			JpKhdd jpkhdd = this.jpKhddServiceImpl.getJpKhddByCgDdbh(shbh, orderNo, req.getPlatcode());
			if(jpkhdd == null || StringUtils.isBlank(jpkhdd.getDdbh())){
				throw new Exception("在系统中没有找到对应的订单【"+orderNo+"】");
			}
			log.setDdbh(jpkhdd.getDdbh());
			log.setPnrNo(req.getPnrNo());
			JpCgdd jpCgdd = this.jpCgddServiceImpl.getDdByZflsh(orderNo);//查支付记录根据平台订单编号查
			if(jpCgdd == null){
				log.add("出票失败！没有找到支付记录！");
                throw new Exception("出票失败！没有找到支付记录");
			}
			String czztmc = "";
			String status = req.getOrderStatus();
			if(DictEnum.ReturnPtStatus.CPWC.getValue().equals(status)){//出票完成
				String xtzfkm = jpCgdd.getCgZfkm();
				jpCgdd.setPlatzt(status);
				czztmc = "供应方出票成功";
				if(StringUtils.isNotBlank(req.getOldPnrNo())){
					jpCgdd.setPlatzt(DictEnum.ReturnPtStatus.GHBMCPWC.getValue());
					czztmc = "供应方更换编码出票完成";
				}
				/**
				 * 转机票
				 */
				Map<String,Object> publicParam=new HashMap<String, Object>();
				Map<String,Object> publicMap=new HashMap<String, Object>();
				Map<String, Object> tk = new HashMap<String, Object>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				//StringBuffer xml = new StringBuffer("<WEBZJP>");
				//xml.append(XmlUtils.xmlnode("DDBH", jpkhdd.getDdbh()));
				publicMap.put("DDBH",  jpkhdd.getDdbh());
				//xml.append(XmlUtils.xmlnode("WBDH", orderNo));
				publicMap.put("WBDH",  orderNo);
				//xml.append(XmlUtils.xmlnode("WBBM", StringUtils.trimToEmpty(jpPtzcZh.getCgdept())));
				publicMap.put("WBBM",  StringUtils.trimToEmpty(jpPtzcZh.getCgdept()));
				//xml.append(XmlUtils.xmlnode("CPLX", "OP"));
				publicMap.put("CGLY",  "OP");
				String ptmc = StringUtils.trimToEmpty(PlatCode.getPtname(req.getPlatcode()));
				//xml.append(XmlUtils.xmlnode("DATAFROM", ptmc+"平台采购出票回填"));
				publicMap.put("DATAFROM",  ptmc+"平台采购出票回填");
				//xml.append(XmlUtils.xmlnode("CP_USERID", jpCgdd.getCjUserid()));
				publicMap.put("CP_USERID", jpCgdd.getCjUserid());
				//xml.append(XmlUtils.xmlnode("SHBH",shbh));
				publicMap.put("SHBH", shbh);
				Shyhb yh = new Shyhb();
				yh.setShbh(shbh);
				yh.setBh(jpCgdd.getCjUserid());
				yh = this.shyhbServiceImpl.getEntityById(yh);
				if(yh!=null){
					//xml.append(XmlUtils.xmlnode("CP_DEPTID",yh.getShbmid()));
					publicMap.put("CP_DEPTID", yh.getShbmid());
				}
				String pnr = req.getPnrNo();
				String oldpnr = req.getOldPnrNo();
				if(StringUtils.isNotBlank(oldpnr)){
					if(pnr.equals(jpkhdd.getCgHkgsPnr())){
						pnr = "";
					}
					//xml.append(XmlUtils.xmlnode("NEWPNR", pnr));
					publicMap.put("NEWPNR", yh.getShbmid());
				}else{
					//xml.append(XmlUtils.xmlnode("NEWPNR", ""));
					publicMap.put("NEWPNR", "");
				}
				//xml.append(XmlUtils.xmlnode("CG_ZFKM", xtzfkm));
				publicMap.put("CG_ZFKM", xtzfkm);
				// 采购金额
				//xml.append(XmlUtils.xmlnode("CGJE", NumberUtils.toDouble(req.getPaymentAmount(), 0)));
				publicMap.put("CGJE", NumberUtils.toDouble(req.getPaymentAmount(), 0));
				//xml.append(XmlUtils.xmlnode("FKDH", StringUtils.trimToEmpty(jpCgdd.getTradeNo())));
				publicMap.put("FKDH", StringUtils.trimToEmpty(jpCgdd.getTradeNo()));
				//xml.append(XmlUtils.xmlnode("PTZCBS", StringUtils.trimToEmpty(req.getPlatcode())));
				publicMap.put("PTZCBS",StringUtils.trimToEmpty(req.getPlatcode()));
				
				publicParam.put("PUBLIC", publicMap);
				//xml.append("<TKS>");
				String[] tknos = req.getTicketNo().split("\\|");
				String[] cjrs = req.getPassenger().split("\\|");
				String tkall = "";
				for (int i = 0; i < tknos.length; i++) {
					String tkno = StringUtils.trimToEmpty(tknos[i]).replaceAll("\\s", "");
					if (tkall.indexOf(tkno) >= 0) {// 去掉重复
						continue;
					}
					String cjr_ = "";
					if (cjrs.length <= i) {
						cjr_ = "";
					} else {
						cjr_ = cjrs[i];
					}
					cjr_ = StringUtils.trimToEmpty(cjr_).replaceAll("\\s", "");
					if (StringUtils.isBlank(cjr_) || StringUtils.isBlank(tkno)) {
						continue;
					}
					//tkno = tkno.replaceAll("[^\\w]*", "");
					tk.put("TKNO", tkno);
					tk.put("CJR", cjr_);
					list.add(tk);
					//xml.append("<TK>");
					//xml.append(XmlUtils.xmlnode("TKNO", tkno));
					//xml.append(XmlUtils.xmlnode("CJR", cjr_));
					//xml.append("</TK>");
					tkall = tkall + "," + tkno;
				}
				publicParam.put("TK", list);
				//xml.append("</TKS>");
				//xml.append("</WEBZJP>");
				log.add("调后台接口转机票入参:"+publicParam);
				//Map<String, Object> paramXml = new HashMap<String, Object>();
				//paramXml.put("pxml", xml.toString());
				try {
					this.pkgZjpSgServiceImpl.fzjppt(publicParam);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("回填票号转机票失败!");
				}
				int result = (Integer)publicParam.get("result");
				if(-1 == result){
					res.setStatus(NoticeRes.FAILL);
					String error = StringUtils.trimToEmpty((String)publicParam.get("perror"));
					res.setMsg(error);
					log.add("采购平台返回票号系统转机票返回:"+result + "===" + error);
				}
			}else if(DictEnum.ReturnPtStatus.WFCP.getValue().equals(status)){//出票失败
				jpCgdd.setPlatzt(status);
				czztmc = "供应方出票失败";
			}
			log.setCzsm(czztmc);
			this.jpCgddServiceImpl.update(jpCgdd);
		} catch (Exception e) {
			log.add("平台返回票号系统转机票返回异常"+e.getMessage());
			res.setStatus(NoticeRes.FAILL);
			String error = e.getMessage();
			res.setMsg(error);
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(log);//写日志
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String s = "781-15245115";
		s = s.replaceAll("\\s", "");
		System.out.println(s);
	}
}
