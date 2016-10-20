package cn.vetech.web.vedsb.cpsz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZffs;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.cpsz.JpCpymSz;
import cn.vetech.vedsb.jp.entity.office.JpOffice;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsxxServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bZffsServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpCpymSzServiceImpl;
import cn.vetech.vedsb.jp.service.office.JpOfficeServiceImpl;
import cn.vetech.vedsb.platpolicy.jzcp.B2cnewException;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlitripCreateorderUnfreezeRequest;
import com.taobao.api.response.AlitripCreateorderUnfreezeResponse;


/**
 * 
 * @author wangshengliang
 *
 */
@Controller
public class JpCpymSzController extends MBaseControl<JpCpymSz,JpCpymSzServiceImpl>{
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	
	@Autowired
	private JpPtzcFzszServiceImpl jpPtzcFzszServiceImpl;
	
	@Autowired
	private JpB2bHkgsxxServiceImpl jpB2bHkgsxxServiceImpl;
	
	@Autowired
	private JpB2bZffsServiceImpl jpB2bZffsServiceImpl;
	
	@Autowired
	private JpOfficeServiceImpl jpOfficeServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Override
	public void updateInitEntity(JpCpymSz t) {
		
	}

	@Override
	public void insertInitEntity(JpCpymSz t) {
		
	}
	
	@RequestMapping(value = "getCpymSz")
	public String getCpymSz(Model model,JpCpymSz t){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		JpCpymSz cpymsz=this.baseService.selectByShbh(shbh,t.getCgly());
		model.addAttribute("cpymsz", cpymsz);
		model.addAttribute("cgly", t.getCgly());
		return viewpath("jpcpymsz");
	}
	
	/**
	 * 获得平台账号设置树列表
	 * @param model
	 * @param pt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPtzhtree")
	public String getPtzhtree(Model model, String pt) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		JpPtzcZh jpPtzcZh = new JpPtzcZh();
		jpPtzcZh.setShbh(user.getShbh());
		jpPtzcZh.setOrderBy("ptzcbs asc");
		List<JpPtzcZh> ptzhList = jpPtzcZhServiceImpl.getJpPtzcZhForList(jpPtzcZh);
		model.addAttribute("ptzhList", ptzhList);
		return viewpath("lable/tree");
	}
	
	/**
	 * 跳转B2B支付方式设置tree页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getB2bzffs")
	public String getB2bzffs(ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		JpB2bZffs jpB2bZffs = new JpB2bZffs();
		jpB2bZffs.setShbh(user.getShbh());
		List<JpB2bZffs> zfList = jpB2bZffsServiceImpl.getMyBatisDao().select(jpB2bZffs);
		model.addAttribute("zfList", zfList);
		/**
		 * 支付科目
		 */
		Shzfkm zfkm = new Shzfkm();
		zfkm.setShbh(user.getShbh());
		zfkm.setSffkkm("1");
		zfkm.setSycp("1001901");//机票产品
		zfkm.setSfqy("1");
		List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);
		model.addAttribute("zfkmList", zfkmList);
		return viewpath("lable/b2bzffssz");
	}
	
	@ResponseBody
	@RequestMapping(value = "saveb2bzffs")
	public String saveb2bzffs(ModelMap model, HttpServletRequest request){
		try {
			Shyhb user = SessionUtils.getShshbSession();
			String[] idAttr = request.getParameterValues("id");
			String[] zfkmXtAttr = request.getParameterValues("zfkmXt");
			if (idAttr == null) {
				return "没有传入数据！";
			}
			for (int i=0;i<idAttr.length;i++) {
				JpB2bZffs zffs = new JpB2bZffs();
				zffs.setId(idAttr[i]);
				zffs.setShbh(user.getShbh());
				zffs.setZfkmXt(zfkmXtAttr[i]);
				jpB2bZffsServiceImpl.updateZffsById(zffs);
			}
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存航司b2b支付方式设置发生错误：" + e);
			return "保存航司b2b支付方式设置发生错误：" + e.getMessage();
		}
	}
	
	/**
	 * 获取采购平台支付方式对应设置
	 * @param model
	 * @param pt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPtFzsz")
	public String getPtFzsz(ModelMap model, String pt) throws Exception {
		try{
			Shyhb user = SessionUtils.getShshbSession();
			//ywlx 1 销售 2 采购
			List<JpPtzcFzsz> fzszList = jpPtzcFzszServiceImpl.getFzszListByYwlx("2", user.getShbh());
			/**
			 * 支付科目
			 */
			Shzfkm zfkm = new Shzfkm();
			zfkm.setShbh(user.getShbh());
			zfkm.setSffkkm("1");
			zfkm.setSycp("1001901");//机票产品
			zfkm.setSfqy("1");
			List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);
			model.addAttribute("zfkmList", zfkmList);
			model.addAttribute("fzszList", fzszList);
			return viewpath("lable/opzffssz");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取采购平台支付方式对应设置发生错误：" + e);
			return this.addError("获取采购平台支付方式对应设置发生错误：" + e.getMessage(), e, "lable/opzffssz", model);
		}
	}
	
	/**
	 * 获得商户的多个配置
	 * @param model
	 * @param pt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getOfficeList")
	public String getOfficeList(ModelMap model) throws Exception {
		try{
			Shyhb user = SessionUtils.getShshbSession();
			//List<JpPz> pzList = jpPzServiceImpl.selectJpPzByShbh(user.getShbh());
			List<JpOffice> officelist= jpOfficeServiceImpl.selectJpOfficeByShbh(user.getShbh());
			
			if(CollectionUtils.isNotEmpty(officelist)){
				attachService.zfkm("bopcgkm").execute(officelist);
			}
			model.addAttribute("officelist", officelist);
			return viewpath("lable/bopofficelist");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取office设置列表发生错误：" + e);
			return this.addError("获取office设置列表发生错误：" + e.getMessage(), e, "lable/bopofficelist", model);
		}
	}
	
	/**
	 * 通过机票配置id取单个配置
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preEditOffice_{id}")
	public String preEditOffice(@PathVariable("id") String id, ModelMap model) {
		try{
			Shyhb user = SessionUtils.getShshbSession();
			JpOffice jo=new JpOffice();
			jo.setShbh(user.getShbh());
			jo.setId(id);
			jo =  jpOfficeServiceImpl.getJpOffice(jo);
			model.addAttribute("jo", jo);
			/**
			 * 支付科目
			 */
			Shzfkm zfkm = new Shzfkm();
			zfkm.setShbh(user.getShbh());
			zfkm.setSffkkm("1");
			zfkm.setSycp("1001901");//机票产品
			zfkm.setSfqy("1");
			List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);
			model.addAttribute("zfkmList", zfkmList);
			System.out.println(viewpath("lable/bopofficeedit"));
			return viewpath("lable/bopofficeedit");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取单个office设置发生错误：" + e);
			return this.addError("获取单个office设置发生错误：" + e.getMessage(), e, "lable/bopofficeedit", model);
		}
	}
	
	/**
	 * 保存配置
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "savePz")
	public String savePz(JpOffice jo, Model model) {
		try{
			Shyhb user = SessionUtils.getShshbSession();
			jo.setShbh(user.getShbh());
			jo.setXgsj(VeDate.getNow());
			jo.setXgyh(user.getBh());
			jpOfficeServiceImpl.getMyBatisDao().updateByPrimaryKey(jo);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存office配置发生错误：" + e);
			return "保存office配置发生错误：" + e.getMessage();
		}
	}
	
	
	/**
	 * 新增配置
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addPz")
	public String addPz(JpOffice jo, Model model) {
		try{
		    Shyhb user = SessionUtils.getShshbSession();
			JpOffice jpoffice=new JpOffice();
			jpoffice.setShbh(user.getShbh());
			jpoffice.setOfficeid(jo.getOfficeid());
			jpoffice=jpOfficeServiceImpl.getJpOffice(jpoffice);
			if (jpoffice != null) {
				return "2";
			}
			jo.setShbh(user.getShbh());
			jo.setCjsj(VeDate.getNow());
			jo.setCjyh(user.getBh());
			jo.setXgsj(VeDate.getNow());
			jo.setXgyh(user.getBh());
			jpOfficeServiceImpl.insert(jo);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存office配置发生错误：" + e);
			return "保存office配置发生错误：" + e.getMessage();
		}
	}
	@RequestMapping(value="getHkgsB2Btree")
	public String getHkgsB2Btree(Model model,HttpServletRequest request, String bca){
		 //B2B	720102
		//B2C   720104
		if (StringUtils.isBlank(bca)) {
			bca="720102";
		}
		List<JpB2bHkgsxx>  hkgsxxList=jpB2bHkgsxxServiceImpl.selectByBca(bca);
		model.addAttribute("hkgsxxList", hkgsxxList);
		return viewpath("lable/tree");
	}
	
	@ResponseBody
	@RequestMapping(value = "jpCpymSzSave")
	public String jpCpymSzSave(JpCpymSz t){
		String result="1";
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		t.setShbh(shbh);
		JpCpymSz cpymsz=this.baseService.selectByShbh(shbh,t.getCgly());
		// 新增
		if (null == cpymsz) {
			try {
				this.baseService.insert(t);
			} catch (Exception e) {
				e.printStackTrace();
				result="0";
			}
		} else {// 修改
			try {
				t.setId(cpymsz.getId());
				this.baseService.update(t);
			} catch (Exception e) {
				e.printStackTrace();
				result="0";
			}
		}
	   return result;
	}

	/**
	 * 调用淘宝接口解除余额不足限制状态
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws B2cnewException
	 */
	@ResponseBody
	@RequestMapping(value = "tbunfreeze")
	public String tbunfreeze(String hkgs, String asmsddbh, HttpServletRequest request, ModelMap model) {
		Shyhb user = SessionUtils.getShshbSession();
		try {
			//取淘宝平台账号设置
			JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.TB, user.getShbh());
			
			if (null == ptzh || StringUtils.isBlank(ptzh.getUrl1()) || StringUtils.isBlank(ptzh.getUser1()) || StringUtils.isBlank(ptzh.getPwd1()) || StringUtils.isBlank(ptzh.getUrl2())) {
				return "下单相关信息没有设置，请检查平台账号中淘宝相关信息是否设置完整!";
			}
			String rtn = this.unfreeze(ptzh);
			if("SUCCESS".equals(rtn)){
				return "1";
			} else {
				return "解除余额不足限制失败！未知错误！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "解除余额不足限制失败！原因：" + e.getMessage();
		}
		
	}
	
	/**
	 * 调用淘宝接口进行解除余额不足限制状态
	 * @return
	 * @throws Exception
	 */
	public String unfreeze(JpPtzcZh ptzh) throws Exception{
		TaobaoClient client=new DefaultTaobaoClient(ptzh.getUrl1(), ptzh.getUser1(), ptzh.getPwd1(),"xml",30*1000,2*60*1000);
		AlitripCreateorderUnfreezeRequest req = new AlitripCreateorderUnfreezeRequest();
		AlitripCreateorderUnfreezeResponse res = client.execute(req, ptzh.getUrl2());
		if(res.isSuccess()){
			return "SUCCESS";
		}else{
			return res.getSubMsg()+"["+res.getErrorCode()+"]";
		}
	}
}
