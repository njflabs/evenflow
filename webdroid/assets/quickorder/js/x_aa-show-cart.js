currPgTitle = stxt[47];
document.title = currPgTitle; 
var euiFFObjArr = null;
var euiFFObjArr = [];

var tmpSQBArr = [];
var tmpVitemArr = [];
var tmpVindex = 0;

var showCurrImg = function() {
tmpIvaval = ci_vala.value;
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




var doCBciqtyedit = function(a, b, c) {
        JSSHOP.ui.setCBBClickClr(mmn,'cls_button cls_button-medium brdrClrDlg txtClrHdr','nanimenu', function(){ doMMenuLd() });
};

var doCIQtyEdit = function(doCBciqtyedit) {
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_dadded", JSSHOP.getUnixTimeStamp()); 
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where _id=?";
tmpDOs["wa"] = [qcid];
tmpDOs["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qcartitem"], "tmp_"));
oi = getNuDBFnvp("qcat",7,null,tmpDOs);
doQComm(oi["rq"], null, "doCBcatedit");
};



var doNuItemDelete = function() {
 
    if(confirm(stxt[42] + " " + stxt[19] + "?")) {
    JSSHOP.ui.setCBBClickClr(dvCartItems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){void(0)});
    procNuUIitem("qcartitem","ci_rtype",JSSHOP.shared.getFrmFieldVal("qcartitem", "_id", 0),"0","reloadCartItems");
    }
};

var doNuItemQtyEdit = function(tciID, tciCQ, tciOQ) {
 
    if(tciCQ !== tciOQ) {
    JSSHOP.ui.setCBBClickClr(dvCartItems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){void(0)});
    procNuUIitem("qcartitem","ci_cartqty",tciID,tciCQ,"reloadCartItems");
    }
};
/*
* opens up the item popup
*/

var doItemCartPop = function() {
    loadJSModal("tplates/aa-mod-edit-cartitem.html");
}
var doCIedit = function(iei) {
    tmpVindex = iei;
	 
    JSSHOP.shared.setFrmVals("qcartitem",tmpVitemArr[iei],function() {doItemCartPop()});
};

/*
* returns the html link for product editing popup
*/
var getCIstr = function(rid, tmprhtml) {
    strTret = "<div>";
    strTret += tmprhtml + "</div>";

    return strTret;
};


/*
* renders all items in the category
*/

var renderNuTQBItems = function(a,b,c) {
	// alert("rendering: " + JSON.stringify(tmpVitemArr));
    // try { spinner.stop(); } catch(e) { alert(e); }
    tstr = "";
    iint = 0;
    ppint = 1;
	 cartTtl = 0;
    len = tmpVitemArr.length;
    strHtml = "";
      //      strHtml += "<div align=\"right\" style=\"align: right;\"><span class=\"txtBig txtClrDlg txtBold\">Qty:     Total:</span></div>";

    while (iint < len) {
        ts = tmpVitemArr[iint];
        strRecID = ts._id;
        strRecType = 50;





        if (ts._id > 0) {
 

	na = ts.ci_price_a - ts.ci_price_b;
	nd = Math.round((na / ts.ci_price_a) * 100);
	strPriceHtml = "";

      tmpATttl = ts.ci_price_b * ts.ci_cartqty;
      tmpTttl = tmpATttl.toFixed(2);
	cartTtl = cartTtl + tmpATttl;


            strPriceHtml = "";
 	
		if(ts.ci_img) {
		 tmpIstrI = JSSHOP.shop.getPrdImgStr("editcat", ts.ci_img);
		}	


            strHtml += "<div class=\"collectionbrdr\">";
            strHtml += "<div class=\"collectionhdr\">";
  		strHtml += "<a class=\"txtDecorNone\" href=\"index.html?pid=aa-show-item&itemid=" + ts.ci_pid + "&cid=" + ts.ci_coid + "&catid=" + ts.ci_catid + "\">";

 		strHtml += "<img src=\"" + tmpIstrI + "\" class=\"slmtable brdrClrDlg icndbtn\" style=\"align: center;text-align:center;margin:6px;\" align=\"absmiddle\">" +  ts.ci_title;
            strHtml += "</a></div>";

            strHtml += "<div style=\"text-align:right;padding:8px\" class=\"bkgdClrANrml\">";
            // strHtml += "<div style=\"text-align:right;padding:8px\"  onclick=\"JSSHOP.ui.setCBBClickClr(this,'crsrPointer brdrClrHdr','crsrPointer brdrNone', function(){doCIedit(" + iint + ");});\" class=\"crsrPointer bkgdClrNrml\">";
 		// strHtml += "<img src=\"images/misc/icon_edit_washed.gif\" class=\"icnbtn\" style=\"align: center;text-align:center;margin:6px;\" align=\"absmiddle\">";

            strHtml += "<span style=\"margin-right: 20px\" class=\"txtSmall txtClrGrey\">" + ts.ci_price_b + " x </span>";
            strHtml += "<input type=\"number\" onchange=\"javascript:doNuItemQtyEdit('" + ts._id + "',this.value,'" + ts.ci_cartqty + "');\"  class=\"crsrPointer bkgdClrNrml brdrClrDlg txtBig txtBold txtClrDlg\" name=\"mod_ci_cartqty" + ts._id + "\" id=\"mod_ci_cartqty" + ts._id + "\"  value=\"" + ts.ci_cartqty + "\" style=\"max-width: 35px;\">";
            // strHtml += "";

            strHtml += "<span class=\"txtBig txtClrHdr txtBold\">" + tmpTttl + "</span>";
             strHtml += "</div>";
             strHtml += "</div>";
             strHtml += "<br>";
     

            // strHtml += "<div style=\" margin: 8px; border-bottom: 1px dashed #D1D5D1;\">"; 
	      // strHtml += "</div>";

 

 


 
            strHtml += "<input type=\"hidden\" id=\"prd" + 5 + iint + "\" value=\"\">";

 
            strHtml += "<input type=\"hidden\" name=\"item_name_" + ppint + "\" value=\"" +  ts.ci_title + "\">";
            strHtml += "<input type=\"hidden\" name=\"item_number_" + ppint + "\" value=\"" +  ts._id + "\">";
            strHtml += "<input type=\"hidden\" name=\"quantity_" + ppint + "\" value=\"" + ts.ci_cartqty + "\">";
            strHtml += "<input type=\"hidden\" name=\"amount_" + ppint + "\" value=\"" + ts.ci_price_b + "\">";

        }
	  ppint++;
        iint++;
    }
    if (tmpVitemArr[0]) {
 
      strHtml += "<div>Total:" + cartTtl.toFixed(2) + "</div>"; 
   } else {
      strHtml += "<div>" + stxt[34] + "</div>";
	strHtml += "<div class=\"collection-item txtSmall txtBold\">Recent:<br>" + currRcntActHstr + "</div>";

      JSSHOP.ui.toggleVisibility("dvBtnsCart");

    }



 
newel = document.createElement('div');
 
newel.innerHTML = strHtml;
tmpTDQI = document.getElementById("dvCartItems");
tmpTDQI.innerHTML = "";
tmpTDQI.appendChild(newel);



};


 


var doCItemEdit = function() {
    JSSHOP.ui.closeLbox();
    tmpFarr = null;
    tmpFarr = JSSHOP.shared.setArrVals(tmpVitemArr, tmpVindex, JSSHOP.shared.getDynFrmVals(document["qcartitem"], "mod_"));

    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = tmpFarr;
    JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_dadded", JSSHOP.getUnixTimeStamp()); 
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qcartitem"], "mod_"));
    tmpFobj["ws"] = "where _id=?";
    tmpFobj["wa"] = [JSSHOP.shared.getFrmFieldVal("qcartitem","_id","0")];
    oi = getNuDBFnvp("qcartitem", 7, null, tmpFobj);
    // JSSHOP.ui.setCBBClickClr(tblCntEditItem,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
    JSSHOP.ui.setCBBClickClr(dvCartItems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){void(0)});
    doQComm(oi["rq"], null, "renderNuTQBItems"); 
 
 
};



var doItemDelete = function(theElem, qid, theRid) {
    carr = tmpVitemArr[qid];
    narr = removeArrElement(tmpVitemArr, theRid);
    theElem.parentNode.parentNode.parentNode.deleteRow(theRid);
    tmpVitemArr = null;
    tmpVitemArr = narr;
    JSSHOP.ui.setCBBClickClr(dvCartItems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
    procNuUIitem("qcartitem","ci_rtype",qid,"0","renderNuTQBItems") 
 
};
 

var fnishClearCart = function(thefCCObj,b,c) {
// alert("fnishClearCart: " + b);
newel = document.createElement('div');
newel.innerHTML = "Cart Cleared<br>";
newel.innerHTML += "<div>" + stxt[34] + "</div>";
newel.innerHTML += "<div class=\"collection-item txtSmall txtBold\">Recent:<br>" + currRcntActHstr + "</div>";

tmpTDQI = document.getElementById(thefCCObj);
tmpTDQI.innerHTML = "";
tmpTDQI.appendChild(newel);
JSSHOP.ui.toggleVisibility("dvBtnsCart");
tmpIval = setCartIArr("y", "[]", "d");
};


var clearCart = function(theCCObj) {
    if(confirm(stxt[52] + " " + stxt[47] + "?")) {
    tmpDOs = null;
    tmpDOs = {};
tmpSobj = null;
tmpSobj = {};
tmpVpar = null;
tmpVpar = [];
    tmpSobj["t"] = "ci_rtype";
    tmpSobj["v"] = "0";
    tmpVpar.push(tmpSobj);
	tmpDOs["knvp"] = tmpVpar;

    tmpDOs["ws"] = "where ci_uid=? and ci_coid=? and ci_cartqty >? and ci_rtype=? and ci_cartid=?";
    tmpDOs["wa"] = [quid,cid,0,5,cartID]; 
 
    oi = getNuDBFnvp("qcartitem",7,null,tmpDOs);
    doQComm(oi["rq"], theCCObj, "fnishClearCart");
    }

};
 

/*
* Process cart
*/

var doProcCart = function() {
qStr = "insert into qordereditem(_id,ci_rtype,ci_uid,ci_cartid,ci_coid,ci_pid,ci_title,ci_price_a,ci_price_b,ci_dimen_n,ci_dimen_v,ci_cartqty,ci_vala,ci_valb,ci_dadded)";
qStr += " select _id,ci_rtype,ci_uid,ci_cartid,ci_coid,ci_pid,ci_title,ci_price_a,ci_price_b,ci_dimen_n,ci_dimen_v,ci_cartqty,ci_vala,ci_valb,ci_dadded from qcartitem";
qStr += " where ci_uid= " + quid + " and ci_coid= " + cid + " and ci_cartqty > 0;";
doQComm(qStr, null, "retProcCart");
};



/*
* submit the send order form
*/

var sendOrder = function() {
tmpRstr = "http://localhost/evenflow/webdroid/assets/quickorder/index.html?pid=aa-show-thks&ppid=" + ppid + "&cid=" + cid + "&c=" + cartID;
JSSHOP.shared.setFrmFieldVal("orderform","return",tmpRstr);
JSSHOP.shared.setFrmFieldVal("orderform","cancel",tmpRstr);
JSSHOP.shared.setFrmFieldVal("orderform","notify_url","http://localhost/evenflow/webdroid/assets/quickorder/_p/pp_notify.php");
JSSHOP.shared.setFrmFieldVal("orderform","custom",cartID);
// alert(tmpRstr);
JSSHOP.ui.setfaction('orderform',true,null);
};



var renderDBis = function(theTmpCORobj) {
 
// var renderDBis = function(theElem, theResp, marble) {
    // alert("renderDBis: " + theResp);
    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = JSON.parse(theTmpCORobj.rs);
    renderNuTQBItems(null,tmpVitemArr,null);
};

var setCIcache = function(theTmpCORobj) {

    tmpIval = setCartIArr("y", theTmpCORobj.rs, "y");
    renderDBis(theTmpCORobj);
};
 
var reloadCartItems = function() {
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["ws"] = "where ci_uid=? and ci_coid=? and ci_cartqty >? and ci_rtype=? and ci_cartid=?";
    tmpDOs["wa"] = [quid,cid,0,5,cartID]; 
 
    oi = getNuDBFnvp("qcartitem",5,null,tmpDOs);
    // doQComm(oi["rq"], null, "renderDBis");
tmpGetCco = nCurrCnxOb();
// tac["lz"] = "y";
tmpGetCco["q"] = oi["rq"];
tmpGetCco["cb"] = "setCIcache";
doNurQComm(tmpGetCco);
	
};



var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {

tfsb = nCurrFFieldOb();
tfsb.fid = "btnEUsend";
tfsb.fty = "button";
tfsb.fcl = function() { sendOrder() };
euiFFObjArr.push(tfsb);

tfsa = nCurrFFieldOb();
tfsa.fid = "btnEUclear";
tfsa.fty = "button";
tfsa.fcl = function() { clearCart('dvCartItems') };
euiFFObjArr.push(tfsa);

JSSHOP.shared.initFrmComps(euiFFObjArr);
tmpPPemalstr = JSSHOP.shared.getFrmFieldVal("qco","c_email","change_this_in_shop_info@to_your_paypal.email");
JSSHOP.shared.setFrmFieldVal("orderform","business",tmpPPemalstr);
// alert(JSSHOP.shared.getFrmFieldVal("orderform","business","change_this_in_shop_info@to_your_paypal.email"));
// reloadCartItems();
// renderDBis(currCartIArr[0]);
    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = currCartIArr;
renderNuTQBItems(null,currCartIArr,null);
};

