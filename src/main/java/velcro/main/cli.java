/**
 * Filename: cli.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Command line interface initialization and interpreter.
 * 
 */


package velcro.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import velcro.Model.*;
import java.util.Scanner;
import com.google.gson.Gson;

public class cli {
	
	
	// Help line descriptions.
	static String launch = " -\t launchGUI (launches GUI; prompts user to save current state)\n";
	static String addClass = " -\t addClass <classname>\n";
	static String deleteClass = " -\t deleteClass <classname>\n";
	static String renameClass = " -\t renameClass <classname> <newclassname>\n";
	static String renameMethod = " -\t renameMethod <classname> <methodname> <methodnewname>\n";
	static String methodAddParam = " -\t methodAddParam <classname> <methodname> <parametername> <parametertype>\n";
	static String methodRemoveParam = " -\t methodRemoveParam <classname> <methodname> <parametername>\n";
	static String methodClearParam = " -\t methodClearParam <classname> <methodname>\n";
	static String addField = " -\t addField <classname> <fieldname> <fieldtype>\n";
	static String renameField = " -\t renameField <classname> <fieldname> <newfieldname>\n";
	static String deleteField = " -\t removeField <classname> <fieldname>\n";
	static String addRelation = " -\t addRelation <classname> <sourceclass> <destinationclass> <relationshiptype>\n";
	static String deleteRelation = " -\t deleteRelation <sourceclass> <destinationclass>\n";
	static String listAllRelationships = " -\t listAllRelationships\n";
	static String updateRelation = " -\t updateRelation <sourceclass> <destinationclass> <newrelationshiptype\n";
	static String classContents = " -\t classContents <classname>\n";
	static String listAllClassContents = " -\t listAllClassContents\n";
	static String save = " -\t save <filename>\n";
	static String load = " -\t load <filename>\n";
	static String exit = " -\t exit";
	
	static String[] command = {"help", "launchGUI", "addClass", "deleteClass", "renameClass", "renameMethod", "methodAddParam", "methodRemoveParam", "methodClearParam", "addField", "renameField", "deleteField", "addRelation", "deleteRelation", "updateRelation", "listAllRelationships", "classContents", "listAllClassContents", "save", "load", "exit"};
	
	
	// Prompt definition.
	public static void printPrompt() {
		System.out.print("> ");
	}

	// Robot actions, for use in auto-completion.
	public static void roboPrint(String match) throws AWTException {
		Robot robot = new Robot();
		robot.delay(200);
		switch (match) {
			case "help":
				robot.keyPress(KeyEvent.VK_H);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_P);
				return;
			case "launchGUI":
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_U);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyPress(KeyEvent.VK_H);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_G);
				robot.keyPress(KeyEvent.VK_U);
				robot.keyPress(KeyEvent.VK_I);
				return;
			case "addClass":
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_S);
				return;
			case "deleteClass":
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_S);
				return;
			case "renameClass":
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_M);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_S);
				return;
			case "renameMethod":
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_M);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_M);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_H);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_D);
				return;
			case "methodAddParam":
				robot.keyPress(KeyEvent.VK_M);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_H);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_M);
				return;
			case "methodRemoveParam":
				robot.keyPress(KeyEvent.VK_M);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_H);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_M);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_M);
				return;
			case "methodClearParam":
				robot.keyPress(KeyEvent.VK_M);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_H);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_M);
				return;
			case "addField":
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_D);
				return;
			case "renameField":
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_M);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_D);
				return;
			case "deleteField":
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_F);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_D);
				return;
			case "addRelation":
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_N);
				return;
			case "deleteRelation":
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_N);
				return;
			case "listAllRelationships":
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_H);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_P);
				robot.keyPress(KeyEvent.VK_S);
				return;
			case "updateRelation":
				robot.keyPress(KeyEvent.VK_U);
				robot.keyPress(KeyEvent.VK_P);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_N);
				return;
			case "classContents":
				robot.keyPress(KeyEvent.VK_C);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_S);
				return;
			case "listAllClassContents":
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyPress(KeyEvent.VK_S);
				return;
			case "save":
				robot.keyPress(KeyEvent.VK_S);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyPress(KeyEvent.VK_E);
				return;
			case "load":
				robot.keyPress(KeyEvent.VK_L);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyPress(KeyEvent.VK_D);
				return;
			case "exit":
				robot.keyPress(KeyEvent.VK_E);
				robot.keyPress(KeyEvent.VK_X);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyPress(KeyEvent.VK_T);
				return;
		}	
	}
	
	// Prints auto-filled suggestion and command prompt.
	public static void printCommand(String[] match) throws AWTException {
		if (match[0] == null) {
			System.out.println("Command not found");
			printPrompt();
			return;
		}
		else if (match[1] == null) {
			printPrompt();
			roboPrint(match[0]);
			return;
		}
		else {
			for (int i = 0; i < match.length; i++) {
				if (match[i] == null) {
					printPrompt();
					return;
				}
				System.out.println("\t" + match[i]);
			}
		}
	}
	
	// Finds closest match of input string.
	public static String[] search(String user) {
		int matches = 0;
		int index = 0;
		String[] closestMatch = new String[10];
		
		for (int i = 0; i < command.length; i++) {
			for (int j = 0; j < user.length(); j++) {
				try {
					if (user.charAt(j) == command[i].charAt(j)) 
						matches++;
				}
				catch (StringIndexOutOfBoundsException e) {
					continue;
				}
			}
			if (matches == user.length()) {
				closestMatch[index] = command[i];
				index++;
			}
			matches = 0;
		}
		return closestMatch;
	}
	
	// Main execution of input command.
	public static boolean executeProgram(Scanner in, String user, Instance thisInstance) throws IOException {
		String param[] = user.split(" ");
		if (param[0].equals("")) 
			return true;
		switch (param[0]) {
		case "launchGUI":
			System.out.print("Save? ");
			user = in.next();
			if (user.equals("yes")) {
				File newFiles = new File(param[0]);
				BufferedWriter writers = new BufferedWriter(new FileWriter(newFiles, true));
				Gson gsons = new Gson();
				writers.append(gsons.toJson((thisInstance)));
				writers.close();
				DrawingGUI.main(null);
				return true;
			} else {
				DrawingGUI.main(null);
				return true;
			}
		case "help":
			System.out.println(launch + addClass + deleteClass + renameClass + renameMethod
					+ methodAddParam + methodRemoveParam + methodClearParam + addField + renameField + deleteField
					+ addRelation + deleteRelation + updateRelation + listAllRelationships + listAllClassContents+ save + load + exit);
			return true;
		case "addClass":
			thisInstance.addClass(param[1]);
			System.out.println("Class added.");
			return true;
		case "deleteClass":
			thisInstance.removeClass(param[1]);
			System.out.println("Class deleted.");
			return true;
		case "renameClass":
			Classes classObj1;
			try {
				classObj1 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			classObj1.rename(param[1], param[2]);
			System.out.println("Class renamed.");
			return true;
		case "renameMethod":
			Classes classobj1;
			try {
				classobj1 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Methods methodObj1;
			try {
				methodObj1 = classobj1.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return true;
			}				
			methodObj1.rename(param[3]);
			System.out.println("Method renamed.");
			return true;
		case "methodAddParam":
			Classes classobj2;
			try {
				classobj2 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Methods methodObj2;
			try {
				methodObj2 = classobj2.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return true;
			}
			methodObj2.addParam(param[3], param[4]);
			System.out.println("Parameter added.");
			return true;
		case "methodRemoveParam":
			Classes classobj3;
			try {
				classobj3 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Methods methodObj3;
			try {
				methodObj3 = classobj3.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return true;
			}		
			methodObj3.removeParam(param[3]);
			System.out.println("Parameter removed.");
			return true;
		case "methodClearParam":
			Classes classobj4;
			try {
				classobj4 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Methods methodObj4;
			try {
				methodObj4 = classobj4.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return true;
			}		
			methodObj4.clearParam();
			System.out.println("Parameters cleared.");
			return true;
		case "renameField":
			Classes classobj5;
			try {
				classobj5 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Fields fieldObj5;
			try {
				fieldObj5 = classobj5.getField(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Field not found!");
				return true;
			}		
			fieldObj5.rename(param[3]);
			System.out.println("Field renamed.");
			return true;
		case "addRelation":
			if (!param[3].toLowerCase().equals("aggregation") && !param[3].toLowerCase().equals("composition") && !param[3].toLowerCase().equals("inheritance") && !param[3].toLowerCase().equals("realization")) {
				System.out.println("Relationships must be one of: Aggregation, Composition, Inheritance, or Realization. Please re-enter command.");
				return true;
			}
			Classes classobj6;
			try {
				classobj6 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			classobj6.addRelationship(param[1], param[2], param[3]);
			System.out.println("Relationship added.");
			return true;
		case "deleteRelation":
			Classes classobj7;
			try {
				classobj7 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Classes classobj8;
			try {
				classobj8 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Relationships relobj7;
			try {
				relobj7 = thisInstance.getClass(param[1]).getRelationship(param[1], param[2]);
			} catch (NullPointerException e) {
				System.out.println("Relationship not found!");
				return true;
			}
			classobj7.removeRelationship(param[1], param[2]);
			classobj8.removeRelationship(param[1], param[2]);
			System.out.println("Relationship removed.");
			return true;
		case "updateRelation":
			Classes classobj17;
			try {
				classobj17 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Classes classobj18;
			try {
				classobj18 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Relationships relobj17;
			try {
				relobj17 = thisInstance.getClass(param[1]).getRelationship(param[1], param[2]);
			} catch (NullPointerException e) {
				System.out.println("Relationship not found!");
				return true;
			}
			classobj17.removeRelationship(param[1], param[2]);
			classobj18.removeRelationship(param[1], param[2]);
			classobj17.addRelationship(param[1], param[2], param[3]);
			classobj18.addRelationship(param[1], param[2], param[3]);
			System.out.println("Relationship changed.");
			return true;
		case "deleteField":
			Classes classobj9;
			try {
				classobj9 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			Fields fieldObj9;
			try {
				fieldObj9 = classobj9.getField(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Field not found!");
				return true;
			}		
			System.out.println("Field removed.");
			return true;
		case "save":
			File newFile = new File(param[1]);
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
			Gson gson1 = new Gson();
			writer.append(gson1.toJson((thisInstance)));
			writer.close();
			System.out.println("File saved.");
			return true;
		case "load":
			Gson gson2 = new Gson();
			thisInstance = gson2.fromJson(new FileReader(param[1]), Instance.class);
			System.out.println("File loaded.");
			return true;
		case "listAllRelationships":
			for (int h = 0; h < thisInstance.classList.size(); h++) {
				System.out.println(thisInstance.classList.get(h).getName());
				for (int i = 0; i < thisInstance.classList.get(h).relationshipList.size(); i++) {
					System.out.println("   Relationship: "
							+ thisInstance.classList.get(h).relationshipList.get(i).source + " (source), "
							+ thisInstance.classList.get(h).relationshipList.get(i).destination + " (destination), "
							+ thisInstance.classList.get(h).relationshipList.get(i).type + " (type)");
				}
			}
			return true;
		case "classContents":
			Classes classobj0 = thisInstance.getClass(param[1]);
			System.out.println(classobj0.getName());
			for (int i = 0; i < classobj0.relationshipList.size(); i++) {
				System.out.println("   Relationship: " + classobj0.relationshipList.get(i).source + " (source), "
						+ classobj0.relationshipList.get(i).destination + " (destination), "
						+ classobj0.relationshipList.get(i).type + " (type)");
			}
			for (int i = 0; i < classobj0.fieldList.size(); i++) {
				System.out.println("      Field: " + classobj0.fieldList.get(i).getName());
			}
			for (int i = 0; i < classobj0.methodList.size(); i++) {
				System.out.println("         Method: " + classobj0.methodList.get(i).getName());
				for (int j = 0; j < classobj0.methodList.get(i).paramList.size(); j++) {
					System.out.println(
							"            Parameter: " + classobj0.methodList.get(i).paramList.get(j).getName()
									+ " (name), " + classobj0.methodList.get(i).paramList.get(j).getType());
				}
			}
			return true;
		case "listAllClassContents":
			for (int h = 0; h < thisInstance.classList.size(); h++) {
				System.out.println(thisInstance.classList.get(h).getName());
				for (int i = 0; i < thisInstance.classList.get(h).relationshipList.size(); i++) {
					System.out.println("   Relationship: "
							+ thisInstance.classList.get(h).relationshipList.get(i).source + " (source), "
							+ thisInstance.classList.get(h).relationshipList.get(i).destination + " (destination), "
							+ thisInstance.classList.get(h).relationshipList.get(i).type + " (type)");
				}
				for (int i = 0; i < thisInstance.classList.get(h).fieldList.size(); i++) {
					System.out.println("      Field: " + thisInstance.classList.get(h).fieldList.get(i).getName());
				}
				for (int i = 0; i < thisInstance.classList.get(h).methodList.size(); i++) {
					System.out.println(
							"         Method: " + thisInstance.classList.get(h).methodList.get(i).getName());
					for (int j = 0; j < thisInstance.classList.get(h).methodList.get(i).paramList.size(); j++) {
						System.out.println("            Parameter: "
								+ thisInstance.classList.get(h).methodList.get(i).paramList.get(j).getName()
								+ " (name), "
								+ thisInstance.classList.get(h).methodList.get(i).paramList.get(j).getType());
					}
				}
			}
			return true;
		case "addField":
			Classes classobj11;
			try {
				classobj11 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return true;
			}
			if (!param[3].toLowerCase().equals("string") && !param[3].toLowerCase().equals("void") &&!param[3].toLowerCase().equals("int") &&!param[3].toLowerCase().equals("float") && !param[3].toLowerCase().equals("double") && !param[3].toLowerCase().equals("char")) {
				System.out.println("Field type must be one of: void, string, int, float, double, or char!");
				return true;
			}
			classobj11.addField(param[2], param[3]);
			System.out.println("Field added.");
		case "exit":
			return true;
		}
		return false;
	}
	
	// Main method, initialization.
	public static void main(String[] args) throws IOException, AWTException {
		Scanner in = new Scanner(System.in);
		String user = null;
		Instance thisInstance = new Instance();

		// Continuing prompt for input.
		while (true) {
			System.out.print("Open in GUI mode? (yes/no): ");
			user = in.next();
			if (user.equals("yes")) {
				DrawingGUI.main(null);
				return;
			}
			if (user.equals("no")) {
				System.out.println("Type \"help\" for command options.");
				break;
			}
			if (!user.equals("yes") || !user.equals("no"))
				System.out.println("Response not recognized. Answer \"yes\" or \"no\".");
		}
		
		while (true) {
			user = in.nextLine();
			if (!executeProgram(in, user, thisInstance)) {
				try {
					printCommand(search(user));
				}
				catch (StringIndexOutOfBoundsException e) {
					System.out.println("Command not found");
					printPrompt();
				}
			}
			else printPrompt();
		}
	}
}
