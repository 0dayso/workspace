package cn.vetech.vedsb.jp.service.pzzx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.pzzx.JpPzPzJzDao;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzPzJz;

@Service
public class JpPzPzJzServiceImpl extends MBaseService<JpPzPzJz, JpPzPzJzDao> {
	/**
	 * 批量插入
	 * @param shybh 用户对象
	 * @param pzzt_arr 以逗号分隔的pzzt
	 * @param xcdNo_arr 以逗号分隔的xcdNo
	 * @param pztype_arr 以逗号分隔的pztype
	 * @throws Exception 
	 */
	public void batchInsert(Shyhb shyhb,String pzzt_arr,String xcdNo_arr,String pztype_arr) throws Exception{
		String[] xcdNo=xcdNo_arr.split(",");
		String[] pztype=pztype_arr.split(",");
		String[] pzzt=pzzt_arr.split(",");
		List<JpPzPzJz> param=new ArrayList<JpPzPzJz>();
		for(int i=0;i < xcdNo.length;i++){
			JpPzPzJz jz=new JpPzPzJz();
			String cz = "";
			String cz_sm = "";
			if ("5".equals(pzzt[i]) || "3".equals(pzzt[i]) || "7".equals(pzzt[i])) {
				cz = "7";
				cz_sm = "  回收";
			} else {
				cz = "8";
				cz_sm = "  取消回收";
			}
			String czsm = "该票证被  " + shyhb.getBmmc() + "/" + shyhb.getXm() + "/"+ cz_sm;
			jz.setId(VeDate.getNo(6));
			jz.setPztype(pztype[i]);
			jz.setPzbh(xcdNo[i]);
			jz.setCzlx(cz);
			jz.setCzDatetime(VeDate.getNow());
			jz.setCzYhbh(shyhb.getBh());
			jz.setCzBmbh(shyhb.getShbmid());
			jz.setCzSm(czsm);
			jz.setBy2(xcdNo[i]);
			jz.setCzShbh(shyhb.getShbh());
			param.add(jz);
		}
		try {
			this.getMyBatisDao().batchInsert(param);
		} catch (Exception e) {
			throw new Exception("批量插入日志信息时出错"+e.getMessage());
		}
			
	}
	/**
	 * 根据用户编号和行程单号查询
	 * @param shbh 用户编号
	 * @param xcdNo 行程单号
	 * @return
	 * @throws Exception 
	 */
	public List<JpPzPzJz>getListByPzBh(String shbh,String xcdNo,String pztype) throws Exception{
		try {
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("shbh", shbh);
			param.put("pzbh", xcdNo);
			param.put("pztype", pztype);
			return this.getMyBatisDao().getListByPzBh(param);
		} catch (Exception e) {
			throw new Exception("根据用户编号和行程单号查询日志信息出错"+e.getMessage());
		}
		
	}
	/**
	 * 记录在入库时记录日志
	 * @param pzzt 票证类型
	 * @param startno 起始号
	 * @param endno 终止号
	 * @param shbh 商户编号
	 * @throws Exception 
	 */
	public void pzrkrz(String pzzt,String pztype,String startno,String endno,Shyhb shyhb) throws Exception{
		try {
			int start=NumberUtils.toInt(startno);
			int end=NumberUtils.toInt(endno);
			if(start !=0 && end !=0 ){
				List<JpPzPzJz> param=new ArrayList<JpPzPzJz>();
				for(int i=0;i<(end-start+1);i++){
					String czsm = "该票证被  " + shyhb.getBmmc() + "/" + shyhb.getXm() + "/取消入库" ;
					JpPzPzJz jz=new JpPzPzJz();
					jz.setId(VeDate.getNo(6));
					jz.setPztype(pztype);
					jz.setPzbh(start+i+"");
					jz.setCzlx(pzzt);
					jz.setCzDatetime(VeDate.getNow());
					jz.setCzYhbh(shyhb.getBh());
					jz.setCzBmbh(shyhb.getShbmid());
					jz.setCzSm(czsm);
					jz.setBy2(start+i+"");
					jz.setCzShbh(shyhb.getShbh());
					param.add(jz);
				}
				this.getMyBatisDao().batchInsert(param);
			}
		} catch (Exception e) {
			throw new Exception("批量插入日志信息时出错"+e.getMessage());
		}
	}
}
