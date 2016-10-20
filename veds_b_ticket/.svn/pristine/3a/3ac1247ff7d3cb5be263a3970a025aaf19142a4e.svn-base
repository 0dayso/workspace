package cn.vetech.web.vedsb.jpgqgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdData;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdDataServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpGqdApplyController extends MBaseControl<JpGqd, JpGqdServiceImpl> {
	
	@Autowired
	private JpGqdDataServiceImpl jpGqdDataServiceImpl;
	
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	
	@Override
	public void updateInitEntity(JpGqd t) {
	}

	@Override
	public void insertInitEntity(JpGqd t) {
	}
	
	/**
	 * 改签申请页面查询机票信息
	 * @param jpJp
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findJp")
	public String findJp(@ModelAttribute()JpJp jpJp, ModelMap model, HttpServletRequest request)  throws Exception{
 		Shyhb user = SessionUtils.getShshbSession();
		jpJp.setShbh(user.getShbh());
		JpGqdData gqdData = null;
		try {
			gqdData = jpGqdDataServiceImpl.getJpGqdDataForApply(jpJp);
			if (gqdData != null) {
				if (!gqdData.isMoreOrder()) {
					String gngj = jpJp.getGngj();
					if (StringUtils.isBlank(gngj)) {
						gngj = "1"; //国内
					}
					/**
					 * 支付科目
					 */
					Shzfkm zfkm = new Shzfkm();
					zfkm.setShbh(user.getShbh());
					zfkm.setSfskkm("1");
					zfkm.setSfqy("1");
					if ("1".equals(gngj)) {
						zfkm.setSycp("1001901");//国内机票产品
					} else if ("0".equals(gngj)) {
						zfkm.setSycp("1001902");//国内机票产品
					}
					List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);
					model.addAttribute("zfkmList", zfkmList);
					model.addAttribute("gqdData", gqdData);
					return viewpath("apply");
				}
				model.addAttribute("ddList", gqdData.getJpKhddList());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取改签申请数据发生错误：", e);
			model.addAttribute("error", "没有匹配到可申请改签的机票信息");
		}
		return viewpath("applylist");
		
	}
	
	/**
	 * 保存改签申请
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveGqdApply", method = RequestMethod.POST)
	public Map<String, Object> saveGqdApply(@ModelAttribute()JpGqd jpGqd,String[] gqXsfyArr, 
			String[]  khddCjrIdArr,
			String[]  jpHdIdArr,
			String[]  nCfsjArr,
			String[]  nDdsjArr,
			String[]  nXsHbhArr,
			String[]  nXsCwArr, HttpServletRequest request, ModelMap model) throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String result = jpGqdDataServiceImpl.saveJpGqdDataForApply(jpGqd,
					gqXsfyArr, khddCjrIdArr, jpHdIdArr, nCfsjArr, nDdsjArr,
					nXsHbhArr, nXsCwArr, user, logger);
			if ("OK".equals(result)) {
				resMap.put("CODE", "0");
			} else {
				resMap.put("CODE", "-1");
				resMap.put("MSG", result);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("后台新增改签订单发生错误：", e);
			resMap.put("CODE", "-1");
			resMap.put("MSG","后台新增改签订单发生错误：" + e);
		}
		return resMap;
	}

}
