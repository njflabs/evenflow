currPgTitle = stxt[31] + " " + stxt[56];
document.title = currPgTitle; 
var euiFFObjArr = null;
var euiFFObjArr = [];

ck_name = /^[A-Za-z0-9 ]{3,20}$/;
ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
ck_username = /^[A-Za-z0-9_]{3,20}$/;
ck_password =  /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;


var cbFedit = function(a,b,c) {
 JSSHOP.ui.setCBBClickClr(divCntEdit,'bkgdClrDlg','clsDummy', function(){void(0)});
document.getElementById("btnEUsave").disabled=false;
// smlspinner.stop();
};

var doNUserEdit = function() {
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getKNVParr(JSSHOP.shared.getDynFrmVals(document["quser"], "tmp_"));
    tmpFobj["ws"] = "where _id=?";
    tmpFobj["wa"] = [quid];
    oi = getNuDBFnvp("quser", 7, null, tmpFobj);
    doQComm(oi["rq"], null, "cbFedit");
};


var doUserEdit = function() {
if(JSSHOP.shared.valVOarr(euiFFObjArr, "dvVdtnError")) {
document.getElementById("btnEUsave").disabled=true;
// smlspinner.spin(dvSpinner);
doNUserEdit();
}
// var cars = ["tmp_u_name","tmp_u_pass"]; 
// if(JSSHOP.shared.valFieldVals(cars)) {doNUserEdit();}
};


var testFFieldOb = function() {
aCurrFFieldOb = null;
aCurrFFieldOb= {};

aCurrFFieldOb["fid"] = "noQvalue"; // field id
aCurrFFieldOb["fty"] = "noQvalue"; // field type - for future use
aCurrFFieldOb["fvr"] = "noQvalue"; // field validation - regex
aCurrFFieldOb["fof"] = "noQvalue"; // field onfocus
aCurrFFieldOb["fob"] = "noQvalue"; // fielonblur
aCurrFFieldOb["fcl"] = "noQvalue"; // click
aCurrFFieldOb["fku"] = "noQvalue"; // keyup
aCurrFFieldOb["fkd"] = "noQvalue"; // keydown
aCurrFFieldOb["lid"] = "noQvalue"; // labelid
aCurrFFieldOb["ltxt"] = "noQvalue"; // labelid
return aCurrFFieldOb;
};





 
var fnishDlayCntLoad = function() {
};



var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
JSSHOP.ui.setTinnerHTML("tdTitleBar", currPgTitle);

JSSHOP.shared.setDynFieldVals(arrAllForms.quser.v[0],"tmp_");

 

tifo = nCurrFFieldOb();
tifo.fid = "tmp_u_name";
tifo.fdv = stxt[48];
tifo.lid = "lbl_u_name"; 
tifo.ltxt = stxt[48]; 
tifo.fvr = ck_username;
tifo.fve = stxt[1001];
euiFFObjArr.push(tifo);

tfui = nCurrFFieldOb();
tfui.fid = "tmp_u_pass";
tfui.fdv = stxt[49];
tfui.lid = "lbl_u_pass"; 
tfui.ltxt = stxt[49]; 
tfui.fvr = ck_password;
tfui.fve = stxt[1001];
euiFFObjArr.push(tfui);


/**/
tfue = nCurrFFieldOb();
tfue.fid = "tmp_u_email";
tfue.fdv = stxt[50];
tfue.lid = "lbl_u_email"; 
tfue.ltxt = stxt[50]; 
tfue.fve = stxt[1001];
euiFFObjArr.push(tfue);



tfuh = nCurrFFieldOb();
tfuh.fid = "tmp_u_header";
tfuh.fdv = stxt[51];
tfuh.lid = "lbl_u_header"; 
tfuh.ltxt = stxt[51]; 
euiFFObjArr.push(tfuh);

tfsb = nCurrFFieldOb();
tfsb.fid = "btnEUsave";
tfsb.fty = "button";
tfsb.fcl = function() { doUserEdit() };

euiFFObjArr.push(tfsb);

JSSHOP.shared.initFrmComps(euiFFObjArr);
return dmyFnishCntLoad;
};

var aEditUser = function() {
 fnishDlayCntLoad(); // was not working with fnishCntLoad function
};