package cn.vetech.web.vedsb.jpzwgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.BairwaycwCache;
import org.vetech.core.business.cache.BairwaycwCacheService;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.JsonBean;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsq;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqCjr;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqJcgzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqZdscServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqZwszServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.platpolicy.cps.response.pay.CpsShzhData;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.specialticket.request.KwCancelRequest;
import cn.vetech.vedsb.specialticket.request.KwOrderRequest;
import cn.vetech.vedsb.specialticket.response.KwCancelResponse;
import cn.vetech.vedsb.specialticket.response.KwOrderResponse;
import cn.vetech.vedsb.specialticket.response.KwResponse;
import cn.vetech.vedsb.specialticket.service.KwService;
import cn.vetech.vedsb.specialticket.util.KwUtil;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.ZwUtil;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpTjsqController extends MBaseControl<JpTjsq, JpTjsqServiceImpl>{
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private BairwaycwCacheService bairwaycwCacheService;
	
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	
	@Autowired 
	JpTjsqJcgzServiceImpl jpTjsqJcgzServiceImpl;
	
	@Autowired
	JpTjsqZwszServiceImpl jpTjsqZwszServiceImpl;
	
	@Autowired
	KwService kwService;//请求追位系统service
	
	@Autowired
	private JpTjsqZdscServiceImpl jpTjsqZdscServiceImpl;
	
	@Autowired
	private JpTjsqCjrServiceImpl jpTjsqCjrServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private CpsService cpsService;
	
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	
	@Override
	public void insertInitEntity(JpTjsq t) {
		Shyhb user = SessionUtils.getShshbSession();
		Date now=new Date();
		t.setSqDatetime(now);
		t.setSqUserid(user.getBh());
		t.setSqDeptid(user.getShbmid());
		t.setShbh(user.getShbh());
	}
	@Override
	public void updateInitEntity(JpTjsq t) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 保存并提交手工追位
	 * @return
	 */
	@RequestMapping
	public @ResponseBody JsonBean saveSgTjsq(HttpServletRequest request,Model model){
		String ddbh = StringUtils.trimToEmpty(request.getParameter("ddbh")); // 订单编号
		String qwjg = StringUtils.trimToEmpty(request.getParameter("qwjg")); // 前台传过来的要追位的价格
		String mbcw = StringUtils.trimToEmpty(request.getParameter("mbcw")); // 舱位--国际的
		String qwcw = StringUtils.trimToEmpty(request.getParameter("qwcw"));// 国内票选择价格时测算的舱位
		// 追位类型 0：国内按价格追位 1：国内按舱位追位 3:国际追
		String zwlx = StringUtils.trimToEmpty(request.getParameter("zwlx"));
		String zwfs = StringUtils.trimToEmpty(request.getParameter("zwfs"));
		Map<String,String> result=new HashMap<String,String>();
		result.put("status", "0");
		if (StringUtils.isBlank(zwfs)) {
			zwfs = "0";
		}
		try {
			Shyhb user=SessionUtils.getShshbSession();
			String shbh=user.getShbh();
			Map<String, String> zwParamMap=jpTjsqJcgzServiceImpl.getTjsqJcgzMap(shbh);
			if(!"1".equals(zwParamMap.get("SFZW"))){
				throw new Exception("请到追位设置==基础设置开启追位！");
			}
			int zwgzCount=jpTjsqZwszServiceImpl.getQygzCount(shbh);
			if(zwgzCount==0){
				throw new Exception("使用追位必须设置追位规则！");
			}
			
			//查询机票订单
			JpKhdd tj=new JpKhdd();
			tj.setDdbh(ddbh);
			tj.setShbh(shbh);
			JpKhdd jpKhdd=jpKhddServiceImpl.getEntityById(tj);
			if("4".equals(jpKhdd.getDdzt()) || "5".equals(jpKhdd.getDdzt())){
				throw new Exception("订单已取消，不可追位！");
			}
			if("0".equals(jpKhdd.getGngj())){
				zwlx="3";
			}
			//取未取消的追位申请单数量
			int yzwCount=this.baseService.countWqxTjsq(ddbh, shbh);
			if(yzwCount > 0){
				throw new Exception("该订单已申请了追位！");
			}
			Date now=VeDate.getNow();
			// 当前时间大于飞机的起飞时间，返回错误信息！
			if (now.after(jpKhdd.getCfrq())) {
				throw new Exception("已经超过了起飞时间！");
			}
			if(!"1".equals(jpKhdd.getHclx())){
				throw new Exception("只能追单程订单！");
			}
			List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jpKhdd.getDdbh(), jpKhdd.getShbh());
			double ddprice=0;
			for (JpKhddCjr cjr : cjrList) {
				if("3".equals(cjr.getCjrlx())){
					throw new Exception("有婴儿的订单不可追位！");
				}
				ddprice=cjr.getCgPj().doubleValue();
			}
			List<BairwaycwCache> cwList=bairwaycwCacheService.getEzdh(jpKhdd.getHkgs());
			String qfrq=VeDate.dateToStr(jpKhdd.getCfrq());//起飞日期
			if("1".equals(zwlx)){
				qwjg="0";
				if(StringUtils.isBlank(qwcw)){
					throw new Exception("指定舱位追位时必须输入期望舱位!");
				}
				BairwaycwCache ddCabin=this.getBairwayCabin(jpKhdd.getCgCw(), cwList,qfrq);
				BairwaycwCache mbCabin=this.getBairwayCabin(qwcw, cwList,qfrq);
				if(mbCabin==null){
					throw new Exception("期望舱位无效!");
				}
				if(!ddCabin.getCwdjlx().equals(mbCabin.getCwdjlx())){
					throw new Exception("只能追同等级的舱位!");
				}
				boolean hasSeat=hasSeat(jpKhdd.getCgPnrZt());
				if(!hasSeat){
					String cw_return = getOriginalCabin(jpKhdd);
					if(qwcw.equals(cw_return)){
						throw new Exception("座位没订妥的订单只能追原舱位！");
					}
				}else{
					boolean isKzcw=isKzcw(ddCabin,cwList,qfrq,qwcw);
					if(!isKzcw){
						throw new Exception("不可追该舱位！");
					}
				}
				if ("0".equals(ddCabin.getIfgbyj())) {
					zwfs = "1";// 特价舱位就只能追指定舱位
				}
			}else {
				if (ddprice == 0) {
					throw new Exception("账单价获取有误！");
				}
				if(NumberUtils.toDouble(qwjg) >= ddprice){
					throw new Exception("价格必须低于原订单账单价，请重新输入！");
				}
			}
			Map<String,Object> tpXx=procedureServiceImpl.genZwddTpf(jpKhdd.getDdbh(),jpKhdd.getShbh());
			Object tpflObj=tpXx.get("p_tpfl");
			Object tpfObj=tpXx.get("p_tpf");
			double tpfl=tpflObj==null ? 0 : NumberUtils.toDouble(tpflObj.toString());//退票费等调后台取
			double tpf=tpfObj==null ? 0 : NumberUtils.toDouble(tpfObj.toString());
			List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
			if(hdList== null || hdList.isEmpty()){
				throw new Exception("没查到订单的航段信息！");
			}
			String hbsk_ = "00:00";
			int time_length = qfrq.length();
			if (time_length >= 16) {
				hbsk_ = qfrq.substring(11, 16);
			}
			JpKhddHd jphd=hdList.get(0);//追位都是单程的，航程只有一段
			JpTjsq jpTjsq=new JpTjsq();
			String sqdh= VeDate.getNo(6);//申请单号
			jpTjsq.setSqdh(sqdh);
			insertInitEntity(jpTjsq);
			jpTjsq.setLxr(jpKhdd.getNxr());
			jpTjsq.setLxsj(jpKhdd.getNxsj());
			jpTjsq.setLxdh(jpKhdd.getNxdh());
			jpTjsq.setHkgs(jpKhdd.getHkgs());
			jpTjsq.setCfcity(jphd.getCfcity());
			jpTjsq.setDdcity(jphd.getDdcity());
			jpTjsq.setCjrqs(jpKhdd.getCfrq());
			jpTjsq.setCjrqz(jpKhdd.getCfrq());
			jpTjsq.setJglx("1");//固定为按价格
			jpTjsq.setZkfw(0d);
			jpTjsq.setPjfw(NumberUtils.toDouble(qwjg,0));
			jpTjsq.setCjrs(cjrList.size());
			jpTjsq.setNffkyd("0"); // 0能否分开预定 nffkyd
			jpTjsq.setSfzd("1");//是否要求直达
			jpTjsq.setHbsks(hbsk_);//航班时刻始
			jpTjsq.setHbskz(hbsk_);//航班时刻止
			jpTjsq.setYxq(jpKhdd.getCfrq());//有效期
			jpTjsq.setDdbh(ddbh);
			jpTjsq.setSqZt("1");// 已申请
			jpTjsq.setCkDatetime(now);
			jpTjsq.setCkDeptid(user.getShbmid());
			jpTjsq.setCkUserid(user.getBh());
			jpTjsq.setZfFkf("0");
			jpTjsq.setZfZfje(0d);
			jpTjsq.setTkZfje(0d);
			jpTjsq.setBy3("订单管理特价预约申请");
			jpTjsq.setYpnrNo(jpKhdd.getCgPnrNo());
			jpTjsq.setGngj(jpKhdd.getGngj());
			jpTjsq.setHbh(jpKhdd.getCgHbh());
			jpTjsq.setSfwbdr("0");
			if("3".equals(zwlx)){//要追的舱位
				jpTjsq.setCw(mbcw);
			}else{
				jpTjsq.setCw(qwcw);
			}
			jpTjsq.setYcw(jpKhdd.getCgCw());
			String ypnrZt=hasSeat(jpKhdd.getCgPnrZt()) ? "1" : "0";
			jpTjsq.setYpnrZt(ypnrZt);
			jpTjsq.setSftjzw("0");// 是否提交追位 0否
			jpTjsq.setZwfs(zwfs);//1只追原舱位，0追原舱位及以下舱位
			List<JpJp> jpList=jpJpServiceImpl.getJpListByDDbh(ddbh, shbh);
			if(CollectionUtils.isNotEmpty(jpList)){
				jpTjsq.setCpDatetime(jpList.get(0).getCpDatetime());
			}
			int ycpCount = jpJpServiceImpl.countYcpByCptmDdbh(ddbh, shbh, VeDate.getStringDateShort()+" 00:00:00");
			if(ycpCount>0){//隔天追位
				jpTjsq.setZwlx("3");
			}else{
				jpTjsq.setZwlx("1");//0 自动   1手动   2预约单  3隔天追位
			}
			jpTjsq.setTpf(tpf);
			jpTjsq.setTpfl(tpfl);
			//是否追共享航班
			String isgx = ZwUtil.getValueByMap(zwParamMap, "GXZW", "0");
			if(!"0".equals(isgx)){
				String gxcjStr=ZwUtil.getValueByMap(zwParamMap, "GXCJ", "0");
				jpTjsq.setGxhbcj(NumberUtils.toDouble(gxcjStr));
			}
			jpTjsq.setGxhbsfzw(isgx);
			jpTjsq.setWdid(jpKhdd.getWdid());
			jpTjsq.setWdpt(jpKhdd.getWdpt());
			List<JpTjsqCjr> zwCjrList=new ArrayList<JpTjsqCjr>();
			for (JpKhddCjr cjr : cjrList) {
				JpTjsqCjr zwCjr=new JpTjsqCjr();
				zwCjr.setId(VeDate.getNo(6));
				zwCjr.setSqdh(sqdh);
				zwCjr.setCjr(cjr.getCjr());
				if(StringUtils.isBlank(cjr.getZjlx())){
					zwCjr.setZjlx("ID");
				}else {
					zwCjr.setZjlx(cjr.getZjlx());
				}
				zwCjr.setZjhm(cjr.getZjhm());
				zwCjr.setSfzwcg("0");//是否追位成功
				zwCjr.setCjrid(cjr.getId());
				zwCjr.setShbh(shbh);
				zwCjr.setYprice(cjr.getCgPj().doubleValue());
				zwCjrList.add(zwCjr);
			}
			try {
				this.baseService.saveJptjsq(jpTjsq,zwCjrList);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("追位订单保存失败！");
			}
			/**请求追位系统，申请追位*/
			KwOrderRequest kwRequest=KwUtil.tjsqToKwRequest(jpTjsq, zwCjrList);
			KwOrderResponse kwOrderResponse=kwService.sendOrder(shbh, kwRequest);
			if(KwResponse.SUCCESS.equals(kwOrderResponse.getCode())){//追位成功
				JpTjsq zwcgBean=new JpTjsq();
				zwcgBean.setShbh(shbh);
				zwcgBean.setSqdh(sqdh);
				zwcgBean.setSftjzw("1");
				zwcgBean.setSqZt(DictEnum.ZWDLSQZT.YSH.getValue());
				baseService.getMyBatisDao().updateByPrimaryKeySelective(zwcgBean);
			}else {
				JpTjsq zwcgBean=new JpTjsq();
				zwcgBean.setShbh(shbh);
				zwcgBean.setSqdh(sqdh);
				zwcgBean.setSftjzw("1");
				zwcgBean.setSqZt(DictEnum.ZWDLSQZT.ZSB.getValue());
				baseService.getMyBatisDao().updateByPrimaryKeySelective(zwcgBean);
				throw new Exception(kwOrderResponse.getError());
			}
			model.addAttribute("isSuccess", "0");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("status", "-1");
			result.put("errMsg", e.getMessage());
		}
		return new JsonBean(result);
	}
	/**
	 * 手动追位
	 * @param ddbh
	 * @return
	 */
	@RequestMapping
	public String toTjsq(@RequestParam(value = "ddbh") String ddbh,Model model){
		try {
			Shyhb user=SessionUtils.getShshbSession();
			String shbh=user.getShbh();
			Map<String, String> zwParamMap=jpTjsqJcgzServiceImpl.getTjsqJcgzMap(shbh);
			if(!"1".equals(zwParamMap.get("SFZW"))){
				throw new Exception("请到追位设置==基础设置开启追位！");
			}
			//检查是否开启追位规则
			int zwgzCount=jpTjsqZwszServiceImpl.getQygzCount(shbh);
			if(zwgzCount==0){
				throw new Exception("使用追位必须设置追位规则！");
			}
			//查询机票订单
			JpKhdd tj=new JpKhdd();
			tj.setDdbh(ddbh);
			tj.setShbh(shbh);
			JpKhdd jpKhdd=jpKhddServiceImpl.getEntityById(tj);
			if("4".equals(jpKhdd.getDdzt()) || "5".equals(jpKhdd.getDdzt())){
				throw new Exception("订单已取消，不可追位！");
			}
			model.addAttribute("gngj",jpKhdd.getGngj());
			//取未取消的追位申请单数量
			int yzwCount=this.baseService.countWqxTjsq(ddbh, shbh);
			if(yzwCount > 0){
				throw new Exception("该订单已申请了追位！");
			}
			Date now=VeDate.getNow();
			// 当前时间大于飞机的起飞时间，返回错误信息！
			if (now.after(jpKhdd.getCfrq())) {
				throw new Exception("已经超过了起飞时间！");
			}
			if(!"1".equals(jpKhdd.getHclx())){
				throw new Exception("只能追单程订单！");
			}
			double pj=0;
			List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jpKhdd.getDdbh(), jpKhdd.getShbh()); 
			for (JpKhddCjr cjr : cjrList) {
				if("3".equals(cjr.getCjrlx())){
					throw new Exception("有婴儿的订单不可追位！");
				}
				pj=cjr.getCgPj().doubleValue();
			}
			if(!"HL".equals(jpKhdd.getCgPnrZt())){
				pj=Arith.sub(pj, 1);
			}
			List<BairwaycwCache> cwList=bairwaycwCacheService.getEzdh(jpKhdd.getHkgs());
			String qfrq=VeDate.dateToStr(jpKhdd.getCfrq());//起飞日期
			String primary_cw = jpKhdd.getCgCw();
			BairwaycwCache ddCabin=this.getBairwayCabin(primary_cw, cwList,qfrq);
			String pnr_zt = jpKhdd.getCgPnrZt();
			boolean is_specialCabin = false;
			String kzCwStr="";
			if (null == ddCabin) {
				is_specialCabin = false;
			} else {
				is_specialCabin = "0".equals(ddCabin.getIfgbyj()) ? true : false;
				kzCwStr=queryCabinInfo(ddCabin, cwList, qfrq);
			}
			model.addAttribute("kzcw", kzCwStr);
			String cw_return="";
			if (!hasSeat(pnr_zt)) {//座位没有订妥只能追原舱位
				cw_return = getOriginalCabin(jpKhdd);
			} else if (!is_specialCabin) {//查找目标舱位
//				int ycpCount = jpJpServiceImpl.countYcpByCptmDdbh(ddbh, shbh, VeDate.getStringDateShort()+" 00:00:00");
//				if(ycpCount > 0){//隔天追位,在今天之前出的票
//					
//				}
				//调用后台获取目标舱位
			}
			model.addAttribute("oldpnr", jpKhdd.getCgPnrNo());
			String zkStr="";
			if(ddCabin != null && ddCabin.getZkl()> 0){
				double zk=Arith.mul(ddCabin.getZkl(), 100);
				zkStr= new BigDecimal(Double.toString(zk)).setScale(0, BigDecimal.ROUND_DOWN).toString();
			}else {
				zkStr="特价";
			}
			if(ddCabin != null){
				model.addAttribute("oldcabin", ddCabin.getCwdj()+"("+zkStr+") "+ddCabin.getCwmc());
			}
			model.addAttribute("cw_return",cw_return);
			model.addAttribute("is_specialCabin", is_specialCabin);
			model.addAttribute("price",pj);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status","-1");
			model.addAttribute("error",e.getMessage());
		}
		return viewpath("sgzw");
	}
	/**
	 * 在b_airway_cw中匹配舱位
	 * @param cabins
	 * @param cws
	 * @param qfrq
	 * @return
	 */
	private BairwaycwCache getBairwayCabin(String cabin, List<BairwaycwCache> cws, String qfrq) {
		Map<String, BairwaycwCache> result = new HashMap<String, BairwaycwCache>();
		String today=VeDate.getStringDateShort();
		for (BairwaycwCache cb : cws) {
			if (!(cb.getCwdj()).equals(cabin)) { // 如果不是需要的舱位的不处理
				continue;
			}
			String yxq = StringUtils.isBlank(cb.getYxq()) ? "1900-01-01" : cb.getYxq();
			String sxrq = StringUtils.isBlank(cb.getBy5()) ? "1900-01-01" : cb.getBy5();
			int day = VeDate.getTwoDay(qfrq, yxq);
			int day1 = VeDate.getTwoDay(today, sxrq);
			// 查询日期必须大于有效期
			// 当天必须大于生效日期
			// 获取最近的一个有效期
			if (day >= 0 && day1 >= 0) {
				if (!result.containsKey(cb.getCwdj())
						|| VeDate.getTwoDay(result.get(cb.getCwdj()).getYxq(), cb.getYxq()) < 0) {
					result.put(cb.getCwdj(), cb);
				}
			}
		}
		return result.get(cabin);
	}
	/**
	 * 获取可追舱位
	 * @param cw
	 * @param cws
	 * @return
	 */
	public String queryCabinInfo(BairwaycwCache cabin,List<BairwaycwCache> cws,String cfrq) {
		List<BairwaycwCache> queryCabinList = new ArrayList<BairwaycwCache>();//普通
		List<BairwaycwCache> tjCabinLlist = new ArrayList<BairwaycwCache>();//特价
		String today=VeDate.getStringDateShort();
		String cwdjlx=cabin.getCwdjlx();
		double ycwZkl=cabin.getZkl();
		String cwdj=cabin.getCwdj();
		String ifgbyj=cabin.getIfgbyj();
		for (BairwaycwCache onebean : cws) {
			if(!cwdjlx.equals(onebean.getCwdjlx())){//只能追同等级舱位
				continue;
			}
			// 查询日期必须大于有效期
			// 当天必须大于生效日期
			if(StringUtils.isNotBlank(onebean.getBy5())){
				int day = VeDate.getTwoDay(today, onebean.getBy5());
				if(day<0){
					continue;
				}
			}
			if(StringUtils.isNotBlank(onebean.getYxq())){
				int day = VeDate.getTwoDay(cfrq, onebean.getYxq());
				if(day < 0){
					continue;
				}
			}
			//普通舱(找折扣比原舱位低的,如果原舱位是特价舱则不可追普通舱
			if(!"0".equals(ifgbyj) && !"0".equals(onebean.getIfgbyj()) && onebean.getZkl() !=null 
					&& onebean.getZkl()>0 && onebean.getZkl() < ycwZkl){
				queryCabinList.add(onebean);
			}
			if (!"0".equals(cwdjlx)) {//只有经济舱可以追特价舱
				continue;
			}
			if("006".equals(onebean.getBy6())){
				//特价舱追位只能追不是原舱位的特价舱
				if("0".equals(ifgbyj)){
					if(!cwdj.equals(onebean.getCwdj())){
						tjCabinLlist.add(onebean);
					}
				}else {
					tjCabinLlist.add(onebean);
				}
			}
		}
		StringBuffer cwsStr=new StringBuffer();
		for (BairwaycwCache ptCw : queryCabinList) {
			double zk=Arith.mul(ptCw.getZkl(), 100);
			String zkStr= new BigDecimal(Double.toString(zk)).setScale(0, BigDecimal.ROUND_DOWN).toString();
			cwsStr.append(ptCw.getCwdj()+"("+zkStr+"),");
		}
		if(queryCabinList.size()>0 && tjCabinLlist.size()>0){
			cwsStr.append("<br>");
		}
		for (BairwaycwCache tjCw : tjCabinLlist) {
			cwsStr.append(tjCw.getCwdj()+"(特价),");
		}
		if(StringUtils.isNotBlank(cwsStr.toString())){
			return cwsStr.substring(0, cwsStr.length()-1);
		}else {
			return "";
		}
	}
	/**
	 * 判断是否为可追舱位
	 * @param cw
	 * @param cws
	 * @param mbcw
	 * @return
	 */
	public boolean isKzcw(BairwaycwCache cabin,List<BairwaycwCache> cws,String cfrq,String mbcw) {
		String today=VeDate.getStringDateShort();
		String cwdjlx=cabin.getCwdjlx();
		double ycwZkl=cabin.getZkl();
		String cwdj=cabin.getCwdj();
		String ifgbyj=cabin.getIfgbyj();
		for (BairwaycwCache onebean : cws) {
			if(!cwdjlx.equals(onebean.getCwdjlx())){//只能追同等级舱位
				continue;
			}
			if(!mbcw.equals(onebean.getCwdj())){
				continue;
			}
			// 查询日期必须大于有效期
			// 当天必须大于生效日期
			if(StringUtils.isNotBlank(onebean.getBy5())){
				int day = VeDate.getTwoDay(today, onebean.getBy5());
				if(day<0){
					continue;
				}
			}
			if(StringUtils.isNotBlank(onebean.getYxq())){
				int day = VeDate.getTwoDay(cfrq, onebean.getYxq());
				if(day < 0){
					continue;
				}
			}
			//普通舱(找折扣比原舱位低的,如果原舱位是特价舱则不可追普通舱
			if(!"0".equals(ifgbyj) && !"0".equals(onebean.getIfgbyj()) && onebean.getZkl() !=null 
					&& onebean.getZkl()>0 && onebean.getZkl() < ycwZkl){
				return true;
			}
			if (!"0".equals(cwdjlx)) {//只有经济舱可以追特价舱
				continue;
			}
			if("006".equals(onebean.getBy6())){
				//特价舱追位只能追不是原舱位的特价舱
				if("0".equals(ifgbyj)){
					if(!cwdj.equals(onebean.getCwdj())){
						return true;
					}
				}else {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 无座PNR状态库
	 */
	private static List<String> PNR_ZTS = new ArrayList<String>();
	{
		PNR_ZTS.add("LL");
		PNR_ZTS.add("HL");
		PNR_ZTS.add("HN");
		PNR_ZTS.add("NN");
		PNR_ZTS.add("DW");
		PNR_ZTS.add("GL");
		PNR_ZTS.add("PL");
		PNR_ZTS.add("TN");
		PNR_ZTS.add("TL");
		PNR_ZTS.add("UU");
		PNR_ZTS.add("US");
		PNR_ZTS.add("NO");
	}
	/**
	 * 根据pnr_zt判断改pnr是否有座位
	 * 
	 * @param pnr_zt
	 *            pnr状态
	 */
	private boolean hasSeat(String pnr_zt){
		if (PNR_ZTS.contains(pnr_zt)) {
			return false;
		}
		return true;
	}
	private String getOriginalCabin(JpKhdd jpKhdd) throws Exception{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("p_ezdh", jpKhdd.getHkgs());
		param.put("p_cwdj", jpKhdd.getCgCw());
		param.put("p_hc", jpKhdd.getHc());
		String cfrq=VeDate.dateToStr(jpKhdd.getCfrq());
		param.put("p_date", cfrq);
		param.put("p_hbh", jpKhdd.getCgHbh());
		List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jpKhdd.getDdbh(), jpKhdd.getShbh()); 
		double zdj = 0.0;
		if (!CollectionUtils.isEmpty(cjrList)) {
			for (JpKhddCjr cjr : cjrList) {
				if ("1".equals(cjr.getCjrlx()) || "2".equals(cjr.getCjrlx())) {
					zdj = cjr.getCgPj().doubleValue();
					break;
				}
			}
		}
		if (zdj < 0) {
			throw new Exception("账单价获取有误");
		}
		param.put("p_zdj", zdj);
		return procedureServiceImpl.getOriginalCabin(param);
	}
	
	@RequestMapping
	public @ResponseBody Page shZwPagelist(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
		@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
		@RequestParam(value = "sortType", defaultValue = "desc") String sortType,HttpServletRequest request,String lb,Model model){
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "",true);
			String hc= request.getParameter("hc");
			if(StringUtils.isNotBlank(hc)){
				if(hc.length() == 6){//航程拆分为城市查询
					String cfcity = hc.substring(0,3);
					String ddcity = hc.substring(3,6);
					searchParams.put("cfcity", cfcity);
					searchParams.put("ddcity", ddcity);
				}else if(hc.length() == 7){
					String cfcity = hc.substring(0,3);
					String ddcity = hc.substring(4,7);
					searchParams.put("cfcity", cfcity);
					searchParams.put("ddcity", ddcity);
				}
			}
			String rqlx = request.getParameter("rqlx");
			String ksrq = request.getParameter("ksrq");
			String jsrq = request.getParameter("jsrq");
			if("2".equals(rqlx)){
				searchParams.put("sqdatetimebegin", ksrq);
				searchParams.put("sqdatetimeend", jsrq);
			}else if("3".equals(rqlx)){
				searchParams.put("qftimebegin", ksrq);
				searchParams.put("qftimeend", jsrq);
			}
			PageHelper ph = new PageHelper();
			searchParams.put("start", ph.getStart(pageNum, pageSize));
			searchParams.put("count", ph.getCount(pageNum, pageSize));
			Shyhb yhb = SessionUtils.getShshbSession();
			searchParams.put("shbh", yhb.getShbh());
			Page page = null;
			if("wjzwdl".equals(lb)){
				page = this.jpTjsqZdscServiceImpl.wjdlQueryPage(searchParams, pageNum, pageSize);
			}else if("zwcgwcl".equals(lb)){
				page = this.baseService.zwcgwclQueryPage(searchParams, pageNum, pageSize);
			}else if("zwcgycy".equals(lb)){
				page = this.baseService.zwcgcyQueryPage(searchParams, pageNum, pageSize);
			}else if("zwcgqx".equals(lb)){
				page = this.baseService.zwcgqxQueryPage(searchParams, pageNum, pageSize);
			}else if("zwcgyl".equals(lb)){
				page = this.baseService.zwcgylQueryPage(searchParams, pageNum, pageSize);
			}else{
				page = this.baseService.shQueryPage(searchParams, pageNum, pageSize);
			}
			List<?> list = page.getList();
			for(int i=0;i<list.size();i++){
				Map<String, Object> map = (Map<String, Object>)list.get(i);
				String cjr = (String)map.get("ZCJR");
				if(StringUtils.isBlank(cjr)){
					cjr = (String)map.get("CJR");
				}
				if(StringUtils.isNotBlank(cjr)){
					if(cjr.length()>10){
						cjr = "<span title='"+VeStr.replace(cjr)+"'>"+cjr.substring(0,10)+"...</span>";
					}
					map.put("ccjr", cjr);
				}
				String BY3 = (String)map.get("BY3");
				if(StringUtils.isNotBlank(BY3)){
					if(BY3.length()>10){
						BY3 = "<span title='"+VeStr.replace(BY3)+"'>"+cjr.substring(0,10)+"...</span>";
					}
					map.put("by3s", BY3);
				}
			}
			page.setList(list);
			attachService.shyhb("SQ_USERID", yhb.getShbh()).execute(list);
			return page;
	}
	
	/**
	 * 调取cps接口获取账户余额
	 * @return
	 */
	@RequestMapping
	public @ResponseBody Map<String, Object> getzhye(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			CpsShzhData zh=cpsService.getZwZh(yhb.getShbh(), "");
			String kyye = zh.getKyye();//可用余额
			String zszhye = zh.getZszhye();//赠送账户余额
			String total = String.valueOf(Arith.add(NumberUtils.toDouble(kyye), NumberUtils.toDouble(zszhye)));
			map.put("total", total);
			map.put("kyye", kyye);
			map.put("zszhye", zszhye);
			map.put("gg", zh.getGg());
			map.put("error", "");
		} catch (Exception e) {
			map.put("error","获取余额失败：追位系统商户信息配置错误，请检查CPS接口配置");
		}
		return map;
	}
	
	/**
	 * 根据申请单号取消追位订单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String qxzwd(String id){
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			String shbh = yhb.getShbh();
			JpTjsqCjr sqcjr = this.jpTjsqCjrServiceImpl.getjptjsqcjr(shbh, id);//根据申请单号获取乘机人
			JpTjsq tjsq = this.baseService.gettjsq(shbh, id);//根据申请单号获取追位订单
			boolean flag = true;
			if(null != sqcjr){
				if(DictEnum.ZWCJRZWZT.ZCG.getValue().equals(sqcjr.getSfzwcg()) || DictEnum.ZWCJRZWZT.YCL.getValue().equals(sqcjr.getSfzwcg())){
					flag = false;
					return "2";//追位订单不能取消
				}
				if(flag){
					KwCancelRequest request=new KwCancelRequest();
					request.setSqdh(id);
					KwCancelResponse response=kwService.cancelOrder(shbh, request);
					if(KwResponse.FAILL.equals(response)){
						return "3";//通知追位系统取消追位单失败，请联系管理员
					}else{
						
						sqcjr.setSfzwcg("3");
						sqcjr.setClDatetime(new Date());//处理时间
						sqcjr.setClUserid(yhb.getBh());//处理人
						sqcjr.setClDeptid(yhb.getShbmid());//处理部门
						this.jpTjsqCjrServiceImpl.update(sqcjr);//更新乘机人状态(改成已取消)
						tjsq.setSqZt("6");
						this.baseService.update(tjsq);
						return "1";
					}
				}
			}else{
				return "0";
			}
		} catch (Exception e) {
			logger.error("取消追位订单失败，规则id为 "+id, e);
			return "0";
		}
		return null;
	}
	
	public String batchQxzwd(String ids) throws Exception{
		Shyhb yhb = SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		String[] id = ids.split(",");
		if(null!=id && id.length>0){
			for(int i=0;i<id.length;i++){
				JpTjsqCjr sqcjr = this.jpTjsqCjrServiceImpl.getjptjsqcjr(shbh, id[i]);//根据申请单号获取乘机人
				JpTjsq tjsq = this.baseService.gettjsq(shbh, id[i]);//根据申请单号获取追位订单
				boolean flag = true;
				if(null!=sqcjr){
					if(DictEnum.ZWCJRZWZT.ZCG.getValue().equals(sqcjr.getSfzwcg()) || DictEnum.ZWCJRZWZT.YCL.getValue().equals(sqcjr.getSfzwcg())){
						flag = false;
						return "2";//追位订单不能取消
					}
					if(flag){
						KwCancelRequest request=new KwCancelRequest();
						request.setSqdh(id[i]);
						KwCancelResponse response=kwService.cancelOrder(shbh, request);
						if(KwResponse.FAILL.equals(response)){
							return "3";//通知追位系统取消追位单失败，请联系管理员
						}else{
							sqcjr.setSfzwcg("3");
							sqcjr.setClDatetime(new Date());//处理时间
							sqcjr.setClUserid(yhb.getBh());//处理人
							sqcjr.setClDeptid(yhb.getShbmid());//处理部门
							this.jpTjsqCjrServiceImpl.update(sqcjr);//更新乘机人状态(改成已取消)
							
							tjsq.setSqZt("6");
							this.baseService.update(tjsq);
							return "1";
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * 批量取消申请单
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String batchQxzwds(String ids){
		try {
			return this.batchQxzwd(ids);
		} catch (Exception e) {
			logger.error("批量取消追位订单失败", e);
			e.printStackTrace();
			return "0";
		}
	}
	
	@ResponseBody
	@RequestMapping
	public String batchAllQxzwd(HttpServletRequest request){
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "",true);
			String hc= request.getParameter("hc");
			if(StringUtils.isNotBlank(hc)){
				if(hc.length() == 6){//航程拆分为城市查询
					String cfcity = hc.substring(0,3);
					String ddcity = hc.substring(3,6);
					searchParams.put("cfcity", cfcity);
					searchParams.put("ddcity", ddcity);
				}else if(hc.length() == 7){
					String cfcity = hc.substring(0,3);
					String ddcity = hc.substring(4,7);
					searchParams.put("cfcity", cfcity);
					searchParams.put("ddcity", ddcity);
				}
			}
			String rqlx = request.getParameter("rqlx");
			String ksrq = request.getParameter("ksrq");
			String jsrq = request.getParameter("jsrq");
			if("1".equals(rqlx)){//不同日期类型查询
				searchParams.put("ckdatetimebegin", ksrq);
				searchParams.put("ckdatetimeend", jsrq);
			}else if("2".equals(rqlx)){
				searchParams.put("sqdatetimebegin", ksrq);
				searchParams.put("sqdatetimeend", jsrq);
			}else if("3".equals(rqlx)){
				searchParams.put("qftimebegin", ksrq);
				searchParams.put("qftimeend", jsrq);
			}
			Shyhb yhb = SessionUtils.getShshbSession();
			searchParams.put("shbh", yhb.getShbh());
			List<Map<String, Object>> list = this.baseService.getzwdlList(searchParams);
			if(CollectionUtils.isNotEmpty(list)){
				StringBuffer s = new StringBuffer();
				for(Map<String, Object> map : list){
					s.append(map.get("SQDH")+",");
				}
				String sqdhs = s.toString();
				sqdhs = sqdhs.substring(0,sqdhs.length()-1);
				return this.batchQxzwd(sqdhs);
			}
		} catch (Exception e) {
			logger.error("批量取消追位订单失败", e);
			e.printStackTrace();
			return "0";
		}
		return null;
	}
	
//	private void myCreateCell(int cellnum, String value, HSSFRow row, HSSFCell cell) {  
//	    cell = row.createCell((short) cellnum);  
//		cell.setCellValue(new HSSFRichTextString(value));  
//	} 
//	
//	@RequestMapping
//	public String toExportzwcy(HttpServletResponse response,HttpServletRequest request){
//		try {
//			Shyhb yhb = SessionUtils.getShshbSession();
//			if(null == yhb){
//				throw new Exception("无权导出");
//			}
//			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "",true);
//			searchParams.put("shbh", yhb.getShbh());
//			List<Map<String, Object>> list = this.baseService.getAllzwcgcyList(searchParams);
//			
//		} catch (Exception e) {
//			logger.error("导出追位已采用订单失败",e);
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public String generateOutExcel(List<Map<String, Object>> list,HttpServletRequest request){
//		try {
//			String contentPath = request.getSession().getServletContext().getRealPath("/");
//			String sourceFilePath = contentPath + "updownFiles" + File.separator + "mb" +File.separator + "";
//			File srcFile = new File(sourceFilePath);
//			String resPath = "updownFiles"+File.separator+"export"+File.separator + VeDate.getNo(4);
//			String destFilePath = contentPath + resPath;
//			String destFilePathName = destFilePath + File.separator+"追位已采用订单.xls";
//			File destpathFile = new File(destFilePath);
//			File destpathNameFile = new File(destFilePathName);
//			if(!destpathFile.exists()){
//				destpathFile.mkdirs();
//			}
//			if(!destpathNameFile.exists()){
//				destpathNameFile.createNewFile();
//			}
//			 FileUtils.copyFile(srcFile, destpathNameFile);
//			 HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(srcFile));
//			 HSSFSheet sheet = workbook.getSheetAt(0);
//			 HSSFRow row = null;  
//			 HSSFCell cell = null;  
//			 int rownum = 2; //添加的起始行  
//			 for(int i=0 ;i<list.size();i++){
//				 row = sheet.createRow(rownum);
//				 Map<String, Object> map = list.get(i);
//				 
//			 }
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return null;
//	}
}
