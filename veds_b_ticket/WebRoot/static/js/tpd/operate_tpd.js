$(function(){ 
	var menuItems = {};
	var menuCount = 0;
	menuItems['tpd_qx'] = {
			name: "取消",
			icon: "delete",
			disabled:function(key,opt){
				var xszt = $(this).attr("menuxszt"); 
				var cgzt = $(this).attr("menucgzt"); 
				var czlx = $(this).attr("menuczlx");
				return !(xszt == '0' && cgzt == '0')
			}
		};
	menuCount ++;
	
	menuItems['tpd_sh'] = {
		name: "审核",
		icon: "edit",
		disabled:function(key,opt){
			var xszt = $(this).attr("menuxszt"); 
			var czlx = $(this).attr("menuczlx");
			return !(xszt == '0');
		}
	};
	menuCount ++;
	
	
	menuItems['tpd_cgtj'] = {
			name: "采购提交",
			icon: "edit",
			disabled:function(key,opt){
				var cgzt = $(this).attr("menucgzt"); 
				var xszt = $(this).attr("menuxszt"); 
				var czlx = $(this).attr("menuczlx");
				var cgly = $(this).attr("menucgly");
				return !(cgzt =='0' && xszt != '0' && (czlx == '4' || czlx == '1'));
				
			}
		};
	menuCount ++;
	
	menuItems['tpd_xswc'] = {
			name: "销售完",
			icon: "paste",
			disabled:function(key,opt){
				var xszt = $(this).attr("menuxszt"); 
				var czlx = $(this).attr("menuczlx");
				return !(xszt == '1' && xszt != '9' && (czlx == '3'  || czlx == '1'));
			}
		};
	menuCount ++;
	
	menuItems['tpd_cgwc'] = {
			name: "采购完",
			icon: "paste",
			disabled:function(key,opt){
				var cgzt = $(this).attr("menucgzt"); 
				var czlx = $(this).attr("menuczlx");
				return !(cgzt == '2' && (czlx == '4'  || czlx == '1') );
			}
		};
	menuCount ++;
	
	menuItems['tpd_qz'] = {
			name: "签注",
			icon: "edit",
			disabled:function(key,opt){
				var cgzt = $(this).attr("menucgzt"); 
				var czlx = $(this).attr("menuczlx");
				return false;
			}
		};
	menuCount ++;
	
	
	
	
	if(menuCount != 0){
		$("table[name='jptpd_table']").contextMenu({
			selector:'.opratorBtn_tpd',
			trigger: 'hover',
			autoHide: true, 
			callback: function(key, options) {
			 	var id = $(this).attr("menuid");
			 	//销售退票状态   0已申请,1已审核,2已办理,9已取消
			 	var xszt = $(this).attr("menuxszt");
			 	//采购退票状态   0待提交,1提交中,2已提交,3已办理,4已拒单,9已取消,5部分办理
			 	var cgzt = $(this).attr("menuxszt");
			 	
			 	// 1全部  2待审核  3待销售办理 4待采购办理  5销售完/采购完   6取消座位
			 	var czlx = $(this).attr("menuczlx");
			 	
			 	var version = $(this).attr("menuversion");
			 	var params = {};
			 	params['jp_tfd.id'] = id;
			 	params['jp_tfd.version'] = version;
			 	if(key == 'tpd_qx'){//取消
			 		cancelTpd(id);
			 	}else if(key == 'tpd_sh'){// 审核
			 		xsShTpd(id);
			 	}else if(key == 'tpd_cgtj'){// 审核
			 		cgTjTpd(id);
			 	}else if(key == 'tpd_xswc'){//销售完成
			 		xsWcTpd(id);
			 	}else if(key == 'tpd_cgwc'){//采购完成
			 		cgWcTpd(id);
			 	}else if(key == 'tpd_qz'){//签注
			 		getQzxx(id);
			 	}
			 },
			 items: menuItems
		});
	}
});
