currPgTitle = stxt[31] + " " + stxt[7];
document.title = currPgTitle; 

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
tstr += "<td><a href=\"index.php?pid=aa-edit-shop&cid=" + ts._id + "\">" + ts.c_title + "</a></td>";
tstr += "<td><a href=\"index.php?pid=aa-edit-categories&cid=" + ts._id + "\">" + stxt[31] + "</a></td>";
tstr += "<td><a href=\"index.php?pid=aa-edit-shop&cid=" + ts._id + "\">" + ts.c_title + "</a></td>";

tstr += "</tr>";
iint++;
}
newel = document.createElement('div');

if(arrToFill[0]) {
strTHhtml = "<th>" + stxt[22] + "</th><th>" + stxt[32] + "</th><th>" + stxt[33] + "</th>";
} else {
strTHhtml = "<th>" + stxt[1] + "</th>";
tstr = "<a href=\"index.php?pid=aa-create-menu\">" + stxt[1] + "</a>";
}
tmpFstr = getTblSortStr(strTHhtml, tstr);

document.getElementById("dvShopsList").innerHTML = tmpFstr;



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

};


var aListShops = function() {
// doDefFormsFill();
};