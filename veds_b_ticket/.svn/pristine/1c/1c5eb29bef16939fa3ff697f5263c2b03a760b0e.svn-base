package cn.vetech.web.vedsb.xepnrgl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.xepnr.XePnrParam;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpxepnr.JpXepnr;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jpxepnr.JpXepnrServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpXepnrController extends MBaseControl<JpXepnr, JpXepnrServiceImpl>{
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	
	@Override
	public void updateInitEntity(JpXepnr t) {
	}

	@Override
	public void insertInitEntity(JpXepnr t) {
	}
	
	
	@RequestMapping(value = "query",method = RequestMethod.POST)
	public @ResponseBody Page query(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,String[] tpzt,Model model,HttpServletRequest request) throws Exception {
		Page page = null;
		try {
			Shyhb user = SessionUtils.getShshbSession();
			Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
			param.put("shbh", user.getShbh());
			page=this.baseService.selectAllJpXepnr(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询自动监控列表出错"+e.getMessage());
		}
		return page;
	}
	
	@RequestMapping(value = "xe")
	public @ResponseBody Map<String, String> xe(String ddbh,String pnrno,String xelx,Model model,HttpServletRequest request) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		Map<String,String> m=new HashMap<String, String>();
		m.put("status", "-1");
		if(StringUtils.isBlank(pnrno)){
			m.put("errmsg","pnrno不能为空");
			return m;
		}
		if(StringUtils.isBlank(xelx)){
			m.put("errmsg","xe类型不能为空");
			return m;
		}
		
		JpPz jppz=jpPzServiceImpl.getJpPzByShbh(user.getShbh());
		if(jppz == null){
			m.put("errmsg","请检查PID配置信息");
			return m;
		}
		
		if("XEPNR".equals(xelx.toUpperCase())){
			XePnrParam xp=new XePnrParam();
			xp.setOfficeId(jppz.getOfficeid());
			xp.setPnrNo(pnrno);
			xp.setUserid(user.getPidyh());
			xp.setUrl("http://"+jppz.getPzIp()+":"+jppz.getPzPort());
			String result=IbeService.xePnr(xp);// 1:XE成功;2:已被XE;3:需要授权
			m.put("status", result);
			if("1".equals(result)){
				m.put("errmsg", "XE成功");
				updateJpKhdd(user.getShbh(),ddbh,pnrno);
			}else if("2".equals(result)){
				updateJpKhdd(user.getShbh(),ddbh,pnrno);
				m.put("errmsg", "已被XE");
			}else if("3".equals(result)){
			    m.put("errmsg", "需要授权");
			}
		}else{
			m.put("errmsg","暂不支持:"+xelx);
		}
		return  m;
	}
	
    private void updateJpKhdd(String shbh,String ddbh,String pnrno){
    	JpKhdd jpkhdd=new JpKhdd();
    	jpkhdd.setShbh(shbh);
    	jpkhdd.setDdbh(ddbh);
    	jpkhdd.setXsPnrNo(pnrno);
    	jpkhdd.setXsPnrZt("XX");
    	jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKey(jpkhdd);
    }
}
