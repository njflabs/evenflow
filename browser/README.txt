this is the adminApp javafx web-browser and spreadsheet app folder.
adminApp interacts with your website by giving you browse and click-to-spreadsheet functionality.
The main class is found in njfbrowser.main.adminApp.
You can use the .bat files to launch adminApp either through the Launcher.class or doubling clicking adminApp.jar.

adminApp comes with some help files on adding your website database info to adminApp.
You can also browse to your webdroid/assets/quickorder/ website, go to a an item categroy page,
click the AppDB link found on the website left menu,
and it will take you to a helper page with a quick link to add the website database to adminApp.
When database is added, clicking on that same left menu AppDB link will switch adminApp to the spreadsheet
panel with that item category records. Edit records and click Upload.


Note:
If your JAVA_HOME enviromental values are not the same as your other JTR_HOME, JDK_HOME etc.,
you may run into problems building and launching adminApp browser.
Depending on your jdk build PATH and your JAVA_HOME path, you may want to check that they are the same version.
JRE and JDK. Or you may run into SSL handshke errors while loading https:// sites.
Also see setting up ssl certificate in the SSLHandshake_fix_keytool.bat file if your running a local web-server for testing.


