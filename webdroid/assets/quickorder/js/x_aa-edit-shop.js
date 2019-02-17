currPgTitle = stxt[59];
var euiFFObjArr = null;
var euiFFObjArr = [];
document.title = currPgTitle; // set the page title 

var cbFedit = function(a,b,c) {
 JSSHOP.ui.setCBBClickClr(divCntEditCo,'cls_button cls_button-medium brdrClrDlg txtClrHdr','clsDummy', function(){void(0)});
};




var doShopEdit = function() {
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["qco"], "tmp_"));
    tmpFobj["ws"] = "where _id=?";
    tmpFobj["wa"] = [JSSHOP.shared.getFrmFieldVal("qco","_id","0")];
    oi = getNuDBFnvp("qco", 7, null, tmpFobj);
    doQComm(oi["rq"], null, "cbFedit");
};


var doNShopEdit = function() {
if(JSSHOP.shared.valVOarr(euiFFObjArr, "dvVdtnError")) {
   doShopEdit();
}
};


var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {


tfip = nCurrFFieldOb();
tfip.fid = "tmp_c_title";
tfip.fdv = stxt[22];
tfip.fve = stxt[1003];
tfip.lid = "lbl_c_title"; 
tfip.ltxt = stxt[22]; 
euiFFObjArr.push(tfip);
 

tfdv = null;
tfdv = nCurrFFieldOb();
tfdv.fid = "tmp_c_desc";
tfdv.fdv = stxt[23];
tfdv.fve = stxt[1003];
tfdv.lid = "lbl_c_desc"; 
tfdv.ltxt = stxt[23]; 
euiFFObjArr.push(tfdv); 

tftv = null;
tftv = nCurrFFieldOb();
tftv.fid = "tmp_c_tel";
tftv.fdv = stxt[24];
tftv.fve = stxt[1003];
tftv.lid = "lbl_c_tel"; 
tftv.ltxt = stxt[24]; 
euiFFObjArr.push(tftv);


tfwv = null;
tfwv = nCurrFFieldOb();
tfwv.fid = "tmp_c_web";
tfwv.fdv = stxt[25];
tfwv.fve = stxt[1003];
tfwv.lid = "lbl_c_web"; 
tfwv.ltxt = stxt[25]; 
euiFFObjArr.push(tfwv);


tfev = null;
tfev = nCurrFFieldOb();
tfev.fid = "tmp_c_email";
tfev.fdv = stxt[26];
tfev.fve = stxt[1003];
tfev.lid = "lbl_c_email"; 
tfev.ltxt = stxt[26]; 
euiFFObjArr.push(tfev);


JSSHOP.shared.initFrmComps(euiFFObjArr);
// JSSHOP.ui.setTinnerHTML("tdCoTitle", JSSHOP.shared.getFieldVal("c_title", "theCo"));
// JSSHOP.shared.setDynFieldVals(arrAllForms.qco.v[0],"tmp_");

JSSHOP.shared.setDynFrmVals(document["qco"], "tmp_");
};

var aEditShop = function() {
// fnishCntLoad();
};