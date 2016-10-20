<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'example.jsp' starting page</title>
    <script src="/ckeditor/ckeditor.js"></script>
    
    <script type="text/javascript">
    
 var basicConfig = {
    height: 100,
    plugins: 'about,basicstyles,clipboard,list,indent,enterkey,entities,link,pastetext,toolbar,undo,wysiwygarea',
    forcePasteAsPlainText : true,
    removeButtons: 'Anchor,Underline,Strike,Subscript,Superscript',
    toolbarGroups: [
      { name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
      { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
      { name: 'forms' },
      { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
      { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
      { name: 'links' },
      { name: 'insert' },
      { name: 'styles' },
      { name: 'colors' },
      { name: 'tools' },
      { name: 'others' }
    ]
  };

  var fullConfig = {
    height: 300,
    plugins: 'about,a11yhelp,basicstyles,bidi,blockquote,clipboard,colorbutton,colordialog,contextmenu,div,elementspath,enterkey,entities,filebrowser,find,flash,floatingspace,font,format,forms,horizontalrule,htmlwriter,image,iframe,indent,justify,link,list,liststyle,magicline,maximize,newpage,pagebreak,pastefromword,pastetext,preview,print,removeformat,resize,save,scayt,selectall,showblocks,showborders,smiley,sourcearea,specialchar,stylescombo,tab,table,tabletools,templates,toolbar,undo,wsc,wysiwygarea',
    removeButtons: '',
    toolbarGroups: [
      { name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
      { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
      { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
      { name: 'forms' },
      '/',
      { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
      { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
      { name: 'links' },
      { name: 'insert' },
      '/',
      { name: 'styles' },
      { name: 'colors' },
      { name: 'tools' },
      { name: 'others' },
      { name: 'about' }
    ]
  };
    
     function aa() {
     
     CKEDITOR.replace('demo-toolbar-simple',
        { toolbar:'Basic', height:70 });
     
		  //  CKEDITOR.appendTo( 'demo-toolbar-simple', basicConfig );
		
		    CKEDITOR.appendTo( 'demo-toolbar-standard', { height: 100 } );
		
		    CKEDITOR.appendTo( 'demo-toolbar-full',
		      CKEDITOR.tools.extend( { height: 100 }, fullConfig ) );
  }
    
    </script>

  </head>
  
  <body onload="aa()">
   <section class="demo-content" id="demo-toolbar" style="display:none">
	<div class="demo-description">
		<h3>Toolbar configuration</h3>
		<p>The following is the standard editor configuration provided with the <strong>standard</strong> option in
			the <a href="/download">download page</a>.</p>
	</div>
	<div class="demo-main">
		<h4>Simple Toolbar</h4>
		<div id="demo-toolbar-simple"></div>
		<h4>Standard Toolbar</h4>
		<div id="demo-toolbar-standard"></div>
		<h4>Full Toolbar</h4>
		<div id="demo-toolbar-full"></div>
	</div>
</section>
  </body>
</html>
