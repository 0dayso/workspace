package cn.vetech.vedsb.common.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.modules.mapper.JsonMapper;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Velog;

/**
 * @author lishun 
 */
@Service
public class VelogServiceImpl {
	protected final Logger logger = LoggerFactory.getLogger(VelogServiceImpl.class);
	private WebApplicationContext applicationContext;
	public VelogServiceImpl(){
		
	}
	public VelogServiceImpl(WebApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	
	public Velog createVelog() {
		Velog log = new Velog();
		log.setDydatetime(VeDate.getStringDate());
		log.setStatus("1");// 默认成功
		log.setSsxt("VEDS");// 默认系统为VEDS
		return log;
	}
	public String sendToVelog(Object bean, String method) {
		String json = JsonMapper.nonEmptyMapper().toJson(bean);
		Map<String, String> param = new HashMap<String, String>();
		param.put("data", json);
		CsbCacheService csbCache = applicationContext.getBean(CsbCacheService.class);
		VecsbCache vecsb = csbCache.get("9012");
		String VEDSLOGSURL = vecsb.getCsz1();
		if(StringUtils.isNotBlank(VEDSLOGSURL)&&"1".equals(vecsb.getCsz2())){
			try {
				return HttpClientUtil.doPost(VEDSLOGSURL + "/velog/vedslog/" + method, param, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("发送请求异常：",e);
				return null;
			}
		}
		return "";
	}
}
