/*
* renders the pop up for current item
*/

var renderNuModItem = function(){ 
	try {
tStrHtml = JSSHOP.shop.getPrdsFullStr("modItem", new Array(JSSHOP.shop.getCurrItemArr()), null, null, null);
tStrHtml += "<img alt=\"imgldr\" height=\"5px\" width=\"5px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=\" onload=\"javascript:JSSHOP.shop.setCatPrdImgs('modItem');\"  onerror=\"javascript:JSSHOP.shop.setCatPrdImgs('modItem');\">";

newel = document.createElement('div');
newel.innerHTML = tStrHtml;
tmpTDQI = document.getElementById("dvPItemCntn");
tmpTDQI.innerHTML = "";
tmpTDQI.appendChild(newel);

	} catch(e) {
	 alert("renderNuModItem: " + e);
	}


};

 

/*
 * main loader function
 */
var aLoadModShowItem = function() {
		renderNuModItem();
};

 
aLoadModShowItem();
  
