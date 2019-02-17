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
mstring = "<a href=\"index.php\">" + hstring + "</a>";
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
	tmpA.href = "index.php?home";
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
// doMMenuLd();