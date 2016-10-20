package cn.vetech.web.vedsb.jpbcd;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shjcsj;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.ShjcsjServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpbcdgl.JpBcdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpBcdController extends MBaseControl<JPBcd, JpBcdServiceImpl>{
	
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpGqdServiceImpl jpGqdServiceImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	@Autowired
	private ShjcsjServiceImpl shjcsjServiceImpl;
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	@Override
	public void insertInitEntity(JPBcd t) {
		Shyhb yhb = SessionUtils.getShshbSession();
		t.setShbh(yhb.getShbh());
	}

	@Override
	public void updateInitEntity(JPBcd t) {
		Shyhb yhb = SessionUtils.getShshbSession();
		t.setXgyh(yhb.getBh());
		t.setXgsj(new Date());
		t.setShbh(t.getShbh());
	}
	
	@Override
	public void selectInitEntity(Map<String,Object> param){
		Shyhb yhb = SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", yhb.getShbh());
	}
	
	@Override
	public void pageAfter(Page page) {
		Shyhb yhb = SessionUtils.getShshbSession();
		List<?> list = page.getList();
		if(CollectionUtils.isNotEmpty(list)){
			attachService.veclass("wdpt").shyhb("cjyh", yhb.getShbh()).shyhb("shyh", yhb.getShbh()).shjcsj("bclx", yhb.getShbh()).execute(list);
		}
	}
	
	@RequestMapping(value="viewlist")
	public String viewlist(Model model){
		Shyhb user = SessionUtils.getShshbSession();
		List<Shjcsj> list = this.shjcsjServiceImpl.getShjcsjList(user.getShbh(), "1001301");
		model.addAttribute("list", list);
		return viewpath("list");
	}
	/**
	 * 打开修改补差单页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String toedit(String id,ModelMap model,String sh,String bcdDetail,String ddbh,String ywlxs){//bcdDetail是差异单列表中点击补差单号弹出补差详情层的标志
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if(StringUtils.isNotBlank(id)){
				JPBcd jpbcd = new JPBcd();
				jpbcd.setShbh(yhb.getShbh());//商户编号必传
				jpbcd.setId(id);
				JPBcd bcd = this.baseService.getEntityById(jpbcd);
				model.addAttribute("bcd", bcd);
				model.addAttribute("bcdDetail",bcdDetail);
			}
			List<Shjcsj> list = this.shjcsjServiceImpl.getShjcsjList(yhb.getShbh(), "1001301");
			model.addAttribute("list", list);
			model.addAttribute("sh", sh);//判断是否是审核
			model.addAttribute("ddbh",ddbh);
			model.addAttribute("ywlxs", ywlxs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewpath("edit");
	}
	
	
	/**
	 * 审核补差单
	 * @param jpbcd
	 * @return
	 */
	@RequestMapping
	public String shBcd(@ModelAttribute("entity") JPBcd jpbcd){//先调父类editForm方法根据id查询对象
		try {
			updateInitEntity(jpbcd);
			Shyhb yhb = SessionUtils.getShshbSession();
			jpbcd.setShsj(new Date());
			jpbcd.setShyh(yhb.getBh());
			jpbcd.setBczt("1");
			this.baseService.update(jpbcd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("审核补差单失败!", e);
		}
		return "/common/turning";
	}
	
	/**
	 * 取消补差单
	 * @param jpbcd
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String qxBcd(@ModelAttribute("entity") JPBcd jpbcd){
		try {
			updateInitEntity(jpbcd);
			Shyhb yhb = SessionUtils.getShshbSession();
			jpbcd.setShyh(yhb.getBh());
			jpbcd.setShsj(new Date());
			jpbcd.setBczt("3");
			this.baseService.update(jpbcd);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消补差单失败!", e);
			return "0";
		}
	}
	
	/**
	 * 打开支付页面
	 * @param jpbcd
	 * @return
	 */
	@RequestMapping
	public String toBcdPay(@ModelAttribute("entity") JPBcd jpbcd,ModelMap model){
		Shyhb yhb = SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		Shzfkm shzfkm = new Shzfkm();
		shzfkm.setSfqy("1");
		shzfkm.setShbh(shbh);
		Wdzlsz t = new Wdzlsz();
		t.setId(jpbcd.getWdid());
		Wdzlsz t1 = this.wdzlszServiceImpl.getEntityById(t);
		Shjcsj shjcsj = this.shjcsjServiceImpl.getMyBatisDao().getShjcsj(yhb.getShbh(),jpbcd.getBclx());
		model.addAttribute("shjcsj", shjcsj);
		List<Shzfkm> zfkmlist = this.shzfkmServiceImpl.getShzfkmList(shzfkm);
		model.addAttribute("zfkmlist", zfkmlist);
		model.addAttribute("t1",t1);
		return viewpath("bcdPay");
	}
	
	@RequestMapping
	public String bcdPay(@ModelAttribute("entity") JPBcd jpbcd){
		try {
			updateInitEntity(jpbcd);
			Shyhb yhb = SessionUtils.getShshbSession();
			jpbcd.setWcyh(yhb.getBh());//完成用户
			jpbcd.setWcsj(new Date());//完成时间
			jpbcd.setSkzt("1");//状态改为已收款
			jpbcd.setBczt("2");//状态改为已完成
			this.baseService.update(jpbcd);//更新
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("补差单支付失败!", e);
		}
		return "/common/turning";
	}
	
	@RequestMapping
	public String saveBcd(String ddbh,String bclx,String bcje,String bcsm,String ywlxs){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<JpTpdMx> mxlist = new ArrayList<JpTpdMx>();
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if("jpdd".equals(ywlxs)){
				JpKhdd jpkhdd = new JpKhdd();
				jpkhdd.setShbh(yhb.getShbh());
				jpkhdd.setDdbh(ddbh);
				jpkhdd = this.jpKhddServiceImpl.getEntityById(jpkhdd);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shbh", yhb.getShbh());
				map.put("ddbh", ddbh);
				list = this.jpJpServiceImpl.getjpjpByddbh(map);
				JPBcd jpbcd = new JPBcd();//补差单bean
				if(CollectionUtils.isNotEmpty(list)){
					Map<String, Object> jp = list.get(0);
					jpbcd.setCpDatetime((Timestamp)jp.get("CP_DATETIME"));
				}
				//jpbcd.setId(VeDate.getNo(6));
				jpbcd.setHc(jpkhdd.getHc());
				jpbcd.setHkgs(jpkhdd.getHkgs());
				jpbcd.setFaid(jpkhdd.getFaid());
				jpbcd.setDdbh(jpkhdd.getDdbh());
				jpbcd.setWdZclx(jpkhdd.getWdZclx());
				jpbcd.setBczt("0");//已申请
				jpbcd.setWdpt(jpkhdd.getWdpt());
				jpbcd.setPnrNo(jpkhdd.getXsPnrNo());
				jpbcd.setWbdh(jpkhdd.getWbdh());
				jpbcd.setBclx(bclx);
				jpbcd.setBcje(NumberUtils.toDouble(bcje));
				jpbcd.setBcsm(bcsm);
				jpbcd.setSkzt("0");
				jpbcd.setShbh(yhb.getShbh());
				jpbcd.setCjyh(yhb.getBh());
				jpbcd.setCjsj(new Date());
				jpbcd.setWdid(jpkhdd.getWdid());
				jpbcd.setYwlx("01");//机票正常单
				jpbcd.setYwdh(jpkhdd.getDdbh());
				this.baseService.insert(jpbcd);
			}else if("gqdd".equals(ywlxs)){
				JpGqd jpgqd = new JpGqd();
				jpgqd = this.jpGqdServiceImpl.getJpGqdByGqdh(ddbh, yhb.getShbh());
				JPBcd jpbcd = new JPBcd();//补差单bean
				//jpbcd.setId(VeDate.getNo(6));
				jpbcd.setHc(jpgqd.getHc());
				jpbcd.setHkgs(jpgqd.getHkgs());
				jpbcd.setFaid(jpgqd.getFaid());
				jpbcd.setDdbh(jpgqd.getDdbh());
				jpbcd.setWdZclx(jpgqd.getWdZclx());
				jpbcd.setBczt("0");//已申请
				jpbcd.setWdpt(jpgqd.getWdpt());
				jpbcd.setPnrNo(jpgqd.getXsPnrNo());
				jpbcd.setWbdh(jpgqd.getWbdh());
				jpbcd.setBclx(bclx);
				jpbcd.setBcje(NumberUtils.toDouble(bcje));
				jpbcd.setBcsm(bcsm);
				jpbcd.setSkzt("0");
				jpbcd.setShbh(yhb.getShbh());
				jpbcd.setCjyh(yhb.getBh());
				jpbcd.setCjsj(new Date());
				jpbcd.setWdid(jpgqd.getWdid());
				jpbcd.setYwlx("03");//机票改签单
				jpbcd.setYwdh(jpgqd.getGqdh());
				this.baseService.insert(jpbcd);
			}else if("tpdd".equals(ywlxs)){
				mxlist = this.jpTpdMxServiceImpl.getJpTpdMxByTpdh(ddbh, yhb.getShbh());
				JpTpd jptpd = new JpTpd();
				jptpd = this.jpTpdServiceImpl.getJpTpdByTpdh(ddbh, yhb.getShbh());
				JPBcd jpbcd = new JPBcd();//补差单bean
				if(CollectionUtils.isNotEmpty(mxlist)){
					JpTpdMx mx = mxlist.get(0);
					jpbcd.setHkgs(mx.getHkgs());
				}
				//jpbcd.setId(VeDate.getNo(6));
				jpbcd.setHc(jptpd.getHc());
				jpbcd.setFaid(jptpd.getFaid());
				jpbcd.setDdbh(jptpd.getDdbh());
				jpbcd.setWdZclx(jptpd.getWdZclx());
				jpbcd.setBczt("0");//已申请
				jpbcd.setWdpt(jptpd.getWdpt());
				jpbcd.setPnrNo(jptpd.getXsPnrNo());
				jpbcd.setWbdh(jptpd.getWbdh());
				jpbcd.setBclx(bclx);
				jpbcd.setBcje(NumberUtils.toDouble(bcje));
				jpbcd.setBcsm(bcsm);
				jpbcd.setSkzt("0");
				jpbcd.setShbh(yhb.getShbh());
				jpbcd.setCjyh(yhb.getBh());
				jpbcd.setCjsj(new Date());
				jpbcd.setWdid(jptpd.getWdid());
				jpbcd.setYwlx("02");//机票退票单
				jpbcd.setYwdh(jptpd.getTpdh());
				this.baseService.insert(jpbcd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增补差单失败", e);
		}
		return "/common/turning";
	}
}
