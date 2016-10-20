package cn.vetech.web.vedsb.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.BairwaycwCache;
import org.vetech.core.business.cache.BairwaycwCacheService;
import org.vetech.core.modules.mapper.JsonMapper;
import org.vetech.core.modules.utils.ToPinYin;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Vecsb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.common.service.base.VecsbServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
/**
 * 机票系统所有异步取数据调用的controller
 * @author win7
 *
 */
@Controller
public class AjaxController {
	@Autowired
	private BairwaycwCacheService bairwaycwCacheService;
	@Autowired
	private VecsbServiceImpl vecsbService;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	/**
	 * 根据航司二字码得到Cw列表json
	 * 这里会按舱位去重
	 */
	@RequestMapping
	@ResponseBody
	public String getcw(@RequestParam String ezdh) {
		if(StringUtils.isBlank(ezdh)){
			return null;
		}
		List<BairwaycwCache> list=bairwaycwCacheService.getEzdh(ezdh);
		if(list==null){
			return null;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		for (BairwaycwCache cache : list) {
			map.put(cache.getCwdj(), cache);
		}
		return JsonMapper.nonEmptyMapper().toJson(map.values());
	}
	/**
	 * <功能详细描述>
	 * 获取控件数据
	 * @param lb
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]  data:{value:最终赋值,mc:显示的名称,label:检索用,pyszm:拼音首字母,pyqp:拼音全拼}
	 */
	@RequestMapping
	@ResponseBody
	public String genDataByLb(@RequestParam String lb) {
		String json=null;
		Shyhb shyhb=SessionUtils.getShshbSession();
		if(shyhb==null){
			return null;
		}
		if("shyhb".equals(lb)){
			Shyhb t=new Shyhb();
			t.setShbh(shyhb.getShbh());
			List<Shyhb> list=shyhbServiceImpl.getMyBatisDao().select(t);
			List<Map<String, Object>> data=new ArrayList<Map<String,Object>>();
			for(Shyhb one : list){
				Map<String, Object> mapb=new HashMap<String, Object>();
				String bh=one.getBh();
				String mc=one.getXm();
				String qp=ToPinYin.getPingYinString(mc);//全拼
				String jp=ToPinYin.getPingYinString(mc);//简拼
				String lable=bh+","+mc+","+qp+","+jp;
				mapb.put("value",bh);
				mapb.put("mc",mc);
				mapb.put("label",lable);
				mapb.put("pyszm", jp);
				mapb.put("pyqp", qp);
				data.add(mapb);
			}
			json=JsonMapper.nonEmptyMapper().toJson(data);
		}
		return json;
	}
	/**
	 * 设置默认模板
	 */
	@RequestMapping
	@ResponseBody
	public String setDefaultMb(@RequestParam(value = "mbid") String mbid) {
		if(StringUtils.isBlank(mbid)){
			return "0";
		}
		Shyhb user = SessionUtils.getShshbSession();
		Vecsb csb=new Vecsb();
		csb.setBh("5555");//5555代表默认模板
		csb.setCompid(user.getShbh());
		Vecsb csb1=vecsbService.getVecsbByBh(csb);
		if(csb1==null){
		    csb.setCsms("这个参数代表着打印时设置的默认模板");
		    csb.setCsz1(mbid);
		    csb.setCj_userid(user.getBh());
		    csb.setCjsj(new Date());
		    csb.setSfxs("0");
		    csb.setCslxmc("打印模板");
		    csb.setCslxbh("100110301");
		    try {
				vecsbService.insert(csb);
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
		}else{
			csb1.setXg_userid(user.getBh());
			csb1.setXgsj(new Date());
			csb1.setCsz1(mbid);
			try {
				vecsbService.updateVecsbMb(csb1);
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
		}
		return "1";
	}
}
