package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shfwkqsz;
import cn.vetech.vedsb.common.service.base.ShfwkqszServiceImpl;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.jpddsz.JpTpdGySuper;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy_tf;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;

@Service
public class JpTpdHandleXSSerivceImpl {
	
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	@Autowired
	private JpDdszServiceImpl jpDdszServiceImpl;
	@Autowired
	private ShfwkqszServiceImpl shfwkqszServiceImpl;
	@Autowired
	private JpddWork_tpd jpddWork_tpd;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	
	private static Map<String,String> tmpMap = new HashMap<String, String>();
	
	/**
	 * 查询符合条件的淘宝退票单，自动提交与客户办理退票接口
	 */
	public int execute(String wdid){
		try {
			JpDdsz jpDdsz = new JpDdsz();
			jpDdsz.setWdid(wdid);
			jpDdsz = jpDdszServiceImpl.getEntityById(jpDdsz);
			if (jpDdsz == null) {// 如果查不到导单设置，或者导单设置没有开启
				return -1;
			}
			// 判断商户服务是否开启
			Shfwkqsz shfwkqsz = shfwkqszServiceImpl.getShfwkqszByShbhLxFwlxid(jpDdsz.getShbh(), "1", "7006101");
			if (shfwkqsz == null || "0".equals(shfwkqsz.getSfkq())) {
	//			ptlb.add("商户导单服务未开启，自动关闭供应导单开关，请开启商务服务后重新打开供应导单开关。");
				JpDdsz _jpDdsz = new JpDdsz();
				_jpDdsz.setWdid(jpDdsz.getWdid());
				_jpDdsz.setDdKqtpdd("0");
				jpDdszServiceImpl.update(_jpDdsz);
				return -1;
			}
			//查询 cg_tpzt='3'（采购退票状态为 已办理） xs_tpzt为0或1（销售退票状态为  0已申请  或 1已审核）并且之前没有与客户办理过的数据
			List<JpTpd> tpdList = jpTpdServiceImpl.getCgblwcTpd(wdid, jpDdsz.getShbh(),DictEnum.PTLOGYWLX.GYTJKHTP.getValue());
			if(CollectionUtils.isEmpty(tpdList)){
				return 0;
			}
			Iterator<JpTpd> iterator = tpdList.iterator();
			String tmpStr = "";
			while(iterator.hasNext()){
				JpTpd jpTpd = iterator.next();
				String wbdh = jpTpd.getWbdh();
				if(tmpStr.indexOf(wbdh)>-1){
					tmpStr += ","+wbdh;
					iterator.remove();
				}
			}
			ExecutorService JP_RK_EXECUTOR = Executors.newFixedThreadPool(3);
			try {
				// 先循环判断重复再入库是为了防止拆分订单判断重复时异常情况
				iterator = tpdList.iterator();
				while (iterator.hasNext()) {
					JpTpd jpTpd = iterator.next();
					JP_RK_EXECUTOR.execute(new Command(jpTpd, jpDdsz));
				}
			} finally {
				JP_RK_EXECUTOR.shutdown();
			}
			try {
				JP_RK_EXECUTOR.awaitTermination(10L, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	class Command implements Runnable {
		JpTpd jpTpd;
		JpDdsz jpDdsz;
		String info;

		public Command(JpTpd jpTpd, JpDdsz one) {
			this.jpTpd = jpTpd;
			this.jpDdsz = one;
		}

		@Override
		public void run() {
			// 与淘宝办理退票
			ticketRefundHandlXS(jpTpd, jpDdsz);
		}
	}
	
	private boolean ticketRefundHandlXS(JpTpd jpTpd, JpDdsz jpDdsz){
		String shbh = jpDdsz.getShbh();
		Shyhb shyhb = new Shyhb();
		shyhb.setBh(jpDdsz.getDdUserid());
		shyhb.setShbh(shbh);
		shyhb = shyhbServiceImpl.getEntityById(shyhb);
		String tpdh = jpTpd.getTpdh();
		String wbdh = jpTpd.getWbdh();
		JpPtLog ptlb = new JpPtLog();
		boolean bl = true;
		try {
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYTJKHTP.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.TF.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setWdid(jpDdsz.getWdid());
			ptlb.setWdmc(jpDdsz.getWdmc());
			ptlb.setBy2(DictEnum.PTLOGCGGY.GY.getValue());
			ptlb.setYhbh(jpDdsz.getDdUserid());
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.setDdbh(jpTpd.getDdbh());
			ptlb.setPnrNo(jpTpd.getXsPnrNo());
			ptlb.setTfid(wbdh);
			TaobaoGy_tf tbgytf = (TaobaoGy_tf)JpTpdGySuper.getJpTpdGySuper(jpDdsz);
			if(tbgytf==null){
				return false;
			}
			String wbtpzt = jpTpd.getWbtpzt();
			String xsSfzytp = jpTpd.getXsSfzytp();//1自愿退票，0非自愿退票
			String cgSfzytp = jpTpd.getCgSfzytp();
			ptlb.add("采购是否自愿退票["+cgSfzytp+"];销售是否自愿退票["+xsSfzytp+"];淘宝外部退票状态为["+wbtpzt+"];");
			if("0".equals(cgSfzytp)&&"1".equals(wbtpzt)){//提交采购非自愿时，不处理wbtpzt=1退票单
				ptlb.add("不符合自动办理的条件1");
				return false;
			}
			if("0".equals(xsSfzytp)&&"1".equals(cgSfzytp)){//不处理销售是非自愿，采购是自愿的的退票单
				ptlb.add("不符合自动办理的条件2");
				return false;
			}
			if(StringUtils.isNotBlank(tmpMap.get(wbdh))){
				ptlb.add("该外部单号正在处理中无需再次提交");
				bl = false;
				return false;
			}
			tmpMap.put(wbdh, wbdh);
			//回填手续费
			if("1".indexOf(wbtpzt)>-1){
				Map<String,String> rtnMap = tbgytf.fillfee(tpdh, shyhb, ptlb, jpddWork_tpd);
				String status = rtnMap.get("status");
				if(!"ture".equals(status)){
					String message = rtnMap.get("message");
					ptlb.add("请求回填手续费接口返回失败，失败原因："+message);
					return false;
				}
			}
			if("1,2".indexOf(wbtpzt)>-1){
				//确认退票
				Map<String,String> rtnMap = tbgytf.confirmreturn(wbdh, shyhb, ptlb);
				String status = rtnMap.get("status");
				if(!"true".equals(status)){
					String message = rtnMap.get("message");
					ptlb.add("请求确认退票接口返回失败，失败原因："+message);
					return false;
				}
				JpTpd newJptpd = new JpTpd();
				newJptpd.setWbdh(wbdh);
				newJptpd.setShbh(shbh);
				newJptpd.setXsTpzt("1");
				newJptpd.setWbtpzt("3");
				newJptpd.setXgly("淘宝自动确认退票");
				newJptpd.setXgyh(jpDdsz.getDdUserid());
				jpTpdServiceImpl.updateTpdByWbdh(jpTpd);
			}
			if("1,2,3".indexOf(wbtpzt)>-1){
				//确认退款
				Map<String,String> rtnMap = tbgytf.refundmoneyConfirm(wbdh, shyhb, ptlb);
				String status = rtnMap.get("status");
				if(!"true".equals(status)){
					String message = rtnMap.get("message");
					ptlb.add("请求确认退款接口返回失败，失败原因："+message);
					return false;
				}
				JpTpd newJptpd = new JpTpd();
				newJptpd.setWbdh(wbdh);
				newJptpd.setShbh(shbh);
				newJptpd.setXsTpzt("2");
				newJptpd.setWbtpzt("8");
				newJptpd.setXgly("淘宝自动确认退款");
				newJptpd.setXgyh(jpDdsz.getDdUserid());
				jpTpdServiceImpl.updateTpdByWbdh(newJptpd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(bl){
				try {
					jpPtLogServiceImpl.insertLog(ptlb);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			tmpMap.remove(wbdh);
		}
		return true;
	}
}
