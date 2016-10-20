package cn.vetech.web.vedsb.colorremind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 订单颜色提醒
 * @author vetech
 *
 */
@Controller
public class ColorRemindController extends MBaseControl<Shcsb,ShcsbServiceImpl> {
	/**
	 * 查询颜色提醒信息
	 */
	@RequestMapping(value = "query",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> query(@RequestParam(value="bh") String bh,Model model){
		Shyhb user = SessionUtils.getShshbSession();
		Map<String,Object> reparam=new HashMap<String,Object>();
		try {
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("shbh", user.getShbh());
			param.put("bh", bh);
			List<Shcsb> list=this.baseService.getListByShbhAndBhs(param);
			reparam.put("CODE", "0");
			reparam.put("DATA", list);
		} catch (Exception e) {
			logger.error("在基础数据表中查询提醒颜色失败");
			reparam.put("CODE", "-1");
			reparam.put("MSG", "获取提醒颜色失败");
		}
		return reparam;
	}
	/**
	 * 更新机票订单颜色提醒
	 */
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(@RequestParam(value="bh") String bh,
			@RequestParam(value="cfrq") String[] cfrq,
			@RequestParam(value="ydsj") String[] ydsj,
			@RequestParam(value="nosj") String[] nosj,
			@RequestParam(value="pzlx") String[] pzlx,
			Model model){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		Map<String,Object> reparam=new HashMap<String,Object>();
		try {
			Map<String,Object> cxparam=new HashMap<String,Object>();
			cxparam.put("bh", bh);
			cxparam.put("shbh", user.getShbh());
			List<Shcsb> list=this.baseService.getListByShbhAndBhs(cxparam);
			//判断该设置是否存在，不存在则插入，存在则更新
			if(CollectionUtils.isEmpty(list)){
				Shcsb sc1=new Shcsb();
				sc1.setId(VeDate.getNo(6));
				sc1.setBh(bh);
				sc1.setShbh(shbh);
				sc1.setCsz1("cfrq");
				sc1.setCsz2(ArrayToString(cfrq));
				sc1.setCj_userid(user.getBh());
				sc1.setCslxbh("1001203");
				sc1.setCslxmc("机票订单类型");
				sc1.setShbh(shbh);
				sc1.setCjsj(VeDate.getNow());
				sc1.setCsms("机票订单出发日期颜色提醒");
				sc1.setSfxs("1");
				this.baseService.insertMrpx(sc1);
				
				Shcsb sc2=new Shcsb();
				sc2.setId(VeDate.getNo(6));
				sc2.setBh(bh);
				sc2.setShbh(shbh);
				sc2.setCsz1("ydsj");
				sc2.setCsz2(ArrayToString(ydsj));
				sc2.setCj_userid(user.getBh());
				sc2.setCslxbh("1001203");
				sc2.setCslxmc("机票订单类型");
				sc2.setShbh(shbh);
				sc2.setCjsj(VeDate.getNow());
				sc2.setCsms("机票订单预定日期颜色提醒");
				sc2.setSfxs("1");
				this.baseService.insertMrpx(sc2);
				
				Shcsb sc3=new Shcsb();
				sc3.setId(VeDate.getNo(6));
				sc3.setBh(bh);
				sc3.setShbh(shbh);
				sc3.setCsz1("nosj");
				sc3.setCsz2(ArrayToString(nosj));
				sc3.setCj_userid(user.getBh());
				sc3.setCslxbh("1001203");
				sc3.setCslxmc("机票订单类型");
				sc3.setShbh(shbh);
				sc3.setCjsj(VeDate.getNow());
				sc3.setCsms("机票订单NO时间颜色提醒");
				sc3.setSfxs("1");
				this.baseService.insertMrpx(sc3);
				
				Shcsb sc4=new Shcsb();
				sc4.setId(VeDate.getNo(6));
				sc4.setBh(bh);
				sc4.setShbh(shbh);
				sc4.setCsz1("pzlx");
				sc4.setCsz2(ArrayToString(pzlx));
				sc4.setCj_userid(user.getBh());
				sc4.setCslxbh("1001203");
				sc4.setCslxmc("机票订单类型");
				sc4.setShbh(shbh);
				sc4.setCjsj(VeDate.getNow());
				sc4.setCsms("机票订单票证颜色提醒");
				sc4.setSfxs("1");
				this.baseService.insertMrpx(sc4);
			}else{
				List<Shcsb> param=new ArrayList<Shcsb>();
				
				Shcsb sc1=new Shcsb();
				sc1.setBh(bh);
				sc1.setShbh(shbh);
				sc1.setCsz1("cfrq");
				sc1.setCsz2(ArrayToString(cfrq));
				sc1.setXg_userid(user.getBh());
				sc1.setXgsj(VeDate.getNow());
				param.add(sc1);
				
				Shcsb sc2=new Shcsb();
				sc2.setBh(bh);
				sc2.setShbh(shbh);
				sc2.setCsz1("ydsj");
				sc2.setCsz2(ArrayToString(ydsj));
				sc2.setXg_userid(user.getBh());
				sc2.setXgsj(VeDate.getNow());
				param.add(sc2);
				
				Shcsb sc3=new Shcsb();
				sc3.setBh(bh);
				sc3.setShbh(shbh);
				sc3.setCsz1("nosj");
				sc3.setCsz2(ArrayToString(nosj));
				sc3.setXg_userid(user.getBh());
				sc3.setXgsj(VeDate.getNow());
				param.add(sc3);
				
				Shcsb sc4=new Shcsb();
				sc4.setBh(bh);
				sc4.setShbh(shbh);
				sc4.setCsz1("pzlx");
				sc4.setCsz2(ArrayToString(pzlx));
				sc4.setXg_userid(user.getBh());
				sc4.setXgsj(VeDate.getNow());
				param.add(sc4);
				
				this.baseService.updateColorRemind(param);
			}
			
			reparam.put("CODE", "0");
		} catch (Exception e) {
			logger.error("更新提醒颜色失败");
			reparam.put("CODE", "-1");
			reparam.put("MSG", "保存提醒颜色失败");
		}
		return reparam;
	}
	/**将String类型的数组转换成中间加|的字符串*/
	private String ArrayToString(String[] arr){
		StringBuilder ab=new StringBuilder();
		for (String s:arr) {
			ab.append(s+"|");
		}
		String str=ab.toString();
		return str.substring(0, str.length()-1);
	}
	
	@RequestMapping(value="saveMrpx",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object>saveMrpx(@RequestParam(value="bh") String bh,
			@RequestParam(value="csz1") String csz1,
			@RequestParam(value="csz2") String csz2){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		Map<String,Object> reparam=new HashMap<String,Object>();
		try {
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("bh", bh);
			param.put("shbh", user.getShbh());
			List<Shcsb> list=this.baseService.getListByShbhAndBhs(param);
			//默认排序方式，不存在则添加一条，存在则更新
			if(CollectionUtils.isEmpty(list)){
				Shcsb cs=new Shcsb();
				cs.setId(VeDate.getNo(6));
				cs.setBh(bh);
				cs.setCj_userid(user.getBh());
				cs.setCsz1(csz1);
				cs.setCsz2(csz2);
				cs.setCslxbh("1001203");
				cs.setCslxmc("机票订单类型");
				cs.setShbh(shbh);
				cs.setCjsj(VeDate.getNow());
				cs.setCsms("机票订单默认排序设置");
				cs.setSfxs("1");
				this.baseService.insertMrpx(cs);
			}else{
				Map<String,Object> updateParam=new HashMap<String,Object>();
				updateParam.put("bh", bh);
				updateParam.put("shbh", shbh);
				updateParam.put("xg_userid", user.getBh());
				updateParam.put("xgsj", VeDate.getNow());
				updateParam.put("csz1", csz1);
				updateParam.put("csz2", csz2);
				this.baseService.updateMrpx(updateParam);
			}
			reparam.put("CODE", "0");
			reparam.put("MSG", "保存成功");
		} catch (Exception e) {
			logger.error("修改机票订单默认排序错误");
			reparam.put("CODE", "-1");
			reparam.put("MSG", "保存失败");
		}
		return reparam;
	}
	@Override
	public void updateInitEntity(Shcsb t) {
	}

	@Override
	public void insertInitEntity(Shcsb t) {
	}

}
