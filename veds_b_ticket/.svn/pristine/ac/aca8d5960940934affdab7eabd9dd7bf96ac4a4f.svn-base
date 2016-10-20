package cn.vetech.web.vedsb.cgptsz;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpCgddController extends MBaseControl<JpCgdd, JpCgddServiceImpl>{

	@Autowired
	private AttachService attachService;
	
	@Override
	public void updateInitEntity(JpCgdd t) {
	}

	@Override
	public void insertInitEntity(JpCgdd t) {
	}

	public void selectInitEntity(Map<String, Object> param){
		Shyhb user = SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", user.getShbh());
	}
	
	public void pageAfter(Page page){
		if(page != null){
			List<?> list = page.getList();
			if(CollectionUtils.isNotEmpty(list)){
				attachService.zfkm("cgZfkm").execute(list);
			}
		}
	}
}
