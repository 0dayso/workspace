package cn.vetech.vedsb.jp.entity.b2bsz;

import java.util.Map;
import java.util.TreeMap;

import cn.vetech.vedsb.utils.DictItem;

public class JpB2bZfzhEnum {
	
	/** 汇付账号状态 */
	public static class HFZT{
		public static DictItem HF1 = new DictItem("1", "钱管家信用账户");
		public static DictItem HF2 = new DictItem("2", "钱管家付款账户");
		public static Map<String, DictItem> dataMap = toMap(HF1, HF2);
	}
	
	/** 易宝账号状态 */
	public static class YBZT{
		public static DictItem YB1 = new DictItem("3", "易宝会员");
		public static DictItem YB2 = new DictItem("4", "易宝信用账户");
		public static Map<String, DictItem> dataMap = toMap(YB1, YB2);
	}
	
	/** 御航宝账号状态 */
	public static class YHZT{
		public static DictItem YH1 = new DictItem("00", "默认账户");
		public static DictItem YH2 = new DictItem("01", "额度账户");
		public static DictItem YH3 = new DictItem("02", "现金账户");
		public static Map<String, DictItem> dataMap = toMap(YH1, YH2, YH3);
	}
	
	/**
	 * 把对象转为Map
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

}
