package cn.vetech.vedsb.jp.service.jpzdcp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpZdcpB2bzh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpDbjpnr;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpZdcpB2bzhServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.b2b.service.B2bService;
import cn.vetech.vedsb.platpolicy.cps.CpsOrderParam;
import cn.vetech.vedsb.platpolicy.cps.CpsSearchParam;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.CreateOrderResponse;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.platpolicy.cpslink.PlatOrderParam;
import cn.vetech.vedsb.platpolicy.cpslink.PlatSearchParam;
import cn.vetech.vedsb.platpolicy.cpslink.response.SubmitOrderRes;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkService;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.bean.B2bpolicyBean;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bPolicyResponse;
import cn.vetech.vedsb.platpolicy.taobao.TaobaobuyerService;
import cn.vetech.vedsb.platpolicy.taobao.response.TBSubmitOrderRes;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.jzcp.CpsSearchService;
import cn.vetech.web.vedsb.jzcp.TbSearchService;
@Service
public class FindBestPolicy {
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private B2bService b2bService;
	@Autowired
	private CpslinkService cpslinkService;
	@Autowired
	private TaobaobuyerService tbTaobaobuyerService;
	@Autowired
	private CpsService cpsService;
	@Autowired
	private JpB2bHkgsServiceImpl jpB2bHkgsServiceImpl;
	@Autowired
	private JpZdcpB2bzhServiceImpl jpZdcpB2bzhServiceImpl;
	/**
	 * <功能详细描述>
	 * 
	 * @param dbmCp
	 * @param jk
	 * @param user
	 * @return [参数说明]
	 * 
	 * @return int [0 下单成功，-1失败,结束比价出票，-2结束全自动出票]
	 */
	public int bjCp(List<JpZdcpDbjpnr> dbjPnrList,JpZdcpJk jk,Shyhb user,String sfxyh,JpZdcpB2bzh dlzh,String office) {
		if(CollectionUtils.isEmpty(dbjPnrList)){
			jk.add("没有要比价出票的编码");
			return -2;
		}
		try {
			String ddbh=jk.getDdbh();
			boolean dbmBj= dbjPnrList.size()==2;//是否有两个编码比价出票
			String bjPt=jk.getBjCybjzc();
			if(StringUtils.isBlank(bjPt)){
				jk.add("没有设置参与比价的政策");
				return -1;
			}
			B2bpolicyBean b2bPolicy=null;
			if(bjPt.contains("BPET")){//查询B2B政策
				b2bPolicy=genB2bPolicy(dbjPnrList.get(0), jk, user, sfxyh);
				if(b2bPolicy==null && !dbmBj){
					if("2".equals(jk.getPtbjB2byccl())){
						jk.add("B2B没获取到政策，暂停比价出票");
						return -1;
					}else if("3".equals(jk.getPtbjB2byccl())){
						jk.add("B2B没获取到政策，结束全自动出票");
						return -2;
					}
				}
			}
			List<PolicyItem> items0=new ArrayList<PolicyItem>();
			JpZdcpDbjpnr dbjPnr0 = dbjPnrList.get(0);
			if(dbmBj){//查政策的底层都是基于机票订单、航程等信息来的，多编码比价时需要将订单采购信息改过来
				jpKhddServiceImpl.changeCabin(ddbh, jk.getShbh(), dbjPnr0);
			}
			int status=genPlatPolicy(dbjPnr0, jk, user, items0 , dbmBj);
			if(!dbmBj && status!=0){
				return status;
			}
			//过滤政策(并根据b2b政策过滤掉：航司B2B采购净价 - 后返 - OP采购净价大于等于jk.getBjCgjjc()时才选择OP政策出票)
			filterPolicy(items0,b2bPolicy,jk);
			List<PolicyItem> items2=new ArrayList<PolicyItem>();
			if(dbmBj){
				jpKhddServiceImpl.changeCabin(ddbh, jk.getShbh(), dbjPnrList.get(1));
				genPlatPolicy(dbjPnrList.get(1), jk, user, items2, dbmBj);
				//过滤政策(并根据b2b政策过滤掉：航司B2B采购净价 - 后返 - OP采购净价大于等于jk.getBjCgjjc()时才选择OP政策出票)
				filterPolicy(items0,b2bPolicy,jk);
			}
			if(b2bPolicy==null && CollectionUtils.isEmpty(items0) && CollectionUtils.isEmpty(items2)){
				jk.add("比价出票失败：比价没获取到政策");
				return -1;
			}
			//平台为最优政策
			if(CollectionUtils.isNotEmpty(items0) || CollectionUtils.isNotEmpty(items2)){
				jk.add("平台政策为最优政策");
				List<PolicyItem> allPtZc=new ArrayList<PolicyItem>();
				allPtZc.addAll(items0);
				allPtZc.addAll(items2);
				double hlfdc=jk.getPtbjPxhlfd()==null ? 0 : jk.getPtbjPxhlfd();
				double hlcz=0;
				if(hlfdc>0){
					JpKhdd jpKhdd=genJpKhddByddbh(jk.getDdbh(), jk.getShbh());
					hlcz=Arith.mul(jpKhdd.getCgZdj(), hlfdc);
				}
				PolicyItem item=genBestPtzc(allPtZc,hlcz,jk);
				if(items2.contains(item)){//
					jpKhddServiceImpl.changeCabin(jk.getDdbh(), jk.getShbh(), dbjPnrList.get(1));
				}else {
					jpKhddServiceImpl.changeCabin(jk.getDdbh(), jk.getShbh(), dbjPnrList.get(0));
				}
				if(PlatCode.TB.equals(item.getPtzcbs())){//淘宝出票
					jk.add("淘宝政策为最优政策开始出票");
					PlatOrderParam orderParam=new PlatOrderParam();
					orderParam.setDdbh(ddbh);
					orderParam.setIsQzd("1");
					orderParam.setJsj(item.getPayfee()+"");
					orderParam.setPlatcode(PlatCode.TB);
					orderParam.setPolicyId(item.getId());
					orderParam.setYhb(user);
					orderParam.setAutopay("1");
					TBSubmitOrderRes res=tbTaobaobuyerService.submitorder(orderParam);
					if(TBSubmitOrderRes.FAILL.equals(res.getStatus())){
						throw new Exception(res.getMsg());
					}
				}else if(PlatCode.CPS.equals(item.getPtzcbs())){//CPS出票
					jk.add("CPS政策为最优政策开始出票");
					CpsOrderParam orderParam=new CpsOrderParam();
					orderParam.setDdbh(ddbh);
					orderParam.setPjCgj(item.getPj_cgj()+"");
					orderParam.setPolicyId(item.getId());
					orderParam.setPolicyJsj(item.getPayfee()+"");
					orderParam.setYhb(user);
					orderParam.setZclx(item.getZclx());
					orderParam.setAutopay("1");
					orderParam.setIsQzd("1");
					orderParam.setPolicytype(item.getPolicytype());
					CreateOrderResponse orderRes=cpsService.submitorder(orderParam);
					if("-1".equals(orderRes.getStatus())){
						String err=StringUtils.isNotBlank(orderRes.getErrMsgTip()) ? orderRes.getErrMsgTip() : orderRes.getErrorMessage();
						throw new Exception(err);
					}
					if(!"10".equals(orderRes.getOrderStatus())){//未支付成功
						throw new Exception("CPS出票自动支付失败");
					}
				}else {//link出票
					String ptmc=item.getPtzcbs();
					jk.add(ptmc+"政策为最优政策开始出票");
					PlatOrderParam orderParam=new PlatOrderParam();
					orderParam.setDdbh(ddbh);
					orderParam.setIsQzd("1");
					orderParam.setJsj(item.getPayfee()+"");
					orderParam.setPlatcode(item.getPtzcbs());
					orderParam.setPolicyId(item.getId());
					orderParam.setYhb(user);
					orderParam.setAutopay("1");
					orderParam.setPolicytype(item.getPolicytype());
					SubmitOrderRes orderRes=cpslinkService.submitorder(orderParam);
					if(SubmitOrderRes.FAILL.equals(orderRes.getStatus())){
						throw new Exception(orderRes.getMsg());
					}
					jk.add(PlatCode.getPtname(ptmc)+"平台下单成功!平台订单编号:"+orderRes.getOrderNo());
					if(!"1".equals(orderRes.getOrderStatus())){
						throw new Exception(ptmc+"出票自动支付失败");
					}
				}
			}else {
				jk.add("B2B政策为最优政策，开始B2B出票");
				jpKhddServiceImpl.changeCabin(ddbh, jk.getShbh(), dbjPnrList.get(0));
				JpKhdd jpKhdd=genJpKhddByddbh(jk.getDdbh(), jk.getShbh());
				if(checkB2bCplr(b2bPolicy, jk,jpKhdd)){
					String error=b2bService.order2dgxt(jpKhdd, user, dlzh, sfxyh, office, b2bPolicy);
					if(StringUtils.isBlank(error)){//b2b代购成功
						jk.add("比价出票B2B出票成功");
						jk.setCpzt("35");//支付成功
						return 0;
					}else {//代购失败
						jk.add("比价出票B2B出票出票失败，"+error);
						return -1;
					}
				}else {
					jk.add("比价出票失败，B2B政策不满足最小利润,"+b2bPolicy.toString());
					return -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jk.add("比价出票失败："+e.getMessage());
			jpKhddServiceImpl.changeCabin(jk.getDdbh(), jk.getShbh(), dbjPnrList.get(0));//把订单采购pnr等改为低仓位的待比价编码
			return -1;
		}
		return 0;
	}
	private boolean checkB2bCplr(B2bpolicyBean b2bPolicy,JpZdcpJk jk,JpKhdd jpKhdd){
		//计算出票利润
		double drXjjsj=Arith.div(jpKhdd.getXsPj(), jpKhdd.getCjrs());
		long jjry=jpKhdd.getCgJsf()+jpKhdd.getCgTax();
		double drCgj=Arith.div(Arith.sub(b2bPolicy.getTotalfee(),jjry), jpKhdd.getCjrs());
		double hfje=Arith.mul(Arith.div(b2bPolicy.getPjhj(), jpKhdd.getCjrs()),jk.getB2bHf());
		double cplr=Arith.div(drXjjsj, drCgj);
		cplr=Arith.sum(cplr,hfje);
		if(cplr<jk.getZxlr()){//判断是否满足最小出票利润
			return false;
		}
		return true;
	}
	/**
	 * <获取平台最优政策>
	 */
	private PolicyItem genBestPtzc(List<PolicyItem> items,double hlcz,JpZdcpJk jk){
		if(CollectionUtils.isEmpty(items)){
			return null;
		}
		DataUtils.sortList(items, new String[]{"payfee,asc"});
		List<PolicyItem> itemPrempList=new ArrayList<PolicyItem>();
		double lowPrice=items.get(0).getPayfee();
		lowPrice=Arith.add(hlcz, lowPrice);//给最低价格加上忽略的差额，在这个差额内，会认为政策返点相同
		int size=items.size();
		for(int i=1; i<size; i++){
			PolicyItem item = items.get(i);
			if(item.getPayfee() > lowPrice){
				continue;
			}
			itemPrempList.add(item);
		}
		if(CollectionUtils.isEmpty(itemPrempList)){//只有一条正常采购金额最低
			return items.get(0);
		}else {
			itemPrempList.add(items.get(0));
		}
		String bjyxj=jk.getPtbjZdxjyxj();
		String[] bjyxjArr=bjyxj.split("/");
		for (String yxjLb : bjyxjArr) {
			if("1".equals(yxjLb)){
				itemPrempList=sortByFpsj(itemPrempList, jk);
				if(itemPrempList.size()==1){
					return itemPrempList.get(0);
				}
			}else if("2".equals(yxjLb)){
				itemPrempList=sortByCplxyxj(itemPrempList, jk);
				if(itemPrempList.size()==1){
					return itemPrempList.get(0);
				}
			}else if("3".equals(yxjLb)){
				itemPrempList=sortByPtyxj(itemPrempList, jk);
				if(itemPrempList.size()==1){
					return itemPrempList.get(0);
				}
			}else if("4".equals(yxjLb)){
				itemPrempList=sortByCpsj(itemPrempList, jk);
				if(itemPrempList.size()==1){
					return itemPrempList.get(0);
				}
			}
		}
		DataUtils.sortList(itemPrempList, new String[]{"payfee,asc"});
		return itemPrempList.get(0);
	}
	/**
	 * <过滤平台政策>
	 */
	private void filterPolicy(List<PolicyItem> items,B2bpolicyBean b2bpolicy,JpZdcpJk jk){
		double b2bJe=0;
		if(b2bpolicy!=null){
			JpKhdd jpKhdd=genJpKhddByddbh(jk.getDdbh(), jk.getShbh());
			b2bJe=b2bpolicy.getTotalfee();
			double hfje=Arith.mul(Arith.div(b2bpolicy.getPjhj(), jpKhdd.getCjrs()),jk.getB2bHf());
			b2bJe=Arith.sub(b2bJe, hfje);
			double cj=jk.getBjCgjjc()==null ? 0 : jk.getBjCgjjc();
			b2bJe=Arith.sub(b2bJe, jk.getBjCgjjc());
		}else {
			b2bJe=Integer.MAX_VALUE;
		}
		List<PolicyItem> prempList=new ArrayList<PolicyItem>();
		for (PolicyItem item : items) {
			if(item.getPayfee() > b2bJe){//比b2b价格高的
				continue;
			}
			if(PlatCode.CPS.equals(item.getPtzcbs())){//cps产品类型过滤
				String cpszclx=jk.getPtbjCpzclx();
				String zclx = item.getZclx();
				if(StringUtils.isBlank(jk.getPtbjCpzclx())){
					continue;
				}
				if(!cpszclx.contains("1") && "1".equals(zclx)){//普通
					continue;
				}
				if(!cpszclx.contains("2") && "2,3,4".contains(zclx)){//特殊
					continue;
				}
				if(!cpszclx.contains("3") && "5,6".contains(zclx)){//特价
					continue;
				}
			}else {
				if("0".equals(jk.getPtbjTszc()) && "1".equals(item.getIsspecmark())){//过滤特殊政策
					continue;
				}
			}
			if(PlatCode.TB.equals(item.getPtzcbs())){//淘宝产品类型过滤
				String tbcplx=jk.getPtbjTbzclx();
				if(StringUtils.isBlank(tbcplx)){
					continue;
				}
				String productType = StringUtils.substringBeforeLast(item.getId(),"@@");
		        productType = StringUtils.substringAfterLast(productType, "@@");
		        if(!tbcplx.contains(productType)){
		        	continue;
		        }
			}else {
				if("0".equals(jk.getPtbjHbmzc()) && "1".equals(item.getChangerecord())){//过滤换编码
					continue;
				}
				if(jk.getPtbjQfsjxz()!=null){//几小时内起飞的政策不能出平台票
					int twoMin=VeDate.getTwoMin(new Date(),VeDate.formatToDate(jk.getDdQfsj(), "yyyy-MM-dd HH:mm"));
					double xzMin=Arith.mul(jk.getPtbjQfsjxz(), 60);
					if(twoMin<xzMin){
						continue;
					}
				}
				if(!PlatCode.CPS.equals(item.getPtzcbs()) && jk.getBjCpfdwc()!=null){//op实际返点与原始返点差值限制
					double ysfd=item.getRate();
					double sjfd=item.getShowsjdlfl();
					double fdc=Arith.sub(sjfd, ysfd);
					double szfdc=Arith.mul(jk.getBjCpfdwc(), 100);
					if(Math.abs(fdc) > Math.abs(szfdc)){
						continue;
					}
				}
			}
			if(StringUtils.isNotBlank(jk.getPtbjFpsjxz())){//废票时间限制过滤
                String szfpsjz = StringUtils.replace(jk.getPtbjFpsjxz(), ":", "");
                String zcfpsjZ = "";//政策废票时间止
                String zcfpsj = StringUtils.trimToEmpty(item.getChooseOutWorkTime());//易行平台:工作时间提前30分钟 8000翼：截止1930
                if(StringUtils.isNotBlank(zcfpsj)){
                    if(zcfpsj.startsWith("截止")){
                        zcfpsj = StringUtils.replace(zcfpsj, "截止", "0000-");
                    }
                    
                    if(StringUtils.contains(zcfpsj, "-")){
                        zcfpsjZ = StringUtils.replace(StringUtils.split(zcfpsj, "-")[1],":","");
                    }
                }
                if(StringUtils.isNotBlank(zcfpsjZ) && zcfpsjZ.length() >= 4 && StringUtils.isNotBlank(szfpsjz) && szfpsjz.length() >= 4){
                    String hour = StringUtils.substring(zcfpsjZ, 0,2);
                    String minu = StringUtils.substring(zcfpsjZ, 2,4);
                    
                    String hoursz = StringUtils.substring(szfpsjz, 0,2);
                    String minusz = StringUtils.substring(szfpsjz, 0,2);
                    
                    try {
                        if(VeDate.isDatetime(VeDate.getStringDateShort() + " " + hour + ":" + minu +":00") &&
                                VeDate.isDatetime(VeDate.getStringDateShort() + " " + hoursz + ":" + minusz +":00") ){
                            if(NumberUtils.toDouble(zcfpsjZ) < NumberUtils.toDouble(szfpsjz)){
                                continue;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
			prempList.add(item);
		}
		items.clear();
		items.addAll(prempList);
	}
	/**
	 * <根据平台优先级取政策>
	 */
	private List<PolicyItem> sortByPtyxj(List<PolicyItem> items,JpZdcpJk jk){
		Map<Integer, List<PolicyItem>> preMap=new HashMap<Integer, List<PolicyItem>>();
		String ptyxj=jk.getPtbjPtyxj();
		int minIndex=999999;
		for (PolicyItem item : items) {//按采购平台优先级别取
			Integer index=ptyxj.indexOf(item.getPtzcbs());
			if(index <= minIndex){
				List<PolicyItem> list=preMap.get(index);
				if(list==null){
					list=new ArrayList<PolicyItem>();
					preMap.put(index, list);
				}
				list.add(item);
			}
		}
		return preMap.get(minIndex);
	}
	/**
	 * <根据出票类型优先级取政策>
	 */
	private List<PolicyItem> sortByCplxyxj(List<PolicyItem> items,JpZdcpJk jk){
		List<PolicyItem> yxjAList=new ArrayList<PolicyItem>();
		String cplxyx=jk.getPtbjCplxyxj().split("/")[0];
		for (PolicyItem item : items) {//按出票类型优先级取
			String cplx=StringUtils.trimToEmpty(item.getPolicytype());
			cplx = StringUtils.isBlank(cplx) ? "BSP" : cplx;
            if("BPET".equals(cplxyx)){
                if(cplx.startsWith("B2B")){
                	yxjAList.add(item);
                }
            }else{//BSPET
                if(cplx.startsWith("BSP")){
                	yxjAList.add(item);
                }
            }
		}
		if(CollectionUtils.isEmpty(yxjAList)){
			return items;
		}
		return yxjAList;
	}
	/**
	 * <根据废票时间优先取政策>
	 */
	private List<PolicyItem> sortByFpsj(List<PolicyItem> items,JpZdcpJk jk){
		List<PolicyItem> prempList=new ArrayList<PolicyItem>();
		for (PolicyItem item : items) {//处理废票时间格式，转为废票时间止的分钟数
			String zcfpsjZ = getWorkTimeEnd(item.getChooseOutWorkTime());
			if (StringUtils.isNotBlank(zcfpsjZ)) {
				item.setChooseOutWorkTime(zcfpsjZ);
				prempList.add(item);
			}
		}
		if(CollectionUtils.isEmpty(prempList)){
			return items;
		}
		DataUtils.sortList(prempList, new String[]{"chooseOutWorkTime,desc"});//按废票止分钟数倒序排序
		double maxTime=NumberUtils.toDouble(prempList.get(0).getChooseOutWorkTime());
		int fpsjHlc=jk.getPtbjFpsjc()==null ? 0 : jk.getPtbjFpsjc();
		List<PolicyItem> afterPx=new ArrayList<PolicyItem>();
		for (PolicyItem item : prempList) {
			double cTimeMins=NumberUtils.toDouble(item.getChooseOutWorkTime());
			double sjc=Arith.sub(maxTime, cTimeMins);
			if(sjc > fpsjHlc){
				continue;
			}
			afterPx.add(item);
		}
		return afterPx;
	}
	/**
	 * <根据出票时间优先取政策>
	 */
	private List<PolicyItem> sortByCpsj(List<PolicyItem> items,JpZdcpJk jk){
		List<PolicyItem> prempList=new ArrayList<PolicyItem>();
		for (PolicyItem item : items) {//处理废票时间格式，转为废票时间止的分钟数
			String zcfpsjZ = getWorkTimeEnd(item.getWorktime());
			if (StringUtils.isNotBlank(zcfpsjZ)) {
				item.setWorktime(zcfpsjZ);
				prempList.add(item);
			}
		}
		if(CollectionUtils.isEmpty(prempList)){
			return items;
		}
		DataUtils.sortList(prempList, new String[]{"worktime,desc"});//按出票票止分钟数倒序排序
		double maxTime=NumberUtils.toDouble(prempList.get(0).getWorktime());
		int fpsjHlc=jk.getPtbjFpsjc()==null ? 0 : jk.getPtbjFpsjc();
		List<PolicyItem> afterPx=new ArrayList<PolicyItem>();
		for (PolicyItem item : prempList) {
			double cTimeMins=NumberUtils.toDouble(item.getWorktime());
			double sjc=Arith.sub(maxTime, cTimeMins);
			if(sjc > fpsjHlc){
				continue;
			}
			afterPx.add(item);
		}
		return afterPx;
	}
	/**
	 * <比价查找B2B政策>
	 * 
	 * @param lowDbjpnr
	 * @param jk
	 * @param user
	 * @param sfxyh
	 * @param info
	 * @return [参数说明]
	 */
	private B2bpolicyBean genB2bPolicy(JpZdcpDbjpnr lowDbjpnr,JpZdcpJk jk,Shyhb user,String sfxyh){
		try {
			String shbh=jk.getShbh();
			String ddbh=jk.getDdbh();
			JpKhdd jpKhdd=genJpKhddByddbh(ddbh, shbh);
			if(!lowDbjpnr.getPnr().equals(jpKhdd.getCgPnrNo())){
				jpKhddServiceImpl.changeCabin(ddbh, shbh, lowDbjpnr);
			}
			String hkgs=lowDbjpnr.getHkgs();
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			String hszhId=jk.getB2bCpzh();
			if(StringUtils.isBlank(hszhId)){
				throw new Exception("请设置B2B出票账号");
			}
			String reg=".*"+hkgs+":([^/.]*)/{0,1}.*";
			String b2bCpzh=DataUtils.getStrByReg(hszhId, reg);
			JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(shbh,hkgs, "720102");
			JpZdcpB2bzh dlzh=new JpZdcpB2bzh();
			dlzh.setId(b2bCpzh);
			dlzh.setShbh(shbh);
			dlzh=jpZdcpB2bzhServiceImpl.getEntityById(dlzh);
			B2bPolicyResponse b2bPolicyResponse=b2bService.getB2bPolicy(shbh, ddbh, jpB2bHkgs, dlzh, user, sfxyh, jpPz);
			List<B2bpolicyBean> b2bPoList=b2bPolicyResponse.getB2bPolicyBean();
			DataUtils.sortList(b2bPoList, new String[]{"totalfee,asc"});
			B2bpolicyBean toOrderPolicy=null;
			if("1".equals(jk.getSfdjcp())){//按低价出票
				toOrderPolicy=b2bPoList.get(0);
			}else {
				toOrderPolicy=b2bPoList.get(b2bPoList.size()-1);
			}
			return toOrderPolicy;
		} catch (Exception e) {
			e.printStackTrace();
			jk.add("获取B2B政策失败："+e.getMessage());
		}
		return null;
	}
	/**
	 * <获取平台、淘宝政策>
	 * 0正常
	 * -1结束比价
	 * -2结束自动出票
	 */
	private int genPlatPolicy(JpZdcpDbjpnr dbjPnr,JpZdcpJk jk,Shyhb user,List<PolicyItem> items,boolean dbmBj){
		String bjPt=jk.getBjCybjzc();
		bjPt=bjPt.replace("BPET/", "").replace("BPET", "");
		String ddbh=jk.getDdbh();
		SearchPlatCall platCall=null;
		TbSearchService tbSearchService=null;
		CpsSearchService cpsSearchService=null;
		int poolSize=0;
		boolean tbHas=false;
		boolean cpsHas=false;
		int linkHas=0;//0都获取到了，1有部分没有，2全部没有
		if(bjPt.contains(PlatCode.CPS)){
			bjPt=bjPt.replace(PlatCode.CPS+"/", "").replace(PlatCode.CPS, "");
			CpsSearchParam cpsSearchParam=new CpsSearchParam();
			cpsSearchParam.setDdbh(ddbh);
			cpsSearchParam.setYhb(user);
			cpsSearchService=new CpsSearchService(cpsService,cpsSearchParam);
			poolSize++;
		}else {
			cpsHas=true;
		}
		if(bjPt.contains(PlatCode.TB)){
			bjPt=bjPt.replace(PlatCode.TB+"/", "").replace(PlatCode.TB, "");
			tbSearchService=new TbSearchService(tbTaobaobuyerService,ddbh,user);
			poolSize++;
		}else {
			tbHas=true;
		}
		if(StringUtils.isNotBlank(bjPt.replace("/", ""))){
			PlatSearchParam platSearchParam=new PlatSearchParam();
			platSearchParam.setDdbh(ddbh);
			platSearchParam.setPlatcodes(bjPt.replaceAll("/", ","));
			platSearchParam.setYhb(user);
			platCall=new SearchPlatCall(cpslinkService,platSearchParam);
			poolSize++;
		}else {
			linkHas=0;
		}
		ExecutorService executor=null;
		try {
			executor=Executors.newFixedThreadPool(poolSize);
			Future<List<PolicyItem>> tbFuture=null;
			Future<List<PolicyItem>> cpsFuture=null;
			Future<List<PolicyItem>> linkFuture=null;
			if(tbSearchService!=null){
				tbFuture=executor.submit(tbSearchService);
			}
			if(cpsSearchService!=null){
				cpsFuture=executor.submit(cpsSearchService);
			}
			if(platCall!=null){
				linkFuture=executor.submit(platCall);
			}
			if(tbFuture!=null){//勾选了淘宝参与比价
				List<PolicyItem> tbList=tbFuture.get();
				if(CollectionUtils.isEmpty(tbList)){
					tbHas=false;
				}else {
					items.addAll(tbList);
				}
			}
			if(cpsFuture!=null){//勾选了cps参与比价
				List<PolicyItem> cpsList=cpsFuture.get();
				if(CollectionUtils.isEmpty(cpsList)){
					cpsHas=false;
				}else {
					items.addAll(cpsList);
				}
			}
			if(linkFuture != null){//勾选了link中的平台
				List<PolicyItem> linkList=linkFuture.get();
				if(CollectionUtils.isEmpty(linkList)){
					linkHas=2;
				}else if (StringUtils.isNotBlank(platCall.getNopolicyPlats())) {
					linkHas=1;
					items.addAll(linkList);
				}else {
					items.addAll(linkList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(executor!=null){
				executor.shutdown();
			}
		}
		int status=0;
		if(!dbmBj){//如果是多编码比价不做这个逻辑处理
			if(!tbHas){
				status=checkTbOutOrOver(jk.getPtbjTbyccl());
			}
			if(status==-1){
				jk.add("淘宝未获取到政策，结束比价出票");
				return status;
			}else if(status==-2){
				jk.add("淘宝未获取到政策，结束自动出票");
				return status;
			}
			boolean isAll=!cpsHas && linkHas==2;
			status=checkPtOutOrOver(jk.getPtbjOpyccl(), isAll);
			if(status==-1){
				jk.add("平台未获取到政策，结束比价出票");
				return status;
			}else if(status==-2){
				jk.add("平台未获取到政策，结束自动出票");
				return status;
			}
		}
		return status;
	}
	/**
	 * <当平台没获取到政策时，判断是结束比价还是结束全自动，还是继续>
	 * @param ptsz
	 */
	private int checkPtOutOrOver(String ptsz,boolean isAll){
		if("1".equals(ptsz)){//没获取到政策时继续
			return 0;
		}else if("2".equals(ptsz)){
			return -1;
		}else if ("3".equals(ptsz)) {
			return -2;
		}else if ("4".equals(ptsz) && isAll) {
			return -1;
		}else if ("5".equals(ptsz) && isAll) {
			return -2;
		}
		return 0;
	}
	/**
	 * <当淘宝旗舰店没获取到政策时，判断是结束比价还是结束全自动，还是继续>
	 * @param ptsz
	 */
	private int checkTbOutOrOver(String ptsz){
		if("1".equals(ptsz)){//没获取到政策时继续
			return 0;
		}else if("2".equals(ptsz)){
			return -1;
		}else if ("3".equals(ptsz)) {
			return -2;
		}
		return 0;
	}
	/**
	 * <查找机票订单>
	 * 
	 * @param ddbh
	 * @param shbh
	 */
	private JpKhdd genJpKhddByddbh(String ddbh,String shbh){
		JpKhdd t=new JpKhdd();
		t.setShbh(shbh);
		t.setDdbh(ddbh);
		t=jpKhddServiceImpl.getEntityById(t);
		return t;
	}
	 /**
     * 根据工作时间范围获取工作时间止
     * @param workTime
     * @return 分钟数，例如08:00对应480分钟
     */
    public static String getWorkTimeEnd(String workTime){
        if(StringUtils.isBlank(workTime)) return null;
        
        workTime = StringUtils.trimToEmpty(workTime);
        
        if(workTime.startsWith("截止")){
            workTime = StringUtils.replace(workTime, "截止", "0000-");
        }
        
        workTime = StringUtils.replace(workTime, ":", "");
        
        if(workTime.contains("-")){
            String[] arr = StringUtils.split(workTime, "-");
            
            if(ArrayUtils.isNotEmpty(arr) && arr.length > 1){
                String time = arr[1];
                double hour = NumberUtils.toDouble(StringUtils.substring(time, 0,2));
                double minu = NumberUtils.toDouble(StringUtils.substring(time, 2));
                return String.valueOf(Arith.add(Arith.mul(hour, 60), minu));
            }
        }else if(workTime.contains("|")){
            String[] arr = StringUtils.split(workTime, "|");
            
            if(ArrayUtils.isNotEmpty(arr) && arr.length > 1){
                String time = arr[1];
                double hour = NumberUtils.toDouble(StringUtils.substring(time, 0,2));
                double minu = NumberUtils.toDouble(StringUtils.substring(time, 2));
                return String.valueOf(Arith.add(Arith.mul(hour, 60), minu));
            }
        }
        
        return null;
    }
}
