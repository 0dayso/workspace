package cn.vetech.vedsb.platpolicy.jzcp;
 
import org.apache.commons.lang.StringUtils;
/**
 * 
 *用于b2c出票后台业务逻辑异常
 * <功能详细描述>
 * 
 * @author  zhanglei
 * @version  [版本号, Apr 18, 2011]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class B2bnewException extends Exception {
	private static final long serialVersionUID = -4009706488283016455L;

	public B2bnewException(String message) {
		super(StringUtils.trimToEmpty(message) );
	}
	public B2bnewException(Exception e) {
		super(e);
	}
}
