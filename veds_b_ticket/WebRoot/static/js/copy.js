/**此js为复制文本方法
 * 需引下面js
 * "${ctx}/static/js/clipboard.min.js"
 * 在属性中加class="copytext" copytext="XXX"
 * 其中class属性固定   copytext中为要复制的内容
 * 例子：<a href="###" class="copytext" copytext={{xspnrno}} >{{pnrno}}</a>
 */
$(function(){
	if(!window.clipboardData){
	    var oHead = document.getElementsByTagName('HEAD').item(0); 

	    var oScript= document.createElement("script"); 

	    oScript.type = "text/javascript"; 

	    oScript.src=$ctx+"/static/js/clipboard.min.js"; 

	    oHead.appendChild(oScript); 
	}

	$(".copytext").live("mousemove",function(){
		var text=$(this).attr("copytext");
		layer.tips('<a href="###" onclick=copy("'+text+'")>复制 <font color=red>'+text+'</font></a>', this);
	});
	$(".copytext").live("mouseout",function(){
		setTimeout(function () {
			layer.closeAll('tips');
		   }, 2000);
	});
	
});
//复制
function copy(text){
	 $(".xubox_layer").remove();
     if(window.clipboardData){
       window.clipboardData.setData("text",text);
     }else{
    		var clipboard = new Clipboard('.xubox_tipsMsg', {
		        text: function() {
		            return text;
		        }
    		});
    		clipboard.on('success', function(e) {
    		});
    		clipboard.on('error', function(e) {
    		});
   }
}