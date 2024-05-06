@echo off
where java 2> nul > nul
if %ERRORLEVEL% NEQ 0 (
	echo Java is not installed on this computer. Press enter to download the installer.
	pause > nul
	echo Opening web browser. Click here if nothing happens: https://aka.ms/download-jdk/microsoft-jdk-21.0.3-windows-x64.msi
	explorer "https://aka.ms/download-jdk/microsoft-jdk-21.0.3-windows-x64.msi"
	echo Run install.bat again when JDK 21 is installed
	timeout 5 > nul
) else (
	.\gradlew run
)
@echo on