currPgTitle="SQL dump";document.title=currPgTitle;var showDumpRes=function(f,b,d){var e=null;e=JSON.parse(b);var a=e.length;strHtml="";tstr="";iint=0;while(iint<a){ts=e[iint];tmpVitemObj=null;tmpVitemObj={};strHtml+="<tr>";for(var c in ts){strHtml+="<td>";strHtml+=ts[c];strHtml+="</td>";}strHtml+="</tr>";iint++;}iint=0;strTHhtml="<thead><tr>";qint=ts.length;for(var c in ts){strTHhtml+="<th>"+Object.keys(ts)[iint]+"</th>";iint++;}strTHhtml+="</tr></thead>";newel=document.createElement("div");tmpFstr='<table class="striped highlight responsive-table" cellpadding="12px" cellspacing="12px">'+strTHhtml+strHtml+"</table>";newel.innerHTML=tmpFstr;tmpTDQI=document.getElementById("tdSql");tmpTDQI.innerHTML="";tmpTDQI.appendChild(newel);};var aLoadDump=function(){try{tmpDOs=null;tmpDOs={};tmpDOs["ws"]="where _id>?";tmpDOs["wa"]=[0];oi=getNuDBFnvp("qbit",5,null,tmpDOs);doQComm(JSSHOP.shared.getFieldVal("taSql","select * from qco"),null,"showDumpRes");}catch(a){alert(a);}};var dmyFnishCntLoad=fnishCntLoad;fnishCntLoad=function(){if(currUrlArr.tqs){if(currUrlArr.tqs=="noQvalue"){}else{JSSHOP.shared.setFieldVal("taSql",currUrlArr.tqs);}aLoadDump();}};