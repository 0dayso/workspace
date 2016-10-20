package cn.vetech.vedsb.jp.service.cgdzbb;
import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cgdzbb.JpTslDzjgDao;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpTslDzjg;
@Service
public class JpTslDzjgServiceImpl extends MBaseService<JpTslDzjg,JpTslDzjgDao>{
	/**
	 * 根据打印机号,office号,日期查询采购对账结果
	 * @param shbh
	 * @param printno
	 * @param office
	 * @param date
	 * @return
	 */
	public List<JpTslDzjg> getTslDzjgList(String shbh,String printno,String office,String date){
		return this.getMyBatisDao().getTslDzjgList(shbh, printno, office, date);
	}
	
	/**
	 * 根据打印机号,office号,日期,对比结果查询采购对账结果
	 * @param shbh
	 * @param printno
	 * @param office
	 * @param date
	 * @param dbjg
	 * @return
	 */
	public List<JpTslDzjg> getDzjgList(String shbh,String printno,String office,String date,String dbjg){
		return this.getMyBatisDao().getDzjgList(shbh, printno, office, date, dbjg);
	}
	
	/**
	 * 获取对比结果的数目
	 * @param shbh
	 * @param printno
	 * @param office
	 * @param date
	 * @param dbjg
	 * @return
	 */
	public int getDzjgCount(String shbh,String printno,String office,String date,String dbjg){
		return this.getMyBatisDao().getDzjgCount(shbh, printno, office, date, dbjg);
	}
}