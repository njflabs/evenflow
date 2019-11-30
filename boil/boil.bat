CHOICE /C ABDWN /M "Build (a = all - b = browser - d = android - w = web-pages - n = none)"
IF %ERRORLEVEL% EQU 1 GOTO BLDALL
IF %ERRORLEVEL% EQU 2 GOTO BLDBROWSER
IF %ERRORLEVEL% EQU 3 GOTO BLDDROID
IF %ERRORLEVEL% EQU 4 GOTO BLDHTML
IF %ERRORLEVEL% EQU 5 GOTO BLDEND
:BLDALL
cd tools
call web-clean.bat
cd ..\browser
call sun8compile.bat
cd ..\webdroid\bin
call b.bat
pause
GOTO BLDEND
:BLDBROWSER
cd ..\browser
call sun8compile.bat
GOTO BLDEND
:BLDDROID
cd ..\webdroid\bin
call b.bat
GOTO BLDEND
:BLDHTML
cd tools
call web-clean.bat
GOTO BLDEND
:BLDEND
pause