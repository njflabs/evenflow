var tmpSTrSorE = "show";



if(currAdmnMode == "y") {
tmpSTrSorE = "edit";
}

var doSubMenuLoad = function() {
try {
arrToFill = null;
arrToFill =  [];
// arrToFill = JSON.parse(theResp);
 
arrToFill = currMenuArr;


var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
while(iint < len) {
ts = arrToFill[iint];
// currMenuArr.push(ts);

if(ts.cat_pid == "0") {
} else {
crMI = "mnuI" + ts.cat_pid;

    var ul = document.getElementById(crMI);

     var li = document.createElement("li");
	li.className="omenuartigo";
    // var children = ul.children.length + 1
    // li.setAttribute("id", "element"+children)

var a = document.createElement('a');
var linkText = document.createTextNode(ts.cat_title);
a.appendChild(linkText);
a.title = ts.cat_title;
a.href = "index.php?pid=aa-" + tmpSTrSorE + "-category&ppid="+ts._id+"&cid="+ts.cat_coid+"&catid="+ts._id;
 


    li.appendChild(a);
    ul.appendChild(li);
   //tmpUstr = "<li><a href=\index.php?pid=aa-add-qbit&ppid="+ts._id+"&cid="+ts.cat_coid+"\">"+ts.cat_title+"</a></li>";
   // ul.append(tmpUstr);

}
iint++;
}

} catch(e) {
 
JSSHOP.logJSerror(e, arguments, "doSubMenuLoad");
} 
if(catid == 0) {
hstring = "Home";
mstring = "<a href=\"index.php?cid=" + +cid + "\">" + hstring + "</a>";
fmcstr = "<div style=\"margin-left: 15px\" class=\"crsrPointer txtSmall txtClrBlack\">" + mstring +  "</div>";
} else {
newMTmpArr = [];
newMTmpArr = JSSHOP.shared.sort(currMenuArr, "cat_pid", "sortDesc");
var len = newMTmpArr.length;
var iint = 0;
var pcid = 0;
tstr = "";
currTmpPID = 0;
ts = null;
hstring = "Home";
mstring = "<a href=\"index.php\">" + hstring + "</a>";
fmcstr = mstring;
mcat = " ";
subcat = "";
while(iint < len) {
ts = newMTmpArr[iint];

if(ts._id == catid) { 
if(ts.cat_pid == "0") {
if(pid == "aa-show-category") {
mcat = "&nbsp;.&nbsp;&nbsp;" + ts.cat_title;
} else {
mcat = "&nbsp;.&nbsp;&nbsp;<a href=\"index.php?pid=aa-" + tmpSTrSorE + "-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "\">" + ts.cat_title + "</a>";
}
} else {
if(pid == "aa-show-category") {
subcat = "&nbsp;.&nbsp;&nbsp;" + ts.cat_title;
 } else {
subcat = "&nbsp;.&nbsp;&nbsp;<a href=\"index.php?pid=aa-" + tmpSTrSorE + "-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "\">" + ts.cat_title + "</a>";
}
currTmpPID = ts.cat_pid;
}

}

if(ts._id == currTmpPID) { 

mcat = "&nbsp;.&nbsp;&nbsp;<a href=\"index.php?pid=aa-" + tmpSTrSorE + "-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "\">" + ts.cat_title + "</a>";

 
}

iint++;
}


// alert("setMFormArr: "  + JSON.stringify(arrAllForms));
fmcstr = "<div style=\"margin-left: 15px\" class=\"txtClrBlack\">" + mcat +  "  " +  subcat + "</div>";


 

// alert("ifnlref: " + ifnlref);

// alert(mstring + mcat + subcat);
} 
/*
upSLMRef = "n";
try {
if(arrUprefs["prfsSHOPuser"][0].slm) {
upSLMRef = arrUprefs["prfsSHOPuser"][0].slm;
}
} catch(e) {

}
ifnlref = "n";
if(upSLMRef == "n") {
ifnlref = "y";
}

*/




// fmcstr += "<div id=\"dvPrfsLmenu\" style=\"margin-left: 15px\" class=\"txtSmall txtClrBlack\"  onclick=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','slm','" + ifnlref + "');JSSHOP.ui.toggleModule('dvPrfsLmenu','tdLMenu','LmenuO','Lmenu1');";
// if(upSLMRef == "n") {}
// fmcstr += "loadLmenu();";


// fmcstr += "\">Lmenu1</div>";
// fmcstr += "<div style=\"margin-left: 15px\" class=\"txtSmall txtClrBlack\"  onclick=\"javascript:doPopCartMod();\" >Cart</div>";

JSSHOP.ui.setTinnerHTML("dvMCatTree", fmcstr);
// alert("mArr " + JSON.stringify(currMenuArr));
};

var getMMenu = function() {
      var tmpmainUL = document.createElement("ul");
	tmpmainUL.className="animenu__nav";
      tmpLI = document.createElement("li");
	tmpLI.className="omenuartigo";	
	tmpA = document.createElement('a');
	linkText = document.createTextNode("Home");
	tmpA.appendChild(linkText);
	tmpA.title = "Home";
	tmpA.href = "index.php?cid=" + cid;
	tmpLI.appendChild(tmpA);
	tmpUL = document.createElement("ul");
	tmpUL.id = "mnuIHome";
	tmpUL.className="animenu__nav__child";
	tmpLI.appendChild(tmpUL);
	tmpmainUL.appendChild(tmpLI);
	// tmpLI.appendChild(tmpUL);
	// tmpmainUL.appendChild(tmpLI);

	return tmpmainUL;
}; 


var doAdmnMnuLd = function() {
      var admenu = document.getElementById("mnuT");
	var mainUL = getMMenu();
      tmpLI = document.createElement("li");
	tmpLI.className="omenuartigo";	
	tmpA = document.createElement('a');
	linkText = document.createTextNode("Companies");
	tmpA.appendChild(linkText);
	tmpA.title = "Companies";
	tmpA.href = "admin.php?pid=aa-edit-cos";
	tmpLI.appendChild(tmpA);
	tmpUL = document.createElement("ul");
	tmpUL.className="animenu__nav__child";
	tmpUL.id = "mnuIAdmin";
	tmpLI.appendChild(tmpUL);
	mainUL.appendChild(tmpLI);
	admenu.appendChild(mainUL);

};


var doSMStart = function() {
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where cat_pid>? and cat_coid=?";
tmpDOs["wa"] = [0,cid];
oi = getNuDBFnvp("qcat",5,null,tmpDOs);
if(cid == 0) {
doQComm(oi["rq"], null, "doSubMenuLoad");
} else {
doQComm(oi["rq"], null, "doSubMenuLoad");
}
};


var doShopMnuLd = function(a,theResp,c) {
// alert("doShopMnuLd" + theResp)
hasr = "n";
fullstr = "";
mnotAppended = "n";
     // var mainUL = document.getElementById("mnuULmain");// 

strMLink = "index.php?pid=aa-show-category";
if(strCurl.indexOf("admin.php") != -1) {
strMLink = "admin.php?pid=aa-add-qbit";
}
var mainUL = getMMenu();

try {
var arrToFill = JSON.parse(theResp);

var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
while(iint < len) {

ts = arrToFill[iint];
currMenuArr.push(ts);

 
 if(ts.cat_pid == "0") { // add only main categories to list


      tmpLI = document.createElement("li");
	tmpLI.className="omenuartigo";
 	
	tmpA = document.createElement('a');

	linkText = document.createTextNode(ts.cat_title);
	tmpA.appendChild(linkText);
	tmpA.title = ts.cat_title + "&nbsp;&nbsp;&nbsp;&nbsp;";
	tmpA.href = strMLink + "&ppid="+ts._id+"&cid="+ts.cat_coid+"&catid="+ts._id;

	tmpLI.appendChild(tmpA); 
	tmpUL = document.createElement("ul");
	tmpUL.className="animenu__nav__child";
	tmpUL.id = "mnuI" + ts._id;
	tmpLI.appendChild(tmpUL);
	mainUL.appendChild(tmpLI);
 
}
iint++;
}
 
document.getElementById("mnuT").appendChild(mainUL);

            imgplay = null;
            imgplay = new Image();
            imgplay.onload = function() {
               doSubMenuLoad()
            };
            imgplay.onerror = function() {
               doSubMenuLoad()
            };
            imgplay.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=";

document.getElementById("mnuT").appendChild(imgplay);
} catch(e) {
alert("doShopMnuLd.error: " + theResp);
}
};


var doNuShopMnuLd = function(theResp) {
// alert("doNuShopMnuLd" + theResp.rs);
hasr = "n";
fullstr = "";
mnotAppended = "n";
     // var mainUL = document.getElementById("mnuULmain");// 

strMLink = "index.php?pid=aa-show-category";
 
var mainUL = getMMenu();

try {
var arrToFill = JSON.parse(theResp.rs);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
while(iint < len) {

ts = arrToFill[iint];
currMenuArr.push(ts);

 
 if(ts.cat_pid == "0") { // add only main categories to list


      tmpLI = document.createElement("li");
	tmpLI.className="omenuartigo";
 	
	tmpA = document.createElement('a');

	linkText = document.createTextNode(ts.cat_title);
	tmpA.appendChild(linkText);
	tmpA.title = ts.cat_title + "&nbsp;&nbsp;&nbsp;&nbsp;";
	tmpA.href = strMLink + "&ppid="+ts._id+"&cid="+ts.cat_coid+"&catid="+ts._id;

	tmpLI.appendChild(tmpA); 
	tmpUL = document.createElement("ul");
	tmpUL.className="animenu__nav__child";
	tmpUL.id = "mnuI" + ts._id;
	tmpLI.appendChild(tmpUL);
	mainUL.appendChild(tmpLI);
 
}
iint++;
}



document.getElementById("mnuT").appendChild(mainUL);

            imgplay = null;
            imgplay = new Image();
            imgplay.onload = function() {
               doSubMenuLoad()
            };
            imgplay.onerror = function() {
               doSubMenuLoad()
            };
            imgplay.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=";

document.getElementById("mnuT").appendChild(imgplay);
// alert("theResp.el: " + theResp.el)
if(window[theResp.el]) {
mf = window[theResp.el];
mf();
}

} catch(e) {
 alert("do NuShopMnuLd.error: " + JSON.stringify(theResp.rs));
}
};

var doMnuFnsh = function() {
bbb = false;
};
var doNuMMenuLd = function(tmpMLcb) {
// alert("doNuMMenuLd");

currMenuArr.length = 0;
currMenuArr = [];
 // clear the top category navigation meni
 // usualy for refreshing on category edits
JSSHOP.ui.setTinnerHTML("mnuT", "");
strCurl = document.location.href;
tmpDOs = null;
tmpDOs = {};
tmpDOs["o"] = "cat_valb Desc";
tmpDOs["ws"] = "where cat_coid=?";
tmpDOs["wa"] = [cid];
oi = getNuDBFnvp("qcat",5,null,tmpDOs);
// alert(oi["rq"]);
if(cid == 0) {
doQComm(oi["rq"], null, "doShopMnuLd");
} else {
ctac = nCurrCnxOb(); 
ctac["q"] = oi["rq"];
ctac["cb"] = "doNuShopMnuLd";
ctac["ts"] = "123";
ctac["lz"] = "n";
ctac["el"] = tmpMLcb;
ccheD = JSSHOP.shared.getFrmFieldVal("qco", "c_dadded", "123");
ctac["fn"] = "catmenu" + ccheD + "-" + currCacheVer + "-" + cid + ".txt";
doNurQComm(ctac);
}
 
 
};

var doMMenuLd = function() {
doNuMMenuLd("doMnuFnsh");
};







var doCollsLoad = function() {

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-add-item&cid=" + cid + "&catid=" + catid + "'";
tmpMCollItem["mi"] = "content_copy";
tmpMCollItem["ti"] = stxt[17];
currMCollItems["aa-add-item"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-show-item&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "'";
tmpMCollItem["mi"] = "content_copy";
tmpMCollItem["ti"] = stxt[67];
currMCollItems["aa-show-item"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-edit-item&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "'";
tmpMCollItem["mi"] = "content_copy";
tmpMCollItem["ti"] = stxt[20];
currMCollItems["aa-edit-item"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-edit-item&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "&cmdci=y'";
tmpMCollItem["mi"] = "content_copy";
tmpMCollItem["ti"] = stxt[69];
currMCollItems["aa-copy-item"] = tmpMCollItem;

tmpWCollItem = null;
tmpWCollItem = {};
tmpWCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-show-category&cid=" + cid + "&catid=" + catid + "'";
tmpWCollItem["mi"] = "content_copy";
tmpWCollItem["ti"] = stxt[505];
currMCollItems["aa-show-category"] = tmpWCollItem;


tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-edit-category&cid=" + cid + "&catid=" + catid + "'";
tmpQCollItem["mi"] = "edit";
tmpQCollItem["ti"] = stxt[504];
currMCollItems["aa-edit-category"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-edit-categories&cid=" + cid + "&catid=" + catid + "'";
tmpQCollItem["mi"] = "content_copy";
tmpQCollItem["ti"] = stxt[503];
currMCollItems["aa-edit-categories"] = tmpQCollItem;


tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-edit-shop&cid=" + cid + "&catid=" + catid + "'";
tmpQCollItem["mi"] = "edit";
tmpQCollItem["ti"] = stxt[68];
currMCollItems["aa-edit-shop"] = tmpQCollItem;


tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-edit-shops'";
tmpQCollItem["mi"] = "edit";
tmpQCollItem["ti"] = stxt[4];
currMCollItems["aa-edit-shops"] = tmpQCollItem;



tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-add-shop'";
tmpQCollItem["mi"] = "add";
tmpQCollItem["ti"] = stxt[1];
currMCollItems["aa-add-shop"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-show-cart&cid=" + cid + "&catid=" + catid + "'";
tmpQCollItem["mi"] = "shopping_cart";
tmpQCollItem["ti"] = stxt[47];
currMCollItems["aa-show-cart"] = tmpQCollItem;



tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-settings&cid=" + cid + "'";
tmpQCollItem["mi"] = "settings";
tmpQCollItem["ti"] = stxt[61];
currMCollItems["aa-settings"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=aa-edit-user&cid=" + cid + "'";
tmpQCollItem["mi"] = "person";
tmpQCollItem["ti"] = stxt[63];
currMCollItems["aa-edit-user"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:preAQR();";
tmpQCollItem["mi"] = "edit";
tmpQCollItem["ti"] = stxt[65];
currMCollItems["aa-qrcode"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:getAppDBReq();";
tmpQCollItem["mi"] = "edit";
tmpQCollItem["ti"] = stxt[66];
currMCollItems["aa-appdbreq"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.php?pid=login'";
tmpQCollItem["mi"] = "lock";
tmpQCollItem["ti"] = "Login";
currMCollItems["aa-login"] = tmpQCollItem;


tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:JSSHOP.cookies.deleteCookie('quid','','');document.location.href='index.php?lgo=y'";
tmpQCollItem["mi"] = "lock";
tmpQCollItem["ti"] = "Logout";
currMCollItems["aa-logout"] = tmpQCollItem;

currMCollArr = [];
tmpPrfSAL = "n";
try {
tmpPrfSAL = arrUprefs["prfsSHOPuser"][0].sAL;
} catch(e) {
tmpPrfSAL = "n";
}

switch(pid) {
case "aa-show-item":
if((arrAllForms.qco.v[0].c_uid == quid) && (tmpPrfSAL == "y")) {
currMCollArr.push("aa-add-item");
currMCollArr.push("aa-edit-item");
currMCollArr.push("aa-copy-item");
currMCollArr.push("aa-edit-category");
}
break;

case "aa-show-category":
if((arrAllForms.qco.v[0].c_uid == quid) && (tmpPrfSAL == "y")) {
currMCollArr.push("aa-add-item");
currMCollArr.push("aa-edit-category");
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-edit-shop");
}
break;

case "aa-add-item":
currMCollArr.push("aa-show-category");
currMCollArr.push("aa-edit-category");
currMCollArr.push("aa-edit-categories");
break;
case "aa-edit-item":
currMCollArr.push("aa-show-item");
currMCollArr.push("aa-copy-item");
currMCollArr.push("aa-edit-category");
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-show-category");
break;
case "aa-edit-category":
currMCollArr.push("aa-show-category");
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-edit-shop");
currMCollArr.push("aa-edit-shops");
break;
case "aa-edit-categories":
currMCollArr.push("aa-edit-shop");
currMCollArr.push("aa-edit-shops");
break;
case "aa-edit-shop":
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-edit-shops");
break;
default:
break;
}
currMCollArr.push("aa-show-cart");
currMCollArr.push("aa-settings");
currMCollArr.push("aa-edit-user");
currMCollArr.push("aa-add-shop");

if((quid == 0) || (quid == "noQvalue")){
currMCollArr.push("aa-login");
} else {
currMCollArr.push("aa-logout");
}
tmpStrbla = ""; 
iti = 0; 
while(iti < currMCollArr.length) {
tmpStrbla += "<a href=\"" + currMCollItems[currMCollArr[iti]].u + "\" class=\"collection-item txtDecorNone\"><i class=\"material-icons\">" + currMCollItems[currMCollArr[iti]].mi + "</i><span style=\"vertical-align:super;padding-left:12px;\"><ti data-ison=\"" + currMCollItems[currMCollArr[iti]].ti + "\" data-desc=\"view\">" + currMCollItems[currMCollArr[iti]].ti + "</ti></span></a>";
iti++; 
}
return tmpStrbla;
}; 
// doMMenuLd();