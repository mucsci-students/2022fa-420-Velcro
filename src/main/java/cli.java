package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.java.velcro.Model.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.google.gson.Gson;

public class cli {

	public static void printPrompt() {
		System.out.print("> ");
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String user = null;
		Instance thisInstance = new Instance();

		while (true) {
			System.out.print("Open in GUI mode? (yes/no): ");
			user = in.next();
			if (user.equals("yes")) {
				app.main(null);
				return;
			}
			if (user.equals("no")) {
				System.out.println("Type \"help\" for command options.");
				break;
			}
			if (!user.equals("yes") || !user.equals("no"))
				System.out.println("Response not recognized. Answer \"yes\" or \"no\".");
		}
		if (user == "yes")
			return;

		String launch = " -\t launchGUI (launches GUI; prompts user to save current state)\n";
		String addClass = " -\t addClass <classname>\n";
		String deleteClass = " -\t deleteClass <classname>\n";
		String renameClass = " -\t renameClass <classname> <newclassname>\n";
		String renameMethod = " -\t renameMethod <classname> <methodname> <methodnewname>\n";
		String methodAddParam = " -\t methodAddParam <classname> <methodname> <parametername> <parametertype>\n";
		String methodRemoveParam = " -\t methodRemoveParam <classname> <methodname> <parametername>\n";
		String methodClearParam = " -\t methodClearParam <classname> <methodname>\n";
		String addField = " -\t addField <classname> <fieldname> <fieldtype>\n";
		String renameField = " -\t renameField <classname> <fieldname> <newfieldname>\n";
		String deleteField = " -\t removeField <classname> <fieldname>\n";
		String addRelation = " -\t addRelation <classname> <sourceclass> <destinationclass> <relationshiptype>\n";
		String deleteRelation = " -\t deleteRelation <sourceclass> <destinationclass>\n";
		String classContents = " -\t classContents <classname>\n";
		String save = " -\t save <filename>\n";
		String load = " -\t load <filename>\n";
		String exit = " -\t exit\n";

		while (true) {
			printPrompt();
			user = in.nextLine();
			String param[] = user.split(" ");

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
					app.main(null);
					return;
				} else {
					app.main(null);
					return;
				}
			case "help":
				System.out.println(launch + addClass + deleteClass + renameClass + renameMethod
						+ methodAddParam + methodRemoveParam + methodClearParam + addField + renameField + deleteField
						+ addRelation + deleteRelation + save + load + exit);
				break;
			case "addClass":
				thisInstance.addClass(param[1]);
				System.out.println("Class added.");
				break;
			case "deleteClass":
				thisInstance.removeClass(param[1]);
				System.out.println("Class deleted.");
				break;
			case "renameClass":
				Classes classObj1;
				try {
					classObj1 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				classObj1.rename(param[1], param[2]);
				System.out.println("Class renamed.");
				break;
			case "renameMethod":
				Classes classobj1;
				try {
					classobj1 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Methods methodObj1;
				try {
					methodObj1 = classobj1.getMethod(param[2]);
				} catch (NullPointerException e) {
					System.out.println("Method not found!");
					break;
				}				
				methodObj1.rename(param[3]);
				System.out.println("Method renamed.");
				break;
			case "methodAddParam":
				Classes classobj2;
				try {
					classobj2 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Methods methodObj2;
				try {
					methodObj2 = classobj2.getMethod(param[2]);
				} catch (NullPointerException e) {
					System.out.println("Method not found!");
					break;
				}
				methodObj2.addParam(param[3], param[4]);
				System.out.println("Parameter added.");
				break;
			case "methodRemoveParam":
				Classes classobj3;
				try {
					classobj3 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Methods methodObj3;
				try {
					methodObj3 = classobj3.getMethod(param[2]);
				} catch (NullPointerException e) {
					System.out.println("Method not found!");
					break;
				}		
				methodObj3.removeParam(param[3]);
				System.out.println("Parameter removed.");
				break;
			case "methodClearParam":
				Classes classobj4;
				try {
					classobj4 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Methods methodObj4;
				try {
					methodObj4 = classobj4.getMethod(param[2]);
				} catch (NullPointerException e) {
					System.out.println("Method not found!");
					break;
				}		
				methodObj4.clearParam();
				System.out.println("Parameters cleared.");
				break;
			case "renameField":
				Classes classobj5;
				try {
					classobj5 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Fields fieldObj5;
				try {
					fieldObj5 = classobj5.getField(param[2]);
				} catch (NullPointerException e) {
					System.out.println("Field not found!");
					break;
				}		
				fieldObj5.rename(param[3]);
				System.out.println("Field renamed.");
				break;
			case "addRelation":
				if (!param[3].toLowerCase().equals("aggregation") && !param[3].toLowerCase().equals("composition") && !param[3].toLowerCase().equals("inheritance") && !param[3].toLowerCase().equals("realization")) {
					System.out.println("Relationships must be one of: Aggregation, Composition, Inheritance, or Realization. Please re-enter command.");
					break;
				}
				Classes classobj6;
				try {
					classobj6 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				classobj6.addRelationship(param[1], param[2], param[3]);
				System.out.println("Relationship added.");
				break;
			case "deleteRelation":
				Classes classobj7;
				try {
					classobj7 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Classes classobj8;
				try {
					classobj8 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Relationships relobj7;
				try {
					relobj7 = thisInstance.getClass(param[1]).getRelationship(param[1], param[2]);
				} catch (NullPointerException e) {
					System.out.println("Relationship not found!");
					break;
				}
				classobj7.removeRelationship(param[1], param[2]);
				classobj8.removeRelationship(param[1], param[2]);
				System.out.println("Relationship removed.");
				break;
			case "deleteField":
				Classes classobj9;
				try {
					classobj9 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				Fields fieldObj9;
				try {
					fieldObj9 = classobj9.getField(param[2]);
				} catch (NullPointerException e) {
					System.out.println("Field not found!");
					break;
				}		
				System.out.println("Field removed.");
				break;
			case "save":
				File newFile = new File(param[1]);
				BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
				Gson gson1 = new Gson();
				writer.append(gson1.toJson((thisInstance)));
				writer.close();
				System.out.println("File saved.");
				break;
			case "load":
				Gson gson2 = new Gson();
				thisInstance = gson2.fromJson(new FileReader(param[1]), Instance.class);
				System.out.println("File loaded.");
				break;
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
				break;
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
				break;
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
				break;
			case "addField":
				Classes classobj11;
				try {
					classobj11 = thisInstance.getClass(param[1]);
				} catch (NullPointerException e) {
					System.out.println("Class not found!");
					break;
				}
				if (!param[3].toLowerCase().equals("string") && !param[3].toLowerCase().equals("void") &&!param[3].toLowerCase().equals("int") &&!param[3].toLowerCase().equals("float") && !param[3].toLowerCase().equals("double") && !param[3].toLowerCase().equals("char")) {
					System.out.println("Field type must be one of: void, string, int, float, double, or char!");
					break;
				}
				classobj11.addField(param[2], param[3]);
				System.out.println("Field added.");
			case "exit":
				return;
			}
		}
	}
}
