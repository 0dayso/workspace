package cn.vetech.vedsb.jp.service.b2bsz;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.dao.b2bsz.JpZdcpB2bzhDao;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.jp.entity.b2bsz.JpZdcpB2bzh;

@Service
public class JpZdcpB2bzhServiceImpl extends MBaseService<JpZdcpB2bzh, JpZdcpB2bzhDao>{

	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpB2bZfzhServiceImpl jpB2bZfzhServiceImpl;
	
	public Page queryPage(JpZdcpB2bzh jpZdcpB2bzh, int pageNum, int pageSize, String sortType) {
		Page page=new Page(pageNum, pageSize);
		List<Map<String, String>> list = this.getMyBatisDao().queryJpzdcpb2bzhList(jpZdcpB2bzh,pageNum,pageSize,sortType);
		if(list != null && list.size()>0){
			for(Map<String, String> map : list){
				if(StringUtils.isNotBlank(map.get("YHBH"))){
					Shyhb shyhb = new Shyhb();
					shyhb.setBh(map.get("YHBH"));
					shyhb.setShbh(map.get("SHBH"));
					shyhb = shyhbServiceImpl.getEntityById(shyhb);
					if(shyhb != null){
						map.put("XM", shyhb.getXm());
					}
				}
				if(StringUtils.isNotBlank(map.get("ZFID"))){
					String[] arr = StringUtils.split(map.get("ZFID"), ",");
					String zfzh1="";
					String zfzh2="";
					if(arr.length == 1){
						JpB2bZfzh bean = jpB2bZfzhServiceImpl.getMyBatisDao().queryByIdAndShbh(arr[0], map.get("SHBH"));
						if(bean != null){
							zfzh1=bean.getZfzh();
						}
						map.put("ZFZH1", zfzh1);
					}else if(arr.length == 2){
						JpB2bZfzh bean1 = jpB2bZfzhServiceImpl.getMyBatisDao().queryByIdAndShbh(arr[0], map.get("SHBH"));
						JpB2bZfzh bean2= jpB2bZfzhServiceImpl.getMyBatisDao().queryByIdAndShbh(arr[1], map.get("SHBH"));
						if(bean1 != null){
							zfzh1=bean1.getZfzh();
						}
						if(bean2 != null){
							zfzh2=bean2.getZfzh();
						}
						map.put("ZFZH1", zfzh1);
						map.put("ZFZH2", zfzh2);
					}
				}
			}
		}
		int count = this.getMyBatisDao().queryJpzdcpb2bzhCount(jpZdcpB2bzh);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 根据航空公司查询对应b2b账号信息
	 * @param shbh
	 * @param hkgs
	 * @return
	 */
	public List<JpZdcpB2bzh> getZdcpB2bzhByHkgs(String shbh,String hkgs){
		return this.getMyBatisDao().getZdcpB2bzhByHkgs(shbh, hkgs);
	}
}