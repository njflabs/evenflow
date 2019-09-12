var currMSetArr = [];
var doMbtn = function(tmpTxt, tmpCallback) {
tmpRd = "<div class=\"crsrPointer\" style=\"margin:5px;\"><div onclick=\"javascript:JSSHOP.ui.setCBBClickClr(this,'txtBig slmtable brdrClrNrml','txtBig slmtable brdrClrHdr', function(){" + tmpCallback + "});\" class=\"slmtable txtBig brdrClrHdr\"  style=\"padding:8px;\">";
tmpRd += tmpTxt + "</div></div>";
return tmpRd;
};


var aLoadChooser = function() {
tmpTblStgs = document.getElementById("tblMChooser");
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);   
tmpCella.innerHTML = doMbtn("File","app_dlg.getEpMDcom('101','file')"); 		
 


tmpRow0 = tmpTblStgs.insertRow(-1);
tmpCell0 = tmpRow0.insertCell(-1);
tmpCell0.innerHTML = doMbtn("Camera","app_dlg.getEpMDcom('102','Camera')"); 	
};

var doQIMeta = function() {
/*
    tmpMetaObj = {};
    tmpMetaObj.qco = arrAllForms.qco.v[0];
    tmpMetaObj.qitem = JSSHOP.shop.getCurrItemArr();
    return JSON.stringify(tmpMetaObj);
*/
};

