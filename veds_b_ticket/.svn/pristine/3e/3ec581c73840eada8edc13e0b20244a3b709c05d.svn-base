package cn.vetech.web.vedsb.cgdzbb;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.WebUtils;
import org.vetech.core.modules.web.JsonBean;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdz;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdzCyb;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdzMx;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpDzsz;
import cn.vetech.vedsb.jp.service.cgdzbb.JpCgdzMxServiceImpl;
import cn.vetech.vedsb.jp.service.cgdzbb.JpCgdzServiceImpl;
import cn.vetech.vedsb.jp.service.jpcwgl.JpDzszServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 所有采购数据对账
 * 
 * @author houya
 *
 */
@Controller
public class JpCgalldzController extends MBaseControl<JpCgdz, JpCgdzServiceImpl> {
	@Autowired
	private JpDzszServiceImpl jpDzszServiceImpl;
	@Autowired
	private JpCgdzMxServiceImpl jpCgdzMxServiceImpl;
	@Autowired
	private JpCgdzServiceImpl jpCgdzServiceImpl;

	/**
	 * 采购对账主表查询列表
	 */
	@RequestMapping
	public @ResponseBody Page getCgdzList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "sortType", defaultValue = "desc") String sortType, HttpServletRequest request, @ModelAttribute("entity") JpCgdzCyb jpcgdzcyb) {
		Page page = null;
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_", true);
			selectInitEntity(searchParams);
			page = this.baseService.queryPage(searchParams, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 创建一条对账记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String createCgdz(Model model, HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		String dzrq = request.getParameter("dzrq");
		JpCgdz jpCgdz = new JpCgdz();
		jpCgdz.setDzrq(dzrq);
		int count = this.baseService.createCgdz(user, jpCgdz);
		if (count > 0) {
			return jpCgdz.getId();
		}
		return 0 + "";
	}

	/**
	 * 查看每天对账详情
	 */
	@RequestMapping
	public String daycgdz(Model model, HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		String id = request.getParameter("id");
		JpCgdz jpCgdz = baseService.getCgdz(user, id);
		if (jpCgdz != null) {
			// 获取页面标签
			Map<String, Object> mxGroup = jpCgdzMxServiceImpl.getCgdzMxGroup(user.getShbh(), id);
			model.addAttribute("mxgroup", mxGroup);
		}
		model.addAttribute("jpcgdz", jpCgdz);
		return view("daycgdz");
	}

	/**
	 * 上传直通车数据
	 */
	@RequestMapping
	public String bankup(Model model, @RequestParam(value = "files") MultipartFile[] files, @RequestParam(value = "gsbhs") String[] gsbhs, HttpServletRequest request) throws Exception {
		Shyhb shyhb = SessionUtils.getShshbSession();
		String id = request.getParameter("id");
		try {
			if (gsbhs == null || gsbhs.length < 1) {
				throw new Exception("没有找到格式编号");
			}

			JpCgdz jpCgdz = jpCgdzServiceImpl.getCgdz(shyhb, id);
			if (jpCgdz == null) {
				throw new Exception("没有找到采购对账主表" + id + "记录");
			}
			String path = WebUtils.getRootPath("updownFiles" + File.separator + "tmpfiles" + File.separator + "cgdz" + File.separator);
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			for (int p = 0; p < gsbhs.length; p++) {
				String gsbh = gsbhs[p];
				if (StringUtils.isBlank(gsbh)) {
					continue;
				}
				MultipartFile file = files[p];
				JpDzsz t = new JpDzsz();
				t.setGsbh(gsbh);
				t.setShbh("0000");// 系统格式商户编号为0000
				JpDzsz jpDzsz = jpDzszServiceImpl.getEntityById(t);
				if (jpDzsz == null) {
					continue;
				}
				// 直通车
				if ("10100073".equals(gsbh)) {
					List<JpCgdzMx> jpCgdzMxList = jpCgdzMxServiceImpl.ztcDz(file, jpDzsz, path);
					jpCgdzMxServiceImpl.insertAll(shyhb, jpCgdz, jpCgdzMxList);
				}
			}
		} catch (Exception e) {
			logger.error("导入账单失败", e);
			request.setAttribute("error", "导入账单失败" + e.getMessage());
		}
		return view("bankup");
	}

	/**
	 * 采购对账主表查询列表
	 */
	@RequestMapping
	public @ResponseBody Page getCgdzMxList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "sortType", defaultValue = "desc") String sortType, HttpServletRequest request, @ModelAttribute("entity") JpCgdzCyb jpcgdzcyb) {
		Page page = null;
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_", true);
			selectInitEntity(searchParams);
			page = jpCgdzMxServiceImpl.queryPage(searchParams, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 对比数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public JsonBean dbdata(HttpServletRequest request) {
		Shyhb shyhb = SessionUtils.getShshbSession();
		String id = request.getParameter("id");
		JsonBean jb = null;
		try {
			JpCgdz jpCgdz = baseService.getCgdz(shyhb, id);
			if (jpCgdz == null || StringUtils.isBlank(jpCgdz.getDzrq())) {
				throw new Exception("没有找到对账主表记录" + id);
			}
			jpCgdzMxServiceImpl.proc_cgdz_zddb(shyhb.getShbh(), jpCgdz.getDzrq(), jpCgdz.getId(), shyhb.getBh());
			jb = new JsonBean("1", "");
		} catch (Exception e) {
			e.printStackTrace();
			jb = new JsonBean("0", "对比异常:" + e.getMessage());
		}
		return jb;
	}

	/**
	 * 清理数据重新对账
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public JsonBean delDbresult(HttpServletRequest request) {
		Shyhb shyhb = SessionUtils.getShshbSession();
		String id = request.getParameter("id");
		JsonBean jb = null;
		try {
			JpCgdz jpCgdz = jpCgdzServiceImpl.getCgdz(shyhb, id);
			if (jpCgdz == null) {
				throw new Exception("没有找到采购对账主表" + id + "记录");
			}
			jpCgdzMxServiceImpl.deleteByZbid(shyhb, jpCgdz);
			jb = new JsonBean("1", "");

		} catch (Exception e) {
			jb = new JsonBean("0", e.getMessage());
		}
		return jb;
	}

	@Override
	public void updateInitEntity(JpCgdz t) {

	}

	@Override
	public void insertInitEntity(JpCgdz t) {

	}
}
