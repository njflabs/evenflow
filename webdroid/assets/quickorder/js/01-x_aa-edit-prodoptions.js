var fillProdOptSelects=function(){tmpArrSG=JSON.parse(document.getElementById("fldChallArray").value);iint=0;ttstr="";len=tmpArrSG.length;while(iint<len){ts=tmpArrSG[iint];tob="selprdop"+ts.o;if(document.getElementById(tob)){addCurrListVal(document.getElementById(tob),ts.t,ts.i);}iint++;}};var checkProdOpEdit=function(b,c){if((c==7)&&(ppid==0)){m=6;}else{m=c;}var a=document.getElementById("OptionTitle").value;if(a.length<1){alert("You must enter a Option Title");return;}tmpVpar=[];tmpVpar.push("OptionTitle");tmpVpar.push("OptionType");oi=getDBFnvp(b,m,tmpVpar);tsas=JSON.stringify(oi);doQComm(oi["rq"],null,"hu");};var rEditProdOptions=function(e,f,d){if(ppid==0){ttstr="New Group";JSSHOP.ui.toggleVisibility("dvTmpOptType","show");}else{ttstr="Edit:";}hasr="n";fullstr="";var g=JSON.parse(f);var c=g.length;var a=0;var h=[];tmpArrEPO={};fullstr='<table class="clsmmdv" border="1px"><tr>';while(a<c){ts=g[a];if(a<1){for(var i in ts){if(document.getElementById(i)){arrDBnDocFNames.push(i);}arrDBFNames.push(i);}}if(ts.OptionType==1001){fullstr+='<td><a href="index.html?pid=aa-edit-prodoptions&ppid='+ts._id+'">'+ts._id+"</a></td>";fullstr+='<td><a href="index.html?pid=aa-edit-prodoptions&ppid='+ts._id+'">'+ts.OptionTitle+"</a></td>";fullstr+='<td><select id="selprdop'+ts._id+'" name="selprdop'+ts._id+'"><option value="0">Add Choice</option></select></td>';fullstr+="<td>Edit</td>";addCurrListVal(document.getElementById("tmpOptionType"),ts.OptionTitle,ts._id);}else{tmpArrEPO=null;tmpArrEPO={};tmpArrEPO["i"]=ts._id;tmpArrEPO["o"]=ts.OptionType;tmpArrEPO["t"]=ts.OptionTitle;h.push(tmpArrEPO);}fullstr+="</tr><tr>";if(ts._id==ppid){for(var b in ts){if(document.getElementById(b)){document.getElementById(b).value=ts[b];}}}a++;}document.getElementById("fldChallArray").value=JSON.stringify(h);fullstr+="</tr></table>";fullsta='<img alt="imgldr" height="4px" width="4px" src="images/misc/trans.gif" onload="javascript:fillProdOptSelects();"  onerror="javascript:fillProdOptSelects();">';newel=document.createElement("div");newel.innerHTML=fullstr;document.getElementById("dvoplist").appendChild(newel);newela=document.createElement("div");newela.innerHTML=fullsta;document.getElementById("dvoplist").appendChild(newela);newelb=document.createElement("div");newelb.innerHTML=ttstr;document.getElementById("dvTmpOptTip").appendChild(newelb);};var aEditProdOptions=function(){doQComm("select * from prod_options order by OptionType desc limit 500",null,"rEditProdOptions");};