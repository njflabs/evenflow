var currMSetArr = [];
currPgTitle = stxt[61];
document.title = currPgTitle; // set the page title 
 
var doMbtn = function(tmpTxt, tmpCallback) {
tmpRd = "<div class=\"crsrPointer\" style=\"margin:5px;\"><div onclick=\"javascript:JSSHOP.ui.setCBBClickClr(this,'txtBig slmtable brdrClrNrml','txtBig slmtable brdrClrHdr', function(){" + tmpCallback + "});\" class=\"slmtable txtBig brdrClrHdr\"  style=\"padding:8px;\">";
tmpRd += tmpTxt + "</div></div>";
return tmpRd;
};
var doNuModAdmnCats = function() {
// alert("doModAdmnCats: " + theResp);
hasr = "n";
fullstr = "";
var arrToFill = currMenuArr;
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
tmpTblStgs = document.getElementById("tblSettings");
while(iint < len) {
ts = arrToFill[iint];
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);
tmpCella.innerHTML = doMbtn(ts.cat_title,"document.location.href='index.html?pid=aa-edit-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "'"); 	
iint++;
}
};
var doModAdmnCats = function(a,theResp,c) {
// alert("doModAdmnCats: " + theResp);
hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
tmpTblStgs = document.getElementById("tblSettings");
while(iint < len) {
ts = arrToFill[iint];
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);
tmpCella.innerHTML = doMbtn(ts.cat_title,"document.location.href='index.html?pid=aa-edit-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "'"); 	
iint++;
}
};

var doMenuShopsList = function(a,theResp,c) {
hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
tmpTblStgs = document.getElementById("tblSettings");
while(iint < len) {
ts = arrToFill[iint];
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);
tmpCella.innerHTML = doMbtn(ts.c_title,"document.location.href='index.html?pid=aa-edit-menu&cid=" + ts._id + "'"); 	
iint++;
}
};



var getMenuShopsList = function() {
tmpDOs = null;
tmpDOs = {};
tmpDOs["l"] = "2";
tmpDOs["ws"] = "where c_uid=?";
tmpDOs["wa"] = [JSSHOP.cookies.getCookie("quid")];
oi = getNuDBFnvp("qco",5,null,tmpDOs);
doDynQArrComm(currMSetArr, oi["rq"], "doMenuShopsList", "doMenuShopsList");
};



var loadAdminSettings = function() {
tmpTblStgs = document.getElementById("tblSettings");
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);
tmpCella.innerHTML = doMbtn(stxt[7],"document.location.href='index.html?pid=aa-edit-shops'"); 		



if(currRQstr == "noQvalue") { // no app query string passed
} else {
tmpRowB = tmpTblStgs.insertRow(-1);
tmpCellB = tmpRowB.insertCell(-1);
tmpCellB.innerHTML = doMbtn(stxt[28],"getAppDBReq()"); 		
}
	

if(cid == 0) {
} else {
if(pid == 0) {
} else {
if(pid.indexOf("aa-show") != -1) {
swws = document.location.href;
swes = swws.replace("aa-show", "aa-edit");
tmpSHRef = "document.location.href='" + swes + "'";

tmpRowBBB = tmpTblStgs.insertRow(-1);
tmpCellBBB = tmpRowBBB.insertCell(-1);
tmpCellBBB.innerHTML = doMbtn("Edetre",tmpSHRef); 	
}
if(pid.indexOf("aa-edit") != -1) {
swws = document.location.href;
swes = swws.replace("aa-edit", "aa-show");
tmpSHRef = "document.location.href='" + swes + "'";

tmpRowBBB = tmpTblStgs.insertRow(-1);
tmpCellBBB = tmpRowBBB.insertCell(-1);
tmpCellBBB.innerHTML = doMbtn("View",tmpSHRef); 	
}

}
tmpRowCC = tmpTblStgs.insertRow(-1);
tmpCellCC = tmpRowCC.insertCell(-1);
tmpSHRef = "document.location.href='" + JSSHOP.shared.getFrmFieldVal("qco","c_web","./") + "/index.html?pid=aa-edit-synch&tt=" + JSSHOP.getUnixTimeStamp() +  "&cid=" + cid + "'";
tmpCellCC.innerHTML = doMbtn("Synch",tmpSHRef); 


/* !! to delete. grabbing from currMenuArr
tmpDOs = null;
tmpDOs = {};
// tmpDOs["l"] = "2,1";
tmpDOs["ws"] = "where cat_coid=?";
tmpDOs["wa"] = [cid];
oi = getNuDBFnvp("qcat",5,null,tmpDOs);
doDynQArrComm(currMSetArr, oi["rq"], "doModAdmnCats", "doModAdmnCats");
*/
doNuModAdmnCats();
}
};


var loadUserSettings = function() {
tmpTblStgs = document.createElement("div");
tmpTblAStgs = document.getElementById("tblDiv");
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);   
tmpCella.innerHTML = doMbtn(stxt[7],"document.location.href='index.html?pid=aa-edit-shops'"); 		
if(currRQstr == "noQvalue") { // no app query string passed
} else {
tmpRowB = tmpTblStgs.insertRow(-1);
tmpCellB = tmpRowB.insertCell(-1);
tmpCellB.innerHTML = doMbtn(stxt[28],"getAppDBReq()"); 		
}

 



tmpRow0 = tmpTblStgs.insertRow(-1);
tmpCell0 = tmpRow0.insertCell(-1);
tmpCell0.innerHTML = doMbtn("Your Info","JSSHOP.ui.closeLbox();document.location.href='index.html?pid=aa-edit-user';"); 	

if((quid == 0) || (quid == "noQvalue")){
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);
tmpCella.innerHTML = doMbtn(stxt[29],"JSSHOP.ui.closeLbox();document.location.href='index.html?pid=login';"); 	
} else {
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);
tmpCella.innerHTML = doMbtn(stxt[39],"JSSHOP.cookies.deleteCookie('quid','','');document.location.href='index.html?lgo=y'"); 	
tmpRowB = tmpTblStgs.insertRow(-1);
tmpCellB = tmpRowB.insertCell(-1);
if(arrUprefs["prfsSHOPuser"]) {
if(arrUprefs["prfsSHOPuser"][0].scv) {
if(arrUprefs["prfsSHOPuser"][0].scv == "r") {
tmpCellB.innerHTML = doMbtn("grid","JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','g')"); 
}  else {
tmpCellB.innerHTML = doMbtn("row","JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','r')"); 
}
}  
}  

tmpRowD = tmpTblStgs.insertRow(-1);
tmpCelld = tmpRowD.insertCell(-1);
tmpCelld.innerHTML = doMbtn('Clear Prefs',"JSSHOP.cookies.deleteCookie('prfsSHOPuser','','');setTimeout(document.location.href='index.html?lgo=r',800)"); 	
tmpTblAStgs.appendChild(tmpTblStgs);
getMenuShopsList();	
}


};

 
var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function () {
currQcommsArr = null;
currQcommsArr = [];
if(arrUprefs["prfsSHOPuser"]) {
// alert(JSON.stringify(arrUprefs["prfsSHOPuser"][0]));
if(arrUprefs["prfsSHOPuser"][0].scv) {
JSSHOP.shared.setCurrSelectOpt(document.getElementById("prfSUscv"), arrUprefs["prfsSHOPuser"][0].scv);
if(arrUprefs["prfsSHOPuser"][0].scv == "r") {
}
}

if(arrUprefs["prfsSHOPuser"][0].sAT) {
if(arrUprefs["prfsSHOPuser"][0].sAT == "y") {
document.getElementById("prfSUsAT").checked=true;
}
}
if(arrUprefs["prfsSHOPuser"][0].nIPP) {
document.getElementById("prfSUnIPP").value=arrUprefs["prfsSHOPuser"][0].nIPP;
}

if(arrUprefs["prfsSHOPuser"][0].sAL) {
if(arrUprefs["prfsSHOPuser"][0].sAL == "y") {
document.getElementById("prfSUsAL").checked=true;
}
}




}

JSSHOP.shared.setCurrSelectOpt(document.getElementById("selPrefUlang"), usrlang);



/*
if(cid == 0) {
} else {
if(arrAllForms.qco.v[0].c_uid == quid) {
loadAdminSettings();
}
}
 
loadUserSettings();
*/
// alert(JSON.stringify(currMSetArr));
doQComm("batch" + JSON.stringify(currMSetArr), null, "fnish");

}
var aLoadSettings = function() {

 
};

// aLoadSettings();