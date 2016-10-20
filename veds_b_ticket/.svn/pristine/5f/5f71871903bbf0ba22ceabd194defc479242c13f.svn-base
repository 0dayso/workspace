package cn.vetech.vedsb.jp.dao.cgdzbb;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpTslDzjg;
@JpMyBatisRepository
public interface JpTslDzjgDao extends Mapper<JpTslDzjg>{
	/**
	 * 根据打印机号,office号,日期查询采购对账结果
	 * @param shbh
	 * @param printno
	 * @param office
	 * @param date
	 * @return
	 */
	@Select(value="select * from jp_tsl_dzjg where shbh=#{param1} and xtpringtno=#{param2} and xtoffice=#{param3} and cp_date=#{param4}")
	List<JpTslDzjg> getTslDzjgList(String shbh,String printno,String office,String date);
	
	/**
	 * 获取采购对账对比结果
	 * @param shbh
	 * @param printno
	 * @param office
	 * @param date
	 * @param dbjg
	 * @return
	 */
	@Select(value="select * from jp_tsl_dzjg where shbh=#{param1} and xtpringtno=#{param2} and xtoffice=#{param3} and cp_date=#{param4} and dbjg=#{param5}")
	List<JpTslDzjg> getDzjgList(String shbh,String printno,String office,String date,String dbjg);
	
	/**
	 * 获取对比结果的数目
	 * @param shbh
	 * @param printno
	 * @param office
	 * @param date
	 * @param dbjg
	 * @return
	 */
	@Select(value="select count(1) from jp_tsl_dzjg where shbh=#{param1} and xtpringtno=#{param2} and xtoffice=#{param3} and cp_date=#{param4} and dbjg=#{param5}")
	int getDzjgCount(String shbh,String printno,String office,String date,String dbjg);
}