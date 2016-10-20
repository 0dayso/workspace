package cn.vetech.web.vedsb.jzcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.pid.api.autoetdz.EtdzParam;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.pidbean.AvCabinModel;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.mapper.JsonMapper;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.ToPinYin;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.cpsz.JpCpymSz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.entity.office.JpOffice;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpCpymSzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.AutoEtdz;
import cn.vetech.vedsb.jp.service.office.JpOfficeServiceImpl;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class CpMainController extends AbstractBaseControl{
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	@Autowired
	private JpCpymSzServiceImpl jpCpymSzServiceImpl;
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private JpOfficeServiceImpl jpOfficeServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;

	/**
	 * <集中出票页面初始化方法>
	 * 
	 * @param model
	 * @param ddbh
	 * @throws Exception 
	 */
	@RequestMapping
	public String main(Model model,String ddbh) throws Exception{
		Shyhb yhb=SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		JpKhdd t=new JpKhdd();
		t.setShbh(shbh);
		t.setDdbh(ddbh);
		JpKhdd jpKhdd=jpKhddServiceImpl.getEntityById(t);
		if(jpKhdd==null){
			throw new Exception("订单不存在！");
		}
		//已订座状态改为出票中
		if (DictEnum.JPDDZT.YDZ.getValue().equals(jpKhdd.getDdzt())) {
			jpKhdd.setDdzt(DictEnum.JPDDZT.CPZ.getValue());// 出票中
			jpKhddServiceImpl.update(jpKhdd);
		}
		JpKhddCjr cjrT=new JpKhddCjr();
		cjrT.setShbh(shbh);
		cjrT.setDdbh(ddbh);
		List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getMyBatisDao().select(cjrT);
		if(CollectionUtils.isEmpty(cjrList)){
			throw new Exception("乘机人不存在！");
		}
		List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
		if(CollectionUtils.isEmpty(hdList)){
			throw new Exception("航段不存在！");
		}
		JpCpymSz cpszTj=new JpCpymSz();
		cpszTj.setShbh(shbh);
		List<JpCpymSz> cpszList=jpCpymSzServiceImpl.getMyBatisDao().select(cpszTj);
		if(CollectionUtils.isNotEmpty(cpszList)){
			for (JpCpymSz sz : cpszList) {
				model.addAttribute("cpsz"+sz.getCgly(), sz);
			}
		//	throw new Exception("请在出票页面设置完成出票设置！");
		}
		
		genWd(jpKhdd);//获取网店名称
		genHclxmc(jpKhdd);//获取航程类型名称
		//获取机票配置
		List<JpOffice> jpofficelist = jpOfficeServiceImpl.selectJpOfficeByShbh(shbh);
		Map<String,Object> officelist=new HashMap<String, Object>();
		if(CollectionUtils.isNotEmpty(jpofficelist)){
			for(JpOffice jpoffice:jpofficelist){
				officelist.put(jpoffice.getOfficeid(), jpoffice);
			}
		}
		
		//rtPnr
		try{
			rtPnr(jpKhdd,cjrList.get(0).getCjrlx(),yhb);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//获取平台账号
		List<JpPtzcZh> ptzhList=genPtzh(shbh);
		model.addAttribute("hcInfo", genHcXx(hdList));//航程信息
		//1姓名重复，2姓名拼音重复
		model.addAttribute("cjrrepeat",checkPassengerName(cjrList));
		model.addAttribute("jpKhdd",jpKhdd);
		model.addAttribute("hdList", hdList);
		model.addAttribute("cjrList",cjrList);
		model.addAttribute("ptzhList", ptzhList);
		model.addAttribute("yhbh", yhb.getBh());
		model.addAttribute("officelist", officelist);
		return viewpath("main");
	}
	/**
	 * <功能详细描述>
	 * 下单前公共检查
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping
	@ResponseBody
	public String checkBeforeOrder(String ddbh,String ptzcbs,String platzcId){
		Map<String, String> result=new HashMap<String, String>();
		result.put("status", "0");
		Shyhb yhb=SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		JpKhdd t=new JpKhdd();
		t.setDdbh(ddbh);
		t.setShbh(shbh);
		JpKhdd jpKhdd=jpKhddServiceImpl.getEntityById(t);
		if("AIRB2B,AIRB2C".indexOf(ptzcbs)>-1){//B2B,B2C验证
			
		}else{//采购平台验证
			JpPtzcZh ptzcZh=jpPtzcZhServiceImpl.getPtzhByPtBs(ptzcbs, shbh);
			if(ptzcZh==null || !"1".equals(ptzcZh.getOpen1())){
				result.put("status", "-1");//错误级别
				result.put("error", "该平台未开启！");
				return JsonMapper.nonEmptyMapper().toJson(result);
			}
			if("1".equals(ptzcZh.getSfkqdk())){
				result.put("autopay", "1");
			}else{
				result.put("autopay", "0");
			}
		}
		//控制已支付的订单是否能够继续下单  参数1控制是否开启配置  参数2控制哪些用户编号能够继续下单
		Shcsb csBean=shcsbServiceImpl.findCs("7761", yhb.getShbh());
		String sfsq="0";//表示不允许继续下单
		if(csBean!=null&&"1".equals(csBean.getCsz1())){
			String cs7761_2 = StringUtils.trimToEmpty(csBean.getCsz2());
			if(cs7761_2.indexOf(yhb.getBh())>-1){
				sfsq = "1";
			}
		}
		if(jpKhdd==null){
			result.put("status", "-1");//错误级别
			result.put("error", "订单不存在！");
			return JsonMapper.nonEmptyMapper().toJson(result);
		}
		JpCgdd jpcgdd=jpCgddServiceImpl.genDdByZfzt(ddbh, "1", shbh);
		if(jpcgdd != null){
			if("0".equals(sfsq)){
				result.put("status", "-1");//错误级别
				result.put("error", "订单已经有支付成功的记录。请不要重复支付！");
			}else{
				result.put("status", "-2");
				result.put("error", "订单已经有支付成功的记录。点击【取消】避免重复采购！");
			}
			return JsonMapper.nonEmptyMapper().toJson(result);
		}
		jpcgdd=jpCgddServiceImpl.genDdByZfzt(ddbh, "0", shbh);
		if(jpcgdd !=null){
			result.put("status", "1");//警告级别，需要确认操作
			result.put("error", "订单已下过单");
			return JsonMapper.nonEmptyMapper().toJson(result);
		}
		return JsonMapper.nonEmptyMapper().toJson(result);
	}
	/**
	 * <清除支付状态>
	 */
	@RequestMapping
	@ResponseBody
	public String clearzfbj(String cgddid){
		Map<String, String> result=new HashMap<String, String>();
		result.put("status", "0");
		try {
			Shyhb yhb=SessionUtils.getShshbSession();
			if(StringUtils.isBlank(cgddid)){
				throw new Exception("采购订单ID不能为空!");
			}
			JpCgdd cgdd=new JpCgdd();
			cgdd.setId(cgddid);
			cgdd.setShbh(yhb.getShbh());
			cgdd.setJyzt("4");
			cgdd.setBzbz(VeDate.getStringDate()+"用户"+yhb.getBh()+"清除采购支付");
			jpCgddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(cgdd);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "-1");
			result.put("error", "清除失败："+e.getMessage());
		}
		return JsonMapper.nonEmptyMapper().toJson(result);
	}
	/**
	 * <功能详细描述>
	 * PAT
	 * @return [参数说明]
	 */
	@RequestMapping
	@ResponseBody
	public String pat(String ddbh,String sfxyh){
		try {
			Shyhb yhb=SessionUtils.getShshbSession();
			String shbh=yhb.getShbh();
			JpKhdd t=new JpKhdd();
			t.setDdbh(ddbh);
			t.setShbh(shbh);
			JpKhdd jpKhdd=jpKhddServiceImpl.getEntityById(t);
			String pnrno=jpKhdd.getCgPnrNo();
			if(StringUtils.isBlank(pnrno)){
				throw new Exception("订单PNR为空！");
			}
			if(pnrno.startsWith("O")){
				throw new Exception("订单PNR为假编码！");
			}
			JpPz jppz = jpPzServiceImpl.getJpPzByShbh(shbh);
			if(jppz==null){
				throw new Exception("没获取到商户"+shbh+"的pid配置");
			}
			List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
			Pnr pnrObj=DataUtils.getPnrPat(pnrno, cjrList.get(0).getCjrlx(), jppz, yhb,sfxyh);
			if(pnrObj==null || !"1".equals(pnrObj.getPnr_lx())){
				throw new Exception("获取PAT内容失败！");
			}
			String pnrnr=pnrObj.getPnr_lr();
			String patnr=pnrObj.getPat();
			String dbm=pnrObj.getBigPnrno();
			JpKhdd updd=new JpKhdd();
			updd.setDdbh(ddbh);
			updd.setPnrLr(pnrnr);
			updd.setCgHkgsPnr(dbm);
			updd.setPatLr(patnr);
			updd.setShbh(shbh);
			jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(updd);
			return patnr;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	 * <舱位验证>
	 * 
	 * @param ddbh
	 * @return [参数说明]
	 */
	@RequestMapping(value="valiHbhCw_{ddbh}")
	@ResponseBody
	public String valiHbhCw(@PathVariable("ddbh") String ddbh){
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("status", "0");
		try{
			Shyhb yhb=SessionUtils.getShshbSession();
			String shbh=yhb.getShbh();
			JpKhdd t=new JpKhdd();
			t.setDdbh(ddbh);
			t.setShbh(shbh);
			JpKhdd jpKhdd=jpKhddServiceImpl.getEntityById(t);
			if(jpKhdd==null){
				throw new Exception("订单不存在");
			}
			List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
			if(CollectionUtils.isEmpty(hdList)){
				throw new Exception("航段不存在");
			}
			int count=0;
			int minSeatNum=0;
			for (JpKhddHd hd : hdList) {
				if("ARNK".equals(hd.getCgHbh())){
					continue;
				}
				List<AvCabinModel> cabinList=jpKhddServiceImpl.getCabinList(shbh, jpKhdd, hd);
				AvCabinModel currentCabin=cabinList.remove(cabinList.size()-1);//最后一个放的是当前舱位
				if(!hd.getCgCw().equals(currentCabin.getCw())){
					throw new Exception("AVH未获取到当前舱位信息");
				}
				String otherSeat="";
				for(AvCabinModel cabin : cabinList){
					otherSeat+=cabin.getCw()+cabin.getCwNum()+" ";
				}
				if(CollectionUtils.isEmpty(cabinList)){
					throw new Exception("AVH获取舱位信息失败");
				}
				if(count == 0){
					result.put("otherCabin", otherSeat);
					result.put("currentCw", currentCabin.getCw());
					minSeatNum="A".equals(currentCabin.getCwNum()) ? 10 : NumberUtils.toInt(currentCabin.getCwNum(),0);
				}else {
					result.put("otherCabinSec", otherSeat);
					int secNum="A".equals(currentCabin.getCwNum()) ? 10 : NumberUtils.toInt(currentCabin.getCwNum(),0);
					if(minSeatNum>secNum){
						minSeatNum=secNum;
					}
				}
				result.put("seatNum", minSeatNum);
				if(minSeatNum < jpKhdd.getCjrs()){
					result.put("hasSeat",false);
				}else {
					result.put("hasSeat", true);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.put("status", "-1");
		}
		return JsonMapper.nonEmptyMapper().toJson(result);
	}
	/**
	 * <检查乘机人姓名>
	 * 1姓名重复，2姓名拼音重复
	 * @param jpKhdd [参数说明]
	 */
	private String checkPassengerName(List<JpKhddCjr> cjrList){
		if(cjrList.size()==1){
			return null;
		}
		Map<String,String> temp1=new HashMap<String, String>();
		Map<String,String> temp2=new HashMap<String, String>();
		for(JpKhddCjr cjr : cjrList){
			String name=cjr.getCjr();
			if(StringUtils.isEmpty(temp1.get(name))){
				temp1.put(name, "1");
			}else{
				return "1";
			}
			String py=ToPinYin.getPingYinString(name);
			if(StringUtils.isEmpty(temp2.get(py))){
				temp2.put(py, "1");
			}else {
				return "2";
			}
		}
		return null;
	}
	/**
	 * <获取航段信息>
	 * @param jpKhdd [参数说明]
	 */
	private String genHcXx(List<JpKhddHd> hdList){
		StringBuffer hdinfotip = new StringBuffer();
		for (JpKhddHd hd : hdList) {
			String cw=hd.getCgCw();
			if(StringUtils.isNotBlank(hd.getCgZcw())){
				cw=hd.getCgZcw();
			}
			hdinfotip.append(hd.getCfcity() + "→" + hd.getDdcity() + "：" + hd.getCgHbh() + "/"
					+ cw + "/" + hd.getCfsj() + "\n");
		}
		return hdinfotip.toString();
	}
	/**
	 * <获取网店信息>
	 * @param jpKhdd [参数说明]
	 */
	private void genWd(JpKhdd jpKhdd){
		Wdzlsz wdtj=new Wdzlsz();
		wdtj.setId(jpKhdd.getWdid());
		wdtj.setShbh(jpKhdd.getShbh());
		Wdzlsz wd=wdzlszServiceImpl.getEntityById(wdtj);
		if(wd != null){
			jpKhdd.getEx().put("WD", wd);
		}
	}
	/**
	 * <航程类型名称转换>
	 * @param jpKhdd [参数说明]
	 */
	private void genHclxmc(JpKhdd jpKhdd){
		jpKhdd.getEx().put("HCLXMC", "单程");
		if("2".equals(jpKhdd.getHclx())){
			jpKhdd.getEx().put("HCLXMC", "往返");
		}else if("3".equals(jpKhdd.getHclx())){
			jpKhdd.getEx().put("HCLXMC", "联程");
		}else if("4".equals(jpKhdd.getHclx())){
			jpKhdd.getEx().put("HCLXMC", "缺口");
		}
	}
	/**
	 * <获取平台账号>
	 * @param shbh [参数说明]
	 */
	private List<JpPtzcZh> genPtzh(String shbh){
		JpPtzcZh t=new JpPtzcZh();
		t.setShbh(shbh);
		List<JpPtzcZh> list=jpPtzcZhServiceImpl.getMyBatisDao().select(t);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		List<VeclassCache> veClassList=FunctionCode.getVeclassLb("100021");
		Map<String, JpPtzcZh> map=new HashMap<String, JpPtzcZh>();
		for(JpPtzcZh bean : list){
			map.put(bean.getPtzcbs(), bean);
		}
		List<JpPtzcZh> result=new ArrayList<JpPtzcZh>();
		for (VeclassCache veclassCache : veClassList) {
			if("none".equals(veclassCache.getParid())){
				continue;
			}
			JpPtzcZh zh=map.get(veclassCache.getYwmc());
			if(zh !=null){
				zh.getEx().put("PLATNAME", veclassCache.getMc());
				result.add(zh);
			}
		}
		if(CollectionUtils.isEmpty(result)){
			return null;
		}
		return result;
	}
	private void rtPnr(JpKhdd jpKhdd,String cjrlx,Shyhb yhb) throws Exception{
		String pnrno=StringUtils.trimToEmpty(jpKhdd.getCgPnrNo());
		if(pnrno.startsWith("O")){
			throw new Exception("pnrno为假编码");
		}
		String ddbh=jpKhdd.getDdbh();
		String shbh=yhb.getShbh();
		JpPz jppz = jpPzServiceImpl.getJpPzByShbh(shbh);
		if(jppz==null){
			throw new Exception("没获取到商户"+shbh+"的pid配置");
		}
		Pnr pnrObj=DataUtils.getPnrPat(jpKhdd.getCgPnrNo(), cjrlx,jppz ,yhb,"");
		if(pnrObj==null){
			return;
		}
		JpKhdd updd=new JpKhdd();
		updd.setDdbh(ddbh);
		updd.setPnrLr(pnrObj.getPnr_lr());
		updd.setPatLr(pnrObj.getPat());
		updd.setCgHkgsPnr(pnrObj.getBigPnrno());
		updd.setShbh(shbh);
		jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(updd);
	}
	
	@RequestMapping(value = "autoEtdz",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> autoEtdz(@RequestParam String ddbh,@RequestParam String cplx,@RequestParam String officeid){
		Shyhb yhb=SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		Map<String,Object> info=new HashMap<String, Object>();
		int zt =-1;
		AutoEtdz autoEtdz = SpringContextUtil.getBean(AutoEtdz.class);
		try {
		    JpKhdd	jpKhdd=new JpKhdd();
		    jpKhdd.setShbh(shbh);
		    jpKhdd.setDdbh(ddbh);
		    jpKhdd=jpKhddServiceImpl.getKhddByDdbh(jpKhdd);
			String selectlowprice="";
			if(!"1".equals(selectlowprice)){
				selectlowprice="";
			}
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			EtdzParam ep=new EtdzParam();
			ep.setIp(jpPz.getPzIp());
			ep.setPort(jpPz.getPzPort());
			ep.setUrl("http://"+jpPz.getPzIp()+":"+jpPz.getPzPort());
			ep.setUserid(yhb.getPidyh());
			ep.setPass(yhb.getMm());
			ep.setShbh(jpKhdd.getShbh());
			ep.setBmbh(yhb.getShbmid());
			ep.setDdbh(ddbh);
			ep.setDylx("VEDS");
			ep.setBbh("0");
			ep.setPatvalue("");
			ep.setCpqdlx(cplx);
			ep.setOffice(officeid);
			ep.setSelectlowprice(selectlowprice);
			ep.setYhbh(yhb.getBh());
			JpZdcpJk jk = new JpZdcpJk();
			Map<String,Object> m=autoEtdz.auto_etdz(ep,jk);
		    zt = (Integer) m.get("zt");
			String errorst=VeStr.getValue(m, "error");
			info.put("error", errorst);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("error", cplx+"出票失败："+e.getMessage()+"<br>");
		}
		info.put("error_corder", zt);
		return info;
	}
}
