var tmpSQBArr=null;var tmpVitemArr=null;tmpSQBArr=[];tmpVitemArr=[];var tmpVindex=0;currIContent="no";var sw="search";tmpUrl=getCurrUrl();if(tmpUrl=="noQvalue"){}else{newArr=JSSHOP.shared.urlToArray(tmpUrl);if(newArr.sw){sw=newArr.sw;}}var doRSrchb=function(a){};var doRecentSearch=function(a,d,b){if(d.indexOf("_id")!=-1){var c=null;c=JSON.parse(d);ts=c[0];procNuUIitem("qextras","e_valf",ts._id,Math.round(ts.e_valf)+1,"doRSrchb");}else{JSSHOP.shared.setFrmFieldVal("qextras","e_dadded",JSSHOP.getUnixTimeStamp());JSSHOP.shared.setFrmFieldVal("qextras","e_uid",quid);JSSHOP.shared.setFrmFieldVal("qextras","e_rtype","11");JSSHOP.shared.setFrmFieldVal("qextras","e_vala",sw);JSSHOP.shared.setFrmFieldVal("qextras","e_valb",cid);JSSHOP.shared.setFrmFieldVal("qextras","e_valc","");JSSHOP.shared.setFrmFieldVal("qextras","e_vald","");JSSHOP.shared.setFrmFieldVal("qextras","e_valf","1");tmpFobj=null;tmpFobj={};tmpFobj["knvp"]=JSSHOP.shared.getFrmVals(document["qextras"],"nada");oi=getNuDBFnvp("qextras",6,null,tmpFobj);clearNuLclStrg("localStorage","setLoadACTB");qitac=nCurrCnxOb();qitac["q"]=oi["rq"];qitac["cb"]="doRSrchb";doNurQComm(qitac);}};var checkRecentSearch=function(){tmpDOs=null;tmpDOs={};tmpDOs["ws"]="where e_uid=? and e_rtype=? and e_vala=?";tmpDOs["wa"]=[quid,11,sw];oi=getNuDBFnvp("qextras",5,null,tmpDOs);doQComm(oi["rq"],null,"doRecentSearch");};var rndrNuSrchItms=function(d,a,c){try{tmpTDQI=document.getElementById("includedContent");if(a.indexOf("_id")!=-1){setCurrMItemsArr(JSON.parse(a));setCurrItemsArr(JSON.parse(a));tmpTDQI.innerHTML="";tStrHtml=JSSHOP.shop.getPrdsFullStr("srch",JSON.parse(a),null,null,null);doRecentActivity();}else{tStrHtml='<div class="txtBold txtClrHdr">Search of '+sw+"  returned 0 results</div>";}newel=document.createElement("div");newel.innerHTML=tStrHtml;tmpTDQI.appendChild(newel);}catch(b){alert("rndrNuSrchItms aas: "+a);}};var renderPrix=function(a){tmpCnstStr="sortDesc";if(a=="u"){tmpCnstStr="sortDesc";}else{tmpCnstStr="sortAsc";}nuVarr=JSSHOP.shared.sort(tmpVitemArr,"i_price_b",tmpCnstStr);renderNuTQBItems("a",JSON.stringify(nuVarr),"c");};var renderAgn=function(){renderNuTQBItems("a",JSON.stringify(tmpVitemArr),"c");};var rndrNuScanItms=function(f,b,d){try{if(b.indexOf("_id")!=-1){var a=JSON.parse(b);strLML="index.html?pid=aa-edit-item&itemid="+a[0]._id+"&cid="+a[0].i_coid+"&catid="+a[0].i_catid;document.location.href=strLML;}else{tmpTDQI=document.getElementById("includedContent");tStrHtml='<div class="txtBold txtClrHdr">Scan of '+sw+"  returned 0 results</div>";newel=document.createElement("div");newel.innerHTML=tStrHtml;tmpTDQI.appendChild(newel);}}catch(c){alert("rndrNuScanItms: "+c);}};var dmyFnishCntLoad=fnishCntLoad;fnishCntLoad=function(){includedContent.className="";tmpDOs=null;tmpDOs={};if(sw.indexOf("barcode:")!=-1){tmpBCstr=sw.replace("barcode:","");tmpDOs["ws"]="where i_bcode=?";tmpDOs["wa"]=[tmpBCstr];oi=getNuDBFnvp("qitem",5,null,tmpDOs);doQComm(oi["rq"],null,"rndrNuScanItms");}else{st1=sw;st2=sw+"%";st3="%"+sw;st4="%"+sw+"%";tmpDOs["ws"]="where i_rtype=? and (i_title=? or i_title like ? or i_title like ? or i_title like ?)";tmpDOs["wa"]=["5",st1,st2,st3,st4];oi=getNuDBFnvp("qitem",5,null,tmpDOs);doQComm(oi["rq"],null,"rndrNuSrchItms");}};