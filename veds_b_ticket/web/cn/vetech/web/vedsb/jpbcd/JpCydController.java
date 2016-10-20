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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JpCyd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpbcdgl.JpBcdServiceImpl;
import cn.vetech.vedsb.jp.service.jpbcdgl.JpCydServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpCydController extends MBaseControl<JpCyd, JpCydServiceImpl>{
	@Autowired
	private JpCydServiceImpl jpCydServiceImpl;
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpBcdServiceImpl jpBcdServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	@Autowired
	private JpGqdServiceImpl jpGqdServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	@Override
	public void insertInitEntity(JpCyd t) {
		Shyhb yhb =SessionUtils.getShshbSession();
		t.setCjsj(new Date());
		t.setCjyh(yhb.getBh());
		t.setShbh(yhb.getShbh());//商户编号
		t.setZt("0");//新增状态为待审核
		if (null != t.getWdTpfl()) {
			t.setWdTpfl(Arith.div(t.getWdTpfl(), 100d));
		}
	}

	@Override
	public void updateInitEntity(JpCyd t) {
		Shyhb yhb = SessionUtils.getShshbSession();
		t.setShbh(yhb.getShbh());
		if (null != t.getWdTpfl()) {
			t.setWdTpfl(Arith.div(t.getWdTpfl(), 100d));
		}
	}
	
	@Override
	public void selectInitEntity(Map<String,Object> param){
		String rqlx = (String) param.get("search_EQ_rqlx");
		String kssj = (String) param.get("search_EQ_kssj");
		String jssj = (String) param.get("search_EQ_jssj");
		if("1".equals(rqlx)){
			param.put("search_GTEdate_cjsj", kssj);
			param.put("search_LTEdate_cjsj", jssj);
		}else if("2".equals(rqlx)){
			param.put("search_GTEdate_shsj", kssj);
			param.put("search_LTEdate_shsj", jssj);
		}
		param.remove("search_EQ_rqlx");
		param.remove("search_EQ_kssj");
		param.remove("search_EQ_jssj");
		Shyhb yhb = SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", yhb.getShbh());
	}
	
	@Override
	public void pageAfter(Page page) {
		Shyhb yhb = SessionUtils.getShshbSession();
		List<?> list = page.getList();
		attachService.veclass("wdpt").shyhb("cjyh", yhb.getShbh()).shyhb("shyh", yhb.getShbh()).shyhb("qyyh", yhb.getShbh()).execute(list);
	}
	/**
	 * 差异单新增或者修改
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping
	public String toedit(ModelMap model,@RequestParam(value = "id", defaultValue = "") String id,String ddlx,String ddbh,JpCyd jpcyds,String sh){
		try {
			JpCyd jpcyd = new JpCyd();
			List<Map<String,Object>> mapkhdd = new ArrayList<Map<String,Object>>();
			String cylx = "";
			if(StringUtils.isNotBlank(id)){
				Shyhb yhb = SessionUtils.getShshbSession();
				String shbh = yhb.getShbh();
				jpcyds.setShbh(shbh);
				if("1".equals(ddlx)){
					mapkhdd  = this.jpCydServiceImpl.searchJpKhddDdbh(jpcyds);//根据订单编号查询机票订单
				}else if("2".equals(ddlx)){
					mapkhdd  = this.jpCydServiceImpl.searchJpTpddTpdh(jpcyds);//根据退票单号查询退票订单
				}else if("3".equals(ddlx)){
					mapkhdd  = this.jpCydServiceImpl.searchJpTpddGqdh(jpcyds);//根据改签单号查询改签订单
				}
				jpcyd.setId(id);
				jpcyd = this.baseService.getEntityById(jpcyd);
				cylx = jpcyd.getCylx();
				model.addAttribute("updateType", "edit");
			}else{
				model.addAttribute("updateType", "add");
				ddlx = "1";//新增默认是正常订单
			}
			model.addAttribute("jpcyd",jpcyd);
			model.addAttribute("ddlx", ddlx);
			model.addAttribute("list",mapkhdd);
			model.addAttribute("cylx", cylx);
			model.addAttribute("sh",sh);//判断是否是审核
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewpath("edit");
	}
	
	/**
	 * 差异单正常订单,改签订单,退废订单查询
	 * @param jpPnr
	 * @param jpWbdh
	 * @return
	 */
	@RequestMapping
	public String searchJpdd(JpCyd jpcyd,String pnr_no,String wbdh,ModelMap model,String ddlx){
		try {
			String isnull = "0";
			Shyhb yhb = SessionUtils.getShshbSession();
			String shbh = yhb.getShbh();
			jpcyd.setShbh(shbh);
			if("1".equals(ddlx)){
				List<Map<String,Object>> mapkhdd = this.jpCydServiceImpl.searchJpKhdd(jpcyd);
				model.addAttribute("mapkhdd", mapkhdd);
				if(CollectionUtils.isNotEmpty(mapkhdd)){
					isnull = "1";
				}
			}else if("2".equals(ddlx)){
				List<Map<String,Object>> maptpdd = this.jpCydServiceImpl.searchJpTpdd(jpcyd);
				model.addAttribute("maptpdd", maptpdd);
				if(CollectionUtils.isNotEmpty(maptpdd)){
					isnull = "1";
				}
			}else{
				List<Map<String,Object>> mapgqdd = this.jpCydServiceImpl.searchJpGqdd(jpcyd);
				model.addAttribute("mapgqdd", mapgqdd);
				if(CollectionUtils.isNotEmpty(mapgqdd)){
					isnull = "1";
				}
			}
			model.addAttribute("isnull",isnull);//查询机票订单(正常,退废,改签)判断返回值是否为空
			model.addAttribute("updateType", "add");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewpath("edit");
	}
	
	/**
	 * 取消差异单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String qxCyd(@RequestParam(value = "id", defaultValue = "") String id){
		try {
			JpCyd cyd = new JpCyd();
			Shyhb yhb =SessionUtils.getShshbSession();
			cyd.setId(id);
			cyd.setShbh(yhb.getShbh());//商户编号必填
			cyd = this.baseService.getEntityById(cyd);//查询id对应的对象是否存在
			if(null != cyd){
				cyd.setZt("3");//取消差异单
				this.baseService.update(cyd);
				return "1";
			}else{
				return "0";
			}
		} catch (Exception e) {
			logger.error("取消差异单[id=" + id + "]失败", e);
			return "0";
		}
	}
	
	/**
	 * 修改差异单或审核差异单
	 * @param jpcyd
	 * @param id
	 * @param sh
	 * @return
	 */
	@RequestMapping
	public String updateCyd(JpCyd jpcyd,String id,String sh){
		try {
			this.updateInitEntity(jpcyd);
			Shyhb yhb = SessionUtils.getShshbSession();
			if(StringUtils.isNotBlank(id)){
				if("1".equals(sh)){
					jpcyd.setShsj(new Date());
					jpcyd.setShyh(yhb.getBh());
					jpcyd.setZt("1");
				}
				this.baseService.updateCyd(jpcyd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改差异单失败!", e);
		}
		return "/common/turning";
	}
	
	@ResponseBody
	@RequestMapping
	public String cydQrPay(@ModelAttribute("entity") JpCyd jpcyd){
		Shyhb yhb =SessionUtils.getShshbSession();
		JPBcd jpbcd = new JPBcd();//补差单bean
		try {
			JpKhdd jpkhdd = new JpKhdd();
			JpTpd jptpd = new JpTpd();
			JpGqd jpgqd = new JpGqd();
			List<JpTpdMx> mxlist = new ArrayList<JpTpdMx>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if("1".equals(jpcyd.getDdlx())){//正常单
				jpkhdd.setShbh(yhb.getShbh());
				jpkhdd.setDdbh(jpcyd.getDdbh());
				jpkhdd = this.jpKhddServiceImpl.getKhddByDdbh(jpkhdd);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shbh", yhb.getShbh());
				map.put("ddbh", jpcyd.getDdbh());
				list = this.jpJpServiceImpl.getjpjpByddbh(map);
				if(CollectionUtils.isNotEmpty(list)){
					Map<String, Object> jp = list.get(0);
					jpbcd.setCpDatetime((Timestamp)jp.get("CP_DATETIME"));
				}
				jpbcd.setHc(jpkhdd.getHc());
				jpbcd.setHkgs(jpkhdd.getHkgs());
				jpbcd.setFaid(jpkhdd.getFaid());
				jpbcd.setDdbh(jpkhdd.getDdbh());
				jpbcd.setWdZclx(jpkhdd.getWdZclx());
				
			}else if("2".equals(jpcyd.getDdlx())){//退票单
				jptpd = this.jpTpdServiceImpl.getJpTpdByTpdh(jpcyd.getDdbh(), yhb.getShbh());
				mxlist = this.jpTpdMxServiceImpl.getJpTpdMxByTpdh(jpcyd.getDdbh(), yhb.getShbh());
				if(CollectionUtils.isNotEmpty(mxlist)){
					JpTpdMx mx = mxlist.get(0);
					jpbcd.setHkgs(mx.getHkgs());
				}
				jpbcd.setHc(jptpd.getHc());
				jpbcd.setFaid(jptpd.getFaid());
				jpbcd.setDdbh(jptpd.getDdbh());
				jpbcd.setWdZclx(jptpd.getWdZclx());
			}else if("3".equals(jpcyd.getDdlx())){//改签单
				jpgqd = this.jpGqdServiceImpl.getJpGqdByGqdh(jpcyd.getDdbh(), yhb.getShbh());
				jpbcd.setHc(jpgqd.getHc());
				jpbcd.setHkgs(jpgqd.getHkgs());
				jpbcd.setFaid(jpgqd.getFaid());
				jpbcd.setDdbh(jpgqd.getDdbh());
				jpbcd.setWdZclx(jpgqd.getWdZclx());
			}
			//jpbcd.setId(VeDate.getNo(6));
			jpbcd.setBczt("0");//已申请
			jpbcd.setWdpt(jpcyd.getWdpt());
			jpbcd.setPnrNo(jpcyd.getPnrNo());
			jpbcd.setWbdh(jpcyd.getWbdh());
			jpbcd.setBclx("0130111");
			jpbcd.setBcje(NumberUtils.toDouble(jpcyd.getCyjeString()));
			jpbcd.setBcsm(jpcyd.getCysm());
			jpbcd.setSkzt("0");
			jpbcd.setShbh(yhb.getShbh());
			jpbcd.setCjyh(yhb.getBh());
			jpbcd.setCjsj(new Date());
			jpbcd.setWdid(jpcyd.getWdid());
			jpbcd.setYwlx("04");//差异单
			jpbcd.setYwdh(jpcyd.getId());
			this.jpBcdServiceImpl.creatBcd(jpbcd, jpcyd, yhb.getBh());
//			this.jpBcdServiceImpl.insert(jpbcd);//生成补差单记录
//			jpcyd.setBcdh(jpbcd.getId());//增加补差单号
//			jpcyd.setZt("2");//修改状态,改成确认支付
//			jpcyd.setQysj(new Date());
//			jpcyd.setQyyh(yhb.getBh());
//			this.baseService.update(jpcyd);//更新
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("差异单确认失败", e);
			return "0";
		}
	}
}
