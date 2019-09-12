currPgTitle = stxt[1];
document.title = currPgTitle; 
var euiFFObjArr = null;
var euiFFObjArr = [];

var ck_ctitle = /^[A-Za-z0-9 ]{3,20}$/;




/*
* redirects on finish
*/
var fnishCatAdds = function() {
       // alert(b);
        try {
             document.location.href = "index.html?pid=aa-edit-categories&cid=" + JSSHOP.shared.getFieldVal("cat_coid", "0");

        } catch (e) {
            document.location.href = "index.html?pid=aa-edit-categories&cid=" + JSSHOP.shared.getFieldVal("cat_coid", "0");
        }

};

/*
* loops through the category title inputs and adds them to db
*/
 
var astCatEdit = function(a, b, c) {
    if (b.indexOf("_id") != -1) {
        var arrToFill = JSON.parse(b);
        var ttsi = arrToFill[0];
	  iint = 0;
	  theSelCats = JSSHOP.shared.getFrmVals(document['frmcatsgsts'], 'nada');
	  currTCTarr = [];
	  while(iint < theSelCats.length) {
           if(theSelCats[iint].v == stxt[8]) { // its default value. do nothing.
            } else {	
		JSSHOP.shared.setFrmFieldVal("qcat", "cat_title", theSelCats[iint].v);
            JSSHOP.shared.setFrmFieldVal("qcat", "cat_coid", ttsi._id);
            tmpFobj = null;
            tmpFobj = {};
            tmpFobj["knvp"] = JSSHOP.shared.getFrmVals(document["qcat"], "nada");
            oi = getNuDBFnvp("qcat", 6, null, tmpFobj);

	     doQComm(oi["rq"], null, "doNada");


 	      }
            iint++;
        }
 setTimeout("fnishCatAdds()", 600);
    } else {
        alert("astCatEdit = false: " + b);
    }
};


/*
* gets the newly added company _id from database
*/
var astMenuEdit = function(a, b, c) {
    tc_title = document.getElementById("tmpCtitle").value;
    JSSHOP.shared.setFrmFieldVal("qco", "c_title", tc_title);
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["c"] = ["_id", "c_title"];
    tmpDOs["l"] = 1;
    tmpDOs["ws"] = "where c_uid=?";
    tmpDOs["wa"] = [quid];
    oi = getNuDBFnvp("qco", 5, null, tmpDOs);
    doQComm(oi["rq"], null, "astCatEdit");
};


/*
* starts menu create
* addds menu company to database
*/
var aCreateMenu = function() {

        hasCatInput = "no";
	  thetSelCats = JSSHOP.shared.getFrmVals(document['frmcatsgsts'], 'nada');
	  currtTCTarr = [];
	  tiint = 0;
	  while(tiint < thetSelCats.length) {
           if(thetSelCats[tiint].v == stxt[8]) { // its default value. do nothing.
		} else {
             hasCatInput = "yes";
		}
            tiint++;
        }


    if(hasCatInput == "yes") {
    tc_title = document.getElementById("tmpCtitle").value;
    JSSHOP.shared.setFrmFieldVal("qco", "c_title", tc_title);
    tmpFobj = null;
    tmpFobj = {};
    tmpFobj["knvp"] = JSSHOP.shared.getFrmVals(document["qco"], "nada");
    oi = getNuDBFnvp("qco", 6, null, tmpFobj);
    doQComm(oi["rq"], null, "astMenuEdit");
    } else {
     alert(stxt[9]);
    }
};


/*
* main loader function
* prefills the qco form
*/



var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function() {

if((quid == 0) || (quid == "noQvalue")) {
document.location.href = "index.html?pid=login";
return;
}

tc_title = stxt[22] + ' ' + stxt[44];
tifo = nCurrFFieldOb();
tifo.fid = "tmpCtitle";
tifo.fdv = stxt[22] + " " + stxt[44];
tifo.lid = "lblCtitle"; 
tifo.ltxt = stxt[10]; 
tifo.fvr = ck_ctitle;
tifo.fve = stxt[1001];
euiFFObjArr.push(tifo);

tcti = nCurrFFieldOb();
tcti.fid = "inpCatTtl";
tcti.fdv = stxt[8];
tcti.lid = "lblCatTtl"; 
tcti.ltxt = stxt[8]; 
tcti.fvr = ck_ctitle;
tcti.fve = stxt[1001];
euiFFObjArr.push(tcti);


tctb = nCurrFFieldOb();
tctb.fid = "inpCatTtlb";
tctb.fdv = stxt[8];
euiFFObjArr.push(tctb);


tctc = nCurrFFieldOb();
tctc.fid = "inpCatTtlc";
tctc.fdv = stxt[8];
euiFFObjArr.push(tctc);


tctd = nCurrFFieldOb();
tctd.fid = "inpCatTtld";
tctd.fdv = stxt[8];
euiFFObjArr.push(tctd);




JSSHOP.shared.initFrmComps(euiFFObjArr);

    document.getElementById("tmpCtitle").value = tc_title;	
    JSSHOP.shared.setFrmFieldVal("qco", "c_title", tc_title);
    JSSHOP.shared.setFrmFieldVal("qco", "c_desc", "The best food at freds!");
    JSSHOP.shared.setFrmFieldVal("qco", "c_email", "your@email.co");
    JSSHOP.shared.setFrmFieldVal("qco", "c_tel", "555-5555");
    JSSHOP.shared.setFrmFieldVal("qco", "c_web", "http://192.168.42.43/evenflow/webdroid/assets/quickorder");
    JSSHOP.shared.setFrmFieldVal("qco", "c_uid", quid);
    JSSHOP.shared.setFrmFieldVal("qco", "c_dadded", JSSHOP.getUnixTimeStamp());
    JSSHOP.shared.setFrmFieldVal("qcat", "cat_uid", quid);
    JSSHOP.shared.setFrmFieldVal("qcat", "cat_dadded", JSSHOP.getUnixTimeStamp());
};
 