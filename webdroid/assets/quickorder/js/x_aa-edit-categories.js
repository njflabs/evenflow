currPgTitle = stxt[503];
document.title = currPgTitle; // set the page title 
var currTtlMainMnus = 1;
var isMnuUpdd = "m";
var euiFFObjArr = null;
var euiFFObjArr = [];

var doCBmnuudate = function(a, b, c) {
	isMnuUpdd = "m";
	doNuMMenuLd("doMainOut");
        // alert("cat has been updated");
};
var runMnuUpdate = function() {
	// alert("runMnuUpdate");
    tmpMUtstmp = JSSHOP.getUnixTimeStamp();
    JSSHOP.shared.setFrmFieldVal("qco", "c_dadded", tmpMUtstmp); 
    procNuUIitem("qco","c_dadded",JSSHOP.shared.getFrmFieldVal("qco", "_id", 0),tmpMUtstmp,"doCBmnuudate");


};
var doMnuUpdate = function() {
    if(isMnuUpdd == "n") {
	setTimeout("runMnuUpdate()", 1000);
    } 

};




 



/*
* reloads category list
* if cat_rtype = 0, its not visible-deleted
*/
var astCatEdit = function(a,b,c) {
isMnuUpdd = "n";
// has to be a better way of reloading html menu
// because basically making the same query to the db

JSSHOP.ui.setCBBClickClr(tdMainOut,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){ doMnuUpdate()});
};





/*
* adds category to menu
*/
var checkNuCatEdit = function() {
 
document.getElementById("cat_dadded").value = JSSHOP.getUnixTimeStamp();
// intPOUP = Math.round(document.getElementById("cat_pid").value)  + 1;
// JSSHOP.shared.setFrmFieldVal("qcat", "cat_valb", 10000);
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qcat"], "tmp_"));

	  oi = getNuDBFnvp("qcat",6,null,tmpFobj);
	  doQComm(oi["rq"], "dvMainOut","astCatEdit");
};

var checkCatEdit = function() {
var vCatEdit = ["tmp_cat_title"]; 
if(JSSHOP.shared.valFieldVals(vCatEdit)) {
checkNuCatEdit();
}
};
 

var doSubCLinks = function(theArr) {
var len = theArr.length;
var iint = 0;
var sblen = 0;
var lastcatID = "";
ts = null;
var tmpScatA = {};
while(iint < len) {
ts = theArr[iint];
if(ts.cat_pid == "0") {
} else {
nsDv = document.createElement('div');
nsDv.className = "collection-item txtDecorNone margleft"; 

      if(tmpScatA[ts.cat_pid]) {
	tmpPA = document.createElement('a');
	tmpUSpn = document.createElement("span");
	tmpUSpn.innerHTML = "<i class=\"material-icons\" alt=\"arrow_upward\" title=\"arrow_upward\">&#xe5d8;</i>";
	tmpPA.appendChild(tmpUSpn);
	tmpPA.title = "Move Up";
	tmpPA.className="txtClrDlg txtSmall margleft";
      intTtlUp = Math.round(ts.cat_valb) + 1;
	tmpPA.href = "javascript:procNuUIitem('qcat','cat_valb','" + ts._id + "','" + intTtlUp + "','astCatEdit');";
	nsDv.appendChild(tmpPA); 
	} else {
	tmpUSpn = document.createElement("span");
	tmpUSpn.innerHTML = "<i class=\"material-icons txtClrWhite\" alt=\"arrow_upward\" title=\"arrow_upward\">&#xe5d8;</i>";

	tmpUSpn.className="slmtable margleft";
	nsDv.appendChild(tmpUSpn); 
      tmpScatA[ts.cat_pid] = ts._id;
	}

crMI = "catMI" + ts.cat_pid;
ptspn = document.getElementById(crMI);
var a = document.createElement('a');

a.innerHTML = "<span style=\"vertical-align:super\">" + ts.cat_title + "</span>";
// var linkText = document.createTextNode("        " + ts.cat_title);
// a.appendChild(linkText);
a.title = ts.cat_title;
a.href = "index.html?pid=aa-edit-category&ppid="+ts._id+"&cid="+ts.cat_coid+"&catid="+ts._id;
nsDv.appendChild(a);
ptspn.appendChild(nsDv);
sblen++;
}
iint++;
}

};



/*
* fills category list
*/
var doMainOut = function() {
// alert("doMainOut: " + JSON.stringify(currMenuArr));
var arrToFill = null;
arrToFill = [];
hasr = "n";
fullstr = "";
tmpSlct = document.getElementById("tmp_cat_pid");
JSSHOP.shared.removeOptions(document.getElementById("tmp_cat_pid"));
// arrToFill = JSSHOP.shared.sort(currMenuArr, "cat_pid", "sortDesc");
// arrToFill = JSSHOP.shared.sort(currMenuArr, "cat_valb", "sortAsc");
arrToFill = currMenuArr;
mainCDiv = document.getElementById("dvMainOut");
mainCDiv.innerHTML = ""; // clear the div, since will be appending
//using the function:
JSSHOP.shared.addCurrSelectOpt(tmpSlct, "0", "Top Main");
// alert(JSON.stringify(arrToFill));
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
var mlen = 0;
tstr = "";
while(iint < len) {
ts = arrToFill[iint];
if(ts.cat_pid == "0") { // add only main categories to list
currTtlMainMnus++;
	
      tmpCdiv = document.createElement("div");
	tmpCdiv.id = "catMI" + ts._id;
	tmpCdiv.className="collection-item";



      if((mlen > 0) && (iint > 0)) {
	tmpUSpn = document.createElement("span");
	tmpUSpn.innerHTML = "<i style=\"font-size: 36px;\" class=\"material-icons txtBold\" alt=\"arrow_upward\" title=\"arrow_upward\">&#xe5d8;</i>";
	tmpPA = document.createElement("a");
	tmpPA.className="txtDecorNone brdrVertNone txtBold txtClrDlg";
	tmpPA.appendChild(tmpUSpn);
	tmpPA.title = "Move Up";
      intTtlUpOrDown = Math.round(ts.cat_valb) + 1;
      // intTtlUpOrDown = Math.round(ts.cat_valb) - 1;
	tmpPA.href = "javascript:procNuUIitem('qcat','cat_valb','" + ts._id + "','" + intTtlUpOrDown + "','astCatEdit');";
	// tmpPA.className = "collection-item txtDecorNone";
	tmpCdiv.appendChild(tmpPA); 
	} else {
	tmpUSpn = document.createElement("span");
	tmpUSpn.innerHTML = "&nbsp;"
	tmpUSpn.innerHTML = "<i style=\"font-size: 36px;\" class=\"material-icons txtBold txtClrWhite\" alt=\"arrow_upward\" title=\"arrow_upward\">&#xe5d8;</i>";
	tmpUSpn.className="slmtable";
	tmpCdiv.appendChild(tmpUSpn); 
	}

 	
	tmpA = document.createElement("a");
 
	tmpA.innerHTML = "<span style=\"vertical-align:super;padding-left:12px;\">" + ts.cat_title + "</span>";
	// tmpA.title = ts.cat_title;
	tmpA.href = "index.html?pid=aa-edit-category&cid=" + cid + "&catid=" + ts._id;

	tmpA.className = "slmtable txtClrHdr txtBold bkgdClrTNrml";
	tmpCdiv.appendChild(tmpA);
		 
	tmpSpn = document.createElement("span");

	tmpCdiv.appendChild(tmpSpn);
	mainCDiv.appendChild(tmpCdiv);

	JSSHOP.shared.addCurrSelectOpt(tmpSlct, ts._id, ts.cat_title);

	mlen++;
}  
tstr += "<tr>";
tstr += "<td><a href=\"index.html?pid=aa-edit-category&cid=" + cid + "&catid=" + ts._id + "\" class=\"txtBig txtBold\">" + ts.cat_title + "</a></td>";
tstr += "</tr>";

iint++;
}

doSubCLinks(currMenuArr);
 
};
 
 
/* overide the done filling def forms in  x_booter fnishCoForm etc., */
var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
JSSHOP.shared.setFrmFieldVal("qcat", "cat_coid", cid);
JSSHOP.shared.setFrmFieldVal("qcat", "cat_uid", quid);


tfit = nCurrFFieldOb();
tfit.fid = "tmp_cat_title";
tfit.fdv = stxt[502];
tfit.fda = "y";
tfit.lid = "lbl_cat_title";
tfit.ltxt = stxt[500];  
euiFFObjArr.push(tfit); 


tfip = nCurrFFieldOb();
tfip.fid = "tmp_cat_pid";
tfip.fdv = stxt[501];
tfip.fda = "y";
tfip.lid = "lbl_cat_pid";
tfip.ltxt = stxt[501] + ":";  
euiFFObjArr.push(tfip); 

JSSHOP.shared.initFrmComps(euiFFObjArr);



doMainOut();
return dmyFnishCntLoad;
};


/*
* main loader function
* makes 2 requests to fill company info
* and list categories in this companys menu
*/
var doPinit = function() {

};
