package cn.vetech.vedsb.platpolicy.cpslink.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.service.base.ShshbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.platpolicy.cpslink.request.RefundNotifyRequest;
import cn.vetech.vedsb.platpolicy.cpslink.response.NoticeRes;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;

@Service
public class CpslinkRefundNoticeService {
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private ShshbServiceImpl shshbServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	public NoticeRes execute(JpPtLog log,RefundNotifyRequest req,JpPtzcZh jpPtzcZh,String shbh) throws Exception{
		//Shyhb user = SessionUtils.getShshbSession();
		//String shbh = user.getShbh();
		Shshb shshb = shshbServiceImpl.getShbhByBh(shbh);
		NoticeRes res = new NoticeRes();
		log.add("采购退废票通知"+PlatCode.getPtname(req.getPlatcode()));
		log.setDdlx(DictEnum.PTLOGDDLX.TF.getValue());
		log.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		log.setBy1("1001901");//国内机票
		log.setYwlx(DictEnum.PTLOGYWLX.TFTZ.getValue());
		log.setCzsm("采购退废票通知");
		log.setPtzcbs(req.getPlatcode());
		log.setShbh(shbh);
		log.setYhbh(shshb.getBh());
		try {
			String status = req.getRefundStatus();
			if(StringUtils.isBlank(status)){
				log.add("平台返回退废票状态为空");
				throw new Exception("平台返回退废票状态为空不能处理");
			}
			JpTpd jptpd = null;
			if(DictEnum.ReturnPtStatus.WCTFP.getValue().equals(status)){
				String orderNo = req.getOrderNo();
				String pttpdh =  req.getOutOrderNo();
				JpKhdd jpkhdd = null;
				if(StringUtils.isNotBlank(pttpdh)){
					jptpd = this.jpTpdServiceImpl.getTpdByCgtpdh(pttpdh, jpPtzcZh.getPtzcbs());
				}else{
					jpkhdd = this.jpKhddServiceImpl.getJpKhddByCgDdbh(shbh, orderNo, jpPtzcZh.getPtzcbs());
					String ddbh = jpkhdd.getDdbh();
					String ticketNo = req.getTicketNo();
					String[] ticketNos = ticketNo.split("\\|");
					ticketNo = ticketNos[0];
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("shbh", shbh);
					param.put("ddbh", ddbh);
					param.put("ticketno", ticketNo);
					jptpd = this.jpTpdServiceImpl.getJpTpdByddbh(param);
				}
			}else{
				res.setStatus(NoticeRes.FAILL);
				res.setMsg("【平台退废票失败】"+req.getReason());
			}
			if(jptpd == null){
				throw new Exception("查询退票单为空!");
			}
			JpCgdd jpCgdd = this.jpCgddServiceImpl.getJpCgddByDdbh(jptpd.getDdbh(), shbh);
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("TPDH", jptpd.getTpdh());
			param.put("USERID",jpCgdd.getCjUserid());
			param.put("TJZT", "");
			param.put("SBYY", req.getRemark());
			param.put("CZLX", "2");
			param.put("CGST", req.getActualRefund());
			List<Map<String,Object>> tklist=new ArrayList<Map<String,Object>>();
			param.put("TK", tklist);
			try{
				procedureServiceImpl.f_cgtp(param);
			}catch(Exception e){
				log.add("采购办理出现异常,异常原因:"+param.get("p_error"));
				e.printStackTrace();
				throw new Exception(e.getMessage()+param.get("p_error"));
			}
			log.add("采购办理入参:"+VeStr.getValue(param, "p_xml"));
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(NoticeRes.FAILL);
			res.setMsg(e.getMessage());
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(log);//写日志
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("TPDH", "TP160813002741");
		param.put("USERID","CS02");
		param.put("TJZT", "");
		param.put("SBYY", "");
		param.put("CZLX", "2");
		param.put("CGST", "760");
		String p_xml = VeStr.getValue(param, "p_xml");// 用于复杂xml直接拼接好后转json
		List<Map<String,Object>> tklist=new ArrayList<Map<String,Object>>();
		param.put("TK", tklist);
		Map<String, Object> m = param;
		JSONObject jsonObject = JSONObject.fromObject(m);
		param.put("p_xml", "{\"SQL\":" + transToUpperObject(jsonObject.toString()) + "}");
	}
	
	//json小写转大写
	public static String transToUpperObject(String json) {
		//正则替换
		String regex = "(\\{|\")([a-zA-Z0-9]|_)+\":";
		Pattern pattern = Pattern.compile(regex);
		StringBuffer sb = new StringBuffer();
		Matcher m = pattern.matcher(json);
		while (m.find()) {
			m.appendReplacement(sb, m.group().toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
