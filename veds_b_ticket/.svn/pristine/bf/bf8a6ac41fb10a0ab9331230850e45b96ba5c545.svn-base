package cn.vetech.vedsb.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.vetech.core.modules.utils.VeDate;

/**
 * 日志类
 * 
 * @author zhanglei
 *
 */
public class LogUtil {

	/**
	 * 数据加工的日志类
	 */
	private static Logger dataLogger = LoggerFactory.getLogger("dataLog");

	/**
	 * 
	 * 导单专用日志 ,需要定义好日志文件保存的目录 dir
	 * 
	 * @param filename
	 *            保存的文件名，可以不传，那么默认为INFO
	 * @return
	 */
	public static Logger getImpDd(String... filename) {
		String filename_ = "INFO";
		String dir = "daodan";// 目录是logs/daodan/
		if (filename.length > 0) {
			filename_ = filename[0];
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}

	public static Logger getZfzhQy(String... filename) {
		String filename_ = "INFO";
		String dir = "zhzhqy";// 目录是logs/zhzhqy/
		if (filename.length > 0) {
			filename_ = filename[0];
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}

	/**
	 * B2C TB B2B代购日志
	 * 
	 * @param filename
	 * @return
	 */
	public static Logger getDgrz(String... filename) {
		String filename_ = "INFO" + "_" + VeDate.getStringDateShort();
		;
		String dir = "dgxt";// 目录是logs/zhzhqy/
		if (filename.length == 1) {
			filename_ = filename[0] + "_" + VeDate.getStringDateShort();
		} else if (filename.length == 2) {
			if (StringUtils.isBlank(filename[1])) {
				filename[1] = "INFO";
			}
			filename_ = filename[0] + "/" + filename[1] + "_" + VeDate.getStringDateShort();
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}

	/**
	 * 淘宝供应日志
	 * 
	 * @param filename
	 * @return
	 */
	public static Logger getGyrz(String... filename) {
		String filename_ = "INFO" + "_" + VeDate.getStringDateShort();
		String dir = "gyrz";// 目录是logs/zhzhqy/
		if (filename.length == 1) {
			filename_ = filename[0] + "_" + VeDate.getStringDateShort();
		} else if (filename.length == 2) {
			if (StringUtils.isBlank(filename[1])) {
				filename[1] = "INFO";
			}
			filename_ = filename[0] + "/" + filename[1] + "_" + VeDate.getStringDateShort();
			;
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}

	/**
	 * 退票处理日志
	 * 
	 * @param filename
	 * @return
	 */
	public static Logger getTprz(String... filename) {
		String filename_ = "INFO" + "_" + VeDate.getStringDateShort();
		String dir = "TPCL";// 目录是logs/zhzhqy/
		if (filename.length == 1) {
			filename_ = filename[0] + "_" + VeDate.getStringDateShort();
		} else if (filename.length == 2) {
			if (StringUtils.isBlank(filename[1])) {
				filename[1] = "INFO";
			}
			filename_ = filename[0] + "/" + filename[1] + "_" + VeDate.getStringDateShort();
			;
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}

	/**
	 * 记录自动出票日志
	 * @param filename
	 * @return
	 */
	public static Logger getAutoEtdz(String... filename) {
		String filename_ = "ETDZ";
		String dir = "autoetdz";// 目录是logs/autoetdz/
		if (filename.length == 1) {
			filename_ = filename[0] + "_" + VeDate.getStringDateShort();
		} else if (filename.length == 2) {
			if (StringUtils.isBlank(filename[1])) {
				filename[1] = "ETDZ";
			}
			filename_ = filename[0] + "/" + filename[1];
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}
	
	
	/**
	 * 记录自动退票日志
	 * @param filename
	 * @return
	 */
	public static Logger getTrfd(String... filename) {
		String filename_ = "TRFD";
		String dir = "trfd";// 目录是logs/trfd/
		if (filename.length == 1) {
			filename_ = filename[0] + "_" + VeDate.getStringDateShort();
		} else if (filename.length == 2) {
			if (StringUtils.isBlank(filename[1])) {
				filename[1] = "TRFD";
			}
			filename_ = filename[0] + "/" + filename[1];
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}
	
	/**
	 * 记录自动补位的日志
	 * @param filename
	 * @return
	 */
	public static Logger getZdbwLog(String... filename) {
		String filename_ = "XEFLIGHT";
		String dir = "xeflight";// 目录是logs/autoetdz/
		if (filename.length == 1) {
			filename_ = filename[0] + "_" + VeDate.getStringDateShort();
		} else if (filename.length == 2) {
			if (StringUtils.isBlank(filename[1])) {
				filename[1] = "XEFLIGHT";
			}
			filename_ = filename[0] + "/" + filename[1];
		}
		MDC.put("filename", dir + "/" + filename_);
		return dataLogger;
	}
	
	
	/**
	 * 记录接收到的Q信息
	 * 
	 * @return
	 */
	public static Logger getPidinfo(String catalog) {
		catalog = StringUtils.trimToEmpty(catalog);
		String dir = "pidinfo";
		String today = VeDate.getUserDate("yyyyMMdd");
		MDC.put("filename", dir + "/pidinfo" + catalog + today);
		return dataLogger;
	}
	
	/**
	 * 记录自动出票日志
	 * @param filename
	 * @return
	 */
	public static Logger getAutoCp() {
		String today = VeDate.getUserDate("yyyyMMdd");
		MDC.put("filename",  "autocp/" + today);
		return dataLogger;
	}
	
	/**
	 * 记录自动出票日志
	 * @param filename
	 * @return
	 */
	public static Logger getAutoCp(String gngj) {
		String today = VeDate.getUserDate("yyyyMMdd");
		if("0".equals(gngj)){
			today+="_GJ";
		}
		MDC.put("filename",  "autocp/" + today);
		return dataLogger;
	}
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			LogUtil.getAutoCp("1").error(
					"<INPUT><COMMAND>VETRFD</COMMAND><PARA><USER>16072810380935</USER><OPERATIONTYPE>AUTO</OPERATIONTYPE><PREVIEW>0</PREVIEW><PRINTER>1</PRINTER><REFUNDNO></REFUNDNO><TICKETNO>731-9135941208</TICKETNO><SECONDTICKETNO></SECONDTICKETNO><CHECK></CHECK><OFFICE>SHA384</OFFICE><CONJUNCTION></CONJUNCTION><COUPONNO></COUPONNO><CROSSREFOUND></CROSSREFOUND><ET></ET><DEDUCTION></DEDUCTION><COMMISSION></COMMISSION><CREDITCARD></CREDITCARD><TAX></TAX><NETREFOUND></NETREFOUND><MONEYTYPE></MONEYTYPE><COUNTRY>D</COUNTRY><PASSENGER></PASSENGER><FORMOFPAYMENT></FORMOFPAYMENT><REMARK></REMARK></PARA></INPUT>");
		}
	}
}
