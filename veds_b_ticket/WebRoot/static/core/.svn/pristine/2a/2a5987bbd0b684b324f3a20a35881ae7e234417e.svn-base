/*!
 * jquery插件,模糊搜索及选择 v1.0
 * 作者:章磊
 *
 * Date: 2015-2-10
 */
;(function($) {
	var styles ='<style type="text/css">';
	styles = styles+'#divAddressMenu {position:absolute;z-index:10000;overflow:hidden;background-color:#fff;border:solid #d3dbde 1px;font-size:12px;} ';
	styles = styles+'#divAddressMenu h4{border-bottom:dotted #CCCCCC 1px;color:#999999;font-size:12px; font-weight:100; padding:2px 2px 0 2px; margin:0;}'; 
	styles = styles+'#divAddressMenu div{padding:1px;}'; 
	styles = styles+'#divAddressMenu a {display:inline-block;clear:left;white-space: nowrap;width:98%;padding:1px 1px 2px 1px;cursor:default;text-decoration:none;color:#333;background-color:none;}';  
	styles = styles+'#divAddressMenu a {cursor:pointer}';   
	styles = styles+'#divAddressMenu a span{float:right;white-space:nowrap;display:block;}'; 
	styles = styles+'#divAddressMenu a:hover {border:solid #afd0dc 1px;border-bottom:solid #afd0dc 1px;background-color:#f2f6f8;}';  
	styles = styles+'.address_selected{border-top:solid #afd0dc 1px;border-bottom:solid #afd0dc 1px;background-color:#f2f6f8;}'; 
	styles = styles+'#divAddressMenu .address_selected a:hover{border:solid #f2f6f8 1px;border-bottom:solid #f2f6f8 1px;background-color:#f2f6f8;}';
	styles = styles+'</style>';
	$('head').append(styles);
	//给页面绑定点击事件
	$(document).bind("click",function(event) {
		var t = event.target||event.srcElement
		t = $(t);
		var divAddressMenu = t.parents("div[id='divAddressMenu']");
		var autoComplete = t.attr("AutoComplete");
		if(divAddressMenu.attr("id")!="divAddressMenu" && autoComplete!="true"){
			var inputObj = $("#"+$("#divAddressMenu").attr("inputObj"));
			var codeinput =  $("#"+$("#divAddressMenu").attr("codeinput"));
			if(inputObj.val()!=""){
				$("#divAddressMenu").find("div.address_selected").click();
				if(codeinput.val()==""){
					inputObj.val("");
				}
			}
			
			hiddenDiv();
		}
	});
	/**
	data:{value:最终赋值,mc:显示的名称,label:检索用,pyszm:拼音首字母,pyqp:拼音全拼}
	renderHTML:自定义显示格式

	**/
	$.fn.autocomplete = function(codeinput,options){
		options = $.extend(true, {
				data:  [{}],
		        renderHTML: null,
				title:"",
				filter:function(data){
					return true;
				},
				callback:null
		}, options);
		return new AutoComplete(this,codeinput,options);
	};

	function AutoComplete(inputObj,codeinput,options) {
		var KEY = {
			UP: 38,
			DOWN: 40,
			RETURN: 13,
			ESC: 27,
			BACKSPACE: 8
		};

		var lastKeyPressCode = "";
		var $input = $(inputObj);

		$input.bind("click",function() {
			$("#"+codeinput).val("");
			showDiv($input,codeinput,options);
		}).bind("keyup", function(event) {
			lastKeyPressCode = event.keyCode;
			switch(event.keyCode) {
				case KEY.UP:
					event.preventDefault();
					moveSelect(-1);
					break;
				case KEY.DOWN:
					event.preventDefault();
					moveSelect(1);
					break;
				case KEY.RETURN:
					event.preventDefault();
					setSelectValue($input,codeinput,options);
					break;
				case KEY.ESC:
					hiddenDiv();
					break;
				case KEY.BACKSPACE:
					showDiv($input,codeinput,options);
					break;
				default:
					showDiv($input,codeinput,options);
					break;
			}
		});

		
		$input.attr("AutoComplete","true");
	};

	/**
		显示DIV
	*/
	function showDiv(inputObj,codeinput,options) {
		var count = 1; 
		hiddenDiv();
		var inputVal = inputObj.val();
		var dataJson = options.data;
		var left = inputObj.offset().left+"px";
		var height = inputObj.height();
		var top = parseInt(height) + parseInt(inputObj.offset().top) + parseInt(6) +"px";
        
		var div ='<div id="divAddressMenu" inputObj="'+inputObj.attr("id")+'" codeinput="'+codeinput+'" style="left:'+left+';top:'+top+'">';
			div += '<h4>'+options.title+'</h4>';
			var showIndex = 1;
			var equalArr=[];
			var notEqualArr=[];
			for(var i in  dataJson) {
				var data = dataJson[i];
				var aTag = options.renderHTML(data);
				if(data.label == undefined) continue;//修改
				if(inputVal!="") {
					var dataArr=(data.label+","+data.pyszm).split(",");
					for(var i=0;i<dataArr.length;i++){
						 var index=dataArr[i].toUpperCase().indexOf(inputVal.toUpperCase());
						  if(index >-1){
							  if(index == 0){
								  if(options.filter(data)){
									  equalArr[equalArr.length]=data;
									  showIndex++;
									  count++;
								  }
								  break;
							   }
						  }
					}
							
						/*
						if(options.filter(data)){
							if(showIndex==1) {
								div +='<div class="address_selected" index="'+count+'" title="'+data.mc+'" value="'+data.value+'" mc="'+data.mc+'" id="city'+count+'">'+aTag+'</div>';
							}else {
								div +='<div  index="'+count+'" title="'+data.mc+'" value="'+data.value+'" mc="'+data.mc+'" id="city'+count+'">'+aTag+'</div>';
							}
							showIndex++;
							count++;
						}*/
					
				}else {
					 if(options.filter(data)){
						 notEqualArr[notEqualArr.length]=data;
						 count++;
					 }
					/*
					if(options.filter(data)){
						if(count==1) {
							div +='<div class="address_selected" index="'+count+'" title="'+data.mc+'" value="'+data.value+'" mc="'+data.mc+'" id="city'+count+'">'+aTag+'</div>';
						}else {
							div +='<div  index="'+count+'" title="'+data.mc+'" value="'+data.value+'" mc="'+data.mc+'" id="city'+count+'">'+aTag+'</div>';
						}
						count++;
					}*/
				}
				if(count>15) {
					break;
				}
			}
			if(showIndex==1 && inputVal!="") {
				div += '<nobr>没有找到您查的信息"'+inputVal+'"。</nobr>';
			}	
			
			//遍历全匹配的数据
			equalArr=equalArr.concat(notEqualArr);
			for(var i=0;i<equalArr.length;i++){
				 var data= equalArr[i];
				 var aTag = options.renderHTML(data);
				 if(options.filter(data)){
					 div +='<div '+(i == 0 ? "class=\"address_selected\"":"")+' index="'+i+'" title="'+data.mc+'" value="'+data.value+'" mc="'+data.mc+'" id="city'+i+'">'+aTag+'</div>';
				 }
			}
			
			div +='</div>';
		$("body").append(div);

		//给A标签绑定事件
		$("#divAddressMenu").find("div[value]").bind("click",function(){
			var selectid = $("#divAddressMenu").find("div.address_selected").attr("index");
			var nextid = $(this).attr("index");
			var nextNode = $("#city"+nextid);
			var selectNode = $("#city"+selectid);

			if(nextNode.get(0)) {//存在
				selectNode.removeClass("address_selected");
				nextNode.addClass("address_selected");
			}
			setSelectValue(inputObj,codeinput);
			if(options.callback){
				options.callback(nextNode.attr("value"));
			}
		});
	}

	/**
	首字母大写（没用）
	*/
	function replaceReg(str){
		var reg = /\b(\w)|\s(\w)/g;
		str = str.toLowerCase();
		return str.replace(reg,function(m){return m.toUpperCase()})
	}
	//移除DIV
	function hiddenDiv() {
		var cityDiv = $("#divAddressMenu");
		cityDiv.remove();;
	}
	//上下移动鼠标
	function moveSelect(step) {
		var selectid = $("#divAddressMenu").find("div.address_selected").attr("index");
		var nextid = parseInt(selectid)+parseInt(step);
		var nextNode = $("#city"+nextid);
		var selectNode = $("#city"+selectid);

		if(nextNode.get(0)) {//存在
			selectNode.removeClass("address_selected");
			nextNode.addClass("address_selected");
		}
	}
	//给文本框复制
	function setSelectValue(input,codeinput) {
		var selectid = $("#divAddressMenu").find("div.address_selected").attr("index");
		var selectNode = $("#city"+selectid);
		if(selectNode.attr("mc")!="" && selectNode.attr("mc")!=null){
			$(input).val(selectNode.attr("mc"));
			$("#"+codeinput).val(selectNode.attr("value"));
			hiddenDiv();
			$(input).focus();
			$(input).blur();
		}
		
	}
	
})(jQuery);


(function($){
	/**
	 * $("#cityname").autocompleteGjCity("citycode");
	 */
	$.fn.autocompleteGjCity = function(inputcode) {
		this.autocomplete(inputcode,{title:'可输城市拼音/汉字/三字码',data:_gjcity,renderHTML:function(data){
			return '<a ><span>'+data.mc+'('+data.value+')</span>'+data.pyqp+'</a>';
		}});
	}
	$.fn.autocompleteGnCity = function(inputcode) {
		this.autocomplete(inputcode,{title:'可输城市拼音/汉字/三字码',data:_gncity,renderHTML:function(data){
			return '<a ><span>'+data.mc+'('+data.value+')</span>'+data.pyszm+'</a>';
		}});
	}
	$.fn.autocompleteHkgs = function(inputcode,callback) {
		this.autocomplete(inputcode,{title:'可输航空公司拼音/汉字/二字码',data:_hkgs,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteGnHkgs = function(inputcode,callback) {
		this.autocomplete(inputcode,{title:'可输航空公司拼音/汉字/二字码',data:_gnhkgs,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		},callback:callback});
	}
	$.fn.autocompleteSf = function(inputcode) {
		this.autocomplete(inputcode,{title:'可输省份拼音/汉字/二字码',data:_sf,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteGncs = function(inputcode,filterFunction) {
		this.autocomplete(inputcode,{title:'可输省份拼音/汉字/三字码',data:_gncs,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteShb = function(inputcode,filterFunction) {
		this.autocomplete(inputcode,{title:'可输商户编号/名称/简称',data:_shb,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteUser = function(inputcode,filterFunction) {
		this.autocomplete(inputcode,{title:'可输用户编号/姓名',data:_user,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteFwz = function(inputcode,filterFunction) {
		this.autocomplete(inputcode,{title:'可输组名称/简拼',data:_fwz,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteShyhb = function(inputcode,filterFunction) {
		this.autocomplete(inputcode,{title:'可输商户用户编号/姓名',data:_shyhb,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteShMkb = function(inputcode,filterFunction) {
		this.autocomplete(inputcode,{title:'可输商户模块编号/名称/拼音/url',data:_shmkb,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteShMkgn = function(inputcode,filterFunction) {
		this.autocomplete(inputcode,{title:'可输商户模块功能编号/名称/拼音/url',data:_shmkgn,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
	$.fn.autocompleteAll = function(inputcode,title,json,filterFunction) {
		this.autocomplete(inputcode,{title:title,data:json,filter:filterFunction,renderHTML:function(data){
			return '<a ><span>'+data.value+'</span>'+data.mc+'</a>';
		}});
	}
})(jQuery);