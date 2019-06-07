# SmartHome

Prerequisites:
1. MySql Server
2. Apache Tomcat
3. Magentix

3.1 Magentix installation
      Download zip : http://gti-ia.upv.es/sma/tools/magentix2/archivos/v2211/magentix2-2.1.1.zip
      Run magentix-setup.exe to configure the platform. You will be asked for a mysql root password and a tomcat user.
      Run Start-Magentix.bat to start the platform.
      To stop the platform run Stop-Magentix.bat

Application execution:
      after starting Magentix using Start-Magentix.bat, open a terminal and place yourself under the location where the jar 
      is located and write java -jar SmartHouse.jar
