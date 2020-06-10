var tmpSQBArr = null;
var tmpVitemArr = null;
tmpSQBArr = [];
tmpVitemArr = [];
var tmpVindex = 0;


var sw = "search"; 
tmpUrl = getCurrUrl();

if(tmpUrl == "noQvalue") {
} else {
newArr = JSSHOP.shared.urlToArray(tmpUrl);
if(newArr.sw){
sw  = newArr.sw;
}
}






var doRSrchb = function(bb) {
// alert("doRSrchb: " + bb);
};

var doRecentSearch = function(aRC, theRCResp, cRC) {

if(theRCResp.indexOf("_id") != -1) {
var arrToFill = null;
arrToFill = JSON.parse(theRCResp);
ts = arrToFill[0];
procNuUIitem("qextras","e_valf",ts._id,Math.round(ts.e_valf) + 1,"doRSrchb");

} else {

 

    JSSHOP.shared.setFrmFieldVal("qextras", "e_dadded", JSSHOP.getUnixTimeStamp());
    JSSHOP.shared.setFrmFieldVal("qextras", "e_uid", quid);
    JSSHOP.shared.setFrmFieldVal("qextras", "e_rtype", "11");
    JSSHOP.shared.setFrmFieldVal("qextras", "e_vala", sw);
    JSSHOP.shared.setFrmFieldVal("qextras", "e_valb", cid);
    JSSHOP.shared.setFrmFieldVal("qextras", "e_valc", "");
    JSSHOP.shared.setFrmFieldVal("qextras", "e_vald", "");
    JSSHOP.shared.setFrmFieldVal("qextras", "e_valf", "1");
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getFrmVals(document["qextras"], "nada");
    oi = getNuDBFnvp("qextras", 6, null, tmpFobj);


clearNuLclStrg("localStorage", "setLoadACTB");
qitac = nCurrCnxOb();
qitac["q"] = oi["rq"];
qitac["cb"] = "doRSrchb";
  

doNurQComm(qitac);


   //  doQComm(oi["rq"], null, "doRSrchb");
}

};





var checkRecentSearch = function() {

tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where e_uid=? and e_rtype=? and e_vala=?";
tmpDOs["wa"] = [quid,11,sw];
oi = getNuDBFnvp("qextras",5,null,tmpDOs);
// alert(oi["rq"]);
doQComm(oi["rq"], null, "doRecentSearch");
 


};





var rndrNuSrchItms = function(theElem, theResp, marble){
// alert("rndrNuSrchItms: " + theResp);



try {
tmpTDQI = document.getElementById("dvQitems");

if(theResp.indexOf("_id") != -1) {


        setCurrMItemsArr(JSON.parse(theResp));
        setCurrItemsArr(JSON.parse(theResp));



tmpTDQI.innerHTML = "";
tStrHtml = JSSHOP.shop.getPrdsFullStr("srch", JSON.parse(theResp), null, null, null);
// checkRecentSearch();


} else { // response has no items
        tStrHtml = "<div class=\"txtBold txtClrHdr\">Search of " + sw + "  returned 0 results</div>";

}

newel = document.createElement('div');
newel.innerHTML = tStrHtml;
tmpTDQI.appendChild(newel);

	} catch(e) {
	 alert("rndrNuSrchItms aas: " + theResp);
	}


};


var renderPrix = function(tmpStrAD) {
	tmpCnstStr = "sortDesc";
	if(tmpStrAD == "u") {
	tmpCnstStr = "sortDesc";
	} else {
	tmpCnstStr = "sortAsc";
	}
	nuVarr = JSSHOP.shared.sort(tmpVitemArr, "i_price_b", tmpCnstStr);
    renderNuTQBItems('a', JSON.stringify(nuVarr), 'c');
};
var renderAgn = function() {
    renderNuTQBItems('a', JSON.stringify(tmpVitemArr), 'c');
};



 
var rndrNuScanItms = function(theElem, theBCResp, marble){
try {
if(theBCResp.indexOf("_id") != -1) {
var tbcret = JSON.parse(theBCResp);
strLML = "index.html?pid=aa-edit-item&itemid=" + tbcret[0]._id + "&cid=" + tbcret[0].i_coid + "&catid=" + tbcret[0].i_catid;
document.location.href=strLML;
} else {
tmpTDQI = document.getElementById("dvQitems");
tStrHtml = "<div class=\"txtBold txtClrHdr\">Scan of " + sw + "  returned 0 results</div>";
newel = document.createElement('div');
newel.innerHTML = tStrHtml;
tmpTDQI.appendChild(newel);
}
 
	} catch(e) {
	 alert("rndrNuScanItms: " + e);
	}

};



 

 


/* overide the done filling def forms in  x_booter fnishCoForm etc., */
 
var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
// alert("fnishCntLoad");

includedContent.className = "";

    tmpDOs = null;
    tmpDOs = {};

if(sw.indexOf("barcode:") != -1) {
tmpBCstr = sw.replace("barcode:","");
tmpDOs["ws"] = "where i_bcode=?";
tmpDOs["wa"] = [tmpBCstr];
oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
doQComm(oi["rq"], null, "rndrNuScanItms");
} else {
st1 = sw;
st2 = sw + "%";
st3 = "%" + sw;
st4 = "%" + sw + "%";
tmpDOs["ws"] = "where i_rtype=? and (i_title=? or i_title like ? or i_title like ? or i_title like ?)";
tmpDOs["wa"] = ["5",st1, st2,st3,st4];
oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
doQComm(oi["rq"], null, "rndrNuSrchItms");
}
};


 
