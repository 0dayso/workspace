package cn.vetech.web.vedsb.jzcp;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.taobao.TaobaobuyerService;
import cn.vetech.vedsb.utils.DataUtils;

public class TbSearchService implements Callable<List<PolicyItem>>{
	private TaobaobuyerService tbTaobaobuyerService;
	private String ddbh;
	private Shyhb yhb;
	public TbSearchService(TaobaobuyerService tbTaobaobuyerService,String ddbh,Shyhb yhb) {
		this.tbTaobaobuyerService=tbTaobaobuyerService;
		this.ddbh=ddbh;
		this.yhb=yhb;
	}
	@Override
	public List<PolicyItem> call() throws Exception {
		try {
			List<PolicyItem> pList= tbTaobaobuyerService.searchpolicy(ddbh, yhb);
			if(CollectionUtils.isNotEmpty(pList)){
				DataUtils.sortList(pList, new String[]{"payfee,asc"});
			}
			return pList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
