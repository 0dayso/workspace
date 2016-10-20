package cn.vetech.web.vedsb.jpzwgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqJcgz;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqJcsz;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqJcgzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqJcszServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpTjsqJcgzController extends MBaseControl<JpTjsqJcgz, JpTjsqJcgzServiceImpl>{
	
	@Autowired
	private JpTjsqJcszServiceImpl jpTjsqJcszServiceImpl;
	
	@Override
	public void insertInitEntity(JpTjsqJcgz t) {
	}

	@Override
	public void updateInitEntity(JpTjsqJcgz t) {
	}
	/**
	 * 追位基础设置 修改前
	 */
	@RequestMapping
	public String toedit(ModelMap model){
			Shyhb yhb = SessionUtils.getShshbSession();
			JpTjsqJcgz jcgzBean = new JpTjsqJcgz();
			jcgzBean.setShbh(yhb.getShbh());
			List<JpTjsqJcgz> jcgzList = this.baseService.getTjsqJcgzList(jcgzBean);
		    model.addAttribute("jcgzList", jcgzList);
		    return viewpath("edit");
	}
	/**
	 * 保存 追位基础规则设置
	 */
	@RequestMapping
	public String saveJcgz(ModelMap model,HttpServletRequest request){
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			JpTjsqJcgz jcgz = new JpTjsqJcgz();
			jcgz.setShbh(yhb.getShbh());
			List<JpTjsqJcgz> jcgzList = this.baseService.getTjsqJcgzList(jcgz);
			Date now=new Date();
			if (null != jcgzList && jcgzList.size() > 0) {
				List<JpTjsqJcsz> jcszBeanList = new ArrayList<JpTjsqJcsz>();
				for (JpTjsqJcgz jcgzBean : jcgzList) {
					String zdywm = jcgzBean.getZdywm();
					String zdz = "";
					if("checkbox".equals(jcgzBean.getZdlx())){
						String[] zdzs=request.getParameterValues(zdywm);
						if(zdzs == null || zdzs.length==0){
							zdz="";
						}else {
							zdz=StringUtils.join(zdzs,"/");
						}
					}else{
						zdz=StringUtils.trimToEmpty(request.getParameter(zdywm));
					}
					String jcszid = request.getParameter(zdywm + "szid");
					JpTjsqJcsz jcszBean = new JpTjsqJcsz();
					jcszBean.setId(jcszid);
					jcszBean.setZdywm(zdywm);
					jcszBean.setZdz(zdz);
					jcszBean.setXgsj(now);
					jcszBean.setShbh(yhb.getShbh());
					jcszBean.setXgyh(yhb.getBh());
					if (StringUtils.isBlank(jcszid)) {
						jcszBean.setCjsj(now);
						jcszBean.setCjyh(yhb.getBh());
					}
					if("SFZW".equals(zdywm) && "0".equals(zdz)){//关闭时不存
						jcszBeanList.clear();
						jcszBeanList.add(jcszBean);
						break;
					}
					jcszBeanList.add(jcszBean);
				}
				jpTjsqJcszServiceImpl.saveTjsqJcsz(jcszBeanList);
			}
			model.addAttribute("isSuccess","0");
		} catch (Exception e) {
			model.addAttribute("isSuccess","-1");
			e.printStackTrace();
		}
	    return viewpath("edit");
	}
}
