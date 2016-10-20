package cn.vetech.vedsb.jp.service.jptpgl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.dao.jptpgl.JpTpdDao;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.attach.AttachService;
/**
 * 退票出票控制台
 * @author wangshengliang
 *
 */
@Service
public class JpTpdCpkztServiceImpl extends MBaseService<JpTpd, JpTpdDao>{
	@Autowired
	private AttachService attachService;
	
   /**
    * 退票出票控制台，待处理，已提交未完成，已完成的数量
    * @param param
    * @return
    */
    public  Map<String,Object> tpCpkztCount(Map<String, Object> param){
    	return this.getMyBatisDao().tpCpkztCount(param);
    }
    
    /**
     * 退票出票控制台，采购来源Count(BSP,OP...)
     * @param param
     * @return
     */
     public  List<Map<String,Object>> tpCpkztCgLyCount(Map<String, Object> param){
     	return this.getMyBatisDao().tpCpkztCgLyCount(param);
     }
     
 	/**
 	 * 退票出票控制台，查询
 	 * @param param
 	 * @return
 	 */
 	public Page tpCpkztQuery(Map<String, Object> param){
 		int pageNum=NumberUtils.toInt(VeStr.getValue(param, "pageNum"));
 		int pageSize=NumberUtils.toInt(VeStr.getValue(param, "pageSize"));
 		Page page=new Page(pageNum,pageSize);
 	    List<Map<String,Object>> listMap=this.getMyBatisDao().tpCpkztQuery(param);
 	    attachService.wdzl("WDID").zfkm("CG_TKKM").execute(listMap);
 		page.setList(listMap);
 		int count=this.getMyBatisDao().tpCpkztQueryCount(param);
 		page.setTotalCount(count);
 		return page;
 	}

}
