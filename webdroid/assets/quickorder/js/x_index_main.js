var tmpSQBArr = null;
var tmpVitemArr = null;
tmpSQBArr = [];
tmpVitemArr = [];
var tmpVindex = 0;
var tmpImgCtr = 0;




 


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
        tmpTDQI = document.getElementById("dvQitems");
        tmpTDQI.innerHTML = "";
	  tmpPrfStr = "<div style=\"margin-bottom:0px;\" align=\"right\">";
        tmpDVlmore = document.createElement('div');

            if (upRefs == "r") {
                tmpPrfStr += "[<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','g');renderAgn();\">grid</a>]";
            }
            else {
                tmpPrfStr += "[<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','r');renderAgn();\">row</a>]";
            }


        tmpDVlpr = document.createElement('span');
        if (upPrixRef == "a") {
            tmpPrfStr += "   [<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scp','d');renderPrix('a');\">price</a>]";
        }
        else {
            tmpPrfStr += "    [<a href=\"javascript:JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scp','a');renderPrix('d');\">PRICE</a>]";
        }
            tmpPrfStr += "</div>";
         tmpDVlmore.innerHTML = tmpPrfStr;
 
            tmpTDQI.appendChild(tmpDVlmore);
        tStrHtml = JSSHOP.shop.getPrdsFullStr("cat", theCArr, null, null, null);
	  tStrHtml += "<img alt=\"imgldr\" height=\"5px\" width=\"5px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=\" onload=\"javascript:JSSHOP.shop.setCatPrdImgs('cat');\"  onerror=\"javascript:JSSHOP.shop.setCatPrdImgs('cat');\">";

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
        tmpTDQI = document.getElementById("dvQitems");
        tmpTDQI.innerHTML = "";
	  currPgIndex = theCurrPage - 1;
	  // if((currPgIndex * currProdsPPg) > tmpVitemArr.length) {intStart = 
	  
        JSSHOP.shop.setCurrItemsArr(tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
        renderCatItems(tmpVitemArr.slice(currPgIndex * currProdsPPg, (currPgIndex * currProdsPPg) + currProdsPPg));
};


var loadCatItems = function (theResp) {
        tmpTDQI = document.getElementById("dvQitems");
        tmpTDQI.innerHTML = "";
	   // alert("loadCatItems:" + JSON.stringify(theResp));
    if(theResp.rs.indexOf("_id") != -1) {
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
		document.getElementById("dvQitems").innerHTML = strHtml;
    }
};
 
 var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function () {
    includedContent.className = "";
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["ws"] = "where i_rtype=?";
    tmpDOs["wa"] = ["5"];
    tmpDOs["l"] = "20";
    oi = getNuDBFnvp("qitem", 5, null, tmpDOs);
    currRQtable = "qitem";
    currRQstr = oi["rq"];
    ccheD = 123;




 

tac = nCurrCnxOb();
 
tac["q"] = oi["rq"];
tac["cb"] = "loadCatItems";
 
doNurQComm(tac);

 
};
 
 
