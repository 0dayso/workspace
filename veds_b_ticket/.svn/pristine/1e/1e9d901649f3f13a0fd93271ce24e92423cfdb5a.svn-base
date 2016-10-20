package cn.vetech.web.vedsb.jpzwgl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.vetech.core.modules.excel.ExcelReaderUtil;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.ServiceException;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsq;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqDr;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqDrImport;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqDrServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqServiceImpl;
import cn.vetech.vedsb.specialticket.request.KwCancelRequest;
import cn.vetech.vedsb.specialticket.response.KwCancelResponse;
import cn.vetech.vedsb.specialticket.response.KwResponse;
import cn.vetech.vedsb.specialticket.service.KwService;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpTjsqDrController extends MBaseControl<JpTjsqDr, JpTjsqDrServiceImpl>{
	
	@Autowired
	private JpTjsqDrServiceImpl jpTjsqDrServiceImpl;
	
	@Autowired
	private JpTjsqServiceImpl jpTjsqServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	KwService kwService;//请求追位系统service
	
	@Override
	public void insertInitEntity(JpTjsqDr t) {
	}

	@Override
	public void updateInitEntity(JpTjsqDr t) {
	}
	
	public void selectInitEntity(Map param){
		String rqlx = (String)param.get("search_EQ_rqlx");
		String kssj = (String)param.get("search_EQ_kssj");
		String jssj = (String)param.get("search_EQ_jssj");
		if("1".equals(rqlx)){
			param.put("search_GTdate_drsj",kssj);
			param.put("search_LTdate_drsj",jssj);
		}else if("2".equals(rqlx)){
			param.put("search_GTdate_sq_Datetime",kssj);
			param.put("search_LTdate_sq_Datetime",jssj);
		}else if("3".equals(rqlx)){
			param.put("search_GT_pnr_Cfrqsj",kssj);
			param.put("search_LT_pnr_Cfrqsj",jssj);
		}
		param.remove("search_EQ_rqlx");
		param.remove("search_EQ_kssj");
		param.remove("search_EQ_jssj");
		Shyhb yhb = SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", yhb.getShbh());
	}
	
	@Override
	public void pageAfter(Page page){
		Shyhb yhb = SessionUtils.getShshbSession();
		List list = page.getList();
		attachService.shyhb("sqUserid",yhb.getShbh()).execute(list);
	}
	/**
	 * 取消追位订单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String qxZwdd(String id){
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			//JpTjsqDr jp =new JpTjsqDr();
			//jp.setSqdh(id);
			//jp.setShbh(yhb.getShbh());
			String shbh = yhb.getShbh();
			JpTjsqDr jptjsqdr = this.baseService.getjptjsqdr(shbh, id);//根据申请单号返回追位订单对象
			Boolean flag = true;//当点"消"时刚好追到位,不能 取消
			if(null != jptjsqdr &&(DictEnum.ZWDDDRZT.BFZ.getValue().equals(jptjsqdr.getSqZt()) || DictEnum.ZWDDDRZT.ZWC.getValue().equals(jptjsqdr.getSqZt()))){//成功
				flag = false;
				return "2";//取消失败
			}
			if(flag){
				KwCancelRequest request=new KwCancelRequest();
				request.setSqdh(id);
				KwCancelResponse response=kwService.cancelOrder(shbh, request);
				if(KwResponse.FAILL.equals(response)){
					return "3";//通知追位系统取消追位单失败，请联系管理员
				}else{
					JpTjsq tjsq = this.jpTjsqServiceImpl.gettjsq(shbh, id);//根据申请单号获取追位订单
					tjsq.setSqZt("6");
					this.jpTjsqServiceImpl.update(tjsq);
					jptjsqdr.setClDatetime(new Date());
					jptjsqdr.setClUserid(yhb.getBh());
					jptjsqdr.setClDeptid(yhb.getShbmid());
					jptjsqdr.setSqZt("6");//管理员消
					this.baseService.update(jptjsqdr);
					return "1";
				}
			}
		} catch (Exception e) {
			logger.error("取消追位订单失败，规则id为 "+id, e);
			return "0";
		}
		return null;
	}
	
	/**
	 * 批量修改追位订单
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String batchQxZwdd(String ids){
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			String shbh = yhb.getShbh();
			String[] id = ids.split(",");
			for(int i=0;i<ids.length();i++){
//				JpTjsqDr jp =new JpTjsqDr();
//				jp.setSqdh(id[i]);
//				jp.setShbh(yhb.getShbh());
				JpTjsqDr jptjsqdr = this.baseService.getjptjsqdr(shbh, id[i]);
				if(null != jptjsqdr &&(DictEnum.ZWDDDRZT.BFZ.getValue().equals(jptjsqdr.getSqZt()) || DictEnum.ZWDDDRZT.ZWC.getValue().equals(jptjsqdr.getSqZt()))){//成功
					return "2";
				}
				JpTjsq tjsq = this.jpTjsqServiceImpl.gettjsq(shbh, id[i]);//根据申请单号获取追位订单
				if(null== tjsq){
					return "2";
				}else{
					KwCancelRequest request=new KwCancelRequest();
					request.setSqdh(id[i]);
					KwCancelResponse response=kwService.cancelOrder(shbh, request);
					if(KwResponse.FAILL.equals(response)){
						return "3";//通知追位系统取消追位单失败，请联系管理员
					}else{
						tjsq.setSqZt("6");
						this.jpTjsqServiceImpl.update(tjsq);
						jptjsqdr.setClDatetime(new Date());
						jptjsqdr.setClUserid(yhb.getBh());
						jptjsqdr.setClDeptid(yhb.getShbmid());
						jptjsqdr.setSqZt("6");//管理员消
						this.baseService.update(jptjsqdr);
						return "1";
					}
				}
			}
		} catch (Exception e) {
			logger.error("批量取消追位订单失败", e);
			return "0";
		}
		return null;
	}
	/**
	 * 根据查询条件批量取消追位订单
	 * @param jptjsqdr
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String batchAllQxZwdd(JpTjsqDr jptjsqdr){
		try {
			boolean b = this.jpTjsqDrServiceImpl.batchAllUpdate(jptjsqdr);
			if(b){
				return "1";
			}else{
				return "0";
			}
		} catch (Exception e) {
			logger.error("批量取消所有追位订单失败", e);
			return "0";
		}
	}
	
	@RequestMapping
	public @ResponseBody Page zwSuccPagelist(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
		@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
		@RequestParam(value = "sortType", defaultValue = "desc") String sortType,HttpServletRequest request){
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "",true);
			String ksrq = request.getParameter("ksrq");
			String jsrq = request.getParameter("jsrq");
			String rqlx = request.getParameter("rqlx");
			searchParams.put("start", pageNum);
			searchParams.put("count", pageSize);
			if("1".equals(rqlx)){
				searchParams.put("dpdatetimeksrq", ksrq);//追位日期
				searchParams.put("dpdatetimejsrq", jsrq);
			}else if("2".equals(rqlx)){
				searchParams.put("sqdatetimeksrq", ksrq);//申请日期
				searchParams.put("sqdatetimejsrq", jsrq);
			}else if("3".equals(rqlx)){
				searchParams.put("qftimeksrq", ksrq);//起飞日期
				searchParams.put("qftimejsrq", jsrq);
			}
			String cpdatetime= request.getParameter("cpdatetime");
			if(StringUtils.isNotBlank(cpdatetime)){
				searchParams.put("nowtime", VeDate.getStringDateShort());//取当前时间
			}
			Shyhb yhb = SessionUtils.getShshbSession();
			searchParams.put("shbh", yhb.getShbh());
			Page page = this.jpTjsqServiceImpl.zwQueryPage(searchParams, pageNum, pageSize);
			pageAfter(page);
			return page;
	}
	
	@ResponseBody
	@RequestMapping
	public String bjZw(String id){
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			JpTjsq jp =new JpTjsq();
			jp.setSqdh(id);
			jp.setShbh(yhb.getShbh());
			JpTjsq jptjsq = this.jpTjsqServiceImpl.getEntityById(jp);//根据id返回追位订单对象
			Boolean flag = true;//当点"消"时刚好追到位,不能 取消
			if(null!=jptjsq &&("2".equals(jptjsq.getSqZt()) || "3".equals(jptjsq.getSqZt()))){//成功
				flag = false;
				return "2";//取消失败
			}
			if(flag){
				jptjsq.setClZt("1");
				jptjsq.setXguserid(yhb.getBh());
				jptjsq.setXgdatetime(new Date());
				this.jpTjsqServiceImpl.update(jptjsq);
			}else{
				return "0";
			}
		} catch (Exception e) {
			logger.error("标记追位订单失败", e);
			return "0";
		}
		return "1";
	}
	/**
	 * 导入EXCel追位订单
	 * @param model
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public String importJpTjsqDr(ModelMap model,
			@RequestParam(value="file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		if(file.isEmpty()){
			request.setAttribute("CODE", "-1");
			request.setAttribute("ERROR", "不能上传空文件!");
			return viewpath("import");
		}
		Shyhb user = SessionUtils.getShshbSession();
		if (user == null) {
			request.setAttribute("CODE", "-1");
			request.setAttribute("ERROR", "无权上传");
			return  viewpath("import");
		}
		try{
			JpTjsqDrImport JpTjsqDrReader = new JpTjsqDrImport(jpTjsqDrServiceImpl,sqlSession);
			JpTjsqDrReader.setShbh(user.getShbh());
			JpTjsqDrReader.setCjyh(user.getBh());
			JpTjsqDrReader.setUserid(user.getBh());
			JpTjsqDrReader.setDeptid(user.getShbmid());
			String fileName = file.getOriginalFilename();
			String tempId = VeDate.getNo(4);	
			String path ="updownFiles"+File.separator+"tmpfiles"+File.separator + tempId + File.separator;
			path += fileName;
			File file2 = new File(path);
			FileUtils.writeByteArrayToFile(file2, file.getBytes());
			file.transferTo(file2);
			
			ExcelReaderUtil.readExcel(JpTjsqDrReader, 1,path, JpTjsqDr.class);
			
			Integer failCount = JpTjsqDrReader.getFailCount();
			Integer succCount = JpTjsqDrReader.getSuccCount();
			
			String sbwjpath = "";
			if(failCount>0){
				List<Map> drsbList = JpTjsqDrReader.getDrsbList();
				sbwjpath = generateFailExcel(drsbList,request);
			}
			if(failCount<1){
				//全部导入成功
				request.setAttribute("CODE","1");
			}else{
				String mes = "总共有 "+(failCount+succCount)+"条 数据，成功导入条数："+succCount+", 失败条数："+failCount+"</br>导入失败数据下载<a href='"+File.separator+sbwjpath+"'>import_fail_zwdd.xls</a>";
				if(succCount>0){
					//部分导入成功
					request.setAttribute("CODE","0");
				}else{
					//全部导入失败
					request.setAttribute("CODE","-1");
				}
				request.setAttribute("ERROR", mes);
			}
		}catch(Exception e){
			request.setAttribute("CODE","-1");
			request.setAttribute("ERROR", e.getMessage());
			e.printStackTrace();
		}
		return viewpath("import");
	}
	
	private String generateFailExcel(List<Map> drsbList,HttpServletRequest request) {
		String contentPath = request.getSession().getServletContext().getRealPath("/");
		String sourceFilePath = contentPath + "updownFiles" + File.separator + "mb" + File.separator
				+ "jp_tjsqdr_import.xls";
		File srcFile = new File(sourceFilePath);
		String resPath = "updownFiles" + File.separator + "tmpfiles" + File.separator + VeDate.getNo(4);
		String destFilePath = contentPath + resPath;
		String destFilePathName = destFilePath + File.separator + "import_fail.xls";
		File destpathFile = new File(destFilePath);
		File destpathNameFile = new File(destFilePathName);
		try {
			if (!destpathFile.exists()) {
				destpathFile.mkdirs();
			}
			if (!destpathNameFile.exists()) {
				destpathNameFile.createNewFile();
			}
			FileUtils.copyFile(srcFile, destpathNameFile);
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(srcFile));
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = null;
			HSSFCell cell = null;
			int rownum = 2; // 添加的起始行
			HSSFRow headRow = sheet.getRow(0);
			for (int i = 0; i < drsbList.size(); i++) {
				row = sheet.createRow(rownum);
				for (int j = 0; j < headRow.getLastCellNum(); j++) {
					HSSFCell btCell=headRow.getCell(j);
					String bt=btCell.getStringCellValue();
					if(StringUtils.isNotBlank(bt)){
						myCreateCell(j, drsbList.get(i).get(bt), row, cell);
					}
				}
				myCreateCell(headRow.getLastCellNum(), drsbList.get(i).get("errMsg"), row, cell);
				rownum++;
			}
			FileOutputStream os = new FileOutputStream(destpathNameFile);
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("生成导入失败数据的 excel文件时报错！");
		}
		return resPath + File.separator+"import_fail.xls";
	}
	
	private void myCreateCell(int cellnum, Object value, HSSFRow row, HSSFCell cell) {
	    if(value==null){
	    	cell=row.createCell(cellnum,HSSFCell.CELL_TYPE_BLANK);
	    	cell.setCellValue("");  
	    }else{
			if(NumberUtils.isNumber(String.valueOf(value))){
				cell=row.createCell(cellnum,HSSFCell.CELL_TYPE_NUMERIC);
		    	cell.setCellValue(NumberUtils.toFloat(String.valueOf(value))); 
			}else {
				cell=row.createCell(cellnum,HSSFCell.CELL_TYPE_STRING);
		    	cell.setCellValue(String.valueOf(value)); 
			}
		}
	}
	
	/**
	 * 打开导入页面
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String toImport(ModelMap model) {
		return viewpath("import");
	}
}
