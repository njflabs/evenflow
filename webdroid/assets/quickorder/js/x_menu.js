var tmpSTrSorE = "show";

if(currAdmnMode == "y") {
tmpSTrSorE = "edit";
}


var doCatTreeLoad = function() {
try {
tmpCatPid = 0;
tmpCatPid = document.getElementById("cat_pid").value;

arrToFill = null;
arrToFill =  [];
// alert(JSON.stringify(currMenuArr));
arrToFill = currMenuArr;
var tmpLsr = "";
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tmpMCatstr = "";
tmpSubCatstr = "";
tstr = "";
while(iint < len) {
ts = arrToFill[iint];

switch(ts._id) {
case catid:
tstr += "&nbsp;&nbsp;&nbsp;<a class=\"txtDecorNone txtClrHdr txtBold\" href=\"index.html?pid=aa-" + tmpSTrSorE + "-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "\">" + ts.cat_title + "</a>";
break;
case tmpCatPid:
tstr += "&nbsp;&nbsp;&nbsp;<a class=\"txtDecorNone\" href=\"index.html?pid=aa-" + tmpSTrSorE + "-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "\">" + ts.cat_title + "</a>";
break;

default:
if(ts.cat_pid == tmpCatPid) {
tstr += "&nbsp;&nbsp;&nbsp;<a class=\"txtDecorNone txtClrRed\" href=\"index.html?pid=aa-" + tmpSTrSorE + "-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "\">" + ts.cat_title + "</a>";
}
if(ts.cat_pid == catid) {
tstr += "&nbsp;&nbsp;&nbsp;<a class=\"txtDecorNone txtClrDlg\" href=\"index.html?pid=aa-" + tmpSTrSorE + "-category&cid=" + ts.cat_coid + "&catid=" + ts._id + "\">" + ts.cat_title + "</a>";
}
// tstr += "";
break;
}
 
iint++;
}


// not in any category
// in main category
// in sub category




JSSHOP.ui.setTinnerHTML("dvMCatTree", tstr);
} catch(e) {
alert("doCatTreeLoad: " + e);
} 
};


var doSubMenuLoad = function() {
try {
arrToFill = null;
arrToFill =  [];
// alert(JSON.stringify(currMenuArr));
arrToFill = currMenuArr;
var tmpLsr = "";
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
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
a.href = "index.html?pid=aa-" + tmpSTrSorE + "-category&ppid="+ts._id+"&cid="+ts.cat_coid+"&catid="+ts._id;
 


    li.appendChild(a);
    ul.appendChild(li);
   //tmpUstr = "<li><a href=\index.html?pid=aa-add-qbit&ppid="+ts._id+"&cid="+ts.cat_coid+"\">"+ts.cat_title+"</a></li>";
   // ul.append(tmpUstr);

}
iint++;
}

// doCatTreeLoad();
} catch(e) {
 
alert("doSubMenuLoad: " + e);
}  
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
	tmpA.href = "index.html?cid=" + cid;
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
	tmpA.href = "admin.html?pid=aa-edit-cos";
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

strMLink = "index.html?pid=aa-show-category";
 
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

strMLink = "index.html?pid=aa-show-category";
 
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
clearNuLclStrg("localStorage", "doNuShopMnuLd");
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
//alert(oi["rq"]);
if(cid == 0) {
doQComm(oi["rq"], null, "doShopMnuLd");
} else {
ctac = nCurrCnxOb(); 
ctac["q"] = oi["rq"];
ctac["cb"] = "doNuShopMnuLd";
ctac["ts"] = "123";
ctac["lz"] = "n";
ctac["ls"] = "n";

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
tmpMCollItem["u"] = "JSSHOP.ui.popAndFillLbox(doFavoritesRndr('y', 'y', 'y', 60).replace(/::/g, '<br>'));";
tmpMCollItem["mi"] = "&#xe87d;"; // faorites
tmpMCollItem["ti"] = "Favorites";
tmpMCollItem["c"] = "collection-item txtClrRed";
tmpMCollItem["nm"] = "aa-show-favorites";
currMCollItems["aa-show-favorites"] = tmpMCollItem;


tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:JSSHOP.ui.getPopHelp('page');";
tmpMCollItem["mi"] = "&#xe887;"; // help
tmpMCollItem["ti"] = stxt[71];
tmpMCollItem["c"] = "collection-item";
tmpMCollItem["nm"] = "aa-show-help";
currMCollItems["aa-show-help"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','sia','y');document.location.href='index.html?cid=" + cid + "'";
tmpMCollItem["mi"] = "&#xe887;"; // about
tmpMCollItem["ti"] = "About";
tmpMCollItem["c"] = "collection-item";
tmpMCollItem["nm"] = "aa-show-about";
currMCollItems["aa-show-about"] = tmpMCollItem;


tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-item&cid=" + cid + "&catid=" + catid + "'";
tmpMCollItem["mi"] = "&#xe145;"; // add
tmpMCollItem["ti"] = stxt[17];
tmpMCollItem["c"] = "coll-menu-item";
tmpMCollItem["nm"] = "aa-add-item";
currMCollItems["aa-add-item"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-show-item&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "'";
tmpMCollItem["mi"] = "&#xe8ff;"; // zoom_in
tmpMCollItem["ti"] = stxt[67];
tmpMCollItem["c"] = "collection-item";
tmpMCollItem["nm"] = "aa-show-item";
currMCollItems["aa-show-item"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-item&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "'";
tmpMCollItem["mi"] = "&#xe3c9;"; // edit
tmpMCollItem["ti"] = stxt[20];
tmpMCollItem["c"] = "coll-menu-item";
tmpMCollItem["nm"] = "aa-edit-item";
currMCollItems["aa-edit-item"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-item&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "&cmdci=y'";
tmpMCollItem["mi"] = "&#xe14d;"; // content_copy
tmpMCollItem["ti"] = stxt[69];
tmpMCollItem["c"] = "coll-menu-item";
tmpMCollItem["nm"] = "aa-copy-item";
currMCollItems["aa-copy-item"] = tmpMCollItem;

tmpWCollItem = null;
tmpWCollItem = {};
tmpWCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-show-category&cid=" + cid + "&catid=" + catid + "'";
tmpWCollItem["mi"] = "&#xe8ff;"; // zoom_in
tmpWCollItem["ti"] = stxt[505];
tmpWCollItem["c"] = "collection-item";
currMCollItems["aa-show-category"] = tmpWCollItem;


/* toggle-buttons  for the products grid-row  layout and price asc-desc order */


tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "tglCatUibtns('btnP-aa-toggle-pview', 'prfsSHOPuser','scv','r');";

// tmpMCollItem["u"] = "javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','r');renderAgn()";
tmpMCollItem["mi"] = "&#xe240;"; // Row
tmpMCollItem["ti"] = "Row";
tmpMCollItem["c"] = "coll-menu-item";
tmpMCollItem["nm"] = "aa-toggle-pview";
tmpMCollItem["h"] = "Sort By:";
currMCollItems["aa-toggle-row"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "tglCatUibtns('btnP-aa-toggle-pview', 'prfsSHOPuser','scv','g');";

// tmpMCollItem["u"] = "javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','g');renderAgn()";
tmpMCollItem["mi"] = "&#xe3ec"; // row
tmpMCollItem["ti"] = "Grid";
tmpMCollItem["c"] = "coll-menu-item";
tmpMCollItem["nm"] = "aa-toggle-pview";
tmpMCollItem["h"] = "Sort By:";
currMCollItems["aa-toggle-grid"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "tglCatUibtns('btnP-aa-toggle-pprice', 'prfsSHOPuser','scp','a');";

// tmpMCollItem["u"] = "javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scp','a');renderPrix('a')";
tmpMCollItem["mi"] = "&#xe25c;"; // item price ascending
tmpMCollItem["ti"] = "Price Ascending";
tmpMCollItem["c"] = "coll-menu-item";
tmpMCollItem["nm"] = "aa-toggle-pprice";
currMCollItems["aa-toggle-priceAsc"] = tmpMCollItem;

tmpWCollItem = null;
tmpWCollItem = {};
tmpWCollItem["u"] = "tglCatUibtns('btnP-aa-toggle-pprice', 'prfsSHOPuser','scp','d');";

// tmpWCollItem["u"] = "javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scp','d');renderPrix('d')";
tmpWCollItem["mi"] = "&#xe227;"; // item price descending
tmpWCollItem["ti"] = "Price Descending";
tmpWCollItem["c"] = "coll-menu-item";
tmpWCollItem["nm"] = "aa-toggle-pprice";
currMCollItems["aa-toggle-priceDesc"] = tmpWCollItem;










tmpWCollItem = null;
tmpWCollItem = {};
tmpWCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-show-category&fc=y&cid=" + cid + "&catid=" + catid + "'";
tmpWCollItem["mi"] = "&#xe363;"; // autorenew
tmpWCollItem["ti"] = "Refresh";
tmpWCollItem["c"] = "collection-item";
currMCollItems["aa-force-clear"] = tmpWCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-category&cid=" + cid + "&catid=" + catid + "'";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = stxt[504];
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-edit-category"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-categories&cid=" + cid + "&catid=" + catid + "'";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = stxt[503];
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-edit-categories"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-shop&cid=" + cid + "&catid=" + catid + "'";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = stxt[68];
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-edit-shop"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-shops'";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = stxt[4];
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-edit-shops"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-add-shop'";
tmpQCollItem["mi"] = "&#xe145;"; // add
tmpQCollItem["ti"] = stxt[1];
tmpQCollItem["c"] = "collection-item";
currMCollItems["aa-add-shop"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:JSSHOP.shop.doCartAddPop();";

// tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-show-cart&cid=" + cid + "&catid=" + catid + "'";
// tmpQCollItem["mi"] = "shopping_cart";
tmpQCollItem["mi"] = "&#xe8cc;";
tmpQCollItem["c"] = "collection-item";
tStrCTtl = "<span name=\"spnCtotal\" class=\"txtSmall\" style=\"text-align: right;margin-bottom:30px; padding:0px;float: right\">";
tStrCTtl += "<a href=\"javascript:JSSHOP.shop.doCartAddPop();\" class=\"txtDecorNone\" style=\"text-align: right;margin-bottom:30px; padding:0px;\">";
tStrCTtl +=  currCartTtl + "</a></span>";

// tmpQCollItem["i"] = tStrCTtl;
tmpQCollItem["ti"] = stxt[47] + "   <span class=\"txtSmall txtClrDlg\" style=\"margin-left: 40px;\">" + currCartTtl + "</span>";
currMCollItems["aa-show-cart"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-settings&cid=" + cid + "'";
tmpQCollItem["mi"] = "&#xe8b8;"; // settings
tmpQCollItem["ti"] = stxt[61]; // settings
tmpQCollItem["c"] = "collection-item";
currMCollItems["aa-settings"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-user&cid=" + cid + "'";
tmpQCollItem["mi"] = "&#xe7fd;"; // person
tmpQCollItem["ti"] = stxt[63];
tmpQCollItem["c"] = "collection-item";
currMCollItems["aa-edit-user"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:preAQR();";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = stxt[65];
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-qrcode"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:getAppDBReq();";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = stxt[66];
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-appdbreq"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:JSSHOP.jndroid.doCutOuts();";
tmpQCollItem["mi"] = "&#xe3b3;"; // edit
tmpQCollItem["ti"] = "CutOuts Cam";
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-docutouts"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-use-adminapp&cid=" + cid + "'";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = "Use Java adminApp";
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-use-adminapp"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-sqldump'";
tmpQCollItem["mi"] = "&#xe3c9;"; // edit
tmpQCollItem["ti"] = "SQL dump";
tmpQCollItem["c"] = "coll-menu-item";
currMCollItems["aa-sqldump"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:document.location.href='index.html?pid=login'";
tmpQCollItem["mi"] = "&#xe897;"; // lock
tmpQCollItem["ti"] = "Login";
tmpQCollItem["c"] = "collection-item";
currMCollItems["aa-login"] = tmpQCollItem;

tmpQCollItem = null;
tmpQCollItem = {};
tmpQCollItem["u"] = "javascript:JSSHOP.cookies.deleteCookie('quid','','');document.location.href='index.html?lgo=y&cid=" + cid + "'";
tmpQCollItem["mi"] = "&#xe897;"; // lock
tmpQCollItem["ti"] = "Logout";
tmpQCollItem["c"] = "collection-item";
currMCollItems["aa-logout"] = tmpQCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-show-app-tools&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "'";
tmpMCollItem["mi"] = "&#xe869;"; // build
tmpMCollItem["ti"] = "App Tools";
tmpMCollItem["c"] = "coll-menu-item";
currMCollItems["aa-show-app-tools"] = tmpMCollItem;


tmpMCollItem = null;
tmpMCollItem = {};
tmpMCollItem["u"] = "javascript:document.location.href='index.html?pid=aa-edit-synch&cid=" + cid + "&catid=" + catid + "&itemid=" + itemid + "'";
tmpMCollItem["mi"] = "&#xe869;"; // build
tmpMCollItem["ti"] = "Synch";
tmpMCollItem["c"] = "coll-menu-item";
currMCollItems["aa-edit-synch"] = tmpMCollItem;

tmpMCollItem = null;
tmpMCollItem = {};
		

tmpMCollItem["u"] = "JSSHOP.ui.popAndFillLbox(doCurrInfoStr());";
tmpMCollItem["mi"] = "&#xe869;"; // build
tmpMCollItem["ti"] = "debug";
tmpMCollItem["c"] = "coll-menu-item";
currMCollItems["aa-show-dbug"] = tmpMCollItem;

currMCollArr = [];
tmpPrfSAL = "y";
try {
tmpPrfSAL = arrUprefs["prfsSHOPuser"][0].sAL;
} catch(e) {
tmpPrfSAL = "y";
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


try { var upMRefs = arrUprefs["prfsSHOPuser"][0].scv; }catch (e) { var upRefs = "r";}
try { var upMPrixRef = arrUprefs["prfsSHOPuser"][0].scp; } catch (e) { var upPrixRef = "u"; }
if (upMRefs == "r") {
currMCollArr.push("aa-toggle-grid");
} else {
currMCollArr.push("aa-toggle-row");
}
if (upMPrixRef == "a") {
currMCollArr.push("aa-toggle-priceDesc");
} else {
currMCollArr.push("aa-toggle-priceDesc");
}

if((arrAllForms.qco.v[0].c_uid == quid) && (tmpPrfSAL == "y")) {
currMCollArr.push("aa-add-item");
currMCollArr.push("aa-edit-category");
currMCollArr.push("aa-force-clear");
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-edit-shop");
if(isJavaFx == "yes") {
currMCollArr.push("aa-appdbreq");
} else {
currMCollArr.push("aa-use-adminapp");
}
}

currMCollArr.push("break");
break;

case "aa-add-item":
currMCollArr.push("aa-show-category");
currMCollArr.push("aa-edit-category");
currMCollArr.push("aa-edit-categories");
break;
case "aa-edit-item":
currMCollArr.push("aa-show-item");
currMCollArr.push("aa-add-item");
currMCollArr.push("aa-copy-item");
currMCollArr.push("aa-edit-category");
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-show-category");
break;
case "aa-edit-category":
currMCollArr.push("aa-add-item");
currMCollArr.push("aa-show-category");
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-edit-shop");
// currMCollArr.push("aa-edit-shops");
currMCollArr.push("aa-edit-synch");
if(isJavaFx == "yes") {
currMCollArr.push("aa-appdbreq");
} else {
currMCollArr.push("aa-use-adminapp");
}
break;
case "aa-edit-categories":
currMCollArr.push("aa-edit-shop");
// currMCollArr.push("aa-edit-shops");
break;
case "aa-edit-shop":
currMCollArr.push("aa-edit-categories");
currMCollArr.push("aa-edit-shops");
break;
case "aa-edit-user":
currMCollArr.push("aa-edit-shop");
// currMCollArr.push("aa-edit-shops");
currMCollArr.push("aa-settings");
currMCollArr.push("aa-add-shop");
break;

default:


if((quid == 0) || (quid == "noQvalue")){
// currMCollArr.push("aa-add-shop");
} else {
// currMCollArr.push("aa-edit-shops");
}

break;
}
currMCollArr.push("aa-edit-shops");


/*
currMCollArr.push("aa-show-cart");
currMCollArr.push("aa-settings");
currMCollArr.push("aa-edit-user");
currMCollArr.push("recent")
currMCollArr.push("aa-add-shop");
*/
if(isJApp == "y") {
currMCollArr.push("aa-show-app-tools");
}

if((quid == 0) || (quid == "noQvalue")){
currMCollArr.push("aa-login");
} else {
currMCollArr.push("aa-logout");
}
// currMCollArr.push("aa-docutouts");

currMCollArr.push("aa-show-cart");
currMCollArr.push("aa-settings");
currMCollArr.push("aa-edit-user");
currMCollArr.push("recent");
currMCollArr.push("aa-show-favorites");
currMCollArr.push("aa-show-help");
currMCollArr.push("aa-show-about");
currMCollArr.push("aa-show-dbug");
tmpStrbla = ""; 
tmpStrAdmnM = "";
iti = 0; 
while(iti < currMCollArr.length) {
if(currMCollArr[iti] == "break") {
tmpStrAdmnM += "<div>---</div>";
} else if(currMCollArr[iti] == "recent") {
tmpStrbla += "<div class=\"collection-item txtSmall txtBold\" style=\"margin-left:10px\">Recent:<br>" + currRcntActHstr.replace(/::/g, "<br>") + "</div>";
} else {
if(currMCollItems[currMCollArr[iti]].c == "collection-item") {
// tmpStrbla += "<a href=\"javascript:void(0);\" onclick=\"javascript:JSSHOP.ui.setNuCBBClickClr(this,'kcoll-menu-item','" + currMCollItems[currMCollArr[iti]].c + "', function(){"  + currMCollItems[currMCollArr[iti]].u + ";JSSHOP.ui.showHideElement('mmDdown','hide')}, 20);\" class=\"" + currMCollItems[currMCollArr[iti]].c + "\"><span><i class=\"menu-material-icons\">" + currMCollItems[currMCollArr[iti]].mi + "</i></span><span style=\"vertical-align:super;padding-left:12px;\">" + currMCollItems[currMCollArr[iti]].ti + "</span></a>";
} else {
// tmpStrAdmnM += "<a href=\"javascript:void(0);\" onclick=\"javascript:JSSHOP.ui.setNuCBBClickClr(this,'kcoll-menu-item','" + currMCollItems[currMCollArr[iti]].c + "', function(){"  + currMCollItems[currMCollArr[iti]].u + ";JSSHOP.ui.showHideElement('mmDdown','hide')}, 20);\" class=\"" + currMCollItems[currMCollArr[iti]].c + "\"><span><i class=\"menu-material-icons "  + currMCollItems[currMCollArr[iti]].c + "\">" + currMCollItems[currMCollArr[iti]].mi + "</i></span><span style=\"vertical-align:super;padding-left:12px;\">" + currMCollItems[currMCollArr[iti]].ti + "</span></a>";
}
tmpClsNoClctn = currMCollItems[currMCollArr[iti]].c;
tmpClscClctn = tmpClsNoClctn;
if(currMCollItems[currMCollArr[iti]].nm) {
mlinkNm = currMCollItems[currMCollArr[iti]].nm;
} else {
mlinkNm = currMCollArr[iti];
}
if(currMCollItems[currMCollArr[iti]].h) {
tmpStrAdmnM += "<div><b>" + currMCollItems[currMCollArr[iti]].h + "</b></div>";
}

tmpStrAdmnM += "<span name=\"btnP-" + mlinkNm + "\"><a href=\"javascript:void(0);\" onclick=\"javascript:JSSHOP.ui.setNuCBBClickClr(this,'kcoll-menu-item','" + currMCollItems[currMCollArr[iti]].c + "', function(){"  + currMCollItems[currMCollArr[iti]].u + ";JSSHOP.ui.showHideElement('mmDdown','hide')}, 20);\" class=\"" + currMCollItems[currMCollArr[iti]].c + "\"><span><i class=\"menu-material-icons "  + tmpClscClctn + "\">" + currMCollItems[currMCollArr[iti]].mi + "</i></span><span style=\"vertical-align:super;padding-left:12px;\" class=\""  + tmpClscClctn + "\">" + currMCollItems[currMCollArr[iti]].ti + "</span></a>";
if(currMCollItems[currMCollArr[iti]].i) {
tmpStrAdmnM += currMCollItems[currMCollArr[iti]].i;
}
tmpStrAdmnM += "</span>";

if(currMCollItems[currMCollArr[iti]].f) {
tmpStrAdmnM += currMCollItems[currMCollArr[iti]].f;
}

}
// tmpStrbla += "<a href=\"" + currMCollItems[currMCollArr[iti]].u + "\" class=\"" + currMCollItems[currMCollArr[iti]].c + "\"><span><i class=\"menu-material-icons\">" + currMCollItems[currMCollArr[iti]].mi + "</i></span><span style=\"vertical-align:super;padding-left:12px;\"><ti data-ison=\"" + currMCollItems[currMCollArr[iti]].ti + "\" data-desc=\"view\">" + currMCollItems[currMCollArr[iti]].ti + "</ti></span></a>";
iti++; 
}
return tmpStrAdmnM;
}; 
