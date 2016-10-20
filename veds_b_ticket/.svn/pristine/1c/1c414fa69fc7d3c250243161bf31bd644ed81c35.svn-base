package cn.vetech.vedsb.jp.service.jpzdcp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;

import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.cpslink.PlatSearchParam;
import cn.vetech.vedsb.platpolicy.cpslink.response.GetRealtimePolicyRes;
import cn.vetech.vedsb.platpolicy.cpslink.response.PlatPolicyBean;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkService;

public class SearchPlatCall implements Callable<List<PolicyItem>>{
	private String nopolicyPlats="";//用于标记没找到政策的平台
	private CpslinkService cpslinkService;
	private PlatSearchParam searchParam;
	public SearchPlatCall(CpslinkService cpslinkService,PlatSearchParam searchParam) {
		this.cpslinkService=cpslinkService;
		this.searchParam=searchParam;
	}
	@Override
	public List<PolicyItem> call() throws Exception {
		List<PolicyItem> allPolicy=new ArrayList<PolicyItem>();
		String bjPt=searchParam.getPlatcodes();
		try {
			GetRealtimePolicyRes platRes=cpslinkService.getRealtimePolicy(searchParam);
			if(platRes!=null && GetRealtimePolicyRes.SUCCESS.equals(platRes.getStatus())){
				for (PlatPolicyBean platBean : platRes.getPlatPolicyList()) {
					if("0".equals(platBean.getStatus()) && CollectionUtils.isNotEmpty(platBean.getPolicyList())){
						List<PolicyItem> onelist=cpslinkService.linkPolicyToItem(platBean.getPolicyList(),platBean.getPlatCode());
						allPolicy.addAll(onelist);
					}else {
						nopolicyPlats+=platBean.getPlatCode();
					}
				}
			}else {//平台都没有政策
				nopolicyPlats=bjPt;
			}
		} catch (Exception e) {
			e.printStackTrace();
			nopolicyPlats=bjPt;
		}
		if(CollectionUtils.isEmpty(allPolicy)){
			nopolicyPlats=bjPt;
			return null;
		}
		return allPolicy;
	}
	public String getNopolicyPlats() {
		return nopolicyPlats;
	}
	public void setNopolicyPlats(String nopolicyPlats) {
		this.nopolicyPlats = nopolicyPlats;
	}

	
}
