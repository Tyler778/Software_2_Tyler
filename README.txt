# Software_2_Tyler - Consulting Scheduling Manager

Created By Tyler Quante
Contact at tquant1@wgu.edu

Version 1.0.0.0 9/26/2021
Created in NetBeans 12.3
JavaFX 17.0.0.1
Java JDF 11.0.10
Java Runtime Environment 1.8.0_301


########################################################################################################################################
To run properly outisde the IDE, it's important that your JavaFX Lib folder is placed in your "C:\Program Files\Java\javafx-sdk-17.0.0.1\lib"

After that, just click on the RunSoftware2Project.bat in the dist folder to launch outside the IDE.  Your login attempts outside the IDE will be recorded in the
login_activity.txt within the dist folder.
########################################################################################################################################
To run properly inside the IDE, ensure JavaFX is added as a modulepath and add the following code to your Project Properties VM Options:
--module-path "C:\Program Files\Java\javafx-sdk-17.0.0.1\lib" --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics

Replace the path with wherever your javafx lib jars are located.

Your login attemps will be recorded at login_activity.txt within the Software_2_Tyler folder.  
########################################################################################################################################

Login with 'test' username and 'test' password.  You will be notified on login of nearby appointments or lackthere of.  You may add, modify, and delete
appointments as you wish in addition to customers.  Your reports will be automatically updated with up to date information grabbed from the database.


The third report consists of appointments filtered by Users who created them.  



School project for hypothetical scenario for modifying and creating appointments for a consulting company that works worldwide in severeal timezones.
All data is backed up to a database that is persistent.
