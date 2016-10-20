package cn.vetech.web.vedsb.jptpsz;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jptpsz.JpTpsz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jptpsz.JpTpszServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpTpszController extends MBaseControl<JpTpsz, JpTpszServiceImpl>{
	@Autowired
	private AttachService attachService;
	@Override
	public void insertInitEntity(JpTpsz t) {
		Shyhb yhb=SessionUtils.getShshbSession();
		t.setShbh(yhb.getShbh());
		t.setCjsj(new Date());
		t.setXgsj(new Date());
		t.setCjyh(yhb.getBh());
		t.setXgyh(yhb.getBh());
		
	}

	@Override
	public void updateInitEntity(JpTpsz t) {
		Shyhb yhb=SessionUtils.getShshbSession();
		t.setXgyh(yhb.getBh());
		t.setXgsj(new Date());
	}
	
	@Override
	public void selectInitEntity(Map param){
		Shyhb yhb=SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", yhb.getShbh());
	}
	@Override
	public void pageAfter(Page page){
		List list=page.getList();
		if(CollectionUtils.isNotEmpty(list)){
			Shyhb yhb=SessionUtils.getShshbSession();
			attachService.shyhb("xgyh",yhb.getShbh()).execute(list);
		}
	}
	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String batchDel(@RequestParam(value = "id", defaultValue = "") String id) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if (StringUtils.isBlank(id)) {
				throw new Exception("id不能为空");
			}
			String[] ids=id.split(",");
			List<JpTpsz> list=new ArrayList<JpTpsz>();
			for (String s : ids) {
				JpTpsz sz=new JpTpsz();
				sz.setShbh(yhb.getShbh());
				sz.setId(s);
				list.add(sz);
			}
			baseService.batchDel(list);
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败，失败id为 " + id, e);
			return "-1";
		}
	}
}
