var tmpSQBArr=null;var tmpVitemArr=null;tmpSQBArr=[];tmpVitemArr=[];var tmpVindex=0;var tStrTbl="qitem";var tStrTDA="i_dadded";var tmpSTblIdx=0;var tStrTJO=[{quser:"u_dadded"},{qco:"c_dadded"},{qitem:"i_dadded"},{qcat:"cat_dadded"}];var strNextSynch="n";var tmpArrSynchObj=[];var doIPFnish=function(a,b,c){JSSHOP.logJSdbug("doIPFnish",arguments,tStrTbl+": nada");};var doIPConf=function(fromWhere,b,c){JSSHOP.logJSdbug("doIPConf",arguments,tStrTbl+": "+fromWhere+": "+b);try{arrDTop=JSON.parse(b);arrDQ=JSON.parse(arrDTop.data);if(fromWhere=="remote"){JSSHOP.logJSdbug("doIPConf",arguments,tStrTbl+":fromWhere == remote: will do doQAppComm");doQAppComm(arrDQ.queryString,fromWhere,"doIPFnish");}else{JSSHOP.logJSdbug("doIPConf",arguments,tStrTbl+":fromWhere !== remote: will do doQSynchComm");doQSynchComm(getShopDir("local"),arrDQ.queryString,fromWhere,"doIPFnish");}}catch(e){JSSHOP.logJSdbug("doIPConf.error",arguments,e);}};var parseImgs=function(fromWhere,b,c){var arrDToFill=null;JSSHOP.logJSdbug("parseImgs",arguments,tStrTbl+" :: response: "+b);try{arrDToFill=JSON.parse(b);var len=arrDToFill.length;var iint=0;tmpVala="";while(iint<len){ts=arrDToFill[iint];tmpVala=ts.i_img;tmpValb=ts.i_img;if((tmpValb.indexOf("lcl-")!=-1)||(tmpValb.indexOf("rem-")!=-1)){tmpVala=tmpValb.substring(4,tmpValb.length);}xdrstr=app.doImgSynch(fromWhere,tmpVala,ts._id);document.getElementById("fldChallArray").value=xdrstr;xadrstr=document.getElementById("fldChallArray").value;JSSHOP.logJSdbug("parseImgs",arguments,tStrTbl+":app return string: "+xadrstr);arrToChange=JSON.parse(xadrstr);theId=arrToChange.id;procNuUIAitem(fromWhere,"qitem","i_img",theId,tmpVala,"doIPConf");iint++;}if(fromWhere=="local"){doImgSynch("remote");}}catch(e){JSSHOP.logJSdbug("parseImgs.error",arguments,tStrTbl+":app return error!!: "+xadrstr+e);}};var doImgSynch=function(fromWhere){try{tmpDOs=null;tmpDOs={};tmpDOs.ws="where i_img like ?";if(fromWhere=="local"){JSSHOP.logJSdbug("doImgSynch",arguments,tStrTbl+":fromWhere == local: will do doQAppComm");tmpDOs.wa=["lcl-%"];oi=getNuDBFnvp("qitem",5,null,tmpDOs);doQAppComm(oi.rq,fromWhere,"parseImgs");}else{tmpDOs.wa=["rem-%"];oi=getNuDBFnvp("qitem",5,null,tmpDOs);JSSHOP.logJSdbug("doImgSynch",arguments,tStrTbl+":fromWhere == remote: will do doQSynchComm");doQSynchComm(getShopDir("local"),oi.rq,fromWhere,"parseImgs");}}catch(e){JSSHOP.logJSdbug("doImgSynch.error",arguments,tStrTbl+" : "+e);}};var doNextSynch=function(fromWhere,b,c){try{tmpSTblIdx++;adrstr="noQvalue";if(tmpSTblIdx<tStrTJO.length){JSSHOP.logJSdbug("doNextSynch",arguments,tStrTbl+":tmpSTblIdx < tStrTJO.length: "+tmpSTblIdx+" - tStrTJO.length: "+tStrTJO.length);doSynchItemsQ(fromWhere);}else{drstr=app.doWriteFile(JSON.stringify(tmpArrSynchObj));document.getElementById("fldChallArray").value=drstr;adrstr=document.getElementById("fldChallArray").value;JSSHOP.logJSdbug("doNextSynch",arguments,tStrTbl+":tmpSTblIdx > tStrTJO.length: "+tmpSTblIdx+" - tStrTJO.length: "+tStrTJO.length+" adrstr: "+adrstr);doImgSynch("local");}}catch(e){JSSHOP.logJSdbug("doNextSynch.ERROR",arguments,tStrTbl+":tmpSTblIdx > tStrTJO.length: "+tmpSTblIdx+" - tStrTJO.length: "+tStrTJO.length+" adrstr: "+adrstr);}};var fSQLoad=function(a,b,c){try{JSSHOP.logJSdbug("fSQLoad",arguments,"nada");document.getElementById(a).innerHTML=b;}catch(e){JSSHOP.logJSdbug("fSQLoad.ERROR",arguments," : "+e);}};var doILcludate=function(a,theResp,c){hasr="n";fullstr="";var arrDToFill=JSON.parse(theResp);var len=arrDToFill.length;var iint=0;var pcid=0;tstr="";currQcommsArr=null;currQcommsArr=[];while(iint<len){ts=arrDToFill[iint];JSSHOP.shared.setFrmVals(tStrTbl,ts,function(){void (0);});tmpDDOeS=null;tmpDDOeS={};tmpDDOeS.knvp=JSSHOP.shared.getFrmVals(document[tStrTbl],"noQvalue");oi=getNuDBFnvp(tStrTbl,9,null,tmpDDOeS);doQAppComm(oi.rq,"dvCoTitle","fSQLoad");iint++;tmpArrSynchObj.push(oi.rq);if(iint==len-1){JSSHOP.logJSdbug("doILcludate",arguments,tStrTbl+":iint == len - 1: "+iint+" - len: "+len);doNextSynch("local","b","c");}else{JSSHOP.logJSdbug("doILcludate",arguments,tStrTbl+":iint < len: "+iint+" - len: "+len);}iint++;}};var doRemupdate=function(a,theResp,c){hasr="n";fullstr="";var arrDToFill=null;var arrDToFill=JSON.parse(theResp);var len=arrDToFill.length;var iint=0;var pcid=0;tstr="";currQcommsArr=null;currQcommsArr=[];while(iint<len){ts=arrDToFill[iint];JSSHOP.shared.setFrmVals(tStrTbl,ts,function(){void (0);});tmpDDOeS=null;tmpDDOeS={};tmpDDOeS.knvp=JSSHOP.shared.getFrmVals(document[tStrTbl],"noQvalue");oi=getNuDBFnvp(tStrTbl,9,null,tmpDDOeS);tmpArrSynchObj.push(oi.rq);if(iint==len-1){JSSHOP.logJSdbug("doRemupdate",arguments,tStrTbl+":iint == len - 1: "+iint+" - len: "+len);doNextSynch("remote","b","c");}else{JSSHOP.logJSdbug("doRemupdate",arguments,tStrTbl+":iint < len: "+iint+" - len: "+len);}iint++;}};var getSyncRemDadded=function(a,b,c){try{tmpDOaaes=null;tmpDOaaes={};appDadded=Math.round(a);tmpCurDadded=123;if(b.indexOf(tStrTDA)!=-1){var arrToFill=null;arrToFill=JSON.parse(b);ts=arrToFill[0];remDadded=Math.round(ts[tStrTDA]);tmpDOaaes.ws="where "+tStrTDA+">?";if(appDadded==remDadded){JSSHOP.logJSdbug("getSyncRemDadded",arguments,tStrTbl+":no update needed .. remDadded: "+remDadded+" - appDadded: "+appDadded);doNextSynch("neutral","b","c");}else{if(remDadded>appDadded){tmpDOaaes.wa=[appDadded];oi=getNuDBFnvp(tStrTbl,5,null,tmpDOaaes);JSSHOP.logJSdbug("getSyncRemDadded",arguments,tStrTbl+":remDadded > .. remDadded: "+remDadded+" - appDadded: "+appDadded);doQSynchComm(getShopDir("local"),oi.rq,"dvCoTitle","doILcludate");}else{tmpDOaaes.wa=[remDadded];oi=getNuDBFnvp(tStrTbl,5,null,tmpDOaaes);JSSHOP.logJSdbug("getSyncRemDadded",arguments,tStrTbl+":appDadded > .. remDadded: "+remDadded+" - appDadded: "+appDadded);doQAppComm(oi.rq,"dvCoTitle","doRemupdate");}}}else{tmpDOaaes.wa=["12"];oi=getNuDBFnvp(tStrTbl,5,null,tmpDOaaes);JSSHOP.logJSdbug("getSyncRemDadded",arguments,tStrTbl+":tStrTDA error: "+tStrTDA+" .. remDadded: "+remDadded+" - appDadded: "+appDadded);doQAppComm(oi.rq,"dvCoTitle","doRemupdate");}}catch(e){alert("getSyncRemDadded.error: "+e);}};var getSyncLclDadded=function(a,b,c){try{if(b.indexOf(tStrTDA)!=-1){var arrToFill=null;arrToFill=JSON.parse(b);ts=arrToFill[0];lclDadded=ts[tStrTDA];tmpDOes=null;tmpDOes={};tmpDOes.c=[tStrTDA];tmpDOes.l="1";tmpDOes.o=tStrTDA+" desc";tmpDOes.ws="";tmpDOes.wa=[];oi=getNuDBFnvp(tStrTbl,5,null,tmpDOes);JSSHOP.logJSdbug("getSyncLclDadded",arguments,"lclDadded: "+lclDadded);doQSynchComm(getShopDir("local"),oi.rq,lclDadded,"getSyncRemDadded");}else{JSSHOP.logJSdbug("getSyncLclDadded",arguments,tStrTbl+": getSyncLclDadded - no dadded");}}catch(e){alert("getSyncLclDadded:error "+e);}};var doNuSynchItemsQ=function(tmpTstr,tmptTDAstr){tStrTbl=tmpTstr;tStrTDA=tmptTDAstr;tmpDOes=null;tmpDOes={};tmpDOes.c=[tStrTDA];tmpDOes.l="1";tmpDOes.o=tStrTDA+" desc";tmpDOes.ws="";tmpDOes.wa=[];oi=getNuDBFnvp(tStrTbl,5,null,tmpDOes);JSSHOP.logJSdbug("doNuSynchItemsQ",arguments,"nada");doQAppComm(oi.rq,"dvCoTitle","getSyncLclDadded");};var doSynchItemsQ=function(fromWhere){kakac=tStrTJO[tmpSTblIdx];for(var gkey in kakac){JSSHOP.logJSdbug("doSynchItemsQ",arguments,gkey+" : "+kakac[gkey]);doNuSynchItemsQ(gkey,kakac[gkey]);}};var dmyFnishCntLoad=fnishCntLoad;fnishCntLoad=function(){return dmyFnishCntLoad;};var aLoadSynch=function(){};