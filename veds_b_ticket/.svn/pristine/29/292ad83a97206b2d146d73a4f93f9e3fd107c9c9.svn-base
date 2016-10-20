package cn.vetech.vedsb.jp.service.jpjpgl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.pnrpat2.PnrHd;
import org.vetech.core.business.pid.api.xeflight.XeFlightParam;
import org.vetech.core.business.pid.util.PidUtils;
import org.vetech.core.modules.utils.ParseXml;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.utils.LogUtil;
/**
 * 自动补位
 * @author vetech
 *
 */
@Service
public class JpzdbwServiceImpl {
	/**
	 * <RESULT>
		<STATUS>1</STATUS>
		<DESCRIPTION>
			<XEFLIGHTresult>  HU7840  N FR16SEP  SHETNA DK1   1700 1845 
				JXXM0Z -   航空公司使用自动出票时限, 请检查PNR  
				*** 预订酒店指令HC, 详情  HC:HELP   ***  
			</XEFLIGHTresult>
			<Pnr>
				<P>
					<R>1</R>
					<N>JXXM0Z</N>
					<T>1</T>
					<Q>  
						1.刘静 JXXM0Z  
						2.  HU7840 N   FR16SEP  SHETNA HK1   1700 1845          E T3-- 
						3.SHA/T SHA/T0571-88136060/ASLAN TRAVEL(SHANGHAI)CO.,LTD /JIANGLING ABCDEFG
						4.TL/0744/09SEP/SHA384 
						5.SSR FOID HU HK1 NI230231198701161523/P1  
						6.SSR OTHS 1E HU7840 /N/16SEP/SHETNA CANCELED DUE TO ATTL EXPIRED  
						7.OSI HU CTCT15857146178   
						8.OSI HU CTCT057156888688  
						9.RMK TJ AUTH HGH372   
						10.RMK TJ AUTH BJS000   
						11.RMK CA/NG64DE
						12.SHA384   
					</Q>
					<B>NG64DE</B>
					<C>SHA384,1,,,,,2016-09-09 07:44:00,0,1,0</C>
					<TL>4</TL>
					<OFFICE>SHA384</OFFICE>
					<AUTH>HGH372,BJS000</AUTH>
					<CTCT>57156888688</CTCT>
					<HD>1|0,HU7840,N,16SEP,SHE,TNA,HK,2016-09-16 17:00:00,2016-09-16 18:45:00,738,,T3-,1,</HD>
					<CJR>1|0,刘静,230231198701161523,NI,1,,,,,,,,,,,,</CJR>
				</P>
			</Pnr>
		</DESCRIPTION>
	</RESULT>
	 * **/
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	public String jpzdbw(Pnr pnr, JpPz jppz, JpPtLog jpPtLog, String pidyh,String ddbh){
		String result = "";
		try {
			String serviceAddress = jppz.getPzIp() + ":" + jppz.getPzPort();
			String office = jppz.getOfficeid();
			String pnrno = pnr.getPnr_no();
			List<PnrHd> hdblist = pnr.getHdblist();
			List<PnrCjr> cjrlist = pnr.getCjrlist();
			if(CollectionUtils.isEmpty(hdblist)||CollectionUtils.isEmpty(cjrlist)){
				LogUtil.getZdbwLog().error(pnrno+"==>pnr航段为空或者乘机人为空!");
				return null;
			}
			jpPtLog.add(pnrno + "自动补位开始:");
			LogUtil.getZdbwLog().error(pnrno+"==>自动补位开始：");
			XeFlightParam param = new XeFlightParam();
			param.setUserid(pidyh);
			param.setPnrno(pnrno);
			param.setUrl(serviceAddress);
			param.setHd(pjhd(hdblist));
			param.setSs(pjhdxl(hdblist, cjrlist));
			param.setOffice(office);
			jpPtLog.add(pnrno + "自动补位入参:==>" + param.toxml());
			LogUtil.getZdbwLog().error(pnrno+"自动补位入参:==>" + param.toxml());
			result = IbeService.xeFlight(param);
			jpPtLog.add(pnrno + "自动补位回参:==>" + result);
			LogUtil.getZdbwLog().error(pnrno + "自动补位回参:==>" + result);
			JpKhdd khdd = new JpKhdd();
			khdd.setDdbh(ddbh);
			//khdd.setShbh(jppz.getShbh());
			ParseXml parsexml = new ParseXml(result);
			Element root = parsexml.getRoot();
			String status = root.elementText("STATUS");
			//1 补位成功 2 补位失败
			if("1".equals(status)){
				khdd.setSfbwcg("1");
			}else{
				khdd.setSfbwcg("2");
			}
			khdd.setXgly("补位操作");
			result=khdd.getSfbwcg();
			this.jpKhddServiceImpl.updateSelective(khdd);
		} catch (Exception e) {
			e.printStackTrace();
			result="补位失败:"+e.getMessage();
		}
		return result;
	}
	
	/**
	 * 拼接航段信息
	 * 多个航段中间用|隔开
	 * 例子:MF8407,HGH,WUH,2017-03-23,Y|MF8408,WUH,HGH,2017-03-24,Y
	 * @param hdblist
	 * @return
	 */
	private String pjhd(List<PnrHd> hdblist){
		String s = "";
			for(PnrHd hd : hdblist){
				String cfsj = hd.getCfsj().substring(0,10);
				String hbh = hd.getHbh();
				if(StringUtils.isBlank(s)){
					s = hbh+","+hd.getCfcity()+","+hd.getDdcity()+","+cfsj+","+hd.getCw();
				}else{
					s = s+"|"+hbh+","+hd.getCfcity()+","+hd.getDdcity()+","+cfsj+","+hd.getCw();
				}
			}
		return s;
	}
	
	/**
	 * 拼接航段序列信息
	 * 多个航段中间用^隔开
	 * 例子:SS MF8407/Y/23MAR17/HGHWUH/LL2^SS MF8408/Y/24MAR17/WUHHGH/LL2
	 * LL后面接的是乘机人的人数
	 * @param hdblist
	 * @param cjrlist
	 * @return
	 */
	private String pjhdxl(List<PnrHd> hdblist,List<PnrCjr> cjrlist){
		String s = "";
		int cjrsize = cjrlist.size();
		for(PnrHd hd : hdblist){
			String cfsj = hd.getCfsj().substring(0,10);
			cfsj = PidUtils.dateToCommandDate(cfsj);
			String city = hd.getCfcity()+hd.getDdcity();
			String hbh = hd.getHbh().replace("*", "");
			if(StringUtils.isBlank(s)){
				s = "SS "+hbh+"/"+hd.getCw()+"/"+cfsj+"/"+city+"/LL"+cjrsize;
			}else{
				s = s+"^SS "+hbh+"/"+hd.getCw()+"/"+cfsj+"/"+city+"/LL"+cjrsize;
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		String s = "";
		ParseXml parsexml;
		try {
			parsexml = new ParseXml(s);
			Element root = parsexml.getRoot();
			String status = root.elementText("STATUS");
			if("1".equals(status)){
				System.out.println("1");
			}else{
				System.out.println("2");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String s = "*MU1345";
//		System.out.println(s.replace("*", ""));
//		PnrRtParam pnrRtParam = new PnrRtParam();
//		pnrRtParam.setUserid("8635");
//		pnrRtParam.setUrl("http://192.168.1.69:8088");
//		//pnrRtParam.setOfficeId(jpPz.getOfficeid());
//		pnrRtParam.setPnrno("HFMF1Q");//"HFHMP1"
//		try {
//			//Pnr pnr = IbeService.rtPnr(pnrRtParam);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
