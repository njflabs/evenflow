var tmpSQBArr = [];
var tmpVitemArr = [];
var tmpVindex = 0;




/*
* opens up the item popup
*/

var doItemEditPop = function() {
    loadJSModal("tplates/aa-mod-edit-cartitem.html");
}
var doQIedit = function(iei) {
    tmpVindex = iei;
	 
    JSSHOP.shared.setFrmVals("qitem",tmpVitemArr[iei],function() {doItemEditPop()});
};

/*
* returns the html link for product editing popup
*/
var getQIstr = function(rid, tmprhtml) {
    strTret = "<div onclick=\"JSSHOP.ui.setCBBClickClr(this,'crsrPointer brdrClrHdr','crsrPointer brdrNone', function(){doQIedit(" + rid + ");});\" class=\"crsrPointer brdrNone\">";
    strTret += tmprhtml + "</div>";

    return strTret;
};





var doAddFnsh = function(theObj,b, iei) {
	getScrollTop();

    JSSHOP.ui.setCBBClickClr(document.getElementById(theObj),'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.setCBBClickClr(document.getElementById('ahCartIcon'),'cls_button cls_button-medium brdrClrDlg txtClrHdr','clsDummy', function(){void(0)})});

    ;

};

var renderCartPop = function(theObj,b, iei) {

tmpDOs = null;
tmpDOs = {};

tmpQtInt = 6;
if(b.indexOf("_id") != -1) {
// alert(b);
var arrToFill = null;
arrToFill = JSON.parse(b);
ts = arrToFill[0];
JSSHOP.shared.setFrmFieldVal("qcartitem", "_id", ts._id);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_cartqty", Math.round(ts.ci_cartqty) + 1);
tmpDOs["ws"] = "where ci_uid=? and ci_coid=? and ci_pid=?";
tmpDOs["wa"] = [quid,cid,strRecID];
tmpQtInt = 7;
} else {
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_cartqty", 1);
}
tmpDOs["knvp"] = JSSHOP.shared.getFrmVals(document["qcartitem"], "nada");
// alert("renderCartPop: " + oi["rq"]);
oi = getNuDBFnvp("qcartitem",tmpQtInt,theObj,tmpDOs);
doQComm(oi["rq"], theObj, "doAddFnsh");
};

var doQIadd = function(theObj,theId, iei){
try {
tmpVindex = iei;
tsa = {};
tsa = tmpSQBArr[tmpVindex];
strRecID = tsa._id;
strRecType = 51;

var today = new Date().getTime();
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_uid", quid);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_cartid", quid + "0" + cid);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_coid", cid);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_pid", strRecID);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_title", tsa.i_title);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_price_a", tsa.i_price_a);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_price_b", tsa.i_price_b);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_dimen_n", tsa.i_dimen_n);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_dimen_v", tsa.i_dimen_v);

JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_vala", tsa.i_vala);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_valb", tsa.i_valb);
JSSHOP.shared.setFrmFieldVal("qcartitem", "ci_dadded", today);


tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where ci_uid=? and ci_coid=? and ci_pid=?";
tmpDOs["wa"] = [quid,cid,strRecID];
oi = getNuDBFnvp("qcartitem",5,null,tmpDOs);
// alert(oi["rq"]);
doQComm(oi["rq"], theObj, "renderCartPop");
} catch(e) {
alert(e);
}
};





/*
* renders all items in the category
*/

var renderNuItem = function(theElem, theResp, marble){
	try {
// alert("renderNuTQBItems: " + theResp);
var arrToFill = null;
arrToFill = JSON.parse(theResp);
tmpSQBArr = JSON.parse(theResp); 
var len = arrToFill.length;
strHtml = "";
tstr = "";
iint = 0;
// alert("renderNuTQBItems: " + theResp);
// alert("renderNuTQBItems JSON: " + JSON.stringify(arrToFill));

while(iint < len) {
ts = arrToFill[iint];
tmpVitemObj = null;
tmpVitemObj = {};
strRecID = ts._id;
strRecType = 51;
if(ts._id > 0) {
	na = ts.i_price_a - ts.i_price_b;
	nd = Math.round((na / ts.i_price_a) * 100);
	strPriceHtml = "";


	tmpIstrI = "menu_iconl.jpg";
	if(ts.i_vala) {
	if(ts.i_vala.indexOf(ts._id) != -1) {
	tmpIstrI = "pimgs/" + ts.i_vala;	
	}
	}
	strImgDsct = "<a href=\"javascript:doImgPop('" + tmpVitemObj["id"] + "');\"><img src=\"images/" + tmpIstrI + "\" class=\"icnmedbtn\"></a>";

	strPriceHtml = "<span class=\"txtstriked txtBig txtClrGrey\">" + ts.i_price_a + "</span> <span class=\"txtBig txtClrRed txtBold\">" + ts.i_price_a + "</span>";
	strHtml += "<tr>";
	strHtml += "<td id=\"tdPP" + ts._id + "\">";
	strHtml += strImgDsct
	strHtml += "</td>";
	strHtml += "<td>";
	strHtml += ts.i_title + " " + ts.i_dimen_n + "    " + strPriceHtml;
	strHtml += "</td>";
	strHtml += "<td  id=\"tdCI" + ts._id + "\" onclick=\"javascript:doQIadd(this.id," + ts._id + "," + iint + ");\"><img src=\"images/cart_r.gif\" class=\"icnsmlbtn brdrClrWhite\">";
	strHtml += "<input type=\"hidden\" id=\"prd" + 5 + iint + "\" value=\"\">";
	strHtml += "</td>";
	strHtml += "</tr>";


}
iint++;
}
newel = document.createElement('div');
// strTHhtml = "<th>ID</th><th>Title</th><th>MSRP</th><th>Price</th>";
strTHhtml = "<th>�</th><th>Produto</th><th>xx</th>";
tmpFstr = getTblSortStr(strTHhtml, strHtml);

newel.innerHTML = tmpFstr;
tmpTDQI = document.getElementById("dvItemCntn");
tmpTDQI.innerHTML = "";





tmpTDQI.appendChild(newel);

	} catch(e) {
	 alert("renderNuTQBItems: " + e);
	}


};


var renderDBis = function(theElem, theResp, marble) {
    // alert("renderDBis: " + theResp);
    tmpVitemArr = null;
    tmpVitemArr = [];
    tmpVitemArr = JSON.parse(theResp);
    renderNuTQBItems("add",tmpVitemArr,"dddc");
};


var aRenderItem = function(a, b, c) {
    // alert("pre: " + JSON.stringify(b));
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["ws"] = "where _id=?";
    tmpDOs["wa"] = [ppid]; 
    oi = getNuDBFnvp("qitem",5,null,tmpDOs);
    doQComm(oi["rq"], null, "renderNuItem");
    // renderTQBItems();
};


 
 
 


var doCBcatedit = function(a, b, c) {
    doMMenuLd();
};


var doItemEdit = function() {
JSSHOP.ui.closeLbox();
    tmpFobj = null;
    tmpFobj = {};
    tmpFarr = null;
	
    tmpFarr = JSSHOP.shared.setArrVals(tmpVitemArr, tmpVindex, JSSHOP.shared.getDynFrmVals(document["qitem"], "mod_"));
    tmpVitemArr = null;
    tmpVitemArr = tmpFarr;
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qitem"], "mod_"));
    tmpFobj["ws"] = "where _id=?";
    tmpFobj["wa"] = [JSSHOP.shared.getFrmFieldVal("qcartitem","_id","0")];
    oi = getNuDBFnvp("qitem", 7, null, tmpFobj);
    // JSSHOP.ui.setCBBClickClr(tblCntEditItem,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});
    JSSHOP.ui.setCBBClickClr(divQitems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});

    // alert("oiRQ " + oi["rq"]); 
    doQComm(oi["rq"], null, "renderNuTQBItems"); 
};



var doItemAdd = function(t, am) {
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qitem"], "tmp_"));
    oi = getNuDBFnvp("qcartitem", 6, null, tmpFobj);
    JSSHOP.ui.setCBBClickClr(dvCartItems,'cls_button cls_button-medium brdrClrDlg txtClrHdr','txtClrHdr bkgdClrWhite', function(){JSSHOP.ui.closeLbox()});

    //alert("doItemAdd: rq" + oi["rq"]); 
    doQComm(oi["rq"], null, "preRenderTQBItems");
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
 


var showQbit = function() {
    tmpStrsq = JSON.stringify(tmpVitemArr);
    tmpSQia = [];
    tmpSQio = {};
    tmpStrsq = "";
    tmpSQia.push(quid);
    tmpSQia.push(JSSHOP.shared.getFrmFieldVal("qco", "_id", cid));
    tmpSQia.push(JSSHOP.shared.getFrmFieldVal("qco", "c_title", "ABC Co."));
    tmpSQia.push(JSSHOP.shared.getFrmFieldVal("qco", "c_tel", "123 ABC Co."));
    tmpSQia.push(JSSHOP.shared.getFrmFieldVal("qco", "c_web", "ABC .Com"));
    tmpSQia.push(JSSHOP.shared.getFrmFieldVal("qco", "c_email", "@ABC Co."));
    tmpSQia.push(ppid);
    tmpSQia.push(JSSHOP.shared.getFrmFieldVal("qbit", "q_title", "Q title"));
    tmpSQia.push(JSSHOP.shared.getFrmFieldVal("qbit", "q_desc", "Q desc"));
    tt = [];
    iint = 0;
    len = tmpVitemArr.length;
    strHtml = "";
    while (iint < len) {
        ts = tmpVitemArr[iint];
        tf = null;
        tf = [];
        for (var gkey in ts) {
            tf.push(ts[gkey]);
        }
        tt.push(tf);
        iint++;
    }
    tmpSQia.push(tt);
    tmpSQis = JSON.stringify(tmpSQia);
    tmpSQias = encPrefCky(tmpSQis);
   // alert("tmpS: " + tmpSQis.length + " : " + tmpSQias.length + "\r\n" + tmpSQias);
    tcnvs = doqr(tmpSQias);
    // tcnvs = docPrefCky(tmpSQias);
    cdiv = document.getElementById('lightbox_content');
    cdiv.appendChild(tcnvs);
    JSSHOP.ui.popAndFillLbox("noQvalue");
    // transRes(tmpSQias);
    // alert("tmpSQis: " + tmpSQis);
    // alert("tmpSQias: " + tmpSQias);
};



 

var fillUserForm = function(theElem, theResp, marble) {
    tmpvarr = null;
    tmpvarr = JSON.parse(theResp);
    JSSHOP.shared.setFrmVals("quser",tmpvarr[0],function() { void(0)});

};

var fillCatForm = function(theElem, theResp, marble) {
    tmpvarr = null;
    tmpvarr = JSON.parse(theResp);
    JSSHOP.shared.setFrmVals("qcat",tmpvarr[0],function() { document.getElementById("tmpQtitle").value = document.getElementById("cat_title").value});

};

var fillCoForm = function(theElem, theResp, marble) {
    tmpvarr = null;
    tmpvarr = JSON.parse(theResp);
    JSSHOP.shared.setFrmVals("qco",tmpvarr[0],function() {JSSHOP.ui.setTinnerHTML("tdCoTitle", JSSHOP.shared.getFieldVal("c_title", "theCo"))});
};



/*
 * main loader function
 */
var aLoadModShowItem = function() {
    JSSHOP.loadScript("js/x_aqr.js", JSSHOP.checkLoader, "js");
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["ws"] = "where _id=?";
    tmpDOs["wa"] = [cid];
    oi = getNuDBFnvp("qco", 5, null, tmpDOs);
    // doNuQComm(oi["rq"],"qco","fillGenForm", fnshFormFill());
    doQComm(oi["rq"],"qco","fillCoForm");
    if (ppid == 0) {} else {
 

        tmpDOs = null;
        tmpDOs = {};
        tmpDOs["ws"] = "where _id=?";
        tmpDOs["wa"] = [JSSHOP.cookies.getCookie("quid")];
        oi = getNuDBFnvp("quser", 5, null, tmpDOs);
        doQComm(oi["rq"], null, "fillUserForm");

		aRenderItem("aa","aa","aa");


    }
    // doQComm('select * from prod_options order by OptionType desc limit 500', null, "rEditProdOptions")
};

 
aLoadModShowItem();
  
