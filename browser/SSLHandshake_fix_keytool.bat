rem #### for importing certificates and avoiding ssl handshake exception
rem #### see http://smartkey.co.uk/development/wotking-around-a-sslhandshakeexception/
rem #### the default keystore password for xampp is: changeit
d:\j8sdk\bin\keytool -import -keystore d:\j8sdk\jre\lib\security\cacerts -alias tomcat -file localhost.crt
pause