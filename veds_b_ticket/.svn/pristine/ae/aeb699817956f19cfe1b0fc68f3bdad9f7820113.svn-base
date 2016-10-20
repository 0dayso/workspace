package cn.vetech.web.vedsb.common;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.SmsFsdl;
import cn.vetech.vedsb.common.entity.base.SmsMb;
import cn.vetech.vedsb.common.service.base.SmsServiceImpl;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jphbyd.JpHbydMxServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class SmsController extends MBaseControl<SmsFsdl, SmsServiceImpl>{
	@Autowired
	private SmsServiceImpl smsService;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private  JpHbydMxServiceImpl JpHbydMxServiceImpl;
	@Override
	public void updateInitEntity(SmsFsdl t) {
		
	}

	@Override
	public void insertInitEntity(SmsFsdl t) {
		
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "enterSendSmsPage")
	public String enterSendSmsPage(Model model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		try{
			String ddbh=request.getParameter("ddbh");
			JpKhdd jpKhdd = new JpKhdd();
			jpKhdd.setDdbh(ddbh);
			jpKhdd.setShbh(user.getShbh());
			jpKhdd = jpKhddServiceImpl.getKhddByDdbh(jpKhdd);
			SmsMb mb=new SmsMb();
			mb.setSfkq("1");//是否启用1为开启状态0为关闭状态，关闭之后，模板不能使用，在手工发送的地方都不出现。如果是商户的模板，这里操作之后会将商户的子模板全部关闭，打开也会将商户的子模板全部打开。
			mb.setMblx("2");//1为平台模板 2为商户模板
			List<SmsMb> smsmblist=smsService.querySmsmbByXcd(mb);
			model.addAttribute("ddbh", ddbh);
			model.addAttribute("mobiles", jpKhdd.getNxsj());
			model.addAttribute("mblist", smsmblist);
		} catch (Exception e){
			logger.error("进入发送短信页面失败", e);
		}
		return "/common/sendsms";
	}
	
	/**
	 * 
	 * @param smsFsdl
	 * @return
	 */
	@RequestMapping(value = "saveMessage")
	@ResponseBody
	public int saveMessage(@ModelAttribute()SmsFsdl smsFsdl){
		try{
			Shyhb user = SessionUtils.getShshbSession();
			smsFsdl.setFl("2");//分类1表示平台发送的2表示商户发送的
			smsFsdl.setFsCzr(user.getBh()+user.getXm());
			smsFsdl.setFsDw(user.getShbh());
			smsFsdl.setCjsj(new Date());
			smsFsdl.setFsfs("1");//发送方式1自动发送0手动发送
			smsFsdl.setJsShbh(user.getShbh());
			smsFsdl.setDxfsts((short)0);
			smsFsdl.setIfqm("0");
			smsFsdl.setTxfsid("");
			this.baseService.insert(smsFsdl);
		} catch (Exception e){
			logger.error("插入短信记录失败", e);
			return 0;//失败
		}
		
		return 1;//成功
	}

	/**
	 * <行程单管理的批量短信>
	 * 
	 * @param smsFsdl
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "batchMessage")
	@ResponseBody
	public int batchMessage(@ModelAttribute()SmsFsdl smsFsdl){
		Shyhb user = SessionUtils.getShshbSession();
		String ids="";
		if(StringUtils.isNotBlank(smsFsdl.getTxfsid())){
			ids=smsFsdl.getTxfsid();
		}
		smsFsdl.setFl("2");//分类1表示平台发送的2表示商户发送的
		smsFsdl.setFsCzr(user.getBh()+user.getXm());
		smsFsdl.setFsDw(user.getShbh());
		smsFsdl.setCjsj(new Date());
		smsFsdl.setFsfs("1");//发送方式1自动发送0手动发送
		smsFsdl.setJsShbh(user.getShbh());
		smsFsdl.setDxfsts((short)0);
		smsFsdl.setIfqm("0");
		smsFsdl.setTxfsid("");
		try {
			return this.baseService.insertSmsChangeYjd(smsFsdl,ids);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("行程单管理的批量短信发送短信失败", e);
			return 0;
		}
	}
	
	/**
	 * <修改短信类型模板时,将通配符修改成对应的信息>
	 * 
	 * @param nr
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "getSmsNr")
	@ResponseBody
	public String getSmsNr(@RequestParam(value = "nr")String nr,@RequestParam(value = "ddbh")String ddbh){
		return this.baseService.getSmsNr(nr,ddbh);
	}
	
	/**
	 * 航班异动短信发送
	 * @param jshm
	 * @param fssj
	 * @param fsnr
	 * @param zt
	 * @return
	 */
	@RequestMapping(value = "HaydSendMessage")
	public @ResponseBody int HaydSendMessage(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "jshm", defaultValue = "") String jshm,
			@RequestParam(value = "fssj", defaultValue = "") String fssj,
			@RequestParam(value = "fsnr", defaultValue = "") String fsnr,
			@RequestParam(value = "zt", defaultValue = "") String zt){
		Shyhb yh = SessionUtils.getShshbSession();
		SmsFsdl smsFsdl = new SmsFsdl();
		int result = 0;
		try {
			fsnr = new String(fsnr.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		if(StringUtils.isNotBlank(jshm)){
			String[] strArr = jshm.trim().split("\\s{1,}");
			for (int i = 0; i < strArr.length; i++) {
				if (StringUtils.isNotBlank(strArr[i])) {
					smsFsdl.setId(VeDate.getNo(4));
					smsFsdl.setFl("2");
					smsFsdl.setFsCzr(yh.getBh());
					smsFsdl.setFsDw(yh.getShbh());
					smsFsdl.setFsnr(fsnr);
					if("1".equals(zt)){
						smsFsdl.setMbbh("5");
					}else if("2".equals(zt)){
						smsFsdl.setMbbh("4");
					}else{
						smsFsdl.setMbbh("6");
					}
					smsFsdl.setJshm(strArr[i]);
					smsFsdl.setFssj(VeDate.strToDate(fssj));
					smsFsdl.setCjsj(VeDate.getNow());
					smsFsdl.setYqfssj(VeDate.strToDate(fssj));
					smsFsdl.setFsfs("1");
					try {
						this.baseService.getMyBatisDao().insert(smsFsdl);
						result = 1;
					} catch (Exception e) {
						e.printStackTrace();
						result = 2;
					}
				}
			}
		}
		JpHbydMxServiceImpl.getMyBatisDao().updateByYdid(id);
		return result;
	}
	
}
