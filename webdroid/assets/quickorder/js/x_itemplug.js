 


var renderPlugItems = function(theElem, theResp, marble){
 
 
    try { spinner.stop(); } catch(e) { alert(e); }
 
tStrHtml = "";
 
if(theResp.indexOf("_id") != -1) {
// alert("renderNuTQBItems: " + theResp);
 
tmpIPlugArr = JSON.parse(theResp); 
var len = tmpIPlugArr.length;
 
tstr = "";
iint = 0;
tStrHtml = JSSHOP.shop.getPrdsFullStr("iplug", tmpIPlugArr, null, null, null);

tStrHtml += "<img alt=\"imgldr\" height=\"5px\" width=\"5px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=\" onload=\"javascript:JSSHOP.shop.setCatPrdImgs('iplug');\"  onerror=\"javascript:JSSHOP.shop.setCatPrdImgs('iplug');\">";


} else { // response has no items
        tStrHtml += "<div class=\"txtBold txtClrHdr\">" + stxt[33] + "</div>";
        tStrHtml = "<th>" + stxt[33] + "</th>";
}
newel = document.createElement('div');
// strTHhtml = "<th>ID</th><th>Title</th><th>MSRP</th><th>Price</th>";
 
 
newel.innerHTML = tStrHtml;
tmpTDQI = document.getElementById(theElem);
tmpTDQI.appendChild(newel);
 


};


 

 
var doItemPlug = function(theDivID) {
// alert("DIP");
includedContent.className = "";
tmpDOs = null;
tmpDOs = {};

if(isJApp == "y") {
tmpDOs["o"] = "random()";
} else {
tmpDOs["o"] = "rand()";
}
tmpDOs["l"] = "4";
tmpDOs["ws"] = "where i_pid=? and i_rtype=?";
tmpDOs["wa"] = [catid,"5"];
oi = getNuDBFnvp("qitem",5,null,tmpDOs);
currRQtable = "qitem";
currRQstr = oi["rq"];
doQComm(oi["rq"], theDivID, "renderPlugItems");
};





var aLoadItemPlug = function() {
};

