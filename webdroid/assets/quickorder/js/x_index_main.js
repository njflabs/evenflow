var hmr = function(a,b,c) {
// string = "";
//string = b;
 alert("Size of sample is: " + b.length + " " + b); 
// var compressed = LZString.compress(string); 
// alert("Size of compressed sample is: " + compressed.length); 
try {
astring = LZString.decompressFromEncodedURIComponent(b);
// astring = LZString.decompress(b);

alert("Sample is: " + astring.length + astring);
} catch(e) {
alert("e" + e);
} 
};

var doTesta = function(theCB) {
/*
mf = window[theCB];
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where cat_coid=?";
tmpDOs["wa"] = ["2"];
oi = getNuDBFnvp("qcat",5,null,tmpDOs);
alert(oi["rq"]);
doQComm(oi["rq"], null, "hmr");
*/
mf = window[theCB];
JSSHOP.ajax.doNuAjaxPipe('dvSearchBox', 'ca8n-ca.txt', mf);
};

var dds = function(theArrResp) {
alert("dds: " + theArrResp.rs);
};


var doTest = function(theCB) {

mf = window[theCB];
tmpDOs = null;
tmpDOs = {};
tmpDOs["ws"] = "where cat_coid=?";
tmpDOs["wa"] = ["2"];
oi = getNuDBFnvp("qcat",5,null,tmpDOs);
// alert(oi["rq"]);
// doQComm(oi["rq"], null, "hmr");

tac = nCurrCnxOb();
tac["lz"] = "y";
tac["q"] = oi["rq"];
tac["cb"] = "dds";
doNurQComm(tac);

};
// doTest("hmr");
