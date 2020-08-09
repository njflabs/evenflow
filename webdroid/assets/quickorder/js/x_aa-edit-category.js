

var tmpSQBArr = null;
var tmpVitemArr = null;
tmpSQBArr = [];
tmpVitemArr = [];
var tmpVindex = 0;
var isCatUpdd = "m";

currPgTitle = stxt[31] + " " + stxt[16];
document.title = currPgTitle; // set the page title 

var euiFFObjArr = null;
var euiFFObjArr = [];

currProdsPPg = 15; // override from X_booter - pagination  - number of items per page



var doCBcatudate = function(a, b, c) {
	isCatUpdd = "m";
        // alert("cat has been updated");
};
var runCatUpdate = function() {
    tmpCUtstmp = JSSHOP.getUnixTimeStamp();
    JSSHOP.shared.setFrmFieldVal("qcat", "cat_dadded", tmpCUtstmp); 
    procNuUIitem("qcat","cat_dadded",JSSHOP.shared.getFrmFieldVal("qcat", "_id", 0),tmpCUtstmp,"doCBcatudate");


};
var doCatUpdate = function() {
    if(isCatUpdd == "n") {
	setTimeout("runCatUpdate()", 1000);
    } 

};

var doneImgInsrt = function(a,b,c) {
try {
doItemEdit();
} catch(e) {
alert(e);
}
};

var finishImgInsrt = function(theImg) {
try {
JSSHOP.shared.setFrmFieldVal("qmedia", "m_file", theImg); 
JSSHOP.shared.setFrmFieldVal("qmedia", "m_pid", JSSHOP.shared.getFrmFieldVal("qitem","_id",0)); 
JSSHOP.shared.setFrmFieldVal("qmedia", "m_dadded", JSSHOP.getUnixTimeStamp()); 
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

JSSHOP.shared.setFrmFieldVal("qitem","i_img",theImg);
document.getElementById("mod_i_img").value = "rem-" + theImg;
JSSHOP.shared.setFrmFieldVal("qmedia", "m_uid", quid);
JSSHOP.shared.setFrmFieldVal("qmedia", "m_coid", cid);
JSSHOP.shared.setFrmFieldVal("qmedia", "m_catid", catid);
JSSHOP.shared.setFrmFieldVal("qmedia", "m_pid", catid);


tIUID = JSSHOP.shared.getFrmFieldVal("qitem","_id",0);

tImgstr = "images/pimgs/" + theImg; 
pImgid = "prodI" + tIUID;

if(isPhP == "no") {
tImgstr = "data:image/png;base64, " + theImg;
}

document.getElementById(pImgid).src = tImgstr;
finishImgInsrt(theImg);
} catch(e) {
alert(e);
}

};


var appFnshImgUload = function(theUri, theImg) {
try {
tImgstr = "images/pimgs/" + theImg; 
tIUID = JSSHOP.shared.getFrmFieldVal("qitem","_id",0);
pImgid = "prodI" + tIUID;

document.getElementById("fldChallArray").value = theUri;
tmpUri = document.getElementById("fldChallArray").value;
// JSSHOP.shared.setFrmFieldVal("qitem","i_vala",tmpUri);
// document.getElementById("mod_i_vala").value = tmpUri;

if(tmpUri.indexOf(".gif") != -1) {
JSSHOP.shared.setFrmFieldVal("qitem","i_vala","lcl-" + tmpUri);
document.getElementById("mod_i_vala").value = "lcl-" + tmpUri;
} else if(tmpUri.indexOf(".mp4") != -1) {
JSSHOP.shared.setFrmFieldVal("qitem","i_valb","lcl-" + tmpUri);
document.getElementById("mod_i_valb").value = "lcl-" + tmpUri;
} else {
JSSHOP.shared.setFrmFieldVal("qitem","i_img","lcl-" + tmpUri);
document.getElementById("mod_i_img").value = "lcl-" + tmpUri;
}
document.getElementById("fldChallArray").value = theImg;
tmpImg = document.getElementById("fldChallArray").value;
tImgstr = "data:image/jpeg;base64, " + tmpImg;
// document.getElementById(pImgid).src = tImgstr;
// JSSHOP.shared.setFrmFieldVal("qitem","i_img",tmpImg);
// document.getElementById("mod_i_img").value = tmpImg;

doItemEdit();
} catch(e) {
alert("appFnshImgUload: " + e);
}
};




var doFUJSLoad = function() {
JSSHOP.shared.setFrmFieldVal("qmedia", "m_dadded", JSSHOP.getUnixTimeStamp());
// JSSHOP.ui.addEvent(btn, "click", function() { JSSHOP.shared.setFrmFieldVal("qmedia", "m_dadded", JSSHOP.getUnixTimeStamp()) });

if(isJApp == "no") { 

JSSHOP.loadScript("js/sau.js", JSSHOP.checkLoader,"js");

} else {
// btn = document.getElementById('uploadBtn');
// JSSHOP.ui.addEvent(btn, "click", function() { JSSHOP.jndroid.doPagePopUp("quickorder/app_imgselect.html", "noQvalue") });
tstr = JSSHOP.ui.doDefBtn("Barcode Scan",app.doBarCodeScan());
JSSHOP.ui.setTinnerHTML("dvAppBCodeBtn", "nada");
}
};








var doItemEdit = function() {
    JSSHOP.ui.closeLbox();
    tmpFarr = null;
    tmpFarr = JSSHOP.shared.setArrVals(tmpVitemArr, tmpVindex, JSSHOP.shared.getDynFrmVals(document["qitem"], "mod_"));

    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = tmpFarr;
    JSSHOP.shared.setFrmFieldVal("qitem", "i_dadded", JSSHOP.getUnixTimeStamp()); 
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qitem"], "mod_"));
    tmpFobj["ws"] = "where _id=?";
    tmpFobj["wa"] = [JSSHOP.shared.getFrmFieldVal("qitem","_id","0")];
    oi = getNuDBFnvp("qitem", 7, null, tmpFobj);
    // JSSHOP.ui.setCBBClickClr(divQitems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
    JSSHOP.ui.setCBBClickClr(divQitems,'rtable brdrclrHdr bkgdClrDlg','txtClrHdr bkgdClrWhite', function(){void(0)});

   // alert("oiRQ " + oi["rq"]); 
   doQComm(oi["rq"], null, "renderNuTQBItems"); 
 
     // doQComm(oi["rq"], null, "preRenderTQBItems");
 if(isCatUpdd == "m") {
     isCatUpdd = "n";
}
};

var fnshRespItemAdd = function(a, b, c) {
    if(b.indexOf("_id") != -1) {
    ttddA =  JSON.parse(b);
    tmpIva = ttddA[0]._id;
document.location.href = "index.html?pid=aa-edit-item&cid=" + cid + "&catid=" + catid + "&itemid=" + tmpIva + "&t=" + JSSHOP.getUnixTimeStamp();
}
    // renderTQBItems();
};


var doRespItemAdd = function(a, b, c) {
    // alert("pre: " + JSON.stringify(b));

    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["l"] = "1";
    tmpDOs["ws"] = "where i_pid=? and i_rtype=?";
    tmpDOs["wa"] = [catid,"5"];
    oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
    doQComm(oi["rq"], null, "fnshRespItemAdd");
    // renderTQBItems();
};


var doItemAdd = function(t, am) {
	// JSSHOP.shared.setDynFrmVals(document["qitem"], "clear");
    JSSHOP.shared.setFrmFieldVal("qitem", "i_dadded", JSSHOP.getUnixTimeStamp());
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qitem"], "tmp_"));
    oi = getNuDBFnvp("qitem", 6, null, tmpFobj);
    // JSSHOP.ui.setCBBClickClr(divQitems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
    //alert("doItemAdd: rq" + oi["rq"]); 
    // tmpVitemArr.push(JSSHOP.shared.getDynFrmVals(document["qitem"], "tmp_"));
   // doQComm(oi["rq"], null, "doRespItemAdd");

    doQComm(oi["rq"], "dobble", "preRenderTQBItems");
 if(isCatUpdd == "m") {
isCatUpdd = "n";
}
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
    carr = tmpVitemArr[tmpVindex];
    narr = removeArrElement(tmpVitemArr, tmpVindex);
/*
    theElem = document.getElementById("tdIedit" + tmpVindex);
    theElem.parentNode.parentNode.parentNode.deleteRow(tmpVindex);
*/
    tmpVitemArr = null;
    tmpVitemArr = narr;
    JSSHOP.ui.setCBBClickClr(divQitems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});

    procNuUIitem("qitem","i_rtype",JSSHOP.shared.getFrmFieldVal("qitem", "_id", 0),"0","renderNuTQBItems");
 if(isCatUpdd == "m") {
 isCatUpdd = "n";
 }
};

















var doN = function(a,b,c) {
// document.location.href = document.location.href + "&t=" + JSSHOP.getUnixTimeStamp();
doItemEdit();
};






var showCurrImg = function() {
try{
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
} catch(e) {
}
};


 
var fnishMdlLoad = function(theElem, theResp, marble) {
tmpFelem = document.getElementById(theElem);
tmpFelem.innerHTML = theResp;
JSSHOP.shared.setDynFrmVals(document['qitem'], 'mod_');
xae = tmpFelem.getElementsByTagName("ti");
var iint = 0;
while(iint < xae.length) {
nuDW(xae[iint]);
iint++;
}
};


/*
* opens up the item popup
*/

var doItemEditPop = function() {
    loadNurJSModal("tplates/aa-mod-edit-item.html?tt=" + JSSHOP.getUnixTimeStamp(), "noQvalue", fnishMdlLoad);
}
var doQIedit = function(iei) {
    tmpVindex = iei;
JSSHOP.shop.setCurrItemArr(tmpVitemArr[iei]);
    JSSHOP.shared.setFrmVals("qitem",tmpVitemArr[iei],function() {doItemEditPop()});
};

var doQIMeta = function() {
    tmpMetaObj = {};
    tmpMetaObj.qco = arrAllForms.qco.v[0];
    tmpMetaObj.qitem = JSSHOP.shop.getCurrItemArr();
    return JSON.stringify(tmpMetaObj);
};


/*
* returns the html link for product editing popup
*/
var getQIstr = function(rid, tmprhtml) {

    strTret = "<div style=\"margin-top: 10px;\" onclick=\"JSSHOP.ui.setCBBClickClr(this,'crsrPointer brdrClrHdr','crsrPointer brdrNone', function(){doQIedit(" + rid + ");});\" class=\"crsrPointer brdrNone txtBig\">";
    strTret += tmprhtml + "</div>";

    return strTret;
};


/*
* renders all items in the category
*/

var renderNuTQBItems = function(a,b,c) {
	doCatUpdate();
	// alert("rendering: " + JSON.stringify(b));
    try { spinner.stop(); } catch(e) { alert(e); }

    tmpTDQI = document.getElementById("divQitems");
    tmpTDQI.innerHTML = "";
    tstr = "";
    iint = 0;
 	tmpVdumArr = tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg);
    len = tmpVdumArr.length;
    strHtml = "";
    while (iint < len) {
        ts = tmpVdumArr[iint];
        strRecID = ts._id;
        strRecType = 50;
        if (ts._id > 0) {
     strHtml += "<div class=\"brdrClrNrml\" style=\"margin-bottom: 10px;\">";
            strPriceHtml = "";
		if(ts.i_img) {
		 tmpIstrI = JSSHOP.shop.getPrdImgStr("editcat",ts.i_img);
		}	
            strImgDsct = "<img id=\"prodI" + ts._id + "\" src=\"" + tmpIstrI + "\"  class=\"slmtable brdrClrDlg icndbtn\" style=\"align: center;text-align:center;margin:6px;\" align=\"absmiddle\">";

            strPriceHtml = "<span class=\"txtBig txtClrRed txtBold\">" + ts.i_price_b + "</span>";
            strPriceHtml += "<input type=\"hidden\" id=\"prd" + 5 + iint + "\" value=\"\">";
            strHtml += getQIstr(iint, strImgDsct + ts.i_title + " " + strPriceHtml);
            strHtml += "<div style=\"text-align: right;\" class=\"cls-form-dv  bkgdClrNrml\" id=\"tdIedit" + iint + "\">";
 
      	strcHtml = "<a href=\"index.html?pid=aa-edit-item&itemid=" + ts._id + "&cid=" + cid + "&catid=" + ts.i_catid + "\"  class=\"txtSmall txtDecorNone\"><span style=\"vertical-align:super\" class=\"txtBold\">" + stxt[31] + "</span></a>";
      	strcHtml += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"index.html?pid=aa-show-item&itemid=" + ts._id + "&cid=" + cid + "&catid=" + ts.i_catid + "\"  class=\"txtSmall txtDecorNone\"><span style=\"vertical-align:super\" class=\"txtBold\">" + stxt[53] + "</span></a>";

      //       strHtml += "<td  onclick=\"javascript:doItemDelete(this," + ts._id + "," + iint + ");\"><img src=\"images/delete_r.gif\" class=\"icnsmlbtn brdrClrWhite\">";
            strHtml += strcHtml + "</div>";
     
           //  strHtml += "<div style=\" margin: 8px; border-bottom: 1px dashed #D1D5D1;\">"; 
	      strHtml += "</div>";

	      strHtml += "</div>";

        }
        iint++;
    }

 

    if (tmpVitemArr[0]) {
        strTHhtml = "<th style=\"text-align: center\" align=\"center\"><img src=\"images/picture_go.png\"></th><th>" + stxt[19] + "</th><th>X</th>";
    	  tmpFstr = getTblSortStr(strTHhtml, strHtml);
 

    } else {
        strHtml += "<b>" + stxt[508] + "</b><br>" + stxt[509];
 
    }
          tmpPtotal = Math.round(tmpVitemArr.length / currProdsPPg);
	   if(tmpVitemArr.length > (tmpPtotal * currProdsPPg)) { tmpPtotal = tmpPtotal + 1; }
	   tmpPGstr = JSSHOP.shop.getDefaultPageNav(currPgIndex, tmpPtotal);
        if(tmpPGstr == 1) {
	  } else {
 
        strHtml += tmpPGstr;
 
	  }


    tmpTDQI.innerHTML = strHtml;
    strHtml = null;
    tmpFstr = null;

if(getViewportWidth() > 480) {
} else {
scrollToElement("tdTitleBar");
}
if(a == "dobble") {
JSSHOP.ui.setCBBClickClr(divQitems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
} 
};


var loadCatChunk = function (theCurrPage) {
	  currPgIndex = theCurrPage-1;
 
        JSSHOP.shop.setCurrItemsArr(tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
        renderNuTQBItems("qq","dddb",tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
};

var renderDBis = function(theElem, theResp, marble) {
     // alert("renderDBis: " + theResp);
    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = JSON.parse(theResp);
    renderNuTQBItems(theElem,"dddb","dddc");
};


var preRenderTQBItems = function(a, b, c) {
    // alert("pre: " + JSON.stringify(b));

    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["ws"] = "where i_pid=? and i_rtype=?";
    tmpDOs["wa"] = [catid,"5"];
    oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
    doQComm(oi["rq"], a, "renderDBis");
    // renderTQBItems();
};


 





var doCBcatedit = function(a, b, c) {
      //   JSSHOP.ui.setCBBClickClr(mmn,'cls_button cls_button-medium brdrClrDlg txtClrHdr','nanimenu', function(){ doMMenuLd() });
         JSSHOP.ui.setCBBClickClr(mmn,'cls_button cls_button-medium brdrClrDlg txtClrHdr','nanimenu', function(){ document.location.href=document.location.href + '&fc=y'  });
};

var doCatTitleEdit = function() {
JSSHOP.shared.setFrmFieldVal("qcat", "cat_dadded", JSSHOP.getUnixTimeStamp()); 
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where _id=?";
tmpDOs["wa"] = [catid];
tmpDOs["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qcat"], "tmp_"));
oi = getNuDBFnvp("qcat",7,null,tmpDOs);
doQComm(oi["rq"], null, "doCBcatedit");
};

 


/* overide the done filling def forms in  x_booter fnishCoForm etc., */
 
var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
if((quid == 0) || (quid == "noQvalue")) {
JSSHOP.ajax.doNuAjaxPipe("includedContent", "tplates/login.html", pfRet);
JSSHOP.loadScript("js/x_login.js", JSSHOP.checkLoader,"js");
return;
}

JSSHOP.shared.setFrmFieldVal("qitem", "i_uid", quid);
JSSHOP.shared.setFrmFieldVal("qitem", "i_coid", cid);
JSSHOP.shared.setFrmFieldVal("qitem", "i_pid", catid);
JSSHOP.shared.setFrmFieldVal("qitem", "i_catid", catid);
JSSHOP.shared.setFrmFieldVal("qitem", "i_desc", "....");
// JSSHOP.ui.setTinnerHTML("tdTitleBar", currPgTitle);


// JSSHOP.ui.setTinnerHTML("tdCoTitle", JSSHOP.shared.getFieldVal("c_title", "theCo"));

 

tfim = nCurrFFieldOb();
tfim.fid = "tmp_cat_title";
tfim.fdv = stxt[16];
tfim.fda = "y";
// tfim.lid = "lbl_cat_title";
// tfim.ltxt = stxt[16];  
euiFFObjArr.push(tfim); 


tfia = nCurrFFieldOb();
tfia.fid = "tmp_i_title";
tfia.fdv = stxt[3001];
tfim.fda = "y";
tfia.lid = "lbl_i_title";
tfia.ltxt = stxt[17];  


tfia.fda = "y";  
euiFFObjArr.push(tfia);


JSSHOP.shared.initFrmComps(euiFFObjArr);

tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where i_uid=? and i_coid=? and i_catid=?";
tmpDOs["wa"] = [quid,cid,catid];
oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
currRQtable = "qitem";
currRQstr = oi["rq"];
doQComm(oi["rq"], null, "renderDBis");
JSSHOP.loadScript("js/x_aqr.js", JSSHOP.checkLoader,"js");
JSSHOP.shared.setFieldVal("tmp_cat_title", document.getElementById("cat_title").value, "....");

};
