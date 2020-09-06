currPgTitle = stxt[31] + " " + stxt[7];
document.title = currPgTitle; 
currIContent = "no"; // ajax request to include the tplates/... file or not 



var doShopsList = function(a,theResp,c) {
// alert(theResp);
try { spinner.stop(); } catch(e) { alert(e); }
hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
while(iint < len) {
ts = arrToFill[iint];
tstr += "<tr>";
// tstr += "<td>" + ts._id + "</td>";
tstr += "<td><a href=\"index.html?pid=aa-edit-shop&cid=" + ts._id + "\">" + ts.c_title + "</a></td>";
tstr += "<td style=\"min-width: 38px;\" class=\"txtClrHdr\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
tstr += "<td class=\"txtClrHdr\"><i class=\"small-material-icons txtClrDlg smltable\" alt=\"edit\" title=\"edit\">&#xe3c9;</i><a href=\"index.html?pid=aa-edit-categories&cid=" + ts._id + "\"><span  style=\"vertical-align:super;margin-left: 10px\" class=\"txtBold\">" + stxt[31] + "</span></a></td>";
tstr += "<td><a href=\"index.html?cid=" + ts._id + "\">View</a></td>";

tstr += "</tr>";
iint++;
}
newel = document.createElement('div');

if(arrToFill[0]) {
strTHhtml = "<th><span style=\"margin-right: 10px; margin-top: 10px\" class=\"txtBold\"><i class=\"menu-material-icons\">&#xe264</i></span>" + stxt[22] + "</th><th></th>";
strTHhtml += "<th><span style=\"margin-right: 10px; margin-top: 10px\" class=\"txtBold\"><i class=\"menu-material-icons\">&#xe896</i></span>" + stxt[32] + "</th>";
strTHhtml += "<th><span style=\"margin-right: 10px; margin-top: 10px\" class=\"txtBold\"><i class=\"menu-material-icons\">&#xe896</i></span>View</th>";

} else {
document.location.href = "index.html?pid=aa-add-shop";
// strTHhtml = "<th>" + stxt[1] + "</th>";
// tstr = "<a href=\"index.html?pid=aa-add-shop\">" + stxt[1] + "</a>";
}
tmpFstr = getTblSortStr(strTHhtml, tstr);
tmpFFstr = "<div class=\"rtable\" style=\"margin: 0 auto;max-width:600px;padding: 10px;margin-left: 15px;margin-right: 15px;\">" + tmpFstr + "</div>";
document.getElementById("includedContent").innerHTML = tmpFFstr;



// standardistaTableSortingInit();
};
 


var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where c_uid=?";
tmpDOs["wa"] = [JSSHOP.cookies.getCookie("quid")];
oi = getNuDBFnvp("qco",5,null,tmpDOs);
doQComm(oi["rq"], null, "doShopsList");
// document.getElementById("includedContent").className = "rtable";
};


var aListShops = function() {
// doDefFormsFill();
};