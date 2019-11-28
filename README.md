<table style="margin: 0 auto; max-width: 900px" align="center">
	<tr>
		<td><p><strong>- About:</strong><br>evenflow is an abstract attempt to use the javafx 
browser, LAMP stack and android to deploy and maintain simple desktop,web,mobile applications. <br>
		Emphasis is on e-commerce software components and how they can be 
		integrated to work together with minimal maintenance.<br>
		No security exploits checking, refactoring, form-connection validation, 
		etc. are enforced with evenflow code.<br>The code 
is written, rewritten and mashed according to the current imagination spool.<br>
		It is what it is. Use it at your own risk.<br>
		</p>
		<p>...</p>
<p><strong>- Quick Setup:</strong><br>upload the whole project or the 
<a href="webdroid/assets/quickorder">webdroid/assets/quickorder</a> folder to your web 
site.<br>create a mysql database 
named evenflow.<br>open <a href="browser/db_schema.txt">browser/db_schema.txt</a> and import it to your database.<br>
open<a href="webdroid/assets/quickorder/_p/do.php"> quickorder/_p/do.php</a> and edit the dbusername and password variables.<br>
point your native web browser to http://...assets/quickorder/index.html<br>
<a href="webdroid/bin/b.bat">build</a> and or 
<a href="webdroid/bin/i.bat">install</a> the
<a href="webdroid/bin/evenflowr-debug.apk">android app apk</a>.<br>
in the browser/ folder <a href="browser/sun2Run.bat">launch</a> the adminApp browser app.<br>load the help docs in 
the adminApp to see how to get it setup with your remote and/or local databases.<br>
edit the quickorder folder and javascript files to use the <strong>app</strong> object javascript 
interface to conect call functions from your android and java-browser apps to enhance your e-commerce site.<br>
The javascript shoots for backwords compatibility.<br>No functions like <em>
Promise async await </em>etc, It will rin in IE6+, Android Froyo Webview and up, 
along with the JavaFX WebView.<br><br><br><strong>- Structure:</strong><br>/browser [main browser app with spreadsheet 
panel]<br>/docs [help docs used mainly for browser - spreadsheet set-up]<br>
/webdroid [android app]<br>/webdroid/assets/quickorder [android app content and 
e-commerce webpage]<br>
<br><strong>- Usage:</strong><br>In the included example, all web-pages are 
found in the webdroid/assets folder.<br>LAMP stack should 
be able to pull this folder assets as a web folder and show
<a href="http://...evenflow/webdroid/assets/quickorder/index.html">http://...evenflow/webdroid/assets/quickorder/index.html</a>.<br>For local 
development, this
<a href="webdroid/assets/quickorder">assets/quickorder</a> folder can be linked through your LAMP config or just dump 
the evenflow project into your htdocs,web,&nbsp; folder.<br>The android assets/ 
folder will serve for the android and jafafx app and web-page.<br>This allows to create 
javascript interfaces for android and the java brower apps inlined directly to 
the actual web-content.<br><br>- <strong>Javascript 
to Java Interface Bridge:</strong><br>
evenflow javafx browser and android app rely heavily onthe JSI_ javascript 
inerface files to interact with the WebViews, etc.<br>See using the javafx 
browser AppDB links on the webpage while running the javafx app for example.<br>
Pages such as edit or view category will show the AppDB clickable link on left 
that will swap browser panel to editable spreadsheet style panel.<br><br>
<img src="webdroid/assets/quickorder/images/img4.jpg" style="height: 168px; width: 300px"><img src="webdroid/assets/quickorder/images/img6.jpg" style="height: 168px; width: 300px"><br><br>- <strong>User Interface:</strong><br>
Rendering of the user interface mostly relies on simple html and javascript, json.<br>
The main index.html file uses ajax requests to include the html/tplates files 
according to the pid variable passed in the URL.<br>The corresponding javascript 
file is also included. These functions are found in x_booter.js<br>The search and nav bar are found in index_nav.html.<br>
The UI is not currently using any 3rd party JS frameowrks.<br>Trying to keep it 
simple with no dependencies on UI.<br><br><strong>- Database:</strong><br>Most of the custom database requests are made 
using javascript, and each contain a javascript callback function:<br>Basically 
an ajax request is sent to quickorder/assets/_p/do.php which connects to mysql 
and sends back a response string.<br>see the older 
<a href="webdroid/assets/quickorder/js/x_booter.js">x_booter,js</a>&nbsp; 
function doQcomm (old function) for sending simple sql statements or doNurQcomm 
(newer function) for sending a connection object with various parameters.<br>To 
limit the number of ajax requests for database, the redundante tables in 
database are implemented as forms in tplates/index_forms.html and&nbsp; on each 
web page load, a ajax request is sent as an object string with a batch of common 
requests each with its own callback. As in the doFrmQLoad function in 
<a href="webdroid/assets/quickorder/js/x_booter.js">x_booter,js</a><br>var doFrmQLoad = function.......<br>doCFrmQ 
= nCurrCnxOb(); // declare a connection object<br>// batch in beggining lets 
know is batch of array of request sent as [q]uery parameter<br>// currFrmQArr 
array is built with redundante mysql requests on each webpage load.<br>
doCFrmQ["q"] = "batch" + JSON.stringify(currFrmQArr);&nbsp; <br>doCFrmQ["cb"] = 
"setMFormArr";&nbsp; // callback function<br>doNurQComm(doCFrmQ); // send the 
connection object<br><br>When using the Android app QuickOrder, the database 
connections will fault to the android sqlite database.<br>You can load your 
remote webpage in QuickOrder android app, and use the javascript interface 
functions with your remote website.<br>These interface functions are not tested 
or validated, use with caution.<br>Some attempts to synch the native android app 
database/media with the local/remote Lamp stack website database/media was made 
using timestamps on essential databases at
<a href="webdroid/assets/quickorder/js/x_aa-edit-synch.js">x_aa-edit-synch.js</a><br>
Keep in mind that the Android app sqlite database is mirrored/copy of your web 
site database structure.<br>For instance, the webdroid/assets/quickorder/db_schema.txt and the 
copy at browser/db_schema.txt should work as the structure for <br>your webpages mysql 
database and are also used in the copyDataBase() functions found in the 
UtilSQLAdapter classes for
<a href="browser/njfbrowser/utils/UtilSQLAdapter.java">Java browser</a> and
<a href="webdroid/src/com/njfsoft_utils/dbutil/UtilSQLAdapter.java">android app</a>.<br>
<br>- <strong>
WorkFlow:</strong><br>webdroid/assets is your e-commerce webpage. you can build 
it directly to your android app.<br>Your android app enhances your e-commerce 
webpage with camera and speech capabilites among others.<br>The javafx broser 
enhances your e-commerce web page with spreadsheet capablities.<br>Great 
emphasis was also put on webpage loading speed. Shop categories and other cpu 
consuming redundant queries etc, are pushed over to users browser localStorage, 
saved as text file etc. Remember to click the Refresh link on left when editing 
categories or add .....<strong>&amp;fc=y</strong> to url so that the localStorage, 
saved to text file, etc.cacheing can occur.<br>User Interface is also taken into 
account; product images all loaded in scroll order for mobile devices. The 
auto-complete website search box delimits the category items while typing, shows 
last seen, gives suggesrions etc.<br><br>
<strong>- Changes:<br>-</strong>&nbsp; have been made: the main file was index.php, but due to 
incompatibility issues with androids webview, it has been renamed to index.html. 
See the .htaccess file on how AddHandler is used to parse .html extension in 
php. You can also change your actual php.ini file to do this, but requires more 
steps.<br>- material icon html tags converted to use numeric reference 
(compatibility with javafx browser
<a href="webdroid/assets/quickorder/fonts/materialicons/numerical_vhar_ref.txt">
see reference here</a>).<br>- For backward copatibility, the javafx 
browser/spreadsheet app code was compiled and run on java2. Compiling now should 
be done on java13, but will be using 8 for now; which seems to have major&nbsp; 
performance over java2. example .bat files included.<br><br>- <strong>
TODO:</strong><br>
clean up and comment code.<br>clean up the UI.<br>
<br>- <strong>
Credits:</strong><br>
Most of the credits for open sourced code that is used in this app is usually 
included in the code.<br>But alot of the code has been treated ginzu style, so if you 
deserve some credit, let us know.<br> </p>
		</td>
	</tr>
</table>
