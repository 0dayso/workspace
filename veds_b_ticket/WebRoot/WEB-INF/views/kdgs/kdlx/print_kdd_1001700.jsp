<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript">
	/* function initPage() {
		LODOP = getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
		LODOP.PRINT_INITA(0,0,"200mm","112mm","默认邮寄单");
		LODOP.ADD_PRINT_SETUP_BKIMG("<img src='/static/images/print_kdd/1001700mr.gif'>");
		LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
		LODOP.SET_PRINT_STYLE("FontColor","#0000FF");
		LODOP.SET_PRINT_STYLE("FontSize","14");
		LODOP.ADD_PRINT_TEXT(267,320,361,30,'${khdd.yzbm}');
		LODOP.ADD_PRINT_TEXT(141,209,361,30,'${khdd.sjr}');
		LODOP.ADD_PRINT_TEXT(360,546,188,30,'${khdd.nxsj}');
		LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",25);
		LODOP.ADD_PRINT_TEXT(17,28,194,29,'${khdd.xjdz}');
		LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",25);
		LODOP.ADD_PRINT_TEXT(227,292,198,28,'${user.dh}');
		LODOP.ADD_PRINT_TEXT(227,487,198,28,'${user.email}');
		LODOP.NewPage();
	} */

	function TFD_LodopPrint(action){
		//如果不是批量打印，则验证form表单
			if(!checkyjform()) {
				return ;
			}
			var flagtype="${size}";
			var dytype="${param.dytype}";
			LODOP = getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
			LODOP.PRINT_INITA(0,0,"200mm","112mm","默认邮寄单");
			LODOP.ADD_PRINT_SETUP_BKIMG("<img src='/static/images/print_kdd/1001700mr.gif'>");
			LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
			LODOP.SET_PRINT_STYLE("FontColor","#0000FF");
			LODOP.SET_PRINT_STYLE("FontSize","14");
			if(flagtype<2){//合并打印和打印快递单
				var jjr_yzbm = document.getElementById("jjr_yzbm").innerText;	 //寄件人邮编
				var ct_yzbm1 = document.getElementById("ct_yzbm1").innerText;	 //收件人邮编
				var jjr_dz = document.getElementById("dx").innerText;	 //寄件人地址
				var ct_sjdz1 = document.getElementById("ct_sjdz1").innerText;//收件人地址
				var ct_sjr1 = document.getElementById("ct_sjr1").innerText;  //收件人
				var ct_smsmobilno = document.getElementById("ct_smsmobilno").innerText; 
				LODOP.ADD_PRINT_TEXT(267,320,361,30,jjr_dz);
				LODOP.ADD_PRINT_TEXT(141,209,361,30,ct_sjdz1);
				LODOP.ADD_PRINT_TEXT(360,546,194,30,jjr_yzbm);
				LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",24);
				LODOP.ADD_PRINT_TEXT(17,28,194,29,ct_yzbm1);
				LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",24);
				LODOP.ADD_PRINT_TEXT(227,292,198,28,ct_sjr1);
				LODOP.ADD_PRINT_TEXT(227,487,198,28,ct_smsmobilno);
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
					LODOP.ADD_PRINT_TEXT(267,320,361,30,'${BUSER.shshb.yydz }');
					LODOP.ADD_PRINT_TEXT(141,209,361,30,'${khddone.xjdz}');
					LODOP.ADD_PRINT_TEXT(360,546,188,30,'${BUSER.shshb.yzbm}');
					LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",24);
					LODOP.ADD_PRINT_TEXT(17,28,194,29,'${khddone.yzbm}');
					LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",24);
					LODOP.ADD_PRINT_TEXT(227,292,198,28,'${khddone.sjr}');
					LODOP.ADD_PRINT_TEXT(227,487,198,28,'${khddone.nxdh}');
					LODOP.NewPage();
				</c:forEach>
				var trn = Lodop_Print(action);
				if(trn>0){
						save_yj('${khddone.ddbh}', '${mbid}');
					}
			}
			
			
	}
</script>
<div style="background:url(/static/images/print_kdd/1001700mr.gif) no-repeat;width:775px;height:386px;" align="center" >
     <div id="move" class="jc">
      	 <div STYLE="position:absolute; z-index:52; left:19px; top:10px; width:200px; height:28px; overflow: hidden;">
        	<p class="c5" style="float:left; height:28px; line-height:30px; font-size:30px; letter-spacing:20px; color:#000;">
	        	<input type="hidden" name ="ct_yzbm" id="ct_yzbm" value="${khdd.yzbm}"  maxlength="6"/>
				<div id="ct_yzbm1" onblur="checkYzbm(this.innerText)" style="font-size:24px; height:24px;width:190px; overflow:hidden; line-height:24px; font-weight:bold; letter-spacing:21px; white-spacing:nowrap;word-break:keep-all;">
				 	${khdd.yzbm}
				</div>
	        </p>
        </div>
      	<div STYLE="position:absolute; z-index:51; left:300px; top:120px; width:520px;">
      		<p class="c4" style="margin-top:10px;margin-left:260px;font-size:18px; font-family:楷体; line-height:40px; 
      				width:360px; ">
       			<input type="hidden" name ="ct_sjdz" id="ct_sjdz"  value="${khdd.xjdz}" />
				<div id="ct_sjdz1" style="float:left;font-size:20px;width:340px; line-height:44px; height:68px; font-weight:bold;word-break:break-all;" 
         			onblur="checksjdzlength(this.innerText)">${khdd.xjdz}
         		</div>
			</p>
    	</div>
	    <div  STYLE="left:304px; top:210px; width:228px;height:40px; position:absolute; z-index:50;">
	        <p class="c4" style="margin-top:10px;font-size:18px; font-family:楷体; line-height:40px; 
      				width:360px; ">
				<!-- 寄件人地址  商户的营业地址-->
		        <div id ="dx" name="dx" style="float:left;font-size:20px;width:340px; line-height:40px; height:68px; font-weight:bold;word-break:break-all;" >
			         ${BUSER.shshb.yydz}
			    </div> 
	        </p>
     	</div>
     	<div STYLE="left:270px; top:180px; width:400px;height:54px; position:absolute; z-index:49;">
	        <p class="c4" >
		        <input type="hidden" name ="ct_sjr" id="ct_sjr"  value="${khdd.sjr}"  />
				<div id="ct_sjr1" onblur="checksjrlength(this.innerText)" style="font-size:20px; width:400px; overflow:hidden;height:30px; font-weight:bold; white-spacing:nowrap;word-break:keep-all;">
				    ${khdd.sjr}&nbsp;&nbsp;(收)
				</div>
				<div id="ct_smsmobilno" style="font-size:20px; width:200px;overflow:hidden;height:24px; font-weight:bold; white-spacing:nowrap;word-break:keep-all;">
				</div>
	        </p>
	    </div>
        <div STYLE="left:543px; top:350px; width:228px;height:20px; position:absolute; z-index:48;">
	        <p class="c5" style="float:left; height:28px; line-height:30px; font-size:30px; letter-spacing:20px; color:#000;">
				<!-- 寄件人邮编 -->
		        <div id="jjr_yzbm" onblur="checkYzbm(this.innerText)" style="font-size:24px; height:24px;width:190px; overflow:hidden; line-height:24px; font-weight:bold; letter-spacing:21px; white-spacing:nowrap;word-break:keep-all;">
		        	${BUSER.shshb.yzbm}
		        </div>  
	        </p>
     	</div>
   	</div>
</div>
