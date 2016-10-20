package cn.vetech.vedsb.jp.service.jpzwgl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.jpzwgl.JpTjsqDrDao;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsq;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqDr;
import cn.vetech.vedsb.specialticket.request.KwCancelRequest;
import cn.vetech.vedsb.specialticket.response.KwCancelResponse;
import cn.vetech.vedsb.specialticket.response.KwResponse;
import cn.vetech.vedsb.specialticket.service.KwService;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class JpTjsqDrServiceImpl extends MBaseService<JpTjsqDr, JpTjsqDrDao>{
	@Autowired
	KwService kwService;//请求追位系统service
	
	@Autowired
	private JpTjsqServiceImpl jpTjsqServiceImpl;
	/**
	 * 根据查询条件查询追位订单
	 * @param jptjsqdr
	 * @return
	 */
	public List<JpTjsqDr> getjpTjsqDrList(JpTjsqDr jptjsqdr){
		return this.getMyBatisDao().getjpTjsqDrList(jptjsqdr);
	}
	
	public boolean batchAllUpdate(JpTjsqDr jptjsqdr) throws Exception{
		StringBuffer str = new StringBuffer();
		List<JpTjsqDr> list = this.getMyBatisDao().getjpTjsqDrList(jptjsqdr);
		for(JpTjsqDr jp : list){
			str.append(jp.getId()+",");
		}
		String s = str.toString();
		if(StringUtils.isNotBlank(s)){
			s = s.substring(0,s.length()-1);
		}
		boolean b = this.batchUpdate(s);
		return b;
	}
	
	public boolean batchUpdate(String s) throws Exception{
		Shyhb yhb = SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		String[] arr = s.split(",");
		for(String str : arr){
//			JpTjsqDr jp =new JpTjsqDr();
//			jp.setSqdh(str);
//			jp.setShbh(yhb.getShbh());
			JpTjsqDr jptjsqdr = this.getjptjsqdr(shbh, str);
			if(null!=jptjsqdr &&("2".equals(jptjsqdr.getSqZt()) || "3".equals(jptjsqdr.getSqZt()))){//成功
				return false;
			}
			JpTjsq tjsq = this.jpTjsqServiceImpl.gettjsq(shbh, str);//根据申请单号获取追位订单
			if(null == tjsq){
				return false;
			}else{
				KwCancelRequest request=new KwCancelRequest();
				request.setSqdh(str);
				KwCancelResponse response=kwService.cancelOrder(shbh, request);
				if(KwResponse.FAILL.equals(response)){
					return false;//通知追位系统取消追位单失败，请联系管理员
				}else{
					tjsq.setSqZt("6");
					this.jpTjsqServiceImpl.update(tjsq);
					jptjsqdr.setClDatetime(new Date());
					jptjsqdr.setClUserid(yhb.getBh());
					jptjsqdr.setClDeptid(yhb.getShbmid());
					jptjsqdr.setSqZt("6");//管理员消
					this.update(jptjsqdr);
				}
			}
		}
		return true;
	}
	
	public JpTjsqDr getjptjsqdr(String shbh,String sqdh){
		return this.getMyBatisDao().getjptjsqdrList(shbh, sqdh);
	}
	/**
	 * 查询待导入正式队列的申请单
	 * @param jpTjsqDr
	 * @return
	 */
	List<JpTjsqDr> getDdrDlList(JpTjsqDr jpTjsqDr){
		return getMyBatisDao().getDdrDlList(jpTjsqDr);
	}
}
