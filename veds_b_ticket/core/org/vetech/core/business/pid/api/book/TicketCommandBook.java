package org.vetech.core.business.pid.api.book;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.vetech.core.business.pid.api.Seat;
import org.vetech.core.business.pid.api.SpellOtherCommand;
import org.vetech.core.business.pid.pidbean.CommandBean;
import org.vetech.core.business.pid.util.BookUtil;
import org.vetech.core.business.pid.util.CommandUtil;

/**
 * 机票指令提交预订类
 * @author  gengxianyan
 * @version  [版本号, Apr 25, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class TicketCommandBook {
	
	@Autowired
//	private PidCommonService commonService;
	
	private CommandBean command;

	private Seat seat;

	private String pnrnr;
	
	/**
	 * <默认构造函数>
	 * @param command 数据传输Bean
	 * @param seat 指令封装对象
	 */
	public TicketCommandBook(CommandBean command,Seat seat){
		this.command = command;
		this.seat = seat;
	}


	/**
	 * 订票方法
	 * @exception Exception 异常
	 */
	public void book() throws Exception {
		try {
			//是否使用IBE配置
			boolean ifibe =  "1".equals(command.getBookType()) ? true :false;
			
			//航程段
			String[] cfdates = command.getCfdate();
			for(int i=0;i<cfdates.length;i++){
				String cfdate = command.getCfdate()[i];
				if (!ifibe) {
					cfdate = BookUtil.dateCommandDay(cfdate);
				}
				command.getCfdate()[i] = cfdate;
				
			}
			
			CommandUtil commandUtil = new CommandUtil(seat);
			commandUtil.addAirSeg(command);
		
			//加团申请
			if (StringUtils.isNotBlank(command.getJtnum()) && !"0".equals(command.getJtnum())) {
				seat.gn(command.getJtnum(), command.getJtname());
			}
			
			int start = 0;
			for (int i = 0; i < Integer.valueOf(command.getCjrxm().length); i++) {
				String p = "";
				if("1".equals(command.getCjrlx()[i])){
					seat.addAdult(command.getCjrxm()[i]);
					if (ifibe) {
						p = command.getCjrxm()[i];
					} else {
						p = "P" + (start + 1);
					}
					seat.addSSR_FOID(command.getHkgs()[0], "NI", command.getZjhm()[i], p);
					
					if (!ObjectUtils.isEmpty(command.getHkgslck())) {
						if (StringUtils.isNotBlank(command.getHkgslck()[i])) {
							seat.addSSR_FQTV(command.getHkgs()[0], command.getHkgslck()[i], p);
						}
					}
					start ++;
				}else if("2".equals(command.getCjrlx()[i])){
					String birthday = "";
					
					// 修改儿童，这里处理两种情况的出生日期
					if (StringUtils.isNotEmpty(command.getZjhm()[i]) && (command.getZjhm()[i].length() == 15 || command.getZjhm()[i].length() == 18)) {
						birthday = BookUtil.dateCommandTime(BookUtil.getBirthDateFromCard(command.getZjhm()[i]));
					} else {
						birthday = BookUtil.dateCommandTime(command.getZjhm()[i]);
					}
					if (StringUtils.isEmpty(birthday)) {
						birthday = BookUtil.dateCommandTime(command.getCsrq()[i]);
					}
					
					seat.addChild(command.getCjrxm()[i], birthday);
					
					String hkgs = command.getHkgs()[0];
					seat.addSSR_CHLD(hkgs, birthday,"P" + (i+1));//所有航空公司儿童预订时，都需要增加CHLD指令项
					
					if (ifibe) {
						p = command.getCjrxm()[i];
					} else {
						p = "P" + (start + 1);
					}
					
					if (!ObjectUtils.isEmpty(command.getHkgslck()) && command.getHkgslck().length > 0) {
						if (StringUtils.isNotBlank(command.getHkgslck()[i])) {
							seat.addSSR_FQTV(command.getHkgs()[0], command.getHkgslck()[i], p);
						}
					}

				}
				
				/** 添加餐食偏好组 */
				SpellOtherCommand.addCsph(command, seat, i, p);
				/** 添加座位偏好组 */
				SpellOtherCommand.addZwph(command, seat, i, p);
			}
			
			int yeCount = 0;
			//单独加婴儿，防止乘机人数组中，婴儿在成人前面预订异常
			for (int i = 0; i < Integer.valueOf(command.getCjrxm().length); i++) {
				if("3".equals(command.getCjrlx()[i])){
					String p = "";
					
					String yedate = "";
					String yep = "";
					if (ifibe) {// //0是eterm 其他是ibe
						yedate = command.getCsrq()[start-1];
						p = command.getCjrxm()[start-1];
						yep = command.getCjrxm()[i];
					} else {
						p = "P" + (yeCount + 1);
						yep = "p" + (yeCount + 1);
						yedate = BookUtil.dateCommandYear(command.getCsrq()[i]);
					}
					
					String ifinf = "";
					if(StringUtils.isNotEmpty(command.getHkgs()[0]) && "MF".equals(command.getHkgs()[0])) {
						ifinf = "NOINF"; // 如果航空公司为厦航，婴儿姓名后面不需要INF
					}
					seat.addInfant(yedate, p, command.getCjrxm()[i],ifinf);
					
					String yexm = BookUtil.mhPingying(command.getCjrxm()[i]);
	
					Map<String,String> otherMap = new HashMap<String,String>();
					otherMap.put("YEP", yep);
					otherMap.put("YEXM", yexm);
					otherMap.put("YECSRQ", BookUtil.dateCommandTime(command.getCsrq()[i]));
					command.setOtherMap(otherMap);
					commandUtil.addInfoAirSeg(command);
					
					yeCount ++;
				}
			}

			//三方协议号
			SpellOtherCommand.addSfxyh(command, seat);
			
			//添加其他指令项
			SpellOtherCommand.addCTCT(command, seat);
			
			//添加儿童编码关联成人PNR编码
			SpellOtherCommand.addSSR_OTHS(command, seat);

			/**
			 * 预留时限逻辑 方式1： 
			 * 1，如果从下单时间点向后推两个小时+30分钟超过了起飞时间的话，就从起飞时间点向前推40分钟作为预留时限
			 * 2，如果从下单时间点向后推两个小时+30分钟没有超过起飞时间的话，就从下单时间点向后推2个小时
			 * 
			 * 方式2：
			 * 如果起飞时间在当天的，订票时间在飞机起飞2小时以前的，预留2小时出票时限； 
			 * 订票时间在飞机起飞2小时以内的，预留40分钟出票时限；
			 * 如果订票时间在飞机起飞40分钟内不能进行订票
			 * 如果是非当天的，订票时限应设置为航班起飞前一天的23:59分 
			 * 参数控制： 为1的时候是使用方式2，为0的时候是使用方式1 默认是方式1
			 */
			BookUtil orderutil = new BookUtil();
			if ("1".equals(command.getYl_timetype())) {
				seat.setTimelimit(orderutil.tkt2(command.getCfdate()[0], command.getCfsj()[0].replace(":", ""), command
								.getBookType()));
			} else {
				// 设置停留时间需要计算，出发日期+出发时间-分钟，-130是3小时
				seat.setTimelimit(orderutil.tktl(command.getCfdate()[0], command.getCfsj()[0].replace(":", ""), command
								.getBookType()));
			}
			pnrnr = seat.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			// IBE预订
//			if ("1".equals(command.getBookType())) {
//				commonService.countSyll(command.getDpyid(), "BOOK", "1");
//			}
		}
	}
	
	public String getPnrnr() {
		return pnrnr;
	}

	public void setPnrnr(String pnrnr) {
		this.pnrnr = pnrnr;
	}
}
