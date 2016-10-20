package cn.vetech.web.vedsb.jpcwgl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpLrfxController extends MBaseControl<JpJp, JpJpServiceImpl>{
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Override
	public void insertInitEntity(JpJp t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInitEntity(JpJp t) {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping
	public String lrfxmxList(HttpServletRequest request,ModelMap model,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		if(StringUtils.isBlank(ksrq)){
			ksrq = VeDate.getStringDateShort();
		}
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		if(StringUtils.isBlank(jsrq)){
			jsrq = ksrq;
		}
		String wdbh = StringUtils.trimToEmpty(request.getParameter("wdbh"));
		String wd = StringUtils.trimToEmpty(request.getParameter("wd"));
		if(StringUtils.isBlank(wdbh)){
        	wdbh = wd;
        }
		
		String zclx = StringUtils.trimToEmpty(request.getParameter("zclx"));
		String cplx = StringUtils.trimToEmpty(request.getParameter("cplx"));
		if(StringUtils.isBlank(zclx)){
        	zclx = cplx;
        }
		
		String tfid = StringUtils.trimToEmpty(request.getParameter("tfid"));
		String tffa = StringUtils.trimToEmpty(request.getParameter("tffa"));
		if(StringUtils.isBlank(tfid)){
        	tfid = tffa;
        }
		String wdpt = StringUtils.trimToEmpty(request.getParameter("wdpt"));
		String zcqd = StringUtils.trimToEmpty(request.getParameter("zcqd"));
		String tjlx = StringUtils.trimToEmpty(request.getParameter("tjlx"));
		String type = StringUtils.trimToEmpty(request.getParameter("type"));
		String hkgs = StringUtils.trimToEmpty(request.getParameter("hkgs"));
		String hc = StringUtils.trimToEmpty(request.getParameter("hc"));
		String jp_hc = StringUtils.trimToEmpty(request.getParameter("jp_hc"));
		if(StringUtils.isBlank(hc)){
        	hc = jp_hc;
        }
		
		String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
		String mxlx = StringUtils.trimToEmpty(request.getParameter("mxlx"));
		String xslx ="";
		try {
			//根据明细和业务类型来设置显示类型
	        String [] ywlxArr = new String[]{"10117114","10117112"};
	        List<String> ywlxList = Arrays.asList(ywlxArr);
	       
	        if(!"0".equals(tjlx)){
		       if("1".equals(mxlx)){
		    	    xslx = "1";
		       }else if(getXslx(mxlx,ywlx)){
		    	   xslx = "2";
		       }else if("4".equals(mxlx)&&"10117109".equals(ywlx)){
		    	   xslx = "3";
		       }else if("4".equals(mxlx)&&"10117110".equals(ywlx)){
		    	   xslx = "4";
		       }else if("4".equals(mxlx)&&"10117115".equals(ywlx)){
		    	   xslx = "5";
		       }else if("4".equals(mxlx)&&ywlxList.contains(ywlx)){
		    	   xslx = "6";
		       }else{
		    	   throw new Exception("返回未知的显示类型，数据异常，请联系管理员！"); 
		       }
		       model.addAttribute("xslx", xslx);
	        }
	       
	        int count = 30, start = 0;
	        PageHelper ph = new PageHelper();
	        start =ph.getStart(pageNum, pageSize);
	        count =ph.getCount(pageNum, pageSize);
	        Page page = new Page(pageNum,pageSize);
	        Map sumMap = new HashMap();
	        
	        StringBuffer xml=new StringBuffer("<SQL>");
	        xml.append(XmlUtils.xmlnode("START", start));
	        xml.append(XmlUtils.xmlnode("COUNT", count));
	        xml.append(XmlUtils.xmlnode("KSRQ", ksrq));
	        xml.append(XmlUtils.xmlnode("JSRQ", jsrq));
	        xml.append(XmlUtils.xmlnode("WDPT", wdpt));
	        xml.append(XmlUtils.xmlnode("ZCQD", zcqd));
	        xml.append(XmlUtils.xmlnode("SHBH", shbh));
	        xml.append(XmlUtils.xmlnode("WDBH", wdbh));
	        xml.append(XmlUtils.xmlnode("ZCLX", zclx));
	        xml.append(XmlUtils.xmlnode("TFID", tfid));
	        xml.append(XmlUtils.xmlnode("HKGS", hkgs));
	        
	        //利润统计模式，1按订单，2当期
	        xml.append(XmlUtils.xmlnode("TYPE", type));
	        // 统计方式，1按网店 2按网店/产品类型 3按投放规则 4按航程 5按网店/产品类型/航程 6 按投放规则/航程 7 按网店/航司  8政策渠道
	        xml.append(XmlUtils.xmlnode("TJFS", tjlx));
	        xml.append(XmlUtils.xmlnode("HC", hc));
	        //1订单数2出票时3准点情况4情形张数
	        xml.append(XmlUtils.xmlnode("MXLX", mxlx));
	        //明细类型为3时此节点对应：1航班延误，2航班取消，0航班准点；明细节点为4时此节点对应返回的业务类型
	        xml.append(XmlUtils.xmlnode("YWLX", ywlx));
	        xml.append("</SQL>");
	        Map result = null;
	        try {
				result = this.procedureServiceImpl.getLrfxMxList(xml.toString());
				if(result != null){
					String error = (String)result.get("p_error");
		            if(!"OK".equals(error)){
		                //取得数据库存出错信息
		                throw new Exception(error); 
		            }
		            List mxlist = (List)result.get("p_cur");
		            if(mxlist.size()>0){
		            	 page.setList(mxlist);
		            }else{
		            	 page.setList(null);
		            }
		            
		            List sumlist = (List)result.get("p_cur_sum");
		            sumMap = (Map)sumlist.get(0);
		            Long ac = NumberUtils.toLong(VeStr.getValue(sumMap, "ALLCOUNT"));
		            if (ac != null){
		       		     page.setTotalCount(ac.intValue());
		       	    }
				}
				 model.addAttribute("sumMap", sumMap);
		         model.addAttribute("page",page);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("查询利润明细调后台过程出错!"+e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询利润分析明细报错",e);
		}
		if("0".equals(tjlx)){
			return viewpath("list");
        }else{
        	return viewpath("xqList");
        }
	}
	
	
	@RequestMapping
	public String lrfxList(HttpServletRequest request,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		if(StringUtils.isBlank(ksrq)){
			ksrq = VeDate.getStringDateShort();
		}
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		if(StringUtils.isBlank(jsrq)){
			jsrq = ksrq;
		}
		String wd = StringUtils.trimToEmpty(request.getParameter("wd"));
		String cplx = StringUtils.trimToEmpty(request.getParameter("cplx"));
		String tffa = StringUtils.trimToEmpty(request.getParameter("tffa"));
		String tjlx = StringUtils.trimToEmpty(request.getParameter("tjlx"));
		String tjms = StringUtils.trimToEmpty(request.getParameter("tjms"));
		String hkgs = StringUtils.trimToEmpty(request.getParameter("hkgs"));
		String jp_hc = StringUtils.trimToEmpty(request.getParameter("jp_hc"));
		
		int count = 30, start = 0;
        start = NumberUtils.toInt(request.getParameter("start"), 0);
        count = NumberUtils.toInt(request.getParameter("count"), 30);
        Page page = new Page(start,count);
        //通过后台，调用记录集合;
        StringBuffer xml = new StringBuffer("<SQL>");
        xml.append(XmlUtils.xmlnode("START",start));
        xml.append(XmlUtils.xmlnode("COUNT",count));
        xml.append(XmlUtils.xmlnode("SHBH",shbh));
        xml.append(XmlUtils.xmlnode("KSRQ",ksrq));
        xml.append(XmlUtils.xmlnode("JSRQ",jsrq));
        xml.append(XmlUtils.xmlnode("WDBH",wd));
        xml.append(XmlUtils.xmlnode("ZCLX",cplx));
        xml.append(XmlUtils.xmlnode("TFID",tffa));
        xml.append(XmlUtils.xmlnode("HKGS",hkgs));
        xml.append(XmlUtils.xmlnode("HC", jp_hc));
        xml.append(XmlUtils.xmlnode("TYPE", tjms));
        xml.append(XmlUtils.xmlnode("TJFS", tjlx));
        xml.append("</SQL>");
        Map result = null;
        try {
        	result = this.procedureServiceImpl.getLrfxList(xml.toString());
        	 List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        	if(result!=null){
        		 String error =(String) result.get("p_error");
        		 if(!"OK".equals(error)){
                     throw new Exception(error); 
                 }
        	   List<Map<String,Object>> zcpList = (List)result.get("p_cur_zcp");
          	   List<Map<String,Object>> hbzdList = (List)result.get("p_cur_hbzd");      
          	   List<Map<String,Object>> lrList = (List)result.get("p_cur_lr");
          	   
	           String [] zcpArray = {"WDBH","WDMC","ZCLX","TFID","HKGS","JP_HC","HCMC","DDS","CPS","PJ_AVG","ZKL_AVG","PJ","TAXHJ","XSHJ"};
	      	   String [] hbzdArray = {"WDBH","ZCLX","TFID","HKGS","IFHBZD","IFHBZDMC","CPS","TPS","TPBL"};
	      	   String [] lrArray = {"WDBH","IFHBZD","ZCLX","TFID","HKGS","JP_HC","YWLX","YWLXMC","LRHJ","LR_AVG","CPS"};
	      	   Map<String,Object> allZcp = new LinkedHashMap();
	      	   Map<String,Object> allHbzd = new LinkedHashMap();
	      	   Map<String,Object> allLr = new LinkedHashMap();
	      	   for(String zcpkey : zcpArray){
	      	      allZcp.put(zcpkey, "");
	      	   }
	      	   for(String hbzdkey : hbzdArray){
	      		   allHbzd.put(hbzdkey, "");
	       	   }
	      	   for(String lrkey : lrArray){
	      		   allLr.put(lrkey, "");
	       	   }
	      	   changeTjfx(tjlx,resultList,zcpList,lrList,allZcp,allHbzd,allLr);
	      	   //封装统计信息
	            List sumList = (List)result.get("p_cur_hj");
	            Map<String, String>  sumMap = null;
	            if (CollectionUtils.isEmpty(sumList)){
	      		   System.out.println("利润分析返回统计数据集合错误 XML =>" + xml);
	      		   throw new Exception("操作失败，数据异常，请联系管理员！");
	      	    }else{         
	 		  		sumMap = (Map)sumList.get(0);
	      	    }
	            model.addAttribute("sumMap", sumMap);
	            if(resultList.size()>0){
	            	 page.setList(resultList);
	            }else{
	            	 page.setList(null);
	            }
        	}
        	model.addAttribute("page",page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询利润分析报错",e);
		}
		return viewpath("list");
	}
	
	/**
     * 通用处理按当期利润统计
     * @param tjlx
     * @param resultList
     * @param zcpList
     * @param lrList
     * @param allZcp
     * @param allHbzd
     * @param allLr
     */
    public void changeTjfx(String tjlx, List<Map<String,Object>> resultList,List<Map<String,Object>> zcpList, List<Map<String,Object>> lrList,Map<String,Object> allZcp,Map<String,Object> allHbzd,Map<String,Object> allLr){  
  	   for(Map<String,Object> f:  zcpList){
    	   for(Map<String,Object> s:  lrList){	
    		   boolean isTrue =getPdzt(tjlx,f,s,"0");
	 		   if(isTrue){   
	    	       Map<String,Object> sTemp = commonAddProperty(allZcp,allHbzd,allLr);
	    		   for(Map.Entry<String, Object> set : f.entrySet()){
	    			   sTemp.put(set.getKey(), set.getValue());
	    		   }
	    	   
	    		   for(Map.Entry<String, Object> set : s.entrySet()){
	    			   sTemp.put("LRQX_"+set.getKey(), set.getValue());
	    		   }
	    		   resultList.add(sTemp);
    		   }
    	   }
       }
    }
    
    /**
     * 通用加载所有属性
     * @param map
     * @param zcpmap
     * @param hbzdmap
     * @param lrmap
     * @return
     */
    public Map<String,Object> commonAddProperty(Map<String,Object> zcpmap,Map<String,Object> hbzdmap,Map<String,Object> lrmap){
    	Map<String,Object> result = new HashMap<String,Object>();
    	for(Map.Entry<String, Object> set: zcpmap.entrySet()){
    		result.put(set.getKey(), set.getValue());
    	}
    	for(Map.Entry<String, Object> set: hbzdmap.entrySet()){
    		result.put("HBZD_"+set.getKey(), set.getValue());
    	}
    	for(Map.Entry<String, Object> set: lrmap.entrySet()){
    		result.put("LRQX_"+set.getKey(), set.getValue());
    	}
    	return result;
    }
    
    /**
     * 根据统计模式返回判断状态
     * @param tjlx
     * @param f
     * @param s
     * @param mark;
     * @return
     */
    public boolean getPdzt(String tjlx,Map<String,Object> f,Map<String,Object>s, String mark){
		boolean isTrue =true;
		if("1".equals(tjlx)){
				if("0".equals(mark)){
				       isTrue =getStr(s.get("WDBH")).equals(getStr((f.get("WDBH"))));
				}else if("1".equals(mark)){
					   isTrue =getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("IFHBZD")).equals(getStr(f.get("IFHBZD")));
				}else{
					   isTrue = getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&& "9".equals(getStr(s.get("IFHBZD")));					
				}
		}else if("2".equals(tjlx)){
				if("0".equals(mark)){
					   isTrue = getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("ZCLX")).equals(getStr(f.get("ZCLX")));
				}else if("1".equals(mark)){
					   isTrue = getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("ZCLX")).equals(getStr(f.get("ZCLX")))&&getStr(s.get("IFHBZD")).equals(getStr(f.get("IFHBZD")));
				}else{
					   isTrue = getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("ZCLX")).equals(getStr(f.get("ZCLX")))&& "9".equals(getStr(s.get("IFHBZD")));
				}
		}else if("3".equals(tjlx)){
				if("0".equals(mark)){
					   isTrue =getStr(s.get("TFID")).equals(getStr(f.get("TFID")));
				}else if("1".equals(mark)){
					   isTrue =getStr(s.get("TFID")).equals(getStr(f.get("TFID"))) &&getStr(s.get("IFHBZD")).equals(getStr(f.get("IFHBZD")));
				}else{
					   isTrue = getStr(s.get("TFID")).equals(getStr(f.get("TFID"))) && "9".equals(getStr(s.get("IFHBZD")));
				}			
		}else if("4".equals(tjlx)){
				if("0".equals(mark)){
					   isTrue =getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC")));
				}else if("1".equals(mark)){
					   isTrue =getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC")))&&getStr(s.get("IFHBZD")).equals(getStr(f.get("IFHBZD")));
				}else{
					   isTrue = getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC"))) && "9".equals(getStr(s.get("IFHBZD")));
				}				
		}else if("5".equals(tjlx)){
				if("0".equals(mark)){
					   isTrue =getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("ZCLX")).equals(getStr(f.get("ZCLX")))&&getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC")));
				}else if("1".equals(mark)){
					   isTrue =getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("ZCLX")).equals(getStr(f.get("ZCLX")))&&getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC")))&&getStr(s.get("IFHBZD")).equals(getStr(f.get("IFHBZD")));
				}else{
					   isTrue = getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("ZCLX")).equals(getStr(f.get("ZCLX")))&&getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC"))) && "9".equals(getStr(s.get("IFHBZD")));
				}							
		}else if("6".equals(tjlx)){
				if("0".equals(mark)){
					   isTrue =getStr(s.get("TFID")).equals(getStr(f.get("TFID")))&&getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC")));
				}else if("1".equals(mark)){
					   isTrue =getStr(s.get("TFID")).equals(getStr(f.get("TFID")))&&getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC")))&&getStr(s.get("IFHBZD")).equals(getStr(f.get("IFHBZD")));
				}else{
					   isTrue = getStr(s.get("TFID")).equals(getStr(f.get("TFID")))&&getStr(s.get("JP_HC")).equals(getStr(f.get("JP_HC"))) && "9".equals(getStr(s.get("IFHBZD")));
				}				
		}else if("7".equals(tjlx)){
				if("0".equals(mark)){
					   isTrue =getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("HKGS")).equals(getStr(f.get("HKGS")));
				}else if("1".equals(mark)){
					   isTrue =getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("HKGS")).equals(getStr(f.get("HKGS")))&&getStr(s.get("IFHBZD")).equals(getStr(f.get("IFHBZD")));
				}else{
					   isTrue = getStr(s.get("WDBH")).equals(getStr(f.get("WDBH")))&&getStr(s.get("HKGS")).equals(getStr(f.get("HKGS"))) && "9".equals(getStr(s.get("IFHBZD")));
				}					      
		}
		return isTrue;
    }
    
    /**
     * 针对null值返回空字符串
     * @param obj
     * @return
     */
    public String getStr(Object obj){
    	if(obj==null){
    		return "";
    	}
    	return (String)obj;
    }
	/**
     * 根据明细类型、业务类型判断是否是该显示类型
     * @param mxlx
     * @param ywlx
     * @return
     */
    private boolean getXslx(String mxlx,String ywlx){
    	String [] ywArr = new String[]{"0","1","2"};
    	String [] ywArr2 = new String[]{"cplr","10117107","10117108","10117101","10117102"
    			,"10117103","10117104","10117105","10117106","10117113"};
    	List<String> list = Arrays.asList(ywArr);
    	List<String> list2 = Arrays.asList(ywArr2);
    	if("2".equals(mxlx)){
    		return true;
    	}else if("3".equals(mxlx)&&list.contains(ywlx)){
    		return true;
    	}else if("4".equals(mxlx)&&list2.contains(ywlx)){
    		return true;
    	}
    	return false;
    }
}
