var aEditProd = function() {
if(ppid == 0) {
doQComm('select * from product order by _id desc limit 1', null, "rEditProd")
} else {
doQComm('select * from product where _id = ' + ppid + ' order by _id desc limit 1', null, "rEditProd")
}
};


var doPrdEdtCatFill = function(theElem, theResp, marble) {
tFelm = document.getElementById("tmpCategoryID");

fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
theO = document.getElementById("tmpUpsellType");
while(iint < len) {
ts = arrToFill[iint];
addCurrListVal(tFelm, ts.CategoryTitle, ts._id);
addCurrListVal(theO, ts.CategoryTitle, ts._id);
iint++;
}
setCurrListIndex(tFelm, document.getElementById("CategoryID").value);
setCurrListIndex(theO, document.getElementById("UpsellType").value);
};


var rEditProd = function(theElem, theResp, marble) {
hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
 
while(iint < len) {
ts = arrToFill[iint];


for(var gkey in ts) {
if(iint < 1) { 
if(ppid == 0) {
if(document.getElementById(gkey)) {
arrDBnDocFNames.push(gkey);
}
}
arrDBFNames.push(gkey); 
}



if(ts._id == ppid) {
if(document.getElementById(gkey)) {
arrDBnDocFNames.push(gkey);
document.getElementById(gkey).value = ts[gkey];
}  

}

fullstr += gkey + " . " + ts[gkey] +  "\n";
} // end for(var....

iint++;
}
doQComm('select _id,CategoryTitle from prod_categories order by _id desc limit 100', 'CategoryID', "doPrdEdtCatFill");
doQComm('select _id,OptionTitle from prod_options where OptionType=1001 order by _id desc limit 100', 'ProductOptions', "doPrdEdtOptFill");
tmpVpo = document.getElementById("ProductOptions").value;
if(tmpVpo.indexOf("[") != -1){}
// setCurrListIndex(theO, pcid);
// alert(pcid);
};



var fncTnew = function(theElem, theStr, n) {
//document.getElementById("binProductOptions").value = theStr;
tJstr = document.getElementById("binProductOptions").value;
theREncDA = [];
if(tJstr.indexOf("[") != -1) {
theREncDA = JSON.parse(tJstr);
}
// alert("theFa: " + theStr)
theFa = JSON.parse(theStr);

arrTmp = theREncDA.concat(theFa);
document.getElementById("binProductOptions").value = JSON.stringify(arrTmp);
};



var showProdOptEdit = function() {

tJstr = document.getElementById("ProductOptions").value;

hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(tJstr);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
ts = null;
while(iint < len) {
ts = arrToFill[iint];
iint++;
}
};






var addPrdOpGrp = function() {
tFelm = document.getElementById("dynProductOptions");
tFo = document.getElementById("tmpProductOptions");
var theREncDA = [];
theArr = getCurrListArr(tFelm);
isO = setCurrListIndex(tFo, theArr[0]);
if(isO == "noQvalue") {

tJstr = document.getElementById("ProductOptions").value;
// alert("s" + tJstr);
try {
if(tJstr.indexOf("[") != -1) {
// alert("q" + tJstr);
theREncDA = JSON.parse(tJstr);
}
} catch(e) {
alert("theREncDA erre: " + e);
}
theFa = [];
tmpArrEPO = null;
tmpArrEPO = {};
tmpArrEPO["i"] = theArr[0];
tmpArrEPO["o"] = ["0"];
tmpArrEPO["t"] = theArr[1];
theFa.push(tmpArrEPO);
arrTmp = theREncDA.concat(theFa);
document.getElementById("ProductOptions").value = JSON.stringify(arrTmp);
addCurrListVal(tFo, theArr[1], theArr[0]);
setCurrListIndex(tFo, theArr[1], theArr[0]);
JSSHOP.ui.popAndFillLbox("Changes made. Dont forget to save...");

}
}

var doPrdEdtOptFill = function(theElem, theResp, marble) {
tFelm = document.getElementById("dynProductOptions");
tFo = document.getElementById("tmpProductOptions");
fullstr = "";
// alert("doPrdEdtOptFill: " +theResp);
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
while(iint < len) {
ts = arrToFill[iint];
addCurrListVal(tFelm, ts.OptionTitle, ts._id);
iint++;
}
iint = 0;
pcid = 0;
tlen = 0;
tJstr = document.getElementById("ProductOptions").value;
// alert("Jstr: " + tJstr);
try {
theEncDA = JSON.parse(tJstr);
tlen = theEncDA.length;
while(iint < tlen) {
newArr = theEncDA[iint];
// alert("newArr.i: " + newArr.i);
addCurrListVal(tFo, newArr.t, newArr.i);
doQComm("select _id,OptionTitle,OptionType from prod_options where OptionType = " + newArr.i + " order by OptionTitle desc limit 100", "gore", "fncTnew");

// alert("newArr.t: " + newArr.t);
iint++;
}
} catch(e) {
} finally {
// alert(document.getElementById("binProductOptions").value);
}
};






var checkProdEdit = function(t,am) {
if((am == 7) && (ppid == 0)) { // not edit, its add product
m = 6;
} else {
m = am;
}
var varcatTitle = document.getElementById("ProductID").value;
if(varcatTitle.length < 1) {
alert("You must enter a ProductID");
return;
}

tmpVpar = [];
tmpVpar.push("ProductID");
tmpVpar.push("ProductShortDescriptionENG");
oi = getDBFnvp(t,m,tmpVpar);


oi = getDBFnvp(t,m,"oh");
tsas = JSON.stringify(oi);
doQComm(oi["rq"], null, "hu")
// alert(switchOccurrences(oi["ws"], "?", oi["wa"]));
JSSHOP.ui.popAndFillLbox(tsas);
// app.getJsnPrs(tsas);
};


 