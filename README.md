#Last Updated: 11/4/2022

##CONTENTS OF THIS FILE
 
 * Introduction
 * Requirements
 * Installation
 * Configuration 
 * Design Pattern Implementations
 * Build
 * Command Line Interface Use
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

####DESIGN PATTERN IMPLEMENTATIONS
 * MVC - as represented by various model, view, and controller classes.
 * Observer - implemented as native ActionListener class; used extensively in MenuController.
 * Memento - instantiated as the Memento class, which acts to support undo/redo functions by maintaining a list of Instance class objects.
 * Iterator - instantiated as the IteratorList class, which abstracts the trasversal of a list of ZEllipse objects, which represent current classes and are traversed during updates and certain methods.
 * Null Object - inside the Memento class, a nested class entitled NullInstance has been created. The Null Object acts as a bookend for the undo and redo stacks and serves functionally to prevent NullPointer exceptions, is immutable and contains declared attributes that do not alter the program, and, through its constructor and getter implementations, exactly one NullInstance exists during the lifecycle of the application (thereby satisfying the Singleton requirement).
 * Singleton - See Null Object above.

####BUILD
POM file provided for Maven build. Installation of Maven required.

To build, open a command line in the project's main folder, and run: mvn clean package

An OS-compatible file, entitled Velcro-3.0.0, will be built in the new Target folder. Windows users are recommended to run the package by running the command: java -jar ./target/Velcro-3.0.0.jar.

###COMMAND LINE INTERFACE USE
When operating the command line interface, use the command --help to list all available commands.

####MAINTAINERS
	* Jon Beare
	* Dylon McGrann
	* Greg Sinclair
	* Cole Stoudt
	* Benedikt Wagenlehner

