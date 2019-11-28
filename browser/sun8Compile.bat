set classpath=./;./lib/;
del adminApp.jar
d:\j8sdk\bin\javac *.java
pause
d:\j8sdk\bin\jar cmf mainClass.txt adminApp.jar Launcher.class org njfbrowser lib
pause
