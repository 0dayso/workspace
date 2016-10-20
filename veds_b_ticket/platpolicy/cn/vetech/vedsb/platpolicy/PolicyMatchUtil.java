package cn.vetech.vedsb.platpolicy;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;



public class PolicyMatchUtil {
	  /**
     * 计算采购净价
     * @param pj_zdj 账单价
     * @param pj_cpfd 产品返点
     * @param pj_gyld 供应留点
     * @param pj_ptld 平台留点
     * @param pj_cplq 产品留钱
     * @param pj_gylq 供应留钱
     * @param pj_ptlq 平台留钱
     * @param pj_pttd 平台贴点
     * @param pj_pttq 平台贴钱
     * @param ve_cspz 参数
     * @return double [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static double calculateCgjj(double pj_zdj, double pj_cpfd, double pj_gyld,double pj_ptld, double pj_cplq, double pj_gylq, double pj_ptlq, double pj_pttd,double pj_pttq, Map<String, String> paramMap) {
        
        // a = (账单价+销售服务费)*((产品返点-供应留点-平台留点)+平台贴点)-(产品留钱+供应留钱+平台留钱 - 平台贴钱)
        double a = Arith.sub(Arith.mul(pj_zdj, Arith.add(Arith.sub(Arith.sub(pj_cpfd, pj_gyld),pj_ptld), pj_pttd)), Arith.sub(Arith.add(Arith.add(pj_cplq, pj_gylq), pj_ptlq),pj_pttq));
        
        // 采购净价=(账单价+销售服务费)-参数1022精确并舍入(a)
        return Arith.sub(pj_zdj, roundForCgFd(a, paramMap));
    }
    
    /**
     * <采购返点舍入>
     * 
     * @param dNum 需人舍入的数字
     * @param ve_cspz 传入参数
     * @return double [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static double roundForCgFd(double dNum, Map<String, String> paramMap) {
        int round = 9;
        int calc = 1;
        if (org.apache.commons.collections.MapUtils.isNotEmpty(paramMap)) {
            String csValue = paramMap.get("1022");
            
            if (StringUtils.isNotBlank(csValue) && csValue.contains(",")) {
                String ydhm = csValue.split(",")[0];
                String yw = csValue.split(",")[1];
                if (StringUtils.isNotBlank(ydhm) && NumberUtils.isDigits(ydhm)) {
                    int iYdhm = NumberUtils.toInt(ydhm);
                    if (iYdhm >= 0 && iYdhm <= 9) {
                        round = iYdhm;
                    }
                }
                if (StringUtils.isNotBlank(yw) && NumberUtils.isDigits(yw)) {
                    int iYw = NumberUtils.toInt(yw);
                    if (iYw >= 0 && iYw <= 2) {
                        calc = iYw;
                    }
                }
            }
            
        }
        return round(dNum, round, calc);
    }
    
    /**
     * 通用舍入
     * 
     * @param dNum 舍入数字
     * @param round 舍入方式，如4表示4舍5入
     * @param calc 精度
     * @return double [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    private static double round(double dNum, int round, int calc) {
        
        if (String.valueOf(dNum).indexOf(".") == -1) {
            return dNum;
        }
        int iTmp = 1;
        for (int i = 1; i <= calc; i++) {
            iTmp *= 10;
        }
        dNum = dNum * iTmp;
        String strNum = String.valueOf(dNum);
        String[] arrNum = strNum.split("\\.");
        dNum = NumberUtils.toInt(arrNum[0]);
        int first = NumberUtils.toInt(arrNum[1].substring(0, 1));
        if (first > round) {
            dNum++;
        }
        return dNum / iTmp;
    }
    
    /**
     * 根据当天提取有效的时间范围 逻辑：根据当天判断当天是工作日还是非工作日，来进行判断
     * 
     * @param datetime 08:00-22:00/09:00-18:00
     * @return 格式：08:00-22:00
     */
    public static String getValidDatetime(String datetime) {
        
        if (StringUtils.isBlank(datetime)) return "";
        
        String ret = "";
        String week = VeDate.getWeekz1(VeDate.getStringDateShort());
        String a [] = datetime.split("/");
        
        if ("六日".contains(week)) {
        	if(a.length>1){
        		ret = a[1];
        	}else{
        		ret = a[0];
        	}
        } else {
            ret = a[0];
        }
        
        return ret;
    }
    
    /**
     * CPS票面销售价是否相符转换成电商页面显示
     * 
     * @param pmxsjxf
     * @return [参数说明]
     */
    public static String convertPmxsjxf(String pmxsjxf) {
        if (StringUtils.isNotBlank(pmxsjxf)) {
            if ("0".equals(pmxsjxf)) {
                pmxsjxf = "1";// 不相符
            } else {
                pmxsjxf = "0";// 相符
            }
        }
        return pmxsjxf;
    }
}
