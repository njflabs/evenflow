var getQIScnStr=function(rid,tmprhtml){strTret="<div onclick=\"JSSHOP.ui.setCBBClickClr(this,'brdrClrRed','brdrNone', function(){doQIedit("+rid+');});" class="brdrNone">';strTret+=tmprhtml+"</div>";return strTret;};var saveCartItem=function(){JSSHOP.shared.setFrmFieldVal("qcartitem","ci_cartqty",JSSHOP.shared.getCurrSelectOpt(document.getElementById("tmpIcartqty")));tmpVpar=null;tmpSobj=null;tmpVpar=[];tmpSobj={};tmpFobj={};if(JSSHOP.shared.getFielVal("ci_vala","n")=="n"){tmpFobj.knvp=getFrmVals(document.qcartitem,"nada");oi=getNuDBFnvp("qcartitem",6,null,tmpFobj);}else{if(tmpVitemObj.id==null){oi=getNuDBFnvp("qitem",6,null,tmpFobj);}else{tmpFobj.ws="where _id=?";tmpFobj.wa=[tmpVitemObj.id];oi=getNuDBFnvp("qitem",7,null,tmpFobj);}}};var aLoadModAddQitem=function(theObj,theId,iei){JSSHOP.shared.setCurrSelectOpt(document.getElementById("tmpIcartqty"),JSSHOP.shared.getFieldVal("ci_cartqty","1"));};var renderProdPop=function(theObj,b,iei){if(b.indexOf("_id")!=-1){var arrToFill=null;arrToFill=JSON.parse(theResp);ts=arrToFill[0];JSSHOP.shared.setFrmFieldVal("qcartitem","ci_cartqty",ts.ci_cartqty);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_vala","y");}else{}loadJSModal("tplates/aa-mod-add-qitem.html");};var doQIadd=function(theObj,theId,iei){try{tmpVindex=iei;tsa={};tsa=tmpSQBArr[tmpVindex];strRecID=tsa._id;strRecType=51;var today=new Date().getTime();JSSHOP.shared.setFrmFieldVal("qcartitem","ci_uid",quid);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_cartid",quid+"0"+cid);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_coid",cid);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_pid",tsa._id);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_title",tsa.i_title);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_price_a",tsa.i_price_a);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_price_b",tsa.i_price_b);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_dimen_n",tsa.i_dimen_n);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_dimen_v",tsa.i_dimen_v);JSSHOP.shared.setFrmFieldVal("qcartitem","ci_vala","n");JSSHOP.shared.setFrmFieldVal("qcartitem","ci_valb","n");JSSHOP.shared.setFrmFieldVal("qcartitem","ci_dadded",today);tmpDOs=null;tmpDOs={};tmpDOs.ws="where _id=? and ci_pid=?";tmpDOs.wa=[strRecID,ppid];oi=getNuDBFnvp("qitem",5,null,tmpDOs);doQComm(oi.rq,null,"renderProdPop");}catch(e){alert(e);}};var renderNuTQBItems=function(theElem,theResp,marble){try{var arrToFill=null;arrToFill=JSON.parse(theResp);tmpSQBArr=JSON.parse(theResp);var len=arrToFill.length;strHtml="";tstr="";iint=0;while(iint<len){ts=arrToFill[iint];tmpVitemObj=null;tmpVitemObj={};strRecID=ts._id;strRecType=51;if(ts._id>0){na=ts.i_price_a-ts.i_price_b;nd=Math.round((na/ts.i_price_a)*100);strPriceHtml="";strImgDsct='<img src="images/discount'+nd+".png\" class=\"icnmedbtn\" onerror=\"this.src='images/trans.gif';this.width='2px';getNPrice('"+ts._id+"','"+nd+"');\">";strPriceHtml='<span class="txtstriked txtBig txtClrGrey">'+ts.i_price_a+'</span> <span class="txtBig txtClrRed txtBold">'+ts.i_price_a+"</span>";strHtml+="<tr>";strHtml+='<td id="tdPP'+ts._id+'">';strHtml+=getQIScnStr(iint,strImgDsct);strHtml+="</td>";strHtml+="<td>";strHtml+=getQIScnStr(iint,ts.i_title+" "+ts.i_dimen_n+"    "+strPriceHtml);strHtml+="</td>";strHtml+='<td  onclick="javascript:doQIadd(this,'+ts._id+","+iint+');"><img src="images/cart_r.gif" class="icnsmlbtn brdrClrWhite">';strHtml+='<input type="hidden" id="prd'+5+iint+'" value="">';strHtml+="</td>";strHtml+="</tr>";}iint++;}newel=document.createElement("div");strTHhtml="<th>�</th><th>Produto</th><th>xx</th>";tmpFstr=getTblSortStr(strTHhtml,strHtml);newel.innerHTML=tmpFstr;tmpTDQI=document.getElementById("tdQitems");tmpTDQI.innerHTML="";tmpTDQI.appendChild(newel);}catch(e){alert("renderNuTQBItems: "+e);}};var aRenderQbit=function(a,b,c){tmpDOs=null;tmpDOs={};tmpDOs.ws="where i_pid=? and i_vala=?";tmpDOs.wa=[tmpSQBArr[6],tmpSQBArr[1]];oi=getNuDBFnvp("qitem",5,null,tmpDOs);alert("aRenderQbit: "+oi.rq);doQComm(oi.rq,null,"renderNuTQBItems");};var astHasQUser=function(a,b,c){if(b.indexOf("u_rtype")!=-1){alert("astHasQUser = true: "+b);checkHasQCo(null,null,null);}else{alert("astHasQUser = false: "+b);tmpFobj=null;tmpFobj={};tmpFobj.knvp=getFrmVals(document.quser,"nada");oi=getNuDBFnvp("quser",6,null,tmpFobj);alert(JSON.stringify(getFrmVals(document.quser,"nada")));alert(oi.rq);doQComm(oi.rq,null,"checkHasQCo");}};var checkHasQCo=function(a,b,c){alert(b);tmpDOs={};tmpDOs.ws="where c_rtype=? and c_uid=? and c_vala=?";tmpDOs.wa=[51,tmpSQBArr[0],tmpSQBArr[1]];oi=getNuDBFnvp("qco",5,null,tmpDOs);doQComm(oi.rq,null,"astHasQCo");};var astHasQCo=function(a,b,c){if(b.indexOf("c_rtype")!=-1){alert("astHasQCo = true: "+b);checkHasQBit(null,null,null);}else{alert("astHasQCo = false: "+tmpSQBArr[4]+" : "+JSON.stringify(tmpSQBArr));tmpFobj=null;tmpFobj={};tmpFobj.knvp=getFrmVals(document.qco,"nada");oi=getNuDBFnvp("qco",6,null,tmpFobj);doQComm(oi.rq,null,"checkHasQBit");}};var checkHasQBit=function(a,b,c){tmpDOs={};tmpDOs.ws="where q_rtype=? and q_uid=? and q_vala=?";tmpDOs.wa=[51,tmpSQBArr[0],tmpSQBArr[6]];oi=getNuDBFnvp("qbit",5,null,tmpDOs);doQComm(oi.rq,null,"astHasQBit");};var astHasQBit=function(a,b,c){if(b.indexOf("q_rtype")!=-1){alert("astHasQbit = true: "+b);checkHasQItems(null,null,null);}else{alert("astHasQbit = false: "+JSON.stringify(tmpSQBArr));tmpFobj=null;tmpFobj={};tmpFobj.knvp=getFrmVals(document.qbit,"nada");oi=getNuDBFnvp("qbit",6,null,tmpFobj);doQComm(oi.rq,null,"checkHasQItems");}};var checkHasQItems=function(a,b,c){tmpDOs={};tmpDOs.ws="where i_rtype=? and i_pid=? and i_vala=?";tmpDOs.wa=[51,tmpSQBArr[6],tmpSQBArr[1]];oi=getNuDBFnvp("qitem",5,null,tmpDOs);doQComm(oi.rq,null,"astHasQItems");};var astHasQItems=function(a,b,c){if(b.indexOf("i_rtype")!=-1){alert("astHasQItems = true: "+b);aRenderQbit(1,2,3);}else{tmpVindex=0;alert("astHasQbit = false: "+JSON.stringify(tmpSQBArr));doQBIsave(null,null,null);}};var doQBIsave=function(a,b,c){try{tt=tmpSQBArr[9];len=tt.length;if(tmpVindex==len){tmpVindex=0;aRenderQbit(null,null,null);}else{JSSHOP.shared.setFrmFieldVal("qitem","i_rtype","51");JSSHOP.shared.setFrmFieldVal("qitem","i_pid",tmpSQBArr[6]);JSSHOP.shared.setFrmFieldVal("qitem","i_vala",tmpSQBArr[1]);JSSHOP.shared.setFrmFieldVal("qitem","i_valb",tt[tmpVindex][0]);JSSHOP.shared.setFrmFieldVal("qitem","i_title",tt[tmpVindex][1]);JSSHOP.shared.setFrmFieldVal("qitem","i_price_a",tt[tmpVindex][2]);JSSHOP.shared.setFrmFieldVal("qitem","i_price_b",tt[tmpVindex][3]);JSSHOP.shared.setFrmFieldVal("qitem","i_dimen_n",tt[tmpVindex][4]);JSSHOP.shared.setFrmFieldVal("qitem","i_dimen_v",tt[tmpVindex][5]);tmpFobj=null;tmpFobj={};tmpFobj.knvp=getFrmVals(document.qitem,"nada");oi=getNuDBFnvp("qitem",6,null,tmpFobj);tmpVindex=tmpVindex+1;doQComm(oi.rq,null,"doQBIsave");}}catch(e){alert("doQBIsave: "+e);}};var transRes=function(cString){try{document.getElementById("fldChallArray").value=cString;atmpQstr=document.getElementById("fldChallArray").value;inQarr=JSON.parse(decPrefCky(atmpQstr));tmpSQBArr=inQarr;JSSHOP.shared.setFrmFieldVal("quser","u_rtype","51");JSSHOP.shared.setFrmFieldVal("quser","u_header",tmpSQBArr[0]);JSSHOP.shared.setFrmFieldVal("qco","c_uid",tmpSQBArr[0]);JSSHOP.shared.setFrmFieldVal("qco","c_vala",tmpSQBArr[1]);JSSHOP.shared.setFrmFieldVal("qco","c_rtype","51");JSSHOP.shared.setFrmFieldVal("qco","c_title",tmpSQBArr[2]);JSSHOP.shared.setFrmFieldVal("qco","c_tel",tmpSQBArr[3]);JSSHOP.shared.setFrmFieldVal("qco","c_web",tmpSQBArr[4]);JSSHOP.shared.setFrmFieldVal("qco","c_email",tmpSQBArr[5]);strTcStr=tmpSQBArr[2]+"<br>"+tmpSQBArr[3]+"<br>"+tmpSQBArr[4]+"<br>"+tmpSQBArr[5];JSSHOP.ui.setTinnerHTML("tdCoTitle",strTcStr);JSSHOP.shared.setFrmFieldVal("qbit","q_rtype","51");JSSHOP.shared.setFrmFieldVal("qbit","q_uid",tmpSQBArr[0]);JSSHOP.shared.setFrmFieldVal("qbit","q_pid",tmpSQBArr[1]);JSSHOP.shared.setFrmFieldVal("qbit","q_vala",tmpSQBArr[6]);JSSHOP.shared.setFrmFieldVal("qbit","q_title",tmpSQBArr[7]);JSSHOP.shared.setFrmFieldVal("qbit","q_desc",tmpSQBArr[8]);JSSHOP.shared.setFieldVal("tmpQtitle",tmpSQBArr[7]);tmpDOs={};tmpDOs.ws="where u_rtype=? and u_header=?";tmpDOs.wa=[51,tmpSQBArr[0]];oi=getNuDBFnvp("quser",5,null,tmpDOs);doQComm(oi.rq,null,"astHasQUser");}catch(e){alert(e);}};var aRenderCat=function(a,b,c){tmpDOs=null;tmpDOs={};tmpDOs.ws="where i_pid=?";tmpDOs.wa=[ppid];oi=getNuDBFnvp("qitem",5,null,tmpDOs);doQComm(oi.rq,null,"renderNuTQBItems");};var aLoadShowCat=function(){try{aRenderCat("a","a","a");}catch(e){alert("aLoadShowCat Error: "+e);}};