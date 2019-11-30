del adminApp.jar
del adminApp.exe
rmdir /S /Q cbox\
xcopy /S /Y ..\boil\default-files\cbox .\cbox\
d:\j8sdk\bin\javac *.java
pause
d:\j8sdk\bin\jar cmf mainClass.txt adminApp.jar Launcher.class org njfbrowser lib
pause
"d:\progs\jsmooth\jsmoothcmd.exe" ..\boil\tools\packaging\adminapp.jsmooth
pause