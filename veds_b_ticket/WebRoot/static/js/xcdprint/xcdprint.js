// 打印机开始实际打印，返回值：正确打印时返回真，打印出错时返回假。
// action P:直接打印 S:打印设置 V:打印预览，一般不用
// 返回值  该数字大于0时表示被实际打印的次数
//
function xcdPrint_lodop(json,action,gngjlx,gp_no){
    CreatePrintPage_xcd(json,gngjlx,gp_no);
    var rtn = Lodop_Print(action);
	return  rtn
}
//打印设计，开发人员用
function xcdPrint_D(json,gngjlx){
	CreatePrintPage_xcd(json,gngjlx,"");
	LODOP.PRINT_DESIGN(); 
}
var formPageCnt=0;
function CreatePrintPage_xcd(json,gngjlx,test_gp_no) {
	var lineH=28;
	var lineH2=23;
	var total = json.total;
	var rows=json.rows;
	var gp_no=rows[0].gp_no;
	if(test_gp_no!=""){
		gp_no=test_gp_no;
	}
	if(formPageCnt++==0||$("printTypeVal1").checked){
		CreateOneFormPage_xcd(gngjlx,gp_no);
	}
	initPageFontSize();
	for(var i=0;i<total;i++){
	    var row = rows[i];
		LODOP.ADD_PRINT_TEXT(25,598,129,lineH,row.xcdh);
		LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",1);	
		var lkxm=row.lkxm;
		if(!isBlank(lkxm)&&lkxm.length>16){
			//lkxm=lkxm.substring(0,16)
		}
		if("0"==gngjlx){
			if(gp_no!=""){
				LODOP.ADD_PRINT_TEXT(42,211,80,lineH,'国际(I)');
			}else{
				LODOP.ADD_PRINT_TEXT(42,140,80,lineH,'国际(I)');
			}
		}
		
		if(gp_no!=""){
			LODOP.ADD_PRINT_TEXT(42,49,160,lineH,gp_no);
		}
		//49,149
		LODOP.ADD_PRINT_TEXT(72,49,400,lineH,lkxm);
		//LODOP.SET_PRINT_STYLEA(2,"Alignment",2);
		LODOP.ADD_PRINT_TEXT(72,202,234,lineH,row.zjhm);
		LODOP.ADD_PRINT_TEXT(72,439,329,lineH,row.qz);
		LODOP.ADD_PRINT_TEXT(101,49,149,lineH,row.pnr_no);
		//LODOP.SET_PRINT_STYLEA(5,"Alignment",2);
		
		var length=row.hdlist.length;//固定为4
		//航程信息
		for(var h=0;h<4;h++){
		    var tophc = 130+h*33
		    var cfhzl=isBlank(row.hdlist[h].cfhzl) ? '  ' : row.hdlist[h].cfhzl;
			LODOP.ADD_PRINT_TEXT(tophc,22,128,lineH,cfhzl+row.hdlist[h].jcmc);
			LODOP.ADD_PRINT_TEXT(tophc,150,80,lineH,row.hdlist[h].ddhzl+row.hdlist[h].hkgsjc);
			LODOP.ADD_PRINT_TEXT(tophc,230,83,lineH,row.hdlist[h].hbh);
			LODOP.ADD_PRINT_TEXT(tophc,313,20,lineH,row.hdlist[h].zwdj);
			LODOP.ADD_PRINT_TEXT(tophc,333,131,lineH,row.hdlist[h].cfrq);
			LODOP.ADD_PRINT_TEXT(tophc,463,68,lineH,row.hdlist[h].cfsj);
			LODOP.ADD_PRINT_TEXT(tophc,529,68,lineH,row.hdlist[h].kpjb);
			LODOP.ADD_PRINT_TEXT(tophc,597,71,lineH,row.hdlist[h].yxrq);
			LODOP.ADD_PRINT_TEXT(tophc,666,71,lineH,row.hdlist[h].jzrq);
   			LODOP.ADD_PRINT_TEXT(tophc,736,48,lineH,row.hdlist[h].mfxl);
		}
		var jcmc5="";
		if(length==5){
			jcmc5="  "+row.hdlist[4].jcmc;
		}
		LODOP.ADD_PRINT_TEXT(130+4*33,22,128,lineH,jcmc5);
		
		//票价信息
		var pj_fh=isBlank(row.pj_fh) ? "CNY" : row.pj_fh;
		var jsf_fh=isBlank(row.jsf_fh) ? "CN" : row.jsf_fh;
		var tax_fh=isBlank(row.tax_fh) ? "YQ" : row.tax_fh;
		var qt_fh=isBlank(row.qt_fh) ? "" : row.qt_fh;
		var hj_fh=isBlank(row.hj_fh) ? "CNY" : row.hj_fh;
		
		var cny = (row.pj_zdj > 1000)? (pj_fh+row.pj_zdj)  : (pj_fh+row.pj_zdj);
		LODOP.ADD_PRINT_TEXT(256,162,130,lineH,cny);
		var cn = (row.pj_jsf > 1000)? (jsf_fh+row.pj_jsf)  : (jsf_fh+row.pj_jsf);
		LODOP.ADD_PRINT_TEXT(256,290,105,lineH,cn);
		var yq = (row.pj_tax > 1000)? (tax_fh+row.pj_tax)  : (tax_fh+row.pj_tax);
		LODOP.ADD_PRINT_TEXT(256,393,110,lineH,yq);
		LODOP.ADD_PRINT_TEXT(256,506,95,lineH,parseInt(row.pj_qt)>0 ? (qt_fh+row.pj_qt) : "");
		var cnyhj = (row.pj_hj > 10000)? (hj_fh+row.pj_hj)  : (hj_fh+row.pj_hj);
		LODOP.ADD_PRINT_TEXT(256,605,167,lineH,cnyhj);
		
		//票号等
		LODOP.ADD_PRINT_TEXT(283,90,175,lineH,row.tkno);
		LODOP.ADD_PRINT_TEXT(284,268,100,lineH,row.yzm);
		LODOP.ADD_PRINT_TEXT(284,390,271,lineH,row.tsxx);
		LODOP.ADD_PRINT_TEXT(284,698,75,lineH,row.pj_bx);
		
		//代理人信息
		LODOP.ADD_PRINT_TEXT(307,89,137,lineH,row.office);
		LODOP.ADD_PRINT_TEXT(335,89,137,lineH,row.dwdh);
		LODOP.ADD_PRINT_TEXT(315,278,339,lineH,row.tkdw);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
		LODOP.ADD_PRINT_TEXT(315,635,140,lineH,row.tkrq);
		
		LODOP.SET_PRINT_STYLEA('ALL',"LetterSpacing",0);
		LODOP.NewPage();
	}
  };
function CreateOneFormPage_xcd(gngjlx,gpno){
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
	if("0"==gngjlx){ 
		if(gpno!=""){
			LODOP.PRINT_INITA(0,0,"210mm","102mm","ASMS国际GP行程单打印");
		}else{
    		LODOP.PRINT_INITA(0,0,"210mm","102mm","ASMS国际行程单打印");
    	}
    }else{
    	if(gpno!=""){
    		LODOP.PRINT_INITA(0,0,"210mm","102mm","ASMS国内GP行程单打印");
    	}else{
    		LODOP.PRINT_INITA(0,0,"210mm","102mm","ASMS行程单打印");
    	}
    }
};
function initPageFontSize(){
	var temp = getCookie("asms_xcd_pagefontsize");
    var pagesizefixed = "0";
    var fontsize = "16";
    var fontname="TEC";
    if(temp){
      pagesizefixed = temp.split(",")[0];
      fontsize = temp.split(",")[1];
      fontname = temp.split(",")[2];
    }
    if(fontname=="TEC"){
    	fontname="TEC";
    }else if(fontname=="2"){
    	fontname="方正姚体简体";
    }else if(fontname=="3"){
    	fontname="长城小姚体";
    }else if(fontname=="4"){
    	fontname="宋体";
    }
    
    if(pagesizefixed=="1"){
		LODOP.SET_PRINT_PAGESIZE(1,"210mm","102mm","CreateCustomPage");
	}
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='/eterm/xcdprintnew/js/0000669044-4.jpg'>");
	LODOP.SET_SHOW_MODE("BKIMG_LEFT",0);
	LODOP.SET_SHOW_MODE("BKIMG_TOP",0);
	LODOP.SET_SHOW_MODE("BKIMG_WIDTH","210mm");
	LODOP.SET_SHOW_MODE("BKIMG_HEIGHT","102mm");
	//LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
	LODOP.SET_PRINT_STYLE("FontName",fontname);
	LODOP.SET_PRINT_STYLE("FontSize",fontsize);
}
function setPageFontSize(){
	var str="";
	if($("asms_xcd_pagesize_fixed_box").checked){
		str="1";
	}else{
		str="0";
	}
	str=str+","+$("asms_xcd_pagesize_fontsize_select").value+","+$("asms_xcd_pagesize_font_select").value;
	setCookie("asms_xcd_pagefontsize",str,360);
}
function onloadsetpagefontsize(){
	var temp = getCookie("asms_xcd_pagefontsize");
    var pagesizefixed = "0";
    var fontsize = "16";
    var fontname="TEC";
    if(temp){
      pagesizefixed = temp.split(",")[0];
      fontsize = temp.split(",")[1];
      fontname = temp.split(",")[2];
    }
    if(isBlank(fontname)){
    	fontname="TEC";
    }
    $("asms_xcd_pagesize_fontsize_select").value = fontsize;
    $("asms_xcd_pagesize_font_select").value = fontname;
    if(pagesizefixed=="1"){
    	$("asms_xcd_pagesize_fixed_box").checked=true;
    }else{
    	$("asms_xcd_pagesize_fixed_box").checked = false;
    }
}
