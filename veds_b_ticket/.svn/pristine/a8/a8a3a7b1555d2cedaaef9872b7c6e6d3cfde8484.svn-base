/**************兼容IE 火狐 谷歌浏览器，可编辑DIV控制光标位置公共JS函数******************/

function getCaretPos(element) {
	var CaretPos = 0; // IE Support
	if (document.selection) {
		element.focus(); //将焦点至于element，并且光标位置不会发生改变
		var Sel = document.selection.createRange(); //document.selection可以获得当前所选区域，而createRange则返回对应的TextRange对象，该对象不包含任何文本
		Sel.moveStart("character", -element.innerText.length); //将光标往后退文本的长度
		var text = Sel.text;//Range对象所包括的文本，由于是整个文档的textRange所以,前面几个字符有可能不是element中的，所以要做最长匹配                
		for (var i = 0; i < element[element.tagName == "DIV" ? "innerText" : "value"].length; i++) {
			if (element[element.tagName == "DIV" ? "innerText" : "value"].substring(0, i + 1) == text.substring(text.length - i - 1, text.length)) {
				CaretPos = i + 1;
			}
		}
		var lines = element[element.tagName == "DIV" ? "innerText" : "value"].substring(0, CaretPos).split("/n").length - 1;
		CaretPos -= lines; //range对象中换行一个字符，当时在String中换行是/n两个字符                  
	} else {
		if (element.selectionStart || element.selectionStart == "0") { // Firefox support only for textarea and input
			CaretPos = element.selectionStart;
		} else {
			if (window.getSelection) {// Firefox support only for div
				var sel = window.getSelection();
				var rng = sel.getRangeAt(0).cloneRange();
				rng.setStart(element, 0);
				CaretPos = rng.toString().length;
			}
		}
	}
	return CaretPos;
}

//DomNode -- element;location -- Number
function setCaretPos(element, location) {
	if (element.setSelectionRange) {
		element.focus();
		element.setSelectionRange(location, location);
	} else {
		if (document.body.createTextRange) {
			var range = document.body.createTextRange();
			range.moveToElementText(element);
			range.collapse(true);
			range.move("character", location);
			range.select();
		} else {
			if (window.getSelection) {
				var nodes = [];
				var getTextNode = function (node) {
					for (var i = 0; i < node.childNodes.length; i++) {
						if (node.childNodes[i].nodeType == 3) {
							nodes.push(node.childNodes[i]);
						} else {
							getTextNode(node.childNodes[i]);
						}
					}
				};
				getTextNode(element);
				var len = 0;
				for (var i = 0; i < nodes.length; i++) {
					len += nodes[i].textContent.length;
					if (len > location) {
						len -= nodes[i].textContent.length;
						break;
					}
				}
				var sel = window.getSelection();
				var nodeObj = nodes.length > i ? nodes[i] : nodes[i-1];
				if(nodeObj){
					if(parseInt(location) == 1){//第一次进入黑屏初始化光标位置
						sel.collapse(element, 1);//(parseInt(location) - parseInt(len))
					}else{
						try{
							sel.collapse(nodeObj, 1);
						}catch(e){
							sel.collapse(nodeObj, 0);
						}
					}
				}
				element.focus();
			}
		}
	}
}

