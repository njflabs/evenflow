

/*
* renders all items in the category
*/

var renderNuCartItems = function(a,b,c) {
 
    tstr = "";
    iint = 0;
    ppint = 1;
	 cartTtl = 0;
	ttCAA = JSON.parse(b);
    len = ttCAA.length;
    strHtml = "";
	ts = null;
    while (iint < len) {
        ts = ttCAA[iint];
        strRecID = ts._id;
        strRecType = 50;





        if (ts._id > 0) {
 

	na = ts.ci_price_a - ts.ci_price_b;
	nd = Math.round((na / ts.ci_price_a) * 100);
	strPriceHtml = "";

      tmpTttl = ts.ci_price_b * ts.ci_cartqty;
	cartTtl = cartTtl + tmpTttl;

 


		if(ts.ci_img) {
		 tmpIstrI = JSSHOP.shop.getPrdImgStr(ts.ci_img);
		}


	
                  strHtml += "<div style=\"max-width:78%\">";
 		strHtml += "<a class=\"txtDecorNone\" href=\"index.html?pid=aa-show-item&itemid=" + ts.ci_pid + "&cid=" + ts.ci_coid + "&catid=" + ts.ci_catid + "\">";
 		strHtml += "<img src=\"" + tmpIstrI + "\" class=\"slmtable brdrClrDlg icndbtn\" style=\"align: center;text-align:center;margin:6px;\" align=\"absmiddle\">" +  ts.ci_title;
            strHtml += "<span class=\"txtstriked txtBig txtClrGrey\">" + ts.ci_price_b + "</span></a>";
            strHtml += "</div>";


            strHtml += "<div style=\"align: right;text-align:right\">";
            strHtml += "<span onclick=\"javascript:JSSHOP.ui.doDefCBBCC('ahCartIcon', null, document.location.href='index.html?pid=aa-show-cart&cid=' + cid + '&ppid=' + ppid);\" class=\"crsrPointer\"><span class=\"txtBig txtClrDlg txtBold\">x" + ts.ci_cartqty + " = " + tmpTttl + "<br><br></span></span>";
             strHtml += "</div>";


     

            strHtml += "<div style=\"padding-top: 10px; margin: 17px; border-bottom: 1px dashed #D1D5D1;\">"; 
	      strHtml += "</div>";

 

 


 
            strHtml += "<input type=\"hidden\" id=\"prd" + 5 + iint + "\" value=\"\">";

 
            strHtml += "<input type=\"hidden\" name=\"item_name_" + ppint + "\" value=\"" +  ts.ci_title + "\">";
            strHtml += "<input type=\"hidden\" name=\"item_number_" + ppint + "\" value=\"" +  ts._id + "\">";
            strHtml += "<input type=\"hidden\" name=\"quantity_" + ppint + "\" value=\"" + ts.ci_cartqty + "\">";
            strHtml += "<input type=\"hidden\" name=\"amount_" + ppint + "\" value=\"" + ts.ci_price_b + "\">";

        }
	  ppint++;
        iint++;
    }
    if (tmpVitemArr[0]) {
 
      strHtml += "<div style=\"text-align: right\">Total:<b>" + cartTtl.toFixed(2) + "</b></div>"; 
   } else {
      strHtml += "<div>" + stxt[34] + "</div>";
 
    }

newelN = document.createElement('div');
 
newelN.innerHTML = strHtml;
tmpNTDQI = document.getElementById("dvCartAdd");
tmpNTDQI.appendChild(newelN);



};


 

/*
 * main loader function
 */
var aLoadModShowCA = function() {
    tmpDOs = null;
    tmpDOs = {};
    tmpDOs["o"] = "ci_dadded Desc";
    tmpDOs["ws"] = "where ci_uid=? and ci_coid=? and ci_cartqty >? and ci_rtype=? and ci_cartid=?";
    tmpDOs["wa"] = [quid,cid,0,5,cartID]; 
    oi = getNuDBFnvp("qcartitem",5,null,tmpDOs);
    doQComm(oi["rq"], null, "renderNuCartItems");
};

 
aLoadModShowCA();
  
