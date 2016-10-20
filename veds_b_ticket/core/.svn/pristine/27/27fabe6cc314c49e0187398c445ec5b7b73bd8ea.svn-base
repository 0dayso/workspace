package org.vetech.core.modules.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author zl
 * 
 */
public class JsonUtil {


	// list to json
	public static String listToJson(List list) {
		if (list == null || list.size() < 1) {
			return "{\"total\":" + 0 + ",\"rows\":[{}]}";
		}
		JSONArray ja = JSONArray.fromObject(list);
		String a = "{\"total\":" + list.size() + ",\"rows\":" + ja.toString() + "}";
		return a;
	}

	// 假的
	public static String mapToJson(Map map) {
		if (map == null || map.size() < 1) {
			return "{\"total\":" + 0 + ",\"rows\":[{}]}";
		}
		Set s = map.keySet();
		Iterator it = s.iterator();
		StringBuffer buffer = new StringBuffer();
		while (it.hasNext()) {
			String key = (String) it.next();
			String time_user = (String) map.get(key);
			String[] time_users = time_user.split(",");
			if (StringUtils.isBlank(buffer.toString())) {
				buffer.append("{bh:'").append(key).append("'").append(",username:'").append(time_users[1]).append("'}");
			} else {
				buffer.append(",").append("{bh:'").append(key).append("'").append(",username:'").append(time_users[1])
						.append("'}");
			}

		}
		String a = "{\"total\":" + map.size() + ",\"rows\":[" + buffer.toString() + "]}";
		return a;
	}
	
	public static String mapToJson2(Map map){
		Set s = map.keySet();
		Iterator it = s.iterator();
		StringBuffer buffer = new StringBuffer();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) map.get(key);
			if (StringUtils.isBlank(buffer.toString())) {
				buffer.append("{\"").append(key).append("\":\"").append(value).append("\",");
			}else{
				buffer.append("\"").append(key).append("\":\"").append(value).append("\",");
			}
		}
		String json = buffer.toString();
		json = json.substring(0, json.length()-1)+"}";
		return json;
	}
	
	// json to list
	public static List jsonToList(String json, Class T) {
		if (json == null) return null;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("JSonParser", json);
		List list = JSONArray.toList(jsonObject.getJSONArray("JSonParser"));
		List listArray = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			MorphDynaBean beana = (MorphDynaBean) list.get(i);
			try {
				Object o = T.newInstance();
				org.apache.commons.beanutils.BeanUtils.copyProperties(o, beana);
				listArray.add(o);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return listArray;
	}

	// begin add by wangwenming 2011-01-04 for:数据转换，将List转换成json,详见文档<<快速预订优化方案20101230(汪文明).doc>>

	/**
	 * 将List转换成json数据，并附上错误码，错误描述,支持子数据拆分 <功能详细描述>
	 * 
	 * @param list
	 *            对象集合
	 * @param retcode
	 *            返回码
	 * @param msg
	 *            消息
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getJsonData(List list, String retcode, String msg) throws Exception {
		StringBuffer retmsg = new StringBuffer("");
		retmsg.append("{" + "\"retcode\":" + retcode);
		retmsg.append("," + "\"msg\":\"" + msg + "\"");

		if (list != null && !list.isEmpty()) {
			retmsg.append("," + "\"data\":" + "[");

			Iterator it = list.iterator();

			try {
				while (it.hasNext()) {
					retmsg.append(JSONObject.fromObject(it.next()));
					retmsg.append(",");
				}
				retmsg.delete(retmsg.length() - 1, retmsg.length());
				retmsg.append("]}");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retmsg.append("}");
		}

		// response.setContentType("text/html; charset=UTF-8");
		// String unstr = java.net.URLDecoder.decode(retmsg.toString(), "UTF-8");

		return retmsg.toString();
	}
}
