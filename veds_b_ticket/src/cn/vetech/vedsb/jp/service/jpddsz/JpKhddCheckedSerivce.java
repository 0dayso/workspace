package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.utils.LogUtil;

@Service
public class JpKhddCheckedSerivce {
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;

	
	/**
	 * 判断订单重复用Map，正常流程用
	 * 
	 */
	private static Map<String, Map<String, String>> validateMap = new HashMap<String, Map<String, String>>();

	private static String nextDate;// 下次清空validateMap时间
	
	public List<OrderBean> execute(String wbdh,List<OrderBean> list,JpDdsz jpDdsz, JpPtLog ptlb){
		String shbh = jpDdsz.getShbh();
		List<OrderBean> newList = new ArrayList<OrderBean>();
		if(CollectionUtils.isEmpty(list)){
			return newList;
		}
		String sfwbcpz = StringUtils.trimToEmpty(list.get(0).getKhddMap().get("SFWBCPZ"));//淘宝派单标识
		if(StringUtils.isBlank(sfwbcpz)){//正常重复判断流程
			newList.addAll(zcdHandle(list, wbdh, shbh,jpDdsz,ptlb));
		}else{//派单业务流程
			newList.addAll(pdHandle(list, wbdh, shbh,ptlb));
		}
		return newList;
	}
	public List<OrderBean> zcdHandle(List<OrderBean> list,String wbdh,String shbh,JpDdsz jpDdsz,JpPtLog ptlb){
		Iterator<OrderBean> iterator = list.iterator();
		while(iterator.hasNext()){
			OrderBean orderBean = iterator.next();
			String wbddzt = StringUtils.trimToEmpty(orderBean.getKhddMap().get("WBDDZT"));
			String ddxh = StringUtils.trimToEmpty(orderBean.getKhddMap().get("DDXH"));
			String cpdh = StringUtils.trimToEmpty(orderBean.getKhddMap().get("CPDH"));
			String wdid = jpDdsz.getWdid();
			String nowtime = VeDate.getStringDate();
			String wbddzt_ddxh = wbddzt+"_"+ddxh;
			Map<String, String> tmpMap = validateMap.get(wdid);// 一个网店平台一个Map,用于订单重复判断验证的。
			if (tmpMap == null) {
				tmpMap = new HashMap<String, String>();
				validateMap.put(wdid, tmpMap);
				nextDate = VeDate.getPreMin(nowtime, 10);
			} else {
				if (StringUtils.isBlank(nextDate) || VeDate.getTwoMin(nowtime, nextDate) >= 0) {
					// 清空validateMap并保存下次清空时间
					tmpMap.clear();
					nextDate = VeDate.getPreMin(nowtime, 10);
				}
			}
			String ddbh_wbddzt_ddxh = tmpMap.get(wbdh+"_"+cpdh+"_"+ddxh);
			if(StringUtils.isNotBlank(ddbh_wbddzt_ddxh)&&("_".equals(wbddzt_ddxh)||ddbh_wbddzt_ddxh.endsWith(wbddzt_ddxh))){//表示判断重复
				String xt_ddbh = ddbh_wbddzt_ddxh.split("_",-1)[0];
				String e = "过滤订单【"+wbdh+"】,重复订单导入，系统订单编号是【"+xt_ddbh+"】";
				ptlb.add(e);
				LogUtil.getGyrz("GLDD").error(e);
				iterator.remove();
				continue;
			}else{
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("wbdh", wbdh);
				param.put("shbh", shbh);
				param.put("ddxh", ddxh);
				param.put("cpdh", cpdh);
				try {
					List<JpKhdd> khddList = null;
					try {
						khddList = jpKhddServiceImpl.selectJpByWbdh(param);
					} catch (Exception e) {
						e.printStackTrace();
						ptlb.add("执行订单重复判断验证SQL报错,本次视为重复入库:"+e.getMessage());
						iterator.remove();
						continue;
					}
					if (list == null || list.isEmpty()) {
						ddbh_wbddzt_ddxh = "";
					}else{
						JpKhdd jpKhdd = khddList.get(0);
						ddbh_wbddzt_ddxh = khddList.get(0).getDdbh()+"_"+jpKhdd.getWbddzt()+"_"+jpKhdd.getDdxh();
					}
					tmpMap.put(wbdh+"_"+cpdh+"_"+ddxh, ddbh_wbddzt_ddxh);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			ptlb.add("外部单号 " + wbdh + "商户" + shbh + "判断重复，从数据库获取的订单和状态是" + ddbh_wbddzt_ddxh);
			if (StringUtils.isBlank(ddbh_wbddzt_ddxh)) {// 表示判断不重复
				continue;
			}
			String xt_ddbh = ddbh_wbddzt_ddxh.split("_", -1)[0];
			if (("_".equals(wbddzt_ddxh)||ddbh_wbddzt_ddxh.endsWith(wbddzt_ddxh))) {
				String e="过滤订单【" + wbdh + "】,重复订单导入，系统订单编号是【" + xt_ddbh + "】";
				ptlb.add(e);
				LogUtil.getGyrz("GLDD").error(e);
				iterator.remove();
				continue;
			} else {// 判断不重复，但是不能执行自动出票
				orderBean.getKhddMap().put("DDBH", xt_ddbh);
				orderBean.getKhddMap().put("IFTBZT", "1");//表示这个是同步状态订单，不能执行
			}
		}
		return list;
	}
	/**
	 * 派单业务判断重复逻辑
	 * 1、判断是否删除原有订单
	 * 2、判断是否异动（WBDDZT不同）
	 * 3、判断是否重复，返回ture表示可以入库，返回false表示不能入库
	 * @param obList
	 * @param wbdh
	 * @param shbh
	 * @return
	 */
	@Transactional
	public List<OrderBean> pdHandle(List<OrderBean> list,String wbdh,String shbh,JpPtLog ptlb){
		List<OrderBean> newlist = new ArrayList<OrderBean>();
		try {
			//根据外部单号查数量
			List<JpKhdd> khddList = jpKhddServiceImpl.getKhddByWbdh(shbh, wbdh);
			for(int i=0;i<khddList.size();i++){
				String ddzt = khddList.get(i).getDdzt();
				if(!ddzt.equals("2")){//存在不是出票中的订单，这里要过滤
					return newlist;
				}
			}
			if(CollectionUtils.isEmpty(khddList)){//第一种情况，系统中不存在该派单订单
				return list;
			}else if(list.size()!=khddList.size()){//第二种情况，系统中订单数量等于再次入库订单数量
				//按外部单号进行拆单
				ptlb.add("订单数量不相同，删除系统中订单信息["+wbdh+"]");
				LogUtil.getGyrz("GLDD").error("订单数量不相同，删除系统中订单信息["+wbdh+"]");
			    int rtn = jpKhddServiceImpl.deleteAllByWbdh(wbdh, shbh);
				return list;
			}else{//当订单数量相等时，开始比较乘机人、XS_PNR_NO信息
				boolean[] blArr = new boolean[khddList.size()];//每一个系统正常单是否匹配入库订单
				boolean ifyd = false;//是否异动
				for(int i=0;i<khddList.size();i++){
					JpKhdd jpKhdd = khddList.get(i);
					String ddbh = StringUtils.trimToEmpty(jpKhdd.getDdbh());
					String xsPnrNo = StringUtils.trimToEmpty(jpKhdd.getXsPnrNo());
					String wbddzt = StringUtils.trimToEmpty(jpKhdd.getWbddzt());
					if(StringUtils.startsWith(xsPnrNo, "O")){
						xsPnrNo = "";
					}
					for(int j=0;j<list.size();j++){
						OrderBean ob = list.get(j);
						String _xsPnrNo = StringUtils.trimToEmpty(ob.getKhddMap().get("XS_PNR_NO"));
						String _wbddzt = StringUtils.trimToEmpty(ob.getKhddMap().get("WBDDZT"));
						if(_xsPnrNo.equals(xsPnrNo)){//如果PNR相同，则检查乘机人数
							List<JpKhddCjr> jpKhddCjrList= jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jpKhdd.getDdbh(), shbh);
							List<Map<String,String>> cjrMapList = ob.getCjrList();
							if(jpKhddCjrList.size()==cjrMapList.size()){
								blArr[i] = true;
								if(!wbddzt.equals(_wbddzt)){
									ifyd = true;
								}
								ob.getKhddMap().put("DDBH", ddbh);
								break;
							}
						}
					}
				}
				boolean bl = false;
				for(int i=0;i<blArr.length;i++){//只要系统订单有一个和再次入库的订单信息不匹配，bl=true
					if(!blArr[i]){
						bl = true;
						break;
					}
				}
				if(bl){
					//按外部单号进行拆单
					ptlb.add("订单系统信息不匹配，删除系统中订单信息["+wbdh+"]");
					LogUtil.getGyrz("GLDD").error("订单系统信息不匹配，删除系统中订单信息["+wbdh+"]");
					jpKhddServiceImpl.deleteAllByWbdh(wbdh, shbh);
					return list;
				}else if(ifyd){
					return list;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newlist;
	}
}
