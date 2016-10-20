package cn.vetech.web.vedsb.b2bsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bDlhz;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bDlzh;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bDlhzServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bDlzhServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsxxServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;


/**
 * 
 * @author wangshengliang
 *
 */
@Controller
public class JpB2bDlzhController extends MBaseControl<JpB2bDlzh,JpB2bDlzhServiceImpl>{
	private static final String PAGE_SIZE = "10";
	
	@Override
	public void updateInitEntity(JpB2bDlzh t) {
	}

	@Override
	public void insertInitEntity(JpB2bDlzh t) {
	}
   
	
	@Autowired
	private JpB2bHkgsxxServiceImpl jpB2bHkgsxxServiceImpl;
	
	@Autowired
	private JpB2bHkgsServiceImpl jpB2bHkgsServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	
	@Autowired
	private JpB2bDlhzServiceImpl jpB2bDlhzServiceImpl;
	
	@RequestMapping(value="tree")
	public String tree(Model model,HttpServletRequest request, String bca){
		 //B2B	720102
		//B2C   720104
		if (StringUtils.isBlank(bca)) {
			bca="720102";
		}
		List<JpB2bHkgsxx>  hkgsxxList=jpB2bHkgsxxServiceImpl.selectByBca(bca);
		//model.addAttribute("", arg1)
		//JpB2bHkgsxx t=null;
		//model.addAttribute("zffsList", getZffs(t));
		model.addAttribute("hkgsxxList", hkgsxxList);
		return viewpath("tree");
	}
	
	@RequestMapping(value="getHkgsxx")
	public String getHkgsxx(Model model,HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize){
		 //B2B	720102
		//B2C   720104
		String pt="b2b";
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
		String hkgs=VeStr.getValue(param, "hkgs");
		String bca=VeStr.getValue(param, "bca");
		if(StringUtils.isBlank(bca)){
			bca="720102";
		}
		
		JpB2bHkgsxx  hkgsxx=jpB2bHkgsxxServiceImpl.selectByHkgs_Bca(user.getShbh() ,hkgs, bca);
		
		JpB2bHkgs  jphkgs=jpB2bHkgsServiceImpl.getB2bHkgsByBca(user.getShbh(), hkgs, bca);
		
		List<JpPz> pzList = jpPzServiceImpl.selectJpPzByShbh(user.getShbh());
		
		List<JpB2bHkgsxx>  hkgsxxList=jpB2bHkgsxxServiceImpl.selectByBca(bca);
		//model.addAttribute("", arg1)
		//JpB2bHkgsxx t=null;
		//model.addAttribute("zffsList", getZffs(t));
		if("720104".equals(bca)){
			pt="b2c";
		}
		
		JpB2bDlzh dlzh = new JpB2bDlzh();
		dlzh.setShbh(user.getShbh());
		dlzh.setHkgs(hkgs);
		dlzh.setBca(bca);
		PageHelper ph = new PageHelper();
		dlzh.setStart(ph.getStart(pageNum, pageSize));
		dlzh.setCount(ph.getCount(pageNum, pageSize));
		dlzh.setPageNum(pageNum);
		dlzh.setPageSize(pageSize);
		Page dlzhPage = this.baseService.getjpdlzhPage(dlzh);
		if (dlzhPage != null) {
			List<?> list = dlzhPage.getList();
            if(CollectionUtils.isNotEmpty(list)){			
            	attachService.shyhb("yhbh", user.getShbh()).execute(list);
            }
		}
		
		//获取航空公司登录网址信息
		List<JpB2bDlhz> jpB2bDlhzList=jpB2bDlhzServiceImpl.getMyBatisDao().queryListByHkgs(hkgs);
		
		model.addAttribute("jpB2bDlhzList", jpB2bDlhzList);
		
		model.addAttribute("pzList", pzList);
		model.addAttribute("dlzhPage", dlzhPage);
		model.addAttribute("t_b2b_hkgsxx", hkgsxx);
		model.addAttribute("t_b2b_hkgs", jphkgs);
		model.addAttribute("pt", pt.toUpperCase());
		model.addAttribute("bca", bca);
		model.addAttribute("t_b2b_hkgsxxList", hkgsxxList);
		model.addAttribute("zffsList", getZffs(hkgsxx,jphkgs));
		return viewpath("b2bzhsz");
	}
	
	@ResponseBody
	@RequestMapping(value="saveHkgs")
	public String saveHkgs(Model model,HttpServletRequest request, JpB2bHkgs  jphkgs) throws Exception{
		 //B2B	720102
		//B2C   720104
		try {
			Shyhb user = SessionUtils.getShshbSession();
			jphkgs.setShbh(user.getShbh());
			String bca = jphkgs.getBca();
			String hkgs = jphkgs.getHkgs();
			JpB2bHkgs b2bhkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(user.getShbh(), hkgs, bca);
			
			JpB2bHkgsxx  hkgsxx=jpB2bHkgsxxServiceImpl.selectByHkgs_Bca(user.getShbh() ,hkgs, bca);
			
			List<String> zfst = new ArrayList<String>();
			if(hkgsxx!=null){
				zfst = hkgsxx.getZffsList();
			}
			String zffsstr="";
			if(!zfst.isEmpty()){
				if(StringUtils.isBlank(hkgsxx.getZdzfzc()) ){
					if("CZ".equals(hkgs)){
						hkgsxx.setZdzfzc("1/2/3/4/6/");
					}else{
						hkgsxx.setZdzfzc("1/2/3/4/");
					}
				}
				for (String str : zfst) {
					if(hkgsxx.getZdzfzc().indexOf(str)>-1){
						zffsstr += str + "/"; //取所有支持的自动支付
					}
				}
			}
			boolean b = false;
			String zdzffs="";
			String[] zdzffsarr = request.getParameterValues("zdzffs");
			if(zdzffsarr!=null){
				for (String str : zdzffsarr) {
					if(StringUtils.isNotBlank(str)){
						zdzffs += str+ '/';
						if(zffsstr.indexOf(str) > -1){
							b = true;
						}
					}
				}
			}
			if(!b){
				zdzffs = "---";
			}
			
			jphkgs.setZdzffs(zdzffs);
			
			if (null == b2bhkgs) {
				jpB2bHkgsServiceImpl.insert(jphkgs);
			} else {
				jpB2bHkgsServiceImpl.updateByhkgs(jphkgs);
			}
			
			String[] idAttr = request.getParameterValues("id");
			String[] zhAttr = request.getParameterValues("zh");
			String[] mmAttr = request.getParameterValues("mm");
			String[] officeAttr = request.getParameterValues("office");
			String[] yhbhAttr = request.getParameterValues("yhbh");
			if (null == idAttr) {
				return "1";
			}
			for (int i=0;i<idAttr.length;i++ ) {
				if (StringUtils.isBlank(idAttr[i])) {
					JpB2bDlzh dlzh = new JpB2bDlzh();
					dlzh.setHkgs(hkgs);
					dlzh.setBca(bca);
					dlzh.setShbh(user.getShbh());
					dlzh.setZh(zhAttr[i]);
					dlzh.setMm(mmAttr[i]);
					dlzh.setOffice(officeAttr[i]);
					dlzh.setYhbh(yhbhAttr[i]);
					this.baseService.insert(dlzh);
				} else {
					JpB2bDlzh dlzh = new JpB2bDlzh();
					dlzh.setId(idAttr[i]);
					dlzh.setHkgs(hkgs);
					dlzh.setBca(bca);
					dlzh.setShbh(user.getShbh());
					dlzh.setZh(zhAttr[i]);
					dlzh.setMm(mmAttr[i]);
					dlzh.setOffice(officeAttr[i]);
					dlzh.setYhbh(yhbhAttr[i]);
					this.baseService.updateByIdAndBca(dlzh);
				}
			}
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存航空公司配置发生错误：" + e);
			return "0";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="checkB2bDlzh")
	public String checkB2bDlzh(Model model,HttpServletRequest request, String zh, String bca, String hkgs,String yhbh) {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			List<JpB2bDlzh> dlzhList = this.baseService.getDlzhList(zh, hkgs, bca, user.getShbh(),yhbh);
			if (null == dlzhList || dlzhList.isEmpty()) {
				return "1";
			} else {
				return "该账号已存在！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("验证航司账号是否存在出错："+ e);
			return "验证航司账号是否存在出错："+ e.getMessage();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="saveB2bDlzh")
	public String saveB2bDlzh(Model model,HttpServletRequest request, JpB2bDlzh dlzh) {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			dlzh.setShbh(user.getShbh());
			if (StringUtils.isBlank(dlzh.getId())) {
				dlzh.setSfsy("0");//启用
				this.baseService.insert(dlzh);
			} else {
				this.baseService.updateByIdAndBca(dlzh);
			}
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存航司账号出错："+ e);
			return "保存航司账号出错："+ e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="delDlzh")
	public String delDlzh(Model model,HttpServletRequest request, String id, String bca) {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			this.baseService.delDlzh(id, bca, user.getShbh());
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除航空登录账号发生错误：" + e);
			return "0";
		}
		
	}
		
	private List<Map<String,String>> getZffs(JpB2bHkgsxx t,JpB2bHkgs  jphkgs){
		if (null == t ) {
			return null;
		}
		if (null == jphkgs) {
			jphkgs = new JpB2bHkgs();
			jphkgs.setFs0("1");
			jphkgs.setFs1("1");
			jphkgs.setFs2("1");
			jphkgs.setFs3("1");
			jphkgs.setFs4("1");
			jphkgs.setFs5("1");
			jphkgs.setFs6("1");
			jphkgs.setFs7("1");
			jphkgs.setFs8("1");
			jphkgs.setFs9("1");
		}
		
		//取对应支付方式是否开启
		List<String>  zffs=new ArrayList<String>();
		zffs.add(t.getFs0());
		zffs.add(t.getFs1());
		zffs.add(t.getFs2());
		zffs.add(t.getFs3());
		zffs.add(t.getFs4());
		zffs.add(t.getFs5());
		zffs.add(t.getFs6());
		zffs.add(t.getFs7());
		zffs.add(t.getFs8());
		zffs.add(t.getFs9());
		
		List<String>  zffs_hkgs=new ArrayList<String>();
		zffs_hkgs.add(jphkgs.getFs0());
		zffs_hkgs.add(jphkgs.getFs1());
		zffs_hkgs.add(jphkgs.getFs2());
		zffs_hkgs.add(jphkgs.getFs3());
		zffs_hkgs.add(jphkgs.getFs4());
		zffs_hkgs.add(jphkgs.getFs5());
		zffs_hkgs.add(jphkgs.getFs6());
		zffs_hkgs.add(jphkgs.getFs7());
		zffs_hkgs.add(jphkgs.getFs8());
		zffs_hkgs.add(jphkgs.getFs9());
		
		//取对应支付名称
		String[] zffsmc = new String[10];
		zffsmc[0]="手动支付是否开启";
		zffsmc[1]="支付宝是否开启";
		zffsmc[2]="财付通是否开启";
		zffsmc[3]="汇付天下是否开启";
		zffsmc[4]="易宝是否开启";
		zffsmc[5]="快钱是否开启";
		zffsmc[6]="御航宝";
		zffsmc[7]="易商旅";
		zffsmc[8]="易生卡";
		zffsmc[9]="易航宝";

		List<Map<String,String>>  zffsList=new ArrayList<Map<String,String>>();
		for (int i = 0; i < 10; i++) {
			if ("1".equals(zffs.get(i))) {
				Map<String,String> a = new HashMap<String,String>();
				a.put("id", "fs" + i);
				a.put("mc", zffsmc[i]);
				a.put("val",zffs_hkgs.get(i));
				zffsList.add(a);
			}
		}
		return zffsList;
	}



}
