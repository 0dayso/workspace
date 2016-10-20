package cn.vetech.vedsb.jp.service.pzzx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.vedsb.jp.dao.pzzx.JpPzOutDao;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzOut;
import cn.vetech.vedsb.jp.service.attach.AttachService;

@Service
public class JpPzOutServiceImpl extends MBaseService<JpPzOut, JpPzOutDao>{
	@Autowired
	private JpPzOutDao jpPzOutDao;
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpShbmServiceImpl shbmServiceImpl;
	
	/**
	 * 查询票证发放表中的信息
	 * @param out_datetime 发放日始
	 * @param ffrz 发放日止
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条目数
	 * @param model
	 * @return Page对象
	 */
	public Page getListByAll(Date out_datetime,Date ffrz,int pageNum, int pageSize,JpPzOut jpPzOut) throws Exception {
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("yhbh", jpPzOut.getYhbh());
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		param.put("startno", jpPzOut.getStartno());
		param.put("endno", jpPzOut.getEndno());
		param.put("out_datetime", out_datetime);
		param.put("ffrz", ffrz);
		param.put("shbh", jpPzOut.getShbh());
		param.put("bmbh", jpPzOut.getBmbh());
		param.put("pzfl", jpPzOut.getPzfl());
		//根据Map中的值查询
		List<JpPzOut> list = null;
		//获取总记录数
		int pageCount;
		try {
			list = this.getMyBatisDao().getListByAll(param);
			//在数据字典中根据pztype获取相应的对象
			attachService.veclass("pzfl").shyhb("yhbh", jpPzOut.getShbh()).execute(list);
			if(CollectionUtils.isNotEmpty(list)){
				//遍历 将bmbh中存放的ID替换成名称
				for(int i=0;i<list.size();i++){
					JpPzOut jp=list.get(i);
					Shbm shbm=shbmServiceImpl.getMyBatisDao().getShbmById(jp.getBmbh(),jpPzOut.getShbh());
					if(shbm != null){
						jp.setBmbh(shbm.getMc());
					}
				}
			}
			pageCount = this.getMyBatisDao().selectCountByAll(param);
		} catch (Exception e) {
			throw new Exception("查询发放表中的记录出错"+e.getMessage());
		}
		//向page中设置值
		page.setList(list);
		page.setTotalCount(pageCount);
		return page;
	}

	/**
	 * 批量添加数据到JpPzOut票证发放表
	 * @param list
	 */
	public void batchInsertJpPzOut(List<JpPzOut> list) throws Exception {
		try {
			jpPzOutDao.batchInsertJpPzOut(list);
		} catch (Exception e) {
			throw new Exception("批量添加数据到发放表出错"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param startno
	 * @param endno
	 * @return
	 */
	public int queryJpPzOutByNo(String[] startno, String[] endno) {
		int count = 0;
		for(int i=0;i<startno.length;i++){
			count = jpPzOutDao.queryJpPzOutByNo(startno[i],endno[i]);
			if(count>0){
				return count;
			}
		}
		return count;
	}
}
