package cn.vetech.web.webcontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.service.BYdrzCzrzMxServiceImpl;
import cn.vetech.vedsb.common.service.cache.ShsysCache;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.job.QrtzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;

@Controller
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private BYdrzCzrzMxServiceImpl bYdrzCzrzMxServiceImpl;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;
	@Autowired
	private ShsysCache shsysCache;

	@Autowired
	private AttachService attachService;
	@Autowired
	private JpDdszServiceImpl jpDdszServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@RequestMapping
	public @ResponseBody String trans(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		
		//查询机票订单
		JpKhdd tj=new JpKhdd();
		tj.setDdbh("JP160318000030");
		tj.setShbh("XIAOXIN");
		JpKhdd jpKhdd=jpKhddServiceImpl.getEntityById(tj);
		
		try {
			jpDdszServiceImpl.insert(new JpDdsz());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("over------");
		return null;
	}
	

	@RequestMapping
	public @ResponseBody String test1(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		logger.error(request.getParameterMap().toString());
		logger.error(bYdrzCzrzMxServiceImpl.toString());
		return "";
	}

	@RequestMapping
	public @ResponseBody String testJob(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		try {
			qrtzServiceImpl.add("10001", "1603160000001");
		} catch (Exception e) {
			logger.error("创建脚本错误", e);
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping
	public @ResponseBody String testCache(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		try {
			logger.error(shsysCache.getUser("00002"));
		} catch (Exception e) {
			logger.error("创建脚本错误", e);
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping
	public @ResponseBody String testAttach(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		try {
			List<Shbm> l = new ArrayList<Shbm>();
			Shbm s = new Shbm();
			s.setBzbz("0002039");
			s.setDh("0002042");
			l.add(s);
			
			s = new Shbm();
			s.setBzbz("0002043");
			s.setDh("0002040");
			s.setCjyh("XIAOXINADMIN");
			s.setShbh("XIAOXIN");
			l.add(s);
			
			

		//	attachService.veclass("Bzbz").veclass("dh").shyhb2("Cjyh","Shbh").execute(s);
			for(Shbm so:l){
				Object oo1=so.getEx().get("BZBZ");
				Object oo2=so.getEx().get("DH");
				Object oo3=so.getEx().get("CJYH");
				System.out.println(oo1);
				System.out.println(oo2);
				System.out.println(oo3);
			}
			
			/////////////
			
			
			List<Map> lmap = new ArrayList<Map>();
			Map sm = new HashMap();
			sm.put("BZBZ", "0002039");
			sm.put("DH", "0002042");
			sm.put("YHBH", "XIAOXINADMIN");
			lmap.add(sm);
			attachService.veclass("BZBZ").veclass("DH").shyhb("YHBH", "XIAOXIN").execute(lmap);
			
			
			for(Map so:lmap){
			System.out.println(so);	 
			}
			
			
		//	attachService.veclass("BZBZ").veclass("DH").execute(lmap);
			
		} catch (Exception e) {
			logger.error("创建脚本错误", e);
			e.printStackTrace();
		}
		return "";
	}

}
