/**
 * Filename: cli.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Command line interface initialization and interpreter.
 * 
 */

package main.java.velcro.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import main.java.velcro.Model.*;
import java.util.Scanner;
import main.java.velcro.Model.Instance;
import main.java.velcro.main.*;

import com.google.gson.Gson;

public class cli {
	
	static String[] command = {"help", "launchgui", "addclass", "deleteclass", "renameclass", "renamemethod", "methodaddparam", "methodremoveparam", "methodclearparam", "addfield", "renamefield", "deletefield", "addrelation", "deleterelation", "classcontents", "save", "load", "exit"};
	static String[] names = new String[100];
	
	public static void printPrompt() {
		System.out.print("> ");
	}
	
	public static void roboPrint(String match) throws AWTException {
		String format = match.toUpperCase();
		char curr;
		int value;
		int index = 0;
		Robot robot = new Robot();
		robot.delay(200);
		
		while (index < format.length()) {
			curr = format.charAt(index);
			value = (int) curr;
			robot.keyPress(value);
			robot.keyRelease(value);
			index++;
		}
		return;
	}
	
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
	
	public static void printName(String[] match, String[] ogString) throws AWTException {
		Robot robot = new Robot();
		if (match[0] == null) {
			System.out.println("Name not found");
			printPrompt();
			for (int i = 0; i < ogString.length; i++) {
				roboPrint(ogString[i]);
				robot.keyPress(KeyEvent.VK_SPACE);
			}
			return;
		}
		else if (match[1] == null) {
			int index = 0;
			printPrompt();
			while (index != (ogString.length - 2)) {
				roboPrint(ogString[index]);
				robot.keyPress(KeyEvent.VK_SPACE);
				index++;
			}
			roboPrint(match[0]);
			return;
		}
		else {
			for (int i = 0; i < match.length; i++) {
				if (match[i] == null) {
					printPrompt();
					for (int j = 0; j < ogString.length - 1; j++) {
						roboPrint(ogString[j]);
						robot.keyPress(KeyEvent.VK_SPACE);
					}
					return;
				}
				System.out.println("\t" + match[i]);
			}
		}
	}
	
	public static String[] searchCommand(String user) {
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
	
	public static String[] searchName(String user) {
		int matches = 0;
		int index = 0;
		String[] closestMatch = new String[100];
		
		for (int i = 0; i < names.length; i++) {
			for (int j = 0; j < user.length(); j++) {
				try {
					if (user.charAt(j) == names[i].charAt(j)) 
						matches++;
				}
				catch (StringIndexOutOfBoundsException | NullPointerException e) {
					continue;
				}
			}
			if (matches == user.length()) {
				closestMatch[index] = names[i];
				index++;
			}
			matches = 0;
		}
		return closestMatch;
	}
	
	public static void executeProgram(Scanner in, String user, Instance thisInstance) throws IOException {
		String param[] = user.split(" ");
		if (param[0].equals("")) 
			return;
		switch (param[0]) {
		case "launchgui":
			System.out.print("Save? ");
			user = in.next();
			if (user.equals("yes")) {
				File newFiles = new File(param[0]);
				BufferedWriter writers = new BufferedWriter(new FileWriter(newFiles, true));
				Gson gsons = new Gson();
				writers.append(gsons.toJson((thisInstance)));
				writers.close();
				DrawingGUI.main(null);
				return;
			} else {
				DrawingGUI.main(null);
				return;
			}
		case "help":
			String launch = " -\t launchgui (launches GUI; prompts user to save current state)\n";
			String addClass = " -\t addclass <classname>\n";
			String deleteClass = " -\t deleteclass <classname>\n";
			String renameClass = " -\t renameclass <classname> <newclassname>\n";
			String renameMethod = " -\t renamemethod <classname> <methodname> <methodnewname>\n";
			String methodAddParam = " -\t methodaddparam <classname> <methodname> <parametername> <parametertype>\n";
			String methodRemoveParam = " -\t methodremoveparam <classname> <methodname> <parametername>\n";
			String methodClearParam = " -\t methodclearparam <classname> <methodname>\n";
			String addField = " -\t addfield <classname> <fieldname> <fieldtype>\n";
			String renameField = " -\t renamefield <classname> <fieldname> <newfieldname>\n";
			String deleteField = " -\t removefield <classname> <fieldname>\n";
			String addRelation = " -\t addrelation <classname> <sourceclass> <destinationclass> <relationshiptype>\n";
			String deleteRelation = " -\t deleterelation <sourceclass> <destinationclass> <relationshiptype>\n";
			String classContents = " -\t classcontents <classname>\n";
			String save = " -\t save <filename>\n";
			String load = " -\t load <filename>\n";
			String exit = " -\t exit";
			System.out.println(launch + addClass + deleteClass + renameClass + renameMethod
					+ methodAddParam + methodRemoveParam + methodClearParam + addField + renameField + deleteField
					+ addRelation + deleteRelation + save + load + exit);
			return;
		case "addclass":
			try {
				thisInstance.addClass(param[1]);
				names[findSpace()] = param[1];
				System.out.println("Class added.");
				return;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a class name");
				return;
			}
		case "deleteClass":
			thisInstance.removeClass(param[1]);
			System.out.println("Class deleted.");
			return;
		case "renameClass":
			Classes classObj1;
			try {
				classObj1 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			classObj1.rename(param[1], param[2]);
			System.out.println("Class renamed.");
			return;
		case "renameMethod":
			Classes classobj1;
			try {
				classobj1 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Methods methodObj1;
			try {
				methodObj1 = classobj1.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return;
			}				
			methodObj1.rename(param[3]);
			System.out.println("Method renamed.");
			return;
		case "methodAddParam":
			Classes classobj2;
			try {
				classobj2 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Methods methodObj2;
			try {
				methodObj2 = classobj2.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return;
			}
			methodObj2.addParam(param[3], param[4]);
			System.out.println("Parameter added.");
			return;
		case "methodRemoveParam":
			Classes classobj3;
			try {
				classobj3 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Methods methodObj3;
			try {
				methodObj3 = classobj3.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return;
			}		
			methodObj3.removeParam(param[3]);
			System.out.println("Parameter removed.");
			return;
		case "methodClearParam":
			Classes classobj4;
			try {
				classobj4 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Methods methodObj4;
			try {
				methodObj4 = classobj4.getMethod(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Method not found!");
				return;
			}		
			methodObj4.clearParam();
			System.out.println("Parameters cleared.");
			return;
		case "renameField":
			Classes classobj5;
			try {
				classobj5 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Fields fieldObj5;
			try {
				fieldObj5 = classobj5.getField(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Field not found!");
				return;
			}		
			fieldObj5.rename(param[3]);
			System.out.println("Field renamed.");
			return;
		case "addRelation":
			if (!param[3].toLowerCase().equals("aggregation") && !param[3].toLowerCase().equals("composition") && !param[3].toLowerCase().equals("inheritance") && !param[3].toLowerCase().equals("realization")) {
				System.out.println("Relationships must be one of: Aggregation, Composition, Inheritance, or Realization. Please re-enter command.");
				return;
			}
			Classes classobj6;
			try {
				classobj6 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			classobj6.addRelationship(param[1], param[2], param[3]);
			System.out.println("Relationship added.");
			return;
		case "deleteRelation":
			Classes classobj7;
			try {
				classobj7 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Classes classobj8;
			try {
				classobj8 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Relationships relobj7;
			try {
				relobj7 = thisInstance.getClass(param[1]).getRelationship(param[1], param[2]);
			} catch (NullPointerException e) {
				System.out.println("Relationship not found!");
				return;
			}
			classobj7.removeRelationship(param[1], param[2]);
			classobj8.removeRelationship(param[1], param[2]);
			System.out.println("Relationship removed.");
			return;
		case "deleteField":
			Classes classobj9;
			try {
				classobj9 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			Fields fieldObj9;
			try {
				fieldObj9 = classobj9.getField(param[2]);
			} catch (NullPointerException e) {
				System.out.println("Field not found!");
				return;
			}		
			System.out.println("Field removed.");
			return;
		case "save":
			File newFile = new File(param[1]);
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
			Gson gson1 = new Gson();
			writer.append(gson1.toJson((thisInstance)));
			writer.close();
			System.out.println("File saved.");
			return;
		case "load":
			Gson gson2 = new Gson();
			thisInstance = gson2.fromJson(new FileReader(param[1]), Instance.class);
			System.out.println("File loaded.");
			return;
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
			return;
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
			return;
		case "seeAllClassContents":
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
			return;
		case "addField":
			Classes classobj11;
			try {
				classobj11 = thisInstance.getClass(param[1]);
			} catch (NullPointerException e) {
				System.out.println("Class not found!");
				return;
			}
			if (!param[3].toLowerCase().equals("string") && !param[3].toLowerCase().equals("void") &&!param[3].toLowerCase().equals("int") &&!param[3].toLowerCase().equals("float") && !param[3].toLowerCase().equals("double") && !param[3].toLowerCase().equals("char")) {
				System.out.println("Field type must be one of: void, string, int, float, double, or char!");
				return;
			}
			classobj11.addField(param[2], param[3]);
			System.out.println("Field added.");
		case "exit":
			return;
		}
		return;
	}
	
	public static int findSpace() {
		for (int index = 0; index < 100; index++) {
			if (names[index] == null)
				return index;
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException, AWTException {
		Scanner in = new Scanner(System.in);
		String user = null;
		Instance thisInstance = new Instance();
		
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
			if (user.equals("exit")) {
				System.out.println("Goodbye");
				return;
			}
			
			String param[] = user.split(" ");
			try {
				if (param[param.length-1].equals("/")) {
					System.out.println("I am robot. I have 2 fathers.");
					if (param.length == 2) {
						printCommand(searchCommand(param[0]));
						continue;
					}
					else if (param.length > 2) {
						printName(searchName(param[param.length - 2]), param);
						continue;
					}
				}
				else executeProgram(in, user, thisInstance);
			}
			catch (StringIndexOutOfBoundsException e) {
					System.out.println("Command not found");
			}
			printPrompt();
		}
	}
}
