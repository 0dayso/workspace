package cn.vetech.vedsb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.utils.VeDate;

public class PlatCode {

	/**
	 * CPS <b style="color:red">10100000</b>
	 */
	public final static String CPS = "10100000";
	public final static String PM = "10100001";
	public final static String JR = "10100002";
	public final static String B51 = "10100003";
	public final static String NA517 = "10100004";
	public final static String BT = "10100005";
	public final static String WY = "10100006";
	public final static String YI8 = "10100007";
	public final static String KKKK = "10100008";
	public final static String YX = "10100009";

	public final static String QN = "10100010";
	/**
	 * 淘宝 <b style="color:red">10100011</b>
	 */
	public final static String TB = "10100011";
	public final static String KX = "10100012";
	public final static String XC = "10100050";
	public final static String TC = "10100024";
	public final static String ZHTD = "10100014";
	public final static String PP = "10100015";

	public final static String C380 = "10100016";
	public final static String LH800 = "10100017";
	public final static String E91 = "10100018";
	public final static String GSS = "10100019";
	public final static String ET361 = "10100021";
	public final static String TDX = "10100023";

	// 直K
	public final static String ZK = "10100070";
	// C站
	public final static String CZ = "10100071";
	// NFD
	public final static String NFD = "10100072";

	public final static String ZYPT = "10002011";
	public final static String ZYTJ = "10002012";
	public final static String ZYKW = "10002013";
	public final static String ZYMP = "10002014";
	public final static String ZYTS = "10002015";

	public final static String Z = "Z";
	public final static String G = "G";
	public final static String P = "P";
	public final static String N = "N";
	public final static String T = "T";
	public final static String B = "B";

	// C站数据来源代码   1 抓取的 2B2T的
	public final static int[] CZSOURCE = { 1, 2 };

	public static String getPtname(String ptzcbs) {
		if (PlatCode.PM.equals(ptzcbs)) {
			return "票盟";
		} else if (PlatCode.JR.equals(ptzcbs)) {
			return "今日";
		} else if (PlatCode.B51.equals(ptzcbs)) {
			return "51BOOK";
		} else if (PlatCode.NA517.equals(ptzcbs)) {
			return "517NA";
		} else if (PlatCode.BT.equals(ptzcbs)) {
			return "百拓";
		} else if (PlatCode.WY.equals(ptzcbs)) {
			return "网赢";
		} else if (PlatCode.YI8.equals(ptzcbs)) {
			return "八千翼";
		} else if (PlatCode.YX.equals(ptzcbs)) {
			return "易行";
		} else if (PlatCode.QN.equals(ptzcbs)) {
			return "去哪儿";
		} else if (PlatCode.TB.equals(ptzcbs)) {
			return "淘宝";
		} else if (PlatCode.KX.equals(ptzcbs)) {
			return "酷讯";
		} else if (PlatCode.PP.equals(ptzcbs)) {
			return "鹏朋";
		} else if (PlatCode.C380.equals(ptzcbs)) {
			return "380";
		} else if (PlatCode.E91.equals(ptzcbs)) {
			return "一九易 ";
		} else if (PlatCode.ET361.equals(ptzcbs)) {
			return "翔游";
		} else if (PlatCode.XC.equals(ptzcbs)) {
			return "携程";
		} else if (PlatCode.TC.equals(ptzcbs)) {
			return "同城";
		} else if (PlatCode.ZK.equals(ptzcbs)) {
			return "直K";
		} else if (PlatCode.CZ.equals(ptzcbs)) {
			return "C站";
		} else if (PlatCode.NFD.equals(ptzcbs)) {
			return "NFD";
		} else if (PlatCode.CPS.equals(ptzcbs)) {
			return "CPS";
		} else if (PlatCode.KKKK.equals(ptzcbs)) {
			return "KKKK";
		} else if (PlatCode.ZHTD.equals(ptzcbs)) {
			return "纵横天地";
		} else if (PlatCode.LH800.equals(ptzcbs)) {
			return "联合800";
		} else if (PlatCode.GSS.equals(ptzcbs)) {
			return "GSS";
		} else if (PlatCode.TDX.equals(ptzcbs)) {
			return "天地行";
		}
		return ptzcbs;
	}

	/**
	 * 平台简拼---平台简拼：票盟：PM；今日：JR；51book:5B；百拓：BT；网赢：WY；8000yi：8Y；易行：YX；去哪:QN;淘宝：TB；酷讯：KX；380:38；19E:9E；翔游：XY；
	 * 
	 * @param ptzcbs
	 * @return
	 */
	public static String getPtCode(String ptzcbs) {
		if (PlatCode.PM.equals(ptzcbs)) {
			return "PM";
		} else if (PlatCode.JR.equals(ptzcbs)) {
			return "JR";
		} else if (PlatCode.B51.equals(ptzcbs)) {
			return "5B";
		} else if (PlatCode.NA517.equals(ptzcbs)) {
			return "5N";
		} else if (PlatCode.BT.equals(ptzcbs)) {
			return "BT";
		} else if (PlatCode.WY.equals(ptzcbs)) {
			return "WY";
		} else if (PlatCode.YI8.equals(ptzcbs)) {
			return "8Y";
		} else if (PlatCode.YX.equals(ptzcbs)) {
			return "YX";
		} else if (PlatCode.QN.equals(ptzcbs)) {
			return "QN";
		} else if (PlatCode.TB.equals(ptzcbs)) {
			return "TB";
		} else if (PlatCode.KX.equals(ptzcbs)) {
			return "KX";
		} else if (PlatCode.PP.equals(ptzcbs)) {
			return "PP";
		} else if (PlatCode.C380.equals(ptzcbs)) {
			return "38";
		} else if (PlatCode.E91.equals(ptzcbs)) {
			return "9E";
		} else if (PlatCode.ET361.equals(ptzcbs)) {
			return "XY";
		} else if (PlatCode.XC.equals(ptzcbs)) {
			return "XC";
		} else if (PlatCode.TC.equals(ptzcbs)) {
			return "TC";
		} else if (PlatCode.ZK.equals(ptzcbs)) {
			return "ZK";
		} else if (PlatCode.CZ.equals(ptzcbs)) {
			return "CZ";
		} else if (PlatCode.NFD.equals(ptzcbs)) {
			return "NF";
		}else if (PlatCode.CPS.equals(ptzcbs)) {
			return "CPS";
		}
		// 取后2位
		return "";
	}

	private static Map<String, String> map = new TreeMap<String, String>();
	static {
		map.put("1000201", "Z");// 1000201 自有 Z
		map.put("1000202", "G");// 1000202 官网 G
		map.put("1000203", "P");// 1000203 平台 P
		map.put("1000204", "N");// 1000204 NFD N
		map.put("1000205", "T");// 1000205 旗舰店  T
		map.put("1000206", "B");// 1000206 航司B2T B
	}

	private static Map<String, String> zymap = new TreeMap<String, String>();
	static {
		zymap.put("10002011", "普通");
		zymap.put("10002012", "特价");
		zymap.put("10002013", "K位");
		zymap.put("10002014", "免票");
		zymap.put("10002015", "特殊");
	}

	/**
	 * 获得政策渠道
	 * 
	 * @return
	 */
	public static Map<String, String> getZcqd() {
		return map;
	}

	/**
	 * 获得自由政策的名称
	 * 
	 * @return
	 */
	public static Map<String, String> getZyzc() {
		return zymap;
	}

	public static String getCitymul(String city) {
		if ("BJS".equals(city)) {
			return "PEK";
		} else if ("SIA".equals(city)) {
			return "XIY";
		} else if ("HHA".equals(city)) {
			return "CSX";
		} else {
			return city;
		}
	}

	public static List<String> sameCity(String city) {
		List<String> tmpCity = new ArrayList<String>();
		if ("NAY/PEK/BJS".contains(city)) {
			tmpCity.add("NAY");
			tmpCity.add("PEK");
		} else if ("SHA/PVG".contains(city)) {
			tmpCity.add("SHA");
			tmpCity.add("PVG");
		} else if ("SIA/XIY".contains(city)) {
			tmpCity.add("XIY");
		} else if ("CAN".equals(city)) {
			tmpCity.add("CAN");
			tmpCity.add("FUO");
		} else {
			tmpCity.add(city);
		}
		return tmpCity;
	}

	/**
	 * 判断当前时间是否在生效时间段内
	 * 
	 * @param sxsj
	 *            00:00-08:00,10:00-17:00
	 * @return
	 */
	public static boolean isWorkTime(String sxsj) {
		if (StringUtils.isBlank(sxsj)) {
			return true;
		}
		String[] s = sxsj.split(",");
		String nt = VeDate.getUserDate("HH:mm");
		for (String o : s) {
			String[] o1 = o.split("-");
			if (o1.length == 2) {
				if (nt.compareTo(o1[0]) >= 0 && nt.compareTo(o1[1]) <= 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 系统定义的截断表的时间，这个时间不能进行任何其他操作
	 * 
	 * @return
	 */
	public static boolean isTruncateTime() {
		String nt = VeDate.getUserDate("HH:mm");
		if (nt.compareTo("03:00") >= 0 && nt.compareTo("03:30") < 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 运行超出工作时间范围下架政策的时间
	 * 
	 * @return
	 */
	public static boolean runWorkTimeout() {
		if (isTruncateTime()) {
			return false;
		}

		String hhmm = VeDate.getUserDate("HH:mm");
		// boolean wkt = hhmm.compareTo("08:00") >= 0 && hhmm.compareTo("18:00") <= 0;
		boolean wkt = hhmm.compareTo("01:00") >= 0 && hhmm.compareTo("18:00") <= 0;
		if (!wkt) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * CA航空公司舱位中文对应的舱位代码
	 * 
	 * @param type
	 * @return
	 */
	public static String[] getCaCwdm(String type) {
		String TDcw[] = { "F", "P", "A" };
		String GWcw[] = { "C", "D", "Z", "J" };
		String QJcw[] = { "Y" };
		String ZKcw[] = { "B", "M", "H", "K", "L", "Q" };
		if ("头等".equals(type)) {
			return TDcw;
		} else if ("公务".equals(type)) {
			return GWcw;
		} else if ("全价".equals(type)) {
			return QJcw;
		} else if ("折扣".equals(type)) {
			return ZKcw;
		}
		return null;
	}
}
