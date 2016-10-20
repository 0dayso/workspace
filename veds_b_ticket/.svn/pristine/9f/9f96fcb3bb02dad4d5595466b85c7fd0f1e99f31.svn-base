package cn.vetech.web.vedsb.pzzx;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzPzJz;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.pzzx.JpPzPzJzServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller  //票证追踪
public class PzzzController extends MBaseControl<JpXcd, JpXcdServiceImpl>{
	@Autowired
	private JpPzPzJzServiceImpl jpPzPzJzServiceImpl;
	@Autowired
	private AttachService attachService;
	
	/**
	 * 查询报废列表
	 * @param xcdNo 行程单号
	 * @param tkNo  票号
	 * @param pztype 票证类型
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示条目数
	 * @return
	 */
	@RequestMapping(value = "pzzzlist")
	public String getListByBfCX(@RequestParam(value = "pztype", defaultValue = "") String pztype,
			@RequestParam(value="xcdNo", defaultValue = "")String xcdNo,
			@RequestParam(value="tkno", defaultValue = "")String tkno,Model model){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		String shbh=shyhb.getShbh();
		try {
			JpXcd jpXcd=this.baseService.getJpXcd(shbh, xcdNo, pztype,tkno);
			List<JpPzPzJz> list=new ArrayList<JpPzPzJz>();
			//判断票号是否为空
			if(StringUtils.isNotBlank(tkno)){//票号不为空，则通过查询jpXcd中的xcdNo来查询
				if(jpXcd!=null){
					list=jpPzPzJzServiceImpl.getListByPzBh(shbh, jpXcd.getXcdNo(), pztype);
				}
			}else{//票号为空，则通过查询用户输入的xcdNo查询
				list=jpPzPzJzServiceImpl.getListByPzBh(shbh, xcdNo, pztype);
			}
			//在数据字典中根据pztype获取相应的对象
			attachService.veclass("pztype").shyhb("czYhbh", shbh).execute(list);
			model.addAttribute("list", list);
			model.addAttribute("jpXcd", jpXcd);
			
		} catch (Exception e) {
			logger.error("查询报废列表信息失败"+e.getMessage());
		}
		return viewpath("list");
	}

	@Override
	public void updateInitEntity(JpXcd t) {
		
	}
	@Override
	public void insertInitEntity(JpXcd t) {
		
	}
}
