currIContent="no";currCartTShow="y";var tmpSQBArr=null;var tmpVitemArr=null;tmpSQBArr=[];tmpVitemArr=[];var tmpVindex=0;var tmpPrdMediaArr=null;tmpPrdMediaArr=[];var doRScb=function(a){dummyval=false;};var doRecentSeen=function(a,d,c){JSSHOP.shared.setFrmFieldVal("qextras","e_dadded",JSSHOP.getUnixTimeStamp());JSSHOP.shared.setFrmFieldVal("qextras","e_uid",quid);JSSHOP.shared.setFrmFieldVal("qextras","e_rtype","10");JSSHOP.shared.setFrmFieldVal("qextras","e_vala",itemid);JSSHOP.shared.setFrmFieldVal("qextras","e_valb",cid);JSSHOP.shared.setFrmFieldVal("qextras","e_valc",catid);currPgTitle=arrAllForms.qitem.v[0].i_title;JSSHOP.shared.setFrmFieldVal("qextras","e_vald",currPgTitle);tmpFobj=null;tmpFobj={};tmpQint=6;if(d.indexOf("_id")!=-1){var b=null;tRCs=null;b=JSON.parse(d);tRCs=b[0];JSSHOP.shared.setFrmFieldVal("qextras","e_valf",Math.round(tRCs.e_valf)+1);tmpQint=7;}else{tmpFobj["knvp"]=JSSHOP.shared.getFrmVals(document["qextras"],"nada");oi=getNuDBFnvp("qextras",tmpQint,null,tmpFobj);clearNuLclStrg("localStorage","setLoadACTB");qitac=nCurrCnxOb();qitac["q"]=oi["rq"];qitac["cb"]="doRScb";doNurQComm(qitac);}};var checkRecentSeen=function(){tmpDOs=null;tmpDOs={};tmpDOs["ws"]="where e_uid=? and e_rtype=? and e_vala=?";tmpDOs["wa"]=[quid,10,itemid];oi=getNuDBFnvp("qextras",5,null,tmpDOs);doQComm(oi["rq"],null,"doRecentSeen");};var renderNuItem=function(){try{tmpVitemArr=null;tmpVitemArr=[];tmpVitemArr=arrAllForms.qitem.v;tStrHtml=JSSHOP.shop.getPrdsFullStr("prodpg",tmpVitemArr,null,null,null);newel=document.createElement("div");newel.innerHTML=tStrHtml;tmpTDQI=document.getElementById("includedContent");tmpTDQI.innerHTML="";tmpTDQI.appendChild(newel);}catch(a){alert("renderNuTQBItems: "+a);}JSSHOP.shop.getPrdMedia(catid,itemid,"dadido");JSSHOP.shop.setCatPrdImgs("prodpg");doRecentActivity();};var getPrdImgEditDv=function(a){tpPIEDv=document.createElement("div");try{tsa=null;tsa={};tsa=tmpPrdMediaArr[a];tmpRetStr='<img src="'+JSSHOP.shop.getPrdImgStr("prodpg",tsa.m_file)+'" style="width: 100%"  class="" onclick="alert(\''+JSSHOP.shared.getFrmFieldVal("qmedia","_id","0")+"');\">";tmpRetStr+='<div class="dvTxtBtns"><input type="button" class="btnTxtLabel" value="Set as Main" onclick="javascript:doPrdMMain();">   |   <input type="button" class="btnTxtLabel" value="Delete" onclick="javascript:doPrdMDelete();"></div>';tmpRetStr+="<br><br>";tpPIEDv.innerHTML=tmpRetStr;return tpPIEDv;}catch(b){alert("getPrdImgEditDv "+b);tpPIEDv.innerHTML="oops. something wrong..";return tpPIEDv;}};var doPrdMIcnClick=function(b){try{tsa=null;tsa={};tsa=tmpPrdMediaArr[b];isrc=JSSHOP.shop.getPrdImgStr("prodpg",tsa.m_file);istr="prodpg"+itemid;if(tsa.m_file.indexOf(".mp4")!=-1){JSSHOP.shared.setFrmVals("qmedia",tmpRPMArr[b],function(){app.getPlayMPF(tsa.m_file);});}else{document.getElementById(istr).src=isrc;}}catch(a){alert("doPrdMIcnClick:"+a);}};var dadido=function(b,c,d){try{if(c.indexOf("_id")!=-1){tmpRPMArr=null;tmpRPMArr=[];tmpRPMArr=JSON.parse(c);tmpPrdMediaArr=null;tmpPrdMediaArr=[];tmpPrdMediaArr=JSON.parse(c);tmmpII=0;tmpRetStr="<div>";tmpAlen=tmpPrdMediaArr.length;while(tmmpII<tmpAlen){tsa=tmpPrdMediaArr[tmmpII];tmpRetStr+='<img src="'+JSSHOP.shop.getPrdImgStr("prdmedia",tsa.m_file)+'" class="icnmnubtn crsrPointer" onclick="doPrdMIcnClick('+tmmpII+');">';tmmpII++;}tmpRetStr+="</div>";JSSHOP.ui.setTinnerHTML("dvPrdMedia","");JSSHOP.ui.setTinnerHTML("dvPrdMedia",tmpRetStr);}}catch(a){alert("dadido:"+a);}};var dmyFnishCntLoad=fnishCntLoad;fnishCntLoad=function(){renderNuItem();};