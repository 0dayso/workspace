package cn.vetech.vedsb.common.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.AttachInf;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.dao.base.SmsDao;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.SmsFsdl;
import cn.vetech.vedsb.common.entity.base.SmsMb;
import cn.vetech.vedsb.common.entity.base.SmsMbSh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddYj;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpkhddYjServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class SmsServiceImpl extends MBaseService<SmsFsdl,SmsDao> implements AttachInf{

	@Autowired
	private JpKhddServiceImpl jpkhddService;
	@Autowired
	private JpkhddYjServiceImpl jpkhddServiceYj;
	@Autowired
	private JpTpdServiceImpl jpTpdService;
	@Autowired
	private JpTpdMxServiceImpl jpTpdmxService;
	@Autowired
	private JpKhddHdServiceImpl jpkhddHdService;
	
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		return null;
	}

	/**
	 * <行程单中查找短信模板>
	 * 
	 * @param mb
	 * @return [参数说明]
	 * 
	 * @return List<SmsMb> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<SmsMb> querySmsmbByXcd(SmsMb mb) {
		return this.getMyBatisDao().querySmsmbByXcd(mb);
	}

	/**
	 * <行程单管理中的批量短信>
	 * 
	 * @param smsFsdl
	 * @param ids
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public int insertSmsChangeYjd(SmsFsdl smsFsdl, String ids) {
		int result=0;
		String mobiles=smsFsdl.getJshm();
		Shyhb user = SessionUtils.getShshbSession();
		//String ddbhs=smsFsdl.getDdbh();
		String[] idarr=ids.split(",");
		String[] mobilearr=mobiles.split(",");
		for(int i=0;i<idarr.length;i++){
			JpKhddYj jpKhddYj=new JpKhddYj();
			jpKhddYj.setId(idarr[i]);
			jpKhddYj=jpkhddServiceYj.getEntityById(jpKhddYj);
			JpKhdd jpKhdd =new JpKhdd();
			jpKhdd.setDdbh(jpKhddYj.getDdbh());
			jpKhdd=jpkhddService.getEntityById(jpKhdd);
			jpKhdd.setDxzt("1");
			result=jpkhddService.updateSmszt(jpKhdd);
			smsFsdl.setDdbh(jpKhddYj.getDdbh());
			if(idarr.length>1){
				//查退票单 ,查退票单明细,查航段 这三张表只是为了解析通配符
				List<JpTpd> jptpdList=(List<JpTpd>)jpTpdService.queryTpdByDdbh(jpKhddYj.getDdbh(),jpKhdd.getShbh());
				List<JpTpdMx> list=new ArrayList<JpTpdMx>();
				if(jptpdList!=null){
					for(JpTpd jptp:jptpdList){
						List<JpTpdMx> tpmxList=jpTpdmxService.getJpTpdMxByTpdh(jptp.getTpdh(), jpKhdd.getShbh());
						if(tpmxList!=null){
							for(JpTpdMx mx:tpmxList){
								list.add(mx);
							}
						}
					}
				}
				List<JpKhddHd> hdList=jpkhddHdService.getKhddHdListByDDbh(jpKhddYj.getDdbh(), jpKhdd.getShbh());
				this.repaclFsnr(smsFsdl.getFsnr(),jpKhdd,jptpdList,list,hdList,user);
			}
			if(mobilearr.length>i){
				smsFsdl.setJshm(mobilearr[i]);
			}else{
				smsFsdl.setJshm(jpKhddYj.getNxdh());
			}
			smsFsdl.setId(VeDate.getNo(2));
			this.getMyBatisDao().insert(smsFsdl);
		}
		return result;
	}
	
	private String repaclFsnr(String fsnr,JpKhdd jpdd,List<JpTpd> jptpdList,List<JpTpdMx> jptpdmx,List<JpKhddHd> hdList,Shyhb user){
		//行程单有关的收件人,寄件人相关的通配符
		if(jpdd!=null){
			fsnr=this.Replace(fsnr, "[lxr]",jpdd.getNxr());//联系人
			fsnr=this.Replace(fsnr, "[sj]", jpdd.getNxsj());//客户联系手机
			fsnr=this.Replace(fsnr, "[lxdh]", jpdd.getNxdh());//联系电话
			fsnr=this.Replace(fsnr, "[yj_datetime]", VeDate.dateToStrLong(jpdd.getYjsj()).substring(5,16));//邮寄时间
			fsnr=this.Replace(fsnr, "[shdz]", jpdd.getXjdz());//收货地址
			//fsnr=this.Replace(fsnr, "[fsbmmc]", ve_yhb.getDeptmc());//发送部门名称
			//fsnr=this.Replace(fsnr, "[fsbmdh]", (String)dept.get("TEL"));//发送部门电话
			fsnr=this.Replace(fsnr, "[farxm]", user.getXm());//发送人姓名
			fsnr=this.Replace(fsnr, "[far]", user.getBh());//发送人工号
			fsnr=this.Replace(fsnr, "[yjdh]",jpdd.getYjdh());//邮寄单号
			fsnr=this.Replace(fsnr, "[SJR]", jpdd.getSjr());
			fsnr=this.Replace(fsnr, "[YJDH]", jpdd.getYjdh());
			fsnr=this.Replace(fsnr, "[YJ_DATETIME]", VeDate.dateToStrLong(jpdd.getYjsj()).substring(5,16));
		}
		//退货单有关的通配符
		if(jptpdList!=null&&jptpdList.size()>0){
			fsnr=this.Replace(fsnr, "[tplxr]", jptpdList.get(0).getNxr());//退票联系人
			fsnr=this.Replace(fsnr, "[hd1]",jptpdList.get(0).getHc());//航程
			
			fsnr=this.Replace(fsnr, "[tpsxf]", jptpdList.get(0).getXsTpsxf().toString());//产生退票费
			fsnr=this.Replace(fsnr, "[ytje]", jptpdList.get(0).getXsTkje().toString());//应退票款
			fsnr=this.Replace(fsnr, "[fsbmdh]",jpdd.getYjdh());//邮寄单号
			if(jptpdmx!=null&&jptpdmx.size()>0){
				fsnr=this.Replace(fsnr, "[tkno1]", jptpdmx.get(0).getTkno());
			}
		}
		//航段有关的通配符
		if(hdList!=null&&hdList.size()>0){
			fsnr=this.Replace(fsnr, "[cfrq][cfcity]", hdList.get(0).getCfsj()+hdList.get(0).getCfcity());
			fsnr=this.Replace(fsnr, "[ddcity][hbh]", hdList.get(0).getDdcity()+hdList.get(0).getXsHbh());
			fsnr=this.Replace(fsnr, "[cfsj2]", hdList.get(0).getCfsj());
		}
		return fsnr;
	}
	
	private String Replace(String source, String oldString, String newString) {
		if (source == null)
			return null;
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	/**
	 * <查找短信模板>
	 * 
	 * @param mbsh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<SmsMbSh> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<SmsMbSh> querySmsmbshByXcd(String shbh,String lx) {
		
		return this.getMyBatisDao().querySmsmbshByXcd(shbh,lx);
	}

	/**
	 * <修改短信类型模板时,将通配符修改成对应的信息>
	 * 
	 * @param nr
	 * @param ddbh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String getSmsNr(String nr, String ddbh) {
		Shyhb user = SessionUtils.getShshbSession();
		JpKhdd jpKhdd =new JpKhdd();
		jpKhdd.setDdbh(ddbh);
		jpKhdd=jpkhddService.getEntityById(jpKhdd);
		//查退票单 ,查退票单明细,查航段 这三张表只是为了解析通配符
		List<JpTpd> jptpdList=(List<JpTpd>)jpTpdService.queryTpdByDdbh(ddbh,user.getShbh());
		List<JpTpdMx> list=new ArrayList<JpTpdMx>();
		if(jptpdList!=null&&jptpdList.size()>0){
			for(JpTpd jptp:jptpdList){
				List<JpTpdMx> tpmxList=jpTpdmxService.getJpTpdMxByTpdh(jptp.getTpdh(), user.getShbh());
				if(tpmxList!=null&&tpmxList.size()>0){
					for(JpTpdMx mx:tpmxList){
						list.add(mx);
					}
				}
			}
		}
		List<JpKhddHd> hdList=jpkhddHdService.getKhddHdListByDDbh(ddbh, user.getShbh());
		return repaclFsnr(nr,jpKhdd,jptpdList,list,hdList,user);
	}


}
