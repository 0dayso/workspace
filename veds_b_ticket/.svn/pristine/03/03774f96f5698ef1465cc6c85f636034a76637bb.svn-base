package cn.vetech.web.vedsb.pidgl;


import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.vetech.core.business.pid.api.pidgl.JpPid;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pidgl.PidglService;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;



/**
 * 部分方法加同步的原因是因为pid的编号无法与我们这边数据库做对应
 * 因此每次修改和新增实际上是吧数据库全部删除，然后把pid的配置查询过来新增
 * pid的备注字段remark，存储了商户编号和是否是默认配置，2个值，没有办法只能这么搞，有点恶心
 * 后面维护人员一定要注意
 * @author zhanglei
 *
 */
@Controller
public class PzglController  extends AbstractBaseControl{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PidglService pidglService;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	/**
	 * <查询PID配置信息>
	 * 
	 * @return [参数说明]
	 * 
	 * @return Page [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "list")
	public String queryPIDinfo(ModelMap model){
		//以后要从session里面获取登陆人的用户编号
		//然后用商户编号加上用户编号传入到PID的userid
		try {
			Shyhb yh = SessionUtils.getShshbSession();
			List<JpPid> list = pidglService.queryList(yh.getShbh());
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("", e);
		}
		return viewpath("list");
	}
	
	/**
	 * <执行PID连接操作>
	 * 
	 * @param no
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "connect_{no}", method = RequestMethod.GET)
	public String connect(@PathVariable("no") String no){
		pidglService.connect("WEB01", no);
		return "/common/turning";
		
	}
	
	/**
	 * <断开PID连接>
	 * 
	 * @param no
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "disconnect_{no}", method = RequestMethod.GET)
	public String disconnect(@PathVariable("no") String no){
		pidglService.disconnect("WEB01", no);
		return "/common/turning";
	}
	
	/**
	 * <删除PID配置>
	 * 
	 * @param no
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "delete_{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") String no,@RequestParam(value = "pidconnectionstate") String pidconnectionstate,ModelMap model){
		logger.debug(pidconnectionstate);
		try {
			if(pidconnectionstate.equals("已连接")){
					throw new Exception("当配置处于连接状态时不允许删除，必须先断掉后，再删除!!!");
			}
			pidglService.deletePid("WEB01", no);
		}catch (Exception e) {
			logger.error("",e);
			return this.addError("连接失败"+e.getMessage(),e,"list",model);
		}
		return "/common/turning";
	}
	
	
	/**
	 * <新增PID配置>
	 * 
	 * @param t
	 * @param model
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void add(JpPid t) throws Exception{
		pidglService.insertPid("WEB01", t);
	}
	@RequestMapping(value = "stop_{no}")
	public synchronized String stop(@PathVariable("no") String no) throws Exception{
		try {
			pidglService.stop(no);
			Shyhb yh = SessionUtils.getShshbSession();
			jpPzServiceImpl.tbPid(yh.getShbh(), yh.getBh(),null);
		}catch(Exception e){
			logger.error("停用失败",e);
			throw e;
		}
		return "/common/turning";
	}
	@RequestMapping(value = "start_{no}")
	public synchronized String start(@PathVariable("no") String no) throws Exception{
		try {
			pidglService.start(no);
			Shyhb yh = SessionUtils.getShshbSession();
			jpPzServiceImpl.tbPid(yh.getShbh(), yh.getBh(),null);
		}catch(Exception e){
			logger.error("启用失败",e);
			throw e;
		}
		return "/common/turning";
	}
	

	/**
	 * <修改PID配置>
	 * 
	 * @param no
	 * @param t
	 * @param model
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void modifyPID(JpPid t){
		pidglService.updatePid("WEB01", t);
	}
	
	/**
	 * <编辑的时候回显 的>
	 * 
	 * @param no
	 * @param model
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "edit_{no}")
	public String edit(@PathVariable("no") String no, ModelMap model) {
//		for(JpPid jppid:list){
//			if(jppid.getNo().equals(no)){
//				model.addAttribute("entity",jppid);
//			}
//		} 
		//这里可以根据no调用查询的方法得到回显的对象   
		try {
			JpPid jpPid = pidglService.getByNo("WEB01", no);
			Shyhb yh = SessionUtils.getShshbSession();
			JpPz jpPz=jpPzServiceImpl.getJpPzByShbh(yh.getShbh());
			ModelMap m = model.addAttribute("entity",jpPid);
			model.addAttribute("jppz",jpPz);
		} catch (Exception e) {
			logger.error("", e);
		}
		return viewpath("edit");
		
	}
	


	/**
	 * <save提交表单信息>
	 * 
	 * @param t
	 * @param model
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public synchronized String savePid(@Valid @ModelAttribute("entity")  JpPid t,String iata,ModelMap model){
		Shyhb yh = SessionUtils.getShshbSession();
		t.setRemark(yh.getShbh());
		setCheckboxValue(t);
		
		try{
			
			/**
			 * 恶心的处理
			 * 由于设置默认配置，需要把其他配置全部设置为非默认配置
			 * 然后再对具体某个配置设置默认配置
			 */
			if("1".equals(t.getSfmr())){
				List<JpPid> jpPzs = pidglService.queryList(yh.getShbh());
				for(JpPid jpPid : jpPzs){
					String remark=jpPid.getRemark();
					String remarks[] = remark.split(",");
					if(remarks.length>1){
						jpPid.setRemark(remarks[0]+",0");
					}else{
						jpPid.setRemark(yh.getShbh()+",0");
					}
					
					pidglService.updatePid("WEB01", jpPid);
				}
			}
			JpPid oldPid = null;
			try{
				oldPid = pidglService.getByNo("WEB01", t.getNo());
			}catch(Exception e){
				
			}
			if(oldPid!=null &&  StringUtils.isNotBlank(oldPid.getNo())){
				modifyPID(t);
			}else{
				add(t);
				
			}
			jpPzServiceImpl.tbPid(yh.getShbh(), yh.getBh(),iata);
		}catch (Exception e) {
			logger.error("保存失败", e);
			return this.addError("保存失败"+e.getMessage(),e,"edit",model);
		}
		return "/common/turning";
	}
	
	/**
	 * <该方法用于在提交form表单之前给checkbox的value属性赋值>
	 * 
	 * @param t [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void setCheckboxValue(@Valid @ModelAttribute("entity") JpPid t){
		if(t.getAutolink()==null){
			t.setAutolink("0");
		}else{
			//只要你对checkbox进行了点击操作， 那么它的value就有值
			t.setAutolink("1");
		}
		if(t.getAutologin()==null){
			t.setAutologin("0");
		}else{
			t.setAutologin("1");
		}
		if(t.getSaflytransmited()==null){
			t.setSaflytransmited("0");
		}else{
			t.setSaflytransmited("1");
		}
		if(t.getWebpid()==null){
			t.setWebpid("0");
		}else{
			t.setWebpid("1");
		}
		if(t.getIpe()==null){
			t.setIpe("0");
		}else{
			t.setIpe("1");
		}
		if(t.getBigpid()==null){
			t.setBigpid("0");
		}else{
			t.setBigpid("1");
		}
		if(t.getFalsepid()==null){
			t.setFalsepid("0");
		}else{
			t.setFalsepid("1");
		}
		if(t.getInputcheckcode()==null){
			t.setInputcheckcode("0");
		}else{
			t.setInputcheckcode("1");
		}
		if(t.getCantsl()==null){
			t.setCantsl("0");
		}else{
			t.setCantsl("1");
		}
		if(t.getCanprinv()==null){
			t.setCanprinv("0");
		}else{
			t.setCanprinv("1");
		}
		if(t.getHastransactionwait()==null){
			t.setHastransactionwait("0");
		}else{
			t.setHastransactionwait("1");
		}
		if(t.getAuthenticationtype()==null){
			t.setAuthenticationtype("0");
		}else{
			t.setAuthenticationtype("1");
		}
		
	}

	
}
