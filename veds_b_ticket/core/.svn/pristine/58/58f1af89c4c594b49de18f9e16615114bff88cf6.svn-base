package org.vetech.core.business.pid.api.xepassenger;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
/**
 * 从PNR中XE乘机人 如果传入参数要求把PNR中的所有人都XE掉的话，就会转换成XEPNR指令把整个PNR 删除掉
 * @author wangshengliang
 *
 */
public class XePassenger {
	
	public static final String XECJR_SUCCESS = "0";// XE乘机人成功
	
	public static final String XEPNR_SUCCESS = "1";// XEPNR成功

	public static final String ERROR = "-1";// 表示传入参数在检测过程中发现数据错误，或不符合执行环境

	public static final String FAILURE = "-2";// 表示指令在执行过程中发生的错误

	public static final String NOXE = "-3";// 表示也是表示指令执行过程中发生的错误，但这个错误ASMS认为是不影响其流程继续运行的因素
	
	public String xepassenger(XePassengerParam param) throws EtermException {
		valid(param);
		String bstrInputXML = param.toXml();
		System.out.println(bstrInputXML);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String data = etermService.xepassenger(bstrInputXML);
		// STATUS DESCRIPTION
		if (StringUtils.isBlank(data)) {
			throw new EtermException("调用PID接口返回空");
		}
		return data;
	}
	
	private void valid(XePassengerParam param) throws EtermException {
		if (StringUtils.isBlank(param.getPnrno())) {
			throw new EtermException("PNR不能为空");
		}
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
	}
	
}
