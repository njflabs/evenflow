var tmpSQBArr = null;
var tmpVitemArr = null;
tmpSQBArr = [];
tmpVitemArr = [];
var tmpVindex = 0;
var tmpImgCtr = 0;
currIContent = "no"; // ajax request to include the tplates/... file or not 
currCartTShow = "y";



 


var renderCatItems = function (theCArr) {
 try {
    var upRefs = arrUprefs["prfsSHOPuser"][0].scv;
}
catch (e) {
    var upRefs = "r";
}
try {
    var upPrixRef = arrUprefs["prfsSHOPuser"][0].scp;
}
catch (e) {
    var upPrixRef = "u";
}

    try {
        strHtml = "";
        tmpTDQI = document.getElementById("includedContent");
        tmpTDQI.innerHTML = "";
        // tmpPrfStr = "<div style=\"margin:16px;max-width:500px;margin: 0 auto;\" class=\"collection-item txtSmall txtBold rtable brdrClrDlg\"><div style=\"margin-left:20px\">Recent:<br>" + currRcntActHstr.replace(/::/g, "<br>") + "</div></div>";
        // tmpPrfStr += "<div style=\"margin:16px;max-width:500px;margin: 0 auto;\" class=\"collection-item txtSmall txtBold rtable brdrClrDlg\">" + JSSHOP.shop.renderNuCartItems("m", "n", 4) + "</div>";
	  tmpPrfStr = "<div style=\"margin-bottom:0px;margin:0 auto;max-width: 75%\" align=\"right\">";
        tmpDVlmore = document.createElement('div');

            if (upRefs == "r") {
                // tmpPrfStr += "<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','g');renderAgn();\"><strong><i class=\"material-icons bigtable brdrClrHdr\" alt=\"grid_on\" title=\"grid_on\" style=\"margin-right: 15px;font-size:32px;\">&#xe3ec;</i></strong></a>";
            }
            else {
                // tmpPrfStr += "<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','r');renderAgn();\"><i class=\"material-icons bigtable brdrClrHdr\" alt=\"group\" title=\"group\" style=\"margin-right: 15px;font-size:32px;\">&#xe240;</i></a>";
            }


        tmpDVlpr = document.createElement('span');
        if (upPrixRef == "a") {
           // tmpPrfStr += "<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scp','d');renderPrix('a');\"><i class=\"material-icons bigtable brdrClrHdr\" alt=\"group\" title=\"group\" style=\"margin-right: 15px;font-size:32px;\">&#xe25c;</i></a>";
        }
        else {
           // tmpPrfStr += "<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scp','a');renderPrix('d');\"><i class=\"material-icons bigtable brdrClrHdr\" alt=\"group\" title=\"group\" style=\"margin-right: 15px;font-size:32px;\">&#xe227;</i></a>";
        }
            tmpPrfStr += "</div>";
         tmpDVlmore.innerHTML = tmpPrfStr;
 
           tmpTDQI.appendChild(tmpDVlmore);


        tStrHtml = JSSHOP.shop.getPrdsFullStr("cat", theCArr, null, null, null);
	  // tStrHtml += "<img alt=\"imgldr\" height=\"5px\" width=\"5px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=\" onload=\"javascript:JSSHOP.shop.setCatPrdImgs('cat');\"  onerror=\"javascript:JSSHOP.shop.setCatPrdImgs('cat');\">";

        newel = document.createElement('div');
 
        newel.innerHTML = tStrHtml;
        tmpTDQI.appendChild(newel);

	   // tmpPGstr = JSSHOP.shop.getDefaultPageNav(tmpVitemArr.length, currProdsPPg, currPgIndex);
	  if(tmpVitemArr.length  > currProdsPPg) {
	// alert(tmpVitemArr.length);
         tmpPtotal = Math.round(tmpVitemArr.length / currProdsPPg);
	   if(tmpVitemArr.length > (tmpPtotal * currProdsPPg)) { tmpPtotal = tmpPtotal + 1; }
	   tmpPGstr = JSSHOP.shop.getDefaultPageNav(currPgIndex, tmpPtotal);
 
        newel = document.createElement('div');
        newel.innerHTML = tmpPGstr;
        tmpTDQI.appendChild(newel);
	  }
 
	// document.getElementById("dvPagination").innerHTML = tmpPGstr;
    }
    catch (e) {
        alert("renderNuTQBItems: " + e);
    }
};

 
var renderPrix = function (tmpStrAD) {
    tmpCnstStr = "sortDesc";
    if (tmpStrAD == "d") {
        tmpCnstStr = "sortDesc";
    }
    else {
        tmpCnstStr = "sortAsc";
    }
    nuVarr = JSSHOP.shared.sort(tmpVitemArr, "i_price_b", tmpCnstStr);
    renderCatItems(nuVarr);
};
var renderAgn = function () {
    renderCatItems(getCurrItemsArr());
};

 

var loadCatChunk = function (theCurrPage) {
        tmpTDQI = document.getElementById("includedContent");
        tmpTDQI.innerHTML = "";
	  currPgIndex = theCurrPage - 1;
	  // if((currPgIndex * currProdsPPg) > tmpVitemArr.length) {intStart = 
	  
        JSSHOP.shop.setCurrItemsArr(tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
        renderCatItems(tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
};


var loadCatItems = function (theResp) {
        tmpTDQI = document.getElementById("includedContent");
        tmpTDQI.innerHTML = "";
	 //  alert("loadCatItems:" + JSON.stringify(theResp));
    if(theResp.rs.indexOf("_id") != -1) {
        // alert("loadCatItems: " + theResp);
        tmpVitemArr = null;
        tmpVitemArr = JSON.parse(theResp.rs);
        JSSHOP.shop.setCurrMItemsArr(tmpVitemArr);


    strUCPID = "";
    strUCPTtl = "";
    strCatID = "";
    strCatName = "";
        var len = tmpVitemArr.length;
        tstr = "";
        iint = 0;
        while (iint < len) {
            ts = tmpVitemArr [iint];
            strUCPID += "ucp:ep:" + iint + "|";
            strUCPTtl += ts.i_title + "|";
            iint++;
        }
        tmpCINstr = currACTBstr.split("::");
        tstrCatID = tmpCINstr[0];
        tstrCatName = tmpCINstr[1];
        strCatID += strUCPID + tstrCatID;
        strCatName += strUCPTtl + tstrCatName;
        // ijUFeedSearch.value = "hehahs";
        strFArr = strCatID + "::" + strCatName;
        actbSearch = loadListACTB(strFArr, "ijUFeedSearch");


        JSSHOP.shop.setCurrItemsArr(tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
        renderCatItems(tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
    } else {
        strHtml = "<div class=\"txtBold txtClrHdr\">" + stxt[33] + "</div>";
		document.getElementById("includedContent").innerHTML = strHtml;
    }
};
 

var setRecentPlugs = function(thePlugObj) {
currRecentPlugs = thePlugObj.rs;
alert("setRecentPlugs: " + currRecentPlugs);
currPlugsArr = JSON.parse(currRecentPlugs);
JSSHOP.cookies.setCookie("recentPlugs",LZString.compressToEncodedURIComponent(JSON.stringify(currPlugsArr)),"30","","","");
loadCatItems(thePlugObj);
};

var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function () {

if((JSSHOP.cookies.getCookie("recentPlugs") !== null) && (JSSHOP.cookies.getCookie("recentPlugs").length > 5) && (JSSHOP.cookies.getCookie("recentPlugs") !== "noQvalue")){
currRecentPlugs = LZString.decompressFromEncodedURIComponent(JSSHOP.cookies.getCookie("recentPlugs"));
currPlugsArr = JSON.parse(currRecentPlugs);
newPlugObj = null;
newPlugObj = {};
newPlugObj["rs"] = currRecentPlugs;
// alert("loading from plug");
loadCatItems(newPlugObj);
} else { 
    includedContent.className = "";
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["ws"] = "where _id > ?";
    tmpDOs["wa"] = [0];
    tmpDOs["l"] = "12";
    // tmpDOs["o"] = "RAND()";
    oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
    currRQtable = "qitem";
    currRQstr = oi["rq"];
    ccheD = 123;
tac = nCurrCnxOb();
tac["q"] = oi["rq"];
tac["cb"] = "setRecentPlugs";
doNurQComm(tac);
}
};
 
 
