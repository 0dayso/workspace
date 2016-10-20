package cn.vetech.web.vedsb.jzcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mapper.JsonMapper;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Exceptions;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtbjHfsz;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtbjHfszServiceImpl;
import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.cps.CpsOrderParam;
import cn.vetech.vedsb.platpolicy.cps.CpsSearchParam;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.CreateOrderResponse;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.platpolicy.cpslink.PlatOrderParam;
import cn.vetech.vedsb.platpolicy.cpslink.PlatSearchParam;
import cn.vetech.vedsb.platpolicy.cpslink.response.GetRealtimePolicyRes;
import cn.vetech.vedsb.platpolicy.cpslink.response.PlatPolicyBean;
import cn.vetech.vedsb.platpolicy.cpslink.response.SubmitOrderRes;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkService;
import cn.vetech.vedsb.platpolicy.taobao.TaobaobuyerService;
import cn.vetech.vedsb.platpolicy.taobao.response.TBSubmitOrderRes;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class PlatPolicyController extends AbstractBaseControl{
	@Autowired
	private CpslinkService cpslinkService;
	@Autowired
	private CpsService cpsService;
	@Autowired
	private TaobaobuyerService tbTaobaobuyerService;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private JpPtbjHfszServiceImpl jpPtbjHfszServiceImpl;
	/**
	 * <查询全部政策>
	 * 
	 * @param model
	 * @param platCode
	 * @param ddbh
	 * @param request
	 */
	@RequestMapping
	public String searchPolicy(Model model,String platCode,String ddbh,HttpServletRequest request) {
		Shyhb yhb=SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		Shcsb csBean=shcsbServiceImpl.findCs("2021", shbh);
		String cs2021_1="2";
		String cs2021_2="2";
		if(csBean!=null){
			if(StringUtils.isNotBlank(csBean.getCsz1())){
				cs2021_1=csBean.getCsz1();
			}
			if(StringUtils.isNotBlank(csBean.getCsz2())){
				cs2021_2=csBean.getCsz2();
			}
		}
		int maxSize=NumberUtils.toInt(cs2021_2,2);
		ExecutorService executor=null;
		try {
			if(StringUtils.isBlank(platCode)){
				throw new Exception("至少要选择一个平台");
			}
			int poolSize=0;
			boolean loadCps=false;
			boolean loadTb=false;
			boolean loadPlat=false;
			
			if(platCode.contains(PlatCode.CPS)){
				loadCps=true;
				platCode=platCode.replace(PlatCode.CPS+",", "").replace(PlatCode.CPS, "");
				poolSize++;
			}
			if(platCode.contains(PlatCode.TB)){
				loadTb=true;
				platCode=platCode.replace(PlatCode.TB+",", "").replace(PlatCode.TB, "");
				poolSize++;
			}
			if(StringUtils.isNotBlank(platCode)){
				loadPlat=true;
				poolSize++;
			}
			List<PolicyItem> allPolicy=new ArrayList<PolicyItem>();
			executor=Executors.newFixedThreadPool(poolSize);
			List<Future<List<PolicyItem>>> futureList=new ArrayList<Future<List<PolicyItem>>>();
			Future<List<PolicyItem>> tbFuture=null;
			if(loadCps){
				CpsSearchParam cpsSearchParam=new CpsSearchParam();
				cpsSearchParam.setDdbh(ddbh);
				cpsSearchParam.setYhb(yhb);
				CpsSearchService cpsSearchService=new CpsSearchService(cpsService,cpsSearchParam);
				futureList.add(executor.submit(cpsSearchService));
			}
			if(loadTb){
				TbSearchService tbSearchService=new TbSearchService(tbTaobaobuyerService,ddbh,yhb);
				tbFuture=executor.submit(tbSearchService);
			}
			if(loadPlat){
				PlatSearchParam searchParam=new PlatSearchParam();
				searchParam.setDdbh(ddbh);
				searchParam.setPlatcodes(platCode);
				searchParam.setYhb(yhb);
				OpSearchService opSearchService=new OpSearchService(searchParam,cs2021_1,maxSize,cpslinkService);
				futureList.add(executor.submit(opSearchService));
			}
			for (Future<List<PolicyItem>> f : futureList ) {
				List<PolicyItem> oneList=f.get();
				if(CollectionUtils.isNotEmpty(oneList)){
					allPolicy.addAll(oneList);
				}
			}
			//淘宝的单独处理，最高一条去参与排序，其他的影藏，可以通过展开更多来显示
			List<PolicyItem> tbList=new ArrayList<PolicyItem>();
			if(tbFuture!=null){
				List<PolicyItem> oneList=tbFuture.get();
				if(CollectionUtils.isNotEmpty(oneList)){
					tbList.addAll(oneList);
				}
			}
			int tbSize=tbList.size();
			if(tbSize>0){
				allPolicy.add(tbList.get(0));
			}
			DataUtils.sortList(allPolicy, new String[]{"payfee,asc","cpsSortNum,asc"});
			//参数为1则取总体返点最高的前几条
			if("1".equals(cs2021_1) && CollectionUtils.isNotEmpty(allPolicy)){
				if(allPolicy.size()>maxSize){
					allPolicy=allPolicy.subList(0, maxSize);
				}
			}
			if(tbSize>1){//淘宝多条记录只显示第一条
				List<PolicyItem> tbHide=new ArrayList<PolicyItem>();
				for(int i=1;i<tbSize;i++){
					PolicyItem tbItem=tbList.get(i);
					tbItem.setHide("1");
					tbHide.add(tbItem);
				}
				int size=allPolicy.size();
				for(int i=0;i<size;i++){
					PolicyItem item=allPolicy.get(i);
					if(PlatCode.TB.equals(item.getPtzcbs())){//淘宝的
						allPolicy.addAll(i+1, tbHide);
						break;
					}
				}
				model.addAttribute("tbSize",tbSize);
			}
			boolean glhbm="1".equals(request.getParameter("sfglHbm"));
			boolean glth="1".equals(request.getParameter("sfglTj"));
			allPolicy=glHbmTjAndJshf(allPolicy, glhbm, glth,shbh);
			if(CollectionUtils.isEmpty(allPolicy)){
				throw new Exception("未查询到政策");
			}
			model.addAttribute("allPolicy",allPolicy);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error","查询平台政策出错："+ Exceptions.getMessageAsString(e));
		}finally{
			if(executor != null){
				executor.shutdown();
			}
		}
		return view("platpolicy");
	}
	/**
	 * <集中出票加载平台采购状态>
	 * 
	 * @param model
	 * @param ddbh
	 * @throws Exception 
	 */
	@RequestMapping
	public String loadOpStatus(Model model,String ddbh){
		Shyhb yhb=SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		JpCgdd cgdd=jpCgddServiceImpl.genDdByZfzt(ddbh, "1", shbh);//查找已支付的
		if(cgdd==null){
			cgdd=jpCgddServiceImpl.genDdByZfzt(ddbh,"0", shbh);
		}
		if(cgdd==null){
			cgdd=jpCgddServiceImpl.genDdByZfzt(ddbh,"4", shbh);//查找已取消的
		}
		if(cgdd!=null){
			String platName=PlatCode.getPtname(cgdd.getCgdw());
			cgdd.getEx().put("PLATNAME", platName);
		}
		model.addAttribute("cgdd",cgdd);
		return viewpath("opstatus");
	}
	/**
	 * <查询单个平台政策>
	 * 
	 * @param model
	 * @param platCode
	 * @param ddbh
	 * @param request
	 * @return [参数说明]
	 */
	@RequestMapping
	public String loadOneOp(Model model,String platCode,String ddbh,HttpServletRequest request){
		String platName=PlatCode.getPtname(platCode);
		try {
			Shyhb yhb=SessionUtils.getShshbSession();
			String shbh=yhb.getShbh();
			PlatSearchParam searchParam=new PlatSearchParam();
			searchParam.setDdbh(ddbh);
			searchParam.setPlatcodes(platCode);
			searchParam.setYhb(yhb);
			GetRealtimePolicyRes platRes=cpslinkService.getRealtimePolicy(searchParam);
			if(platRes==null){
				throw new Exception("调用link接口失败");
			}
			if(GetRealtimePolicyRes.FAILL.equals(platRes.getStatus())){
				throw new Exception(platRes.getMsg());
			}
			if(CollectionUtils.isEmpty(platRes.getPlatPolicyList())){
				throw new Exception("未查询到政策");
			}
			PlatPolicyBean platBean=platRes.getPlatPolicyList().get(0);
			if(platBean==null){
				throw new Exception("未查询到政策");
			}
			if("-1".equals(platBean.getStatus())){
				throw new Exception(platBean.getMsg());
			}
			if(CollectionUtils.isEmpty(platBean.getPolicyList())){
				throw new Exception("未查询到政策");
			}
			List<PolicyItem> allPolicy=cpslinkService.linkPolicyToItem(platBean.getPolicyList(),platBean.getPlatCode());
			boolean glhbm="1".equals(request.getParameter("sfglHbm"));
			boolean glth="1".equals(request.getParameter("sfglTj"));
			allPolicy=glHbmTjAndJshf(allPolicy, glhbm, glth,shbh);
			if(CollectionUtils.isEmpty(allPolicy)){
				throw new Exception("未查询到政策");
			}
			DataUtils.sortList(allPolicy,new String[]{"payfee,asc"});
			model.addAttribute("allPolicy", allPolicy);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "查找"+platName+"政策失败："+Exceptions.getMessageAsString(e));
		}
		return view("platpolicy");
	}
	/**
	 * <下单到link>
	 */
	@RequestMapping
	public String platOrder(String ddbh,String platcode,String policyId,String jsj,String isZddk,String policytype,Model model){
		Shyhb yhb=SessionUtils.getShshbSession();
		PlatOrderParam orderParam=new PlatOrderParam();
		orderParam.setDdbh(ddbh);
		orderParam.setIsQzd("0");
		orderParam.setJsj(jsj);
		orderParam.setPlatcode(platcode);
		orderParam.setPolicyId(policyId);
		orderParam.setYhb(yhb);
		orderParam.setAutopay(isZddk);
		orderParam.setPolicytype(policytype);
		try {
			SubmitOrderRes orderRes=cpslinkService.submitorder(orderParam);
			if(SubmitOrderRes.FAILL.equals(orderRes.getStatus())){
				throw new Exception(orderRes.getMsg());
			}
			if(!"1".equals(orderRes.getOrderStatus()) && CollectionUtils.isEmpty(orderRes.getPayLinkList())){
				throw new Exception("未获取到平台订单支付链接！");
			}
			model.addAttribute("res", orderRes);
			double jsjD=NumberUtils.toDouble(jsj,0);
			double payfee=NumberUtils.toDouble(orderRes.getPaymentAmount(),0);
			model.addAttribute("jezq", jsjD==payfee);
		} catch (Exception e) {
			e.printStackTrace();
			String error=StringUtils.trimToEmpty(e.getMessage());
			if(!error.contains("下单失败")){
				error="下单失败："+error;
			}
			model.addAttribute("error", error);
		}
		return view("platorder");
	}
	/**
	 * <淘宝代购>
	 */
	@RequestMapping
	@ResponseBody
	public String tbOrder(String ddbh,String platcode,String policyId,String jsj){
		Map<String, String> result=new HashMap<String, String>();
		result.put("status", "0");
		Shyhb yhb=SessionUtils.getShshbSession();
		PlatOrderParam orderParam=new PlatOrderParam();
		orderParam.setDdbh(ddbh);
		orderParam.setIsQzd("0");
		orderParam.setJsj(jsj);
		orderParam.setPlatcode(platcode);
		orderParam.setPolicyId(policyId);
		orderParam.setYhb(yhb);
		orderParam.setAutopay("1");
		try {
			TBSubmitOrderRes res=tbTaobaobuyerService.submitorder(orderParam);
			if(TBSubmitOrderRes.FAILL.equals(res.getStatus())){
				throw new Exception(res.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error=StringUtils.trimToEmpty(e.getMessage());
			if(!error.contains("下单失败")){
				error="下单失败："+error;
			}
			result.put("status", "-1");
			result.put("error", error);
		}
		return JsonMapper.nonEmptyMapper().toJson(result);
	}
	/**
	 * <CPS下单>
	 */
	@RequestMapping
	@ResponseBody
	public String cpsOrder(String ddbh,String platcode,String policyId,String jsj,String isZddk,String zdj,String zclx,String policytype){
		Map<String, String> result=new HashMap<String, String>();
		result.put("status", "0");
		Shyhb yhb=SessionUtils.getShshbSession();
		CpsOrderParam orderParam=new CpsOrderParam();
		orderParam.setDdbh(ddbh);
		orderParam.setPjCgj(zdj);
		orderParam.setPolicyId(policyId);
		orderParam.setPolicyJsj(jsj);
		orderParam.setYhb(yhb);
		orderParam.setZclx(zclx);
		orderParam.setAutopay(isZddk);
		orderParam.setIsQzd("0");
		orderParam.setPolicytype(policytype);
		try {
			CreateOrderResponse orderRes=cpsService.submitorder(orderParam);
			if("-1".equals(orderRes.getStatus())){
				String err=StringUtils.isNotBlank(orderRes.getErrMsgTip()) ? orderRes.getErrMsgTip() : orderRes.getErrorMessage();
				throw new Exception(err);
			}
			if(!"10".equals(orderRes.getOrderStatus()) && StringUtils.isBlank(orderRes.getPayurl())){
				throw new Exception("未获取到平台订单支付链接！");
			}
			result.put("payurl", orderRes.getPayurl());
			double jsjD=NumberUtils.toDouble(jsj,0);
			double payfee=NumberUtils.toDouble(orderRes.getPaymentAmount(),0);
			boolean isJezq= jsjD==payfee;
			result.put("jezq", String.valueOf(isJezq));
			result.put("jsj", jsj);
			result.put("payfee", payfee+"");
			if("10".equals(orderRes.getOrderStatus())){//已支付
				result.put("zfzt", "1");
			}else{
				result.put("zfzt", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error=StringUtils.trimToEmpty(e.getMessage());
			if(!error.contains("下单失败")){
				error="下单失败："+error;
			}
			result.put("status", "-1");
			result.put("error", error);
		}
		return JsonMapper.nonEmptyMapper().toJson(result);
	}
	/**
	 * <过滤换编码或特惠政策,并计算后返>
	 * 
	 * @param allPolicy
	 * @param glhbm
	 * @param glth
	 * @return List<PolicyItem> [返回类型说明]
	 */
	private List<PolicyItem> glHbmTjAndJshf(List<PolicyItem> allPolicy,boolean glhbm,boolean glth,String shbh){
		if(CollectionUtils.isEmpty(allPolicy)){
			return allPolicy;
		}
		Map<String, JpPtbjHfsz> hfMap=jpPtbjHfszServiceImpl.genPthf(shbh);
		if(hfMap==null && !glhbm && !glth){
			return allPolicy;
		}
		List<PolicyItem> policys=new ArrayList<PolicyItem>();
		for(PolicyItem item : allPolicy){
			if(glhbm && "1".equals(item.getChangerecord())){
				continue;
			}
			if(glth && "1".equals(item.getIsspecmark())){
				continue;
			}
			String platBs=item.getPtzcbs();
			if(StringUtils.isNotBlank(platBs) && hfMap!=null){
				jsHf(hfMap.get(platBs),item);
			}
			policys.add(item);
		}
		return policys;
	}
	/**
	 * <计算后返>
	 */
	private void jsHf(JpPtbjHfsz hf,PolicyItem item){
		if(hf == null){
			return;
		}
		double hffd=hf.getPthfds()==null ? 0 : hf.getPthfds();//后返返点
		hffd=Arith.div(hffd, 100);
		double hfje=hf.getPthfje()==null ? 0 : hf.getPthfje();//后返金额
		double scny=item.getPj_cgj()==null ? 0 : item.getPj_cgj();//总账单价
		double hflr=Arith.add(Arith.mul(hffd, scny), hfje);//后返利润
		hflr=Arith.cut(hflr, 1);
		item.setHfje(hflr);
	}
}
