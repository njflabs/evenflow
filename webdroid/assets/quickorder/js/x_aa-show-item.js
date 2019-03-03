 


var tmpSQBArr = null;
var tmpVitemArr = null;
tmpSQBArr = [];
tmpVitemArr = [];
var tmpVindex = 0;
var tmpPrdMediaArr = null;
tmpPrdMediaArr = [];

var doRScb = function(bb) {
 // alert("doRScb: " + bb.rs);
dummyval = false;
};

var doRecentSeen = function(aRC, theRCResp, cRC) {
    JSSHOP.shared.setFrmFieldVal("qextras", "e_dadded", JSSHOP.getUnixTimeStamp());
    JSSHOP.shared.setFrmFieldVal("qextras", "e_uid", quid);
    JSSHOP.shared.setFrmFieldVal("qextras", "e_rtype", "10");
    JSSHOP.shared.setFrmFieldVal("qextras", "e_vala", itemid);
    JSSHOP.shared.setFrmFieldVal("qextras", "e_valb", cid);
    JSSHOP.shared.setFrmFieldVal("qextras", "e_valc", catid);
	currPgTitle = arrAllForms.qitem.v[0].i_title;
	JSSHOP.shared.setFrmFieldVal("qextras", "e_vald", currPgTitle);
    tmpFobj = null;
    tmpFobj = {};
    tmpQint = 6;



if(theRCResp.indexOf("_id") != -1) {
var arrRCToFill = null;
tRCs = null
arrRCToFill = JSON.parse(theRCResp);
tRCs = arrRCToFill[0];
    JSSHOP.shared.setFrmFieldVal("qextras", "e_valf", Math.round(tRCs.e_valf) + 1);
    tmpQint = 7;
} else { // not updateing times viewed, wll get it through javascript
    tmpFobj["knvp"] = JSSHOP.shared.getFrmVals(document["qextras"], "nada");
    oi = getNuDBFnvp("qextras", tmpQint, null, tmpFobj);



clearNuLclStrg("localStorage", "setLoadACTB");
qitac = nCurrCnxOb();
qitac["q"] = oi["rq"];
qitac["cb"] = "doRScb";
  

doNurQComm(qitac);
}
  //   doQComm(oi["rq"], null, "doRScb");
};





var checkRecentSeen = function() {

tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where e_uid=? and e_rtype=? and e_vala=?";
tmpDOs["wa"] = [quid,10,itemid];
oi = getNuDBFnvp("qextras",5,null,tmpDOs);
// alert(oi["rq"]);
doQComm(oi["rq"], null, "doRecentSeen");
 


};





 


/*
* renders all items in the category
*/

var renderNuItem = function(theElem, theResp, marble){
	try {
// alert("renderNuTQBItems: " + theResp);
 
var qts = JSON.parse(theResp); 
tStrHtml = JSSHOP.shop.getPrdsFullStr("prodpg", qts, null, null, null);
tStrHtml += "<img alt=\"imgldr\" height=\"5px\" width=\"5px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=\" onload=\"javascript:JSSHOP.shop.setCatPrdImgs('prodpg');\"  onerror=\"javascript:JSSHOP.shop.setCatPrdImgs('prodpg');\">";

newel = document.createElement('div');
 

newel.innerHTML = tStrHtml;
tmpTDQI = document.getElementById("dvItemCntn");
tmpTDQI.innerHTML = "";

tmpTDQI.appendChild(newel);

	} catch(e) {
	 alert("renderNuTQBItems: " + e);
	}
JSSHOP.shop.getPrdMedia(catid,itemid,"dadido");
checkRecentSeen();
};

 






var getPrdImgEditDv = function(tpIncrNPI) {
tpPIEDv = document.createElement('div');
try {
tsa = null;
tsa = {};
tsa = tmpPrdMediaArr[tpIncrNPI];
// alert(tsa.m_file + " :: " + JSSHOP.shared.getFrmFieldVal("qmedia", "m_file", 'nada'));
  // JSSHOP.ui.setCBBClickClr(tblCntEditItem,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});

tmpRetStr = "<img src=\"" + JSSHOP.shop.getPrdImgStr("prodpg", tsa.m_file) +  "\" style=\"width: 100%\"  class=\"\" onclick=\"alert('" + JSSHOP.shared.getFrmFieldVal("qmedia", "_id", "0") + "');\">"
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
tsa = null;
tsa = {};
tsa = tmpPrdMediaArr[tpDPRC];
isrc = JSSHOP.shop.getPrdImgStr("prodpg", tsa.m_file);
istr = "prodpg" + itemid;
document.getElementById(istr).src = isrc;
// JSSHOP.shared.setFrmVals('qmedia', tmpRPMArr[tpDPRC], function() { JSSHOP.ui.popAndAppendLbox(getPrdImgEditDv(tpDPRC),'y') } );

// JSSHOP.shared.setFrmVals('qmedia', tmpRPMArr[tpDPRC], function() { JSSHOP.ui.popAndAppendLbox(getPrdImgEditDv(tpDPRC),'y') } );
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




var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
tmpVitemArr = null;
tmpVitemArr = [];
tmpVitemArr = arrAllForms.qitem.v;
renderNuItem("a", JSON.stringify(tmpVitemArr), null);
};

 
  
