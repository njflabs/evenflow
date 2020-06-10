function doCUConfBundle() {
        try {
tmpMSArrQ = null;
tmpMSArrQ = {};
            tsret = app_cutouts.getCUConfBundle();

document.getElementById("fldChallArray").value = tsret;
tmpMStr = document.getElementById("fldChallArray").value;
document.getElementById("tTmpOutpu").innerHTML = tmpMStr;
tmpMSArrQ = JSON.parse(tmpMStr);
if(tmpMSArrQ.confCUuseDSpeak) {
if(tmpMSArrQ.confCUuseDSpeak == "yes") {
document.getElementById("prfMSdps").checked=true;
}
}

if(tmpMSArrQ.confCUTitleWmark) {
if(tmpMSArrQ.confCUTitleWmark== "yes") {
document.getElementById("prfMSptw").checked=true;
}
}

tmpFlterID = "no";
if(tmpMSArrQ.confCUUseFilter) {
tmpFlterID = tmpMSArrQ.confCUUseFilter;
}
JSSHOP.shared.setCurrSelectOpt(document.getElementById("selfilterid"), tmpFlterID);
		// alert(ret);
        } catch (e) {
		document.getElementById("tTmpOutpu").innerHTML = document.getElementById("tTmpOutpu").innerHTML + "doCUConfBundle.error: " + e;
        }

    }


function doCUChckbxStgs(tEl,tKey,tKtrue,tKfalse) {
        try {
    if(tEl.checked) {
app_cutouts.setCUConfValStr(tKey, tKtrue);
} else {
app_cutouts.setCUConfValStr(tKey, tKfalse);
}
        } catch (e) {
		document.getElementById("tTmpOutpu").innerHTML = document.getElementById("tTmpOutpu").innerHTML + "doCUChckbxStgs.error: " + e;
        }

    }



var doFilterChange = function(theObj) { 
 
objVal = JSSHOP.shared.getCurrSelectOpt(theObj);
app_cutouts.setCUConfValStr("confCUUseFilter", objVal);
};

var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function () {
doCUConfBundle();
}

