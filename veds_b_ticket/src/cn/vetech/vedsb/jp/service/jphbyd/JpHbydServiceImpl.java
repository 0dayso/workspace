package cn.vetech.vedsb.jp.service.jphbyd;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.dao.jphbyd.JpHbydDao;
import cn.vetech.vedsb.jp.entity.jphbyd.JpHbyd;

@Service
public class JpHbydServiceImpl extends MBaseService<JpHbyd, JpHbydDao>{

	/**
	 * 根据条件查询列表和数据记录,返回page到controller
	 * @param JpHbyd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page queryPage(JpHbyd JpHbyd, int pageNum, int pageSize, String sortType) {
		Page page=new Page(pageNum, pageSize);
		List<Map<String,Object>> list = this.getMyBatisDao().queryHbydList(JpHbyd,pageNum,pageSize,sortType);
		for(Map<String,Object> map : list){
			if(map.get("YJ_CFSJ") != null ){
				String cfrq = VeStr.getValue(map, "CFRQ")+":00";
				String yjrq = VeStr.getValue(map, "YJ_CFSJ")+":00";
				//map.put("YWSC", VeDate.getTwoMil(cfrq, yjrq));
			}
		}
		int totalCount=getMyBatisDao().queryHbydCount(JpHbyd);
		page.setList(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * 根据条件查询受影响订单列表和数据记录,返回page到controller
	 * @param JpHbyd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page queryJpHbydCl(JpHbyd JpHbyd, int pageNum, int pageSize) {
		Page page=new Page(pageNum, pageSize);
		List<Map<String,Object>> list = this.getMyBatisDao().queryHbydClList(JpHbyd,pageNum,pageSize);
		int totalCount=getMyBatisDao().queryHbydClCount(JpHbyd);
		page.setList(list);
		page.setTotalCount(totalCount);
		return page;
	}
	
	public boolean isJpHbyd(JpHbyd jpHbyd){
		return this.getMyBatisDao().isHbyd(jpHbyd) > 0 ;
	}
	/**
	 * 更新状态
	 */
	public void upZt(JpHbyd jpHbyd,String zt) throws Exception{
		    jpHbyd.setZt(zt);
	       this.getMyBatisDao().updateByPrimaryKeySelective(jpHbyd);
			}
	
	public JpHbyd getHbydByHbh(JpHbyd hbyd){
		return this.getMyBatisDao().getHbydByHbh(hbyd);
	}
}
