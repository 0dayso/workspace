package cn.vetech.vedsb.jp.service.jpgqgl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.jpgqgl.JpGqdDao;
import cn.vetech.vedsb.jp.entity.bbzx.Gqbb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdCjrBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdHdBean;
import cn.vetech.vedsb.jp.entity.jpgqgl.AsmsOrderSplitBean;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCjr;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgQueryServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class JpGqdServiceImpl extends MBaseService<JpGqd, JpGqdDao>{
	@Autowired
	private JpGqdCjrServiceImpl jpGqdCjrServiceImpl;
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private PkgQueryServiceImpl pkgQueryServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	/**
	 * 改签管理页面分页查询改签单和改签单明细
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page query(Map<String,Object> jpgqdMap) throws Exception {
		int pagenum = (Integer)jpgqdMap.get("pageNum");
		int pagesize = (Integer)jpgqdMap.get("pageSize");
		Page page = new Page(pagenum,pagesize);
		if (null == jpgqdMap.get("orderBy") || StringUtils.isBlank(jpgqdMap.get("orderBy").toString())) {
			jpgqdMap.put("orderBy", "ddsj desc");
		}
		List<Map<String,Object>> jpgqdList = this.getMyBatisDao().query(jpgqdMap);
		Shyhb user = SessionUtils.getShshbSession();
		/**
		 * 获取乘机人信息放到机票改签单对象里
		 */
		if(CollectionUtils.isNotEmpty(jpgqdList)){
			for(int i=0;i<jpgqdList.size();i++){
				Map<String,Object> param=jpgqdList.get(i);
				List<JpGqdCjr> cjrList = jpGqdCjrServiceImpl.getCjrListByGqdh(param.get("GQDH").toString(), user.getShbh());
				param.put("CJRLIST", cjrList);
			}
			attachService.wdzl("WDID").zfkm("SKKM").zfkm("GQ_CGKM").execute(jpgqdList);
		}
		/**
		 * 获取总条数
		 */
		int pagecount = this.getMyBatisDao().getPageCount(jpgqdMap);
		page.setList(jpgqdList);
		page.setTotalCount(pagecount);
		return page;
	}
	
	/**
	 * 查询改签报表数据
	 * @param jpgqdMap
	 * @return
	 * @throws Exception
	 */
	public Page queryForGqbb(Gqbb gqbb,int pageNum, int pageSize) throws Exception {
		Page page = new Page(pageNum, pageSize);
		try {
			String tjfs = gqbb.getTjfs();
			Map<String,Object> param = pkgQueryServiceImpl.gqReport(gqbb);
			
			List<Map<String,Object>> list = (List<Map<String,Object>>)param.get("list");
			page.setList(list);
			
			//明细查询
			if ("1".equals(tjfs) || "A".equals(tjfs)) {
				page.setTotalCount(NumberUtils.toInt((String)param.get("allCount")));
			} else {
				//统计
				page.setPageAllCount(-1);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return page;
		
	}
	
	/**
	 * 通过改签单号查询改签信息
	 */
	public JpGqd getJpGqdByGqdh(String gqdh, String shbh) {
		JpGqd jpGqd = new JpGqd();
		jpGqd.setGqdh(gqdh);
		jpGqd.setShbh(shbh);
		return this.getMyBatisDao().getJpGqdByGqdh(jpGqd);
	}
	
	/**
	 * 生成新订单
	 */
	public void newOrder(AsmsOrderSplitBean newOrder) {
		Shyhb user = SessionUtils.getShshbSession();
		JpDdBean jpdd = new JpDdBean();
		jpdd.setXs_pnr_no(newOrder.getNew_pnrno());
		jpdd.setXs_hkgs_pnr(newOrder.getBig_pnrno());
		jpdd.setXgly(newOrder.getCzFrom());
		List<JpDdCjrBean> cjrList = newOrder.getCjrList();
		List<JpDdHdBean> hdList = newOrder.getHdList();
		jpKhddServiceImpl.saveOrder(user.getShbh(), jpdd, cjrList, hdList, null);
	}
	
	/**
	 * 通过改签单号修改改签单信息
	 * @param gqdh
	 * @return
	 */
	public int updateJpGqdById(JpGqd jpgqd) {
		return this.getMyBatisDao().updateJpGqdById(jpgqd);
	}
	
	/**
	 * 历史数据调整列表查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getHistoryGqList(Map<String, Object> map){
		return this.getMyBatisDao().getHistoryGqList(map);
	}
	/**
	 * 历史数据调整根据条件查询
	 * @param map
	 * @return
	 */
	public Map<String, Object> getjpgqdobject(Map<String, Object> map){
		return this.getMyBatisDao().getjpgqdobject(map);
	}
	
	/**
	 * 修改改签单价格
	 * @param map
	 * @throws Exception
	 */
	public void saveGqdHistory(Map<String, Object> map) throws Exception{
		try {
			this.getMyBatisDao().saveGqdHistory(map);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	 /**
	  * 将改签报表的查询条件拼接为Xml
	  * @return
	  */
	public String paramToXml(Map<String,Object> jpgqdMap) {
		StringBuffer ticketChangeXml = new StringBuffer();
		Shyhb user = SessionUtils.getShshbSession();
		ticketChangeXml.append("<GQREPORT>");
		String yhbh = user.getBh();
		if (StringUtils.isNotBlank(yhbh)) {
			ticketChangeXml.append("<YHBH>").append(yhbh).append("</YHBH>");
		}
		String shbh = user.getShbh();
		if (StringUtils.isNotBlank(shbh)) {
			ticketChangeXml.append("<SHBH>").append(shbh).append("</SHBH>");
		}
		String shbmid = user.getShbmid();
		if (StringUtils.isNotBlank(shbmid)) {
			ticketChangeXml.append("<SHBMID>").append(shbmid).append("</SHBMID>");
		}
		String rqtj = jpgqdMap.get("rqtj") == null ? null : jpgqdMap.get("rqtj").toString();
		if (StringUtils.isNotBlank(rqtj)) {
			ticketChangeXml.append("<RQTJ>").append(rqtj).append("</RQTJ>");
		}
		String ksrq = jpgqdMap.get("ksrq") == null ? null : jpgqdMap.get("ksrq").toString();
		if (StringUtils.isNotBlank(ksrq)) {
			ticketChangeXml.append("<KSRQ>").append(ksrq).append("</KSRQ>");
		}
		String jsrq = jpgqdMap.get("jsrq") == null ? null : jpgqdMap.get("jsrq").toString();
		if (StringUtils.isNotBlank(jsrq)) {
			ticketChangeXml.append("<JSRQ>").append(jsrq).append("</JSRQ>");
		}
		String gngj = jpgqdMap.get("gngj") == null ? null : jpgqdMap.get("gngj").toString();
		if (StringUtils.isNotBlank(gngj)) {
			ticketChangeXml.append("<GNGJ>").append(gngj).append("</GNGJ>");
		}
		String ddbm = jpgqdMap.get("ddbm") == null ? null : jpgqdMap.get("ddbm").toString();
		if (StringUtils.isNotBlank(ddbm)) {
			ticketChangeXml.append("<DDBM>").append(ddbm).append("</DDBM>");
		}
		String ddyh = jpgqdMap.get("ddyh") == null ? null : jpgqdMap.get("ddyh").toString();
		if (StringUtils.isNotBlank(ddyh)) {
			ticketChangeXml.append("<DDYH>").append(ddyh).append("</DDYH>");
		}
		String gq_blr = jpgqdMap.get("gq_blr") == null ? null : jpgqdMap.get("gq_blr").toString();
		if (StringUtils.isNotBlank(gq_blr)) {
			ticketChangeXml.append("<GQ_BLR>").append(gq_blr).append("</GQ_BLR>");
		}
		String gqdh = jpgqdMap.get("gqdh") == null ? null : jpgqdMap.get("gqdh").toString();
		if (StringUtils.isNotBlank(gqdh)) {
			ticketChangeXml.append("<GQDH>").append(gqdh).append("</GQDH>");
		}
		String tkno = jpgqdMap.get("tkno") == null ? null : jpgqdMap.get("tkno").toString();
		if (StringUtils.isNotBlank(tkno)) {
			ticketChangeXml.append("<TKNO>").append(tkno).append("</GQDH>");
		}
		String hc = jpgqdMap.get("hc") == null ? null : jpgqdMap.get("hc").toString();
		if (StringUtils.isNotBlank(hc)) {
			ticketChangeXml.append("<HC>").append(hc.trim()).append("</HC>");
		}
		String xsHbh = jpgqdMap.get("xsHbh") == null ? null : jpgqdMap.get("xsHbh").toString();
		if (StringUtils.isNotBlank(xsHbh)) {
			ticketChangeXml.append("<XS_HBH>").append(xsHbh.trim()).append("</XS_HBH>");
		}
		String xsPnrNo = jpgqdMap.get("xsPnrNo") == null ? null : jpgqdMap.get("xsPnrNo").toString();
		if (StringUtils.isNotBlank(xsPnrNo)) {
			ticketChangeXml.append("<XS_PNR_NO>").append(xsPnrNo.trim()).append("</XS_PNR_NO>");
		}
		String cjr = jpgqdMap.get("cjr") == null ? null : jpgqdMap.get("cjr").toString();
		if (StringUtils.isNotBlank(cjr)) {
			ticketChangeXml.append("<CJR>").append(cjr.trim()).append("</CJR>");
		}
		String gqzt = jpgqdMap.get("gqzt") == null ? null : jpgqdMap.get("gqzt").toString();
		if (StringUtils.isNotBlank(gqzt)) {
			ticketChangeXml.append("<GQZT>").append(gqzt).append("</GQZT>");
		}
		String tjfs = jpgqdMap.get("tjfs") == null ? null : jpgqdMap.get("tjfs").toString();
		if (StringUtils.isNotBlank(tjfs)) {
			ticketChangeXml.append("<TJFS>").append(tjfs).append("</TJFS>");
		}
		String gqlx = jpgqdMap.get("gqlx") == null ? null : jpgqdMap.get("gqlx").toString();
		if (StringUtils.isNotBlank(gqlx)) {
			ticketChangeXml.append("<GQLX>").append(gqlx).append("</GQLX>");
		}
		String gq_cgly = jpgqdMap.get("gqCgly") == null ? null : jpgqdMap.get("gqCgly").toString();
		if (StringUtils.isNotBlank(gq_cgly)) {
			ticketChangeXml.append("<GQ_CGLY>").append(gq_cgly).append("</GQ_CGLY>");
		}
		String gq_cgkm = jpgqdMap.get("gq_cgkm") == null ? null : jpgqdMap.get("gq_cgkm").toString();
		if (StringUtils.isNotBlank(gq_cgkm)) {
			ticketChangeXml.append("<GQ_CGKM>").append(gq_cgkm).append("</GQ_CGKM>");
		}
		String hkgs = jpgqdMap.get("hkgs") == null ? null : jpgqdMap.get("hkgs").toString();
		if (StringUtils.isNotBlank(hkgs)) {
			ticketChangeXml.append("<HKGS>").append(hkgs).append("</HKGS>");
		}
		String gq_cgdw = jpgqdMap.get("gq_cgdw") == null ? null : jpgqdMap.get("gq_cgdw").toString();
		if (StringUtils.isNotBlank(gq_cgdw)) {
			ticketChangeXml.append("<GQ_CGDW>").append(gq_cgdw).append("</GQ_CGDW>");
		}
		String orderby = jpgqdMap.get("orderby") == null ? null : jpgqdMap.get("orderby").toString();
		if (StringUtils.isNotBlank(orderby)) {
			ticketChangeXml.append("<ORDERBY>").append(orderby).append("</ORDERBY>");
		}
		String start = jpgqdMap.get("start") == null ? null : jpgqdMap.get("start").toString();
		if (StringUtils.isNotBlank(start)) {
			ticketChangeXml.append("<MX_START>").append(start).append("</MX_START>");
		}
		String count = jpgqdMap.get("count") == null ? null : jpgqdMap.get("count").toString();
		if (StringUtils.isNotBlank(count)) {
			ticketChangeXml.append("<MX_COUNT>").append(count).append("</MX_COUNT>");
		}
		String wdid = jpgqdMap.get("wdid") == null ? null : jpgqdMap.get("wdid").toString();
		if (StringUtils.isNotBlank(wdid)) {
			ticketChangeXml.append("<WDID>").append(wdid).append("</WDID>");
		}
		String wd_zcdm = jpgqdMap.get("wdZcdm") == null ? null : jpgqdMap.get("wdZcdm").toString();
		if (StringUtils.isNotBlank(wd_zcdm)) {
			ticketChangeXml.append("<WD_ZCDM>").append(wd_zcdm.trim()).append("</WD_ZCDM>");
		}
		ticketChangeXml.append("</GQREPORT>");
		return ticketChangeXml.toString();
	}
	public List<JpGqd> checkGqdByWbdh(Map<String,Object> map){
		return this.getMyBatisDao().checkGqdByWbdh(map);
	}
	public List<Map<String, String>> getTknoByGqTkno(Map<String,Object> map){
		return this.getMyBatisDao().getTknoByGqTkno(map);
	}
}
