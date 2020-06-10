        function setConfValInt(theKey, theVal)  {
        app_cnvs.setConfValInt(theKey, theVal);
        }

        function setConfValString(theKey, theVal)  {
        if(theKey == "confBrowBkgd") {
        document.body.style.backgroundColor = theVal;
        }
        app_cnvs.setConfValString(theKey, theVal);
        }

        function getConfValInt(theKey)  {
        strConf = app_cnvs.fetchConfValInt(theKey);
        return strConf;
        }

        function getConfValString(theKey)  {
        strConf = app_cnvs.fetchConfValString(theKey);
        return strConf;
        }

        function setStreamPic(thePicType) {
        app_cnvs.setStreamPic(thePicType);
        }

        function showStreamPic(strImgstr) {
        imgStreamPic.src = "data:image/png;base64, " + strImgstr;
        }

        function setUrlSlider() {
        app_cnvs.getUrlSlider();
        }
 

        function setImgLoad() {
        try {
        app_cnvs.setImgLoad();
        } catch(e) {
        alert("setPageLoad.error: " + e)
        }
        }
        function setImgEdit() {
        try {
        app_cnvs.setImgEdit();
        } catch(e) {
        alert("setImgEdit.error: " + e)
        }
        }

 



 

        function showHideElement(element, showHide) {
        // alert(element);
        //function to show or hide elements
        //element variable can be string or object
        if (document.getElementById(element)) {
        element = document.getElementById(element);
        }
        if(element) {
        if(showHide == "show") {
        element.style.visibility = "visible";
        try {
        element.style.display = "block";
        } catch(e) {
        }
        } else {
        element.style.visibility = "hidden";
        try {
        element.style.display = "none";
        } catch(e) {
        }
        }
        }
        }

        function setTinnerHTML(theElemId, theInnerHtml){
        document.getElementById(theElemId).innerHTML = theInnerHtml;
        }

        function setObjBGcolor(theObj, theclr) {
        theObj.style.backgroundColor = theclr;
        }

        function setStoryShare(sNwork) {
        try {
        showHideElement('tdFormControls', 'show');
        showHideElement('epSharePop', 'hide');
        stitle = document.getElementById("taStitle").value;
        sdesc  = document.getElementById("taSdesc").value
        app.setStoryShare(sNwork, stitle, sdesc);
        } catch(e) {
        alert(e);
        }
        }
        function setNewStoryID(sNwork) {
        try {
        app.setNewStoryID();
        } catch(e) {
        // alert(e);
        }
        }
        function hideSSharePop() {
        try {
        showHideElement('tdFormControls', 'show');
        showHideElement('epSharePop', 'hide');
        } catch(e) {
        alert(e);
        }
        }
        function setSSharePop() {
        try {
        showHideElement('epSharePop', 'show');
        showHideElement('tdFormControls', 'hide');
        centerPopWin('epSharePop');
        } catch(e) {
        alert(e);
        }
        }

        /**
        * Code below taken from - http://www.evolt.org/article/document_body_doctype_switching_and_more/17/30655/
        *
        * Modified 4/22/04 to work with Opera/Moz (by webmaster at subimage dot com)
        *
        * Gets the full width/height because it's different for most browsers.
        */
        function getViewportHeight() {
        if (window.innerHeight!=window.undefined) return window.innerHeight;
        if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
        if (document.body) return document.body.clientHeight;

        return window.undefined;
        }
        function getViewportWidth() {
        var offset = 17;
        var width = null;
        if (window.innerWidth!=window.undefined) return window.innerWidth;
        if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth;
        if (document.body) return document.body.clientWidth;
        }

        /**
        * Gets the real scroll top
        */
        function getScrollTop() {
        if (self.pageYOffset) // all except Explorer
        {
        return self.pageYOffset;
        }
        else if (document.documentElement && document.documentElement.scrollTop)
        // Explorer 6 Strict
        {
        return document.documentElement.scrollTop;
        }
        else if (document.body) // all other Explorers
        {
        return document.body.scrollTop;
        }
        }

        function getScrollLeft() {
        if (self.pageXOffset) // all except Explorer
        {
        return self.pageXOffset;
        }
        else if (document.documentElement && document.documentElement.scrollLeft)
        // Explorer 6 Strict
        {
        return document.documentElement.scrollLeft;
        }
        else if (document.body) // all other Explorers
        {
        return document.body.scrollLeft;
        }
        }

        function centerPopWin(theObj) {
        thePopUpObj = document.getElementById(theObj);
        width = thePopUpObj.offsetWidth;
        height = thePopUpObj.offsetHeight;
        var theBody = document.getElementsByTagName("BODY")[0];
        var scTop = parseInt(getScrollTop(),10);
        var scLeft = parseInt(theBody.scrollLeft,10);
        var fullHeight = getViewportHeight();
        var fullWidth = getViewportWidth();
        thePopUpObj.style.top = (scTop + ((fullHeight - height) / 2)) + "px";
        thePopUpObj.style.left =  (scLeft + ((fullWidth - width) / 2)) + "px";
        }

        function getUnixTimeStamp() {
        ts = Math.round(new Date().getTime() / 1000);
        return ts;
        }