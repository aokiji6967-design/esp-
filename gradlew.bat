@echo off
setlocal
set GRADLE_VERSION=8.14.3
set DIST=%USERPROFILE%\.gradle\esp-wrapper\gradle-%GRADLE_VERSION%-bin.zip
set DIR=%USERPROFILE%\.gradle\esp-wrapper\gradle-%GRADLE_VERSION%
if not exist "%DIR%\bin\gradle.bat" (
  if not exist "%USERPROFILE%\.gradle\esp-wrapper" mkdir "%USERPROFILE%\.gradle\esp-wrapper"
  if not exist "%DIST%" powershell -NoProfile -ExecutionPolicy Bypass -Command "Invoke-WebRequest -UseBasicParsing -Uri 'https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip' -OutFile '%DIST%'"
  powershell -NoProfile -ExecutionPolicy Bypass -Command "Expand-Archive -Force '%DIST%' '%USERPROFILE%\.gradle\esp-wrapper'"
)
call "%DIR%\bin\gradle.bat" %*
endlocal
