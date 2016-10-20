package cn.vetech.vedsb.platpolicy;

import org.vetech.core.business.tag.Function;


public class GetPolicyUtil {
	//将票价后的小数去掉
	public static String getIntStr(double pj){
		try{
			return Function.round(pj+"", "0");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
	//将票价后的小数去掉
	public static String getIntStr(String pj){
		try{
			return Function.round(pj, "0");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
}
