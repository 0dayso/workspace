package cn.vetech.vedsb.pay;

import javax.servlet.http.HttpServletRequest;

import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;

/**
 * 
 * 支付签约操作
 * 
 * @author houya
 * @version [版本号, Apr 24, 2011]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public interface ZfSign {
	/**
	 * 签约
	 * 
	 * @param request
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	String sign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh);

	String updatesign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh);

	String searchsign(JpB2bZfzh jpB2bZfzh) throws Exception;
	
	String unsign(JpB2bZfzh jpB2bZfzh);
	
}
