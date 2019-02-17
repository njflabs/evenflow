<script language="php">
include "_p/inc-header.php";
</script>
<!DOCTYPE html> 
<html>
<head>
<meta content="en-us" http-equiv="Content-Language">
<script language="php">
$pgTitle = "QuickOrder";
$pgDesc = "QuickOrder";
$pgKeywords = "QuickOrder"; 
include "_p/inc-index_meta.php";
$fArr = array("index_header", "index_nav", "index_main", "index_footer");
 
</script> 
<meta name="viewport" content="initial-scale=1, user-scalable=0, maximum-scale=1">
<script  type="text/javascript">
var isPhP = "no";
var isJApp = "no";
var isJavaFx = "no";
var dw = function(theElem, tStr) { theElem.innerHTML = tStr; };
var nuDW = function(theElem) { 
if(theElem.getAttribute("data-ison") != null) {
try {
tDison = theElem.getAttribute("data-ison");
theElem.innerHTML = eval(tDison);
} catch(e) {
alert(e);
}
}
};

var getFArr = function() {
return new Array("index_header", "index_nav", "index_main", "index_footer");
};
</script>
 
<script language="php">
echo '<scr' . 'ipt>isPhP=5;</scr' . 'ipt>';
</script>
<script language="javascript" type="text/javascript" src="js/lz-string.js"></script>
<script src="js/x_admin.js"  language="javascript" type="text/javascript"></script>
<script src="js/x_all.js"  language="javascript" type="text/javascript"></script>
<script src="js/x_booter.js"  language="javascript" type="text/javascript"></script>
<script language="php">
include "_p/js-header.php";
</script>


 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- <link href="css/materialize.css" rel="stylesheet"> -->

<link href="css/main.css" rel="stylesheet">






</head>
<body>
<header>
<div id="includedHeader">
</div>
<div id="includedNav">
</div>
</header>
<div style="min-height:60px"></div>

<div id="dvSearchBoxSlim" style="top: 60px; left:25px; position:fixed; background-color: #FFFFFF"></div>
<div class="onlySmallScreen icnbtn brdrClrDlg crsrPointer bkgdClrWhite" style="position: fixed" onclick="javascript:JSSHOP.ui.toggleNuModule('dvLMenuTgl','tdLMenu');">
<div id="dvLMenuTgl"><i class="small-material-icons" style="font-size:22px">expand_more</i>
</div></div>

<input type="hidden" name="fldChallArray" id="fldChallArray" value="noQvalue">
<div id="includedForms">
</div>
<script>setTimeout("doBootLoad()", 200)</script>
 
 <div id="mmn" class="nanimenu animenu">
 	<div  id="mnuT" style="padding-left:10px;" >
  
	 </div></div> 

	 
	 <div id="dvMCatTree" style="z-index:99999;min-height:20px;float:right;">
</div>
<div>

<table id="tblMNContent" style="min-height:80px;" cellpadding="0" cellspacing="0" width="100%"><tr>
	<td valign="top" class="clsTDLmenu">
	
<div id="tdLMenu" class="onlyWideScreen clsLeftMenu">
	
 

	    <table style="width: 100%">
			<tr>
				<td class="collectionhdr"><div class="onlySmallScreen icnbtn brdrClrDlg crsrPointer" style="float: right;" onclick="javascript:JSSHOP.ui.toggleNuModule('dvLMenuTgl','tdLMenu');">
<div><i class="small-material-icons" style="font-size:22px">expand_less</i>
</div></div>
</td>
			</tr>
			<tr>
				<td>
</td>
			</tr>
			 
		</table>
	
	
	 </div>


</td><td valign="top" width="100%"><table style="min-height:80px;margin:0 auto" align="center" width="100%" cellpadding="0" cellspacing="0"><tr>
		<td valign="top">

<div><div id="includedContent">
</div></div></td></tr></table></td></tr></table></div>

<div id="includedFooter" class="txtBold links_footer">
</div>
<div id="dvDummy"></div>
</body> 
</html>
