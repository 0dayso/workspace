package cn.vetech.vedsb.jp.dao.pzzx;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzIn;

@JpMyBatisRepository
public interface JpPzInDao extends Mapper<JpPzIn>{

	 /**
     * 根据用户编号查询总有记录数
     * @param yhbh
     * @return
     */
    int selectCountByAll(Map<String,Object> param);
    
    /**
     * 根据用户输入的信息查询
     * @param param
     * @return
     */
    List<JpPzIn> getListByAll(Map<String,Object> param);

    /**
     * 票证入库
     * @param jpPzIn
     */
	void saveJpPzIn(JpPzIn jpPzIn);

	void updateByInNo(JpPzIn jpPzIn);

	void updateByInNo2(String inNo, String sfsh,String shbh);
	
	List<JpPzIn> validateNo(String startno,String endno);
}
