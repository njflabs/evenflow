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

 
tmpRowA = tmpTblStgs.insertRow(-1);
tmpCella = tmpRowA.insertCell(-1);
tmpCella.innerHTML = doMbtn("Trumpster","app_dlg.getEpMDcom('103','trumpster')"); 	

tmpRowB = tmpTblStgs.insertRow(-1);
tmpCellB = tmpRowB.insertCell(-1); 
tmpCellB.innerHTML = doMbtn("grid","JSSHOP.user.setCkiePrfKV('prfsSHOPuser','scv','g')"); 
 

tmpRowD = tmpTblStgs.insertRow(-1);
tmpCelld = tmpRowD.insertCell(-1);
tmpCelld.innerHTML = doMbtn('Clear Prefs',"JSSHOP.cookies.deleteCookie('prfsSHOPuser','','');setTimeout(document.location.href='index.php?lgo=r',800)"); 	
};

aLoadChooser();