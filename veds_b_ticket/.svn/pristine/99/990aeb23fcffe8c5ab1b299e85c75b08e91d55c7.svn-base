package cn.vetech.web.vedsb.jpddsz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.service.job.QrtzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpddWork;
import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_gqd;
import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_jp;
import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_tpd;
import cn.vetech.vedsb.platpolicy.taobao.TaoBaoGyAutoCp2Service;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 网店设置
 * 
 * @author wangfeng
 * 
 */
@Controller
public class JpDdszController extends MBaseControl<JpDdsz, JpDdszServiceImpl> {
	private static Logger logger = LoggerFactory.getLogger(JpDdszController.class);
	@Autowired
	private JpddWork jpddWork;
	@Autowired
	private JpddWork_tpd jpddWork_tpd;
	@Autowired
	private JpddWork_gqd jpddWork_gqd;
	@Autowired
	private JpddWork_jp jpddWork_jp;
	@Autowired
	private QrtzServiceImpl qrtzServiceImpl;
	@Autowired
	private WdzlszServiceImpl wdzlszSImpl;
	@Autowired
	private ShyhbServiceImpl shyhbSImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmSImpl;
	@Autowired
	private TaoBaoGyAutoCp2Service taoBaoGyAutoCp2Service;
	@Autowired
	private JpKhddServiceImpl JpKhddServiceImpl;
	
	/**
	 * 打开新增（编辑）页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public String pageJpDdsz(HttpServletRequest request) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		
		String wdid = request.getParameter("wdid");
		Wdzlsz wdzlsz = new Wdzlsz();
		wdzlsz.setId(wdid);
		wdzlsz = wdzlszSImpl.getEntityById(wdzlsz);
		request.setAttribute("wdzlsz", wdzlsz);
		
		Shyhb record = new Shyhb();
		record.setShbh(user.getShbh());
		List<Shyhb> shyhbList = shyhbSImpl.getMyBatisDao().select(record);
		request.setAttribute("shyhbList", shyhbList);
		Shzfkm shzfkm = new Shzfkm();
		shzfkm.setShbh(user.getShbh());
		shzfkm.setSfqy("1");
		shzfkm.setSfskkm("1");
		List<Shzfkm> shzfkmList = shzfkmSImpl.getShzfkmList(shzfkm);
		request.setAttribute("shzfkmList", shzfkmList);
		
		JpDdsz jpDdsz = new JpDdsz();
		jpDdsz.setWdid(wdid);
		jpDdsz = this.baseService.getEntityById(jpDdsz);
		request.setAttribute("jpDdsz", jpDdsz);
		return viewpath("pageJpDdsz");
	}
	/**
	 * 保存机票导单设置
	 * @param request
	 * @param jpDdsz
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public String saveJpDdsz(HttpServletRequest request,@Valid @ModelAttribute("jpDdsz")JpDdsz jpDdsz) throws Exception {
		String type = request.getParameter("type");
		insertInitEntity(jpDdsz);
		if("edit".equals(type)){
			this.baseService.update(jpDdsz);
		}else{
			this.baseService.insert(jpDdsz);
		}
		registerJob(jpDdsz);
		return "/common/turning";
	}
	/**
	 * 机票导单列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public String listJpDdsz(HttpServletRequest request) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("zt", "1");
			param.put("shbh", user.getShbh());
			param.put("ywlxs", new String[]{"1001901","1001902"});
			List<Wdzlsz> wdzlszList = wdzlszSImpl.getWdZlszListByYwlx(param);
			JpDdsz record = new JpDdsz();
			record.setShbh(user.getShbh());
			List<JpDdsz> jpDdszList = this.baseService.getMyBatisDao().select(
					record);
			Map<String,JpDdsz> jpDdszMap = new HashMap<String, JpDdsz>();
			for(int i=0;i<jpDdszList.size();i++){
				JpDdsz oneJpDdsz = jpDdszList.get(i);
				jpDdszMap.put(oneJpDdsz.getWdid(), oneJpDdsz);
			}
			request.setAttribute("wdzlszList", wdzlszList);
			request.setAttribute("jpDdszMap", jpDdszMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewpath("listJpDdsz");
	}
	/**
	 * 测试用代码 扫描入库
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public String smrk(HttpServletRequest request) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
			String wdid = request.getParameter("wdid");
			String type = request.getParameter("type");
			if("0".equals(type)){//正常单入库
				String dateStr = request.getParameter("dh");
				jpddWork.queryorder(wdid,dateStr);	
			}else if("1".equals(type)){//退废单入库
				String dateStr = request.getParameter("dh");
				jpddWork_tpd.queryorder_tf(wdid,dateStr);
			}else if("2".equals(type)){//改签单入库
				String dateStr = request.getParameter("dh");
				jpddWork_gqd.queryorder_gq(wdid,dateStr);	
			}else if("3".equals(type)){//票号回填
				jpddWork_jp.ptjp_auto(wdid);
			}else if("4".equals(type)){//退废单复核
//				String dh = request.getParameter("dh");
//				jpddWork_tpd.fh(dh, user);
			}else if("5".equals(type)){//票号回填
//				String dh = request.getParameter("dh");
//				jpddWork_gqd.gqcl(dh, user);
			}else if("6".equals(type)){
				String dateStr = request.getParameter("dh");
				jpddWork.register_tbcp_job_byDateStr(wdid, dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "任务执行失败，原因："+e.getMessage();
		}
		return "任务执行完成";
	}
	/**
	 * 测试用代码 扫描入库
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public String zjp(HttpServletRequest request) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
		List<String> wbdhList = new ArrayList<String>();
		String wbdh = request.getParameter("wbdh");
		if(StringUtils.isNotBlank(wbdh)){
			wbdhList.add(wbdh);
		}else{
			List<String> _wbdhList = JpKhddServiceImpl.getZjpWbdh();
			wbdhList.addAll(_wbdhList);
		}
		for(int i=0;i<wbdhList.size();i++){
			String ddbh_shbh = wbdhList.get(i)+"_"+user.getShbh();
			taoBaoGyAutoCp2Service.TBOrderDetailByDddh(ddbh_shbh);
		}
		
		
		return "任务执行完成";
	}
	@RequestMapping
	public String ddrk(HttpServletRequest request){
		String wdid = StringUtils.trimToEmpty(request.getParameter("wdid"));
		String wbdh = StringUtils.trimToEmpty(request.getParameter("wbdh"));
		String errMsg = "";
		errMsg = jpddWork.getByWbdh(wbdh, wdid);
		if(StringUtils.isBlank(errMsg)){
			errMsg="执行导入订单成功";
			return "/common/turning";
		}
		request.setAttribute("errMsg", errMsg);
		return viewpath("sgDdrk");
	}
	@RequestMapping
	public String opensgDdrk(HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("zt", "1");
		param.put("shbh", user.getShbh());
		String gngj=request.getParameter("gngj");
		if("0".equals(gngj)){
			param.put("ywlxs", new String[]{"1001902"});
		}else{
			param.put("ywlxs", new String[]{"1001901"});
		}
		
		List<Wdzlsz> wdzlszList = wdzlszSImpl.getWdZlszListByYwlx(param);
		request.setAttribute("wdzlszList", wdzlszList);
		return viewpath("sgDdrk");
	}
	@Override
	public void insertInitEntity(JpDdsz jpDdsz) {
		Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
		jpDdsz.setCjyh(user.getBh());
		jpDdsz.setShbh(user.getShbh());
		jpDdsz.setCjsj(new Timestamp(new Date().getTime()));
		jpDdsz.setXgsj(new Timestamp(new Date().getTime()));
		jpDdsz.setXgyh(user.getBh());
	}

	@Override
	public void updateInitEntity(JpDdsz t) {
		// TODO Auto-generated method stub

	}
	/**
	 * 根据导单设置，添加和删除扫描job
	 */
	private void registerJob(JpDdsz jpDdsz){
		//开启和关闭导单job
		try {
			if("1".equals(jpDdsz.getDdKqzcdd())){
				logger.error("开启正常单扫描job");
				qrtzServiceImpl.add("10001", jpDdsz.getWdid());//注册正常单jop
				logger.error("开启票号回填扫描job");
				qrtzServiceImpl.add("10004", jpDdsz.getWdid());//注册票号回填
			}else{
				logger.error("关闭正常单扫描job");
				qrtzServiceImpl.del("10001", jpDdsz.getWdid());
				logger.error("关闭票号回填扫描job");
				qrtzServiceImpl.del("10004", jpDdsz.getWdid());
			}
			if("1".equals(jpDdsz.getDdKqtpdd())){
				logger.error("开启退废单扫描job");
				qrtzServiceImpl.add("10002", jpDdsz.getWdid());//注册退废单job
				if(PlatCode.TB.equals(jpDdsz.getWdpt())&&"0".equals(jpDdsz.getDdGngj())){
					qrtzServiceImpl.add("10006", jpDdsz.getWdid());//注册自动提交淘宝客户退票job
					qrtzServiceImpl.add("10007", jpDdsz.getWdid());//注册同步淘宝退票状态job
				}
				
			}else{
				logger.error("关闭退废单扫描job");
				qrtzServiceImpl.del("10002", jpDdsz.getWdid());
				if(PlatCode.TB.equals(jpDdsz.getWdpt())){
					qrtzServiceImpl.del("10006", jpDdsz.getWdid());
					qrtzServiceImpl.del("10007", jpDdsz.getWdid());//注册同步淘宝退票状态job
				}
			}
			if("1".equals(jpDdsz.getDdKqgqdd())){
				logger.error("开启改签单扫描job");
				qrtzServiceImpl.add("10003", jpDdsz.getWdid());//注册改签单job
			}else{
				logger.error("关闭改签单扫描job");
				qrtzServiceImpl.del("10003", jpDdsz.getWdid());
			}
			if("1".equals(jpDdsz.getDdautocp())){
				logger.error("开启订单自动出票job");
				qrtzServiceImpl.add("10008", jpDdsz.getWdid());//注册订单自动出票job
			}else{
				logger.error("关闭订单自动出票job");
				qrtzServiceImpl.del("10008", jpDdsz.getWdid());
			}
		} catch (Exception e) {
			logger.error("创建脚本错误",e);
			e.printStackTrace();
		}
	}
}