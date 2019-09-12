var doCoList = function(a,theResp,c) {
// alert("theResp" + theResp)
hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
while(iint < len) {
ts = arrToFill[iint];
if(ts._id == ppid) {
tArr = [];
tArr.push(ts);
rFFFVs(null,JSON.stringify(tArr),null);


}
 
tstr += "<tr>";
tstr += "<td>" + ts._id + "</td>";
tstr += "<td>" + ts.c_title + "</td><td><a href=\"index.html?pid=aa-edit-cat&cid=" + ts._id + "\">Edit</a></td><td><a href=\"index.html?pid=aa-edit-cos&ppid=" + ts._id + "\">Edit</a></td>";
tstr += "<td><a href=\"index.html?pid=aa-add-qbit&cid=" + ts._id + "\">Add</a></td>";
tstr += "</tr>";
iint++;
}

if(ppid == 0) {
newel = document.createElement('div');
strTHhtml = "<th>ID</th><th>Empresa</th><th>Categoria</th><th>Editar</th><th>Adicionar</th>";
tmpFstr = getTblSortStr(strTHhtml, tstr);
document.getElementById("dvCosList").innerHTML = tmpFstr;
}

};

var doCoQbitsList = function(a,theResp,c) {
// alert("theResp" + theResp)
hasr = "n";
fullstr = "";
var arrToFill = JSON.parse(theResp);
var len = arrToFill.length;
var iint = 0;
var pcid = 0;
tstr = "";
while(iint < len) {
ts = arrToFill[iint];
 
tstr += "<tr>";
tstr += "<td>" + ts._id + "</td>";
tstr += "<td>" + ts.q_title + "</td><td>" + ts.q_pid + "</td><td><a href=\"index.html?pid=aa-add-qbit&ppid=" + ts._id + "\">Edit</a></td>";
tstr += "</tr>";
iint++;
}

newel = document.createElement('div');
strTHhtml = "<th>ID</th><th>Title</th><th>Web</th><th>Edit</th>";
tmpFstr = getTblSortStr(strTHhtml, tstr);
document.getElementById("dvCosQList").innerHTML = tmpFstr;
JSSHOP.shared.setFieldVal("tmpCtitle", JSSHOP.shared.getFrmFieldVal("qco","c_title","nada"));
JSSHOP.shared.setFieldVal("tmpCdesc", JSSHOP.shared.getFrmFieldVal("qco","c_desc","nada"));
JSSHOP.shared.setFieldVal("tmpCemail", JSSHOP.shared.getFrmFieldVal("qco","c_email","nada"));
JSSHOP.shared.setFieldVal("tmpCtel", JSSHOP.shared.getFrmFieldVal("qco","c_tel","nada"));
JSSHOP.shared.setFieldVal("tmpCweb", JSSHOP.shared.getFrmFieldVal("qco","c_web","nada"));



};

var cbFedit = function(a,b,c) {
alert(b);
document.location.href="index.html?pid=aa-edit-cos&sa=y";
};

var checkCoEdit = function() {

tc_title = document.getElementById("tmpCtitle").value;
tc_desc = document.getElementById("tmpCdesc").value;
tc_email = document.getElementById("tmpCemail").value;
tc_tel = document.getElementById("tmpCtel").value;
tc_web= document.getElementById("tmpCweb").value;

JSSHOP.shared.setFrmFieldVal("qco","c_title",tc_title);
JSSHOP.shared.setFrmFieldVal("qco","c_desc",tc_desc);
JSSHOP.shared.setFrmFieldVal("qco","c_email",tc_email);
JSSHOP.shared.setFrmFieldVal("qco","c_tel",tc_tel);
JSSHOP.shared.setFrmFieldVal("qco","c_web",tc_web);

document.getElementById("c_uid").value = quid;
document.getElementById("c_dadded").value = new Date().getTime();
tR = ["c_uid","c_rtype","c_title","c_desc","c_email","c_tel","c_web","c_dadded"];
if(ppid == 0) {
oi = getNuDBFnvp("qco",6,tR,null);
} else {
oi = getNuDBFnvp("qco",7,tR,null);
}
doQComm(oi["rq"], null, "cbFedit");
};



var aListCos = function() {
JSSHOP.loadScript("js/x_aqr.js", JSSHOP.checkLoader,"js");
tmpDOs = null;
tmpDOs = {};
// tmpDOs["c"] = "_id,c_title";
if(ppid == 0) {
tmpDOs["ws"] = "where c_uid=?";
tmpDOs["wa"] = [JSSHOP.cookies.getCookie("quid")];
oi = getNuDBFnvp("qco",5,null,tmpDOs);
doQComm(oi["rq"], null, "doCoList");
} else {
tmpDOs["ws"] = "where _id=?";
tmpDOs["wa"] = [ppid];
oi = getNuDBFnvp("qco",5,null,tmpDOs);
doQComm(oi["rq"], null, "doCoList");
tmpDOs["ws"] = "where q_pid=?";
tmpDOs["wa"] = [ppid];
oi = getNuDBFnvp("qbit",5,null,tmpDOs);
doQComm(oi["rq"], null, "doCoQbitsList");
}
// doQComm('select * from prod_options order by OptionType desc limit 500', null, "rEditProdOptions")
};