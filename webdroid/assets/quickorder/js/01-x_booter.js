var onlyNums=new RegExp(/^\d{10}$/);var jscssprefix="01-";var pipeDir="noQvalue";var forceCache="n";var currCacheVer="1";var timeout_handles=[];var loaded_scripts=[];var loaded_streams=[];var arrUprefs=[];var trgr_bclck=[];var canLoad="no";var pid="index_main";var ppid=0;var cid=0;var catid=0;var itemid=0;var pgpq="noQvalue";var arrDBnDocFNames=[];var arrDBnDocFNVpar=[];var arrDBFNames=[];var arrAllForms=[];var tmpSQBArr=[];var tmpVindex=0;var ntImgCtr=[];var currMresp="";var quid=0;var cartID="noQvalue";var currRQstr="noQvalue";var currRQdb="evenflow-local";var currRQtable="qitem";var currDBUGstr="";var currMenuObj;var currACTBstr="";var currMenuArr=[];var currItemArr=[];var currItemsArr=[];var currMItemsArr=[];var currProdsArr=[];var currQcommsArr=[];var currFrmQArr=[];var currPgTitle="";var currAdmnMode="n";var currImgSleep=280;var currUrlArr={};var currCnxOb={};var currFFieldOb={};var currFFinitArr=[];var currPgIndex=0;var currProdsPPg=10;var currMCollItems={};var currUserFavs="";var stxt=[];var usrlang="en_us";var actbSearch;var actbLoaded=false;var shopDir=document.location.href;path=shopDir;n=path.lastIndexOf("/");q=path.lastIndexOf("?");if(n>=0){shopDir=path.substring(0,n+1);}else{shopDir+="/";}var doNada=function(tmpa,tmpb,tmpc){};if(!window.JSSHOP){var JSSHOP=new Object();}var getFrcCacheRLoad=function(tmpDRV){if(currUrlArr.fc){return currUrlArr.fc;}else{return tmpDRV;}};try{tmpNvstr=navigator.userAgent;if(tmpNvstr.indexOf("JavaFX")!=-1){isJavaFx="yes";}}catch(e){}var nCurrFFieldOb=function(){aCurrFFieldOb=null;aCurrFFieldOb={};aCurrFFieldOb.fid="noQvalue";aCurrFFieldOb.fty="noQvalue";aCurrFFieldOb.fdc="cls_input_text";aCurrFFieldOb.ffc="cls_input_text cls_input_text_focus";aCurrFFieldOb.fdv="noQvalue";aCurrFFieldOb.fda="false";aCurrFFieldOb.fvr="noQvalue";aCurrFFieldOb.fve="noQvalue";aCurrFFieldOb.fof="noQvalue";aCurrFFieldOb.fob="noQvalue";aCurrFFieldOb.fcl="noQvalue";aCurrFFieldOb.fku="noQvalue";aCurrFFieldOb.fkd="noQvalue";aCurrFFieldOb.lid="noQvalue";aCurrFFieldOb.ltxt="noQvalue";return aCurrFFieldOb;};var nCurrCnxOb=function(){acurrCnxOb=null;acurrCnxOb={};acurrCnxOb.st="noQvalue";acurrCnxOb.fn="noQvalue";acurrCnxOb.ts="123";acurrCnxOb.fc=getFrcCacheRLoad("n");acurrCnxOb.lz="n";acurrCnxOb.ls="n";acurrCnxOb.el="noQvalue";acurrCnxOb.cb="noQvalue";acurrCnxOb.q="noQvalue";acurrCnxOb.ur=shopDir+"_p/do.php?";acurrCnxOb.rs="noQvalue";acurrCnxOb.er="noQvalue";acurrCnxOb.ui="noQvalue";return acurrCnxOb;};currCnxOb=nCurrCnxOb();currFFielOb=nCurrFFieldOb();function getNuLclStrg(lsObj,lsKey,lsDefVal){try{if(localStorage[lsKey]){return localStorage[lsKey];}else{return lsDefVal;}}catch(e){return lsDefVal;}}function clearNuLclStrg(lsObj,lsKey){try{if(typeof(Storage)!=="undefined"){localStorage.removeItem(lsKey);}}catch(e){alert("clearNuLclStrg: "+e);}}function setNuLclStrg(lsObj,lsKey,lsVal){try{if(typeof(Storage)!=="undefined"){localStorage[lsKey]=lsVal;}}catch(e){}}function getLclStrg(lsName,lsDefVal){if(localStorage[lsName]){return localStorage[lsName];}else{return lsDefVal;}}function clearLclStrg(lsName){if(typeof(Storage)!=="undefined"){localStorage.removeItem(lsName);}}function setLclStrg(lsName,lsVal){if(typeof(Storage)!=="undefined"){localStorage[lsName]=lsVal;}}var doPopItemMod=function(){loadJSModal("tplates/aa-mod-show-item.html?tt="+JSSHOP.getUnixTimeStamp());};var doPopCartMod=function(){loadJSModal("tplates/aa-mod-show-cartadd.html?tt="+JSSHOP.getUnixTimeStamp());};var doCAshow=function(theObj){JSSHOP.ui.setCBBClickClr(document.getElementById(theObj),"crsrPointer icndbtn slmtable bkgdClrTtl brdrClrHdr txtClrHdr fltrImgInvClr","crsrPointer icndbtn",function(){void (0);});JSSHOP.shared.setFrmVals("qitem",tmpVitemArr[tmpVindex],function(){doCartAddPop();});};var setPopItemMod=function(tmpIPopArr){setCurrItemArr(tmpIPopArr);JSSHOP.shared.setFrmVals("qitem",tmpIPopArr,function(){doPopItemMod();});};var setCurrItemArr=function(tmpIarr){currItemArr=null;currItemArr=tmpIarr;};var getCurrItemArr=function(){return currItemArr;};var setCurrItemsArr=function(tmpIarr){currItemsArr=null;currItemsArr=tmpIarr;};var getCurrItemsArr=function(){return currItemsArr;};var setCurrMItemsArr=function(tmpIarr){currMItemsArr=null;currMItemsArr=tmpIarr;};var getCurrMItemsArr=function(){return currMItemsArr;};var getCurrUrl=function(){var strTurl="noQvalue";strCurl=document.location.href;if(strCurl.indexOf("?")!=-1){strTurl=strCurl.substring(strCurl.indexOf("?")+1);}if(isPhP=="no"){try{JSSHOP.loadScript("css/"+jscssprefix+"x_japp.css",JSSHOP.checkLoader,"css");strTurl=app.getCurrPageVars("nada");document.getElementById("fldChallArray").value=strTurl;strTurl=document.getElementById("fldChallArray").value;}catch(e){alert("getCurrUrl: "+e);}}return strTurl;};try{cartID=app.fetchConfValString("cartID");isJApp="y";}catch(e){}var pfRet=function(theElem,theResp,marble){document.getElementById(theElem).innerHTML=theResp;};var doCloseAdd=function(theElem,theResp,marble){fullResp='<div onclick="JSSHOP.ui.closeLbox();" style="float:right">Close</div>'+theResp;document.getElementById(theElem).innerHTML=fullResp;};var loadNuJSModal=function(theMinc,theClass){spinner.spin(lightbox_content);if(theMinc.indexOf("images/")!=-1){JSSHOP.ui.popAndFillLbox('<img src="'+theMinc+'">');}else{JSSHOP.ajax.doNuAjaxPipe("lightbox_content",theMinc,doCloseAdd);JSSHOP.ui.popAndFillLbox(theClass);}};var loadJSModal=function(theMinc){loadNuJSModal(theMinc,"noQvalue");};var getShopDir=function(locorrem){tmpRetVal="noQvalue";tmpShopDir="noQvalue";if(locorrem=="local"){tmpShopDir=document.location.href;tmpPath=tmpShopDir;n=tmpPath.lastIndexOf("/");q=tmpPath.lastIndexOf("?");if(n>=0){tmpShopDir=tmpPath.substring(0,n+1);}else{tmpShopDir+="/";}}else{tmpShopDir=JSSHOP.shared.getFrmFieldVal("qco","c_web","noQvalue");}tmpRetVal=tmpShopDir;return tmpRetVal;};JSSHOP.logJSdbug=function(theFunction,theArgs,theMsg){tmpStrArgs="noQvalue";tmpAStrArgs="noQvalue";try{tmpStrArgs=JSON.stringify(theArgs);}catch(e){tmpStrArgs=theArgs;}if(tmpStrArgs.length>12){tmpAStrArgs='<span onclick="javascript:alert(this.innerHTML);" style="max-width:120px;max-height:90px;overflow:hidden;">'+tmpStrArgs+"</span>";}else{tmpAStrArgs=tmpStrArgs;}currDBUGstr+="<br><br>: "+JSSHOP.getUnixTimeStamp()+" :: "+theFunction+" :: "+tmpAStrArgs+" :: "+theMsg;};JSSHOP.logJSerror=function(theError,theArgs,theMsg){fullArgs=new Array();if(theArgs.length){fullArgs=theArgs;fullArgs=JSON.stringify(theArgs);}else{fullArgs="noQvalue";}fullSError=JSON.stringify(theError);if(theError.lineNumber){fullSError+="Line: "+theError.lineNumber+"\r\n";}if(theError.fileName){fullSError+="File: "+theError.fileName+"\r\n";}if(theError.msg){fullSError+="msg: "+theError.msg+"\r\n";}if(theError.constructor){fullSError+=theError.constructor;}setTimeout(function(){ermsg="epconsole.error: "+fullSError+" :: "+fullArgs+" :: "+theMsg;throw new Error(ermsg);},0);};var getMainSearch=function(theSstr){document.location.href="index.html?pid=aa-show-search&cid="+cid+"&sw="+theSstr;};JSSHOP.startNuIntrvlEvnt=function(theObjTag,theFunction,theInterval){try{eval(theObjTag+" = "+window.setInterval(theFunction,theInterval));}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.startNuIntrvlEvnt");}};JSSHOP.stopNuIntrvlEvnt=function(theObjTag){try{window.clearInterval(theObjTag);theObjTag=null;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.stopNuIntrvlEvnt");}};JSSHOP.startIntervalEvent=function(theObjTag,theFunction,theInterval){try{timeout_handles[theObjTag]=window.setInterval(theFunction,theInterval);}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.startIntervalEvent");}};JSSHOP.stopIntervalEvent=function(theObjTag){try{window.clearInterval(timeout_handles[theObjTag]);timeout_handles[theObjTag]=null;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.stopIntervalEvent");}};JSSHOP.getUnixMiliStamp=function(){try{ts=Math.round(new Date().getTime());return ts;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.getUnixTimeStamp");}};JSSHOP.getUnixTimeStamp=function(){try{ts=Math.round(new Date().getTime()/1000);return ts;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.getUnixTimeStamp");}};JSSHOP.loadScript=function(path,callback,filetype){n=path.lastIndexOf("/");q=path.lastIndexOf("?");if(filetype=="js"){var scr=document.createElement("script");scr.setAttribute("type","text/javascript");scr.src=path;}else{if(filetype=="css"){var scr=document.createElement("link");scr.setAttribute("rel","stylesheet");scr.setAttribute("type","text/css");scr.href=path;}}var done=false;scr.onload=handleLoad;scr.onreadystatechange=handleReadyStateChange;scr.onerror=handleError;if(n>=0){if(q>=0){tid=path.substring(n+1,q);}else{tid=path.substring(n+1);}scr.id=tid;}document.getElementsByTagName("head")[0].appendChild(scr);function handleLoad(){if(!done){done=true;callback(path,"ok");}}function handleReadyStateChange(){var state;if(!done){state=scr.readyState;if(state==="complete"){handleLoad();}}}function handleError(){if(!done){done=true;callback(path,"error");}}try{}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.shared.loadScript");}};JSSHOP.checkLoader=function(thePath,theMessage){try{ttlLoaded=loaded_scripts.length;loaded_scripts[ttlLoaded]=thePath;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.shared.checkLoader");}};if(!window.JSSHOP.cookies){JSSHOP.cookies=new Object();}JSSHOP.cookies.getCookie=function(check_name){var cretval;var tval;if((isPhP=="no")||(isJavaFx=="yes")){try{if(check_name=="quid"){tval=app.fetchConfValInt(check_name);}else{tval=app.fetchConfValString(check_name);}cretval=tval;if(cretval=="noQvalue"){return null;}else{return cretval;}}catch(e){alert("getCookie: "+e);return null;}}else{var a_all_cookies=document.cookie.split(";");var a_temp_cookie="";var cookie_name="";var cookie_value="";var b_cookie_found=false;for(i=0;i<a_all_cookies.length;i++){a_temp_cookie=a_all_cookies[i].split("=");cookie_name=a_temp_cookie[0].replace(/^\s+|\s+$/g,"");if(cookie_name==check_name){b_cookie_found=true;if(a_temp_cookie.length>1){cookie_value=unescape(a_temp_cookie[1].replace(/^\s+|\s+$/g,""));}return cookie_value;break;}a_temp_cookie=null;cookie_name="";}if(!b_cookie_found){return null;}}};JSSHOP.cookies.setCookie=function(name,value,expires,path,domain,secure){if((isPhP=="no")||(isJavaFx=="yes")){try{if(name=="quid"){app.setConfValInt(name,value);}else{app.setConfValString(name,value);}}catch(e){alert("setCookie.E: "+e);}}else{var today=new Date();today.setTime(today.getTime());if(expires){expires=expires*1000*60*60*24;}var expires_date=new Date(today.getTime()+(expires));document.cookie=name+"="+escape(value)+((expires)?";expires="+expires_date.toGMTString():"")+((path)?";path="+path:"")+((domain)?";domain="+domain:"")+((secure)?";secure":"");}};JSSHOP.cookies.deleteCookie=function(name,path,domain){if((isPhP=="no")||(isJavaFx=="yes")){try{if(name=="quid"){app.setConfValInt(name,0);}else{app.setConfValString(name,"noQvalue");}}catch(e){alert("setCookie.E: "+e);}}else{if(JSSHOP.cookies.getCookie(name)){document.cookie=name+"="+((path)?";path="+path:"")+((domain)?";domain="+domain:"")+";expires=Thu, 01-Jan-1970 00:00:01 GMT";}}};/� shared functions */;if(!window.JSSHOP.shared){JSSHOP.shared=new Object();}JSSHOP.shared.endsWith=function(str,suffix){return str.indexOf(suffix,str.length-suffix.length)!==-1;};JSSHOP.shared.urlToArray=function(url){try{var request={};var arr=[];var pairs=url.substring(url.indexOf("?")+1).split("&");for(var i=0;i<pairs.length;i++){var pair=pairs[i].split("=");if(JSSHOP.shared.endsWith(decodeURIComponent(pair[0]),"[]")){var arrName=decodeURIComponent(pair[0]).substring(0,decodeURIComponent(pair[0]).length-2);if(!(arrName in arr)){arr.push(arrName);arr[arrName]=[];}arr[arrName].push(decodeURIComponent(pair[1]));request[arrName]=arr[arrName];}else{request[decodeURIComponent(pair[0])]=decodeURIComponent(pair[1]);}}return request;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.shared.urlToArray");return"noQvalue";}};if(!window.JSSHOP.user){JSSHOP.user=new Object();}JSSHOP.user.decPrefCky=function(cString){try{strPa=cString.split("x1").join('[{"');strPb=strPa.split("x2").join('":"');strPc=strPb.split("x3").join('","');strPd=strPc.split("x4").join('":');strPe=strPd.split("x5").join(',"');strPf=strPe.split("x6").join('"}]');strPg=strPf.split("x7").join("}]");return strPg;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.user.decPrefCky");return"noQvalue";}};JSSHOP.user.encPrefCky=function(cString){try{strPa=cString.split('[{"').join("x1");strPb=strPa.split('":"').join("x2");strPc=strPb.split('","').join("x3");strPd=strPc.split('":').join("x4");strPe=strPd.split(',"').join("x5");strPf=strPe.split('"}]').join("x6");strPg=strPf.split("}]").join("x7");return strPg;}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.user.encPrefCky");return"noQvalue";}};JSSHOP.user.setCkieUprefs=function(ckyP){try{if(JSSHOP.cookies.getCookie(ckyP)){JSSHOP.cookies.deleteCookie(ckyP,"","");}JSSHOP.cookies.setCookie(ckyP,JSSHOP.user.encPrefCky(JSON.stringify(arrUprefs[ckyP])),"30","","","");}catch(e){alert(e);JSSHOP.logJSerror(e,arguments,"JSSHOP.user.setCkieUprefs");}};JSSHOP.user.setCkiePrfKV=function(dCky,key,val){try{arrUprefs[dCky][0][key]=val;JSSHOP.user.setCkieUprefs(dCky);}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.user.setCkiePrfKV");}};JSSHOP.user.setCkiePrfDispVal=function(ckyName,key,rowname){try{val=false;theRow=document.getElementById(rowname);if(theRow.style.display=="none"){val=true;}arrUprefs[ckyName][0][key]=val;JSSHOP.user.setCkieUprefs(ckyName);}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.user.setCkiePrfDispVal");}};JSSHOP.user.doCkieUprefs=function(daCky){try{if(JSSHOP.cookies.getCookie(daCky)){fldChallArray.value=JSSHOP.cookies.getCookie(daCky);tval=fldChallArray.value;arrUprefs[daCky]=JSON.parse(JSSHOP.user.decPrefCky(tval));}else{arrUprefs[daCky]=[{prfDspLmenu:false,scv:"g",sAL:"y",sAT:"y"}];JSSHOP.user.setCkieUprefs(daCky);}}catch(e){JSSHOP.logJSerror(e,arguments,"JSSHOP.user.doCkieUprefs");}};var getNuDBFnvp=function(t,m,da,de){icce=0;tmpV=[];tmpFV=[];xol=null;xol={};xol.m=m;xol.t=t;xol.c=null;xol.o="_id Desc";xol.l=100;xol.knvp=null;if(de!=null){if(de.ws!=null){xol.ws=de.ws;}if(de.wa!=null){xol.wa=de.wa;}if(de.o!=null){xol.o=de.o;}if(de.l!=null){xol.l=de.l;}if(de.c!=null){xol.c=de.c;}if(de.knvp!=null){xol.knvp=de.knvp;}}else{xol.ws="where _id=?";xol.wa=[ppid];}if(xol.knvp==null){tlen=arrDBnDocFNames.length;if(tlen>0){}else{nint=0;if(da!=null){while(nint<da.length){arrDBnDocFNames.push(da[nint]);arrDBFNames.push(da[nint]);nint++;}}}arrDBnDocFNVpar=null;arrDBnDocFNVpar=[];while(icce<arrDBnDocFNames.length){nnvo={};nvk=arrDBnDocFNames[icce];nvl=document.getElementById(arrDBnDocFNames[icce]).value;nnvo.t=nvk;nnvo.v=nvl;tmpStrSWA="";arrDBnDocFNVpar.push(nnvo);icce++;}xol.knvp=null;xol.knvp=arrDBnDocFNVpar;}len=xol.knvp.length;arrDBFNames._id=ppid;if(xol.ws!=null){tmpStrSWA=switchOccurrences(xol.ws,"?",xol.wa);}sqs="";switch(m){case 5:sqs="select ";if(xol.c!=null){if(xol.c.length>0){sqs+=xol.c+" ";}else{sqs+="* ";}}else{sqs+="* ";}sqs+="from "+t+" "+tmpStrSWA+" order by "+xol.o+" limit "+xol.l;break;case 6:var iint=0;ts="";tv="";ark=[];while(iint<len){if(xol.knvp[iint].t=="_id"){}else{ts+=xol.knvp[iint].t+",";theVstrClean=xol.knvp[iint].v;tv+="'"+theVstrClean+"',";}iint++;}sqs="insert into "+t+"("+ts.substring(0,ts.length-1)+") values ("+tv.substring(0,tv.length-1)+")";break;case 7:var iint=0;ts="";tv="";ark=[];while(iint<len){if(xol.knvp[iint].t=="_id"){}else{ts+=xol.knvp[iint].t+"=";theVstrClean=decodeURIComponent(xol.knvp[iint].v);ts+="'"+theVstrClean+"',";}iint++;}sqs="update "+t+" set "+ts.substring(0,ts.length-1)+" "+tmpStrSWA;break;case 8:sqs="delete from "+t+" "+tmpStrSWA;break;case 9:var iint=0;ts="";tv="";ark=[];while(iint<len){ts+=xol.knvp[iint].t+",";theVstrClean=xol.knvp[iint].v;tv+="'"+theVstrClean+"',";iint++;}sqs="replace into "+t+" values ("+tv.substring(0,tv.length-1)+")";iint=0;break;default:sqs="select";}xol.rq=sqs;return xol;};var doFrmQArr=function(strQ,theElem,theCB){nnvo=null;nnvo={};nnvo.f=theCB;nnvo.v=strQ;nnvo.e=theElem;currFrmQArr.push(nnvo);};var addFrmQArr=function(theForm,theTmpId,theTmpCB){tmpDOs=null;tmpDOs={};tmpDOs.ws="where _id=?";tmpDOs.wa=[theTmpId];oi=getNuDBFnvp(theForm,5,null,tmpDOs);doFrmQArr(oi.rq,theForm,theTmpCB);};var addNuFrmQArr=function(theForm,theTmpFld,theTmpId,theTmpCB){tmpDOs=null;tmpDOs={};tmpDOs.ws="where "+theTmpFld+"=?";tmpDOs.wa=[theTmpId];oi=getNuDBFnvp(theForm,5,null,tmpDOs);doFrmQArr(oi.rq,theForm,theTmpCB);};var doDynQArrComm=function(theTarr,strQ,theElem,theCB){nnvo=null;nnvo={};nnvo.f=theCB;nnvo.v=strQ;nnvo.e=theElem;theTarr.push(nnvo);};var doQArrComm=function(strQ,theElem,theCB){nnvo=null;nnvo={};nnvo.f=theCB;nnvo.v=strQ;nnvo.e=theElem;currQcommsArr.push(nnvo);};var addQArrComm=function(theForm,theTmpId,theTmpCB){tmpDOs=null;tmpDOs={};tmpDOs.ws="where _id=?";tmpDOs.wa=[theTmpId];oi=getNuDBFnvp(theForm,5,null,tmpDOs);doQArrComm(oi.rq,theForm,theTmpCB);};var getCleanAppStr=function(theDASStr){document.getElementById("fldChallArray").value=theDASStr;newCleanAppStr=document.getElementById("fldChallArray").value;return newCleanAppStr;};var pfDRet=function(theElem,theResp,marble){document.getElementById("fldChallArray").value=theResp;aresp=document.getElementById("fldChallArray").value;alert("pfDRet "+aresp);};navigator.sayswho=(function(){var ua=navigator.userAgent,tem,M=ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i)||[];if(/trident/i.test(M[1])){tem=/\brv[ :]+(\d+)/g.exec(ua)||[];return"IE "+(tem[1]||"");}if(M[1]==="Chrome"){tem=ua.match(/\b(OPR|Edge)\/(\d+)/);if(tem!=null){return tem.slice(1).join(" ").replace("OPR","Opera");}}M=M[2]?[M[1],M[2]]:[navigator.appName,navigator.appVersion,"-?"];if((tem=ua.match(/version\/(\d+)/i))!=null){M.splice(1,1,tem[1]);}return M.join(" ");})();var remp=function(src){var script=document.createElement("script");script.type="text/javascript";script.async=true;script.src=src;document.getElementsByTagName("head")[0].appendChild(script);};var fnishCntLoad=function(){};var fnishCoForm=function(){};var fnishUserForm=function(){};var fnishCatForm=function(){};var fnishItemForm=function(){};var fnishExtrasForm=function(){};var fnishCartForm=function(){};var pushActbArr=function(theArr){};var clearActbArr=function(){};var mfnishCntLoad=function(){if(currAdmnMode=="y"){tmpStrLgotxt="Admin";}else{tmpStrLgotxt=JSSHOP.shared.getFieldVal("c_title","QuickOrder");}JSSHOP.ui.setTinnerHTML("ancLogoTxt",tmpStrLgotxt);xae=document.getElementsByTagName("ti");var iint=0;while(iint<xae.length){nuDW(xae[iint]);iint++;}fnishCntLoad();};var mfnishCoForm=function(){fnishCoForm();};var mfnishUserForm=function(){fnishUserForm();};var mfnishCatForm=function(){fnishCatForm();};var mfnishItemForm=function(){fnishItemForm();};var mfnishExtrasForm=function(){fnishExtrasForm();};var mfnishCartForm=function(){fnishCartForm();};var mpushActbArr=function(tmpActArr){pushActbArr(tmpActArr);};var mclearActbArr=function(){clearActbArr();};var doNurQComm=function(tComObj){if(pipeDir=="noQvalue"){if(isPhP=="no"){try{atmpArrQ=app.getNuDBselectQ(tComObj.q);document.getElementById("fldChallArray").value=atmpArrQ;tmpArrQ=document.getElementById("fldChallArray").value;tmpNewArrO=null;tmpNewArrO={};tmpNewArrO=JSON.parse(tmpArrQ);var nNAxObj=null;nNAxObj=tComObj;nNAxObj.rs=tmpNewArrO.data;if(tComObj.el=="give"){return tmpArrQ;}else{mf=window[tComObj.cb];mf(nNAxObj);}}catch(e){alert(e);}}else{tmpArrQ=JSSHOP.ajax.doRequestPrep(tComObj);}}else{tComObj.ur=pipeDir+"_p/do.php?";tmpArrQ=JSSHOP.ajax.doRequestPrep(tComObj);}};var doNuQComm=function(strCQname,isRefrsh,dstamp,strQ,theElem,theCB){tmpArrQ="noQvalue";tmpQstr="";tmpQstr+="t="+dstamp+"&";tmpQstr+="f="+isRefrsh+"&";if(strCQname=="noQvalue"){}else{tmpQstr+="c="+strCQname+"&";}tmpQstr+="q="+JSSHOP.shared.utf8_encode(strQ);if(pipeDir=="noQvalue"){if(isPhP=="no"){try{atmpArrQ=app.getDBselectQ(strQ);document.getElementById("fldChallArray").value=atmpArrQ;tmpArrQ=document.getElementById("fldChallArray").value;tmpNewArr=null;tmpNewArr=[];tmpNewArr=JSON.parse(tmpArrQ);if(theElem=="give"){return tmpArrQ;}else{mf=window[theCB];mf(theElem,tmpArrQ,null);}}catch(e){rstr=shopDir+"_p/jsdo.php?cb="+theCB+"&"+tmpQstr;remp(rstr);}}else{mf=window[theCB];tmpArrQ=JSSHOP.ajax.doNuAjaxPipe(theElem,shopDir+"_p/do.php?"+tmpQstr,mf);}}else{mf=window[theCB];tmpArrQ=JSSHOP.ajax.doNuAjaxPipe(theElem,pipeDir+"_p/do.php?"+tmpQstr,mf);}};var doQComm=function(strQ,theElem,theCB){doNuQComm("noQvalue","n","123",strQ,theElem,theCB);};var doQSynchComm=function(strHst,strQ,theElem,theCB){try{mf=window[theCB];tmpArrQ=JSSHOP.ajax.doNuAjaxPipe(theElem,strHst+"_p/do.php?q="+JSSHOP.shared.utf8_encode(strQ),mf);}catch(e){alert("doQSynchComm; "+e);}};var doQAppComm=function(strQ,theElem,theCB){try{atmpArrQ=app.getDBselectQ(strQ);document.getElementById("fldChallArray").value=atmpArrQ;tmpArrQ=document.getElementById("fldChallArray").value;if(theElem=="give"){return tmpArrQ;}else{mf=window[theCB];mf(theElem,tmpArrQ,null);}}catch(e){alert("Ooops, this should be done in our Android App: "+e);}};var getQArr=function(tType,strQ){if(isPhP=="no"){tmpArrQ=app.getDBselectQ(strQ);document.getElementById("fldChallArray").value=tmpArrQ;tmpArrQ=document.getElementById("fldChallArray").value;pfDRet(null,tmpArrQ,null);}else{isJ=JSSHOP.ajax.doNuAjaxPipe(null,"do.php?q="+strQ,pfDRet);}};var getPageMContArr=function(mType,mID){switch(mType){case 5:q="select * from users where _id = "+mID;break;default:}getQArr(mType,q);};var getPVname=function(name){if(name=(new RegExp("[?&]"+encodeURIComponent(name)+"=([^&]*)")).exec(location.search)){return decodeURIComponent(name[1]);}};var loadLmenu=function(){var tmpmainUL=document.createElement("ul");tmpmainUL.className="animenu__nav";tmpLI=document.createElement("li");tmpLI.className="omenuartigo";tmpA=document.createElement("a");linkText=document.createTextNode("Recent");tmpA.appendChild(linkText);tmpA.title="Recent";tmpLI.appendChild(tmpA);tmpRPUL=document.createElement("ul");tmpLI.appendChild(tmpRPUL);tmpmainUL.appendChild(tmpLI);tmpLI=document.createElement("li");tmpLI.className="omenuartigo";tmpA=document.createElement("a");linkText=document.createTextNode("Searches");tmpA.title="Searches";tmpLI.appendChild(tmpA);tmpRSUL=document.createElement("ul");tmpLI.appendChild(tmpRSUL);tmpmainUL.appendChild(tmpLI);strCatID="tip:ep:Smart Autocomplete|";strCatName="This will be a smart auto-complete search box.|";strULPID="";strULPTtl="";strULSID="";strULSTtl="";fullIDstr="";fullIDttl="";if(arrAllForms.qextras){theLMarr=arrAllForms.qextras;len=theLMarr.length;tstr="";iint=0;inop=0;inos=0;var tmpSTrSorE="show";if(currAdmnMode=="y"){tmpSTrSorE="edit";}while(iint<len){ts=null;ts=theLMarr[iint];switch(ts.e_rtype){case"10":strLML="index.html?pid=aa-"+tmpSTrSorE+"-item&itemid="+ts.e_vala+"&cid="+ts.e_valb+"&catid="+ts.e_valc;strLMTtl=ts.e_vald;if(inop<8){tmpLI=document.createElement("li");tmpLI.className="omenuartigo";tmpA=document.createElement("a");linkText=document.createTextNode(strLMTtl);tmpA.appendChild(linkText);tmpA.title=strLMTtl+"&nbsp;&nbsp;&nbsp;&nbsp;";tmpA.href=strLML;tmpLI.appendChild(tmpA);tmpRPUL.appendChild(tmpLI);}inop++;break;case"11":strLML="index.html?pid=aa-show-search&cid="+ts.e_valb+"&sw="+ts.e_vala;strLMTtl=ts.e_vala;tmpLI=document.createElement("li");tmpLI.className="omenuartigo";tmpA=document.createElement("a");linkText=document.createTextNode(strLMTtl);tmpA.appendChild(linkText);tmpA.title=strLMTtl+"&nbsp;&nbsp;&nbsp;&nbsp;";tmpA.href=strLML;tmpLI.appendChild(tmpA);tmpRSUL.appendChild(tmpLI);inos++;break;default:break;}iint++;}}tmpMColStr=doCollsLoad();if(document.getElementById("mmDdown")){document.getElementById("mmDdown").innerHTML="";document.getElementById("mmDdown").innerHTML=tmpMColStr;document.getElementById("tdLMenu").innerHTML="";tmpDV=document.createElement("div");tmpDV.className="collection collectionbrdr";tmpDV.innerHTML=tmpMColStr;document.getElementById("tdLMenu").appendChild(tmpDV);}};var setLoadACTB=function(theACb){strCatID="tip:ep:Smart Autocomplete|";strCatName="This will be a smart auto-complete search box.|";strULPID="";strULPTtl="";strULSID="";strULSTtl="";fullIDstr="";fullIDttl="";var tmpSTrSorE="show";if(theACb.rs){var arrToFill=null;arrToFill=JSON.parse(theACb.rs);arrAllForms.qextras=arrToFill;if(currAdmnMode=="y"){tmpSTrSorE="edit";}len=arrToFill.length;tstr="";iint=0;while(iint<len){ts=arrToFill[iint];tsDTtle=ts.e_vald;tsATtle=ts.e_vala;if(tsDTtle.length>20){tsDTtle=tsDTtle.substring(0,16)+"...";}if(tsATtle.length>20){tsATtle=tsATtle.substring(0,16)+"...";}switch(ts.e_rtype){case"10":tULPID="index.html?pid=aa-"+tmpSTrSorE+"-item&itemid="+ts.e_vala+"&cid="+ts.e_valb+"&catid="+ts.e_valc;strULPID+="ulp:ep:"+tULPID+"|";strULPTtl+=ts.e_vald+"|";if(iint<6){currUserFavs+='<a href="'+tULPID+'">'+tsDTtle+"</a><br>";}break;case"11":strULSID+="uls:ep:"+ts.e_vala+"|";strULSTtl+=ts.e_vala+"|";currUserFavs+='<a href="'+ts.e_vala+'">'+tsATtle+"</a><br>";break;default:break;}iint++;}fullIDstr=strULSID+":ea:"+strULPID;fullIDttl=strULSTtl+":ea:"+strULPTtl;}strCatID+=fullIDstr+":ea:";strCatName+=fullIDttl+":ea:";strCatID+="tip:ep:Suggestions|";strCatName+="Suggestionsss are gathered from existing page arrays.|";currACTBstr=strCatID+"::"+strCatName;if(pid=="aa-show-category"){}else{try{actbSearch=loadListACTB(currACTBstr,"ijUFeedSearch");}catch(e){alert("doLoadACTB: "+e);}}loadLmenu();};var doLoadACTB=function(){tmpDOs=null;tmpDOs={};tmpDOs.l="30";tmpDOs.ws="where e_uid=?";tmpDOs.wa=[quid];oi=getNuDBFnvp("qextras",5,null,tmpDOs);atac=null;atac=nCurrCnxOb();atac.q=oi.rq;atac.cb="setLoadACTB";atac.ls="localStorage";doNurQComm(atac);};var doCntLoad=function(a,b,c){try{document.getElementById(a).innerHTML=b;}catch(e){alert("doCntLoad.error: "+a+" : "+e);}};var fnish=function(a,b,c){accA=null;accA=[];tmpAfnish=null;tmpAfnish=[];tmpAfnish=JSON.parse(b);if(isJApp=="no"){accA=tmpAfnish;}else{accA=tmpAfnish[0];}for(var gkey in accA){try{mf=window[accA[gkey].f];mf(accA[gkey].e,JSON.stringify(accA[gkey].v),null);}catch(e){alert("fnish; "+e);}}};var bootACTB=function(theElem,theResp,marble){document.getElementById(theElem).innerHTML=theResp;JSSHOP.loadScript("js/"+jscssprefix+"js_actb.js",doLoadACTB,"js");JSSHOP.ui.toggleVisibility("dvSearchBoxSlim");};var doWinResizeE=function(){document.getElementById("dvSearchBoxSlim").innerHTML="";document.getElementById("dvSearchBox").innerHTML="";tmpSBox="dvSearchBoxSlim";if(getViewportWidth()>500){tmpSBox="dvSearchBox";}JSSHOP.ajax.doNuAjaxPipe(tmpSBox,"tplates/aa-cmp-search.html",bootACTB);};var doWinResizeA=function(){tmpSIobj=document.getElementById("ijUFeedSearch");if((tmpSIobj===document.activeElement)||(isJApp=="y")){}else{}};var finishCntLoad=function(a,b,c){try{document.getElementById(a).innerHTML=b;doWinResizeE();setTimeout("mfnishCntLoad()",500);}catch(e){alert("finishCntLoad.error: "+a+" : "+e);}};var doMainFooter=function(a,b,c){document.getElementById(a).innerHTML=b;JSSHOP.ajax.doNuAjaxPipe("includedFooter","tplates/"+fCa[3]+".html",finishCntLoad);doMMenuLd();};var doMainContent=function(a,b,c){document.getElementById(a).innerHTML=b;fCa=getFArr();if((pid.indexOf("edit-")!=-1)&&((quid==0)||(quid=="noQvalue"))){JSSHOP.ajax.doNuAjaxPipe("includedContent","tplates/login.html",doMainFooter);JSSHOP.loadScript("js/"+jscssprefix+"x_login.js",JSSHOP.checkLoader,"js");}else{if((quid==0)||(quid=="noQvalue")){JSSHOP.ajax.doNuAjaxPipe("includedContent","tplates/"+pid+".html",doMainFooter);}else{if(pid=="index_main"){pid="aa-edit-shops";JSSHOP.loadScript("js/"+jscssprefix+"x_aa-edit-shops.js",JSSHOP.checkLoader,"js");JSSHOP.ajax.doNuAjaxPipe("includedContent","tplates/aa-edit-shops.html",doMainFooter);}else{JSSHOP.ajax.doNuAjaxPipe("includedContent","tplates/"+pid+".html",doMainFooter);}}}};var doMainNav=function(a,b,c){document.getElementById(a).innerHTML=b;fCa=getFArr();JSSHOP.ajax.doNuAjaxPipe("includedNav","tplates/"+fCa[1]+".html",doMainContent);};var doMainForms=function(){try{JSSHOP.ajax.doNuAjaxPipe("includedForms","tplates/index_forms.html",doMainNav);}catch(e){alert("doMainForms: "+e);}};var fillMFormArr=function(a,b,c){try{if(arrAllForms.qco){JSSHOP.shared.setFrmVals("qco",arrAllForms.qco.v[0],function(){void (0);});}if(arrAllForms.quser){JSSHOP.shared.setFrmVals("quser",arrAllForms.quser.v[0],function(){void (0);});}if(arrAllForms.qcat){JSSHOP.shared.setFrmVals("qcat",arrAllForms.qcat.v[0],function(){void (0);});}if(arrAllForms.qitem){JSSHOP.shared.setFrmVals("qitem",arrAllForms.qitem.v[0],function(){void (0);});}if(arrAllForms.qextras){JSSHOP.shared.setFrmVals("qextras",arrAllForms.qextras.v[0],function(){void (0);});}if(arrAllForms.qcartitem){JSSHOP.shared.setFrmVals("qcartitem",arrAllForms.qcartitem.v[0],function(){void (0);});}}catch(e){alert("fillMFormArr: "+e);}};var setMFormArr=function(theRobj){try{tmpAforms=[];tmpAforms=JSON.parse(theRobj.rs);arrAllForms=tmpAforms;}catch(e){}doMainForms();};var doFrmQLoad=function(thePath,theMessage,thearr){try{doCFrmQ=nCurrCnxOb();doCFrmQ.q="batch"+JSON.stringify(currFrmQArr);doCFrmQ.cb="setMFormArr";doNurQComm(doCFrmQ);}catch(e){alert("doFrmQLoad; "+e+" "+doCFrmQ.q);doMainForms();}};var doIPGLoad=function(thePath,theMessage,thearr){JSSHOP.loadScript("js/"+jscssprefix+"x_itemplug.js",doFrmQLoad,"js");};var doMenuLoad=function(thePath,theMessage,thearr){JSSHOP.loadScript("js/"+jscssprefix+"x_menu.js",doIPGLoad,"js");};var doSpinLoad=function(thePath,theMessage,thearr){JSSHOP.loadScript("js/"+jscssprefix+"js_spinner.js",doMenuLoad,"js");};var doPidLoad=function(thePath,theMessage,thearr){JSSHOP.loadScript("js/"+jscssprefix+"x_"+pid+".js",doSpinLoad,"js");};var doLANG=function(thePath,theMessage,thearr){if(JSSHOP.cookies.getCookie("usrlang")!==null){usrlang=JSSHOP.cookies.getCookie("usrlang");}JSSHOP.loadScript("js/"+jscssprefix+"aa-"+usrlang+".js",doPidLoad,"js");};var doBootLoad=function(thef){try{JSSHOP.user.doCkieUprefs("prfsSHOPuser");}catch(e){alert("doBootLoad error: "+e);JSSHOP.logJSerror(e,arguments,"doBootLoad:doCkieUprefs");}try{tmpUrl=getCurrUrl();if(tmpUrl=="noQvalue"){}else{currUrlArr=JSSHOP.shared.urlToArray(tmpUrl);if(currUrlArr.fc){forceCache=currUrlArr.fc;jscssprefix="";}if(currUrlArr.pid){pid=currUrlArr.pid;}if(currUrlArr.ppid){ppid=currUrlArr.ppid;}if(currUrlArr.cid){cid=currUrlArr.cid;addFrmQArr("qco",cid,"fnishCoForm");}if(currUrlArr.catid){catid=currUrlArr.catid;addFrmQArr("qcat",catid,"fnishCatForm");}if(currUrlArr.itemid){itemid=currUrlArr.itemid;addFrmQArr("qitem",itemid,"fnishItemForm");}}if(JSSHOP.cookies.getCookie("quid")!==null){quid=JSSHOP.cookies.getCookie("quid");addFrmQArr("quser",quid,"fnishUserForm");try{if(pid.indexOf("edit-")!=-1){currAdmnMode="y";JSSHOP.loadScript("css/"+jscssprefix+"x_admn.css",JSSHOP.checkLoader,"css");}}catch(e){alert("doBootLoad error: "+e);}}if(JSSHOP.cookies.getCookie("cartID")==null){tmpcid=Math.random().toString(36).slice(2);JSSHOP.cookies.setCookie("cartID",tmpcid,"30","","","");cartID=tmpcid;}else{cartID=JSSHOP.cookies.getCookie("cartID");}doLANG("js/"+jscssprefix+"x_all.js?v=4",doLANG,"js");}catch(e){alert("doBootLoad error: "+e);JSSHOP.logJSerror(e,arguments,"doBootLoad");}};var doBtmJSLoad=function(thef){xx="ww";};var aLoadModSettings=function(){JSSHOP.loadScript("js/"+jscssprefix+"x_aa-mod-settings.js",JSSHOP.checkLoader,"js");};var fillDivs=function(){};