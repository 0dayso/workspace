package cn.vetech.vedsb.jp.service.jptpgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.detrxml.DetrResult;
import org.vetech.core.business.pid.api.detrxml.DetrXmlParam;
import org.vetech.core.business.pid.api.detrxml.Segment;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.rtkt.RtKtParam;
import org.vetech.core.business.pid.api.rtkt.RtKtResult;
import org.vetech.core.business.pid.api.trfd.TrfdParam;
import org.vetech.core.business.pid.api.trfd.TrfdResult;
import org.vetech.core.business.pid.api.trfd.TrfdSubmitResult;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.jptpgl.JpTpdDao;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;
import cn.vetech.vedsb.jp.service.jptpgl.zxzw.TpXeZw;
import cn.vetech.vedsb.utils.LogUtil;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlitripSellerRefundUnsuspendRequest;
import com.taobao.api.response.AlitripSellerRefundUnsuspendResponse;

@Service
public class JpTpdServiceImpl extends MBaseService<JpTpd, JpTpdDao>{
	/**
	 * 分页查询退票单信息
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	
	@Autowired
	private JpHdServiceImpl jpHdServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private JpDdszServiceImpl  jpDdszServiceImpl;
	
	@Autowired
	private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;
	
	public Page query(Map<String, Object> param) throws Exception {
		int start=Integer.parseInt(param.get("pageNum").toString());
		int count=Integer.parseInt(param.get("pageSize").toString());
		Page page = new Page(start,count);
		String xs_hbh=VeStr.getValue(param, "xs_hbh");
		xs_hbh=xs_hbh.replace("*", "");
		if(StringUtils.isNotBlank(xs_hbh)){
			if(xs_hbh.length() == 2 ){
				param.put("xs_hbh", "");
				param.put("hkgs", xs_hbh);
			}
		}
		List<Map<String,Object>> jptpdList = this.getMyBatisDao().query(param);
		if(CollectionUtils.isNotEmpty(jptpdList)){
			attachService.wdzl("WDID").zfkm("XS_TKKM").zfkm("CG_TKKM").execute(jptpdList);
		}
		int pagecount = this.getMyBatisDao().getPageCount(param);
		page.setList(jptpdList);
		page.setTotalCount(pagecount);
		return page;
	}
	
	
	/**
	 * 取退票单详
	 */
	public Map<String, Object> detail(JpTpd jptpd) {
		return this.getMyBatisDao().detail(jptpd);
	}
	
	/**
     * 通过TPDH取退票单主表信息
     */
	public JpTpd getJpTpdByTpdh(String tpdh,String shbh){
		
		return this.getMyBatisDao().getJpTpdByTpdh(tpdh,shbh);
	}
	
	 /**
     * <根据订单编号和商户编号查找退票单>
     * 
     * @param map
     * @return [参数说明]
     * @author heqiwen
     * @return List<JpTpd> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
	public List<JpTpd> queryTpdByDdbh(String ddbh,String shbh) {
		return this.getMyBatisDao().queryTpdByDdbh(ddbh,shbh);
	}
	
	/**
     * 通过TPDH取退票明细汇总信息
     */
	public JpTpd getJpTpdMxSum(String tpdh,String shbh){
		
		return this.getMyBatisDao().getJpTpdMxSum(tpdh,shbh);
	}
	
	/**
	 * 查询退票申请信息
	 *
	 */
	public List<Map<String,Object>> getJptpdForApply(Map<String, Object> paramsMap){
		return this.getMyBatisDao().getJptpdForApply(paramsMap);
	}
	
	/**
	 * 取消退票单前验证
	 */
     public String cancelCheck(JpTpd jptpd,String version){
		if (null == jptpd) {
			return "退票单不存在!";
		}
		String xs_tpzt=jptpd.getXsTpzt();
		if (JpTpd.XS_TPZT_YQX.equals(xs_tpzt)) {
			return "订单已取消";
		} else if (JpTpd.XS_TPZT_YBL.equals(xs_tpzt)) {
			return "订单已办理";
		} else {
				
				/*
				if ("1".equals(fkzt)) {
					return "ASMS-TF-OP-0029";
				} else if ("2".equals(fkzt)) {
					return "ASMS-TF-OP-0030";
				} else {
					if ("1".equals(wcf) || "6".equals(wcf) || "7".equals(wcf)) {
						return "ASMS-TF-OP-0031";
					} else if ("2".equals(wcf)) {
						return "ASMS-TF-OP-0086";
					} else if ("4".equals(wcf) || "5".equals(wcf)) {
						return "ASMS-TF-OP-0032";
					} else {
						if ("3".equals(ticket_return.getTjlx()) && "10100000".equals(ticket_return.getPtzcbs())) {
							if ("8".equals(wcf)) {
								return "ASMS-TF-OP-0087";
							}
						}
						
						Ticket_returnrecord_service ticket_returnrecord_service = (Ticket_returnrecord_service) WebAppUtil
								.getService("ticket_returnrecord_service", request);
						List<Map<String, Object>> mxList = ticket_returnrecord_service.getTicket_returnrecordByTfid(id);
						if (mxList != null && !mxList.isEmpty()) {
							Iterator<Map<String, Object>> itr = mxList.iterator();
							while (itr.hasNext()) {
								Map<String, Object> _map = itr.next();
								String cg_wcf = (String) _map.get("CG_WCF");
								if (!"0".equals(cg_wcf) && !"3".equals(cg_wcf) && !"8".equals(cg_wcf)) {
									return "ASMS-TF-OP-0033";
								}
							}
						}
						if ("1".equals(sffh)) {
							return "ASMS-TF-OP-0034";
						} else if ("2".equals(sffh)) {
							return "ASMS-TF-OP-0035";
						}
					}
				}
				
				*/
			}
		    return "";
	 }
     
     
     
	/**
	 * 取消机票退票单
	 * @param jptpd
	 * @param xgly 修改来源
	 * @throws Exception 
	 */
    public void cancelJptpd(JpTpd jptpd,String xgly) throws Exception{
		
    	jptpd.setXsTpzt(JpTpd.XS_TPZT_YQX);
    	jptpd.setXgly(xgly);
    	
        this.update(jptpd);//修改update JpTpd
        
		List<Map<String,Object>> jptpdmx_list = jpTpdMxServiceImpl.getJpTpdMxList(jptpd.getTpdh(), jptpd.getShbh());

		if (CollectionUtils.isNotEmpty(jptpdmx_list)) {
			for (int i = 0; i < jptpdmx_list.size(); i++) {
			//	JpTpdMx jptpdmx = jptpdmx_list.get(i);
			//	jpTpdMxServiceImpl.update(jptpdmx);
			}

		}
       
        
    	
    	/*
		TfdSaveForm tfdForm = (TfdSaveForm) form;
		String xgcs = request.getParameter("xgcs");
		response.setContentType("text/x-json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		String qzqxtfd = StringUtils.trimToEmpty(request.getParameter("qzqxtfd"));// CPS供应是否强制取消订单 0.是 1.否
		Tmp_service tmp_service = (Tmp_service) WebAppUtil.getService("tmp_service", request);
		Ticket_return_service ticket_return_service = (Ticket_return_service) WebAppUtil.getService("ticket_return_service", request);
		Ticket_bx_service ticket_bx_service =  (Ticket_bx_service) WebAppUtil.getService("ticket_bx_service", request);
		Ve_yhb ve_yhb = AsmsUtils.getVeasms(request);
		TfdSave tfdSave = new TfdSave();
		PropertyUtils.copyProperties(tfdSave, tfdForm);
		Ticket_return ticket_return = ticket_return_service.getTicket_returnById(tfdSave.getId());
		tfdSave.setVe_yhb(ve_yhb);
		tfdSave.setTp_tpzt("7");
		if ("8".equals(ticket_return.getCg_wcf())) {
			tfdSave.setCg_wcf("3");
			tfdSave.setCgwc_compid(ve_yhb.getCompid());
			tfdSave.setCgwc_deptid(ve_yhb.getDeptid());
			tfdSave.setCgwc_userid(ve_yhb.getBh());
			tfdSave.setCgwc_datetime(VeDate.getStringDate());
		}
		// tfdSave.setCzfrom("ASMS取消退废单");
		// 验证
		TfCheck _tfCheck = new TfCheck(request, ve_yhb);
		String check = _tfCheck.checkOperateBefore(tfdSave.getId(), "5", xgcs);
		if (StringUtils.isNotBlank(check)) {
            out.write(Function.tfCode(check));
            if (out != null) {
                out.close();
            }
            return null;
		}

		// 调用平台审核接口拒单
		if (!"0".equals(qzqxtfd)) {
    		try {
                TicketOrderPlatUtils.rejectTicketRefund(tfdSave.getId(), tfdSave.getQxyy(), ve_yhb, request, tfdSave.getCzfrom());
            } catch (Exception e) {
                e.printStackTrace();
                out.write("GYPT_CANCEL_GQD_FAIL退废单取消失败，状态同步到平台失败，错误原因：" + e.getMessage());
                if (out != null) {
                    out.close();
                }
                return null;
            }
		}
		
		ticket_return_service.saveTicket_return(tfdSave);

		// 取消退费单 删除服务费
		try {
			String sql = " delete from KH_DD_QT where ywdh=? and ywlx='1010403' ";
			List<Object> param = new ArrayList<Object>();
			param.add(ticket_return.getId());
			tmp_service.getJdbcTempSource().getJdbcTemp().operate(param, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// 写入取消消息提醒信息
			String zkfx = ticket_return.getZkfx();
			String ct_hyid = ticket_return.getCt_hyid();
			if ("1".equals(zkfx)) {
				FxsNewMessage _newmessage = new FxsNewMessage(tfdSave.getId(), "2", tfdSave.getQxyy(), ct_hyid, ve_yhb,
						tmp_service.getJdbcTempSource().getDataSource());
				NewMessageService msgService = new NewMessageService(_newmessage);
				msgService.send();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 写入通知任务
		if ("1".equals(ticket_return.getZkfx())) {
			T_b2b_tzjk_send tbts = new T_b2b_tzjk_send();
			try {
				tbts.sendTask("2", tfdSave.getId(), request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//如果该退费单申请时挂票了，取消退废单时要解挂
		String tkno = ticket_return.getTkno();
		String ddbh = ticket_return.getDdbh();
		String tssError = "";
		if(StringUtils.isNotBlank(tkno)){
			try {
				ticket_return_service.ticket_return_tss(tfdSave.getId(),tkno.split(","), request, "2",ddbh, tfdSave.getCzfrom());
			} catch (Exception e) {
				e.printStackTrace();
				tssError = "退废单取消成功，解挂失败，错误原因：";
				if(StringUtils.isNotBlank(e.getMessage())){
					tssError+=e.getMessage();
				}
			}
		}
		if(StringUtils.isNotBlank(tssError)){
			 out.write("自动解挂失败："+tssError+"请到机票=》出票中心=》挂票解挂管理 手动操作");
             if (out != null) {
                 out.close();
             }
		//	return mapping.findForward("tfdsq_turning");
		}
		return null;*/
		
	}
	/**
	 * 用于查询（网店）退票单复核操作所需参数
	 * @param tpdh  退票单号
	 * @param shbh  商户编号
	 * @return
	 */
    public Map<String,Object> selectTpFhByTfdh(String tpdh,String shbh){
    	return this.getMyBatisDao().selectTpFhByTfdh(tpdh, shbh);
    }
    
    /**
     * 查询历史退票数据
     * @param map
     * @return
     */
    public List<Map<String, Object>> getHistoryTpList(Map<String, Object> map){
    	return this.getMyBatisDao().getHistoryTpList(map);
    }
    /**
     * 根据条件查询历史退票数据
     * @param map
     * @return
     */
    public Map<String, Object> getjptpdobject(Map<String, Object> map){
    	return this.getMyBatisDao().getjptpdobject(map);
    }
    
    public void saveTpdHistory(Map<String, Object> map) throws Exception{
    	try {
			this.getMyBatisDao().saveTpdHistory(map);
		} catch (Exception e) {
			throw e;
		}
    }

    
    public  Map<String,String>  cancelSeat(boolean isAuto,String tpdh,Shyhb shyhb,JpKhdd jpKhdd,JpPz jppz){
    	String shbh=shyhb.getShbh();
    	JpTpd jptpd=this.getJpTpdByTpdh(tpdh, shbh);
    	Map<String,String>  map=new HashMap<String,String>();
    	TpXeZw tpxeZw =SpringContextUtil.getBean(TpXeZw.class);
		try {
			tpxeZw.xezw(isAuto, shyhb, jptpd,jpKhdd,jppz);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return map;
    }
    
    
    public List<Map<String,Object>> TpBbQuery(Map<String, Object> param){
    	return this.getMyBatisDao().TpBbQuery(param);
    }
   
    /**
     * 跟据平台退票单号查询退废票
     * @param shbh
     * @param cgtpdh
     * @param cgdw
     * @return
     */
    public JpTpd getTpdByCgtpdh(String cgtpdh,String cgdw){
    	return this.getMyBatisDao().getTpdByCgtpdh(cgtpdh, cgdw);
    }
    /**
     * 根据订单号,采购退票状态以及票号查询退废票单
     * @param param
     * @return
     */
    public JpTpd getJpTpdByddbh(Map<String, Object> param){
    	return this.getMyBatisDao().getJpTpdByddbh(param);
    }
    
    /**
	 * 获取淘宝代购订单信息
	 * @param tpdh
	 * @param shbh
	 * @return
	 */
    public Map<String,Object> getTaobaoDg(String shbh,String tpdh){
    	return this.getMyBatisDao().getTaobaoDg(shbh, tpdh);
    }
    
    /**
	 * 通过商户编号获取淘宝代购订单信息
	 * @param tpdh
	 * @param shbh
	 * @return
	 */
    public List<JpTpd> getTaobaoDgByShbh(Map<String, Object> param){
    	return this.getMyBatisDao().getTaobaoDgByShbh(param);
    }
    
	/**
	 * 验证退票单票号是否已经存在
     * @param tpphs 多个用逗号分隔
     * @param cgdw
	 * @return
	 */
	public Map<String,String> checkTpdByPh(Map<String,Object> param){
		return this.getMyBatisDao().checkTpdByPh(param);
	}
	
	
	/**
	 * 检查客票状态
	 * @param ticket_returnrecord_service
	 * @param ticket_returnrecord
	 * @param ve_yhb
	 * @param sc
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> checkKpzt(boolean isAuto,JpTpd jptpd,JpTpdMx mx,Shyhb sh_yhb,JpPz jppz) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//1)DETR检查客票状态
		String szdh_tkno=mx.getTkno().replace("-", "");
		String tkno= mx.getTkno();
		String shbh=sh_yhb.getShbh();
		List<JpHd> tphdList = null;
		try {
			tphdList =jpHdServiceImpl.getJpHdByTpMxId(mx.getId(), shbh);
			if (CollectionUtils.isEmpty(tphdList)) {
				map.put("STATUS", "-1");
				map.put("ERROR", "取退票航段失败，未知错误");
				return map;
			} 

			DetrXmlParam detrXmlParam=new DetrXmlParam();
			detrXmlParam.setTkno(szdh_tkno);
			detrXmlParam.setUserid(sh_yhb.getPidyh());//
			detrXmlParam.setUrl("http://"+jppz.getPzIp()+":"+jppz.getPzPort());//
			
			DetrResult detrResult=IbeService.detrXml(detrXmlParam);
			if (detrResult == null) {
				map.put("STATUS", "-1");
				map.put("ERROR", "DETR票号信息异常");
				return map;
			}
			
			List<Segment> segments =detrResult.getSegments();
			if (CollectionUtils.isEmpty(segments)) {
				map.put("STATUS", "-1");
				map.put("ERROR", "DETR无航段信息");
				return map;
			} 
			String kpzt="";
			for(Segment s:segments ){
			    kpzt=s.getKpzt();
			    if("REFUNDED".equals(kpzt) && segments.size() == 1){
			    	map.put("STATUS", "0");
					map.put("ERROR", "票号["+tkno+"]已退票");
					PzSuper.zdtpJkLog(false,true,jptpd, "票号:["+tkno+"]的客票状态为:<font color=green>"+kpzt+"</font>", jpZdtpJkServiceImpl);
			    	return map;
			    }
				if (!("OPEN FOR USE".equals(kpzt) || "SUSPENDED".equals(kpzt))) {
					map.put("STATUS", "-2");
					map.put("ERROR", "客票状态为[" + kpzt + "]不能申请全自动退票");
					PzSuper.zdtpJkLog(isAuto,jptpd, "票号:["+tkno+"]的客票状态为:<font color=red>"+kpzt+"</font>", jpZdtpJkServiceImpl);
					return map;
				}
			}
			
			PzSuper.zdtpJkLog(isAuto,jptpd, "票号:["+tkno+"]的客票状态为:<font color=green>"+kpzt+"</font>", jpZdtpJkServiceImpl);
			//挂票状态需要调用 供应的解挂票接口
			if("SUSPENDED".equals(kpzt)){
				//1.取供应导单账号设置
				JpDdsz jpDdsz = new JpDdsz();
				jpDdsz.setWdid(jptpd.getWdid());
				jpDdsz = jpDdszServiceImpl.getEntityById(jpDdsz);
				if (jpDdsz == null || StringUtils.isBlank(jpDdsz.getWdpt())) {
					map.put("STATUS", "-2");
					map.put("ERROR", "网店Id[" + jptpd.getWdid() + "]没有对应的网店资料,无法对客票进行解挂");
					return map;
				}
				String url = jpDdsz.getDdJkdz();
				String appkey = jpDdsz.getDdJkzh();
				String secret = jpDdsz.getDdJkmm();
				String sessionKey = jpDdsz.getDdAqm1();
				TaobaoClient client = new DefaultTaobaoClient(url, appkey,secret);
				AlitripSellerRefundUnsuspendRequest req = new AlitripSellerRefundUnsuspendRequest();
				req.setApplyId(NumberUtils.toLong(jptpd.getWbdh()));
				req.setTicketNo(tkno);
				AlitripSellerRefundUnsuspendResponse rsp = client.execute(req,sessionKey);
				System.out.println("解挂接口返回：" + rsp.getBody());
				if (!rsp.isSuccess()) {
					map.put("STATUS", "-2");
					map.put("ERROR", "票号[" + tkno + "]解挂失败!");
					PzSuper.zdtpJkLog(isAuto,jptpd, "票号[" + tkno + "]解挂失败!", jpZdtpJkServiceImpl);
					return map;
				}
				PzSuper.zdtpJkLog(isAuto,jptpd, "票号[" + tkno + "]解挂成功!", jpZdtpJkServiceImpl);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("STATUS", "-1");
			map.put("ERROR", "取退票航段失败，" + e.getMessage());
		}
		
		return map;
	}
	
	/**
	 * BSP退票
	 * @param ticket_returnrecord_service
	 * @param ticket_returnrecord
	 * @param ve_yhb
	 * @param sc
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> trfd(boolean isAuto,boolean isHbyw,JpTpd jptpd,JpTpdMx mx,Shyhb sh_yhb,JpPz jppz) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String tkno= mx.getTkno();
		String shbh=sh_yhb.getShbh();
		List<JpHd> tphdList = null;
		try {
			tphdList =jpHdServiceImpl.getJpHdByTpMxId(mx.getId(), shbh);
			if (CollectionUtils.isEmpty(tphdList)) {
				map.put("STATUS", "-1");
				map.put("ERROR", "取退票航段失败，未知错误");
				return map;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			map.put("STATUS", "-1");
			map.put("ERROR", "取退票航段失败，" + e.getMessage());
			return map;
		}
		
		String url="http://"+jppz.getPzIp()+":"+jppz.getPzPort();
		TrfdParam param=new TrfdParam();
		param.setUserid(sh_yhb.getPidyh());
		param.setUrl(url);
		param.setPreview("0");
		String OperationType="AUTO";
		
		TrfdResult trfdResult = null;
		if(isHbyw || (!isAuto && "0".equals(jptpd.getCgSfzytp()))){
			OperationType="HALFAUTO";//航班延误和手动 退票采用半自动退票进行全退
			String CouponNo = "1000,0000,0000,0000";// 单程
			if (!"1".equals(mx.getHclx()) && CollectionUtils.isNotEmpty(tphdList)) {
				String one = "0";
				String two = "0";
				String three = "0";
				String four = "0";
				CouponNo = "";
				for(JpHd hd:tphdList){
					Short sxh=hd.getSxh();
					if (sxh == 1) {
						one = "1";
					} else if (sxh == 2) {
						two = "2";
					} else if (sxh == 3) {
						three = "3";
					} else if (sxh == 4) {
						four = "4";
					}
				}
				CouponNo += one + two + three + four + ",";
				if (tphdList.size() <= 4) {
					CouponNo += "0000,";
				}
				CouponNo += "0000,0000";
			}

			param.setConjunction("1");
			
			param.setCouponNo(CouponNo);
			// 面价
			param.setCrossRefound(String.valueOf(Math.abs(mx.getCgPj().doubleValue())));
			//货币类型
			if(StringUtils.isBlank(param.getMoneyType())){
				param.setMoneyType("CNY-2");
			}
			//付款方式
			if(StringUtils.isBlank(param.getFormofPayMent())){
				param.setFormofPayMent("CASH");
			}
			// 电子客票
			param.setEt("Y");
			//代理费率
			param.setCommission("0.0");
			//代理费
			
			RtKtParam rtktParam=new RtKtParam();
			rtktParam.setUrl(url);
			rtktParam.setUserid(sh_yhb.getPidyh());
			rtktParam.setTkno(tkno);
			RtKtResult rtktResult=null;

			try {
				rtktResult = IbeService.rtkt(rtktParam);
			} catch (EtermException ex) {
                ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			String Agencyfee = "0";
			if (rtktResult != null && "1".equals(rtktResult.getStatus())) {
				if (rtktResult.getResult() != null && rtktResult.getResult().getRtkt() != null) {
					Agencyfee=rtktResult.getResult().getRtkt().getAgentfee();
				}
			}
			Agencyfee = String.valueOf(NumberUtils.toInt(Agencyfee, 0));
			
			PzSuper.zdtpJkLog(isAuto, jptpd, "代理费为:" + Agencyfee,jpZdtpJkServiceImpl);
			
			param.setAgencyfee(Agencyfee);
			
			//退票费
			param.setDeduction("0");
			
			double tax=0.0;
			//国内退机建，国际退税费
			if ("1".equals(mx.getHclx())) {
				tax=Math.abs(mx.getCgJsf());
				param.setTax("CN,"+tax);
				
			}else{
				tax=Math.abs(mx.getCgTax());
				param.setTax("YQ,"+tax);
			}
			//应退金额
			param.setNetRefound(String.valueOf(Math.abs(mx.getCgPj().doubleValue()) + tax));
			LogUtil.getTrfd().error("退票明细ID["+mx.getId()+"]=>有航变,执行半自动退票指令");
			
			trfdResult = new TrfdResult();
			trfdResult.setCommission(param.getCommission());//代理费率
			trfdResult.setCommissionAmount(param.getAgencyfee());//代理费
			trfdResult.setGrossRefund(param.getCrossRefound());
			trfdResult.setTax(param.getTax());//税费
			String [] tknos=mx.getTkno().split("-");
			trfdResult.setAirlineCode(tknos[0]);//三字代号
			trfdResult.setTktNumber(tknos[1]);//票号
			trfdResult.setDeduction(param.getDeduction());//退票手续费
			trfdResult.setNetRefund(param.getNetRefound());//退款金额
		}
		param.setOperationType(OperationType);
		param.setTicketNo(mx.getTkno());
		String printno=mx.getPrintno();
		if(StringUtils.isBlank(printno)){
			printno=jppz.getPrintno();
		}
		String officeId=mx.getCpOfficeid();
		if(StringUtils.isBlank(officeId)){
			officeId=jppz.getOfficeid();
		}
		param.setPrinter(printno);
		param.setOfficeId(officeId);
		
		String Country = "1".equals(jptpd.getGngj()) ? "D" : "I";
		param.setCountry(Country);
		
			
		LogUtil.getTrfd().error("退票明细ID["+mx.getId()+"]=>退票指令入参:"+param.toXml());
		PzSuper.zdtpJkLog(isAuto,jptpd, "开始执行退票指令=><div style=\"word-wrap:break-word; word-break:break-all;\"><xmp>"+param.toXml()+"</xmp></div>", jpZdtpJkServiceImpl);
		//执行退票指令
		TrfdSubmitResult submitResult = null;
		try {
			submitResult = (TrfdSubmitResult) IbeService.trfd(param);
		} catch (Exception e) {
             e.printStackTrace();
		}
		LogUtil.getTrfd().error("退票明细ID["+mx.getId()+"]=>退票指令回参:"+XmlUtils.toXml(submitResult));
		if (submitResult == null) {
			map.put("STATUS", "-1");
			map.put("ERROR", "提取退票确认信息失败，错误：PID返回NULL,请及时手工处理！");
			return map;
		} 
		boolean isSucessed="1".equals(submitResult.getSucessed());
		if (isSucessed && submitResult.getResult1xml() == null) {
			submitResult.setResult1xml(trfdResult);
		}
		map.put("TrfdResult",submitResult.getResult1xml());
		map.put("STATUS", "0");
		map.put("ERROR", "");
		if(!isSucessed){
			map.put("STATUS", "-2");
			map.put("ERROR", "退票失败 !");
			PzSuper.zdtpJkLog(isAuto,jptpd, "指令返回信息=><div style=\"background:black;color:red\">"+submitResult.getResult()+"</div>", jpZdtpJkServiceImpl);
		}
		PzSuper.zdtpJkLog(isAuto,jptpd, "退票结果：<font color=" + (isSucessed ? "green" : "red") +">"+submitResult.getResult()+"</font>", jpZdtpJkServiceImpl);
		return map;
	}
	/**
	 * 获取wbtpzt=1,cgTpzt=3(采购办理完成)退票单
	 * @param wdid 网店id
	 * @param shbh 商户编号
	 * @return
	 */
	public List<JpTpd> getCgblwcTpd(String wdid, String shbh, String ywlx){
		return this.getMyBatisDao().getCgblwcTpd(wdid, shbh, ywlx);
	}
	/**
	 * 获取未审核、未办理；需要同步淘宝退票状态的退票单号集合
	 * @param wdid 网店id
	 * @param shbh 商户编号
	 * @return
	 */
	public List<String> getTbztTpdWbdh(String wdid, String shbh){
		return this.getMyBatisDao().getTbztTpdWbdh(wdid, shbh);
	}
	/**
	 * 根据退票单wbdh修改退票单信息
	 * @return
	 */
	public int updateTpdByWbdh(JpTpd jpTpd){
		return this.getMyBatisDao().updateTpdByWbdh(jpTpd);
	}
	
	public List<JpTpd> getTpdByWbdh(Map<String,Object> param){
		return this.getMyBatisDao().getTpdByWbdh(param);
	}
}
