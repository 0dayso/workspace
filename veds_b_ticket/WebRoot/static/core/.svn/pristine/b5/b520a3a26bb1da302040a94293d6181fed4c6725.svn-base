$(function(){
	$.ajaxSetup({cache:false});
	/*ie6,7下拉框美化*/
    if ( $.browser.msie ){
    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
    		$('.select').each(function(i){
			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
			 });
    	}
    }
    
    $("#searchFormButton").on('click',function(){
    	$("#searchForm").attr("from","searchButton");
		$("#searchForm").pageSearch();

	});
  
    //从turning页面过来要自动查询
    if($("#turning").val()=="1"){
    	if($("#pageNum").val()==""){
    		$("#pageNum").val("1");
    	}
    	$("#searchForm").pageSearch();
    }
});
(function($) {    
	/**
	 * 参数是一个json对象
	 * tplid:显示模板ID
	 * tableid:列表显示的ID
	 * 
	 */
	$.fn.pageSearch = function(options) {
		var auto_ref=typeof($("#auto_ref").html())=="undefined";//undefined 自动刷新用不需要倒计时
		if(!auto_ref){
			$.autoRefreshStop();
		}
		var layerObject = layer.load('加载中');
		options = $.extend(true, {
				tplid:  "tpl_list_table",
		        tableid: "list_table",
		        tablepageid: "list_table_page",
		        tablepageid1: "list_table_page1"
		}, options);
		options.form=this;
		options.pageNum=options.form.find("input[id='pageNum']");
		//如果不是点击分页的那么就是查询框的，查询框需要将page改为1
    	if(options.form.attr("from")=="searchButton"){
    		options.pageNum.val("1");
    		$("#"+options.tablepageid).attr("loaded","");
    	}
    	if(!options.pageNum.val()){
    		layer.close(layerObject);
    		return;
    	}
		this.ajaxSubmit({
			success: function(result){
				//查询成功后回调
				try{
					if(!auto_ref){
						$.autoRefreshStop();
						$.autoRefresh({showResponse:function(){$("#searchFormButton").click();}});	
					}
					if(searchPageSuccess){
						searchPageSuccess(options);
					}
				
				}catch(e){
					
				}
				
				layer.close(layerObject);
				options.form.attr("from","");
				 if(result.list==null){
					 $("#"+options.tableid).html("");
					 $("#"+options.tablepageid).html("");
					 $("#"+options.tablepageid1).html("");
					 return;
				 }
				var gettpl = $("#"+options.tplid).html();
				laytpl(gettpl).render(result, function(html){
					   $("#"+options.tableid).html(html);
				});
				//表格行，鼠标放上去变色
				$(".tr:odd").css("background", "#f9f9f9");
				$(".tr:odd").each(function(){
					$(this).hover(function(){
						$(this).css("background-color", "#fffcea");
					}, function(){
						$(this).css("background-color", "#f9f9f9");
					});
				});
				$(".tr:even").each(function(){
					$(this).hover(function(){
						$(this).css("background-color", "#fffcea");
					}, function(){
						$(this).css("background-color", "#fff");
					});
				}); 
				$(".list_table").colResizable({
				        liveDrag:true,
				        gripInnerHtml:"<div class='grip'></div>", 
				        draggingClass:"dragging", 
				        minWidth:30
			    });
				
				//添加排序
				tplHeadSort();
				
				if(result.totalPages!=0){
					//如果没有总页数,那么就新增分页组件,以后就要不在新增了
					if($("#"+options.tablepageid).attr("loaded")!="1"){
						laypage({
						    cont: [options.tablepageid,options.tablepageid1], //支持显示多个分页，容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
						    pages: result.pageAllCount, //总页数
						    allCount:result.totalCount,
						    curr: result.pageNum, //当前页
						    groups: 7, //连续显示分页数
						    skip: true, //是否开启跳页
						    jump: function(e){ //触发分页后的回调
						    	options.pageNum.val(e.curr);
						    	if($("#"+options.tablepageid).attr("loaded")=="1"){
						    		//$(options.btnObject).attr("from","page");
						    		//$(options.btnObject).click();
						    		options.form.pageSearch();
						    	}
						    }
						});
						//已加载分页
						
						$("#"+options.tablepageid).attr("loaded","1");
					}
					$("select[name=selectPage]").each(function(i){
		    			$(this).val($("#pageSize").val());
		    		});
				}else{
					$("#"+options.tablepageid).html("");
					$("#"+options.tablepageid1).html("");
				}
			}
		});
	};
	
	var qtime = 30;
	var autoRefrefsh = {
		time : 30,
		fieldId : "auto_ref",
		showResponse : function(){
			
		}
	};
	var autoRefreshThread;
	var autoRefreshResponse;
	var djs = function(userOptions){
		if(qtime == 1){
			if(userOptions.showResponse){
				userOptions.showResponse();
			}
		}else{
			qtime--;
			$("#"+userOptions.fieldId).text(qtime);
			autoRefreshThread = window.setTimeout(function(){djs(userOptions);},1000);
		}
	};
	$.autoRefresh = function(options){
		var userOptions = $.extend(true,{},autoRefrefsh,options);
		qtime = userOptions.time;
		autoRefreshResponse = userOptions.showResponse;
		djs(userOptions);
	};
	
	$.autoRefreshStop = function(){
		if(autoRefreshThread){
			window.clearTimeout(autoRefreshThread);
		}
	};
	
	$.autoRefreshRun = function(options){
		var userOptions = autoRefrefsh;;
		userOptions['time'] = qtime;
		userOptions['showResponse'] = autoRefreshResponse;
		djs(userOptions);
	};
	
	$.browser = function(){
		$.support();
	};
	

	//setTimeout操作，支持带参函数调用 
	$.extend({
        timeOut:function(_callBack,_times,_data){//_callBack：回调函数，_times：延时毫秒数，_data：回调函数所需参数，可不传
        	
        	if(!!!_times){
        		_times = 1000;
        	}
        
        	if(_callBack){
	        	_callBack(_data);//具体实现工具类
        	}

            setTimeout("$.timeOut("+_callBack+","+_times+",'"+_data+"')",_times);
        }
    });
   
	
	
})(jQuery);
(function($){
	// 对Date的扩展，将 Date 转化为指定格式的String   
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
	// 例子：   
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
	Date.prototype.format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "H+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}  
	
	$.getUrlParam = function(name){
		 var aQuery = window.location.href.split("?");//取得Get参数
         if(aQuery.length > 1)
         {
             var aBuf = aQuery[1].split("&");
             for(var i=0, iLoop = aBuf.length; i<iLoop; i++)
             {
                 var aTmp = aBuf[i].split("=");//分离key与Value
                 if(aTmp.length==2){
                	 if(aTmp[0]==name){
                		 return aTmp[1];
                	 }
                 }
             }
         }
         return "";
	}
	$.nullToEmpty = function(value){
		if(value==null){
			return "";
		}
		return value;
		
	}
	// 格式化输出日期，value是一个long型的毫秒
	$.dateF = function(value,f){
		if( value == null || value == ""){
			return "";
		}
		if(f == null || f=="") {
			f="yyyy-MM-dd HH:mm:ss";
		}
		var myDate=new Date();
		myDate.setTime(value);
		return myDate.format(f);
	}
	
	
	//从json对象数组中查找，value值等于v 的json对象
	//js 文件和对象说明   
	
	//国际机场               _gjcity      gj_city.js
	//国内机场               _gncity      gn_city.js
	//国内城市               _gncs        gn_cs.js
	//国航空公司             _gnhkgs     gnhkgs.js
	//所有航空公司          _hkgs       hkgs.js
	//国际城市               _sf          sf.js
	
	//例子 得到CA对应的航空公司名称   $.findJson(_hkgs,"CA").mc;
	$.findJson = function(jsonArray,v){
		jsonArray = eval(jsonArray);
		for(var p in jsonArray){//遍历json数组时，这么写p为索引，0,1
			if(jsonArray[p].value == v){
				return jsonArray[p];
			}
		}
		return {"value":"","label":"","mc":"","pyszm":""};
	}
	//截取字符串超过len长度的字符，并在title中展示
	$.cut = function(str,len){
		if(str == null || str == ""){
			return "";
		}
		if(str.length > len +2){
			return "<span title='" + str + "'>" + str.substring(0, len) + "...</span>";
		}else{
			return  str;
		}
	}
	/*
	 返回字符串的字节数 一个汉字是2个字节   
	 */
	$.validateStrBytes =function(varStr) {
	    var count=0; 
	    for (var i = 0; i< varStr.length; i++) {     
		     if (varStr.charCodeAt(i) > 127 || varStr.charCodeAt(i) == 94) { 
		        count=count+2;   
		        } 
		     else { 
		        count=count+1;
		     }
	    } 
	    return count;
	} 

})(jQuery);
/**
 * 动态添加和删除行
 */
(function($){
	$.fn.dynTable = function(options) {
	options = $.extend(true, {
			tplid:  "",
			onBeforeDel:function(){
				return true;
			},
			onBeforeAdd:function(){
				return true;
			},
			onAfterDel:function(){
			},
			onAfterAdd:function(){
			}
	}, options);
	this.on('click',".addButton,.addRow",function(){
		var otr = $(this).parents("tr");
		if(options.onBeforeAdd()){
			otr.after($("#"+options.tplid).html());
			options.onAfterAdd();
		}
		
		
	});
	this.on('click',".delButton,.delRow",function(){
		var otr = $(this).parents("tr");
		if(options.onBeforeDel()){
			$(otr).remove();
			options.onAfterDel();
		}
	
		
	});
};
})(jQuery);
//合并display的表头
(function($){
	$.mergeTable=function (tableid,mergeobj){
	 	//mergeobj = [{col:'2,3,4,5',title:'合并'},{col:'6,7,8,9,10',title:'合并2'}];
		tableid=document.getElementById(tableid);;
		if(tableid && tableid.rows.length>0){
			var iColumns = tableid.rows[0].cells.length;
			var start=0;
			var th="";
			var delcell="";
			for(var i=0;i<mergeobj.length;i++){
				hbcol = mergeobj[i].col;
				if(hbcol!=""){
					hbtitle = mergeobj[i].title;
					stcol = hbcol.split(",")[0];
					colcount = hbcol.split(",").length;
					for(;start<stcol;start++){
						th=th+"<th rowspan=2>"+tableid.rows[0].cells[start].innerHTML+"</th>";
						delcell+=start+",";
					}
					th=th+"<th colspan="+colcount+">"+hbtitle+"</th>";
					start+=colcount;
		 		}
		 	}
			for(;start<iColumns;start++){
				th=th+"<th rowspan=2>"+tableid.rows[0].cells[start].innerHTML+"</th>";
				delcell+=start+",";
			}
			var tr = "<tr>"+th+"</tr>";
			//删除cell
			var delcell_ = delcell.split(",");
			for(var i=delcell_.length -1 ;i>=0;i--){
			  	if(delcell_[i]!=""){
			  		tableid.rows[0].removeChild(tableid.rows[0].cells[delcell_[i]]);//支持火狐、谷歌
			  		//tableid.rows[0].cells[delcell_[i]].removeNode();//removeNode 支持IE
			  	}
			}
			//new Insertion.Before(tableid.getElementsByTagName("tr")[0], tr); 
			$(tableid.getElementsByTagName("tr")[0]).before(tr);
		}
	}
})(jQuery);
/*
*用来实现displaytag中,鼠标经过一行,该行变色
*/  
var pre;
function highlightTableRows(tableId) {   
    var previousClass = null;   
    var table = document.getElementById(tableId);
    if(table==null||table==undefined||table==""){return;}
    var tbody = table.getElementsByTagName("tbody")[0];   
    if (tbody == null) {   
         rows = table.getElementsByTagName("tr");   
    } else {   
         rows = tbody.getElementsByTagName("tr");   
    }   
    for (i=0; i < rows.length; i++) { 
        rows[i].onmouseover = function() { 
        	if(this.style.backgroundColor!='#b8dcff' && this.style.backgroundColor!='#fdf5c7'){
        		this.style.backgroundColor="#fdf5c7";
        	}
        };   
        rows[i].onmouseout = function() { 
	         if(this.style.backgroundColor!='#b8dcff' && this.style.backgroundColor!='#fdf5c7'){
	        	 this.style.backgroundColor="";
	         }
         };   
        rows[i].onclick = function() { 
	        if(pre!=null){
	        	if(pre.style.backgroundColor=='#b8dcff' && pre.style.backgroundColor=='#fdf5c7'){
	        		pre.style.backgroundColor="";
	        	}
	        }
	        pre=this;  
	        this.style.backgroundColor="#b8dcff";  
	        }   
    }   
}

//得到两个日期相关天数
$.dateDiff = function(ksrq,jsrq){
	var aDate, oDate1, oDate2, iDays;
    aDate = jsrq.split("-");
    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为12-18-2002格式
    aDate = ksrq.split("-");
    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
    iDays = (oDate1 - oDate2) / 1000 / 60 / 60 /24;  //把相差的毫秒数转换为天数
    return iDays;
};


/**
 * 列标题排序
 * 使用方法：
 * 1、增加<thead id="tplSortHead"> ，
 * 2、在排序行增加 <th class="tpl-th-sort"><span class="tpl-sort" sortName="mc">标题名称</span></th>
 * 3、只能在searchForm的表单查询中使用
 * 4、如果原来页面上有orderBy，则必须加ID标记
 * 5、示例：
 * <table width="98%" border="0" cellpadding="0" cellspacing="0" class="list_table" align="center">
	<thead id="tplSortHead">
	<tr class="ls_table_th">
  		<th width="60">序号</th>
  		<th width="80">操作</th>
  		<th class="tpl-th-sort"><span class="tpl-sort" sortName="mc">名称</span></th>
  	</tr> 
  	</thead>
  	6、tplSortHead这个ID固定
 */
function tplHeadSort(){
	if(!$("#tplSortHead")){
		return;
	}
	
	var sort = $("#tplSortHead").attr("pagesort");
	var oldsortname = "";
	//判断有没有放orderBy
	var orderbyobj = $("#orderBy");
	var oldsort = "";
	var sortname = "";
	if(orderbyobj){
		sortname = $.trim($("#orderBy").val());
		if(sortname=="" || sortname.indexOf(" ")<0){
			//空着，不处理
		}else{
			var lastindex = sortname.lastIndexOf(" ");
			oldsort = $.trim(sortname.substring(lastindex).toLowerCase());
			var firstindex = sortname.indexOf(" ");
			oldsortname = sortname.substring(0,firstindex).toLowerCase();
		}
	}else{
		//在当前查询FORM表单中增加一个orderby隐藏域
		$("#searchForm" ).append("<input type='hidden' name='orderBy' id='orderBy'>");
	}
	
     $(".tpl-sort").each(function() {
		var mename = ($(this).attr("sortName")).toLowerCase();
		if(oldsortname == mename) {
			$(this).addClass("tpl-sort-" + oldsort);
		}
	}); 
     
    //绑定单击事件
    $("#tplSortHead").find(".tpl-th-sort").each(function() {
			var newsortname = $(this).find(".tpl-sort").attr("sortName");
			var newsort = (oldsort=="asc" ? "desc" : "asc" );
			$(this).click(function() {
				$("#orderBy").val(newsortname +" " + newsort);
				$("#searchForm").attr("from","searchButton");
				$("#searchForm").pageSearch();
			});
		});
}


function isBlank(s){
	if(s==null){
		return true;
	}
	return strTrim(s)=="";
}

function strTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}