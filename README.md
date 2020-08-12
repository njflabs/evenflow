<table style="margin: 0 auto; max-width: 900px" align="center">
	<tr>
		<td><p><strong>- About:</strong><br>evenflow is an abstract attempt to use the javafx 
browser, LAMP stack and android to deploy and maintain simple desktop,web,mobile applications. <br>
		Emphasis is on e-commerce software components and how they can be 
		integrated to work together with minimal maintenance.<br>A working
		<strong>demo</strong> can be currently found here:
		<a href="http:///quickorder.eu5.net">http:///quickorder.eu5.net</a><br>
		</p>
<p><strong>- Quick Setup:</strong><br>upload the whole project or the 
<a href="webdroid/assets/quickorder">webdroid/assets/quickorder</a> folder to your web 
site.<br>create a mysql database 
named evenflow.<br>open <a href="browser/db_schema.txt">browser/db_schema.txt</a> and import it to your database.<br>
open<a href="webdroid/assets/quickorder/_p/do.php"> quickorder/_p/do.php</a> and edit the dbusername and password variables.<br>
point your native web browser to http://...assets/quickorder/index.html<br>
<a href="webdroid/bin/b.bat">build</a> and or 
<a href="webdroid/bin/i.bat">install</a> the
<a href="webdroid/bin/QuickOrder-debug.apk">android app apk</a>.<br>
in the browser/ folder <a href="browser">launch</a> the adminApp browser app.<br><br><strong>- Build:</strong><br>the build 
folder is boil/<br>the javafx, android app and web page compression are done from 
command line.<br>See <a href="boil">boil/README.txt</a><br> </p>
		<p><strong>- Structure:<br></strong>/boil [builld folder]<br>/browser [main 
		javafx browser app with spreadsheet 
panel]<br>
/webdroid [android app]<br>/webdroid/assets/quickorder [android app content and 
e-commerce webpage]<br>
		<br>- <strong>Javascript 
to Java Interface Bridge:</strong><br>
evenflow javafx browser and android app rely heavily onthe JSI_ javascript 
inerface files to interact with the WebViews, etc.<br>See using the javafx 
browser AppDB links on the webpage while running the javafx app for example.<br>
Pages such as edit or view category will show the AppDB clickable link on left 
that will swap browser panel to editable spreadsheet style panel.<br><br>
<img src="webdroid/assets/quickorder/images/img4.jpg" style="height: 168px; width: 300px"><img src="webdroid/assets/quickorder/images/img6.jpg" style="height: 168px; width: 300px"><br><br>- <strong>User Interface:</strong><br>
Rendering of the user interface mostly relies on simple html and javascript, json.<br><br><strong>- Database:</strong><br>Import 
		the db_schema.txt file found in browser/ folder to your database.<br>See 
		webdroid/README.txt for info on interacting with your database.<br>
<br>- <strong>
WorkFlow:</strong><br>webdroid/assets is your e-commerce webpage. you can build 
it directly to your android app.<br>Your android app enhances your e-commerce 
webpage with camera and speech capabilites among others.<br>The javafx broser 
enhances your e-commerce web page with spreadsheet capablities.<br><br>
<strong>- Changes:</strong><br>Feb 30, 2019<br>-&nbsp; boil/ folder added 
containing pages-scrips-classes to clean and build evenflow project.-<br>-&nbsp; 
adminApp jar file added. read&nbsp; browser/README for more info.<br>-.. 
adminApp javafx browser made more user friendly.<br>-&nbsp; README.txt files 
added to most main project folders. [READ THEM. it may save you tons of time]<br>
-&nbsp; android main package changed to com.quickorder<br><br>
		Nov 19, 2019<br>-&nbsp; have been made: the main file was index.php, but due to 
incompatibility issues with androids webview, it has been renamed to index.html. 
See the .htaccess file on how AddHandler is used to parse .html extension in 
php. You can also change your actual php.ini file to do this, but requires more 
steps.<br>- material icon html tags converted to use numeric reference 
(compatibility with javafx browser
<a href="webdroid/assets/quickorder/fonts/materialicons/numerical_vhar_ref.txt">
see reference here</a>).<br><br>- <strong>
TODO:</strong><br>
clean up and comment code.<br>clean up the UI.<br>
<br>- <strong>
Credits:</strong><br>
Most of the credits for open sourced code that is used in this app is usually 
included in the code.<br>If you 
deserve some credit, let us know.<br> </p>
		</td>
	</tr>
</table>
