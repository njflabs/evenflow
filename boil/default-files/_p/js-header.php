<script  type="text/javascript">
//  removeEventListener("load", doJSload, false);

var doPJSload = function() {
try {
tmpV = "y";
// http://localhost/w/pm/
// alert("js-header");
} catch(e) {
// alert(e);
JSSHOP.logJSerror(e, arguments, "doJSload");
}
}

 

try {
if (window.addEventListener){
window.addEventListener("load", doPJSload, false);
 } else if (window.attachEvent) {
 window.attachEvent("onload", doPJSload);
 } else {
window.onload = doPJSload;
}
} catch(e) {
alert("window.addEventListener: " + e);
}

</script>
