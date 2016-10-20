package cn.vetech.web.vedsb.jzcp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;

import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.cps.CpsSearchParam;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.utils.DataUtils;

public class CpsSearchService implements Callable<List<PolicyItem>>{
	private CpsService cpsService;
	private CpsSearchParam cpsSearchParam;
	public CpsSearchService(CpsService cpsService,CpsSearchParam cpsSearchParam){
		this.cpsService=cpsService;
		this.cpsSearchParam=cpsSearchParam;
	}
	@Override
	public List<PolicyItem> call() throws Exception {
		try {
			List<PolicyItem> pList=cpsService.searchpolicy(cpsSearchParam);
			if(CollectionUtils.isEmpty(pList)){
				return null;
			}
			List<PolicyItem> policyList=new ArrayList<PolicyItem>();
			DataUtils.sortList(policyList, new String[]{"payfee,asc"});
			boolean flagOne = false;
			boolean flagTwo = false;
			int number = 1;
			PolicyItem pitem = null;
			for(PolicyItem policyItem : pList){
				String zclx = policyItem.getZclx();
				String matchwl = policyItem.getMatchWL();//满足白名单情况【集中出票验证白名单后结果分类】 0完全满足 1部分满足 2完全不满足
				if (4 == policyList.size()) {
					break;
				}else{
					if("1".equals(zclx) && !"2".equals(matchwl)){//普通政策
						if(number<3){
							if(number == 1 ){
								policyItem.setCpsSortNum("0");
							}else{
								policyItem.setCpsSortNum("1");
							}
							policyList.add(policyItem);
						}
						number++;
					}else if("2,3,4".contains(zclx) && !flagOne && !"2".equals(matchwl)){//2 3 4 属于特殊政策 取一条特殊政策
						policyList.add(policyItem);
						flagOne = true;
					}else if("5.6".contains(zclx) && !flagTwo && !"2".equals(matchwl)){// 5 6属于特价政策 取一条特价政策
						policyList.add(policyItem);
						flagTwo = true;
					}else if("5.6".contains(zclx) && flagTwo && !flagOne && !"2".equals(matchwl) && pitem==null){//如果没有特殊政策 则取两条特价政策
						pitem = policyItem;
					}
				}
			}
			if (4 != policyList.size() && pitem != null) {
				policyList.add(pitem);
			}
			return policyList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
