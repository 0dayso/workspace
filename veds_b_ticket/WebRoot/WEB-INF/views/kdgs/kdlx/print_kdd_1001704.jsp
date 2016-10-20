<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">

	function TFD_LodopPrint(action){
		//如果不是批量打印，则验证form表单
		var flagtype="${size}";
		var dytype="${param.dytype}";
		LODOP = getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
		LODOP.PRINT_INITA(0,0,"200mm","112mm","圆通");
		LODOP.ADD_PRINT_SETUP_BKIMG("<img src='/static/images/print_kdd/1001704yt.gif'>");
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
			LODOP.ADD_PRINT_TEXT(91,70,200,25,jjrdw);
			LODOP.ADD_PRINT_TEXT(63,75,80,25,jjr);
			LODOP.ADD_PRINT_TEXT(117,69,240,40,jjr_dz);
			LODOP.ADD_PRINT_TEXT(174,231,119,25,jjr_dh);
			LODOP.ADD_PRINT_TEXT(102,395,200,25,ct_sjdw);
			LODOP.ADD_PRINT_TEXT(126,394,240,40,ct_sjdz);
			LODOP.ADD_PRINT_TEXT(79,397,79,25,ct_sjr);
			LODOP.ADD_PRINT_TEXT(174,433,101,25,ct_smsmobilno);
			LODOP.ADD_PRINT_TEXT(218,78,137,25,ct_njpm);					
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
				LODOP.ADD_PRINT_TEXT(91,70,200,25,"${BUSER.shshb.ywy_compid}");
				LODOP.ADD_PRINT_TEXT(63,75,80,25,"${BUSER.xm}");
				LODOP.ADD_PRINT_TEXT(117,69,240,40,"${BUSER.shshb.yydz}");
				LODOP.ADD_PRINT_TEXT(174,231,119,25,"${BUSER.dh}");
				//这里的邮政编码是指收件人的公司；因为目前不知道哪个字段代表收件人公司
				LODOP.ADD_PRINT_TEXT(102,395,200,25,"${khddone.yzbm}");
				LODOP.ADD_PRINT_TEXT(126,394,240,40,"${khddone.xjdz}");
				LODOP.ADD_PRINT_TEXT(79,397,79,25,"${khddone.sjr}");
				LODOP.ADD_PRINT_TEXT(174,433,101,25,"${khddone.nxdh}");
				LODOP.ADD_PRINT_TEXT(218,78,137,25,document.getElementById("ct_njpm").value);
				LODOP.NewPage();
			</c:forEach>
			var trn = Lodop_Print(action);
			if(trn>0){
					save_yj('${khddone.ddbh}', '${mbid}');
				}
		}
	}
</script>
<div style="background:width:775px;height:386px;" align="center" >
	<div style='float:left;overflow:hidden;background-color:#808080;width:770;height:440px;'>
    	<div class="pb" >
    		<div id="page1" class="jc">
      	  		<div class="jnc" STYLE=" left:0; top:0;clip:rect(0,600,302,0)" > 
          			<!-- 背景图片-->
	      			<img class='hide_for_jatools_print' src="${ctx}/static/images/print_kdd/1001704yt.gif" width="769px" height="432px" alt='本图片只在预览中可见'>
	  	  		</div>
		      	<div id="move">
				    <!-- 寄件人公司  dh dx lxr mc ct_sjdw ct_sjr ct_smsmobilno ct_sjdz-->
				    <div class="jc" STYLE="position:absolute; z-index:50; left:70px; top:95px; width:40px;height:20px">
				    	<input class="i3" type="text" id ="mc" name="mc" onblur="lengchek(this,19)" value="${BUSER.shshb.ywy_compid}" style=" width:185px; cursor: pointer;" />
				    </div> 
				    <!--寄件人 -->
				    <div class="jc" STYLE="position:absolute; z-index:50; left:75px; top:70px; width:40px;height:20px">
			        	<input class="i3" type="text" id ="lxr" name="yjrn" onblur="lengchek(this,8)" value="${BUSER.xm}" style=" width:60px; cursor: pointer;" />
			      	</div>
			       	<!-- 寄件人地址 -->
			      	<div class="jc" STYLE="position:absolute; z-index:50; left:70px; top:125px; width:40px;height:20px">
			        	<textarea class="i3"  id ="dx" name="dx"  rows="2" cols="50" style=" width:255px;overflow:hidden;cursor: pointer;" >${BUSER.shshb.yydz }</textarea> 
			     	</div> 
				    <!-- 寄件人电话 -->
				    <div class="jc" STYLE="position:absolute; z-index:50; left:250px; top:195px; width:30px;height:20px">
				        <input class="i3" type="text" id ="dh" name="dh" onblur="lengchek(this,20)" value="${BUSER.dh}" style=" width:100px; cursor: pointer;" />
				    </div> 
				    <!-- 收件人公司      -->
				    <div class="jc" STYLE="position:absolute; z-index:50; left:430px; top:110px; width:40px;height:20px">
				        <input class="i3"  type="text" id ="ct_sjdw" name="ct_sjdw" onblur="checksjdwlength()" value="" style=" width:185px; cursor: pointer;" />
				    </div> 
				    <!-- 收件人 -->
				    <div class="jc" STYLE="position:absolute; z-index:50; left:430px; top:85px; width:40px;height:20px">
				        <input class="i3" type="text" id ="ct_sjr" name="sjr" onblur="checksjrlength()" value="${khdd.sjr}" style=" width:80px; cursor: pointer;" />
				    </div> 
				    <!--收件人地址 -->
				    <div class="jc" STYLE="position:absolute; z-index:50; left:410px; top:135px; width:40px;height:20px">
				        <textarea class="i3" id="ct_sjdz" name="xjdz"  rows="3" cols="50" onblur="checksjdzlength()" style=" width:245px;overflow:hidden;cursor: pointer;" > ${khdd.xjdz}</textarea> 
				    </div> 
				    <!-- 收件人电话 -->   
				    <div class="jc" STYLE="position:absolute; z-index:50; left:460px; top:195px; width:40px;height:20px">
				        <input class="i3" type="text" id ="ct_smsmobilno" name="nxdh" onblur="checksj()" value="${khdd.nxdh}" style=" width:110px; cursor: pointer;" />
				    </div> 
				    <!-- 内件品名 --> 
				    <div class="jc" STYLE="position:absolute; z-index:50; left:80px; top:248px; width:40px;height:20px">
				        <input class="i3" type="text" id ="ct_njpm" name="ct_njpm" onblur="checksj()" value="" style=" width:150px; cursor: pointer;" />
				    </div> 
			   </div> 
       	  </div>
       	</div>
    </div>
</div>


