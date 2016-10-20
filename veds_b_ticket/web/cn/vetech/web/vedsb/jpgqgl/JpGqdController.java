package cn.vetech.web.vedsb.jpgqgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.BcityCacheService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.tknetrdx.JpGqdBpResult;
import org.vetech.core.business.pid.api.tknetrdx.VeTkneTrdxIndex;
import org.vetech.core.business.pid.pidbean.AvCabinModel;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shjcsj;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.ShjcsjServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpQz;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCjr;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdHd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdStepEnum;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpbcdgl.JpBcdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpQzServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdDataServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.utils.JpGqdBpUtils;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpGqdController extends MBaseControl<JpGqd, JpGqdServiceImpl> {

	private static final String PAGE_SIZE = "10";

	@Autowired
	private JpGqdDataServiceImpl jpGqdDataServiceImpl;

	@Autowired
	private JpGqdCjrServiceImpl jpGqdCjrServiceImpl;

	@Autowired
	private JpGqdHdServiceImpl jpGqdHdServiceImpl;

	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;

	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;

	@Autowired
	private JpBcdServiceImpl jpBcdServiceImpl;

	@Autowired
	private JpGqdServiceImpl jpGqdServiceImpl;

	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;

	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;

	@Autowired
	private AttachService attachService;

	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;

	@Autowired
	private JpQzServiceImpl jpQzService;

	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;

	@Autowired
	private ShjcsjServiceImpl shjcsjServiceImpl;

	@Autowired
	private BcityCacheService bcityCacheService;

	@Override
	public void updateInitEntity(JpGqd t) {

	}

	@Override
	public void insertInitEntity(JpGqd t) {

	}

	/**
	 * 进入改签订单列表查询页面
	 */
	@RequestMapping(value = "viewlist")
	public String view(ModelMap model, String gngj) throws Exception {
		try {
			if (StringUtils.isBlank(gngj)) {
				gngj = "1"; // 国内
			}
			Shyhb user = SessionUtils.getShshbSession();
			// 获取网店信息
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("zt", "1");// 启用
			param.put("shbh", user.getShbh());
			if ("1".equals(gngj)) {
				param.put("ywlxs", new String[] { "1001901" });
			} else if ("0".equals(gngj)) {
				param.put("ywlxs", new String[] { "1001902" });
			}
			List<Wdzlsz> wdzlszList = wdzlszServiceImpl
					.getWdZlszListByYwlx(param);
			model.addAttribute("wdzlszList", wdzlszList);
			return viewpath("list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转改签列表页面错误：", e);
			return this.addError("跳转改签列表页面错误：" + e.getMessage(), e, "list",
					model);
		}
	}

	/**
	 * 分页查询改签管理列表
	 * 
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param sortName
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
	Page query(
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			Model model, HttpServletRequest request) throws Exception {
		Page page = null;
		Map<String, Object> jpgqdMap = null;
		try {
			Shyhb user = SessionUtils.getShshbSession();
			jpgqdMap = Servlets.getParametersStartingWith(request, "", false);
			PageHelper ph = new PageHelper();
			jpgqdMap.put("start", ph.getStart(pageNum, pageSize));
			jpgqdMap.put("count", ph.getCount(pageNum, pageSize));
			jpgqdMap.put("pageNum", pageNum);
			jpgqdMap.put("pageSize", pageSize);
			jpgqdMap.put("shbh", user.getShbh());
			page = this.baseService.query(jpgqdMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询改签列表页面数据错误：", e);
			throw new Exception("查询改签列表页面数据错误：" + e.getMessage());
		}
		return page;
	}

	/**
	 * 改签详内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "detail_{gqdh}")
	public String detail(@PathVariable("gqdh") String gqdh, ModelMap model,
			HttpServletRequest request) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			/**
			 * 通过改签单号获得改签信息
			 */
			JpGqd jpGqd = this.baseService.getJpGqdByGqdh(gqdh, user.getShbh());
			if (jpGqd == null || StringUtils.isBlank(jpGqd.getGqdh())) {
				throw new Exception("未获取到改签订单信息，请核对改签单号【" + gqdh
						+ "】对应改签订单是否存在");
			}
			//网店ID替值
			attachService.wdzl("wdid").execute(jpGqd);
			/**
			 * 通过改签单号获得改签明细乘机人信息
			 */
			Map<String, List<JpGqdCjr>> jpGqdCjrMap = jpGqdCjrServiceImpl
					.getCjrListByGqdhGroup(gqdh, user.getShbh());
			if (jpGqdCjrMap == null || jpGqdCjrMap.isEmpty()) {
				throw new Exception("未获取到改签订单乘机人信息，请核对改签单号【" + gqdh
						+ "】对应改签订单乘机人信息是否存在");
			}

			/**
			 * 通过票号获得改签前航段信息和改签后航段信息
			 */
			List<JpGqdHd> jpGqdHdList = jpGqdHdServiceImpl.getHdListByGqdh(
					gqdh, user.getShbh());
			if (jpGqdHdList == null || jpGqdHdList.isEmpty()) {
				throw new Exception("未获取到改签订单航段信息，请核对改签单号【" + gqdh
						+ "】对应改签订单航段信息是否存在");
			}

			// 补差单信息
			List<JPBcd> jpBcdList = this.jpBcdServiceImpl.getbcdList(
					user.getShbh(), gqdh, "03");
			for (JPBcd jpbcd : jpBcdList) {
				Shjcsj shjcsj = this.shjcsjServiceImpl.getMyBatisDao()
						.getShjcsj(user.getShbh(), jpbcd.getBclx());
				jpbcd.setBclxname(shjcsj.getMc());
			}
			model.addAttribute("bcdList", jpBcdList);

			/**
			 * 支付科目
			 */

			Shzfkm zfkm = new Shzfkm();
			zfkm.setShbh(user.getShbh());
			zfkm.setSfskkm("1");
			zfkm.setSfqy("1");
			String gngj = jpGqd.getGngj();
			if (StringUtils.isBlank(gngj)) {
				gngj = "1"; // 国内
			}
			if ("1".equals(gngj)) {
				zfkm.setSycp("1001901");// 国内机票产品
			} else if ("0".equals(gngj)) {
				zfkm.setSycp("1001902");// 国际机票产品
			}
			List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);
			model.addAttribute("zfkmList", zfkmList);

			/**
			 * 通过订单编号获得正常单信息
			 */
			String ddbh = jpGqd.getDdbh();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ddbh", ddbh);
			paramMap.put("shbh", user.getShbh());
			Map<String, Object> jpKhddMap = jpKhddServiceImpl.detail(paramMap);

			model.addAttribute("jpgqd", jpGqd);
			model.addAttribute("jpGqdCjrMap", jpGqdCjrMap);
			model.addAttribute("jpGqdHdList", jpGqdHdList);
			model.addAttribute("jpKhddMap", jpKhddMap);

			List<JpQz> list = jpQzService.queryListByYwdh(gqdh);
			attachService.shyhb("qzYhbh", user.getShbh()).execute(list);
			model.addAttribute("list", list);
			model.addAttribute("ywlx", "03");// 01为正常订单
			return viewpath("detail");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询改签详内容错误：", e);
			return this.addError("查询改签详内容错误：" + e.getMessage(), e, "detail",
					model);
		}

	}

	/**
	 * 进入改签订单签注信息页面
	 */
	@RequestMapping(value = "viewgqd_qzxx_{gqdh}")
	public String viewQz(@PathVariable("gqdh") String gqdh, ModelMap model)
			throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			List<JpQz> list = jpQzService.queryListByYwdh(gqdh);
			attachService.shyhb("qzYhbh", user.getShbh()).execute(list);
			model.addAttribute("list", list);
			model.addAttribute("ywlx", "03");// 01为正常订单
			model.addAttribute("gqdh", gqdh);
			return viewpath("gqd_qzxx");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入签注页面错误：", e);
			return this.addError("跳转改签列表页面错误：" + e.getMessage(), e, "gqd_qzxx",
					model);
		}
	}

	/**
	 * 改签单审核页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "review")
	public String review(String gqdh, String forward, ModelMap model,
			HttpServletRequest request) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			/**
			 * 通过改签单号获得改签信息
			 */
			JpGqd jpGqd = this.baseService.getJpGqdByGqdh(gqdh, user.getShbh());
			if (jpGqd == null || StringUtils.isBlank(jpGqd.getGqdh())) {
				throw new Exception("未获取到改签订单信息，请核对改签单号【" + gqdh
						+ "】对应改签订单是否存在");
			}

			String ddbh = jpGqd.getDdbh();
			JpKhdd jpkhdd = new JpKhdd();
			jpkhdd.setDdbh(ddbh);
			jpkhdd.setShbh(user.getShbh());
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(jpkhdd);

			/**
			 * 通过改签单号获得改签明细乘机人信息
			 */
			Map<String, List<JpGqdCjr>> jpGqdCjrMap = jpGqdCjrServiceImpl
					.getCjrListByGqdhGroup(gqdh, user.getShbh());
			if (jpGqdCjrMap == null || jpGqdCjrMap.isEmpty()) {
				throw new Exception("未获取到改签订单乘机人信息，请核对改签单号【" + gqdh
						+ "】对应改签订单乘机人信息是否存在");
			}

			/**
			 * 通过改签单号获得改签前航段信息和改签后航段信息
			 */
			List<JpGqdHd> jpGqdHdList = jpGqdHdServiceImpl.getHdListByGqdh(
					gqdh, user.getShbh());
			if (jpGqdHdList == null || jpGqdHdList.isEmpty()) {
				throw new Exception("未获取到改签订单航段信息，请核对改签单号【" + gqdh
						+ "】对应改签订单航段信息是否存在");
			}

			/**
			 * 支付科目
			 */
			Shzfkm zfkm = new Shzfkm();
			zfkm.setShbh(user.getShbh());
			zfkm.setSffkkm("1");
			zfkm.setSfqy("1");
			String gngj = jpGqd.getGngj();
			if (StringUtils.isBlank(gngj)) {
				gngj = "1"; // 国内
			}
			if ("1".equals(gngj)) {
				zfkm.setSycp("1001901");// 国内机票产品
			} else if ("0".equals(gngj)) {
				zfkm.setSycp("1001902");// 国际机票产品
			}
			List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);

			if ("transact".equals(forward)
					&& JpGqd.GQZT_YQR.equals(jpGqd.getGqzt())) {
				jpGqd.setGqzt(JpGqd.GQZT_GQZ);
				jpGqd.setShbh(user.getShbh());
				this.baseService.updateJpGqdById(jpGqd);
			}
			model.addAttribute("jpKhdd", jpKhdd);
			model.addAttribute("jpgqd", jpGqd);
			model.addAttribute("jpGqdCjrMap", jpGqdCjrMap);
			model.addAttribute("jpGqdHdList", jpGqdHdList);
			model.addAttribute("zfkmList", zfkmList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询改签办理内容错误：", e);
			return this.addError("查询改签办理内容错误：" + e.getMessage(), e, forward,
					model);
		}
		return viewpath("review");
	}

	/**
	 * 跳转改签办理相关页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "transact")
	public String transact(String gqdh, String forward, ModelMap model,
			HttpServletRequest request) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			/**
			 * 通过改签单号获得改签信息
			 */
			JpGqd jpGqd = this.baseService.getJpGqdByGqdh(gqdh, user.getShbh());
			if (jpGqd == null || StringUtils.isBlank(jpGqd.getGqdh())) {
				throw new Exception("未获取到改签订单信息，请核对改签单号【" + gqdh
						+ "】对应改签订单是否存在");
			}

			String ddbh = jpGqd.getDdbh();
			JpKhdd jpkhdd = new JpKhdd();
			jpkhdd.setDdbh(ddbh);
			jpkhdd.setShbh(user.getShbh());
			JpKhdd jpKhdd = jpKhddServiceImpl.getKhddByDdbh(jpkhdd);

			/**
			 * 通过改签单号获得改签明细乘机人信息
			 */
			Map<String, List<JpGqdCjr>> jpGqdCjrMap = jpGqdCjrServiceImpl
					.getCjrListByGqdhGroup(gqdh, user.getShbh());
			if (jpGqdCjrMap == null || jpGqdCjrMap.isEmpty()) {
				throw new Exception("未获取到改签订单乘机人信息，请核对改签单号【" + gqdh
						+ "】对应改签订单乘机人信息是否存在");
			}

			/**
			 * 通过改签单号获得改签前航段信息和改签后航段信息
			 */
			List<JpGqdHd> jpGqdHdList = jpGqdHdServiceImpl.getHdListByGqdh(
					gqdh, user.getShbh());
			if (jpGqdHdList == null || jpGqdHdList.isEmpty()) {
				throw new Exception("未获取到改签订单航段信息，请核对改签单号【" + gqdh
						+ "】对应改签订单航段信息是否存在");
			}

			/**
			 * 支付科目
			 */
			Shzfkm zfkm = new Shzfkm();
			zfkm.setShbh(user.getShbh());
			zfkm.setSffkkm("1");
			zfkm.setSfqy("1");
			String gngj = jpGqd.getGngj();
			if (StringUtils.isBlank(gngj)) {
				gngj = "1"; // 国内
			}
			if ("1".equals(gngj)) {
				zfkm.setSycp("1001901");// 国内机票产品
			} else if ("0".equals(gngj)) {
				zfkm.setSycp("1001902");// 国际机票产品
			}
			// zfkm.setSycp("312012401");//机票产品
			List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);

			if ("transact".equals(forward)
					&& JpGqd.GQZT_YQR.equals(jpGqd.getGqzt())) {
				jpGqd.setGqzt(JpGqd.GQZT_GQZ);
				jpGqd.setShbh(user.getShbh());
				this.baseService.updateJpGqdById(jpGqd);
			}

			model.addAttribute("jpKhdd", jpKhdd);
			model.addAttribute("jpgqd", jpGqd);
			model.addAttribute("jpGqdCjrMap", jpGqdCjrMap);
			model.addAttribute("jpGqdHdList", jpGqdHdList);
			model.addAttribute("zfkmList", zfkmList);
			return viewpath(forward);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询改签办理内容错误：", e);
			return this.addError("查询改签办理内容错误：" + e.getMessage(), e, forward,
					model);
		}

	}

	/**
	 * 保存改签详新备注
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveGqdBz", method = RequestMethod.POST)
	public String saveGqdBz(@ModelAttribute() JpGqd jpgqd,
			HttpServletRequest request, ModelMap model) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			jpgqd.setShbh(user.getShbh());
			this.baseService.updateJpGqdById(jpgqd);
			return "common/turning";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改备注信息错误：", e);
			throw new Exception("修改备注信息错误：" + e.getMessage());
		}
	}

	/**
	 * 保存改签详
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "saveGqd", method = RequestMethod.POST)
	public Map<String, Object> saveGqd(@ModelAttribute() JpGqd jpGqdBean,
			String[] jpGqdCjrIdArr, String[] gqCgfyArr, String[] gqXsfyArr,
			String[] gqCgscfyArr, String[] gqXsscfyArr,
			String[] jpGqdHdIdArr, String[] gqTknoArr, String[] nCfsjArr,
			String[] nDdsjArr, String[] nXsHbhArr, String[] nXsCwArr,
			HttpServletRequest request, ModelMap model) throws Exception {

		Shyhb user = SessionUtils.getShshbSession();
		Map<String, Object> resMap = new HashMap<String, Object>();
		jpGqdBean.setXgly("后台-改签详保存");
		try {
			String result = jpGqdDataServiceImpl.updateJpGqdData(jpGqdBean,
					jpGqdCjrIdArr, gqCgfyArr, gqXsfyArr,gqCgscfyArr,gqXsscfyArr, jpGqdHdIdArr,
					gqTknoArr, nCfsjArr, nDdsjArr, nXsHbhArr, nXsCwArr, user,
					logger);
			if ("OK".equals(result)) {
				resMap.put("CODE", "0");
			} else {
				resMap.put("CODE", "-1");
				resMap.put("MSG", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存改签详内容错误：", e);
			resMap.put("CODE", "-1");
			resMap.put("MSG", "保存改签详内容错误：" + e);
		}
		return resMap;
	}

	/**
	 * 取消改签单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "cancelGqd")
	public String cancelGqd(@ModelAttribute() JpGqd jpgqd,
			HttpServletRequest request) {
		Shyhb user = SessionUtils.getShshbSession();
		jpgqd.setShbh(user.getShbh());
		JpGqd jpGqd = this.baseService.getEntityById(jpgqd);
		if (jpGqd == null) {
			return "未找到对应改签单";
		}
		String xgly = jpgqd.getXgly();
		if ("fromGqgl".equals(xgly)) {
			jpgqd.setXgly("改签管理页面取消改签单");
		} else {
			jpgqd.setXgly("出票控制台取消改签单");
		}
		jpgqd.setGqzt(JpGqd.GQZT_YQX);
		jpgqd.setXgyh(user.getBh());
		try {
			this.baseService.updateJpGqdById(jpgqd);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消改签单发生错误：", e);
			return "取消改签单发生错误：" + e.getMessage();
		}

	}

	/**
	 * 降舱
	 * 
	 * @return
	 */
	@RequestMapping(value = "downGrading")
	public String downGrading(String ddbh, ModelMap model,
			HttpServletRequest request) {
		try {
			if (StringUtils.isNotBlank(ddbh)) {
				/**
				 * 通过订单编号获得正常单信息
				 */
				Map<String, Object> paramMap = new HashMap<String, Object>();
				Shyhb user = SessionUtils.getShshbSession();
				String shbh = user.getShbh();
				paramMap.put("shbh", shbh);
				paramMap.put("ddbh", ddbh);
				Map<String, Object> jpKhddMap = jpKhddServiceImpl
						.detail(paramMap);
				JpKhdd jpKhdd = new JpKhdd();
				jpKhdd.setDdbh(ddbh);
				jpKhdd.setShbh(shbh);
				jpKhdd = jpKhddServiceImpl.getEntityById(jpKhdd);
				if (jpKhdd == null || StringUtils.isBlank(jpKhdd.getDdbh())) {
					throw new Exception("未获取到正常单信息");
				}

				/**
				 * 获取正常单航段信息
				 */
				List<JpKhddHd> khddHdList = jpKhddHdServiceImpl
						.getKhddHdListByDDbh(ddbh, shbh);

				if (khddHdList == null || khddHdList.isEmpty()) {
					throw new Exception("未获取到正常单航段信息");
				}
				JpKhddHd jpKhddHd = khddHdList.get(0);

				List<AvCabinModel> cabinList = jpKhddServiceImpl.getCabinList(
						shbh, jpKhdd, jpKhddHd);

				model.addAttribute("jpkhdd", jpKhddMap);
				model.addAttribute("jpkhddHd", khddHdList.get(0));
				model.addAttribute("currentAvCabin",
						cabinList.remove(cabinList.size() - 1));
				model.addAttribute("cabinList", cabinList);
			}
			return viewpath("gqd_jc");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("降舱发生错误", e);
			return this.addError("降舱发生错误" + e.getMessage(), e, "gqd_jc", model);
		}
	}

	/**
	 * 改签审核处理
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveReview", method = RequestMethod.POST)
	public Map<String, Object> saveReview(@ModelAttribute() JpGqd jpGqdBean,
			String[] jpGqdCjrIdArr, String[] gqCgfyArr, String[] gqXsfyArr,
			String[] gqCgscfyArr, String[] gqXsscfyArr,
			String[] jpGqdHdIdArr, String[] gqTknoArr, String[] nCfsjArr,
			String[] nDdsjArr, String[] nXsHbhArr, String[] nXsCwArr,
			HttpServletRequest request, ModelMap model) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, Object> resMap = new HashMap<String, Object>();
		jpGqdBean.setGqzt(JpGqd.GQZT_YQR);
		jpGqdBean.setGqShr(user.getBh());
		jpGqdBean.setGqShsj(VeDate.getNow());
		jpGqdBean.setXgly("改签订单管理");
		try {
			String result = jpGqdDataServiceImpl.updateJpGqdData(jpGqdBean,
					jpGqdCjrIdArr, gqCgfyArr, gqXsfyArr,gqCgscfyArr,gqXsscfyArr, jpGqdHdIdArr,
					gqTknoArr, nCfsjArr, nDdsjArr, nXsHbhArr, nXsCwArr, user,
					logger);
			if ("OK".equals(result)) {
				resMap.put("CODE", "0");
			} else {
				resMap.put("CODE", "-1");
				resMap.put("MSG", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存改签办理发生错误：", e);
			resMap.put("CODE", "-1");
			resMap.put("MSG", "保存改签办理发生错误：" + e);
		}
		return resMap;
	}

	/**
	 * 保存改签办理
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveBl", method = RequestMethod.POST)
	public Map<String, Object> saveBl(@ModelAttribute() JpGqd jpGqdBean,
			String[] jpGqdCjrIdArr, String[] gqCgfyArr, String[] gqXsfyArr,
			String[] gqCgscfyArr, String[] gqXsscfyArr,
			String[] jpGqdHdIdArr, String[] gqTknoArr, String[] nCfsjArr,
			String[] nDdsjArr, String[] nXsHbhArr, String[] nXsCwArr,
			HttpServletRequest request, ModelMap model) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, Object> resMap = new HashMap<String, Object>();
		jpGqdBean.setGqzt(JpGqd.GQZT_YGQ);
		jpGqdBean.setGqBlr(user.getBh());
		jpGqdBean.setGqBlsj(VeDate.getNow());
		jpGqdBean.setXgly("后台-改签办理");
		try {
			String result = jpGqdDataServiceImpl.updateJpGqdData(jpGqdBean,
					jpGqdCjrIdArr, gqCgfyArr, gqXsfyArr,gqCgscfyArr,gqXsscfyArr, jpGqdHdIdArr,
					gqTknoArr, nCfsjArr, nDdsjArr, nXsHbhArr, nXsCwArr, user,
					logger);
			if ("OK".equals(result)) {
				resMap.put("CODE", "0");
			} else {
				resMap.put("CODE", "-1");
				resMap.put("MSG", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存改签办理发生错误：", e);
			resMap.put("CODE", "-1");
			resMap.put("MSG", "保存改签办理发生错误：" + e);
		}
		return resMap;
	}

	/**
	 * 获取白屏改签步骤
	 * 
	 * @return
	 */
	@RequestMapping(value = "getGqBz")
	public String getGqBz(String gqdh, ModelMap model,
			HttpServletRequest request) throws Exception {
		String commandType = StringUtils.trimToEmpty(request
				.getParameter("commandType"));// 指令
		String bmsfyx = StringUtils.trimToEmpty(request
				.getParameter("isPnrValid"));// 编码是否有效
		String sfblbm = StringUtils.trimToEmpty(request
				.getParameter("isRetainPnr"));// 是否保留编码
		String sfsygq = StringUtils.trimToEmpty(request
				.getParameter("isAllPassnerChange"));// 是否所有乘机人改签
		try {
			if (StringUtils.isBlank(commandType)
					|| (!"TKNE".equals(commandType) && !"TRDX"
							.equals(commandType))) {
				throw new Exception("传入指令错误！" + commandType);
			}
			boolean isPnrValid = checkParam(bmsfyx, "编码是否有效");
			boolean isRetainPnr = checkParam(sfblbm, "是否保留编码");
			boolean isAllPassnerChange = checkParam(sfsygq, "是否所有乘机人改签");
			List<JpGqdStepEnum> list = JpGqdBpUtils.getStepList(commandType,
					isPnrValid, isRetainPnr, isAllPassnerChange);
			if (null == list || list.isEmpty()) {
				throw new Exception("获取改签步骤结果为空！");
			}
			model.addAttribute("list", list);
			return viewpath("gqd_bzxx_bp");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取改签步骤发生错误：", e);
			return this.addError("获取改签步骤发生错误：" + e.getMessage(), e,
					"gqd_bzxx_bp", model);
		}

	}

	/**
	 * 获取TRDX指令所需的价格
	 * 
	 * @return
	 */
	@RequestMapping(value = "getGqPrice")
	public String getGqPrice(String gqdh, ModelMap model,
			HttpServletRequest request) throws Exception {
		String commandType = StringUtils.trimToEmpty(request
				.getParameter("commandType"));// 指令
		String bmsfyx = StringUtils.trimToEmpty(request
				.getParameter("isPnrValid"));// 编码是否有效
		String sfblbm = StringUtils.trimToEmpty(request
				.getParameter("isRetainPnr"));// 是否保留编码
		String osict = StringUtils.trimToEmpty(request.getParameter("osict"));// 手机号码
		String scny = StringUtils.trimToEmpty(request.getParameter("price"));// 选定的价格
		boolean isPnrValid = checkParam(bmsfyx, "编码是否有效");
		boolean isRetainPnr = checkParam(sfblbm, "是否保留编码");
		Shyhb user = SessionUtils.getShshbSession();
		JpGqdBpResult result = null;
		try {
			List<Map<String, Object>> personList = new ArrayList<Map<String, Object>>();
			JpGqd jpgqd = jpGqdServiceImpl.getJpGqdByGqdh(gqdh, user.getShbh());
			if (jpgqd == null || StringUtils.isBlank(jpgqd.getGqdh())) {
				throw new Exception("获取改签订单信息为空");
			}
			if ("1".equals(jpgqd.getBpgqzt()) || "2".equals(jpgqd.getBpgqzt())) {
				throw new Exception("该改签订单已经做过白屏改签");
			} else {
				JpGqdBpUtils jpGqdUtil = new JpGqdBpUtils();
				result = jpGqdUtil.changeTicket(gqdh, commandType, osict,
						isPnrValid, isRetainPnr, scny, user,
						jpGqdCjrServiceImpl, jpGqdHdServiceImpl,
						jpKhddServiceImpl, jpGqdServiceImpl, jpJpServiceImpl,
						jpPzServiceImpl, bcityCacheService, request);
				jpgqd = jpGqdServiceImpl.getJpGqdByGqdh(gqdh, user.getShbh());
				// 第一次TRDX时的价格处理
				if ("TRDX".equals(commandType) && "3".equals(jpgqd.getBpgqzt())) {
					// 获取价格项时处理
					if (result.getTrdxIndexs() != null
							&& !result.getTrdxIndexs().isEmpty()) {
						List<VeTkneTrdxIndex> trdxIndexs = result
								.getTrdxIndexs();
						VeTkneTrdxIndex person = null;
						String priceDetail = null;

						for (int i = 0; i < trdxIndexs.size(); i++) {
							person = trdxIndexs.get(i);
							Map<String, Object> personMap = new HashMap<String, Object>();
							personMap.put("INDEX", person.getIndex());
							personMap.put("PERSON", person.getPerson());
							personMap.put("FOID", person.getFoid());
							personMap.put("PRICE", person.getPrice());

							// 处理每个人的价格信息,可能有一个或多个价格
							priceDetail = person.getPrice();
							getPrices(priceDetail, personMap);
							personList.add(personMap);
						}

						request.setAttribute("personList", personList);
					}
					// 当TRDX时未获取价格项时的处理
					else {
						// 修改改签单状态为失败
						jpgqd.setBpgqzt("2");
						jpgqd.setXgly("白屏改签");
						request.setAttribute("trdxPriceErr", "执行TRDX指令时无价格项!");
					}
				}
			}
			return viewpath("gqd_prices_bp");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取TRDX指令价格发生错误：", e);
			return this.addError("获取TRDX指令价格发生错误：" + e.getMessage(), e,
					"gqd_prices_bp", model);
		}
	}

	/**
	 * 检验编码是否有效及乘机人是否全部改签
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkPnr")
	public Map<String, Object> checkPnr(String gqdh, Model model,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Shyhb user = SessionUtils.getShshbSession();
		try {
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(user.getShbh());
			JpGqdPnrCheck jpGqdPnrCheck = new JpGqdPnrCheck(gqdh, user,
					request, jpPz, this.baseService, jpKhddServiceImpl);
			map.put("CODE", "0");
			map.put("ISALLPASSNERCHANGE", jpGqdPnrCheck.isAllPassnerChange());
			map.put("ISPNRVALID", jpGqdPnrCheck.isPnrValid());
			map.put("PNRNR", jpGqdPnrCheck.getPnrnr());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("CODE", "-1");
			map.put("ERROR", "检查编码是否有效失败！" + e.getMessage());
		}
		return map;
	}

	/**
	 * 白屏改签 执行TKNE或TRDX指令完成改签并封口
	 * 
	 * @return ActionForward [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping(value = "gq")
	public Map<String, Object> gq(String gqdh, Model model,
			HttpServletRequest request) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		String commandType = StringUtils.trimToEmpty(request
				.getParameter("commandType"));// 指令
		String bmsfyx = StringUtils.trimToEmpty(request
				.getParameter("isPnrValid"));// 编码是否有效
		String sfblbm = StringUtils.trimToEmpty(request
				.getParameter("isRetainPnr"));// 是否保留编码
		String sfsygq = StringUtils.trimToEmpty(request
				.getParameter("isAllPassnerChange"));// 是否所有乘机人改签
		String osict = StringUtils.trimToEmpty(request.getParameter("osict"));// 手机号码
		String scny = StringUtils.trimToEmpty(request.getParameter("price"));// 选定的价格

		boolean isPnrValid = checkParam(bmsfyx, "编码是否有效");
		boolean isRetainPnr = checkParam(sfblbm, "是否保留编码");
		boolean isAllPassnerChange = checkParam(sfsygq, "是否所有乘机人改签");

		Map<String, Object> gqDetails = null;
		JpGqdBpResult result = null;
		StringBuffer otherErr = new StringBuffer();

		try {
			JpGqd jpGqd = null;
			try {
				jpGqd = this.baseService.getJpGqdByGqdh(gqdh, user.getShbh());
			} catch (Exception e) {
				e.printStackTrace();
				otherErr.append("取改签单信息错误，请联系管理员！");
				throw new Exception("取改签单信息错误，请联系管理员！<br>原因：" + e.getMessage());
			}
			if (jpGqd == null || StringUtils.isBlank(jpGqd.getGqdh())) {
				otherErr.append("取改签单信息失败，请检查改签单是否存在！");
				throw new Exception("取改签单信息失败，请检查改签单是否存在！");
			}

			if ("1".equals(jpGqd.getBpgqzt()) || "2".equals(jpGqd.getBpgqzt())) {
				otherErr.append("改签已执行，不能再次改签！");
				throw new Exception("改签已执行，不能再次改签！前一次改签状态为: "
						+ jpGqd.getBpgqzt());
			}
			JpGqdBpUtils jpGqdBpUtil = new JpGqdBpUtils();
			// 执行改签
			result = jpGqdBpUtil.changeTicket(gqdh, commandType, osict,
					isPnrValid, isRetainPnr, scny, user, jpGqdCjrServiceImpl,
					jpGqdHdServiceImpl, jpKhddServiceImpl, jpGqdServiceImpl,
					jpJpServiceImpl, jpPzServiceImpl, bcityCacheService,
					request);

			List<JpGqdStepEnum> stepList = JpGqdBpUtils.getStepList(
					commandType, isPnrValid, isRetainPnr, isAllPassnerChange);
			gqDetails = getGqDetails(result, stepList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("白屏改签发生错误：", e);
			gqDetails = new HashMap<String, Object>();
			gqDetails.put("STATUS", "-1");
			if (result != null && StringUtils.isNotBlank(result.getError())) {
				gqDetails.put("ERROR", result.getError());
			} else {
				gqDetails.put("ERROR", otherErr.toString());
			}
			return gqDetails;
		}

		return gqDetails;
	}

	/**
	 * 检查传入的指令并将传入的参数转换成boolean
	 * 
	 * @param str
	 * @param name
	 * @return
	 * @throws Exception
	 */
	private boolean checkParam(String str, String name) throws Exception {
		if (StringUtils.isBlank(str)
				|| (!"true".equals(str) && !"false".equals(str))) {
			throw new Exception("传参错误！" + name + "传入" + str);
		}
		if ("true".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 处理白屏改签时获得的运价
	 * 
	 * @param priceDetail
	 * @param personMap
	 */
	private void getPrices(String priceDetail, Map<String, Object> personMap) {
		String[] priceArr = new String[10];
		String[] fareArr = new String[10];
		String[] taxArr = new String[10];
		String[] yqArr = new String[10];
		String[] totalArr = new String[10];

		int i = 0;
		String pricePatten = "(.*CNY(\\d+\\.\\d{2}).*CNY(\\d+\\.\\d{2}).*CNY(\\d+\\.\\d{2}).*TOTAL:(\\d+\\.\\d{2}).*ACNY:(\\d+\\.\\d{2}))";
		Pattern pattern = Pattern.compile(pricePatten);
		Matcher matcher = pattern.matcher(priceDetail);
		while (matcher.find()) {
			priceArr[i] = matcher.group(1);
			fareArr[i] = matcher.group(2);
			taxArr[i] = matcher.group(3);
			yqArr[i] = matcher.group(4);
			totalArr[i] = matcher.group(5);
			i++;
		}

		personMap.put("PRICESIZE", i);
		personMap.put("PRICEARR", priceArr);
		personMap.put("FAREARR", fareArr);
		personMap.put("TAXARR", taxArr);
		personMap.put("YQARR", yqArr);
		personMap.put("TOTALARR", totalArr);
	}

	private Map<String, Object> getGqDetails(JpGqdBpResult result,
			List<JpGqdStepEnum> stepList) {
		Map<String, Object> gqDetails = new HashMap<String, Object>();
		if (stepList == null || stepList.size() == 0) {
			gqDetails.put("STEPS", "0");
			gqDetails.put("STATUS", "-1");
			gqDetails.put("ERROR", "改签步骤为空");
		} else {
			gqDetails.put("STEPS", stepList.size());
			gqDetails.put("STATUS", result.getStatus());
			gqDetails.put("ERROR", result.getError());
			for (int i = 0; i < stepList.size(); i++) {
				JpGqdStepEnum step = stepList.get(i);
				// 预订新PNR
				if (step.getId() == 3) {
					String pnrBookDetails = result.getPnrBookDetails();
					String pnrBookStatus = result.getPnrBookStatus();
					if (StringUtils.isNotBlank(pnrBookDetails)
							&& StringUtils.isNotBlank(pnrBookStatus)) {
						if (NumberUtils.toInt(pnrBookStatus) > 0) {
							pnrBookDetails = "<pre><font color='green'>"
									+ pnrBookDetails + "</font></pre>";
						} else {
							pnrBookDetails = "<font color='red'>"
									+ pnrBookDetails + "</font>";
						}
						gqDetails.put("XQ_" + (i + 1),
								StringUtils.trimToEmpty(pnrBookDetails));
						gqDetails.put("STATUS_" + (i + 1), pnrBookStatus);
					}
				}
				// XE原PNR
				else if (step.getId() == 4) {
					String pnrXeDetails = result.getPnrXeDetails();
					String pnrXeStatus = result.getPnrXeStatus();
					if (StringUtils.isNotBlank(pnrXeDetails)
							&& StringUtils.isNotBlank(pnrXeStatus)) {
						if (NumberUtils.toInt(pnrXeStatus) > 0) {
							pnrXeDetails = "<pre><font color='green'>"
									+ pnrXeDetails + "</font></pre>";
						} else {
							pnrXeDetails = "<font color='red'>" + pnrXeDetails
									+ "</font>";
						}
						gqDetails.put("XQ_" + (i + 1),
								StringUtils.trimToEmpty(pnrXeDetails));
						gqDetails.put("STATUS_" + (i + 1), pnrXeStatus);
					}
				}
				// XE原航段 或 预订新航段
				else if (step.getId() == 6) {
					String pnrOldDetails = result.getPnrOldDetails();
					String pnrOldStatus = result.getPnrOldStatus();
					if (StringUtils.isNotBlank(pnrOldDetails)
							&& StringUtils.isNotBlank(pnrOldStatus)) {
						if (NumberUtils.toInt(pnrOldStatus) > 0) {
							pnrOldDetails = "<pre><font color='green'>"
									+ pnrOldDetails + "</font></pre>";
						} else {
							pnrOldDetails = "<font color='red'>"
									+ pnrOldDetails + "</font>";
						}
						gqDetails.put("XQ_" + (i + 1),
								StringUtils.trimToEmpty(pnrOldDetails));
						gqDetails.put("STATUS_" + (i + 1), pnrOldStatus);
					}
				}
				// 分离原PNR
				else if (step.getId() == 7) {
					String pnrSpDetails = result.getPnrSpDetails();
					String pnrSpStatus = result.getPnrSpStatus();
					if (StringUtils.isNotBlank(pnrSpDetails)
							&& StringUtils.isNotBlank(pnrSpStatus)) {
						if (NumberUtils.toInt(pnrSpStatus) > 0) {
							pnrSpDetails = "<pre><font color='green'>"
									+ pnrSpDetails + "</font></pre>";
						} else {
							pnrSpDetails = "<font color='red'>" + pnrSpDetails
									+ "</font>";
						}
						gqDetails.put("XQ_" + (i + 1),
								StringUtils.trimToEmpty(pnrSpDetails));
						gqDetails.put("STATUS_" + (i + 1), pnrSpStatus);
						break;
					}
				}
				// TRDX指令
				else if (step.getId() == 2) {
					String trdxErrorInfo = result.getTrdxError();
					if (StringUtils.isNotBlank(trdxErrorInfo)) {
						trdxErrorInfo = "<font color='red'>" + trdxErrorInfo
								+ "</font>";
						gqDetails.put("XQ_" + (i + 1),
								StringUtils.trimToEmpty(trdxErrorInfo));
						gqDetails.put("STATUS_" + (i + 1), "-1");
						break;
					}
				} else {
					gqDetails.put("XQ_" + (i + 1), "");
				}

			}
		}
		return gqDetails;
	}

	/**
	 * 获取改签航段记录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getLsGqjl")
	public Map<String, Object> getLsGqjl(String jp_hdid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] jp_hdids = jp_hdid.split(",");
		Map<String, List<Map<String, Object>>> gqjlMap = null;
		try {
			gqjlMap = jpGqdDataServiceImpl.getJpGqdHdJlByJpHdid(jp_hdids);
			resultMap.put("STATUS", "0");
			resultMap.put("GQJL", gqjlMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("STATUS", "-1");
			resultMap.put("ERROR", e.getMessage());
		}
		return resultMap;
	}
}
