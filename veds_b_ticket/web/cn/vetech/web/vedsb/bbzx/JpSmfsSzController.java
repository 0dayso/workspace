package cn.vetech.web.vedsb.bbzx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.service.SpringPropertyResourceReader;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpSmSjSz;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpSmfsSz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpjpgl.JpSmSjSzServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpSmfsSzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpSmfsSzController extends MBaseControl<JpSmfsSz, JpSmfsSzServiceImpl>{
	@Autowired
	private AttachService attachService;
	@Autowired
	private WdzlszServiceImpl wdzlszSImpl;
	@Autowired
	private JpSmSjSzServiceImpl jpSmSjSzServiceImpl;
	@Autowired
	private SpringPropertyResourceReader propertyResourceReader;

	@Override
	public void updateInitEntity(JpSmfsSz t) {
		
	}
	@Override
	public void insertInitEntity(JpSmfsSz t) {
		
	}

	/**
	 * 获取所有采购来源 、网店名称、扫描方式
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "viewlist")
	public String view(ModelMap model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
		param.put("shbh", user.getShbh());
		List<VeclassCache> veList=FunctionCode.getVeclassLb("10014");
		List<String> veIds=new ArrayList<String>();
		for(VeclassCache ve:veList){
			if(!"10014".equals(ve.getId())){
				veIds.add(ve.getId());
			}
		}
		param.put("cglyLen", veIds.size());
		param.put("cglyIds", StringUtils.join(veIds,","));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("zt", "1");
		map.put("shbh", user.getShbh());
		map.put("ywlxs", new String[]{"1001901","1001902"});
		List<Wdzlsz> wdzlszList = wdzlszSImpl.getWdZlszListByYwlx(map);
		List<String> wdIds=new ArrayList<String>();
		for(Wdzlsz w:wdzlszList){
			wdIds.add(w.getId());
		}
		param.put("wdIdlen", wdzlszList.size());
		param.put("wdIds", StringUtils.join(wdIds,","));
		try {
			String driver = StringUtils.trimToEmpty(propertyResourceReader.getProperty("jp.jdbc.driver"));
			if (!"oracle.jdbc.driver.OracleDriver".equals(driver)) {
				param.put("prior", "1");
			}
			//获取扫描方式设置
			List<Map<String,Object>> list=this.baseService.queryJpSmfsszListByCgly(param);
			attachService.veclass("CGLY").wdzl("WDID").execute(list);
			model.addAttribute("list",list);
			model.addAttribute("len", wdzlszList.size());
			//获取扫描时间设置
			JpSmSjSz jpsmsjsz=jpSmSjSzServiceImpl.queryByShbh(user.getShbh());
			if(jpsmsjsz !=null){
				model.addAttribute("jpsmsjsz", jpsmsjsz);
			}
		} catch (Exception e) {
			logger.error("查询JpSmfsSz出错:"+e.getMessage());
		}
		return viewpath("list");
	}
	
	/**
	 * 保存设置
	 * @param model
	 * @param request
	 * @param cgly 采购来源
	 * @param wdid 网店ID
	 * @param detr_lx DETR方式
	 * @param all_pt 0所有采购来源统一设置而  1非统一设置
	 * @param detr_type 所有采购来源统一设置时的DETR方式
	 * @return
	 */
	@RequestMapping(value = "saveSmfs")
	@ResponseBody
	public String saveSmfs(ModelMap model,HttpServletRequest request,String[] cgly,String[] wdid,String[] detr_lx,
			String all_pt,String detr_type,String sfkq,String tqts,String pidyhbh,String pidmm){
		Shyhb user = SessionUtils.getShshbSession();
		try {
			//删除jpSmSjSz表中原时间设置
			jpSmSjSzServiceImpl.deleteByShbh(user.getShbh());
			//插入新的时间设置jpSmSjSz表中原时间设置
			jpSmSjSzServiceImpl.insertSmSjSz(user.getShbh(), -NumberUtils.toInt(tqts), sfkq,pidyhbh,pidmm);
			//删除jpSmfsSz表中原扫描方式设置
			this.baseService.deleteBySmlx("0", user.getShbh());
			if ("0".equals(all_pt)) {
				JpSmfsSz jpsmfs = new JpSmfsSz();
				jpsmfs.setId(VeDate.getNo(6));
				jpsmfs.setSmlx("0");
				jpsmfs.setDetrLx(detr_type);
				jpsmfs.setCgly("---");
				jpsmfs.setShbh(user.getShbh());
				jpsmfs.setYhbh(user.getBh());
				jpsmfs.setCzsj(new Date());
				jpsmfs.setWdid("---");
				//插入新的扫描方式设置
				this.baseService.insert(jpsmfs);
			} else {
				for (int i = 0; i < wdid.length; i++) {
					JpSmfsSz jpsmfs = new JpSmfsSz();
					jpsmfs.setId(VeDate.getNo(6));
					jpsmfs.setSmlx("0");
					jpsmfs.setDetrLx(detr_lx[i]);
					jpsmfs.setCgly(cgly[i]);
					jpsmfs.setShbh(user.getShbh());
					jpsmfs.setYhbh(user.getBh());
					jpsmfs.setCzsj(new Date());
					jpsmfs.setWdid(wdid[i]);
					//插入新的扫描方式设置
					this.baseService.insert(jpsmfs);
				}
			}
			return "设置成功";
		} catch (Exception e) {
			logger.error("设置扫描方式出错"+e.getMessage());
		}
		return "设置失败";
	}
}
