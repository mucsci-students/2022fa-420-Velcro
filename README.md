#Last Updated: 9/28/2022

##CONTENTS OF THIS FILE
 
 * Introduction
 * Requirements
 * Installation
 * Configuration 
 * Creating an .Exe
 * Maintainers

###INTRODUCTION
In order to run our program, we advise that the user installs and runs it on the Eclipse IDE.
This was the IDE that we used to develop the program and works well on both Windows and Mac.
After downloading the Eclipse IDE, download the most recent version of our program from github.
The file will be downloaded in the form of a zip file. Extract the file and copy it into whichever
directory you wish to work from.

###REQUIREMENTS

 * Eclipse IDE version: 2019-06 (4.12.0)
 * Current version of the GUI downloadable from ![github](https://github.com/mucsci-students/2022fa-420-Velcro)
 * Java version 18.0.2.1
 
###INSTALLATION
 * 
 
###CONFIGURATION
 * Open the Eclipse IDE, click on the file drop-down tab in the top-left of the window and 	click 'import.'
 * A window will open promping you to choose an install wizard. Double-click 'Projects from 	Folder or Archive', then click the 'Directory' button.
 * Navigate to the folder where you saved the program and select it. This will 
	add the folder to the box in the middle of the window.
 * Once this is done click 'Finish'. You should see the folder 
	in the 'Package Explorer' panel along the left side of the IDE.
 * Click the drop-down arrow next to the filename and click the drop-down arrow next to the 	'src' folder.
 * Right-click on the package name 'velcro', hover over 'Build Path', then click on 	'Configure Build Path.'
 * Navigate to the 'Libraries' tab and delete any and all paths under 'Classpath'.
	Click on 'Classpath' and click the 'Add JARs' button along the right-hand side of the 	window.
 * This will open a window titled 'JAR Selection.' Click the drop-down arrow next to our 	folder containing the program, then click the drop-down arrow next to the folder named 	'lib' and Ctrl+Click to select all three libraries.
 * Press 'OK', then press 'Apply and Close'. You are now ready to run the program.
 * You can run a program either by clicking on the green button with a white 'play' symbol 	at the top of the IDE, or by right-clicking on any java file in the project, hovering 	over 'Run As', pressing 'Run Configurations' and pressing 'Run'in the bottom-right of 	the opened window.
 * If you run your program using the latter method, make sure that the 'Project Name' 	matches the name of the file containing the program and that 'Main Class:' is 	velcro.LandingPage. 

####CREATING AN .EXE
* Install Launch4j to the host machine from this location: https://sourceforge.net/projects/launch4j/files/launch4j-3/3.14/
* Open a new Windows Explorer page (Windows + E keys). Open your Java folder, which is located in your Program Files folder (paste %ProgramFiles(x86)% into your explorer address bar, or %ProgramFiles% on 32-bit systems). Open the latest-numbered JRE folder in your Java folder.
* Create a new folder on your desktop (or other easily-accessible location). Inside the new folder, create another folder entitled “jre.”
* Right-click and drag the “bin” and “lib” folders from your Java folder to the jre folder, and select “copy here.”
* Within Eclipse, right-click the project name and select “Export…” -> “Runnable JAR file” (within the Java Folder).
* Under Launch configuration, navigate to and select the "cli" file of the Velcro project. In the export destination section, navigate to the desktop folder you created that contains the “jre” folder, and select “Extract required libraries into generated JAR.” Make sure the destination includes a memorable file name ending with “.jar”. Click Finish. If asked about repacking referenced libraries, select Okay.
* Your desktop folder should now contain a jar file and a jre folder, which contains two folders named bin and lib. 
* Run Launch4j.
* In the “Output file” field at the top, click the folder icon, navigate to your desktop folder, and enter a name for the finished .exe. Note: the filename *must* end in “.exe”. In the “Jar” field, click the folder icon and navigate to/select the .jar file you saved in the desktop folder.
* Click the “Classpath” header item at the top, check the “Custom classpath” toggle, click the folder icon, and again select the .jar file in your desktop folder.
* Click the “Header” header item at the top, click the option for Header Type: Console.
* Click the “JRE” header item at the top, and in the “Bundled JRE paths” field type in the three letters: jre.
* Click the “Build Wrapper” button at the top (it looks like a gear). Navigate to the desktop folder you created earlier, and give the textbox a name for the Launch4j config file that it creates.
* The .exe file should now be created in the desktop folder. You can run it by double-clicking the .exe file or, without leaving Launch4j, by clicking the “Test wrapper” button that looks like a play button at the top.

####MAINTAINERS
	* Jon Beare
	* Dylon McGrann
	* Greg Sinclair
	* Cole Stoudt
	* Benedikt Wagenlehner

