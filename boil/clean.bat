cd D:\
cd D:\www\htdocs\evenflow
rmdir /S /Q D:\www\htdocs\evenflow\browser\cbox\
rmdir /S /Q D:\www\htdocs\evenflow\webdroid\assets\quickorder\images\pimgs\
rmdir /S /Q D:\www\htdocs\evenflow\webdroid\assets\quickorder\_p\
xcopy /S /Y D:\www\htdocs\evenflow\boil\cbox D:\www\htdocs\evenflow\browser\cbox
xcopy /S /Y D:\www\htdocs\evenflow\boil\pimgs D:\www\htdocs\evenflow\webdroid\assets\quickorder\images\pimgs
xcopy /S /Y D:\www\htdocs\evenflow\boil\_p D:\www\htdocs\evenflow\webdroid\assets\quickorder\_p
cd browser
call sun8Compile.bat
cd D:\www\htdocs\evenflow\boil\yuicompressor4
call run-yuihelper.bat
call run-RemoveVTI.bat