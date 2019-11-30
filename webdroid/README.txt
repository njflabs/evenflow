this is the android app folder its basically a WebView with added functions for quickorder/ website.
the files found in assets/quickorder are also your actual web files.
This should be taken into consideration when editing your quickorder/ files.

this project is built from the command line.
just run the b.bat in the /bin folder
to install apk, edit the bin/i.bat file (w/ text editor) to point to your QuickOrder-debug.apk

See the evenflow/README.md for information on the javascript - web files and how they 
interact with android using javascript bridges etc. 
the main class is com.quickorder.QuickOrder

The android app is curently a bit crude.
In the near future:
Automated voice processing on adding shop items.
Barcode and qrcode scannng of items.
Better PayPal IPN coding.
Enhanced image media capturing and editing:
gif, mp4, with item captions - item prices, etc.




