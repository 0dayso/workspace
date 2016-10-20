package cn.vetech.vedsb.jp.service.jpzdcp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.autoetdz.EtdzParam;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.xepnr.XePnrParam;
import org.vetech.core.business.pid.pidbean.CommandBean;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShshbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpZdcpB2bzh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddKz;
import cn.vetech.vedsb.jp.entity.jpxepnr.JpXepnr;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpDbjpnr;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpZdcpB2bzhServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddKzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jpxepnr.JpXepnrServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.platpolicy.b2b.service.B2bService;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.bean.B2bpolicyBean;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bPolicyResponse;
import cn.vetech.vedsb.utils.BookOrderUtil;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.vedsb.utils.zdcp.AutoCpErrorCode;
import cn.vetech.vedsb.utils.zdcp.AutoCpException;

@Component
public class JpzdcpWork {
	@Autowired
	private FindLowPriceService lowPriceService;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private ShshbServiceImpl shshbServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpZdcpDbjpnrServiceImpl jpZdcpDbjpnrServiceImpl;
	@Autowired
	private B2bService b2bService;
	@Autowired
	private JpB2bHkgsServiceImpl jpB2bHkgsServiceImpl;
	@Autowired
	private JpZdcpB2bzhServiceImpl jpZdcpB2bzhServiceImpl;
	@Autowired
	private JpZdcpJkServiceImpl jpZdcpJkServiceImpl;
	@Autowired
	private FindBestPolicy findBestPolicy;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private JpKhddKzServiceImpl jpKhddKzServiceImpl;
	@Autowired
	private JpXepnrServiceImpl jpXepnrServiceImpl;
	//全自动出票入口
	public JpZdcpJk start(String ddbh,String shbh) throws Exception{
		LogUtil.getAutoCp().error("全自动出票开始,"+ddbh+","+shbh+"开始匹配全自动出票规则<br>");
		JpZdcpJk jk = null;
		Map<String, String> map = null;
		String result = "";
		String error = "";
		try {
			try {
				map = procedureServiceImpl.qzdcp(ddbh, shbh, "");
				result = VeStr.getValue(map, "result");
				error = VeStr.getValue(map, "p_error");
			} catch (Exception e) {
				LogUtil.getAutoCp().error("调用自动出票规则失败",e);
			}
			
			if(StringUtils.isBlank(result)){
				throw new Exception("匹配全自动规则失败"+error);
			}
			JpZdcpJk t = new JpZdcpJk();
			t.setId(result);
			t.setShbh(shbh);
			jk=jpZdcpJkServiceImpl.getEntityById(t);
			if (jk == null) {
				throw new Exception("匹配全自动规则失败");
			}
			if ("0".equals(jk.getZdcpSffh())) {
				throw new Exception("订单不符合自动出票规则");
			}
			jk.add(error);
			excute(jk, ddbh);
		} catch (Exception e) {
			e.printStackTrace();
			String s = e.getMessage();
			if (jk != null) {
				jk.add(s);
			}else{
				LogUtil.getAutoCp().error(s);
			}
			if(!s.contains("出过票")){
				try {
					JpKhdd jpkhdd = jpKhddServiceImpl.getKhddByDdbh(shbh, ddbh);
					if (!DictEnum.JPDDZT.YWC.getValue().equals(jpkhdd.getDdzt())) {
						JpKhdd khdd = new JpKhdd();
						khdd.setDdbh(ddbh);
						khdd.setShbh(shbh);
						khdd.setDdzt(DictEnum.JPDDZT.YDZ.getValue());
						khdd.setXgly("全自动出票");
						if (jk != null) {
							khdd.setXgyh(jk.getZdcpy());
						}
						khdd.setXgsj(VeDate.getNow());
						jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(khdd);// 出票过程中发生异常情况,将订单状态改为已订座
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
			throw e;
		}finally{
			if (jk != null) {
				String log =VeStr.substrByte(jk.getString(), 3000);
				System.out.println("全自动出票记录日志信息"+log);
				jk.setSm(log);
				jpZdcpJkServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jk);
			}
		}
		return jk;
	}
	//全自动开始
	private void excute(JpZdcpJk jk,String ddbh){
		try {
			String shbh = jk.getShbh();
			JpKhdd t = new JpKhdd();
			t.setDdbh(jk.getDdbh());
			t.setShbh(jk.getShbh());
			JpKhdd khdd = jpKhddServiceImpl.getEntityById(t);
			// 是否换编码
			boolean isHbm = "1".equals(jk.getSfhbm());
//			if (khdd.getCgPnrNo().startsWith("O")) {// 假编码的一定要创建新编码
//				isHbm = true;
//			}
			// 获取PID配置
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			if (jpPz == null) {
				throw new AutoCpException(-50,	jk.add("未获取到"+shbh+"商户PID配置"));
			}
			// 获取自动出票员
			Shyhb yhTj = new Shyhb();
			yhTj.setBh(jk.getZdcpy());
			yhTj.setShbh(shbh);
			Shyhb yhb = shyhbServiceImpl.getEntityById(yhTj);
			if(yhb==null){
				throw new AutoCpException(-50,"未找到自动出票员");
			}
			deleteDbjPnrByddbh(ddbh);
			//寻找同一时刻更低的舱位并创建编码到待比价编码池
			boolean ischangepnr = false;
			String sftsk = jk.getSftskbjcp();//是否开启同时刻航班比价
			/**淘宝订单中如果含有儿童则按原航班出票**/
			if(!"0".equals(sftsk)){
				String wbdh = khdd.getWbdh();
				jk.add("该订单的外部单号："+wbdh);
				JpKhdd jpkhdd = new JpKhdd();
				jpkhdd.setShbh(shbh);
				jpkhdd.setWbdh(wbdh);
				List<JpKhdd> khddlist = this.jpKhddServiceImpl.queryList(jpkhdd);
				for(JpKhdd khdds : khddlist){
					String _ddbh = khdds.getDdbh();
					List<JpKhddCjr> _cjrlist = this.jpKhddCjrServiceImpl.getKhddCjrListByDDbh(_ddbh, shbh);
					for(JpKhddCjr cjr : _cjrlist){
						if("2".equals(cjr.getCjrlx())){
							sftsk = "0";
							break;
						}
					}
					if("0".equals(sftsk)){
						break;
					}
				}
			}
			/**订单中如果含有儿童则按原航班出票**/
			
			StringBuffer pnrztflag=new StringBuffer();//这个标志是说明新生成的编码是不是有座位的状态,如果该状态不能等于空时,新生成的编码是没有座位的状态,将新编码xe掉,按原编码出
			if("1".equals(jk.getDdHclx()) && ("1".equals(sftsk) || ("2".equals(sftsk) && jk.getDdHbh().contains("*")))){
				double jjcj = jk.getJjcj();//净价差价,找到的低价航班需要大于这个差价才能出票,否则按原编码出票
				try {
					jk.add("开始寻找同一时刻更低的舱位");
					ischangepnr =lowPriceService.findLowPrice(jk, yhb,khdd,sftsk,jjcj,pnrztflag);
					if(ischangepnr){
						String neworused = "";
						if(StringUtils.isNotBlank(pnrztflag.toString())){
							neworused = pnrztflag.toString();//新编码
							jk.add("生成新编码,但是新编码的状态不是HK或者KK或者KL状态,开始自动xe新编码,新编码为:"+pnrztflag.toString());
						}else{
							neworused = khdd.getXsPnrNo();//原编码
							jk.add("生成新编码,开始自动xe旧编码,旧编码为:"+khdd.getXsPnrNo());
						}
						if(neworused.startsWith("O")){	
							jk.add("编码为假编码,不xe编码!");
						}else{
							String result =this.zdxepnr(jpPz, neworused, yhb,jk);
							this.insertXepnr(khdd, yhb, "降舱出票",result,neworused);
						}
					}else{
						if("1".equals(sftsk)){
							jk.add("寻找同一时刻更低的舱位失败!");
						}else{
							jk.add("寻找同一共享航班对应的承运航班舱位失败!");
						}
					}
				} catch (Exception e) {
					if("1".equals(sftsk)){
						jk.add("寻找同一时刻更低的舱位失败!");
					}else if("2".equals(sftsk)){
						jk.add("寻找同一共享航班对应的承运航班舱位失败!");
					}
					e.printStackTrace();
				}
			}
			
			khdd=genJpKhddByddbh(ddbh, shbh);
			if(DictEnum.JPDDZT.YWC.getValue().equals(khdd.getDdzt())){
				throw new Exception("换编码时发现pnr已经出过票,自动出票终止！");
			}
			if(!ischangepnr){
				if (isHbm) {// 开始换编码
					jk.setIfhbmed("0");
					try {
						jk.add("开始换编码");
						if(StringUtils.isNotBlank(jk.getHbmosi())){
							khdd.setNxsj(jk.getHbmosi());
						}
						//else if("1".equals(jk.getSfhbm())){
							//info.append("设置了换编码出票，但没设置OSI项<br>");
							//throw new AutoCpException(-50,"设置了换编码出票，但没设置OSI项");
						//}
						//创建新编码
						Map<String, String> pnrInfo=creatPnr(yhb, jpPz, khdd);
						String changepnr = pnrInfo.get("PNRNO");
						String pnrzt = pnrInfo.get("PNRZT");
						if(!"HK".equals(pnrzt) && !"KK".equals(pnrzt) && !"KL".equals(pnrzt)){//换编码的pnrzt不是有座位的状态
							throw new Exception("换编码生成的pnr是没有座位的状态,"+changepnr);
						}
						jk.add("换编码成功,生成的新的pnr为:"+changepnr);
						
						if(!khdd.getXsPnrNo().startsWith("O")){//假编码不xe
							jk.add("生成新编码,开始自动xe旧编码,旧编码为:"+khdd.getXsPnrNo());
							String result =this.zdxepnr(jpPz, khdd.getXsPnrNo(), yhb,jk);//xepnr
							this.insertXepnr(khdd, yhb, "换编码出票",result,khdd.getXsPnrNo());
						}
						khdd.setCgPnrNo(changepnr);
						khdd.setCgPnrZt(pnrzt);
						khdd.setCgHkgsPnr(pnrInfo.get("BIGPNR"));
						khdd.setPnrLr(pnrInfo.get("PNRLR"));
						jk.setNewPnr(changepnr);
						jk.setNewHkgspnr(pnrInfo.get("BIGPNR"));
						jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(khdd);
						insertToDbj(jk, khdd);//将新编码插入待比价编码池
					} catch (Exception e) {
						String message = e.getMessage();
						jk.add("换编码失败:"+message);
						if(message.contains("没有座位")){
							String[] messageArray = message.split(",");
							String changednewpnr = messageArray[1];
							jk.add("生成新编码,但是新编码是没有座位的状态,新编码为:"+changednewpnr+",开始xe新编码!");
							String result =this.zdxepnr(jpPz, changednewpnr, yhb,jk);//xepnr
							this.insertXepnr(khdd, yhb, "换编码出票",result,changednewpnr);
						}
						jk.add("换编码失败:"+e.getMessage());
						e.printStackTrace();
						if(!khdd.getCgPnrNo().startsWith("O") && "1".equals(jk.getYpnrcc())){
							insertToDbj(jk, khdd);//换编码失败按原编码出票
						}
					}
				}else{
					insertToDbj(jk, khdd);//按原编码出票
				}
			}else if(StringUtils.isNotBlank(pnrztflag.toString())){
				insertToDbj(jk, khdd);//按原编码出票
			}
			JpZdcpDbjpnr dbjTj=new JpZdcpDbjpnr();
			dbjTj.setJkbid(jk.getId());
			List<JpZdcpDbjpnr> dbjList=jpZdcpDbjpnrServiceImpl.getMyBatisDao().select(dbjTj);
			if(CollectionUtils.isEmpty(dbjList)){
				throw new AutoCpException(-50,"没有可比价的编码");
			}
			if(!"0".equals(jk.getSfjcosi())){
				List<JpZdcpDbjpnr> list=new ArrayList<JpZdcpDbjpnr>();
				for(JpZdcpDbjpnr dbjpnr : dbjList){
					try {
						checkOsi(dbjpnr.getPnr(), jpPz, yhb, jk);
						list.add(dbjpnr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(CollectionUtils.isEmpty(list)){
					throw new AutoCpException(-50,"OSI检查不通过");
				}
				dbjList.clear();
				dbjList.addAll(list);
			}
			//开始出票
			startTicket(jk, dbjList, khdd, yhb);
		} catch (AutoCpException e) {
			e.printStackTrace();
			jk.add("全自动出票失败:"+e.getMessage());
			// excuteT_zdcp_jk(t_zdcp_jk, e.getErrorcode(), info, e);
			throw new AutoCpException(-50, e, e.getErrorcode());

		} catch (Exception e) {
			e.printStackTrace();
			jk.add("全自动出票失败:"+e.getMessage());
			AutoCpErrorCode _code = AutoCpErrorCode.instance("QTYY");
			// excuteT_zdcp_jk(t_zdcp_jk, _code, info, e);
			throw new AutoCpException(-50, e, _code);
		}
	}
	/**
	 * <开始比较出票>
	 * 
	 * @param jk
	 * @param dbjList
	 * @return [参数说明]
	 */
	private void startTicket(JpZdcpJk jk,List<JpZdcpDbjpnr> dbjList,JpKhdd jpKhdd,Shyhb yhb) throws Exception{
		// 获取PID配置
		String shbh=jpKhdd.getShbh();
		String ddbh=jpKhdd.getDdbh();
		JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
		String[] bpyxj=jk.getBpyxj().split("/");
		DataUtils.sortList(dbjList, new String[]{"patZdj,asc"});
		JpZdcpDbjpnr lowDbjpnr=dbjList.get(0);//最低价格舱位
		String errorst = "";
		boolean allFail=true;
		String sfxyh=genSfxyh(jk.getXyh());
		for (int i = 0; i < bpyxj.length; i++) {//有一个渠道成功则结束循环，没成功继续下一个渠道出票
			if("BJCP".equals(bpyxj[i])){//比价出票
				String bjPt=jk.getBjCybjzc();
				jk.add("比价出票开始");
				String hkgs=lowDbjpnr.getHkgs();
				//格式为CA:1234555/MU:25478888/3U:875552
				JpZdcpB2bzh dlzh=new JpZdcpB2bzh();
				String hszhId=jk.getBjB2bCpzh();
				if(StringUtils.isBlank(hszhId) && bjPt.contains("BPET")){
					throw new Exception("请设置B2B出票账号");
				}else{
					if(StringUtils.isNotBlank(hszhId)){
						String reg=".*"+hkgs+":([^/.]*)/{0,1}.*";
						String b2bCpzh=DataUtils.getStrByReg(hszhId, reg);
						//JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(shbh,hkgs, "720102");
						dlzh.setId(b2bCpzh);
						dlzh.setShbh(shbh);
						dlzh=jpZdcpB2bzhServiceImpl.getEntityById(dlzh);
					}
				}
				int status=findBestPolicy.bjCp(dbjList, jk, yhb, sfxyh, dlzh, jpPz.getOfficeid());
				if(status==0){//下单并支付成功,等待票号回填
					jk.add("自动出票比价出票成功");
					jk.setCpzt("35");//支付成功
					allFail=false;
					break;
				}else if (status==-1) {//比价出票失败
					continue;
				}else if (status==-2){//终止全自动出票
					break;
				}
			}else if("BPET".equals(bpyxj[i])){//B2B出票
				jk.add("B2B出票开始");
				try {
					jpKhdd=genJpKhddByddbh(ddbh, shbh);
					if(!lowDbjpnr.getPnr().equals(jpKhdd.getCgPnrNo())){
						jpKhddServiceImpl.changeCabin(ddbh, shbh, lowDbjpnr);
					}
					String hkgs=lowDbjpnr.getHkgs();
					//格式为CA:1234555/MU:25478888/3U:875552
					String hszhId=jk.getB2bCpzh();
					if(StringUtils.isBlank(hszhId)){
						throw new Exception("请设置B2B出票账号");
					}
					String reg=".*"+hkgs+":([^/.]*)/{0,1}.*";
					String b2bCpzh=DataUtils.getStrByReg(hszhId, reg);
					JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getB2bHkgsByBca(shbh,hkgs, "720102");
					JpZdcpB2bzh dlzh=new JpZdcpB2bzh();
					dlzh.setId(b2bCpzh);
					dlzh.setShbh(shbh);
					dlzh=jpZdcpB2bzhServiceImpl.getEntityById(dlzh);
					B2bPolicyResponse b2bPolicyResponse=b2bService.getB2bPolicy(shbh, ddbh, jpB2bHkgs, dlzh, yhb, sfxyh, jpPz);
					List<B2bpolicyBean> b2bPoList=b2bPolicyResponse.getB2bPolicyBean();
					DataUtils.sortList(b2bPoList, new String[]{"totalfee,asc"});
					B2bpolicyBean toOrderPolicy=null;
					if("1".equals(jk.getSfdjcp())){//按低价出票
						toOrderPolicy=b2bPoList.get(0);
					}else {
						toOrderPolicy=b2bPoList.get(b2bPoList.size()-1);
					}
					//计算出票利润
					double drXjjsj=Arith.div(jpKhdd.getXsPj(), jpKhdd.getCjrs());
					long jjry=jpKhdd.getCgJsf()+jpKhdd.getCgTax();
					double drCgj=Arith.div(Arith.sub(toOrderPolicy.getTotalfee(),jjry), jpKhdd.getCjrs());
					double hfje=Arith.mul(Arith.div(toOrderPolicy.getPjhj(), jpKhdd.getCjrs()),jk.getB2bHf());
					double cplr=Arith.div(drXjjsj, drCgj);
					cplr=Arith.sum(cplr,hfje);
					if(cplr<jk.getZxlr()){//判断是否满足最小出票利润
						jk.add(bpyxj[i]+"出票失败，B2B政策不满足最小利润,"+toOrderPolicy.toString());
						continue;
					}
					String error=b2bService.order2dgxt(jpKhdd, yhb, dlzh, sfxyh, jpPz.getOfficeid(), toOrderPolicy);
					if(StringUtils.isBlank(error)){//b2b代购成功
						jk.add("自动出票"+bpyxj[i]+"出票成功");
						jk.setCpzt("35");//支付成功
						allFail=false;
						break;
					}else {//代购失败
						jk.add(bpyxj[i]+"出票失败，"+error);
					}
				} catch (Exception e) {
					e.printStackTrace();
					jk.add(bpyxj[i]+"出票失败："+e.getMessage());
				}
			}else if("BSPET".equals(bpyxj[i]) || "BOP".equals(bpyxj[i])){
				jk.add(bpyxj[i]+"出票开始");
				if(lowDbjpnr.getPnr().contains("O")){//bop,bspet出票如果是假编码一定要换编码
					if("0".equals(jk.getIfhbmed())){
						jk.add("BOP或者BSPET不能出假编码");
						jk.add("终止BSPET或者BOP出票");
						break;
					}else{
						deleteDbjPnrByddbh(ddbh);
						jk.add("开始换编码");
						try {
							//创建新编码
							Map<String, String> pnrInfo=creatPnr(yhb, jpPz, jpKhdd);
							String changepnr = pnrInfo.get("PNRNO");
							String pnrzt = pnrInfo.get("PNRZT");
							if(!"HK".equals(pnrzt) && !"KK".equals(pnrzt) && !"KL".equals(pnrzt)){//换编码的pnrzt不是有座位的状态
								throw new Exception("换编码生成的pnr是没有座位的状态,"+changepnr);
							}
							jk.add("换编码成功,生成的新的pnr为:"+changepnr);
							jpKhdd.setCgPnrNo(changepnr);
							jpKhdd.setCgPnrZt(pnrzt);
							jpKhdd.setCgHkgsPnr(pnrInfo.get("BIGPNR"));
							jpKhdd.setPnrLr(pnrInfo.get("PNRLR"));
							jk.setNewPnr(changepnr);
							jk.setNewHkgspnr(pnrInfo.get("BIGPNR"));
							jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpKhdd);
							insertToDbj(jk, jpKhdd);//将新编码插入待比价编码池
							JpZdcpDbjpnr dbjTj=new JpZdcpDbjpnr();
							dbjTj.setJkbid(jk.getId());
							List<JpZdcpDbjpnr> list=jpZdcpDbjpnrServiceImpl.getMyBatisDao().select(dbjTj);
							lowDbjpnr=list.get(0);
						} catch (Exception e) {
							String message = e.getMessage();
							jk.add("换编码失败:"+message);
							if(message.contains("没有座位")){
								String[] messageArray = message.split(",");
								String changednewpnr = messageArray[1];
								jk.add("生成新编码,但是新编码是没有座位的状态,新编码为:"+changednewpnr+",开始xe新编码!");
								String result =this.zdxepnr(jpPz, changednewpnr, yhb,jk);//xepnr
								this.insertXepnr(jpKhdd, yhb, "换编码出票",result,changednewpnr);
							}
							jk.add("换编码失败!终止BSPET或者BOP出票,"+e.getMessage());
							e.printStackTrace();
							break;
						}
					}
				}
				try {
					jpKhdd=genJpKhddByddbh(ddbh, shbh);
					if(!lowDbjpnr.getPnr().equals(jpKhdd.getCgPnrNo())){
						jpKhddServiceImpl.changeCabin(ddbh, shbh, lowDbjpnr);
					}
					String selectlowprice=jk.getSfdjcp();
					if(!"1".equals(selectlowprice)){
						selectlowprice="";
					}
					
					AutoEtdz autoEtdz = SpringContextUtil.getBean(AutoEtdz.class);
					EtdzParam ep=new EtdzParam();
					ep.setIp(jpPz.getPzIp());
					ep.setPort(jpPz.getPzPort());
					ep.setUserid(yhb.getPidyh());
					ep.setPass(jk.getZdcpymm());
					ep.setShbh(jpKhdd.getShbh());
					ep.setBmbh(yhb.getShbmid());
					ep.setXyh(sfxyh);
					ep.setDdbh(ddbh);
					ep.setDylx("VEDS");
					ep.setBbh("0");
					ep.setPatvalue("");
					ep.setYhbh(yhb.getBh());
					ep.setCpqdlx(bpyxj[i]);
					if("BSPET".equals(bpyxj[i])){
						ep.setOffice(jk.getBspOffice()+","+StringUtils.trimToEmpty(jk.getBspAgent())+","+StringUtils.trimToEmpty(jk.getBspPrintno()));
					}else if("BOP".equals(bpyxj[i])){
						ep.setOffice(jk.getBopOffice()+","+StringUtils.trimToEmpty(jk.getBopAgent())+",");
					}
					ep.setSelectlowprice(selectlowprice);
					Map<String,Object> m=autoEtdz.auto_etdz(ep,jk);
					int autook = (Integer) m.get("zt");
					errorst=VeStr.getValue(m, "error");
					//info.append(autoEtdz.getInfo().toString());
					JpKhddKz jpKhddKz =new JpKhddKz();
					jpKhddKz.setDdbh(ddbh);
					jpKhddKz = jpKhddKzServiceImpl.getEntityById(jpKhddKz);//查询自动出票是否成功
					String zdcpzt = jpKhddKz.getZdcpzt();
					if (autook == AutoEtdz.SUCCESS) {
						jk.add("自动出票"+bpyxj[i]+"出票成功");
						jk.setCpzt("0");
						allFail=false;
					} else if (autook == AutoEtdz.RETURNCP) {// 德付通支付成功,但是ETDZ执行失败 保持已订座
						jk.add("德付通支付成功,但是ETDZ执行失败");
						jk.setCpzt("35");
						//allFail=false;
					} else {
						// 添加一个逻辑，如果返回的失败信息中包含(出票已经成功字段)，则将此订单继续保存为出票中状态
						if (errorst.contains("出票已经成功")) {
							jk.add("出票失败返回：'"+errorst+"',包含'出票已成功'");
							allFail=false;
						}
					}
					if(!allFail){//结束全自动出票
						
						jk.setCpwcsj(VeDate.getNow());
						jk.setZxhs(VeDate.getTwoSec(jk.getCpwcsj(), jk.getCjsj()));
						jk.add("全自动出票结束");
						break;
					}else{
						if("1".equals(zdcpzt)){
							allFail=false;
							jk.setCpwcsj(VeDate.getNow());
							jk.setCpzt("0");
							jk.add("全自动出票成功,转机票失败!,失败原因:"+errorst);
						}else if("2".equals(zdcpzt)){
							jk.add("全自动出票失败,失败原因:"+errorst);
						}else{
							jk.add("出票前调etdz检查不通过,不通过原因:"+errorst);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					jk.add(bpyxj[i]+"出票失败："+e.getMessage());
				}
			}
		}
		jpKhdd=genJpKhddByddbh(ddbh, shbh);
		if(allFail && !DictEnum.JPDDZT.YWC.getValue().equals(jpKhdd.getDdzt())){//所有渠道都出失败了并且不是已经出票成功的，转手工出,将订单状态改为已订座
			jk.setCpzt("-50");
			JpKhdd record=new JpKhdd();
			if(errorst.contains("航班座位已经被NO掉了")){
				record.setCgPnrZt("NO");
				if(jpKhdd.getCgPnrZt().equals(jpKhdd.getXsPnrZt())){
					record.setXsPnrZt("NO");
				}
			}
			record.setDdbh(ddbh);
			record.setShbh(shbh);
			record.setDdzt(DictEnum.JPDDZT.YDZ.getValue());
			jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(record);
		}
	}
	/**
	 * <检查OSI>
	 * 
	 * @throws Exception
	 */
	private void checkOsi(String pnrno,JpPz jpPz,Shyhb yhb,JpZdcpJk jk) throws Exception{
		String sfjcosi=jk.getSfjcosi();
		Pnr pnr=DataUtils.rtPnr(pnrno, jpPz, yhb);
		if("1".equals(sfjcosi) || "3".equals(sfjcosi)){
			String osiCjr="";
			List<PnrCjr> cjrlist=pnr.getCjrlist();
			for(PnrCjr cjr : cjrlist){
				if(StringUtils.isNotBlank(cjr.getSjhm())){
					osiCjr=cjr.getSjhm();
					break;
				}
			}
			if(StringUtils.isNotBlank(osiCjr)){
				jk.add("OSI项目检查，乘机人有联系号码"+osiCjr);
			}else {
				throw new Exception("OSI项目检查，乘机人没有联系号码");
			}
		}
		if("2".equals(sfjcosi) || "3".equals(sfjcosi)){
			String ctct=checkCtCt(pnr.getPnr_lr());
			if(StringUtils.isNotBlank(ctct)){
				jk.add("OSICTCT项目检查，PNR有CTCT项"+ctct);
			}else {
				throw new Exception("OSICTCT项目检查，PNR无CTCT项");
			}
		}
		
	}
	private String checkCtCt(String pnrnr) {
		String regex = "(\\d{1,2}.OSI\\s*[0-9A-Z]{2}\\s*CTCT\\s*[\\d]{1,}(/P[1-9]){0,1}[\\r|\\n]{0,1}\\s*){1,}";
		String regx2 = "^([A-Z0-9]{2})\\s*CTC\\w(\\d{1,12})(/P[1-9]){0,1}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pnrnr);
		if (matcher.find()) {
			String group0 = StringUtils.trimToEmpty(matcher.group(0));
			if (StringUtils.isNotBlank(group0)) {
				group0 = group0.replaceAll("[\\d]{2}\\.", "");
				group0 = group0.replaceAll("[\\d]{1}\\.", "");
				group0 = StringUtils.trimToEmpty(group0);
				String[] osiarr = group0.split("\\s*OSI");
				if (ArrayUtils.isNotEmpty(osiarr)) {
					Pattern patterns = Pattern.compile(regx2);
					for (String s : osiarr) {
						if (StringUtils.isNotBlank(StringUtils.trimToEmpty(s))) {
							Matcher matchers = patterns.matcher(StringUtils.trimToEmpty(s));
							if (matchers.find()) {
								return StringUtils.trimToEmpty(matchers.group(2));
							}
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * <生成新编码>
	 * 
	 * @throws Exception
	 */
	private Map<String, String> creatPnr(Shyhb yhb,JpPz jpPz,JpKhdd khdd) throws Exception{
		try {
			String ddbh=khdd.getDdbh();
			String shbh=khdd.getShbh();
			List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
			if(CollectionUtils.isEmpty(hdList)){
				throw new Exception("没找到订单航段信息！");
			}
			List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
			if(CollectionUtils.isEmpty(cjrList)){
				throw new Exception("没找到订单乘机人信息！");
			}
			int hdLen=hdList.size();
			String[] cfcityArr=new String[hdLen];
			String[] ddcityArr=new String[hdLen];
			String[] cfdateArr=new String[hdLen];
			String[] cfskArr=new String[hdLen];
			String[] hkgsArr=new String[hdLen];
			String[] hbhArr=new String[hdLen];
			String[] cwArr=new String[hdLen];
			String[] ifnoseatArr=new String[hdLen];
			for(int i=0;i<hdLen;i++){
				JpKhddHd hd=hdList.get(i);
				cfcityArr[i]=hd.getCfcity();
				ddcityArr[i]=hd.getDdcity();
				cfdateArr[i]=hd.getCfsj().substring(0,10);
				cfskArr[i]=hd.getCfsj().substring(11,16);
				hbhArr[i]=hd.getCgHbh();
				hkgsArr[i]=hd.getCgHbh().replace("*", "").substring(0,2);
				cwArr[i]=StringUtils.isNotBlank(hd.getCgZcw()) ? hd.getCgZcw() : hd.getCgCw();
				ifnoseatArr[i]="1";
			}
			int cjrLen=cjrList.size();
			String lxsj=genLxsj(khdd, shbh);
			String[] cjrlxArr=new String[cjrLen];
			String[] cjrxmArr=new String[cjrLen];
			String[] zjhmArr=new String[cjrLen];
			String[] csrqArr=new String[cjrLen];
			String[] sjhmArr=new String[cjrLen];
			for(int i=0;i<cjrLen;i++){
				JpKhddCjr cjr=cjrList.get(i);
				cjrlxArr[i]=cjr.getCjrlx();
				cjrxmArr[i]=cjr.getCjr();
				zjhmArr[i]=cjr.getZjhm();
				csrqArr[i]=cjr.getCsrq();
				sjhmArr[i]=lxsj;
			}
			
			CommandBean commandBean=new CommandBean();
			commandBean.setBookType("0");//ETERM
			commandBean.setGngj(khdd.getGngj());
			commandBean.setDpyid(yhb.getPidyh());
			commandBean.setHkgs(hkgsArr);
			commandBean.setHbh(hbhArr);
			commandBean.setCfcity(cfcityArr);
			commandBean.setDdcity(ddcityArr);
			commandBean.setCfdate(cfdateArr);
			commandBean.setCfsj(cfskArr);
			commandBean.setCw(cwArr);
			commandBean.setIfnoseat(ifnoseatArr);
			commandBean.setRs(cjrLen+"");
			commandBean.setCjrlx(cjrlxArr);
			commandBean.setCjrxm(cjrxmArr);
			commandBean.setZjhm(zjhmArr);
			commandBean.setCsrq(csrqArr);
			commandBean.setSjhm(sjhmArr);
			commandBean.setIfqk("1");
			commandBean.setCt_phoneno(lxsj);
			BookOrderUtil bookutil = new BookOrderUtil();
			return bookutil.excCommand(commandBean, jpPz.getPzIp()+":"+jpPz.getPzPort());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("生成编码失败："+e.getMessage());
		}
	}
	private String genLxsj(JpKhdd khdd,String shbh){
		String lxsj = khdd.getNxsj();//订单联系手机
		if(StringUtils.isBlank(lxsj)){
			Shcsb shcsb = this.shcsbServiceImpl.findCs("2023", shbh);
			lxsj = shcsb.getCsz2();//取参数联系手机
		}
		if(StringUtils.isBlank(lxsj)){
			Shshb shshb = shshbServiceImpl.getShbhByBh(shbh);
			lxsj = shshb.getLxrsj();
		}
		return lxsj;
	}
	/**
	 * 插入待比价编码
	 * @throws Exception 
	 */
	private void insertToDbj(JpZdcpJk jk,JpKhdd khdd) throws Exception{
		List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(jk.getDdbh(), jk.getShbh());
		JpZdcpDbjpnr dbjPnr=new JpZdcpDbjpnr();
		dbjPnr.setId(Identities.randomLong()+"");
		dbjPnr.setJkbid(jk.getId());
		dbjPnr.setDdbh(khdd.getDdbh());
		dbjPnr.setPnr(khdd.getCgPnrNo());
		dbjPnr.setHkgspnr(khdd.getCgHkgsPnr());
		String hkgs=khdd.getCgHbh().substring(0,2);
		dbjPnr.setHkgs(hkgs);
		dbjPnr.setHclx(khdd.getHclx());
		dbjPnr.setHcgngj(khdd.getGngj());
		int hdLen=hdList.size();
		String[] hcArr=new String[hdLen];
		String[] cwArr=new String[hdLen];
		String[] hbhArr=new String[hdLen];
		String[] qfsjArr=new String[hdLen];
		String[] ddsjArr=new String[hdLen];
		String[] hzlArr=new String[hdLen];
		for(int i=0;i<hdLen;i++){
			JpKhddHd hd=hdList.get(i);
			hcArr[i]=hd.getCfcity()+hd.getDdcity();
			cwArr[i]=hd.getCgCw();
			hbhArr[i]=hd.getCgHbh();
		}
		dbjPnr.setHc(StringUtils.join(hcArr,"|"));
		dbjPnr.setCw(StringUtils.join(cwArr,"|"));
		dbjPnr.setCwzkl("0");
		dbjPnr.setHbh(StringUtils.join(hbhArr,"|"));
		dbjPnr.setQfsj(StringUtils.join(qfsjArr,"|"));
		dbjPnr.setDdsj(StringUtils.join(ddsjArr,"|"));
		dbjPnr.setHzl(StringUtils.join(hzlArr,"|"));
		dbjPnr.setCjr(khdd.getCjr());
		dbjPnr.setYdDatetime(khdd.getDdsj());
		dbjPnr.setPnrZt(khdd.getCgPnrZt());
		dbjPnr.setSfcpbm("0");
		dbjPnr.setPatZdj(Arith.div(khdd.getCgZdj(), khdd.getCjrs()));
		double jsf=Arith.div(khdd.getCgJsf(), khdd.getCjrs());
		dbjPnr.setPatJj(jsf);
		dbjPnr.setPatSf(0d);
		jpZdcpDbjpnrServiceImpl.insert(dbjPnr);
	}
	/**
	 * <获取三方协议号，多个时随机取一个>
	 * 
	 * @param xyhs
	 * @return [参数说明]
	 */
	private String genSfxyh(String xyhs){
		if(StringUtils.isBlank(xyhs)){
			return null;
		}
		String[] xyhArr=xyhs.split("/");
		int len=xyhArr.length;
		if(len==1){
			return xyhArr[0];
		}
		int index=(int)(Math.random()*len);
		return xyhArr[index];
	}
	/**
	 * <查找机票订单>
	 * 
	 * @param ddbh
	 * @param shbh
	 */
	private JpKhdd genJpKhddByddbh(String ddbh,String shbh){
		JpKhdd t=new JpKhdd();
		t.setShbh(shbh);
		t.setDdbh(ddbh);
		t=jpKhddServiceImpl.getEntityById(t);
		return t;
	}
	
	private void insertXepnr(JpKhdd jpkhdd,Shyhb yhb,String s,String xezt,String pnrno){
		String shbh = yhb.getShbh();
		//String pnrno = jpkhdd.getXsPnrNo();
		String ddbh = jpkhdd.getDdbh();
		String yh = yhb.getBh();
		String cjr = jpkhdd.getCjr();
		String hc = jpkhdd.getHc();
		String pnrnr = jpkhdd.getPnrLr();
		JpXepnr jpxepnr = new JpXepnr();
		jpxepnr.setId(Identities.randomLong()+"");
		jpxepnr.setXelx("XEPNR");
		jpxepnr.setXezt(xezt);
		jpxepnr.setCjr(cjr);
		jpxepnr.setHd(hc);
		jpxepnr.setShbh(shbh);
		jpxepnr.setCjyh(yh);
		jpxepnr.setXeyh(yh);
		jpxepnr.setPnrLr(pnrnr);
		jpxepnr.setCjsj(VeDate.getNow());
		jpxepnr.setXesj(VeDate.getNow());
		jpxepnr.setSfyzhd("0");
		jpxepnr.setSfyzzj("0");
		jpxepnr.setXesy("换编码");
		jpxepnr.setCzly(s);
		jpxepnr.setDdbh(ddbh);
		jpxepnr.setPnrNo(pnrno);
		try {
			this.jpXepnrServiceImpl.getMyBatisDao().insert(jpxepnr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**换编码后自动xepnr*/
	private String zdxepnr(JpPz jppz,String pnrno,Shyhb yhb,JpZdcpJk jk){
		String xezt = "";
		try {
			XePnrParam xp=new XePnrParam();
			xp.setOfficeId(jppz.getOfficeid());
			xp.setPnrNo(pnrno);
			xp.setUserid(yhb.getPidyh());
			xp.setUrl("http://"+jppz.getPzIp()+":"+jppz.getPzPort());
			String result=IbeService.xePnr(xp);// 1:XE成功;2:已被XE;3:需要授权\
			if("1".equals(result)){
				jk.add("xe成功!");
				xezt = "1";//已取消
			}else if("2".equals(result)){
				jk.add("编码已被xe掉");
				xezt = "1";//编码已经被xe掉
			}else if("3".equals(result)){
				jk.add("编码需要授权");
				xezt = "2";//取消失败
			}else{
				jk.add("xe编码失败!");
				xezt = "2";
			}
			return xezt;
		} catch (Exception e) {
			e.printStackTrace();
			return "2";
		}
	}
	
	private void deleteDbjPnrByddbh(String ddbh){
		JpZdcpDbjpnr jpZdcpDbjpnr = new JpZdcpDbjpnr();
		jpZdcpDbjpnr.setDdbh(ddbh);
		this.jpZdcpDbjpnrServiceImpl.delete(jpZdcpDbjpnr);//根据订单编号清空相应订单编号对应的待比价池中的数据
	}
	public static void main(String[] args) {
	}
}
