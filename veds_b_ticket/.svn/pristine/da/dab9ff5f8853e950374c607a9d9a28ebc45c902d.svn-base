package cn.vetech.vedsb.jp.jpddsz.tongcheng;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TongchengGy extends JpddGySuper{
	public static String plat = "10100024";
	
	private String URL;//接口地址
	
	private String USER;// 

	private String PASS;// shengyi456
	
	private String password;
	
	private JpDdsz jpDdsz;
	
//	private static Map<String, String> OrderIdsMap = new HashMap<String, String>();
	
	private String encode = "UTF-8";
	public TongchengGy(JpDdsz jpDdsz){
		this.jpDdsz = jpDdsz;
		this.USER = jpDdsz.getDdJkzh();
		this.PASS = jpDdsz.getDdJkmm();
		this.URL = jpDdsz.getDdJkdz();	
		this.password = MD5Tool.MD5Encode(USER+"#"+PASS);
	}

	@Override
	public boolean orderOutTicket(JpPtLog ptlb, PlatJpBean pjb) throws Exception {
		//票号回填
		String orderSerialid = pjb.getWdbh();
		
		JSONObject reqObj = new JSONObject();
		reqObj.put("Username", USER);
		reqObj.put("Password", password);
		reqObj.put("OrderSerialid", orderSerialid);
		reqObj.put("IsTicketSuccess", "T");//出票状态 T出票成功  J出票失败
		reqObj.put("Remark", "");//出票备注
		reqObj.put("DifferencePrice", "0");//出票差价	

		List<JpKhdd> JpKhddList = pjb.getJpKhddList();
		Map<String,List<JpJp>> jpListMap = pjb.getJpJpListMap();
		JSONArray ticketInfo = new JSONArray();
		for(int i=0;i<JpKhddList.size();i++){
			JpKhdd oneJpKhdd = JpKhddList.get(i);
			String tmpPnr = oneJpKhdd.getXsPnrNo();
			List<JpJp> jpList = jpListMap.get(oneJpKhdd.getDdbh());
			for(int j=0;j<jpList.size();j++){
				JpJp oneJp = jpList.get(j);
				String cjr = oneJp.getCjr();
				String tkno = oneJp.getTkno();
				JSONObject oneInfo = new JSONObject();
				oneInfo.put("PassengerName", cjr);
				oneInfo.put("Pnr", tmpPnr);
				oneInfo.put("TicketNo", tkno.replaceAll("-", ""));
				ticketInfo.add(oneInfo);
			}
		}
		reqObj.put("ticketInfo", ticketInfo);

		String reqJson = reqObj.toJSONString();
		String resJson=handle(reqJson,"4",ptlb);
		String msg = ParseTongchengGy.parseTicketNotify(resJson,ptlb);
		if(StringUtils.isBlank(msg)){
			return true;
		}
		throw new Exception("票号回填失败："+msg);
	}

	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception {
		//同程导单接口有30秒时间间隔
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		listOrder.addAll(_queryOrders(ptlb,60, ""));
		return listOrder;
	}
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb,String dateStr) throws Exception {
		//同程导单接口有30秒时间间隔
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		listOrder.addAll(_queryOrders(ptlb,24*60, dateStr));
		return listOrder;
	}
	private List<OrderBean> _queryOrders(JpPtLog ptlb,int min,String dateStr) throws Exception {
		String now = dateStr;
		if(StringUtils.isBlank(now)){
			now = VeDate.getStringDateShort();
		}
		//订单状态： N已付款待出票
		String orderStatus = "N";//默认为N
		String ksrq=VeDate.getPreMin(now, -min).replaceAll(" ", "T");//往前推60分钟
		String jsrq=now.replaceAll(" ", "T");
		JSONObject reqObj = new JSONObject();
		reqObj.put("Username", USER);
		reqObj.put("Password", password);
		reqObj.put("OrderBeginDataTime", ksrq);
		reqObj.put("OrderEndDataTime", jsrq);
		reqObj.put("OrderStatus", orderStatus);
		String reqJson = reqObj.toJSONString();

		String resJson=handle(reqJson,"1",ptlb);

		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		List<String> orderIdList=ParseTongchengGy.parseOrderIdList(resJson,ptlb);
		if(CollectionUtils.isEmpty(orderIdList)){
			return listOrder;
		}
		for(String orderId:orderIdList){
			reqObj = new JSONObject();
			reqObj.put("Username", USER);
			reqObj.put("Password", password);
			reqObj.put("OrderSerialid", orderId);
			reqJson = reqObj.toJSONString();
			//请求订单详
			resJson=handle(reqJson,"3",ptlb);
			List<OrderBean> orderList=ParseTongchengGy.parseOrderList(ptlb,resJson,jpDdsz);
			if(CollectionUtils.isEmpty(orderList)){
				continue;
			}
			listOrder.addAll(orderList);
		}
		return listOrder;
	}
	@Override
	public List<OrderBean> getByWbdh(String wbdh, JpPtLog ptlb)
			throws Exception {
		JSONObject reqObj = new JSONObject();
		reqObj.put("Username", USER);
		reqObj.put("Password", password);
		reqObj.put("OrderSerialid", wbdh);
		String reqJson = reqObj.toJSONString();
		//请求订单详
		String resJson=handle(reqJson,"3",ptlb);
		List<OrderBean> orderList=ParseTongchengGy.parseOrderList(ptlb,resJson,jpDdsz);
		return orderList;
	}
	//发送请求
	private String handle(String reqJson,String method,JpPtLog ptlb){
		//发送请求，method：1 订单列表查询；  2 确认订单；  3请求订单详；  4票号回填
		String url = URL;
		if("1".equals(method)){
			url+="/tc/getorderlist.ashx";
			ptlb.add("同程订单列表查询请求:"+ url+ " 接口名:getorderlist 参数:"+ reqJson);
		}else if("2".equals(method)){
			url+="/tc/OrderConfirm.ashx";
			ptlb.add("同程确认订单请求:"+ url+ " 接口名:OrderConfirm 参数:"+ reqJson);
		}else if("3".equals(method)){
			url+="/tc/getorderdetail.ashx";
			ptlb.add("同程订单详请求:"+ url+ " 接口名：getorderdetail 参数："+ reqJson);
		}else if("4".equals(method)){
			url+="/tc/ticketnotify.ashx";
			ptlb.add("同程回填票号请求："+ url+ " 接口名：ticketnotify 参数："+ reqJson);
		}
		String resJson = "";
		try {
			byte[] res_byte = HttpClientUtil.doPostStream(url, reqJson.getBytes(encode), null, encode, 10000, 60000);
			resJson = new String(res_byte,encode);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("请求失败，失败原因"+e.toString());
		}
		ptlb.add("接口返回\r\n"+resJson);
		return resJson;
	}
}
