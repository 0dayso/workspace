package cn.vetech.vedsb.jp.service.pzzx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.pzzx.JpPzInDao;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzIn;
import cn.vetech.vedsb.jp.service.attach.AttachService;


@Service
public class JpPzInServiceImpl extends MBaseService<JpPzIn, JpPzInDao>{
	@Autowired
	private JpPzInDao jpPzInDao;
	@Autowired
	private AttachService attachService;

	/**
	 * 根据商户的编号,入库时间，入库日止，起始号码，终止号码，页码大小，当前页码，是否审核查询票证信息
	 * @param in_datetime 入库日始
	 * @param rkrz 入库日止
	 * @param pageNum当前页码
	 * @param pageSize每页显示数
	 * @return page
	 * @throws Exception 
	 */
	public Page getListByAll(Date in_datetime, Date rkrz, int pageNum,int pageSize,JpPzIn jpPzIn) throws Exception{
		//创建Page对象，将当前页码和显示的条目数传入
		Page page = null;
		try {
			page = new Page(pageNum,pageSize);
			//创建一个Map并传入值
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("shbh", jpPzIn.getShbh());
			param.put("in_datetime", in_datetime);
			param.put("rkrz", rkrz);
			param.put("pageNum", pageNum);
			param.put("pageSize", pageSize);
			param.put("startno", jpPzIn.getStartno());
			param.put("endno", jpPzIn.getEndno());
			param.put("sfsh", jpPzIn.getSfsh());
			param.put("pztype", jpPzIn.getPztype());
			//根据Map中的值查询
			List<JpPzIn> list=this.getMyBatisDao().getListByAll(param);
			attachService.veclass("pztype").shyhb("yhbh", jpPzIn.getShbh()).execute(list);
			//获取总记录数
			int pageCount=this.getMyBatisDao().selectCountByAll(param);
			//向page中设置值
			page.setList(list);
			page.setTotalCount(pageCount);
		} catch (Exception e) {
			throw new Exception("查询入库票证信息出错"+e.getMessage());
		}
		return page;
	}
	
	public void saveJpPzIn(JpPzIn jpPzIn) throws Exception {
		try {
			jpPzInDao.saveJpPzIn(jpPzIn);
		} catch (Exception e) {
			throw new Exception("保存票证入库信息出错"+e.getMessage());
		}
	}

	/**
	 * 更改票证的状态为已审核:0变为1
	 * @param jpPzIn
	 */
	public void updateByInNo(JpPzIn jpPzIn) throws Exception {
		try {
			jpPzInDao.updateByInNo(jpPzIn);
		} catch (Exception e) {
			throw new Exception("更改票证状态为审核时报错"+e.getMessage());
		}
	}

	/**
	 * 更改票证的状态为未审核:1变为0
	 * @param inNo
	 * @param sfsh
	 */
	public void updateByInNo2(String inNo, String sfsh,String shbh) throws Exception {
		try {
			jpPzInDao.updateByInNo2(inNo,sfsh,shbh);
		} catch (Exception e) {
			throw new Exception("更改票证状态为未审核时报错"+e.getMessage());
		}
	}

	/**
	 * 票证入库添加方法
	 */
	@Override
	public int insert(JpPzIn t) throws Exception {
		try {
			return super.insert(t);
		} catch (Exception e) {
			throw new Exception("保存票证入库信息出错"+e.getMessage());
		}
	}

	public List<JpPzIn> validateNo(String startno ,String endno) {
		return jpPzInDao.validateNo(startno,endno);
	}

}
