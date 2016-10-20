<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">

	function TFD_LodopPrint(action){
		//如果不是批量打印，则验证form表单
			if(!checkform()) {
				return ;
			}
			var flagtype="${size}";
			var dytype="${param.dytype}";
			LODOP = getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
			LODOP.PRINT_INITA(0,0,"200mm","112mm","申通");
			LODOP.ADD_PRINT_SETUP_BKIMG("<img src='/static/images/print_kdd/1001702st.gif'>");
			LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
			LODOP.SET_PRINT_STYLE("FontColor","#0000FF");
			LODOP.SET_PRINT_STYLE("FontSize","14");
			if(flagtype<2){
				var ct_sjdw = document.getElementById("ct_sjdw").value;//收件人公司
				var jjrdw = document.getElementById("mc").value;	 //寄件人公司
				var jjr = document.getElementById("lxr").value;	     //寄件人
				var jjr_dz = document.getElementById("dx").value;	 //寄件人地址
				var jjr_dh = document.getElementById("dh").value;	 //寄件人电话
				var ct_sjdz = document.getElementById("ct_sjdz").value;//收件人地址
			    var ct_sjr = document.getElementById("ct_sjr").value;  //收件人
			    var ct_smsmobilno = document.getElementById("ct_smsmobilno").value;//联系手机
			    var ct_njpm = document.getElementById("ct_njpm").value;//内件品名
				LODOP.ADD_PRINT_TEXT(124,73,252,30,jjrdw);
				LODOP.ADD_PRINT_TEXT(88,129,65,30,jjr);
				LODOP.ADD_PRINT_TEXT(153,111,252,57,jjr_dz);
				LODOP.ADD_PRINT_TEXT(214,99,163,25,jjr_dh);
				LODOP.ADD_PRINT_TEXT(126,438,205,27,ct_sjdw);
				LODOP.ADD_PRINT_TEXT(151,440,245,62,ct_sjdz);
				LODOP.ADD_PRINT_TEXT(86,451,75,30,ct_sjr);
				LODOP.ADD_PRINT_TEXT(213,503,139,26,ct_smsmobilno);
				LODOP.ADD_PRINT_TEXT(307,60,186,25,ct_njpm);
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
					LODOP.ADD_PRINT_TEXT(124,73,252,30,"${BUSER.shshb.ywy_compid}");
					LODOP.ADD_PRINT_TEXT(88,129,65,30,"${BUSER.xm}");
					LODOP.ADD_PRINT_TEXT(153,111,252,57,"${BUSER.shshb.yydz}");
					LODOP.ADD_PRINT_TEXT(214,99,163,25,"${BUSER.dh}");
					//这里的邮政编码是指收件人的公司；因为目前不知道哪个字段代表收件人公司
					LODOP.ADD_PRINT_TEXT(126,438,205,27,"${khddone.yzbm}");
					LODOP.ADD_PRINT_TEXT(151,440,245,62,"${khddone.xjdz}");
					LODOP.ADD_PRINT_TEXT(86,451,75,30,"${khddone.sjr}");
					LODOP.ADD_PRINT_TEXT(213,503,139,26,"${khddone.nxdh}");
					LODOP.ADD_PRINT_TEXT(307,60,186,25,document.getElementById("ct_njpm").value);
					LODOP.NewPage();
				</c:forEach>
				var trn = Lodop_Print(action);
				if(trn>0){
						save_yj('${khddone.ddbh}', '${mbid}');
					}
			}
			
			
	}
</script>
<div style='float:left;overflow:hidden;background-color:#808080;width:770;height:440px;'>
  <div class="pb" >
    <div id="page1" class="jc">
      <div class="jnc" STYLE=" left:0; top:0;clip:rect(0,600,302,0)" > 
        <!-- 背景图片-->
      <img class='hide_for_jatools_print' src="${ctx}/static/images/print_kdd/1001702st.gif" width="769px" height="432px" alt='本图片只在预览中可见'></div>
      <div id="move">
	       <!-- 寄件人公司  dh dx lxr mc ct_sjdw ct_sjr ct_smsmobilno ct_sjdz-->
	      <div class="jc" STYLE="position:absolute; z-index:50; left:80px; top:125px; width:40px;height:20px">
	        <input class="i3" readonly="readonly" type="text" id ="mc" name="mc" onblur="lengchek(this,19)" value="${BUSER.shshb.ywy_compid}" style=" width:185px; cursor: pointer;" />
	      </div> 
	       <!--寄件人 -->
	      <div class="jc" STYLE="position:absolute; z-index:50; left:130px; top:90px; width:40px;height:20px">
	        <input class="i3" readonly="readonly" type="text" id ="lxr" name="yjrn" onblur="lengchek(this,8)" value="${BUSER.xm}" style=" width:60px; cursor: pointer;" />
	      </div>
	       <!-- 寄件人地址 -->
	      <div class="jc" STYLE="position:absolute; z-index:50; left:110px; top:155px; width:40px;height:20px">
	        <textarea class="i3" readonly="readonly" id ="dx" name="dx"  rows="3" cols="50" style=" width:255px;overflow:hidden;cursor: pointer;" >${BUSER.shshb.yydz }</textarea> 
	      </div> 
	      
	       <!-- 寄件人电话 -->
	      <div class="jc" STYLE="position:absolute; z-index:50; left:120px; top:215px; width:40px;height:20px">
	        <input class="i3" readonly="readonly" type="text" id ="dh" name="nxdh" onblur="lengchek(this,20)" value="${BUSER.dh}" style=" width:170px; cursor: pointer;" />
	      </div> 
	       <!-- 收件人公司      -->
	      <div class="jc" STYLE="position:absolute; z-index:50; left:442px; top:126px; width:40px;height:20px">
	        <input class="i3" readonly="readonly" type="text" id ="ct_sjdw" name="ct_sjdw" onblur="checksjdwlength()" value="" style=" width:185px; cursor: pointer;" />
	      </div> 
	       <!-- 收件人 -->
	      <div class="jc" STYLE="position:absolute; z-index:50; left:450px; top:90px; width:40px;height:20px">
	        <input class="i3" readonly="readonly" type="text" id ="ct_sjr" name="sjr" onblur="checksjrlength()" value="${khdd.sjr}" style=" width:80px; cursor: pointer;" />
	      </div> 
	       <!--收件人地址 -->
	      <div class="jc" STYLE="position:absolute; z-index:50; left:440px; top:155px; width:40px;height:20px">
	        <textarea class="i3" readonly="readonly" id="ct_sjdz" name="xjdz"  rows="3" cols="50" onblur="checksjdzlength()" style=" width:245px;overflow:hidden;cursor: pointer;" > ${khdd.xjdz}</textarea> 
	      </div> 
	       <!-- 收件人电话 -->   
	      <div class="jc" STYLE="position:absolute; z-index:50; left:500px; top:215px; width:40px;height:20px">
	        <input class="i3" readonly="readonly" type="text" id ="ct_smsmobilno" name="ct_smsmobilno" onblur="checksj()" value="${khdd.nxdh}" style=" width:150px; cursor: pointer;" />
	      </div> 
	      <!-- 内件品名 --> 
		  <div class="jc" STYLE="position:absolute; z-index:50; left:58px; top:308px; width:40px;height:20px">
		    <input readonly="readonly" class="i3" type="text" id ="ct_njpm" name="ct_njpm" onblur="checksj()" value="" style=" width:190px; cursor: pointer;" />
		  </div>  
	   </div> 
	     
    </div>
  </div>
</div>


