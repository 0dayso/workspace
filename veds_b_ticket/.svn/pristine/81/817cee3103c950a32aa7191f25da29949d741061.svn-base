package cn.vetech.vedsb.jp.service.jpjpgl;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.jpjpgl.JpSmSjSzDao;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpSmSjSz;

@Service
public class JpSmSjSzServiceImpl extends MBaseService<JpSmSjSz, JpSmSjSzDao>{
	/**
	 * 根据商户编号删除JpSmSjSz的记录
	 * @param shbh 商户编号
	 * @throws Exception
	 */
	public void deleteByShbh(String shbh) throws Exception{
		try {
			JpSmSjSz js=new JpSmSjSz();
			js.setShbh(shbh);
			this.getMyBatisDao().delete(js);
		} catch (Exception e) {
			throw new Exception("删除JpSmSJSz出错"+e.getMessage());
		}
	}
	/**
	 * 插入数据
	 * @param shbh 商户编号
	 * @param tqts 提前天数
	 * @param sfkq 是否开启
	 * @throws Exception 
	 */
	public void insertSmSjSz(String shbh,int tqts,String sfkq,String pidyhbh,String pidmm) throws Exception{
		try {
			JpSmSjSz js=new JpSmSjSz();
			js.setId(VeDate.getNo(6));
			js.setShbh(shbh);
			js.setSfkq(sfkq);
			js.setTqts(tqts);
			js.setPidyhbh(pidyhbh);
			js.setPidmm(pidmm);
			this.getMyBatisDao().insert(js);
		} catch (Exception e) {
			throw new Exception("插入JpSmSJSz出错"+e.getMessage());
		}
	}
	/**
	 * 根据商户编号查询对应的扫描时间设置
	 * @param shbh
	 * @return
	 * @throws Exception 
	 */
	public JpSmSjSz queryByShbh(String shbh) throws Exception{
		JpSmSjSz jpsmsjsz=null;
		try {
			jpsmsjsz=this.getMyBatisDao().queryByShbh(shbh);
		} catch (Exception e) {
			throw new Exception("查询JpSmSJSz出错"+e.getMessage());
		}
		return jpsmsjsz;
	}
}
