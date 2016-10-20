package cn.vetech.web.vedsb.jzcp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.cpslink.PlatSearchParam;
import cn.vetech.vedsb.platpolicy.cpslink.response.GetRealtimePolicyRes;
import cn.vetech.vedsb.platpolicy.cpslink.response.PlatPolicyBean;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkService;
import cn.vetech.vedsb.utils.DataUtils;

public class OpSearchService implements Callable<List<PolicyItem>>{
	private PlatSearchParam searchParam;
	private String cs2021_1;
	private int maxSize;
	private CpslinkService cpslinkService;
	public OpSearchService(PlatSearchParam searchParam,String cs2021_1,int maxSize,CpslinkService cpslinkService){
		this.searchParam=searchParam;
		this.cs2021_1=cs2021_1;
		this.maxSize=maxSize;
		this.cpslinkService=cpslinkService;
	}
	@Override
	public List<PolicyItem> call(){
		try {
			List<PolicyItem> allPolicy=new ArrayList<PolicyItem>();
			GetRealtimePolicyRes platRes=cpslinkService.getRealtimePolicy(searchParam);
			if(platRes!=null && GetRealtimePolicyRes.SUCCESS.equals(platRes.getStatus())){
				for (PlatPolicyBean platBean : platRes.getPlatPolicyList()) {
					if("0".equals(platBean.getStatus()) && CollectionUtils.isNotEmpty(platBean.getPolicyList())){
						List<PolicyItem> onelist=cpslinkService.linkPolicyToItem(platBean.getPolicyList(),platBean.getPlatCode());
						DataUtils.sortList(onelist,new String[]{"payfee,asc"});
						PolicyItem pTemp=null;
						List<PolicyItem> platList=new ArrayList<PolicyItem>();
						for (PolicyItem p : onelist) {
							if(pTemp!=null){
								double payfeeTmp=pTemp.getPayfee()==null ? 0 : pTemp.getPayfee();
								double payfee=p.getPayfee()==null ? 0 : p.getPayfee();
								if(payfee != payfeeTmp){
									pTemp=p;
									platList.add(pTemp);
								}else {//应付金额一样时比较工作时间
									String workTimeTmp=StringUtils.trimToEmpty(pTemp.getWorktime());
									String workTime=StringUtils.trimToEmpty(p.getWorktime());
									int calVal1=compTimeRange(workTime,workTimeTmp);
									if(calVal1>0){
										platList.remove(platList.size()-1);
										platList.add(p);
										pTemp=p;
									}else if(calVal1==0){
										String chooseOutWorkTimeTmp=StringUtils.trimToEmpty(pTemp.getChooseOutWorkTime());
										String chooseOutWorkTime=StringUtils.trimToEmpty(p.getChooseOutWorkTime());
										int calVal2=compTimeRange(chooseOutWorkTime, chooseOutWorkTimeTmp);
										if(calVal2>0){
											platList.remove(platList.size()-1);
											platList.add(p);
											pTemp=p;
										}
									}
								}
							}else{
								pTemp=p;
								platList.add(pTemp);
							}
						}
						//每个平台取返点最高的前几条
						if("2".equals(cs2021_1) && CollectionUtils.isNotEmpty(platList)){
							if(platList.size()>maxSize){
								allPolicy.addAll(platList.subList(0, maxSize));
							}else {
								allPolicy.addAll(platList);
							}
						}
					}
				}
			}
			return allPolicy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 如果第一个时间范围大于第二个，返回正整数，小于返回负数，等于返回0
	 */
	private  int compTimeRange(String time1,String time2){
		try{
			if(time1.indexOf("截止")>-1&&time2.indexOf("截止")>-1){
				int  str1=NumberUtils.toInt(time1.substring(2));
				int  str2=NumberUtils.toInt(time2.substring(2));
				return str1-str2;
			}else if(time1.indexOf("-")>-1&&time2.indexOf("-")>-1){
				String[]str1=time1.split("-");
				String[]str2=time2.split("-");
				int cal1=NumberUtils.toInt(str1[1].replace(":", ""))-NumberUtils.toInt(str1[0].replace(":", ""));
				int cal2=NumberUtils.toInt(str2[1].replace(":", ""))-NumberUtils.toInt(str2[0].replace(":", ""));
				return cal1-cal2;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
