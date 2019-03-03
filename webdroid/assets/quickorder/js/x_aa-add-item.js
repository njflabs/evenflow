var tmpSQBArr = null;
var tmpVitemArr = null;
var tmpPrdMediaArr = null;
tmpPrdMediaArr = [];
tmpSQBArr = [];
tmpVitemArr = [];
var tmpVindex = 0;
currPgTitle = stxt[17];
document.title = currPgTitle; // set the page title 
var euiFFObjArr = null;
var euiFFObjArr = [];

ck_name = /^[A-Za-z0-9 ]{3,20}$/;
ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
ck_username = /^[A-Za-z0-9_]{3,20}$/;
ck_password =  /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
ck_title = /^[A-Za-z0-9.\s]{3,120}$/;

var doDelIRdir = function(a,b,c) {
document.location.href = "index.php?pid=aa-edit-category&cid=" + cid + "&catid=" + catid + "&t=" + JSSHOP.getUnixTimeStamp();
};


var doN = function(a,b,c) {
// document.location.href = document.location.href + "&t=" + JSSHOP.getUnixTimeStamp();
doItemEdit();
};



var doneImgInsrt = function(a,b,c) {
try {
JSSHOP.shop.getPrdMedia(catid,itemid,"dadido");
// JSSHOP.shared.setFrmFieldVal("qmedia", "m_dadded", JSSHOP.getUnixTimeStamp());
doAIU();
// doItemEdit();
} catch(e) {
alert(e);
}
};

var finishImgInsrt = function(theImg) {
try {
JSSHOP.shared.setFrmFieldVal("qmedia", "m_file", theImg); 
JSSHOP.shared.setFrmFieldVal("qmedia", "m_uid", quid);
JSSHOP.shared.setFrmFieldVal("qmedia", "m_coid", cid);
JSSHOP.shared.setFrmFieldVal("qmedia", "m_catid", catid);
JSSHOP.shared.setFrmFieldVal("qmedia", "m_pid", itemid);
// JSSHOP.shared.setFrmFieldVal("qmedia", "m_dadded", JSSHOP.getUnixTimeStamp());
tmpDOs = null;
tmpDOs = {};
tmpDOs["knvp"] = JSSHOP.shared.getFrmVals(document["qmedia"], "nada");
oi = getNuDBFnvp("qmedia",6,null,tmpDOs);
doQComm(oi["rq"], null, "doneImgInsrt");
} catch(e) {
alert(e);
}
}

var finishImgUload = function(theImg) {
try {

// JSSHOP.shared.setFrmFieldVal("qitem","i_img",theImg);
// document.getElementById("mod_i_img").value = theImg;
// document.getElementById("imgIedit").src = tImgstr;
finishImgInsrt(theImg);
} catch(e) {
alert(e);
}

};

var fnshProdMDel = function(aa,bb,cc) { 
JSSHOP.ui.closeLbox();
JSSHOP.shop.getPrdMedia(catid,itemid,"dadido")
};

var doPrdMDelete = function() { 
    if(confirm(stxt[42] + " " + stxt[19] + "?")) {
    procNuUIitem("qmedia","m_rtype",JSSHOP.shared.getFrmFieldVal("qmedia", "_id", 0),"0","fnshProdMDel");
    }
};

var fnshCatChange = function() { 
document.getElementById("mod_i_catid").disabled=false;
 JSSHOP.ui.setCBBClickClr(tdSpin,'bkgdClrDlg','clsDummy', function(){void(0)});
};



var doCatChange = function(theObj) { 
document.getElementById("mod_i_catid").disabled=true;
objVal = JSSHOP.shared.getCurrSelectOpt(theObj);
procNuUIitem("qitem","i_catid",itemid,objVal,"fnshCatChange");
};

 
var fnshProdMMain = function(aa,bb,cc) { 
JSSHOP.ui.closeLbox();
showCurrImg();
};


var doPrdMMain = function() { 
    if(confirm("Set as main product picture?" + JSSHOP.shared.getFrmFieldVal("qmedia", "m_file", 0))) {
JSSHOP.shared.setFrmFieldVal("qitem", "i_img", JSSHOP.shared.getFrmFieldVal("qmedia", "m_file", 0));
    procNuUIitem("qitem","i_img",itemid,JSSHOP.shared.getFrmFieldVal("qmedia", "m_file", 0),"fnshProdMMain");
    }
};
 


var doQIMeta = function() {
    tmpMetaObj = {};
    tmpMetaObj.qco = arrAllForms.qco.v[0];
    tmpMetaObj.qitem = JSSHOP.shop.getCurrItemArr();
    return JSON.stringify(tmpMetaObj);
};


var appFnshImgUload = function(theUri, theImg) {
try {
document.getElementById("fldChallArray").value = theUri;
tmpImg = document.getElementById("fldChallArray").value;
finishImgInsrt(tmpImg);
} catch(e) {
alert("appFnshImgUload: " + e);
}
};



var doFUJSLoad = function() {
ttbtn = document.getElementById('uploadBtn');

if((isJApp !== "no") && (isPhP == "no")) { 
alert("japp-no-php");
// JSSHOP.ui.addEvent(ttbtn, "click", function() { JSSHOP.jndroid.doPagePopUp("quickorder/media_chooser.html", "noQvalue");return false; });

} else {
JSSHOP.loadScript("js/sau.js", JSSHOP.checkLoader,"js");

// JSSHOP.ui.addEvent(ttbtn, "click", function() { JSSHOP.jndroid.doPagePopUp("quickorder/app_imgselect.html", "noQvalue") });
// tstr = JSSHOP.ui.doDefBtn("Barcode",app.setImgEdit());
// JSSHOP.ui.setTinnerHTML("dvAppBCodeBtn", tstr);
}
};


var showCurrImg = function() {
tmpIvaval = i_img.value;
if(tmpIvaval !== 'noQvalue') { 

if(isPhP == "no") {
tmpIstrI = "data:image/png;base64, " + tmpIvaval;
imgIedit.src=tmpIstrI;
} else {
if(tmpIvaval.indexOf(".") != -1) {
tmpIstrI = "images/pimgs/s_thumb" + tmpIvaval;	
imgIedit.src=tmpIstrI;
}  

}
}
};

/*
* opens up the item popup
*/

var doItemEditPop = function() {
    loadJSModal("tplates/aa-mod-edit-item.html?tt=" + JSSHOP.getUnixTimeStamp());
}
var doQIedit = function(iei) {
    tmpVindex = iei;
	 
    JSSHOP.shared.setFrmVals("qitem",tmpVitemArr[iei],function() {doItemEditPop()});
};

/*
* returns the html link for product editing popup
*/
var getQIstr = function(rid, tmprhtml) {
    strTret = "<div onclick=\"JSSHOP.ui.setCBBClickClr(this,'crsrPointer brdrClrHdr','crsrPointer brdrNone', function(){doQIedit(" + rid + ");});\" class=\"crsrPointer brdrNone txtBig\">";
    strTret += tmprhtml + "</div>";

    return strTret;
};


/*
* renders all items in the category
*/

var renderNuTQBItems = function(a,b,c) {
JSSHOP.ui.setCBBClickClr(dvWrapper,'cls_button cls_button-medium brdrClrDlg txtClrHdr','clsDummy', function(){void(0)})
};



var renderDBis = function(theElem, theResp, marble) {
    // alert("renderDBis: " + theResp);
    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = JSON.parse(theResp);
    JSSHOP.shared.setFrmVals("qitem",tmpVitemArr[0],void(0));
    JSSHOP.shared.setDynFieldVals(tmpVitemArr[0],"mod_");
     if(tmpVitemArr[0].i_img){
	tmpIva = tmpVitemArr[0].i_img;
	if(tmpIva == "noQvalue") {
	} else {
	tImgstr = "images/pimgs/" + tmpVitemArr[0].i_img; 
	document.getElementById("imgIedit").src =  tImgstr;
	}
  }
};


var preRenderTQBItems = function(a, b, c) {
    // alert("pre: " + JSON.stringify(b));
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["ws"] = "where _id=? and i_rtype=?";
    tmpDOs["wa"] = [itemid,"5"];
    oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
    doQComm(oi["rq"], null, "renderDBis");
    // renderTQBItems();
};






var doCBcatedit = function(a, b, c) {
  //       JSSHOP.ui.setCBBClickClr(mmn,'cls_button cls_button-medium brdrClrDlg txtClrHdr','nanimenu', function(){ doMMenuLd() });
         JSSHOP.ui.setCBBClickClr(mmn,'cls_button cls_button-medium brdrClrDlg txtClrHdr','nanimenu', function(){ document.location.href=document.location.href + '&fc=y' });

   
};

var doCatTitleEdit = function(qcid) {
JSSHOP.shared.setFrmFieldVal("qcat", "cat_dadded", JSSHOP.getUnixTimeStamp()); 
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where _id=?";
tmpDOs["wa"] = [qcid];
tmpDOs["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qcat"], "tmp_"));
oi = getNuDBFnvp("qcat",7,null,tmpDOs);
doQComm(oi["rq"], null, "doCBcatedit");
};



var doItemEdit = function() {
    JSSHOP.ui.closeLbox();

/*
    tmpFarr = null;
    tmpFarr = JSSHOP.shared.setArrVals(tmpVitemArr, tmpVindex, JSSHOP.shared.getDynFrmVals(document["qitem"], "mod_"));

    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = tmpFarr;

*/



    JSSHOP.shared.setFrmFieldVal("qitem", "i_dadded", JSSHOP.getUnixTimeStamp()); 
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qitem"], "mod_"));
    tmpFobj["ws"] = "where _id=?";
    tmpFobj["wa"] = [itemid];
    oi = getNuDBFnvp("qitem", 7, null, tmpFobj);
    // JSSHOP.ui.setCBBClickClr(tblCntEditItem,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
 
if(JSSHOP.shared.valVOarr(euiFFObjArr, "dvVdtnError")) {
   doQComm(oi["rq"], null, "renderNuTQBItems"); 
}
   // alert("oiRQ " + oi["rq"]); 

 
     // doQComm(oi["rq"], null, "preRenderTQBItems");
};



var doItemAdd = function(t, am) {
	// JSSHOP.shared.setDynFrmVals(document["qitem"], "clear");
    JSSHOP.shared.setFrmFieldVal("qitem", "i_desc", "....");
    JSSHOP.shared.setFrmFieldVal("qitem", "i_uid", quid);
    JSSHOP.shared.setFrmFieldVal("qitem", "i_coid", cid);
    JSSHOP.shared.setFrmFieldVal("qitem", "i_dadded", JSSHOP.getUnixTimeStamp());
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qitem"], "tmp_"));
    oi = getNuDBFnvp("qitem", 6, null, tmpFobj);
    JSSHOP.ui.setCBBClickClr(divQitems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
    //alert("doItemAdd: rq" + oi["rq"]); 
    // tmpVitemArr.push(JSSHOP.shared.getDynFrmVals(document["qitem"], "tmp_"));
    doQComm(oi["rq"], null, "preRenderTQBItems");
};


var doItemDelete = function(theElem, qid, theRid) {
    carr = tmpVitemArr[qid];
    narr = removeArrElement(tmpVitemArr, theRid);
    theElem.parentNode.parentNode.parentNode.deleteRow(theRid);
    tmpVitemArr = null;
    tmpVitemArr = narr;
    JSSHOP.ui.setCBBClickClr(divQitems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
    JSSHOP.shared.setFrmFieldVal("qitem", "i_dadded", JSSHOP.getUnixTimeStamp());
    JSSHOP.shared.setFrmFieldVal("qitem", "i_rtype", "0");
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where _id=?";
tmpDOs["wa"] = [qid];
tmpDOs["knvp"] = JSSHOP.shared.getFrmVals(document["qitem"], "nada");
oi = getNuDBFnvp("qcartitem",7,null,tmpDOs);
doQComm(oi["rq"], null, "renderNuTQBItems");
  //   procNuUIitem("qitem","i_rtype",qid,"0","renderNuTQBItems") ;
 
};
 
var doNuItemDelete = function() {
 
    if(confirm(stxt[42] + " " + stxt[19] + "?")) {
    procNuUIitem("qitem","i_rtype",JSSHOP.shared.getFrmFieldVal("qitem", "_id", 0),"0","doDelIRdir");
    }
};



var getPrdImgEditDv = function(tpIncrNPI) {
tpPIEDv = document.createElement('div');
try {
tsa = null;
tsa = {};
tsa = tmpPrdMediaArr[tpIncrNPI];
// alert(tsa.m_file + " :: " + JSSHOP.shared.getFrmFieldVal("qmedia", "m_file", 'nada'));
  // JSSHOP.ui.setCBBClickClr(tblCntEditItem,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
if(tsa.m_file.indexOf(".mp4") != -1) {
tmpRetStr = "<video controls><source type=\"video/mp4\" src=\"" + JSSHOP.shop.getPrdImgStr("prodpg", tsa.m_file) +  "\"></video>";

} else {
tmpRetStr = "<img src=\"" + JSSHOP.shop.getPrdImgStr("prodpg", tsa.m_file) +  "\" style=\"width: 100%\"  class=\"\" onclick=\"alert('" + JSSHOP.shared.getFrmFieldVal("qmedia", "_id", "0") + "');\">"
}
tmpRetStr += "<div class=\"dvTxtBtns\"><input type=\"button\" class=\"btnTxtLabel\" value=\"Set as Main\" onclick=\"javascript:doPrdMMain();\">   |   <input type=\"button\" class=\"btnTxtLabel\" value=\"Delete\" onclick=\"javascript:doPrdMDelete();\"></div>";
tmpRetStr += "<br><br>";
tpPIEDv.innerHTML = tmpRetStr;
return tpPIEDv;
} catch(e) {
alert("getPrdImgEditDv " + e);
tpPIEDv.innerHTML = "oops. something wrong..";
return tpPIEDv;
}
};

var doPrdMIcnClick = function(tpDPRC) {
ttta = tmpRPMArr[tpDPRC];
	if(ttta.m_file.indexOf(".mp4") != -1) {
		JSSHOP.shared.setFrmVals('qmedia', tmpRPMArr[tpDPRC], function() {  app.getPlayMPF(ttta.m_file) } );
		// JSSHOP.shared.setFrmVals('qmedia', tmpRPMArr[tpDPRC], function() { JSSHOP.ui.popAndAppendLbox(getPrdImgEditDv(tpDPRC),'y') } );

	} else {
		JSSHOP.shared.setFrmVals('qmedia', tmpRPMArr[tpDPRC], function() { JSSHOP.ui.popAndAppendLbox(getPrdImgEditDv(tpDPRC),'y') } );
	}
// JSSHOP.shared.setFrmFieldVal("qmedia", "m_dadded", JSSHOP.getUnixTimeStamp());
};



var dadido = function(aa,bb,cc) {
try {
if(bb.indexOf("_id") != -1) {
tmpRPMArr = null;
tmpRPMArr = [];
tmpRPMArr = JSON.parse(bb);
tmpPrdMediaArr = null;
tmpPrdMediaArr = [];
tmpPrdMediaArr = JSON.parse(bb); 
tmmpII = 0;
tmpRetStr = "<div>";
tmpAlen = tmpPrdMediaArr.length;
        while (tmmpII < tmpAlen) {
            tsa = tmpPrdMediaArr[tmmpII];

		tmpRetStr += "<img src=\"" + JSSHOP.shop.getPrdImgStr("prdmedia", tsa.m_file) +  "\" class=\"icnmnubtn crsrPointer\" onclick=\"doPrdMIcnClick(" + tmmpII + ");\">";   
            tmmpII++;
        }
tmpRetStr += "</div>";
JSSHOP.ui.setTinnerHTML("dvPrdMedia","");

JSSHOP.ui.setTinnerHTML("dvPrdMedia",tmpRetStr);
}
} catch(e) {
alert("rndrPrdMedia " + e);
}
};







var doSubCOpts = function(theArr) {
var len = theArr.length;
var iint = 0;
ts = null;
tmpSlct = document.getElementById("mod_i_catid");
while(iint < len) {
ts = theArr[iint];
if(ts.cat_pid == "0") {
} else {
JSSHOP.shared.addOptAtVal(tmpSlct, ts.cat_pid, ts._id, "      " + ts.cat_title, "txtClrDlg");
}
iint++;
}
JSSHOP.shared.setCurrSelectOpt(tmpSlct, catid);
};




 

var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {

doFUJSLoad();
// JSSHOP.ui.setTinnerHTML("tdTitleBar", currPgTitle);
 


// alert(JSON.stringify(arrAllForms["qitem"]));
JSSHOP.shared.setDynFrmVals(document["qitem"], "mod_");
showCurrImg();
// JSSHOP.shared.setFrmFieldVal("qmedia", "m_dadded", JSSHOP.getUnixTimeStamp());
JSSHOP.shop.getPrdMedia(catid,itemid,"dadido");

tfuh = nCurrFFieldOb();
tfuh.fid = "mod_i_title";
tfuh.fdv = stxt[10];
tfuh.fvr = ck_title;
tfuh.fve = stxt[1004];
tfuh.lid = "lbl_i_title"; 
tfuh.ltxt = stxt[10]; 
euiFFObjArr.push(tfuh);

tfid = nCurrFFieldOb();
tfid.fid = "mod_i_desc";
tfid.fdv = stxt[40];
tfid.fvr = ck_title;
tfid.fve = stxt[1003];
tfid.lid = "lbl_i_desc"; 
tfid.ltxt = stxt[40]; 
euiFFObjArr.push(tfid); 


tfip = nCurrFFieldOb();
tfip.fid = "mod_i_price_b";
tfip.fdv = "0.99";
tfip.fve = stxt[1003];
tfip.lid = "lbl_i_price_b"; 
tfip.ltxt = stxt[18]; 
euiFFObjArr.push(tfip); 

tfic = nCurrFFieldOb();
tfic.fid = "mod_i_catid";
tfic.fdv = stxt[16];
tfic.fve = stxt[1003];
tfic.lid = "lbl_i_catid"; 
tfic.ltxt = stxt[16]; 
euiFFObjArr.push(tfic); 

tfim = nCurrFFieldOb();
tfim.fid = "uploadBtn";
tfim.lid = "lbl_i_media";
tfim.ltxt = stxt[55];  
euiFFObjArr.push(tfim); 


tfsb = nCurrFFieldOb();
tfsb.fid = "btnEUsave";
tfsb.fty = "button";
tfsb.fcl = function() { doItemEdit() };

euiFFObjArr.push(tfsb);

JSSHOP.shared.initFrmComps(euiFFObjArr);


// JSSHOP.shared.initFrmComps(euiFFObjArr);

JSSHOP.shared.removeOptions(document.getElementById("mod_i_catid"));
// arrToFill = JSSHOP.shared.sort(currMenuArr, "cat_pid", "sortDesc");
// arrToFill = JSSHOP.shared.sort(currMenuArr, "cat_valb", "sortAsc");
tmpSlct = document.getElementById("mod_i_catid");
JSSHOP.shared.addCurrSelectOpt(tmpSlct, "0", "Top Main");
var len = currMenuArr.length;
var iint = 0;
var pcid = 0;
tstr = "";
arrToOptFill = currMenuArr;
while(iint < len) {
ts = arrToOptFill[iint];
if(ts.cat_pid == "0") { // add only main categories to list
JSSHOP.shared.addOptAtVal(tmpSlct, "noQvalue", ts._id, ts.cat_title, "txtClrWhite bkgdClrHdr");
// JSSHOP.shared.addCurrSelectOpt(tmpSlct, ts._id, ts.cat_title);
} 
iint++;
}

doSubCOpts(arrToOptFill);
};

 
