package cn.vetech.web.vedsb.bbzx;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpztjk.Jpkpzt;
import cn.vetech.vedsb.jp.service.jpztjk.JpkpztServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 到期Open票操作
 * @author win7
 *
 */
@Controller
public class JpztjkController extends MBaseControl<Jpkpzt, JpkpztServiceImpl> {

	private static final String PAGE_SIZE = "20";
	
	@Override
	public void updateInitEntity(Jpkpzt t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertInitEntity(Jpkpzt t) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 到期Open票分页列表查询
	 * @param jpkpzt
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryPage",method = RequestMethod.POST)
	public @ResponseBody Page queryPage(@ModelAttribute() Jpkpzt jpkpzt,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "desc") String sortType,
			HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String kssj = StringUtils.trimToEmpty(request.getParameter("kssj"));
		String jssj = StringUtils.trimToEmpty(request.getParameter("jssj"));
		String cprs = StringUtils.trimToEmpty(request.getParameter("cprs"));
		String cprz = StringUtils.trimToEmpty(request.getParameter("cprz"));
		jpkpzt.setShbh(user.getShbh());
		jpkpzt.setKssj(kssj);
		jpkpzt.setJssj(jssj);
		jpkpzt.setCprs(cprs);
		jpkpzt.setCprz(cprz);
		Page page = null;
		try {
			page  = this.baseService.queryPage(jpkpzt, pageNum, pageSize, sortType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 批量办理
	 * @param tknos
	 * @param clbz
	 * @return
	 */
	@RequestMapping(value = "jpztjkEditSave",method = RequestMethod.POST)
	public @ResponseBody int jpztjkEditSave(@RequestParam(value = "tknos", defaultValue = "") String tknos,
			@RequestParam(value = "clbz", defaultValue = "") String clbz){
		int recount = 0;
		int count = 0;
		try {
			clbz = new String(clbz.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		Shyhb user = SessionUtils.getShshbSession();
		Jpkpzt jpkpzt = new Jpkpzt();
		jpkpzt.setShbh(user.getShbh());
		jpkpzt.setClUserid(user.getBh());
		jpkpzt.setClDeptid(user.getShbmid());
		jpkpzt.setClCompid(user.getShbh());
		jpkpzt.setClZt("1");
		jpkpzt.setClBz(clbz);
		jpkpzt.setClDatetime(VeDate.dateToStr(VeDate.getNow()));
		if(StringUtils.isNotBlank(tknos)){
			String[] arr = StringUtils.split(tknos.trim(), ",");
			for(int i=0;i<arr.length;i++){
				jpkpzt.setTkno(arr[i]);
				this.baseService.getMyBatisDao().updateByTkno(jpkpzt);
				count++;
			}
			if(count>0){
				recount=1;
			}else{
				recount=2;
			}
		}
		return recount;
	}
	
	/**
	 * 根据票号删除状态和商户编号
	 * @param tknos
	 * @return
	 */
	@RequestMapping(value="jpztjkDel")
	public String jpztjkDel(@RequestParam(value = "tknos", defaultValue = "") String tknos,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		if(StringUtils.isNotBlank(tknos)){
			String[] arr = StringUtils.split(tknos.trim(), ",");
			for(int i=0;i<arr.length;i++){
				try {
					this.baseService.getMyBatisDao().delByTknoAndShbh(arr[i],user.getShbh());
				} catch (Exception e) {
					logger.error("删除错误", e);
					return this.addError("删除错误" + e.getMessage(), e, "list", model);
				}
			}
		}
		return "/common/turning";
	}
	
}
