currPgTitle = "Using adminApp";
document.title = currPgTitle; 
 

function getNewDBDlg() {
app.doNewDBdialog(shopDir + "_p/");
}


var doCBdbttlud = function(a, b, c) {
history.go(-1);
};



function setNewDBTitle(tstrDBTtl) {
procNuUIitem("qco","c_vala",JSSHOP.shared.getFrmFieldVal("qco", "_id", 0),tstrDBTtl,"doCBdbttlud");
alert(tstrDBTtl + "database all set!\r\n. Remember to Use Web Browser Panel button to come back.");
qEncdQstring = tstrDBTtl + ":|:" + currRQtable + ":|:" + currRQstr;
// app.doDB(qEncdQstring);
}

var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {
	try {
	tmpstrGDBT = app.doStrDBTitles(); // JSI_Interface, gets list od dbtitles
	tmpclnTGC = getCleanAppStr(tmpstrGDBT);
      fnlStr = "tds: " + tmpclnTGC;
	if(tmpclnTGC == "Demo-Database") {
      fnlStr += "tds =  Demo-Database\r\n";
	
	} else { // has more db titles besides Demo-Database
	if(arrAllForms.qco.v[0].c_vala) {
	tmpDBTTL = arrAllForms.qco.v[0].c_vala;
	if(tmpDBTTL.length >= 2) {
	currRQdb = tmpDBTTL;
	if(tmpclnTGC.indexOf(tmpDBTTL) != -1) {
      qEncdQstring = currRQdb + ":|:" + currRQtable + ":|:" + currRQstr;
	// alert("qEncdQstring: " + qEncdQstring);
	app.doDB(qEncdQstring);
	} else { // tmpDBTTL not in database title list
      fnlStr += "tds not in list::\r\n";
	}
 	} else { // arrAllForms.qco.v[0].c_vala not a valid string
      fnlStr += "arrAllForms.qco.v[0].c_vala not a valid string::\r\n";
	}
	} else { // arrAllForms.qco.v[0].c_vala does not even exist
      fnlStr += "arrAllForms.qco.v[0].c_vala does not even exist::\r\n";
	}

	} // end of else has more db titles besides Demo-Database
	// alert(fnlStr);

    } catch (e) {
	alert("getAppDBReq " + e);
    }
};


 