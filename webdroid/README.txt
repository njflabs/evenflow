This is the android app folder its basically a WebView with added functions for quickorder/ website.
The android assets/quickorder folder will serve for the android and jafafx app and web-page.
This should be taken into consideration when editing your quickorder/ files.
LAMP stack should be able to pull this folder assets as a web folder and show http://...evenflow/webdroid/assets/quickorder/index.html.
For local development, this assets/quickorder folder can be linked through your LAMP config or just dump the evenflow project into your htdocs,web,  folder.
This allows to create javascript interfaces for android and the java brower apps inlined directly to the actual web-content.

--- Web-pages quickorder/: 
The main index.html file uses ajax requests to include the html/tplates files
according to the pid variable passed in the URL.
The corresponding javascript file is also included. These functions are found in [x_booter.js]
Ex: https://.../index.html?pid=aa-show-item&itemid=18&cid=2&catid=3, will load
js/x_aa-show-item.js and tplates/aa-show-item.html

- The fnishCntLoad() function found in the .js files is call when page is loaded:
var dmyFnishCntLoad = fnishCntLoad;
fnishCntLoad = function () {.......your code

- The search and navigation bar are found in index_nav.html.
See -Database below for information on the javascript - web files and how they 
connect to database and interact with android using javascript bridges etc.
Edit the quickorder folder and javascript files to use the app object javascript interface
to conect call functions from your android and java-browser apps to enhance your e-commerce site.
Product images all loaded in scroll order for mobile devices.
The auto-complete website search box delimits the category items while typing, shows last seen,
gives suggesrions etc.

- Shop categories and other cpu consuming redundant queries etc, are pushed over to users browser localStorage,
saved as text file etc. Remember to click the Refresh link on left when editing categories or add the "&fc=y" to url
so that the localStorage, saved to text file, etc.cacheing can occur.
The javascript shoots for backwords compatibility.
No functions like Promise async await etc, 
It will rin in IE6+, Android Froyo Webview and up, along with the JavaFX WebView.
The UI is not currently using any 3rd party JS frameowrks.
Trying to keep it simple with no dependencies on UI.
Quick web page load speed is mandatory. ask google.
see the boil/yuicompressor4 folder for javascript and css file compression.



-- Android App:
this project is built from the command line.
just run the b.bat in the /bin folder
to install apk, edit the bin/i.bat file (w/ text editor) to point to your QuickOrder-debug.apk
the main class is com.quickorder.QuickOrder
The android app is curently a bit crude.
In the near future:
Automated voice processing on adding shop items.
Barcode and qrcode scannng of items.
Better PayPal IPN coding.
Enhanced image media capturing and editing:
gif, mp4, with item captions - item prices, etc.



--- Database and Javascript Requests:
Import the db_schema.txt file found in browser/ folder to your database.
There is also a db_schema.txt in your webdroid/quickorder/assets folder.
These both should be duplicates. One is used for the sqlite in android, and the other is used for sqlite in javafx browser
(javafx sqlite not currently used . the javafx browser reads off your webs php mysql database. see the coinbin project on using javafx and sqlite).
Most of the custom database requests are made using javascript, and each contain a javascript callback function:
Basically an ajax request is sent to quickorder/assets/_p/do.php which connects to mysql and sends back a response string.
see the older x_booter,js  function doQcomm (old function) for sending simple sql statements or doNurQcomm (newer function) for sending a connection object with various parameters.
To limit the number of ajax requests for database, the redundante tables in database are implemented as forms in tplates/index_forms.html and  on each web page load, a ajax request is sent as an object string with a batch of common requests each with its own callback. As in the doFrmQLoad function in x_booter,js
var doFrmQLoad = function.......
doCFrmQ = nCurrCnxOb(); // declare a connection object
// batch in beggining lets know is batch of array of request sent as [q]uery parameter
// currFrmQArr array is built with redundante mysql requests on each webpage load.
doCFrmQ["q"] = "batch" + JSON.stringify(currFrmQArr);  
doCFrmQ["cb"] = "setMFormArr";  // callback function
doNurQComm(doCFrmQ); // send the connection object

When using the Android app QuickOrder, the database connections will fault to the android sqlite database.
You can load your remote webpage in QuickOrder android app, and use the javascript interface functions with your remote website.
These interface functions are not tested or validated, use with caution.
Some attempts to synch the native android app database/media with the local/remote Lamp stack website database/media was made using timestamps on essential databases at x_aa-edit-synch.js
Keep in mind that the Android app sqlite database is mirrored/copy of your web site database structure.
For instance, the webdroid/assets/quickorder/db_schema.txt and the copy at browser/db_schema.txt should work as the structure for 
your webpages mysql database and are also used in the copyDataBase() functions found in the UtilSQLAdapter classes for Java browser and android app.


 
!! No security exploits checking, refactoring, form-connection validation, etc. are enforced with evenflow code.
Use it at your own risk. !!