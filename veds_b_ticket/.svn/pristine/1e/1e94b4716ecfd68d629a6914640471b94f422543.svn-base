package cn.vetech.web.vedsb.eterm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.business.pid.api.eterm.CommandHandler;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class HpController extends AbstractBaseControl{
	@Autowired
	private CsbCacheService cacheService;
	@RequestMapping(value="command",method = RequestMethod.POST)
	public @ResponseBody String command(String command){
		
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2(); 
		Shyhb yhb =SessionUtils.getShshbSession();
		CommandHandler commandHandler = new CommandHandler();
		try {
			String data = commandHandler.excute(command,yhb.getPidyh(), yhb.getMm(), url);
			return data;
		} catch (EtermException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
