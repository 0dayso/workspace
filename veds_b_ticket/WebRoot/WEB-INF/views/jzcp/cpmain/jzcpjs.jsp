<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/static/js/op_policy.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hkgsbuyer.js"></script>
<script type="text/javascript" src="${ctx}/static/js/b2bcp/b2bcp.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/static/js/b2bcp/b2bnew.css"/>
<script type="text/javascript" src="${ctx}/static/js/miniboard.js"></script> 
<script type="text/javascript">
	var ddbh='${param.ddbh}';
	var hclx = '${jpKhdd.hclx}';
	var hkgs = '${jpKhdd.hkgs}';
	var yhbh = '${yhbh}';
	var loadPtFlag="${cpszOP.sfkq eq '1' and empty filterinfo}";
	var isZddkFlag='${cpszOP.opDq}'
	$(function(){
		var cjrRepeat='${cjrrepeat}';
		if(cjrRepeat=='1' || cjrRepeat=='2'){
			var msg='该订单中,乘机人姓名有相同,同一个PNR中出票可能会导致客人不能乘机,建议分离后再出票，是否确定继续采购?'
			if(cjrRepeat=='2'){
				msg='该订单中,乘机人姓名拼音有相同,同一个PNR中出票可能会导致客人不能乘机,建议分离后再出票，是否确定继续采购?';
			}
			$.layer({
		    	 area: ['400px', '180px'],
		         dialog: {
		             msg: msg,
		             btns: 2,                    
		             type: 4,
		             btn: ['确定','取消'],
		             no: function(){
		                window.close();
		             }
		         }
		    });
		}
		//加载平台采购状态
		loadOpStauts();
		//勾选需要选中的平台
		platChecked();
		//过滤换编码是否勾选，过滤特价是否勾选
		hbmTjGl();
		//是否显示pat
		gxPat();
		$('#dbpat').dblclick(pat);
		if(loadPtFlag==true || loadPtFlag=='true'){
			//加载平台政策
			loadOpPolicy();
		}
		//加载B2C政策
		loadB2CPolicy();
		//loadB2BPolicy("1");//1表示自动登录
	    indexB2bPage(ddbh,$("#b2b_policy_div"));
	})
	//清除采购支付状态
	function clearZfbj(cgddid){
		$.getJSON('${ctx}/vedsb/jzcp/cpmain/clearzfbj',{cgddid:cgddid},function(data){
			if('0'==data.status){
				layer.msg('清除成功',2,1);
				window.location.reload();
			}else{
				layer.msg('清除失败',2,3);
			}
		});
	}
	//pat
	function pat(){
		var sfxyh=$('#sfxyh').val();
		$.post('${ctx}/vedsb/jzcp/cpmain/pat',{ddbh:ddbh,sfxyh:sfxyh},function(data){
			$('#patcontextval').text(data);
		});
	}
	//显示或展示pat
	function showPat(obj){
		if(obj.checked){
			$('#patcontext_div').show();
			setCookie('showPat','1',360);
			pat();
		}else{
			$('#patcontext_div').hide();
			setCookie('showPat','0',360);
		}
	}
	//根据cookie判断是否显示pat
	function gxPat(){
		var showPat=getCookie('showPat');
		if(showPat=='1'){
			$('#spat').attr('checked',true);
			$('#patcontext_div').show();
		}
	}
	//平台列表展开收起
	function showPt(){
		var $ptShow=$('#ptshow');
		var isShow=$ptShow.attr('show');
		if(isShow=='1'){//收起
			$ptShow.attr({show:'0'});
			$('#ptimg').attr({src:'${ctx}/static/images/jzcp/show_down_blue.png'});
			$('#ptfilter_list_div').hide();
		}else{//展开
			$ptShow.attr({show:'1'});
			$('#ptimg').attr({src:'${ctx}/static/images/jzcp/show_up_blue.png'});
			$('#ptfilter_list_div').show();
		}
	}
	//把选中的平台放入cookie
	function setLoadPlat(){
		var platCode="";
		$('#ptfilter_list_div input[name="ptzcbs_chk"]:checked').each(function(){
			if(!platCode){
				platCode=$(this).val();
			}else{
				platCode+=","+$(this).val();
			}
		});
		if(!platCode){
			platCode="0";
		}
		setCookie('checkedPlat',platCode,360);
	}
	//勾选需要选中的平台
	function platChecked(){
		var checkedPlat=getCookie('checkedPlat');
		if(!checkedPlat){//第一次全选
			$('#ptfilter_list_div input[name="ptzcbs_chk"]').each(function(){
				$(this).attr({checked:true});
			});
		}else if(checkedPlat=='0'){//全部不选
			$('#ptfilter_list_div input[name="ptzcbs_chk"]').each(function(){
				$(this).attr({checked:false});
			});
		}else{
			var plats=checkedPlat.split(',');
			$.each(plats, function(i, n){
				var $plat=$('#ptzcbs_'+n+'_chk');
				if(!!$plat){
					$plat.attr({checked:true});
				}
			});
		}
	}
	//换编码、特价放cookie
	function setPnrTjCookie(obj,lb){
		var chec=$(obj).attr('checked');
		var val="0";
		if(chec){
			val="1";	
		}
		setCookie(lb,val,360);
	}
	//过滤换编码是否勾选，过滤特价是否勾选
	function hbmTjGl(){
		var hbmCk=getCookie('glhbm');
		var tjCk=getCookie('gltjzc');
		if(hbmCk==1){
			$('#filter_change_pnr_chk').attr({checked:true});
		}else{
			$('#filter_change_pnr_chk').attr({checked:false});
		}
		if(tjCk==1){
			$('#filter_thzc_chk').attr({checked:true});
		}else{
			$('#filter_thzc_chk').attr({checked:false});
		}
	}
	//验舱
	function valiHbhCw(btn){
		$(btn).attr('disabled',true);
		if(hclx=="1"){//单程的调用出票前降舱
			window.setTimeout(function(){
					$(btn).removeAttr('disabled');
			},2000);
			var url ="${ctx}/vedsb/jpddgl/jpkhdd/enterJcPage_"+ddbh;
		    $.layer({
				type: 2,
				title: ['<b>舱位信息</b>'],
				area: ['600px', '380px'],
				iframe: {src: url}
		    });
		}else{
			if($(btn).val().indexOf("点击验舱")==-1){
				return;
			}
			$(btn).val('正在验证');
			var url='${ctx}/vedsb/jzcp/cpmain/valiHbhCw_'+ddbh;
			$.getJSON(url,function(data){
				if(data.status=='-1'){
					var error="系统获取舱位剩余座位数发生异常！因为该政策需要换编码出票，请通过其他途径核实舱位剩余座位数，以保证出票的成功率！";
					$(btn).val('点击验舱');
					layer.alert(error,8,'验舱结果');
				}else{
					var str="";
					if(data.hasSeat=='true' || data.hasSeat==true){//验舱成功
						str="当前舱位:"+data.currentCw+" 还有剩余座位数，座位数状态：["+data.seatNum+"]。";
					}else{
						var str="当前舱位为："+data.currentCw+",座位数为："+data.seatNum+",小于编码中人数，<br>如果选择换编码出票的政策进行采购，供应可能会拒单。";
					}
					if(!!data.otherCabin){
						str+="<br>该航班下其他舱位情况："+data.otherCabin;
					}
					if(!!data.otherCabinSec){
						str+="<br>第二航程下其他舱位情况："+data.otherCabinSec;
					}
					layer.alert(str,-1,'验舱结果');
					$(btn).val('验舱完成');
					$(btn).attr('title',str);
				}
			});
		}
	}
	function changeXyh(obj){
		for(i=0;i<obj.length;i++){
			if(obj[i].selected){
				$("#sfxyh").val(obj[i].value);
			}
		}
	}
	
	function autoetdz(cplx,ddbh){
	     var officeid=$("#officeid option:selected").val();
	     if(officeid == ""){
	        layer.alert("请选择Office");
	        return;
	     }
	     
	     var url="${ctx}/vedsb/jzcp/cpmain/autoEtdz?ddbh="+ddbh+"&cplx="+cplx+"&officeid="+officeid;
	     	$.layer({
	        area: ['250px', '150px'],
	        dialog: {
	        msg: "确定出"+cplx+"票吗？",
	        btns: 2,                    
	        type: 4,
	        btn: ['确定','取消'],
	        yes: function(){
	             var ii = layer.load("正在出票中，请稍等……");
	             $.ajax({
       	 			type:"post",
 					url:url,
 					dataType : "json",
 					success:function(data){
						if(data.error_corder == -1){
							layer.alert("出票失败!"+data.error, 5, '提示信息',function index(){
               			 });
						}else{
							layer.alert("出票成功!", 1, '提示信息',function index() {
								window.close();
               			 	});
						}
 					}
       	 		});
		}, no: function(){
            layer.msg("放弃出"+cplx+"票!", 2, 3);
        }
     }});
   		
	}
</script>
