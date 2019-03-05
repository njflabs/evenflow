currPgTitle = "SQL dump";
document.title = currPgTitle; // set the page title 


var showDumpRes = function(theElem, theResp, marble){

var arrToFill = null;
arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
strHtml = "";
tstr = "";
iint = 0;
// alert("renderNuTQBItems: " + theResp);
// alert("renderNuTQBItems JSON: " + JSON.stringify(arrToFill));
// strHtml += "<table>";
while(iint < len) {
ts = arrToFill[iint];
tmpVitemObj = null;
tmpVitemObj = {};


strHtml += "<tr>";

for(var gkey in ts) {
	strHtml += "<td>";
	strHtml +=  ts[gkey];
	strHtml += "</td>";
}  


	strHtml += "</tr>";
 
iint++;
}

iint = 0;
strTHhtml = "<thead><tr>";
// alert("renderNuTQBItems: " + theResp);
// alert("renderNuTQBItems JSON: " + JSON.stringify(arrToFill));
// strHtml += "<table>";
qint = ts.length;
for(var gkey in ts) {
strTHhtml += "<th>" + Object.keys(ts)[iint] + "</th>";
iint++;
}
strTHhtml += "</tr></thead>";
newel = document.createElement('div');
tmpFstr = "<table class=\"striped highlight responsive-table\" cellpadding=\"12px\" cellspacing=\"12px\">" + strTHhtml +  strHtml + "</table>";

newel.innerHTML = tmpFstr;
tmpTDQI = document.getElementById("tdSql");
tmpTDQI.innerHTML = "";
tmpTDQI.appendChild(newel);
};


var aLoadDump = function() {
	try {
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where _id>?";
tmpDOs["wa"] = [0];
oi = getNuDBFnvp("qbit",5,null,tmpDOs);
doQComm(JSSHOP.shared.getFieldVal("taSql", "select * from qco"), null, "showDumpRes");
	//  alert(oi["rq"]);
	}catch(e) {
	alert(e);
	}
// doQComm('select * from prod_options order by OptionType desc limit 500', null, "rEditProdOptions")
};


var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
if(currUrlArr.tqs){
if(currUrlArr.tqs == "noQvalue") {
} else {
JSSHOP.shared.setFieldVal("taSql", currUrlArr.tqs);
}
aLoadDump();
}
};
