package cn.vetech.vedsb.jp.service.jppz;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.business.pid.api.pidgl.JpPid;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pidgl.PidglService;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.jppz.JpPzDao;
@Service
public class JpPzServiceImpl extends MBaseService<JpPz,JpPzDao>{
	@Autowired
	private PidglService pidglService;
	@Autowired
	private CsbCacheService cacheService;
	/**
	 * 取单个配置
	 * @param shbh
	 * @return
	 */
	public JpPz getJpPzByShbh(String shbh) {
		return this.getMyBatisDao().getJpPzByShbh(shbh);
	}

	/**
	 * 取配置集合
	 * @param shbh
	 * @return
	 */
	public List<JpPz> selectJpPzByShbh(String shbh) {
		return this.getMyBatisDao().selectJpPzByShbh(shbh);
	}
	
	/**
	 * 通过office号获得对应一条配置
	 * @param officeid
	 * @return
	 */
	public JpPz getJpPzByOfficeid(String officeid, String shbh) {
		return this.getMyBatisDao().getJpPzByOfficeid(officeid, shbh);
	}
	
	/**
	 * 取根据id取配置
	 * @param shbh
	 * @return
	 */
	public JpPz getJpPzByShbh(String shbh, String id) {
		return this.getMyBatisDao().getJpPzById(shbh, id);
	}
	
	/**
	 * 根据id修改配置
	 * @param shbh
	 * @return
	 */
	public void updateJppzById(JpPz jppz) {
		this.getMyBatisDao().updateJppzById(jppz);
	}
	
	@Transactional
	public void tbPid(String shbh,String userid,String iata) throws Exception{
		VecsbCache vecsbCache = cacheService.get("2012");
		JpPz jpPzDelete = new JpPz();
		jpPzDelete.setShbh(shbh);
		this.getMyBatisDao().delete(jpPzDelete);
		List<JpPid> jpPzs = pidglService.queryList(shbh);
		for(JpPid jpPid : jpPzs){
			JpPz jpPz = new JpPz();
			jpPz.setId(VeDate.getNo(4));
			String si = jpPid.getSi();
			si = StringUtils.substring(si, 2).trim();
			String []sia = si.split("/");
			if(sia!=null && sia.length>1){
				jpPz.setAgent(sia[0]);
				jpPz.setAgentPass(sia[1]);
			}else{
				jpPz.setAgent("EMPTY");
				jpPz.setAgentPass("EMPTY");
			}
			jpPz.setShbh(shbh);
			jpPz.setPidzbh(jpPid.getPidzid()+","+jpPid.getPidid());
			jpPz.setPzyhm(jpPid.getUsername());
			jpPz.setPzmm(jpPid.getPassword());
			if(StringUtils.isNotBlank(jpPid.getServerip())){
				jpPz.setServerAddr(jpPid.getServerip());
			}else{
				jpPz.setServerAddr("127.0.0.1");
			}
			if(StringUtils.isNotBlank(jpPid.getServerport())){
				jpPz.setServerPort(jpPid.getServerport());
			}else{
				jpPz.setServerPort("127.0.0.1");
			}
			
			jpPz.setCjsj(new Date());
			jpPz.setCjyh(userid);
			jpPz.setXgsj(new Date());
			jpPz.setXgyh(userid);
			if(StringUtils.isNotBlank(jpPid.getOffice())){
				jpPz.setOfficeid(jpPid.getOffice());
			}else{
				jpPz.setOfficeid("EMPTY");
			}
			if(StringUtils.isNotBlank(jpPid.getPrintno())){
				jpPz.setSfcppz("1");
				jpPz.setPrintno(jpPid.getPrintno());
			}else{
				jpPz.setSfcppz("0");
			}
			jpPz.setPzyt("0");
			jpPz.setPzzt(jpPid.getStop());
			jpPz.setSftb("1");
			jpPz.setSfmr(jpPid.getSfmr());
		
			jpPz.setPzIp(vecsbCache.getCsz1());
			jpPz.setPzPort(vecsbCache.getCsz2());
			if (null != iata) {
				jpPz.setIata(iata);
			}
			this.insert(jpPz);
		}
	}
}