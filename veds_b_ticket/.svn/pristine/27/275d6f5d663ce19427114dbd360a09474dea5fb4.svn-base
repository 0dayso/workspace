<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	function TFD_LodopPrint(action){
		//如果不是批量打印，则验证form表单
		var flagtype="${size}";
		var dytype="${param.dytype}";
		LODOP = getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
		LODOP.PRINT_INITA(0,0,"200mm","112mm","EMS");
		LODOP.ADD_PRINT_SETUP_BKIMG("<img src='/static/images/print_kdd/1001706ems.jpg'>");
		LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
		LODOP.SET_PRINT_STYLE("FontColor","#0000FF");
		LODOP.SET_PRINT_STYLE("FontSize","14");
		if(flagtype<2){//合并打印和打印快递单
			var ct_sjdw = document.getElementById("ct_sjdw").value;//收件人公司
			var jjrdw = document.getElementById("mc").value;	 //寄件人公司
			var jjr = document.getElementById("lxr").value;	     //寄件人
			var jjr_dz = document.getElementById("dx").value;	 //寄件人地址
			var jjr_dh = document.getElementById("dh").value;	 //寄件人电话
			var ct_sjdz = document.getElementById("ct_sjdz").value;//收件人地址
		    var ct_sjr = document.getElementById("ct_sjr").value;  //收件人
		    var ct_smsmobilno = document.getElementById("ct_smsmobilno").value;//联系手机
			var jjryb = document.getElementById("by2").value;        //寄件人邮编
			var sjryb = document.getElementById("ct_yzbm").value;        //收件人邮编
			LODOP.ADD_PRINT_TEXT(96,73,151,25,jjrdw);
			LODOP.ADD_PRINT_TEXT(74,60,63,25,jjr);
			LODOP.ADD_PRINT_TEXT(119,55,240,35,jjr_dz);
			LODOP.ADD_PRINT_TEXT(72,214,123,27,jjr_dh);
			LODOP.ADD_PRINT_TEXT(208,69,174,25,ct_sjdw);
			LODOP.ADD_PRINT_TEXT(232,52,247,35,ct_sjdz);
			LODOP.ADD_PRINT_TEXT(184,65,65,23,ct_sjr);
			LODOP.ADD_PRINT_TEXT(184,222,111,24,ct_smsmobilno);
			LODOP.ADD_PRINT_TEXT(152,273,147,20,jjryb);
			LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",11);
			LODOP.ADD_PRINT_TEXT(286,272,147,20,sjryb);
			LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",11);
			var trn = Lodop_Print(action);
				if(trn>0){
					if(dytype&&dytype>1){//合并打印
						save_yj('${param.ddbhs}', '${mbid}');
					}else{//打印快递单
						save_yj('${khdd.ddbh}', '${mbid}');
					}
				}
		}else{//批量打印
				<c:forEach items="${khddList}" var="khddone">
					LODOP.ADD_PRINT_TEXT(96,73,151,25,"${BUSER.shshb.ywy_compid}");
					LODOP.ADD_PRINT_TEXT(74,60,63,25,"${BUSER.xm}");
					LODOP.ADD_PRINT_TEXT(119,55,240,35,"${BUSER.shshb.yydz}");
					LODOP.ADD_PRINT_TEXT(72,214,123,27,"${BUSER.dh}");
					//这里的邮政编码是指收件人的公司；因为目前不知道哪个字段代表收件人公司
					LODOP.ADD_PRINT_TEXT(208,69,174,25,"${khddone.yzbm}");
					LODOP.ADD_PRINT_TEXT(232,52,247,35,"${khddone.xjdz}");
					LODOP.ADD_PRINT_TEXT(184,65,65,23,"${khddone.sjr}");
					LODOP.ADD_PRINT_TEXT(184,222,111,24,"${khddone.nxdh}");
					LODOP.ADD_PRINT_TEXT(152,273,147,20,"${BUSER.shshb.yzbm}");
					LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",11);
					LODOP.ADD_PRINT_TEXT(286,272,147,20,"${khddone.yzbm}");
					LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",11);
					LODOP.NewPage();
				</c:forEach>
				var trn = Lodop_Print(action);
				if(trn>0){
						save_yj('${khddone.ddbh}', '${mbid}');
					}
			
			}
		} 

</script>
<div style="background:width:575px;height:356px;" align="center" >
  	<div style='float:left;overflow:hidden;background-color:#808080;width:599;height:360px;'>
	  	<div id="page1" class="jc">
		  	<div class="jnc" STYLE=" left:0; top:0;clip:rect(0,600,302,0)" > 
		  		<!-- 背景图片-->
  				<img class='hide_for_jatools_print' src="${ctx}/static/images/print_kdd/1001706ems.jpg" width="599px" height="360px" alt='本图片只在预览中可见'>
  			</div>
  			<!-- 寄件人公司  dh dx lxr mc ct_sjdw ct_sjr ct_smsmobilno ct_sjdz-->
   	        <div class="jc" STYLE="position:absolute; z-index:50; left:55px; top:78px; width:40px;height:20px">
			    <input class="i3" type="text" id ="mc" name="mc" onblur="lengchek(this,19)" value="${BUSER.shshb.ywy_compid}" style=" width:185px; cursor: pointer;" />
			</div> 
			<!--寄件人 -->
			<div class="jc" STYLE="position:absolute; z-index:50; left:45px; top:55px; width:40px;height:20px">
			    <input class="i3" type="text" id ="lxr" name="yjrn" onblur="lengchek(this,8)" value="${BUSER.xm}" style=" width:70px; cursor: pointer;" />
			</div>
			<!-- 寄件人地址 -->
			<div  class="jc" STYLE="position:absolute; z-index:50; left:38px; top:96px; width:40px;height:15px">
			    <textarea class="i3"  id ="dx" name="dx"  rows="2" cols="50" style=" width:205px;overflow:hidden;cursor: pointer;" >${BUSER.shshb.yydz }</textarea> 
			</div> 
			<!-- 寄件人邮编 -->
			<div  class="jc" STYLE="position:absolute; z-index:50; left:215px; top:125px; width:40px;height:20px">
			    <input class="i3" type="text" id ="by2" name="by2" onblur="checkYzbm(this)" value="${BUSER.shshb.yzbm}" style=" width:65px; cursor: pointer;" />
			</div>   
			<!-- 寄件人电话 -->
			<div  class="jc" STYLE="position:absolute; z-index:50; left:170px; top:55px; width:30px;height:20px">
			    <input class="i3" type="text" id ="dh" name="dh" onblur="lengchek(this,20)" value="${BUSER.dh}" style=" width:120px; cursor: pointer;" />
			</div> 
			<!-- 收件人公司      -->
			<div  class="jc" STYLE="position:absolute; z-index:50; left:55px; top:175px; width:40px;height:20px">
			    <input class="i3"  type="text" id ="ct_sjdw" name="ct_sjdw" onblur="checksjdwlength()" value="" style=" width:125px; cursor: pointer;" />
			</div> 
			<!-- 收件人 -->
			<div  class="jc" STYLE="position:absolute; z-index:50; left:45px; top:155px; width:40px;height:20px">
			    <input class="i3" type="text" id ="ct_sjr" name="sjr" onblur="checksjrlength()" value="${khdd.sjr}" style=" width:80px; cursor: pointer;" />
			</div> 
			<!--收件人地址 -->
			<div  class="jc" STYLE="position:absolute; z-index:50; left:38px; top:195px; width:40px;height:20px">
			    <textarea class="i3" id="ct_sjdz" name="xjdz"  rows="2" cols="50" onblur="checksjdzlength()" style=" width:215px;overflow:hidden;cursor: pointer;" > ${khdd.xjdz}</textarea> 
			</div> 
			<!-- 收件人邮编 -->
			<div  class="jc" STYLE="position:absolute; z-index:50; left:215px; top:240px; width:40px;height:20px">
			    <input class="i3" type="text" id ="ct_yzbm" name="yzbm" onblur="checkYzbm(this)" value="${khdd.yzbm}" style=" width:75px; cursor: pointer;" />
			</div>  
			<!-- 收件人电话 -->   
			<div  class="jc" STYLE="position:absolute; z-index:50; left:178px; top:155px; width:40px;height:20px">
			    <input class="i3" type="text" id ="ct_smsmobilno" name="nxdh" onblur="checksj()" value="${khdd.nxdh}" style=" width:110px; cursor: pointer;" />
			</div> 
      	</div>
      </div>
</div>