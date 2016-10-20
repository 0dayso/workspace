package cn.vetech.vedsb.jp.service.cgptsz;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cgptsz.JpCgddDao;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
@Service
public class JpCgddServiceImpl extends MBaseService<JpCgdd,JpCgddDao>{
	/**
	 * 查指定采购来源、未取消的jdCgdd信息
	 * @param cgly
	 * @param shbh
	 * @return
	 */
	public JpCgdd getJpCgddByCgly(String ddbh, String cgly, String dgdh){
		return this.getMyBatisDao().getJpCgddByCgly(ddbh, cgly, dgdh);
	}
	/**
	 * 查指定采购来源、未取消的jdCgdd信息
	 * @param ddbh
	 * @param shbh
	 * @param cgly
	 * @param zfzt
	 * @return
	 */
	public JpCgdd genDdByZfzt(String ddbh,String zfzt,String shbh) {
		return this.getMyBatisDao().genDdByZfzt(ddbh,zfzt, shbh);
	}
	
	/**
	 * 跟据平台订单号查询jdCgdd信息
	 * @param lsh
	 * @param shbh
	 * @return
	 */
	public JpCgdd getDdByZflsh(String ptddbh){
		return this.getMyBatisDao().getDdByZflsh(ptddbh);
	}
	
	/**
	 * 根据订单编号和交易状态查询jpCgdd信息
	 * @param ddbh
	 * @param shbh
	 * @return
	 */
	public JpCgdd getJpCgddByDdbh(String ddbh,String shbh){
		return this.getMyBatisDao().getJpCgddByDdbh(ddbh, shbh);
	}
	
	/**
	 * 根据id查询jpCgdd信息
	 * @param ddbh
	 * @param shbh
	 * @return
	 */
	public JpCgdd getJpCgddById(String id){
		return this.getMyBatisDao().getJpCgddById(id);
	}
}