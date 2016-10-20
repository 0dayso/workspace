package cn.vetech.vedsb.jp.service.cgdzbb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vetech.core.modules.dao.BatchMapperUtils;
import org.vetech.core.modules.excel.ExcelReaderUtil;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.cgdzbb.JpCgdzMxDao;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdz;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdzMx;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpDzsz;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.utils.bankdb.BankDbUtil;
import cn.vetech.web.vedsb.jpcwgl.BankImport;

/**
 * 采购对账明细
 * 
 * @author houya
 *
 */
@Service
public class JpCgdzMxServiceImpl extends MBaseService<JpCgdzMx, JpCgdzMxDao> {
	@Autowired
	private JpCgdzServiceImpl jpCgdzServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	private static Logger logger = LoggerFactory.getLogger(JpCgdzMxServiceImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSessionJp;

	public int insertAll(final Shyhb shyhb, final JpCgdz jpCgdz, List<JpCgdzMx> jpCgdzMxList) {
		final Map<String, String> map = getCgdzMxByMap(shyhb.getShbh(), jpCgdz.getId());
		new BatchMapperUtils<JpCgdzMxDao>(sqlSessionJp, JpCgdzMxDao.class) {
			@Override
			public void exe(JpCgdzMxDao dao, Object o) {
				JpCgdzMx jpCgdzMx = (JpCgdzMx) o;
				if (StringUtils.isBlank(jpCgdzMx.getId())) {
					// 读取序列
					long id = dao.seq_jp_cgdd_mx_id();
					jpCgdzMx.setId(id + "");
				}
				jpCgdzMx.setZbid(jpCgdz.getId());
				jpCgdzMx.setDrsj(new Date());
				jpCgdzMx.setShbh(shyhb.getShbh());
				jpCgdzMx.setDrr(shyhb.getBh());
				if (StringUtils.isBlank(jpCgdzMx.getJglx())) {
					jpCgdzMx.setJglx("0");
				}

				String pnrno = StringUtils.trimToEmpty(jpCgdzMx.getPnrno());
				String bigpnrno = StringUtils.trimToEmpty(jpCgdzMx.getBigpnrno());
				String tkno = StringUtils.trimToEmpty(jpCgdzMx.getTkno());
				String bdfs = StringUtils.trimToEmpty(jpCgdzMx.getBdfs());
				String cgDdbh = StringUtils.trimToEmpty(jpCgdzMx.getCgDdbh());

				// zbid相同，票号相同的 先删除，然后导入
				String key01 = pnrno + bigpnrno + tkno + bdfs + cgDdbh;
				String dbid = map.get(key01);
				if (StringUtils.isNotBlank(dbid)) {
					dao.deleteByPrimaryKey(dbid);
				}
				dao.insert(jpCgdzMx);
				map.put(key01, jpCgdzMx.getId());
			}
		}.batchInsert(jpCgdzMxList, 100);

		return 1;
	}

	/**
	 * 通过zbid删除数据
	 * 
	 * @param shyhb
	 * @param jpCgdz
	 */
	public void deleteByZbid(Shyhb shyhb, JpCgdz jpCgdz) {
		this.getMyBatisDao().deleteByZbid(shyhb.getShbh(), jpCgdz.getId());
		JpCgdz upJpCgdz = new JpCgdz();
		upJpCgdz.setId(jpCgdz.getId());
		upJpCgdz.setDzZt("0");
		upJpCgdz.setShZt("0");
		try {
			jpCgdzServiceImpl.updateSelective(upJpCgdz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 存储关键数据用于判断重复
	 */
	private Map<String, String> getCgdzMxByMap(String shbh, String zbid) {
		Map<String, String> map1 = new HashMap<String, String>();
		List<Map<String, String>> list = this.getMyBatisDao().getCgdzMxByZbid(shbh, zbid);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Map<String, String> m : list) {
				String id = StringUtils.trimToEmpty(m.get("ID"));
				String pnrno = StringUtils.trimToEmpty(m.get("PNRNO"));
				String bigpnrno = StringUtils.trimToEmpty(m.get("BIGPNRNO"));
				String tkno = StringUtils.trimToEmpty(m.get("TKNO"));
				String bdfs = StringUtils.trimToEmpty(m.get("BDFS"));
				String cgDdbh = StringUtils.trimToEmpty(m.get("CGDDBH"));
				String key01 = pnrno + bigpnrno + tkno + bdfs + cgDdbh;
				map1.put(key01, id);
			}
		}
		return map1;
	}

	/**
	 * 获取标签数字
	 * 
	 * @param shbh
	 * @param zbid
	 * @return
	 */
	public Map<String, Object> getCgdzMxGroup(String shbh, String zbid) {
		List<Map<String, Object>> list = this.getMyBatisDao().getCgdzMxGroup(shbh, zbid);
		Map<String, Object> mnew = new HashMap<String, Object>();
		int all = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> m = list.get(i);
				String jglx = (String) m.get("JGLX");
				Object count = m.get("COUNT");
				if (count != null) {
					all += NumberUtils.toInt(count.toString());
				}
				mnew.put(jglx, count);
			}
		}
		mnew.put("all", all);//总数
		return mnew;
	}

	/**
	 * 采购对账
	 * 
	 * @param p_shbh
	 * @param p_dzrq
	 * @param p_zbid
	 * @param p_dzr
	 * @throws Exception
	 */
	public void proc_cgdz_zddb(String p_shbh, String p_dzrq, String p_zbid, String p_dzr) throws Exception {
		procedureServiceImpl.proc_cgdz_zddb(p_shbh, p_dzrq, p_zbid, p_dzr);
	}

	/**
	 * 解析直通车数据
	 * 
	 * @param file
	 *            上传的文件
	 * @param jpDzsz
	 *            对账设置
	 * @param path
	 *            保存的路径
	 * @return 返回数据
	 * @throws Exception
	 */
	public List<JpCgdzMx> ztcDz(MultipartFile file, JpDzsz jpDzsz, String path) throws Exception {
		String fileName = VeDate.getNo(4) + file.getOriginalFilename();
		if (!fileName.endsWith(ExcelReaderUtil.EXCEL03_EXTENSION) && !fileName.endsWith(ExcelReaderUtil.EXCEL07_EXTENSION)) {
			String e = "上传的文件" + file.getOriginalFilename() + "不合法";
			logger.error(e);
			throw new Exception(e);
		}
		String absfile = path + fileName;
		File file2 = new File(absfile);
		FileUtils.writeByteArrayToFile(file2, file.getBytes());
		BankImport bankImport = new BankImport(jpDzsz.getQsh() - 1);
		ExcelReaderUtil.readExcel(bankImport, jpDzsz.getQsh() - 1, jpDzsz.getQsh() - 1, absfile, null);
		List<Map<String, Object>> dataList = bankImport.getBankList();
		List<JpCgdzMx> rtnList = new ArrayList<JpCgdzMx>();
		for (int i = 0; i < dataList.size(); i++) {
			JpCgdzMx j = new JpCgdzMx();
			Map<String, Object> m = dataList.get(i);
			String tkno = BankDbUtil.objToString(m.get("票号"));
			if (StringUtils.length(tkno) < 10) {
				logger.error("导入直通车数据票号" + tkno + "不合法");
				continue;
			}
			// RFND TKTT CANX
			String kplx = BankDbUtil.objToString(m.get("客票\n类型"));
			String ddlx = "0";
			if ("RFND".equals(kplx)) {
				ddlx = "2";
			} else if ("TKTT".equals(kplx)) {
				ddlx = "1";
			} else {
				ddlx = "0";
				j.setJglx("9");
			}
			j.setDdlx(ddlx);
			j.setDrly("直通车");

			String cprq = BankDbUtil.objToString(m.get("出票\n日期"));
			j.setYwrq(VeDate.strToDate(cprq));
			String pnrno = BankDbUtil.objToString(m.get("PNR"));
			j.setPnrno(pnrno);

			j.setTkno(tkno);
			j.setBdfs("1");
			Double cgPmj = BankDbUtil.obj2Double(m.get("实收价\n(SCNY)"));
			cgPmj = cgPmj == null ? 0 : cgPmj;
			j.setCgPmj(cgPmj);

			Double cgJsf = BankDbUtil.obj2Double(m.get("税总和"));
			cgJsf = cgJsf == null ? 0 : cgJsf;
			j.setCgJsf(cgJsf);

			Double cgTax = 0.0;
			j.setCgTax(cgTax);

			Double cgXj = BankDbUtil.obj2Double(m.get("应收款\n(ACNY)"));
			cgXj = cgXj == null ? 0 : cgXj;
			j.setCgXj(cgXj);

			Double zdlf = BankDbUtil.obj2Double(m.get("总代\n理费"));
			zdlf = zdlf == null ? 0 : zdlf;
			j.setCgDlf(zdlf);

			j.setCgCgj(j.getCgPmj() - zdlf);
			j.setCgSxf(0.0);

			Double cgTpf = BankDbUtil.obj2Double(m.get("退票费"));
			cgTpf = cgTpf == null ? 0 : cgTpf;
			j.setCgTpf(cgTpf);

			// 退票费率
			if (j.getCgPmj() != null && j.getCgPmj() != 0) {
				double cgtpfl = Arith.div(Math.abs(cgTpf), Math.abs(j.getCgPmj()), 2);
				j.setCgTpfl(cgtpfl);
			}

			Double cgSfje = BankDbUtil.obj2Double(m.get("应上\n缴款"));
			cgSfje = cgSfje == null ? 0 : cgSfje;
			j.setCgSfje(cgSfje);

			String lkxm = BankDbUtil.objToString(m.get("旅客\n姓名"));
			String hc = BankDbUtil.objToString(m.get("全航程"));
			String office = BankDbUtil.objToString(m.get("出票\noffice"));
			String bop = BankDbUtil.objToString(m.get("BOP\n标识"));

			String cgZfspmc = kplx + ";" + bop + ";" + office + ";" + lkxm + ";" + hc;
			j.setCgZfspmc(cgZfspmc);
			rtnList.add(j);
		}
		return rtnList;
	}

	/**
	 * 德付通转
	 * 
	 * @param file
	 * @return
	 */
	public List<JpCgdzMx> dftDz(MultipartFile file) {
		InputStream in = null;
		try {
			in = file.getInputStream();
			List<Map<String, String>> list = parseCsv(in);

			for (int i = 0; i < list.size(); i++) {
				JpCgdzMx j = new JpCgdzMx();
				Map<String, String> m = list.get(i);
				// j.setDdlx(ddlx);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private List<Map<String, String>> parseCsv(InputStream in) throws Exception {
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		List<String> list = IOUtils.readLines(in, Charsets.toCharset("GBK"));
		if (list == null || list.size() < 2) {
			return null;
		}
		int headrow = 2;
		int datarow = 3;
		String split = ",";
		String[] head = null;

		for (int i = 0; i < list.size(); i++) {
			String[] row = StringUtils.trimToEmpty(list.get(i)).split(split);
			for (int p = 0; i < row.length; p++) {
				// 去空格
				row[p] = StringUtils.trimToEmpty(row[p]);
			}
			if (i == headrow - 1) {
				head = row;
				continue;
			}
			if (i < datarow - 1) {
				continue;
			}

			Map<String, String> nmap = new HashMap<String, String>();
			int rcount = row.length;
			for (int p = 0; i < head.length; p++) {
				String v = "";
				if (rcount > p) {
					v = row[p];
				}
				nmap.put(head[p], v);
			}
			listmap.add(nmap);
		}

		return listmap;
	}

	public static void main(String[] args) throws Exception {
		BankImport bankImport = new BankImport(2);
		String absfile = "e:/阿里/SHA384_BSP_国内日报_20160818 - 副本.xls";
		ExcelReaderUtil.readExcel(bankImport, 2, 2, absfile, null);
		List<Map<String, Object>> dataList = bankImport.getBankList();
		System.out.println(dataList);
		System.out.println(NumberUtils.toDouble("14,021.00".replace(",", "")));
	}
}
