package cn.vetech.vedsb.utils;

import java.util.Map;
import java.util.TreeMap;

import org.vetech.core.business.tag.Function;


/**
 * 数据字典定义
 * 
 * @author zhanglei
 *
 */
public class DictEnum {

	/** 销售退票状态 */
	public static class XSTFPZT {
		/** 0 已申请 */
		public static DictItem YSQ = new DictItem("0", "已申请");
		/** 1 已审核 */
		public static DictItem YSH = new DictItem("1", "已审核");
		/** 2 已办理 */
		public static DictItem YBL = new DictItem("2", "已办理");
		/** 9 已取消 */
		public static DictItem YQX = new DictItem("9", "已取消");

		public static Map<String, DictItem> dataMap = toMap(YSQ, YSH, YBL, YQX);
	}

	/** 采购退票状态 */
	public static class CGTFPZT {
		/** 0 待提交 */
		public static DictItem DTJ = new DictItem("0", "待提交");
		/** 1 提交中 */
		public static DictItem TJZ = new DictItem("1", "提交中");
		/** 2 已提交 */
		public static DictItem YTJ = new DictItem("2", "已提交");
		/** 3已办理 */
		public static DictItem DBL = new DictItem("3", "已办理");
		/** 4 已拒单 */
		public static DictItem YJD = new DictItem("4", "已拒单");
		/** 9 已取消 */
		public static DictItem YQX = new DictItem("9", "已取消");

		public static Map<String, DictItem> dataMap = toMap(DTJ, TJZ, YTJ, DBL, YJD, YQX);
	}
	
	/** 机票订单状态 */
	public static class JPDDZT {
		/** 0待确认 */
		public static DictItem DQR = new DictItem("0", "待确认");
		/** 1 已订座 */
		public static DictItem YDZ = new DictItem("1", "已订座");
		/** 2 出票中 */
		public static DictItem CPZ = new DictItem("2", "出票中");
		/** 3已完成*/
		public static DictItem YWC = new DictItem("3", "已完成");
		/** 4 客户消 */
		public static DictItem KHX = new DictItem("4", "客户消");
		/** 5已取消 */
		public static DictItem YQX = new DictItem("5", "已取消");

		public static Map<String, DictItem> dataMap = toMap(DQR, YDZ, CPZ, YWC, KHX, YQX);
	}
	/**航程类型:1单程、2往返、3联程、4缺口*/
	public static class HCLX{
		/** 1 单程 */
		public static DictItem DC = new DictItem("1", "单程");
		/** 2 往返 */
		public static DictItem WF = new DictItem("2", "往返");
		/** 3联程*/
		public static DictItem LC = new DictItem("3", "联程");
		/** 4缺口 */
		public static DictItem QK = new DictItem("4", "缺口");

		public static Map<String, DictItem> dataMap = toMap(DC, WF, LC, QK);
	}
	/** 机票改签状态 */
	public static class JPGQZT {
		/** 0待确认 */
		public static DictItem DQR = new DictItem("0", "待确认");
		/** 1 已确认 */
		public static DictItem YQR = new DictItem("1", "已确认");
		/** 3 改签中 */
		public static DictItem GQZ = new DictItem("3", "改签中");
		/** 4已改签*/
		public static DictItem YGQ = new DictItem("4", "已改签");
		/** 7 客户消 */
		public static DictItem KHX = new DictItem("7", "客户消");
		/** 8已取消 */
		public static DictItem YQX = new DictItem("8", "已取消");

		public static Map<String, DictItem> dataMap = toMap(DQR, YQR, GQZ, YGQ, KHX, YQX);
	}
	/** 追位队列状态*/
	public static class ZWDLSQZT {
		/**0 已申请*/
		public static DictItem YSQ = new DictItem("0", "已申请");
		/**1已审核/追位中 */
		public static DictItem YSH = new DictItem("1", "已审核");
		/**2部分追位  */
		public static DictItem BFZ = new DictItem("2", "部分追位");
		/**3追位完成 */
		public static DictItem ZWC = new DictItem("3", "追位完成");
		/**4追位失败 */
		public static DictItem ZSB = new DictItem("4", "追位失败");
		/**5客户取消  */
		public static DictItem KHX = new DictItem("5", "客户消");
		/**6管理员消 */
		public static DictItem YQX = new DictItem("6", "已取消");
		
		public static Map<String, DictItem> dataMap = toMap(YSQ, YSH, BFZ, ZWC, ZSB, KHX, YQX);
	}
	/** 追位临时队列状态*/
	public static class ZWLSDLSQZT {
		/**0 已申请*/
		public static DictItem YSQ = new DictItem("0", "已申请");
		/**1已审核/追位中 */
		public static DictItem YSH = new DictItem("1", "已审核");
		/**2部分追位  */
		public static DictItem BFZ = new DictItem("2", "部分追位");
		/**3追位完成 */
		public static DictItem ZWC = new DictItem("3", "追位完成");
		/**4追位失败 */
		public static DictItem ZSB = new DictItem("4", "追位失败");
		/**5客户取消  */
		public static DictItem KHX = new DictItem("5", "客户消");
		/**6管理员消 */
		public static DictItem YQX = new DictItem("6", "已取消");
	}
	/**追位导入订单状态*/
	public static class ZWDDDRZT{
		/**0 已申请*/
		public static DictItem YSQ = new DictItem("0", "已申请");
		/**1已审核/追位中 */
		public static DictItem YSH = new DictItem("1", "已审核");
		/**2部分追位  */
		public static DictItem BFZ = new DictItem("2", "部分追位");
		/**3追位完成 */
		public static DictItem ZWC = new DictItem("3", "追位完成");
		/**4追位失败 */
		public static DictItem ZSB = new DictItem("4", "追位失败");
		/**5客户取消  */
		public static DictItem KHX = new DictItem("5", "客户消");
		/**6管理员消 */
		public static DictItem YQX = new DictItem("6", "已取消");
		
		public static Map<String, DictItem> dataMap = toMap(YSQ, YSH, BFZ, ZWC, ZSB, KHX, YQX);
	}
	/** 追位乘机人追位状态*/
	public static class ZWCJRZWZT {
		/**0 未成功*/
		public static DictItem WCG = new DictItem("0", "未成功");
		/**1追成功 */
		public static DictItem ZCG = new DictItem("1", "追成功");
		/**2已处理  */
		public static DictItem YCL = new DictItem("2", "已处理");
		/**3已取消 */
		public static DictItem YQX = new DictItem("3", "已取消");
		/**4预留 */
		public static DictItem ZSB = new DictItem("4", "预留");
	}
	/**
	 * 采购状态 0待确认 1已订座 2出票中 3已完成 4客户消
	 */
	public static class CGZT {
		/**0待确认*/
		public static DictItem DQR = new DictItem("0", "待确认");
		/**1已订座*/
		public static DictItem YDZ = new DictItem("1", "已订座");
		/**2出票中  */
		public static DictItem CPZ = new DictItem("2", "出票中");
		/** 3已完成  */
		public static DictItem YWC = new DictItem("3", "已完成");
		/**4客户消 */
		public static DictItem KHX = new DictItem("4", "客户消");
		public static Map<String, DictItem> dataMap = toMap(DQR, YDZ, CPZ, YWC, KHX);
	}
	/**
	 * 采购订单状态
	 * @author vetech
	 *0 等待支付 1 出票完成 2 待出票 5客票挂起 -1 订单取消
	 */
	public static class CGDDZT {
		/**0 等待支付*/
		public static DictItem DDZF = new DictItem("0", "等待支付");
		/**1出票完成 */
		public static DictItem CPWC = new DictItem("1", "出票完成");
		/**2 待出票  */
		public static DictItem DCP = new DictItem("2", " 待出票");
		/**2 待出票  */
		public static DictItem KPGQ = new DictItem("5", " 客票挂起");
		/**2 待出票  */
		public static DictItem DDQX = new DictItem("-1", " 订单取消");
		public static Map<String, DictItem> dataMap = toMap(DDZF, CPWC, DCP, KPGQ, DDQX);
	}
	
	/**
	 * 采购订单支付状态
	 * @author vetech
	 *
	 */
	public static class CGDDJYZT {
		/**0 初始*/
		public static DictItem CS = new DictItem("0", "初始");
		/**1支付成功 */
		public static DictItem ZFCG = new DictItem("1", "支付成功");
		/**2支付失败  */
		public static DictItem ZFSB = new DictItem("2", "支付失败");
		/**3支付中  */
		public static DictItem ZFZ = new DictItem("3", "支付中");
		/**4 退款成功 */
		public static DictItem TKCG = new DictItem("4", "退款成功");
		/**5退款中  */
		public static DictItem TKZ = new DictItem("5", "退款中");
		/**6退款失败  */
		public static DictItem TKSB = new DictItem("6", "退款失败");
		
		public static Map<String, DictItem> dataMap = toMap(CS, ZFCG, ZFSB,ZFZ,TKCG,TKZ,TKSB);
	}
	
	/**
	 * 平台日志业务类型
	 * @author vetech
	 *
	 */
	public static class PTLOGYWLX {
		/** 1001查询政策*/
		public static DictItem CZC=new DictItem("1001","查询政策");
		/** 2001下单*/
		public static DictItem XD=new DictItem("2001","下单");
		/** 2002支付通知*/
		public static DictItem ZFTZ=new DictItem("2003","支付通知");
		/** 2004出票通知*/
		public static DictItem CPTZ=new DictItem("2004","出票通知");
		/** 3001退废下单*/
		public static DictItem TFXD=new DictItem("3001","退废下单");
		/** 3002退废通知*/
		public static DictItem TFTZ=new DictItem("3002","退废通知");
		
		public static DictItem GYDDLASTDAY=new DictItem("6001","供应每天检索正常单");
		public static DictItem GYDD=new DictItem("6002","供应正常单导单");
		public static DictItem GYPHHT=new DictItem("6003","供应票号回填");
		public static DictItem GYSGDD=new DictItem("6004","供应手工导正常单");
		public static DictItem GYDDTZ=new DictItem("6005","供应订单通知");
		public static DictItem GYTFDLASTDAY=new DictItem("7001","供应每天检索退废单");
		public static DictItem GYTFD=new DictItem("7002","供应退废单导单");
		public static DictItem GYTFDSH=new DictItem("7003","供应退废单审核");
		public static DictItem GYSGTFD=new DictItem("7004","供应手工导退废单");
		public static DictItem GYTJKHTP=new DictItem("7005","供应提交客户退票");
		public static DictItem GYTFDTBLASTDAY=new DictItem("7006","供应每天同步退废单状态");
		public static DictItem GYGQDLASTDAY=new DictItem("8001","供应每天检索改签单");
		public static DictItem GYGQD=new DictItem("8002","供应改签单导单");
		public static DictItem GYGQDGQ=new DictItem("8003","供应改签单改签");
		public static DictItem GYSGGQD=new DictItem("8004","供应手工导改签单");
		
		
		public static Map<String, DictItem> dataMap = toMap(CZC, XD,ZFTZ, CPTZ, TFXD, TFTZ, GYDDLASTDAY, GYDD, GYPHHT, GYSGDD, GYDDTZ, GYTFDLASTDAY, GYTFD, GYTFDSH, GYSGTFD, GYTJKHTP, GYTFDTBLASTDAY, GYGQDLASTDAY, GYGQD, GYGQDGQ, GYSGGQD);
	}
	
	/**
	 * 平台通知返回相关状态
	 * @author vetech
	 *
	 */
	public static class ReturnPtStatus{
		public static DictItem YDCG = new DictItem("1","预订成功");
		public static DictItem ZFWC = new DictItem("10","支付完成");
		public static DictItem WFCP = new DictItem("12","无法出票");
		public static DictItem CPWC = new DictItem("13","出票完成");
		public static DictItem GHBMCPWC = new DictItem("14","更换编码出票完成");
		public static DictItem TPCLZ = new DictItem("21","退票处理中");
		public static DictItem WFTP = new DictItem("22","无法退票");
		public static DictItem FPCLZ = new DictItem("31","废票处理中");
		public static DictItem WFFP = new DictItem("32","无法废票");
		public static DictItem WCTFP = new DictItem("90","完成退废票");
		public static DictItem JXQXTK = new DictItem("99","交易取消退款");
	}
	
	/**
	 * 平台日志订单类型
	 * @author vetech
	 */
	public static class PTLOGDDLX {
		/**1 正常单*/
		public static DictItem ZC = new DictItem("1", "正常单");
		/**2 退废单 */
		public static DictItem TF = new DictItem("2", "退废单");
		/**3 改签单  */
		public static DictItem GQ = new DictItem("3", "改签单");
		
		public static Map<String, DictItem> dataMap = toMap(ZC, TF, GQ);
	}
	
	/**
	 * 平台日志采购供应
	 * @author vetech
	 */
	public static class PTLOGCGGY {
		/**1 采购*/
		public static DictItem CG = new DictItem("1", "采购");
		/**2 供应 */
		public static DictItem GY = new DictItem("2", "供应");
		
		public static Map<String, DictItem> dataMap = toMap(CG, GY);
	}
	
	public static class BJCPGZYXJ {
		public static DictItem CPSJ =new DictItem("yxj001","出票时间最晚");
		public static DictItem CPLX =new DictItem("yxj002","出票类型优先级");
		public static DictItem FPSJ =new DictItem("yxj003","废票时间最晚");
		public static DictItem PTLB =new DictItem("yxj004","采购平台类别");
		public static Map<String, DictItem> dataMap = toMap(CPSJ, CPLX, FPSJ, PTLB);
	}
	
	/**
	 * 航空公司b2c设置登录方式
	 * @author vetech
	 *
	 */
	public static class HKGSSZDLFS {
		public static DictItem YHM =new DictItem("0","用户名");
		public static DictItem SJHM =new DictItem("1","手机号码");
		public static DictItem DZYX =new DictItem("2","电子邮箱");
		public static DictItem JPKH =new DictItem("3","金鹏卡号");
		public static DictItem FHZY =new DictItem("4","凤凰知音");
		public static DictItem MZHY =new DictItem("5","明珠会员登录");
		public static DictItem SHDF =new DictItem("6","深航电粉账号");
		public static DictItem QBZH =new DictItem("7","钱包账号");
		public static DictItem SFZ =new DictItem("8","身份证");
		public static Map<String, DictItem> dataMap = toMap(YHM, SJHM, DZYX, JPKH, FHZY, MZHY, SHDF, QBZH, SFZ);
	}
	
	/**
	 * 票证状态
	 */
	public static class PZZT {
		public static DictItem WSH =new DictItem("0","未审核");
		public static DictItem YSH =new DictItem("1","已审核");
		public static DictItem YZF =new DictItem("2","已作废");
		public static DictItem YQX =new DictItem("3","已取消");
		public static Map<String, DictItem> dataMap = toMap(WSH, YSH, YZF,YQX);
	}
	
	/**
	 * 票证库存状态
	 */
	public static class PZKCZT {
		public static DictItem WSH =new DictItem("0","入库");
		public static DictItem YSH =new DictItem("1","未使用");
		public static DictItem YZF =new DictItem("9","报废");
		public static Map<String, DictItem> dataMap = toMap(WSH, YSH, YZF);
	}
	

	/**
	 * 行程单状态
	 * 0 在库(未发放) 1未使用 2出票 3未创建已作废未回收
	 * 4未创建已作废已回收 5已创建已作废未回收 6已创建已作废已回收
	 * 7退废票已作废未回收 8退废票已作废已回收 9报废 10已使用
	 */
	public static class XCDZT {
		public static DictItem WFF =new DictItem("0","入库");
		public static DictItem WSY =new DictItem("1","未使用");
		public static DictItem CP =new DictItem("2","出票");
		public static DictItem WCJWHS =new DictItem("3","未创建已作废未回收");
		public static DictItem WCJYHS =new DictItem("4","未创建已作废已回收");
		public static DictItem YCJWHS =new DictItem("5","已创建已作废未回收");
		public static DictItem YCJYHS =new DictItem("6","已创建已作废已回收");
		public static DictItem TFPWHS =new DictItem("7","退废票已作废未回收");
		public static DictItem TFPYHS =new DictItem("8","退废票已作废已回收");
		public static DictItem BFSL =new DictItem("9","报废");
		public static DictItem YSY =new DictItem("10","已使用");
		public static Map<String, DictItem> dataMap = toMap(WFF, WSY, CP,WCJWHS,WCJYHS,YCJWHS,YCJYHS,TFPWHS,TFPYHS,BFSL,YSY);
	}
	/**
	 * 票证追踪操作状态
	 * 1入库，2发放，3退回，4报废，5打印，6作废，7回收，8取消回收，9取消入库，10已使用
	 * @author vetech
	 *
	 */
	public static class PZZZCZLX{
		public static DictItem RK =new DictItem("1","入库");
		public static DictItem FF =new DictItem("2","发放");
		public static DictItem TH =new DictItem("3","退回");
		public static DictItem BF =new DictItem("4","报废");
		public static DictItem DY =new DictItem("5","打印");
		public static DictItem ZF =new DictItem("6","作废");
		public static DictItem HS =new DictItem("7","回收");
		public static DictItem QXHS =new DictItem("8","取消回收");
		public static DictItem QXRK =new DictItem("9","取消入库");
		public static DictItem YSY =new DictItem("10","已使用");
		public static Map<String, DictItem> dataMap = toMap(RK,FF,TH,BF,DY,ZF,HS,QXHS,QXRK,YSY);
	}
	
	/**
	 * 平台退废票失败原因
	 */
	public static class ReturnPtTfStatus{
		public static DictItem ZBNTFP = new DictItem("10001","咱不能退废票");
		public static DictItem ZBNTFPYJJ = new DictItem("10002","咱不能退废票已解决");
		public static DictItem JJTFP = new DictItem("10003","拒绝退废票");
	}
	
	/**
	 * 平台出票失败原因
	 */
	public static class ReturnPtCpStatus{
		public static DictItem CPSBPTTK = new DictItem("10001","出票失败平台退款");
		public static DictItem ZBNCP = new DictItem("10002","咱不能出票");
	}
	
	/**
	 * 把对象转为Map
	 * 
	 * @param dictItem
	 * @return
	 */
	private static Map<String, DictItem> toMap(DictItem... dictItem) {
		Map<String, DictItem> dataMap = new TreeMap<String, DictItem>();
		for (DictItem d : dictItem) {
			dataMap.put(d.getValue(), d);
		}
		return dataMap;
	}

	public static void main(String[] args) {
		System.out.println(DictEnum.XSTFPZT.YSQ.equals(XSTFPZT.YSH));
		System.out.println(CGTFPZT.dataMap.toString());
		System.out.println(Function.toJSON(Function.dictList("HKGSSZDLFS")));

//		if (DictEnum.XSTFPZT.YBL.equals(jpDd.getZt())) {
//
//		}
	}
}
