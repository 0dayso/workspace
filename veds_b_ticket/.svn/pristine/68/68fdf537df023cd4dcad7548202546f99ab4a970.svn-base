package cn.vetech.vedsb.jp.service.cgptsz;
import java.io.File;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.Exceptions;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.WebUtils;

import cn.vetech.vedsb.jp.dao.cgptsz.JpPtLogDao;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
@Service
public class JpPtLogServiceImpl extends MBaseService<JpPtLog,JpPtLogDao>{
	private final Logger logger = LoggerFactory.getLogger(JpPtLogServiceImpl.class);
	/**
	 * <功能详细描述>
	 * 记录日志到日志文件
	 * @param tddlog [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String updateLogTxt(JpPtLog tddlog) {
		try {
			if(StringUtils.isBlank(tddlog.getInfo())){
				return "";
			}
			String date = "";
			if(tddlog.getLogDate()!=null){
				date = VeDate.dateToStr(tddlog.getLogDate()).replaceAll("-", "");
			}else{//这里考虑到LogDate为空时报错问题
				date = VeDate.getStringDateShort();
			}
			
			String path="updownFiles/ptlog/" + date + "/" + tddlog.getYwlx() + "/";
			String filePath = WebUtils.getRootPath("updownFiles/ptlog/" + date + "/" + tddlog.getYwlx() + "/");

			String fileName = tddlog.getId() + ".txt";
			
			FileUtils.writeStringToFile(new File(filePath + fileName), tddlog.getInfo(), "UTF-8", true);
			tddlog.clearInfo();
			return "/"+path+fileName;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存CPSLINK日志详细信息失败,写文件失败,错误:" + Exceptions.getMessageAsString(e));
		}
		return null;
	}
	/**
	 * <功能详细描述>
	 * 保存订单日志
	 * @param tddlog [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void insertLog(JpPtLog tddlog) {
		tddlog.setId(Identities.randomLong()+"");
		tddlog.setLogDate(VeDate.getNow());
		String logPath=this.updateLogTxt(tddlog);
		tddlog.setRzlj(logPath);
		getMyBatisDao().insert(tddlog);
	}
	/**
	 * <功能详细描述>
	 * 修改订单日志
	 * @param tddlog [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void updateTDsDdlog(JpPtLog tddlog) {
		String logPath=this.updateLogTxt(tddlog);
		tddlog.setRzlj(logPath);
		getMyBatisDao().updateByPrimaryKeySelective(tddlog);
	}
	/**
	 * 保存每天最后一次修改日志
	 */
	public void saveLastdayLog(JpPtLog tddlog){
		tddlog.setLogDate(VeDate.getNowDateShort());
		List<JpPtLog> ptlogList = getMyBatisDao().getLastDayLog(tddlog);
		if(CollectionUtils.isEmpty(ptlogList)){
			insertLog(tddlog);
		}else{
			JpPtLog _tddlog = ptlogList.get(0);
			tddlog.setId(_tddlog.getId());
			_tddlog.setLogDate(VeDate.getNow());
			String logPath=updateLogTxt(tddlog);
			if(StringUtils.isNotBlank(logPath)){
				_tddlog.setRzlj(logPath);
			}
			try {
				update(_tddlog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}