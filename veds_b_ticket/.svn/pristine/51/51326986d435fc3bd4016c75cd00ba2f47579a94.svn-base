
function keycodeToFX(key){
	if(key==112){
		return "F1";
	}else if(key==113){
		return "F2";
	}else if(key==114){
		return "F3";
	}else if(key==115){
		return "F4";
	}else if(key==116){
		return "F5";
	}else if(key==117){
		return "F6";
	}else if(key==118){
		return "F7";
	}else if(key==119){
		return "F8";
	}else if(key==120){
		return "F9"
	}else if(key==121){
		return "F10";
	}else if(key==122){
		return "F11";
	}else if(key==123){
		return "F12";
	}else{
		return "";
	}
}

function key(o){
	var event = getEvent();
	var keyCode = event.which ? event.which : event.keyCode;
	//ctrl
	if(event.ctrlKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.shiftKey && !event.altKey){
		o.value="CTRL+"+String.fromCharCode(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;

		
	}else if(event.ctrlKey && (keyCode>=112 && keyCode<=123) && !event.shiftKey && !event.altKey){
		o.value="CTRL+"+keycodeToFX(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}
	//shift
	if(event.shiftKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.ctrlKey && !event.altKey){
		o.value="";
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;

		
	}else if(event.shiftKey && (keyCode>=112 && keyCode<=123) && !event.ctrlKey && !event.altKey){
		o.value="SHIFT+"+keycodeToFX(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}

	//ALT
	if(event.altKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.ctrlKey && !event.shiftKey){
		o.value="ALT+"+String.fromCharCode(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
		
	}else if(event.altKey && (keyCode>=112 && keyCode<=123) && !event.ctrlKey && !event.shiftKey){
		o.value="ALT+"+keycodeToFX(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}
	//ctrl+shift
	if(event.ctrlKey && event.shiftKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.altKey){
		o.value="CTRL+"+"SHIFT+"+String.fromCharCode(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}else if(event.ctrlKey && event.shiftKey && (keyCode>=112 && keyCode<=123) && !event.altKey){
		o.value="CTRL+"+"SHIFT+"+keycodeToFX(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}
	
	//ctrl+alt
	if(event.ctrlKey && event.altKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.shiftKey){
		o.value="CTRL+"+"ALT+"+String.fromCharCode(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}else if(event.ctrlKey && event.altKey && (keyCode>=112 && keyCode<=123) && !event.shiftKey){
		o.value="CTRL+"+"ALT+"+keycodeToFX(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}

	//shift+alt
	if(event.shiftKey && event.altKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.ctrlKey){
		o.value="SHIFT+"+"ALT+"+String.fromCharCode(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}else if(event.shiftKey && event.altKey && (keyCode>=112 && keyCode<=123) && !event.ctrlKey){
		o.value="SHIFT+"+"ALT+"+keycodeToFX(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}
	//F1-F12
	if(keyCode>=112 && keyCode<=123){
		o.value=keycodeToFX(keyCode);
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}else if(!event.shiftKey && !event.altKey && !event.ctrlKey){
		o.value="";
		//event.keyCode=0;
		//event.returnValue=false;
		disableContextEditor();
		return;
	}
	
}

function returnkey(){
	var event = getEvent();
	var keyCode = event.which ? event.which : event.keyCode;
	//ctrl
	var value="";
	
	if(event.ctrlKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.shiftKey && !event.altKey){
		value="CTRL+"+String.fromCharCode(keyCode);
		//keyCode=0;
		//event.returnValue=false;
		//disableContextEditor();
		return value;

		
	}else if(event.ctrlKey && (keyCode>=112 && keyCode<=123) && !event.shiftKey && !event.altKey){
		value="CTRL+"+keycodeToFX(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}
	if(event.ctrlKey && keyCode==38 && !event.shiftKey && !event.altKey){
		//event.returnValue=false;
		disableContextEditor();
		return "UP";
	}
	if(event.ctrlKey && keyCode==40 && !event.shiftKey && !event.altKey){
		//event.returnValue=false;
		disableContextEditor();
		return "DOWN";
	}
	//shift
	//if(event.shiftKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.ctrlKey && !event.altKey){
	//	value="SHIFT+"+String.fromCharCode(keyCode);
	//	keyCode=0;
	//	event.returnValue=false;
	//	return value;

	if(event.shiftKey && (keyCode>=112 && keyCode<=123) && !event.ctrlKey && !event.altKey){
		value="SHIFT+"+keycodeToFX(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}
	//ALT
	if(event.altKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.ctrlKey && !event.shiftKey){
		value="ALT+"+String.fromCharCode(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
		
	}else if(event.altKey && (keyCode>=112 && keyCode<=123) && !event.ctrlKey && !event.shiftKey){
		value="ALT+"+keycodeToFX(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}
	//ctrl+shift
	if(event.ctrlKey && event.shiftKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.altKey){
		value="CTRL+"+"SHIFT+"+String.fromCharCode(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}else if(event.ctrlKey && event.shiftKey && (keyCode>=112 && keyCode<=123) && !event.altKey){
		value="CTRL+"+"SHIFT+"+keycodeToFX(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}
	//ctrl+alt
	if(event.ctrlKey && event.altKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.shiftKey){
		value="CTRL+"+"ALT+"+String.fromCharCode(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}else if(event.ctrlKey && event.altKey && (keyCode>=112 && keyCode<=123) && !event.shiftKey){
		value="CTRL+"+"ALT+"+keycodeToFX(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}
	//shift+alt
	if(event.shiftKey && event.altKey && ((keyCode>=65 && keyCode<=90) || (keyCode>=48 && keyCode<=57)) && !event.ctrlKey){
		value="SHIFT+"+"ALT+"+String.fromCharCode(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}else if(event.shiftKey && event.altKey && (keyCode>=112 && keyCode<=123) && !event.ctrlKey){
		value="SHIFT+"+"ALT+"+keycodeToFX(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}
	//F1-F12
	if(keyCode>=112 && keyCode<=123){
		value=keycodeToFX(keyCode);
		//event.returnValue=false;
		disableContextEditor();
		return value;
	}
}

//获取事件 同时兼容ie和ff的写法 
function getEvent(){ 
     if (document.all && window.event) return window.event;
     func = getEvent.caller;
     while (func != null) {
         var arg0 = func.arguments[0];
         if (arg0) {
             if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
                 return arg0;
             }
         }
         func = func.caller;
     }
     return null;
}
