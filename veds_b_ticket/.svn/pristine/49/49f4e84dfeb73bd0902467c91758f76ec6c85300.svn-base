<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/static/js/xcdprint/combox.js"></script>
<style type="text/css">
	ul{list-style-type:none;margin:0;padding-left:0;}
	li{margin:0;}
	@font-face {
		font-family: 'icomoon';
		src:url('/static/js/xcdprint/fonts/icomoon.eot?-fl11l');
		src:url('/static/js/xcdprint/fonts/icomoon.eot?#iefix-fl11l') format('embedded-opentype'),
			url('/static/js/xcdprint/fonts/icomoon.woff?-fl11l') format('woff'),
			url('/static/js/xcdprint/fonts/icomoon.ttf?-fl11l') format('truetype'),
			url('/static/js/xcdprint/fonts/icomoon.svg?-fl11l#icomoon') format('svg');
		font-weight: normal;
		font-style: normal;
	}
	.ficomoon{font-family:'icomoon';}
	.icon-angle-top:before {content: "\f102"}.icon-angle-bottom:before {content: "\f103"}
	/*可根据自己的实际情况做修改*/
	.combox_border{border:1px solid #c2c2c2;width:245px}
	.combox_input{border:0;line-height:25px;height:20px;padding-left: 5px;width:85%;vertical-align: middle;}
	.combox_button{width:12%;text-align:center;vertical-align: middle;cursor:pointer;}
	.combox_select{border:1px solid #c2c2c2;border-top:0;width:100%}
	.combox_select li{overflow:hidden;height:30px;line-height:30px;cursor:pointer;}
	.combox_select a {display: block;line-height: 28px;padding: 0 8px;text-decoration: none;color: #666;}
	.combox_select a:hover {text-decoration: none;background:#f5f5f5}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#qzcombox').combox({datas:['', '不得签转','不得签转不得退票','不得变更不得签转','不得签转变更退票','不得签转不得变更不得退票','不得签转仅限原出票地退票']});
		$('#qzcombox>input').val("");
	})
		
</script>
	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" style="border:1px dotted gray;">
	  	  <tr>
			<td width="35%" ><span title="最近5次历史打印记录">历史打印记录:共计${fn:length(xcdinfoPrintHistoryList) }次</span>
				<c:forEach items="${xcdinfoPrintHistoryList}" var="xcdinfoPH" varStatus="pIndex">
					<c:if test="${pIndex.count lt 6}">	
						<a class="${param.xcid eq xcdinfoPH.id ? 'red' : '' }" title="点击查看工号${xcdinfoPH.cj_userid }的打印记录" href="javascript:void(0);" onclick="openPrintLog('${xcdinfoPH.id}','${xcdinfoPH.tkno}');">[${pIndex.count }]</a>
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>
				<input name="electronicTicket" id ="electronicTicket" type="hidden" value="${xcdinfo.tkno}"/>
			</td>
		</tr>
	</table>
	
   <c:set var="xcdformid" value="${param.tkno }"></c:set>
   <form action=""  id="xcdform${xcdformid}" method="post">
	  	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" style="margin-top:25px;border:1px solid #5A7D4A;">
	  	  <tr>
	        <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	          <tr>
	            <td width="31%">
	            		
	              <input name="lkxm" type="text" value="${xcdinfo.lkxm}" onkeyup="toUpperCaseo(this)" onblur="changecrinfo()" class="input1" style="width:220px;"  /></td>
	            <td width="38%">有效身份证件号码
	              <input name="zjhm" type="text" value="${xcdinfo.zjhm}" onblur="changecrinfo()" class="input1" style="width:130px;" maxLength="20"/></td>
	            <td width="28%">签注
				<span id="qzcombox" name="qz"></span>
	            </td>  
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#EFF3E8"></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#BBBEB4"></td>
	      </tr>
	      <tr>
	        <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
	          <tr>
	            <td width="7%" class="ysblue" style="color: blue;">订座记录</td>
	            <td width="13%" class="ysblue">
	              <input name="pnr_no" type="text" value="${xcdinfo.pnrNo}" onblur="value=$.trim(this.value).toUpperCase();" class="input1" style="width:70px;"/>
	            </td>
	            <td width="6%">承运人</td>
	            <td width="7%"><div align="center">航班号</div></td>
	            <td width="8%">舱位等级</td>
	            <td width="7%"><div align="center">日期</div></td>  
	            <td width="6%"><div align="center">时间</div></td>
	            <td width="15%">票价级别/客票类别</td>
	            <td width="12%">客票生效日期</td>
	            <td width="12%">有效截止日期</td>
	            <td width="7%">免费行李</td>
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#EFF3E8"></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#BBBEB4"></td>
	      </tr>
	      <tr>
	        <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
	       		<c:set var="hdlist" value="${xcdinfo.hdlist}"></c:set>
	       		<c:forEach begin="0" end="3" var="hdIndex" varStatus="v">
		       		<tr>
		       		<c:if test="${v.count == 1}">
					  <td width="7%">自FROM</td>
					</c:if>
		          	<c:if test="${v.count gt 1}">
					  <td width="7%">至TO</td>
					</c:if>
					<td width="10%"><span class="ysblue">
						 <%--<input type="text" name="cfhzl" onkeyup="toUpperCaseo(this)" value="${hdlist[hdIndex].cfhzl }" class="input1" maxlength="2" style="width:20px;"/>--%>
	             		 <input type="text" name="jcmc"  onkeyup="toUpperCaseo(this)" value="${hdlist[hdIndex].jcmc }" class="input1" maxlength="10" style="width:80px;text-align:center;"/>
	             		 <input name="jcszm" type="hidden"  value="" />
	            	</span></td>
	            	
		           <td width="10%"><span class="ysblue">
		               <%--<input type="text" name="ddhzl"  class="input1" onkeyup="toUpperCaseo(this)" value="${hdlist[hdIndex].ddhzl }" maxlength="2" style="width:20px;"  />--%>
		              <input type="text" name="hkgsjc" class="input1" onkeyup="toUpperCaseo(this)" value="${hdlist[hdIndex].hkgsjc }" maxlength="10" style="width:55px;" />
		           </span></td>
		           
		           <td width="8%"><span class="ysblue">
		             <input name="hbh" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:50px;" value="${fn:replace(hdlist[hdIndex].hbh,'*','') }" />
		           </span></td>
		           
		           <td width="5%"><span class="ysblue">
		             <input name="zwdj" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:30px;" value="${hdlist[hdIndex].zwdj }" />
		           </span></td>
		           
		            <td width="10%"><span class="ysblue">
		              <input name="cfrq" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:80px;" value="${hdlist[hdIndex].cfrq }" />
		            </span></td>
		            
		            <td width="6%"><span class="ysblue">
		              <input name="cfsj" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:50px;" value="${hdlist[hdIndex].cfsj }" />
		            </span></td>
		            
		            
		            <td width="14%"><span class="ysblue">
		              <input name="kpjb" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:80px;" value="${hdlist[hdIndex].kpjb }" />
		            </span></td>
		            
		            
		            <td width="13%"><span class="ysblue">
		              <input name="yxrq" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:90px;" value="${hdlist[hdIndex].yxrq }"/>
		            </span></td>
		            
		            
		            <td width="13%"><span class="ysblue">
		              <input name="jzrq" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:90px;" value="${hdlist[hdIndex].jzrq }"/>
		            </span></td>
		            
		            <td width="6%"><span class="ysblue">
		              <input name="mfxl" type="text" class="input1" onkeyup="toUpperCaseo(this)" style="width:40px;" value="${hdlist[hdIndex].mfxl }" />
		            </span>
		            </td></tr>
	       		</c:forEach>
	    		<tr>
	            <td width="8%">至TO</td>
	            <td width="10%"><span class="ysblue">
	               <input type="text" name="jcmc"  onkeyup="toUpperCaseo(this)" value="${hdlist[4].jcmc}" class="input1" maxlength="10" style="width:80px;text-align:center;"/>
	            </span></td>
	            <td width="5%"><span class="ysblue">
	              <input type="text" name="hd5Temp1" readonly value="" class="input1" style="width:55px;"/>
	            </span></td>
	            <td colspan="9"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="border:1px dashed #666;background:#FFFFEC;">
	              <tr>
	                <td width="22%">票价<span class="ysblue">
	                			<input name="pj_fh" type="text" class="input1" style="width:25px;font-size:11px;margin-left:-5px" value="CNY" onBlur="toUpperCase(this);"/>
								<input name="pj_zdj" type="text" class="input1" onblur="changecrinfo();calPjhj('${xcdinfo.ifgq}');" style="margin-left:-5px;width:55px;" value="${xcdinfo.pjZdj}" />
	                </span>
	                </td>
	                <td width="18%">机建<span class="ysblue">
	                <input name="jsf_fh" type="text" class="input1" style="width:20px;font-size:11px;margin-left:-5px" value="CN" onBlur="toUpperCase(this);"/>
	                <input name="pj_jsf" type="text" class="input1" onblur="changecrinfo();calPjhj('${xcdinfo.ifgq}');" style="width:45px;margin-left:-5px;" value="${xcdinfo.pjJsf}" />
	                </span></td> 
	                <td width="18%">税费<span class="ysblue">
	                	<input name="tax_fh" type="text" class="input1" style="width:20px;font-size:11px;margin-left:-5px" value="YQ" onBlur="toUpperCase(this);"/>
	                	<input name="pj_tax" type="text" class="input1" onblur="changecrinfo();calPjhj('${xcdinfo.ifgq}');" style="width:45px;margin-left:-5px;" value="${xcdinfo.pjTax eq '0' ? 'EXEMPT' : xcdinfo.pjTax}" />
	                </span></td>
	                <td width="18%">其它<span class="ysblue">
	                	<input name="qt_fh" type="text" class="input1" style="width:20px;font-size:11px;margin-left:-5px" value="" onBlur="toUpperCase(this);"/>
	                	<input name="pj_qt" type="text" class="input1" onblur="changecrinfo();calPjhj('${xcdinfo.ifgq}');" style="width:45px;margin-left:-5px;" value="0" />
	                </span></td>
	                <td width="22%">合计<span class="ysblue">
	                <input name="hj_fh" type="text" class="input1" style="width:25px;font-size:11px;margin-left:-5px" value="CNY" onBlur="toUpperCase(this);"/>
		            <input name="pj_hj" type="text" class="input1" onblur="changecrinfo();" style="width:64px;margin-left:-5px" value="${xcdinfo.pjHj}"/>
               	<img title="点击计算合计票价" style="cursor: pointer;"  src="${ctx}/static/images/wdimages/jsqicon.gif" onclick="calPjhj('0')">
	                </span></td>
	              </tr>
	            </table></td>
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#EFF3E8"></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#BBBEB4"></td>
	      </tr>
	      
	      <tr>
	        <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	          <tr>
	            <td width="32%" class="ysblue" style="color: blue;">电子客票号码<span class="ysblue" title="如果是连续客票,此处显示连续客票号">
	              <input name="tkno" type="text" onblur="changecrinfo()" class="input1" style="width:140px;" value="${not empty xcdinfo.lxkp ? xcdinfo.lxkp : xcdinfo.tkno }" maxLength="16"/>
	            </span></td>
	            <td width="14%">验证码<span class="ysblue">
	              <input name="yzm" type="text" class="input1" style="width:50px;" value="${xcdinfo.yzm}" maxlength="4"/>
	            </span></td>
	            <td width="34%">提示信息<span class="ysblue">
	              <input name="tsxx" type="text" class="input1" onblur="changecrinfo()" style="width:180px;" value="${xcdinfo.tsxx}" />
	            </span></td>
	            <td width="20%">保险费
		            <span class="ysblue">
		            	<c:if test="${xcdinfo.pjBx ne '0'}">
			            	<input name="pj_bx" type="text" class="input1" onblur="changecrinfo()" style="width:90px;" value="${xcdinfo.pjBx}" />
		            	</c:if>
		            	<c:if test="${xcdinfo.pjBx eq '0'}">
			            	<input name="pj_bx" type="text" class="input1" onblur="changecrinfo()" style="width:90px;" value="XXX" />
		            	</c:if>
		            </span>
	            </td>
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#EFF3E8"></td>
	      </tr>
	      <tr>
	        <td height="1" bgcolor="#BBBEB4"></td>
	      </tr>
	      
	      
	      <tr>
	        <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	          <tr>
	            <td width="12%">销售单位代号 </td>
	            <td width="18%">
	              <input type="text" name="office"  class="input1" value="${xcdinfo.office}" style="width:120px;">
	            </td>
	            <td width="51%">填开单位</td>
	            <td width="19%">填开日期</td>
	          </tr>
	          <tr>
	            <td>AGENT CODE</td>
	            <td><span class="ysblue">
	              <input name="dwdh" type="text" class="input1"  style="width:120px;" value="${xcdinfo.dwdh}" />
	            </span></td>
	            <td>
	            	<input name="tkdw" type="text" class="input1"  style="width:120px;" value="${xcdinfo.tkdw}" />
	            	<%-- 
	            	  <c:if test="${cs_tkdwxg eq '0'}">
	            		 <c:if test="${!empty cs_7841_tkdw}">
	            	    <input type="text" name="tkdw" value="${cs_7841_tkdw}" style="width:300px;height:22px;font-size:10pt;border-top:1px solid #7F9DB9;border-right:1px solid #7F9DB9;border-bottom:1px solid #7F9DB9;border-left:1px solid #7F9DB9;">
	            	    </c:if>
	            	     <c:if test="${empty cs_7841_tkdw}">
	            	     <input type="text" name="tkdw" value="${not empty xcdinfo.tkdw ? xcdinfo.tkdw : OFFLIST[0].BY1}" style="width:300px;height:22px;font-size:10pt;border-top:1px solid #7F9DB9;border-right:1px solid #7F9DB9;border-bottom:1px solid #7F9DB9;border-left:1px solid #7F9DB9;">
	            	    </c:if>
						<span style="width:18px;border:0px solid red;" id="tttt">
							<select name="r11" style="margin-left:-141px;width:160px; background-color:#FFEEEE;" onChange="tkdw.value=this.value;" onclick="this.selectedIndex=-1;">
				                <c:if test="${cs_tkdwkg eq '1'}">
			            			<option value=""></option>
		            			</c:if>
		            			
				                <logic:present name="OFFLIST">
					                <logic:iterate id="one" name="OFFLIST">
					                  <option value="${one.BY1}">${one.BY1}</option>
					                </logic:iterate>
			                	</logic:present>
			                	<c:if test="${!empty cs_7841_tkdw}">
			            			<option value="${cs_7841_tkdw }">${cs_7841_tkdw }</option>
		            			</c:if>
			                	
		            		</select>
			            </span> 
	            	</c:if>
	            	<c:if test="${cs_tkdwxg eq '1'}">
	            		<input type="text" name="tkdw" style="width:300px;height:22px;font-size:10pt;border-top:1px solid #7F9DB9;border-right:1px solid #7F9DB9;border-bottom:1px solid #7F9DB9;border-left:1px solid #7F9DB9;" value="${not empty xcdinfo.tkdw ? xcdinfo.tkdw : OFFLIST[0].BY1}">
	            	</c:if>--%>
	            </span></td>
	            <td><span class="ysblue">
	              <input name="tkrq" type="text" class="input1"  style="width:120px;" value="${xcdinfo.tkrq}" />
	            </span></td>
	          </tr>
	        </table></td>
	      </tr>
	  	</table>
	  	<input name="datafrom" type="hidden" value="${xcdinfo.datafrom}" />
  	</form>

	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" style="border:1px dotted gray;border-bottom:0px;">
		<tr><td align="left" style="color:red">
			  该行程单打印数据来源：<span><c:if test="${xcdinfo.datafrom eq '1'}">【系统订单】</c:if>
						         <c:if test="${xcdinfo.datafrom eq '2'}">【RT指令获取】</c:if>
								 <c:if test="${xcdinfo.datafrom eq '3'}">【DETR指令获取】</c:if>
								 <c:if test="${xcdinfo.datafrom eq '4'}"><span title="航信原始返回行程单信息：<br>${xcdinfo.jkfhsj }">【航信原始打印数据】</span></c:if>
							</span>
			</td>
		</tr>
	</table>
				      


	<script type="text/javascript">
	/**
	updateCreateStatus();
	//更新行程单状态
	function updateCreateStatus(){
		var tkno="${xcdinfo.tkno}";
		if(!isBlank(tkno)){
			var createStatus="${createStatus}";
			if("OK"==createStatus){
				setXcdztSpanInnerHTML("创建成功","",tkno);
				$("xcdzt"+tkno).value="3";
			}else if(createStatus.indexOf("|ERROR|")==0){
				setXcdztSpanInnerHTML("创建失败",createStatus.replace("|ERROR|",""),tkno);
			}
		}
	}
	**/
	
	
	//controlDisplay();
	/**
	  * 显示行程单号选择列表 
	  * 0显示不可修改   可选择行程单号
	  * 1显示可修改  可修改所有内容 可套打 可脱机打
	  * 2不显示不可修改  不显示
	  * 4显示所有内容都不能修改 
	  * 5只能订单管理打印
	  * 6是否允许脱机打
	*/
	 function controlDisplay(){
		var tkno="${param.tkno}";
		if(!tkno){
			return;
		}
		
		var cs_phxg = '${cs_phxg}';//本参数用于控制行程打印票号和PNR是否可修改。1为可修改，0为不可修改。默认为不可修改
		var cs_tkdwxg = '${cs_tkdwxg}';//行程单填开单位是否可以自由修改，0为是，1为否 默认为1不能修改
		
		//给行程单号赋值
		//if(!isBlank("${param.xcid}")){//如果是查看历史记录才重新给行程单显示赋值
			setxcdno("${xcdinfo.xcdh}");
		//}
		
		var printNum="${fn:length(xcdinfoPrintHistoryList) }";
		if($("xcdprintnum"+tkno)&&!isBlank(printNum)){
			$("xcdprintnum"+tkno).value=printNum;
		}
		
		if($("btnRePrint")){//重打按钮控制 未出票的或未打印的没有重打按钮
			if($("xcdprintnum"+tkno).value>0){
				$("btnRePrint").style.display="";
			}else{
				$("btnRePrint").style.display="none";
			}
		}
		
		var formObj=$("xcdform"+tkno);
		if(kzdy != '1'){
		    var inputNodes=formObj.getElementsByTagName("input");
		    for(var i=0;i<inputNodes.length;i++){
		    	if(inputNodes[i].type=="text"){
		    		inputNodes[i].readOnly="readOnly";
		    	}
		    }
		    var selectNodes=formObj.getElementsByTagName("select");
		    for(var i=0;i<selectNodes.length;i++){
		    	selectNodes[i].disabled="true";
		    }
		}
		
		if(kzdy!="4"&&cs_phxg=="1"){
			formObj.tkno.readOnly="";
			formObj.pnr_no.readOnly="";
		}else{
			formObj.tkno.readOnly="readOnly";
			formObj.pnr_no.readOnly="readOnly";
		}
		if(kzdy!="4"&&cs_tkdwxg=="0"){
			formObj.tkdw.readOnly="";
			formObj.r11.readOnly="";
		}else{
			formObj.tkdw.readOnly="readOnly";
			if(formObj.r11){
				formObj.r11.readOnly="readOnly";
			}
		}
		
		//国际行程单，票价都可以修改
		var jphcglgj=$("jphcglgj"+tkno).value;
		if("0"==jphcglgj){
			formObj.pj_fh.readOnly="";
			formObj.pj_zdj.readOnly="";
			formObj.jsf_fh.readOnly="";
			formObj.qt_fh.readOnly="";
			formObj.hj_fh.readOnly="";
		}
		//直销整改 其它可以修改
		formObj.pj_qt.readOnly="";
		
	}
	</script>