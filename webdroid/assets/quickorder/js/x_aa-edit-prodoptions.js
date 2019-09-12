
var fillProdOptSelects = function(){
tmpArrSG = JSON.parse(document.getElementById("fldChallArray").value);
// alert(JSON.stringify("fillProdOptSelects: " + tmpArrSG));
iint = 0;
ttstr = "";
len = tmpArrSG.length;
while(iint < len) {
ts = tmpArrSG[iint];
tob = "selprdop" + ts.o;
if(document.getElementById(tob)) {
addCurrListVal(document.getElementById(tob), ts.t, ts.i);
}
iint++;
}
};



var checkProdOpEdit = function(t,am) {
if((am == 7) && (ppid == 0)) { // not edit, its add category
m = 6;
} else {
m = am;
}
var varcatTitle = document.getElementById("OptionTitle").value;
if(varcatTitle.length < 1) {
alert("You must enter a Option Title");
return;
}
tmpVpar = [];
tmpVpar.push("OptionTitle");
tmpVpar.push("OptionType");
oi = getDBFnvp(t,m,tmpVpar);
tsas = JSON.stringify(oi);
doQComm(oi["rq"], null, "hu");
// alert(switchOccurrences(oi["ws"], "?", oi["wa"]));
// JSSHOP.ui.popAndFillLbox(oi["rq"]);
// app.getJsnPrs(tsas);
};



var rEditProdOptions = function(theElem, theResp, marble) {
if(ppid == 0) {
ttstr = "New Group";
JSSHOP.ui.toggleVisibility("dvTmpOptType","show");
} else {
ttstr = "Edit:";
}


hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var tmpavfields = [];
tmpArrEPO = {};
fullstr = "<table class=\"clsmmdv\" border=\"1px\"><tr>"
while(iint < len) {
ts = arrToFill[iint];
 


if(iint < 1) { 
for(var gkey in ts) {
if(document.getElementById(gkey)) {
arrDBnDocFNames.push(gkey);
} 
arrDBFNames.push(gkey); 
}
}

 




if(ts.OptionType == 1001) {
fullstr += "<td><a href=\"index.html?pid=aa-edit-prodoptions&ppid=" + ts._id +  "\">" + ts._id + "</a></td>";
fullstr += "<td><a href=\"index.html?pid=aa-edit-prodoptions&ppid=" + ts._id +  "\">" + ts.OptionTitle + "</a></td>";
fullstr += "<td><select id=\"selprdop" + ts._id + "\" name=\"selprdop" + ts._id + "\"><option value=\"0\">Add Choice</option></select></td>";
fullstr += "<td>Edit</td>";
addCurrListVal(document.getElementById('tmpOptionType'), ts.OptionTitle, ts._id);
} else {
tmpArrEPO = null;
tmpArrEPO = {};
tmpArrEPO["i"] = ts._id;
tmpArrEPO["o"] = ts.OptionType;
tmpArrEPO["t"] = ts.OptionTitle;
tmpavfields.push(tmpArrEPO);
// alert("ts._id: " + ts._id)
}

 
fullstr += "</tr><tr>";


if(ts._id == ppid) {
for(var agkey in ts) {
if(document.getElementById(agkey)) {
document.getElementById(agkey).value = ts[agkey];
} 
}
}
 


iint++;
}
document.getElementById("fldChallArray").value = JSON.stringify(tmpavfields);
fullstr += "</tr></table>";
fullsta = "<img alt=\"imgldr\" height=\"4px\" width=\"4px\" src=\"images/misc/trans.gif\" onload=\"javascript:fillProdOptSelects();\"  onerror=\"javascript:fillProdOptSelects();\">";

// document.getElementById("dvoplist").innerHTML = fullstr;


 
newel = document.createElement('div');
newel.innerHTML = fullstr;
document.getElementById("dvoplist").appendChild(newel);
newela = document.createElement('div');
newela.innerHTML = fullsta;
document.getElementById("dvoplist").appendChild(newela);

newelb = document.createElement('div');
newelb.innerHTML = ttstr;
document.getElementById("dvTmpOptTip").appendChild(newelb);
};




var aEditProdOptions = function() {
doQComm('select * from prod_options order by OptionType desc limit 500', null, "rEditProdOptions")
};


