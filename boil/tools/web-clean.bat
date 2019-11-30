rmdir /S /Q ..\..\webdroid\assets\quickorder\images\pimgs\
rmdir /S /Q ..\..\webdroid\assets\quickorder\_p\
xcopy /S /E /Y  ..\default-files\pimgs ..\..\webdroid\assets\quickorder\images\pimgs\
xcopy /S /E /Y  ..\default-files\_p ..\..\webdroid\assets\quickorder\_p\
cd yuicompressor4
call run-yuihelper.bat
CHOICE /C YN /M "REMOVE BOM and VTI (Y = yes - n = no)"
IF %ERRORLEVEL% EQU 1 call run-RemoveVTI.bat
cd ..\..\
pause