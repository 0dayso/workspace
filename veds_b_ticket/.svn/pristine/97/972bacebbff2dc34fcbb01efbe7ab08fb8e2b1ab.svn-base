package cn.vetech.vedsb.jp.service.jpjpgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpjpgl.JpSmfsSzDao;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpSmfsSz;

@Service
public class JpSmfsSzServiceImpl extends MBaseService<JpSmfsSz, JpSmfsSzDao>{
	/**
	 * <根据商户编号查找open票DETR方式列表>
	 * 
	 * @param shbh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpSmfsSz> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, List<JpSmfsSz>> queryJpSmfsszListByShbh(String shbh) {
		List<JpSmfsSz> jpsmfslist=this.getMyBatisDao().queryJpSmfsszListByShbh(shbh);
		Map<String, List<JpSmfsSz>> smlxMap = new HashMap<String, List<JpSmfsSz>>();
		List<JpSmfsSz> listopen=new ArrayList<JpSmfsSz>();
		List<JpSmfsSz> listwcj=new ArrayList<JpSmfsSz>();
		List<JpSmfsSz> listgq=new ArrayList<JpSmfsSz>();
		if(jpsmfslist!=null&&jpsmfslist.size()>0){
			for(JpSmfsSz smfs:jpsmfslist){
				String shlx = smfs.getSmlx();
				if("0".equals(shlx)){//到期open票
					listopen.add(smfs);
				}
				if("1".equals(shlx)){//未乘机
					listwcj.add(smfs);
				}
				if("2".equals(shlx)){//改签
					listgq.add(smfs);
				}
			}
		}
		smlxMap.put("0", listopen);
		smlxMap.put("1", listwcj);
		smlxMap.put("2", listgq);
		return smlxMap;
	}

	/**
	 * 查询已开启扫描设置的商户信息
	 * @param smlx 扫描类型0到期OPEN票扫描 1未乘机2异常改签
	 * @return JpSmfsSz 机票扫描方式数值 
	 * @throws Exception 
	 */
	public List<Map<String,Object>>queryJpSmfsszListBySmlx(String smlx,String cgly,String detrLx) throws Exception{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			list=this.getMyBatisDao().queryJpSmfsszListBySmlx(smlx,cgly,detrLx);
		} catch (Exception e) {
			throw new Exception("查询已开启扫描设置的商户信息出错"+e.getMessage());
		}
		return list;
	}
	/**
	 * 根据采购来源 、扫描方式查找所有对应的JpSmFsSz
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>>queryJpSmfsszListByCgly(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			list=this.getMyBatisDao().queryJpSmfsszListByCgly(param);
		} catch (Exception e) {
			throw new Exception("根据采购来源查询所有JpSmfsSz出错"+e.getMessage());
		}
		return list;
	}
	
	/**
	 * <根据扫描类型删除>
	 * 
	 * @param smlx [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteBySmlx(String smlx,String shbh) throws Exception{
		try {
			this.getMyBatisDao().deleteBySmlx(smlx,shbh);
		} catch (Exception e) {
			throw new Exception("删除JpSmfsSz出错"+e.getMessage());
		}
	}
	
}
